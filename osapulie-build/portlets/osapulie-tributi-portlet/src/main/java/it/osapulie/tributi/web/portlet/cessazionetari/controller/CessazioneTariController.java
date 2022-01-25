package it.osapulie.tributi.web.portlet.cessazionetari.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import org.springframework.mock.web.MockMultipartFile;
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
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
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
import it.osapulie.tributi.web.portlet.cessazionetari.form.DatiCessazioneTari;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
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
 * Portlet controller per il servizio di richiesta di cessazione tari.
 *
 * @author Federico Melli
 *
 */
@Controller("cessazioneTariController")
@RequestMapping("view")
@SessionAttributes({ "datiTari", "param", "uploadItem" })
public class CessazioneTariController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(CessazioneTariController.class);

	private static final String SERVIZIO = "RICHIESTA CESSAZIONE TARI";

	private static final String REPORT_PATH = "/reports/cessazioneTARI.jrxml";
	private static final String REPORT_NAME = "richiestaCessazioneTari.pdf";

	private static final String JSP_PATH = "cessazionetari/";

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy");

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private XMLHelper xmlHelper;

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
	@Named("datiCessazioneTariValidator")
	private Validator datiCessazioneValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

	@Inject
	private CommonService commonService;
	
	@Inject
	private ServizioService servizioService;
	
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
		log.debug("homePage-cessazioneTariController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la rimborso tassa sugli immobili.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getCessazioneTariForm")
	public void getCessazioneTariForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getCessazioneTariForm");

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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();
		 
		 
		 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		try {
			DatiAnagrafici dati = tributiService.richiediDatiAnagrafici(richiesta, userPreferences);

			DatiUtente datiGen = null;
			ComponentiNucleoFamiliare componente = null;
			try {
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
				log.warn("getCessazioneTariForm :: " + e.getMessage());
			}

			// TODO caricare dai service i dati utili alla compilazione della richiesta

			DatiCessazioneTari datiTari = new DatiCessazioneTari();
			if (componente != null && datiGen != null) {
				datiTari.setCodiceFiscale(componente.getCodiceFiscale());
				datiTari.setNome(componente.getNome());
				datiTari.setCognome(componente.getCognome());
				datiTari.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					if (comuneByCodiceISTAT != null) {
						datiTari.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiTari.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
				}
				datiTari.setIndirizzoResidenza(datiGen.getIndirizzo());
				datiTari.setComuneResidenza(datiGen.getComuneResidenza());
				datiTari.setProvResidenza(datiGen.getProvinciaResidenza());
			}
			// Caricamento dati da utente in OSApulie
			else {
				ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
				datiTari.setCodiceFiscale(codiceFiscale);
				datiTari.setNome(profiloUtente.getNome());
				datiTari.setCognome(profiloUtente.getCognome());
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					Comune comune = comuneIsa.getComune();
					if (comune != null) {
						datiTari.setComuneNascita(comune.getDenominazione());
						datiTari.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
					}
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					datiTari.setIndirizzoResidenza(residenza.getVia());
					Comune comune = residenza.getComune();
					if (comune != null) {
						datiTari.setComuneResidenza(comune.getDenominazione());
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiTari.setProvResidenza(provincia.getDenominazioneProvincia());
						}
					}
				}
			}
			model.addAttribute("datiTari", datiTari);
		}
		catch (Exception e) {
			log.error("getCessazioneTariForm :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderCessazioneTariForm");
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
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
	 * Metodo che prende i campi inseriti nella form e li mette in sessione
	 *
	 * @param datiDichiarazione
	 * @param result
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaDichiarazione")
	public void compilaDichiarazione(@ModelAttribute("datiTari") DatiCessazioneTari datiTari, BindingResult result, Model model, ActionResponse response, ActionRequest request) throws Exception {
		log.debug("compilaDichiarazione CF=" + datiTari.getCodiceFiscale());
		datiCessazioneValidator.validate(datiTari, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));

			response.setRenderParameter("action", "renderCessazioneTariForm");
		}
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune());
			model.addAttribute("datiTari", datiTari);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderCessazioneTariForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param datiTari
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("cessazioneTariReport")
	public void serveCertificato(@ModelAttribute("datiTari") DatiCessazioneTari datiTari, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {
		log.debug("cessazioneTariReport ");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiCessazioneTari> beans = new ArrayList<DatiCessazioneTari>();

		// Imposto la data di compilazione della richiesta
		DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
		datiTari.setDataCompilazioneRichiesta(df.format(new Date()));

		beans.add(datiTari);

		// Converto l'array di "allegati" e "motivi" in una lista di String, più facile da gestire
		// in ireport
		if (datiTari.getAllegati() != null) {
			param.put("allegati", getCheckArray(datiTari.getAllegati()));
		}

		if (datiTari.getMotivi() != null) {
			param.put("motivi", getCheckArray(datiTari.getMotivi()));
		}

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), REPORT_PATH, null);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);
		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
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
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiTari") DatiCessazioneTari datiTari) throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		// model.addAttribute("firma", signedFiles);
		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(datiTari);
				if (multipartFileForInterop != null) {
					uploadItem.getMultipartFiles().add(multipartFileForInterop);
				}

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(numeroProtocollo);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					UserPreferences userPreferences = helper.getUserPreferences(request);
					tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
				}

				
				final String uuidOperazione=(String)session.getAttribute("UUID");

				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CESSAZIONE_TARI)
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
	 * Presenta la form per la richiesta di cessazione Tari
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderCessazioneTariForm")
	public String renderCessazioneTariForm(Model model) throws Exception {
		return toLocalViewPath("cessazioneTari");
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
	 * "/dichiarazioneici/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(Exception exception, PortletRequest portletRequest) {

		log.error("maxUploadSizeExceededException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		String maxUploadSize = String.valueOf(((MaxUploadSizeExceededException) exception).getMaxUploadSize());
		model.put("formError", messageSource.getMessage("Maximum.upload.size.exceded", new String[] { maxUploadSize }, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

	private List<String> getCheckArray(String[] vector) {
		List<String> result = new ArrayList<String>();
		if (vector != null) {
			for (int i = 0; i < vector.length; i++) {
				result.add(vector[i]);
			}
		}
		return result;
	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 *
	 */
	private MultipartFile generateMultipartFileForInterop(DatiCessazioneTari datiTari) {

		MultipartFile multipartFile = null;

		try {
			String xml = null;

			// CessazioneTariInterop interop = new CessazioneTariInterop();
			// // Contribuente
			// UtenzaTARI contribuente = new UtenzaTARI();
			// contribuente.setTipoUtenza(datiTari.getTipoUtenza());
			// contribuente.setCognome(datiTari.getCognome());
			// contribuente.setNome(datiTari.getNome());
			// contribuente.setCodiceFiscale(datiTari.getCodiceFiscale());
			// if (datiTari.getDataNascita() != null) {
			// Calendar calendar = new GregorianCalendar();
			// calendar.setTime(simpleDateFormat.parse(datiTari.getDataNascita()));
			// contribuente.setDataNascita(calendar);
			// }
			// contribuente.setComuneNascita(datiTari.getComuneNascita());
			// contribuente.setProvinciaNascita(datiTari.getProvinciaNascita());
			// contribuente.setIndirizzoResidenza(datiTari.getIndirizzoResidenza());
			// contribuente.setComuneResidenza(datiTari.getComuneResidenza());
			// contribuente.setProvinciaResidenza(datiTari.getProvResidenza());
			// contribuente.setCivicoResidenza(datiTari.getCivicoResidenza());
			// contribuente.setEmail(datiTari.getEmail());
			// contribuente.setTelefono(datiTari.getTelefono());
			// contribuente.setFax(datiTari.getFax());
			// contribuente.setPec(datiTari.getPec());
			//
			// // Societa
			// SocietaCessazioneTARI societa = new SocietaCessazioneTARI();
			// societa.setNomeSocieta(datiTari.getNomeSocieta());
			// societa.setPartitaIva(datiTari.getPartitaIva());
			// societa.setRuolo(datiTari.getRuolo());
			// societa.setCittaSedeLegale(datiTari.getCittaSedeLegale());
			// societa.setCapSedeLegale(datiTari.getCapSedeLegale());
			// societa.setViaSedeLegale(datiTari.getViaSedeLegale());
			// societa.setCivicoSedeLegale(datiTari.getCivicoSedeLegale());
			// interop.setSocieta(societa);
			//
			// // Immobile di riferimento
			// ImmCessazioneTARI immobile = new ImmCessazioneTARI();
			// immobile.setIndirizzo(datiTari.getViaUnitaImmobiliare());
			// immobile.setCivico(datiTari.getCivicoUnitaImmobiliare());
			// immobile.setFoglioCatastale(datiTari.getFgUnitaImmobiliare());
			// immobile.setNumeroVisura(datiTari.getNumUnitaImmobiliare());
			// immobile.setSub(datiTari.getSubUnitaImmobiliare());
			// interop.setImmobile(immobile);
			//
			// // motivazioni
			// for (int i = 0; i < datiTari.getMotivi().length; i++) {
			// interop.getMotivi().add(datiTari.getMotivi()[i]);
			// }
			// interop.setAltriMotivi(datiTari.getAltriMotivi());
			// interop.setAttivitaCessata(datiTari.getAttivitaCessata());
			// interop.setNominativoDeceduto(datiTari.getNominativoDeceduto());
			// interop.setDataDecesso(datiTari.getDataDecesso());
			// interop.setSoggettoDuplicazione(datiTari.getSoggettoDuplicazione());
			//
			// // allegati
			// for (int i = 0; i < datiTari.getAllegati().length; i++) {
			// interop.getAllegati().add(datiTari.getMotivi()[i]);
			// }
			// interop.setAltriAllegati(datiTari.getAltriAllegati());
			// interop.setEstremiDocumento(datiTari.getEstremiDocumento());
			// interop.setDataCompilazioneRichiesta(datiTari.getDataCompilazioneRichiesta());

			// xml = xmlHelper.marshal(interop);

			multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		}
		catch (Exception e) {
			log.error("generateMultipartFileForInterop() :: " + e.getMessage(), e);
		}

		return multipartFile;
	}
}
