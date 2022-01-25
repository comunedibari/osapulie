package it.osapulie.tributi.web.portlet.variazionetasi.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.XMLHelper;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form.DatiDichiarazioneTassaImmobili.PosizioniTassaImmobiliDichiarazione;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.portlet.variazionetasi.form.DatiVariazioneTasi;
import it.osapulie.tributi.web.portlet.varie.Componente;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
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
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio di variazione TASI.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 * @author Antonio Abbinante
 */
@Controller("variazioneTasiPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiVariazioneTasi", "param", "uploadItem" })
public class VariazioneTasiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(VariazioneTasiPortletController.class);

	private static final String SERVIZIO = "VARIAZIONE TASI";

	private static final String REPORT_PATH = "/reports/variazioneTasi.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/variazioneTasi_subreport_immobili.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/variazioneTasi_subreport_familiari.jrxml";
	private static final String REPORT_NAME = "variazioneTASI.pdf";

	private static final String JSP_PATH = "variazionetasi/";

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
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	@Named("datiVariazioneTasiValidator")
	private Validator variazioneTasiValidator;

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
		log.debug("homePage-variazioneTasiPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la variazione TASI.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getVariazioneTasiForm")
	public void getVariazioneTasiForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getVariazioneTasiForm");

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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		try {
			DatiUtente datiGen = null;
			ComponentiNucleoFamiliare componente = null;
			try {
				DatiAnagrafici dati = tributiService.richiediDatiAnagrafici(richiesta, helper.getUserPreferences(request));

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				datiGen = tributiService.richiediDatiAnagraficiAltriServizi(richiestaGen, helper.getUserPreferences(request));
				componente = null;
				for (int k = 0; k < dati.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(dati.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = dati.getComponentiNucleoFamiliare().get(k);
					}
				}
			}
			catch (Exception e) {
				log.warn("getVariazioneTasiForm :: " + e.getMessage());
			}

			DatiVariazioneTasi datiVariazioneTasi = new DatiVariazioneTasi();
			if (componente != null && datiGen != null) {
				datiVariazioneTasi.setCodiceFiscale(componente.getCodiceFiscale());
				datiVariazioneTasi.setNome(componente.getNome());
				datiVariazioneTasi.setCognome(componente.getCognome());
				datiVariazioneTasi.setSesso(componente.getSesso());
				datiVariazioneTasi.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
				// Caricamento comune da codice ISTAT
				if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
					Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
					datiVariazioneTasi.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
					datiVariazioneTasi.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
				}
				datiVariazioneTasi.setIndirizzoResidenza(datiGen.getIndirizzo());
				datiVariazioneTasi.setComuneResidenza(datiGen.getComuneResidenza());
				datiVariazioneTasi.setProvResidenza(datiGen.getProvinciaResidenza());
				datiVariazioneTasi.setCapResidenza(datiGen.getCap());
			}
			else {
				// Caricamento dati da utente in OSApulie
				ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
				datiVariazioneTasi.setCodiceFiscale(profiloUtente.getCodiceFiscale());
				datiVariazioneTasi.setNome(profiloUtente.getNome());
				datiVariazioneTasi.setCognome(profiloUtente.getCognome());

				// Caricamento comune da codice ISTAT
				ComuneISA comuneIsa = profiloUtente.getComuneIsa();
				if (comuneIsa != null) {
					Comune comune = comuneIsa.getComune();
					if (comune != null) {
						datiVariazioneTasi.setComuneNascita(comune.getDenominazione());
						datiVariazioneTasi.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
					}
				}
				it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
				if (residenza != null) {
					datiVariazioneTasi.setIndirizzoResidenza(residenza.getVia());
					Comune comune = residenza.getComune();
					if (comune != null) {
						datiVariazioneTasi.setComuneResidenza(comune.getDenominazione());
						Provincia provincia = comune.getProvincia();
						if (provincia != null) {
							datiVariazioneTasi.setProvResidenza(provincia.getDenominazioneProvincia());
						}
					}
				}
				PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			}

			// TODO AGGIUNGERE CAMPI CHE PER ORA NON ARRIVANO TIPO EMAIL E
			// VERIFICARE SE GLI IMMOBILI DA PREVALORIZZARE SONO GLI STESSI DEL FORM DI
			// DICHIARAZIONE IMU/TASI
			DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();
			dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(codiceFiscale);
			// recupero i dati dell'anno precedente e di quello corrente; se non ci sono dati per
			// l'anno corrente utilizzo gli anni precedebti ed informo l'utente
			Integer annoCorrente = new Integer(DateUtils.getAnnoCorrente());
			dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(annoCorrente - 5);
			dichiarazioneTassaImmobiliRichiesta.setAnnoFine(annoCorrente);

			DichiarazioneTassaImmobiliRisposta visuraTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);

			List<DatiTassaImmobili> situazioni = visuraTassaImmobiliRisposta.getSituazione();
			if (situazioni != null && !situazioni.isEmpty()) {
				// Prelevo l'ultimo anno
				DatiTassaImmobili situazione = situazioni.get(situazioni.size() - 1);

				List<Posizioni> posizioni = situazione.getPosizioni();
				List<PosizioniTassaImmobiliDichiarazione> posizioniVariazioneTasi = new ArrayList<PosizioniTassaImmobiliDichiarazione>();
				if (posizioni != null) {
					for (Posizioni p : posizioni) {
						PosizioniTassaImmobiliDichiarazione posizione = new PosizioniTassaImmobiliDichiarazione();
						posizione.setIndirizzoUtenza(p.getIndirizzoUtenza());
						posizione.setCaratteristicaImmobile(p.getCaratteristicaImmobile());
						posizione.setNumero(p.getNumero());
						posizione.setSezione(p.getSezione());
						posizione.setFoglio(p.getFoglio());
						posizione.setParticella(p.getParticella());
						posizione.setSubalterno(p.getSubalterno());
						posizione.setValoreImmobile(p.getValoreImmobile());
						posizione.setCategoriaImmobile(p.getCategoriaImmobile());
						posizione.setPercentualePossesso(p.getPercentualePossesso());
						posizione.setMesiPossesso(p.getMesiPossesso());
						posizione.setMesiDetrazione(p.getMesiDetrazione());
						posizione.setImportoDetrazioneAbPrincipale(p.getImportoDetrazioneAbPrincipale());
						posizione.setPosseduto3112(p.isPosseduto3112());
						posizione.setAbitazionePrincipale(p.isAbitazionePrincipale());
						posizione.setRiduzione(p.isRiduzione());
						// TODO Perchè qui a differenza della dichiarazione non esiste la differenza
						// tra immobili totalmente
						// imponibili e immobili parzialmente imponibili o esenti??
						posizioniVariazioneTasi.add(posizione);
					}

					datiVariazioneTasi.setPosizioni(posizioniVariazioneTasi);
				}
				model.addAttribute("situazione", situazione);
				model.addAttribute("annoCorrente", annoCorrente);
			}
			model.addAttribute("datiVariazioneTasi", datiVariazioneTasi);

		}
		catch (Exception e) {
			log.error("getVariazioneTasiForm :: " + e.getMessage(), e);
		}

		response.setRenderParameter("action", "renderVariazioneTasiForm");
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
	 * @param request
	 * @param model
	 * @param selectNumAllegati
	 * @throws PortletException
	 * @throws IOException
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiVariazioneTasi") DatiVariazioneTasi datiVariazioneTasi) throws Exception {

		log.debug("uploadFile");
		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
		// model.addAttribute("firma", signedFiles);
		if (selectNumAllegati == null) {
			// TODO verificare da dove ricavare questo valore
			// uploadItem.setSignedFiles(signedFiles);
			validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI);

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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				// Generazione xml per interoperabilità
				MultipartFile multipartFileForInterop = generateMultipartFileForInterop(datiVariazioneTasi);
				uploadItem.getMultipartFiles().add(multipartFileForInterop);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					Fascicolo fascicoloProtocollo = new Fascicolo();
					fascicoloProtocollo.setIdProfiloUtente(profiloUtente);
					fascicoloProtocollo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(request));
					fascicoloProtocollo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI);
					fascicoloProtocollo.setRicercabileDaComune(true);
					fascicoloProtocollo.setNumeroProtocollo(numeroProtocollo);
					fascicoloProtocollo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicoloProtocollo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					UserPreferences userPreferences = helper.getUserPreferences(request);
					tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				}
				catch (Exception e) {
					log.error("upload :: " + e.getMessage(), e);
				}

