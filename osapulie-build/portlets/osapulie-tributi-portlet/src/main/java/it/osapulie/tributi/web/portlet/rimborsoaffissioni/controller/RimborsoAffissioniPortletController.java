package it.osapulie.tributi.web.portlet.rimborsoaffissioni.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import it.osapulie.tributi.web.portlet.rimborsoaffissioni.form.DatiRimborsoAffissioni;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType;
import it.osapulie.tributi.web.ws.output.types.PagamentiAffissioniType.PosizioniAffissione;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.ArcoTemporale;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta.TipoTributo.Tributo;
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
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Richiesta rimborso affissioni.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("rimborsoAffissioniPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiRimbAffissioni", "param", "uploadItem" })
public class RimborsoAffissioniPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RimborsoAffissioniPortletController.class);

	private static final String SERVIZIO = "RICHIESTA RIMBORSO AFFISSIONI";

	private static final String REPORT_PATH = "/reports/rimborsoAffissioni.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/subreportRimborso.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/subreportRimborsoFooter.jrxml";
	private static final String REPORT_NAME = "rimborsoAffissioni.pdf";

	private static final String JSP_PATH = "rimborsoaffissioni/";

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
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiRimborsoAffissioniValidator")
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
	
	@Inject
	private DelegaService delegaService;
 
	@Inject
	private DwhService dwhService;
	
	@Inject
	private AuditDwhService auditDwhService;
	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) throws Exception {
		log.debug("homePage-rimborsoAffissioniPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la rimborso Affissioni
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getRimborsoAffissioniForm")
	public void getRimborsoAffissioniForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getRimborsoAffissioniForm");

		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

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
				log.warn("getRimborsoAffissioniForm :: " + e.getMessage());
			}

			DatiRimborsoAffissioni datiRimbAffissioni = new DatiRimborsoAffissioni();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiRimbAffissioni.setRagSociale(aziendaByPiva.getRagioneSociale());
				datiRimbAffissioni.setpIva(partitaIvaServizio);
				Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiRimbAffissioni.setSedeLegale(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiRimbAffissioni.setProvincia(comune.getProvincia().getSigla());
						datiRimbAffissioni.setComune(comune.getDenominazione());
					}
				}
				if (componente != null && datiGen != null) {
					datiRimbAffissioni.setCodiceFiscaleGiu(componente.getCodiceFiscale());
					datiRimbAffissioni.setCodiceFiscaleRapp(componente.getCodiceFiscale());
					datiRimbAffissioni.setNomeRapp(componente.getNome());
					datiRimbAffissioni.setCognomeRapp(componente.getCognome());
					datiRimbAffissioni.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbAffissioni.setComuneNascitaRapp(comuneByCodiceISTAT.getDenominazione());
						datiRimbAffissioni.setProvinciaNascitaRapp(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbAffissioni.setSessoRapp(componente.getSesso());
					datiRimbAffissioni.setIndirizzoResidenzaRapp(datiGen.getIndirizzo());
					datiRimbAffissioni.setComuneResidenzaRapp(datiGen.getComuneResidenza());
					datiRimbAffissioni.setProvResidenzaRapp(datiGen.getProvinciaResidenza());
					datiRimbAffissioni.setCapResidenzaRapp(datiGen.getCap());

				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbAffissioni.setCodiceFiscaleRapp(profiloUtente.getCodiceFiscale());
					datiRimbAffissioni.setNomeRapp(profiloUtente.getNome());
					datiRimbAffissioni.setCognomeRapp(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbAffissioni.setComuneNascitaRapp(comune.getDenominazione());
							datiRimbAffissioni.setProvinciaNascitaRapp(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbAffissioni.setIndirizzoResidenzaRapp(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbAffissioni.setComuneResidenzaRapp(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbAffissioni.setProvResidenzaRapp(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
			}
			if (componente != null && datiGen != null) {
				datiRimbAffissioni.setCodiceFiscale(componente.getCodiceFiscale());
				datiRimbAffissioni.setNome(componente.getNome());
				datiRimbAffissioni.setCognome(componente.getCognome());
				datiRimbAffissioni.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Setto il liferay date time
				PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				// fine
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					datiRimbAffissioni.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					datiRimbAffissioni.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
				}
				datiRimbAffissioni.setSesso(componente.getSesso());
				datiRimbAffissioni.setIndirizzoResidenza(datiGen.getIndirizzo());
				datiRimbAffissioni.setComuneResidenza(datiGen.getComuneResidenza());
				datiRimbAffissioni.setProvResidenza(datiGen.getProvinciaResidenza());
				datiRimbAffissioni.setCapResidenza(datiGen.getCap());
			}
			else {
				// Caricamento dati da utente in OSApulie
				ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
				datiRimbAffissioni.setCodiceFiscale(profiloUtente.getCodiceFiscale());
				datiRimbAffissioni.setNome(profiloUtente.getNome());
				datiRimbAffissioni.setCognome(profiloUtente.getCognome());
				// Caricamento comune da codice ISTAT
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					Comune comune = comuneIsa.getComune();
					if (comune != null) {
						datiRimbAffissioni.setComuneNascita(comune.getDenominazione());
						datiRimbAffissioni.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
					}
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					datiRimbAffissioni.setIndirizzoResidenza(residenza.getVia());
					Comune comune = residenza.getComune();
					if (comune != null) {
						datiRimbAffissioni.setComuneResidenza(comune.getDenominazione());
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiRimbAffissioni.setProvResidenza(provincia.getDenominazioneProvincia());
						}
					}
				}
			}

			datiRimbAffissioni.setAnno("" + (new Integer(DateUtils.getAnnoCorrente()) - 1));

			VisuraPosizioneTributariaRichiesta richiestaTrib = new VisuraPosizioneTributariaRichiesta();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				richiestaTrib.setPartitaIva(partitaIvaServizio);
			}
			else {
				richiestaTrib.setCodiceFiscale(codiceFiscale);
			}
			TipoTributo tipo = new TipoTributo();
			Tributo tributo = new Tributo();
			tributo.setAFFISSIONI("AFFISSIONI");
			tipo.setTributo(tributo);
			richiestaTrib.setTipoTributo(tipo);
			ArcoTemporale arco = new ArcoTemporale();
			arco.setAnnoInizio((new Integer(DateUtils.getAnnoCorrente()) - 1));
			arco.setAnnoFine((new Integer(DateUtils.getAnnoCorrente()) - 1));
			richiestaTrib.setArcoTemporale(arco);

			VisuraPosizioneTributariaRisposta icp = null;
			try {
				icp = tributiService.richiediPosizioneTributaria(richiestaTrib, userPreferences);
			}
			catch (Exception e) {
				log.error("getRimborsoAffissioniForm :: " + e.getMessage(), e);
			}

			List<PosizioniAffissione> pos = new ArrayList<PosizioniAffissione>();

			int i = 0;

			if (icp != null && icp.getElencoPagamentiTassaAffissioni() != null && icp.getElencoPagamentiTassaAffissioni().size() > 0) {
				for (int k = 0; k < icp.getElencoPagamentiTassaAffissioni().size(); k++) {
					PagamentiAffissioniType situazione = icp.getElencoPagamentiTassaAffissioni().get(k);
					pos = situazione.getPosizioniAffissione();
					for (int ii = 0; ((ii < pos.size()) && (i < 6)); ii++) {
						i++;
						Method metodo = datiRimbAffissioni.getClass().getMethod("setNum" + (i), String.class);
						metodo.invoke(datiRimbAffissioni, "" + pos.get(ii).getNumeroManifesti());

						Method metodo1 = datiRimbAffissioni.getClass().getMethod("setDim" + (i), String.class);
						metodo1.invoke(datiRimbAffissioni, "" + pos.get(ii).getDimensioneManifesti());

						Method metodo2 = datiRimbAffissioni.getClass().getMethod("setVersato" + (i), String.class);
						metodo2.invoke(datiRimbAffissioni, "" + pos.get(ii).getTariffa());

						Method metodo3 = datiRimbAffissioni.getClass().getMethod("setDovuto" + (i), String.class);
						metodo3.invoke(datiRimbAffissioni, "");

						Method metodo4 = datiRimbAffissioni.getClass().getMethod("setGg" + (i), String.class);
						metodo4.invoke(datiRimbAffissioni, "" + pos.get(ii).getGiorniAffissione());
					}
				}
			}

			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("datiRimbAffissioni", datiRimbAffissioni);

		}
		catch (Exception e) {
			log.error("getRimborsoAffissioniForm :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderRimborsoAffissioniForm");
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
	 * Gestisce l'upload dei file
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
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);
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
				//
				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(request));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);
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
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI)
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
	 * Metodo che prende i campi inseriti nella form e li mette in sessione
	 *
	 * @param datiRimbAffissioni
	 * @param result
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaRimborso")
	public void compilaDichiarazione(@ModelAttribute("datiRimbAffissioni") DatiRimborsoAffissioni datiRimbAffissioni, BindingResult result,
			@RequestParam(required = false, value = "cambio") String cambio, Model model, ActionResponse response, ActionRequest request) throws Exception {

		log.debug("compilaDichiarazione CF=" + datiRimbAffissioni.getCodiceFiscale());
		log.debug("cambio=" + cambio);

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiRimbAffissioni.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiRimbAffissioni.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
		}

		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (cambio == null) {
			rimborsoValidator.validate(datiRimbAffissioni, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderRimborsoAffissioniForm");
			}
			else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", userPreferences.getNomeComune());
				model.addAttribute("datiRimbAffissioni", datiRimbAffissioni);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderRimborsoAffissioniForm");
			}
		}
		else if (cambio != null) {
			log.debug("anno = " + datiRimbAffissioni.getAnno());

			VisuraPosizioneTributariaRichiesta richiestaTrib = new VisuraPosizioneTributariaRichiesta();
			// Controllo profilo cittadino/azienda
			if (userPreferences.getPartitaIvaServizio() != null) {
				richiestaTrib.setPartitaIva(userPreferences.getPartitaIvaServizio());
			}
			else {
				richiestaTrib.setCodiceFiscale(userPreferences.getCodiceFiscaleServizio());
			}
			TipoTributo tipo = new TipoTributo();
			Tributo tributo = new Tributo();
			tributo.setAFFISSIONI("AFFISSIONI");
			tipo.setTributo(tributo);
			richiestaTrib.setTipoTributo(tipo);
			ArcoTemporale arco = new ArcoTemporale();
			arco.setAnnoInizio((new Integer(datiRimbAffissioni.getAnno())));
			arco.setAnnoFine((new Integer(datiRimbAffissioni.getAnno())));
			richiestaTrib.setArcoTemporale(arco);

			VisuraPosizioneTributariaRisposta icp = null;
			try {
				icp = tributiService.richiediPosizioneTributaria(richiestaTrib, userPreferences);
			}
			catch (Exception e) {
				log.error("compilaDichiarazione :: " + e.getMessage(), e);
			}

			List<PosizioniAffissione> pos = new ArrayList<PosizioniAffissione>();

			int i = 0;

			if (icp != null && icp.getElencoPagamentiTassaAffissioni() != null && icp.getElencoPagamentiTassaAffissioni().size() > 0) {
				for (int k = 0; k < icp.getElencoPagamentiTassaAffissioni().size(); k++) {
					PagamentiAffissioniType situazione = icp.getElencoPagamentiTassaAffissioni().get(k);
					pos = situazione.getPosizioniAffissione();
					for (int ii = 0; ((ii < pos.size()) && (i < 6)); ii++) {
						i++;
						Method metodo = datiRimbAffissioni.getClass().getMethod("setNum" + (i), String.class);
						metodo.invoke(datiRimbAffissioni, "" + pos.get(ii).getNumeroManifesti());

						Method metodo1 = datiRimbAffissioni.getClass().getMethod("setDim" + (i), String.class);
						metodo1.invoke(datiRimbAffissioni, "" + pos.get(ii).getDimensioneManifesti());

						Method metodo2 = datiRimbAffissioni.getClass().getMethod("setVersato" + (i), String.class);
						metodo2.invoke(datiRimbAffissioni, "" + pos.get(ii).getTariffa());

						Method metodo3 = datiRimbAffissioni.getClass().getMethod("setDovuto" + (i), String.class);
						metodo3.invoke(datiRimbAffissioni, "");

						Method metodo4 = datiRimbAffissioni.getClass().getMethod("setGg" + (i), String.class);
						metodo4.invoke(datiRimbAffissioni, "" + pos.get(ii).getGiorniAffissione());
					}
				}

			}
			log.debug("i=" + i);
			for (int j = i; j < 5; j++) {
				Method metodo = datiRimbAffissioni.getClass().getMethod("setNum" + (j + 1), String.class);
				metodo.invoke(datiRimbAffissioni, "");

				Method metodo1 = datiRimbAffissioni.getClass().getMethod("setDim" + (j + 1), String.class);
				metodo1.invoke(datiRimbAffissioni, "");

				Method metodo2 = datiRimbAffissioni.getClass().getMethod("setVersato" + (j + 1), String.class);
				metodo2.invoke(datiRimbAffissioni, "");
				Method metodo3 = datiRimbAffissioni.getClass().getMethod("setDovuto" + (j + 1), String.class);
				metodo3.invoke(datiRimbAffissioni, "");
				Method metodo4 = datiRimbAffissioni.getClass().getMethod("setGg" + (j + 1), String.class);
				metodo4.invoke(datiRimbAffissioni, "");
			}

			model.addAttribute("datiRimbAffissioni", datiRimbAffissioni);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			response.setRenderParameter("action", "renderRimborsoAffissioniForm");
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
	@ResourceMapping("rimborsoAffissioniReport")
	public void serveCertificato(@ModelAttribute("datiRimbAffissioni") DatiRimborsoAffissioni datiRimbAffissioni, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {
		log.debug("rimborsoAffissioniReport ");

		// Query the service layer for some objects
		List<DatiRimborsoAffissioni> beans = new ArrayList<DatiRimborsoAffissioni>();

		beans.add(datiRimbAffissioni);

		param.put("subreportParameters", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_AFFISSIONI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

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
	 * Presenta la form per il rimborso Affissioni
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRimborsoAffissioniForm")
	public String renderDichiarazioneAffissioniForm(Model model) throws Exception {
		return toLocalViewPath("rimborsoAffissioni");
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
	 * "/rimborsoaffissioni/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
