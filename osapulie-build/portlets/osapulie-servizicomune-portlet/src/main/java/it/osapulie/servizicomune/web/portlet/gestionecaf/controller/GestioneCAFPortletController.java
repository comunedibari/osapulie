/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecaf.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.activation.MimetypesFileTypeMap;
import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.json.configurazione.ConfigurazioneServizi;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.servizicomune.model.DelegaReportModel;
import it.osapulie.servizicomune.service.FirmaGrafometricaService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.gestionecaf.form.DelegaModel;
import it.osapulie.servizicomune.web.portlet.gestionecaf.validator.GestioneCAFPortletValidator;
import it.osapulie.servizicomune.web.utils.DelegaModelUtil;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.util.FirmaGrafometricaResponse;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;

import com.liferay.portal.kernel.util.PrefsPropsUtil;

/**
 * Portlet controller per la gestione delle deleghe da parte dei CAF.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneCAFPortletController")
@RequestMapping("view")
public class GestioneCAFPortletController extends BaseController {

    private static final String REPORT_PATH = "/reports/delegaDichiarazioniRichiesta.jrxml";
    private static final String REPORT_PREFIX_NAME = "Delega_dichiarazioni_richiesta_";
    private static final String REPORT_SUFFIX_NAME = ".pdf";

    private final Logger log = LoggerFactory.getLogger(GestioneCAFPortletController.class);

    @Inject
    private PortletHelper helper;

    @Inject
    private DelegaService delegaService;

    @Inject
    private ProfiloUtenteService profiloUtenteService;

    @Inject
    private ServizioService servizioService;

    @Inject
    private ComuneISAService comuneISAService;

    @Inject
    private CommonService commonService;

    @Inject
    private ConfigurazioneService configurazioneService;

    @Inject
    private ResourceBundleMessageSource messageSource;

    @Inject
    private GestioneCAFPortletValidator gestioneCAFPortletValidator;

    @Inject
    private ReportService reportService;

    @Inject
    private AuditRepository auditRepository;

    @Value("#{applicationProperties['gestioneutenti.page.url']}")
    private String gestioneUtentiPageUrl;

    @Value("#{applicationProperties['modello.delega.url']}")
    private String modelloDelegaUrl;

    @Value("#{applicationProperties['radio.value.allegato']}")
    private String radioValueAllegato;

    @Value("#{applicationProperties['radio.value.firma.grafometrica']}")
    private String radioValueFirmaGrafometrica;

    @Inject
    private FirmaGrafometricaService firmaService;
    /**
     * Default entry quando la portlet viene visualizzata per la prima volta.
     *
     * @param model il view model da popolare
     * @return il view ID da renderizzare
     */
    @RenderMapping
    public String homePage(@RequestParam(required = false) String codiceFiscale, @ModelAttribute("cafCorrente") Azienda cafCorrente, Model model, PortletRequest request) {

        model.addAttribute("delegaList", getListaDeleghe(cafCorrente, model, request));
        model.addAttribute("statoIscrizioneSelect", "");
        model.addAttribute("cfProfiloUtenteCittadino", codiceFiscale);

        return toLocalViewPath("home");
    }

    /**
     * Carica la lista deleghe relative all'azienda/CAF.
     *
     * @param cafCorrente
     *
     * @param model
     * @param request
     * @return
     */
    public List<Delega> getListaDeleghe(Azienda cafCorrente, Model model, PortletRequest request) {

        log.debug("homePage :: entering method");
        List<Delega> delegaList = null;
        try {

            String nome = request.getParameter("delegante.nome");
            String cognome = request.getParameter("delegante.cognome");
            String cf = request.getParameter("delegante.codifceFiscale");
            String stato = request.getParameter("statoDelegaSelect");

            model.addAttribute("nomeDelegante", nome);
            model.addAttribute("cognomeDelegante", cognome);
            model.addAttribute("cfDelegante", cf);
            model.addAttribute("statoDelegaSelect", stato);

            Boolean statoBoolean = null;
            if ((stato != null) && !stato.equals("")) {
                if (stato.equals(PortletConstants.STATO_DELEGA_ATTIVA)) {
                    statoBoolean = true;
                }
                else {
                    statoBoolean = false;
                }
            }

            if (cafCorrente != null) {
                delegaList = delegaService.searchDeleghe(cognome, nome, cf, statoBoolean, cafCorrente.getId());
            }

        }
        catch (Exception e) {
            log.error("getListaDeleghe :: " + e.getMessage(), e);
        }
        return delegaList;
    }

    @ModelAttribute("cafCorrente")
    public Azienda getCAFCorrente(PortletRequest request) {

        Azienda cafCorrente = null;

        UserPreferences userPreferences = helper.getUserPreferences(request);
        String partitaIva = userPreferences.getPartitaIvaServizio();

        OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

        List<Azienda> aziende = osApulieUserDetails.getAziende();
        // Caricamento da profilo selezionato
        if (partitaIva != null) {
            if (aziende != null) {
                for (Azienda azienda : aziende) {
                    String tipoAzienda = azienda.getTipo();
                    if (azienda.isAttiva() && tipoAzienda != null && (tipoAzienda.equals(TipoAzienda.CAF.toString()) || tipoAzienda.equals(TipoAzienda.AZIENDA.toString())) && azienda.getPartitaIva().equals(partitaIva)) {
                        cafCorrente = azienda;
                        break;
                    }
                }
            }
        }
        // Caricamento primo CAF disponibile
        else {
            if (aziende != null) {
                for (Azienda azienda : aziende) {
                    String tipoAzienda = azienda.getTipo();
                    if (azienda.isAttiva() && tipoAzienda != null && (tipoAzienda.equals(TipoAzienda.CAF.toString()) || tipoAzienda.equals(TipoAzienda.AZIENDA.toString()))) {
                        cafCorrente = azienda;
                        break;
                    }
                }
            }
        }

        return cafCorrente;
    }

    @ModelAttribute(value = "delega")
    public DelegaModel getDelega(@RequestParam(required = false) String idDelega, PortletRequest request) {

        // Distinzione tra servizi disponibili ed associati
        Delega delega = null;

        if (idDelega != null) {
            delega = delegaService.getDelegaByPk(Long.parseLong(idDelega));
            request.getPortletSession().setAttribute("idDelega", idDelega);
        }
        else {
            request.getPortletSession().removeAttribute("idDelega");
        }

        DelegaModel delegaModel = DelegaModelUtil.getDelegaModelByDelega(delega);
        UserPreferences userPreferences = helper.getUserPreferences(request);

        if (delega == null) {
            delega = new Delega();
            ProfiloUtenteCittadino profiloUtenteCittadino = new ProfiloUtenteCittadino();
            Azienda azienda = new Azienda();
            if (getCAFCorrente(request) != null) {
                azienda = getCAFCorrente(request);
            }
            delega.setDelegante(profiloUtenteCittadino);
            delega.setDelegato(azienda);
            if (userPreferences.getIdComuneIsa() != null && userPreferences.getIdComuneIsa() != -1) {
                delegaModel.setIdComuneIsa(userPreferences.getIdComuneIsa());
            }
            // Precaricamento Comune ISA da sede CAF (se presente)
            else {
                Indirizzo sede = azienda.getSede();
                if (sede != null) {
                    Comune comune = sede.getComune();
                    if (comune != null) {
                        ComuneISA comuneByCodiceIstat = comuneISAService.getComuneByCodiceIstat(comune.getCodiceIstatAN());
                        if (comuneByCodiceIstat != null) {
                            delegaModel.setIdComuneIsa(comuneByCodiceIstat.getId());
                        }
                    }
                }
            }
        }
        else {
            delegaModel.setIdComuneIsa(delega.getComuneIsa().getId());
        }

        if (delegaModel != null) {
            setDelegaModel(request, delega, delegaModel);
        }


        return delegaModel;

    }

    /**
     * @param request
     * @param delega
     * @param delegaModel
     */
    private void setDelegaModel(PortletRequest request, Delega delega, DelegaModel delegaModel) {

        // Caricamento servizi: filtro in base al comune di appartenenza
        List<Servizio> serviziComuneIsaList = getServiziAttiviList(request, delegaModel.getIdComuneIsa());
        request.setAttribute("serviziComuneIsaList", serviziComuneIsaList);

        Map<Long, Servizio> serviziDelegaMap = new HashMap<Long, Servizio>();

        // Controllo servizi delega in base al comune selezionato

        List<String> serviziAssociatiStrings = delegaModel.getServiziAssociatiStrings();
        if (delega != null && delega.getComuneIsa() != null) {
            ComuneISA comuneIsaDelega = delega.getComuneIsa();
            if (comuneIsaDelega.getId().equals(delegaModel.getIdComuneIsa())) {
                // Creazione mappe
                // Mappa servizi attualmente associati alla delega
                if (delega != null && delega.getServizi() != null) {
                    List<Servizio> serviziDelegaList = delega.getServizi();
                    for (Servizio servizio : serviziDelegaList) {
                        serviziDelegaMap.put(servizio.getId(), servizio);
                    }
                }
            }
        }
        else {
            if (serviziAssociatiStrings != null) {
                for (String string : serviziAssociatiStrings) {
                    serviziDelegaMap.put(Long.parseLong(string), new Servizio());
                }
            }
        }
        request.setAttribute("serviziDelegaMap", serviziDelegaMap);
    }

    /**
     * @param request
     * @return
     */
    private List<Servizio> getServiziAttiviList(PortletRequest request, long idComuneIsa) {

        List<Servizio> result = new ArrayList<Servizio>();
        try {
            // Caricamento dei servizi abilitati al CAF
            List<Servizio> elencoServizi = servizioService.getServiziAttiviForDelegaByComuneISA(idComuneIsa);
            if (elencoServizi != null) {
                for (Servizio servizio : elencoServizi) {
                    if (configurazioneService.isServizioEnableForCAF(servizio.getCodiceServizio(), idComuneIsa)) {
                        result.add(servizio);
                    }
                }
            }
        }
        catch (Exception e) {
            log.error("getListaServizi :: " + e.getMessage(), e);
        }
        return result;
    }

    @ActionMapping(params = "action=ricercaUtente")
    public void ricercaUtente(@RequestParam String codiceFiscale, @ModelAttribute("cafCorrente") Azienda cafCorrente, @ModelAttribute("delega") DelegaModel delegaModel, Model model,
                              ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

        try {
            ProfiloUtenteCittadino profiloUtenteCittadino = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscale);

            if (profiloUtenteCittadino == null) {
                model.addAttribute("formError", messageSource.getMessage("message.label.utenteNonTrovato", null, request.getLocale()));

                Map<String, String> sourceUrlParameters = new HashMap<String, String>();
                sourceUrlParameters.put("action", "home");
                model.addAttribute("gestioneUtentiPageUrl", commonService.getGestioneUtentiUrl(request, codiceFiscale, sourceUrlParameters, null));
                model.addAttribute("cfProfiloUtenteCittadino", codiceFiscale);
            }
            else {
                boolean delegaJustExists = false;
                List<Delega> delegheDelegati = profiloUtenteCittadino.getDelegheDelegati();
                if (delegheDelegati != null) {
                    for (Delega delega : delegheDelegati) {
                        if (delega.getDelegato().getId().equals(cafCorrente.getId())) {
                            delegaJustExists = true;
                            break;
                        }
                    }
                }
                // Se il cittadino ha già una delega --> informo l'utente...
                if (delegaJustExists) {
                    model.addAttribute("message", messageSource.getMessage("message.label.delegaEsistente", new String[] { codiceFiscale.toUpperCase() }, request.getLocale()));
                }
                // ...altrimenti redirect verso la pagina di creazione delega
                else {
                    delegaModel.setCodiceFiscaleDelegante(codiceFiscale.toUpperCase());
                    response.setRenderParameter("action", "editDelega");
                }
            }
        }
        catch (ServiceLayerException e) {
            log.error("ricercaUtente :: " + e.getMessage(), e);
        }
        catch (NoSuchMessageException e) {
            log.error("ricercaUtente :: " + e.getMessage(), e);
        }
    }

    @RenderMapping(params = "action=editDelega")
    public String editDelegaPage(Model model, PortletRequest request) {

        model.addAttribute("comuniIsaList", getComuniIsaList());
        model.addAttribute("modelloDelegaUrl", modelloDelegaUrl);
        try {
            model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
        } catch (Exception e) {
            log.error("renderEditForm :: " + e.getMessage(), e);
        }

        return toLocalViewPath("editDelega");
    }

    private List<ComuneISA> getComuniIsaList() {

        List<ComuneISA> result = new ArrayList<ComuneISA>();

        try {
            // Caricamento comuni abilitati ad essere utilizzati dal CAF
            List<ComuneISA> comuniList = comuneISAService.getComuniAttivi();
            if (comuniList != null) {
                for (ComuneISA comuneISA : comuniList) {
                    ConfigurazioneServizi configurazioneServiziComune = configurazioneService.getConfigurazioneServiziComune(comuneISA.getId());
                    if (configurazioneServiziComune != null) {
                        List<it.osapulie.domain.json.configurazione.Servizio> servizi = configurazioneServiziComune.getServizi();
                        if (servizi != null && !servizi.isEmpty()) {
                            result.add(comuneISA);
                        }
                    }
                }
            }
        }
        catch (Exception e) {
            log.warn("getComuniIsaList :: " + e.getMessage());
        }

        return result;
    }

    @ActionMapping(params = "action=saveDelega")
    public void saveDelega(@RequestParam(required = false, value = "cambio") String cambio, @ModelAttribute("cafCorrente") Azienda delegato, @ModelAttribute("delega") DelegaModel delegaModel,
                           BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus, PortletSession session) throws UnsupportedEncodingException, IOException {

        log.debug("saveDelega :: entering method");
        log.info("DelegaModel: "+delegaModel.getFirma());
       
        	
	        // Cambio comune
	        if (cambio != null) {
	            Delega delega = null;
	
	            if (delegaModel.getIdDelega() != 0) {
	                delega = delegaService.getDelegaByPk(delegaModel.getIdDelega());
	            }
	
	            if (delega == null) {
	                ProfiloUtenteCittadino profiloUtenteCittadino = new ProfiloUtenteCittadino();
	                delega = new Delega();
	                Azienda azienda = new Azienda();
	                delega.setDelegante(profiloUtenteCittadino);
	                delega.setDelegato(azienda);
	            }
	
	            setDelegaModel(request, delega, delegaModel);
	
	            response.setRenderParameter("action", "editDelega");
	            return;
	        }
	        // Salvataggio
	        else {
	
	            gestioneCAFPortletValidator.validate(delegaModel, bindingResult);
	
	            if (bindingResult.hasErrors()) {
	                setDelegaModel(request, null, delegaModel);
	                response.setRenderParameter("action", "editDelega");
	                return;
	            }
	
	            String uuidStr=UUID.randomUUID().toString();
	            session.setAttribute("UUID", uuidStr);
	            AuditManager auditManager= AuditConfiguration.configure().audit()
	                    .addUuidOperazione(uuidStr)
	                    .addInizioOperazione()
	                    .addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
	                    .addPaginaCorrente(helper.getCurrentPageName(request))
	                    .setOrigin(Origin.getIp(request))
	                    .addInfo("controller","GestioneCAFPortletController");
	//			.addMetaField("azienda", delegato)
	//			.addMetaField("operatore", helper.getOsApulieUserDetails().getProfiloUtenteCittadino())
	//			.addMetaField("userPreferences", helper.getUserPreferences(request));
	
	            Delega delega = null;
	            // Update delega
	            if (delegaModel.getIdDelega() != 0) {
	                delega = delegaService.getDelegaByPk(delegaModel.getIdDelega());
	
	                auditManager
	                        .addOperazioneRichiesta("editDelega");
	                //.addMetaField("oldDelega", delega);
	
	            }
	            // New Delega
	            else {
	
	                auditManager
	                        .addOperazioneRichiesta("newDelega");
	
	                String codiceFiscaleDelegante = delegaModel.getCodiceFiscaleDelegante();
	                ProfiloUtenteCittadino delegante = profiloUtenteService.getProfiloUtenteCittadinoByCf(codiceFiscaleDelegante);
	
	                delega = new Delega();
	                delega.setIdDelegante(delegante.getId());
	                delega.setIdDelegato(delegato.getId());
	                delega.setDelegante(delegante);
	                delega.setDelegato(delegato);
	                delega.setDataRichiesta(new Date());
	                delega.setDataAttivazione(new Date());
	                delega.setAttiva(true);
	            }
	
	            // Setto il Comune ISA
	            ComuneISA comuneISA = comuneISAService.getComuneByPk(delegaModel.getIdComuneIsa());
	            delega.setComuneIsa(comuneISA);
	
	            List<Servizio> serviziAssociati = new ArrayList<Servizio>();
	
	            // Aggiunta servizi
	            List<String> serviziAssociatiStrings = delegaModel.getServiziAssociatiStrings();
	            if (serviziAssociatiStrings != null) {
	                for (String string : serviziAssociatiStrings) {
	                    Servizio servizio = servizioService.getServizioById(Long.parseLong(string));
	                    serviziAssociati.add(servizio);
	                }
	            }
	
	            delega.setServizi(serviziAssociati);
	
	            //Bisogna distinguere i casi dove si sceglie la sottomissione della delega con allegato o con la firma grafometrica
	            if ((delegaModel.getTipoAcquisizione()).equalsIgnoreCase(radioValueAllegato) && delegaModel.getAllegato() != null && !delegaModel.getAllegato().isEmpty()){
	                CommonsMultipartFile allegato = delegaModel.getAllegato();
	                delega.setFirmaGrafometrica(false);
	                delega.setAllegato(allegato.getBytes());
	                String md5 = CheckSumGenerator.generateCheckSum(allegato.getBytes());
	                log.debug(md5);
	                delega.setChecksum(md5);
	            }
	
				
				//MS Con firma grafometrica
				delega.setFirmaGrafometrica(true);
				ProfiloUtenteCittadino ope = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
				ProfiloUtenteCittadino delegante = profiloUtenteService.getProfiloUtenteCittadinoByCf(delegaModel.getCodiceFiscaleDelegante());
				//Costruzione bean popolato da mandare al jrxml
				DelegaReportModel delegaReport = DelegaModelUtil.getDelegaReportModel(delegaModel, delegato, ope, delegante, serviziAssociati);
				List<DelegaReportModel> beans = new ArrayList<DelegaReportModel>();
				//delega.setAllegato_documento(delegaModel.getDocumento().getBytes());
				beans.add(delegaReport);
				
				 if(delegaModel.getFirma() == null || StringUtils.isBlank(delegaModel.getFirma())) {
					// Creazione pdf da jrxml
					byte[] reportBytes = reportService.jrxmlToPdf(beans, REPORT_PATH);
					
					String md5 = CheckSumGenerator.generateCheckSum(reportBytes);
					log.debug(md5);
					delega.setChecksum(md5);
					
					//Richiamo il servizio di firmaGrafometrica
					
					FirmaGrafometricaResponse responseFirma = null;
					try {
						log.info("Richiamo il servizio di Firma Grafometrica");
						 responseFirma = firmaService.invokeFirmaService(delegaReport, reportBytes);
						 
						 if(responseFirma != null && responseFirma.getUrl() != null) {
							 model.addAttribute("firma", responseFirma.getUrl());
							 model.addAttribute("token", responseFirma.getToken());
						     
							 response.setRenderParameter("action", "editDelega");	 
							 
						 }
					} catch (Exception e) {
						log.error("Errore nellinvocazione del client: " + e.getMessage(), e);
					}
				 }
        
				 else {
	        	
		        	byte[] fileDownload = null;
		        	
		        	
		        	log.info("Salvataggio delega e richiamo del servizio di Wait");
		        		        	
		        	try {
		    			fileDownload = firmaService.waitAndSigned(delegaReport,delegaModel.getToken());
		    		} catch (Exception e) {
		    			// TODO Auto-generated catch block
		    			e.printStackTrace();
		    		}
		        	delega.setAllegato(fileDownload);
		    		//MS loggo la firma grafometrica
		    		//auditManager.addFirmaGrafoAcquisita(delegaModel.getStringFirma()); 
		    		
		    		if(delega.getAllegato() != null) {
		    			//MS QUI firma acquisita
		    			
		    			delega.setNomeAllegato(REPORT_PREFIX_NAME+delegaModel.getCodiceFiscaleDelegante()+REPORT_SUFFIX_NAME);
		    			delega.setnDocumento(delegaModel.getNumeroDocumento());
		    			delega.setDataScadenza(delegaModel.getDataScadenzaDocumento());
		    			delega.setRilasciatoDa(delegaModel.getRilasciatoDa());
		    			delega.setCfOperatore(helper.getOsApulieUserDetails().getProfiloUtenteCittadino().getCodiceFiscale());
		    			 
		    			auditManager
		    				.addImprontaDelega(delega.getChecksum());
		    				//.addMetaField("newDelega", delega);
		
		                try {
		
		                    delegaService.saveDelega(delega);
		
		                }
		                catch (ServiceLayerException e) {
		                    log.error("saveDelega :: " + e.getMessage(), e);
		
		                    auditManager
		                            .addFineOperazione()
		                            .addEsitoError()
		                            .addInfo("errorMessage", e.getMessage())
		                            //.addMetaField("error", e)
		                            .build()
		                            .exportUser(false, new Export() {
		                                @Override
		                                public void call(OperationExportFile op) throws IOException {
		                                    List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
		                                    if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
		                                        Date dateCreazione=new Date();
		                                        Audit a= new Audit();
		                                        a.setUserCod(op.getManager().getKeyUser());
		                                        a.setGiornoMeseAnno(op.getManager().getFolderDay());
		                                        a.setFileName(op.getManager().getCodFileName());
		                                        a.setPathFilesystem(op.getManager().getPath_Log_User());
		                                        a.setChecksum("0");
		                                        a.setDataCreazione(dateCreazione);
		                                        a.setDataUltimaModifica(dateCreazione);
		                                        a.setCodiceRegistro("0");
		                                        a.setStato("0");
		                                        a.setCons("0");
		                                        auditRepository.save(a);
		                                    }
		                                }
		                            });
		
		                    response.setRenderParameter("action", "editDelega");
		                    return;
		                }
		
		                // TODO aggiornamento profilo
		                auditManager
		                        .addFineOperazione()
		                        .addEsitoSuccess()
		                        .build()
		                        .exportUser(false, new Export() {
		                            @Override
		                            public void call(OperationExportFile op) throws IOException {
		                                List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
		                                if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
		                                    Date dateCreazione=new Date();
		                                    Audit a= new Audit();
		                                    a.setUserCod(op.getManager().getKeyUser());
		                                    a.setGiornoMeseAnno(op.getManager().getFolderDay());
		                                    a.setFileName(op.getManager().getCodFileName());
		                                    a.setPathFilesystem(op.getManager().getPath_Log_User());
		                                    a.setChecksum("0");
		                                    a.setDataCreazione(dateCreazione);
		                                    a.setDataUltimaModifica(dateCreazione);
		                                    a.setCodiceRegistro("0");
		                                    a.setStato("0");
		                                    a.setCons("0");
		                                    auditRepository.save(a);
		                                }
		                            }
		                        });
		                
		                 	model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		                }
		    		else
		            	model.addAttribute("formError", messageSource.getMessage("message.label.fileFirmatoNonSalvato", null, request.getLocale()));
		            response.setRenderParameter("action", toLocalViewPath("home"));
		
		            sessionStatus.setComplete();
				 }
	        }
    }

    @ResourceMapping("downloadAllegato")
    public void downloadAllegato(@RequestParam("id") Long id, ResourceRequest request, ResourceResponse response) throws IOException {
        Delega delega = delegaService.getDelegaByPk(id);
        byte[] allegato = delega.getAllegato();
        if (allegato != null && allegato.length > 0) {
            String reportFileName = String.format(delega.getNomeAllegato());
            String mimeType = new MimetypesFileTypeMap().getContentType(reportFileName);
            helper.sendStreamAsAttachment(allegato, response, "\"" + reportFileName + "\"", mimeType);
        }
    }
    

    @ActionMapping(params = "action=deleteDelega")
    public void deleteDelega(@RequestParam("idDelega") Long idDelega, @ModelAttribute("cafCorrente") Azienda delegato, BindingResult bindingResult, Model model, ActionRequest request,
                             ActionResponse response,PortletSession session, SessionStatus sessionStatus) throws UnsupportedEncodingException, IOException {
        UserPreferences userPreferences = helper.getUserPreferences(request);
        String uuidStr=UUID.randomUUID().toString();
        session.setAttribute("UUID", uuidStr);

        Delega dlg = delegaService.getDelegaByPk(idDelega);

        AuditManager auditManager = AuditConfiguration.configure().audit()
                .addUuidOperazione(uuidStr)
                .addInizioOperazione()
                .addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
                .addOperazioneRichiesta("deleteDelega")
                .addPaginaCorrente(helper.getCurrentPageName(request))
                .addImprontaDelega((dlg.getChecksum()!=null && !dlg.getChecksum().isEmpty()) ? dlg.getChecksum() : "")
                .setOrigin(Origin.getIp(request))
                .addInfo("controller","GestioneCAFPortletController");
//			.addMetaField("delega", dlg)
//			.addMetaField("azienda", delegato)
//			.addMetaField("operatore", helper.getOsApulieUserDetails().getProfiloUtenteCittadino())
//			.addMetaField("userPreferences", userPreferences);

        try{
            delegaService.deleteDelega(idDelega);
        }
        catch (ServiceLayerException e) {
            log.error("getListaDeleghe :: " + e.getMessage(), e);

            auditManager
                    .addFineOperazione()
                    .addInfo("errore message", e.getMessage())
                    //.addMetaField("errore", e)
                    .addEsitoError()
                    .build().exportUser(false, new Export() {

                @Override
                public void call(OperationExportFile op) throws IOException {

                    List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
                    if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
                        Date dateCreazione=new Date();
                        Audit a= new Audit();
                        a.setUserCod(op.getManager().getKeyUser());
                        a.setGiornoMeseAnno(op.getManager().getFolderDay());
                        a.setFileName(op.getManager().getCodFileName());
                        a.setPathFilesystem(op.getManager().getPath_Log_User());
                        a.setChecksum("0");
                        a.setDataCreazione(dateCreazione);
                        a.setDataUltimaModifica(dateCreazione);
                        a.setCodiceRegistro("0");
                        a.setStato("0");
                        a.setCons("0");
                        auditRepository.save(a);
                    }

                }
            });
        }

        model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));

        auditManager
                .addFineOperazione()
                .addEsitoSuccess()
                .build().exportUser(false, new Export() {

            @Override
            public void call(OperationExportFile op) throws IOException {

                List<Audit> ltsAudit= auditRepository.findByFileName(op.getManager().getCodFileName());
                if(ltsAudit==null || ltsAudit!=null && ltsAudit.size()<1) {
                    Date dateCreazione=new Date();
                    Audit a= new Audit();
                    a.setUserCod(op.getManager().getKeyUser());
                    a.setGiornoMeseAnno(op.getManager().getFolderDay());
                    a.setFileName(op.getManager().getCodFileName());
                    a.setPathFilesystem(op.getManager().getPath_Log_User());
                    a.setChecksum("0");
                    a.setDataCreazione(dateCreazione);
                    a.setDataUltimaModifica(dateCreazione);
                    a.setCodiceRegistro("0");
                    a.setStato("0");
                    a.setCons("0");
                    auditRepository.save(a);
                }

            }
        });
        sessionStatus.setComplete();
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public String maxUploadSizeExceededException(Exception exception, PortletRequest portletRequest) {

        log.error("maxUploadSizeExceededException :: " + exception.getMessage());

        Object idDelega = portletRequest.getPortletSession().getAttribute("idDelega");

        String maxUploadSize = String.valueOf(((MaxUploadSizeExceededException) exception).getMaxUploadSize());
        portletRequest.setAttribute("formError", messageSource.getMessage("Maximum.upload.size.exceded", new String[] { maxUploadSize }, portletRequest.getLocale()));
        if (idDelega != null) {
            portletRequest.setAttribute("delega", getDelega((String) idDelega, portletRequest));
        }
        portletRequest.setAttribute("comuniIsaList", getComuniIsaList());
        return toLocalViewPath("editDelega");
    }

    /**
     * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
     * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
     * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
     * "/gestionecaf/home" </code>
     *
     * @param viewName l'ID della view locale
     * @return il view ID completo del path a cui la vista appartiene
     */
    private static String toLocalViewPath(String viewName) {
        return "gestionecaf/" + viewName;
    }
}
