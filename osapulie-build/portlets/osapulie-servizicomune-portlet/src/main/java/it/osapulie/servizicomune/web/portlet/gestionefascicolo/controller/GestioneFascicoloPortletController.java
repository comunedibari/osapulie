/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionefascicolo.controller;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.AziendaService;
import it.osapulie.service.RichiestaServizioService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.RoleConstants;

/**
 * Portlet controller per la gestione dei fascicoli cittadini.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneFascicoloPortletController")
@RequestMapping("view")
@SessionAttributes("servizioList")
public class GestioneFascicoloPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneFascicoloPortletController.class);

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

	@Inject
	private PortletHelper helper;

	@Inject
	private RichiestaServizioService richiestaServizioService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private AziendaService aziendaService;

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

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access) {
			if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR)) {
				model.addAttribute("comuneIsa", osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa());
			}
			model.addAttribute("richiestaServizioList", getRichiestaServizioList(model, request));
			model.addAttribute("access", access);
		}

		model.addAttribute("statoIscrizioneSelect", "");

		return toLocalViewPath("home");
	}

	/**
	 * @param osApulieUserDetails
	 * @return
	 */
	private boolean accessToService(OSApulieUserDetails osApulieUserDetails) {
		boolean access = false;

		if (osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa() != null) {
			access = true;
		}

		if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR)) {
			access = true;
		}
		return access;
	}

	public List<RichiestaServizio> getRichiestaServizioList(Model model, PortletRequest request) {

		log.debug("getListaPin :: entering method");

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		String cognome = request.getParameter("profiloUtenteCittadino.cognome");
		String nome = request.getParameter("profiloUtenteCittadino.nome");
		String cf = request.getParameter("profiloUtenteCittadino.codifceFiscale");
		String servizio = request.getParameter("servizioSelect");
		String dataRichiestaDa = request.getParameter("dataRichiestaDa");
		String dataRichiestaA = request.getParameter("dataRichiestaA");
		String azienda = request.getParameter("idAzienda");

		Long idServizio = null;
		if (Validator.isNotNull(servizio)) {
			idServizio = Long.parseLong(servizio);
		}
		Long idAzienda = null;
		if (Validator.isNotNull(azienda)) {
			idAzienda = Long.parseLong(azienda);
		}
		Date da = null;
		Date a = null;
		try {
			if (dataRichiestaDa != null && !dataRichiestaDa.isEmpty()) {
				da = simpleDateFormat.parse(dataRichiestaDa);
			}
			if (dataRichiestaA != null && !dataRichiestaA.isEmpty()) {
				a = simpleDateFormat.parse(dataRichiestaA);
			}
		}
		catch (ParseException e) {
			log.error("getRichiesteServizioAzienda :: " + e.getMessage(), e);
		}

		model.addAttribute("nome", nome);
		model.addAttribute("cognome", cognome);
		model.addAttribute("cf", cf);
		model.addAttribute("servizioSelect", servizio);
		model.addAttribute("dataRichiestaDa", dataRichiestaDa);
		model.addAttribute("dataRichiestaA", dataRichiestaA);
		model.addAttribute("idAzienda", azienda);

		Long idComuneISA = null;
		if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR) && osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa() != null) {
			idComuneISA = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa().getId();
		}

		List<RichiestaServizio> richiestaServizioList = richiestaServizioService.searchRichiesteServizio(idComuneISA, cognome, nome, cf, idServizio, idAzienda, da, a);

		if (richiestaServizioList != null) {
			setInfoAggiuntiveMap(richiestaServizioList);
		}

		return richiestaServizioList;
	}

	@ModelAttribute("servizioList")
	public List<Servizio> getServizioComuneISAList(Model model, PortletRequest portletRequest) {

		log.debug("getServizioComuneISAList :: entering method");

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)

		List<Servizio> allServizio = null;

		try {
			if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR)) {
				allServizio = servizioService.getAllServizio();
			}
			else {
				allServizio = getServiziAttiviList(model, portletRequest);
			}
		}
		catch (ServiceLayerException e) {
			log.error("getServizioComuneISAList :: " + e.getMessage(), e);
		}

		return allServizio;

	}

	@ModelAttribute("aziende")
	public List<Azienda> getAziende() {
		List<Azienda> aziende = aziendaService.getAziendeAttive();
		return aziende;
	}

	/**
	 * @param request
	 * @return
	 */
	private List<Servizio> getServiziAttiviList(Model model, PortletRequest request) {
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		Long idComuneISA = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa().getId();
		List<Servizio> elencoServizi = servizioService.getServiziAttiviByComuneISA(idComuneISA);
		return elencoServizi;
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
		return "gestionefascicolo/" + viewName;
	}

	/**
	 * @param richiesteServizi
	 */
	private void setInfoAggiuntiveMap(List<RichiestaServizio> richiesteServizi) {
		try {
			for (RichiestaServizio richiestaServizio : richiesteServizi) {
				setInfoAggiuntive(richiestaServizio);
			}
		}
		catch (Exception e) {
			log.error("setInfoAggiuntiveMap :: " + e.getMessage(), e);
		}
	}

	/**
	 * @param richiestaServizio
	 */
	private void setInfoAggiuntive(RichiestaServizio richiestaServizio) {
		String infoAggiuntive = richiestaServizio.getInfoAggiuntive();
		if (infoAggiuntive != null && !infoAggiuntive.isEmpty() && infoAggiuntive.contains("ID")) {
			Gson gson = new Gson();
			Type type = new TypeToken<Map<String, String>>() {
			}.getType();
			Map<String, String> infoAggiuntiveMap = gson.fromJson(infoAggiuntive, type);
			richiestaServizio.setInfoAggiuntiveMap(infoAggiuntiveMap);
		}
	}
}
