package it.osapulie.anagrafe.web.portlet.iscrizionealboscrutatori;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import com.liferay.portal.kernel.util.Validator;
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
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
 * Portlet controller per il servizio Iscrizione albo scrutatori di seggio.
 *
 * @author Birtolo Maria MIchela
 * @author Gianluca Pindinelli
 */
@Controller("iscrizioneAlboScrutatoriPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiAnagrafici", "iscrizione", "uploadItem" })
public class IscrizioneAlboScrutatoriPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(IscrizioneAlboScrutatoriPortletController.class);

	public static final String SERVIZIO = "ISCRIZIONE ALBO SCRUTATORI DI SEGGIO";

	public static final String REPORT_PATH = "/reports/iscrizioneAlboScrutatori.jrxml";
	public static final String REPORT_NAME = "iscrizioneAlboScrutatori.pdf";

	public static final String JSP_PATH = "iscrizionealboscrutatori/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private VisureService visureService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Autowired
	public ResourceBundleMessageSource messageSource;
	
	@Inject
	private AuditRepository auditRepository;

	@Inject
	private CommonService commonService;
	@Inject
	private ComuneService comuneService;
	@Inject
	private DelegaService delegaService;
	@Inject
	private ServizioService servizioService;
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
		log.debug("homePage-iscrizioneAlboScrutatoriPortletController");

		return toLocalViewPath("home");
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@RequestMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response,ActionRequest request,PortletSession session) throws ServiceLayerException, Exception {
		UploadItem item = new UploadItem();
		model.addAttribute("uploadItem", item);
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		
		DwhDataminingDTO dwhDataminingDTO = new DwhDataminingDTO();
		DwhServizioAttributeDTO dwhServizioAttributeDTO = new DwhServizioAttributeDTO();
		DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
		
		
		dwhServizioAttributeDTO = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
		.setUUID(uuidStr)
		.getServizioAttribute();
		
		dwhDataminingDTO = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
		.setUuidOperazione(uuidStr)
		.getDatamining();
		
		dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidStr).go_StartTime().getTempiMedi();
 
		auditDwhService.invokeAuditService(dwhServizioAttributeDTO, dwhDataminingDTO, dwhTempiMediDTO);
		
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
		
