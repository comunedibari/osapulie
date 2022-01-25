/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSecurityException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.UnavailableException;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.google.gson.Gson;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Provincia;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.ComuneEsteroService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneISAServizioService;
import it.osapulie.service.ProvinciaService;
import it.osapulie.service.ServiceUnreachableException;
import it.osapulie.service.ServizioService;
import it.osapulie.servizi.web.ws.types.Civico;
import it.osapulie.servizi.web.ws.types.Indirizzo;
import it.osapulie.servizi.web.ws.types.StradarioRichiesta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta;
import it.osapulie.servizi.web.ws.types.StradarioRisposta.Errore;
import it.osapulie.servizi.web.ws.types.Via;
import it.osapulie.shared.constant.PortalConstants;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.SPID;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.UnauthorizedException;
import it.osapulie.web.portlet.model.stradario.Stradario;
import it.osapulie.web.portlet.util.CommonServiceUtil;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.impl.LiferayPortletHelperImpl;

/**
 * Base controller per settare in pagina la URL per la valutazione di un servizio. La URL viene
 * settata se la funzionalità è attiva (<code>evaluationServiceEnable == true</code>).
 *
 * @author Gianluca Pindinelli
 *
 */
public class BaseController {

	private final Logger log = LoggerFactory.getLogger(BaseController.class.getName());

	@Value("#{applicationProperties['evaluation.service.enable']}")
	private Boolean evaluationServiceEnable;

	@Value("#{applicationProperties['evaluation.service.parameter.name']}")
	private String evaluationServiceUrl;

	@Value("#{applicationProperties['evaluation.service.page.parameters']}")
	private String evaluationServicePageParameters;


	@Value("#{applicationProperties['evaluation.service.codce.certificati.list']}")
	private String evaluationCodceCertificati;

	@Value("#{applicationProperties['evaluation.service.codci.fiscali.certificati.v2.list']}")
	private String evaluationCodiciFiscaliV2;


	@Inject
	private LiferayPortletHelperImpl helper;

	@Inject
	private CommonServiceUtil commonServiceUtil;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ComuneISAServizioService comuneISAServizioService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ProvinciaService provinciaService;

	@Inject
	private ComuneEsteroService comuneEsteroService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Value("#{applicationProperties['send.reports.enable']}")
	protected Boolean sendReportsEnable;

	@Value("#{applicationProperties['redirectPortlet.return.url.parameter']}")
	private String redirectPortletReturnUrlParameter;

	@ModelAttribute("evaluationServiceEnable")
	public Boolean getEvaluationServiceEnable() {
		return evaluationServiceEnable;
	}

	@ModelAttribute("sendReportsEnable")
	public Boolean getSendReportsEnable() {
		return sendReportsEnable;
	}

