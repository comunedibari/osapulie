/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.calcoloimu.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
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
import javax.validation.Valid;

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
import it.osapulie.tributi.web.portlet.calcoloimu.form.DatiCalcoloImu;
import it.osapulie.tributi.web.portlet.calcoloimu.form.DatiCalcoloImuImmobile;
import it.osapulie.tributi.web.portlet.calcoloimu.form.DatiCalcoloImuRiepilogo;
import it.osapulie.tributi.web.portlet.utils.DatiTassaImmobiliComparator;
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
 * Portlet controller per il servizio Calcolo IMU.
 *
 * @author Gianluca Pindinelli
 * @author Damiano Miri
 */
@Controller("calcoloImuPortletController")
@RequestMapping("view")
@SessionAttributes({ "datiCalImu", "param", "uploadItem" })
public class CalcoloImuPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(CalcoloImuPortletController.class);

	private static final String JSP_PATH = "calcoloimu/";

	private static final String REPORT_PATH = "/reports/modelloF24.jrxml";
	private static final String SUB_REPORT_PATH_1 = "/reports/modelloF24_subreport1.jrxml";
	private static final String REPORT_NAME = "f24_semplificato_IMU.pdf";
	private static final String SERVIZIO = "SERVIZIO CALCOLO IMU";
	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private TributiService tributiService;

	@Inject
	private CategoriaImmobileService categoriaImmobileService;

	@Inject
	private ServizioService servizioService;

	@Inject
	@Named("datiCalcoloImuValidator")
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
		log.debug("homePage-calcoloImuPortletController");
		return toLocalViewPath("home");
	}

	/**
	 * Presenta la form per il Calcolo IMU.
	 *
	 * @param model
	 * @throws Exception
	 */
	@ActionMapping(params = "action=getCalcoloImuForm")
	public void getCalcoloImuForm(Model model, ActionResponse response, ActionRequest request,PortletSession session) throws Exception {

		log.debug("getCalcoloImuForm");
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
					.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
					.setOrigin(Origin.getIp(request))
					.addCallGeo().build();
		
		DwhServizioAttributeDTO servizioAttributeDto = new DwhServizioAttributeDTO();
		DwhDataminingDTO dataminingDto = new DwhDataminingDTO();
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
 

		servizioAttributeDto = DwhServiceAttributeUtil.get(commonService, comuneService, delegaService, servizioService, userPreferences, request, dwhService)
		.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
		.setUUID(uuidOperazione)
		.getServizioAttribute();	
		
		dataminingDto = DwhDataminingUtil.get(codiceFiscale, dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
		.setUuidOperazione(uuidOperazione)
		.getDatamining();
		
		 tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
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
				log.warn("getCalcoloImuForm :: " + e.getMessage());
			}

			DatiCalcoloImu datiCalImu = new DatiCalcoloImu();
			// Controllo profilo cittadino/azienda
			if (partitaIvaServizio != null) {
				datiCalImu.setPartitaIva(partitaIvaServizio);
				Azienda aziendaByPiva = aziendaService.getAziendaByPiva(partitaIvaServizio);
				datiCalImu.setCodiceFiscale(aziendaByPiva.getPartitaIva());
				datiCalImu.setCognome(aziendaByPiva.getRagioneSociale());
				it.osapulie.domain.Indirizzo sede = aziendaByPiva.getSede();
				if (sede != null) {
					datiCalImu.setIndirizzoResidenza(sede.getVia() + ", " + sede.getNrCivico());
					Comune comune = sede.getComune();
					if (comune != null) {
						datiCalImu.setProvResidenza(comune.getProvincia().getSigla());
						datiCalImu.setComuneResidenza(comune.getDenominazione());
					}
				}
				datiCalImu.setDataNascita(null);
			}
			else {
				if (componente != null && datiGen != null) {
					datiCalImu.setCodiceFiscale(componente.getCodiceFiscale());
					datiCalImu.setNome(componente.getNome());
					datiCalImu.setCognome(componente.getCognome());
					datiCalImu.setDataNascita(DateUtils.getDataGGMMAAAA(componente.getDataNascita().getTime()));
					datiCalImu.setDataNascitaForm(componente.getDataNascita().getTime());
					// Setto il liferay date time
					PortletUtils.setDateIntoLiferayInputDate(request, componente.getDataNascita().getTime(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
					// fine

					// Caricamento comune da codice ISTAT
					if (componente.isCittadinanzaItaliana() && componente.getCodiceIstatComuneNascita() != null) {
						Comune comuneByCodiceISTAT = comuneService.getComuneByCodiceISTAT(componente.getCodiceIstatComuneNascita());
						if (comuneByCodiceISTAT != null) {
							datiCalImu.setComuneNascita(comuneByCodiceISTAT.getDenominazione());
							datiCalImu.setProvinciaNascita(comuneByCodiceISTAT.getProvincia().getDenominazioneProvincia());
						}
					}
					datiCalImu.setSesso(componente.getSesso());
					datiCalImu.setIndirizzoResidenza(datiGen.getIndirizzo());
					datiCalImu.setComuneResidenza(datiGen.getComuneResidenza());
					datiCalImu.setProvResidenza(datiGen.getProvinciaResidenza());
					datiCalImu.setCapResidenza(datiGen.getCap());
				}
				else {
					// Caricamento dati da utente in OSApulie
					ProfiloUtenteCittadino profiloUtente = commonService.getCurrentProfiloUtenteCittadino(userPreferences);
					datiCalImu.setCodiceFiscale(profiloUtente.getCodiceFiscale());
					datiCalImu.setNome(profiloUtente.getNome());
					datiCalImu.setCognome(profiloUtente.getCognome());

					// Caricamento comune da codice ISTAT
					ComuneISA comuneIsa = profiloUtente.getComuneIsa();
					if (comuneIsa != null) {
						Comune comune = comuneIsa.getComune();
						if (comune != null) {
							datiCalImu.setComuneNascita(comune.getDenominazione());
							datiCalImu.setProvinciaNascita(comune.getProvincia().getDenominazioneProvincia());
						}
					}

					it.osapulie.domain.Indirizzo residenza = profiloUtente.getResidenza();
					if (residenza != null) {
						datiCalImu.setIndirizzoResidenza(residenza.getVia());
						Comune comune = residenza.getComune();
						if (comune != null) {
							datiCalImu.setComuneResidenza(comune.getDenominazione());
							Provincia provincia = comune.getProvincia();
							if (provincia != null) {
								datiCalImu.setProvResidenza(provincia.getDenominazioneProvincia());
							}
						}
					}
					PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				}
			}

			Integer annoCorrente = new Integer(DateUtils.getAnnoCorrente());
			datiCalImu.setAnno("" + annoCorrente);

			if (getVisuraPosizioniTributarieActive(request)) {

				DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();

				if (partitaIvaServizio != null) {
					dichiarazioneTassaImmobiliRichiesta.setPartitaIva(partitaIvaServizio);
				}
				else {
					dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(codiceFiscale);
				}

				// recupero i dati dell'anno precedente e di quello corrente; se non ci sono dati
				// per l'anno corrente utilizzo gli anni precedebti ed informo l'utente
				dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(annoCorrente - 5);
				dichiarazioneTassaImmobiliRichiesta.setAnnoFine(annoCorrente);

				DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);

				List<DatiTassaImmobili> situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
				Collections.sort(situazioni, new DatiTassaImmobiliComparator());

				if (situazioni != null && !situazioni.isEmpty()) {
					// Prelevo l'ultimo anno
					DatiTassaImmobili situazione = situazioni.get(situazioni.size() - 1);

					List<Posizioni> pos = situazione.getPosizioni();
					List<DatiCalcoloImuImmobile> listaRiepilogoDati = new ArrayList<DatiCalcoloImuImmobile>();

					for (int i = 0; i < pos.size(); i++) {
						DatiCalcoloImuImmobile datiCalcoloImmobileRiepilogo = new DatiCalcoloImuImmobile();
						Posizioni posizioni = pos.get(i);
						String indirizzo = PortletUtils.getIndirizzoFromPosizione(posizioni);
						datiCalcoloImmobileRiepilogo.setIndirizzo(indirizzo);
						datiCalcoloImmobileRiepilogo.setFoglio(String.valueOf(posizioni.getFoglio()));
						datiCalcoloImmobileRiepilogo.setNum(String.valueOf(posizioni.getParticella()));
						datiCalcoloImmobileRiepilogo.setQuota(String.valueOf(posizioni.getPercentualePossesso()));
						Codifica categoriaImmobile = posizioni.getCategoriaImmobile();
						if (categoriaImmobile != null) {
							datiCalcoloImmobileRiepilogo.setCategoria(categoriaImmobile.getCodice());
						}
						datiCalcoloImmobileRiepilogo.setSezione(posizioni.getSezione());
						datiCalcoloImmobileRiepilogo.setValoreim(String.valueOf(posizioni.getValoreImmobile()));
						datiCalcoloImmobileRiepilogo.setValoreimtable(String.valueOf(posizioni.getValoreImmobile()));
						datiCalcoloImmobileRiepilogo.setAliquota(Double.toString(posizioni.getAliquota()));

						String mesiPoss = String.valueOf(posizioni.getMesiPossesso());
						if (mesiPoss != null && mesiPoss.compareTo("") != 0) {
							datiCalcoloImmobileRiepilogo.setQuotaMesi(String.valueOf(posizioni.getMesiPossesso()));
						}
						else {
							datiCalcoloImmobileRiepilogo.setQuotaMesi("12");
						}

						datiCalcoloImmobileRiepilogo.setDetcasa(String.valueOf(posizioni.getImportoDetrazioneAbPrincipale()));
						datiCalcoloImmobileRiepilogo.setAnnoDiRiferimento(datiCalImu.getAnno());
						datiCalcoloImmobileRiepilogo.setCalcoloEffettuato(false);

						listaRiepilogoDati.add(datiCalcoloImmobileRiepilogo);
					}

					datiCalImu.setDatiRiepilogo(listaRiepilogoDati);
					datiCalImu.setNumeroImmobiliDaVisura(listaRiepilogoDati.size());
					setDatiCalcoloImu(model, datiCalImu, request);
					model.addAttribute("situazione", situazione);
					model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				}
			}

			Map<String, Object> param = new HashMap<String, Object>();
			model.addAttribute("param", param);
			model.addAttribute("datiCalImu", datiCalImu);
		}
		catch (Exception e) {
			log.error("getCalcoloImuForm :: " + e.getMessage(), e);
		}
		response.setRenderParameter("action", "renderCalcoloImuForm");
	}

	@ModelAttribute("listaCategorieImmobili")
	public List<CategoriaImmobile> getCategorieImmobili(PortletRequest request) {
		UserPreferences userPreferences = helper.getUserPreferences(request);
		List<CategoriaImmobile> categorieImmobiliByComuneISAAndAnnoAndServizio = categoriaImmobileService.getCategorieImmobiliByComuneISAAndAnnoAndServizio(userPreferences.getIdComuneIsa(),
				new Integer(DateUtils.getAnnoCorrente()), PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);

		return categorieImmobiliByComuneISAAndAnnoAndServizio;
	}

	/**
	 * Setta l'aliquota in base alla tipologia dell'immobile
	 *
	 * @param datiCalImu
	 * @param idDivImmobile
	 * @param model
	 * @param response
	 * @param session
	 * @param request
	 * @throws Exception
	 */
	@ActionMapping(params = "action=changeSelectCategoriaImmobile")
	public void changeSelectCategoriaImmobile(@ModelAttribute("datiCalImu") DatiCalcoloImu datiCalImu, Model model, ActionResponse response, PortletSession session, ActionRequest request)
			throws Exception {
		log.debug("changeSelectCategoriaImmobile");

		Method metodo;
		String idDivImmobile = datiCalImu.getSelectCambiata();
		metodo = datiCalImu.getClass().getDeclaredMethod("getCategoria" + idDivImmobile);
		String idCategoriaImmobile = (String) metodo.invoke(datiCalImu);
		CategoriaImmobile categoriaImmobile = categoriaImmobileService.getCategoriaImmobileById(Long.parseLong(idCategoriaImmobile));
		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);
		Tributo tributo = servizio.getTributo();

		CategoriaImmobileTributo catImmTributo = null;
		for (CategoriaImmobileTributo tmp : categoriaImmobile.getCategorieImmobiliTributi()) {
			if (tmp.getTributo().getId().equals(tributo.getId())) {
				catImmTributo = tmp;
			}

		}
		if (catImmTributo != null) {
			Double aliquota = catImmTributo.getAliquota();
			// set aliquota
			metodo = datiCalImu.getClass().getMethod("setAliquota" + idDivImmobile, String.class);
			metodo.invoke(datiCalImu, String.valueOf(aliquota));

			metodo = datiCalImu.getClass().getMethod("setCategoriaImmobileTributo" + idDivImmobile, String.class);
			metodo.invoke(datiCalImu, String.valueOf(aliquota));

			model.addAttribute("listaAgevolazioni", catImmTributo.getAgevolazioni());
			model.addAttribute("listaDetrazioni", catImmTributo.getDetrazioni());
			model.addAttribute("listaEsenzioni", catImmTributo.getEsenzioni());
		}
		setDatiCalcoloImu(model, datiCalImu, request);
		response.setRenderParameter("action", "renderCalcoloImuForm");
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
			Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);
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
	@ActionMapping(params = "ope=calcoloImu")
	public void calcoloImuParziale(@ModelAttribute("datiCalImu") @Valid DatiCalcoloImu datiCalImu, BindingResult result, @RequestParam(required = false, value = "cambio") String cambio, Model model,
			ActionResponse response, ActionRequest request) throws Exception {

		log.debug("Ho premuto Calcola Imu CF=" + datiCalImu.getCodiceFiscale());
		log.debug("cambio=" + cambio);

		Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		datiCalImu.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
		Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
		datiCalImu.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
		// Setto il liferay date time
		try {
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		}
		catch (Exception e) {
		}

		UserPreferences userPreferences = helper.getUserPreferences(request);

		if (cambio == null) {

			calcoloValidator.validate(datiCalImu, result);
			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
				model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, request.getLocale()));
				response.setRenderParameter("action", "renderCalcoloImuForm");
			}
			else {
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("comune", userPreferences.getNomeComune());

				datiCalImu.setDaPagar();
				datiCalImu.setTotale();
				datiCalImu.setRate();
				model.addAttribute("param", param);
				model.addAttribute("download", "si");
				response.setRenderParameter("action", "renderCalcoloImuForm");
			}
		}
		else if (cambio != null && getVisuraPosizioniTributarieActive(request)) {

			log.debug("anno = " + datiCalImu.getAnno());

			DichiarazioneTassaImmobiliRichiesta dichiarazioneTassaImmobiliRichiesta = new DichiarazioneTassaImmobiliRichiesta();
			if (datiCalImu.getpIva() != null) {
				dichiarazioneTassaImmobiliRichiesta.setPartitaIva(datiCalImu.getpIva());
			}
			else {
				dichiarazioneTassaImmobiliRichiesta.setCodiceFiscale(datiCalImu.getCodiceFiscale());
			}
			dichiarazioneTassaImmobiliRichiesta.setAnnoInizio(new Integer(datiCalImu.getAnno()));
			dichiarazioneTassaImmobiliRichiesta.setAnnoFine(new Integer(datiCalImu.getAnno()));

			DichiarazioneTassaImmobiliRisposta dichiarazioneTassaImmobiliRisposta = tributiService.richiediSituazioneTassaImmobili(dichiarazioneTassaImmobiliRichiesta, userPreferences);

			List<DatiTassaImmobili> situazioni = dichiarazioneTassaImmobiliRisposta.getSituazione();
			if (situazioni != null && !situazioni.isEmpty()) {
				DatiTassaImmobili situazione = situazioni.get(0);
				List<Posizioni> pos = situazione.getPosizioni();
				int i;
				for (i = 0; i < pos.size(); i++) {
					String indirizzoFromPosizione = PortletUtils.getIndirizzoFromPosizione(pos.get(i));
					if (indirizzoFromPosizione != null) {
						Method metodo = datiCalImu.getClass().getMethod("setIndirizzo" + (i + 1), String.class);
						metodo.invoke(datiCalImu, indirizzoFromPosizione);
					}

					Method metodo1 = datiCalImu.getClass().getMethod("setFoglio" + (i + 1), String.class);
					metodo1.invoke(datiCalImu, "" + pos.get(i).getFoglio());

					Method metodo2 = datiCalImu.getClass().getMethod("setNum" + (i + 1), String.class);
					metodo2.invoke(datiCalImu, "" + pos.get(i).getParticella());

					Method metodo3 = datiCalImu.getClass().getMethod("setQuota" + (i + 1), String.class);
					metodo3.invoke(datiCalImu, "" + pos.get(i).getPercentualePossesso());

					Method metodo4 = datiCalImu.getClass().getMethod("setCategoria" + (i + 1), String.class);
					metodo4.invoke(datiCalImu, pos.get(i).getCategoriaImmobile());

					Method metodo5 = datiCalImu.getClass().getMethod("setSezione" + (i + 1), String.class);
					metodo5.invoke(datiCalImu, pos.get(i).getSezione());

					Method metodo6 = datiCalImu.getClass().getMethod("setValoreim" + (i + 1), String.class);
					metodo6.invoke(datiCalImu, String.valueOf(pos.get(i).getValoreImmobile()));

					Method metodo7 = datiCalImu.getClass().getMethod("setAliquota" + (i + 1), String.class);
					metodo7.invoke(datiCalImu, pos.get(i).getAliquota());

					Method metodo8 = datiCalImu.getClass().getMethod("setMesi" + (i + 1), String.class);
					metodo8.invoke(datiCalImu, String.valueOf(pos.get(i).getMesiPossesso()));

					Method metodo9 = datiCalImu.getClass().getMethod("setDetcasa" + (i + 1), String.class);
					metodo9.invoke(datiCalImu, String.valueOf(pos.get(i).getImportoDetrazioneAbPrincipale()));

				}
				log.debug("i=" + i);
				for (int j = i; j < 5; j++) {
					Method metodo = datiCalImu.getClass().getMethod("setIndirizzo" + (j + 1), String.class);
					metodo.invoke(datiCalImu, "");

					Method metodo1 = datiCalImu.getClass().getMethod("setFoglio" + (j + 1), String.class);
					metodo1.invoke(datiCalImu, "");

					Method metodo2 = datiCalImu.getClass().getMethod("setNum" + (j + 1), String.class);
					metodo2.invoke(datiCalImu, "");

					Method metodo3 = datiCalImu.getClass().getMethod("setQuota" + (j + 1), String.class);
					metodo3.invoke(datiCalImu, "");

					Method metodo4 = datiCalImu.getClass().getMethod("setCategoria" + (j + 1), String.class);
					metodo4.invoke(datiCalImu, "");

					Method metodo5 = datiCalImu.getClass().getMethod("setSezione" + (j + 1), String.class);
					metodo5.invoke(datiCalImu, "");

					Method metodo6 = datiCalImu.getClass().getMethod("setValoreim" + (j + 1), String.class);
					metodo6.invoke(datiCalImu, "");

					Method metodo7 = datiCalImu.getClass().getMethod("setAliquota" + (j + 1), String.class);
					metodo7.invoke(datiCalImu, "");

					Method metodo8 = datiCalImu.getClass().getMethod("setMesi" + (j + 1), String.class);
					metodo8.invoke(datiCalImu, "");

					Method metodo9 = datiCalImu.getClass().getMethod("setDetcasa" + (j + 1), String.class);
					metodo9.invoke(datiCalImu, "");

				}
			}
			model.addAttribute("annoCorrente", DateUtils.getAnnoCorrente());
			setDatiCalcoloImu(model, datiCalImu, request);
			response.setRenderParameter("action", "renderCalcoloImuForm");
		}
	}

	@ActionMapping(params = "ope=calcoloImuTotale")
	public void calcoloImuTotale(@ModelAttribute("datiCalImu") DatiCalcoloImu datiCalImu, BindingResult result, Model model, ActionResponse response, ActionRequest request) throws Exception {

		List<DatiCalcoloImuImmobile> lista = new ArrayList<DatiCalcoloImuImmobile>();
		String indice = datiCalImu.getNumeroDivCalcoloParziale();
		int i = 0;

		// Se datiRiepilogo è NULL, significa che sto inserendo il primo immobile
		if (datiCalImu.getDatiRiepilogo() != null) {
			lista = datiCalImu.getDatiRiepilogo();
		}
		else {
			datiCalImu.setDatiRiepilogo(lista);
		}

		if (!indice.equalsIgnoreCase("reset")) {

			DatiCalcoloImuImmobile immobile = new DatiCalcoloImuImmobile();

			// Se l'indice è "new", significa che devo calcolare l imu di un immobile che è stato
			// appena inserito
			// se l'indice è un valore numerico, devo calcolare l imu di un immobile precedentemente
			// registrato

			if (indice.equalsIgnoreCase("new")) {
				immobile = this.estraiNuovoImmobile(datiCalImu);
				// se quello che sto inserendo è il primo immobile(lista == null) setto l'indice a
				// 0, altrimenti l'indice deve essere uguale alla dimensione della lista
				if (lista != null) {
					i = lista.size();
				}
			}
			else {
				i = Integer.valueOf(indice);
				immobile = lista.get(i);
			}
			immobile.setIndex(indice);

			Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
			datiCalImu.setDataNascita(DateUtils.getDataGGMMAAAA(dataNascita));
			Date dataNascitaRapp = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaRappDay", "dataNascitaRappMonth", "dataNascitaRappYear");
			datiCalImu.setDataNascitaRapp(DateUtils.getDataGGMMAAAA(dataNascitaRapp));
			// Setto il liferay date time
			try {
				PortletUtils.setDateIntoLiferayInputDate(request, dataNascita, "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
				PortletUtils.setDateIntoLiferayInputDate(request, dataNascitaRapp, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
			}
			catch (Exception e) {
			}

			calcoloValidator.validate(datiCalImu, result);

			if (result.hasErrors()) {
				for (ObjectError error : result.getAllErrors()) {
					log.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
				}
				// Se la validazione è riferita al form di inserimento di un nuovo immobile, setto
				// il flag a TRUE, in questo modo mi visualizzerà il form al caricamento della
				// pagina
				if (indice.equalsIgnoreCase("new")) {
					datiCalImu.setNuovoImmobile(true);
				}
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "renderCalcoloImuForm");
			}
			else {

				try {

					BigDecimal rendita = new BigDecimal(immobile.getValoreim());

					/* se il campo valore rendita è stato inserito, procedo a calcolare */
					if (rendita.compareTo(new BigDecimal(0)) != 0) {

						BigDecimal quotaPercPossesso = new BigDecimal(immobile.getQuota());

						/* dato per il calcolo */
						BigDecimal quotaPossesso = quotaPercPossesso.divide(new BigDecimal(100), MathContext.DECIMAL128);

						BigDecimal quotaMesiIntero = new BigDecimal(immobile.getQuotaMesi());

						/* dato per il calcolo */
						BigDecimal quotaMesiDiv12 = quotaMesiIntero.divide(new BigDecimal(12), MathContext.DECIMAL128);
						BigDecimal aliquota = new BigDecimal(immobile.getAliquota());
						aliquota = aliquota.divide(new BigDecimal(1000), MathContext.DECIMAL128);

						String idAgevolazione = immobile.getAgevolazione();

						String idDetrazione = immobile.getDetcasa();

						String idEsenzione = immobile.getEsenzione();

						String categoriaImmobileId = immobile.getCategoria();

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
						Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);
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
							BigDecimal imponibilePertC2 = renditaPertC2.multiply(rivalutazione).multiply(coefficienteMoltiplicazione);
							// rendita * rivalutazione * coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC2 = imponibilePertC2.multiply(quotaPossessoPertC2).multiply(quotaMesiPertC2Div12).multiply(aliquota).multiply(valoreAgevolazionePertC2);

						}

						if (renditaPertC6.compareTo(new BigDecimal(0)) != 0) {
							BigDecimal imponibilePertC6 = renditaPertC6.multiply(rivalutazione).multiply(coefficienteMoltiplicazione);
							// rendita * rivalutazione * coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC6 = imponibilePertC6.multiply(quotaPossessoPertC6).multiply(quotaMesiPertC6Div12).multiply(aliquota).multiply(valoreAgevolazionePertC6);

						}

						if (renditaPertC7.compareTo(new BigDecimal(0)) != 0) {
							BigDecimal imponibilePertC7 = renditaPertC7.multiply(rivalutazione).multiply(coefficienteMoltiplicazione);
							// rendita * rivalutazione * coefficienteMoltiplicazione;
							/*
							 * imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
							 * valoreAgevolazione;
							 */
							impostaPertC7 = imponibilePertC7.multiply(quotaPossessoPertC7).multiply(quotaMesiPertC7Div12).multiply(aliquota).multiply(valoreAgevolazionePertC7);

						}

						impostaTotPertinenze = impostaPertC2.add(impostaPertC6).add(impostaPertC7);
						/*-------------------------------------------------------*/

						/* CALCOLO RELATIVO ALL'IMMOBILE */

						BigDecimal imponibile = rendita.multiply(rivalutazione).multiply(coefficienteMoltiplicazione);
						// rendita * rivalutazione * coefficienteMoltiplicazione;

						BigDecimal imposta = imponibile.multiply(quotaPossesso).multiply(quotaMesiDiv12).multiply(aliquota).multiply(valoreAgevolazione);
						// imponibile * quotaPossesso * quotaMesiDiv12 * aliquota *
						// valoreAgevolazione;

						BigDecimal importoParziale; // (tot)

						imposta = imposta.add(impostaTotPertinenze);
						BigDecimal impostaRounded = imposta.setScale(2, BigDecimal.ROUND_HALF_UP);
						immobile.setImpostaImu(impostaRounded.toString());
						BigDecimal totaleDetrazionePerRiepilogo = new BigDecimal(0);

						if (esenzioneVal == true) {
							/*
							 * se è presente l'esenzione, vince su tutto, quindi
							 * l'importo(ImportoParziale) da pagare è zero. L'imposta viene comunque
							 * calcolata
							 */
							BigDecimal sottraendo = imposta;
							importoParziale = imposta.subtract(sottraendo);
							immobile.setImportoDetrazione(impostaRounded.toString());
							totaleDetrazionePerRiepilogo = impostaRounded;

						}
						else {
							/*
							 * se non c'è esenzione, calcolo l'importoTotaleParziale con le
							 * detrazioni, se presenti. nella formula è l'importo Imu.
							 */

							importoParziale = imposta.subtract(importoDetrazione);
							/* se minore di 0 (es. 30.5€ - 100€ = -69.5 => 0 */
							if (importoParziale.compareTo(new BigDecimal(0)) < 0) {
								importoParziale = new BigDecimal(0);
							}

							BigDecimal importoRounded = importoDetrazione.setScale(2, BigDecimal.ROUND_HALF_UP);
							immobile.setImportoDetrazione(importoRounded.toString());
							totaleDetrazionePerRiepilogo = importoRounded;

						}

						// IMPORTO IMU da pagare (iposta-detrazioni)
						BigDecimal importoParzialeRounded = importoParziale.setScale(2, BigDecimal.ROUND_HALF_UP);
						immobile.setDapagar(importoParzialeRounded.toString());

						/* se è > ho aggiunto oltre la visura un immobile all'elenco */
						// if (Integer.valueOf(datiCalImu.getNumeroDivCalcoloParziale()) >
						// datiCalImu.getDatiRiepilogo().size()) {
						// datiCalImu.getDatiRiepilogo().add(new DatiCalcoloImuImmobile());
						// }

						// IN CASO DI CALCOLO DI IMU PER UN IMMOBILE APPENA INSERITO, AGGIUNGO
						// QUEST'ULTIMO NELLA TABELLA
						if (indice.equalsIgnoreCase("new")) {
							lista.add(i, immobile);
						}

						/* aggiorno campi visura in lista riepilogo */
						String indirizzo = immobile.getIndirizzo();
						lista.get(i).setIndirizzo(indirizzo);

						String foglio = immobile.getFoglio();
						lista.get(i).setFoglio(foglio);

						String num = immobile.getNum();
						lista.get(i).setNum(num);

						String sezione = immobile.getSezione();
						lista.get(i).setSezione(sezione);

						BigDecimal renditaTotale = rendita.add(renditaPertC2).add(renditaPertC6).add(renditaPertC7);
						lista.get(i).setValoreimtable(renditaTotale.toString());
						lista.get(i).setTotaleImmobile(importoParzialeRounded.toString());
						lista.get(i).setTotaleImmobileNonArrotondato(importoParziale.toString());
						lista.get(i).setDetrazione(totaleDetrazionePerRiepilogo.toString());
						if (catImmTributo.getCodiceTributo() != null) {
							lista.get(i).setCodTributo(catImmTributo.getCodiceTributo().toString());
						}
						lista.get(i).setCalcoloEffettuato(true);
						datiCalImu.setDatiRiepilogo(lista);

						/* dati prova */

						/* calcolo del totale imu da pagare */
						BigDecimal totaleDaPagareNonArrotondato = new BigDecimal(0);
						BigDecimal totaleDaPagareArrotondato = new BigDecimal(0);
						for (int k = 0; k < datiCalImu.getDatiRiepilogo().size(); k++) {
							BigDecimal valoreCorr = new BigDecimal(datiCalImu.getDatiRiepilogo().get(k).getTotaleImmobileNonArrotondato());
							totaleDaPagareNonArrotondato = totaleDaPagareNonArrotondato.add(valoreCorr);
						}
						totaleDaPagareArrotondato = totaleDaPagareNonArrotondato.setScale(2, BigDecimal.ROUND_HALF_UP);

						datiCalImu.setTotaleNonArrotondato(totaleDaPagareNonArrotondato.toString());
						datiCalImu.setTotale(totaleDaPagareArrotondato.toString());

						// Quando caricherà la pagina, non devo inserire un nuovo immobile,
						// quindi setto il flag a FALSE
						datiCalImu.setNuovoImmobile(false);

						/*
						 * Setto a NULL tutti i campi utilizzati per l'inserimento di un nuovo
						 * immobile
						 */
						datiCalImu.setIndirizzo(null);
						datiCalImu.setFoglio(null);
						datiCalImu.setNum(null);
						datiCalImu.setSezione(null);
						datiCalImu.setQuota(null);
						datiCalImu.setQuotaMesi(null);
						datiCalImu.setDetcasa(null);
						datiCalImu.setValoreim(null);
						datiCalImu.setAliquota(null);

						datiCalImu.setPertAgevolazioneC2(null);
						datiCalImu.setPertAgevolazioneC6(null);
						datiCalImu.setPertAgevolazioneC7(null);
						datiCalImu.setPertPossessoMesiC2(null);
						datiCalImu.setPertPossessoMesiC6(null);
						datiCalImu.setPertPossessoMesiC7(null);
						datiCalImu.setPertPossessoPercC2(null);
						datiCalImu.setPertPossessoPercC6(null);
						datiCalImu.setPertPossessoPercC7(null);
						datiCalImu.setPertRenditaCatC2(null);
						datiCalImu.setPertRenditaCatC6(null);
						datiCalImu.setPertRenditaCatC7(null);

						model.addAttribute("listaEsenzioni", null);
						model.addAttribute("listaDetrazioni", null);
						model.addAttribute("listaAgevolazioni", null);

					}
				}
				catch (SecurityException e) {
					log.error("calcoloImuTotale :: " + e.getMessage(), e);
				}
				catch (IllegalArgumentException e) {
					log.error("calcoloImuTotale :: " + e.getMessage(), e);
				}
			}

		}
		else {
			/*
			 * Setto a NULL tutti i campi utilizzati per l'inserimento di un nuovo immobile
			 */
			datiCalImu.setIndirizzo(null);
			datiCalImu.setFoglio(null);
			datiCalImu.setNum(null);
			datiCalImu.setSezione(null);
			datiCalImu.setQuota(null);
			datiCalImu.setQuotaMesi(null);
			datiCalImu.setDetcasa(null);
			datiCalImu.setValoreim(null);
			datiCalImu.setAliquota(null);

			datiCalImu.setPertAgevolazioneC2(null);
			datiCalImu.setPertAgevolazioneC6(null);
			datiCalImu.setPertAgevolazioneC7(null);
			datiCalImu.setPertPossessoMesiC2(null);
			datiCalImu.setPertPossessoMesiC6(null);
			datiCalImu.setPertPossessoMesiC7(null);
			datiCalImu.setPertPossessoPercC2(null);
			datiCalImu.setPertPossessoPercC6(null);
			datiCalImu.setPertPossessoPercC7(null);
			datiCalImu.setPertRenditaCatC2(null);
			datiCalImu.setPertRenditaCatC6(null);
			datiCalImu.setPertRenditaCatC7(null);

			model.addAttribute("listaEsenzioni", null);
			model.addAttribute("listaDetrazioni", null);
			model.addAttribute("listaAgevolazioni", null);

			model.addAttribute("formError", null);
		}
		setDatiCalcoloImu(model, datiCalImu, request);
		response.setRenderParameter("action", "renderCalcoloImuForm");
	}

	@ResourceMapping("calcoloImuRiepilogo")
	public void calcoloImuRiepilogo(@ModelAttribute("datiCalImu") DatiCalcoloImu datiCalImu, Model model, ResourceResponse response, PortletSession session, ResourceRequest request) throws Exception {
		log.debug("calcoloImuRiepilogo ");

	}

	/**
	 * Metodo che genera la dichiarazione IMU (F24) e ne consente il download.
	 *
	 * @param datiTari
	 * @param param
	 * @param model
	 * @param response
	 * @param session
	 * @throws Exception
	 */
	@ResourceMapping("dichiarazioneReport")
	public void serveCertificato(@ModelAttribute("datiCalImu") DatiCalcoloImu datiCalImu, @ModelAttribute("param") Map<String, Object> param, Model model, ResourceResponse response,
			PortletSession session, ResourceRequest request) throws Exception {
		log.debug("dichiarazioneReport ");

		ProfiloUtenteCittadino profiloUtente = helper.getProfiloUtente(session);

		Long idComuneIsa = helper.getUserPreferences(request).getIdComuneIsa();
		ComuneISA comuneByPk = comuneISAService.getComuneByPk(idComuneIsa);
		String codiceCatastale = comuneByPk.getComune().getCodiceCatastale();

		// Query the service layer for some objects
		List<DatiF24> beans = new ArrayList<DatiF24>();

		DatiF24 datiF24 = new DatiF24();

		List<DatiCalcoloImuImmobile> listaImmobili = datiCalImu.getDatiRiepilogo();
		/* inserisco solo gli immobili di cui è stato effettuato il calcolo */
		List<MotivoPagamentoF24> motiviPagamento = new ArrayList<MotivoPagamentoF24>();
		for (DatiCalcoloImuRiepilogo datiCalcoloImuRiepilogo : listaImmobili) {
			if (datiCalcoloImuRiepilogo.isCalcoloEffettuato()) {
				MotivoPagamentoF24 motivoPagamentoF24 = new MotivoPagamentoF24();
				motivoPagamentoF24.setAcconto(false);
				motivoPagamentoF24.setAnnoRiferimento(datiCalcoloImuRiepilogo.getAnnoDiRiferimento());
				motivoPagamentoF24.setCodiceEnte(codiceCatastale);
				motivoPagamentoF24.setCodiceTributo(datiCalcoloImuRiepilogo.getCodTributo());
				motivoPagamentoF24.setDetrazione(datiCalcoloImuRiepilogo.getDetrazione());
				motivoPagamentoF24.setImmobiliVariati(false);
				motivoPagamentoF24.setImportiACreditoCompensati(datiCalcoloImuRiepilogo.getImportiACredito());
				// TODO cambiare con imposta imu
				motivoPagamentoF24.setImportiADebitoVersati(datiCalcoloImuRiepilogo.getTotaleImmobile());
				motivoPagamentoF24.setNumeroImmobili(datiCalcoloImuRiepilogo.getnImmobili());
				motivoPagamentoF24.setRateazioneMeseRiferimento(datiCalcoloImuRiepilogo.getRateazione());
				motivoPagamentoF24.setRavvedimento(false);
				motivoPagamentoF24.setSaldo(true);
				motivoPagamentoF24.setSezione(datiCalcoloImuRiepilogo.getSezione());
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

		datiF24.setMotiviPagamento(motiviPagamento);
		datiF24.setAgenzia("");
		datiF24.setAgenziaProv("");
		datiF24.setCodAtto("");
		datiF24.setCodFiscale(datiCalImu.getCodiceFiscale());
		datiF24.setCodFiscaleAltro("");
		datiF24.setCodIdentificativo("");
		datiF24.setCodUfficio("");
		datiF24.setCognome(datiCalImu.getCognome());
		datiF24.setComuneDiNascita(datiCalImu.getComuneNascita());
		datiF24.setDataDiNascita(datiCalImu.getDataNascita());
		datiF24.setDelegaIrrevocabileA(null);
		datiF24.setIban(datiCalImu.getIban());
		datiF24.setNome(datiCalImu.getNome());
		datiF24.setProvDiNascita(datiCalImu.getProvinciaNascita());
		datiF24.setSesso(datiCalImu.getSesso());
		datiF24.setTotale(datiCalImu.getTotale());

		beans.add(datiF24);

		param.put("subreportParameters1", motiviPagamento);

		Map<String, String> subreportJrxmlMap = new HashMap<String, String>();
		subreportJrxmlMap.put("subreportParameter1", SUB_REPORT_PATH_1);

		Servizio servizio = servizioService.getServizioByCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);

		byte[] reportBytes = reportService.jrxmlToPdf(param, beans, helper.getUserPreferences(request).getIdComuneIsa(), servizio.getCodiceServizio(), REPORT_PATH, subreportJrxmlMap);

		String md5 = CheckSumGenerator.generateCheckSum(reportBytes);

		// memorizzo la richiesta
		Fascicolo fascicolo = new Fascicolo();
		fascicolo.setIdProfiloUtente(profiloUtente);
		fascicolo.setServizio(messageSource.getMessage("label.fascicoloUtente.servizio1", null, request.getLocale()));
		fascicolo.setUserPreferences(helper.getUserPreferences(request));
		fascicolo.setCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU);
		fascicolo.setRicercabileDaComune(false);
		fascicolo.setNumeroProtocollo(null);
		fascicolo.setChecksum(md5);
		fascicoloService.saveNuovaRichiesta(fascicolo);

		
		final String uuidOperazione=(String)session.getAttribute("UUID");
		
		
		DwhTempiMediDTO tempiMediDto = new DwhTempiMediDTO();
		//MS
		tempiMediDto = DwhTempiMediUtil.get(dwhService)
		.go_EndTime(uuidOperazione)
		.setCodServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
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
			.addServizioCodiceServizio(PortletConstants.CODICE_SERVIZIO_CALCOLO_IMU)
			.setOrigin(Origin.getIp(request))
			.addCallGeo()
			.build();
		
		
		
		String reportFileName = String.format(REPORT_NAME);
		helper.sendStreamAsAttachment(reportBytes, response, reportFileName, ContentMimeTypes.PDF);
	}

	@RenderMapping(params = "action=renderCalcoloImuForm")
	public String renderCalcoloImuForm(Model model) throws Exception {
		return toLocalViewPath("calcoloImu");
	}

	/**
	 * @param model
	 * @param datiCalcoloImu
	 * @param request
	 * @throws Exception
	 */
	private void setDatiCalcoloImu(Model model, DatiCalcoloImu datiCalcoloImu, ActionRequest request) throws Exception {
		// Setto il liferay date time
		PortletUtils.setDateIntoLiferayInputDate(request, datiCalcoloImu.getDataNascitaForm(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
		PortletUtils.setDateIntoLiferayInputDate(request, null, "inputDataNascitaRappDay", "inputDataNascitaRappMonth", "inputDataNascitaRappYear");
		model.addAttribute("datiCalImu", datiCalcoloImu);

		// TEST
		String json = new Gson().toJson(datiCalcoloImu.getDatiRiepilogo());
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
	 * "/calcoloimu/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return JSP_PATH + viewName;
	}

	// Metodo utile a convertire in dati del form relativo ad un nuovo immobile in
	// un oggetto di tipo DatiCalcoloTasiImmobile
	private DatiCalcoloImuImmobile estraiNuovoImmobile(DatiCalcoloImu datiCalImu) {

		DatiCalcoloImuImmobile immobile = new DatiCalcoloImuImmobile();
		immobile.setIndirizzo(datiCalImu.getIndirizzo());
		immobile.setFoglio(datiCalImu.getFoglio());
		immobile.setNum(datiCalImu.getNum());
		immobile.setSezione(datiCalImu.getSezione());
		immobile.setCategoria(datiCalImu.getCategoria());
		immobile.setValoreim(datiCalImu.getValoreim());
		immobile.setQuota(datiCalImu.getQuota());
		immobile.setQuotaMesi(datiCalImu.getQuotaMesi());
		immobile.setAliquota(datiCalImu.getAliquota());
		immobile.setAgevolazione(datiCalImu.getAgevolazione());
		immobile.setDetcasa(datiCalImu.getDetcasa());
		immobile.setEsenzione(datiCalImu.getEsenzione());

		immobile.setPertAgevolazioneC2(datiCalImu.getPertAgevolazioneC2());
		immobile.setPertPossessoMesiC2(datiCalImu.getPertPossessoMesiC2());
		immobile.setPertPossessoPercC2(datiCalImu.getPertPossessoPercC2());
		immobile.setPertRenditaCatC2(datiCalImu.getPertRenditaCatC2());

		immobile.setPertAgevolazioneC6(datiCalImu.getPertAgevolazioneC6());
		immobile.setPertPossessoMesiC6(datiCalImu.getPertPossessoMesiC6());
		immobile.setPertPossessoPercC6(datiCalImu.getPertPossessoPercC6());
		immobile.setPertRenditaCatC6(datiCalImu.getPertRenditaCatC6());

		immobile.setPertAgevolazioneC7(datiCalImu.getPertAgevolazioneC7());
		immobile.setPertPossessoMesiC7(datiCalImu.getPertPossessoMesiC7());
		immobile.setPertPossessoPercC7(datiCalImu.getPertPossessoPercC7());
		immobile.setPertRenditaCatC2(datiCalImu.getPertRenditaCatC7());

		return immobile;
	}

	@ActionMapping(params = "op=eliminaImmobile")
	public void elimina(@RequestParam("id") int id, @ModelAttribute("datiCalImu") DatiCalcoloImu datiCalImu, Model model, ActionResponse response, PortletSession session, ActionRequest request) {
		datiCalImu.getDatiRiepilogo().remove(id);

		String totale = getTotaleAggiornato(datiCalImu, true);
		String totaleNonArrotondato = getTotaleAggiornato(datiCalImu, false);
		datiCalImu.setTotale(totale);
		datiCalImu.setTotaleNonArrotondato(totaleNonArrotondato);

		response.setRenderParameter("action", "renderCalcoloImuForm");
		try {
			setDatiCalcoloImu(model, datiCalImu, request);
		}
		catch (Exception e) {
			log.error("elimina :: " + e.getMessage(), e);
		}
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
	 * Dato l'oggetto di tipo DatiCalcoloImu, estraggo la lista aggiornata di immobili e ricalcolo
	 * il calcolo totale (lo restituisce arrotondato se arrotondato=true). Questo metodo viene
	 * richiamato dalla funzione "elimina" per riaggiornare il totale ogniqualvolta si elimina un
	 * immobile.
	 *
	 * @param DatiCalcoloImu
	 * @param arrotondato
	 */
	private String getTotaleAggiornato(DatiCalcoloImu dati, boolean arrotondato) {
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
