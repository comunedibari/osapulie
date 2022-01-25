package it.osapulie.tributi.web.portlet.rimborsoservizicimiteriali.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
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

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
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
import it.osapulie.tributi.service.ServiziCimiterialiService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.rimborsoservizicimiteriali.form.DatiRimborsoServiziCimiteriali;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRichiesta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiRisposta;
import it.osapulie.tributi.web.ws.output.types.PagamentiServiziCimiterialiType;
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
 * Portlet controller per il servizio Richiesta rimborso servizi cimiteriali.
 *
 * @author Gianluca Pindinelli
 */
@Controller("rimborsoServiziCimiterialiPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiRimbServiziCimiteriali", "param", "uploadItem" })
public class RimborsoServiziCimiterialiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RimborsoServiziCimiterialiPortletController.class);

	private static final String SERVIZIO = "RICHIESTA RIMBORSO SERVIZI CIMITERIALI";

	private static final String REPORT_PATH = "/reports/rimborsoServiziCimiteriali.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/subreportRimborso.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/rimborsoServiziCimiteriali_subreport1.jrxml";
	private static final String SUB_REPORT_PATH_3 = "/reports/subreportRimborsoFooter.jrxml";
	private static final String REPORT_NAME = "RimborsoServiziCimiteriali.pdf";

	private static final String JSP_PATH = "rimborsoservizicimiteriali/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private ServiziCimiterialiService serviziCimiterialiService;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiRimborsoServiziCimiterialiValidator")
	private Validator rimborsoValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

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
	 * Presenta la form per il rimborso servizi cimiteriali.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getRimborsoServiziCimiterialiForm")
	public void getRimborsoServiziCimiterialiForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getRimborsoServiziCimiterialiForm :: entering method");

		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+" - START")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();		
		
		
		 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		 
		DatiRimborsoServiziCimiteriali datiRimbServiziCimiteriali = new DatiRimborsoServiziCimiteriali();
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
				log.warn("getRimborsoServiziCimiterialiForm :: " + e.getMessage());
			}
			if (componente != null && datiGen != null) {

				datiRimbServiziCimiteriali.setCodiceFiscale(componente.getCodiceFiscale());
				datiRimbServiziCimiteriali.setNome(componente.getNome());
				datiRimbServiziCimiteriali.setCognome(componente.getCognome());
				datiRimbServiziCimiteriali.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));

				PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");

				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					datiRimbServiziCimiteriali.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					datiRimbServiziCimiteriali.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
				}
				datiRimbServiziCimiteriali.setSesso(componente.getSesso());

				datiRimbServiziCimiteriali.setIndirizzoResidenza(datiGen.getIndirizzo());
				datiRimbServiziCimiteriali.setComuneResidenza(datiGen.getComuneResidenza());
				datiRimbServiziCimiteriali.setProvResidenza(datiGen.getProvinciaResidenza());
				datiRimbServiziCimiteriali.setCapResidenza(datiGen.getCap());

			}
			else {
				// Caricamento dati da utente in OSApulie
				ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
				datiRimbServiziCimiteriali.setCodiceFiscale(profiloUtente.getCodiceFiscale());
				datiRimbServiziCimiteriali.setNome(profiloUtente.getNome());
				datiRimbServiziCimiteriali.setCognome(profiloUtente.getCognome());

				// Caricamento comune da codice ISTAT
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					Comune comune = comuneIsa.getComune();
					if (comune != null) {
						datiRimbServiziCimiteriali.setComuneNascita(comune.getDenominazione());
						datiRimbServiziCimiteriali.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
					}
				}

				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					datiRimbServiziCimiteriali.setIndirizzoResidenza(residenza.getVia());
					Comune comune = residenza.getComune();
					if (comune != null) {
						datiRimbServiziCimiteriali.setComuneResidenza(comune.getDenominazione());
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiRimbServiziCimiteriali.setProvResidenza(provincia.getDenominazioneProvincia());
						}
					}
				}

				PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			}

			datiRimbServiziCimiteriali.setAnno(String.valueOf((new Integer(DateUtils.getAnnoCorrente()) - 1)));

			PagamentiServiziCimiterialiRichiesta richiestaPagamenti = new PagamentiServiziCimiterialiRichiesta();
			richiestaPagamenti.setCodiceFiscale(codiceFiscale);
			Calendar da = Calendar.getInstance();
			da.set(new Integer(datiRimbServiziCimiteriali.getAnno()), 1, 1);
			richiestaPagamenti.setDal(da);
			Calendar a = Calendar.getInstance();
			a.set(new Integer(datiRimbServiziCimiteriali.getAnno()), 12, 31);
			richiestaPagamenti.setAl(a);

			List<PagamentiServiziCimiterialiType> elencoPagamentiCimiteriali = null;
			try {
				PagamentiServiziCimiterialiRisposta risposta = serviziCimiterialiService.richiediDatiServiziCimiteriali(richiestaPagamenti, userPreferences);
				elencoPagamentiCimiteriali = risposta.getElencoPagamentiCimiteriali();
			}
			catch (Exception e) {
				log.error("getRimborsoServiziCimiterialiForm :: " + e.getMessage(), e);
			}

			if (elencoPagamentiCimiteriali != null && !elencoPagamentiCimiteriali.isEmpty()) {

				for (int i = 0; i < elencoPagamentiCimiteriali.size(); i++) {
					PagamentiServiziCimiterialiType pagamentiServiziCimiterialiType = elencoPagamentiCimiteriali.get(i);
					datiRimbServiziCimiteriali.setNumeroDocumento(pagamentiServiziCimiterialiType.getNumeroDocumento());
					datiRimbServiziCimiteriali.setContoCorrente(pagamentiServiziCimiterialiType.getContoCorrente());
					datiRimbServiziCimiteriali.setImportoDocumento(pagamentiServiziCimiterialiType.getImportoDocumento());

					datiRimbServiziCimiteriali.setDescrizione("Pagamento per l'anno: " + pagamentiServiziCimiterialiType.getAnnoRiferimento());
				}
			}

		}
		catch (Exception e) {
			log.error("getRimborsoServiziCimiterialiForm :: " + e.getMessage(), e);
		}

		PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataDay", "inputDataMonth", "inputDataYear");

		model.addAttribute("datiRimbServiziCimiteriali", datiRimbServiziCimiteriali);
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

		response.setRenderParameter("action", "renderRimborsoServiziCimiterialiForm");
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
		// model.addAttribute("firma", signedFiles);
		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI);
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
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI);
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
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo()
					.build();
	 
				
				// memorizzo la richiesta
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
	 * @param datiRimbServiziCimiteriali
	 * @param cambio
	 * @param result
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaRimborso")
	public void compilaDichiarazione(@ModelAttribute("datiRimbServiziCimiteriali") DatiRimborsoServiziCimiteriali datiRimbServiziCimiteriali, BindingResult result,
			@RequestParam(required = false, value = "cambio") String cambio, Model model, ActionResponse response, ActionRequest request) throws Exception {

		log.debug("compilaDichiarazione :: entering method");

		// Generazione rimborso
		if (cambio == null) {
			Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
			Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");

			datiRimbServiziCimiteriali.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
			datiRimbServiziCimiteriali.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));

			rimborsoValidator.validate(datiRimbServiziCimiteriali, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}

				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

				// Setto il liferay date time
				try {
					PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
				catch (Exception e) {
				}

				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderRimborsoServiziCimiterialiForm");
				return;
			}

			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune());
			model.addAttribute("datiRimbServiziCimiteriali", datiRimbServiziCimiteriali);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderRimborsoServiziCimiterialiForm");
		}
		// Cambio anno
		else {
			String codiceFiscale = helper.getUserPreferences(request).getCodiceFiscaleServizio();

			PagamentiServiziCimiterialiRichiesta richiesta = new PagamentiServiziCimiterialiRichiesta();

			richiesta.setCodiceFiscale(codiceFiscale);
			Calendar da = Calendar.getInstance();
			da.set(new Integer(datiRimbServiziCimiteriali.getAnno()), 1, 1);
			richiesta.setDal(da);
			Calendar a = Calendar.getInstance();
			a.set(new Integer(datiRimbServiziCimiteriali.getAnno()), 12, 31);
			richiesta.setAl(a);

			PagamentiServiziCimiterialiRisposta risposta = null;
			List<PagamentiServiziCimiterialiType> elencoPagamentiCimiteriali = null;
			try {
				risposta = serviziCimiterialiService.richiediDatiServiziCimiteriali(richiesta, helper.getUserPreferences(request));
				elencoPagamentiCimiteriali = risposta.getElencoPagamentiCimiteriali();
			}
			catch (Exception e) {
				log.error("compilaDichiarazione :: " + e.getMessage(), e);
			}

			if (elencoPagamentiCimiteriali != null && !elencoPagamentiCimiteriali.isEmpty()) {

				for (int i = 0; i < elencoPagamentiCimiteriali.size(); i++) {
					PagamentiServiziCimiterialiType pagamentiServiziCimiterialiType = elencoPagamentiCimiteriali.get(i);
					datiRimbServiziCimiteriali.setNumeroDocumento(pagamentiServiziCimiterialiType.getNumeroDocumento());
					datiRimbServiziCimiteriali.setContoCorrente(pagamentiServiziCimiterialiType.getContoCorrente());
					datiRimbServiziCimiteriali.setImportoDocumento(pagamentiServiziCimiterialiType.getImportoDocumento());

					datiRimbServiziCimiteriali.setDescrizione("Pagamento per l'anno: " + pagamentiServiziCimiterialiType.getAnnoRiferimento());
				}
			}

			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("datiRimbServiziCimiteriali", datiRimbServiziCimiteriali);

			response.setRenderParameter("action", "renderRimborsoServiziCimiterialiForm");

		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param datiRimbServiziCimiteriali
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @param request
	 * @throws Exception
	 */
	@ResourceMapping("rimborsoServiziCimiterialiReport")
	public void serveCertificato(@ModelAttribute("datiRimbServiziCimiteriali") DatiRimborsoServiziCimiteriali datiRimbServiziCimiteriali, @ModelAttribute("param") Map<String, Object> param,
			Model model, ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {

		log.debug("serveCertificato :: entering method");

		// Setto la lista a partire dalla mappa delle rate
		// Query the service layer for some objects
		List<DatiRimborsoServiziCimiteriali> beans = new ArrayList<DatiRimborsoServiziCimiteriali>();

		beans.add(datiRimbServiziCimiteriali);
		// serve per passare i dati ai sottoreport
		param.put("subreportParameters", beans);
		param.put("subreportParametersRate", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);
		subreportJrxmlMap.put("subreportParameter3", SUB_REPORT_PATH_3);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_SERVIZI_CIMITERIALI);
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
	 * Presenta la form per il rimborso servizi cimiteriali.
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRimborsoServiziCimiterialiForm")
	public String renderDichiarazioneIciForm(Model model) throws Exception {
		return toLocalViewPath("rimborsoServiziCimiteriali");
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
}
