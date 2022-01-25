package it.osapulie.tributi.web.portlet.rimborsotassaimmobili.controller;

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
import eng.tz.la.core.AuditManager;
import eng.tz.la.core.Export;
import eng.tz.la.model.OperationExportFile;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Audit;
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
import it.osapulie.persistence.AuditRepository;
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
import it.osapulie.tributi.web.portlet.rimborsotassaimmobili.form.DatiRimborsoTassaImmobili;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
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

/**
 * Portlet controller per il servizio Richiesta rimborso tassa sugli immobili.
 *
 * @author Birtolo Maria Michela
 * @author Gianluca Pindinelli
 */
@Controller("rimborsoTassaImmobiliPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiRimbTassaImmobili", "param", "uploadItem" })
public class RimborsoTassaImmobiliPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(RimborsoTassaImmobiliPortletController.class);

	private static final String SERVIZIO = "RICHIESTA RIMBORSO ICI/IMU/TASI";

	private static final String REPORT_PATH = "/reports/rimborsoTassaImmobili.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/subreportRimborso.jrxml";
	private static final String SUB_REPORT_PATH_2 = "/reports/subreportRimborsoTassaImmobiliFooter.jrxml";
	private static final String REPORT_NAME = "rimborsoTassaImmobili.pdf";

	private static final String JSP_PATH = "rimborsotassaimmobili/";

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
	@Named("datiRimborsoTassaImmobiliValidator")
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

		log.debug("homePage-rimborsoTassaImmobiliPortletController");

		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per la rimborso tassa sugli immobili.
	 *
	 * @param model
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getRimborsoTassaImmobiliForm")
	public void getDichiarazioneTassaImmobiliForm(Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest) throws Exception {

		log.debug("getRimborsoTassaImmobiliForm");
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("RICHIESTA RIMPBORSO IMU/TASI/ICI")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addServizioTimeString()
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
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
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setUUID(uuidStr)
		.getServizioAttribute();
		
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setUuidOperazione(uuidStr)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidStr).go_StartTime().getTempiMedi();

		
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
				log.warn("getDichiarazioneTassaImmobiliForm :: " + e.getMessage());
			}

			DatiRimborsoTassaImmobili datiRimbTassaImmobili = new DatiRimborsoTassaImmobili();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiRimbTassaImmobili.setRagSociale(aziendaByPiva.getRagioneSociale());
				datiRimbTassaImmobili.setpIva(partitaIvaServizio);
				datiRimbTassaImmobili.setCodiceFiscaleGiu(partitaIvaServizio);
				Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiRimbTassaImmobili.setSedeLegale(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiRimbTassaImmobili.setProvincia(comune.getProvincia().getSigla());
						datiRimbTassaImmobili.setComune(comune.getDenominazione());
					}
				}
				if (componente != null && datiGen != null) {
					datiRimbTassaImmobili.setCodiceFiscaleRapp(componente.getCodiceFiscale());
					datiRimbTassaImmobili.setNomeRapp(componente.getNome());
					datiRimbTassaImmobili.setCognomeRapp(componente.getCognome());
					datiRimbTassaImmobili.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbTassaImmobili.setComuneNascitaRapp(comuneByCodiceISTAT.getDenominazione());
						datiRimbTassaImmobili.setProvinciaNascitaRapp(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbTassaImmobili.setSessoRapp(componente.getSesso());
					datiRimbTassaImmobili.setIndirizzoResidenzaRapp(datiGen.getIndirizzo());
					datiRimbTassaImmobili.setComuneResidenzaRapp(datiGen.getComuneResidenza());
					datiRimbTassaImmobili.setProvResidenzaRapp(datiGen.getProvinciaResidenza());
					datiRimbTassaImmobili.setCapResidenzaRapp(datiGen.getCap());

				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbTassaImmobili.setCodiceFiscaleRapp(profiloUtente.getCodiceFiscale());
					datiRimbTassaImmobili.setNomeRapp(profiloUtente.getNome());
					datiRimbTassaImmobili.setCognomeRapp(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbTassaImmobili.setComuneNascitaRapp(comune.getDenominazione());
							datiRimbTassaImmobili.setProvinciaNascitaRapp(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbTassaImmobili.setIndirizzoResidenzaRapp(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbTassaImmobili.setComuneResidenzaRapp(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbTassaImmobili.setProvResidenzaRapp(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
				}
			}
			else {
				if (componente != null && datiGen != null) {
					datiRimbTassaImmobili.setCodiceFiscale(componente.getCodiceFiscale());
					datiRimbTassaImmobili.setNome(componente.getNome());
					datiRimbTassaImmobili.setCognome(componente.getCognome());
					datiRimbTassaImmobili.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine
					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiRimbTassaImmobili.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiRimbTassaImmobili.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}
					datiRimbTassaImmobili.setSesso(componente.getSesso());
					datiRimbTassaImmobili.setIndirizzoResidenza(datiGen.getIndirizzo());
					datiRimbTassaImmobili.setComuneResidenza(datiGen.getComuneResidenza());
					datiRimbTassaImmobili.setProvResidenza(datiGen.getProvinciaResidenza());
					datiRimbTassaImmobili.setCapResidenza(datiGen.getCap());
				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiRimbTassaImmobili.setCodiceFiscale(profiloUtente.getCodiceFiscale());
					datiRimbTassaImmobili.setNome(profiloUtente.getNome());
					datiRimbTassaImmobili.setCognome(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiRimbTassaImmobili.setComuneNascita(comune.getDenominazione());
							datiRimbTassaImmobili.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiRimbTassaImmobili.setIndirizzoResidenza(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiRimbTassaImmobili.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiRimbTassaImmobili.setProvResidenza(provincia.getDenominazioneProvincia());
							}
						}
					}

					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				}
			}
			datiRimbTassaImmobili.setAnno("" + new Integer(DateUtils.getAnnoCorrente()));

			DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				dichiarazioneTassaImmobiliRichiesta.setPartitaIva(partitaIvaServizio);
			}
			else {
				dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(codiceFiscale);
			}
			dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(new Integer(DateUtils.getAnnoCorrente()));
			dichiarazioneTassaImmobiliRichiesta.setAnnoFine(new Integer(DateUtils.getAnnoCorrente()));

			List<DatiTassaImmobili> situazioni = null;
			try {
				DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);
				situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
			}
			catch (Exception e) {
				log.error("getDichiarazioneTassaImmobiliForm :: " + e.getMessage(), e);
				auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			}
			if (situazioni != null && !situazioni.isEmpty()) {
				DatiTassaImmobili situazione = situazioni.get(0);
				datiRimbTassaImmobili.setDescrizioneTassa(situazione.getDescrizioneTassa());
				List<Posizioni> posizioni = situazione.getPosizioni();
				if (posizioni != null) {
					for (int i = 0; i < posizioni.size(); i++) {
						Posizioni posizioni2 = posizioni.get(i);
						Method metodo = datiRimbTassaImmobili.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
						metodo.invoke(datiRimbTassaImmobili, PortletUtils.getIndirizzoFromPosizione(posizioni2));

						Method metodo1 = datiRimbTassaImmobili.getClass().getMethod("setFoglio" + (i + 1), String.class);
						metodo1.invoke(datiRimbTassaImmobili, "" + posizioni2.getFoglio());

						Method metodo2 = datiRimbTassaImmobili.getClass().getMethod("setNum" + (i + 1), String.class);
						metodo2.invoke(datiRimbTassaImmobili, "" + posizioni2.getParticella());

						Method metodo3 = datiRimbTassaImmobili.getClass().getMethod("setQuota" + (i + 1), String.class);
						metodo3.invoke(datiRimbTassaImmobili, "" + posizioni2.getPercentualePossesso());

						Method metodo4 = datiRimbTassaImmobili.getClass().getMethod("setCategoria" + (i + 1), String.class);
						metodo4.invoke(datiRimbTassaImmobili, posizioni2.getCategoriaImmobile());

						Method metodo5 = datiRimbTassaImmobili.getClass().getMethod("setSezione" + (i + 1), String.class);
						metodo5.invoke(datiRimbTassaImmobili, posizioni2.getSezione());
					}
				}
			}

			model.addAttribute("datiRimbTassaImmobili", datiRimbTassaImmobili);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			model.addAttribute("isPersonaFisica", String.valueOf(datiRimbTassaImmobili.getpIva() == null));
		}
		catch (Exception e) {
			log.error("getRimborsoTassaImmobiliForm :: " + e.getMessage(), e);
			auditManager
			.addFineOperazione()
			.addEsitoError()
			.addInfo("Exception", e.getMessage())
			.build();
		}
		auditManager.build();
		response.setRenderParameter("action", "renderRimborsoTassaImmobiliForm");
	}

	/**
	 * Restituisce la form per l'upload delle richieste e doc.
	 *
	 * @param model
	 * @param response
	 * @param session 
	 * @param request 
	 */
	@ActionMapping(params = "ope=getUploadForm")
	public void getUploadForm(Model model, ActionResponse response, PortletSession session, PortletRequest request) throws Exception {
		
		String uuidStr=UUID.randomUUID().toString();
		session.setAttribute("UUID", uuidStr);
		AuditManager auditManager= AuditConfiguration.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidStr)
					.addOperazioneRichiesta("RICHIESTA RIMPBORSO IMU/TASI/ICI")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
					.addPaginaCorrente(helper.getCurrentPageName(request))
					.addServizioTimeString()
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
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
		UploadItem item = new UploadItem();
		model.addAttribute("uploadItem", item);
		auditManager.build();
		
		UserPreferences userPreferences = helper.getUserPreferences(request);
		String codiceFiscale = userPreferences.getCodiceFiscaleServizio();
		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setUuidOperazione(uuidStr)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setNomeServizio(SERVIZIO)
		.setUuid(uuidStr).go_StartTime().getTempiMedi();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
		.setUUID(uuidStr)
		.getServizioAttribute();
		
		auditDwhService.invokeAuditService(servizioAttributeDto, dataminingDto, tempiMediDto);
		
		
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
	 * @param portletRequest 
	 * @param portletSession 
	 * @throws Exception 
	 * @throws ServiceLayerException 
	 */
	@ActionMapping(params = "ope=upload")
	public void upload(@ModelAttribute("uploadItem") UploadItem uploadItem, BindingResult result, ActionResponse response, PortletSession session, ActionRequest request, Model model,
			@RequestParam(required = false, value = "selectNumAllegati") String selectNumAllegati, @ModelAttribute("datiRimbTassaImmobili") DatiRimborsoTassaImmobili datiRimbTassaImmobili, PortletRequest portletRequest, PortletSession portletSession)
			throws ServiceLayerException, Exception {

		log.debug("uploadFile");
		 
final String uuidOperazione=(String)session.getAttribute("UUID");
		
		
		AuditConfiguration
		.configure()
		.audit()
		.addInizioOperazione()
		.addUuidOperazione(uuidOperazione)
		.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD - INIT")
		.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
		.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
		.addEsitoSuccess()
		.setOrigin(Origin.getIp(request))
		.build();
		
		
		
		if (selectNumAllegati == null) {
			ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);
			UserPreferences userPreferences = helper.getUserPreferences(request);
			Azienda aziendaByPiva = null;
			String partitaIvaServizio = userPreferences.getPartitaIvaServizio();
			if (partitaIvaServizio != null) {
				aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				validator.validate(uploadItem, result, aziendaByPiva, PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);
			}
			else {
				validator.validate(uploadItem, result, profiloUtente, PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);
			}

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				String allegato1 = datiRimbTassaImmobili.getAllegatoUno();
				String allegato2 = datiRimbTassaImmobili.getAllegatoDue();
				String allegato3 = datiRimbTassaImmobili.getAllegatoTre();
				String allegato4 = datiRimbTassaImmobili.getAllegatoQuattro();
				ArrayList<String> descrizioni = new ArrayList<String>();
				int numAllegatiSelectInteger = uploadItem.getMultipartFiles().size();
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
				//
				Fascicolo fascicolo = new Fascicolo();
				fascicolo.setIdProfiloUtente(profiloUtente);
				fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
				fascicolo.setUserPreferences(helper.getUserPreferences(request));
				fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);
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
					fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(numeroProtocollo);
					fascicolo.setChecksum(null);
					fascicoloService.saveNuovaRichiesta(fascicolo);
				}

				// Invio email a cittadino
				String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { SERVIZIO }, new Locale("it"));
				try {
					tributiCommonService.sendMailToUser(userPreferences, subject, SERVIZIO, numeroProtocollo);
				
					AuditConfiguration
					.configure()
					.audit()
					.addInizioOperazione()
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addDelegante("")
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
					.addUuidOperazione(uuidOperazione)
					.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AL CITTADINO")
					.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
					.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
					.addDelegante("")
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
				.addUuidOperazione(uuidOperazione)
				.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AZIENDA")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
				.addDelegante("")
				.addNumeroProcollo(""+numeroProtocollo)
				.addEsitoSuccess()
				.setOrigin(Origin.getIp(request))
				.build();
					
					}
					catch (Exception e) {
						AuditConfiguration
						.configure()
						.audit()
						.addInizioOperazione()
						.addUuidOperazione(uuidOperazione)
						.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD INVIO EMAIL AZIENDA")
						.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
						.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
						.addDelegante("")
						.setOrigin(Origin.getIp(request))
						.addNumeroProcollo(""+numeroProtocollo)
								.addEsitoError("Exception errore invio email azienda")
								.build();	
					}
				}

				
				AuditConfiguration
				.configure()
				.audit()
				.addInizioOperazione()
				.addFineOperazione()
				.addUuidOperazione(uuidOperazione)
				.addOperazioneRichiesta(SERVIZIO+"  - STEP FINALE UPLOAD")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest))
				.addDelegante("")
				.addNumeroProcollo(""+numeroProtocollo)
				.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
				.addServizioTimeString()
				.addServizioUuidTransazione(uuidOperazione)
				.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
				.addCallGeo()
				.setOrigin(Origin.getIp(request))
				.build();
				
		
				DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
				
				//MS
				tempiMediDto = DwhTempiMediUtil.get(dwhService)
				.go_EndTime(uuidOperazione)
				.setCodServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI)
				.setNomeServizio(SERVIZIO)
				.getTempiMedi();

				auditDwhService.invokeAuditService(null, null, tempiMediDto);
				
				 DwhServiceAttributeUtil.saveTimeFine(dwhService,new IUpdateAttribute()  {
					

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
				 
				// memorizzo la richiesta
				response.setRenderParameter("action", "renderEsitoUpload");
			}
		}
		else {
			String descAllegatoUno = datiRimbTassaImmobili.getAllegatoUno();
			String descAllegatoDue = datiRimbTassaImmobili.getAllegatoDue();
			String descAllegatoTre = datiRimbTassaImmobili.getAllegatoTre();
			String allegato4 = datiRimbTassaImmobili.getAllegatoQuattro();
			ArrayList<String> descrizioni = new ArrayList<String>();

			String numAllegatiSelect = request.getParameter("numAllegatiSelect");
			int numAllegatiSelectInteger = Integer.parseInt(numAllegatiSelect);
			if (descAllegatoUno.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(descAllegatoUno);
			}
			if (descAllegatoDue.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(descAllegatoDue);
			}
			if (descAllegatoTre.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(descAllegatoTre);
			}
			if (allegato4.trim().length() > 0) {
				numAllegatiSelectInteger++;
				descrizioni.add(allegato4);
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
	 * Metodo che prende i campi inseriti nella form e li mette in sessione.
	 *
	 * @param datiDichiarazione
	 * @param result
	 * @param model
	 * @param response
	 * @param session 
	 * @param portletRequest 
	 * @param session 
	 * @param portletRequest 
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=generaRimborso")
	public void compilaDichiarazione(@ModelAttribute("datiRimbTassaImmobili") DatiRimborsoTassaImmobili datiRimbTassaImmobili, BindingResult result,
			@RequestParam(required = false, value = "cambio")String cambio, 
			@ModelAttribute("isPersonaFisica") String isPersonaFisica, Model model, ActionResponse response, ActionRequest request, PortletSession session, PortletRequest portletRequest)
			throws Exception {

		log.debug("compilaDichiarazione CF=" + datiRimbTassaImmobili.getCodiceFiscale());
		log.debug("cambio=" + cambio);
		
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("RICHIESTA RIMBORSO IMU/TASI/ICI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiRimbTassaImmobili.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiRimbTassaImmobili.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
		}
		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (!Boolean.parseBoolean(isPersonaFisica) && datiRimbTassaImmobili.getpIva().equalsIgnoreCase("")) {
			datiRimbTassaImmobili.setpIva("  ");// workaround: altrimenti il frontend visualizza il
												// box p.fisica
		}

		if (cambio == null) {

			rimborsoValidator.validate(datiRimbTassaImmobili, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				model.addAttribute("datiRimbTassaImmobili", datiRimbTassaImmobili);
				response.setRenderParameter("action", "renderRimborsoTassaImmobiliForm");
			}
			else {

				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", userPreferences.getNomeComune());
				param.put("descrizioneTassa", datiRimbTassaImmobili.getDescrizioneTassa());

				model.addAttribute("datiRimbTassaImmobili", datiRimbTassaImmobili);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderRimborsoTassaImmobiliForm");
			}
		}
		else if (cambio != null && getVisuraPosizioniTributarieActive(request)) {

			log.debug("anno = " + datiRimbTassaImmobili.getAnno());

			DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();
			// Controllo profilo cittadino/azienda
			if (userPreferences.getPartitaIvaServizio() != null) {
				dichiarazioneTassaImmobiliRichiesta.setPartitaIva(userPreferences.getPartitaIvaServizio());
			}
			else {
				dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(userPreferences.getCodiceFiscaleServizio());
			}
			dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(new Integer(datiRimbTassaImmobili.getAnno()));
			dichiarazioneTassaImmobiliRichiesta.setAnnoFine(new Integer(datiRimbTassaImmobili.getAnno()));

			List<DatiTassaImmobili> situazioni = null;
			try {
				DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);
				situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
			}
			catch (Exception e) {
				log.error("compilaDichiarazione :: " + e.getMessage(), e);
				auditManager
				.addFineOperazione()
				.addEsitoError()
				.addInfo("Exception", e.getMessage())
				.build();
			}

			int i = 0;
			if (situazioni != null && !situazioni.isEmpty()) {
				DatiTassaImmobili situazione = situazioni.get(0);
				datiRimbTassaImmobili.setDescrizioneTassa(situazione.getDescrizioneTassa());
				List<Posizioni> posizioni = situazione.getPosizioni();
				if (posizioni != null) {
					for (i = 0; i < posizioni.size(); i++) {
						Posizioni posizioni2 = posizioni.get(i);
						Method metodo = datiRimbTassaImmobili.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
						metodo.invoke(datiRimbTassaImmobili, PortletUtils.getIndirizzoFromPosizione(posizioni2));

						Method metodo1 = datiRimbTassaImmobili.getClass().getMethod("setFoglio" + (i + 1), String.class);
						metodo1.invoke(datiRimbTassaImmobili, "" + posizioni2.getFoglio());

						Method metodo2 = datiRimbTassaImmobili.getClass().getMethod("setNum" + (i + 1), String.class);
						metodo2.invoke(datiRimbTassaImmobili, "" + posizioni2.getParticella());

						Method metodo3 = datiRimbTassaImmobili.getClass().getMethod("setQuota" + (i + 1), String.class);
						metodo3.invoke(datiRimbTassaImmobili, "" + posizioni2.getPercentualePossesso());

						Method metodo4 = datiRimbTassaImmobili.getClass().getMethod("setCategoria" + (i + 1), String.class);
						metodo4.invoke(datiRimbTassaImmobili, posizioni2.getCategoriaImmobile());

						Method metodo5 = datiRimbTassaImmobili.getClass().getMethod("setSezione" + (i + 1), String.class);
						metodo5.invoke(datiRimbTassaImmobili, posizioni2.getSezione());

						Method metodo6 = datiRimbTassaImmobili.getClass().getMethod("setDovuto" + (i + 1), String.class);
						metodo6.invoke(datiRimbTassaImmobili, "");

						Method metodo7 = datiRimbTassaImmobili.getClass().getMethod("setVersato" + (i + 1), String.class);
						metodo7.invoke(datiRimbTassaImmobili, "");
					}
				}
			}

			for (int j = i; j < 5; j++) {
				Method metodo = datiRimbTassaImmobili.getClass().getMethod("setIndirizzo" + (j + 1), String.class);
				metodo.invoke(datiRimbTassaImmobili, "");

				Method metodo1 = datiRimbTassaImmobili.getClass().getMethod("setFoglio" + (j + 1), String.class);
				metodo1.invoke(datiRimbTassaImmobili, "");

				Method metodo2 = datiRimbTassaImmobili.getClass().getMethod("setNum" + (j + 1), String.class);
				metodo2.invoke(datiRimbTassaImmobili, "");

				Method metodo3 = datiRimbTassaImmobili.getClass().getMethod("setQuota" + (j + 1), String.class);
				metodo3.invoke(datiRimbTassaImmobili, "");

				Method metodo4 = datiRimbTassaImmobili.getClass().getMethod("setCategoria" + (j + 1), String.class);
				metodo4.invoke(datiRimbTassaImmobili, "");

				Method metodo5 = datiRimbTassaImmobili.getClass().getMethod("setSezione" + (j + 1), String.class);
				metodo5.invoke(datiRimbTassaImmobili, "");

				Method metodo6 = datiRimbTassaImmobili.getClass().getMethod("setDovuto" + (i + 1), String.class);
				metodo6.invoke(datiRimbTassaImmobili, "");

				Method metodo7 = datiRimbTassaImmobili.getClass().getMethod("setVersato" + (i + 1), String.class);
				metodo7.invoke(datiRimbTassaImmobili, "");
			}

			model.addAttribute("datiRimbTassaImmobili", datiRimbTassaImmobili);
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			auditManager.build();
			response.setRenderParameter("action", "renderRimborsoTassaImmobiliForm");
		}
	}

	/**
	 * Metodo che genera il certificato e ne consente il download
	 *
	 * @param codFisc
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest 
	 * @param portletRequest 
	 * @param dati
	 * @param dichiarazione
	 * @throws Exception
	 */
	@ResourceMapping("rimborsoTassaImmobiliReport")
	public void serveCertificato(@ModelAttribute("datiRimbTassaImmobili") DatiRimborsoTassaImmobili datiRimbTassaImmobili, @ModelAttribute("param") Map<String, Object> param, Model model,
			ResourceResponse response, PortletSession session, ResourceRequest request, PortletRequest portletRequest ) throws Exception {

		log.debug("rimborsoTassaImmobiliReport ");
		
		AuditManager auditManager= AuditConfiguration
				.configure()
				.audit()
				.addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("RICHIESTA RIMBORSO IMU/TASI/ICI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(portletRequest))
				.setOrigin(Origin.getIp(request))
				.addPaginaCorrente(helper.getCurrentPageName(portletRequest));
				

		// Query the service layer for some objects
		List<DatiRimborsoTassaImmobili> beans = new ArrayList<DatiRimborsoTassaImmobili>();

		beans.add(datiRimbTassaImmobili);
		// serve per passare i dati ai sottoreport
		param.put("subreportParameters", beans);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);
		subreportJrxmlMap.put("subreportParameter2", SUB_REPORT_PATH_2);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(helper.getProfiloUtente(session));
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio2", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_RIMBORSO_TASSA_IMMOBILI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		// consentire il download del documento.
		String reportFileName = String.format(REPORT_NAME);
		auditManager
		.addFineOperazione()
		.addEsitoSuccess()
		.build();
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
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
	 * Presenta la form per il rimborso tassa immobili
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderRimborsoTassaImmobiliForm")
	public String renderRimborsoTassaImmobiliForm(Model model, PortletRequest request, PortletSession session) throws Exception {
			AuditConfiguration.configure().audit().addUuidOperazione((String)session.getAttribute("UUID"))
				.addOperazioneRichiesta("RICHIESTA RIMBORSO IMU/TASI/ICI")
				.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
				.addPaginaCorrente(helper.getCurrentPageName(request))
				.setOrigin(Origin.getIp(request))
				.build();
		return toLocalViewPath("rimborsoTassaImmobili");
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
	 * @param request 
	 * @param session 
	 * @return l'ID della view
	 */
	@RenderMapping(params = "action=renderEsitoUpload")
	public String renderEsitoUpload(Model model, PortletRequest request, PortletSession session) {
 
		log.debug("Model = " + model);
		return toLocalViewPath("esitoUploadDichiarazione");
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
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/rimborsotassaimmobili/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}
}
