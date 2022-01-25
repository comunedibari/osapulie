/*******************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.catalogoservizi.web.portlet.listaservizi.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import it.osapulie.catalogoservizi.web.utils.PortletConstants;
import it.osapulie.catalogoservizi.web.utils.ServizioComparator;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.Delega;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.domain.servizi.Tipologia;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServizioService;
import it.osapulie.service.TipologiaService;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione della lista dei servizi di front end e back end.
 *
 *
 * @author Gianluca Pindinelli
 * @author Antonio Abbinante
 */
@Controller("listaServiziPortletController")
@RequestMapping("view")
public class ListaServiziPortletController {

	private final Logger log = LoggerFactory.getLogger(ListaServiziPortletController.class);

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private TipologiaService tipologiaService;

	@Inject
	private DelegaService delegaService;

	@Inject
	private PortletHelper helper;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {
		try {
			HttpServletRequest httpReq = PortalUtil.getHttpServletRequest(request);
			httpReq = PortalUtil.getOriginalServletRequest(httpReq);
			String areaTematica = ParamUtil.getString(httpReq, PortletConstants.PARAMETRO_ID_AREA_TEMATICA_SERVIZIO);
			String tipologia = ParamUtil.getString(httpReq, PortletConstants.PARAMETRO_ID_TIPOLOGIA_SERVIZIO);
			String comune = ParamUtil.getString(httpReq, PortletConstants.PARAMETRO_ID_COMUNE);

			ComuneISA comuneSelezionato = null;
			Delega delega = null;
			Boolean isCittadino = null;
			Boolean isProfiloAzienda = null;
			Long idDelega = null;
			// Solo per area privata
			ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
			if (themeDisplay.getLayout().isPrivateLayout()) {

				UserPreferences userPreferences = helper.getUserPreferences(request);
				OSApulieUserDetails profiloUtente = profilazioneUtenteService.getById(String.valueOf(themeDisplay.getUserId()));
				model.addAttribute("profiloUtente", profiloUtente.getProfiloUtenteCittadino());

				// Caricamento comune selezionato
				if (userPreferences.getCodiceIstatComune() != null) {
					comuneSelezionato = comuneISAService.getComuneByCodiceIstat(userPreferences.getCodiceIstatComune());
					model.addAttribute("comuneSelezionato", comuneSelezionato);
				}

				// Controllo profilo Azienda
				if (userPreferences.getPartitaIvaServizio() != null && !userPreferences.getPartitaIvaServizio().equals("")) {
					isProfiloAzienda = true;
				}
				// Controllo delega
				else if (userPreferences.getIdDelega() != null && userPreferences.getIdDelega() != 0) {
					delega = delegaService.getDelegaByPk(userPreferences.getIdDelega());
					model.addAttribute("delega", delega);
					idDelega = delega.getId();
				}
				else {
					isCittadino = true;
				}
				model.addAttribute("isProfiloAzienda", isProfiloAzienda);
			}

			Map<Tipologia, List<Servizio>> elencoServizi = new HashMap<Tipologia, List<Servizio>>();
			Long idAreaTematicaLong = null;
			Long idTipologiaLong = null;
			if (areaTematica != null && !areaTematica.equalsIgnoreCase("")) {
				idAreaTematicaLong = Long.valueOf(areaTematica);
			}
			else if (tipologia != null && !tipologia.equalsIgnoreCase("")) {
				idTipologiaLong = Long.valueOf(tipologia);
			}
			else if (comune != null && !comune.equalsIgnoreCase("")) {
				comuneSelezionato = comuneISAService.getComuneByPk(Long.parseLong(comune));
			}

			elencoServizi = getServiziMap(request, idAreaTematicaLong, idTipologiaLong, comuneSelezionato, isCittadino, isProfiloAzienda, idDelega);

			model.addAttribute("mappaServizi", elencoServizi);
			model.addAttribute(PortletConstants.PARAMETRO_ID_COMUNE, comune);

		}
		catch (Exception e) {
			log.error("error in Render ListaServiziPortletController :: " + e.getMessage(), e);
		}

		return toLocalViewPath("home");
	}

