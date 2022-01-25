package it.osapulie.tributi.web.portlet.dichiarazionetari.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.mock.web.MockMultipartFile;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.portlet.handler.PortletSessionRequiredException;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Backup;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Delega;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.StatoEstero;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ProvinciaService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.StatoEsteroService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiDichiarazioneCambioResidenza;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiDichiarazioneCambioResidenza.Componente;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiDichiarazioneTari;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.DatiImmobile;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.Occupante;
import it.osapulie.tributi.web.portlet.dichiarazionetari.form.Valore;
import it.osapulie.tributi.web.portlet.utils.DatiTassaRifiutiComparator;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.input.types.Cessazione;
import it.osapulie.tributi.web.ws.input.types.Cessazione.Rimborso;
import it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeDomestiche.CessazionePerCoabitazione;
import it.osapulie.tributi.web.ws.input.types.Codifica;
import it.osapulie.tributi.web.ws.input.types.Contribuente;
import it.osapulie.tributi.web.ws.input.types.DatiCatastali;
import it.osapulie.tributi.web.ws.input.types.Dichiarante;
import it.osapulie.tributi.web.ws.input.types.Dichiarazione;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta.Errore;
import it.osapulie.tributi.web.ws.input.types.Documento;
import it.osapulie.tributi.web.ws.input.types.Iscrizione;
import it.osapulie.tributi.web.ws.input.types.Iscrizione.UtenzeCommerciali;
import it.osapulie.tributi.web.ws.input.types.Iscrizione.UtenzeDomestiche;
import it.osapulie.tributi.web.ws.input.types.OccupanteImmobile;
import it.osapulie.tributi.web.ws.input.types.OccupanteImmobileVariazione;
import it.osapulie.tributi.web.ws.input.types.PersonaFisica;
import it.osapulie.tributi.web.ws.input.types.PersonaGiuridica;
import it.osapulie.tributi.web.ws.input.types.Rappresentante;
import it.osapulie.tributi.web.ws.input.types.Recapito;
import it.osapulie.tributi.web.ws.input.types.Riduzione;
import it.osapulie.tributi.web.ws.input.types.Riduzione.Tipo;
import it.osapulie.tributi.web.ws.input.types.Trasferimento;
import it.osapulie.tributi.web.ws.input.types.Trasferimento.NuovaUtenzaCommerciale;
import it.osapulie.tributi.web.ws.input.types.Trasferimento.NuovaUtenzaDomestica;
import it.osapulie.tributi.web.ws.input.types.UtenzaCommerciale;
import it.osapulie.tributi.web.ws.input.types.UtenzaCommercialeVariazione;
import it.osapulie.tributi.web.ws.input.types.UtenzaDomestica;
import it.osapulie.tributi.web.ws.input.types.UtenzaDomesticaVariazione;
import it.osapulie.tributi.web.ws.input.types.Variazione;
import it.osapulie.tributi.web.ws.input.types.VariazioneOccupanteImmobile;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.Indirizzo.Civico;
import it.osapulie.util.DwhDataminingUtil;
import it.osapulie.util.DwhServiceAttributeUtil;
import it.osapulie.util.DwhTempiMediUtil;
import it.osapulie.util.IUpdateAttribute;
import it.osapulie.util.audit.ApplicationContextUtil;
import it.osapulie.util.audit.AuditConfiguration;
import it.osapulie.util.audit.AuditCustomMeta;
import it.osapulie.util.audit.Origin;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Portlet controller per il servizio Dichiarazione TARI.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 * @author Andrea Filomena
 */
