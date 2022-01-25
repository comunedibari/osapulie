package it.osapulie.anagrafe.web.portlet.produrreautocertificazioni.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.service.AnagrafeCommonService;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.anagrafe.service.VisureService;
import it.osapulie.anagrafe.web.portlet.produrreautocertificazioni.form.DatiAutocertificazione;
import it.osapulie.anagrafe.web.portlet.util.PortletUtils;
import it.osapulie.anagrafe.web.portlet.util.impl.PortletConstants;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.persistence.AuditRepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceUnreachableException;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.SPID;
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
 * Portlet controller per il servizio produrre autocertificazioni.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("produrreAutocertificazioniPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiAnagrafici", "datiUtente", "autocertificazione", "datiAutocertificazione" })
public class ProdurreAutocertificazioniPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ProdurreAutocertificazioniPortletController.class);

	public static final String SERVIZIO = "AUTOCERTIFICAZIONE";

	public static final String REPORT_PATH = "/reports/autocertificazione.jrxml";
	public static final String REPORT_NAME = "autocertificazione.pdf";

	public static final String JSP_PATH = "produrreautocertificazioni/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private VisureService visureService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ReportService reportService;

	@Inject
	@Named("datiAutocertificazioneValidator")
	private Validator datiAutocertificazioneValidator;

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

		log.debug("homePage-produrreAutocertificazioniPortletController");

		UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		log.debug("userCF = " + codiceFiscale);
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);
		DatiAnagraficiGenerali richDatiAna = new DatiAnagraficiGenerali();
		richDatiAna.setCodiceFiscale(codiceFiscale);

		DatiUtente datiUtente = new DatiUtente();
		DatiAnagrafici dati = new DatiAnagrafici();
		ComponentiNucleoFamiliare componente = new ComponentiNucleoFamiliare();
		try {
			dati = visureService.richiediDatiAnagrafici(richiesta, userPreferences);
			datiUtente = visureService.richiediDatiAnagraficiAltriServizi(richDatiAna, userPreferences);
			List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
			for (int i = 0; i < componenti.size(); i++) {
				componente = componenti.get(i);
				if (codiceFiscale != null && codiceFiscale.equals(componente.getCodiceFiscale())) {
					anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
					break;
				}
			}
		}
		catch (ServiceUnreachableException e) {
			log.warn("homePage :: " + e.getMessage());
		}

		// Caricamento dati da dati locali al sistema
		if (componente == null || componente.getCodiceFiscale() == null) {
			setDatiFromSystem(componente, dati, datiUtente, codiceFiscale);
		}

		model.addAttribute("tipologieMap", getTipologieMap());
		model.addAttribute("datiAnagrafici", dati);
		model.addAttribute("datiUtente", datiUtente);
		model.addAttribute("datiAutocertificazione", getDatiAutocertificazione(componente, dati, datiUtente));
		
		return toLocalViewPath("home");
	}

	@ActionMapping(params = "ope=cambioSoggetto")
	public void cambioSoggetto(@ModelAttribute("datiAnagrafici") DatiAnagrafici dati, @ModelAttribute("datiUtente") DatiUtente datiUtente,
			@RequestParam(required = false, value = "codFisc") String codFisc, @ModelAttribute("datiAutocertificazione") DatiAutocertificazione datiAutocertificazione,
			@RequestParam(required = false, value = "cambio") String cambio, Model model, ActionResponse actionResponse, PortletSession session) throws Exception {
		log.debug("cambioSoggetto cf= ..." + codFisc);
		
		if (cambio != null) {
			ComponentiNucleoFamiliare componente = null;
			if (dati != null) {
				List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
				for (int i = 0; i < componenti.size(); i++) {
					componente = componenti.get(i);
					if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
						anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
						model.addAttribute("componenteFamiglia", componente);
						break;
					}
				}
			}
			model.addAttribute("datiAutocertificazione", getDatiAutocertificazione(componente, dati, datiUtente));
		}

		actionResponse.setRenderParameter("action", "renderDatiAnagrafici");
	}
	 
	@ActionMapping(params = "ope=generaAutocertificazione")
	public void compilaAutocertificazione(@RequestParam("codFisc") String codFisc, Model model, ActionResponse response, ActionRequest request, 
			@RequestParam("tipologiaCertificato") String tipologiaCertificato, @ModelAttribute("datiAnagrafici") DatiAnagrafici dati,
			@ModelAttribute("datiAutocertificazione") DatiAutocertificazione datiAutocertificazione, BindingResult result,
			@RequestParam(required = false, value = "carica_modello") String carica_modello, PortletSession session, PortletRequest portletRequest) throws Exception {

 
		log.debug("generaAutocertificazione cf= ..." + codFisc);

		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();

 
		try {
			ComponentiNucleoFamiliare componente = null;
			if (dati != null) {
				List<ComponentiNucleoFamiliare> componenti = dati.getComponentiNucleoFamiliare();
				for (int i = 0; i < componenti.size(); i++) {
					componente = componenti.get(i);
					if (codFisc != null && codFisc.equals(componente.getCodiceFiscale())) {
						anagrafeCommonService.addInfoAggiuntiveComponenteToModel(model, componente);
						model.addAttribute("componenteFamiglia", componente);
						break;
					}
				}
			}

			if (carica_modello == null && !datiAutocertificazione.getTipologia().equals("")) {
				datiAutocertificazioneValidator.validate(datiAutocertificazione, result);

				if (result.hasErrors()) {
					for (ObjectError error : result.getAllErrors()) {
						log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
					}

					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					response.setRenderParameter("action", "renderDatiAnagrafici");
				}
				else {

					model.addAttribute("download", "si");
					Map<String, Object> autocertificazione = new HashMap<String, Object>();
					// Caricamento da messages
					autocertificazione.put("tipologia",
							messageSource.getMessage("label.header.pdf." + datiAutocertificazione.getTipologia(), null, datiAutocertificazione.getTipologia(), request.getLocale()));
					// autocertificazione.put( "dichiarazione", dichiarazione );
					String modello = messageSource.getMessage("modello." + datiAutocertificazione.getTipologia(), generaArrayArgomenti(datiAutocertificazione), Locale.ITALIAN);
					autocertificazione.put("dichiarazione", modello);
					model.addAttribute("autocertificazione", autocertificazione);
				}
			}
			else {
				Map<String, Object> autocertificazione = new HashMap<String, Object>();
				datiAutocertificazione.setTipologia(tipologiaCertificato);

				model.addAttribute("autocertificazione", autocertificazione);
				model.addAttribute("datiAutocertificazione", datiAutocertificazione);
			
				String uuidOperazione=UUID.randomUUID().toString();
				session.setAttribute("UUID", uuidOperazione);
				
				DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
				DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
				.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
				.setUUID(uuidOperazione)
				.getServizioAttribute();
				
				dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
				.setUuidOperazione(uuidOperazione)
				.getDatamining();
				
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
				.setNomeServizio(SERVIZIO)
				.setUuid(uuidOperazione).go_StartTime().getTempiMedi();
				
				auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);

				
				AuditConfiguration
				.configure()
				.audit()
				.addInizioOperazione()
				.addUuidOperazione(uuidOperazione)
				.addOperazioneRichiesta("AUTOCERTIFICAZIONE - "+tipologiaCertificato+"  - START")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.addEsitoSuccess("SUCCESS")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
				.setOrigin(Origin.getIp(request))
				.addCallGeo()
				.build();
	
				
				
			}
		
			response.setRenderParameter("action", "renderDatiAnagrafici");
		}
		catch (Exception e) {
			log.error("compilaAutocertificazione :: " + e.getMessage(), e);
		 
		}
	 
	}

	@ResourceMapping("autocertificazioneReport")
	public void serveCertificato(@RequestParam("codFisc") String codFisc, Model model, ResourceRequest request, ResourceResponse response, PortletSession session,
			@ModelAttribute("datiAutocertificazione") DatiAutocertificazione datiAutocertificazione, @ModelAttribute("autocertificazione") Map<String, Object> autocertificazione, PortletRequest porteltRequest) throws Exception {
		
		log.debug("autocertificazioneReport cf= ..." + codFisc);
 	
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiAutocertificazione> beans = new ArrayList<DatiAutocertificazione>();
		beans.add(datiAutocertificazione);

		// Create the report
		byte[] reportBytes = reportService.jrxmlToPdf(autocertificazione, beans, helper.getUserPreferences(request).getIdComuneIsa(), PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI, REPORT_PATH,
				null);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio", null, request.getLocale()) + " " + autocertificazione.get("tipologia"));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(null);		
		fascicoloService.saveNuovaRichiesta(fascicolo);
		 
		
		final String uuidOperazione=(String)session.getAttribute("UUID");
 
		DwhTempiMediDTO dwhTempiMediDTO = new DwhTempiMediDTO();
		//MS
		dwhTempiMediDTO = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
		.setNomeServizio("AUTOCERTIFICAZIONE - "+ autocertificazione.get("tipologia"))
		.getTempiMedi();
		
		auditDwhService.invokeAuditService(null, null, dwhTempiMediDTO);

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
			.addOperazioneRichiesta("AUTOCERTIFICAZIONE - "+ autocertificazione.get("tipologia")+"  - STEP FINALE DOWNLOAD DOCUMENTO ")
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_AUTOCERTIFICAZIONI)
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		
		
		// consentire il download del documento.
		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
 
	}

	/**
	 *
	 * @param componente
	 * @param dati
	 * @param datiUtente
	 * @param codiceFiscale
	 * @throws PortalException
	 * @throws SystemException
	 */
	private void setDatiFromSystem(ComponentiNucleoFamiliare componente, DatiAnagrafici dati, DatiUtente datiUtente, String codiceFiscale) throws PortalException, SystemException {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		ProfiloUtenteCittadino profiloUtente = osApulieUserDetails.getProfiloUtenteCittadino();

		componente.setCodiceFiscale(codiceFiscale);
		componente.setNome(profiloUtente.getNome());
		componente.setCognome(profiloUtente.getCognome());

		// SPID
		if (osApulieUserDetails.getAuthenticationChannel().equals(AuthenticationChannel.SPID)) {
			JsonParser parser = new JsonParser();
			JsonObject jsonObject = parser.parse(profiloUtente.getInfoAggiuntive()).getAsJsonObject();
			if (jsonObject.has(SPID.ATTRIBUTE_DATEOFBIRTH.toString())) {
				Calendar calendar = new GregorianCalendar();
				calendar.setTime(DateUtils.getData(jsonObject.get(SPID.ATTRIBUTE_DATEOFBIRTH.toString()).getAsString(), "yyyy-MM-dd"));
				componente.setDataNascita(calendar);
			}
			if (jsonObject.has(SPID.ATTRIBUTE_PLACEOFBIRTH.toString())) {
				Comune comuneNascita = comuneService.getComuneByCodiceCatastale(jsonObject.get(SPID.ATTRIBUTE_PLACEOFBIRTH.toString()).getAsString());
				componente.setCodiceIstatComuneNascita(comuneNascita.getCodiceIstat1());

			}
		}
		else {
			User liferayUser = osApulieUserDetails.getLiferayUser();
			Date birthday = liferayUser.getBirthday();
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(birthday);
			componente.setDataNascita(calendar);
		}
		// + informazioni comuni agli altri canali
		Indirizzo residenza = profiloUtente.getResidenza();
		if (residenza != null) {
			dati.setDescrizioneVia(residenza.getVia());
			if (residenza.getNrCivico() != null) {
				dati.setNumeroCivico(residenza.getNrCivico());
			}
			Comune comuneResidenza = residenza.getComune();
			if (comuneResidenza != null) {
				datiUtente.setComuneResidenza(comuneResidenza.getDenominazione());
			}
		}
	}

	/**
	 *
	 * @param componente
	 * @param datiAnagrafici
	 * @param datiUtente
	 * @return
	 */
	private DatiAutocertificazione getDatiAutocertificazione(ComponentiNucleoFamiliare componente, DatiAnagrafici datiAnagrafici, DatiUtente datiUtente) {

		DatiAutocertificazione datiAutocertificazione = new DatiAutocertificazione();
		datiAutocertificazione.setNomeRichiedente(componente.getNome());
		datiAutocertificazione.setCognomeRichiedente(componente.getCognome());
		datiAutocertificazione.setCodiceFiscaleRichiedente(componente.getCodiceFiscale());
		String codiceIstatComuneNascita = componente.getCodiceIstatComuneNascita();
		if (codiceIstatComuneNascita != null) {
			Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(codiceIstatComuneNascita);
			if (comuneByCodiceISTAT != null) {
				datiAutocertificazione.setComuneNascitaRichiedente(comuneByCodiceISTAT.getDenominazione());
				datiAutocertificazione.setProvinciaNascitaRichiedente(comuneByCodiceISTAT.getProvincia().getSigla());
			}
			// Ricerca in comuni esteri
			else {
				if (!codiceIstatComuneNascita.isEmpty()) {
					ComuneEstero comuneEsteroByCodice = comuneEsteroService.getComuneEsteroByCodice(Integer.parseInt(codiceIstatComuneNascita));
					if (comuneEsteroByCodice != null) {
						datiAutocertificazione.setComuneNascitaRichiedente(comuneEsteroByCodice.getDenominazione());
					}
				}
			}
		}

		Calendar dataNascita = componente.getDataNascita();
		if (dataNascita != null) {
			datiAutocertificazione.setDataNascitaRichiedente(DateUtils.getDataGGMMAAAA(dataNascita.getTime()));
		}
		if (datiAnagrafici != null) {
			datiAutocertificazione.setViaResidenzaRichiedente(datiAnagrafici.getDescrizioneVia());
		}

		if (datiUtente != null) {
			String codiceIstatResidenza = datiUtente.getCodiceIstatResidenza();
			Comune comuneResidenza = comuneService.getComuneByCodiceISTAT(codiceIstatResidenza);
			if (comuneResidenza != null) {
				datiAutocertificazione.setComuneResidenzaRichiedente(comuneResidenza.getDenominazione());
				Provincia provincia = comuneResidenza.getProvincia();
				if (provincia != null) {
					datiAutocertificazione.setProvinciaResidenzaRichiedente(provincia.getSigla());
				}
			}
		}

		return datiAutocertificazione;
	}

	/**
	 * @param datiUtente
	 * @return
	 */
	private DatiAutocertificazione getDatiAutocertificazione(DatiUtente datiUtente, String codiceFiscale) {

		DatiAutocertificazione datiAutocertificazione = new DatiAutocertificazione();
		datiAutocertificazione.setNomeRichiedente(datiUtente.getNome());
		datiAutocertificazione.setCognomeRichiedente(datiUtente.getCognome());
		datiAutocertificazione.setCodiceFiscaleRichiedente(codiceFiscale);
		datiAutocertificazione.setComuneNascitaRichiedente(datiUtente.getComuneNascita());
		datiAutocertificazione.setProvinciaNascitaRichiedente(datiUtente.getProvinciaNascita());
		datiAutocertificazione.setDataNascitaRichiedente(DateUtils.getDataGGMMAAAA(datiUtente.getDataNascita().getTime()));
		datiAutocertificazione.setComuneResidenzaRichiedente(datiUtente.getComuneResidenza());
		datiAutocertificazione.setProvinciaResidenzaRichiedente(datiUtente.getProvinciaResidenza());
		datiAutocertificazione.setViaResidenzaRichiedente(datiUtente.getIndirizzo());

		return datiAutocertificazione;
	}

	/**
	 * Presenta la visura a video.
	 *
	 * @param model
	 * @return l'ID della view per visualizzare i dati anagrafici
	 */
	@RenderMapping(params = "action=renderDatiAnagrafici")
	public String renderVisura(Model model, PortletRequest request, PortletSession session) {
		model.addAttribute("yearsMap", PortletUtils.getYearsMap());
		model.addAttribute("tipologieMap", getTipologieMap());
		log.debug("Model = " + model);
		return toLocalViewPath("home");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/visuraposizioneelettorale/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	/*
	 * Crea il vettore di stringhe da sostituire ai messages
	 */
	private String[] generaArrayArgomenti(DatiAutocertificazione dati) {

		return new String[] { dati.getNome(), dati.getCognome(), dati.getComuneNascita(), dati.getProvinciaNascita(), dati.getDataNascita(), dati.getComuneResidenza(), dati.getProvinciaResidenza(),
				dati.getViaResidenza(), dati.getCivicoResidenza(), dati.getParentela(), dati.getComuneMorte(), dati.getProvinciaMorte(), dati.getDataMorte(), dati.getCodiceFiscale(), dati.getAnno(),
				dati.getLeva(), dati.getAlbo(), dati.getIva(), dati.getReddito(), dati.getGenerica() };
	}

	private HashMap<String, String> getTipologieMap() {

		LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
		map.put("", "");
		map.put("ATTONOTORIO", "Atto notorio");
		map.put("FIGLIO", "Nascita figlio");
		map.put("DECESSO", "Decesso del coniuge, ascendente o discendente");
		map.put("REDDITO", "Situazione reddituale o economica");
		map.put("DISOCCUPAZIONE", "Stato di disoccupazione");
		map.put("LEVA", "Tutte le posizioni relative agli obblighi di leva");
		map.put("CONDANNE", "Di non aver riportato condanne penali");
		map.put("ALBI", "Iscrizione ad albi");
		map.put("CURATORE", "Di avere la qualita di curatore di");
		map.put("PIVA", "Possesso e numero di PIVA");
		map.put("CODFISC", "Possesso e numero di codice fiscale");
		map.put("GENERICA", "Dichiarazioni sostitutive generiche");

		return map;
	}
}
