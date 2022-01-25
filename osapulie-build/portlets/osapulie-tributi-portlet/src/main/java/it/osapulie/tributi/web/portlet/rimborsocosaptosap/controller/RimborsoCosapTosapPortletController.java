package it.osapulie.tributi.web.portlet.rimborsocosaptosap.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.service.VisureTosapCosapService;
import it.osapulie.tributi.web.portlet.rimborsocosaptosap.form.DatiRimborsoCosapTosap;
import it.osapulie.tributi.web.portlet.rimborsocosaptosap.form.DatiRimborsoCosapTosap.PosizioneOsap;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente;
import it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapPermanenteRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiOsapTemporaneaRisposta;
import it.osapulie.util.DwhDataminingUtil;
import it.osapulie.util.DwhServiceAttributeUtil;
import it.osapulie.util.DwhTempiMediUtil;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.Origin;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Richiesta rimborso Cosap/Tosap.
 *
 * @author Gianluca Pindinelli
 */
@Controller("rimborsoCosapTosapPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiRimbCosapTosap", "param", "uploadItem" })
public class RimborsoCosapTosapPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RimborsoCosapTosapPortletController.class);

	private static final String SERVIZIO = "RICHIESTA RIMBORSO COSAP/TOSAP";

	private static final String REPORT_PATH = "/reports/rimborsoCosapTosap.jrxml";
	private static final String SUB_REPORT_PATH = "/reports/subreportRimborso.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/rimborsoCosapTosap_subreport1.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/rimborsoCosapTosap_subreport2.jrxml";
	private static final String SUB_REPORT_PATH_3 = "/reports/subreportRimborsoFooter.jrxml";
	private static final String REPORT_NAME = "RimborsoCosapTosap.pdf";

	private static final String JSP_PATH = "rimborsocosaptosap/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	private VisureTosapCosapService visureTosapCosapService;
 
	
	@Inject
	private DelegaService delegaService;
 
	@Inject
	private DwhService dwhService;
	
	@Inject
	private AuditDwhService auditDwhService;
	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiRimborsoCosapTosapValidator")
	private Validator rimborsoValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private CommonService commonService;

	@ExceptionHandler(Exception.class)
	public String handleExceptions(Exception e) {
		log.error("handleExceptions :: " + e.getMessage(), e);
		return "common/defError";
	}

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) throws Exception {

		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per il rimborso Cosap/Tosap.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getRimborsoCosapTosapForm")
	public void getRimborsoCosapTosapForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getRimborsoCosapTosapForm :: entering method");

		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		

		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+"  - START")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiRimborsoCosapTosap datiRimbCosapTosap = new DatiRimborsoCosapTosap();
		try {
			DatiUtente datiGen = null;
			ComponentiNucleoFamiliare componente = null;
			try {
				DatiAnagrafici dati = tributiService.richiediDatiAnagrafici(richiesta, userPreferences);

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();

				richiestaGen.setCodiceFiscale(codiceFiscale);
				datiGen = tributiService.richiediDatiAnagraficiAltriServizi(richiestaGen, userPreferences);
				componente = null;
				for (int k = 0; k < dati.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(dati.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = dati.getComponentiNucleoFamiliare().get(k);
					}
				}
			}
			catch (Exception e) {
				log.warn("getRimborsoCosapTosapForm :: " + e.getMessage());
			}

			// Controllo profilo cittadino/azienda
			if (userPreferences.getPartitaIvaServizio() != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(userPreferences.getPartitaIvaServizio());
				datiRimbCosapTosap.setRagSociale(aziendaByPiva.getRagioneSociale());
				datiRimbCosapTosap.setpIva(userPreferences.getPartitaIvaServizio());
				Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiRimbCosapTosap.setSedeLegale(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiRimbCosapTosap.setProvincia(comune.getProvincia().getSigla());
						datiRimbCosapTosap.setComune(comune.getDenominazione());
					}
				}
				if (componente != null && datiGen != null) {
					datiRimbCosapTosap.setCodiceFiscaleGiu(componente.getCodiceFiscale());
					datiRimbCosapTosap.setCodiceFiscaleRapp(componente.getCodiceFiscale());
					datiRimbCosapTosap.setNomeRapp(componente.getNome());
					datiRimbCosapTosap.setCognomeRapp(componente.getCognome());
					datiRimbCosapTosap.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbCosapTosap.setComuneNascitaRapp(comuneByCodiceISTAT.getDenominazione());
						datiRimbCosapTosap.setProvinciaNascitaRapp(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbCosapTosap.setSessoRapp(componente.getSesso());
					datiRimbCosapTosap.setIndirizzoResidenzaRapp(datiGen.getIndirizzo());
					datiRimbCosapTosap.setComuneResidenzaRapp(datiGen.getComuneResidenza());
					datiRimbCosapTosap.setProvResidenzaRapp(datiGen.getProvinciaResidenza());
					datiRimbCosapTosap.setCapResidenzaRapp(datiGen.getCap());

				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbCosapTosap.setCodiceFiscaleRapp(profiloUtente.getCodiceFiscale());
					datiRimbCosapTosap.setNomeRapp(profiloUtente.getNome());
					datiRimbCosapTosap.setCognomeRapp(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbCosapTosap.setComuneNascitaRapp(comune.getDenominazione());
							datiRimbCosapTosap.setProvinciaNascitaRapp(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbCosapTosap.setIndirizzoResidenzaRapp(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbCosapTosap.setComuneResidenzaRapp(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbCosapTosap.setProvResidenzaRapp(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
			}
			else {
				if (componente != null && datiGen != null) {

					datiRimbCosapTosap.setCodiceFiscale(componente.getCodiceFiscale());
					datiRimbCosapTosap.setNome(componente.getNome());
					datiRimbCosapTosap.setCognome(componente.getCognome());
					datiRimbCosapTosap.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));

					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");

					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbCosapTosap.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiRimbCosapTosap.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbCosapTosap.setSesso(componente.getSesso());

					datiRimbCosapTosap.setIndirizzoResidenza(datiGen.getIndirizzo());
					datiRimbCosapTosap.setComuneResidenza(datiGen.getComuneResidenza());
					datiRimbCosapTosap.setProvResidenza(datiGen.getProvinciaResidenza());
					datiRimbCosapTosap.setCapResidenza(datiGen.getCap());
				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbCosapTosap.setCodiceFiscale(profiloUtente.getCodiceFiscale());
					datiRimbCosapTosap.setNome(profiloUtente.getNome());
					datiRimbCosapTosap.setCognome(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbCosapTosap.setComuneNascita(comune.getDenominazione());
							datiRimbCosapTosap.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbCosapTosap.setIndirizzoResidenza(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbCosapTosap.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbCosapTosap.setProvResidenza(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				}
			}
			datiRimbCosapTosap.setAnno(String.valueOf((new Integer(DateUtils.getAnnoCorrente()) - 1)));

		}
		catch (Exception e) {
			log.error("getRimborsoCosapTosapForm :: " + e.getMessage(), e);
		}

		PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		model.addAttribute("datiRimbCosapTosap", datiRimbCosapTosap);
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		model.addAttribute("tipoOsap", "p");

		response.setRenderParameter("action", "renderRimborsoCosapTosapForm");
	}

	/**
	 * Restituisce la form per l'upload delle richieste e doc.
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response) {
		UploadItem item = new UploadItem();
		model.addAttribute("uploadItem", item);
		response.setRenderParameter("action", "renderUploadForm");
	}

	/**
	 * Gestisce l'upload dei file.
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param session
	 * @param request
	 * @param model
	 * @param selectNumAllegati
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati) throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		if (selectNumAllegati == null) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);
			}

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderUploadForm");
			}
			else {

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(request));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));
				// memorizzo la richiesta
				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(numeroProtocollo);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
				}

				// Invio email ad azienda
				if (partitaIvaServizio != null) {
					String subjectAzienda = messageSource.getMessage("mail.azienda.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
					try {
						tributiCommonService.sendMailToCompany(userPreferences, aziendaByPiva, subjectAzienda, SERVIZIO, numeroProtocollo);
					}
					catch (Exception e) {
						log.error("upload :: " + e.getMessage(), e);
					}
				}


				final String uuidOperazione=(String)session.getAttribute("UUID");
				
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
				.setNomeServizio(SERVIZIO)
				.getTempiMedi();
				
				auditDwhService.invokeAuditService(null, null, tempiMediDto);

				 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
					
					  
							public String updateServizioProtocollo() {
							 
								return numeroProtocollo;
							}
							public String updateServizioParametro3() {
								 
								return null;
							}
							public String updateServizioParametro2() {
								 
								return  null;
							}
							public String updateServizioParametro1() {
							 
								return null;
							}
							public Date updateServizioFine() {
							 
								return new Date();
							}
							public String searchUUID() {
							
								return uuidOperazione;
							}
						});				
				
					AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addFineOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO)
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP)
					.setOrigin(Origin.getIp(request))
					.addCallGeo()
					.build();
				
				
				
				
				response.setRenderParameter("action", "renderEsitoUpload");
			}
		}
		else {
			String numAllegatiSelect = request.getParameter("numAllegatiSelect");

			int numAllegatiSelectInteger = Integer.parseInt(numAllegatiSelect);
			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			for (int i = 0; i < numAllegatiSelectInteger; i++) {
				fileList.add(null);
			}
			uploadItem.setMultipartFiles(fileList);

			model.addAttribute("selectNumAllegati", numAllegatiSelect);
			model.addAttribute("uploadItem", uploadItem);
			response.setRenderParameter("action", "renderUploadForm");
		}
	}

	/**
	 * Metodo che prende i campi inseriti nella form e li mette in sessione.
	 *
	 * @param datiRimbCosapTosap
	 * @param cambio
	 * @param tipoOsap
	 * @param result
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaRimborso")
	public void compilaDichiarazione(@ModelAttribute("datiRimbCosapTosap") DatiRimborsoCosapTosap datiRimbCosapTosap, BindingResult result,
			@RequestParam(required = false, value = "cambio") String cambio, @RequestParam(required = false, value = "tipoOsap") String tipoOsap, Model model, ActionResponse response,
			ActionRequest request) throws Exception {

		log.debug("compilaDichiarazione :: entering method");

		// Generazione rimborso
		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (cambio == null) {
			Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
			Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");

			datiRimbCosapTosap.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
			datiRimbCosapTosap.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));

			if (datiRimbCosapTosap.getImportoDovuto() != null) {
				String[] importoDovutoStrings = datiRimbCosapTosap.getImportoDovuto().split(",");

				if (importoDovutoStrings != null) {
					// Controllo numero "," nella stringa: se il numero è != del numero di elementi
					// della mappa --> errore
					if (importoDovutoStrings.length == datiRimbCosapTosap.getPosizioniOsapMap().size()) {
						for (int i = 0; i < datiRimbCosapTosap.getPosizioniOsapMap().size(); i++) {
							if (importoDovutoStrings[i] != null && !importoDovutoStrings[i].equals("") && datiRimbCosapTosap.getPosizioniOsapMap() != null
									&& !datiRimbCosapTosap.getPosizioniOsapMap().isEmpty()) {
								try {
									Double importoDovutoDouble = Double.parseDouble(importoDovutoStrings[i]);
									datiRimbCosapTosap.getPosizioniOsapMap().get(i).setImportoDovuto(importoDovutoDouble);
								}
								catch (NumberFormatException e) {
									FieldError error = new FieldError("datiRimbCosapTosap", "importoDovuto", messageSource.getMessage("typeMismatch", null, request.getLocale()));
									result.addError(error);
									datiRimbCosapTosap.getPosizioniOsapMap().get(i).setImportoDovuto(0.0);
									break;
								}
							}
						}
					}
					else {
						FieldError error = new FieldError("datiRimbCosapTosap", "importoDovuto", messageSource.getMessage("typeMismatch", null, request.getLocale()));
						result.addError(error);
						datiRimbCosapTosap.setImportoDovuto("");
					}
				}
			}

			rimborsoValidator.validate(datiRimbCosapTosap, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}

				// Setto il liferay date time
				try {
					PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
				catch (Exception e) {
				}

				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("tipoOsap", tipoOsap);
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));

				response.setRenderParameter("action", "renderRimborsoCosapTosapForm");
				return;
			}

			datiRimbCosapTosap.setTipoOsap(tipoOsap);

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", userPreferences.getNomeComune());
			model.addAttribute("datiRimbCosapTosap", datiRimbCosapTosap);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			model.addAttribute("tipoOsap", tipoOsap);
			response.setRenderParameter("action", "renderRimborsoCosapTosapForm");
		}
		// Cambio anno
		else {

			log.info("compilaDichiarazione :: tipoOsap : " + tipoOsap);

			try {
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
				String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
				Calendar da = Calendar.getInstance();
				Calendar a = Calendar.getInstance();
				da.set(new Integer(datiRimbCosapTosap.getAnno()), 1, 1);
				a.set(new Integer(datiRimbCosapTosap.getAnno()), 12, 31);

				// Osap Permanente
				if (tipoOsap.equals("p")) {

					datiRimbCosapTosap.setTipoOsapString("PERMANENTE");

					PagamentiOsapPermanenteRichiesta osapPermanenteRichiesta = new PagamentiOsapPermanenteRichiesta();
					// Controllo profilo cittadino/azienda
					if (partitaIvaServizio != null) {
						osapPermanenteRichiesta.setPartitaIva(partitaIvaServizio);
					}
					else {
						osapPermanenteRichiesta.setCodiceFiscale(codiceFiscale);
					}
					osapPermanenteRichiesta.setDal(da);
					osapPermanenteRichiesta.setAl(a);

					List<DatiOsapPermanente> elencoOsapPermanente = null;
					try {
						PagamentiOsapPermanenteRisposta osapPermanenteRisposta = visureTosapCosapService.richiediDatiTosapCosapPermanente(osapPermanenteRichiesta, userPreferences);
						elencoOsapPermanente = osapPermanenteRisposta.getElencoOsapPermanente();
					}
					catch (Exception e) {
						log.error("compilaDichiarazione :: " + e.getMessage(), e);
					}

					// Creazione mappa ordinata
					Map<Integer, PosizioneOsap> map = new LinkedHashMap<Integer, DatiRimborsoCosapTosap.PosizioneOsap>();
					if (elencoOsapPermanente != null && !elencoOsapPermanente.isEmpty()) {
						for (int i = 0; i < elencoOsapPermanente.size(); i++) {
							DatiOsapPermanente datiOsapPermanente = elencoOsapPermanente.get(i);

							datiRimbCosapTosap.setNumeroDocumento(datiOsapPermanente.getNumeroDocumento());
							datiRimbCosapTosap.setContoCorrente(datiOsapPermanente.getContoCorrente());
							datiRimbCosapTosap.setImportoDocumento(datiOsapPermanente.getImportoDocumento());

							List<it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.PosizioniOsap> posizioniList = datiOsapPermanente.getPosizioniOsap();
							PosizioneOsap posizioneOsap = null;
							for (int j = 0; j < posizioniList.size(); j++) {
								it.osapulie.tributi.web.ws.output.types.DatiOsapPermanente.PosizioniOsap posizioniOsap = posizioniList.get(j);
								posizioneOsap = datiRimbCosapTosap.new PosizioneOsap();

								posizioneOsap.setIndirizzoUtenza(posizioniOsap.getIndirizzoUtenza());
								posizioneOsap.setZonaUtenza(posizioniOsap.getZonaUtenza());
								posizioneOsap.setSuperficie(posizioniOsap.getSuperficie());
								posizioneOsap.setDescrizioneTariffa(posizioniOsap.getDescrizioneTariffa());
								posizioneOsap.setImportoDaPagare(posizioniOsap.getImportoDaPagare());
								posizioneOsap.setMesi(posizioniOsap.getMesi());

								map.put(j, posizioneOsap);
							}
						}
					}

					datiRimbCosapTosap.setPosizioniOsapMap(map);
				}
				// Osap Temporanea
				else if (tipoOsap.equals("t")) {

					datiRimbCosapTosap.setTipoOsapString("TEMPORANEA");

					PagamentiOsapTemporaneaRichiesta osapTemporaneaRichiesta = new PagamentiOsapTemporaneaRichiesta();

					// Controllo profilo cittadino/azienda
					if (partitaIvaServizio != null) {
						osapTemporaneaRichiesta.setPartitaIva(partitaIvaServizio);
					}
					else {
						osapTemporaneaRichiesta.setCodiceFiscale(codiceFiscale);
					}
					osapTemporaneaRichiesta.setDal(da);
					osapTemporaneaRichiesta.setAl(a);

					List<DatiOsapTemporanea> elencoOsapTemporanea = null;
					try {
						PagamentiOsapTemporaneaRisposta osapTemporaneaRisposta = visureTosapCosapService.richiediDatiTosapCosapTemporanea(osapTemporaneaRichiesta, userPreferences);
						elencoOsapTemporanea = osapTemporaneaRisposta.getElencoOsapTemporanea();
					}
					catch (Exception e) {
						log.error("compilaDichiarazione :: " + e.getMessage(), e);
					}

					// Creazione mappa ordinata
					Map<Integer, PosizioneOsap> map = new LinkedHashMap<Integer, DatiRimborsoCosapTosap.PosizioneOsap>();
					if (elencoOsapTemporanea != null && !elencoOsapTemporanea.isEmpty()) {

						for (int i = 0; i < elencoOsapTemporanea.size(); i++) {
							DatiOsapTemporanea osapTemporanea = elencoOsapTemporanea.get(i);

							datiRimbCosapTosap.setNumeroDocumento(osapTemporanea.getNumeroDocumento());
							datiRimbCosapTosap.setContoCorrente(osapTemporanea.getContoCorrente());
							datiRimbCosapTosap.setImportoDocumento(osapTemporanea.getImportoDocumento());

							List<it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap> posizioniOsapTemporanee = osapTemporanea.getPosizioniOsap();
							if (posizioniOsapTemporanee != null && !posizioniOsapTemporanee.isEmpty()) {

								PosizioneOsap posizioneOsap = null;
								for (int j = 0; j < posizioniOsapTemporanee.size(); j++) {
									it.osapulie.tributi.web.ws.output.types.DatiOsapTemporanea.PosizioniOsap posizioniOsapTemporanea = posizioniOsapTemporanee.get(j);
									posizioneOsap = datiRimbCosapTosap.new PosizioneOsap();

									posizioneOsap.setIndirizzoUtenza(posizioniOsapTemporanea.getIndirizzoUtenza());
									posizioneOsap.setZonaUtenza(posizioniOsapTemporanea.getZona());
									posizioneOsap.setSuperficie(posizioniOsapTemporanea.getSuperficie());
									posizioneOsap.setDescrizioneTariffa(posizioniOsapTemporanea.getDescrizioneTariffa());
									posizioneOsap.setDataPagamento(posizioniOsapTemporanea.getDataPagamento().getTime());
									posizioneOsap.setImportoDaPagare(posizioniOsapTemporanea.getImportoDaPagare());
									posizioneOsap.setImportoPagato(posizioniOsapTemporanea.getImportoPagato());

									map.put(j, posizioneOsap);
								}
							}
						}
					}
					datiRimbCosapTosap.setPosizioniOsapMap(map);
				}
			}
			catch (Exception e) {
				log.error("compilaDichiarazione :: " + e.getMessage(), e);
			}

			datiRimbCosapTosap.setTipoOsap(tipoOsap);

			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("datiRimbCosapTosap", datiRimbCosapTosap);
			model.addAttribute("tipoOsap", tipoOsap);

			response.setRenderParameter("action", "renderRimborsoCosapTosapForm");

		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @param dichiarazione
	 * @throws Exception
	 */
	@ResourceMapping("rimborsoCosapTosapReport")
	public void serveCertificato(@ModelAttribute("datiRimbCosapTosap") DatiRimborsoCosapTosap datiRimbCosapTosap, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {

		log.debug("serveCertificato :: entering method");

		// Setto la lista a partire dalla mappa delle rate
		Map<Integer, PosizioneOsap> map = datiRimbCosapTosap.getPosizioniOsapMap();
		List<PosizioneOsap> posizioneOsapList = new ArrayList<DatiRimborsoCosapTosap.PosizioneOsap>();
		if (map != null && !map.isEmpty()) {
			Iterator<Integer> it = map.keySet().iterator();
			while (it.hasNext()) {
				Integer key = it.next();
				PosizioneOsap posizioneOsap = map.get(key);
				if (posizioneOsap.getImportoDovuto() != null && posizioneOsap.getImportoDovuto() != 0.0) {
					posizioneOsapList.add(posizioneOsap);
				}
			}
			datiRimbCosapTosap.setPosizioniOsapList(posizioneOsapList);
		}
		// Query the service layer for some objects
		List<DatiRimborsoCosapTosap> beans = new ArrayList<DatiRimborsoCosapTosap>();
		beans.add(datiRimbCosapTosap);
		// serve per passare i dati ai sottoreport
		param.put("subreportParameters", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter", SUB_REPORT_PATH);
		if (datiRimbCosapTosap.getTipoOsap().equals("p")) {
			param.put("subreportParameters1", posizioneOsapList);
			subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		}
		else {
			param.put("subreportParameters2", posizioneOsapList);
			subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);
		}
		subreportJrxmlMap.put("subreportParameter3", SUB_REPORT_PATH_3);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);

		UserPreferences userPreferences = helper.getUserPreferences(request);
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(userPreferences);
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_COSAP_TOSAP);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(Exception exception, PortletRequest portletRequest) {

		log.error("maxUploadSizeExceededException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		String maxUploadSize = String.valueOf(((MaxUploadSizeExceededException) exception).getMaxUploadSize());
		model.put("formError", messageSource.getMessage("Maximum.upload.size.exceded", new String[] { maxUploadSize }, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

	/**
	 * Presenta la form per il rimborso Cosap/Tosap.
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRimborsoCosapTosapForm")
	public String renderDichiarazioneIciForm(Model model) throws Exception {
		return toLocalViewPath("rimborsoCosapTosap");
	}

	/**
	 * Presenta la form per l'upload del file.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderUploadForm")
	public String renderUploadForm(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("uploadDichiarazione");
	}

	/**
	 * Presenta la jsp di esito dell'upload del file.
	 *
	 * @param model
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderEsitoUpload")
	public String renderEsitoUpload(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/rimborsocosaptosap/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
