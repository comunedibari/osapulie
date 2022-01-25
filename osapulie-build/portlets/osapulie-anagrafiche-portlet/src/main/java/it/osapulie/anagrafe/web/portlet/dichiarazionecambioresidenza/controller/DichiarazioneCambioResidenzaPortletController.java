/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
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
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AnagrafeService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form.DatiDichiarazioneCambioResidenza;
import it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form.Veicolo;
import it.osapulie.util.DwhDataminingUtil;
import it.osapulie.util.DwhServiceAttributeUtil;
import it.osapulie.util.DwhTempiMediUtil;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.varie.form.Componente;
import it.osapulie.anagrafe.web.ws.input.types.Codifica;
import it.osapulie.anagrafe.web.ws.input.types.ContrattoLocazione;
import it.osapulie.anagrafe.web.ws.input.types.DatiCatastali;
import it.osapulie.anagrafe.web.ws.input.types.Dichiarante;
import it.osapulie.anagrafe.web.ws.input.types.Dichiarazione;
import it.osapulie.anagrafe.web.ws.input.types.Dichiarazione.ResidenteIscritto;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRichiesta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta;
import it.osapulie.anagrafe.web.ws.input.types.DichiarazioneCambioResidenzaRisposta.Errore;
import it.osapulie.anagrafe.web.ws.input.types.Documento;
import it.osapulie.anagrafe.web.ws.input.types.Immobile;
import it.osapulie.anagrafe.web.ws.input.types.Indirizzo;
import it.osapulie.anagrafe.web.ws.input.types.Indirizzo.Civico;
import it.osapulie.anagrafe.web.ws.input.types.Iscrizione;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneAltroMotivo;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneDaStatoEstero;
import it.osapulie.anagrafe.web.ws.input.types.IscrizioneDaStatoEsteroAIRE;
import it.osapulie.anagrafe.web.ws.input.types.Patente;
import it.osapulie.anagrafe.web.ws.input.types.PermessoSoggiorno;
import it.osapulie.anagrafe.web.ws.input.types.Recapito;
import it.osapulie.anagrafe.web.ws.input.types.TitoloOccupazioneImmobile;
import it.osapulie.anagrafe.web.ws.input.types.TitoloOccupazioneImmobile.ContrattoLocazioneComodatario;
import it.osapulie.anagrafe.web.ws.input.types.Trasferimento;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Backup;
import it.osapulie.domain.BozzaDocumenti;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Delega;
import it.osapulie.domain.DwhDatamining;
import it.osapulie.domain.DwhGeolocalizzazione;
import it.osapulie.domain.DwhTempiMedi;
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
import it.osapulie.servizi.web.ws.types.IndirizzoAscot;
import it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioAscotRisposta;
import it.osapulie.shared.service.UserPreferences;
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
import it.osapulie.web.portlet.util.VocabolarioComparator;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Dichiarazione cambio di Residenza/Iscrizione anagrafica.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 * @author Antonio Magrì
 */
@Controller("dichiarazioneCambioResidenzaPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiDichiarazione", "param", "uploadItem" })
public class DichiarazioneCambioResidenzaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneCambioResidenzaPortletController.class);

	private static final int NUMERO_VEICOLI_FORM = 4;

	public static final String TITOLO_ABITATIVO_PROPRIETARIO = "A";
	public static final String TITOLO_ABITATIVO_INTESTATARIO_CONTRATTO = "B";
	public static final String TITOLO_ABITATIVO_CONTRATTO_EDILIZIA_PUBBLICA = "C";
	public static final String TITOLO_ABITATIVO_CONTRATTO_COMODATO_GRATUITO = "D";
	public static final String TITOLO_ABITATIVO_USUFRUTTUARIO = "E";
	public static final String TITOLO_ABITATIVO_ALTRO = "F";

	public static final String TIPO_DICHIARAZIONE_ALTRO = "altro";
	public static final String TIPO_DICHIARAZIONE_STESSO_COMUNE = "stessoComune";
	public static final String TIPO_DICHIARAZIONE_AIRE = "aire";
	public static final String TIPO_DICHIARAZIONE_ESTERO = "estero";
	public static final String TIPO_DICHIARAZIONE_ALTRO_COMUNE = "altroComune";

	public static final String SERVIZIO = "DICHIARAZIONE CAMBIO RESIDENZA";

	private static final String REPORT_PATH = "/reports/dichiarazioneCambioResidenza.jrxml";
	private static final String SUB_REPORT_PATH = "/reports/dichiarazioneCambioResidenza_subreport1.jrxml";

	private static final String JSP_PATH = "dichiarazionecambioresidenza/";

	private static final String REPORT_PREFIX_NAME = "Dichiarazione_cambio_residenza";
	private static final String REPORT_SUFFIX_NAME = ".pdf";
	private static final String FORMATT_DATAORA_INIZIO_OPERAZIONE="dd-MM-yyyy HH:mm:ss.SSS";
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
	private VisureService visureService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Inject
	private AnagrafeService anagrafeService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiDichiarazioneCambioResidenzaValidator")
	private Validator dichiarazioneValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProvinciaService provinciaService;

	@Inject
	private StatoEsteroService statoEsteroService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private CommonService commonService;

	@Inject
	private ConfigurazioneService configurazioneService;
	
	@Inject
	private AuditRepository auditRepository;

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
	public String homePage(Model model, PortletSession session, PortletRequest request) throws Exception {
		log.debug("homePage-dichiarazioneCambioResidenzaPortletController");
		AuditManager auditManager= AuditConfiguration.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
			auditManager.build();
		return toLocalViewPath("home");
	}

	@ModelAttribute("bozza")
	public DatiDichiarazioneCambioResidenza getBozza(PortletRequest request, PortletSession session) {

		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA - GET BOZZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));
		UserPreferences userPreferences = helper.getUserPreferences(request);
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
		DatiDichiarazioneCambioResidenza bozza = commonService.getBozza(userPreferences, servizio.getId(), DatiDichiarazioneCambioResidenza.class);

		if (bozza != null) {
			List<Veicolo> veicoli = bozza.getVeicoli();
			if (veicoli == null || veicoli.size() == 0) {
				bozza.setVeicoli(getVeicoli());
			}
		}
		if (bozza != null) {
		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getFirmaGrafometrica()) {
			//TODO
			//LUTENTE HA FIRMATO CON FIRMA GRAFOMETRICA!!!
			bozza.setFirmaGrafometrica(true);	
		}
		if(delega!=null && delega.getDelegante()!=null) {
			bozza.setCodDelegante(""+delega.getDelegante().getId());
		auditManager.addDelegante(""+delega.getDelegante().getId());
		}
		}
		}
		
		setInfoAggiuntiveForm(bozza);
		auditManager.build();
		return bozza;
	}

	@ModelAttribute("numeroMaxVeicoliForm")
	public int getNumeroMaxVeicoliForm() {
		return NUMERO_VEICOLI_FORM;
	}

	@ModelAttribute("isDichiarazioneTariOk")
	public boolean isDichiarazioneTariOk(PortletRequest request, Model model) {
		boolean checkDichiarazioneTariOk = checkDichiarazioneTariOk(request);
		if (!checkDichiarazioneTariOk) {
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI);
			model.addAttribute("dichiarazioneTariUrl", servizio.getUri());
		}
		return checkDichiarazioneTariOk;
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
		boolean interoperabilitaEnable = configurazioneService.isInteroperabilitaEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA, userPreferences.getIdComuneIsa());
		return interoperabilitaEnable;
	}

	/**
	 * ELIMINARE! SOLO X BARI Setta la proprietà utile a capire se il servizio è destinato a
	 * funzionare mediante interoperabilità con il backoffice comunale.
	 *
	 * @param request
	 * @return
	 */
	@ModelAttribute("isComuneBari")
	public boolean isComuneBari(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		return userPreferences.getIdComuneIsa() == 1;
	}

	/**
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "action=redirectSegnalazioneCustom")
	public void redirectSegnalazioneCustom(ActionRequest request, ActionResponse response, PortletSession session) throws Exception {

		UserPreferences userPreferences = helper.getUserPreferences(request);
		ProfiloUtenteCittadino profiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);

		// Aggiunta URL portlet segnalazioni
		String codiceServizio = PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI;
		Map<String, String> successReturnRequestParameters = new HashMap<String, String>();
		successReturnRequestParameters.put("action", "getDichiarazioneCambioResidenzaForm");

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

	@ActionMapping(params = "action=getBozzaFrom")
	public void getBozzaFrom(@ModelAttribute("bozza") DatiDichiarazioneCambioResidenza bozza, @RequestParam(required = false) String message, Model model, ActionResponse response,
							PortletSession session,	ActionRequest request, PortletRequest portletRequest) throws Exception {
		//MS
	 
		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidStr)
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		
		
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

	 
		if (message != null) {
			model.addAttribute("message", message);
		}

		if(bozza.getUuidOperazione()==null || bozza.getUuidOperazione().isEmpty()) {
			bozza.setUuidOperazione(uuidStr);
		}
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		ProfiloUtenteCittadino profiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		//userPreferences.getIdDelega();
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getFirmaGrafometrica()) {
			//TODO
			//LUTENTE HA FIRMATO CON FIRMA GRAFOMETRICA!!!
			bozza.setFirmaGrafometrica(true);	
		}
		if(delega!=null && delega.getDelegante()!=null) {
			bozza.setCodDelegante(""+delega.getDelegante().getId());
		    auditManager.addDelegante(""+delega.getDelegante().getId());
		}
		}
		
		model.addAttribute("datiDichiarazione", bozza);
		
		// Verifica l'abilitazione del cambio di residenza (FIXME SOLO PER IL COMUNE DI BARI che
		// richiede l'identificativo famiglia)
		boolean abilitaCambioAbitazione = abilitaCambioAbitazione(bozza, userPreferences, model);
		bozza.setAbilitaCambioAbitazione(abilitaCambioAbitazione);
		

		auditManager.build();
		
		
		DwhServizioAttributeDTO dwhServizioAttributeDTO = new DwhServizioAttributeDTO();
		//MS
		DwhServiceAttributeUtil serviceAttributeUtil=DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService);
		dwhServizioAttributeDTO = serviceAttributeUtil
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
		.setUUID(bozza.getUuidOperazione())
		.getServizioAttribute();
		
		auditDwhService.invokeAuditService(dwhServizioAttributeDTO,null, null);
		
		
		response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
	}
	
	
	/**
	 * Presenta la form per la dichiarazione di cambio di residenza
	 *
	 * @param model
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getDichiarazioneCambioResidenzaForm")
	public void getIscrizioneTemporaneaForm(Model model, ActionRequest request, ActionResponse response,PortletSession session, PortletRequest portletRequest) throws Exception {
		
		log.debug("entering method");
		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidStr)
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
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
		
		// Verifica esistenza servizio dichiarazione TARI attivo & verifica se l'utente ha
		// effettuato la dichiarazione TARI/esiste bozza --> altrimenti invio segnalazione
		boolean isDichiarazioneTariOk = checkDichiarazioneTariOk(request);

		if (!isDichiarazioneTariOk) {
			// Redirect homepage
			return;
		}
		 
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		try {
			log.info("CODICE FISCALE PRELEVATO DA USERPREFERENCES [{}]",codiceFiscale);
			
			DwhServizioAttributeDTO dwhServizioAttributeDTO = new DwhServizioAttributeDTO();
			DwhDataminingDTO dwhDataminingDTO = new DwhDataminingDTO();
			DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
			//MS
			dwhServizioAttributeDTO = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService)
			.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
			.setUUID(uuidStr)
			.getServizioAttribute();
			
			
			//MS
			dwhDataminingDTO = DwhDataminingUtil.get(codiceFiscale, dwhService)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
			.setUuidOperazione(uuidStr)
			.getDatamining();
			
			dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
					.setNomeServizio(SERVIZIO)
					.setUuid(uuidStr).go_StartTime().getTempiMedi();
					 
					auditDwhService.invokeAuditService(dwhServizioAttributeDTO, dwhDataminingDTO, dwhTempiMediDTO);

			
		}catch (Exception e) {
			log.error(e.getMessage());
		}
		
		DatiDichiarazioneCambioResidenza dati = new DatiDichiarazioneCambioResidenza();
		//MS INIZIOOPERAZIONE
		dati.setInizioOperazione(new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).format(new Date()));
		dati.setUuidOperazione(uuidStr);
		dati.setIpAddress(AuditCustomMeta.get().getOrigin());
		
 
		
		//UserPreferences userPreferences = helper.getUserPreferences(request);
		//String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

		
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);
		DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
		richiestaGen.setCodiceFiscale(codiceFiscale);
		auditManager.addMetaField("UserPreferences", userPreferences)
		.addMetaField("RichiestaDatiAnagrafici", richiesta);
		DatiAnagrafici datiAnagrafci = null;
		DatiUtente datiUtente = null;
		ComponentiNucleoFamiliare componente = null;
		List<ComponentiNucleoFamiliare> altriComponenti = null;
		try {
			datiAnagrafci = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));
			datiUtente = visureService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));

			componente = null;
			altriComponenti = new ArrayList<DatiAnagrafici.ComponentiNucleoFamiliare>();
			for (int i = 0; i < datiAnagrafci.getComponentiNucleoFamiliare().size(); i++) {
				if (codiceFiscale.equals(datiAnagrafci.getComponentiNucleoFamiliare().get(i).getCodiceFiscale())) {
					componente = datiAnagrafci.getComponentiNucleoFamiliare().get(i);
				}
			}
			for (int i = 0; i < datiAnagrafci.getComponentiNucleoFamiliare().size(); i++) {
				if (!codiceFiscale.equals(datiAnagrafci.getComponentiNucleoFamiliare().get(i).getCodiceFiscale())) {
					altriComponenti.add(datiAnagrafci.getComponentiNucleoFamiliare().get(i));
				}
			}
		}
		catch (Exception e) {
			log.warn("getIscrizioneTemporaneaForm :: " + e.getMessage());
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
		}

		boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA, userPreferences.getIdComuneIsa());
		boolean isInteroperabilitaEnable = isInteroperabilitaEnable(request);

		// Precarico i dati dell'utente nel form
		String codiceIstatComune = userPreferences.getCodiceIstatComune();
		Comune comuneAttuale = comuneService.getComuneByCodiceISTAT(codiceIstatComune);
		if (componente != null && datiAnagrafci != null && datiUtente != null) {
			// Comune di iscrizione è il comune ISA attuale (se non specificato dopo)
			dati.setComuneResidenza(comuneAttuale.getCodiceIstatAN());
			dati.setProvinciaResidenza(comuneAttuale.getProvincia() != null ? comuneAttuale.getProvincia().getSigla() : null);

			dati.setIdentificativoUtente(componente.getIdentificativo());
			dati.setIdentificativoFamiglia(String.valueOf(componente.getIdentificativoNucleoFamiliare()));
			dati.setNome(componente.getNome());
			dati.setCognome(componente.getCognome());
			dati.setSesso(componente.getSesso());
			dati.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
			dati.setCodiceFiscale(codiceFiscale);

			// Caricamento comune da codice ISTAT
			if (componente.getStatoEsteroNascita() == null && componente.getCodiceIstatComuneNascita() != null) {
				Comune comuneNascita = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
				if (comuneNascita != null) {
					dati.setComuneNascita(comuneNascita.getCodiceIstatAN());
					dati.setComuneNascitaHidden(comuneNascita.getDenominazione());
					dati.setProvinciaNascita(comuneNascita.getProvincia().getSigla());
				}
			}
			else {
				// comune estero
				if (componente.getCodiceIstatComuneNascita() != null) {
					ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componente.getCodiceIstatComuneNascita()));
					if (comuneEstero != null) {
						dati.setComuneNascitaEstero(comuneEstero.getCodice().toString());
						dati.setComuneNascitaEsteroHidden(comuneEstero.getDenominazione());
					}
				}
				// da visura ritorna una descrizione --> ricerca
				List<StatoEstero> searchStati = statoEsteroService.searchStati(componente.getStatoEsteroNascita());
				if (searchStati != null && !searchStati.isEmpty()) {
					StatoEstero statoEstero = searchStati.get(0);
					dati.setStatoEsteroNascita(statoEstero.getCodiceStato().toString());
					dati.setStatoEsteroNascitaHidden(statoEstero.getDenominazione());
				}
			}

			// da visura ritorna una descrizione --> ricerca per similitudine
			Map<String, String> vocabolarioStatiCivili = getVocabolarioStatiCivili();
			for (Map.Entry<String, String> entry : vocabolarioStatiCivili.entrySet()) {
				if (entry.getValue().toLowerCase().contains(componente.getStatoCivile().toLowerCase())) {
					dati.setStatoCivile(entry.getKey());
					break;
				}
			}

			if (componente.isCittadinanzaItaliana()) {
				dati.setCittadinanza("100");
			}

			dati.setIndirizzoResidenza(datiAnagrafci.getDescrizioneVia());
			dati.setCivicoResidenza(datiAnagrafci.getNumeroCivico());
			dati.setCivicoTextHidden(datiAnagrafci.getNumeroCivico());
			dati.setEsponenteResidenza(datiAnagrafci.getEsponente());
			dati.setInternoResidenza(datiAnagrafci.getInterno());
			dati.setPianoResidenza(datiAnagrafci.getPiano());
			dati.setScalaResidenza(datiAnagrafci.getScala());
		}
		else {
			ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
			dati.setNome(profiloUtente.getNome());
			dati.setCognome(profiloUtente.getCognome());
			dati.setCodiceFiscale(codiceFiscale);

			it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
			if (residenza != null) {
				if (isStradarioEnabled) {
					dati.setViaTextHidden(residenza.getVia());
					dati.setCivicoTextHidden(null);
				}
				else {
					dati.setIndirizzoResidenza(residenza.getVia());
				}
				Comune comune = residenza.getComune();
				if (comune != null && !comune.getId().equals(-1L)) {
					dati.setComuneResidenza(comune.getCodiceIstatAN());
					Provincia provincia = comune.getProvincia();
					if (provincia != null) {
						dati.setProvinciaResidenza(provincia.getSigla());
					}
				}
			}
		}

		dati.setNumeroParenti(0);

		// Altri componenti
		if (altriComponenti != null) {
			List<Componente> familiari = new ArrayList<Componente>();
			for (ComponentiNucleoFamiliare altroCompoenenteNucleoFamiliare : altriComponenti) {
				Componente familiare = getComponente(altroCompoenenteNucleoFamiliare);
				familiari.add(familiare);
			}
			dati.setFamiliari(familiari);
			dati.setNumeroParenti(familiari.size());
		}

		// Verifica l'abilitazione del cambio di residenza (FIXME SOLO PER IL COMUNE DI BARI che
		// richiede l'identificativo famiglia)
		boolean abilitaCambioAbitazione = abilitaCambioAbitazione(dati, userPreferences, model);
		dati.setAbilitaCambioAbitazione(abilitaCambioAbitazione);

		// Verifica l'abilitazione dello stradario per l'indirizzo di residenza
		boolean stardarioResidenzaEnable = isStradarioEnabled && !isInteroperabilitaEnable && isCittadinoResidente(userPreferences, dati);
		dati.setStardarioResidenzaEnable(stardarioResidenzaEnable);

		// Inizializzazione veicoli
		List<Veicolo> veicoli = getVeicoli();
		dati.setVeicoli(veicoli);

		
		 
		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getFirmaGrafometrica()) {
			//TODO
			//LUTENTE HA FIRMATO CON FIRMA GRAFOMETRICA!!!
			dati.setFirmaGrafometrica(true);	
		}
		if(delega!=null && delega.getDelegante()!=null) {
		dati.setCodDelegante(""+delega.getDelegante().getId());
		auditManager.addDelegante(""+delega.getDelegante().getId());
		}
		}
		
		auditManager
		.addServizioTimeString()
		.addServizioUuidTransazione(dati.getUuidOperazione())
		.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
		.addCallGeo();
		
		setInfoAggiuntiveForm(dati);

		model.addAttribute("datiDichiarazione", dati);
		auditManager.build();
		
		response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
	}

	/**
	 *
	 * Verifica che l'utente attuale (in OR): <ul> <li>abbia già effettuato la dichiarazione
	 * TARI</li> <li>abbia già effettuato un invio di segnalazione di omessa dichiarazione TARI</li>
	 * <li>abbia una bozza di dichiarazione cambio residenza attiva</li> </ul>
	 *
	 * @param request
	 * @return
	 */
	private boolean checkDichiarazioneTariOk(PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);
		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
		// Verifica servizio attivo per il comune ISA
		ComuneISA comuneISA = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
		List<ComuneISAServizio> servizi = comuneISA.getServizi();
		if (servizi != null) {
			for (ComuneISAServizio comuneISAServizio : servizi) {
				if (comuneISAServizio.getServizio().getCodiceServizio().equals(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI)) {
					if (!comuneISAServizio.isAttivo() || !comuneISAServizio.getServizio().isAttivo()) {
						return true;
					}
				}
			}
		}

		// Ricerca effettuazione dichiarazione TARI in sessione
		PortletSession portletSession = request.getPortletSession();
		boolean isDichiarazioneOk = false;
		String dichiarazioneTariSessionAttribute = PortletConstants.DICHIARAZIONE_TARI_SHARED_ATTRIBUTE + "_" + currentProfiloUtenteCittadino.getCodiceFiscale();
		if (portletSession.getAttribute(dichiarazioneTariSessionAttribute, PortletSession.APPLICATION_SCOPE) != null) {
			isDichiarazioneOk = (Boolean) portletSession.getAttribute(dichiarazioneTariSessionAttribute, PortletSession.APPLICATION_SCOPE);
			if (isDichiarazioneOk) {
				return true;
			}
		}

		// Ricerca effettuazione invio segnalazione
		boolean isSegnalazioneOk = false;
		String segnalazioneCustomSessionAttribute = PortletConstants.SEGNALAZIONE_SERVIZIO_SHARED_ATTRIBUTE + PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI + "_"
				+ currentProfiloUtenteCittadino.getCodiceFiscale();
		if (portletSession.getAttribute(segnalazioneCustomSessionAttribute, PortletSession.APPLICATION_SCOPE) != null) {
			isSegnalazioneOk = (Boolean) portletSession.getAttribute(segnalazioneCustomSessionAttribute, PortletSession.APPLICATION_SCOPE);
			if (isSegnalazioneOk) {
				return true;
			}
		}

		// Ricerca bozza
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
		DatiDichiarazioneCambioResidenza bozza = commonService.getBozza(userPreferences, servizio.getId(), DatiDichiarazioneCambioResidenza.class);
		if (bozza != null) {
			return true;
		}

		return false;
	}

	/**
	 * Carica il componente familiare a partire dalla visura BE.
	 *
	 * @param altroCompoenenteNucleoFamiliare
	 * @return
	 */
	private Componente getComponente(ComponentiNucleoFamiliare altroCompoenenteNucleoFamiliare) {
		Componente familiare = new Componente();
		if (altroCompoenenteNucleoFamiliare.isCittadinanzaItaliana()) {
			familiare.setCittadinanza("100");
		}
		familiare.setCodiceFiscale(altroCompoenenteNucleoFamiliare.getCodiceFiscale());
		familiare.setCognome(altroCompoenenteNucleoFamiliare.getCognome());
		if (altroCompoenenteNucleoFamiliare.getCodiceIstatComuneNascita() != null) {
			Comune comune = comuneService.getComuneByCodiceISTAT(altroCompoenenteNucleoFamiliare.getCodiceIstatComuneNascita());
			if (comune != null) {
				familiare.setComuneNascita(comune.getCodiceIstatAN());
				familiare.setComuneNascitaHidden(comune.getDenominazione());
				if (comune.getProvincia() != null) {
					familiare.setProvinciaNascita(comune.getProvincia().getSigla());
				}
			}
		}
		if (altroCompoenenteNucleoFamiliare.getDataNascita() != null) {
			familiare.setDataNascitaString(simpleDateFormat.format(altroCompoenenteNucleoFamiliare.getDataNascita().getTime()));

		}
		familiare.setIdentificativoUtente(altroCompoenenteNucleoFamiliare.getIdentificativo());
		familiare.setNome(altroCompoenenteNucleoFamiliare.getNome());
		// FIXME Ricerca in base alla descrizione
		String relazioneParentela = altroCompoenenteNucleoFamiliare.getRelazioneParentela();
		if (relazioneParentela != null && !relazioneParentela.isEmpty()) {
			Map<String, String> vocabolario = getVocabolarioRelazioniDiParentela();
			String vocabolarioKey = getKeyFromMap(vocabolario, relazioneParentela.substring(0, 5));
			if (vocabolarioKey != null) {
				familiare.setParentela(vocabolarioKey);
				familiare.setParentelaHidden(vocabolario.get(vocabolarioKey));
			}
		}
		familiare.setSesso(altroCompoenenteNucleoFamiliare.getSesso());
		// FIXME Ricerca in base alla descrizione
		String statoCivile = altroCompoenenteNucleoFamiliare.getStatoCivile();
		if (statoCivile != null) {
			Map<String, String> vocabolario = getVocabolarioStatiCivili();
			String vocabolarioKey = getKeyFromMap(vocabolario, statoCivile.substring(0, 5));
			if (vocabolarioKey != null) {
				familiare.setStatoCivile(vocabolarioKey);
				familiare.setStatoCivileHidden(vocabolario.get(vocabolarioKey));
			}
		}
		// FIXME Ricerca in base alla descrizione
		String statoEsteroNascita = altroCompoenenteNucleoFamiliare.getStatoEsteroNascita();
		if (statoEsteroNascita != null) {
			// Setto il comune estero di nascita
			if (altroCompoenenteNucleoFamiliare.getCodiceIstatComuneNascita() != null) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(altroCompoenenteNucleoFamiliare.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					familiare.setComuneNascitaEstero(comuneEsteroByCodice.getCodice().toString());
					familiare.setComuneNascitaEsteroHidden(comuneEsteroByCodice.getDenominazione());
				}
			}
			List<StatoEstero> searchStati = statoEsteroService.searchStati(statoEsteroNascita);
			if (searchStati != null && !searchStati.isEmpty()) {
				StatoEstero statoEstero = searchStati.get(0);
				familiare.setStatoEsteroNascita(statoEstero.getCodiceStato().toString());
				familiare.setStatoEsteroNascitaHidden(statoEstero.getDenominazione());
			}
		}
		// FIXME Ricerca in base al codice!!! PERCHEEEEE!!!!
		// String titoloStudio = altroCompoenenteNucleoFamiliare.getTitoloStudio();
		// if (titoloStudio != null) {
		// Map<String, String> vocabolario = getVocabolarioTitoliDiStudio();
		// if (vocabolario.containsKey(titoloStudio)) {
		// familiare.setTitoloStudio(titoloStudio);
		// familiare.setTitoloStudioHidden(vocabolario.get(titoloStudio));
		// }
		// }
		return familiare;
	}

	/**
	 * Ritorna il value della mappa in base al valore passato.
	 *
	 * @param vocabolarioRelazioniDiParentela
	 * @param substring
	 * @return
	 */
	private String getKeyFromMap(Map<String, String> vocabolarioRelazioniDiParentela, String substring) {
		if (vocabolarioRelazioniDiParentela != null) {
			for (Map.Entry<String, String> entry : vocabolarioRelazioniDiParentela.entrySet()) {
				if (entry.getValue().toLowerCase().contains(substring.toLowerCase())) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

	/**
	 * Verifica se il cittadino è residente o meno nel comune in cui sta effettuando la
	 * dichiarazione (sulla base dei dati anagrafici precaricati dal BE o dal sistema).
	 *
	 * @param userPreferences
	 * @param dati
	 * @return
	 */
	private boolean isCittadinoResidente(UserPreferences userPreferences, DatiDichiarazioneCambioResidenza dati) {

		boolean result = false;

		String codiceIstatComuneResidenza = dati.getComuneResidenza();
		Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(codiceIstatComuneResidenza);
		if (comuneResidenza != null) {
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			ComuneISA comuneISA = comuneISAService.getComuneByPk(idComuneIsa);
			if (comuneISA != null && comuneISA.getComune() != null) {
				result = comuneResidenza.getId().equals(comuneISA.getComune().getId());
			}
		}

		return result;
	}

	/**
	 * FIXME SOLO PER IL COMUNE DI BARI Abilita la parte di form dedicata al cambio di abitazione.
	 *
	 * @param model
	 * @param userPreferences
	 * @param dati
	 */
	private boolean abilitaCambioAbitazione(DatiDichiarazioneCambioResidenza dati, UserPreferences userPreferences, Model model) {
		// Verifica abilitazione cambio abitazione:
		boolean cambioAbitazioneOn = false;
		if (userPreferences.getIdComuneIsa() == 1 && (isCittadinoResidente(userPreferences, dati) && dati.getIdentificativoFamiglia() != null && !dati.getIdentificativoFamiglia().isEmpty())) {
			cambioAbitazioneOn = true;

		}
		return cambioAbitazioneOn;
	}

	/**
	 * Ritorna la lista dei veicoli vuoti da mostrare in form.
	 *
	 * @return
	 */
	private List<Veicolo> getVeicoli() {
		List<Veicolo> veicoli = new ArrayList<Veicolo>();
		for (int i = 0; i < NUMERO_VEICOLI_FORM; i++) {
			Veicolo veicolo = new Veicolo();
			veicoli.add(veicolo);
		}
		return veicoli;
	}

	// FIXME per tutti i vocabolari andrebbe implementato il sistema di schedulazione notturna di
	// popolamento tb_vocabolario per ogni comune
	@ModelAttribute("listaStatiEsteri")
	public List<StatoEstero> getStatiEsteri() {
		return statoEsteroService.getAllStatiAndEscludi(100);
	}

	@ModelAttribute("vocabolarioAltriMotiviIscrizione")
	public Map<String, String> getVocabolarioAltriMotiviIscrizione() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("3", "Ricomparsa da irreperibilita’");
		vocabolario.put("6", "Ricomparsa per mancato rinnovo permesso di soggiorno");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioTipiVeicolo")
	public Map<String, String> getVocabolarioTipiVeicolo() {

		Map<String, String> vocabolario = new TreeMap<String, String>();
		vocabolario.put("A", "Autoveicolo");
		vocabolario.put("C", "Ciclomotore");
		vocabolario.put("M", "Motoveicolo");
		vocabolario.put("R", "Rimorchio");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioTipiPatente")
	public Map<String, String> getVocabolarioTipiPatente() {

		Map<String, String> vocabolario = new TreeMap<String, String>();
		vocabolario.put("A", "A");
		vocabolario.put("A1", "A1");
		vocabolario.put("A2", "A2");
		vocabolario.put("AM", "AM");
		vocabolario.put("AS", "AS");
		vocabolario.put("B", "B");
		vocabolario.put("B1", "B1");
		vocabolario.put("BE", "BE");
		vocabolario.put("BS", "BS");
		vocabolario.put("C", "C");
		vocabolario.put("C1", "C1");
		vocabolario.put("C1E", "C1E");
		vocabolario.put("CE", "CE");
		vocabolario.put("CS", "CS");
		vocabolario.put("D", "D");
		vocabolario.put("D1", "D1");
		vocabolario.put("D1E", "D1E");
		vocabolario.put("DE", "DE");
		vocabolario.put("DS", "DS");
		vocabolario.put("F", "F");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioEntiRilascioPatente")
	public Map<String, String> getVocabolarioEntiRilascioPatente() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("1", "Prefettura");
		vocabolario.put("2", "Motorizzazione");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioPosizioniProfessionali")
	public Map<String, String> getVocabolarioPosizioniProfessionali() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("0", "Condizione non professionale o att. disoccupato");
		vocabolario.put("1", "Imprenditore/Libero professionista");
		vocabolario.put("2", "Dirigente/Impiegato");
		vocabolario.put("3", "Lavoratore in proprio");
		vocabolario.put("4", "Operaio e assimilati");
		vocabolario.put("5", "Coadiuvante");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioCondizioniNonProfessionali")
	public Map<String, String> getVocabolarioCondizioniNonProfessionali() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("0", "Nessuna condizione");
		vocabolario.put("1", "Casalinga");
		vocabolario.put("2", "Studente");
		vocabolario.put("3", "Disoccupato/in cerca di prima occupazione");
		vocabolario.put("4", "Pensionato/Ritirato dal lavoro");
		vocabolario.put("5", "Altra condizione non professionale");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioStatiCivili")
	public Map<String, String> getVocabolarioStatiCivili() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("1", "Celibe/nubile");
		vocabolario.put("2", "Coniugato/coniugata");
		vocabolario.put("3", "Vedovo/vedova");
		vocabolario.put("4", "Divorziato/divorziata");
		vocabolario.put("9", "Ignoto");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioTitoliDiStudio")
	public Map<String, String> getVocabolarioTitoliDiStudio() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		vocabolario.put("1", "Nessun titolo");
		vocabolario.put("2", "Analfabeta");
		vocabolario.put("3", "Terza elementare");
		vocabolario.put("4", "Quinta elementare");
		vocabolario.put("5", "Licenza elementare");
		vocabolario.put("6", "Licenza avv.prof.");
		vocabolario.put("7", "Licenza media");
		vocabolario.put("8", "Diploma");
		vocabolario.put("9", "Laurea");

		return vocabolario;
	}

	@ModelAttribute("vocabolarioRelazioniDiParentela")
	public Map<String, String> getVocabolarioRelazioniDiParentela() {

		Map<String, String> vocabolario = new TreeMap<String, String>(new VocabolarioComparator());
		// vocabolario.put("1", "Intestatario scheda");
		vocabolario.put("2", "Marito/moglie");
		vocabolario.put("3", "Figlio/figlia");
		vocabolario.put("4", "Nipote (figlio/a di figlio/a)");
		vocabolario.put("5", "Pronipote");
		vocabolario.put("6", "Padre/madre");
		vocabolario.put("7", "Nonno/nonna");
		vocabolario.put("8", "Bisnonno/bisnonna");
		vocabolario.put("9", "Fratello/sorella");
		vocabolario.put("10", "Nipote (figlio/a di fratello/sorella)");
		vocabolario.put("11", "Zio/zia");
		vocabolario.put("12", "Cugino/cugina");
		vocabolario.put("13", "Altro/a parente (fino al 6^grado)");
		vocabolario.put("14", "Figliastro/figliastra");
		vocabolario.put("15", "Patrigno/matrigna");
		vocabolario.put("16", "Genero/nuora");
		vocabolario.put("17", "Suocero/suocera");
		vocabolario.put("18", "Cognato/cognata");
		vocabolario.put("19", "Fratellastro/sorellastra");
		vocabolario.put("20", "Nipote (figlio/a di cognato/a)");
		vocabolario.put("21", "Zio/zia (marito/moglie di zio/a)");
		vocabolario.put("22", "Altro/a affine (fino al 6^ grado)");
		vocabolario.put("23", "Convivente in famiglia");
		vocabolario.put("30", "Responsabile di convivenza");
		vocabolario.put("31", "Membro di convivenza");

		return vocabolario;
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 * @param portletRequest 
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 */
	@ActionMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response, ActionRequest request,PortletSession session, PortletRequest portletRequest) throws Exception {
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidStr)
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
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
		auditManager.addMetaField("uploadItem", item);
		model.addAttribute("uploadItem", item);
		if (!model.containsAttribute("datiDichiarazione")) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
			DatiDichiarazioneCambioResidenza dati = new DatiDichiarazioneCambioResidenza();
			dati.setNumeroParenti(0);
			dati.setCodiceFiscale(codiceFiscale);
			auditManager.addMetaField("DatiDichiarazioneCambioResidenza", dati);
			model.addAttribute("datiDichiarazione", dati);
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
	 * @param request
	 * @param model
	 * @param selectNumAllegati
	 * @param portletRequest 
	 * @throws PortletException
	 * @throws IOException
	 */
	
 
	
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@ModelAttribute("datiDichiarazione") DatiDichiarazioneCambioResidenza datiDichiarazione, @RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati,
			SessionStatus sessionStatus, PortletRequest portletRequest) throws Exception {
		log.debug("uploadFile");
		
		String tipoDichiarazioneUtene=" "+determinaTipoDichiarazione(datiDichiarazione.getTipoDichiarazione());
 
		
		 
		
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		UserPreferences userPreferences = helper.getUserPreferences(request);
		  
		ProfiloUtenteCittadino currentProfiloUtenteCittadino = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
		
		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		
		
		if (selectNumAllegati == null) {

			validator.validate(uploadItem, result, currentProfiloUtenteCittadino, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);

			List<MultipartFile> multipartFiles = uploadItem.getMultipartFiles();
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("selectNumAllegati", "");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));

				response.setRenderParameter("action", "renderUploadForm");
			}
			else {
					
				try {
					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(userPreferences);
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);
					
					 
				String numeroProtocollo = null;

					DichiarazioneCambioResidenzaRichiesta dichiarazioneCambioResidenzaRichiesta = getDichiarazioneCambioResidenzaInputRichiesta(datiDichiarazione, userPreferences);

					// Allego documenti
					List<Documento> documenti = dichiarazioneCambioResidenzaRichiesta.getDocumento();

					List<Documento> documentiFromUploadItem = getDocumentiFromUploadItem(uploadItem);
					documenti.addAll(documentiFromUploadItem);

					// Salvataggio backup locale per recupero causa eventuali errori
					Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
					Backup backup = commonService.saveBackup(anagrafeService.getDichiarazioneXml(dichiarazioneCambioResidenzaRichiesta), userPreferences, servizio.getId());

					boolean interoperabilitaEnable = isInteroperabilitaEnable(request);
					// Se è abilitata l'interoperabilità...
					if (interoperabilitaEnable) {
						DichiarazioneCambioResidenzaRisposta dichiarazioneCambioResidenzaRisposta = anagrafeService.inviaDichiarazione(dichiarazioneCambioResidenzaRichiesta, userPreferences);
						Errore errore = dichiarazioneCambioResidenzaRisposta.getErrore();
						if (errore != null && errore.getCodice() != ERROR_CODE_3) {
							throw new Exception("Codice di errore: " + errore.getCodice() + ", descrizione: " + errore.getDescrizione());
						}

						// Aggiunta info aggiuntive con ID pratica
						fascicolo.addInfoAggiuntive("ID Pratica", dichiarazioneCambioResidenzaRisposta.getIdPratica());
						Calendar dataInserimento = dichiarazioneCambioResidenzaRisposta.getDataInserimento();
						if (dataInserimento != null) {
							SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
							fascicolo.addInfoAggiuntive("Data Inserimento", simpleDateFormat.format(dataInserimento.getTime()));
						}
						numeroProtocollo = PortletUtils.getNumeroProtocollo(dichiarazioneCambioResidenzaRisposta.getNumeroProtocollo(),
								dichiarazioneCambioResidenzaRisposta.getDataProtocollo().getTime());
					}
					// ... altrimenti invio a PEC/Protocollo nel modo classico
					else {
						MultipartFile multipartFileForInterop = generateMultipartFileForInterop(dichiarazioneCambioResidenzaRichiesta);
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
						anagrafeCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
						AuditConfiguration
						.configure()
						.audit()
						.addUuidOperazione((String)session.getAttribute("UUID"))
						.addInizioOperazione()
						.addOperazioneRichiesta(SERVIZIO+tipoDichiarazioneUtene +" - UPLOAD DICHIARAZIONE - STEP FINALE - INVIO EMAIL AL CITTADINO")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
						.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
						.addFineOperazione()
						.addEsitoSuccess()
						.setOrigin(Origin.getIp(request))
						.addInfo("SUBJECT", subject)
						.build();
					}
					catch (Exception e) {
						
						AuditConfiguration
						.configure()
						.audit()
						.addUuidOperazione((String)session.getAttribute("UUID"))
						.addInizioOperazione()
						.addOperazioneRichiesta(SERVIZIO+tipoDichiarazioneUtene +" - UPLOAD DICHIARAZIONE - STEP FINALE - INVIO EMAIL AL CITTADINO")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
						.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
						.addFineOperazione()
						.setOrigin(Origin.getIp(request))
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
					}

					
					AuditManager auditManager= AuditConfiguration.configure().audit();
					auditManager.addUuidOperazione((String)session.getAttribute("UUID"))
					.addOperazioneRichiesta(SERVIZIO+ tipoDichiarazioneUtene +" - UPLOAD DICHIARAZIONE - STEP FINALE")
					.setOrigin(Origin.getIp(request))
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addFirmaGrafoAcquisita(datiDichiarazione.getFirmaGrafometricaBase64())
					.addDelegante(datiDichiarazione.getCodDelegante())
					.addMetaField("Fascicolo", fascicolo)
					.addMetaField("ProfiloUtenteCittadino", currentProfiloUtenteCittadino);
					
					
					//QUI SALVATORE!!!
					if(numeroProtocollo!=null)
					auditManager.addInfo("NumeroProtocollo",numeroProtocollo);	
					
					 	auditManager
						.addServizioTimeString()
						.addServizioUuidTransazione(datiDichiarazione.getUuidOperazione())
						.addServizioIdServizio(""+servizio.getId())
						.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
						.addCallGeo();
						
						 auditManager=setAuditAttributiServizio(datiDichiarazione, userPreferences, auditManager);

						 auditManager.addFineOperazione()
						 .addEsitoSuccess()
						 .build();
						 
					DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
						 //MS
					dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
					.go_EndTime(datiDichiarazione.getUuidOperazione())
					.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA)
					.setNomeServizio(SERVIZIO+" - "+determinaTipoDichiarazione(datiDichiarazione.getTipoDichiarazione()))
					.getTempiMedi();
					
					auditDwhService.invokeAuditService(null, null, dwhTempiMediDTO);
					
					   final String protocollo=numeroProtocollo;
					   final DatiDichiarazioneCambioResidenza dati=datiDichiarazione;
						 
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
								 
									return determinaTipoDichiarazione(dati.getTipoDichiarazione());
								}
								public Date updateServizioFine() {
								 
									return new Date();
								}
								public String searchUUID() {
								
									return dati.getUuidOperazione();
								}
							});
					
							// Eliminazione Bozza documenti
					commonService.deleteBozzaDocumenti(userPreferences, servizio.getId());
					
					//Eliminazione Bozza
					commonService.deleteBozza(userPreferences, servizio.getId()); 

					// Eliminazione Backup
					commonService.deleteBackup(backup.getId());

					// Pulizia attributi in portlet session
					session.removeAttribute(PortletConstants.DICHIARAZIONE_TARI_SHARED_ATTRIBUTE + "_" + currentProfiloUtenteCittadino.getCodiceFiscale(), PortletSession.APPLICATION_SCOPE);
					session.removeAttribute(
							PortletConstants.SEGNALAZIONE_SERVIZIO_SHARED_ATTRIBUTE + PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TARI + "_" + currentProfiloUtenteCittadino.getCodiceFiscale(),
							PortletSession.APPLICATION_SCOPE);
					sessionStatus.setComplete();
					
			 
					
					
					
					response.setRenderParameter("action", "renderEsitoUpload");
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
					model.addAttribute("formError", messageSource.getMessage("exception.unreachable.message.args", new String[] { e.getMessage() }, request.getLocale()));
					AuditManager auditManager= AuditConfiguration.configure().audit();
					auditManager
					.addFineOperazione()
					.addEsitoError()
					.addInfo("Exception", e.getMessage())
					.setOrigin(Origin.getIp(request))
					.build();
					response.setRenderParameter("action", "renderUploadForm");
				}
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

	private DatiDichiarazioneCambioResidenza cleanMultiparts(DatiDichiarazioneCambioResidenza datiDichiarazione) {
        datiDichiarazione.setGeneratedFile(null);
        datiDichiarazione.setFileAmpliamentoNucleoFamiliare(null);
        datiDichiarazione.setFileAssensoProprietario(null);
        datiDichiarazione.setFileExtra(null);
        datiDichiarazione.setFileIntestatarioEdiResPub(null);
        datiDichiarazione.setFileIntra(null);
        
        datiDichiarazione.setFileUsufruttuario(null);
        datiDichiarazione.setFirmaGrafometricaBase64(null);
		return datiDichiarazione;
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
	public void generaDichiarazione(@RequestParam(required = false) String bozza, @ModelAttribute("datiDichiarazione") DatiDichiarazioneCambioResidenza datiDichiarazione,  BindingResult result,
			Model model, ActionResponse response, ActionRequest request, @RequestParam(required = false, value = "selectNumParenti") String selectNumParenti,PortletSession session, PortletRequest portletRequest) throws Exception {
		log.info("compilaDichiarazione CF=" + datiDichiarazione.getCodiceFiscale());
		//new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).format(new Date());//, @RequestParam(required = false) String inviaallegato
		//MS firmaGrafometrica
		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		String uuidAudit=(String)session.getAttribute("UUID");
		if(uuidAudit==null) {
			uuidAudit=UUID.randomUUID().toString();
			session.setAttribute("UUID", uuidAudit);
		}
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione(uuidAudit)
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		
		UserPreferences userPreferences = helper.getUserPreferences(request);

		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getFirmaGrafometrica()) {
			//TODO
			//LUTENTE HA FIRMATO CON FIRMA GRAFOMETRICA!!!
			datiDichiarazione.setFirmaGrafometrica(true);	
		}
		if(delega!=null && delega.getDelegante()!=null) {
		datiDichiarazione.setCodDelegante(""+delega.getDelegante().getId());
		auditManager.addDelegante(""+delega.getDelegante().getId());
		}
		}
		
		if(datiDichiarazione.getInizioOperazione()==null || datiDichiarazione.getInizioOperazione().isEmpty()) {
		datiDichiarazione.setInizioOperazione(new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).format(new Date()));	
		}
		
		if (datiDichiarazione.getHiddenLoadFileClick().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileClick("N");
			//silvio
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			try {
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getGeneratedFile().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getGeneratedFile().getOriginalFilename());//silvio
	            datiDichiarazione = cleanMultiparts(datiDichiarazione);
				commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			} catch (Exception e) {
				throw new Exception("datiDichiarazione.getGeneratedFile():"+datiDichiarazione.getGeneratedFile());
			}
			//silvio
			setInfoAggiuntiveForm(datiDichiarazione);
			//model.addAttribute("message", messageSource.getMessage("label.bozza.salvata", null, request.getLocale()));
			model.addAttribute("message", "Allegato caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileAmpliamentoNucleoFamiliare().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileAmpliamentoNucleoFamiliare("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato ampliamento nucleo familiare caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileAssensoProprietario().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileAssensoProprietario("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAssensoProprietario().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAssensoProprietario().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato assenso proprietario caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileExtra().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileExtra("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileExtra().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileExtra().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato per extracomunitario caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileIntra().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileIntra("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntra().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntra().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato per comunitario caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileIntestatarioEdiResPub().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileIntestatarioEdiResPub("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato intestatario edilizia residenziale pubbblica caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		if (datiDichiarazione.getHiddenLoadFileUsufruttuario().equals("S")) {//silvio
			datiDichiarazione.setHiddenLoadFileUsufruttuario("N");
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
			BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
            bozzaDocumenti.setAllegato(datiDichiarazione.getFileUsufruttuario().getBytes());
            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileUsufruttuario().getOriginalFilename());
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozzaDocumenti(datiDichiarazione, userPreferences, servizio.getId(), bozzaDocumenti);
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", "Allegato usufruttuario caricato!");
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}//silvio
		
		// Salvataggio bozza
		if (bozza != null) {
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);

			if (datiDichiarazione.getFileUsufruttuario()!=null 
					&& datiDichiarazione.getFileUsufruttuario().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileUsufruttuario().getOriginalFilename().trim().equals("")
					) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileUsufruttuario().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileUsufruttuario().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			if (datiDichiarazione.getFileIntestatarioEdiResPub()!=null 
					&& datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename().trim().equals("")) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			if (datiDichiarazione.getFileIntra()!=null 
					&& datiDichiarazione.getFileIntra().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileIntra().getOriginalFilename().trim().equals("")) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntra().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntra().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			if (datiDichiarazione.getFileExtra()!=null 
					&& datiDichiarazione.getFileExtra().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileExtra().getOriginalFilename().trim().equals("")) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileExtra().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileExtra().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			if (datiDichiarazione.getFileAssensoProprietario()!=null 
					&& datiDichiarazione.getFileAssensoProprietario().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileAssensoProprietario().getOriginalFilename().trim().equals("")) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAssensoProprietario().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAssensoProprietario().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			if (datiDichiarazione.getFileAmpliamentoNucleoFamiliare()!=null 
					&& datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename()!=null 
					&& !datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename().trim().equals("")) {//silvio
				BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
	            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getBytes());//silvio
	            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename());//silvio
				commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
			}//silvio
			
            datiDichiarazione = cleanMultiparts(datiDichiarazione);
			commonService.saveBozza(datiDichiarazione, userPreferences, servizio.getId());
			
			setInfoAggiuntiveForm(datiDichiarazione);
			model.addAttribute("message", messageSource.getMessage("label.bozza.salvata", null, request.getLocale()));
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			return;
		}

		boolean isInteroperabilitaEnable = isInteroperabilitaEnable(request);
		if (selectNumParenti == null) {
			// controllo/set indirizzi secondo stradario Ascot (Solo per BARI)
			boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA, userPreferences.getIdComuneIsa());
			String tipoDichiarazione = datiDichiarazione.getTipoDichiarazione();
			if (isInteroperabilitaEnable && isStradarioEnabled && userPreferences.getIdComuneIsa() == 1 && tipoDichiarazione != null && !tipoDichiarazione.isEmpty()) {
				StradarioAscotRichiesta richiesta = new StradarioAscotRichiesta();
				it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta.Indirizzo indirizzoAscotRichiesta = new it.osapulie.servizi.web.ws.types.StradarioAscotRichiesta.Indirizzo();
				String nuovoCodiceViaHidden = datiDichiarazione.getNuovoCodiceViaHidden();
				if (nuovoCodiceViaHidden != null && !nuovoCodiceViaHidden.isEmpty()) {
					indirizzoAscotRichiesta.setBarrato(datiDichiarazione.getNuovoEsp());
					// mapping frazioni
					String codiceLocalitaDaVocabolario = getVocabolarioFrazioniAscot().get(datiDichiarazione.getNuovaLocalitaHidden());
					indirizzoAscotRichiesta.setCodiceFrazione(codiceLocalitaDaVocabolario);
					indirizzoAscotRichiesta.setCodiceVia(Integer.parseInt(nuovoCodiceViaHidden));
					indirizzoAscotRichiesta.setCorte(datiDichiarazione.getNuovaCorte());
					if (datiDichiarazione.getNuovoNumTextHidden() != null && !datiDichiarazione.getNuovoNumTextHidden().isEmpty()) {
						indirizzoAscotRichiesta.setNumeroCivico(Integer.parseInt(datiDichiarazione.getNuovoNumTextHidden()));
					}
					richiesta.setIndirizzo(indirizzoAscotRichiesta);
					StradarioAscotRisposta rispostaStradarioAscot = null;
					try {
						rispostaStradarioAscot = anagrafeService.inviaRichiestaStradarioAscot(richiesta, userPreferences);
					}
					catch (Exception e) {
						log.error("generaDichiarazione :: " + e.getMessage(), e);
						// Stampo errore in pagina
						
						FieldError error = new FieldError("datiTari", "nuovaVia", e.getMessage());
						result.addError(error);
					}
					if (rispostaStradarioAscot != null) {
						it.osapulie.servizi.web.ws.types.StradarioAscotRisposta.Errore errore = rispostaStradarioAscot.getErrore();
						if (errore != null) {
							// Stampo errore in pagina
							FieldError error = new FieldError("datiTari", "nuovaVia", errore.getDescrizione());
							result.addError(error);
						}
						else {
							IndirizzoAscot indirizzoAscot = rispostaStradarioAscot.getIndirizzoAscot();
							// Set campi indirizzo per successivo invio a BE
							datiDichiarazione.setNuovoIndirizzoCodiceFrazioneAscot(indirizzoAscot.getCodiceFrazione());
							datiDichiarazione.setNuovoIndirizzoCodiceViaAscot(String.valueOf(indirizzoAscot.getCodiceVia()));
							datiDichiarazione.setNuovoIndirizzoIdentificativoCivicoAscot(String.valueOf(indirizzoAscot.getIdentificativoCivico()));
							datiDichiarazione.setNuovoIndirizzoNumeroCivicoAscot(String.valueOf(indirizzoAscot.getNumeroCivico()));
							datiDichiarazione.setNuovoIndirizzoBarratoAscot(indirizzoAscot.getBarrato());
							datiDichiarazione.setNuovoIndirizzoCorteAscot(indirizzoAscot.getCorte());
							auditManager.addMetaField("DatiDichiarazioneCambioResidenza", datiDichiarazione);
						}
					}
				}
			}

			setInfoAggiuntiveForm(datiDichiarazione);
			
			
			// Controllo identificativoUtente per familiari se TIPO_DICHIARAZIONE_STESSO_COMUNE
			if (isInteroperabilitaEnable && tipoDichiarazione != null && tipoDichiarazione.equals(TIPO_DICHIARAZIONE_STESSO_COMUNE)) {
				datiDichiarazione.setIdentificativoUtente(null);
				RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
				richiesta.setCodiceFiscale(datiDichiarazione.getCodiceFiscale());
				DatiAnagrafici richiediDatiAnagrafici = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));
				List<ComponentiNucleoFamiliare> componentiNucleoFamiliare = richiediDatiAnagrafici.getComponentiNucleoFamiliare();
				// Ricerca e set identificativo richiedente
				if (componentiNucleoFamiliare != null) {
					for (ComponentiNucleoFamiliare componenteNucleoFamiliare : componentiNucleoFamiliare) {
						if (componenteNucleoFamiliare.getCodiceFiscale().equals(datiDichiarazione.getCodiceFiscale())) {
							datiDichiarazione.setIdentificativoUtente(componenteNucleoFamiliare.getIdentificativo());
							break;
						}
					}
				}
				List<Componente> familiari = datiDichiarazione.getFamiliari();
				if (familiari != null && !familiari.isEmpty()) {
					try {
						// Ricerca e set identificativi componenti famiglia
						for (Componente componente : familiari) {
							componente.setIdentificativoUtente(null);
							RichiestaDatiAnagrafici nuovaRichiesta = new RichiestaDatiAnagrafici();
							nuovaRichiesta.setCodiceFiscale(componente.getCodiceFiscale());
							richiediDatiAnagrafici = visureService.richiediDatiAnagrafici(nuovaRichiesta, helper.getUserPreferences(request));
							List<ComponentiNucleoFamiliare> componentiComponenteNucleoFamiliare = richiediDatiAnagrafici.getComponentiNucleoFamiliare();
							if (componentiComponenteNucleoFamiliare != null) {
								for (ComponentiNucleoFamiliare componenteNucleoFamiliare : componentiComponenteNucleoFamiliare) {
									if (componente.getCodiceFiscale().equals(componenteNucleoFamiliare.getCodiceFiscale())) {
										componente.setIdentificativoUtente(componenteNucleoFamiliare.getIdentificativo());
									}
								}
							}
						}
					}
					catch (Exception e) {
						log.error("generaDichiarazione :: " + e.getMessage());
						FieldError error = new FieldError("datiTari", "verificaFamiliari", messageSource.getMessage("error.verificaFamiliari", null, request.getLocale()));
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
						result.addError(error);
					}
				}
			}
			
			if(datiDichiarazione.isFirmaGrafometrica() && datiDichiarazione.getFirmaGrafometricaBase64()==null|| datiDichiarazione.isFirmaGrafometrica()&&datiDichiarazione.getFirmaGrafometricaBase64().trim().isEmpty())
			{
				ValidationUtils.rejectIfEmptyOrWhitespace(result, "tipoCambioAbitazione", "NotEmpty.field.required");
			}
		
			 
			dichiarazioneValidator.validate(datiDichiarazione, result);

			// Controllo che la dichiarazione sia da altro comune (controllo se comune selezionato
			// == stesso comune)
			ComuneISA comuneAttuale = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
			if (tipoDichiarazione != null) {
				if (datiDichiarazione.getTipoDichiarazione().equals(TIPO_DICHIARAZIONE_ALTRO_COMUNE) && datiDichiarazione.getComuneResidenza() != null && comuneAttuale != null
						&& datiDichiarazione.getComuneResidenza().equals(comuneAttuale.getComune().getCodiceIstatAN())) {
					FieldError error = new FieldError("datiTari", "comuneResidenza",
							messageSource.getMessage("error.tipologiadichirazione.altrocomune", new String[] { comuneAttuale.getComune().getDenominazione() }, request.getLocale()));
					result.addError(error);
				}

				// Controllo flagNoteInformative
				if (!datiDichiarazione.isFlagNoteInformative()) {
					FieldError error = new FieldError("datiTari", "flagNoteInformative", messageSource.getMessage("NotEmpty.field.required", null, request.getLocale()));
					result.addError(error);
				}
			}

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.toString());
				}
				datiDichiarazione.setDichiarazioneCompletata(false);
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			}
			else {
				//Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
		
				
				// Set indirizzo residenza con dati vecchia residenza
				if (tipoDichiarazione != null && tipoDichiarazione.equals(TIPO_DICHIARAZIONE_STESSO_COMUNE)) {
					String codiceIstatComune = userPreferences.getCodiceIstatComune();
					datiDichiarazione.setComuneResidenza(codiceIstatComune);
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComune);
					if (comuneByCodiceISTAT != null) {
						 
						auditManager.addAttrServizio("cpartenza", comuneByCodiceISTAT.getDenominazione());
						
						 datiDichiarazione.setComuneResidenzaHidden(comuneByCodiceISTAT.getDenominazione());
						datiDichiarazione.setProvinciaResidenza(comuneByCodiceISTAT.getProvincia().getSigla());
					}
				
				}

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", helper.getUserPreferences(request).getNomeComune()); // specificare
				// il comune scelto in fase iniziale
				datiDichiarazione.setDichiarazioneCompletata(true);
				model.addAttribute("datiDichiarazione", datiDichiarazione);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);

				if (datiDichiarazione.getFileUsufruttuario()!=null 
						&& datiDichiarazione.getFileUsufruttuario().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileUsufruttuario().getOriginalFilename().trim().equals("")
						) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileUsufruttuario().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileUsufruttuario().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getFileIntestatarioEdiResPub()!=null 
						&& datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename().trim().equals("")) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntestatarioEdiResPub().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getFileIntra()!=null 
						&& datiDichiarazione.getFileIntra().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileIntra().getOriginalFilename().trim().equals("")) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileIntra().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileIntra().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getFileExtra()!=null 
						&& datiDichiarazione.getFileExtra().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileExtra().getOriginalFilename().trim().equals("")) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileExtra().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileExtra().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getFileAssensoProprietario()!=null 
						&& datiDichiarazione.getFileAssensoProprietario().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileAssensoProprietario().getOriginalFilename().trim().equals("")) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAssensoProprietario().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAssensoProprietario().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getFileAmpliamentoNucleoFamiliare()!=null 
						&& datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename()!=null 
						&& !datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename().trim().equals("")) {//silvio
					BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();//silvio
		            bozzaDocumenti.setAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getBytes());//silvio
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getFileAmpliamentoNucleoFamiliare().getOriginalFilename());//silvio
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}//silvio
				if (datiDichiarazione.getGeneratedFile()!=null 
						&& datiDichiarazione.getGeneratedFile().getOriginalFilename()!=null 
						&& !datiDichiarazione.getGeneratedFile().getOriginalFilename().trim().equals("")) {//silvio
		            BozzaDocumenti bozzaDocumenti = new BozzaDocumenti();
		            bozzaDocumenti.setAllegato(datiDichiarazione.getGeneratedFile().getBytes());
		            bozzaDocumenti.setNomeAllegato(datiDichiarazione.getGeneratedFile().getOriginalFilename());
					commonService.saveBozzaDocumenti(cleanMultiparts(datiDichiarazione.clone()), userPreferences, servizio.getId(), bozzaDocumenti);//silvio
				}
	            datiDichiarazione = cleanMultiparts(datiDichiarazione);
