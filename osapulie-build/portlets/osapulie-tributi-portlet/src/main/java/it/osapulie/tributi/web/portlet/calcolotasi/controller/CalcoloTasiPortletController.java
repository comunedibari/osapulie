package it.osapulie.tributi.web.portlet.calcolotasi.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.google.gson.Gson;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.domain.Azienda;
import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.categoriaimmobile.Agevolazione;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.categoriaimmobile.Detrazione;
import it.osapulie.domain.categoriaimmobile.Esenzione;
import it.osapulie.domain.categoriaimmobile.Tributo;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.ContentMimeTypes;
import it.osapulie.infrastructure.ReportService;
import it.osapulie.infrastructure.impl.DateUtils;
import it.osapulie.service.AziendaService;
import it.osapulie.service.CategoriaImmobileService;
import it.osapulie.service.CommonService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.DwhService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.service.AuditDwhService;
import it.osapulie.tributi.service.TributiService;
import it.osapulie.tributi.web.portlet.calcolotasi.form.DatiCalcoloTasi;
import it.osapulie.tributi.web.portlet.calcolotasi.form.DatiCalcoloTasiImmobile;
import it.osapulie.tributi.web.portlet.utils.PortletConstants;
import it.osapulie.tributi.web.portlet.utils.PortletUtils;
import it.osapulie.tributi.web.portlet.varie.f24.DatiF24;
import it.osapulie.tributi.web.portlet.varie.f24.MotivoPagamentoF24;
import it.osapulie.tributi.web.ws.output.types.Codifica;
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
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Portlet controller per il servizio Calcolo TASI.
 *
 * @author Gianluca Pindinelli
 * @author Damiano Miri
 */
