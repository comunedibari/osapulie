package it.osapulie.sociali.web.portlet.serviziorifiuti;

import java.io.IOException;
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
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.sociali.service.AuditDwhService;
import it.osapulie.sociali.service.SocialiCommonService;
import it.osapulie.sociali.service.VisureService;
import it.osapulie.sociali.web.portlet.model.DatiServiziRifiuti;
import it.osapulie.sociali.web.portlet.model.Parente;
import it.osapulie.sociali.web.portlet.util.impl.PortletConstants;
import it.osapulie.sociali.web.ws.input.types.ServizioRifiutiRichiesta;
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

@Controller("servizioRifiutiController")
@RequestMapping("view")
@SessionAttributes({ "datiServiziRifiuti", "param", "uploadItem", "soggettoRichiedente", "componentiNucleoFamiliare" })
public class ServizioRifiutiController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ServizioRifiutiController.class);

	private static final String REPORT_PATH = "/reports/serviziRifiuti.jrxml";
	private static final String SUB_REPORT_PATH = "/reports/serviziRifiuti_subreport1.jrxml";
	private static final String REPORT_NAME = "serviziRifiuti.pdf";

	private static final String JSP_PATH = "serviziorifiuti/";

	private static final String SERVIZIO = "RICHIESTA SERVIZIO RIFIUTI";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private XMLHelper xmlHelper;

	@Inject
	private SocialiCommonService socialiCommonService;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private ReportService reportService;

	@Inject
	private VisureService visureService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiServiziRifiutiValidator")
	private Validator datiValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ServizioService servizioService;
	
	@Inject
	private ComuneService comuneService;
	
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
		log.debug("homePage-servizioRifiutiPortlet");
		if (model.containsAttribute("datiServiziRifiuti")) {
			model.addAttribute("visualizzaTastoInviaDocumento", true);
		}
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la dichiarazione
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getServizioRifiutiForm")
	public void getIscrizioneTemporaneaForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getServizioRifiutiForm");

		DatiServiziRifiuti dati = new DatiServiziRifiuti();
		Parente soggettoRichiedente = new Parente();

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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);
		DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
		richiestaGen.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici datiAnagrafci = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));
		DatiUtente datiUtente = visureService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));

		ComponentiNucleoFamiliare richiedente = null;

		for (int k = 0; k < datiAnagrafci.getComponentiNucleoFamiliare().size(); k++) {
			if (codiceFiscale.equals(datiAnagrafci.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {

				// Il soggetto considerato è il RICHIEDENTE
				richiedente = datiAnagrafci.getComponentiNucleoFamiliare().get(k);
				soggettoRichiedente.setCf(richiedente.getCodiceFiscale());
				dati.setCf(richiedente.getCodiceFiscale());
				soggettoRichiedente.setNome(richiedente.getNome());
				dati.setNome(richiedente.getNome());
				soggettoRichiedente.setCognome(richiedente.getCognome());
				dati.setCognome(richiedente.getCognome());
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				dati.setIndirizzo(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				dati.setComune(datiUtente.getComuneResidenza());
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());
				dati.setProvincia(datiUtente.getProvinciaResidenza());

			}
		}

		model.addAttribute("componentiNucleoFamiliare", datiAnagrafci.getComponentiNucleoFamiliare());
		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiServiziRifiuti", dati);

		response.setRenderParameter("action", "renderServizioRifiutiForm");
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
	public void compilaDichiarazione(@ModelAttribute("datiServiziRifiuti") DatiServiziRifiuti dati, BindingResult result, Model model, ActionResponse response, ActionRequest request)
			throws Exception {

		datiValidator.validate(dati, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			response.setRenderParameter("action", "renderServizioRifiutiForm");
		}
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune()); // specificare
			// il
			// comune
			// scelto in fase iniziale
			model.addAttribute("datiServiziRifiuti", dati);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderServizioRifiutiForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param datiDichiarazione
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("servizioRifiutiReport")
	public void serveCertificato(@ModelAttribute("datiServiziRifiuti") DatiServiziRifiuti dati, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {

		log.debug("servizioRifiutiReport");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// Query the service layer for some objects
		List<DatiServiziRifiuti> beans = new ArrayList<DatiServiziRifiuti>();
		beans.add(dati);

		// Converto l'array di "materiali" in una lista di String, così è più facile da gestire in
		// ireport
		if (dati.getMateriali() != null) {
			param.put("materiali", convertMaterialiInArray(dati.getMateriali()));
		}

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter", SUB_REPORT_PATH);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);
		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI);
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
	 * Presenta la form per la dichiarazione
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderServizioRifiutiForm")
	public String rederIscrizioneTemporaneaForm(Model model) throws Exception {
		return toLocalViewPath("richiestaServizioRifiuti");
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
	 * Gestisce il cambio del soggetto per cui si vuole effettuare la dichiarazione
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param dati
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=cambioSoggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, ActionRequest request, @ModelAttribute("soggettoRichiedente") Parente datiAnagrafici,
			@ModelAttribute("componentiNucleoFamiliare") ArrayList<ComponentiNucleoFamiliare> componenti) throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);

		DatiServiziRifiuti dati = new DatiServiziRifiuti();
		Parente soggettoRichiedente = new Parente();

		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codFisc);
				DatiUtente datiUtente = visureService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));

				soggettoRichiedente.setCf(componente.getCodiceFiscale());
				dati.setCf(componente.getCodiceFiscale());
				soggettoRichiedente.setNome(componente.getNome());
				dati.setNome(componente.getNome());
				soggettoRichiedente.setCognome(componente.getCognome());
				dati.setCognome(componente.getCognome());
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());
				dati.setIndirizzo(datiUtente.getIndirizzo());
				dati.setComune(datiUtente.getComuneResidenza());
				dati.setProvincia(datiUtente.getProvinciaResidenza());

			}
		}

		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiServiziRifiuti", dati);
		model.addAttribute("componentiNucleloFamiliare", componenti);

		response.setRenderParameter("action", "renderServizioRifiutiForm");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/serviziorifiuti/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
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
	 * Gestisce l'upload dei file.
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param session
	 * @param portletRequest
	 * @param model
	 * @param selectNumAllegati
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, PortletRequest portletRequest, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiServiziRifiuti") DatiServiziRifiuti dati) throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// model.addAttribute("firma", signedFiles);

		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI);

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, portletRequest.getLocale()));
				response.setRenderParameter("action", "renderUploadForm");
			}
			else {

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, portletRequest.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(portletRequest));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(dati);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), helper.getUserPreferences(portletRequest));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, portletRequest.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(portletRequest));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI);
					fascicoloProtocollo.setRicercabileDaComune(true);
					fascicoloProtocollo.setNumeroProtocollo(numeroProtocollo);
					fascicoloProtocollo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicoloProtocollo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					socialiCommonService.sendMailToUser(profiloUtente, subject, SERVIZIO, numeroProtocollo);
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
				}


				final String uuidOperazione=(String)session.getAttribute("UUID");
				
				
				
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
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
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIFIUTI_INGOMBRANTI)
					.setOrigin(Origin.getIp(portletRequest))
					.addCallGeo()
					.build();
				
				
				
				response.setRenderParameter("action", "renderEsitoUpload");
			}
		}
		else {
			String numAllegatiSelect = portletRequest.getParameter("numAllegatiSelect");

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

	@ModelAttribute("tipologieMateriali")
	public ArrayList<String> getTipologieMateriali() {
		ArrayList<String> result = new ArrayList<String>();
		result.add("armadio (3 colli)");
		result.add("apparecchi illuminazione (lampadari)");
		result.add("asciugacapelli");
		result.add("asciugatrice");
		result.add("attrezzi");
		result.add("attrezzo sportivo");
		result.add("auto giocattolo elettriche");
		result.add("bicicletta (tricicli ecc.)");
		result.add("bilancia");
		result.add("boiler elettrico");
		result.add("box bambini");
		result.add("calcolatore");
		result.add("cappa");
		result.add("cassettiera");
		result.add("ceste");
		result.add("comò");
		result.add("comodino");
		result.add("computer");
		result.add("computer portatili");
		result.add("condizionatore");
		result.add("congelatore");
		result.add("credenza");
		result.add("cucine elettriche a gas");
		result.add("divano");
		result.add("doghe letto (struttura laterale)");
		result.add("elettrica/elettronica");
		result.add("fax");
		result.add("ferro da stiro");
		result.add("forno");
		result.add("fotocopiatrice");
		result.add("friggitrice");
		result.add("frigorifero");
		result.add("frullatore");
		result.add("giardinaggio elettrici (tagliaerba)");
		result.add("infissi (porte, finestre)");
		result.add("lavastoviglie");
		result.add("lavatrice");
		result.add("legno/fusti ferro");
		result.add("lettore videocassette/dvd");
		result.add("macchina caffè");
		result.add("macchina da scriver");
		result.add("macchina per cucire");
		result.add("macchinetta per misurazione");
		result.add("materasso");
		result.add("mensole");
		result.add("microonde");
		result.add("mobile bagno");
		result.add("mobile frigo");
		result.add("monitor computer");
		result.add("passeggino/carrozzina");
		result.add("pensile");
		result.add("pensili");
		result.add("poltrona");
		result.add("pressione elettrica");
		result.add("radio");
		result.add("rete");
		result.add("letto");
		result.add("sanitari");
		result.add("scaffale");
		result.add("scaldabagno elettrico");
		result.add("scope elettriche");
		result.add("sedia");
		result.add("segreteria");
		result.add("stampante");
		result.add("stendino");
		result.add("stereo");
		result.add("stufe");
		result.add("tavolo");
		result.add("telefono");
		result.add("televisore");
		result.add("termoconvettore");
		result.add("termosifone");
		result.add("valigia");
		result.add("ventilatore");
		result.add("videoregistratore");
		return result;
	}

	List<String> convertMaterialiInArray(String[] materiali) {
		List<String> result = new ArrayList<String>();
		if (materiali != null) {
			for (int i = 0; i < materiali.length; i++) {
				result.add(materiali[i]);
			}
		}
		return result;
	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param dati
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DatiServiziRifiuti dati) {

		ServizioRifiutiRichiesta interop = new ServizioRifiutiRichiesta();

		interop.setNome(dati.getNome());
		interop.setCognome(dati.getCognome());
		interop.setComune(dati.getComune());
		interop.setProvincia(dati.getProvincia());
		interop.setModalitaContatto(dati.getModalitaContatto());
		interop.setTelefono(dati.getTelefono());
		interop.setEmail(dati.getEmail());
		if (dati.getMateriali() != null) {
			String[] materiali = dati.getMateriali();
			for (int i = 0; i < materiali.length; i++) {
				interop.getMateriali().add(materiali[i]);
			}
		}
		interop.setAltro(dati.getAltro());
		interop.setNote(dati.getNote());

		String xml = xmlHelper.marshal(interop);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

}
