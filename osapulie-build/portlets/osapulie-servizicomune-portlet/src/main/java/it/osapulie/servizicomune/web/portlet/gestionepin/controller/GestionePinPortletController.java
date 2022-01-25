/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionepin.controller;

import java.util.Date;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.model.User;

import it.osapulie.domain.Pin;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.service.PinService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PasswordGenerator;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet controller per la gestione delle richieste di PIN inviate dai cittadini.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestionePinPortletController")
@RequestMapping("view")
public class GestionePinPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestionePinPortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private PinService pinService;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

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
			if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), "Administrator")) {
				model.addAttribute("comuneIsa", osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa());
			}
			model.addAttribute("pinList", getListaPin(model, request));
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

		if (helper.userHasRole(osApulieUserDetails.getLiferayUser(), "Administrator")) {
			access = true;
		}
		return access;
	}

	@RenderMapping(params = "action=detailPin")
	public String detailDelega(@ModelAttribute(value = "pin") Pin pin, PortletRequest request) {
		return toLocalViewPath("detailPin");
	}

	public List<Pin> getListaPin(Model model, PortletRequest request) {

		log.debug("getListaPin :: entering method");

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		String nome = request.getParameter("profiloUtenteCittadino.nome");
		String cognome = request.getParameter("profiloUtenteCittadino.cognome");
		String cf = request.getParameter("profiloUtenteCittadino.codifceFiscale");
		String stato = request.getParameter("statoPinSelect");

		model.addAttribute("nome", nome);
		model.addAttribute("cognome", cognome);
		model.addAttribute("cf", cf);
		model.addAttribute("statoPinSelect", stato);

		Long id = null;
		if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), "Administrator") && osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa() != null) {
			id = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa().getId();
		}

		List<Pin> pinList = pinService.searchPin(id, cognome, nome, cf, stato);

		return pinList;
	}

	@ModelAttribute(value = "pin")
	public Pin getDettaglioDelega(@RequestParam(required = false) String idPin, PortletRequest request) throws Exception {

		log.debug("homePage :: entering method");

		Pin pin = null;

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access && (idPin != null)) {
			long idDelegaLong = Long.parseLong(idPin);
			pin = pinService.getPinByPk(idDelegaLong);
		}

		return pin;
	}

	/**
	 * Genera il PIN, cambio lo stato dell'oggetto in ASSEGNATO e lo invia tramite mail all'utente.
	 *
	 * @param idPin
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=updatePinAssegnato")
	public void updatePinAssegnato(@RequestParam(required = true) String idPin, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("updatePinAssegnato :: entering method");

		Pin pin = pinService.getPinByPk(Long.parseLong(idPin));
		pin.setDataAttivazione(new Date());
		pin.setStato(PortletConstants.STATO_PIN_ASSEGNATO);

		// Generazione password
		String password = PasswordGenerator.generateRandomString(8);

		ProfiloUtenteCittadino profiloUtenteCittadino = pin.getProfiloUtenteCittadino();
		try {

			// Setto la nuova password
			OSApulieUserDetails osApulieUserDetails = profilazioneUtenteService.getById(String.valueOf(profiloUtenteCittadino.getId()));
			User liferayUser = osApulieUserDetails.getLiferayUser();
			liferayUser.setPasswordUnencrypted(password);
			liferayUser.setPassword(password);
			osApulieUserDetails.getProfiloUtenteCittadino().setPassword(password);

			// Setto i dati circa l'autenticazione forte
			osApulieUserDetails.getProfiloUtenteCittadino().setAutenticazioneForte(true);
			osApulieUserDetails.getProfiloUtenteCittadino().setDataAutenticazioneForte(new Date());
			profilazioneUtenteService.update(osApulieUserDetails);

			pinService.updatePin(pin);
		}
		catch (ServiceLayerException e) {
			log.error("updatePinAssegnato :: " + e.getMessage(), e);
			response.setRenderParameter("action", "common/defError");
			return;
		}
		catch (ProfilazioneUtenteException e) {
			log.error("updatePinAssegnato :: " + e.getMessage(), e);
			response.setRenderParameter("action", "common/defError");
			return;
		}

		// Invio email (PEC o tradizionale) all'utente
		String subjectMail = messageSource.getMessage("mail.pin.subject1", new String[] { pin.getComuneIsa().getNome() }, request.getLocale());
		Map<String, String> mailModel = new HashMap<String, String>();
		mailModel.put("cognome", profiloUtenteCittadino.getCognome());
		mailModel.put("nome", profiloUtenteCittadino.getNome());
		mailModel.put("pin", password);
		mailModel.put("firmaComune", "Comune di " + pin.getComuneIsa().getNome());

		try {
			senderHelper.sendCommunicationToCittadino(subjectMail, "velocityTemplate/pinAssegnato.vm", mailModel, null, profiloUtenteCittadino);
		}
		catch (CommunicationException e) {
			log.error("updatePinAssegnato :: " + e.getMessage(), e);
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", toLocalViewPath("home"));

		sessionStatus.setComplete();

	}

	/**
	 * Respinge la richiesta di PIN.
	 *
	 * @param idPin
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=updatePinRespinto")
	public void updatePinRespinto(@ModelAttribute(value = "pin") Pin pin, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("updatePinRespinto :: entering method");

		pin.setStato(PortletConstants.STATO_PIN_RESPINTO);

		ProfiloUtenteCittadino profiloUtenteCittadino = pin.getProfiloUtenteCittadino();
		try {
			pinService.updatePin(pin);
		}
		catch (ServiceLayerException e) {
			log.error("updatePinRespinto :: " + e.getMessage(), e);
			response.setRenderParameter("action", "common/defError");
			return;
		}

		String subjectMail = messageSource.getMessage("mail.pin.subject2", new String[] { pin.getComuneIsa().getNome() }, request.getLocale());
		Map<String, String> mailModel = new HashMap<String, String>();
		mailModel.put("cognome", profiloUtenteCittadino.getCognome());
		mailModel.put("nome", profiloUtenteCittadino.getNome());
		mailModel.put("motivazione", pin.getNote());
		mailModel.put("firmaComune", "Comune di " + pin.getComuneIsa().getNome());

		try {
			senderHelper.sendCommunicationToCittadino(subjectMail, "velocityTemplate/pinRespinto.vm", mailModel, null, profiloUtenteCittadino);
		}
		catch (Exception e) {
			log.error("updatePinRespinto :: " + e.getMessage(), e);
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", toLocalViewPath("home"));

		sessionStatus.setComplete();

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
		return "gestionepin/" + viewName;
	}
}