@Controller("calcoloTasiPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiCalTasi", "param", "uploadItem" })
public class CalcoloTasiPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(CalcoloTasiPortletController.class);

	private static final String JSP_PATH = "calcolotasi/";

	private static final String REPORT_PATH = "/reports/modelloF24.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/modelloF24_subreport1.jrxml";
	private static final String REPORT_NAME = "f24_semplificato_TASI.pdf";
	private static final String SERVIZIO = "SERVIZIO CALCOLO TASI";
	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private TributiService tributiService;

	@Inject
	private CategoriaImmobileService categoriaImmobileService;

	@Inject
	private ServizioService servizioService;

	@Inject
	@Named("datiCalcoloTasiValidator")
	private Validator calcoloValidator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ReportService reportService;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private AziendaService aziendaService;

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
		log.debug("homePage-calcoloTasiPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per il Calcolo TASI.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getCalcoloTasiForm")
	public void getCalcoloTasiForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getCalcoloTasiForm");
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
 
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
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
				log.warn("getCalcoloTasiForm :: " + e.getMessage());
			}

			DatiCalcoloTasi datiCalTasi = new DatiCalcoloTasi();

			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				datiCalTasi.setPartitaIva(partitaIvaServizio);
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiCalTasi.setCodiceFiscale(aziendaByPiva.getPartitaIva());
				datiCalTasi.setCognome(aziendaByPiva.getRagioneSociale());
				it.osapulie.domain.Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiCalTasi.setIndirizzoResidenza(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiCalTasi.setProvResidenza(comune.getProvincia().getSigla());
						datiCalTasi.setComuneResidenza(comune.getDenominazione());
					}
				}
				datiCalTasi.setDataNascita(null);
			}
			else {
				if (componente != null && datiGen != null) {
					datiCalTasi.setCodiceFiscale(componente.getCodiceFiscale());
					datiCalTasi.setNome(componente.getNome());
					datiCalTasi.setCognome(componente.getCognome());
					datiCalTasi.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					datiCalTasi.setDataNascitaForm(componente.getDataNascita().getTime());
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine

					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						datiCalTasi.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
						datiCalTasi.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
					}

					datiCalTasi.setSesso(componente.getSesso());
					datiCalTasi.setIndirizzoResidenza(datiGen.getIndirizzo());
					datiCalTasi.setComuneResidenza(datiGen.getComuneResidenza());
					datiCalTasi.setProvResidenza(datiGen.getProvinciaResidenza());
					datiCalTasi.setCapResidenza(datiGen.getCap());
				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiCalTasi.setCodiceFiscale(profiloUtente.getCodiceFiscale());
					datiCalTasi.setNome(profiloUtente.getNome());
					datiCalTasi.setCognome(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiCalTasi.setComuneNascita(comune.getDenominazione());
							datiCalTasi.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiCalTasi.setIndirizzoResidenza(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiCalTasi.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiCalTasi.setProvResidenza(provincia.getDenominazioneProvincia());
							}
						}
					}
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				}
			}

			Integer annoCorrente = new Integer(DateUtils.getAnnoCorrente());
			datiCalTasi.setAnno("" + annoCorrente);

			if (getVisuraPosizioniTributarieActive(request)) {

				DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();

				// Controllo profilo cittadino/azienda
				if (partitaIvaServizio != null) {
					dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(codiceFiscale);
				}
				else {
					dichiarazioneTassaImmobiliRichiesta.setPartitaIva(partitaIvaServizio);
				}

				// recupero i dati dell'anno precedente e di quello corrente; se non ci sono dati
				// per l'anno corrente utilizzo gli anni precedenti ed informo l'utente
				dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(annoCorrente - 5);
				dichiarazioneTassaImmobiliRichiesta.setAnnoFine(annoCorrente);

				DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);
				List<DatiTassaImmobili> situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
				if (situazioni != null && !situazioni.isEmpty()) {
					// Prelevo l'ultimo anno
					DatiTassaImmobili situazione = situazioni.get(situazioni.size() - 1);

					List<Posizioni> pos = situazione.getPosizioni();
					List<DatiCalcoloTasiImmobile> listaRiepilogoDati = new ArrayList<DatiCalcoloTasiImmobile>();

					for (int i = 0; i < pos.size(); i++) {
						DatiCalcoloTasiImmobile datiCalcoloTasiRiepilogo = new DatiCalcoloTasiImmobile();
						datiCalcoloTasiRiepilogo.setIndirizzo(PortletUtils.getIndirizzoFromPosizione(pos.get(i)));
						datiCalcoloTasiRiepilogo.setFoglio(String.valueOf(pos.get(i).getFoglio()));
						datiCalcoloTasiRiepilogo.setNum(String.valueOf(pos.get(i).getParticella()));
						datiCalcoloTasiRiepilogo.setQuota(String.valueOf(pos.get(i).getPercentualePossesso()));
						Codifica categoriaImmobile = pos.get(i).getCategoriaImmobile();
						if (categoriaImmobile != null) {
							datiCalcoloTasiRiepilogo.setCategoria(categoriaImmobile.getCodice());
						}
						datiCalcoloTasiRiepilogo.setSezione(pos.get(i).getSezione());
						datiCalcoloTasiRiepilogo.setValoreim(String.valueOf(pos.get(i).getValoreImmobile()));
						datiCalcoloTasiRiepilogo.setValoreimtable(String.valueOf(pos.get(i).getValoreImmobile()));
						datiCalcoloTasiRiepilogo.setAliquota(Double.toString(pos.get(i).getAliquota()));

						String mesiPoss = String.valueOf(pos.get(i).getMesiPossesso());
						if (mesiPoss != null && mesiPoss.compareTo("") != 0) {
							datiCalcoloTasiRiepilogo.setQuotaMesi(String.valueOf(pos.get(i).getMesiPossesso()));
						}
						else {
							datiCalcoloTasiRiepilogo.setQuotaMesi("12");
						}

						datiCalcoloTasiRiepilogo.setDetcasa(String.valueOf(pos.get(i).getImportoDetrazioneAbPrincipale()));
						datiCalcoloTasiRiepilogo.setAnnoDiRiferimento(datiCalTasi.getAnno());
						datiCalcoloTasiRiepilogo.setCalcoloEffettuato(false);

						listaRiepilogoDati.add(datiCalcoloTasiRiepilogo);
					}

					datiCalTasi.setDatiRiepilogo(listaRiepilogoDati);

					datiCalTasi.setNumeroImmobiliDaVisura(listaRiepilogoDati.size());
					model.addAttribute("situazione", situazione);
					model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				}
			}

			Map<String, Object> param = new HashMap<String, Object>();
			model.addAttribute("param", param);
			setDatiCalcoloTasi(model, datiCalTasi, request);

		}
		catch (Exception e) {
			log.error("getCalcoloTasiForm :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderCalcoloTasiForm");
	}

	@ModelAttribute("listaCategorieImmobili")
	public List<CategoriaImmobile> getCategorieImmobili(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);

		List<CategoriaImmobile> categorieImmobiliByComuneISAAndAnnoAndServizio = categoriaImmobileService.getCategorieImmobiliByComuneISAAndAnnoAndServizio(userPreferences.getIdComuneIsa(),
				new Integer(DateUtils.getAnnoCorrente()), PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);

		return categorieImmobiliByComuneISAAndAnnoAndServizio;
	}

	/**
	 * Setta l'aliquota in base alla tipologia dell'immobile.
	 *
	 * @param datiCalTasi
	 * @param idDivImmobile
	 * @param model
	 * @param response
	 * @param session
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "action=changeSelectCategoriaImmobile")
	public void changeSelectCategoriaImmobileWithSubmit(@ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, Model model, ActionResponse response, PortletSession session, ActionRequest request)
			throws Exception {
		log.debug("changeSelectCategoriaImmobile");

		Method metodo;
		String idDivImmobile = datiCalTasi.getSelectCambiata();
		metodo = datiCalTasi.getClass().getDeclaredMethod("getCategoria" + idDivImmobile);
		String idCategoriaImmobile = (String) metodo.invoke(datiCalTasi);
		CategoriaImmobile categoriaImmobile = categoriaImmobileService.getCategoriaImmobileById(Long.parseLong(idCategoriaImmobile));
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);
		Tributo tributo = servizio.getTributo();

		CategoriaImmobileTributo catImmTributo = null;
		for (CategoriaImmobileTributo tmp : categoriaImmobile.getCategorieImmobiliTributi()) {
			if (tmp.getTributo().getId().equals(tributo.getId())) {
				catImmTributo = tmp;
			}
		}
		// CategoriaImmobileTributo catImmTributo =
		// categoriaImmobile.getCategorieImmobiliTributi().get(i);
		if (catImmTributo != null) {
			Double aliquota = catImmTributo.getAliquota();
			// set aliquota
			metodo = datiCalTasi.getClass().getMethod("setAliquota" + idDivImmobile, String.class);
			metodo.invoke(datiCalTasi, String.valueOf(aliquota));

			metodo = datiCalTasi.getClass().getMethod("setCategoriaImmobileTributo" + idDivImmobile, String.class);
			metodo.invoke(datiCalTasi, String.valueOf(aliquota));

			model.addAttribute("datiCalTasi", datiCalTasi);

			model.addAttribute("listaAgevolazioni", catImmTributo.getAgevolazioni());
			model.addAttribute("listaDetrazioni", catImmTributo.getDetrazioni());
			model.addAttribute("listaEsenzioni", catImmTributo.getEsenzioni());
		}
		setDatiCalcoloTasi(model, datiCalTasi, request);
		response.setRenderParameter("action", "renderCalcoloTasiForm");
	}

	@ResourceMapping(value = "caricaSelectByCategoria")
	public void changeSelectCategoriaImmobile(ResourceRequest request, ResourceResponse response) throws Exception {
		log.debug("changeSelectCategoriaImmobile");

		String message = "";
		String isError = "KO";
		// String idDivImmobile =request.getParameter("idNews");
		String idCategoria = request.getParameter("valoreSelectCategoria");

		JSONObject jsonGlobale = JSONFactoryUtil.createJSONObject();

		if (!idCategoria.equals("")) {
			CategoriaImmobile categoriaImmobile = categoriaImmobileService.getCategoriaImmobileById(Long.parseLong(idCategoria));
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);
			Tributo tributo = servizio.getTributo();

			CategoriaImmobileTributo catImmTributo = null;
			for (CategoriaImmobileTributo tmp : categoriaImmobile.getCategorieImmobiliTributi()) {
				if (tmp.getTributo().getId().equals(tributo.getId())) {
					catImmTributo = tmp;
				}

			}
			if (catImmTributo != null) {
				isError = "OK";
				message = "";

				jsonGlobale.put("isSuccess", isError);
				jsonGlobale.put("message", message);

				Double aliquota = catImmTributo.getAliquota();

				// setta l'aliquota della categoria scelta
				jsonGlobale.put("aliquota", aliquota);

				JSONArray arrayAgevolazione = null;
				if (catImmTributo.getAgevolazioni() != null && !catImmTributo.getAgevolazioni().isEmpty()) {
					arrayAgevolazione = JSONFactoryUtil.createJSONArray();
					for (Agevolazione corrente : catImmTributo.getAgevolazioni()) {
						JSONObject arrayElement = JSONFactoryUtil.createJSONObject();
						arrayElement.put("id", corrente.getId());
						arrayElement.put("descrizione", corrente.getNome());
						arrayAgevolazione.put(arrayElement);
					}
				}
				jsonGlobale.put("listaAgevolazioni", arrayAgevolazione);

				JSONArray arrayDetrazioni = null;
				if (catImmTributo.getDetrazioni() != null && !catImmTributo.getDetrazioni().isEmpty()) {
					arrayDetrazioni = JSONFactoryUtil.createJSONArray();
					for (Detrazione corrente : catImmTributo.getDetrazioni()) {
						JSONObject arrayElement = JSONFactoryUtil.createJSONObject();
						arrayElement.put("id", corrente.getId());
						arrayElement.put("descrizione", corrente.getDescrizione());
						arrayDetrazioni.put(arrayElement);
					}
				}
				jsonGlobale.put("listaDetrazioni", arrayDetrazioni);

				JSONArray arrayEsenzioni = null;
				if (catImmTributo.getEsenzioni() != null && !catImmTributo.getEsenzioni().isEmpty()) {
					arrayEsenzioni = JSONFactoryUtil.createJSONArray();
					for (Esenzione corrente : catImmTributo.getEsenzioni()) {
						JSONObject arrayElement = JSONFactoryUtil.createJSONObject();
						arrayElement.put("id", corrente.getId());
						arrayElement.put("descrizione", corrente.getDescrizione());
						arrayEsenzioni.put(arrayElement);
					}
				}
				jsonGlobale.put("listaEsenzioni", arrayEsenzioni);

			}
			else {
				isError = "KO";
				message = "categoria non trovata";
			}

			try {
				jsonGlobale.put("messaggioRitorno", "OK");
				response.getWriter().write(jsonGlobale.toString());
			}
			catch (IOException e) {
				log.error(e.getMessage());
			}

		}
	}

	/**
	 * Metodo che prende i campi inseriti nella form e li mette in sessione.
	 *
	 * @param datiDichiarazione
	 * @param result
	 * @param model
	 * @param response
	 * @throws Exception
	 */
	@ActionMapping(params = "ope=calcoloTasi")
	public void calcoloTasiParziale(@ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, BindingResult result, @RequestParam(required = false, value = "cambio") String cambio, Model model,
			ActionResponse response, ActionRequest request) throws Exception {
		System.out.println("Ho premuto Calcola Tasi");

		log.debug("Ho premuto Calcola Tasi CF=" + datiCalTasi.getCodiceFiscale());
		log.debug("cambio=" + cambio);

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiCalTasi.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiCalTasi.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
		}
		if (cambio == null) {

			calcoloValidator.validate(datiCalTasi, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderCalcoloTasiForm");
			}
			else {
				log.debug("calcolo ici");
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", helper.getUserPreferences(request).getNomeComune());

				datiCalTasi.setDaPagar();
				datiCalTasi.setTotale();
				datiCalTasi.setRate();
				model.addAttribute("datiCalTasi", datiCalTasi);
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderCalcoloTasiForm");
			}
		}
		else if (cambio != null && getVisuraPosizioniTributarieActive(request)) {

			log.debug("anno = " + datiCalTasi.getAnno());

			DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();
			if (datiCalTasi.getpIva() != null) {
				dichiarazioneTassaImmobiliRichiesta.setPartitaIva(datiCalTasi.getpIva());
			}
			else {
				dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(datiCalTasi.getCodiceFiscale());
			}
			dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(new Integer(datiCalTasi.getAnno()));
			dichiarazioneTassaImmobiliRichiesta.setAnnoFine(new Integer(datiCalTasi.getAnno()));

			DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta,
					helper.getUserPreferences(request));

			List<DatiTassaImmobili> situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
			if (situazioni != null && !situazioni.isEmpty()) {
				DatiTassaImmobili situazione = situazioni.get(0);
				List<Posizioni> pos = situazione.getPosizioni();
				int i;
				for (i = 0; i < pos.size(); i++) {
					Method metodo = datiCalTasi.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
					metodo.invoke(datiCalTasi, PortletUtils.getIndirizzoFromPosizione(pos.get(i)));

					Method metodo1 = datiCalTasi.getClass().getMethod("setFoglio" + (i + 1), String.class);
					metodo1.invoke(datiCalTasi, "" + pos.get(i).getFoglio());

					Method metodo2 = datiCalTasi.getClass().getMethod("setNum" + (i + 1), String.class);
					metodo2.invoke(datiCalTasi, "" + pos.get(i).getParticella());

					Method metodo3 = datiCalTasi.getClass().getMethod("setQuota" + (i + 1), String.class);
					metodo3.invoke(datiCalTasi, "" + pos.get(i).getPercentualePossesso());

					Method metodo4 = datiCalTasi.getClass().getMethod("setCategoria" + (i + 1), String.class);
					metodo4.invoke(datiCalTasi, pos.get(i).getCategoriaImmobile());

					Method metodo5 = datiCalTasi.getClass().getMethod("setSezione" + (i + 1), String.class);
					metodo5.invoke(datiCalTasi, pos.get(i).getSezione());

					Method metodo6 = datiCalTasi.getClass().getMethod("setValoreim" + (i + 1), String.class);
					metodo6.invoke(datiCalTasi, String.valueOf(pos.get(i).getValoreImmobile()));

					Method metodo7 = datiCalTasi.getClass().getMethod("setAliquota" + (i + 1), String.class);
					metodo7.invoke(datiCalTasi, pos.get(i).getAliquota());

					Method metodo8 = datiCalTasi.getClass().getMethod("setMesi" + (i + 1), String.class);
					metodo8.invoke(datiCalTasi, String.valueOf(pos.get(i).getMesiPossesso()));

					Method metodo9 = datiCalTasi.getClass().getMethod("setDetcasa" + (i + 1), String.class);
					metodo9.invoke(datiCalTasi, String.valueOf(pos.get(i).getImportoDetrazioneAbPrincipale()));

				}
				log.debug("i=" + i);
				for (int j = i; j < 5; j++) {
					Method metodo = datiCalTasi.getClass().getMethod("setIndirizzo" + (j + 1), String.class);
					metodo.invoke(datiCalTasi, "");

					Method metodo1 = datiCalTasi.getClass().getMethod("setFoglio" + (j + 1), String.class);
					metodo1.invoke(datiCalTasi, "");

					Method metodo2 = datiCalTasi.getClass().getMethod("setNum" + (j + 1), String.class);
					metodo2.invoke(datiCalTasi, "");

					Method metodo3 = datiCalTasi.getClass().getMethod("setQuota" + (j + 1), String.class);
					metodo3.invoke(datiCalTasi, "");

					Method metodo4 = datiCalTasi.getClass().getMethod("setCategoria" + (j + 1), String.class);
					metodo4.invoke(datiCalTasi, "");

					Method metodo5 = datiCalTasi.getClass().getMethod("setSezione" + (j + 1), String.class);
					metodo5.invoke(datiCalTasi, "");

					Method metodo6 = datiCalTasi.getClass().getMethod("setValoreim" + (j + 1), String.class);
					metodo6.invoke(datiCalTasi, "");

					Method metodo7 = datiCalTasi.getClass().getMethod("setAliquota" + (j + 1), String.class);
					metodo7.invoke(datiCalTasi, "");

					Method metodo8 = datiCalTasi.getClass().getMethod("setMesi" + (j + 1), String.class);
					metodo8.invoke(datiCalTasi, "");

					Method metodo9 = datiCalTasi.getClass().getMethod("setDetcasa" + (j + 1), String.class);
					metodo9.invoke(datiCalTasi, "");

				}
			}

			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			setDatiCalcoloTasi(model, datiCalTasi, request);
			response.setRenderParameter("action", "renderCalcoloTasiForm");
		}
	}

	@ActionMapping(params = "ope=calcoloTasiTotale")
	public void calcoloTasiTotale(@ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, BindingResult result, Model model, ActionResponse response, ActionRequest request) throws Exception {

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiCalTasi.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiCalTasi.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
			log.error("Error :: calcoloTasiTotale :: " + e);
		}

		List<DatiCalcoloTasiImmobile> lista = new ArrayList<DatiCalcoloTasiImmobile>();
		String indice = datiCalTasi.getNumeroDivCalcoloParziale();
		int i = 0;

		// Se datiRiepilogo è NULL, significa che sto inserendo il primo immobile
		if (datiCalTasi.getDatiRiepilogo() != null) {
			lista = datiCalTasi.getDatiRiepilogo();
		}
		else {
			datiCalTasi.setDatiRiepilogo(lista);
		}

		// Se l'id è "reset", allora deve essere ricaricata la pagina
		if (!indice.equalsIgnoreCase("reset")) {
			DatiCalcoloTasiImmobile immobile = new DatiCalcoloTasiImmobile();

			// Se l'indice è "new", significa che devo calcolare la tasi di un immobile che è stato
			// appena inserito
			// se l'indice è un valore numerico, devo calcolare la tasi di un immobile
			// precedentemente registrato

			if (indice.equalsIgnoreCase("new")) {
				immobile = this.estraiNuovoImmobile(datiCalTasi);
				// se quello che sto inserendo è il primo immobile(lista == null) setto l'indice a
				// 0, altrimenti l'indice deve essere uguale alla dimensione della lista
				if (lista != null) {
					i = lista.size();
				}
			}
			else {
				// i = Integer.valueOf(indice);
				immobile = lista.get(i);
			}
			immobile.setIndex(indice);

			calcoloValidator.validate(datiCalTasi, result);

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				// Se la validazione è riferita al form di inserimento di un nuovo immobile, setto
				// il flag
				// a TRUE, in questo modo mi visualizzerà il form al caricamento della pagina
				if (indice.equalsIgnoreCase("new")) {
					datiCalTasi.setNuovoImmobile(true);
				}
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderCalcoloTasiForm");
			}
			else {

				try {

					// In caso di aggiunta di un

					/* dato per il calcolo */
					BigDecimal rendita = new BigDecimal(immobile.getValoreim());
					/* se il campo valore rendita è stato inserito, procedo a calcolare */
					if (rendita.compareTo(new BigDecimal(0)) != 0) {

						BigDecimal quotaPercPossesso = new BigDecimal(immobile.getQuota());
						/* dato per il calcolo */
						BigDecimal quotaPossesso = quotaPercPossesso.divide(new BigDecimal(100), MathContext.DECIMAL128);

						BigDecimal quotaMesiIntero = new BigDecimal(immobile.getQuotaMesi());
						/* dato per il calcolo */
						BigDecimal quotaMesiDiv12 = quotaMesiIntero.divide(new BigDecimal(12), MathContext.DECIMAL128);

						/* dato per il calcolo */
						BigDecimal aliquota = new BigDecimal(immobile.getAliquota());
						aliquota = aliquota.divide(new BigDecimal(1000), MathContext.DECIMAL128);

						String idAgevolazione = immobile.getAgevolazione();

						String idDetrazione = immobile.getDetcasa();

						String idEsenzione = immobile.getEsenzione();

						String categoriaImmobileId = immobile.getCategoria();

						/* RECUPERO DATI PERTINENZE */

						String idAgevPertC2 = immobile.getPertAgevolazioneC2();

						String idAgevPertC6 = immobile.getPertAgevolazioneC6();

						String idAgevPertC7 = immobile.getPertAgevolazioneC7();

						BigDecimal pertPossessoPercC2 = new BigDecimal(0);
						BigDecimal quotaPossessoPertC2 = new BigDecimal(0);
						BigDecimal pertPossessoMesiC2 = new BigDecimal(0);
						BigDecimal quotaMesiPertC2Div12 = new BigDecimal(0);
						/*
						 * recupera i valori delle pertinenze solo se la rendita catastale è stata
						 * inserita
						 */

						BigDecimal renditaPertC2 = new BigDecimal(0);
						if (immobile.getPertRenditaCatC2() != null && !(immobile.getPertRenditaCatC2()).equals("")) {
							renditaPertC2 = new BigDecimal(immobile.getPertRenditaCatC2());

							pertPossessoPercC2 = new BigDecimal(immobile.getPertPossessoPercC2());
							/* dato per il calcolo pert */
							quotaPossessoPertC2 = pertPossessoPercC2.divide(new BigDecimal(100), MathContext.DECIMAL128);

							pertPossessoMesiC2 = new BigDecimal(immobile.getPertPossessoMesiC2());
							/* dato per il calcolo */
							quotaMesiPertC2Div12 = pertPossessoMesiC2.divide(new BigDecimal(12), MathContext.DECIMAL128);

						}

						BigDecimal pertPossessoPercC6 = new BigDecimal(0);
						BigDecimal quotaPossessoPertC6 = new BigDecimal(0);
						BigDecimal pertPossessoMesiC6 = new BigDecimal(0);
						BigDecimal quotaMesiPertC6Div12 = new BigDecimal(0);

						/*
						 * recupera i valori delle pertinenze solo se la rendita catastale è stata
						 * inserita
						 */
						BigDecimal renditaPertC6 = new BigDecimal(0);
						if (immobile.getPertRenditaCatC6() != null && !(immobile.getPertRenditaCatC6()).equals("")) {
							renditaPertC6 = new BigDecimal(immobile.getPertRenditaCatC6());

							pertPossessoPercC6 = new BigDecimal(immobile.getPertPossessoPercC6());
							/* dato per il calcolo pert */
							quotaPossessoPertC6 = pertPossessoPercC6.divide(new BigDecimal(100), MathContext.DECIMAL128);
							pertPossessoMesiC6 = new BigDecimal(immobile.getPertPossessoMesiC6());
							/* dato per il calcolo */
							quotaMesiPertC6Div12 = pertPossessoMesiC6.divide(new BigDecimal(12), MathContext.DECIMAL128);

						}

						BigDecimal pertPossessoPercC7 = new BigDecimal(0);
						BigDecimal quotaPossessoPertC7 = new BigDecimal(0);
						BigDecimal pertPossessoMesiC7 = new BigDecimal(0);
						BigDecimal quotaMesiPertC7Div12 = new BigDecimal(0);

						/*
						 * recupera i valori delle pertinenze solo se la rendita catastale è stata
						 * inserita
						 */

						BigDecimal renditaPertC7 = new BigDecimal(0);
						if (immobile.getPertRenditaCatC7() != null && !(immobile.getPertRenditaCatC7()).equals("")) {
							renditaPertC7 = new BigDecimal(immobile.getPertRenditaCatC7());

							pertPossessoPercC7 = new BigDecimal(immobile.getPertPossessoPercC7());
							/* dato per il calcolo pert */
							quotaPossessoPertC7 = pertPossessoPercC7.divide(new BigDecimal(100), MathContext.DECIMAL128);

							pertPossessoMesiC7 = new BigDecimal(immobile.getPertPossessoMesiC7());
							/* dato per il calcolo */
							quotaMesiPertC7Div12 = pertPossessoMesiC7.divide(new BigDecimal(12), MathContext.DECIMAL128);

						}

						/* fine recupero pertinenze */

						BigDecimal importoDetrazione = new BigDecimal(0);
						boolean esenzioneVal = false;

						/* recupero la categoria selezionata */
						Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);
						Tributo tributo = servizio.getTributo();
						CategoriaImmobile cat = categoriaImmobileService.getCategoriaImmobileById(Long.parseLong(categoriaImmobileId));

						CategoriaImmobileTributo catImmTributo = null;
						for (CategoriaImmobileTributo tmp : cat.getCategorieImmobiliTributi()) {
							if (tmp.getTributo().getId().equals(tributo.getId())) {
								catImmTributo = tmp;
							}

						}

						BigDecimal valoreAgevolazione = new BigDecimal(1);

						BigDecimal valoreAgevolazionePertC2 = new BigDecimal(1);
						BigDecimal valoreAgevolazionePertC6 = new BigDecimal(1);
						BigDecimal valoreAgevolazionePertC7 = new BigDecimal(1);

						/* VALORI RELATIVO ALLE PERTINENZE */
						BigDecimal impostaPertC2 = new BigDecimal(0);
						BigDecimal impostaPertC6 = new BigDecimal(0);
						BigDecimal impostaPertC7 = new BigDecimal(0);
						BigDecimal impostaTotPertinenze = new BigDecimal(0);

						/* dati comuni per il calcolo */
						BigDecimal coefficienteMoltiplicazione = new BigDecimal(cat.getCoefficienteMoltiplicazione());
						BigDecimal rivalutazione = new BigDecimal(cat.getBaseDiCalcolo().getValore());

						if (catImmTributo != null) {

							model.addAttribute("listaAgevolazioni", catImmTributo.getAgevolazioni());
							model.addAttribute("listaDetrazioni", catImmTributo.getDetrazioni());
							model.addAttribute("listaEsenzioni", catImmTributo.getEsenzioni());

							/*
							 * scorro le agevolazioni. In un unico ciclo riesco a recuperare tutte
							 * le agevolazioni selezionate
							 */
							// if( ){
							Agevolazione agevolazione = null;
							Agevolazione agevolazionePertiC2 = null;
							Agevolazione agevolazionePertiC6 = null;
							Agevolazione agevolazionePertiC7 = null;

							for (Agevolazione tmpAgev : catImmTributo.getAgevolazioni()) {

								/*
								 * recupera oggetto agevolazione in base all'agevolazione
								 * selezionata
								 */
								if (!idAgevolazione.equalsIgnoreCase("NO") && !idAgevolazione.equalsIgnoreCase("NONE") && tmpAgev.getId().compareTo(Long.valueOf(idAgevolazione)) == 0) {
									agevolazione = tmpAgev;

								}
								/*
								 * recupera oggetto agevolazione in base all'agevolazione della
								 * pertinenza C2 se selezionata
								 */
								if (idAgevPertC2 != null && idAgevPertC2.compareTo("") != 0 && !idAgevPertC2.equalsIgnoreCase("NO") && !idAgevPertC2.equalsIgnoreCase("NONE")
										&& tmpAgev.getId().compareTo(Long.valueOf(idAgevPertC2)) == 0) {
									agevolazionePertiC2 = tmpAgev;
								}
								/*
								 * recupera oggetto agevolazione in base all'agevolazione della
								 * pertinenza C6 se selezionata
								 */
								if (idAgevPertC6 != null && idAgevPertC6.compareTo("") != 0 && !idAgevPertC6.equalsIgnoreCase("NO") && !idAgevPertC6.equalsIgnoreCase("NONE")
										&& tmpAgev.getId().compareTo(Long.valueOf(idAgevPertC6)) == 0) {
									agevolazionePertiC6 = tmpAgev;
								}
								/*
								 * recupera oggetto agevolazione in base all'agevolazione della
								 * pertinenza C7 se selezionata
								 */
								if (idAgevPertC7 != null && idAgevPertC7.compareTo("") != 0 && !idAgevPertC7.equalsIgnoreCase("NO") && !idAgevPertC7.equalsIgnoreCase("NONE")
										&& tmpAgev.getId().compareTo(Long.valueOf(idAgevPertC7)) == 0) {
									agevolazionePertiC7 = tmpAgev;
								}

							}

							if (agevolazione != null) {
								valoreAgevolazione = new BigDecimal(agevolazione.getValore()).divide(new BigDecimal(100), MathContext.DECIMAL128);
							}

							if (agevolazionePertiC2 != null) {
								valoreAgevolazionePertC2 = new BigDecimal(agevolazionePertiC2.getValore()).divide(new BigDecimal(100), MathContext.DECIMAL128);
							}

							if (agevolazionePertiC6 != null) {
								valoreAgevolazionePertC6 = new BigDecimal(agevolazionePertiC6.getValore()).divide(new BigDecimal(100), MathContext.DECIMAL128);
							}

							if (agevolazionePertiC7 != null) {
								valoreAgevolazionePertC7 = new BigDecimal(agevolazionePertiC7.getValore()).divide(new BigDecimal(100), MathContext.DECIMAL128);
								// }//
							}

							// scorro le detrazioni
							if (!idDetrazione.equalsIgnoreCase("NO") && !idDetrazione.equalsIgnoreCase("NONE")) {
								Detrazione detrazione = null;
								for (Detrazione tmpDetra : catImmTributo.getDetrazioni()) {
									if (tmpDetra.getId().compareTo(Long.valueOf(idDetrazione)) == 0) {
										detrazione = tmpDetra;
										break;
									}
								}

								if (detrazione != null) {
									importoDetrazione = new BigDecimal(detrazione.getImporto());
								}
							}

							// scorro le esenzioni
							if (!idEsenzione.equalsIgnoreCase("NO") && !idEsenzione.equalsIgnoreCase("NONE")) {
								Esenzione esenzione = null;
								for (Esenzione tmpEse : catImmTributo.getEsenzioni()) {
									if (tmpEse.getId().compareTo(Long.valueOf(idEsenzione)) == 0) {
										esenzione = tmpEse;
										break;
									}
								}

								if (esenzione != null) {
									esenzioneVal = true;
								}
								else {
									esenzioneVal = false;
								}
							}

						}

						/* CALCOLO RELATIVO ALLE PERTINENZE */

						/*
						 * se è stato inserita la rendita della pertinenza procedo al calcolo,
						 * altrimenti è pari a zero
						 */
						if (renditaPertC2.compareTo(new BigDecimal(0)) != 0) {
							BigDecimal imponibilePertC2 = renditaPertC2.multiply(rivalutazione).multiply(coefficienteMoltiplicazione); // rendita
							// *
							// rivalutazione
							// *
							// coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC2 = imponibilePertC2.multiply(quotaPossessoPertC2).multiply(quotaMesiPertC2Div12).multiply(aliquota).multiply(valoreAgevolazionePertC2);

						}

						if (renditaPertC6.compareTo(new BigDecimal(0)) != 0) {
							BigDecimal imponibilePertC6 = renditaPertC6.multiply(rivalutazione).multiply(coefficienteMoltiplicazione); // rendita
							// *
							// rivalutazione
							// *
							// coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC6 = imponibilePertC6.multiply(quotaPossessoPertC6).multiply(quotaMesiPertC6Div12).multiply(aliquota).multiply(valoreAgevolazionePertC6);

						}

						if (renditaPertC7.compareTo(new BigDecimal(0)) != 0) {
							BigDecimal imponibilePertC7 = renditaPertC7.multiply(rivalutazione).multiply(coefficienteMoltiplicazione); // rendita
							// *
							// rivalutazione
							// *
							// coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC7 = imponibilePertC7.multiply(quotaPossessoPertC7).multiply(quotaMesiPertC7Div12).multiply(aliquota).multiply(valoreAgevolazionePertC7);

						}

						impostaTotPertinenze = impostaPertC2.add(impostaPertC6).add(impostaPertC7);
						/*-------------------------------------------------------*/

						/* CALCOLO RELATIVO ALL'IMMOBILE */

						BigDecimal imponibile = rendita.multiply(rivalutazione).multiply(coefficienteMoltiplicazione); // rendita
						// *
						// rivalutazione
						// *
						// coefficienteMoltiplicazione;

						BigDecimal imposta = imponibile.multiply(quotaPossesso).multiply(quotaMesiDiv12).multiply(aliquota).multiply(valoreAgevolazione); // imponibile
						// *
						// quotaPossesso
						// *
						// quotaMesiDiv12
						// *
						// aliquota
						// *
						// valoreAgevolazione;

						BigDecimal importoParziale; // (tot)

						imposta = imposta.add(impostaTotPertinenze);
						// nomeMetodo = "ImpostaTasi";
						// metodo = datiCalTasi.getClass().getMethod(tipoSet + nomeMetodo + (i + 1),
						// String.class);
						BigDecimal impostaRounded = imposta.setScale(2, BigDecimal.ROUND_HALF_UP);
						// metodo.invoke(datiCalTasi, impostaRounded.toString());
						immobile.setImpostaTasi(impostaRounded.toString());
						BigDecimal totaleDetrazionePerRiepilogo = new BigDecimal(0);

						if (esenzioneVal == true) {
							/*
							 * se è presente l'esenzione, vince su tutto, quindi
							 * l'importo(ImportoParziale) da pagare è zero. L'imposta viene comunque
							 * calcolata
							 */
							BigDecimal sottraendo = imposta;
							importoParziale = imposta.subtract(sottraendo);
							// nomeMetodo = "ImportoDetrazione";
							// metodo = datiCalTasi.getClass().getMethod(tipoSet + nomeMetodo + (i +
							// 1), String.class);
							// metodo.invoke(datiCalTasi, impostaRounded.toString());
							immobile.setImportoDetrazione(impostaRounded.toString());
							totaleDetrazionePerRiepilogo = impostaRounded;

						}
						else {
							/*
							 * se non c'è esenzione, calcolo l'importoTotaleParziale con le
							 * detrazioni, se presenti. nella formula è l'importo Tasi.
							 */

							importoParziale = imposta.subtract(importoDetrazione);
							/* se minore di 0 (es. 30.5€ - 100€ = -69.5 => 0 */
							if (importoParziale.compareTo(new BigDecimal(0)) < 0) {
								importoParziale = new BigDecimal(0);
							}

							// nomeMetodo = "ImportoDetrazione";
							// metodo = datiCalTasi.getClass().getMethod(tipoSet + nomeMetodo + (i +
							// 1), String.class);
							BigDecimal importoRounded = importoDetrazione.setScale(2, BigDecimal.ROUND_HALF_UP);
							// metodo.invoke(datiCalTasi, importoRounded.toString());
							immobile.setImportoDetrazione(importoRounded.toString());
							totaleDetrazionePerRiepilogo = importoRounded;

						}

						// IMPORTO TASI da pagare (iposta-detrazioni)
						// nomeMetodo = "Dapagar";
						// metodo = datiCalTasi.getClass().getMethod(tipoSet + nomeMetodo + (i + 1),
						// String.class);
						BigDecimal importoParzialeRounded = importoParziale.setScale(2, BigDecimal.ROUND_HALF_UP);
						// metodo.invoke(datiCalTasi, importoParzialeRounded.toString());
						immobile.setDapagar(importoParzialeRounded.toString());
						/* se è > ho aggiunto oltre la visura un immobile all'elenco */
						// if (Integer.valueOf(datiCalTasi.getNumeroDivCalcoloParziale()) >
						// datiCalTasi.getDatiRiepilogo().size()) {
						// datiCalTasi.getDatiRiepilogo().add(new DatiCalcoloTasiImmobile());
						// }

						// IN CASO DI CALCOLO DI TASI PER UN IMMOBILE APPENA INSERITO, AGGIUNGO
						// QUEST'ULTIMO NELLA TABELLA
						if (indice.equalsIgnoreCase("new")) {
							lista.add(i, immobile);
						}

						/* aggiorno campi visura in lista riepilogo */
						String indirizzo = immobile.getIndirizzo();
						// datiCalTasi.getDatiRiepilogo().get(i).setIndirizzo(indirizzo);
						lista.get(i).setIndirizzo(indirizzo);

						String foglio = immobile.getFoglio();
						// datiCalTasi.getDatiRiepilogo().get(i).setFoglio(foglio);
						lista.get(i).setFoglio(foglio);

						String num = immobile.getNum();
						// datiCalTasi.getDatiRiepilogo().get(i).setNum(num);
						lista.get(i).setNum(num);

						String sezione = immobile.getSezione();
						// datiCalTasi.getDatiRiepilogo().get(i).setSezione(sezione);
						lista.get(i).setSezione(sezione);

						BigDecimal renditaTotale = rendita.add(renditaPertC2).add(renditaPertC6).add(renditaPertC7);
						// datiCalTasi.getDatiRiepilogo().get(i).setValoreim(renditaTotale.toString());
						// datiCalTasi.getDatiRiepilogo().get(i).setValoreimtable(renditaTotale.toString());
						lista.get(i).setValoreimtable(renditaTotale.toString());

						// datiCalTasi.getDatiRiepilogo().get(i).setTotaleImmobile(importoParzialeRounded.toString());
						lista.get(i).setTotaleImmobile(importoParzialeRounded.toString());

						// datiCalTasi.getDatiRiepilogo().get(i).setTotaleImmobileNonArrotondato(importoParziale.toString());
						lista.get(i).setTotaleImmobileNonArrotondato(importoParziale.toString());

						// datiCalTasi.getDatiRiepilogo().get(i).setDetrazione(totaleDetrazionePerRiepilogo.toString());
						lista.get(i).setDetrazione(totaleDetrazionePerRiepilogo.toString());

						// datiCalTasi.getDatiRiepilogo().get(i).setCodTributo(catImmTributo.getCodiceTributo().toString());
						if (catImmTributo.getCodiceTributo() != null) {
							lista.get(i).setCodTributo(catImmTributo.getCodiceTributo().toString());
						}

						// datiCalTasi.getDatiRiepilogo().get(i).setCalcoloEffettuato(true);
						lista.get(i).setCalcoloEffettuato(true);

						datiCalTasi.setDatiFormRiepilogo(lista);

						/* calcolo del totale tasi da pagare */
						BigDecimal totaleDaPagareNonArrotondato = new BigDecimal(0);
						BigDecimal totaleDaPagareArrotondato = new BigDecimal(0);
						// BigDecimal tmpTotaliNonArrotondati = new BigDecimal(0);
						for (int k = 0; k < datiCalTasi.getDatiRiepilogo().size(); k++) {
							BigDecimal valoreCorr = new BigDecimal(datiCalTasi.getDatiRiepilogo().get(k).getTotaleImmobileNonArrotondato());
							totaleDaPagareNonArrotondato = totaleDaPagareNonArrotondato.add(valoreCorr);
						}
						totaleDaPagareArrotondato = totaleDaPagareNonArrotondato.setScale(2, BigDecimal.ROUND_HALF_UP);

						datiCalTasi.setTotaleNonArrotondato(totaleDaPagareNonArrotondato.toString());
						datiCalTasi.setTotale(totaleDaPagareArrotondato.toString());

						// Quando caricherà la pagina, non devo inserire un nuovo immobile,
						// quindi setto il flag a FALSE
						datiCalTasi.setNuovoImmobile(false);

						/*
						 * Setto a NULL tutti i campi utilizzati per l'inserimento di un nuovo
						 * immobile
						 */
						datiCalTasi.setIndirizzo(null);
						datiCalTasi.setFoglio(null);
						datiCalTasi.setNum(null);
						datiCalTasi.setSezione(null);
						datiCalTasi.setQuota(null);
						datiCalTasi.setQuotaMesi(null);
						datiCalTasi.setDetcasa(null);
						datiCalTasi.setValoreim(null);
						datiCalTasi.setAliquota(null);
						datiCalTasi.setPertAgevolazioneC2(null);
						datiCalTasi.setPertAgevolazioneC6(null);
						datiCalTasi.setPertAgevolazioneC7(null);
						datiCalTasi.setPertPossessoMesiC2(null);
						datiCalTasi.setPertPossessoMesiC6(null);
						datiCalTasi.setPertPossessoMesiC7(null);
						datiCalTasi.setPertPossessoPercC2(null);
						datiCalTasi.setPertPossessoPercC6(null);
						datiCalTasi.setPertPossessoPercC7(null);
						datiCalTasi.setPertRenditaCatC2(null);
						datiCalTasi.setPertRenditaCatC6(null);
						datiCalTasi.setPertRenditaCatC7(null);
						model.addAttribute("listaEsenzioni", null);
						model.addAttribute("listaDetrazioni", null);
						model.addAttribute("listaAgevolazioni", null);

					}

				}
				catch (SecurityException e) {
					log.error("calcoloTasiTotale :: " + e.getMessage(), e);
				}
				catch (IllegalArgumentException e) {
					log.error("calcoloTasiTotale :: " + e.getMessage(), e);
				}

			}
		}
		else {
			/*
			 * Setto a NULL tutti i campi utilizzati per l'inserimento di un nuovo immobile
			 */
			datiCalTasi.setIndirizzo(null);
			datiCalTasi.setFoglio(null);
			datiCalTasi.setNum(null);
			datiCalTasi.setSezione(null);
			datiCalTasi.setQuota(null);
			datiCalTasi.setQuotaMesi(null);
			datiCalTasi.setDetcasa(null);
			datiCalTasi.setValoreim(null);
			datiCalTasi.setAliquota(null);
			datiCalTasi.setPertAgevolazioneC2(null);
			datiCalTasi.setPertAgevolazioneC6(null);
			datiCalTasi.setPertAgevolazioneC7(null);
			datiCalTasi.setPertPossessoMesiC2(null);
			datiCalTasi.setPertPossessoMesiC6(null);
			datiCalTasi.setPertPossessoMesiC7(null);
			datiCalTasi.setPertPossessoPercC2(null);
			datiCalTasi.setPertPossessoPercC6(null);
			datiCalTasi.setPertPossessoPercC7(null);
			datiCalTasi.setPertRenditaCatC2(null);
			datiCalTasi.setPertRenditaCatC6(null);
			datiCalTasi.setPertRenditaCatC7(null);
			model.addAttribute("listaEsenzioni", null);
			model.addAttribute("listaDetrazioni", null);
			model.addAttribute("listaAgevolazioni", null);

			model.addAttribute("formError", null);
		}
		setDatiCalcoloTasi(model, datiCalTasi, request);
		response.setRenderParameter("action", "renderCalcoloTasiForm");

	}

	@ResourceMapping("calcoloTasiRiepilogo")
	public void calcoloTasiRiepilogo(@ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, Model model, ResourceResponse response, PortletSession session, ResourceRequest request)
			throws Exception {
		log.debug("calcoloTasiRiepilogo ");

	}

	/**
	 * Metodo che genera la dichiarazione TASI (F24) e ne consente il download.
	 *
	 * @param datiTari
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneReport")
	public void serveCertificato(@ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {
		log.debug("dichiarazioneReport ");

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		Long idComuneIsa = helper.getUserPreferences(request).getIdComuneIsa();
		ComuneISA comuneByPk = comuneISAService.getComuneByPk(idComuneIsa);
		String codiceCatastale = comuneByPk.getComune().getCodiceCatastale();

		// Query the service layer for some objects
		List<DatiF24> beans = new ArrayList<DatiF24>();

		DatiF24 datiF24 = new DatiF24();

		if (datiCalTasi != null) {
			List<DatiCalcoloTasiImmobile> listaImmobili = datiCalTasi.getDatiRiepilogo();
			/* inserisco solo gli immobili di cui è stato effettuato il calcolo */
			List<MotivoPagamentoF24> motiviPagamento = new ArrayList<MotivoPagamentoF24>();
			for (DatiCalcoloTasiImmobile datiCalcoloTasiRiepilogo : listaImmobili) {
				if (datiCalcoloTasiRiepilogo.isCalcoloEffettuato()) {
					MotivoPagamentoF24 motivoPagamentoF24 = new MotivoPagamentoF24();
					motivoPagamentoF24.setAcconto(false);
					motivoPagamentoF24.setAnnoRiferimento(datiCalcoloTasiRiepilogo.getAnnoDiRiferimento());
					motivoPagamentoF24.setCodiceEnte(codiceCatastale);
					motivoPagamentoF24.setCodiceTributo(datiCalcoloTasiRiepilogo.getCodTributo());
					motivoPagamentoF24.setDetrazione(datiCalcoloTasiRiepilogo.getDetrazione());
					motivoPagamentoF24.setImmobiliVariati(false);
					motivoPagamentoF24.setImportiACreditoCompensati(datiCalcoloTasiRiepilogo.getImportiACredito());
					motivoPagamentoF24.setImportiADebitoVersati(datiCalcoloTasiRiepilogo.getTotaleImmobile());
					motivoPagamentoF24.setNumeroImmobili(datiCalcoloTasiRiepilogo.getnImmobili());
					motivoPagamentoF24.setRateazioneMeseRiferimento(datiCalcoloTasiRiepilogo.getRateazione());
					motivoPagamentoF24.setRavvedimento(false);
					motivoPagamentoF24.setSaldo(true);
					motivoPagamentoF24.setSezione(datiCalcoloTasiRiepilogo.getSezione());
					motiviPagamento.add(motivoPagamentoF24);
				}
			}

			// Aggiunta elementi vuoti
			if (motiviPagamento.size() < 10) {
				int elementiMancanti = 10 - motiviPagamento.size();
				for (int i = 0; i < elementiMancanti; i++) {
					motiviPagamento.add(new MotivoPagamentoF24());
				}
			}

			param.put("subreportParameters1", motiviPagamento);

			datiF24.setMotiviPagamento(motiviPagamento);
			datiF24.setAgenzia("");
			datiF24.setAgenziaProv("");
			datiF24.setCodAtto("");
			datiF24.setCodFiscale(datiCalTasi.getCodiceFiscale());
			datiF24.setCodFiscaleAltro("");
			datiF24.setCodIdentificativo("");
			datiF24.setCodUfficio("");
			datiF24.setCognome(datiCalTasi.getCognome());
			datiF24.setComuneDiNascita(datiCalTasi.getComuneNascita());
			datiF24.setDataDiNascita(datiCalTasi.getDataNascita());
			datiF24.setDelegaIrrevocabileA(null);
			datiF24.setIban(datiCalTasi.getIban());
			datiF24.setNome(datiCalTasi.getNome());
			datiF24.setProvDiNascita(datiCalTasi.getProvinciaNascita());
			datiF24.setSesso(datiCalTasi.getSesso());
			datiF24.setTotale(datiCalTasi.getTotale());
		}
		beans.add(datiF24);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);
		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
		.setNomeServizio(SERVIZIO)
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
			.addOperazioneRichiesta(SERVIZIO)
			.addUrlRichiesta(ApplicationContextUtil.getCurrentUrl(request))
			.addPaginaCorrente(helper.getCurrentPageName(request))
			.addEsitoSuccess("SUCCESS - OPERAZIONE CONCLUSA")
			.addServizioTimeString()
			.addServizioUuidTransazione(uuidOperazione)
			.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_TASI)
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		

		String reportFileName = String.format(REPORT_NAME);
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
	 * Presenta la form per il calcolo TASI.
	 *
	 * @param model
	 * @throws Exception
	 */
	@RenderMapping(params = "action=renderCalcoloTasiForm")
	public String renderCalcoloTasiForm(Model model) throws Exception {
		return toLocalViewPath("calcolotasi");
	}

	/**
	 * @param model
	 * @param datiCalTasi
	 * @param request
	 * @throws Exception
	 */
	private void setDatiCalcoloTasi(Model model, DatiCalcoloTasi datiCalTasi, ActionRequest request) throws Exception {
		// Setto il liferay date time
		PortletUtils.setDateIntoLiferayInputDate(request, datiCalTasi.getDataNascitaForm(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
		PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		model.addAttribute("datiCalTasi", datiCalTasi);

		// TEST
		String json = new Gson().toJson(datiCalTasi.getDatiFormRiepilogo());
		model.addAttribute("immobiliJson", json);
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
		System.out.println("sono in renderUploadForm");

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
		System.out.println("sono in renderEsitoUpload");
		return toLocalViewPath("esitoUploadDichiarazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/calcolotasi/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	// Metodo utile a convertire in dati del form relativo ad un nuovo immobile in
	// un oggetto di tipo DatiCalcoloTasiImmobile
	private DatiCalcoloTasiImmobile estraiNuovoImmobile(DatiCalcoloTasi datiCalTasi) {

		DatiCalcoloTasiImmobile immobile = new DatiCalcoloTasiImmobile();
		immobile.setIndirizzo(datiCalTasi.getIndirizzo());
		immobile.setFoglio(datiCalTasi.getFoglio());
		immobile.setNum(datiCalTasi.getNum());
		immobile.setSezione(datiCalTasi.getSezione());
		immobile.setCategoria(datiCalTasi.getCategoria());
		immobile.setValoreim(datiCalTasi.getValoreim());
		immobile.setValoreimtable(datiCalTasi.getValoreim());
		immobile.setQuota(datiCalTasi.getQuota());
		immobile.setQuotaMesi(datiCalTasi.getQuotaMesi());
		immobile.setAliquota(datiCalTasi.getAliquota());
		immobile.setAgevolazione(datiCalTasi.getAgevolazione());
		immobile.setDetcasa(datiCalTasi.getDetcasa());
		immobile.setEsenzione(datiCalTasi.getEsenzione());

		immobile.setPertAgevolazioneC2(datiCalTasi.getPertAgevolazioneC2());
		immobile.setPertPossessoMesiC2(datiCalTasi.getPertPossessoMesiC2());
		immobile.setPertPossessoPercC2(datiCalTasi.getPertPossessoPercC2());
		immobile.setPertRenditaCatC2(datiCalTasi.getPertRenditaCatC2());

		immobile.setPertAgevolazioneC6(datiCalTasi.getPertAgevolazioneC6());
		immobile.setPertPossessoMesiC6(datiCalTasi.getPertPossessoMesiC6());
		immobile.setPertPossessoPercC6(datiCalTasi.getPertPossessoPercC6());
		immobile.setPertRenditaCatC6(datiCalTasi.getPertRenditaCatC6());

		immobile.setPertAgevolazioneC7(datiCalTasi.getPertAgevolazioneC7());
		immobile.setPertPossessoMesiC7(datiCalTasi.getPertPossessoMesiC7());
		immobile.setPertPossessoPercC7(datiCalTasi.getPertPossessoPercC7());
		immobile.setPertRenditaCatC2(datiCalTasi.getPertRenditaCatC7());

		return immobile;
	}

	@ActionMapping(params = "op=eliminaImmobile")
	public void elimina(@RequestParam("id") int id, @ModelAttribute("datiCalTasi") DatiCalcoloTasi datiCalTasi, Model model, ActionResponse response, PortletSession session, ActionRequest request) {
		datiCalTasi.getDatiRiepilogo().remove(id);

		String totale = getTotaleAggiornato(datiCalTasi, true);
		String totaleNonArrotondato = getTotaleAggiornato(datiCalTasi, false);
		datiCalTasi.setTotale(totale);
		datiCalTasi.setTotaleNonArrotondato(totaleNonArrotondato);

		try {
			setDatiCalcoloTasi(model, datiCalTasi, request);
		}
		catch (Exception e) {
			log.error("elimina :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderCalcoloTasiForm");
	}

	/**
	 * Dato l'oggetto di tipo DatiCalcoloTasi, estraggo la lista aggiornata di immobili e ricalcolo
	 * il calcolo totale (lo restituisce arrotondato se arrotondato=true). Questo metodo viene
	 * richiamato dalla funzione "elimina" per riaggiornare il totale ogniqualvolta si elimina un
	 * immobile.
	 *
	 * @param DatiCalcoloTasi
	 * @param arrotondato
	 */
	private String getTotaleAggiornato(DatiCalcoloTasi dati, boolean arrotondato) {
		String result = "";
		/* calcolo del totale imu da pagare */
		BigDecimal totaleDaPagareNonArrotondato = new BigDecimal(0);
		BigDecimal totaleDaPagareArrotondato = new BigDecimal(0);
		for (int k = 0; k < dati.getDatiRiepilogo().size(); k++) {
			BigDecimal valoreCorr = new BigDecimal(dati.getDatiRiepilogo().get(k).getTotaleImmobileNonArrotondato());
			totaleDaPagareNonArrotondato = totaleDaPagareNonArrotondato.add(valoreCorr);
		}
		totaleDaPagareArrotondato = totaleDaPagareNonArrotondato.setScale(2, BigDecimal.ROUND_HALF_UP);

		if (arrotondato) {
			result = totaleDaPagareArrotondato.toString();
		}
		else {
			result = totaleDaPagareNonArrotondato.toString();
		}
		return result;
	}
}