final String uuidOperazione=(String)session.getAttribute("UUID");
				
				
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI)
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
	 * Metodo che prende i campi inseriti nella form e li mette in sessione.
	 *
	 * @param datiVariazioneTasi
	 * @param result
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaDichiarazione")
	public void compilaDichiarazione(@ModelAttribute("datiVariazioneTasi") DatiVariazioneTasi datiVariazioneTasi, BindingResult result, Model model,
			@RequestParam(required = false, value = "aggiungiFamiliare") String aggiungiFamiliare, @RequestParam(required = false, value = "rimuoviFamiliareIndex") String rimuoviFamiliare,
			ActionResponse response, ActionRequest request) throws Exception {

		log.debug("compilaDichiarazione CF=" + datiVariazioneTasi.getCodiceFiscale());

		if (aggiungiFamiliare != null && !aggiungiFamiliare.equalsIgnoreCase("")) {
			log.debug("aggiungiContitolare");
			datiVariazioneTasi.getFamiliari().add(new Componente());
			model.addAttribute("datiVariazioneTasi", datiVariazioneTasi);
		}
		else if (rimuoviFamiliare != null && !rimuoviFamiliare.equalsIgnoreCase("")) {
			log.debug("rimuoviContitolare");
			int i = new Integer(rimuoviFamiliare);
			datiVariazioneTasi.getFamiliari().remove(i);
			model.addAttribute("datiVariazioneTasi", datiVariazioneTasi);
		}
		else {

			variazioneTasiValidator.validate(datiVariazioneTasi, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			}
			else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", helper.getUserPreferences(request).getNomeComune());
				model.addAttribute("datiVariazioneTasi", datiVariazioneTasi);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
			}
		}
		response.setRenderParameter("action", "renderVariazioneTasiForm");

	}

	/**
	 * Metodo che genera il certificato e ne consente il download.
	 *
	 * @param datiVariazioneTasi
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("variazioneTasiReport")
	public void serveCertificato(@ModelAttribute("datiVariazioneTasi") DatiVariazioneTasi datiVariazioneTasi, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {

		log.debug("serveCertificato :: entering method");

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		// Query the service layer for some objects
		List<DatiVariazioneTasi> beans = new ArrayList<DatiVariazioneTasi>();

		beans.add(datiVariazioneTasi);
		param.put("subreportParameters1", datiVariazioneTasi.getPosizioni());
		param.put("subreportParameters2", datiVariazioneTasi.getFamiliari());

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta

		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_VARIAZIONE_TASI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

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
	 * Presenta la form per la comunicazione ici
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderVariazioneTasiForm")
	public String renderVariazioneTasiForm(Model model) throws Exception {
		return toLocalViewPath("variazioneTasi");
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
	 * "/variazionetasi/home" </code>
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
	private MultipartFile generateMultipartFileForInterop(DatiVariazioneTasi datiVariazione) throws ParseException {

		String xml = null;

		// VariazioneTASIInterop interop = new VariazioneTASIInterop();
		//
		// // Contribuente
		// Soggetto contribuente = new Soggetto();
		// contribuente.setNome(datiVariazione.getNome());
		// contribuente.setCognome(datiVariazione.getCognome());
		// contribuente.setSesso(datiVariazione.getSesso());
		// contribuente.setCodiceFiscale(datiVariazione.getCodiceFiscale());
		// contribuente.setComuneNascita(datiVariazione.getComuneNascita());
		// if (datiVariazione.getDataNascita() != null) {
		// Calendar calendar = new GregorianCalendar();
		// calendar.setTime(simpleDateFormat.parse(datiVariazione.getDataNascita()));
		// contribuente.setDataNascita(calendar);
		// }
		// contribuente.setProvinciaNascita(datiVariazione.getProvinciaNascita());
		// contribuente.setEmail(datiVariazione.getEmail());
		// contribuente.setTelefono(datiVariazione.getTelefono());
		// interop.setContribuente(contribuente);
		//
		// // Indirizzo Contribuente
		// // Indirizzo residenzaContribuente = new Indirizzo();
		// // // TODO CODICE ISTAT
		// // residenzaContribuente.setComune(new Codifica(null,
		// datiVariazione.getComuneResidenza()));
		// // // residenzaContribuente.setProvincia(datiVariazione.getProvResidenza());
		// // residenzaContribuente.setCap(datiVariazione.getCapResidenza());
		// // residenzaContribuente.setViaNonCodificata(datiVariazione.getIndirizzoResidenza());
		// // residenzaContribuente.setScala(datiVariazione.getScala());
		// // residenzaContribuente.setPiano(datiVariazione.getPiano());
		// // residenzaContribuente.setInterno(datiVariazione.getInterno());
		// // interop.setResidenzaContribuente(residenzaContribuente);
		//
		// // Dichiarante
		// Dichiarante dichiarante = new Dichiarante();
		// dichiarante.setNome(datiVariazione.getNomeDic());
		// dichiarante.setCognome(datiVariazione.getCognomeDic());
		// dichiarante.setSesso(datiVariazione.getSessoDic());
		// dichiarante.setNaturaCarica(datiVariazione.getNaturaCarica());
		// if (datiVariazione.getDataNascitaDic() != null) {
		// Calendar calendar = new GregorianCalendar();
		// calendar.setTime(simpleDateFormat.parse(datiVariazione.getDataNascitaDic()));
		// dichiarante.setDataNascita(calendar);
		// }
		// dichiarante.setComuneNascita(datiVariazione.getComuneNascitaDic());
		// dichiarante.setProvinciaNascita(datiVariazione.getProvinciaNascitaDic());
		// dichiarante.setCodiceFiscale(datiVariazione.getCodiceFiscaleDic());
		// dichiarante.setTelefono(datiVariazione.getTelefonoDic());
		// dichiarante.setEmail(datiVariazione.getEmailDic());
		// interop.setDichiarante(dichiarante);
		//
		// // Indirizzo Dichiarante
		// // IndirizzoCompleto residenzaDichiarante = new IndirizzoCompleto();
		// // residenzaDichiarante.setViaNonCodificata(datiVariazione.getIndirizzoResidenzaDic());
		// // residenzaDichiarante.setCap(datiVariazione.getCapResidenzaDic());
		// // residenzaDichiarante.setCivico(Integer.parseInt(datiVariazione.getCivicoDic()));
		// // // TODO codice ISTAT
		// // residenzaDichiarante.setComune(new Codifica(null,
		// // datiVariazione.getComuneResidenzaDic()));
		// // // residenzaDichiarante.setProvincia(datiVariazione.getProvResidenzaDic());
		// // interop.setResidenzaDichiarante(residenzaDichiarante);
		//
		// // Immobili
		// List<PosizioniTassaImmobiliDichiarazione> immobili = datiVariazione.getPosizioni();
		// if (immobili != null) {
		// for (PosizioniTassaImmobiliDichiarazione p : immobili) {
		// PosizioneTotalmenteImponibile posizione = new PosizioneTotalmenteImponibile();
		// // TODO codice caratteristica immobili
		// posizione.setCaratteristicaImmobile(new Codifica(null, p.getCaratteristicaImmobile()));
		// posizione.setCategoriaImmobile(p.getCategoriaImmobile());
		// IndirizzoUtenzaImmobile indirizzo = new IndirizzoUtenzaImmobile();
		// indirizzo.setIndirizzo(PortletUtils.getIndirizzoFromPosizione(p));
		// posizione.setIndirizzoUtenzaImmobile(indirizzo);
		// posizione.setCodiceCatasto(p.getCodiceCatasto());
		// posizione.setTipoCatasto(p.getTipoCatasto());
		// posizione.setSezione(p.getSezione());
		// posizione.setFoglio(p.getFoglio() + "");
		// posizione.setParticella(p.getParticella() + "");
		// posizione.setSubalterno(p.getSubalterno() + "");
		// posizione.setCategoriaImmobile(p.getCategoriaImmobile());
		// posizione.setClasse(p.getClasse());
		// if (p.getAnno() != null) {
		// Calendar dataProcollo = Calendar.getInstance();
		// dataProcollo.set(Calendar.YEAR, Integer.parseInt(p.getAnno()));
		// posizione.setDataProtocollo(dataProcollo);
		// }
		// posizione.setInagibile(p.isInagibile());
		// posizione.setValoreImmobile(p.getValoreImmobile());
		// posizione.setPercentualePossesso(p.getPercentualePossesso());
		// if (p.getDataInizioPossesso() != null) {
		// Calendar calendar = new GregorianCalendar();
		// calendar.setTime(simpleDateFormat.parse(p.getDataInizioPossesso()));
		// posizione.setDataInizioPossesso(calendar);
		// }
		// posizione.setDiAcquisto(p.isDiAcquisto());
		// posizione.setDiCessione(p.isDiCessione());
		// posizione.setAgenziaEntrate(p.getAgenziaEntrate());
		// posizione.setEstremiTitolo(p.getEstremiTitolo());
		// posizione.setAnnotazioni(p.getAnnotazioni());
		// interop.getImmobili().add(posizione);
		// }
		// }
		//
		// // Coabitanti
		// List<Componente> coabitanti = datiVariazione.getFamiliari();
		// if (coabitanti != null) {
		// for (Componente c : coabitanti) {
		// it.osapulie.tributi.web.ws.input.types.Familiare familiare = new
		// it.osapulie.tributi.web.ws.input.types.Familiare();
		// familiare.setNome(c.getNome());
		// familiare.setCognome(c.getCognome());
		// familiare.setSesso(c.getSesso());
		// familiare.setComuneNascita(c.getComuneNascita());
		// familiare.setRelParentela(c.getParentela());
		// }
		// }
		// interop.setAnnotazioni(datiVariazione.getNote());
		// xml = xmlHelper.marshal(interop);

		MultipartFile multipartFile = new MockMultipartFile(PortletConstants.INTEROP_FILE_NAME, PortletConstants.INTEROP_FILE_NAME, "text/xml", xml.getBytes());
		return multipartFile;
	}

}
