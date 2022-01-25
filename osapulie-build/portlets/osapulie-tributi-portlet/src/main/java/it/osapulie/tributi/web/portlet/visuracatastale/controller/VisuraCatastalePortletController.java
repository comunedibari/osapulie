package it.osapulie.tributi.web.portlet.visuracatastale.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.catasto.domain.Fabbricato;
import it.osapulie.catasto.domain.Terreno;
import it.osapulie.catasto.service.FabbricatoService;
import it.osapulie.catasto.service.TerrenoService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
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
import it.osapulie.web.portlet.util.PortletHelper;

@Controller("visuraCatastalePortletController")
@RequestMapping("view")
public class VisuraCatastalePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VisuraCatastalePortletController.class);

	@Inject
	private PortletHelper helper;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private TerrenoService terrenoService;

	@Inject
	private FabbricatoService fabbricatoService;

	@Inject
	private TributiService tributiService;

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
	
	
	@Value("#{applicationProperties.segnalazioniPageUrl}")
	private String segnalazioniPageUrl;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare (<strong>elencoOpere</strong>).
	 */
	@RenderMapping
	public String homePage(Model model, PortletSession session, RenderRequest request) throws Exception {

		log.debug("homePage ");
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF=" + codiceFiscale);
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);
		DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
		richiestaGen.setCodiceFiscale(codiceFiscale);

		DatiUtente intestatario = tributiService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));
		List<Fabbricato> fabbricati = fabbricatoService.getFabbricatiByCf(codiceFiscale);
		List<Terreno> terreni = terrenoService.getTerreniByCf(codiceFiscale);

		model.addAttribute("intestatario", intestatario);
		model.addAttribute("fabbricati", fabbricati);
		model.addAttribute("terreni", terreni);

		
		final String uuidOperazione=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidOperazione);
		AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta("SERVIZIO VISURA CATASTO  - START")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addEsitoSuccess("SUCCESS")
					.setOrigin(Origin.getIp(request))
					.build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_CATASTO)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_CATASTO)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_CATASTO)
		.setNomeServizio("SERVIZIO VISURA CATASTO")
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();
		 
		 auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
			//MS
			tempiMediDto = DwhTempiMediUtil.get(dwhService)
			.go_EndTime(uuidOperazione)
			.setCodServizio(PortletConstants.CODICE_SERVIZIO_VISURA_CATASTO)
			.setNomeServizio("SERVIZIO VISURA CATASTO")
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
				.addOperazioneRichiesta("SERVIZIO VISURA CATASTO")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VISURA_CATASTO)
				.setOrigin(Origin.getIp(request))
				.addCallGeo()
				.build();
			
		
		
		
		return toLocalViewPath("mostraVisura");
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare il fascicolo
	 */
	@RenderMapping(params = "action=renderVisura")
	public String renderVisura(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("mostraVisura");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/fascicoloutente/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "visuracatastale/" + viewName;
	}
}
