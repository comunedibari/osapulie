package it.osapulie.anagrafe.web.portlet.iscrizioneanagrafetemporanea.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

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

import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.iscrizioneanagrafetemporanea.form.DatiIscrizioneAnagrafeTemporanea;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.ComuneService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio di Iscrizione all'Anagrafe Temporanea.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("iscrizioneAnagrafeTemporaneaPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiIscrizione", "param", "uploadItem" })
public class IscrizioneAnagrafeTemporaneaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(IscrizioneAnagrafeTemporaneaPortletController.class);

	public static final String SERVIZIO = "ISCRIZIONE ALL'ANAGRAFE TEMPORANEA";

	public static final String REPORT_PATH = "/reports/iscrizioneAnagrafeTemporanea.jrxml";
	public static final String REPORT_NAME = "iscrizioneAnagrafeTemporanea.pdf";

	public static final String JSP_PATH = "iscrizioneanagrafetemporanea/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("iscrizioneAnagrafeTemporaneaValidator")
	private Validator iscrizioneValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	private VisureService visureService;

	@Autowired
	private ComuneService comuneService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @param session
	 * @return il view ID da renderizzare.
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session) throws Exception {
		log.debug("homePage-iscrizioneAnagrafeTemporaneaPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la richiesta di iscrizione all'anagrafe temporanea.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getIscrizioneTemporaneaForm")
	public void getIscrizioneTemporaneaForm(Model model, ActionResponse response, ActionRequest request) throws Exception {

		log.debug("getIscrizioneTemporaneaForm");
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		DatiIscrizioneAnagrafeTemporanea dati = new DatiIscrizioneAnagrafeTemporanea();
		dati.setCodiceFiscale(codiceFiscale);

		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		try {
			DatiUtente datiGen = null;
			ComponentiNucleoFamiliare componente = null;
			try {
				DatiAnagrafici datiAnagrafici = visureService.richiediDatiAnagrafici(richiesta, userPreferences);

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				datiGen = visureService.richiediDatiAnagraficiAltriServizi(richiestaGen, userPreferences);
				componente = null;
				for (int k = 0; k < datiAnagrafici.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(datiAnagrafici.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = datiAnagrafici.getComponentiNucleoFamiliare().get(k);
					}
				}
			}
			catch (Exception e) {
				log.error("getIscrizioneTemporaneaForm :: " + e.getMessage(), e);
			}

			if (componente != null && datiGen != null) {
				dati.setCodiceFiscale(componente.getCodiceFiscale());
				dati.setNome(componente.getNome());
				dati.setCognome(componente.getCognome());
				dati.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					if (comuneByCodiceISTAT != null) {
						dati.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						dati.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
				}
				dati.setIndirizzoResidenza(datiGen.getIndirizzo());
			}
			// Caricamento dati da utente in OSApulie
			else {
				OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
				ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();
				dati.setCodiceFiscale(codiceFiscale);
				dati.setNome(profiloUtente.getNome());
				dati.setCognome(profiloUtente.getCognome());
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					dati.setComuneNascita(comuneIsa.getComune().getDenominazione());
					dati.setProvinciaNascita(comuneIsa.getComune().getProvincia().getDenominazioneProvincia());
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					dati.setIndirizzoResidenza(residenza.getVia());
					Comune comune = residenza.getComune();
					if (comune != null) {
						dati.setComuneIscrizione(comune.getDenominazione());
					}
				}
			}
		}
		catch (Exception e) {
			log.error("getIscrizioneTemporaneaForm :: " + e.getMessage(), e);
		}

		model.addAttribute("datiIscrizione", dati);
		response.setRenderParameter("action", "renderIscrizioneTemporaneaForm");
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "action=getUploadForm")
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
	 * @throws PortletException
	 * @throws IOException
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati) throws PortletException, IOException {
		log.debug("uploadFile");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		if (selectNumAllegati == null) {
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(request));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA);
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
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
				}

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
	 * @param iscrizione
	 * @param result
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaIscrizione")
	public void compilaIscrizioneTemporanea(@ModelAttribute("datiIscrizione") DatiIscrizioneAnagrafeTemporanea datiIscrizione, BindingResult result, Model model, ActionResponse response,
			ActionRequest request) throws Exception {
		log.debug("compilaIscrizioneTemporanea CF=" + datiIscrizione.getCodiceFiscale());
		iscrizioneValidator.validate(datiIscrizione, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			response.setRenderParameter("action", "renderIscrizioneTemporaneaForm");
		}
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune()); // specificare
			// il comune scelto in fase iniziale
			model.addAttribute("datiIscrizione", datiIscrizione);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderIscrizioneTemporaneaForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @param param
	 * @throws Exception
	 */
	@ResourceMapping("iscrizioneAnagrafeTemporaneaReport")
	public void serveCertificato(@ModelAttribute("datiIscrizione") DatiIscrizioneAnagrafeTemporanea datiIscrizione, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {
		log.debug("iscrizioneAnagrafeTemporaneaReport");
		log.debug("dati=" + datiIscrizione);

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiIscrizioneAnagrafeTemporanea> beans = new ArrayList<DatiIscrizioneAnagrafeTemporanea>();

		beans.add(datiIscrizione);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA, REPORT_PATH,
				null);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);
		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA);
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
	 * Presenta la form per la richiesta di iscrizione all'anagrafe temporanea
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderIscrizioneTemporaneaForm")
	public String rederIscrizioneTemporaneaForm(Model model) throws Exception {
		return toLocalViewPath("iscrizioneAnagrafeTemporanea");
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
	 * "/iscrizioneanagrafetemporanea/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
