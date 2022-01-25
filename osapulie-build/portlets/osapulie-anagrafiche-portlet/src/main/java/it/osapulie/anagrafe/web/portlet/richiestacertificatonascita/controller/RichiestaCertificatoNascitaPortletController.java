package it.osapulie.anagrafe.web.portlet.richiestacertificatonascita.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.service.impl.AuditDwhServiceImpl;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
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
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio richiesta Certificato di Nascita.
 *
 * @author Birtolo Maria Michela
 * @author Federico Melli
 */
@Controller("richiestaCertificatoNascitaPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoNascitaPortletController extends BaseController {

	/**
	 *
	 */
	private static final String DATA_NASCITA_CONDITION = "dataNascitaCondition";

	private final Logger log = LoggerFactory.getLogger(RichiestaCertificatoNascitaPortletController.class);

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private VisureService visureService;

	@Inject
	private ReportService reportService;

	@Inject
	private TimbroDigitaleService timbroService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private TemplateCertificatiHelper templateCertificatiHelper;

	@Inject
	private AnagrafeCommonService anagrafeCommonService;

	@Inject
	private ConfigurazioneService configurazioneService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ComuneEsteroService comuneEsteroService;
	
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
	public String homePage(Model model, PortletSession session, PortletRequest portletRequest) throws Exception {

		log.debug("homePage-richiestaCertificatoNascitaPortletController");
		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditManager auditManager= AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta("CERTIFICATO DI NASCITA")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.setOrigin(Origin.getIp(portletRequest))
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
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService)
		.setCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO NASCITA")
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

 		
				
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		
		
		
		try {
			RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
			richiesta.setCodiceFiscale(codiceFiscale);

			DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));

			model.addAttribute("datiAnagrafici", dati);
			model.addAttribute("listaUsi", getUsiList());

			List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
			for (int i = 0; i < componenti.size(); i++) {
				ComponentiNucleoFamiliare componente = componenti.get(i);
				if (codiceFiscale != null && codiceFiscale.equals(componente.getCodiceFiscale())) {
					anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
					model.addAttribute("componenteFamiglia", componente);

					// Controllo scaricamento certificato
					Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),
							userPreferences.getIdComuneIsa());
					if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
						model.addAttribute("mostraLink", "no");
						String conditionValue = configurazioneService.getCondizioneGenerazioneCertficiati(DATA_NASCITA_CONDITION, PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),
								userPreferences.getIdComuneIsa());
						if (conditionValue != null) {
							model.addAttribute(DATA_NASCITA_CONDITION, conditionValue.substring(0, 4));
						}
					}

					// Controllo se il certificato può essere scaricato (in base al comune di
					// nascita ed all'atto di nascita trascritto mancante)
					// FIXME eliminare controllo x comune di Bari
					Comune comuneNascitaComponente = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					if (comuneNascitaComponente != null) {
						if (userPreferences.getCodiceIstatComune().equals("072006") && !comuneNascitaComponente.getCodiceIstatAN().equals(userPreferences.getCodiceIstatComune())
								&& (componente.getNumeroAttoNascitaTrascritto() == null || componente.getNumeroAttoNascitaTrascritto() == 0)) {
							model.addAttribute("mostraLink", "no");
						}
					}
					break;
				}
			}
		}
		catch (Exception e) {
			log.error("homePage :: " + e.getMessage(), e);
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
		}
		auditManager.build();
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati, Model model, ActionRequest request, ActionResponse response, PortletRequest portletRequest, PortletSession session)
			throws Exception {

		log.debug("cambioSoggetto cf= ..." + codFisc);
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CERTIFICATO DI NASCITA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		UserPreferences userPreferences = helper.getUserPreferences(request);

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);

				// Controllo scaricamento certificato
				Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),
						userPreferences.getIdComuneIsa());
				if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
					model.addAttribute("mostraLink", "no");
					String conditionValue = configurazioneService.getCondizioneGenerazioneCertficiati(DATA_NASCITA_CONDITION, PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),
							userPreferences.getIdComuneIsa());
					if (conditionValue != null) {
						model.addAttribute(DATA_NASCITA_CONDITION, conditionValue.substring(0, 4));
					}
				}

				// Controllo se il certificato può essere scaricato (in base al comune di
				// nascita ed all'atto di nascita trascritto mancante)
				// FIXME eliminare controllo x comune di Bari
				Comune comuneNascitaComponente = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
				if (comuneNascitaComponente != null) {
					if (userPreferences.getCodiceIstatComune().equals("072006") && !comuneNascitaComponente.getCodiceIstatAN().equals(userPreferences.getCodiceIstatComune())
							&& (componente.getNumeroAttoNascitaTrascritto() == null || componente.getNumeroAttoNascitaTrascritto() == 0)) {
						model.addAttribute("mostraLink", "no");
					}
				}

				break;
			}
		}
		auditManager.build();
		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	@ResourceMapping("certificatoNascitaReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, ResourceRequest request, PortletSession session,PortletRequest portletRequest,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {

		log.info(MessageFormat.format("Generazione Certificato di Nascita Cittadino[Codice Fiscale = [{0}]]",codFisc));
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CERTIFICATO DI NASCITA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		UserPreferences userPreferences = helper.getUserPreferences(request);

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		ComponentiNucleoFamiliare componenteSel = null;

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		String comuneNascita = null;
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;

				// Controllo scaricamento certificato
				Map<String, Boolean> conditions = configurazioneService.getCondizioniGenerazioneCertificati(componente, PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),
						userPreferences.getIdComuneIsa());
				if (conditions != null && !conditions.get(DATA_NASCITA_CONDITION)) {
					log.info("serveCertificato :: dataNascitaCondition : impossibile generare il certificato per : " + codFisc);
					return;
				}

				// Controllo se il certificato può essere scaricato (in base al comune di
				// nascita ed all'atto di nascita trascritto mancante)
				// FIXME eliminare controllo x comune di Bari
				Comune comuneNascitaComponente = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
				if (comuneNascitaComponente != null) {
					if (userPreferences.getCodiceIstatComune().equals("072006") && !comuneNascitaComponente.getCodiceIstatAN().equals(userPreferences.getCodiceIstatComune())
							&& (componente.getNumeroAttoNascitaTrascritto() == null || componente.getNumeroAttoNascitaTrascritto() == 0)) {
						log.info("serveCertificato :: comune di nascita != dal comune selezionato ed atto di nascita trascritto mancante : impossibile generare il certificato per : " + codFisc);
						return;
					}
				}

				// Caricamento comune nascita
				String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
				if (codiceIstatComuneNascita != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
					if (comuneByCodiceISTAT != null) {
						comuneNascita = comuneByCodiceISTAT.getDenominazione();
					}
					// Ricerca in comuni esteri
					else {
						if (!codiceIstatComuneNascita.isEmpty()) {
							ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(codiceIstatComuneNascita));
							if (comuneEsteroByCodice != null) {
								comuneNascita = comuneEsteroByCodice.getDenominazione();
							}
						}
					}
				}
				break;
			}
		}

		// Controllo che esista il comune di residenza, altrimenti vieto la generazione del
		// certificato
		if (comuneNascita == null) {
			log.info("serveCertificato :: comune di nascita non trovato nel DB : impossibile generare il certificato per : " + codFisc);
			return;
		}

		String osapulieHost = request.getServerName();

		String reportFileName = String.format("certificato-di-nascita.pdf");
		byte[] certificatoTimbrato = null;
		switch (this.stampVersion(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE),codFisc)){
			case 0:
				String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, "", null, null, "certificatonascita", helper.getUserPreferences(request),
						PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE), osapulieHost);
				certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, helper.getUserPreferences(request));
				break;
			case 1:
				certificatoTimbrato = timbroService.richiediCertificatoTimbrato(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA,codFisc, helper.getUserPreferences(request),null,null,componenteSel, dati);
				break;
			default:
				break;
		}
		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE));
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);
		fascicoloService.saveNuovaRichiesta(fascicolo);
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		DwhTempiMediDTO tempiMediDTO = new DwhTempiMediDTO();
		//MS
		tempiMediDTO = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO NASCITA")
		.getTempiMedi();
		
		auditDwhService.invokeAuditService(null, null, tempiMediDTO);
		
		DwhServiceAttributeUtil.saveTimeFine(dwhService, new IUpdateAttribute() {
			public String updateServizioProtocollo() {return null;}
			public String updateServizioParametro3() {return null;}
			public String updateServizioParametro2() {return null;}
			public String updateServizioParametro1() {return null;}
			public Date updateServizioFine() {return new Date();}
			public String searchUUID() {return uuidOperazione;}
		});
		
			AuditConfiguration
			.configure()
			.audit()
			.addInizioOperazione()
			.addFineOperazione()
			.addUuidOperazione(uuidOperazione)
			.addOperazioneRichiesta("RICHIESTA CERTIFICATO NASCITA - STEP FINALE DOWNLOAD DOCUMENTO ")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		helper.sendStreamAsAttachment(certificatoTimbrato, response, reportFileName, ContentMimeTypes.PDF);
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {

		log.debug("certificatoNascitaReportPdfURL cf= ..." + codFisc);

		UserPreferences userPreferences = helper.getUserPreferences(request);

		List<String> listaUsi = getUsiList();
		String usoCertificatoId = request.getParameter("uso");

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;

				// Controllo se il certificato può essere scaricato (in base al comune di
				// nascita)
				if (!componente.getCodiceIstatComuneNascita().equals(userPreferences.getCodiceIstatComune())) {
					log.info("reportPdf :: comune di nascita non corrispondente al comune selezionato : impossibile generare il certificato per : " + codFisc);
					return;
				}
				break;
			}
		}

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		if (listaUsi != null) {
			// Setto la motivazione di utilizzo del certificato
			param.put("uso", listaUsi.get(Integer.parseInt(usoCertificatoId)));
		}

		// mellif 20150316: comune e provincia passati come parametri in seguito all'introduzione
		// del codiceIstat
		Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneNascita());
		if (comuneByCodiceISTAT != null) {
			param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
			param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
		}
		// Ricerca in comuni esteri
		else {
			if (!componenteSel.getCodiceIstatComuneNascita().isEmpty()) {
				ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(componenteSel.getCodiceIstatComuneNascita()));
				if (comuneEsteroByCodice != null) {
					param.put("comuneNascita", comuneEsteroByCodice.getDenominazione());
				}
			}
		}

		String report_path = "/reports/richiestaCertificatoNascita.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE), report_path,
				null);

		// TODO Da verificare se vanno salvate nel fascicolo anche le richieste di report in PDF
		// Per ora commentiamo il tutto
		// String md5 = CheckSumGenerator.generateCheckSum(reportBytes);
		// ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// Fascicolo fascicolo = new Fascicolo();
		// fascicolo.setIdProfiloUtente(profiloUtente);
		// fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null,
		// portletRequest.getLocale()));
		// fascicolo.setUserPreferences(helper.getUserPreferences( portletRequest ));
		// fascicolo.setCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE));
		// fascicolo.setRicercabileDaComune(false);
		// fascicolo.setNumeroProtocollo(null);
		// fascicolo.setChecksum(md5);
		// fascicoloService.saveNuovaRichiesta(fascicolo);

		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		//MS
		
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO NASCITA")
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
			.addOperazioneRichiesta("RICHIESTA CERTIFICATO NASCITA - STEP FINALE DOWNLOAD REPORT_PDF ")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		
		// download del pdf
		String reportFileName = String.format("certificatonascita.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/*
	 * METODO DI TEST PER DEMO 26/01/2015
	 */
	@ResourceMapping("reportPdfDEMO")
	public void reportPdfDEMO(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAnagrafici") DatiAnagrafici dati) throws Exception {
		log.debug("certificatoNascitaReportPdfURL cf= ..." + codFisc);

		List<String> listaUsi = getUsiList();
		String usoCertificatoId = request.getParameter("uso");

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		// mellif 20150316: comune e provincia passati come parametri in seguito all'introduzione
		// del codiceIstat
		Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componenteSel.getCodiceIstatComuneNascita());
		param.put("comuneNascita", comuneByCodiceISTAT.getDenominazione());
		param.put("provinciaNascita", comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());

		if (listaUsi != null) {
			// Setto la motivazione di utilizzo del certificato
			param.put("uso", listaUsi.get(Integer.parseInt(usoCertificatoId)));
		}
		
		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO NASCITA")
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
			.addOperazioneRichiesta("RICHIESTA CERTIFICATO NASCITA - STEP FINALE DOWNLOAD DOCUMENTO ")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE))
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		
		String report_path = "/reports/demo/richiestaCertificatoNascita.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA.get(PortletConstants.CODICE), report_path,
				null);

		// download del pdf
		String reportFileName = String.format("certificatonascita.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare i dati anagrafici
	 */
	@RenderMapping(params = "action=renderDatiAnagrafici")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		model.addAttribute("listaUsi", getUsiList());
		return toLocalViewPath("home");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/richiesta-certificato-nascita/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestacertificatonascita/" + viewName;
	}

	private List<String> getUsiList() {
		List<String> lista = new ArrayList<String>();
		// lista.add("Uso bollo (bollo da 16,00 euro da acquistare e applicare a cura del
		// richiedente)");
		// lista.add("Uso abbonamenti per trasporto di persone (articolo 24 DPR 642/72)");
		// lista.add("Uso adozione e affido minore (articolo 13 DPR 642/72)");
		// lista.add("Uso aiuti settore agricolo (articolo 21bis DPR 642/72 - articolo 16 Legge
		// 153/75)");
		// lista.add("Uso ammissione soci in cooperativa edilizia (legge 427/93)");
		// lista.add("Uso anticipazione indennità fine rapporto (articolo 9 DPR 642/72)");
		// lista.add("Uso applicazione leggi tributarie (articolo 5 DPR 642/72)");
		// lista.add("Uso assegni familiari (articolo 9 - articolo 12 DPR 642/72)");
		// lista.add("Uso assicurazioni sociali obbligatorie (articolo 9 DPR 642/72)");
		// lista.add("Uso assistenza sanitaria (articolo 10 DPR 642/72)");
		// lista.add("Uso atti, documenti e provvedimenti in materia penale (articolo 3 DPR
		// 642/72)");
		// lista.add("Uso atti e provvedimenti del procedimento innanzi alla corte costituzionale");
		// lista.add("Uso attività sportive (articolo 8 bis DPR 642/72)");
		// lista.add("Uso attribuzione, rettifica o modifica del codice fiscale o partita iva");
		// lista.add("Uso borsa di studio (articolo 11 DPR 642/72)");
		// lista.add("Uso calcolo detrazioni IRPEF (articolo 5 DPR 642/72)");
		// lista.add("Uso cause scioglimento matrimonio (articolo 19 Legge 74/87)");
		// lista.add("Uso controversie in materia di lavoro (articolo 10 DPR 642/72)");
		// lista.add("Uso domande di sussidio (articolo 8 DPR 642/72)");
		// lista.add("Uso erogazione prestiti INDAP (articolo 9 DPR 642/72)");
		// lista.add("Uso esonero o frequenza insegnamento religioso (articolo 11 DPR 642/72)");
		// lista.add("Uso esonero tasse scolastiche (articolo 11 DPR 642/72)");
		// lista.add("Uso espatrio minore (articolo 18 DPR 642/72)");
		// lista.add("Uso espropriazione (articolo 22 DPR 642/72)");
		// lista.add("Uso istruzione di secondo grado (articolo 9 DPR 642/72)");
		// lista.add("Uso legalizzazione di fotografia (articolo 34 comma 2 DPR 445/2000)");
		// lista.add("Uso organismi utilità sociale (ONLUS) (articolo 27 bis DPR 642/72)");
		// lista.add("Uso pensione (articolo 12 DPR 642/72)");
		// lista.add("Uso ricongiunzione carriera ai fini contributivi (articolo 9 DPR 642/72)");
		// lista.add("Uso ricorso avverso il diniego del ricongiungimento familiare dall'estero");
		// lista.add("Uso successione (articolo 5 DPR 642/72)");
		// lista.add("Uso tutela minori e interdetti (articolo 13 DPR 642/72)");
		// lista.add("Uso volontariato (Legge 266/91)");
		return lista;

	}
}
