package it.osapulie.sociali.web.portlet.anzianicaseriposo;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import it.osapulie.sociali.web.portlet.model.AllegatoUnoBean;
import it.osapulie.sociali.web.portlet.model.DatiDichiarazioneCaseRiposo;
import it.osapulie.sociali.web.portlet.model.Parente;
import it.osapulie.sociali.web.portlet.util.DateFormatUtil;
import it.osapulie.sociali.web.portlet.util.impl.PortletConstants;
import it.osapulie.sociali.web.ws.input.types.AllegatoUno;
import it.osapulie.sociali.web.ws.input.types.CaseRiposoRichiesta;
import it.osapulie.sociali.web.ws.input.types.Familiare;
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

@Controller("anzianiCaseRiposoController")
@RequestMapping("view")
@SessionAttributes({ "datiDichiarazione", "param", "uploadItem", "soggettoRichiedente", "componentiNucleoFamiliare" })
public class AnzianiCaseRiposoController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(AnzianiCaseRiposoController.class);

	private static final String REPORT_PATH = "/reports/dichiarazioneCaseRiposo.jrxml";
	private static final String SUB_REPORT_PATH = "/reports/caseriposoA1_subreport.jrxml";
	private static final String SUB_SUB_REPORT_PATH_1 = "/reports/tableNucleo_subreport.jrxml";
	private static final String SUB_SUB_REPORT_PATH_2 = "/reports/tableReddito_subreport.jrxml";
	private static final String SUB_SUB_REPORT_PATH_3 = "/reports/caseriposoA1_subreport_subreport1.jrxml";
	private static final String REPORT_NAME = "dichiarazioneCaseRiposo.pdf";

	private static final String JSP_PATH = "anzianicaseriposo/";

	private static final String SERVIZIO = "ANZIANI: RICHIESTA CASA DI RIPOSO";

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
	@Named("datiCaseRiposoValidator")
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
		log.debug("homePage-dichiarazioneCaseRiposoPortlet");
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
	@ActionMapping(params = "action=getDichiarazioneCaseRiposoForm")
	public void getIscrizioneTemporaneaForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getDichiarazioneCaseRiposoForm");

		DatiDichiarazioneCaseRiposo dati = new DatiDichiarazioneCaseRiposo();
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
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
		ArrayList<Parente> listaParenti = new ArrayList<Parente>();

		for (int k = 0; k < datiAnagrafci.getComponentiNucleoFamiliare().size(); k++) {
			if (codiceFiscale.equals(datiAnagrafci.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {

				// Il soggetto considerato è il RICHIEDENTE
				richiedente = datiAnagrafci.getComponentiNucleoFamiliare().get(k);
				soggettoRichiedente.setCf(richiedente.getCodiceFiscale());
				soggettoRichiedente.setNome(richiedente.getNome());
				soggettoRichiedente.setCognome(richiedente.getCognome());
				soggettoRichiedente.setDataNascita(DateFormatUtil.getDataGGMMAAAA(richiedente.getDataNascita().getTime()));
				if (richiedente.isCittadinanzaItaliana() && richiedente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(richiedente.getCodiceIstatComuneNascita());
					soggettoRichiedente.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					soggettoRichiedente.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					soggettoRichiedente.setCodiceIstatComuneNascita(richiedente.getCodiceIstatComuneNascita());
				}
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());

			}
			else if (codiceFiscale != null && !codiceFiscale.equals(datiAnagrafci.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
				ComponentiNucleoFamiliare familiare = datiAnagrafci.getComponentiNucleoFamiliare().get(k);
				Parente p = new Parente();
				p.setNome(familiare.getNome());
				p.setCognome(familiare.getCognome());
				p.setDataNascita(DateFormatUtil.getDataGGMMAAAA(familiare.getDataNascita().getTime()));
				p.setAlimenti(false);
				p.setParentela(familiare.getRelazioneParentela());
				p.setReddito("0");
				listaParenti.add(p);
			}
		}
		dati.setRichiedente(soggettoRichiedente);
		dati.setParenti(listaParenti);

		model.addAttribute("componentiNucleoFamiliare", datiAnagrafci.getComponentiNucleoFamiliare());
		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiDichiarazione", dati);

		response.setRenderParameter("action", "renderDichiarazioneCaseRiposoForm");
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
	public void compilaDichiarazione(@ModelAttribute("datiDichiarazione") DatiDichiarazioneCaseRiposo datiDichiarazione, BindingResult result, Model model, ActionResponse response,
			ActionRequest request) throws Exception {

		datiValidator.validate(datiDichiarazione, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			response.setRenderParameter("action", "renderDichiarazioneCaseRiposoForm");
		}
		else {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", helper.getUserPreferences(request).getNomeComune());
			// specificare il comune scelto in fase iniziale
			model.addAttribute("datiDichiarazione", datiDichiarazione);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			response.setRenderParameter("action", "renderDichiarazioneCaseRiposoForm");
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
	@ResourceMapping("dichiarazioneCaseRiposoReport")
	public void serveCertificato(@ModelAttribute("datiDichiarazione") DatiDichiarazioneCaseRiposo datiDichiarazione, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {

		log.debug("dichiarazioneCaseRiposoReport ");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// Query the service layer for some objects
		List<DatiDichiarazioneCaseRiposo> beans = new ArrayList<DatiDichiarazioneCaseRiposo>();

		beans.add(datiDichiarazione);

		if (datiDichiarazione.getRichiedente() != null) {
			param.put("ricNome", datiDichiarazione.getRichiedente().getNome());
			param.put("ricCognome", datiDichiarazione.getRichiedente().getCognome());
			param.put("ricDataNascita", datiDichiarazione.getRichiedente().getDataNascita());
			param.put("ricProvinciaNascita", datiDichiarazione.getRichiedente().getProvinciaNascita());
			param.put("ricComuneResidenza", datiDichiarazione.getRichiedente().getComuneResidenza());
			param.put("ricIndirizzoResidenza", datiDichiarazione.getRichiedente().getIndirizzoResidenza());
			param.put("ricComuneNascita", datiDichiarazione.getRichiedente().getComuneNascita());
			param.put("ricProvinciaResidenza", datiDichiarazione.getRichiedente().getProvinciaResidenza());
		}
		// Dati allegati
		AllegatoUnoBean aUnoBean = new AllegatoUnoBean();
		if (datiDichiarazione.getRuolo().equalsIgnoreCase("a titolo personale")) {
			aUnoBean.setNome(datiDichiarazione.getRichiedente().getNome());
			aUnoBean.setCognome(datiDichiarazione.getRichiedente().getCognome());
			aUnoBean.setDataNascita(datiDichiarazione.getRichiedente().getDataNascita());
			aUnoBean.setComuneNascita(datiDichiarazione.getRichiedente().getComuneNascita());
			aUnoBean.setProvinciaNascita(datiDichiarazione.getRichiedente().getProvinciaNascita());
		}
		else {
			aUnoBean.setNome(datiDichiarazione.getaUnoNome());
			aUnoBean.setCognome(datiDichiarazione.getaUnoCognome());
			aUnoBean.setDataNascita(datiDichiarazione.getaUnoDataNascita());
			aUnoBean.setComuneNascita(datiDichiarazione.getaUnoComuneNascita());
			aUnoBean.setProvinciaNascita(datiDichiarazione.getaUnoProvinciaNascita());
		}
		aUnoBean.setAutosufficiente(datiDichiarazione.isAutosufficiente());
		aUnoBean.setParzialmenteAutosuf(datiDichiarazione.isParzialmenteAutosuf());
		aUnoBean.setPatolTemporanea(datiDichiarazione.isPatolTemporanea());
		aUnoBean.setServiziSimiliComune(datiDichiarazione.isServiziSimiliComune());
		aUnoBean.setServiziSimiliEnte(datiDichiarazione.isServiziSimiliEnte());
		aUnoBean.setCanoneAppartamento(datiDichiarazione.getCanoneAppartamento());
		aUnoBean.setParenti(datiDichiarazione.getParenti());
		aUnoBean.setServiziComune(datiDichiarazione.getServiziComune());
		aUnoBean.setAlimenti(datiDichiarazione.isAlimenti());
		aUnoBean.setAppartamentoProprio(datiDichiarazione.isAppartamentoProprio());
		aUnoBean.setServiziEntiPubblici(datiDichiarazione.getServiziEntiPubblici());

		List<AllegatoUnoBean> listaAllegatoUno = new ArrayList<AllegatoUnoBean>();
		listaAllegatoUno.add(aUnoBean);
		param.put("aUnoBean", listaAllegatoUno);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter", SUB_REPORT_PATH);
		subreportJrxmlMap.put("subSubReportParameter1", SUB_SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subSubReportParameter2", SUB_SUB_REPORT_PATH_2);
		subreportJrxmlMap.put("subSubReportParameter3", SUB_SUB_REPORT_PATH_3);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);
		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO);
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
	@RenderMapping(params = "action=renderDichiarazioneCaseRiposoForm")
	public String rederIscrizioneTemporaneaForm(Model model) throws Exception {
		return toLocalViewPath("dichiarazioneCaseRiposo");
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

		List<Parente> listaParenti = new ArrayList<Parente>();
		DatiDichiarazioneCaseRiposo dati = new DatiDichiarazioneCaseRiposo();
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
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					soggettoRichiedente.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					soggettoRichiedente.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					soggettoRichiedente.setCodiceIstatComuneNascita(componente.getCodiceIstatComuneNascita());
				}
				soggettoRichiedente.setIndirizzoResidenza(datiUtente.getIndirizzo());
				soggettoRichiedente.setComuneResidenza(datiUtente.getComuneResidenza());
				soggettoRichiedente.setProvinciaResidenza(datiUtente.getProvinciaResidenza());

			}
			else if (codFisc != null && !codFisc.equals(componente.getCodiceFiscale())) {
				Parente p = new Parente();
				p.setNome(componente.getNome());
				p.setCognome(componente.getCognome());
				p.setDataNascita(DateFormatUtil.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				p.setAlimenti(false);
				p.setParentela(componente.getRelazioneParentela());
				p.setReddito("0");
				listaParenti.add(p);
			}
		}
		dati.setParenti(listaParenti);
		dati.setRichiedente(soggettoRichiedente);

		model.addAttribute("soggettoRichiedente", soggettoRichiedente);
		model.addAttribute("datiDichiarazione", dati);
		model.addAttribute("componentiNucleloFamiliare", componenti);

		response.setRenderParameter("action", "renderDichiarazioneCaseRiposoForm");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/anzianicaseriposo/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	@ModelAttribute("ruoliRichiedente")
	public Map<String, String> listaRuoliRichiedente() {
		Map<String, String> ruoli = new LinkedHashMap<String, String>();
		ruoli.put("a titolo personale", "a titolo personale");
		ruoli.put("parente", "parente");
		ruoli.put("affine", "affine");

		return ruoli;
	}

	@ModelAttribute("listaCondizioni")
	public List<String> listaCondizioni() {
		List<String> condizioni = new ArrayList<String>();
		condizioni.add("autosufficiente, ma con situazione di disagio sociale");
		condizioni.add("solo, affetto da patologie e /o invalidità con temporanea riduzione dell’autosufficienza (allegare certificato medico o documentazione attestante l’invalidità)");
		condizioni.add("parzialmente autosufficiente, anche con familiari che per comprovati impedimenti (età, salute, etc.) non siano in grado di fornire adeguata assistenza");
		condizioni.add("di non usufruire di altri servizi a carico del comune");
		condizioni.add("di usufruire di altri servizi a carico del comune");
		condizioni.add("di non usufruire di altri servizi a carico di altri Enti pubblici");
		condizioni.add("di usufruire di altri servizi a carico di altri Enti pubblici");

		return condizioni;
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
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest portletRequest, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiDichiarazione") DatiDichiarazioneCaseRiposo datiDichiarazione)
			throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		if (selectNumAllegati == null) {
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(datiDichiarazione);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getProfiloUtente(session), helper.getUserPreferences(portletRequest));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, portletRequest.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(portletRequest));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO);
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
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
				.setNomeServizio(SERVIZIO)
				.getTempiMedi();
				
				auditDwhService.invokeAuditService(null, null, tempiMediDto);

				 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute() {
					
					  
							public String updateServizioProtocollo() {
							 
								return null;
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_ANZIANI_CASE_RIPOSO)
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

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param dati
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DatiDichiarazioneCaseRiposo dati) {

		CaseRiposoRichiesta interop = new CaseRiposoRichiesta();

		// Richiedente
		Soggetto richiedente = new Soggetto();
		richiedente.setNome(dati.getRichiedente().getNome());
		richiedente.setCognome(dati.getRichiedente().getCognome());
		richiedente.setDataNascita(dati.getRichiedente().getDataNascita());
		richiedente.setComuneNascita(dati.getRichiedente().getComuneNascita());
		richiedente.setProvinciaNascita(dati.getRichiedente().getProvinciaNascita());
		richiedente.setComuneResidenza(dati.getRichiedente().getComuneResidenza());
		richiedente.setProvinciaResidenza(dati.getRichiedente().getProvinciaResidenza());
		richiedente.setIndirizzoResidenza(dati.getRichiedente().getIndirizzoResidenza());
		richiedente.setTelefono(dati.getTelefono());
		interop.setRichiedente(richiedente);

		interop.setRuolo(dati.getRuolo());
		if (dati.getRuolo().equalsIgnoreCase("affine")) {
			interop.setAffinita(dati.getAffinita());
		}

		if (!dati.getRuolo().equalsIgnoreCase("a titolo personale")) {
			Soggetto anziano = new Soggetto();
			anziano.setNome(dati.getAnzNome());
			anziano.setCognome(dati.getAnzCognome());
			anziano.setDataNascita(dati.getAnzDataNascita());
			anziano.setComuneNascita(dati.getAnzComuneNascita());
			anziano.setProvinciaNascita(dati.getAnzProvinciaNascita());
			anziano.setComuneResidenza(dati.getAnzComuneNascita());
			anziano.setProvinciaResidenza(dati.getAnzProvinciaResidenza());
			anziano.setIndirizzoResidenza(dati.getAnzIndirizzoResidenza());
			anziano.setTelefono(dati.getAnzTelefono());
			interop.setAnziano(anziano);
		}

		interop.setTipologia(dati.getTipologia());
		interop.setDenominazione(dati.getDenominazione());
		interop.setUbicazione(dati.getUbicazione());

		// Allegato Uno

		AllegatoUno aUno = new AllegatoUno();
		if (!dati.getRuolo().equalsIgnoreCase("a titolo personale")) {
			aUno.setNome(dati.getRichiedente().getNome());
			aUno.setCognome(dati.getRichiedente().getCognome());
			aUno.setDataNascita(dati.getRichiedente().getDataNascita());
			aUno.setComuneNascita(dati.getRichiedente().getComuneNascita());
			aUno.setProvinciaNascita(dati.getRichiedente().getProvinciaNascita());
		}
		else {
			aUno.setNome(dati.getaUnoNome());
			aUno.setCognome(dati.getaUnoCognome());
			aUno.setComuneNascita(dati.getaUnoComuneNascita());
			aUno.setProvinciaNascita(dati.getaUnoProvinciaNascita());
			aUno.setDataNascita(dati.getaUnoDataNascita());
		}
		aUno.setAutosufficiente(dati.isAutosufficiente());
		aUno.setParzialmenteAutosuff(dati.isParzialmenteAutosuf());
		aUno.setPatolTemporanea(dati.isPatolTemporanea());
		aUno.setServiziSimiliComune(dati.isServiziSimiliComune());
		aUno.setServiziSimiliEnte(dati.isServiziSimiliEnte());
		if (dati.isServiziSimiliComune()) {
			aUno.setServiziComune(dati.getServiziComune());
		}
		if (dati.isServiziSimiliEnte()) {
			aUno.setServiziEntiPubblici(dati.getServiziEntiPubblici());
		}

		aUno.setAlimenti(dati.isAlimenti());
		if (dati.isAlimenti()) {
			if (dati.getParenti() != null) {
				for (Parente p : dati.getParenti()) {
					if (p.isAlimenti()) {
						Familiare familiare = new Familiare();
						familiare.setNome(p.getNome());
						familiare.setCognome(p.getCognome());
						familiare.setDataNascita(p.getDataNascita());
						familiare.setParentela(p.getParentela());
						familiare.setReddito(p.getReddito());
						aUno.getParentiAlimenti().add(familiare);
					}
				}
			}
		}
		aUno.setAppartamentoProprio(dati.isAppartamentoProprio());
		aUno.setCanoneAppartamento(dati.getCanoneAppartamento());

		interop.setAllegatoUno(aUno);

		if (dati.getParenti() != null) {
			for (Parente p : dati.getParenti()) {
				Familiare familiare = new Familiare();
				familiare.setNome(p.getNome());
				familiare.setCognome(p.getCognome());
				familiare.setDataNascita(p.getDataNascita());
				familiare.setParentela(p.getParentela());
				familiare.setReddito(p.getReddito());
				interop.getParenti().add(familiare);
			}
		}

		String xml = xmlHelper.marshal(interop);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

}