//				String idServizio=servizio==null?"16":(""+servizio.getId());
//				 //MS QUI
//				 auditManager
//				.addServizioTimeString()
//				.addServizioUuidTransazione(uuidAudit)
//				.addServizioIdServizio(idServizio);
//				
//				 auditManager=setAuditAttributiServizio(datiDichiarazione, userPreferences, auditManager);
//				 
//				
//				
//				
//				auditManager.build();
				
				
				response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");
			}
		}
		else {
			// E' stato cambiato il numero di parenti da inserire
			datiDichiarazione.setFamiliari(new ArrayList<Componente>());
			for (int i = 0; i < datiDichiarazione.getNumeroParenti(); i++) {
				datiDichiarazione.getFamiliari().add(new Componente());
			}				
			
//			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
//			String idServizio=servizio==null?"16":(""+servizio.getId());
			 
//			 auditManager
//			.addServizioTimeString()
//			.addServizioUuidTransazione(uuidAudit)
//			.addServizioIdServizio(idServizio);
//			
//			 auditManager=setAuditAttributiServizio(datiDichiarazione, userPreferences, auditManager);

			
			model.addAttribute("datiDichiarazione", datiDichiarazione);
			model.addAttribute("selectNumParenti", datiDichiarazione.getNumeroParenti());
			auditManager
			.addFineOperazione()
			.addEsitoSuccess()
			.build();
			response.setRenderParameter("action", "renderDichiarazioneCambioResidenzaForm");

		}
	}

	public Map<String, String> getVocabolarioFrazioniAscot() {

		Map<String, String> vocabolario = new TreeMap<String, String>();
		vocabolario.put("BARI", "");
		vocabolario.put("CARBONARA", "1");
		vocabolario.put("CEGLIE DEL CAMPO", "2");
		vocabolario.put("LOSETO", "3");
		vocabolario.put("PALESE", "4");
		vocabolario.put("SANTO SPIRITO", "5");
		vocabolario.put("TORRE A MARE", "6");

		return vocabolario;
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param datiDichiarazione
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneCambioResidenzaReport")
	public void serveCertificato(@ModelAttribute("datiDichiarazione") DatiDichiarazioneCambioResidenza datiDichiarazione, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request, PortletRequest portletRequest) throws Exception {
		log.info("dichiarazioneCambioResidenzaReport ");
		
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE CAMBIO RESIDENZA - GENERA REPORT CAMBIO RESIDENZA PDF")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		UserPreferences userPreferences = helper.getUserPreferences(request);
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		List<DatiDichiarazioneCambioResidenza> beans = new ArrayList<DatiDichiarazioneCambioResidenza>();
		beans.add(datiDichiarazione);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreport", SUB_REPORT_PATH);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA, REPORT_PATH,
				subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);
		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);
		auditManager.addMetaField("Fascicolo - dichiarazioneCambioResidenzaReport  ", fascicolo);
		
		// consentire il download del documento.
		String reportFileName = String.format(REPORT_PREFIX_NAME);
		reportFileName += "_" + userPreferences.getCodiceFiscaleServizio() + REPORT_SUFFIX_NAME;
		