@Controller("dichiarazioneTariPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiTari", "param", "uploadItem" })
public class DichiarazioneTariPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneTariPortletController.class);

	/**
	 * Numero degli occupanti inseribili nel form.
	 */
	private static final int NUMERO_OCCUPANTI_FORM = 6;
	/**
	 * Numero degli immobili inseribili nel form.
	 */
	private static final int NUMERO_IMMOBILI_FORM = 5;

	public static final String PERSONA_FISICA = "fisica";
	public static final String PERSONA_GIURIDICA = "giuridica";

	public static final String UTENZA_NON_DOMESTICA = "non_domestica";
	public static final String UTENZA_DOMESTICA = "domestica";

	public static final String TIPO_DICHIARAZIONE_ISCRIZIONE = "iscrizione";
	public static final String TIPO_DICHIARAZIONE_VARIAZIONE = "variazione";
	public static final String TIPO_DICHIARAZIONE_CESSAZIONE = "cessazione";
	public static final String TIPO_DICHIARAZIONE_TRASFERIMENTO = "trasferimento";

	public static final String CODIFICA_UTENZA_DOMESTICA = "D";
	public static final String CODIFICA_UTENZA_COMMERCIALE = "C";
	public static final String CODIFICA_UTENZA_ALL = "";

	private static final String SERVIZIO = "DICHIARAZIONE TARI";

	private static final String REPORT_DOMESTICA_PATH = "/reports/dichiarazione_tari_dom.jrxml";
	private static final String REPORT_NON_DOMESTICA_PATH = "/reports/dichiarazione_tari_nd.jrxml";
	private static final String REPORT_ISCRIZIONE_SUB_REPORT_PATH = "/reports/dichiarazione_tari_iscrizione_subreport1.jrxml";
	private static final String REPORT_VARIAZIONE_SUB_REPORT_PATH = "/reports/dichiarazione_tari_variazione_subreport1.jrxml";

	public static final String REPORT_PREFIX_NAME = "Dichiarazione_TARI";
	public static final String REPORT_SUFFIX_NAME = ".pdf";

	public static final String JSP_PATH = "dichiarazionetari/";

	/**
	 * Errore GRAVE/bloccante
	 */
	private static final int ERROR_CODE_1 = 1;
	/**
	 * Errore saltuario ma da verificare
	 */
	private static final int ERROR_CODE_2 = 2;
	/**
	 * Errore non bloccante (ad es. invio email fallito)
	 */
	private static final int ERROR_CODE_3 = 3;

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

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
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	private CommonService commonService;

	@Inject
	private ConfigurazioneService configurazioneService;

	@Inject
	@Named("datiDichiarazioneTariValidator")
	private Validator dichiarazioneValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProvinciaService provinciaService;

	@Inject
	private StatoEsteroService statoEsteroService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private DelegaService delegaService;
	
	@Inject
	private AuditRepository auditRepository;

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
	public String homePage(Model model, PortletSession session, PortletRequest request) throws Exception {
		log.debug("homePage-dichiarazioneTariPortletController");
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
	AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("DICHIARAZIONE TARI")
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
			 auditManager.build();
		return toLocalViewPath("home");
	}

	@ModelAttribute("bozza")
	public DatiDichiarazioneTari getBozza(PortletRequest request, PortletSession session) {

		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE TARI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		UserPreferences userPreferences = helper.getUserPreferences(request);
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
		DatiDichiarazioneTari bozza = commonService.getBozza(userPreferences, servizio.getId(), DatiDichiarazioneTari.class);

		setInfoAggiuntiveForm(bozza);
		auditManager.build();
		return bozza;
	}

	@ActionMapping(params = "action=getBozzaFrom")
	public void getBozzaFrom(@ModelAttribute("bozza") DatiDichiarazioneTari bozza, Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		model.addAttribute("datiTari", bozza);
		String renderForm = "renderDichiarazioneTariDomesticaForm";

		if (UTENZA_NON_DOMESTICA.equalsIgnoreCase(bozza.getTipoUtenza())) {
			renderForm = "renderDichiarazioneTariNonDomesticaForm";
		}
		UserPreferences userPreferences = helper.getUserPreferences(request);
		checkIndirizzoResidenzaConStradario(userPreferences, bozza);
		checkIndirizzoSpedizioneConStradario(userPreferences, bozza);
		auditManager.build();
		response.setRenderParameter("action", renderForm);
	}

	/**
	 * Verifica se è attivare la funzionalità di invio delle segnalazioni custom.
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@ModelAttribute("invioSegnalazioneCustomEnable")
	public boolean invioSegnalazioneCustomEnable(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		boolean invioSegnalazioneCustomEnable = true;

		String codiceServizio = PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI;
		SegnalazioneCustom segnalazioneCustom = configurazioneService.getSegnalazioneCustomServizio(codiceServizio, userPreferences.getIdComuneIsa());

		invioSegnalazioneCustomEnable = invioSegnalazioneCustomEnable && segnalazioneCustom != null;

		return invioSegnalazioneCustomEnable;
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "action=redirectSegnalazioneCustom")
	public void redirectSegnalazioneCustom(ActionRequest request, ActionResponse response, PortletSession session) throws Exception {

		if (invioSegnalazioneCustomEnable(request)) {

			UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);

			// Aggiunta URL portlet segnalazioni
			String codiceServizio = PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI;
			Map<String, String> successReturnRequestParameters = new HashMap<String, String>();
			successReturnRequestParameters.put("action", "getSaltaDichiarazioneForm");

			Map<String, String> cancelReturnRequestParameters = new HashMap<String, String>();
			cancelReturnRequestParameters.put("action", "home");

			SegnalazioneCustom segnalazioneCustom = configurazioneService.getSegnalazioneCustomServizio(codiceServizio, userPreferences.getIdComuneIsa());
			segnalazioneCustom.setCodiceFiscale(profiloUtenteCittadino.getCodiceFiscale());
			segnalazioneCustom.setNome(profiloUtenteCittadino.getNome());
			segnalazioneCustom.setCognome(profiloUtenteCittadino.getCognome());
			segnalazioneCustom.setCodiceServizio(codiceServizio);

			commonService.addSegnalazioneCustomToSession(session, segnalazioneCustom);

			String destinationPortletUrl = commonService.getInvioSegnalazioneCustomUrl(request, successReturnRequestParameters, PortletRequest.ACTION_PHASE, cancelReturnRequestParameters, null);
			response.sendRedirect(destinationPortletUrl);
		}
	}

	/**
	 * Redirect verso la pagina del servizio successivo (settato nella configurazione JSON del
	 * servizio attuale).
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getSaltaDichiarazioneForm")
	public void getSaltaDichiarazioneForm(ActionRequest request, ActionResponse response) throws Exception {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String redirectServizioURL = configurazioneService.getRedirectServizioUrl(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());
		response.sendRedirect(redirectServizioURL);
	}

	/**
	 * Presenta la form per la dichiarazione tari.
	 *
	 * @param model
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getDichiarazioneTariForm")
	public void getDichiarazioneTariForm(Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {

		log.debug("getDichiarazioneTariForm");
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("DICHIARAZIONE TARI")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
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
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String renderForm = "renderDichiarazioneTariDomesticaForm"; // default

		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		//MS
		DwhServiceAttributeUtil serviceAttributeUtil=DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService);
		servizioAttributeDto =  serviceAttributeUtil
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
		.setUUID(uuidStr)
		.getServizioAttribute();
		
		 //MS
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
		.setUuidOperazione(uuidStr)
		.getDatamining();
 
		
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidStr).go_StartTime().getTempiMedi();
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		try {
			Integer identificativoNucleoFamiliare = null;

			DatiAnagrafici datiAnagrafici = null;
			ComponentiNucleoFamiliare componente = null;
			try {
				datiAnagrafici = tributiService.richiediDatiAnagrafici(richiesta, userPreferences);
				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				componente = null;
				for (int k = 0; k < datiAnagrafici.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(datiAnagrafici.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = datiAnagrafici.getComponentiNucleoFamiliare().get(k);
						identificativoNucleoFamiliare = componente.getIdentificativoNucleoFamiliare();
					}
				}
			}
			catch (Exception e) {
				log.warn("getDichiarazioneTariForm :: " + e.getMessage());
				auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			}

			boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());

			DatiDichiarazioneTari datiTari = new DatiDichiarazioneTari();
			datiTari.setIdentificativoNucleoFamiliare(identificativoNucleoFamiliare);
			datiTari.setUuidOperazione(uuidStr);
			datiTari.setIpAddress(AuditCustomMeta.get().getOrigin());
			List<Occupante> occupanti = new ArrayList<Occupante>();
			List<Occupante> varOccupanti = new ArrayList<Occupante>();
			for (int i = 0; i < NUMERO_OCCUPANTI_FORM; i++) {
				Occupante occupante = new Occupante();
				Occupante varOccupante = new Occupante();
				occupanti.add(occupante);
				varOccupanti.add(varOccupante);
			}

			datiTari.setOccupanti(occupanti);
			datiTari.setVariazioneOccupanti(varOccupanti);

			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				datiTari.setTipoPersona(PERSONA_GIURIDICA);
				datiTari.setTipoUtenza(UTENZA_NON_DOMESTICA);
				renderForm = "renderDichiarazioneTariNonDomesticaForm";
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiTari.setNomeSocieta(aziendaByPiva.getRagioneSociale());
				if (aziendaByPiva.getPartitaIva() != null && !aziendaByPiva.getPartitaIva().isEmpty()) {
					datiTari.setPartitaIva(aziendaByPiva.getPartitaIva());
				}
				Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiTari.setViaSedeLegale(sede.getVia());
					datiTari.setNumeroSedeLegale(sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiTari.setComuneSedeLegale(comune.getCodiceIstatAN());
					}
				}
			}
			else {
				datiTari.setTipoPersona(PERSONA_FISICA);
				datiTari.setTipoUtenza(UTENZA_DOMESTICA);
			}

			// Precarico i dati dell'utente nel form
			String codiceIstatComune = userPreferences.getCodiceIstatComune();
			Comune comuneAttuale = comuneService.getComuneByCodiceISTAT(codiceIstatComune);
			if (componente != null && datiAnagrafici != null) {

				// Comune di iscrizione è il comune ISA attuale (se non specificato dopo)
				datiTari.setComuneResidenza(comuneAttuale.getCodiceIstatAN());
				datiTari.setProvResidenza(comuneAttuale.getProvincia() != null ? comuneAttuale.getProvincia().getSigla() : null);

				datiTari.setCodiceFiscale(componente.getCodiceFiscale());
				datiTari.setNome(componente.getNome());
				datiTari.setCognome(componente.getCognome());
				datiTari.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					if (comuneByCodiceISTAT != null) {
						datiTari.setComuneNascita(String.valueOf(comuneByCodiceISTAT.getCodiceIstatAN()));
						datiTari.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getSigla());
					}
				}
				boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
				// Se lo stradario è abilitato x il comune --> non setto i dati nello stradario (non
				// avendo i codici nella visura)
				if (!isIndirizzoResidenzaConStradario) {
					datiTari.setIndirizzoResidenza(datiAnagrafici.getDescrizioneVia());
					datiTari.setCivicoResidenza(datiAnagrafici.getNumeroCivico());
					datiTari.setEsponenteResidenza(datiAnagrafici.getEsponente());
				}

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
						datiTari.setComuneNascita(String.valueOf(comune.getCodiceIstatAN()));
						datiTari.setProvinciaNascita(comune.getProvincia().getSigla());
					}
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					Comune comune = residenza.getComune();
					if (comune != null && !comune.getId().equals(-1L)) {
						datiTari.setComuneResidenza(String.valueOf(comune.getCodiceIstatAN()));
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiTari.setProvResidenza(provincia.getSigla());
						}
					}
					boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
					// Se lo stradario è abilitato x il comune --> non setto i dati nello stradario
					// (non avendo i codici nella visura)
					if (!isIndirizzoResidenzaConStradario) {
						datiTari.setIndirizzoResidenza(residenza.getVia());
					}
				}
			}

			boolean visuraPosizioniTributarieActive = false;
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			if (comuneByPk != null) {
				visuraPosizioniTributarieActive = comuneByPk.getVisuraPosizioniTributarieActive();
			}

			checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
			checkIndirizzoSpedizioneConStradario(userPreferences, datiTari);

			List<DatiTassaRifiuti> situazioniRifiuti = new ArrayList<DatiTassaRifiuti>();
			if (visuraPosizioniTributarieActive) {

				// Carico i dati catastali degli immobili associati al cittadino
				Integer annoCorrente = new Integer(DateUtils.getAnnoCorrente());

				DichiarazioneTassaRifiutiRichiesta richiestaSituazioniRifiuti = new DichiarazioneTassaRifiutiRichiesta();
				if (partitaIvaServizio != null) {
					richiestaSituazioniRifiuti.setPartitaIva(partitaIvaServizio);
				}
				else {
					richiestaSituazioniRifiuti.setCodiceFiscale(codiceFiscale);
				}
				// recupero i dati dell'anno precedente e di quello corrente; se non ci sono dati
				// per l'anno corrente utilizzo gli anni precedebti ed informo l'utente
				richiestaSituazioniRifiuti.setAnnoInizio(annoCorrente - 5);
				richiestaSituazioniRifiuti.setAnnoFine(annoCorrente);

				DichiarazioneTassaRifiutiRisposta rispostaSituazioniRifiuti = tributiService.richiediSituazioneTassaRifiuti(richiestaSituazioniRifiuti, userPreferences);
				situazioniRifiuti = rispostaSituazioniRifiuti.getSituazione();

				Collections.sort(situazioniRifiuti, new DatiTassaRifiutiComparator());
			}
			datiTari.setStradarioEnabled(isStradarioEnabled);
			// FIXME esternalizzare la configurazione del numero max di visuare immobiliari. Per
			// Bari è stato impostato a 3
			int limiteImmobili = NUMERO_IMMOBILI_FORM;
			if (userPreferences.getIdComuneIsa() == 1) {
				limiteImmobili = 3;
			}

			List<DatiTassaRifiuti.Posizioni> posizioni = new ArrayList<DatiTassaRifiuti.Posizioni>();
			List<DatiTassaRifiuti.Posizioni> posizioniDomestiche = new ArrayList<DatiTassaRifiuti.Posizioni>();
			List<DatiTassaRifiuti.Posizioni> posizioniCommerciali = new ArrayList<DatiTassaRifiuti.Posizioni>();

			datiTari.setIscrizioneDom(generateList(userPreferences, posizioni, limiteImmobili, false));
			datiTari.setIscrizioneNonDom(generateList(userPreferences, posizioni, limiteImmobili, false));

			if (situazioniRifiuti != null && !situazioniRifiuti.isEmpty()) {
				// Prelevo l'ultimo anno
				DatiTassaRifiuti situazione = situazioniRifiuti.get(situazioniRifiuti.size() - 1);
				posizioni = situazione.getPosizioni();
				posizioniDomestiche = situazione.getPosizioni();
				posizioniCommerciali = situazione.getPosizioni();
			}

			boolean interoperabilitaEnable = isInteroperabilitaEnable(request);
			datiTari.setLimitatoIscrizione(false);
			datiTari.setEscludiDomestiche(false);
			datiTari.setEscludiCommerciali(false);
			if (interoperabilitaEnable) {
				// attiva solo l'iscrizione in caso di interoperabilità e
				// se l'idContribuente non è settato su nessuna posizione
				posizioni = filtraPosizioni(posizioni, CODIFICA_UTENZA_ALL);
				datiTari.setLimitatoIscrizione(posizioni.isEmpty());

				posizioniDomestiche = filtraPosizioni(posizioni, CODIFICA_UTENZA_DOMESTICA);
				datiTari.setEscludiDomestiche(posizioniDomestiche.isEmpty());

				posizioniCommerciali = filtraPosizioni(posizioni, CODIFICA_UTENZA_COMMERCIALE);
				datiTari.setEscludiCommerciali(posizioniCommerciali.isEmpty());

			}
			datiTari.setIdContribuente(getIdentificativoUtenzaFromPosizioni(posizioni));

			datiTari.setVariazioneDom(generateList(userPreferences, posizioniDomestiche, posizioniDomestiche.size(), false));
			datiTari.setVariazioneNonDom(generateList(userPreferences, posizioniCommerciali, posizioniCommerciali.size(), false));

			datiTari.setCessazioneDom(generateList(userPreferences, posizioniDomestiche, posizioniDomestiche.size(), false));
			datiTari.setCessazioneNonDom(generateList(userPreferences, posizioniCommerciali, posizioniCommerciali.size(), false));

			setSelectedCheckbox(datiTari);

			setInfoAggiuntiveForm(datiTari);
			model.addAttribute("datiTari", datiTari);
		}
		catch (Exception e) {
			log.error("getDichiarazioneTariForm :: " + e.getMessage(), e);
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
		}
		auditManager.build();
		response.setRenderParameter("action", renderForm);
	}

	/**
	 * Restituisce l'idContribuente in prima posizione nella concatenazione
	 * idContribuente|idUtenza|dataVariazioneUtenza|dataInizioUtenza dell'identificativo utenza di
	 * una posizione
	 *
	 * @param posizioni
	 * @return int
	 */
	private String getIdentificativoUtenzaFromPosizioni(List<Posizioni> posizioni) {
		if (posizioni != null) {
			for (int i = 0; i < posizioni.size(); i++) {
				Posizioni posizione = posizioni.get(i);
				if (posizione.getIdentificativoUtenza() != null && !posizione.getIdentificativoUtenza().isEmpty()) {
					return posizione.getIdentificativoUtenza();
				}
			}
		}
		return "";
	}

	/**
	 * Restituisce la lista delle posizioni con idContribuente valorizzato
	 *
	 * @param posizioni
	 * @return
	 */
	private List<DatiTassaRifiuti.Posizioni> filtraPosizioni(List<DatiTassaRifiuti.Posizioni> posizioni, String tipologia) {

		List<DatiTassaRifiuti.Posizioni> result = new ArrayList<DatiTassaRifiuti.Posizioni>();
		for (int i = 0; i < posizioni.size(); i++) {
			Posizioni posizione = posizioni.get(i);
			if (posizione.getIdentificativoUtenza() != null && !posizione.getIdentificativoUtenza().isEmpty()) {

				if (tipologia.isEmpty() || tipologia.equalsIgnoreCase(posizione.getDestinazione().getCodice())) {
					result.add(posizione);
				}
			}
		}

		return result;
	}

	/**
	 * Verifica se il comune di residenza dell'utente coincide con il comune attualmente selezionato
	 * --> se si abilito lo stradario per la selezione dell'indirizzo di residenza
	 *
	 * @param userPreferences
	 * @param datiDichiarazioneTari
	 * @return
	 */
	private boolean checkIndirizzoResidenzaConStradario(UserPreferences userPreferences, DatiDichiarazioneTari datiDichiarazioneTari) {

		boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());
		if (isStradarioEnabled && datiDichiarazioneTari.getComuneResidenza() != null && !datiDichiarazioneTari.getComuneResidenza().isEmpty()) {
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(datiDichiarazioneTari.getComuneResidenza());
			if (comuneResidenza != null && comuneResidenza.getId().equals(comuneByPk.getComune().getId())) {
				datiDichiarazioneTari.setIndirizzoResidenzaConStradario(true);
				return true;
			}
		}
		datiDichiarazioneTari.setIndirizzoResidenzaConStradario(false);

		return false;
	}

	/**
	 * Verifica se il comune di spedizione dell'utente coincide con il comune attualmente
	 * selezionato --> se si abilito lo stradario per la selezione dell'indirizzo di residenza
	 *
	 * @param userPreferences
	 * @param datiDichiarazioneTari
	 * @return
	 */
	private boolean checkIndirizzoSpedizioneConStradario(UserPreferences userPreferences, DatiDichiarazioneTari datiDichiarazioneTari) {

		boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());
		if (isStradarioEnabled && datiDichiarazioneTari.getComuneSpedizione() != null && !datiDichiarazioneTari.getComuneSpedizione().isEmpty()) {
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			Comune comuneSpedizione = comuneService.getComuneByCodiceISTAT(datiDichiarazioneTari.getComuneSpedizione());
			if (comuneSpedizione != null && comuneSpedizione.getId().equals(comuneByPk.getComune().getId())) {
				datiDichiarazioneTari.setIndirizzoSpedizioneConStradario(true);
				return true;
			}
		}
		datiDichiarazioneTari.setIndirizzoSpedizioneConStradario(false);

		return false;
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
		item.setSignedFiles(false);
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
	 * @throws PortletException
	 * @throws IOException
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiTari") DatiDichiarazioneTari datiTari, SessionStatus sessionStatus) {

		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		String deleganteId="";
		UserPreferences userPreferences = helper.getUserPreferences(request);
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getDelegante()!=null) {
			deleganteId=""+delega.getDelegante().getId();
		}
		}
		
		
		log.debug("uploadFile");
		if (selectNumAllegati == null) {
			//UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
			}
			else {
				validator.validate(uploadItem, result, currentProfiloUtenteCittadino, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
			}

			List<MultipartFile> multipartFiles = uploadItem.getMultipartFiles();
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}

				String allegato1 = datiTari.getAllegatoUno();
				String allegato2 = datiTari.getAllegatoDue();
				String allegato3 = datiTari.getAllegatoTre();
				String allegato4 = datiTari.getAllegatoQuattro();
				ArrayList<String> descrizioni = new ArrayList<String>();
				int numAllegatiSelectInteger = multipartFiles.size();
				if (allegato1.trim().length() > 0) {
					numAllegatiSelectInteger++;
					descrizioni.add(allegato1);
				}
				if (allegato2.trim().length() > 0) {
					numAllegatiSelectInteger++;
					descrizioni.add(allegato2);
				}
				if (allegato3.trim().length() > 0) {
					numAllegatiSelectInteger++;
					descrizioni.add(allegato3);
				}
				if (allegato4.trim().length() > 0) {
					numAllegatiSelectInteger++;
					descrizioni.add(allegato4);
				}
				for (int i = 0; i < numAllegatiSelectInteger - descrizioni.size(); i++) {
					descrizioni.add(null);
				}

				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("descrAllegati", descrizioni);
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderUploadForm");
			}
			else {
				try {

					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(userPreferences);
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);

					String numeroProtocollo = null;
					boolean interoperabilitaEnable = isInteroperabilitaEnable(request);

					DichiarazioneTassaRifiutiInputRichiesta dichiarazioneTassaRifiutiInputRichiesta = getDichiarazioneTassaRifiutiInputRichiesta(datiTari, userPreferences);

					// Allego documenti
					List<Documento> documenti = dichiarazioneTassaRifiutiInputRichiesta.getDocumento();

					List<Documento> documentiFromUploadItem = getDocumentiFromUploadItem(uploadItem);
					documenti.addAll(documentiFromUploadItem);

					// Salvataggio backup locale per recupero causa eventuali errori
					Backup backup = commonService.saveBackup(tributiService.getDichiarazioneXml(dichiarazioneTassaRifiutiInputRichiesta), userPreferences, servizio.getId());

					// Se è abilitata l'interoperabilità...
					if (interoperabilitaEnable) {

						DichiarazioneTassaRifiutiInputRisposta dichiarazioneTassaRifiutiInputRisposta = tributiService.inviaDichiarazione(dichiarazioneTassaRifiutiInputRichiesta, userPreferences);
						Errore errore = dichiarazioneTassaRifiutiInputRisposta.getErrore();
						if (errore != null && errore.getCodice() != ERROR_CODE_3) {
							throw new Exception("Codice di errore: " + errore.getCodice() + ", descrizione: " + errore.getDescrizione());
						}

						// Aggiunta info aggiuntive con ID pratica
						fascicolo.addInfoAggiuntive("ID Pratica", dichiarazioneTassaRifiutiInputRisposta.getIdPratica());
						Calendar dataInserimento = dichiarazioneTassaRifiutiInputRisposta.getDataInserimento();
						if (dataInserimento != null) {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							fascicolo.addInfoAggiuntive("Data Inserimento", simpleDateFormat.format(dataInserimento.getTime()));
						}
						numeroProtocollo = PortletUtils.getNumeroProtocollo(dichiarazioneTassaRifiutiInputRisposta.getNumeroProtocollo(),
								dichiarazioneTassaRifiutiInputRisposta.getDataProtocollo().getTime());
					
 
					
				  		AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addUuidOperazione(datiTari.getUuidOperazione())
						.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE - UPLOAD ")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addDelegante(deleganteId)
						.addNumeroProcollo(""+numeroProtocollo)
						.addEsitoSuccess("SUCCESS - IDPRATICA "+dichiarazioneTassaRifiutiInputRisposta.getIdPratica())
						.setOrigin(Origin.getIp(request))
						.build();
					
					
					}
					// ... altrimenti invio a PEC/Protocollo nel modo classico
					else {
						MultipartFile multipartFileForInterop = generateMultipartFileForInterop(dichiarazioneTassaRifiutiInputRichiesta);
						if (multipartFileForInterop != null) {
							if (multipartFiles == null) {
								multipartFiles = new ArrayList<MultipartFile>();
							}
							multipartFiles.add(multipartFileForInterop);
						}
						numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, userPreferences);
					}

					if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
						fascicolo.setNumeroProtocollo(numeroProtocollo);
					}

					fascicoloService.saveNuovaRichiesta(fascicolo);

					// Invio email a cittadino
					String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
					try {
						tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
					
						  		AuditConfiguration
								.configure()
								.audit()
								.addInizioOperazione()
								.addUuidOperazione(datiTari.getUuidOperazione())
								.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
								.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
								.addPaginaCorrente(helper.getCurrentPageName(request))
								.addDelegante(deleganteId)
								.addNumeroProcollo(""+numeroProtocollo)
								.addEsitoSuccess()
								.setOrigin(Origin.getIp(request))
								.build();
					}
					catch (Exception e) {
						
						log.error("upload :: " + e.getMessage(), e);
				  		AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addUuidOperazione(datiTari.getUuidOperazione())
						.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addDelegante(deleganteId)
						.addNumeroProcollo(""+numeroProtocollo)
						.addEsitoError("Exception errore invio email al cittadino")
						.setOrigin(Origin.getIp(request))
						.build();
						
					}

					// Invio email ad azienda
					if (partitaIvaServizio != null) {
						String subjectAzienda = messageSource.getMessage("mail.azienda.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
						try {
							tributiCommonService.sendMailToCompany(userPreferences, aziendaByPiva, subjectAzienda, SERVIZIO, numeroProtocollo);
						
							 AuditConfiguration
									.configure()
									.audit()
									.addInizioOperazione()
									.addUuidOperazione(datiTari.getUuidOperazione())
									.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE UPLOAD INVIO EMAIL AZIENDA")
									.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
									.addPaginaCorrente(helper.getCurrentPageName(request))
									.addDelegante(deleganteId)
									.addNumeroProcollo(""+numeroProtocollo)
									.addEsitoSuccess()
									.setOrigin(Origin.getIp(request))
									.build();
						
						}
						catch (Exception e) {
							log.error("upload :: " + e.getMessage(), e);
							AuditConfiguration
							.configure()
							.audit()
							.addInizioOperazione()
							.addFineOperazione()
							.addUuidOperazione(datiTari.getUuidOperazione())
							.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
							.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
							.addPaginaCorrente(helper.getCurrentPageName(request))
							.addDelegante(deleganteId)
							.addNumeroProcollo(""+numeroProtocollo)
							.addEsitoError("Exception errore invio email azienda")
							.setOrigin(Origin.getIp(request))
							.build();
						}
					}
					 
					
					DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
					
					//MS
					tempiMediDto = DwhTempiMediUtil.get(dwhService)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
					.setNomeServizio(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione()))
					.go_EndTime(datiTari.getUuidOperazione()).getTempiMedi();
					
					   final String protocollo=numeroProtocollo;
					   final String uuidOperazione=datiTari.getUuidOperazione();
					   final String tipoDichiarazione=  datiTari.getTipoDichiarazione();
					 
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
								 
									return determinaTipoDichiarazione(tipoDichiarazione);
								}
								public Date updateServizioFine() {
								 
									return new Date();
								}
								public String searchUUID() {
								
									return uuidOperazione;
								}
							});
					
					
					String idServizio=""+servizio.getId();
					
					// Eliminazione Bozza
					commonService.deleteBozza(userPreferences, servizio.getId());

					// Eliminazione Backup
					commonService.deleteBackup(backup.getId());

					 AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addUuidOperazione(datiTari.getUuidOperazione())
						.addOperazioneRichiesta(SERVIZIO+" - "+determinaTipoDichiarazione(datiTari.getTipoDichiarazione())+" - STEP FINALE UPOAD ")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
						.addPaginaCorrente(helper.getCurrentPageName(request))
						.addDelegante(deleganteId)
						.addNumeroProcollo(""+numeroProtocollo)
						.addFineOperazione()
						.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
						.addServizioTimeString()
						.addServizioUuidTransazione(datiTari.getUuidOperazione())
						.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)
						.setOrigin(Origin.getIp(request))
						.addCallGeo()
						.build();
					
					
					// Verifica servizio dichiarazione cambio residenza attivo per il comune ISA
					boolean dichiarazioneCambioResidenzaActive = false;
					ComuneISA comuneISA = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
					List<ComuneISAServizio> servizi = comuneISA.getServizi();
					if (servizi != null) {
						for (ComuneISAServizio comuneISAServizio : servizi) {
							if (comuneISAServizio.getServizio().getCodiceServizio().equals(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)) {
								if (comuneISAServizio.isAttivo() && comuneISAServizio.getServizio().isAttivo()) {
									dichiarazioneCambioResidenzaActive = true;
									break;
								}
							}
						}
					}

					// Salvataggio bozza in cambio residenza (solo se il servizio di destinazione è
					// attivo e se è un'iscrizione domestica)
					if (dichiarazioneCambioResidenzaActive && datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) && datiTari.getTipoUtenza().equals(UTENZA_DOMESTICA)) {
						DatiDichiarazioneCambioResidenza datiDichiarazioneCambioResidenza = getDatiDichiarazioneCambioResidenza(userPreferences, datiTari);
						Servizio servizioByCodiceServizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
						commonService.saveBozza(datiDichiarazioneCambioResidenza, userPreferences, servizioByCodiceServizio.getId());
						// redirect a portlet dichiarazione cambio residenza
						String dichiarazioneCambioResidenzaUrl = tributiCommonService.getDichiarazioneCambioResidenzaUrl(request,
								messageSource.getMessage("label.messaggio.dichiarazioneCambioResidenza", null, request.getLocale()));
						sessionStatus.setComplete();

						// Salvataggio in sessione HTTP del flag "isDichiarazioneTARIok"
						session.setAttribute(PortletConstants.DICHIARAZIONE_TARI_SHARED_ATTRIBUTE + "_" + currentProfiloUtenteCittadino.getCodiceFiscale(), true, PortletSession.APPLICATION_SCOPE);

						response.sendRedirect(dichiarazioneCambioResidenzaUrl);
						return;
					}
					
					
					response.setRenderParameter("action", "renderEsitoUpload");
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
					model.addAttribute("formError", messageSource.getMessage("exception.unreachable.message.args", new String[] { e.getMessage() }, request.getLocale()));
					response.setRenderParameter("action", "renderUploadForm");
				}
			}
		}
		else {
			String allegatoUno = datiTari.getAllegatoUno();
			String allegatoDue = datiTari.getAllegatoDue();
			String allegatoTre = datiTari.getAllegatoTre();
			String allegatoQuattro = datiTari.getAllegatoQuattro();
			ArrayList<String> descrizioni = new ArrayList<String>();

			String numAllegatiSelect = request.getParameter("numAllegatiSelect");
			int numAllegatiSelectInteger = Integer.parseInt(numAllegatiSelect);
			if (allegatoUno != null && allegatoUno.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(allegatoUno);
			}
			if (allegatoDue != null && allegatoDue.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(allegatoDue);
			}
			if (allegatoTre != null && allegatoTre.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(allegatoTre);
			}
			if (allegatoQuattro != null && allegatoQuattro.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(allegatoQuattro);
			}
			for (int i = 0; i < numAllegatiSelectInteger - descrizioni.size(); i++) {
				descrizioni.add(null);
			}

			List<MultipartFile> fileList = new ArrayList<MultipartFile>();
			for (int i = 0; i < numAllegatiSelectInteger; i++) {
				fileList.add(null);
			}
			uploadItem.setMultipartFiles(fileList);

			model.addAttribute("selectNumAllegati", numAllegatiSelect);
			model.addAttribute("descrAllegati", descrizioni);
			model.addAttribute("uploadItem", uploadItem);
			response.setRenderParameter("action", "renderUploadForm");
		}
	}

	/**
	 * Ritorna il form di dichiarazione cambio residenza compilato con i campi della dichiarazione
	 * TARI.
	 *
	 * @param datiTari
	 * @return
	 */
	private DatiDichiarazioneCambioResidenza getDatiDichiarazioneCambioResidenza(UserPreferences userPreferences, DatiDichiarazioneTari datiTari) {

		DatiDichiarazioneCambioResidenza datiDichiarazioneCambioResidenza = new DatiDichiarazioneCambioResidenza();

		// datiDichiarazioneCambioResidenza.setAltroMotivoDichiarazione(String);
		datiDichiarazioneCambioResidenza.setCellulare(null);
		datiDichiarazioneCambioResidenza.setCittadinanza(null);
		datiDichiarazioneCambioResidenza.setCodiceFiscale(datiTari.getCodiceFiscale());
		datiDichiarazioneCambioResidenza.setCognome(datiTari.getCognome());
		
		String comuneResidenza = datiTari.getComuneResidenza();
		Comune comuneByCodiceCatastale = comuneService.getComuneByCodiceISTAT(comuneResidenza);
		datiDichiarazioneCambioResidenza.setComuneResidenza(comuneByCodiceCatastale != null ? comuneByCodiceCatastale.getCodiceIstatAN() : null);
		datiDichiarazioneCambioResidenza.setComuneResidenzaHidden(datiTari.getComuneResidenzaHidden());
		Comune comuneNascitaByCodiceISTAT = comuneService.getComuneByCodiceISTAT(datiTari.getComuneNascita());
		datiDichiarazioneCambioResidenza.setComuneNascita(comuneNascitaByCodiceISTAT != null ? comuneNascitaByCodiceISTAT.getCodiceIstatAN() : null);

		datiDichiarazioneCambioResidenza.setComuneNascitaEstero(datiTari.getCodiceNascitaEstero());
		datiDichiarazioneCambioResidenza.setComuneNascitaEsteroHidden(datiTari.getComuneNascitaEstero());

		datiDichiarazioneCambioResidenza.setCondNonProfessionale(null);
		datiDichiarazioneCambioResidenza.setDataNascita(datiTari.getDataNascita());
		datiDichiarazioneCambioResidenza.setDataRilascio(null);
		datiDichiarazioneCambioResidenza.setDataRilascioPatente(null);
		datiDichiarazioneCambioResidenza.setEmail(datiTari.getEmail());
		List<Occupante> occupanti = datiTari.getOccupanti();
		if (occupanti != null) {
			List<Componente> familiari = new ArrayList<DatiDichiarazioneCambioResidenza.Componente>();
			for (Occupante occupante : occupanti) {
				if (occupante.getCodiceFiscale() != null && !occupante.getCodiceFiscale().isEmpty()) {
					Componente componente = new DatiDichiarazioneCambioResidenza().new Componente();
					componente.setCognome(occupante.getCognome());
					componente.setNome(occupante.getNome());
					componente.setDataNascitaString(occupante.getDataNascita());
					componente.setCodiceFiscale(occupante.getCodiceFiscale());
					familiari.add(componente);
				}
			}
			datiDichiarazioneCambioResidenza.setNumeroParenti(familiari.size());
			datiDichiarazioneCambioResidenza.setFamiliari(familiari);
		}
		// datiDichiarazioneCambioResidenza.setFlagIscritto(boolean);
		// TODO suppongo debba caricare solo l'abitazione principale e non le pertinenze...
		List<DatiImmobile> iscrizioneDom = datiTari.getIscrizioneDom();
		if (iscrizioneDom != null) {
			for (DatiImmobile datiImmobile : iscrizioneDom) {
				if (datiImmobile.getTipo().equals("A")) {
					datiDichiarazioneCambioResidenza.setSezione(datiImmobile.getSezione());
					datiDichiarazioneCambioResidenza.setFoglio(datiImmobile.getFoglio());
					datiDichiarazioneCambioResidenza.setParticella(datiImmobile.getParticella());
					datiDichiarazioneCambioResidenza.setSubalterno(datiImmobile.getSubalterno());
					datiDichiarazioneCambioResidenza.setNuovoPiano(datiImmobile.getPiano());
					datiDichiarazioneCambioResidenza.setNuovoInterno(datiImmobile.getInterno());
					datiDichiarazioneCambioResidenza.setNuovaScala(datiImmobile.getScala());

					// Indirizzo
					datiDichiarazioneCambioResidenza.setNuovaVia(datiImmobile.getIndirizzo());
					datiDichiarazioneCambioResidenza.setNuovaViaTextHidden(datiImmobile.getIndirizzoTextHidden());
					datiDichiarazioneCambioResidenza.setNuovoNum(datiImmobile.getCivico());
					datiDichiarazioneCambioResidenza.setNuovoNumTextHidden(datiImmobile.getCivicoTextHidden());
					datiDichiarazioneCambioResidenza.setNuovoEsp(datiImmobile.getEsponente());
					datiDichiarazioneCambioResidenza.setNuovaLocalitaHidden(datiImmobile.getLocalitaHidden());
					datiDichiarazioneCambioResidenza.setNuovoCodiceViaHidden(datiImmobile.getCodiceViaHidden());

					break;
				}
			}
		}
		// TODO ha senso settarlo?
		datiDichiarazioneCambioResidenza.setIdentificativoUtente(null);
		if (datiTari.getIdentificativoNucleoFamiliare() != null) {
			datiDichiarazioneCambioResidenza.setIdentificativoFamiglia(datiTari.getIdentificativoNucleoFamiliare().toString());
		}
		boolean checkIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
		if (checkIndirizzoResidenzaConStradario) {
			datiDichiarazioneCambioResidenza.setIndirizzoResidenza(datiTari.getIndirizzoResidenzaTextHidden());
			datiDichiarazioneCambioResidenza.setCivicoResidenza(datiTari.getCivicoResidenzaTextHidden());
		}
		else {
			datiDichiarazioneCambioResidenza.setIndirizzoResidenza(datiTari.getIndirizzoResidenza());
			datiDichiarazioneCambioResidenza.setCivicoResidenza(datiTari.getCivicoResidenza());
		}
		datiDichiarazioneCambioResidenza.setEsponenteResidenza(datiTari.getEsponenteResidenza());

		datiDichiarazioneCambioResidenza.setInternoResidenza(datiTari.getResInt());
		datiDichiarazioneCambioResidenza.setPianoResidenza(datiTari.getResP());
		datiDichiarazioneCambioResidenza.setScalaResidenza(datiTari.getResSc());

		datiDichiarazioneCambioResidenza.setStatoEstero(datiTari.getStatoEstero());
		datiDichiarazioneCambioResidenza.setStatoEsteroHidden(datiTari.getDenominazioneEstero());

		// Iscritto da non considerare
		// datiDichiarazioneCambioResidenza.setIscrittoCodiceFiscale(String);
		// datiDichiarazioneCambioResidenza.setIscrittoCognome(String);
		// datiDichiarazioneCambioResidenza.setIscrittoDataNascita(String);
		// datiDichiarazioneCambioResidenza.setIscrittoLuogoNascita(String);
		// datiDichiarazioneCambioResidenza.setIscrittoNome(String);
		// datiDichiarazioneCambioResidenza.setIscrittoParentela(boolean);
		// datiDichiarazioneCambioResidenza.setIscrittoTipoParentela(String);
		datiDichiarazioneCambioResidenza.setNome(datiTari.getNome());
		datiDichiarazioneCambioResidenza.setNumPatente(null);
		datiDichiarazioneCambioResidenza.setNumSogg(null);
		datiDichiarazioneCambioResidenza.setOrganoRilascioPatente(null);
		datiDichiarazioneCambioResidenza.setPec(datiTari.getPec());
		datiDichiarazioneCambioResidenza.setProfessione(null);
		datiDichiarazioneCambioResidenza.setProvinciaNascita(datiTari.getProvinciaNascita());
		datiDichiarazioneCambioResidenza.setProvinciaResidenza(datiTari.getProvResidenza());
		datiDichiarazioneCambioResidenza.setProvPatente(null);
		datiDichiarazioneCambioResidenza.setQuestura(null);
		datiDichiarazioneCambioResidenza.setRecCellulare(null);
		datiDichiarazioneCambioResidenza.setRecCivico(datiTari.getCivicoSpedizione());
		datiDichiarazioneCambioResidenza.setRecComune(datiTari.getComuneSpedizione());
		datiDichiarazioneCambioResidenza.setRecEmail(null);
		datiDichiarazioneCambioResidenza.setRecEsponente(datiTari.getEspSpedizione());
		datiDichiarazioneCambioResidenza.setRecFax(null);
		datiDichiarazioneCambioResidenza.setRecPec(null);
		datiDichiarazioneCambioResidenza.setRecProvincia(datiTari.getProvinciaSpedizione());
		datiDichiarazioneCambioResidenza.setRecTelefono(null);
		datiDichiarazioneCambioResidenza.setRecVia(datiTari.getIndirizzoSpedizione());
		datiDichiarazioneCambioResidenza.setSesso(datiTari.getSesso());
		datiDichiarazioneCambioResidenza.setStatoCivile(null);
		datiDichiarazioneCambioResidenza.setStatoEstero(datiTari.getStatoEstero());
		// TODO attenzione al telefono: quello in form è per la sede legale??
		datiDichiarazioneCambioResidenza.setTelefono(null);
		datiDichiarazioneCambioResidenza.setTipoDichiarazione("altroComune");
		datiDichiarazioneCambioResidenza.setTipoPatente(null);

		return datiDichiarazioneCambioResidenza;
	}

	/**
	 * @param uploadItem
	 * @throws IOException
	 */
	private List<Documento> getDocumentiFromUploadItem(UploadItem uploadItem) throws IOException {
		List<Documento> result = new ArrayList<Documento>();

		MultipartFile generatedFile = uploadItem.getGeneratedFile();
		if (generatedFile != null) {
			Documento documento = new Documento();
			documento.setNome(generatedFile.getOriginalFilename());
			documento.setContentType(generatedFile.getContentType());
			documento.setContenuto(generatedFile.getBytes());
			documento.setDescrizione(generatedFile.getOriginalFilename());
			documento.setPrincipale(true);
			documento.setTitolo(generatedFile.getOriginalFilename());
			result.add(documento);
		}

		List<MultipartFile> multipartFiles = uploadItem.getMultipartFiles();
		if (multipartFiles != null) {
			for (MultipartFile multipartFile : multipartFiles) {
				Documento documento = new Documento();
				documento.setNome(multipartFile.getOriginalFilename());
				documento.setContentType(multipartFile.getContentType());
				documento.setContenuto(multipartFile.getBytes());
				documento.setDescrizione(multipartFile.getOriginalFilename());
				documento.setPrincipale(false);
				documento.setTitolo(multipartFile.getOriginalFilename());
				result.add(documento);
			}
		}

		return result;

	}

	/**
	 * Setta la proprietà utile a capire se il servizio è destinato a funzionare mediante
	 * interoperabilità con il backoffice comunale.
	 *
	 * @param request
	 * @return
	 */
	@ModelAttribute("isInteroperabilitaEnable")
	public boolean isInteroperabilitaEnable(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		boolean interoperabilitaEnable = configurazioneService.isInteroperabilitaEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());
		return interoperabilitaEnable;
	}

	/**
	 * Metodo che prende i campi inseriti nella form e li mette in sessione
	 *
	 * @param datiDichiarazione
	 * @param result
	 * @param model
	 * @param response
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaDichiarazione")
	public void generaDichiarazione(@RequestParam(required = false) String bozza, @ModelAttribute("datiTari") DatiDichiarazioneTari datiTari, BindingResult result, Model model,
			@RequestParam(required = false) String tipologiaUtenza, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {

		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE TARI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		setInfoAggiuntiveForm(datiTari);

		String renderForm = "renderDichiarazioneTariDomesticaForm";
		if (UTENZA_NON_DOMESTICA.equalsIgnoreCase(datiTari.getTipoUtenza())) {
			renderForm = "renderDichiarazioneTariNonDomesticaForm";
		}

		// Salvataggio bozza
		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (bozza != null) {
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
			commonService.saveBozza(datiTari, userPreferences, servizio.getId());
			setInfoAggiuntiveForm(datiTari);
			response.setRenderParameter("action", renderForm);
			model.addAttribute("message", messageSource.getMessage("label.bozza.salvata", null, request.getLocale()));
			return;
		}

		boolean checkIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
		if (tipologiaUtenza != null && !tipologiaUtenza.isEmpty()) {
			if (checkIndirizzoResidenzaConStradario) {
				// campi vuoti indirizzo residenza
				datiTari.setIndirizzoResidenza(null);
				datiTari.setIndirizzoResidenzaTextHidden(null);
				datiTari.setCivicoResidenza(null);
				datiTari.setCivicoResidenzaTextHidden(null);
				datiTari.setEsponenteResidenza(null);
			}
		}

		boolean checkIndirizzoSpedizioneConStradario = checkIndirizzoSpedizioneConStradario(userPreferences, datiTari);
		if (tipologiaUtenza != null && !tipologiaUtenza.isEmpty()) {
			if (checkIndirizzoSpedizioneConStradario) {
				// campi vuoti indirizzo spedizione
				datiTari.setIndirizzoSpedizione(null);
				datiTari.setIndirizzoSpedizioneTextHidden(null);
				datiTari.setCivicoSpedizione(null);
				datiTari.setCivicoSpedizioneTextHidden(null);
				datiTari.setEsponenteResidenza(null);
			}
		}

		if ((checkIndirizzoResidenzaConStradario || checkIndirizzoSpedizioneConStradario) && tipologiaUtenza != null && !tipologiaUtenza.isEmpty()) {
			response.setRenderParameter("action", renderForm);
			return;
		}

		log.debug("compilaDichiarazione CF=" + datiTari.getCodiceFiscale());

		dichiarazioneValidator.validate(datiTari, result);

		// TODO Validazione per CDB: max 1 Abitazione principale e 2 Pertinenze: eliminare non
		// appena verrà aggiornato GITRI
		boolean servizioEnableForCAF = configurazioneService.isServizioEnableForCAF(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());
		if (servizioEnableForCAF && userPreferences.getIdComuneIsa() == 1) {
			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
				String field = "";
				List<DatiImmobile> iscrizioni = datiTari.getIscrizioneDom();
				if (datiTari.getTipoUtenza().equals(UTENZA_DOMESTICA)) {
					iscrizioni = datiTari.getIscrizioneDom();
					field = "iscrizioneDom[0].tipo";
				}
				if (!checkValidazioneCustomBari(iscrizioni)) {
					FieldError error = new FieldError("datiTari", field, messageSource.getMessage("error.checkValidazioneCustomBari.failed", null, request.getLocale()));
					result.addError(error);
				}
			}
		}

		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				log.error("Error: " + error.toString());
			}
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
			datiTari.setDichiarazioneCompletata(false);
			response.setRenderParameter("action", renderForm);
		}
		else {
			// setto la data di compilazione della dichiarazione
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("comune", userPreferences.getNomeComune());
			datiTari.setDichiarazioneCompletata(true);
			model.addAttribute("datiTari", datiTari);
			model.addAttribute("param", param);
			model.addAttribute("download", "si");
			auditManager.build();
			response.setRenderParameter("action", renderForm);
		}

	}

	/**
	 * @param iscrizioneDom
	 * @return
	 */
	private boolean checkValidazioneCustomBari(List<DatiImmobile> iscrizioneDom) {

		int numeroUtenzeAbitazionePrincipale = 0;
		int numeroUtenzePertinenza = 0;
		if (iscrizioneDom != null) {

			for (DatiImmobile datiImmobile : iscrizioneDom) {
				if ("A".equals(datiImmobile.getTipo())) {
					numeroUtenzeAbitazionePrincipale++;
				}
				if ("P".equals(datiImmobile.getTipo())) {
					numeroUtenzePertinenza++;
				}
			}
		}

		boolean isAbitazioneOk = numeroUtenzeAbitazionePrincipale <= 1;
		boolean isPertinenzeOk = numeroUtenzePertinenza <= 2;

		return isAbitazioneOk && isPertinenzeOk;
	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param datiTari
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneTariReport")
	public void serveCertificato(@ModelAttribute("datiTari") DatiDichiarazioneTari datiTari, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request, PortletRequest portletRequest) throws Exception {
		log.debug("dichiarazioneTariReport ");
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE TARI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		setInfoAggiuntiveForm(datiTari);

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiDichiarazioneTari> beans = new ArrayList<DatiDichiarazioneTari>();

		setInfoAggiuntiveForm(datiTari);

		UserPreferences userPreferences = helper.getUserPreferences(request);
		boolean checkIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiTari);
		if (!checkIndirizzoResidenzaConStradario) {
			datiTari.setIndirizzoResidenzaTextHidden(datiTari.getIndirizzoResidenza());
			datiTari.setCivicoResidenzaTextHidden(datiTari.getCivicoResidenza());
		}

		boolean checkIndirizzoSpedizioneConStradario = checkIndirizzoSpedizioneConStradario(userPreferences, datiTari);
		if (!checkIndirizzoSpedizioneConStradario) {
			datiTari.setIndirizzoSpedizioneTextHidden(datiTari.getIndirizzoSpedizione());
			datiTari.setCivicoSpedizioneTextHidden(datiTari.getCivicoSpedizione());
		}

		beans.add(datiTari);

		// Converto l'array di "allegati" e "motivi" in una lista di String, più facile da gestire
		// in ireport
		if (datiTari.getTipoUtenza().equals(UTENZA_DOMESTICA)) {
			if (datiTari.getTipologiaRiduzioneDom() != null) {
				param.put("tipologiaRiduzioneDom", getCheckArray(datiTari.getTipologiaRiduzioneDom()));
			}
		}

		if (datiTari.getTipoUtenza().equals(UTENZA_NON_DOMESTICA)) {
			if (datiTari.getTipologiaRiduzioneNonDom() != null) {
				param.put("tipologiaRiduzioneNonDom", getCheckArray(datiTari.getTipologiaRiduzioneNonDom()));
			}
		}

		byte[] reportBytes = null;

		String report_path = "";

		// Dataset per tables nei template JR
		List<Occupante> occupanti = null;

		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniReport = getRiduzioniReport(datiTari);
		datiTari.setRiduzioniDomestiche(riduzioniReport);

		JRBeanCollectionDataSource utenzeIscrizioneDataset = null;
		JRBeanCollectionDataSource utenzeVariazioneDataset = null;
		JRBeanCollectionDataSource utenzeCessazioneDataset = null;
		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		if (datiTari.getTipoUtenza().equals(UTENZA_DOMESTICA)) {
			report_path = REPORT_DOMESTICA_PATH;
			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
				subreportJrxmlMap.put("subreport", REPORT_ISCRIZIONE_SUB_REPORT_PATH);
				utenzeIscrizioneDataset = getJRBeanCollectionDataSource(datiTari.getIscrizioneDom());
				utenzeCessazioneDataset = getJRBeanCollectionDataSource(datiTari.getCessazioneDom(), datiTari.getIdentificativoUtenzeSelezionateCessazione());
				param.put("utenzeIscrizioneDataSource", utenzeIscrizioneDataset);
				param.put("utenzeCessazioneDataSource", utenzeCessazioneDataset);
				occupanti = datiTari.getOccupanti();
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
				subreportJrxmlMap.put("subreport", REPORT_VARIAZIONE_SUB_REPORT_PATH);
				utenzeVariazioneDataset = getJRBeanCollectionDataSource(datiTari.getVariazioneDom(), datiTari.getIdentificativoUtenzeSelezionateVariazione());
				for (Iterator iterator = utenzeVariazioneDataset.getData().iterator(); iterator.hasNext();) {
					DatiImmobile immobile = (DatiImmobile) iterator.next();
					if (!immobile.getVarDiSuperficieA().isEmpty()) {
						datiTari.setDiSuperficie(true);
						param.put("utenzeVariazioneDataSource", utenzeVariazioneDataset);
					}
					else {
						datiTari.setDiSuperficie(false);
					}
				}

				occupanti = datiTari.getVariazioneOccupanti();
				for (Occupante occupante : occupanti) {
					if (!occupante.getTipoModifica().isEmpty()) {
						occupante.setTipoModificaDescrizione(getTipoModificaVariazioneOccupantiMap().get(occupante.getTipoModifica()));
					}
				}
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_CESSAZIONE)) {
				utenzeCessazioneDataset = getJRBeanCollectionDataSource(datiTari.getCessazioneDom(), datiTari.getIdentificativoUtenzeSelezionateCessazione());
				param.put("utenzeCessazioneDataSource", utenzeCessazioneDataset);
			}

			if (occupanti != null) {
				// Eliminazione occupanti in eccesso
				List<Occupante> occupantiReali = new ArrayList<Occupante>();
				for (Occupante occupante : occupanti) {
					if (occupante.getCodiceFiscale() != null && !occupante.getCodiceFiscale().isEmpty()) {
						occupantiReali.add(occupante);
					}
				}
				JRBeanCollectionDataSource occupantiDataset = new JRBeanCollectionDataSource(occupantiReali);
				param.put("occupantiDataSource", occupantiDataset);
			}

		}
		else {
			report_path = REPORT_NON_DOMESTICA_PATH;
			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
				subreportJrxmlMap.put("subreport", REPORT_ISCRIZIONE_SUB_REPORT_PATH);
				utenzeIscrizioneDataset = getJRBeanCollectionDataSource(datiTari.getIscrizioneNonDom());
				utenzeCessazioneDataset = getJRBeanCollectionDataSource(datiTari.getCessazioneNonDom(), datiTari.getIdentificativoUtenzeSelezionateCessazione());
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
				subreportJrxmlMap.put("subreport", REPORT_VARIAZIONE_SUB_REPORT_PATH);
				utenzeVariazioneDataset = getJRBeanCollectionDataSource(datiTari.getVariazioneNonDom(), datiTari.getIdentificativoUtenzeSelezionateVariazione());
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_CESSAZIONE)) {
				utenzeCessazioneDataset = getJRBeanCollectionDataSource(datiTari.getCessazioneNonDom(), datiTari.getIdentificativoUtenzeSelezionateCessazione());
			}
			param.put("utenzeIscrizioneTrasferimentoDataSource", utenzeIscrizioneDataset);
			param.put("utenzeVariazioneDataSource", utenzeVariazioneDataset);
			param.put("utenzeCessazioneTrasferimentoDataSource", utenzeCessazioneDataset);
		}

		reportBytes = reportService.jrxmlToPdf(param, beans, userPreferences.getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, report_path, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(userPreferences);
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		String reportFileName = String.format(REPORT_PREFIX_NAME);
		reportFileName += "_" + userPreferences.getCodiceFiscaleServizio() + REPORT_SUFFIX_NAME;
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * @param datiTari
	 */
	private void setInfoAggiuntiveForm(DatiDichiarazioneTari datiTari) {

		if (datiTari == null) {
			return;
		}
		if (datiTari.getComuneNascita() != null && !datiTari.getComuneNascita().isEmpty()) {
			Comune comuneNascita = comuneService.getComuneByCodiceISTAT(datiTari.getComuneNascita());
			datiTari.setComuneNascitaHidden(comuneNascita != null ? comuneNascita.getDenominazione() : null);
		}
		if (datiTari.getComuneResidenza() != null && !datiTari.getComuneResidenza().isEmpty()) {
			Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(datiTari.getComuneResidenza());
			datiTari.setComuneResidenzaHidden(comuneResidenza != null ? comuneResidenza.getDenominazione() : null);
		}
		if (datiTari.getComuneSedeLegale() != null && !datiTari.getComuneSedeLegale().isEmpty()) {
			Comune comuneSedeLegale = comuneService.getComuneByCodiceISTAT(datiTari.getComuneSedeLegale());
			datiTari.setComuneSedeLegaleHidden(comuneSedeLegale != null ? comuneSedeLegale.getDenominazione() : null);
		}
		if (datiTari.getComuneSpedizione() != null && !datiTari.getComuneSpedizione().isEmpty()) {
			Comune comuneSpedizione = comuneService.getComuneByCodiceISTAT(datiTari.getComuneSpedizione());
			datiTari.setComuneSpedizioneHidden(comuneSpedizione != null ? comuneSpedizione.getDenominazione() : null);
		}

		if (datiTari.getStatoEstero() != null && !"".equalsIgnoreCase(datiTari.getStatoEstero())) {
			StatoEstero statoEstero = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(datiTari.getStatoEstero()));
			datiTari.setDenominazioneEstero(statoEstero != null ? statoEstero.getDenominazione() : null);
		}
		if (datiTari.getCodiceNascitaEstero() != null && !"".equalsIgnoreCase(datiTari.getCodiceNascitaEstero())) {
			ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(datiTari.getCodiceNascitaEstero()));
			datiTari.setComuneNascitaEstero(comuneEstero != null ? comuneEstero.getDenominazione() : null);
		}
		if (datiTari.getComuneEmigrazioneDom() != null && !datiTari.getComuneEmigrazioneDom().isEmpty()) {
			Comune comuneEmigrazioneDom = comuneService.getComuneByCodiceISTAT(datiTari.getComuneEmigrazioneDom());
			datiTari.setComuneEmigrazioneDomHidden(comuneEmigrazioneDom != null ? comuneEmigrazioneDom.getDenominazione() : null);
		}
	}

	/**
	 * @param datiTari
	 * @return
	 */
	private List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> getRiduzioniReport(DatiDichiarazioneTari datiTari) {

		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniDomesticheReport = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniDomestiche = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniDomesticheTutte = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		if (datiTari.getTipoUtenza().equals(DichiarazioneTariPortletController.UTENZA_DOMESTICA)) {
			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
				riduzioniDomesticheTutte = getRiduzioniDomesticheIscrizioneList();
				riduzioniDomestiche = datiTari.getRiduzioniDomesticheIscrizione();
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
				riduzioniDomestiche = datiTari.getRiduzioniDomesticheVariazione();
				riduzioniDomesticheTutte = getRiduzioniDomesticheVarizioneList();
			}
		}
		else {
			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
				riduzioniDomesticheTutte = getRiduzioniNonDomesticheIscrizioneList();
				riduzioniDomestiche = datiTari.getRiduzioniNonDomesticheIscrizione();
			}
			else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
				riduzioniDomestiche = datiTari.getRiduzioniNonDomesticheVariazione();
				riduzioniDomesticheTutte = getRiduzioniNonDomesticheVarizioneList();
			}
		}

		if (riduzioniDomestiche != null) {
			for (it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzioneDomestica : riduzioniDomestiche) {
				List<Valore> valoriRiduzioneDomestica = riduzioneDomestica.getValori();

				for (int i = 0; i < valoriRiduzioneDomestica.size(); i++) {
					if (valoriRiduzioneDomestica.get(i).getChiave() == null) {
						valoriRiduzioneDomestica.remove(i);
						i--;
					}
				}
				if (valoriRiduzioneDomestica != null) {
					for (it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzioneTutte : riduzioniDomesticheTutte) {
						if (riduzioneTutte.getCodiceArticolo().equals(riduzioneDomestica.getCodiceArticolo())) {
							List<Valore> valoriRiduzioniTutte = riduzioneTutte.getValori();
							for (Valore valore : valoriRiduzioneDomestica) {
								for (Valore valoreTutte : valoriRiduzioniTutte) {
									if (valore.getChiave() != null && valore.getChiave().equals(valoreTutte.getChiave())) {
										valore.setValore(valoreTutte.getValore());
										riduzioneDomestica.setDescrizioneArticolo(riduzioneTutte.getDescrizioneArticolo());
										riduzioniDomesticheReport.add(riduzioneDomestica);
									}
								}
							}
						}
					}
				}
			}
		}
		return riduzioniDomesticheReport;
	}

	/**
	 * Ritorna il {@link JRBeanCollectionDataSource} dalla lista di utenze passata in input.
	 *
	 * @param utenze
	 * @return
	 */
	private JRBeanCollectionDataSource getJRBeanCollectionDataSource(List<DatiImmobile> utenze) {

		List<DatiImmobile> utenzeReali = new ArrayList<DatiImmobile>();
		if (utenze != null) {
			for (DatiImmobile datiImmobile : utenze) {
				if (datiImmobile.getIndirizzo() != null && !datiImmobile.getIndirizzo().isEmpty()) {
					utenzeReali.add(datiImmobile);
				}
			}
		}

		return new JRBeanCollectionDataSource(utenzeReali);
	}

	/**
	 * Ritorna il {@link JRBeanCollectionDataSource} dalla lista di utenze passata in input.
	 *
	 * @param utenze
	 * @param identificativoUtenza
	 * @return
	 */
	private JRBeanCollectionDataSource getJRBeanCollectionDataSource(List<DatiImmobile> utenze, String identificativoUtenza) {

		JRBeanCollectionDataSource result = null;
		if (utenze != null) {
			for (DatiImmobile datiImmobile : utenze) {
				if (datiImmobile.getIdentificativoUtenza() != null && datiImmobile.getIdentificativoUtenza().equals(identificativoUtenza)) {
					result = new JRBeanCollectionDataSource(Arrays.asList(datiImmobile));
					break;
				}
				if (datiImmobile.getIdentificativoUtenza() == null && identificativoUtenza.isEmpty() && !datiImmobile.getIndirizzo().isEmpty()) {
					result = new JRBeanCollectionDataSource(Arrays.asList(datiImmobile));
					break;
				}
			}
		}

		return result;
	}

	@ModelAttribute("listaStatiEsteri")
	public List<StatoEstero> getStatiEsteri() {
		// escludi dalla lista l'italia - codice stato 100
		return statoEsteroService.getAllStatiAndEscludi(100);
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

	@ModelAttribute("categorieCatastaliMap")
	public Map<String, String> getCategorieCatastali() {
		Map<String, String> categorieCatastaliMap = new TreeMap<String, String>();

		categorieCatastaliMap.put("A01", "A/1");
		categorieCatastaliMap.put("A02", "A/2");
		categorieCatastaliMap.put("A03", "A/3");
		categorieCatastaliMap.put("A04", "A/4");
		categorieCatastaliMap.put("A05", "A/5");
		categorieCatastaliMap.put("A06", "A/6");
		categorieCatastaliMap.put("A07", "A/7");
		categorieCatastaliMap.put("A08", "A/8");
		categorieCatastaliMap.put("A09", "A/9");
		categorieCatastaliMap.put("A10", "A/10");
		categorieCatastaliMap.put("C01", "C/1");
		categorieCatastaliMap.put("C02", "C/2");
		categorieCatastaliMap.put("C03", "C/3");
		categorieCatastaliMap.put("C04", "C/4");
		categorieCatastaliMap.put("C05", "C/5");
		categorieCatastaliMap.put("C06", "C/6");
		categorieCatastaliMap.put("C07", "C/7");

		return categorieCatastaliMap;
	}

	@ModelAttribute("tipologieImmobileMap")
	public Map<String, String> getTipologieImmobile() {
		Map<String, String> tipologiaImmobileMap = new TreeMap<String, String>();

		tipologiaImmobileMap.put("A", "Abitazione");
		tipologiaImmobileMap.put("P", "Pertinenza");

		return tipologiaImmobileMap;
	}

	@ModelAttribute("tipoModificaVariazioneOccupantiMap")
	public Map<String, String> getTipoModificaVariazioneOccupantiMap() {
		Map<String, String> tipoModificaVariazioneOccupanti = new TreeMap<String, String>();

		tipoModificaVariazioneOccupanti.put("A", "Aggiunta");
		tipoModificaVariazioneOccupanti.put("R", "Rimozione");
		tipoModificaVariazioneOccupanti.put("M", "Modifica");

		return tipoModificaVariazioneOccupanti;
	}

	@ModelAttribute("riduzioniDomesticheIscrizione")
	public List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> getRiduzioniDomesticheIscrizioneList() {
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> result = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		riduzione.setCodiceArticolo("art23");
		riduzione.setDescrizioneArticolo("riduzione del 20% ai sensi dell'art.23 del regolamento Tari.");

		List<Valore> valoriRiduzione = new ArrayList<Valore>();
		valoriRiduzione.add(new Valore("A", "Abitazioni con un unico occupante"));
		valoriRiduzione.add(new Valore("B", "Abitazioni tenute a disposizione per uso non superiore a 183 giorni nell'anno solare"));
		valoriRiduzione.add(new Valore("C", "Abitazioni occupate da soggetti che risiedano o abbiano la dimora all'estero per piu' di 6 mesi all'anno"));
		valoriRiduzione.add(new Valore("D", "Fabbricati rurali ad uso abitativo"));
		riduzione.setValori(valoriRiduzione);

		result.add(riduzione);

		return result;
	}

	@ModelAttribute("riduzioniNonDomesticheIscrizione")
	public List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> getRiduzioniNonDomesticheIscrizioneList() {
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> result = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione = new ArrayList<Valore>();
		riduzione.setCodiceArticolo("art10");
		riduzione.setDescrizioneArticolo("riduzione ai sensi art. 10 Regolamento Tari per Superfici soggette a produzione rifiuti speciali.");
		valoriRiduzione.add(new Valore("1",
				"Ambulatori dentistici, laboratori radiologici, laboratori odontotecnici, laboratori di analisi (non facenti parte delle strutture sanitarie che operano in forma organizzata e continuativa nell'ambito e per finalità di cui alla legge 833/88 e successive variazioni)"));
		valoriRiduzione.add(new Valore("2",
				"Ambulatori medici (non facenti parte delle strutture sanitarie che operano in forma organizzata e continuativa nell'ambito e per finalit&amp;agrave; di cui alla legge 833/88 e successive variazioni)"));
		valoriRiduzione.add(new Valore("3", "Lavanderie a secco e tintorie non industriali"));
		valoriRiduzione.add(new Valore("4", "Distributori di carburanti"));
		valoriRiduzione.add(new Valore("5", "Autofficine per riparazione veicoli"));
		valoriRiduzione.add(new Valore("6", "Gommisti"));
		valoriRiduzione.add(new Valore("7", "Elettrauto"));
		valoriRiduzione.add(new Valore("8", "Caseifici e aziende produttrici di vino e bevande"));
		valoriRiduzione.add(new Valore("9", "Rosticcerie e pasticcerie"));
		valoriRiduzione.add(new Valore("10", "Falegnamerie"));
		valoriRiduzione.add(new Valore("11", "Autocarrozzerie e verniciatori in genere, galvanotecnici, fonderie, produzione di ceramiche, smalterie"));
		valoriRiduzione.add(new Valore("12", "Officine di carpenteria metallica"));
		valoriRiduzione.add(new Valore("13", "Tipografie, stamperie, incisioni e vetrerie"));
		valoriRiduzione.add(new Valore("14", "Laboratori fotografici ed eliografie"));
		valoriRiduzione.add(new Valore("15", "Allestimenti pubblicitari, insegne luminose"));
		valoriRiduzione.add(new Valore("16", "Produzione di plastiche e vetroresine"));
		valoriRiduzione.add(new Valore("17", "Macellerie"));
		valoriRiduzione.add(new Valore("18", "Pescherie"));
		valoriRiduzione.add(new Valore("19", "Parrucchieri"));
		valoriRiduzione.add(new Valore("20", "Imprese di trasporto"));
		riduzione.setValori(valoriRiduzione);
		result.add(riduzione);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione2 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione2 = new ArrayList<Valore>();
		riduzione2.setCodiceArticolo("art24");
		riduzione2.setDescrizioneArticolo("riduzione ai sensi art. 24 Regolamento Tari per utenze non domestiche non stabilmente attive.");
		valoriRiduzione2.add(new Valore("10",
				"Locali, diversi dalle abitazioni, ed aree scoperte adibiti ad uso stagionale o ad uso non continuativo, ma ricorrente, purché non superiore a 183 giorni nell’anno solare (30%)"));
		riduzione2.setValori(valoriRiduzione2);
		result.add(riduzione2);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione3 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione3 = new ArrayList<Valore>();
		riduzione3.setCodiceArticolo("art26");
		riduzione3.setDescrizioneArticolo("riduzione ai sensi art. 26 Regolamento Tari per inferiori livelli di prestazione del servizio.");
		valoriRiduzione3.add(new Valore("40",
				"Utenze poste a una distanza superiore a 300 mt. e sino a 500 mt. dal più vicino punto di conferimento, misurata dall'accesso dell'utenza alla strada pubblica (60%)"));
		valoriRiduzione3.add(new Valore("41", "Utenze poste a una distanza superiore a 500 mt. dal più vicino punto di conferimento, misurata dall'accesso dell'utenza alla strada pubblica (70%)"));
		valoriRiduzione3.add(new Valore("44", "Periodi di mancato svolgimento del servizio di gestione dei rifiuti (80%)"));
		riduzione3.setValori(valoriRiduzione3);
		result.add(riduzione3);

		return result;
	}

	@ModelAttribute("riduzioniDomesticheVariazione")
	public List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> getRiduzioniDomesticheVarizioneList() {
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> result = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione = new ArrayList<Valore>();
		riduzione.setCodiceArticolo("art23");
		riduzione.setDescrizioneArticolo("riduzione ai sensi art. 23 Regolamento Tari.");
		valoriRiduzione.add(new Valore("A", "Abitazioni con un unico occupante"));
		valoriRiduzione.add(new Valore("B", "Abitazioni tenute a disposizione per uso non superiore a 183 giorni nell'anno solare"));
		valoriRiduzione.add(new Valore("C", "Abitazioni occupate da soggetti che risiedano o abbiano la dimora all'estero per piu' di 6 mesi all'anno"));
		valoriRiduzione.add(new Valore("D", "Fabbricati rurali ad uso abitativo"));
		riduzione.setValori(valoriRiduzione);
		result.add(riduzione);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione2 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione2 = new ArrayList<Valore>();
		riduzione2.setCodiceArticolo("art27");
		riduzione2.setDescrizioneArticolo("riduzione ai sensi art. 27 Regolamento Tari.");
		valoriRiduzione2.add(new Valore("1", "Nucleo familiare il cui valore ISEE complessivo non superi il limite di Euro 4.000 (100%)"));
		valoriRiduzione2.add(new Valore("2",
				"Nucleo familiare composto esclusivamente da ultraottantenni ovvero da un ultraottantenne con coniuge fiscalmente a carico,il cui valore ISEE complessivo riferito all’anno precedente non superi il limite di Euro 10.000 (100%)"));
		valoriRiduzione2.add(new Valore("3", "Nucleo familiare con un numero di componenti maggiore o uguale a 6, il cui valore ISEE complessivo non superi il limite di Euro 25.000 (100%)"));
		valoriRiduzione2.add(new Valore("4", "Nucleo familiare il cui valore ISEE complessivo riferito all’anno precedente non superi il limite di Euro 7.000 (20%)"));
		riduzione2.setValori(valoriRiduzione2);
		result.add(riduzione2);

		return result;
	}

	@ModelAttribute("riduzioniNonDomesticheVariazione")
	public List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> getRiduzioniNonDomesticheVarizioneList() {
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> result = new ArrayList<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione>();

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione = new ArrayList<Valore>();
		riduzione.setCodiceArticolo("art10");
		riduzione.setDescrizioneArticolo("riduzione ai sensi art. 10 Regolamento Tari per Superfici soggette a produzione rifiuti speciali per le utenze non domestiche.");
		valoriRiduzione.add(new Valore("1",
				"Ambulatori dentistici, laboratori radiologici, laboratori odontotecnici, laboratori di analisi (non facenti parte delle strutture sanitarie che operano in forma organizzata e continuativa nell'ambito e per finalità di cui alla legge 833/88 e successive variazioni)"));
		valoriRiduzione.add(new Valore("2",
				"Ambulatori medici (non facenti parte delle strutture sanitarie che operano in forma organizzata e continuativa nell'ambito e per finalità di cui alla legge 833/88 e successive variazioni)"));
		valoriRiduzione.add(new Valore("3", "Lavanderie a secco e tintorie non industriali"));
		valoriRiduzione.add(new Valore("4", "Distributori di carburanti"));
		valoriRiduzione.add(new Valore("5", "Autofficine per riparazione veicoli"));
		valoriRiduzione.add(new Valore("6", "Gommisti"));
		valoriRiduzione.add(new Valore("7", "Elettrauto"));
		valoriRiduzione.add(new Valore("8", "Caseifici e aziende produttrici di vino e bevande"));
		valoriRiduzione.add(new Valore("9", "Rosticcerie e pasticcerie"));
		valoriRiduzione.add(new Valore("10", "Falegnamerie"));
		valoriRiduzione.add(new Valore("11", "Autocarrozzerie e verniciatori in genere, galvanotecnici, fonderie, produzione di ceramiche, smalterie"));
		valoriRiduzione.add(new Valore("12", "Officine di carpenteria metallica"));
		valoriRiduzione.add(new Valore("13", "Tipografie, stamperie, incisioni e vetrerie"));
		valoriRiduzione.add(new Valore("14", "Laboratori fotografici ed eliografie"));
		valoriRiduzione.add(new Valore("15", "Allestimenti pubblicitari, insegne luminose"));
		valoriRiduzione.add(new Valore("16", "Produzione di plastiche e vetroresine"));
		valoriRiduzione.add(new Valore("17", "Macellerie"));
		valoriRiduzione.add(new Valore("18", "Pescherie"));
		valoriRiduzione.add(new Valore("19", "Parrucchieri"));
		valoriRiduzione.add(new Valore("20", "Imprese di trasporto"));
		riduzione.setValori(valoriRiduzione);
		result.add(riduzione);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione2 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione2 = new ArrayList<Valore>();
		riduzione2.setCodiceArticolo("art24");
		riduzione2.setDescrizioneArticolo("riduzione ai sensi art. 24 Regolamento Tari per le utenze non domestiche non stabilmente attive.");
		valoriRiduzione2.add(new Valore("10",
				"Locali, diversi dalle abitazioni, ed aree scoperte adibiti ad uso stagionale o ad uso non continuativo, ma ricorrente, purché non superiore a 183 giorni nell’anno solare (30%)"));
		riduzione2.setValori(valoriRiduzione2);
		result.add(riduzione2);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione3 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione3 = new ArrayList<Valore>();
		riduzione3.setCodiceArticolo("art25");
		riduzione3.setDescrizioneArticolo("riduzione di tariffa ai sensi art. 25 Regolamento Tari.");
		valoriRiduzione3.add(new Valore("2", "Produzione di rifiuti assimilati avviati al recupero maggiore del 30% e fino al 40 % della produzione totale annua del rifiuto (20%)"));
		valoriRiduzione3.add(new Valore("46", "Produzione di rifiuti assimilati avviati al recupero maggiore del 40% e fino al 60 % della produzione totale annua del rifiuto (30%)"));
		valoriRiduzione3.add(new Valore("6", "Produzione di rifiuti assimilati avviati al recupero maggiore del 60% della produzione totale annua del rifiuto (40%)"));
		riduzione3.setValori(valoriRiduzione3);
		result.add(riduzione3);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione4 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione4 = new ArrayList<Valore>();
		riduzione4.setCodiceArticolo("art26");
		riduzione4.setDescrizioneArticolo("riduzione di tariffa ai sensi art. 26 Regolamento Tari per inferiori livelli di prestazione del servizio.");
		valoriRiduzione4.add(new Valore("40",
				"Utenze poste a una distanza superiore a 300 mt. e sino a 500 mt. dal piu' vicino punto di conferimento, misurata dall'accesso dell'utenza alla strada pubblica (60%)"));
		valoriRiduzione4.add(new Valore("41", "Utenze poste a una distanza superiore a 500 mt. dal piu' vicino punto di conferimento, misurata dall'accesso dell'utenza alla strada pubblica (70%)"));
		valoriRiduzione4.add(new Valore("44", "Periodi di mancato svolgimento del servizio di gestione dei rifiuti (80%)"));
		riduzione4.setValori(valoriRiduzione4);
		result.add(riduzione4);

		it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzione5 = new it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione();
		List<Valore> valoriRiduzione5 = new ArrayList<Valore>();
		riduzione5.setCodiceArticolo("art27");
		riduzione5.setDescrizioneArticolo("riduzione di tariffa ai sensi art. 27 Regolamento Tari.");
		valoriRiduzione5.add(new Valore("5", "Locali e aree utilizzati da Enti e Associazioni riconosciuti per legge come enti morali aventi finalità di assistenza e beneficenza (100%)"));
		valoriRiduzione5.add(new Valore("20",
				"Aree scoperte destinate alla commercializzazione di autoveicoli ovvero ad altra attività avente analoga produttività di rifiuti, con superficie almeno pari a 1.000 metri quadrati (30%)"));
		valoriRiduzione5.add(new Valore("45", "Locali individuati dal Comune ed affidati a Onlus, per la realizzazione dei servizi sociali (100%)"));
		riduzione5.setValori(valoriRiduzione5);
		result.add(riduzione5);

		return result;
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

	@RenderMapping(params = "action=renderDichiarazioneTariDomesticaForm")
	public String rederDichiarazioneTariUtenzaDomesticaForm(RenderRequest request, Model model) throws Exception {
		return toLocalViewPath("dichiarazioneTariDomestica");
	}

	@RenderMapping(params = "action=renderDichiarazioneTariNonDomesticaForm")
	public String rederDichiarazioneTariUtenzaNonDomesticaForm(RenderRequest request, Model model) throws Exception {
		return toLocalViewPath("dichiarazioneTariNonDomestica");
	}

	@RenderMapping(params = "action=renderSaltaDichiarazioneForm")
	public String renderSaltaDichiarazioneForm(Model model) throws Exception {
		return toLocalViewPath("saltaDichiarazioneForm");
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
	 * "/dichiarazionetari/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param richiesta
	 * @return
	 */
	private MultipartFile generateMultipartFileForInterop(DichiarazioneTassaRifiutiInputRichiesta richiesta) {

		MultipartFile multipartFile = null;

		try {
			String xml = xmlHelper.marshal(richiesta);
			multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		}
		catch (Exception e) {
			log.error("generateMultipartFileForInterop() :: " + e.getMessage(), e);
		}

		return multipartFile;
	}

	@ModelAttribute("visuraPosizioniTributarieActive")
	public Boolean getVisuraPosizioniTributarieActive(PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);

		boolean result = false;

		ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
		if (comuneByPk != null) {
			result = comuneByPk.getVisuraPosizioniTributarieActive();
		}

		return result;
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

	private List<DatiImmobile> generateList(UserPreferences userPreferences, List<DatiTassaRifiuti.Posizioni> posizioni, int limit, boolean nuovoImmobile) {

		ArrayList<DatiImmobile> results = new ArrayList<DatiImmobile>();

		if (nuovoImmobile) {
			limit++;
		}

		for (int i = 0; i < limit; i++) {
			DatiImmobile immobile = new DatiImmobile();
			if (posizioni.size() > 0 && i < posizioni.size()) {
				Posizioni posizione = posizioni.get(i);

				it.osapulie.tributi.web.ws.output.types.Indirizzo indirizzoUtenza = posizione.getIndirizzoUtenza();
				if (indirizzoUtenza != null) {
					it.osapulie.tributi.web.ws.output.types.Codifica via = indirizzoUtenza.getVia();
					Civico civico = indirizzoUtenza.getCivico();
					String indirizzoText = via.getCodice() != null ? via.getCodice() : via.getDescrizione();
					String civicoText = civico.getCodice() != null ? civico.getCodice() : String.valueOf(civico.getNumero());
					immobile.setIndirizzo(indirizzoText);
					immobile.setIndirizzoTextHidden(via.getDescrizione());
					immobile.setCivico(civicoText);
					immobile.setEsponente(civico.getEsponente());
					immobile.setCivicoTextHidden(String.valueOf(civico.getNumero()));
					immobile.setLocalita(indirizzoUtenza.getLocalita());
					immobile.setPiano(indirizzoUtenza.getPiano());
					immobile.setInterno(indirizzoUtenza.getInterno());
					immobile.setScala(indirizzoUtenza.getScala());
					immobile.setSuffisso(indirizzoUtenza.getSuffisso());
					immobile.setCap(indirizzoUtenza.getCap());
				}

				String superficie = posizione.getSuperficie() != null ? posizione.getSuperficie().toPlainString() : null;
				immobile.setSuperficieTotale(superficie);
				immobile.setVarDiSuperficieDa(superficie);
				if (posizione.getRiduzione() != null) {
					immobile.setUtenzaConRiduzione("S");
				}
				else {
					immobile.setUtenzaConRiduzione("N");
				}

				immobile.setTipologiaSuperficie(posizione.getTipoSuperficie());
				immobile.setFoglio(String.valueOf(posizione.getFoglio()));
				immobile.setSezione(posizione.getSezione());
				immobile.setParticella(String.valueOf(posizione.getParticella()));
				immobile.setSubalterno(posizione.getSubalterno() != null ? String.valueOf(posizione.getSubalterno()) : null);

				immobile.setTipo(posizione.getTipo());
				it.osapulie.tributi.web.ws.output.types.Codifica categoriaImmobile = posizione.getCategoriaImmobile();
				if (categoriaImmobile != null) {
					String categoria = categoriaImmobile.getCodice() != null ? categoriaImmobile.getCodice() : categoriaImmobile.getDescrizione();
					immobile.setCategoria(categoria);
				}

				immobile.setIdentificativoUtenza(posizione.getIdentificativoUtenza());
			}
			results.add(immobile);
		}

		return results;
	}

	/**
	 * Setta i campi checkbox di selezione utenza già selezionati con la prima scelta nel form.
	 *
	 * @param datiTari
	 */
	private void setSelectedCheckbox(DatiDichiarazioneTari datiTari) {
		List<DatiImmobile> cessazioneDom = datiTari.getCessazioneDom();
		if (cessazioneDom != null) {
			for (int i = 0; i < cessazioneDom.size(); i++) {
				DatiImmobile datiImmobile = cessazioneDom.get(i);
				String identificativoUtenza = datiImmobile.getIdentificativoUtenza();
				if (identificativoUtenza != null && i == 0) {
					datiTari.setIdentificativoUtenzeSelezionateCessazione(identificativoUtenza);
					break;
				}
			}
		}

		List<DatiImmobile> cessazioneNonDom = datiTari.getCessazioneNonDom();
		if (cessazioneNonDom != null) {
			for (int i = 0; i < cessazioneNonDom.size(); i++) {
				DatiImmobile datiImmobile = cessazioneNonDom.get(i);
				String identificativoUtenza = datiImmobile.getIdentificativoUtenza();
				if (identificativoUtenza != null && i == 0) {
					datiTari.setIdentificativoUtenzeSelezionateCessazione(identificativoUtenza);
					break;
				}
			}
		}

		List<DatiImmobile> variazioneDom = datiTari.getVariazioneDom();
		if (variazioneDom != null) {
			for (int i = 0; i < variazioneDom.size(); i++) {
				DatiImmobile datiImmobile = variazioneDom.get(i);
				String identificativoUtenza = datiImmobile.getIdentificativoUtenza();
				if (identificativoUtenza != null && i == 0) {
					datiTari.setIdentificativoUtenzeSelezionateVariazione(identificativoUtenza);
					break;
				}
			}
		}

		List<DatiImmobile> variazioneNonDom = datiTari.getVariazioneNonDom();
		if (variazioneNonDom != null) {
			for (int i = 0; i < variazioneNonDom.size(); i++) {
				DatiImmobile datiImmobile = variazioneNonDom.get(i);
				String identificativoUtenza = datiImmobile.getIdentificativoUtenza();
				if (identificativoUtenza != null && i == 0) {
					datiTari.setIdentificativoUtenzeSelezionateVariazione(identificativoUtenza);
					break;
				}
			}
		}

	}

	/**
	 * Genera il file XML dalla dichiarazione passata in input.
	 *
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 */
	private DichiarazioneTassaRifiutiInputRichiesta getDichiarazioneTassaRifiutiInputRichiesta(DatiDichiarazioneTari datiTari, UserPreferences userPreferences) throws Exception {

		DichiarazioneTassaRifiutiInputRichiesta dichiarazioneTassaRifiutiInputRichiesta = new DichiarazioneTassaRifiutiInputRichiesta();

		boolean stradarioEnable = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI, userPreferences.getIdComuneIsa());

		String tipoUtenza = datiTari.getTipoUtenza();

		Dichiarazione dichiarazione = getDichiarazione(datiTari, userPreferences);
		if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE)) {
			// Dati generali dichiarazione
			Iscrizione iscrizione = new Iscrizione();
			BeanUtils.copyProperties(dichiarazione, iscrizione);

			// TODO La provenienza suppongo coincide con la residenza (da come è impostato il form)?
			iscrizione.setProvenienza(dichiarazione.getDichiarante().getDomicilio());

			List<DatiImmobile> immobiliDom = datiTari.getIscrizioneDom();
			if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {
				UtenzeCommerciali utenzeCommerciali = new UtenzeCommerciali();
				List<UtenzaCommerciale> utenze = utenzeCommerciali.getUtenza();
				List<DatiImmobile> iscrizioneNonDom = datiTari.getIscrizioneNonDom();
				if (iscrizioneNonDom != null) {
					for (DatiImmobile datiImmobile : iscrizioneNonDom) {
						UtenzaCommerciale utenzaCommerciale = getUtenzaCommerciale(datiTari, datiImmobile, stradarioEnable);
						utenze.add(utenzaCommerciale);
					}
				}

				utenzeCommerciali.setNote(datiTari.getAltroNoteNonDom());

				iscrizione.setUtenzeCommerciali(utenzeCommerciali);
			}
			else if (tipoUtenza.equals(UTENZA_DOMESTICA)) {
				UtenzeDomestiche utenzeDomestiche = new UtenzeDomestiche();
				List<UtenzaDomestica> utenze = utenzeDomestiche.getUtenza();
				for (DatiImmobile datiImmobile : immobiliDom) {
					UtenzaDomestica utenzaDomestica = getUtenzaDomestica(datiTari, datiImmobile, stradarioEnable);
					utenze.add(utenzaDomestica);
				}

				utenzeDomestiche.setContribuenteResidente(!datiTari.isNonResidente());

				// TODO verificare con Coppi l'errore del numero componenti non valido (se metto 0)
				Integer numeroResidenti = 0;
				if (datiTari.getTotaleNucleoInResidenza() != null && !datiTari.getTotaleNucleoInResidenza().isEmpty()) {
					numeroResidenti = Integer.parseInt(datiTari.getTotaleNucleoInResidenza());
				}
				else if (datiTari.getTotaleNucleoFamiliare() != null && !datiTari.getTotaleNucleoFamiliare().isEmpty()) {
					numeroResidenti = Integer.parseInt(datiTari.getTotaleNucleoFamiliare());
				}

				utenzeDomestiche.setNumComponentiNucleoFamiliare(numeroResidenti);

				List<Occupante> occupanti = datiTari.getOccupanti();
				if (occupanti != null) {
					List<OccupanteImmobile> occupantiImmobile = utenzeDomestiche.getOccupanteImmobile();
					for (Occupante occupante : occupanti) {
						if (occupante.getCodiceFiscale() != null && !occupante.getCodiceFiscale().isEmpty()) {
							OccupanteImmobile occupanteImmobile = new OccupanteImmobile();
							occupanteImmobile.setCodiceFiscale(occupante.getCodiceFiscale());
							occupanteImmobile.setCognome(occupante.getCognome());
							occupanteImmobile.setDataInizioOccupazione(getData(occupante.getDataInizioOccupazione()));
							occupanteImmobile.setDataFineOccupazione(getData(occupante.getDataFineOccupazione()));
							occupanteImmobile.setDataNascita(getData(occupante.getDataNascita()));
							occupanteImmobile.setNome(occupante.getNome());
							occupantiImmobile.add(occupanteImmobile);
						}
					}
				}

				iscrizione.setUtenzeDomestiche(utenzeDomestiche);
			}

			dichiarazioneTassaRifiutiInputRichiesta.setIscrizione(iscrizione);
		}
		else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {

			// Dati generali dichiarazione
			Variazione variazione = new Variazione();
			BeanUtils.copyProperties(dichiarazione, variazione);

			if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {
				List<DatiImmobile> variazioneNonDom = datiTari.getVariazioneNonDom();
				if (variazioneNonDom != null) {
					it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeCommerciali utenzeCommerciali = new it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeCommerciali();
					List<UtenzaCommercialeVariazione> utenze = utenzeCommerciali.getUtenza();
					for (DatiImmobile datiImmobile : variazioneNonDom) {
						if (datiTari.getIdentificativoUtenzeSelezionateVariazione().equalsIgnoreCase(datiImmobile.getIdentificativoUtenza())) {
							UtenzaCommercialeVariazione utenzaCommercialeVariazione = getUtenzaCommercialeVariazione(datiTari, datiImmobile, stradarioEnable);
							utenze.add(utenzaCommercialeVariazione);
						}
					}
					utenzeCommerciali.setNote(datiTari.getAltroNoteNonDom());

					variazione.setUtenzeCommerciali(utenzeCommerciali);
				}
			}
			else if (tipoUtenza.equals(UTENZA_DOMESTICA)) {
				List<DatiImmobile> variazioneDom = datiTari.getVariazioneDom();
				if (variazioneDom != null) {
					it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeDomestiche utenzeDomestiche = new it.osapulie.tributi.web.ws.input.types.Variazione.UtenzeDomestiche();
					List<UtenzaDomesticaVariazione> utenze = utenzeDomestiche.getUtenza();
					for (DatiImmobile datiImmobile : variazioneDom) {
						if (datiTari.getIdentificativoUtenzeSelezionateVariazione().equalsIgnoreCase(datiImmobile.getIdentificativoUtenza())) {
							UtenzaDomesticaVariazione utenzaDomesticaVariazione = getUtenzaDomesticaVariazione(datiTari, datiImmobile, stradarioEnable);
							utenze.add(utenzaDomesticaVariazione);
						}
					}

					utenzeDomestiche.setAltro(datiTari.getAltroNoteDom());
					utenzeDomestiche.setNumeroComponentiNucleoFamiliare(datiTari.getVarCompNuclA() != null ? Integer.parseInt(datiTari.getVarCompNuclA()) : null);

					List<Occupante> occupanti = datiTari.getVariazioneOccupanti();
					if (occupanti != null) {
						List<VariazioneOccupanteImmobile> variazioneOccupanteImmobile = utenzeDomestiche.getVariazioneOccupanteImmobile();
						for (Occupante occupante : occupanti) {
							if (occupante.getCodiceFiscale() != null && !occupante.getCodiceFiscale().isEmpty()) {
								VariazioneOccupanteImmobile occupanteImmobile = new VariazioneOccupanteImmobile();
								occupanteImmobile.setCodiceFiscale(occupante.getCodiceFiscale());
								occupanteImmobile.setCognome(occupante.getCognome());
								occupanteImmobile.setDataInizioOccupazione(getData(occupante.getDataInizioOccupazione()));
								occupanteImmobile.setDataFineOccupazione(getData(occupante.getDataFineOccupazione()));
								occupanteImmobile.setDataNascita(getData(occupante.getDataNascita()));
								occupanteImmobile.setNome(occupante.getNome());
								Codifica tipoModifica = new Codifica();
								tipoModifica.setCodice(occupante.getTipoModifica());
								occupanteImmobile.setTipoVariazione(tipoModifica);
								variazioneOccupanteImmobile.add(occupanteImmobile);
							}
						}
					}

					variazione.setUtenzeDomestiche(utenzeDomestiche);
				}
			}
			dichiarazioneTassaRifiutiInputRichiesta.setVariazione(variazione);
		}
		else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_CESSAZIONE)) {

			Cessazione cessazione = new Cessazione();
			BeanUtils.copyProperties(dichiarazione, cessazione);
			Rimborso rimborso = new Rimborso();
			Codifica modalitaRimborso = new Codifica();
			if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {

				if ("rimborso".equalsIgnoreCase(datiTari.getConcessioneQuoteNonDom())) {
					modalitaRimborso.setDescrizione(datiTari.getModalitaRimborsoNonDom());
					if (datiTari.getModalitaRimborsoNonDom().equals("filiale")) {
						modalitaRimborso.setCodice("M");
					}
					else if (datiTari.getModalitaRimborsoNonDom().equals("accredito")) {
						modalitaRimborso.setCodice("A");
					}
					if (!datiTari.getIbanNonDom().isEmpty()) {
						rimborso.setCodIban(datiTari.getIbanNonDom());
					}
					rimborso.setModalitaRimborso(modalitaRimborso);
					cessazione.setRimborso(rimborso);
				}
			}
			else if (tipoUtenza.equals(UTENZA_DOMESTICA)) {

				if ("rimborso".equalsIgnoreCase(datiTari.getConcessioneQuoteDom())) {
					modalitaRimborso.setDescrizione(datiTari.getModalitaRimborsoDom());
					if (datiTari.getModalitaRimborsoDom().equals("filiale")) {
						modalitaRimborso.setCodice("M");
					}
					else if (datiTari.getModalitaRimborsoDom().equals("accredito")) {
						modalitaRimborso.setCodice("A");
					}
					if (!datiTari.getIbanDom().isEmpty()) {
						rimborso.setCodIban(datiTari.getIbanDom());
					}
					rimborso.setModalitaRimborso(modalitaRimborso);
					cessazione.setRimborso(rimborso);
				}
			}

			String comuneEmigrazioneDom = datiTari.getComuneEmigrazioneDom();
			if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {
				List<DatiImmobile> cessazioneNonDom = datiTari.getCessazioneNonDom();
				if (cessazioneNonDom != null) {
					it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeCommerciali utenzeCommerciali = new it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeCommerciali();
					List<UtenzaCommerciale> utenze = utenzeCommerciali.getUtenza();
					for (DatiImmobile datiImmobile : cessazioneNonDom) {
						if (datiTari.getIdentificativoUtenzeSelezionateCessazione().equalsIgnoreCase(datiImmobile.getIdentificativoUtenza())) {
							UtenzaCommerciale utenzaCommerciale = getUtenzaCommerciale(datiTari, datiImmobile, stradarioEnable);
							utenze.add(utenzaCommerciale);
						}
					}

					// Motivo cessazione
					String motiviCessazioneNonDom = datiTari.getMotivoCessazioneNonDom();
					if (motiviCessazioneNonDom != null) {
						Codifica motivo = new Codifica();
						motivo.setCodice(motiviCessazioneNonDom);
						if (motiviCessazioneNonDom.equals("cessazione")) {
							motivo.setDescrizione(datiTari.getAttivitaCessata());
						}
						else if (motiviCessazioneNonDom.equals("duplicazione")) {
							motivo.setDescrizione(datiTari.getCoabitanteNonDom());
						}
						else if (motiviCessazioneNonDom.equals("cambio_denominazione")) {
							motivo.setDescrizione(datiTari.getNuovaDenominazione());
						}
						else if (motiviCessazioneNonDom.equals("trasferimento")) {
							it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazioneAltroComune = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
							ubicazioneAltroComune.setCap(datiTari.getCapEmigrazioneNonDom());

							it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civicoEmigrazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
							if (datiTari.getCivicoEmigrazioneDom() != null && !datiTari.getCivicoEmigrazioneDom().isEmpty()) {
								civicoEmigrazione.setNumero(Integer.parseInt(datiTari.getCivicoEmigrazioneDom()));
							}
							civicoEmigrazione.setEsponente(datiTari.getEsponenteEmigrazioneDom());
							ubicazioneAltroComune.setCivico(civicoEmigrazione);

							ubicazioneAltroComune.setComune(getCodificaComune(comuneEmigrazioneDom));
							ubicazioneAltroComune.setVia(new Codifica(null, datiTari.getViaEmigrazioneDom()));

							utenzeCommerciali.setCessazionePerTrasferimentoAltroComune(ubicazioneAltroComune);
						}
						else if (motiviCessazioneNonDom.equals("altro")) {
							motivo.setDescrizione(datiTari.getAltriMotiviNonDom());
						}
						utenzeCommerciali.setMotivo(motivo);
					}

					cessazione.setUtenzeCommerciali(utenzeCommerciali);
				}

			}
			else if (tipoUtenza.equals(UTENZA_DOMESTICA)) {
				List<DatiImmobile> cessazioneDom = datiTari.getCessazioneDom();
				if (cessazioneDom != null) {
					it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeDomestiche utenzeDomestiche = new it.osapulie.tributi.web.ws.input.types.Cessazione.UtenzeDomestiche();
					List<UtenzaDomestica> utenze = utenzeDomestiche.getUtenza();
					for (DatiImmobile datiImmobile : cessazioneDom) {
						if (datiTari.getIdentificativoUtenzeSelezionateCessazione().equalsIgnoreCase(datiImmobile.getIdentificativoUtenza())) {
							UtenzaDomestica utenzaDomestica = getUtenzaDomestica(datiTari, datiImmobile, stradarioEnable);
							utenze.add(utenzaDomestica);
						}
					}

					String motiviCessazioneDom = datiTari.getMotivoCessazioneDom();
					if (motiviCessazioneDom != null) {
						if (motiviCessazioneDom.equals("decesso")) {
							utenzeDomestiche.setCessazionePerDecesso(getData(datiTari.getDataDecesso()));
						}
						else if (motiviCessazioneDom.equals("coabitazione")) {
							CessazionePerCoabitazione cessazionePerCoabitazione = new CessazionePerCoabitazione();
							cessazionePerCoabitazione.setCodiceFiscaleCoabitante(datiTari.getCoabitanteDom());
							it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
							ubicazione.setCap(datiTari.getCapCoabitazione());
							it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civicoCoabitazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
							if (stradarioEnable) {
								civicoCoabitazione.setCodice(datiTari.getCivicoCoabitazione());
								if (datiTari.getCivicoCoabitazioneTextHidden() != null && !datiTari.getCivicoCoabitazioneTextHidden().isEmpty()) {
									civicoCoabitazione.setNumero(Integer.parseInt(datiTari.getCivicoCoabitazioneTextHidden()));
								}
								ubicazione.setVia(new Codifica(datiTari.getIndirizzoCoabitazione(), datiTari.getIndirizzoCoabitazioneTextHidden()));
							}
							else {
								ubicazione.setVia(new Codifica(null, datiTari.getIndirizzoCoabitazione()));
							}
							civicoCoabitazione.setEsponente(datiTari.getEspCoabitazione());
							ubicazione.setCivico(civicoCoabitazione);
							ubicazione.setComune(getCodificaComune(userPreferences.getCodiceIstatComune()));
							ubicazione.setInterno(datiTari.getIntCoabitazione());
							ubicazione.setLocalita(null);
							ubicazione.setPiano(datiTari.getPianoCoabitazione());
							ubicazione.setScala(datiTari.getScalaCoabitazione());
							ubicazione.setSuffisso(datiTari.getSuffCoabitazione());
							cessazionePerCoabitazione.setUbicazione(ubicazione);
							utenzeDomestiche.setCessazionePerCoabitazione(cessazionePerCoabitazione);
						}
						else if (motiviCessazioneDom.equals("trasferimento")) {
							it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazioneAltroComune = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
							ubicazioneAltroComune.setCap(datiTari.getCapEmigrazioneDom());

							it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civicoEmigrazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
							if (datiTari.getCivicoEmigrazioneDom() != null && !datiTari.getCivicoEmigrazioneDom().isEmpty()) {
								civicoEmigrazione.setNumero(Integer.parseInt(datiTari.getCivicoEmigrazioneDom()));
							}
							civicoEmigrazione.setEsponente(datiTari.getEsponenteEmigrazioneDom());
							ubicazioneAltroComune.setCivico(civicoEmigrazione);
							ubicazioneAltroComune.setComune(getCodificaComune(comuneEmigrazioneDom));
							ubicazioneAltroComune.setVia(new Codifica(null, datiTari.getViaEmigrazioneDom()));

							utenzeDomestiche.setCessazionePerEmigrazioneAltroComune(ubicazioneAltroComune);
						}
						else if (motiviCessazioneDom.equals("concessione") || motiviCessazioneDom.equals("restituito") || motiviCessazioneDom.equals("vuoto") || motiviCessazioneDom.equals("venduto")
								|| motiviCessazioneDom.equals("altro")) {
							utenzeDomestiche.setAltroMotivo(new Codifica(motiviCessazioneDom, motiviCessazioneDom));
						}
					}

					cessazione.setUtenzeDomestiche(utenzeDomestiche);
				}
			}

			dichiarazioneTassaRifiutiInputRichiesta.setCessazione(cessazione);

		}
		else if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {

			Trasferimento trasferimento = new Trasferimento();
			BeanUtils.copyProperties(dichiarazione, trasferimento);

			trasferimento.setIdentificativoUtenza(datiTari.getIdentificativoUtenzeSelezionateCessazione());

			if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {
				NuovaUtenzaCommerciale nuovaUtenzaCommerciale = new NuovaUtenzaCommerciale();
				List<UtenzaCommerciale> utenze = nuovaUtenzaCommerciale.getUtenza();
				List<DatiImmobile> iscrizioneNonDom = datiTari.getIscrizioneNonDom();
				if (iscrizioneNonDom != null) {
					for (DatiImmobile datiImmobile : iscrizioneNonDom) {
						UtenzaCommerciale utenzaCommerciale = getUtenzaCommerciale(datiTari, datiImmobile, stradarioEnable);
						utenze.add(utenzaCommerciale);
					}
				}

				nuovaUtenzaCommerciale.setNote(datiTari.getAltroNoteNonDom());

				trasferimento.setNuovaUtenzaCommerciale(nuovaUtenzaCommerciale);
			}
			else if (tipoUtenza.equals(UTENZA_DOMESTICA)) {
				NuovaUtenzaDomestica nuovaUtenzaDomestica = new NuovaUtenzaDomestica();
				List<UtenzaDomestica> utenze = nuovaUtenzaDomestica.getUtenza();
				List<DatiImmobile> iscrizioneDom = datiTari.getIscrizioneDom();
				if (iscrizioneDom != null) {
					for (DatiImmobile datiImmobile : iscrizioneDom) {
						UtenzaDomestica utenzaDomestica = getUtenzaDomestica(datiTari, datiImmobile, stradarioEnable);
						utenze.add(utenzaDomestica);
					}
				}

				nuovaUtenzaDomestica.setContribuenteResidente(!datiTari.isNonResidente());

				// TODO verificare con Coppi l'errore del numero componenti non valido (se metto 0)
				Integer numeroResidenti = 0;
				if (datiTari.getTotaleNucleoInResidenza() != null && !datiTari.getTotaleNucleoInResidenza().isEmpty()) {
					numeroResidenti = Integer.parseInt(datiTari.getTotaleNucleoInResidenza());
				}
				else if (datiTari.getTotaleNucleoFamiliare() != null && !datiTari.getTotaleNucleoFamiliare().isEmpty()) {
					numeroResidenti = Integer.parseInt(datiTari.getTotaleNucleoFamiliare());
				}

				nuovaUtenzaDomestica.setNumComponentiNucleoFamiliare(numeroResidenti);

				List<Occupante> occupanti = datiTari.getOccupanti();
				if (occupanti != null) {
					List<OccupanteImmobileVariazione> occupantiImmobile = nuovaUtenzaDomestica.getOccupanteImmobile();
					for (Occupante occupante : occupanti) {
						if (occupante.getCodiceFiscale() != null && !occupante.getCodiceFiscale().isEmpty()) {
							OccupanteImmobileVariazione occupanteImmobile = new OccupanteImmobileVariazione();
							occupanteImmobile.setCodiceFiscale(occupante.getCodiceFiscale());
							occupanteImmobile.setCognome(occupante.getCognome());
							occupanteImmobile.setNome(occupante.getNome());
							occupanteImmobile.setDataNascita(getData(occupante.getDataNascita()));

							occupanteImmobile.setDataInizioOccupazione(getData(occupante.getDataInizioOccupazione()));
							occupanteImmobile.setDataFineOccupazione(getData(occupante.getDataFineOccupazione()));
							occupanteImmobile.setTipoVariazioneOccupanti(new Codifica("A", getTipoModificaVariazioneOccupantiMap().get("A")));
							occupantiImmobile.add(occupanteImmobile);
						}
					}
				}

				trasferimento.setNuovaUtenzaDomestica(nuovaUtenzaDomestica);
			}

			dichiarazioneTassaRifiutiInputRichiesta.setTrasferimento(trasferimento);

		}

		return dichiarazioneTassaRifiutiInputRichiesta;
	}

	/**
	 * Ritorna la codifica corrispondente al comune passato in input.
	 *
	 * @param comune
	 * @return
	 */
	private Codifica getCodificaComune(String codiceIstat) {

		Comune comune = comuneService.getComuneByCodiceISTAT(codiceIstat);

		return new Codifica(comune != null ? comune.getCodiceCatastale() : null, comune != null ? comune.getDenominazione() : null);
	}

	/**
	 *
	 * @param datiTari
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private UtenzaDomestica getUtenzaDomestica(DatiDichiarazioneTari datiTari, DatiImmobile datiImmobile, boolean stradarioEnable) {

		if (datiImmobile.getIndirizzo() == null || datiImmobile.getIndirizzo().isEmpty() || datiImmobile.getIndirizzo().equals("0")) {
			return null;
		}

		UtenzaDomestica utenzaDomestica = new UtenzaDomestica();
		if (datiImmobile.getCategoria() != null) {
			utenzaDomestica.setCategoriaCatastale(new Codifica(datiImmobile.getCategoria(), getCategorieCatastali().get(datiImmobile.getCategoria())));
		}

		// datiCastali
		utenzaDomestica.setDatiCatastali(getDatiCatastali(datiImmobile));
		utenzaDomestica.setIdentificativoUtenza(datiImmobile.getIdentificativoUtenza());

		// Riduzione
		String tipologiaRichiesta = datiTari.getTipologiaRichiestaDom();
		boolean applicazioneRiduzione = tipologiaRichiesta != null ? tipologiaRichiesta.equals("applicazione") : false;
		boolean revocaRiduzione = tipologiaRichiesta != null ? tipologiaRichiesta.equals("revoca") : false;

		List<Riduzione> riduzioni = utenzaDomestica.getRiduzione();
		List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniDomestiche = null;

		if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
			riduzioniDomestiche = datiTari.getRiduzioniDomesticheVariazione();
		}
		else {
			riduzioniDomestiche = datiTari.getRiduzioniDomesticheIscrizione();
		}

		if (riduzioniDomestiche != null) {
			for (it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzioneDomestica : riduzioniDomestiche) {
				List<Valore> valori = riduzioneDomestica.getValori();
				if (valori != null) {
					for (Valore valore : valori) {
						if (riduzioneDomestica.getCodiceArticolo() != null && valore.getChiave() != null) {
							Riduzione riduzione = new Riduzione();
							riduzione.setArticolo(new Codifica(riduzioneDomestica.getCodiceArticolo(), riduzioneDomestica.getDescrizioneArticolo()));
							riduzione.setValore(new Codifica(valore.getChiave(), valore.getValore()));
							riduzione.setTipo(new Tipo(revocaRiduzione, applicazioneRiduzione));
							riduzioni.add(riduzione);
						}
					}
				}
			}
		}

		if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ISCRIZIONE) || datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_TRASFERIMENTO)) {
			utenzaDomestica.setSuperficie(getBigDecimal(datiImmobile.getMq()));
		}
		if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
			utenzaDomestica.setSuperficie(getBigDecimal(datiImmobile.getVarDiSuperficieDa()));
		}
		utenzaDomestica.setUbicazione(getIndirizzoFromImmobile(datiImmobile, stradarioEnable));
		utenzaDomestica.setUso(new Codifica(datiImmobile.getTipo(), getTipologieImmobile().get(datiImmobile.getTipo())));

		return utenzaDomestica;
	}

	/**
	 *
	 * @param datiTari
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private UtenzaCommerciale getUtenzaCommerciale(DatiDichiarazioneTari datiTari, DatiImmobile datiImmobile, boolean stradarioEnable) {

		if (datiImmobile.getIndirizzo() == null || datiImmobile.getIndirizzo().isEmpty()) {
			return null;
		}

		UtenzaCommerciale utenzaCommerciale = new UtenzaCommerciale();

		utenzaCommerciale.setCodiceAttivita(datiImmobile.getCodAteco());
		utenzaCommerciale.setDatiCatastali(getDatiCatastali(datiImmobile));
		utenzaCommerciale.setIdentificativoUtenza(datiImmobile.getIdentificativoUtenza());

		String tipologiaRichiesta = datiTari.getTipologiaRichiestaNonDom();
		boolean applicazioneRiduzione = true;
		boolean revocaRiduzione = false;
		if (tipologiaRichiesta != null) {
			applicazioneRiduzione = tipologiaRichiesta.equals("applicazione");
			revocaRiduzione = tipologiaRichiesta.equals("revoca");
		}

		if ("S".equalsIgnoreCase(datiImmobile.getUtenzaConRiduzione())) {

			List<it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione> riduzioniNonDomestiche = null;

			if (datiTari.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_VARIAZIONE)) {
				riduzioniNonDomestiche = datiTari.getRiduzioniNonDomesticheVariazione();
			}
			else {
				riduzioniNonDomestiche = datiTari.getRiduzioniNonDomesticheIscrizione();
			}

			if (riduzioniNonDomestiche != null) {
				List<Riduzione> riduzioni = utenzaCommerciale.getRiduzione();
				for (it.osapulie.tributi.web.portlet.dichiarazionetari.form.Riduzione riduzioneForm : riduzioniNonDomestiche) {
					if (riduzioneForm.getCodiceArticolo() != null && !riduzioneForm.getCodiceArticolo().isEmpty()) {

						List<Valore> valori = riduzioneForm.getValori();
						if (valori != null) {
							for (Valore valore : valori) {
								if (riduzioneForm.getCodiceArticolo() != null && valore.getChiave() != null) {
									Riduzione riduzione = new Riduzione();
									riduzione.setArticolo(new Codifica(riduzioneForm.getCodiceArticolo(), riduzioneForm.getDescrizioneArticolo()));
									riduzione.setValore(new Codifica(valore.getChiave(), valore.getValore()));
									riduzione.setTipo(new Tipo(revocaRiduzione, applicazioneRiduzione));
									riduzioni.add(riduzione);
								}
							}
						}
					}
				}
			}
		}
		Integer superficieIntassabile = datiImmobile.getIntassabileFissi() != null && !datiImmobile.getIntassabileFissi().isEmpty() ? getBigDecimal(datiImmobile.getIntassabileFissi()).intValue() : 0;
		if (datiImmobile.getIntassabileAltre() != null && !datiImmobile.getIntassabileAltre().isEmpty()) {
			superficieIntassabile += Integer.parseInt(datiImmobile.getIntassabileAltre());
		}
		utenzaCommerciale.setSuperficieIntassabile(superficieIntassabile);

		Integer superficie = datiImmobile.getSuperficieTotale() != null && !datiImmobile.getSuperficieTotale().isEmpty() ? getBigDecimal(datiImmobile.getSuperficieTotale()).intValue() : null;
		utenzaCommerciale.setSuperficieTotale(superficie);

		if ("S".equalsIgnoreCase(datiImmobile.getTipologiaSuperficie())) {
			utenzaCommerciale.setSuperficieScoperta(superficie);
		}
		else {
			utenzaCommerciale.setSuperficieCoperta(superficie);
		}

		utenzaCommerciale.setUbicazione(getIndirizzoFromImmobile(datiImmobile, stradarioEnable));
		// TODO non esiste nella dichiarazione
		// utenzaCommerciale.setSuperficieRifiutiSpeciali(Integer);

		return utenzaCommerciale;
	}

	/**
	 *
	 * @param datiTari
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private UtenzaCommercialeVariazione getUtenzaCommercialeVariazione(DatiDichiarazioneTari datiTari, DatiImmobile datiImmobile, boolean stradarioEnable) {

		UtenzaCommerciale utenzaCommerciale = getUtenzaCommerciale(datiTari, datiImmobile, stradarioEnable);
		Integer nuovaSuperficie = datiImmobile.getVarDiSuperficieA() != null && !datiImmobile.getVarDiSuperficieA().isEmpty() ? getBigDecimal(datiImmobile.getVarDiSuperficieA()).intValue() : null;

		return new UtenzaCommercialeVariazione(utenzaCommerciale.getIdentificativoUtenza(), utenzaCommerciale.getUbicazione(), utenzaCommerciale.getDatiCatastali(),
				utenzaCommerciale.getCodiceAttivita(), utenzaCommerciale.getRiduzione(), utenzaCommerciale.getSuperficieTotale(), utenzaCommerciale.getSuperficieCoperta(),
				utenzaCommerciale.getSuperficieScoperta(), utenzaCommerciale.getSuperficieIntassabile(), utenzaCommerciale.getSuperficieRifiutiSpeciali(), nuovaSuperficie,
				datiImmobile.getVarDiCategoriaA());
	}

	/**
	 *
	 * @param datiTari
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private UtenzaDomesticaVariazione getUtenzaDomesticaVariazione(DatiDichiarazioneTari datiTari, DatiImmobile datiImmobile, boolean stradarioEnable) {

		UtenzaDomestica utenzaDomestica = getUtenzaDomestica(datiTari, datiImmobile, stradarioEnable);
		Integer nuovaSuperficie = datiImmobile.getVarDiSuperficieA() != null && !datiImmobile.getVarDiSuperficieA().isEmpty() ? getBigDecimal(datiImmobile.getVarDiSuperficieA()).intValue() : null;

		return new UtenzaDomesticaVariazione(utenzaDomestica.getIdentificativoUtenza(), utenzaDomestica.getUbicazione(), utenzaDomestica.getDatiCatastali(), utenzaDomestica.getCategoriaCatastale(),
				utenzaDomestica.getUso(), utenzaDomestica.getSuperficie(), utenzaDomestica.getRiduzione(), nuovaSuperficie);
	}

	/**
	 *
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 * @throws ParseException
	 */
	private Dichiarazione getDichiarazione(DatiDichiarazioneTari datiTari, UserPreferences userPreferences) throws ParseException {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		Dichiarazione dichiarazione = new Dichiarazione();

		dichiarazione.setData(Calendar.getInstance());
		dichiarazione.setDataDecorrenza(getData(datiTari.getaDecorrereDa()));
		dichiarazione.setDelegato(getDelegato(userPreferences, osApulieUserDetails));
		dichiarazione.setDichiarante(getDichiarante(datiTari, userPreferences));
		dichiarazione.setDocumentoIdentita(datiTari.getEstremiDocumento());
		dichiarazione.setNote(datiTari.getNoteGenerali());

		String tipoUtenza = datiTari.getTipoUtenza();
		// TODO descrizione codifica
		if (tipoUtenza.equals(UTENZA_DOMESTICA)) {
			dichiarazione.setTipoUtenza(new Codifica("D", tipoUtenza));
		}
		else if (tipoUtenza.equals(UTENZA_NON_DOMESTICA)) {
			dichiarazione.setTipoUtenza(new Codifica("C", tipoUtenza));
		}

		dichiarazione.setTracciamento(tributiService.getTracciamento(osApulieUserDetails, userPreferences));

		return dichiarazione;
	}

	/**
	 * @param datiTari
	 * @param userPreferences
	 * @return
	 * @throws ParseException
	 */
	private Dichiarante getDichiarante(DatiDichiarazioneTari datiTari, UserPreferences userPreferences) throws ParseException {

		Dichiarante dichiarante = new Dichiarante();
		Contribuente contribuente = getContribuenteFromDichiarazione(datiTari);
		BeanUtils.copyProperties(contribuente, dichiarante);

		// TODO non c'è il cellulare
		dichiarante.setCellulare(null);
		if (datiTari.getEmail() != null && !datiTari.getEmail().isEmpty()) {
			dichiarante.setEmail(datiTari.getEmail());
		}
		dichiarante.setIdentificativoContribuente(datiTari.getIdContribuente());

		if (datiTari.getPec() != null && !datiTari.getPec().isEmpty()) {
			dichiarante.setPec(datiTari.getPec());
		}

		if (datiTari.getTipoUtenza().equals(UTENZA_NON_DOMESTICA)) {
			it.osapulie.tributi.web.ws.input.types.Indirizzo indirizzo = getIndirizzoSedeLegaleFromDatiDichiarazione(datiTari);
			dichiarante.setDomicilio(indirizzo);
			Rappresentante rappresentante = getRappresentanteFromDichiarazione(datiTari);
			dichiarante.setRappresentante(rappresentante);
		}
		else if (datiTari.getTipoUtenza().equals(UTENZA_DOMESTICA)) {
			it.osapulie.tributi.web.ws.input.types.Indirizzo indirizzo = getIndirizzoResidenzaFromDatiDichiarazione(datiTari, userPreferences);
			dichiarante.setDomicilio(indirizzo);
			dichiarante.setFax(datiTari.getFax());
			dichiarante.setTelefono(datiTari.getTelefono());
		}
		Recapito recapito = getRecapitoFromDichiarazione(datiTari, userPreferences);
		dichiarante.setRecapito(recapito);

		return dichiarante;
	}

	/**
	 * @param userPreferences
	 * @param osApulieUserDetails
	 * @param iscrizione
	 */
	private String getDelegato(UserPreferences userPreferences, OSApulieUserDetails osApulieUserDetails) {

		String result = null;
		Long idDelega = userPreferences.getIdDelega();
		if (idDelega != null) {
			Delega delega = delegaService.getDelegaByPk(idDelega);
			Azienda aziendaDelegata = delega.getDelegato();
			ProfiloUtenteCittadino utenteAzienda = osApulieUserDetails.getProfiloUtenteCittadino();
			result = utenteAzienda.getCognome() + " " + utenteAzienda.getNome() + " (" + aziendaDelegata.getRagioneSociale() + ")";
		}

		return result;
	}

	/**
	 * @param data
	 * @return
	 * @throws ParseException
	 */
	private Calendar getData(String data) throws ParseException {
		Calendar calendar = null;
		if (data != null && !data.isEmpty()) {
			calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(data));
		}
		return calendar;
	}

	/**
	 * @param datiImmobile
	 * @return
	 */
	private DatiCatastali getDatiCatastali(DatiImmobile datiImmobile) {

		DatiCatastali datiCatastali = new DatiCatastali();
		datiCatastali.setSezione(datiImmobile.getSezione());
		if (datiImmobile.getFoglio() != null && !datiImmobile.getFoglio().isEmpty()) {
			datiCatastali.setFoglio(Integer.parseInt(datiImmobile.getFoglio()));
		}
		if (datiImmobile.getParticella() != null && !datiImmobile.getParticella().isEmpty()) {
			datiCatastali.setParticella(Integer.parseInt(datiImmobile.getParticella()));
		}
		if (datiImmobile.getSubalterno() != null && !datiImmobile.getSubalterno().isEmpty()) {
			datiCatastali.setSubalterno(Integer.parseInt(datiImmobile.getSubalterno()));
		}

		return datiCatastali;
	}

	/**
	 * @param datiTari
	 * @return
	 */
	private Recapito getRecapitoFromDichiarazione(DatiDichiarazioneTari datiTari, UserPreferences userPreferences) {

		Recapito recapito = null;

		if (datiTari.getModalitaInvio() != null && datiTari.getModalitaInvio().equals("altro")) {
			recapito = new Recapito();

			recapito.setCap(datiTari.getCapSpedizione());

			it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
			Codifica codiceVia = new Codifica();

			boolean isIndirizzoSpedizioneConStradario = checkIndirizzoSpedizioneConStradario(userPreferences, datiTari);
			String indirizzoSpedizione = datiTari.getIndirizzoSpedizione();
			if (isIndirizzoSpedizioneConStradario) {
				civico.setCodice(datiTari.getCivicoResidenza());
				civico.setNumero(Integer.parseInt(datiTari.getCivicoResidenzaTextHidden()));
				if (indirizzoSpedizione.contains("-")) {
					indirizzoSpedizione = indirizzoSpedizione.split("-")[0];
				}
				codiceVia.setCodice(indirizzoSpedizione);
				codiceVia.setDescrizione(datiTari.getIndirizzoSpedizioneTextHidden());
			}
			else {
				civico.setNumero(Integer.parseInt(datiTari.getCivicoSpedizione()));
				codiceVia.setDescrizione(indirizzoSpedizione);
			}

			civico.setEsponente(datiTari.getEspSpedizione());
			recapito.setCivico(civico);
			String comuneSpedizione = datiTari.getComuneSpedizione();
			recapito.setComune(getCodificaComune(comuneSpedizione));
			recapito.setInterno(null);
			recapito.setLocalita(null);
			recapito.setPiano(null);
			recapito.setScala(null);
			recapito.setVia(codiceVia);
		}
		return recapito;
	}

	/**
	 * @param datiTari
	 * @return
	 * @throws ParseException
	 */
	private Contribuente getContribuenteFromDichiarazione(DatiDichiarazioneTari datiTari) throws ParseException {
		Contribuente contribuente = new Contribuente();
		if (datiTari.getPartitaIva() != null && !datiTari.getPartitaIva().isEmpty()) {
			contribuente.setPartitaIVA(datiTari.getPartitaIva());
		}
		if (datiTari.getTipoUtenza().equals(UTENZA_NON_DOMESTICA)) {
			if (datiTari.getNumRea() != null && !datiTari.getNumRea().isEmpty()) {
				contribuente.setNumeroREA(Long.parseLong(datiTari.getNumRea()));
			}
			contribuente.setProvinciaREA(datiTari.getProvinciaRea());
		}
		if (datiTari.getTipoPersona().equals(PERSONA_FISICA)) {
			PersonaFisica personaFisica = new PersonaFisica();
			personaFisica.setCodiceFiscale(datiTari.getCodiceFiscale());
			personaFisica.setCognome(datiTari.getCognome());
			// Distinzione tra comune di nascita Italiano e stato estero (codici ISTAT AN e codice
			// stato)
			if (datiTari.getStatoEstero() == null || datiTari.getStatoEstero().isEmpty()) {
				personaFisica.setComuneNascita(new Codifica(datiTari.getComuneNascita(), datiTari.getComuneNascitaHidden()));
			}
			else {
				personaFisica.setStatoEsteroNascita(new Codifica(datiTari.getStatoEstero(), datiTari.getDenominazioneEstero()));
				personaFisica.setComuneEsteroNascita(new Codifica(datiTari.getCodiceNascitaEstero(), datiTari.getComuneNascitaEstero()));
			}
			String dataNascita = datiTari.getDataNascita();
			if (dataNascita != null) {
				Calendar calendar = getData(dataNascita);
				personaFisica.setDataNascita(calendar);
			}
			personaFisica.setNome(datiTari.getNome());
			if (datiTari.getPartitaIva() != null && !datiTari.getPartitaIva().isEmpty()) {
				personaFisica.setPartitaIVA(datiTari.getPartitaIva());
			}
			if (datiTari.getProvinciaNascita() != null && !datiTari.getProvinciaNascita().isEmpty()) {
				personaFisica.setProvinciaNascita(new Codifica(datiTari.getProvinciaNascita(), null));
			}
			personaFisica.setSesso(new Codifica(datiTari.getSesso(), datiTari.getSesso()));
			contribuente.setPersonaFisica(personaFisica);
		}
		else if (datiTari.getTipoPersona().equals(PERSONA_GIURIDICA)) {
			PersonaGiuridica personaGiuridica = new PersonaGiuridica();
			personaGiuridica.setCodiceFiscale(datiTari.getCodiceFiscale());
			personaGiuridica.setRagioneDenominazioneSociale(datiTari.getNomeSocieta());
			if (datiTari.getPartitaIva() != null && !datiTari.getPartitaIva().isEmpty()) {
				personaGiuridica.setPartitaIva(datiTari.getPartitaIva());
			}
			contribuente.setPersonaGiuridica(personaGiuridica);

			if (datiTari.getNumRea() != null && !datiTari.getNumRea().isEmpty()) {
				contribuente.setNumeroREA(Long.parseLong(datiTari.getNumRea()));
			}
			contribuente.setProvinciaREA(datiTari.getProvinciaRea());

		}

		return contribuente;
	}

	/**
	 * @param datiTari
	 * @return
	 * @throws ParseException
	 */
	private Rappresentante getRappresentanteFromDichiarazione(DatiDichiarazioneTari datiTari) throws ParseException {
		Rappresentante rappresentante = new Rappresentante();
		// TODO non c'è il cellulare
		rappresentante.setCellulare(null);
		rappresentante.setCodiceFiscale(datiTari.getCodiceFiscale());
		rappresentante.setCognome(datiTari.getCognome());
		String dataNascita = datiTari.getDataNascita();
		if (dataNascita != null) {
			Calendar calendar = getData(dataNascita);
			rappresentante.setDataNascita(calendar);
		}
		if (datiTari.getEmail() != null && !datiTari.getEmail().isEmpty()) {
			rappresentante.setEmail(datiTari.getEmail());
		}
		rappresentante.setFax(datiTari.getFax());
		rappresentante.setNome(datiTari.getNome());
		if (datiTari.getPec() != null && !datiTari.getPec().isEmpty()) {
			rappresentante.setPec(datiTari.getPec());
		}

		// Distinzione tra comune di nascita Italiano e stato estero (codici ISTAT AN e codice
		// stato)
		if (datiTari.getStatoEstero() == null || datiTari.getStatoEstero().isEmpty()) {
			rappresentante.setComuneNascita(new Codifica(datiTari.getComuneNascita(), datiTari.getComuneNascitaHidden()));
		}
		else {
			rappresentante.setStatoEsteroNascita(new Codifica(datiTari.getStatoEstero(), datiTari.getDenominazioneEstero()));
			rappresentante.setComuneEsteroNascita(new Codifica(datiTari.getCodiceNascitaEstero(), datiTari.getComuneNascitaEstero()));
		}

		if (datiTari.getProvinciaNascita() != null && !datiTari.getProvinciaNascita().isEmpty()) {
			rappresentante.setProvinciaNascita(new Codifica(datiTari.getProvinciaNascita(), null));
		}
		rappresentante.setRagioneDenominazioneSociale(datiTari.getNomeSocieta());
		rappresentante.setSesso(new Codifica(datiTari.getSesso(), datiTari.getSesso()));
		rappresentante.setQualifica(datiTari.getRuolo());
		rappresentante.setTelefono(datiTari.getTelefono());
		return rappresentante;
	}

	/**
	 * @param datiImmobile
	 * @param stradarioEnable
	 * @return
	 */
	private it.osapulie.tributi.web.ws.input.types.Indirizzo getIndirizzoFromImmobile(DatiImmobile datiImmobile, boolean stradarioEnable) {
		it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
		Codifica codiceVia = new Codifica();
		String indirizzo = datiImmobile.getIndirizzo();
		if (stradarioEnable) {
			civico.setCodice(datiImmobile.getCivico());
			civico.setEsponente(datiImmobile.getEsponente());
			if (datiImmobile.getCivicoTextHidden() != null && !datiImmobile.getCivicoTextHidden().isEmpty()) {
				civico.setNumero(Integer.parseInt(datiImmobile.getCivicoTextHidden()));
			}
			ubicazione.setCivico(civico);
			if (indirizzo.contains("-")) {
				indirizzo = indirizzo.split("-")[0];
			}
			codiceVia.setCodice(indirizzo);
			codiceVia.setDescrizione(datiImmobile.getIndirizzoTextHidden());
			ubicazione.setVia(codiceVia);
		}
		else {
			civico.setEsponente(datiImmobile.getEsponente());
			if (datiImmobile.getCivico() != null && !datiImmobile.getCivico().isEmpty()) {
				civico.setNumero(Integer.parseInt(datiImmobile.getCivico()));
			}
			ubicazione.setCivico(civico);
			codiceVia.setDescrizione(indirizzo);
			ubicazione.setVia(codiceVia);
		}

		ubicazione.setLocalita(datiImmobile.getLocalita());
		ubicazione.setPiano(datiImmobile.getPiano());
		ubicazione.setInterno(datiImmobile.getInterno());
		ubicazione.setScala(datiImmobile.getScala());
		ubicazione.setSuffisso(datiImmobile.getSuffisso());
		ubicazione.setCap(datiImmobile.getCap());
		return ubicazione;
	}

	/**
	 * @param userPreferences
	 * @param datiImmobile
	 * @return
	 */

	private it.osapulie.tributi.web.ws.input.types.Indirizzo getIndirizzoResidenzaFromDatiDichiarazione(DatiDichiarazioneTari datiDichiarazioneTari, UserPreferences userPreferences) {

		it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
		Codifica codiceVia = new Codifica();

		// Set codice catastale
		String comuneResidenza = datiDichiarazioneTari.getComuneResidenza();
		ubicazione.setCap(datiDichiarazioneTari.getCapResidenza());
		ubicazione.setComune(getCodificaComune(comuneResidenza));

		boolean isIndirizzoResidenzaConStradario = checkIndirizzoResidenzaConStradario(userPreferences, datiDichiarazioneTari);
		String indirizzoResidenza = datiDichiarazioneTari.getIndirizzoResidenza();
		if (isIndirizzoResidenzaConStradario) {
			civico.setCodice(datiDichiarazioneTari.getCivicoResidenza());
			civico.setNumero(Integer.parseInt(datiDichiarazioneTari.getCivicoResidenzaTextHidden()));
			if (indirizzoResidenza.contains("-")) {
				indirizzoResidenza = indirizzoResidenza.split("-")[0];
			}
			codiceVia.setCodice(indirizzoResidenza);
			codiceVia.setDescrizione(datiDichiarazioneTari.getIndirizzoResidenzaTextHidden());
		}
		else {
			civico.setNumero(Integer.parseInt(datiDichiarazioneTari.getCivicoResidenza()));
			codiceVia.setDescrizione(indirizzoResidenza);
		}

		civico.setEsponente(datiDichiarazioneTari.getEsponenteResidenza());
		ubicazione.setCivico(civico);

		ubicazione.setVia(codiceVia);

		return ubicazione;
	}

	/**
	 * @param datiImmobile
	 * @return
	 */

	private it.osapulie.tributi.web.ws.input.types.Indirizzo getIndirizzoSedeLegaleFromDatiDichiarazione(DatiDichiarazioneTari datiDichiarazioneTari) {

		it.osapulie.tributi.web.ws.input.types.Indirizzo ubicazione = new it.osapulie.tributi.web.ws.input.types.Indirizzo();
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();

		String comuneSedeLegale = datiDichiarazioneTari.getComuneSedeLegale();
		ubicazione.setCap(datiDichiarazioneTari.getCapSedeLegale());
		ubicazione.setComune(getCodificaComune(comuneSedeLegale));

		if (datiDichiarazioneTari.getNumeroSedeLegale() != null && !datiDichiarazioneTari.getNumeroSedeLegale().isEmpty()) {
			civico.setNumero(Integer.parseInt(datiDichiarazioneTari.getNumeroSedeLegale()));
		}
		civico.setEsponente(datiDichiarazioneTari.getEspSedeLegale());
		ubicazione.setCivico(civico);

		Codifica codiceVia = new Codifica();
		codiceVia.setDescrizione(datiDichiarazioneTari.getViaSedeLegale());
		ubicazione.setVia(codiceVia);

		ubicazione.setPiano(datiDichiarazioneTari.getpSedeLegale());
		ubicazione.setInterno(datiDichiarazioneTari.getIntSedeLegale());
		ubicazione.setScala(datiDichiarazioneTari.getScSedeLegale());
		return ubicazione;
	}

	private BigDecimal getBigDecimal(String valore) {
		try {
			Double.parseDouble(valore);// se è un double, si può convertire in bigdecimal
			return new BigDecimal(valore);
		}
		catch (NumberFormatException e) { // altrimenti, provo a convertirlo
			NumberFormat nf = NumberFormat.getInstance();
			try {
				valore = nf.parse(valore).toString();
				return new BigDecimal(valore);
			}
			catch (ParseException pe) {
				pe.printStackTrace();
				return null;
			}
		}
	}

	private String determinaTipoDichiarazione(String type) {
		if(type==null)
			return "";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_CESSAZIONE))
			return "TIPO_DICHIARAZIONE_CESSAZIONE";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_ISCRIZIONE))
			return "TIPO_DICHIARAZIONE_ISCRIZIONE";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_TRASFERIMENTO))
			return "TIPO_DICHIARAZIONE_TRASFERIMENTO";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_VARIAZIONE))
			return "TIPO_DICHIARAZIONE_VARIAZIONE";
	 
		return "";
	}
	
}