	/**
	 * {@link ResourceMapping} per il caricamento della URL del sistema di gradimento dei servizi.
	 *
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@ResourceMapping("serviceEvaluation")
	public void serviceEvaluation(ResourceRequest request, ResourceResponse response) throws Exception {

		log.debug("serviceEvaluation :: entering method");

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);

		// Sezione valutazione servizio
		if (evaluationServiceEnable && themeDisplay.isSignedIn()) {

			// Caricamento URL
			String currentUrl = themeDisplay.getURLCurrent();

			if (currentUrl.contains("?")) {
				currentUrl = currentUrl.substring(0, currentUrl.indexOf("?"));
			}

			Servizio servizioByUri = servizioService.getServizioByUri(currentUrl);
			UserPreferences userPreferences = helper.getUserPreferences(request);
			String codiceServizio = userPreferences.getCodiceIstatComune() + "-" + servizioByUri.getCodiceServizio();
			String evaluationServiceUrl = commonServiceUtil.getEvaluationServiceUrl(codiceServizio);
			request.setAttribute(this.evaluationServiceUrl, evaluationServiceUrl);

			response.setContentType("text/plain");
			response.getWriter().println(evaluationServiceUrl);
		}
	}

	/**
	 * {@link ResourceMapping} per la ricerca di un indirizzo nello stradario comunale.
	 *
	 * @param indirizzo
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping("searchStradario")
	public void searchStradario(@RequestParam(required = false) String indirizzo, ResourceRequest request, ResourceResponse response) throws IOException {

		Stradario stradario = new Stradario();

		UserPreferences userPreferences = helper.getUserPreferences(request);

		StradarioRichiesta stradarioRichiesta = new StradarioRichiesta();
		if (indirizzo != null) {
			it.osapulie.servizi.web.ws.types.StradarioRichiesta.Indirizzo indirizzoRichiesta = new it.osapulie.servizi.web.ws.types.StradarioRichiesta.Indirizzo();
			indirizzoRichiesta.setDenominazione(indirizzo);
			stradarioRichiesta.setIndirizzo(indirizzoRichiesta);
		}

		StradarioRisposta richiediStradario = commonServiceUtil.richiediStradario(stradarioRichiesta, userPreferences);

		Errore errore = richiediStradario.getErrore();
		if (errore != null) {
			it.osapulie.web.portlet.model.stradario.Errore erroreStradario = new it.osapulie.web.portlet.model.stradario.Errore();
			erroreStradario.setCodice(errore.getCodice());
			String errorMessage = null;
			if (errore.getCodice() == 2) {
				// Civico o via non trovata
				errorMessage = messageSource.getMessage("label.stradario.via.not.found", null, request.getLocale());
			}
			else {
				// Altro errore
				errorMessage = messageSource.getMessage("label.stradario.error", new String[] { String.valueOf(errore.getCodice()), errore.getDescrizione() }, request.getLocale());
			}
			log.error(errorMessage);
			erroreStradario.setMessaggio(errorMessage);
			stradario.setErrore(erroreStradario);
		}
		else {

			List<it.osapulie.servizi.web.ws.types.Indirizzo> indirizziStradario = richiediStradario.getIndirizzo();
			List<it.osapulie.web.portlet.model.stradario.Via> result = new ArrayList<it.osapulie.web.portlet.model.stradario.Via>();
			Map<it.osapulie.web.portlet.model.stradario.Via, List<it.osapulie.web.portlet.model.stradario.Civico>> resultMap = new HashMap<it.osapulie.web.portlet.model.stradario.Via, List<it.osapulie.web.portlet.model.stradario.Civico>>();
			if (indirizziStradario != null) {
				for (Indirizzo indirizzoStradario : indirizziStradario) {
					Via via = indirizzoStradario.getVia();
					Civico civico = indirizzoStradario.getCivico();

					if (via != null) {

						it.osapulie.web.portlet.model.stradario.Via viaModel = new it.osapulie.web.portlet.model.stradario.Via();
						viaModel.setIdentificativo(via.getIdentificativo());
						viaModel.setDenominazione(via.getDenominazione());

						if (civico != null) {
							it.osapulie.web.portlet.model.stradario.Civico civicoModel = new it.osapulie.web.portlet.model.stradario.Civico();
							if (civico.getLocalita() != null) {
								civicoModel.setLocalita(civico.getLocalita());
								viaModel.setLocalita(civico.getLocalita());
							}
							List<it.osapulie.web.portlet.model.stradario.Civico> civici = resultMap.get(viaModel);

							if (civici == null) {
								civici = new ArrayList<it.osapulie.web.portlet.model.stradario.Civico>();
							}
							civicoModel.setIdentificativo(civico.getIdentificativo());
							civicoModel.setNumero(civico.getNumero());
							civicoModel.setEsponente(civico.getEsponente());
							civicoModel.setCap(civico.getCap());
							civici.add(civicoModel);

							resultMap.put(viaModel, civici);
						}
					}
				}
			}

			for (Map.Entry<it.osapulie.web.portlet.model.stradario.Via, List<it.osapulie.web.portlet.model.stradario.Civico>> entry : resultMap.entrySet()) {
				it.osapulie.web.portlet.model.stradario.Via key = entry.getKey();
				key.setCivici(entry.getValue());
				result.add(key);
			}

			// Sort
			Collections.sort(result);
			for (it.osapulie.web.portlet.model.stradario.Via via : result) {
				if (via.getCivici() != null) {
					Collections.sort(via.getCivici());
				}
			}

			stradario.setVie(result);
		}

		Gson gson = new Gson();
		String json = gson.toJson(stradario);
		response.getWriter().write(json);

	}

	/**
	 * {@link ResourceMapping} per la ricerca (LIKE) dei comuni esteri presenti nel sistema.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping("searchListaComuniEsteri")
	public void searchListaComuniEsteri(ResourceRequest request, ResourceResponse response) throws IOException {

		HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
		String query = httpServletRequest.getParameter("q");
		List<it.osapulie.domain.json.Comune> comuniResult = new ArrayList<it.osapulie.domain.json.Comune>();
		if (query != null && !"".equalsIgnoreCase(query)) {
			List<ComuneEstero> result = comuneEsteroService.searchComuni(query);
			if (result != null) {
				for (ComuneEstero comune : result) {
					it.osapulie.domain.json.Comune comuneEstero = new it.osapulie.domain.json.Comune();
					comuneEstero.setCodice(comune.getCodice().toString());
					comuneEstero.setDenominazione(comune.getDenominazione());
					comuniResult.add(comuneEstero);
				}
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(comuniResult);
		response.getWriter().write(json);
	}

	/**
	 * {@link ResourceMapping} per la ricerca (LIKE) dei comuni presenti nel sistema.
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@ResourceMapping("searchListaComuni")
	public void searchListaComuni(@RequestParam String query, ResourceRequest request, ResourceResponse response) throws IOException {

		List<it.osapulie.domain.json.Comune> comuniResult = new ArrayList<it.osapulie.domain.json.Comune>();
		Provincia provinciaBySigla = provinciaService.getProvinciaBySigla(query);
		if (provinciaBySigla != null) {
			List<Comune> result = provinciaBySigla.getComuni();
			if (result != null) {
				for (Comune comune : result) {
					it.osapulie.domain.json.Comune comuneEstero = new it.osapulie.domain.json.Comune();
					comuneEstero.setCodice(comune.getCodiceIstatAN());
					comuneEstero.setDenominazione(comune.getDenominazione());
					comuniResult.add(comuneEstero);
				}
			}
			Gson gson = new Gson();
			String json = gson.toJson(comuniResult);
			response.getWriter().write(json);
		}
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ModelAndView unauthorizedException(Exception exception, PortletRequest portletRequest) {

		log.error("unauthorizedException :: " + exception.getMessage());

		// Genrazione link di logout/login per SPID
		String servizioUrl = null;
		try {
			if (helper.isAuthenticationChannelEnable(AuthenticationChannel.SPID)) {
				Servizio servizio = null;
				ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);
				Layout layout = themeDisplay.getLayout();
				if (layout.getFriendlyURL() != null && !layout.getFriendlyURL().equals("")) {
					String pageUrl = layout.getFriendlyURL();
					servizio = servizioService.getServizioByUriScheda(pageUrl);

					if (servizio != null) {
						Locale defaultLocale = LocaleUtil.getDefault();
						servizioUrl = servizio.getUri();
						String language = themeDisplay.getLocale().getLanguage();
						if (!defaultLocale.getLanguage().equals(language)) {
							servizioUrl = "/" + language + servizioUrl;
						}
					}
				}

				UserPreferences userPreferences = helper.getUserPreferences(portletRequest);
				ComuneISAServizio comuneISAServizio = comuneISAServizioService.getComuneISAServizio(userPreferences.getIdComuneIsa(), servizio.getId());
				if (comuneISAServizio != null && servizio != null) {
					ComuneISA comuneISA = comuneISAService.getComuneByPk(userPreferences.getIdComuneIsa());
					servizioUrl += "?" + PortletConstants.COMUNE_ISA_PARAMETER_NAME + "=" + comuneISA.getCodiceIstat();
					Integer livelloAutenticazione = comuneISAServizio.getLivelloAutenticazione();

					if (livelloAutenticazione != null) {
						String logoutUrl = "/c/portal/logout" + "?" + SPID.SERVICES_RETURN_URL_PARAMETER.getName() + "=";
						servizioUrl = logoutUrl + SPID.SERVICES_LEVEL_PATH_PATTERN.getName() + livelloAutenticazione.toString() + "?" + PortalConstants.REDIRECT_FILTER_PARAMETER_NAME + "="
								+ servizioUrl;
					}
				}
			}
		}
		catch (Exception e) {
			log.error("getServizioUrl :: " + e.getMessage(), e);
		}

		Map<Object, Object> model = new HashMap<Object, Object>();
		String unauthorizedException = messageSource.getMessage("exception.unauthorized.message." + exception.getMessage(), new String[] { servizioUrl }, portletRequest.getLocale());
		model.put("unauthorizedExceptionMessage", unauthorizedException);

		return new ModelAndView("unauthorized", (Map) model);
	}

	@ExceptionHandler(UnavailableException.class)
	public ModelAndView unavailableException(Exception exception) {
		log.error("unavailableException :: " + exception.getMessage());
		return new ModelAndView("unavailable");
	}

	@ExceptionHandler(PortletSecurityException.class)
	public ModelAndView portletSecurityException(Exception exception) {
		log.error("portletSecurityException :: " + exception.getMessage());
		return new ModelAndView("unauthorized");
	}

	@ExceptionHandler(ServiceUnreachableException.class)
	public ModelAndView serviceUnreachableException(Exception e) {
		log.error("serviceUnreachableException :: " + e.getMessage(), e);
		return new ModelAndView("unreachable");
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView genericException(Exception e) {
		log.error("genericException :: " + e.getMessage(), e);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("formError", "Unexpected error: " + e.getMessage());

		return new ModelAndView("defError", (Map) model);
	}


	public int stampVersion(String codceCertificato, String codiceFiscale) {
		if(this.evaluationCodceCertificati == null || this.evaluationCodceCertificati.length() == 0) return 0;
		if(this.evaluationCodceCertificati.toUpperCase().contains(codceCertificato.toUpperCase()) || this.evaluationCodceCertificati.toUpperCase().equalsIgnoreCase("ALL")) {
			if(this.evaluationCodiciFiscaliV2 == null || this.evaluationCodiciFiscaliV2.length() == 0 || this.evaluationCodiciFiscaliV2.toUpperCase().contains(codiceFiscale.toUpperCase()) || this.evaluationCodiciFiscaliV2.toUpperCase().contains("ALL")) return 1;
		}
		return 0;
	}

}
