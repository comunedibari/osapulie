package it.osapulie.tributi.web.portlet.rimborsotassarifiuti.controller;

import java.io.IOException;
import java.lang.reflect.Method;
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
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.service.UploadDichiarazioniService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiCommonService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.rimborsotassarifiuti.form.DatiRimborsoTassaRifiuti;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;
import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni;
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
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.web.portlet.controller.BaseController;
import it.osapulie.web.portlet.util.CheckSumGenerator;
import it.osapulie.web.portlet.util.FileUploadValidator;
import it.osapulie.web.portlet.util.UploadItem;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Richiesta rimborso tassa sui rifiuti.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("rimborsoTassaRifiutiPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiRimbTassaRifiuti", "param", "uploadItem" })
public class RimborsoTassaRifiutiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RimborsoTassaRifiutiPortletController.class);

	public static final String SERVIZIO = "RICHIESTA RIMBORSO TARI/TARES/TARSU";

	public static final String REPORT_PATH = "/reports/rimborsoTassaRifiuti.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/subreportRimborso.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/subreportRimborsoFooter.jrxml";
	public static final String REPORT_NAME = "rimborsoTassaRifiuti.pdf";

	public static final String JSP_PATH = "rimborsotassarifiuti/";

	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ReportService reportService;

	@Inject
	private UploadDichiarazioniService uploadService;

	@Inject
	private TributiService tributiService;

	@Inject
	private TributiCommonService tributiCommonService;

	@Inject
	@Named("fileUploadValidator")
	private FileUploadValidator validator;

	@Inject
	@Named("datiRimborsoTassaRifiutiValidator")
	private Validator rimborsoValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

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

		log.debug("homePage :: entering method");

		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la rimborso tassa rifiuti.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getRimborsoTassaRifiutiForm")
	public void getRimborsoTassaRifiutiForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getRimborsoTassaRifiutiForm :: entering method");

		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		String partitaIvaServizio = userPreferences.getPartitaIvaServizio();

		
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidOperazione).go_StartTime().getTempiMedi();	
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		RichiestaDatiAnagrafici richiesta = new RichiestaDatiAnagrafici();
		richiesta.setCodiceFiscale(codiceFiscale);

		try {
			DatiUtente datiGen = null;
			ComponentiNucleoFamiliare componente = null;
			try {
				DatiAnagrafici dati = tributiService.richiediDatiAnagrafici(richiesta, userPreferences);

				DatiAnagraficiGenerali richiestaGen = new DatiAnagraficiGenerali();
				richiestaGen.setCodiceFiscale(codiceFiscale);
				datiGen = tributiService.richiediDatiAnagraficiAltriServizi(richiestaGen, userPreferences);
				componente = null;
				for (int k = 0; k < dati.getComponentiNucleoFamiliare().size(); k++) {
					if (codiceFiscale.equals(dati.getComponentiNucleoFamiliare().get(k).getCodiceFiscale())) {
						componente = dati.getComponentiNucleoFamiliare().get(k);
					}
				}
			}
			catch (Exception e) {
				log.warn("getRimborsoTassaRifiutiForm :: " + e.getMessage());
			}

			DatiRimborsoTassaRifiuti datiRimbTassaRifiuti = new DatiRimborsoTassaRifiuti();

			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiRimbTassaRifiuti.setRagSociale(aziendaByPiva.getRagioneSociale());
				datiRimbTassaRifiuti.setpIva(partitaIvaServizio);
				datiRimbTassaRifiuti.setCodiceFiscaleGiu(partitaIvaServizio);

				Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiRimbTassaRifiuti.setSedeLegale(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiRimbTassaRifiuti.setProvincia(comune.getProvincia().getSigla());
						datiRimbTassaRifiuti.setComune(comune.getDenominazione());
					}
				}
				if (componente != null && datiGen != null) {
					datiRimbTassaRifiuti.setCodiceFiscaleRapp(componente.getCodiceFiscale());
					datiRimbTassaRifiuti.setNomeRapp(componente.getNome());
					datiRimbTassaRifiuti.setCognomeRapp(componente.getCognome());
					datiRimbTassaRifiuti.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbTassaRifiuti.setComuneNascitaRapp(comuneByCodiceISTAT.getDenominazione());
						datiRimbTassaRifiuti.setProvinciaNascitaRapp(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbTassaRifiuti.setSessoRapp(componente.getSesso());
					datiRimbTassaRifiuti.setIndirizzoResidenzaRapp(datiGen.getIndirizzo());
					datiRimbTassaRifiuti.setComuneResidenzaRapp(datiGen.getComuneResidenza());
					datiRimbTassaRifiuti.setProvResidenzaRapp(datiGen.getProvinciaResidenza());
					datiRimbTassaRifiuti.setCapResidenzaRapp(datiGen.getCap());

				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbTassaRifiuti.setCodiceFiscaleRapp(profiloUtente.getCodiceFiscale());
					datiRimbTassaRifiuti.setNomeRapp(profiloUtente.getNome());
					datiRimbTassaRifiuti.setCognomeRapp(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbTassaRifiuti.setComuneNascitaRapp(comune.getDenominazione());
							datiRimbTassaRifiuti.setProvinciaNascitaRapp(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbTassaRifiuti.setIndirizzoResidenzaRapp(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbTassaRifiuti.setComuneResidenzaRapp(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbTassaRifiuti.setProvResidenzaRapp(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
			}
			else {

				if (componente != null && datiGen != null) {
					datiRimbTassaRifiuti.setCodiceFiscale(componente.getCodiceFiscale());
					datiRimbTassaRifiuti.setNome(componente.getNome());
					datiRimbTassaRifiuti.setCognome(componente.getCognome());
					datiRimbTassaRifiuti.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbTassaRifiuti.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiRimbTassaRifiuti.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbTassaRifiuti.setSesso(componente.getSesso());
					datiRimbTassaRifiuti.setIndirizzoResidenza(datiGen.getIndirizzo());
					datiRimbTassaRifiuti.setComuneResidenza(datiGen.getComuneResidenza());
					datiRimbTassaRifiuti.setProvResidenza(datiGen.getProvinciaResidenza());
					datiRimbTassaRifiuti.setCapResidenza(datiGen.getCap());

				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbTassaRifiuti.setCodiceFiscale(profiloUtente.getCodiceFiscale());
					datiRimbTassaRifiuti.setNome(profiloUtente.getNome());
					datiRimbTassaRifiuti.setCognome(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbTassaRifiuti.setComuneNascita(comune.getDenominazione());
							datiRimbTassaRifiuti.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbTassaRifiuti.setIndirizzoResidenza(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbTassaRifiuti.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbTassaRifiuti.setProvResidenza(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				}
			}

			datiRimbTassaRifiuti.setAnno("" + (new Integer(DateUtils.getAnnoCorrente())));

			DichiarazioneTassaRifiutiRichiesta dichiarazioneTassaRifiutiRichiesta = new DichiarazioneTassaRifiutiRichiesta();
			dichiarazioneTassaRifiutiRichiesta.setAnnoInizio(Integer.parseInt(DateUtils.getAnnoCorrente()));
			dichiarazioneTassaRifiutiRichiesta.setAnnoFine(Integer.parseInt(DateUtils.getAnnoCorrente()));

			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				dichiarazioneTassaRifiutiRichiesta.setPartitaIva(partitaIvaServizio);
			}
			else {
				dichiarazioneTassaRifiutiRichiesta.setCodiceFiscale(codiceFiscale);
			}

			DichiarazioneTassaRifiutiRisposta dichiarazioneTassaRifiutiRisposta = null;
			try {
				dichiarazioneTassaRifiutiRisposta = tributiService.richiediSituazioneTassaRifiuti(dichiarazioneTassaRifiutiRichiesta, userPreferences);
			}
			catch (Exception e) {
				log.error("getRimborsoTassaRifiutiForm :: " + e.getMessage(), e);
			}

			if (dichiarazioneTassaRifiutiRisposta != null) {
				List<DatiTassaRifiuti> situazioni = dichiarazioneTassaRifiutiRisposta.getSituazione();
				if (situazioni != null && !situazioni.isEmpty()) {
					DatiTassaRifiuti situazione = situazioni.get(0);
					int i = 0;
					if (situazione != null) {
						datiRimbTassaRifiuti.setTipoTassa(situazione.getDescrizioneTassa());
						List<Posizioni> posizioni = situazione.getPosizioni();
						if (posizioni != null && !posizioni.isEmpty()) {
							for (Posizioni posizioni2 : posizioni) {
								Method metodo = datiRimbTassaRifiuti.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
								metodo.invoke(datiRimbTassaRifiuti, PortletUtils.getIndirizzoFromPosizione(posizioni2));

								Method metodo1 = datiRimbTassaRifiuti.getClass().getMethod("setMq" + (i + 1), String.class);
								metodo1.invoke(datiRimbTassaRifiuti, "" + posizioni2.getSuperficie());

								Method metodo2 = datiRimbTassaRifiuti.getClass().getMethod("setVersato" + (i + 1), String.class);
								metodo2.invoke(datiRimbTassaRifiuti, "" + posizioni2.getImportoDaPagare());
								i++;
							}
						}
					}
				}
			}

			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());

			model.addAttribute("datiRimbTassaRifiuti", datiRimbTassaRifiuti);

		}
		catch (Exception e) {
			log.error("getRimborsoTassaRifiutiForm :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderRimborsoTassaRifiutiForm");
	}

	/**
	 * Restituisce la form per l'upload delle richieste e doc.
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
	 * Gestisce l'upload dei file
	 *
	 * @param uploadItem
	 * @param result
	 * @param response
	 * @param session
	 * @param request
	 * @param model
	 * @param selectNumAllegati
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati) throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		if (selectNumAllegati == null) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);
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
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);
				fascicolo.setRicercabileDaComune(true);
				fascicolo.setNumeroProtocollo(null);
				fascicolo.setChecksum(null);
				fascicoloService.saveNuovaRichiesta(fascicolo);

				final String numeroProtocollo = uploadService.processaUploadDichiarazioni(SERVIZIO, uploadItem, helper.getUserPreferences(request));

				if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
					fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtente);
					fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
					fascicolo.setUserPreferences(helper.getUserPreferences(request));
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);
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
				}

				// Invio email ad azienda
				if (partitaIvaServizio != null) {
					String subjectAzienda = messageSource.getMessage("mail.azienda.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
					try {
						tributiCommonService.sendMailToCompany(userPreferences, aziendaByPiva, subjectAzienda, SERVIZIO, numeroProtocollo);
					}
					catch (Exception e) {
						log.error("upload :: " + e.getMessage(), e);
					}
				}

				final String uuidOperazione=(String)session.getAttribute("UUID");
				
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo()
					.build();
				
				
				// memorizzo la richiesta
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
	 * @param datiRimbTassaRifiuti
	 * @param result
	 * @param model
	 * @param response
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaRimborso")
	public void compilaDichiarazione(@ModelAttribute("datiRimbTassaRifiuti") DatiRimborsoTassaRifiuti datiRimbTassaRifiuti, BindingResult result,
			@RequestParam(required = false, value = "cambio") String cambio, Model model, ActionResponse response, ActionRequest request) throws Exception {
		log.debug("compilaDichiarazione CF=" + datiRimbTassaRifiuti.getCodiceFiscale());
		log.debug("cambio=" + cambio);

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiRimbTassaRifiuti.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiRimbTassaRifiuti.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
		}

		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (cambio == null) {
			rimborsoValidator.validate(datiRimbTassaRifiuti, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderRimborsoTassaRifiutiForm");
			}
			else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", userPreferences.getNomeComune());
				model.addAttribute("datiRimbTassaRifiuti", datiRimbTassaRifiuti);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderRimborsoTassaRifiutiForm");
			}
		}
		else if (cambio != null && getVisuraPosizioniTributarieActive(request)) {
			log.debug("anno = " + datiRimbTassaRifiuti.getAnno());

			DichiarazioneTassaRifiutiRichiesta dichiarazioneTassaRifiutiRichiesta = new DichiarazioneTassaRifiutiRichiesta();
			dichiarazioneTassaRifiutiRichiesta.setAnnoInizio(Integer.parseInt(datiRimbTassaRifiuti.getAnno()));
			dichiarazioneTassaRifiutiRichiesta.setAnnoFine(Integer.parseInt(datiRimbTassaRifiuti.getAnno()));
			// Controllo profilo cittadino/azienda
			if (userPreferences.getPartitaIvaServizio() != null) {
				dichiarazioneTassaRifiutiRichiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
			}
			else {
				dichiarazioneTassaRifiutiRichiesta.setCodiceFiscale(userPreferences.getCodiceFiscaleServizio());
			}

			DichiarazioneTassaRifiutiRisposta dichiarazioneTassaRifiutiRisposta = null;
			try {
				dichiarazioneTassaRifiutiRisposta = tributiService.richiediSituazioneTassaRifiuti(dichiarazioneTassaRifiutiRichiesta, userPreferences);
			}
			catch (Exception e) {
				log.error("compilaDichiarazione :: " + e.getMessage(), e);
			}

			int i = 0;
			if (dichiarazioneTassaRifiutiRisposta != null) {
				List<DatiTassaRifiuti> situazioni = dichiarazioneTassaRifiutiRisposta.getSituazione();
				if (situazioni != null && !situazioni.isEmpty()) {
					DatiTassaRifiuti situazione = situazioni.get(0);
					if (situazione != null) {
						datiRimbTassaRifiuti.setTipoTassa(situazione.getDescrizioneTassa());
						List<Posizioni> posizioni = situazione.getPosizioni();
						if (posizioni != null && !posizioni.isEmpty()) {
							for (Posizioni posizioni2 : posizioni) {
								Method metodo = datiRimbTassaRifiuti.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
								metodo.invoke(datiRimbTassaRifiuti, PortletUtils.getIndirizzoFromPosizione(posizioni2));

								Method metodo1 = datiRimbTassaRifiuti.getClass().getMethod("setMq" + (i + 1), String.class);
								metodo1.invoke(datiRimbTassaRifiuti, "" + posizioni2.getSuperficie());

								Method metodo2 = datiRimbTassaRifiuti.getClass().getMethod("setVersato" + (i + 1), String.class);
								metodo2.invoke(datiRimbTassaRifiuti, "" + posizioni2.getImportoDaPagare());
								i++;
							}
						}
					}
				}
			}

			log.debug("i=" + i);
			for (int j = i; j < 5; j++) {
				Method metodo = datiRimbTassaRifiuti.getClass().getMethod("setIndirizzo" + (j + 1), String.class);
				metodo.invoke(datiRimbTassaRifiuti, "");

				Method metodo1 = datiRimbTassaRifiuti.getClass().getMethod("setMq" + (j + 1), String.class);
				metodo1.invoke(datiRimbTassaRifiuti, "");

				Method metodo2 = datiRimbTassaRifiuti.getClass().getMethod("setVersato" + (j + 1), String.class);
				metodo2.invoke(datiRimbTassaRifiuti, "");
				Method metodo3 = datiRimbTassaRifiuti.getClass().getMethod("setDovuto" + (j + 1), String.class);
				metodo3.invoke(datiRimbTassaRifiuti, "");
			}

			model.addAttribute("datiRimbTassaRifiuti", datiRimbTassaRifiuti);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			response.setRenderParameter("action", "renderRimborsoTassaRifiutiForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param dati
	 * @param dichiarazione
	 * @throws Exception
	 */
	@ResourceMapping("rimborsoTassaRifiutiReport")
	public void serveCertificato(@ModelAttribute("datiRimbTassaRifiuti") DatiRimborsoTassaRifiuti datiRimbTassaRifiuti, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {
		log.debug("rimborsoTassaRifiutiReport ");

		// Query the service layer for some objects
		List<DatiRimborsoTassaRifiuti> beans = new ArrayList<DatiRimborsoTassaRifiuti>();

		beans.add(datiRimbTassaRifiuti);

		param.put("subreportParameters", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_RIFIUTI);
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

	/**
	 * Presenta la form per il rimborso tassa rifiuti
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRimborsoTassaRifiutiForm")
	public String renderRimborsoTassaRifiutiForm(Model model) throws Exception {
		return toLocalViewPath("rimborsoTassaRifiuti");
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
	 * "/rimborsotassarifiuti/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