	/**
	 * Restiuisce la mappa dei servizi (per tipologia) in base ai parametri in input.
	 *
	 * @param request
	 * @param idAreaTematica
	 * @param idTipologia
	 * @param comuneISA
	 * @param isCittadino
	 * @param isAzienda
	 * @param idDelega
	 * @return
	 * @throws Exception
	 */
	private Map<Tipologia, List<Servizio>> getServiziMap(PortletRequest request, Long idAreaTematica, Long idTipologia, ComuneISA comuneISA, Boolean isCittadino, Boolean isAzienda, Long idDelega)
			throws Exception {

		Map<Tipologia, List<Servizio>> serviziMap = new LinkedHashMap<Tipologia, List<Servizio>>();

		Long idComuneISA = null;
		if (comuneISA != null) {
			idComuneISA = comuneISA.getId();
		}

		List<Servizio> elencoServizi = servizioService.getServiziByParemeters(idComuneISA, idAreaTematica, idTipologia, idDelega, isCittadino, isAzienda, true);

		if (comuneISA != null) {
			setAutenticazioneServizi(elencoServizi, comuneISA);
		}
		if (idTipologia != null) {
			Tipologia tipologia = tipologiaService.getTipologiaById(idTipologia);
			serviziMap.put(tipologia, elencoServizi);
		}
		else {
			serviziMap = getMappaServiziPerTipologia(elencoServizi, request);
		}

		return serviziMap;

	}

	/**
	 * Metodo che prende in input una lista di servizi e restituisce una mappa che ha come chiave le
	 * tipologie e come valore le liste di servizi associate
	 *
	 * @param elencoServizi
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Map<Tipologia, List<Servizio>> getMappaServiziPerTipologia(List<Servizio> elencoServizi, PortletRequest request) throws Exception {

		Collections.sort(elencoServizi, new ServizioComparator());

		List<Servizio> serviziNoTipologia = new ArrayList<Servizio>();
		Map<Tipologia, List<Servizio>> serviziByTipologiaMap = new LinkedHashMap<Tipologia, List<Servizio>>();

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Locale defaultLocale = LocaleUtil.getDefault();

		for (Servizio servizio : elencoServizi) {
			if (servizio.isAttivo()) {
				List<Tipologia> tipologie = servizio.getTipologie();
				if (tipologie != null && tipologie.size() > 0) {
					for (Tipologia tipologia : tipologie) {
						if (serviziByTipologiaMap.containsKey(tipologia)) {
							List<Servizio> serviziListEsistente = serviziByTipologiaMap.get(tipologia);
							if (!serviziListEsistente.contains(servizio)) {
								serviziByTipologiaMap.get(tipologia).add(servizio);
							}
						}
						else {
							List<Servizio> serviziList = new ArrayList<Servizio>();
							serviziList.add(servizio);
							serviziByTipologiaMap.put(tipologia, serviziList);
						}
					}
				}
				else {
					serviziNoTipologia.add(servizio);
				}
			}

			// Aggiornamento uri servizio in base al locale
			String language = themeDisplay.getLocale().getLanguage();
			String servizioUrl = servizio.getUri();
			if (!defaultLocale.getLanguage().equals(language)) {
				servizioUrl = "/" + language + servizioUrl;
				servizio.setUri(servizioUrl);
			}
		}

		// Union servizi con areaTematica + servizi senza
		if (!serviziNoTipologia.isEmpty()) {
			Tipologia altriServizi = new Tipologia();
			altriServizi.setNome(messageSource.getMessage("label.altriServizi", null, request.getLocale()));
			serviziByTipologiaMap.put(altriServizi, serviziNoTipologia);
		}

		return serviziByTipologiaMap;
	}

	/**
	 * Sovrascrive la proprietà "autenticazioneForte" del {@link Servizio} in base a quella presente
	 * nella relazione {@link ComuneISAServizio} del Comune passato in input.
	 *
	 * @param elencoServizi
	 * @param comuneSelezionato
	 */
	private void setAutenticazioneServizi(List<Servizio> elencoServizi, ComuneISA comuneSelezionato) {

		if (elencoServizi != null) {
			for (Servizio servizio : elencoServizi) {
				List<ComuneISAServizio> comuni = servizio.getComuni();
				if (comuni != null) {
					for (ComuneISAServizio comuneISAServizio : comuni) {
						if (comuneISAServizio.getIdComuneISA() == comuneSelezionato.getId()) {
							servizio.setAutenticazioneForte(comuneISAServizio.isAutenticazioneForte());
							servizio.setLivelloAutenticazione(comuneISAServizio.getLivelloAutenticazione());
							break;
						}
					}
				}
			}
		}
	}

	@ModelAttribute("authenticationChannels")
	public AuthenticationChannel[] authenticationChannels() {
		return helper.getScenario3AuthenticationChannels();
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/profiloutente/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "listaservizi/" + viewName;
	}

}
