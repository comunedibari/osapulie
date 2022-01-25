package it.osapulie.anagrafe.web.portlet.richiestacertificatovedovanza.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.inject.Inject;
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

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.TimbroDigitaleService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.portlet.util.impl.TemplateCertificatiHelper;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
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
 * Portlet controller per il servizio richiesta Certificato di vedovanza.
 *
 * @author Birtolo Maria Michela
 */
@Controller("richiestaCertificatoVedovanzaPortletController")
@RequestMapping("view")
@SessionAttributes("datiAnagrafici")
public class RichiestaCertificatoVedovanzaPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RichiestaCertificatoVedovanzaPortletController.class);

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
		log.debug("homePage-richiestaCertificatoVedovanzaPortletController");
		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditManager auditManager= AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta("CERTIFICATO VEDOVANZA")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addEsitoSuccess("SUCCESS")
					.addServizioTimeString()
					.addServizioUuidTransazione(uuidOperazione)
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
					.setOrigin(Origin.getIp(portletRequest))
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
		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);

		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, portletRequest, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO VEDOVANZA")
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();

	 
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		DatiAnagrafici dati = visureService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(portletRequest));

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
		
		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		
		if(userPreferences.getIdDelega() != null) {
			Delega delega =delegaService.getDelegaByPk(userPreferences.getIdDelega());
			model.addAttribute("firma",delega.getFirmaGrafometrica());
		}
		else
			model.addAttribute("firma",false);
		
		auditManager.build();
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "cambio=Cambia soggetto")
	public void cambioSoggetto(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response,PortletRequest request, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati)
			throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);
		AuditManager auditManager= AuditConfiguration.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CERTIFICATO VEDOVANZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(request));

		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
				model.addAttribute("componenteFamiglia", componente);
				break;
			}
		}
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		
		if(userPreferences.getIdDelega() != null) {
			Delega delega =delegaService.getDelegaByPk(userPreferences.getIdDelega());
			model.addAttribute("firma",delega.getFirmaGrafometrica());
		}
		else
			model.addAttribute("firma",false);

		try {
			model.addAttribute("wacomSignatureSdk", PrefsPropsUtil.getString("wacom.signature.sdk"));
		} catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}
		
		auditManager.build();
		response.setRenderParameter("action", "renderDatiAnagrafici");
	}

	@ResourceMapping("certificatoVedovanzaReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response,PortletRequest portletRequest, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {
		log.debug("generaCertificatovedovanza cf= ..." + codFisc);
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("CERTIFICATO VEDOVANZA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		ComponentiNucleoFamiliare componenteSel = null;
		List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
		for (int i = 0; i < componenti.size(); i++) {
			ComponentiNucleoFamiliare componente = componenti.get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}
		if (componenteSel.getStatoCivile() != null && (componenteSel.getStatoCivile().equalsIgnoreCase("vedovo") || componenteSel.getStatoCivile().equalsIgnoreCase("vedova"))) {

			DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
			richDatiAna.setCodiceFiscale(codFisc);
			DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

			String osapulieHost = request.getServerName();

			String reportFileName = String.format("certificato-vedovanza.pdf");
			byte[] certificatoTimbrato = null;
			switch (this.stampVersion(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE),codFisc)){
				case 0:
					String datiCert = templateCertificatiHelper.componiXml(dati, componenteSel, "", null, null, "certificatovedovanza", helper.getUserPreferences(request),
							PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE), osapulieHost);
					certificatoTimbrato = timbroService.richiediCertificatoTimbrato(datiCert, helper.getUserPreferences(request));
					break;
				case 1:
					certificatoTimbrato = timbroService.richiediCertificatoTimbrato(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA,codFisc, helper.getUserPreferences(request),null,null,componenteSel, dati);
					break;
				default:
					break;
			}
			// memorizzo la richiesta

			Fascicolo fascicolo = new Fascicolo();
			fascicolo.setIdProfiloUtente(profiloUtente);
			fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()));
			fascicolo.setUserPreferences(helper.getUserPreferences(request));
			fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE));
			fascicolo.setRicercabileDaComune(false);
			fascicolo.setNumeroProtocollo(null);
			fascicolo.setChecksum(null);

			fascicoloService.saveNuovaRichiesta(fascicolo);

			
			final String uuidOperazione=(String)session.getAttribute("UUID");
			
			DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
			
			//MS
			tempiMediDto = DwhTempiMediUtil.get(dwhService)
			.go_EndTime(uuidOperazione)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
			.setNomeServizio("RICHIESTA CERTIFICATO VEDOVANZA")
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
				.addOperazioneRichiesta("RICHIESTA CERTIFICATO VEDOVANZA - STEP FINALE DOWNLOAD DOCUMENTO ")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
				.setOrigin(Origin.getIp(request))
				.addCallGeo()
				.build();
			
			
			
			// consentire il download del documento.
			helper.sendStreamAsAttachment(certificatoTimbrato, response, reportFileName, ContentMimeTypes.PDF);

		}
		else {
		}
		auditManager.
		addFineOperazione()
		.addEsitoSuccess()
		.build();
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
		return toLocalViewPath("home");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/richiestacertificatovedovanza/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestacertificatovedovanza/" + viewName;
	}

	@ResourceMapping("reportPdf")
	public void reportPdf(@RequestParam("codFisc") String codFisc, Model model, ResourceResponse response, PortletSession session, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			ResourceRequest request) throws Exception {

		log.debug("certificatoVedovanzaReportPdfURL cf= ..." + codFisc);

		ComponentiNucleoFamiliare componenteSel = null;
		for (int i = 0; i < dati.getComponentiNucleoFamiliare().size(); i++) {
			ComponentiNucleoFamiliare componente = dati.getComponentiNucleoFamiliare().get(i);
			if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
				componenteSel = componente;
				break;
			}
		}

		/*
		 * L'oggetto datiUte serve a recuperare le informazioni riguardanti via, comune, cap e
		 * provincia di residenza
		 */
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codFisc);
		DatiUtente datiUte = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, helper.getUserPreferences(request));

		String via = (dati.getToponimoIndirizzo() != null && !dati.getToponimoIndirizzo().equals("") ? " " + dati.getToponimoIndirizzo() : "") + " " + dati.getDescrizioneVia() + " n."
				+ dati.getNumeroCivico() + (dati.getEsponente() != null && !dati.getEsponente().equals("") ? " " + dati.getEsponente() : "")
				+ (dati.getScala() != null && !dati.getScala().equals("") ? " scala " + dati.getScala() : "")
				+ (dati.getPiano() != null && !dati.getPiano().equals("") ? " piano " + dati.getPiano() : "");

		List<ComponentiNucleoFamiliare> beans = new ArrayList<ComponentiNucleoFamiliare>();
		beans.add(componenteSel);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("comune", helper.getUserPreferences(request).getNomeComune());
		param.put("viaRes", via);

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

		if (datiUte != null) {
			param.put("capRes", datiUte.getCap());
			param.put("provRes", datiUte.getProvinciaResidenza());
			param.put("comuneRes", datiUte.getComuneResidenza());
		}

		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
		.setNomeServizio("RICHIESTA CERTIFICATO VEDOVANZA")
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
			.addOperazioneRichiesta("RICHIESTA CERTIFICATO VEDOVANZA - STEP FINALE DOWNLOAD REPORT_PDF ")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE))
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		
		String report_path = "/reports/old/richiestaCertificatoVedovanza.jrxml";
		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA.get(PortletConstants.CODICE), report_path,
				null);

		// download del pdf
		String reportFileName = String.format("certificatovedovanza.pdf");
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}
}