final String uuidOperazione=(String)session.getAttribute("UUID");
		
		AuditConfiguration
		.configure()
		.audit()
		.addInizioOperazione()
		.addUuidOperazione(uuidOperazione)
		.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD - INIT")
		.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
		.addPaginaCorrente(helper.getCurrentPageName(request))
		.setOrigin(Origin.getIp(request))
		.addEsitoSuccess()
		.build();
		
		
		
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// model.addAttribute("firma", signedFiles);
		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), helper.getUserPreferences(request));

				if (Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(request));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI);
					fascicoloProtocollo.setRicercabileDaComune(true);
					fascicoloProtocollo.setNumeroProtocollo(numeroProtocollo);
					fascicoloProtocollo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicoloProtocollo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					UserPreferences userPreferences = helper.getUserPreferences(request);
					anagrafeCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				
					AuditConfiguration
			.configure()
			.audit()
			.addInizioOperazione()
			.addUuidOperazione(uuidOperazione)
			.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addDelegante("")
			.addNumeroProcollo(""+numeroProtocollo)
			.setOrigin(Origin.getIp(request))
			.addEsitoSuccess()
			.build();
				
				
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
					
					AuditConfiguration
				.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidOperazione)
				.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addDelegante("")
				.setOrigin(Origin.getIp(request))
				.addNumeroProcollo(""+numeroProtocollo)
						.addEsitoError("Exception errore invio email al cittadino")
						.build();	
				}
				
				
				AuditConfiguration
				.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidOperazione)
				.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addDelegante("")
				.addNumeroProcollo(""+numeroProtocollo)
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
				.setOrigin(Origin.getIp(request))
				.addCallGeo()
				.build();
				
				
				
				DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
				
					//MS
				dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
				.setNomeServizio(SERVIZIO)
				.getTempiMedi();

				auditDwhService.invokeAuditService(null, null, dwhTempiMediDTO);
				
				 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute(){
						
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
	 * Avvia la compilazione della domanda di iscrizione all'albo scrutatori mostrando la form
	 * precompilata con i dati del soggetto
	 *
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=iscrizioneAlboScrutatori")
	public void iscrizioneAlboScrutatori(Model model, ActionResponse response, PortletSession session, ActionRequest request, PortletRequest portletRequest) throws Exception {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
	AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("ISCRIZIONE ALBO SCRUTATORI DI SEGGIO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addServizioTimeString()
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo();
			 auditManager.exportUser(false, new Export() {
			
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
			 	DwhDataminingDTO dwhDataminingDTO = new DwhDataminingDTO();
				DwhServizioAttributeDTO dwhServizioAttributeDTO = new DwhServizioAttributeDTO();
				DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
			 
				dwhDataminingDTO = DwhDataminingUtil.get(codiceFiscale, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
				.setUuidOperazione(uuidStr)
				.getDatamining();
				
				dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
				.setUuid(uuidStr).go_StartTime().getTempiMedi();

				dwhServizioAttributeDTO = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI)
				.setUUID(uuidStr)
				.getServizioAttribute();	 
			 
				auditDwhService.invokeAuditService(dwhServizioAttributeDTO, dwhDataminingDTO, dwhTempiMediDTO);
				
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));

		model.addAttribute("datiAnagrafici", dati);

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codiceFiscale != null && codiceFiscale.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}
		auditManager.build();
		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	/**
	 * Gestisce il cambio del soggetto per cui si vuole effettuare l'iscrizione
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=cambioSoggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati)
			throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}

		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	/**
	 * Metodo che prende i campi inseriti nella form e li mette in sessione
	 *
	 * @param codFisc
	 * @param titoloStudio
	 * @param annoStudio
	 * @param sedeStudio
	 * @param model
	 * @param response
	 * @param dati
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaIscrizione")
	public void compilaIscrizione(@RequestParam("codFisc") String codFisc, @RequestParam("titoloStudio") String titoloStudio, @RequestParam("annoStudio") String annoStudio,
			@RequestParam("sedeStudio") String sedeStudio, @RequestParam(required = false, value = "dichiarazioneProfessione") String dichiarazioneProfessione,
			@RequestParam(required = false, value = "dichiarazionePresso") String dichiarazionePresso,
			@RequestParam(required = false, value = "dichiarazioneCondizione") String dichiarazioneCondizione, @RequestParam(required = false, value = "tipoDichiarazione") String tipoDichiarazione,
			Model model, ActionResponse response, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati, PortletSession session, ActionRequest request, PortletRequest portletRequest) throws Exception {
		log.debug("generaIscrizione cf= ..." + codFisc);
		// ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente( session );
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("ISCRIZIONE ALBO SCRUTATORI DI SEGGIO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}
		log.debug("generaIscrizione titoloStudio= " + titoloStudio);

		Map<String, Object> iscrizione = new HashMap<String, Object>();
		iscrizione.put("titoloStudio", titoloStudio);
		iscrizione.put("annoStudio", annoStudio);
		iscrizione.put("sedeStudio", sedeStudio);
		iscrizione.put("comune", helper.getUserPreferences(request).getNomeComune());
		iscrizione.put("tipoDichiarazione", tipoDichiarazione);
		if (tipoDichiarazione != null) {
			if (tipoDichiarazione.equals("1")) {
				iscrizione.put("dichiarazioneProfessione", dichiarazioneProfessione);
				iscrizione.put("dichiarazionePresso", dichiarazionePresso);
			}
			else if (tipoDichiarazione.equals("2")) {
				iscrizione.put("dichiarazioneCondizione", dichiarazioneCondizione);
			}
		}
		iscrizione.put("email", helper.getProfiloUtente(session).getMail());
		model.addAttribute("iscrizione", iscrizione);

		// Validazione campi
		boolean formError = false;
		if (tipoDichiarazione != null && !tipoDichiarazione.equals("")) {
			if (tipoDichiarazione.equals("1")) {
				if (dichiarazioneProfessione.equals("") || dichiarazionePresso.equals("")) {
					formError = true;
				}
			}
			else if (tipoDichiarazione.equals("2")) {
				if (dichiarazioneCondizione.equals("")) {
					formError = true;
				}
			}
		}

		if (titoloStudio.equals("") || annoStudio.equals("") || sedeStudio.equals("") || (tipoDichiarazione == null || tipoDichiarazione.equals(""))) {
			formError = true;
		}

		if (formError) {
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
		}
		else {
			model.addAttribute("download", "si");
		}
		auditManager.build();
		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @param iscrizione
	 * @throws Exception
	 */
	@ResourceMapping("iscrizioneAlboScrutatoriReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response,PortletRequest portletRequest, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			@ModelAttribute("iscrizione") Map<String, Object> iscrizione, ResourceRequest request) throws Exception {
		log.debug("generaIscrizioneAlboScrutatori cf= ..." + codFisc);
		
		AuditManager auditManager= AuditConfiguration.configure().audit().addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("ISCRIZIONE ALBO SCRUTATORI DI SEGGIO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

		// Query the service layer for some objects
		List<DatiUtente> beans = new ArrayList<DatiUtente>();

		beans.add(datiUte);

		byte[] reportBytes = reportService.jrxmlToPdf(iscrizione, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI, REPORT_PATH,
				null);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format(REPORT_NAME);
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
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
	 * Presenta la form per l'iscrizione a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare i dati anagrafici
	 */
	@RenderMapping(params = "action=renderDatiAnagrafici")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		return toLocalViewPath("iscrizioneAlboScrutatori");
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
		return toLocalViewPath("../common/esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/iscrizionealboscrutatori/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