//		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA);
//		String idServizio=servizio==null?"16":(""+servizio.getId());
//		 //MS QUI
//		 auditManager
//		.addServizioTimeString()
//		.addServizioUuidTransazione((String)session.getAttribute("UUID"))
//		.addServizioIdServizio(idServizio);
//		
//		 auditManager=setAuditAttributiServizio(datiDichiarazione, userPreferences, auditManager);
//		 
		 
		//MS prendo l'id delega per controllare se l'utente delegante ha firmato con firma grafometrica
		if(userPreferences.getIdDelega()!=null) {
		Delega delega= delegaService.getDelegaByPk(userPreferences.getIdDelega());
		if(delega!=null && delega.getFirmaGrafometrica()) {
			//TODO
			//LUTENTE HA FIRMATO CON FIRMA GRAFOMETRICA!!!
			datiDichiarazione.setFirmaGrafometrica(true);	
		}
		if(delega!=null && delega.getDelegante()!=null) {
			datiDichiarazione.setCodDelegante(""+delega.getDelegante().getId());
		auditManager.addDelegante(""+delega.getDelegante().getId());
		}
		}
		if(datiDichiarazione.getInizioOperazione()==null || datiDichiarazione.getInizioOperazione().isEmpty()) {
			datiDichiarazione.setInizioOperazione(new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).format(new Date()));	
			}
		
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
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

	@ModelAttribute("comuniEsteriList")
	public List<ComuneEstero> getComuniEsteriList() {
		return comuneEsteroService.getAllComuni();
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
	 * Presenta la form per la dichiarazione di cambio di residenza
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderDichiarazioneCambioResidenzaForm")
	public String rederIscrizioneTemporaneaForm(Model model) throws Exception {

		return toLocalViewPath("dichiarazioneCambioResidenza");
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
	 * "/dichiarazionecambioresidenza/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	/**
	 * Aggiunge al form le info aggiuntive necessarie alla corretta visualizzazione (campi hidden,
	 * ecc.).
	 *
	 * @param datiDichiarazione
	 */
	private void setInfoAggiuntiveForm(DatiDichiarazioneCambioResidenza datiDichiarazione) {

		if (datiDichiarazione == null) {
			return;
		}

		String altroMotivoDichiarazione = datiDichiarazione.getAltroMotivoDichiarazione();
		if (altroMotivoDichiarazione != null && !altroMotivoDichiarazione.isEmpty()) {
			datiDichiarazione.setAltroMotivoDichiarazioneHidden(getVocabolarioAltriMotiviIscrizione().get(altroMotivoDichiarazione));
		}

		Comune comuneNascita = comuneService.getComuneByCodiceISTAT(datiDichiarazione.getComuneNascita());
		datiDichiarazione.setComuneNascitaHidden(comuneNascita != null ? comuneNascita.getDenominazione() : null);
		Comune comuneResidenzaHidden = comuneService.getComuneByCodiceISTAT(datiDichiarazione.getComuneResidenza());
		datiDichiarazione.setComuneResidenzaHidden(comuneResidenzaHidden != null ? comuneResidenzaHidden.getDenominazione() : null);
		if (datiDichiarazione.getCittadinanza() != null && !datiDichiarazione.getCittadinanza().isEmpty()) {
			StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(datiDichiarazione.getCittadinanza()));
			datiDichiarazione.setCittadinanzaHidden(statoEsteroByCodiceStato != null ? statoEsteroByCodiceStato.getDenominazione() : null);
		}
		if (datiDichiarazione.getStatoEsteroNascita() != null && !datiDichiarazione.getStatoEsteroNascita().isEmpty()) {
			StatoEstero statoEsteroNascita = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(datiDichiarazione.getStatoEsteroNascita()));
			datiDichiarazione.setStatoEsteroNascitaHidden(statoEsteroNascita.getDenominazione());
		}
		if (datiDichiarazione.getComuneNascitaEstero() != null && !datiDichiarazione.getComuneNascitaEstero().isEmpty()) {
			ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(datiDichiarazione.getComuneNascitaEstero()));
			datiDichiarazione.setComuneNascitaEsteroHidden(comuneEstero.getDenominazione());
		}

		String statoEstero = datiDichiarazione.getStatoEstero();
		if (statoEstero != null && !statoEstero.isEmpty()) {
			StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEstero));
			datiDichiarazione.setStatoEsteroHidden(statoEsteroByCodiceStato != null ? statoEsteroByCodiceStato.getDenominazione() : null);
		}
		datiDichiarazione.setCondNonProfessionaleHidden(datiDichiarazione.getCondNonProfessionale() != null && !datiDichiarazione.getCondNonProfessionale().isEmpty()
				? getVocabolarioCondizioniNonProfessionali().get(datiDichiarazione.getCondNonProfessionale())
				: null);
		datiDichiarazione.setOrganoRilascioPatenteHidden(datiDichiarazione.getOrganoRilascioPatente() != null && !datiDichiarazione.getOrganoRilascioPatente().isEmpty()
				? getVocabolarioEntiRilascioPatente().get(datiDichiarazione.getOrganoRilascioPatente())
				: null);
		datiDichiarazione.setProfessioneHidden(
				datiDichiarazione.getProfessione() != null && !datiDichiarazione.getProfessione().isEmpty() ? getVocabolarioPosizioniProfessionali().get(datiDichiarazione.getProfessione()) : null);
		datiDichiarazione.setStatoCivileHidden(
				datiDichiarazione.getStatoCivile() != null && !datiDichiarazione.getStatoCivile().isEmpty() ? getVocabolarioStatiCivili().get(datiDichiarazione.getStatoCivile()) : null);
		datiDichiarazione.setTitoloStudioHidden(
				datiDichiarazione.getTitoloStudio() != null && !datiDichiarazione.getTitoloStudio().isEmpty() ? getVocabolarioTitoliDiStudio().get(datiDichiarazione.getTitoloStudio()) : null);

		datiDichiarazione.setVeicoli(getVeicoliInseriti(datiDichiarazione.getVeicoli()));

		if (datiDichiarazione.getComuneIscrizioneAIRE() != null && !datiDichiarazione.getComuneIscrizioneAIRE().isEmpty()) {
			Comune comuneResidenzaAIRE = comuneService.getComuneByCodiceISTAT(datiDichiarazione.getComuneIscrizioneAIRE());
			datiDichiarazione.setComuneIscrizioneAIREHidden(comuneResidenzaAIRE.getDenominazione());
		}

		List<Componente> familiari = datiDichiarazione.getFamiliari();
		if (familiari != null) {
			for (Componente componente : familiari) {
				String cittadinanza = componente.getCittadinanza();
				if (cittadinanza != null && !cittadinanza.isEmpty()) {
					StatoEstero statoEsteroCittadinanza = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(cittadinanza));
					componente.setCittadinanzaHidden(statoEsteroCittadinanza != null ? statoEsteroCittadinanza.getDenominazione() : null);
				}

				if (componente.getStatoEsteroNascita() != null && !"".equalsIgnoreCase(componente.getStatoEsteroNascita())) {
					if (componente.getComuneNascitaEstero() != null && !"".equalsIgnoreCase(componente.getComuneNascitaEstero())) {
						ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componente.getComuneNascitaEstero()));
						componente.setComuneNascitaEsteroHidden(comuneEstero.getDenominazione());
					}
				}

				Comune comuneNascitaComponente = comuneService.getComuneByCodiceISTAT(componente.getComuneNascita());
				componente.setComuneNascitaHidden(comuneNascitaComponente != null ? comuneNascitaComponente.getDenominazione() : null);
				componente.setCondNonProfessionaleHidden(componente.getCondNonProfessionale() != null && !componente.getCondNonProfessionale().isEmpty()
						? getVocabolarioCondizioniNonProfessionali().get(componente.getCondNonProfessionale())
						: null);
				componente.setOrganoRilascioPatenteHidden(componente.getOrganoRilascioPatente() != null && !componente.getOrganoRilascioPatente().isEmpty()
						? getVocabolarioEntiRilascioPatente().get(componente.getOrganoRilascioPatente())
						: null);
				componente.setProfessioneHidden(
						componente.getProfessione() != null && !componente.getProfessione().isEmpty() ? getVocabolarioPosizioniProfessionali().get(componente.getProfessione()) : null);
				componente.setParentelaHidden(componente.getParentela() != null && !componente.getParentela().isEmpty() ? getVocabolarioRelazioniDiParentela().get(componente.getParentela()) : null);
				componente.setStatoCivileHidden(componente.getStatoCivile() != null && !componente.getStatoCivile().isEmpty() ? getVocabolarioStatiCivili().get(componente.getStatoCivile()) : null);
				componente.setTitoloStudioHidden(
						componente.getTitoloStudio() != null && !componente.getTitoloStudio().isEmpty() ? getVocabolarioTitoliDiStudio().get(componente.getTitoloStudio()) : null);

				// Veicoli
				componente.setVeicoli(getVeicoliInseriti(componente.getVeicoli()));
			}
		}

		// Recapiti
		if (datiDichiarazione.getRecComune() != null && !datiDichiarazione.getRecComune().isEmpty()) {
			Comune comune = comuneService.getComuneByCodiceISTAT(datiDichiarazione.getRecComune());
			datiDichiarazione.setRecComuneHidden(comune != null ? comune.getDenominazione() : null);
		}

		String iscrittoTipoParentela = datiDichiarazione.getIscrittoTipoParentela();
		if (iscrittoTipoParentela != null && !iscrittoTipoParentela.isEmpty()) {
			datiDichiarazione.setIscrittoTipoParentelaHidden(getVocabolarioRelazioniDiParentela().get(iscrittoTipoParentela));
		}
	}

	/**
	 * @param veicoli
	 * @return
	 */
	private List<Veicolo> getVeicoliInseriti(List<Veicolo> veicoli) {

		List<Veicolo> veicoliInseriti = null;

		if (veicoli != null) {
			veicoliInseriti = new ArrayList<Veicolo>();
			for (Veicolo veicolo : veicoli) {
				if (veicolo.getTipo() != null && !veicolo.getTipo().isEmpty() && veicolo.getTarga() != null && !veicolo.getTarga().isEmpty()) {
					veicolo.setTipoHidden(getVocabolarioTipiVeicolo().get(veicolo.getTipo()));
					veicoliInseriti.add(veicolo);
				}
			}
		}

		return veicoliInseriti;
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
				result.add(documento);
			}
		}

		return result;

	}

	/**
	 * Genera il file XML di interoperabilità del servizio che dovrà essere allegato durante l'invio
	 * all'ente.
	 *
	 * @param richiesta
	 * @return
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private MultipartFile generateMultipartFileForInterop(DichiarazioneCambioResidenzaRichiesta richiesta) throws IllegalAccessException, InvocationTargetException {

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

	
	//MS QUI
	private AuditManager setAuditAttributiServizio(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences,AuditManager auditManager) throws Exception {
	
	 Date fineOpe= new Date();
	 int secondi=0;
	 String fineOperazione=new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).format(fineOpe);
	 try {
 
	  if(datiDichiarazione.getInizioOperazione()==null || datiDichiarazione.getInizioOperazione().isEmpty()) {
			datiDichiarazione.setInizioOperazione(fineOperazione);	
			}	
 
	Date inizioOpe=new SimpleDateFormat(FORMATT_DATAORA_INIZIO_OPERAZIONE).parse(datiDichiarazione.getInizioOperazione());
	 
	long millisDifferenza=fineOpe.getTime()-inizioOpe.getTime();
	
	 secondi=(int)(millisDifferenza / 1000);
	 }catch (Exception e) {  }
	 
	 
	 if (datiDichiarazione.getTipoDichiarazione() != null) {
		    
 			
			String tipoDichiarazione = datiDichiarazione.getTipoDichiarazione();
			if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ALTRO_COMUNE)) {
				auditManager.addAttrServizio("cpartenza", datiDichiarazione.getComuneResidenza());	
				auditManager.addAttrServizio("carrivo", userPreferences.getCodiceIstatComune());
				auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
				auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
				if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
					for(Componente c:datiDichiarazione.getFamiliari()) {
					auditManager.addAttrServizio("cpartenza",datiDichiarazione.getComuneResidenza());	
					auditManager.addAttrServizio("carrivo", userPreferences.getCodiceIstatComune());	
					}
				}
			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ESTERO)) {

			 
				String statoEstero = datiDichiarazione.getStatoEstero();
				if (statoEstero != null) {
					StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEstero));
					auditManager.addAttrServizio("cpartenza", ""+statoEsteroByCodiceStato.getCodiceStato());		
					auditManager.addAttrServizio("carrivo", userPreferences.getCodiceIstatComune());
					auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
					auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
					if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
						for(Componente c:datiDichiarazione.getFamiliari()) {
						auditManager.addAttrServizio("cpartenza",""+statoEsteroByCodiceStato.getCodiceStato());	
						auditManager.addAttrServizio("carrivo", userPreferences.getCodiceIstatComune());	
						}
					}
				}
			 
			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_AIRE)) {
				
				String statoEstero = datiDichiarazione.getStatoEstero();
				if (statoEstero != null) {
					StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEstero));
					auditManager.addAttrServizio("cpartenza", ""+statoEsteroByCodiceStato.getCodiceStato());	
					auditManager.addAttrServizio("carrivo", datiDichiarazione.getNuovaLocalitaHidden());	
					auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
					auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
					if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
						for(Componente c:datiDichiarazione.getFamiliari()) {
						auditManager.addAttrServizio("cpartenza", ""+ statoEsteroByCodiceStato.getCodiceStato());	
						auditManager.addAttrServizio("carrivo", datiDichiarazione.getNuovaLocalitaHidden());	
						}
					}
				}

				String comuneIscrizioneARE = datiDichiarazione.getComuneIscrizioneAIRE();
				if (comuneIscrizioneARE != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(comuneIscrizioneARE);
					auditManager.addAttrServizio("cpartenza", comuneByCodiceISTAT.getCodiceIstatAN());	
					auditManager.addAttrServizio("carrivo", datiDichiarazione.getComuneResidenza());	
					auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
					auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
					if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
						for(Componente c:datiDichiarazione.getFamiliari()) {
						auditManager.addAttrServizio("cpartenza",  comuneByCodiceISTAT.getCodiceIstatAN());	
						auditManager.addAttrServizio("carrivo",  datiDichiarazione.getComuneResidenza());	
						}
					}
				}
 
			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_STESSO_COMUNE)) {

				auditManager.addAttrServizio("cpartenza",  userPreferences.getCodiceIstatComune());	
				auditManager.addAttrServizio("carrivo",  userPreferences.getCodiceIstatComune());	
				auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
				auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
				if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
					for(Componente c:datiDichiarazione.getFamiliari()) {
					auditManager.addAttrServizio("cpartenza",  userPreferences.getCodiceIstatComune());	
					auditManager.addAttrServizio("carrivo",  userPreferences.getCodiceIstatComune());	
					}
				}
			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ALTRO)) {

				auditManager.addAttrServizio("cpartenza",  userPreferences.getCodiceIstatComune());	
				auditManager.addAttrServizio("carrivo",  userPreferences.getCodiceIstatComune());
				if(datiDichiarazione.getFamiliari()!=null && datiDichiarazione.getFamiliari().size()>0) {
					for(Componente c:datiDichiarazione.getFamiliari()) {
					auditManager.addAttrServizio("cpartenza",  userPreferences.getCodiceIstatComune());	
					auditManager.addAttrServizio("carrivo",  userPreferences.getCodiceIstatComune());	
					}
				}
				auditManager.addAttrServizio("ind_partenza", datiDichiarazione.getIndirizzoResidenza()+", "+datiDichiarazione.getCivicoResidenza());	
				auditManager.addAttrServizio("ind_carrivo",  datiDichiarazione.getNuovaVia()+", "+datiDichiarazione.getNuovoNum());	
			}

		}

	 return auditManager;
	}
	//dichiarazione.getFamiliare
	
	/**
	 * Genera il file XML dalla dichiarazione passata in input.
	 *
	 * @param datiDichiarazione
	 * @param userPreferences
	 * @return
	 */
	//MS QUI
	private DichiarazioneCambioResidenzaRichiesta getDichiarazioneCambioResidenzaInputRichiesta(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences) throws Exception {

		DichiarazioneCambioResidenzaRichiesta richiesta = new DichiarazioneCambioResidenzaRichiesta();

		if (datiDichiarazione.getTipoDichiarazione() != null) {

			Dichiarazione dichiarazione = getDichiarazione(datiDichiarazione, userPreferences);
			 
			String tipoDichiarazione = datiDichiarazione.getTipoDichiarazione();
			if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ALTRO_COMUNE)) {

				Iscrizione iscrizione = new Iscrizione();
				BeanUtils.copyProperties(dichiarazione, iscrizione);
				// Familiari
				if (dichiarazione.getFamiliare() != null) {
					iscrizione.getFamiliare().addAll(dichiarazione.getFamiliare());
				}

				richiesta.setIscrizione(iscrizione);

			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ESTERO)) {

				IscrizioneDaStatoEstero iscrizioneDaStatoEstero = new IscrizioneDaStatoEstero();
				BeanUtils.copyProperties(dichiarazione, iscrizioneDaStatoEstero);

				if (dichiarazione.getFamiliare() != null) {
					iscrizioneDaStatoEstero.getFamiliare().addAll(dichiarazione.getFamiliare());
				}

				String statoEstero = datiDichiarazione.getStatoEstero();
				if (statoEstero != null) {
					StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEstero));
					iscrizioneDaStatoEstero.setStatoEstero(new Codifica(statoEstero, statoEsteroByCodiceStato.getDenominazione()));
				}
				richiesta.setIscrizioneDaStatoEstero(iscrizioneDaStatoEstero);
			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_AIRE)) {
				IscrizioneDaStatoEsteroAIRE iscrizioneDaStatoEsteroAIRE = new IscrizioneDaStatoEsteroAIRE();
				BeanUtils.copyProperties(dichiarazione, iscrizioneDaStatoEsteroAIRE);

				if (dichiarazione.getFamiliare() != null) {
					iscrizioneDaStatoEsteroAIRE.getFamiliare().addAll(dichiarazione.getFamiliare());
				}

				String statoEstero = datiDichiarazione.getStatoEstero();
				if (statoEstero != null) {
					StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEstero));
					iscrizioneDaStatoEsteroAIRE.setStatoEstero(new Codifica(statoEstero, statoEsteroByCodiceStato.getDenominazione()));
				}

				String comuneIscrizioneARE = datiDichiarazione.getComuneIscrizioneAIRE();
				if (comuneIscrizioneARE != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(comuneIscrizioneARE);
					iscrizioneDaStatoEsteroAIRE.setComuneIscrizioneAIRE(new Codifica(comuneIscrizioneARE, comuneByCodiceISTAT.getDenominazione()));
				}

				richiesta.setIscrizioneAIRE(iscrizioneDaStatoEsteroAIRE);

			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_STESSO_COMUNE)) {

				Trasferimento trasferimento = new Trasferimento();
				BeanUtils.copyProperties(dichiarazione, trasferimento);

				if (dichiarazione.getFamiliare() != null) {
					trasferimento.getFamiliare().addAll(dichiarazione.getFamiliare());
				}

				trasferimento.setTipoRegistrazione(new Codifica(datiDichiarazione.getTipoCambioAbitazione(), null));

				richiesta.setTrasferimento(trasferimento);

			}
			else if (tipoDichiarazione.equals(TIPO_DICHIARAZIONE_ALTRO)) {

				IscrizioneAltroMotivo iscrizioneAltroMotivo = new IscrizioneAltroMotivo();
				BeanUtils.copyProperties(dichiarazione, iscrizioneAltroMotivo);

				if (dichiarazione.getFamiliare() != null) {
					iscrizioneAltroMotivo.getFamiliare().addAll(dichiarazione.getFamiliare());
				}

				iscrizioneAltroMotivo
						.setAltroMotivo(new Codifica(datiDichiarazione.getAltroMotivoDichiarazione(), getVocabolarioAltriMotiviIscrizione().get(datiDichiarazione.getAltroMotivoDichiarazione())));

				richiesta.setAltro(iscrizioneAltroMotivo);

			}

		}

		return richiesta;
	}

	/**
	 *
	 * @param datiDichiarazione
	 * @param userPreferences
	 * @return
	 * @throws ParseException
	 */
	private Dichiarazione getDichiarazione(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences) throws ParseException {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		boolean isStradarioEnabled = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA, userPreferences.getIdComuneIsa());

		Dichiarazione dichiarazione = new Dichiarazione();

		dichiarazione.setAbitazione(getAbitazione(datiDichiarazione, userPreferences, isStradarioEnabled));

		dichiarazione.setData(Calendar.getInstance());
		dichiarazione.setDichiarante(getDichiarante(datiDichiarazione, userPreferences, isStradarioEnabled));
		if (datiDichiarazione.getNoteGenerali() != null && !datiDichiarazione.getNoteGenerali().isEmpty()) {
			dichiarazione.setNote(datiDichiarazione.getNoteGenerali());
		}
		if (datiDichiarazione.getNumSogg() != null) {
			PermessoSoggiorno permessoSoggiorno = new PermessoSoggiorno();
			permessoSoggiorno.setDataRilascio(getData(datiDichiarazione.getDataRilascio()));
			permessoSoggiorno.setNumero(datiDichiarazione.getNumSogg());
			permessoSoggiorno.setQuestura(datiDichiarazione.getQuestura());
			dichiarazione.setPermessoSoggiorno(permessoSoggiorno);
		}

		List<Componente> familiari = datiDichiarazione.getFamiliari();
		if (familiari != null) {
			List<it.osapulie.anagrafe.web.ws.input.types.Componente> familiariInput = dichiarazione.getFamiliare();
			for (Componente componenteInput : familiari) {
				it.osapulie.anagrafe.web.ws.input.types.Componente componente = getComponente(componenteInput);
				componente.setIdentificativoFamiglia(datiDichiarazione.getIdentificativoFamiglia());
				familiariInput.add(componente);
			}
		}

		ResidenteIscritto residenteIscritto = getResidenteIscritto(datiDichiarazione);
		dichiarazione.setResidenteIscritto(residenteIscritto);

		TitoloOccupazioneImmobile titoloOccupazioneImmobile = new TitoloOccupazioneImmobile();
		// proprietario
		if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_PROPRIETARIO)) {
			titoloOccupazioneImmobile.setProprietario(datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_PROPRIETARIO));
		}
		else if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_INTESTATARIO_CONTRATTO)) {
			ContrattoLocazione contrattoLocazioneIntestatario = new ContrattoLocazione();
			contrattoLocazioneIntestatario.setAgenziaEntrate(datiDichiarazione.getTitoloAbitativoAgenzia1());
			contrattoLocazioneIntestatario.setData(getData(datiDichiarazione.getTitoloAbitativoData1()));
			contrattoLocazioneIntestatario.setNumero(datiDichiarazione.getTitoloAbitativoNumero1());
			titoloOccupazioneImmobile.setContrattoLocazioneIntestatario(contrattoLocazioneIntestatario);
		}
		else if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_CONTRATTO_EDILIZIA_PUBBLICA)) {
			ContrattoLocazione contrattoLocazioneIntestatarioEdiliziaPubblica = new ContrattoLocazione();
			titoloOccupazioneImmobile.setContrattoLocazioneIntestatarioEdiliziaPubblica(contrattoLocazioneIntestatarioEdiliziaPubblica);
		}
		else if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_CONTRATTO_COMODATO_GRATUITO)) {
			ContrattoLocazioneComodatario contrattoLocazioneComodatario = new ContrattoLocazioneComodatario();
			ContrattoLocazione contrattoLocazione = new ContrattoLocazione();
			contrattoLocazione.setAgenziaEntrate(datiDichiarazione.getTitoloAbitativoAgenzia2());
			contrattoLocazione.setData(getData(datiDichiarazione.getTitoloAbitativoData2()));
			contrattoLocazione.setNumero(datiDichiarazione.getTitoloAbitativoNumero2());
			contrattoLocazioneComodatario.setContrattoLocazione(contrattoLocazione);
			// TODO ???
			contrattoLocazioneComodatario.setNonRegistrato(null);
			titoloOccupazioneImmobile.setContrattoLocazioneComodatario(contrattoLocazioneComodatario);
		}
		else if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_USUFRUTTUARIO)) {
			titoloOccupazioneImmobile.setTitoloUsufruttuario(datiDichiarazione.getTitoloAbitativoAltro1());
		}
		else if (datiDichiarazione.getTitoloAbitativo().equals(TITOLO_ABITATIVO_ALTRO)) {
			titoloOccupazioneImmobile.setTitoloOccupante(datiDichiarazione.getTitoloAbitativoAltro2());
		}

		dichiarazione.setTitoloOccupazioneImmpobile(titoloOccupazioneImmobile);
		dichiarazione.setTracciamento(anagrafeService.getTracciamento(osApulieUserDetails, userPreferences));

		return dichiarazione;
	}

	/**
	 * @param componente
	 * @return
	 * @throws ParseException
	 */
	private it.osapulie.anagrafe.web.ws.input.types.Componente getComponente(Componente componenteInput) throws ParseException {

		it.osapulie.anagrafe.web.ws.input.types.Componente componente = null;
		if (componenteInput != null) {
			componente = new it.osapulie.anagrafe.web.ws.input.types.Componente();

			componente.setCellulare(componenteInput.getCellulare());
			componente.setEmail(componenteInput.getEmail());
			componente.setFax(null);
			componente.setPec(componenteInput.getPec());
			componente.setTelefono(componenteInput.getTelefono());

			if (componenteInput.getCittadinanza() != null && !componenteInput.getCittadinanza().isEmpty()) {
				Codifica cittadinanzaCodifica = new Codifica();
				cittadinanzaCodifica.setCodice(componenteInput.getCittadinanza());
				StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(componenteInput.getCittadinanza()));
				cittadinanzaCodifica.setDescrizione(statoEsteroByCodiceStato.getDenominazione());
				componente.setCittadinanza(cittadinanzaCodifica);
			}

			componente.setCodiceFiscale(componenteInput.getCodiceFiscale());
			componente.setCognome(componenteInput.getCognome());
			// Comune nascita italiano...
			String statoEsteroNascita = componenteInput.getStatoEsteroNascita();
			if (statoEsteroNascita == null || statoEsteroNascita.equals("")) {
				String comuneNascita = componenteInput.getComuneNascita();
				Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(comuneNascita);
				Codifica comuneNascitaCodifica = new Codifica(comuneNascita, comuneByCodiceISTAT.getDenominazione());
				componente.setComuneNascita(comuneNascitaCodifica);
			}
			// ...altrimenti comune estero
			else {
				String comuneNascitaEstero = componenteInput.getComuneNascitaEstero();
				ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(comuneNascitaEstero));
				Codifica comuneNascitaCodifica = new Codifica(String.valueOf(comuneEstero.getCodice()), comuneEstero.getDenominazione());
				componente.setComuneNascita(comuneNascitaCodifica);
				StatoEstero statoEstero = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEsteroNascita));
				componente.setStatoEsteroNascita(new Codifica(statoEstero.getCodiceStato().toString(), statoEstero.getDenominazione()));
			}

			String condNonProfessionale = componenteInput.getCondNonProfessionale();
			if (condNonProfessionale != null) {
				componente.setCondizioneNonProfessionale(new Codifica(condNonProfessionale, getVocabolarioCondizioniNonProfessionali().get(condNonProfessionale)));
			}

			componente.setDataNascita(getData(componenteInput.getDataNascitaString()));
			componente.setIdentificativo(componenteInput.getIdentificativoUtente() != null && !componenteInput.getIdentificativoUtente().isEmpty() ? componenteInput.getIdentificativoUtente() : null);
			componente.setNome(componenteInput.getNome());
			componente.setPartitaIVA(null);
			componente.setPatente(getPatenteComponente(componenteInput));
			String professione = componenteInput.getProfessione();
			if (professione != null) {
				Codifica codifica = new Codifica(professione, getVocabolarioPosizioniProfessionali().get(professione));
				componente.setPosizioneProfessionale(codifica);
			}
			String provinciaNascita = componenteInput.getProvinciaNascita();
			componente.setProvinciaNascita(provinciaNascita != null ? new Codifica(provinciaNascita, provinciaNascita) : null);
			String parentela = componenteInput.getParentela();
			if (parentela != null) {
				componente.setRelazioneParentela(new Codifica(parentela, getVocabolarioRelazioniDiParentela().get(parentela)));
			}
			componente.setSesso(new Codifica(componenteInput.getSesso(), componenteInput.getSesso()));
			String statoCivile = componenteInput.getStatoCivile();
			if (statoCivile != null) {
				componente.setStatoCivile(new Codifica(statoCivile, getVocabolarioStatiCivili().get(statoCivile)));
			}
			String titoloStudio = componenteInput.getTitoloStudio();
			if (titoloStudio != null) {
				componente.setTitoloStudio(new Codifica(titoloStudio, getVocabolarioTitoliDiStudio().get(titoloStudio)));
			}

			List<Veicolo> veicoliInput = componenteInput.getVeicoli();
			if (veicoliInput != null) {
				List<it.osapulie.anagrafe.web.ws.input.types.Veicolo> veicoli = componente.getVeicolo();
				for (Veicolo veicoloInput : veicoliInput) {
					String targa = veicoloInput.getTarga();
					String tipo = veicoloInput.getTipo();
					if (targa != null && !targa.isEmpty() && tipo != null && !tipo.isEmpty()) {
						it.osapulie.anagrafe.web.ws.input.types.Veicolo veicolo = new it.osapulie.anagrafe.web.ws.input.types.Veicolo();
						veicolo.setTarga(targa);
						veicolo.setTipo(new Codifica(tipo, veicoloInput.getTipoHidden()));
						veicoli.add(veicolo);
					}
				}
			}
		}

		return componente;
	}

	/**
	 * Carica i dati dell'eventuale componente già iscritto nell'immobile.
	 *
	 * @param datiDichiarazione
	 * @return
	 * @throws ParseException
	 */
	private ResidenteIscritto getResidenteIscritto(DatiDichiarazioneCambioResidenza datiDichiarazione) throws ParseException {
		ResidenteIscritto residenteIscritto = null;

		String iscrittoCodiceFiscale = datiDichiarazione.getIscrittoCodiceFiscale();

		if (iscrittoCodiceFiscale != null && !iscrittoCodiceFiscale.isEmpty()) {

			residenteIscritto = new ResidenteIscritto();
			it.osapulie.anagrafe.web.ws.input.types.Componente componente = new it.osapulie.anagrafe.web.ws.input.types.Componente();
			componente.setCodiceFiscale(iscrittoCodiceFiscale);
			componente.setCognome(datiDichiarazione.getIscrittoCognome());
			componente.setNome(datiDichiarazione.getIscrittoNome());
			componente.setDataNascita(getData(datiDichiarazione.getIscrittoDataNascita()));
			String iscrittoLuogoNascita = datiDichiarazione.getIscrittoLuogoNascita();
			if (iscrittoLuogoNascita != null) {
				Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(iscrittoLuogoNascita);
				if (comuneByCodiceISTAT != null) {
					componente.setComuneNascita(new Codifica(iscrittoLuogoNascita, comuneByCodiceISTAT.getDenominazione()));
				}
			}

			BeanUtils.copyProperties(componente, residenteIscritto);
			residenteIscritto.setParentela(datiDichiarazione.isIscrittoParentela());
			String iscrittoTipoParentela = datiDichiarazione.getIscrittoTipoParentela();
			if (iscrittoTipoParentela != null && !iscrittoTipoParentela.isEmpty()) {
				residenteIscritto.setVincoloParentela(new Codifica(iscrittoTipoParentela, getVocabolarioRelazioniDiParentela().get(iscrittoTipoParentela)));
				datiDichiarazione.setIscrittoTipoParentelaHidden(getVocabolarioRelazioniDiParentela().get(iscrittoTipoParentela));
			}
		}
		return residenteIscritto;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 * @throws ParseException
	 */
	private it.osapulie.anagrafe.web.ws.input.types.Componente getDichiarante(DatiDichiarazioneCambioResidenza datiDichiarazione) throws ParseException {

		it.osapulie.anagrafe.web.ws.input.types.Componente componente = new it.osapulie.anagrafe.web.ws.input.types.Componente();

		if (datiDichiarazione.getCittadinanza() != null && !datiDichiarazione.getCittadinanza().isEmpty()) {
			Codifica cittadinanzaCodifica = new Codifica();
			cittadinanzaCodifica.setCodice(datiDichiarazione.getCittadinanza());
			StatoEstero statoEsteroByCodiceStato = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(datiDichiarazione.getCittadinanza()));
			cittadinanzaCodifica.setDescrizione(statoEsteroByCodiceStato.getDenominazione());
			componente.setCittadinanza(cittadinanzaCodifica);
		}

		componente.setCellulare(datiDichiarazione.getCellulare());
		componente.setEmail(datiDichiarazione.getEmail());
		componente.setFax(null);
		componente.setPec(datiDichiarazione.getPec());
		componente.setTelefono(datiDichiarazione.getTelefono());

		componente.setCodiceFiscale(datiDichiarazione.getCodiceFiscale());
		componente.setCognome(datiDichiarazione.getCognome());

		String statoEsteroNascita = datiDichiarazione.getStatoEsteroNascita();
		// Comune nascita italiano...
		if (statoEsteroNascita == null || statoEsteroNascita.equals("")) {
			String comuneNascita = datiDichiarazione.getComuneNascita();
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(comuneNascita);
			Codifica comuneNascitaCodifica = new Codifica(comuneNascita, comuneByCodiceISTAT.getDenominazione());
			componente.setComuneNascita(comuneNascitaCodifica);
		}
		// ...altrimenti comune estero
		else {
			String comuneNascitaEstero = datiDichiarazione.getComuneNascitaEstero();
			ComuneEstero comuneEstero = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(comuneNascitaEstero));
			Codifica comuneNascitaCodifica = new Codifica(String.valueOf(comuneEstero.getCodice()), comuneEstero.getDenominazione());
			componente.setComuneNascita(comuneNascitaCodifica);
			StatoEstero statoEstero = statoEsteroService.getStatoEsteroByCodiceStato(Integer.parseInt(statoEsteroNascita));
			componente.setStatoEsteroNascita(new Codifica(statoEstero.getCodiceStato().toString(), statoEstero.getDenominazione()));
		}

		String condNonProfessionale = datiDichiarazione.getCondNonProfessionale();
		if (condNonProfessionale != null && !condNonProfessionale.isEmpty()) {
			componente.setCondizioneNonProfessionale(new Codifica(condNonProfessionale, getVocabolarioCondizioniNonProfessionali().get(condNonProfessionale)));
		}

		componente.setDataNascita(getData(datiDichiarazione.getDataNascita()));
		componente
				.setIdentificativo(datiDichiarazione.getIdentificativoUtente() != null && !datiDichiarazione.getIdentificativoUtente().isEmpty() ? datiDichiarazione.getIdentificativoUtente() : null);
		componente.setIdentificativoFamiglia(datiDichiarazione.getIdentificativoFamiglia());
		componente.setNome(datiDichiarazione.getNome());
		componente.setPartitaIVA(null);
		componente.setPatente(getPatenteDichiarante(datiDichiarazione));
		String professione = datiDichiarazione.getProfessione();
		if (professione != null && !professione.isEmpty()) {
			Codifica codifica = new Codifica(professione, getVocabolarioPosizioniProfessionali().get(professione));
			componente.setPosizioneProfessionale(codifica);
		}
		String provinciaNascita = datiDichiarazione.getProvinciaNascita();
		componente.setProvinciaNascita(provinciaNascita != null ? new Codifica(provinciaNascita, provinciaNascita) : null);
		componente.setSesso(new Codifica(datiDichiarazione.getSesso(), datiDichiarazione.getSesso()));
		String statoCivile = datiDichiarazione.getStatoCivile();
		if (statoCivile != null && !statoCivile.isEmpty()) {
			componente.setStatoCivile(new Codifica(statoCivile, getVocabolarioStatiCivili().get(statoCivile)));
		}
		String titoloStudio = datiDichiarazione.getTitoloStudio();
		if (titoloStudio != null && !titoloStudio.isEmpty()) {
			componente.setTitoloStudio(new Codifica(titoloStudio, getVocabolarioTitoliDiStudio().get(titoloStudio)));
		}

		List<Veicolo> veicoliInput = datiDichiarazione.getVeicoli();
		if (veicoliInput != null) {
			List<it.osapulie.anagrafe.web.ws.input.types.Veicolo> veicoli = componente.getVeicolo();
			for (Veicolo veicoloInput : veicoliInput) {
				String targa = veicoloInput.getTarga();
				String tipo = veicoloInput.getTipo();
				if (targa != null && !targa.isEmpty() && tipo != null && !tipo.isEmpty()) {
					it.osapulie.anagrafe.web.ws.input.types.Veicolo veicolo = new it.osapulie.anagrafe.web.ws.input.types.Veicolo();
					veicolo.setTarga(targa);
					veicolo.setTipo(new Codifica(tipo, veicoloInput.getTipoHidden()));
					veicoli.add(veicolo);
				}
			}
		}

		componente.getVeicolo();

		return componente;
	}

	/**
	 * Crea l'oggetto {@link Patente} per il dichiarante.
	 *
	 * @param datiDichiarazione
	 * @return
	 * @throws ParseException
	 */
	private Patente getPatenteDichiarante(DatiDichiarazioneCambioResidenza datiDichiarazione) throws ParseException {

		Patente patente = null;
		String numPatente = datiDichiarazione.getNumPatente();
		if (numPatente != null && !numPatente.isEmpty()) {
			patente = new Patente();
			patente.setCarattereControllo(numPatente.substring(numPatente.length() - 1));
			patente.setCategoria(datiDichiarazione.getTipoPatente());
			patente.setDataRilascio(getData(datiDichiarazione.getDataRilascioPatente()));
			patente.setNumero(numPatente.substring(2, numPatente.length() - 1));
			String organoRilascioPatente = datiDichiarazione.getOrganoRilascioPatente();
			if (organoRilascioPatente != null && !organoRilascioPatente.isEmpty()) {
				patente.setOrganoRilascio(new Codifica(organoRilascioPatente, getVocabolarioEntiRilascioPatente().get(organoRilascioPatente)));
			}
			String provPatente = datiDichiarazione.getProvPatente();
			if (provPatente != null) {
				patente.setProvincia(new Codifica(provPatente, provPatente));
			}
			patente.setSigla(numPatente.substring(0, 2));

		}
		return patente;
	}

	/**
	 * Crea l'oggetto {@link Patente} per il componente familiare.
	 *
	 * @param componenteInput
	 * @return
	 * @throws ParseException
	 */
	private Patente getPatenteComponente(Componente componenteInput) throws ParseException {
		Patente patente = null;
		String numPatente = componenteInput.getNumPatente();
		if (numPatente != null && !numPatente.isEmpty()) {
			patente = new Patente();
			patente.setCarattereControllo(numPatente.substring(numPatente.length() - 1));
			patente.setCategoria(componenteInput.getTipoPatente());
			patente.setDataRilascio(getData(componenteInput.getDataRilascioPatente()));
			patente.setNumero(numPatente.substring(2, numPatente.length() - 1));
			String organoRilascioPatente = componenteInput.getOrganoRilascioPatente();
			if (organoRilascioPatente != null && !organoRilascioPatente.isEmpty()) {
				patente.setOrganoRilascio(new Codifica(organoRilascioPatente, getVocabolarioEntiRilascioPatente().get(organoRilascioPatente)));
			}
			String provPatente = componenteInput.getProvPatente();
			if (provPatente != null) {
				patente.setProvincia(new Codifica(provPatente, provPatente));
			}
			patente.setSigla(numPatente.substring(0, 2));

		}
		return patente;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 * @throws ParseException
	 */
	private Dichiarante getDichiarante(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences, boolean stradarioEnable) throws ParseException {

		it.osapulie.anagrafe.web.ws.input.types.Componente componente = getDichiarante(datiDichiarazione);
		Dichiarante dichiarante = new Dichiarante();
		BeanUtils.copyProperties(componente, dichiarante);
		List<it.osapulie.anagrafe.web.ws.input.types.Veicolo> veicoli = componente.getVeicolo();
		dichiarante.getVeicolo().addAll(veicoli);

		dichiarante.setResidenza(getImmobileResidenza(datiDichiarazione, userPreferences, stradarioEnable));
		Indirizzo indirizzoRecapito = getIndirizzoRecapito(datiDichiarazione, stradarioEnable);
		Recapito recapito = new Recapito();
		BeanUtils.copyProperties(indirizzoRecapito, recapito);
		recapito.setCellulare(datiDichiarazione.getRecCellulare());
		recapito.setEmail(datiDichiarazione.getRecEmail());
		recapito.setFax(datiDichiarazione.getRecFax());
		recapito.setPec(datiDichiarazione.getRecPec());
		recapito.setPresso(null);
		recapito.setTelefono(datiDichiarazione.getRecTelefono());
		dichiarante.setRecapito(recapito);

		return dichiarante;
	}

	/**
	 * @param datiDichiarazione
	 * @param stradarioEnable
	 * @return
	 */
	private Immobile getAbitazione(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences, boolean stradarioEnable) {
		Immobile immobile = new Immobile();
		immobile.setCategoriaImmobile(null);
		immobile.setClasse(null);
		immobile.setCodiceCatasto(null);
		immobile.setDataProtocollo(null);
		immobile.setDatiCatastali(getDatiCatastali(datiDichiarazione));
		Indirizzo indirizzoImmobile = getIndirizzoImmobile(datiDichiarazione, userPreferences, stradarioEnable);
		// Setto comune attuale per indirizzo abitazione
		Comune comuneByCodiceISTAT = comuneService.getComuneById(userPreferences.getIdComuneIsa());
		indirizzoImmobile.setComune(new Codifica(comuneByCodiceISTAT.getCodiceIstatAN(), comuneByCodiceISTAT.getDenominazione()));
		immobile.setIndirizzo(indirizzoImmobile);
		immobile.setNumeroProtocollo(null);
		immobile.setTipoCatasto(null);
		immobile.setValoreImmobile(null);
		return immobile;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 */
	private Indirizzo getIndirizzoRecapito(DatiDichiarazioneCambioResidenza datiDichiarazione, boolean stradarioEnable) {

		Indirizzo indirizzo = new Indirizzo();
		Civico civico = new Civico();
		Codifica codiceVia = new Codifica();
		civico.setEsponente(datiDichiarazione.getRecEsponente());
		if (datiDichiarazione.getRecCivico() != null && !datiDichiarazione.getRecCivico().isEmpty()) {
			civico.setNumero(Integer.parseInt(datiDichiarazione.getRecCivico()));
		}
		indirizzo.setCivico(civico);
		codiceVia.setDescrizione(datiDichiarazione.getRecVia());
		indirizzo.setVia(codiceVia);

		return indirizzo;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 */
	private Immobile getImmobileResidenza(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences, boolean stradarioEnable) {
		Immobile immobile = new Immobile();
		immobile.setCategoriaImmobile(null);
		immobile.setClasse(null);
		immobile.setCodiceCatasto(null);
		immobile.setDataProtocollo(null);
		Indirizzo indirizzoResidenza = getIndirizzoImmobile(datiDichiarazione, userPreferences, stradarioEnable);
		// Setto il comune di residenza (sovrascrivo)
		String comuneResidenza = datiDichiarazione.getComuneResidenza();
		if (comuneResidenza != null && !comuneResidenza.isEmpty()) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(comuneResidenza);
			if (comuneByCodiceISTAT != null) {
				indirizzoResidenza.setComune(new Codifica(comuneByCodiceISTAT.getCodiceIstatAN(), comuneByCodiceISTAT.getDenominazione()));
			}
		}
		immobile.setIndirizzo(indirizzoResidenza);

		immobile.setNumeroProtocollo(null);
		immobile.setTipoCatasto(null);
		immobile.setValoreImmobile(null);
		return immobile;
	}

	/**
	 * Crea il codiceFrazioneVia richiesto da ASCOT.
	 *
	 *
	 * @param indirizzoCodiceFrazioneAscot
	 * @param indirizzoCodiceViaAscot
	 * @return
	 */
	private String getCodiceViaAscot(String indirizzoCodiceFrazioneAscot, String indirizzoCodiceViaAscot) {

		if (indirizzoCodiceFrazioneAscot == null || indirizzoCodiceFrazioneAscot.isEmpty()) {
			indirizzoCodiceFrazioneAscot = "0";
		}

		String codiceFrazione = String.format("%02d", Integer.parseInt(indirizzoCodiceFrazioneAscot));

		if (indirizzoCodiceViaAscot == null || indirizzoCodiceViaAscot.isEmpty()) {
			indirizzoCodiceViaAscot = "0";
		}
		String codiceVia = String.format("%06d", Integer.parseInt(indirizzoCodiceViaAscot));

		return codiceFrazione + codiceVia;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 */
	private Indirizzo getIndirizzoImmobile(DatiDichiarazioneCambioResidenza datiDichiarazione, UserPreferences userPreferences, boolean stradarioEnable) {

		Indirizzo indirizzo = new Indirizzo();

		Civico civico = new Civico();
		Codifica codiceVia = new Codifica();

		// Se comune di BARI setto i valori ASCOT...
		if (userPreferences.getIdComuneIsa() == 1) {
			civico.setCodice(datiDichiarazione.getNuovoIndirizzoIdentificativoCivicoAscot());
			civico.setEsponente(datiDichiarazione.getNuovoIndirizzoBarratoAscot());
			if(datiDichiarazione.getNuovoIndirizzoNumeroCivicoAscot() != null)
				civico.setNumero(Integer.parseInt(datiDichiarazione.getNuovoIndirizzoNumeroCivicoAscot()));
			indirizzo.setCivico(civico);
			codiceVia.setCodice(getCodiceViaAscot(datiDichiarazione.getNuovoIndirizzoCodiceFrazioneAscot(), datiDichiarazione.getNuovoIndirizzoCodiceViaAscot()));
			codiceVia.setDescrizione(datiDichiarazione.getNuovaViaTextHidden());
			indirizzo.setVia(codiceVia);
		}
		// .. altrimenti setto i valori di default (stradario o testo semplice)
		else {
			if (stradarioEnable) {
				civico.setCodice(datiDichiarazione.getNuovoNum());
				civico.setEsponente(datiDichiarazione.getNuovoEsp());
				if (datiDichiarazione.getNuovoNumTextHidden() != null && !datiDichiarazione.getNuovoNumTextHidden().isEmpty()) {
					civico.setNumero(Integer.parseInt(datiDichiarazione.getNuovoNumTextHidden()));
				}
				indirizzo.setCivico(civico);
				codiceVia.setCodice(datiDichiarazione.getNuovaVia());
				codiceVia.setDescrizione(datiDichiarazione.getNuovaViaTextHidden());
				indirizzo.setVia(codiceVia);
			}
			else {
				civico.setEsponente(datiDichiarazione.getNuovoEsp());
				civico.setNumero(Integer.parseInt(datiDichiarazione.getNuovoNum()));
				indirizzo.setCivico(civico);
				codiceVia.setDescrizione(datiDichiarazione.getNuovaVia());
				indirizzo.setVia(codiceVia);
			}
		}

		indirizzo.setLocalita(datiDichiarazione.getNuovaLocalitaHidden());
		indirizzo.setPiano(datiDichiarazione.getNuovoPiano());
		indirizzo.setInterno(datiDichiarazione.getNuovoInterno());
		indirizzo.setScala(datiDichiarazione.getNuovaScala());
		indirizzo.setCorte(datiDichiarazione.getNuovaCorte());
		indirizzo.setCap(null);

		return indirizzo;
	}

	/**
	 * @param datiDichiarazione
	 * @return
	 */
	private DatiCatastali getDatiCatastali(DatiDichiarazioneCambioResidenza datiDichiarazione) {
		DatiCatastali datiCatastali = new DatiCatastali();
		if (datiDichiarazione.getFoglio() != null && !datiDichiarazione.getFoglio().isEmpty()) {
			datiCatastali.setFoglio(Integer.parseInt(datiDichiarazione.getFoglio()));
		}
		if (datiDichiarazione.getParticella() != null && !datiDichiarazione.getParticella().isEmpty()) {
			datiCatastali.setParticella(Integer.parseInt(datiDichiarazione.getParticella()));
		}
		datiCatastali.setSezione(datiDichiarazione.getSezione());

		if (datiDichiarazione.getSubalterno() != null && !datiDichiarazione.getSubalterno().isEmpty()) {
			datiCatastali.setSubalterno(Integer.parseInt(datiDichiarazione.getSubalterno()));
		}
		return datiCatastali;
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
	
	private String determinaTipoDichiarazione(String type) {
		if(type==null)
			return "";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_ALTRO))
			return "TIPO_DICHIARAZIONE_ALTRO";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_STESSO_COMUNE))
			return "TIPO_DICHIARAZIONE_STESSO_COMUNE";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_AIRE))
			return "TIPO_DICHIARAZIONE_AIRE";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_ESTERO))
			return "TIPO_DICHIARAZIONE_ESTERO";
		if(type.equalsIgnoreCase(TIPO_DICHIARAZIONE_ALTRO_COMUNE))
			return "TIPO_DICHIARAZIONE_ALTRO_COMUNE";
		
		return "";
	}
	
}
