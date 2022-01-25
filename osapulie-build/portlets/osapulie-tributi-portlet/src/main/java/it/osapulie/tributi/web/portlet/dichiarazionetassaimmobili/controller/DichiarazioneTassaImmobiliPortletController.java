package it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.controller;

import java.io.IOException;
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
import org.springframework.web.portlet.handler.PortletSessionRequiredException;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form.DatiDichiarazioneTassaImmobili;
import it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form.DatiDichiarazioneTassaImmobili.PosizioniTassaImmobiliDichiarazione;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.input.types.Codifica;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaImmobiliInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaImmobiliInputRichiesta.Dichiarante;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaImmobiliInputRichiesta.PosizioneParzialmenteImponibileOEsente;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaImmobiliInputRichiesta.PosizioneTotalmenteImponibile;
import it.osapulie.tributi.web.ws.input.types.Immobile.IndirizzoUtenzaImmobile;
import it.osapulie.tributi.web.ws.input.types.Indirizzo;
import it.osapulie.tributi.web.ws.input.types.IndirizzoCompleto;
import it.osapulie.tributi.web.ws.input.types.Soggetto;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta;
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
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;

/**
 * Portlet controller per il servizio Dichiarazione Tassa Immobili (IMU/TASI).
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("dichiarazioneTassaImmobiliPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiTassaImmobili", "param", "uploadItem" })
public class DichiarazioneTassaImmobiliPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(DichiarazioneTassaImmobiliPortletController.class);

	private static final String SERVIZIO = "DICHIARAZIONE IMU/TASI";

	private static final String REPORT_PATH = "/reports/dichiarazioneTassaImmobili.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/dichiarazioneTassaImmobili_subreport_totalmenteImponibili.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/dichiarazioneTassaImmobili_subreport_parzialmenteImponibili.jrxml";
	private static final String REPORT_NAME = "dichiarazioneTassaImmobili.pdf";

	private static final String JSP_PATH = "dichiarazionetassaimmobili/";

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
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiDichiarazioneTassaImmobiliValidator")
	private Validator dichiarazioneValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private AziendaService aziendaService;

	@Inject
	private ComuneISAService comuneISAService;

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
	public String homePage(Model model, PortletSession session) throws Exception {
		log.debug("homePage-dichiarazioneTassaImmobiliPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la dichiarazione tassa immobili.
	 *
	 * @param model
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getDichiarazioneTassaImmobiliForm")
	public void getDichiarazioneTassaImmobiliForm(Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {

		log.debug("getDichiarazioneTassaImmobiliForm");
		
		String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO)
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
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
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiUtente datiGen = null;
		ComponentiNucleoFamiliare componente = null;
		try {
			try {
				DatiAnagrafici dati = tributiService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				datiGen = tributiService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));
				if (dati != null) {
					for (int k = 0; k < dati.getComponentiNucleoFamiliare().size(); k++) {
						if (codiceFiscale.equals(dati.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
							componente = dati.getComponentiNucleoFamiliare().get(k);
						}
					}
				}
			}
			catch (Exception e) {
				log.warn("getDichiarazioneTassaImmobiliForm :: " + e.getMessage());
				auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			}

			DatiDichiarazioneTassaImmobili datiTassaImmobili = new DatiDichiarazioneTassaImmobili();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				datiTassaImmobili.setPartitaIva(partitaIvaServizio);
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiTassaImmobili.setCodiceFiscale(aziendaByPiva.getPartitaIva());
				datiTassaImmobili.setCognome(aziendaByPiva.getRagioneSociale());
				it.osapulie.domain.Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiTassaImmobili.setIndirizzo(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiTassaImmobili.setProvResidenza(comune.getProvincia().getSigla());
						datiTassaImmobili.setComuneResidenza(comune.getDenominazione());
					}
				}
				datiTassaImmobili.setDataNascita(null);
			}
			else {
				if (componente != null && datiGen != null) {
					datiTassaImmobili.setCodiceFiscale(componente.getCodiceFiscale());
					datiTassaImmobili.setNome(componente.getNome());
					datiTassaImmobili.setCognome(componente.getCognome());
					datiTassaImmobili.setSesso(componente.getSesso());
					datiTassaImmobili.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					datiTassaImmobili.setSesso(componente.getSesso());
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiTassaImmobili.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiTassaImmobili.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getSigla());
					}
					datiTassaImmobili.setIndirizzo(datiGen.getIndirizzo());
					datiTassaImmobili.setCapResidenza(datiGen.getCap());
					datiTassaImmobili.setComuneResidenza(datiGen.getComuneResidenza());
					datiTassaImmobili.setProvResidenza(datiGen.getProvinciaResidenza());
				}
				// Caricamento dati da utente in OSApulie
				else {
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiTassaImmobili.setCodiceFiscale(codiceFiscale);
					datiTassaImmobili.setNome(profiloUtente.getNome());
					datiTassaImmobili.setCognome(profiloUtente.getCognome());
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiTassaImmobili.setComuneNascita(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiTassaImmobili.setProvinciaNascita(provincia.getSigla());
							}
						}
					}
					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiTassaImmobili.setIndirizzo(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiTassaImmobili.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiTassaImmobili.setProvResidenza(provincia.getSigla());
							}
						}
					}
				}
			}

			if (getVisuraPosizioniTributarieActive(request)) {

				DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();

				if (partitaIvaServizio != null) {
					dichiarazioneTassaImmobiliRichiesta.setPartitaIva(partitaIvaServizio);
				}
				else {
					dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(codiceFiscale);
				}
				dichiarazioneTassaImmobiliRichiesta.setAnnoInizio((new Integer(DateUtils.getAnnoCorrente()) - 1));
				dichiarazioneTassaImmobiliRichiesta.setAnnoFine((new Integer(DateUtils.getAnnoCorrente()) - 1));

				DichiarazioneTassaImmobiliRisposta visuraTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);

				List<DatiTassaImmobili> situazioni = visuraTassaImmobiliRisposta.getSituazione();
				if (situazioni != null && !situazioni.isEmpty()) {
					DatiTassaImmobili situazione = situazioni.get(0);
					List<Posizioni> posizioni = situazione.getPosizioni();
					List<PosizioniTassaImmobiliDichiarazione> posizioniTassaImmobiliTotalmenteImpoibili = new ArrayList<DatiDichiarazioneTassaImmobili.PosizioniTassaImmobiliDichiarazione>();
					List<PosizioniTassaImmobiliDichiarazione> posizioniTassaImmobiliParzialmenteImpoibiliOEsenti = new ArrayList<DatiDichiarazioneTassaImmobili.PosizioniTassaImmobiliDichiarazione>();
					if (posizioni != null) {
						for (Posizioni posizioni2 : posizioni) {
							PosizioniTassaImmobiliDichiarazione posizione = new PosizioniTassaImmobiliDichiarazione();
							posizione.setIndirizzoUtenza(posizioni2.getIndirizzoUtenza());
							posizione.setCaratteristicaImmobile(posizioni2.getCaratteristicaImmobile());
							posizione.setNumero(posizioni2.getNumero());
							posizione.setSezione(posizioni2.getSezione());
							posizione.setFoglio(posizioni2.getFoglio());
							posizione.setParticella(posizioni2.getParticella());
							posizione.setSubalterno(posizioni2.getSubalterno());
							posizione.setValoreImmobile(posizioni2.getValoreImmobile());
							posizione.setCategoriaImmobile(posizioni2.getCategoriaImmobile());
							posizione.setPercentualePossesso(posizioni2.getPercentualePossesso());
							posizione.setMesiPossesso(posizioni2.getMesiPossesso());
							posizione.setMesiDetrazione(posizioni2.getMesiDetrazione());
							posizione.setImportoDetrazioneAbPrincipale(posizioni2.getImportoDetrazioneAbPrincipale());
							posizione.setPosseduto3112(posizioni2.isPosseduto3112());
							posizione.setAbitazionePrincipale(posizioni2.isAbitazionePrincipale());
							posizione.setRiduzione(posizioni2.isRiduzione());
							// TODO VALORIZZA UNA O L'ALTRA LISTA DELLE POSIZIONI IN BASE AL
							// RISULTATO
							// CHE ARRIVERA DAL WS
							// if(posizioni2.getNonSoCosa)
							posizioniTassaImmobiliTotalmenteImpoibili.add(posizione);
							// posizioniTassaImmobiliParzialmenteImpoibiliOEsenti.add(posizione);
							//
						}
						datiTassaImmobili.setPosizioniTotalmenteImponibili(posizioniTassaImmobiliTotalmenteImpoibili);
						datiTassaImmobili.setPosizioniParzialmenteImponibiliOEsenti(posizioniTassaImmobiliParzialmenteImpoibiliOEsenti);
					}
				}
			}

			model.addAttribute("datiTassaImmobili", datiTassaImmobili);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
		}
		catch (Exception e) {
			log.error("getDichiarazioneTassaImmobiliForm :: " + e.getMessage(), e);
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
		}
		auditManager.build();
		response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
	}

	public Map<String, String> getCaratteristicheImmobiliMap() {
		Map<String, String> caratteristicheImmobiliMap = new TreeMap<String, String>();

		caratteristicheImmobiliMap.put("1", "Terreno agricolo");
		caratteristicheImmobiliMap.put("2", "Area fabbricabile");
		caratteristicheImmobiliMap.put("3", "Fabbricato con valore determinato");
		caratteristicheImmobiliMap.put("4", "Fabbricato di tipo D con valore contabile");
		caratteristicheImmobiliMap.put("5", "Abitazione principale");
		caratteristicheImmobiliMap.put("6", "Pertinenza");
		caratteristicheImmobiliMap.put("7.1", "Per immobili non produttivi di reddito fondiario, ai sensi dell'art.43 del TUIR");
		caratteristicheImmobiliMap.put("7.2", "Per immobili posseduti da soggetti passivi IRES");
		caratteristicheImmobiliMap.put("7.3", "Per immobili locati");
		caratteristicheImmobiliMap.put("8", "Cosiddetti beni merce");

		return caratteristicheImmobiliMap;
	}

	/**
	 * Restituisce la form per l'upload delle dichiarazioni e doc.
	 *
	 * @param model
	 * @param response
	 */
	@ActionMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response, PortletSession session, PortletRequest request) throws Exception {
		
		String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
	AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO)
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
			 
			 UserPreferences userPreferences = helper.getUserPreferences(request);
				String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
			 
				DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
				DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
				.setUUID(uuidOperazione)
				.getServizioAttribute();	
				
				dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
				.setUuidOperazione(uuidOperazione)
				.getDatamining();
				
				 tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
				.setNomeServizio(SERVIZIO)
				.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
				
				auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
			
			 
		UploadItem item = new UploadItem();
		item.setSignedFiles(true);
		model.addAttribute("uploadItem", item);
		auditManager.build();
		response.setRenderParameter("action", "renderUploadForm");
	}

	/**
	 * Gestisce l'upload dei file
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param portletRequest 
	 * @throws PortletException
	 * @throws IOException
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiTassaImmobili") DatiDichiarazioneTassaImmobili datiTassaImmobili, PortletRequest portletRequest)
			throws Exception {

		log.debug("uploadFile");
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta(SERVIZIO)
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		if (selectNumAllegati == null) {
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			UserPreferences userPreferences = helper.getUserPreferences(request);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);
			}

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(datiTassaImmobili, userPreferences);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(numeroProtocollo);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
					auditManager
					.addFineOperazione()
					.addEsitoError()
					.addInfo("Exception", e.getMessage())
					.build();
				}

				// Invio email ad azienda
				if (partitaIvaServizio != null) {
					String subjectAzienda = messageSource.getMessage("mail.azienda.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
					try {
						tributiCommonService.sendMailToCompany(userPreferences, aziendaByPiva, subjectAzienda, SERVIZIO, numeroProtocollo);
					}
					catch (Exception e) {
						log.error("upload :: " + e.getMessage(), e);
						auditManager
						.addFineOperazione()
						.addEsitoError()
						.addInfo("Exception", e.getMessage())
						.build();
					}
				}
				 
				
				final String uuidOperazione=(String)session.getAttribute("UUID");
				
			
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI)
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
	public void compilaDichiarazione(@ModelAttribute("datiTassaImmobili") DatiDichiarazioneTassaImmobili datiTassaImmobili, BindingResult result,
			@RequestParam(required = false, value = "aggiungiImmobileTotalmenteImponibile") String aggiungiImmobileTotalmenteImponibile,
			@RequestParam(required = false, value = "aggiungiImmobileParzialmenteImponibile") String aggiungiImmobileParzialmenteImponibile,
			@RequestParam(required = false, value = "rimuoviImmobileTotalmenteImponibileIndex") String rimuoviImmobileTotalmenteImponibile,
			@RequestParam(required = false, value = "rimuoviImmobileParzialmenteImponibileIndex") String rimuoviImmobileParzialmenteImponibile,
			@RequestParam(required = false, value = "annoDichiarazione") String annoDichiarazione, Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {
		log.debug("compilaDichiarazione CF=" + datiTassaImmobili.getCodiceFiscale());
		
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE IMU/TASI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
		
		model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

		if (aggiungiImmobileParzialmenteImponibile == null && aggiungiImmobileTotalmenteImponibile == null
				&& (rimuoviImmobileTotalmenteImponibile == null || rimuoviImmobileTotalmenteImponibile.equalsIgnoreCase(""))
				&& (rimuoviImmobileParzialmenteImponibile == null || rimuoviImmobileParzialmenteImponibile.equalsIgnoreCase(""))) {
			dichiarazioneValidator.validate(datiTassaImmobili, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
			}
			else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", helper.getUserPreferences(request).getNomeComune());
				param.put("annoDichiarazione", DateUtils.getAnnoCorrente());// new
																			// Integer(annoDichiarazione)));

				model.addAttribute("datiTassaImmobili", datiTassaImmobili);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
			}
		}
		else if (aggiungiImmobileParzialmenteImponibile != null) {
			log.debug("aggiungiImmobileParzialmenteImponibile");
			if (datiTassaImmobili.getPosizioniParzialmenteImponibiliOEsenti() == null) {
				datiTassaImmobili.setPosizioniParzialmenteImponibiliOEsenti(new ArrayList<PosizioniTassaImmobiliDichiarazione>());
			}
			datiTassaImmobili.getPosizioniParzialmenteImponibiliOEsenti().add(new PosizioniTassaImmobiliDichiarazione());
			model.addAttribute("datiTassaImmobili", datiTassaImmobili);
			response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
		}
		else if (aggiungiImmobileTotalmenteImponibile != null) {
			log.debug("aggiungiImmobileTotalmenteImponibile");
			if (datiTassaImmobili.getPosizioniTotalmenteImponibili() == null) {
				datiTassaImmobili.setPosizioniTotalmenteImponibili(new ArrayList<PosizioniTassaImmobiliDichiarazione>());
			}
			datiTassaImmobili.getPosizioniTotalmenteImponibili().add(new PosizioniTassaImmobiliDichiarazione());
			model.addAttribute("datiTassaImmobili", datiTassaImmobili);
			response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
		}
		else if (!("").equalsIgnoreCase(rimuoviImmobileTotalmenteImponibile)) {
			log.debug("rimuoviImmobileTotalmenteImponibile");
			int i = new Integer(rimuoviImmobileTotalmenteImponibile);
			log.debug("rimuovo=" + i);
			datiTassaImmobili.getPosizioniTotalmenteImponibili().remove(i);
			model.addAttribute("datiTassaImmobili", datiTassaImmobili);
			response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
		}
		else if (!("").equalsIgnoreCase(rimuoviImmobileParzialmenteImponibile)) {
			log.debug("rimuoviPosizione=" + rimuoviImmobileParzialmenteImponibile);
			int j = new Integer(rimuoviImmobileParzialmenteImponibile);
			log.debug("rimuovo=" + j);
			datiTassaImmobili.getPosizioniParzialmenteImponibiliOEsenti().remove(j);
			model.addAttribute("datiTassaImmobili", datiTassaImmobili);
			
			auditManager.build();
			response.setRenderParameter("action", "renderDichiarazioneTassaImmobiliForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest 
	 * @param dati
	 * @param dichiarazione
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneTassaImmobiliReport")
	public void serveCertificato(@ModelAttribute("datiTassaImmobili") DatiDichiarazioneTassaImmobili datiTassaImmobili, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request, PortletRequest portletRequest) throws Exception {

		log.debug("dichiarazioneTassaImmobiliReport ");
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE IMU/TASI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		// Query the service layer for some objects
		List<DatiDichiarazioneTassaImmobili> beans = new ArrayList<DatiDichiarazioneTassaImmobili>();

		beans.add(datiTassaImmobili);
		param.put("subreportParameters1", datiTassaImmobili.getPosizioniTotalmenteImponibili());
		param.put("subreportParameters2", datiTassaImmobili.getPosizioniParzialmenteImponibiliOEsenti());

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		log.debug(md5);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

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

	@ModelAttribute("visuraPosizioniTributarieActive")
	public Boolean getVisuraPosizioniTributarieActive(PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);

		boolean result = false;

		ComuneISA comuneByPk = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
		if (comuneByPk != null) {
			result = comuneByPk.getVisuraPosizioniTributarieActive();
			if (result) {
				result = configurazioneService.isVisuraPosizioneTributariaEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI, comuneByPk.getId());
			}
		}

		return result;
	}

	/**
	 * Presenta la form per la dichiarazione ici
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderDichiarazioneTassaImmobiliForm")
	public String renderDichiarazioneTassaImmobiliForm(Model model) throws Exception {
		return toLocalViewPath("dichiarazioneTassaImmobili");
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
	public String renderEsitoUpload(Model model, PortletRequest request, PortletSession session) {
		log.debug("Model = " + model);
		AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("DICHIARAZIONE IMU/TASI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addFineOperazione()
				.addEsitoSuccess()
				.setOrigin(Origin.getIp(request))
				.build();
		return toLocalViewPath("esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/dichiarazionetassaimmobili/home" </code>
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
	 * @throws ParseException
	 *
	 *
	 */
	private MultipartFile generateMultipartFileForInterop(DatiDichiarazioneTassaImmobili datiDichiarazione, UserPreferences userPreferences) throws ParseException {

		String xml = null;

		DichiarazioneTassaImmobiliInputRichiesta interop = new DichiarazioneTassaImmobiliInputRichiesta();

		// Contribuente
		Soggetto contribuente = new Soggetto();
		contribuente.setNome(datiDichiarazione.getNome());
		contribuente.setCognome(datiDichiarazione.getCognome());
		contribuente.setSesso(datiDichiarazione.getSesso());
		contribuente.setCodiceFiscale(datiDichiarazione.getCodiceFiscale());
		contribuente.setComuneNascita(datiDichiarazione.getComuneNascita());
		if (datiDichiarazione.getDataNascita() != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(datiDichiarazione.getDataNascita()));
			contribuente.setDataNascita(calendar);
		}
		contribuente.setProvinciaNascita(datiDichiarazione.getProvinciaNascita());
		contribuente.setEmail(datiDichiarazione.getEmail());
		contribuente.setTelefono(datiDichiarazione.getTelefono());
		interop.setContribuente(contribuente);

		// Indirizzo Contribuente
		Indirizzo residenzaContribuente = new Indirizzo();
		// TODO CODICE ISTAT
		residenzaContribuente.setComune(new Codifica(null, datiDichiarazione.getComuneResidenza()));
		// residenzaContribuente.setProvincia(datiDichiarazione.getProvResidenza());
		boolean stradarioEnable = configurazioneService.isStradarioEnable(PortletConstants.CODICE_SERVIZIO_DICHIARAZIONE_TASSA_IMMOBILI, userPreferences.getIdComuneIsa());
		it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico civico = new it.osapulie.tributi.web.ws.input.types.Indirizzo.Civico();
		Codifica via = new Codifica();
		if (stradarioEnable) {
			civico.setCodice(datiDichiarazione.getIndirizzo());
			civico.setNumero(Integer.parseInt(datiDichiarazione.getCivico()));
			residenzaContribuente.setCivico(civico);
			via.setCodice(datiDichiarazione.getIndirizzo());
			// via.setDescrizione(datiDichiarazione.getIndirizzoTextHidden());
			residenzaContribuente.setVia(via);
		}
		else {
			if (datiDichiarazione.getCivico() != null) {
				civico.setNumero(Integer.parseInt(datiDichiarazione.getCivico()));
				residenzaContribuente.setCivico(civico);
			}
			via.setDescrizione(datiDichiarazione.getIndirizzo());
			residenzaContribuente.setVia(via);
		}
		residenzaContribuente.setScala(datiDichiarazione.getScala());
		residenzaContribuente.setPiano(datiDichiarazione.getPiano());
		residenzaContribuente.setInterno(datiDichiarazione.getInterno());

		interop.setResidenzaContribuente(residenzaContribuente);

		// Dichiarante
		Dichiarante dichiarante = new Dichiarante();
		dichiarante.setNome(datiDichiarazione.getNome());
		dichiarante.setCognome(datiDichiarazione.getCognome());
		dichiarante.setSesso(datiDichiarazione.getSesso());
		dichiarante.setNaturaCarica(datiDichiarazione.getNaturaCarica());
		if (datiDichiarazione.getDataNascita() != null) {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(simpleDateFormat.parse(datiDichiarazione.getDataNascita()));
			dichiarante.setDataNascita(calendar);
		}
		dichiarante.setComuneNascita(datiDichiarazione.getComuneNascita());
		dichiarante.setProvinciaNascita(datiDichiarazione.getProvinciaNascita());
		dichiarante.setCodiceFiscale(datiDichiarazione.getCodiceFiscale());
		dichiarante.setTelefono(datiDichiarazione.getTelefono());
		dichiarante.setEmail(datiDichiarazione.getEmail());
		dichiarante.setCodiceFiscaleIntermediario(datiDichiarazione.getCodiceFiscaleIntermediarioDic());
		dichiarante.setCodiceFiscaleSocietaEnte(datiDichiarazione.getCodiceFiscaleSocietaEnteDic());
		dichiarante.setImpegnoPresentazioneTelematica(datiDichiarazione.isImpegnoPresentazioneTelematicaDic());
		interop.setDichiarante(dichiarante);

		// Indirizzo Dichiarante
		IndirizzoCompleto residenzaDichiarante = new IndirizzoCompleto();
		if (stradarioEnable) {
			// TODO
		}
		else {
			residenzaDichiarante.setCap(datiDichiarazione.getCapResidenza());
			residenzaDichiarante.setCivico(null);
			residenzaDichiarante.setCodiceStatoEstero(null);
			residenzaDichiarante.setComune(new Codifica(null, datiDichiarazione.getComuneResidenza()));
			residenzaDichiarante.setInterno(datiDichiarazione.getInterno());
			residenzaDichiarante.setPiano(datiDichiarazione.getPiano());
			residenzaDichiarante.setScala(datiDichiarazione.getScala());
			residenzaDichiarante.setVia(new Codifica(null, datiDichiarazione.getIndirizzo()));

		}
		interop.setResidenzaDichiarante(residenzaDichiarante);

		// Immobili Totalmente Imponibili
		List<PosizioniTassaImmobiliDichiarazione> posTotImponibili = datiDichiarazione.getPosizioniTotalmenteImponibili();
		if (posTotImponibili != null) {
			for (PosizioniTassaImmobiliDichiarazione p : posTotImponibili) {
				PosizioneTotalmenteImponibile posizione = new PosizioneTotalmenteImponibile();
				posizione.setCaratteristicaImmobile(new Codifica(p.getCaratteristicaImmobile(), getCaratteristicheImmobiliMap().get(p.getCaratteristicaImmobile())));
				it.osapulie.tributi.web.ws.output.types.Codifica categoriaImmobile = p.getCategoriaImmobile();
				if (categoriaImmobile != null) {
					posizione.setCategoriaImmobile(categoriaImmobile.getCodice());
				}
				IndirizzoUtenzaImmobile indirizzo = new IndirizzoUtenzaImmobile();
				indirizzo.setIndirizzo(PortletUtils.getIndirizzoFromPosizione(p));
				posizione.setIndirizzoUtenzaImmobile(indirizzo);
				posizione.setCodiceCatasto(p.getCodiceCatasto());
				posizione.setTipoCatasto(p.getTipoCatasto());
				posizione.setSezione(p.getSezione());
				posizione.setFoglio(p.getFoglio() + "");
				posizione.setParticella(p.getParticella() + "");
				posizione.setSubalterno(p.getSubalterno() + "");
				posizione.setClasse(p.getClasse());
				if (p.getAnno() != null) {
					Calendar dataProcollo = Calendar.getInstance();
					dataProcollo.set(Calendar.YEAR, Integer.parseInt(p.getAnno()));
					posizione.setDataProtocollo(dataProcollo);
				}
				posizione.setInagibile(p.isInagibile());
				posizione.setValoreImmobile(p.getValoreImmobile());
				posizione.setPercentualePossesso(p.getPercentualePossesso());
				if (p.getDataInizioPossesso() != null) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(simpleDateFormat.parse(p.getDataInizioPossesso()));
					posizione.setDataInizioPossesso(calendar);
				}
				posizione.setDiAcquisto(p.isDiAcquisto());
				posizione.setDiCessione(p.isDiCessione());
				posizione.setAgenziaEntrate(p.getAgenziaEntrate());
				posizione.setEstremiTitolo(p.getEstremiTitolo());
				posizione.setAnnotazioni(p.getAnnotazioni());
				interop.getPosizioneTotalmenteImponibile().add(posizione);
			}
		}

		// Immobili Parzialmente Imponibili o Esenti
		List<PosizioniTassaImmobiliDichiarazione> parImponibili = datiDichiarazione.getPosizioniParzialmenteImponibiliOEsenti();
		if (parImponibili != null) {
			for (PosizioniTassaImmobiliDichiarazione p : parImponibili) {
				PosizioneParzialmenteImponibileOEsente posizione = new PosizioneParzialmenteImponibileOEsente();
				posizione.setCaratteristicaImmobile(new Codifica(p.getCaratteristicaImmobile(), getCaratteristicheImmobiliMap().get(p.getCaratteristicaImmobile())));
				it.osapulie.tributi.web.ws.output.types.Codifica categoriaImmobile = p.getCategoriaImmobile();
				if (categoriaImmobile != null) {
					posizione.setCategoriaImmobile(categoriaImmobile.getCodice());
				}
				IndirizzoUtenzaImmobile indirizzo = new IndirizzoUtenzaImmobile();
				indirizzo.setIndirizzo(PortletUtils.getIndirizzoFromPosizione(p));
				posizione.setIndirizzoUtenzaImmobile(indirizzo);
				posizione.setCodiceCatasto(p.getCodiceCatasto());
				posizione.setTipoCatasto(p.getTipoCatasto());
				posizione.setSezione(p.getSezione());
				posizione.setFoglio(p.getFoglio() + "");
				posizione.setParticella(p.getParticella() + "");
				posizione.setSubalterno(p.getSubalterno() + "");
				posizione.setClasse(p.getClasse());
				if (p.getAnno() != null) {
					Calendar dataProcollo = Calendar.getInstance();
					dataProcollo.set(Calendar.YEAR, Integer.parseInt(p.getAnno()));
					posizione.setDataProtocollo(dataProcollo);
				}
				posizione.setInagibile(p.isInagibile());
				posizione.setValoreImmobile(p.getValoreImmobile());
				posizione.setPercentualePossesso(p.getPercentualePossesso());
				if (p.getDataInizioPossesso() != null) {
					Calendar calendar = new GregorianCalendar();
					calendar.setTime(simpleDateFormat.parse(p.getDataInizioPossesso()));
					posizione.setDataInizioPossesso(calendar);
				}
				posizione.setDiAcquisto(p.isDiAcquisto());
				posizione.setDiCessione(p.isDiCessione());
				posizione.setAgenziaEntrate(p.getAgenziaEntrate());
				posizione.setEstremiTitolo(p.getEstremiTitolo());
				// ATTIVITA SVOLTA
				posizione.setAttivitaSvolta1(p.isAttivitaSvolta1());
				posizione.setAttivitaSvolta2(p.isAttivitaSvolta2());
				posizione.setAttivitaSvolta3(p.isAttivitaSvolta3());
				posizione.setAttivitaSvolta4(p.isAttivitaSvolta4());
				posizione.setAttivitaSvolta5(p.isAttivitaSvolta5());
				posizione.setAttivitaSvolta6(p.isAttivitaSvolta6());
				posizione.setAttivitaSvolta7(p.isAttivitaSvolta7());
				posizione.setAttivitaSvolta8(p.isAttivitaSvolta8());
				posizione.setAttivitaSvolta9(p.isAttivitaSvolta9());
				posizione.setAttivitaSvolta10(p.isAttivitaSvolta10());
				// ATTIVITA DIDATTICA
				posizione.setCmAttivitaDidattica(p.getCmAttivitaDidattica());
				posizione.setCmsAttivitaDidattica(p.getCmsAttivitaDidattica());
				posizione.setAAttivitaDidattica(p.getaAttivitaDidattica());
				posizione.setA1AttivitaDidattica(p.getA1AttivitaDidattica());
				posizione.setBAttivitaDidattica(p.getbAttivitaDidattica());
				posizione.setB1AttivitaDidattica(p.getB1AttivitaDidattica());
				posizione.setCAttivitaDidattica(p.getcAttivitaDidattica());
				posizione.setDAttivitaDidattica(p.getdAttivitaDidattica());
				posizione.setEAttivitaDidattica(p.geteAttivitaDidattica());
				posizione.setFAttivitaDidattica(p.getfAttivitaDidattica());
				posizione.setGAttivitaDidattica(p.isgAttivitaDidattica());
				posizione.setHAttivitaDidattica(p.ishAttivitaDidattica());
				posizione.setIAttivitaDidattica(p.getiAttivitaDidattica());
				posizione.setJAttivitaDidattica(p.getjAttivitaDidattica());
				posizione.setKAttivitaDidattica(p.getkAttivitaDidattica());
				// ALTRE ATTIVITA
				posizione.setCencAltreAttivita(p.getCencAltreAttivita());
				posizione.setCmAltreAttivita(p.getCmAltreAttivita());
				posizione.setAAltreAttivita(p.getaAltreAttivita());
				posizione.setA1AltreAttivita(p.getA1AltreAttivita());
				posizione.setBAltreAttivita(p.getbAltreAttivita());
				posizione.setB1AltreAttivita(p.getB1AltreAttivita());
				posizione.setCAltreAttivita(p.getcAltreAttivita());
				posizione.setDAltreAttivita(p.getdAltreAttivita());
				posizione.setEAltreAttivita(p.geteAltreAttivita());
				interop.getPosizioneParzialmenteImponibileOEsente().add(posizione);
			}
		}
		// DETERMINAZIONE IMU E TASI
		interop.setImuDovuta(datiDichiarazione.getImuDovuta());
		interop.setEccedenzaImuPrecedenteDichiarazione(datiDichiarazione.getEccedenzaImuPrecedenteDichiarazione());
		interop.setEccedenzaImuPrecedenteDichiarazioneCompensataF24(datiDichiarazione.getEccedenzaImuPrecedenteDichiarazioneCompensataF24());
		interop.setRateVersateImu(datiDichiarazione.getRateVersateImu());
		interop.setImuDebito(datiDichiarazione.getImuDebito());
		interop.setImuCredito(datiDichiarazione.getImuCredito());
		interop.setTasiDovuta(datiDichiarazione.getTasiDovuta());
		interop.setEccedenzaTasiPrecedenteDichiarazione(datiDichiarazione.getEccedenzaTasiPrecedenteDichiarazione());
		interop.setEccedenzaTasiPrecedenteDichiarazioneCompensataF24(datiDichiarazione.getEccedenzaTasiPrecedenteDichiarazioneCompensataF24());
		interop.setRateVersateTasi(datiDichiarazione.getRateVersateTasi());
		interop.setTasiDebito(datiDichiarazione.getTasiDebito());
		interop.setTasiCredito(datiDichiarazione.getTasiCredito());

		// COMPENSAZIONI E RIMBORSI
		interop.setImuCreditoCompensazione(datiDichiarazione.getImuCreditoCompensazione());
		interop.setImuCreditoRimborso(datiDichiarazione.getImuCreditoRimborso());
		interop.setImuCreditoPrecedenteDichiarazione(datiDichiarazione.getImuCreditoPrecedenteDichiarazione());
		interop.setTasiCreditoRimborso(datiDichiarazione.getTasiCreditoRimborso());
		interop.setTasiCreditoCompensazione(datiDichiarazione.getTasiCreditoCompensazione());
		interop.setTasiCreditoPrecedenteDichiarazione(datiDichiarazione.getTasiCreditoPrecedenteDichiarazione());

		xml = xmlHelper.marshal(interop);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

}
