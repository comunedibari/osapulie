/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.selezioneprofilo.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.selezioneprofilo.form.Profilo;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la selezione dei parametri relativi al profilo utente in uso durante la
 * navigazione.
 *
 * @author Gianluca Pindinelli
 */
@Controller("selezioneProfiloPortletController")
@RequestMapping("view")
public class SelezioneProfiloPortletController extends BaseController {

	private static final String PROFILO_CITTADINO = "cittadino";
	private static final String PROFILO_DELEGA = "delega";
	private static final String PROFILO_AZIENDA = "azienda";

	private final Logger log = LoggerFactory.getLogger(SelezioneProfiloPortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	@Inject
	private DelegaService delegaService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {

		ProfiloUtenteCittadino profiloUtenteCittadino = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
		model.addAttribute("profiloUtente", profiloUtenteCittadino);

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "comuniList")
	public List<ComuneISA> getComuniUtenteModel(PortletRequest request) {

		List<ComuneISA> comuniList = null;

		try {
			comuniList = comuneISAService.getComuniAttivi();
		}
		catch (ServiceLayerException e) {
			log.warn("getComuniUtenteModel :: " + e.getMessage());
		}

		return comuniList;
	}

	@ModelAttribute(value = "comuneSelezionato")
	public ComuneISA getComuneSelezionato(PortletRequest request) {

		log.debug("getComuneSelezionato :: entering method");

		UserPreferences userPreferences = helper.getUserPreferences(request);
		ComuneISA comuneSelezionato = new ComuneISA();
		if (userPreferences.getCodiceIstatComune() != null) {
			comuneSelezionato = comuneISAService.getComuneByCodiceIstat(userPreferences.getCodiceIstatComune());
		}

		return comuneSelezionato;
	}

	@ModelAttribute(value = "profiliMap")
	public Map<String, List<Profilo>> getProfiliMap(PortletRequest request) {

		Map<String, List<Profilo>> profiliMap = new LinkedHashMap<String, List<Profilo>>();

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();

		// Aggiunta profilo di default (CF)
		Profilo profiloDefault = new Profilo();
		profiloDefault.setDenominazione(profiloUtenteCittadino.getNome() + " " + profiloUtenteCittadino.getCognome() + " (" + profiloUtenteCittadino.getCodiceFiscale() + ")");
		profiloDefault.setValore(profiloUtenteCittadino.getCodiceFiscale());
		profiloDefault.setTipo(PROFILO_CITTADINO);

		List<Profilo> profiliList = new ArrayList<Profilo>();
		profiliList.add(profiloDefault);
		profiliMap.put(messageSource.getMessage("label.profili.cittadino", null, request.getLocale()), profiliList);

		// Aggiunta aziende associate
		List<Azienda> aziende = osApulieUserDetails.getAziende();
		if (aziende != null && !aziende.isEmpty()) {
			profiliList = new ArrayList<Profilo>();
			for (Azienda azienda : aziende) {
				Profilo profiloAzienda = new Profilo();
				profiloAzienda.setDenominazione(azienda.getRagioneSociale() + " (" + azienda.getPartitaIva() + ")");
				profiloAzienda.setValore(azienda.getPartitaIva());
				profiloAzienda.setTipo(PROFILO_AZIENDA);
				profiliList.add(profiloAzienda);
			}
			profiliMap.put(messageSource.getMessage("label.profili.azienda", null, request.getLocale()), profiliList);
		}
		else {
			// Controllo eliminazione azienda se ancora in sessione utente
			UserPreferences userPreferences = helper.getUserPreferences(request);
			if (userPreferences.getPartitaIvaServizio() != null) {
				userPreferences.setPartitaIvaServizio(null);
			}
		}

		// Aggiunta Deleghe
		if (profiloUtenteCittadino.getProfiliUtenteCittadinoAziende() != null) {

			UserPreferences userPreferences = helper.getUserPreferences(request);

			// Caricamento deleghe
			List<Delega> delegaList = getListaDeleghe(userPreferences);
			if (delegaList != null && !delegaList.isEmpty()) {
				profiliList = new ArrayList<Profilo>();
				for (Delega delega : delegaList) {
					Profilo profiloDelega = new Profilo();
					ProfiloUtenteCittadino delegante = delega.getDelegante();
					profiloDelega.setDenominazione(delegante.getNome() + " " + delegante.getCognome() + " (" + delegante.getCodiceFiscale().toUpperCase() + ")");
					profiloDelega.setValore(delega.getId().toString());
					profiloDelega.setTipo(PROFILO_DELEGA);
					profiliList.add(profiloDelega);
				}
				profiliMap.put(messageSource.getMessage("label.profili.delega", null, request.getLocale()), profiliList);
			}
		}

		return profiliMap;
	}

	@ModelAttribute(value = "profilo")
	public Profilo getProfilo(PortletRequest request) {

		log.debug("getProfilo :: entering method");
		UserPreferences userPreferences = helper.getUserPreferences(request);
		Profilo profilo = new Profilo();
		// Attenzione: l'ordine di verifica è importante!
		if (userPreferences.getPartitaIvaServizio() != null) {
			profilo.setValore(userPreferences.getPartitaIvaServizio());
			profilo.setTipo(PROFILO_AZIENDA);
		}
		else if (userPreferences.getIdDelega() != null && userPreferences.getIdDelega() != 0) {
			profilo.setValore(userPreferences.getIdDelega().toString());
			profilo.setTipo(PROFILO_DELEGA);
		}
		else {
			profilo.setValore(userPreferences.getCodiceFiscaleServizio());
			profilo.setTipo(PROFILO_CITTADINO);
		}

		return profilo;
	}

	/**
	 * Aggiorna il comune ISA selezionato.
	 *
	 * @param entity
	 * @param bindingResult
	 * @param request
	 * @param response
	 */
	@ActionMapping(params = "action=updateComuneSelezionato")
	public void updateComuneSelezionato(@ModelAttribute("comuneSelezionato") ComuneISA entity, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response) {

		log.debug("updateComuneSelezionato :: entering method");

		String codiceIstat = entity.getCodiceIstat();

		ComuneISA comuneSelezionato = comuneISAService.getComuneByCodiceIstat(codiceIstat);

		UserPreferences userPreferences = helper.getUserPreferences(request);
		userPreferences.setIdComuneIsa(comuneSelezionato.getId());
		userPreferences.setCodiceIstatComune(comuneSelezionato.getCodiceIstat());
		userPreferences.setUriServizioGateway(comuneSelezionato.getUriServizioGateway());
		userPreferences.setNomeComune(comuneSelezionato.getNome());

		// Resetto la delega al cambio di comune
		userPreferences.setIdDelega(null);

		// Resetto il codice fiscale dell'utente
		userPreferences.setCodiceFiscaleServizio(helper.getOsApulieUserDetails().getProfiloUtenteCittadino().getCodiceFiscale());

		// Aggiornamento mappa profili
		Map<String, List<Profilo>> profiliMap = getProfiliMap(request);
		model.addAttribute("profiliMap", profiliMap);

	}

	/**
	 * Aggiorna il profilo selezionato.
	 *
	 * @param profilo
	 * @param bindingResult
	 * @param request
	 * @param response
	 */
	@ActionMapping(params = "action=updateProfiloSelezionato")
	public void updateProfiloSelezionato(@ModelAttribute("profilo") Profilo profilo, BindingResult bindingResult, ActionRequest request, ActionResponse response) {

		log.debug("updateProfiloSelezionato :: entering method");
		Map<String, List<Profilo>> profiliMap = getProfiliMap(request);
		// Ricerco il profilo
		if (profiliMap != null) {
			for (Map.Entry<String, List<Profilo>> entry : profiliMap.entrySet()) {
				for (Profilo profiloMap : entry.getValue()) {
					if (profiloMap.getValore().equals(profilo.getValore())) {
						profilo.setTipo(profiloMap.getTipo());
						break;
					}
				}
			}
		}

		// Setto il profilo
		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (profilo.getTipo().equals(PROFILO_AZIENDA)) {
			userPreferences.setPartitaIvaServizio(profilo.getValore());
			userPreferences.setIdDelega(null);
		}
		else if (profilo.getTipo().equals(PROFILO_CITTADINO)) {
			userPreferences.setCodiceFiscaleServizio(profilo.getValore());
			userPreferences.setPartitaIvaServizio(null);
			userPreferences.setIdDelega(null);
		}
		else if (profilo.getTipo().equals(PROFILO_DELEGA)) {
			try {
				Delega delega = delegaService.getDelegaByPk(Long.parseLong(profilo.getValore()));
				userPreferences.setIdDelega(delega.getId());
				userPreferences.setCodiceFiscaleServizio(delega.getDelegante().getCodiceFiscale());
				userPreferences.setPartitaIvaServizio(null);
			}
			catch (NumberFormatException e) {
				log.error("updateProfiloSelezionato :: " + e.getMessage(), e);
			}
			catch (ServiceLayerException e) {
				log.error("updateProfiloSelezionato :: " + e.getMessage(), e);
			}
		}
	}

	/**
	 * Ritorna la lista delle deleghe (deleganti) associate all'utente.
	 *
	 * @param request
	 * @return
	 */
	private List<Delega> getListaDeleghe(UserPreferences userPreferences) {

		Long idComuneIsa = userPreferences.getIdComuneIsa();

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		try {
			osApulieUserDetails = profilazioneUtenteService.getById(osApulieUserDetails.getUsername());
		}
		catch (ProfilazioneUtenteException e) {
			log.error("getListaDelegati :: " + e.getMessage(), e);
		}

		List<Delega> delegaList = new ArrayList<Delega>();
		List<Azienda> aziende = osApulieUserDetails.getAziende();
		if (aziende != null) {
			for (Azienda azienda : aziende) {
				delegaList.addAll(azienda.getDelegheDeleganti());
			}
		}
		List<Delega> delegaListResult = new ArrayList<Delega>();
		for (Delega delega : delegaList) {
			if (delega.isAttiva() && idComuneIsa != null && delega.getComuneIsa().getId() == idComuneIsa
					&& delega.getIdDelegante() != osApulieUserDetails.getProfiloUtenteCittadino().getId().longValue()) {
				delega.getDelegante();
				delegaListResult.add(delega);
			}
		}

		return delegaListResult;
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/selezioneprofilo/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "selezioneprofilo/" + viewName;
	}
}
