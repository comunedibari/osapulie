package it.osapulie.anagrafe.web.portlet.dichiarazionecambiodomicilio.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.portlet.handler.PortletSessionRequiredException;
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
import it.osapulie.domain.Comune;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProvinciaService;
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
 * Portlet controller per il servizio Dichiarazione cambio di Domicilio.
 *
 * @author Birtolo Maria MIchela
 * @author Gianluca Pindinelli
 */

@Controller("dichiarazioneCambioDomicilioPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiAnagrafici", "dichiarazione", "uploadItem" })
public class DichiarazioneCambioDomicilioPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneCambioDomicilioPortletController.class);

	public static final String SERVIZIO = "DICHIARAZIONE CAMBIO DOMICILIO";

	public static final String REPORT_PATH = "/reports/dichiarazioneCambioDomicilio.jrxml";
	public static final String REPORT_NAME = "dichiarazioneCambioDomicilio.pdf";

	public static final String JSP_PATH = "dichiarazionecambiodomicilio/";

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private XMLHelper xmlHelper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProvinciaService provinciaService;

	@Inject
	private VisureService visureService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Autowired
	public ResourceBundleMessageSource messageSource;
	
	@Inject
	private AuditRepository auditRepository;
	
	@Inject
	private CommonService commonService;
	
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
		log.debug("homePage-dichiarazioneCambioDomicilioPortletController");
		if (model.containsAttribute("dichiarazione")) {
			model.addAttribute("visualizzaTastoInviaDocumento", true);
		}
		return toLocalViewPath("home");
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 */
	@RequestMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response, PortletRequest request, PortletSession session) throws Exception {
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("DICHIARAZIONE CAMBIO DOMICILIO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.setOrigin(Origin.getIp(request))
					.addPaginaCorrente(helper.getCurrentPageName(request));
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
		UploadItem item = new UploadItem();
		model.addAttribute("uploadItem", item);
		if (!model.containsAttribute("datiAnagrafici")) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
			RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
			richiesta.setCodiceFiscale(codiceFiscale);
			DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));

			model.addAttribute("datiAnagrafici", dati);
		}
		auditManager.build();
		response.setRenderParameter("action", "renderUploadForm");
	}

	/**
	 * Gestisce l'upload dei file
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
	@RequestMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, PortletRequest portletRequest, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiAnagrafici") DatiAnagrafici datiAnagrafici,
			@ModelAttribute("dichiarazione") Map<String, Object> dichiarazione) throws ServiceLayerException, Exception {
		log.debug("uploadFile");
		
		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		AuditConfiguration
		.configure()
		.audit()
		.addInizioOperazione()
		.addUuidOperazione(uuidOperazione)
		.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD - INIT")
		.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
		.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
		.setOrigin(Origin.getIp(portletRequest))
		.addEsitoSuccess()
		.build();
		
		
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// model.addAttribute("firma", signedFiles);
		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO);

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, portletRequest.getLocale()));
				response.setRenderParameter("action", "renderUploadForm");
			}
			else {
				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);

				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, portletRequest.getLocale()));
				fascicolo.setUserPreferences(userPreferences);
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(datiAnagrafici, dichiarazione, profiloUtente, userPreferences);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), userPreferences);

				if (Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, portletRequest.getLocale()));
					fascicoloProtocollo.setUserPreferences(userPreferences);
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO);
					fascicoloProtocollo.setRicercabileDaComune(true);
					fascicoloProtocollo.setNumeroProtocollo(numeroProtocollo);
					fascicoloProtocollo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicoloProtocollo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					anagrafeCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
					AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addDelegante("")
					.addNumeroProcollo(""+numeroProtocollo)
					.setOrigin(Origin.getIp(portletRequest))
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
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.setOrigin(Origin.getIp(portletRequest))
					.addDelegante("")
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
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
				.addDelegante("")
				.addNumeroProcollo(""+numeroProtocollo)
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO)
				.setOrigin(Origin.getIp(portletRequest))
				.addCallGeo()
				.build();
			
				
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO)
				.setNomeServizio(SERVIZIO)
				.getTempiMedi(); 
				
				auditDwhService.invokeAuditService(null,null,tempiMediDto);
				 
				final String protocollo=numeroProtocollo;
				
				DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {	
						
						public String updateServizioProtocollo() {
						 
							return protocollo;
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

	@ModelAttribute("comuniList")
	public List<Comune> getComuniList() {
		List<Comune> result = comuneService.getAllComuni();
		return result;
	}

	@ModelAttribute("provinceList")
	public List<Provincia> getProvinceList(PortletRequest request) {
		List<Provincia> result = provinciaService.getAllProvince();
		return result;
	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param datiAnagrafici
	 * @param dichiarazione
	 * @param profiloUtente
	 * @param userPreferences
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DatiAnagrafici datiAnagrafici, Map<String, Object> dichiarazione, ProfiloUtenteCittadino profiloUtente, UserPreferences userPreferences) {

		// DichiarazioneCambioDomicilioRichiesta richiestaCambioDomicilioInterop = new
		// DichiarazioneCambioDomicilioRichiesta();
		//
		// ComponentiNucleoFamiliare componente = (ComponentiNucleoFamiliare)
		// dichiarazione.get("componenteFamiglia");
		//
		// Componente richiedente = new Componente();
		// richiedente.setCodiceFiscale(componente.getCodiceFiscale());
		// richiedente.setNome(componente.getNome());
		// richiedente.setCognome(componente.getCognome());
		//
		// richiestaCambioDomicilioInterop.setRichiedente(richiedente);
		// richiestaCambioDomicilioInterop.setDataRichiesta(Calendar.getInstance());
		// Indirizzo domicilioSorgente = new Indirizzo();
		// domicilioSorgente.setCivico(datiAnagrafici.getNumeroCivico());
		// domicilioSorgente.setComune(userPreferences.getCodiceIstatComune());
		//
		// // TODO: interno??
		// domicilioSorgente.setInterno(null);
		// domicilioSorgente.setPiano(datiAnagrafici.getPiano());
		// domicilioSorgente.setScala(datiAnagrafici.getScala());
		// domicilioSorgente.setVia(datiAnagrafici.getDescrizioneVia());
		//
		// Indirizzo docmicilioDestinazione = new Indirizzo();
		// docmicilioDestinazione.setCivico((String) dichiarazione.get("civico"));
		// docmicilioDestinazione.setComune(userPreferences.getCodiceIstatComune());
		// docmicilioDestinazione.setInterno((String) dichiarazione.get("interno"));
		// docmicilioDestinazione.setPiano((String) dichiarazione.get("piano"));
		// docmicilioDestinazione.setScala((String) dichiarazione.get("scala"));
		// docmicilioDestinazione.setVia((String) dichiarazione.get("nuovoIndir"));
		//
		// richiestaCambioDomicilioInterop.setDomicilioSorgente(domicilioSorgente);
		// richiestaCambioDomicilioInterop.setDomicilioDestinazione(docmicilioDestinazione);
		//
		// if (dichiarazione.containsKey("cfParenti")) {
		// @SuppressWarnings("unchecked")
		// List<String> parenti = (List<String>) dichiarazione.get("cfParenti");
		// if (parenti != null) {
		// List<ComponentiNucleoFamiliare> componentiList =
		// datiAnagrafici.getComponentiNucleoFamiliare();
		// if (componentiList != null) {
		// for (String codiceFiscaleParente : parenti) {
		// for (ComponentiNucleoFamiliare componenti : componentiList) {
		// if (componenti.getCodiceFiscale().equals(codiceFiscaleParente)) {
		// Componente familiare = new Componente();
		// familiare.setCodiceFiscale(componenti.getCodiceFiscale());
		// familiare.setNome(componenti.getNome());
		// familiare.setCognome(componenti.getCognome());
		// richiestaCambioDomicilioInterop.getFamiliare().add(familiare);
		// }
		// }
		// }
		// }
		// }
		// }

		String xml = xmlHelper.marshal(null);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

	/**
	 * Avvia la dichiarazione del cambio di domicilio mostrando la form precompilata con i dati del
	 * soggetto
	 *
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@RequestMapping(params = "ope=dichiarazioneCambioDomicilio")
	public void dichiarazioneCambioDomicilio(Model model, ActionResponse response, PortletRequest request,PortletSession session) throws Exception {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("DICHIARAZIONE CAMBIO DOMICILIO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.setOrigin(Origin.getIp(request))
					.addPaginaCorrente(helper.getCurrentPageName(request));
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
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO)
		.setUUID(uuidStr)
		.getServizioAttribute();
		
		dataminingDto =  DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO)
		.setUuidOperazione(uuidStr)
		.getDatamining();
		
		tempiMediDto =  DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidStr).go_StartTime().getTempiMedi();
		
		auditDwhService.invokeAuditService(servizioAttributeDto,dataminingDto,tempiMediDto);
		 
		
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
	 * Gestisce il cambio del soggetto per cui si vuole effettuare la dichiarazione
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @throws Exception
	 */
	@RequestMapping(params = "ope=cambioSoggetto")
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
	 * @param civico
	 * @param nuovoIndir
	 * @param interno
	 * @param scala
	 * @param piano
	 * @param parente1
	 * @param parente2
	 * @param parente3
	 * @param parente4
	 * @param parente5
	 * @param parente6
	 * @param parente7
	 * @param model
	 * @param response
	 * @param dati
	 * @throws Exception
	 */
	@RequestMapping(params = "ope=generaDichiarazione")
	public void compilaDichiarazione(@RequestParam("codFisc") String codFisc, @RequestParam("civico") String civico, @RequestParam("nuovoIndir") String nuovoIndir,
			@RequestParam("interno") String interno, @RequestParam("scala") String scala, @RequestParam("piano") String piano, @RequestParam(required = false, value = "parenti[]") String[] parenti,
			Model model, ActionResponse response, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati, PortletSession session, PortletRequest request) throws Exception {

		log.debug("generaDichiarazione cf= ..." + codFisc);
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO DOMICILIO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		
		Map<String, Object> dichiarazione = new HashMap<String, Object>();

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				model.addAttribute("componenteFamiglia", componente);
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				dichiarazione.put("componenteFamiglia", componente);
				break;
			}
		}
		log.debug("generaDichiarazione nuovoIndir= " + nuovoIndir);
		dichiarazione.put("piano", piano);
		dichiarazione.put("nuovoIndir", nuovoIndir);
		dichiarazione.put("civico", civico);
		dichiarazione.put("interno", interno);
		dichiarazione.put("scala", scala);
		dichiarazione.put("comune", helper.getUserPreferences(request).getNomeComune());

		// scelto in fase iniziale
		List<String> par = new ArrayList<String>();
		log.debug("parenti = " + parenti);
		if (parenti != null) {
			par = Arrays.asList(parenti);
		}

		log.debug("parentiList = " + par.size());

		dichiarazione.put("cfParenti", par);
		if (par != null) {
			List<String> nomeCognomeParenteList = new ArrayList<String>();
			for (String cfParente : par) {
				if (componenti != null) {
					for (ComponentiNucleoFamiliare componente : componenti) {
						if (componente.getCodiceFiscale().equals(cfParente)) {
							nomeCognomeParenteList.add(componente.getCognome() + " " + componente.getNome());
						}
					}
				}
			}
			dichiarazione.put("parenti", nomeCognomeParenteList);

		}
		model.addAttribute("dichiarazione", dichiarazione);
		if (nuovoIndir.equals("") || civico.equals("")) {
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
	 * @param dichiarazione
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneCambioDomicilioReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			@ModelAttribute("dichiarazione") Map<String, Object> dichiarazione, PortletRequest request) throws Exception {
		log.debug("generaDichiarazioneCambioDomicilio cf= ..." + codFisc);
		
		AuditManager auditManager= AuditConfiguration.configure()
				.audit().addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO DOMICILIO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

		// Query the service layer for some objects
		List<DatiUtente> beans = new ArrayList<DatiUtente>();

		beans.add(datiUte);

		byte[] reportBytes = reportService.jrxmlToPdf(dichiarazione, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO,
				REPORT_PATH, null);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO);
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
	 * Handler per intercettare un tentativo di upload prima di aver generato la dichiarazione.
	 *
	 * @param exception
	 * @param portletRequest
	 * @return
	 */
	@ExceptionHandler(PortletSessionRequiredException.class)
	public ModelAndView portletSessionRequiredException(Exception exception, PortletRequest portletRequest) {

		log.error("portletSessionRequiredException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("formError", messageSource.getMessage("exception.dichiarazione.required.message", null, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

	/**
	 * Presenta la form per la dichiarazione a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare i dati anagrafici
	 */
	@RenderMapping(params = "action=renderDatiAnagrafici")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("dichiarazioneCambioDomicilio");
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
	public String renderEsitoUpload(Model model, PortletSession session, PortletRequest request) {
		log.debug("Model = " + model);
		AuditConfiguration
		.configure()
		.audit()
		.addUuidOperazione((String)session.getAttribute("UUID"))
		.addOperazioneRichiesta("DICHIARAZIONE CAMBIO DOMICILIO")
		.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
		.addPaginaCorrente(helper.getCurrentPageName(request))
		.addFineOperazione()
		.addEsitoSuccess()		
		.setOrigin(Origin.getIp(request))
		.build();
		return toLocalViewPath("../common/esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/dichiarazionecambiodomicilio/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
