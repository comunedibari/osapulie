/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.listaservizi.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.servizi.AreaTematica;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServizioService;
import it.osapulie.servizi.web.ws.types.RichiestaServiziAttivi;
import it.osapulie.servizi.web.ws.types.ServiziAttivi;
import it.osapulie.servizicomune.service.ServiziAttiviService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione della lista dei servizi associati al comune di
 * appartenenza dell'utente.
 *
 *
 * @author Gianluca Pindinelli
 */
@Controller("listaServiziPortletController")
@RequestMapping("view")
public class ListaServiziPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(ListaServiziPortletController.class);

	@Inject
	private PortletHelper helper;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ServiziAttiviService serviziService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private DelegaService delegaService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "comuneSelezionato")
	public ComuneISA getComuneSelezionato(PortletRequest request) {

		log.debug("getComuneSelezionato :: entering method");

		UserPreferences userPreferences = helper.getUserPreferences(request);
		ComuneISA comuneSelezionato = null;
		if (userPreferences.getCodiceIstatComune() != null) {
			comuneSelezionato = comuneISAService.getComuneByCodiceIstat(userPreferences.getCodiceIstatComune());
		}

		return comuneSelezionato;
	}

	@ModelAttribute(value = "mappaServizi")
	public Map<AreaTematica, List<Servizio>> getMappaServizi(Model model, PortletRequest request) throws Exception {

		log.debug("homePage :: entering method");

		Map<AreaTematica, List<Servizio>> elencoServizi = getServiziAttiviMap(model, request);

		return elencoServizi;
	}

	/**
	 * @param request
	 * @return
	 */
	private Map<AreaTematica, List<Servizio>> getServiziAttiviMap(Model model, PortletRequest request) {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		RichiestaServiziAttivi richiesta = new RichiestaServiziAttivi();
		richiesta.setCodiceFiscale(osApulieUserDetails.getProfiloUtenteCittadino().getCodiceFiscale());

		if (osApulieUserDetails.getCertificato() == null) {
			richiesta.setTipoAutenticazione("Debole");
		}
		else {
			richiesta.setTipoAutenticazione("Forte");
		}

		List<Servizio> elencoServizi = null;

		Map<AreaTematica, List<Servizio>> serviziByAreaTematicaMap = null;

		try {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			// Controllo delega
			Map<Long, Servizio> serviziDeleganteMap = null;
			if (userPreferences.getIdDelega() != null && userPreferences.getIdDelega() != 0) {

				serviziDeleganteMap = new HashMap<Long, Servizio>();
				Delega delega = delegaService.getDelegaByPk(userPreferences.getIdDelega());

				// Setto il codice fiscale del delegante
				if (delega != null && delega.getDelegante() != null) {
					String cfDelegante = delega.getDelegante().getCodiceFiscale();
					richiesta.setCodiceFiscale(cfDelegante);

					// Caricamento servizi delegante
					List<Servizio> serviziDeleganteList = delega.getServizi();
					if (serviziDeleganteList != null) {
						for (Servizio servizio : serviziDeleganteList) {
							serviziDeleganteMap.put(servizio.getId(), servizio);
						}
					}
				}
				model.addAttribute("delega", delega);
			}

			// Caricamento tutti servizi dal comune
			ServiziAttivi servizi = serviziService.richiediServiziAttivi(richiesta, userPreferences);

			elencoServizi = new ArrayList<Servizio>();

			for (it.osapulie.servizi.web.ws.types.Servizio servizio : servizi.getServizio()) {
				Servizio srv = servizioService.getServizioByCodiceServizio(servizio.getCodiceServizio());
				if (servizio.isAttivo() && srv != null && srv.isAttivo()) {
					srv.setAutenticazioneForte(servizio.isAutenticazioneForte());
					srv.setAttivo(servizio.isAttivo());
					if (serviziDeleganteMap == null) {
						if (srv != null && Validator.isNotNull(srv.getUri())) {
							elencoServizi.add(srv);
						}
					}
					else {
						if (srv != null && Validator.isNotNull(srv.getUri()) && serviziDeleganteMap.containsKey(srv.getId())) {
							elencoServizi.add(srv);
						}
					}
				}
			}

			// Caricamento servizi in base alla tipologia
			serviziByAreaTematicaMap = new LinkedHashMap<AreaTematica, List<Servizio>>();
			List<Servizio> serviziNoAreaTematica = new ArrayList<Servizio>();

			for (Servizio servizio : elencoServizi) {
				AreaTematica areaTematica = servizio.getAreaTematica();
				if (areaTematica != null) {
					if (serviziByAreaTematicaMap.containsKey(areaTematica)) {
						serviziByAreaTematicaMap.get(areaTematica).add(servizio);
					}
					else {
						List<Servizio> serviziList = new ArrayList<Servizio>();
						serviziList.add(servizio);
						serviziByAreaTematicaMap.put(areaTematica, serviziList);
					}
				}
				else {
					serviziNoAreaTematica.add(servizio);
				}
			}

			// Union servizi con tipologia + servizi senza
			if (!serviziNoAreaTematica.isEmpty()) {
				AreaTematica altreAree = new AreaTematica();
				altreAree.setNome(messageSource.getMessage("label.altriServizi", null, request.getLocale()));
				serviziByAreaTematicaMap.put(altreAree, serviziNoAreaTematica);
			}
		}
		catch (Exception e) {
			log.error("getListaServizi :: " + e.getMessage(), e);
		}

		return serviziByAreaTematicaMap;
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
