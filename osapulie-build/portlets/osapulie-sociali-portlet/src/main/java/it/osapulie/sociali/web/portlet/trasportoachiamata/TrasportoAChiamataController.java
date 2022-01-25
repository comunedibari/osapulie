package it.osapulie.sociali.web.portlet.trasportoachiamata;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
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
import it.osapulie.sociali.web.portlet.model.DatiRichiestaTrasporto;
import it.osapulie.sociali.web.portlet.model.Parente;
import it.osapulie.sociali.web.portlet.util.DateFormatUtil;
import it.osapulie.sociali.web.portlet.util.impl.PortletConstants;
import it.osapulie.sociali.web.ws.input.types.RichiestaTrasportoRichiesta;
import it.osapulie.sociali.web.ws.input.types.Soggetto;
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

@Controller("trasportoAChiamataController")
@RequestMapping("view")
@SessionAttributes({ "datiDichiarazione", "param", "uploadItem", "soggettoRichiedente", "componentiNucleoFamiliare" })
public class TrasportoAChiamataController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(TrasportoAChiamataController.class);

	public static final String REPORT_PATH = "/reports/richiestaTrasporto.jrxml";
	public static final String REPORT_NAME = "richiestaTrasporto.pdf";
	public static final String JSP_PATH = "trasportoachiamata/";

	public static final String SERVIZIO = "RICHIESTA TRASPORTO A CHIAMATA";

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
	@Named("datiRichiestaTrasportoValidator")
	private Validator datiValidator;

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
	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) throws Exception {
		log.debug("homePage-richiestaTrasportoAChiamata");
		if (model.containsAttribute("datiDichiarazione")) {
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
	@ActionMapping(params = "action=getRichiestaTrasportoForm")
	public void getIscrizioneTemporaneaForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getRichiestaTrasportoForm");

		DatiRichiestaTrasporto dati = new DatiRichiestaTrasporto();
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
 

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
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
				dati.setCfRichiedente(richiedente.getCodiceFiscale());
				soggettoRichiedente.setNome(richiedente.getNome());
				soggettoRichiedente.setCognome(richiedente.getCognome());
				soggettoRichiedente.setDataNascita(DateFormatUtil.getDataGGMMAAAA(richiedente.getDataNascita().getTime()));
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				if (richiedente.isCittadinanzaItaliana() && richiedente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(richiedente.getCodiceIstatComuneNascita());
					soggettoRichiedente.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					soggettoRichiedente.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					soggettoRichiedente.setCodiceIstatComuneNascita(richiedente.getCodiceIstatComuneNascita());
				}
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());
				dati.setNomeRichiedente(richiedente.getNome());
				dati.setCognomeRichiedente(richiedente.getCognome());

			}
		}

		model.addAttribute("componentiNucleoFamiliare", datiAnagrafci.getComponentiNucleoFamiliare());
		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiDichiarazione", dati);
		model.addAttribute("datiAnagrafici", datiAnagrafci);

		response.setRenderParameter("action", "renderRichiestaTrasportoForm");
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
	public void compilaDichiarazione(@ModelAttribute("datiDichiarazione") DatiRichiestaTrasporto dati, BindingResult result, Model model, ActionResponse response, ActionRequest request,
			@ModelAttribute("soggettoRichiedente") Parente soggettoRichiedente) throws Exception {

		datiValidator.validate(dati, result);

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			response.setRenderParameter("action", "renderRichiestaTrasportoForm");
		}
		else {

			if (dati.getTipoRichiedente().equalsIgnoreCase("a titolo personale")) {
				// Se la richiesta è a TITOLO PERSONALE, i dati anagrafici devono essere presi dalla
				// visura
				dati.setDisComuneNasc(soggettoRichiedente.getComuneNascita());
				dati.setDisProvinciaNasc(soggettoRichiedente.getProvinciaNascita());
				dati.setDisDataNascita(soggettoRichiedente.getDataNascita());
				dati.setDisComuneRes(soggettoRichiedente.getComuneResidenza());
				dati.setDisProvinciaRes(soggettoRichiedente.getProvinciaResidenza());
				dati.setDisIndirizzoRes(soggettoRichiedente.getIndirizzoResidenza());
			}
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune()); // specificare
			// il
			// comune

			// scelto in fase iniziale
			model.addAttribute("datiDichiarazione", dati);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderRichiestaTrasportoForm");
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
	@ResourceMapping("richiestaTrasportoReport")
	public void serveCertificato(@ModelAttribute("datiDichiarazione") DatiRichiestaTrasporto dati, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {

		log.debug("richiestaTrasportoReport ");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiRichiestaTrasporto> beans = new ArrayList<DatiRichiestaTrasporto>();

		beans.add(dati);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), REPORT_PATH, null);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);
		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Presenta la form per la dichiarazione
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRichiestaTrasportoForm")
	public String rederIscrizioneTemporaneaForm(Model model) throws Exception {
		return toLocalViewPath("richiestaTrasporto");
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
	 * "/trasportoachiamata/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
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
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, ActionRequest request, @ModelAttribute("datiAnagrafici") DatiAnagrafici datiAnagrafici,
			@ModelAttribute("componentiNucleoFamiliare") ArrayList<ComponentiNucleoFamiliare> componenti) throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);

		// List<ComponentiNucleoFamiliare> componenti =
		// datiAnagrafici.getComponentiNucleoFamiliare();
		DatiRichiestaTrasporto dati = new DatiRichiestaTrasporto();
		Parente soggettoRichiedente = new Parente();

		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codFisc);
				DatiUtente datiUtente = visureService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));

				soggettoRichiedente.setCf(componente.getCodiceFiscale());
				soggettoRichiedente.setNome(componente.getNome());
				soggettoRichiedente.setCognome(componente.getCognome());
				soggettoRichiedente.setDataNascita(DateFormatUtil.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					soggettoRichiedente.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					soggettoRichiedente.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					soggettoRichiedente.setCodiceIstatComuneNascita(componente.getCodiceIstatComuneNascita());
				}
				dati.setNomeRichiedente(componente.getNome());
				dati.setCognomeRichiedente(componente.getCognome());
				dati.setCfRichiedente(componente.getCodiceFiscale());
			}
		}

		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiDichiarazione", dati);
		model.addAttribute("componentiNucleloFamiliare", componenti);

		response.setRenderParameter("action", "renderRichiestaTrasportoForm");
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

	@ModelAttribute("ruoliRichiedente")
	public Map<String, String> listaRuoliRichiedente() {
		Map<String, String> ruoli = new LinkedHashMap<String, String>();
		ruoli.put("a titolo personale", "a titolo personale");
		ruoli.put("genitore", "genitore");
		ruoli.put("tutore", "tutore");
		ruoli.put("amministratore di sostegno", "amministratore di sostegno");
		ruoli.put("delegato", "delegato");
		ruoli.put("legale rappresentante", "legale rappresentante");

		return ruoli;
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
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiDichiarazione") DatiRichiestaTrasporto dati)
			throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		if (selectNumAllegati == null) {
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(dati);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(request));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA);
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
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_TRASPORTO_A_CHIAMATA)
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
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param dati
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DatiRichiestaTrasporto dati) {

		RichiestaTrasportoRichiesta interop = new RichiestaTrasportoRichiesta();

		interop.setTipoRichiesta(dati.getTipoRichiesta());

		interop.setNomeRichiedente(dati.getNomeRichiedente());
		interop.setCognomeRichiedente(dati.getCognomeRichiedente());
		interop.setTipoRichiedente(dati.getTipoRichiedente());
		if (!dati.getTipoRichiedente().equalsIgnoreCase("a titolo personale")) {
			Soggetto disabile = new Soggetto();
			disabile.setNome(dati.getDisNome());
			disabile.setCognome(dati.getDisCognome());
			disabile.setDataNascita(dati.getDisDataNascita());
			disabile.setComuneNascita(dati.getDisComuneNasc());
			disabile.setProvinciaNascita(dati.getDisProvinciaNasc());
			disabile.setComuneResidenza(dati.getDisComuneRes());
			disabile.setProvinciaResidenza(dati.getDisProvinciaRes());
			disabile.setIndirizzoResidenza(dati.getDisIndirizzoRes());
			disabile.setCivicoResidenza(dati.getDisNumCivico());
			disabile.setTelefono(dati.getDisTelefono());
			disabile.setEmail(dati.getDisEmail());
			interop.setDisabile(disabile);
		}

		interop.setSediaARotelle(dati.isSediaARotelle());
		interop.setAnnoIsee(dati.getAnnoIsee());
		interop.setIsee(dati.getIsee());
		interop.setTipoSportello(dati.getTipoSportello());
		interop.setSportello(dati.getSportello());
		interop.setIndirizzoSportello(dati.getIndirizzoSportello());

		String xml = xmlHelper.marshal(interop);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView maxUploadSizeExceededException(Exception exception, PortletRequest portletRequest) {

		log.error("maxUploadSizeExceededException :: " + exception.getMessage());

		Map<Object, Object> model = new HashMap<Object, Object>();
		String maxUploadSize = String.valueOf(((MaxUploadSizeExceededException) exception).getMaxUploadSize());
		model.put("formError", messageSource.getMessage("Maximum.upload.size.exceded", new String[] { maxUploadSize }, portletRequest.getLocale()));

		return new ModelAndView(toLocalViewPath("uploadDichiarazione"), (Map) model);
	}

}
