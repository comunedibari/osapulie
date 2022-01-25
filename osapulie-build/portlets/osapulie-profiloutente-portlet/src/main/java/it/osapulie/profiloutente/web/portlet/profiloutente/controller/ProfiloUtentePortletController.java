/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.web.portlet.profiloutente.controller;

import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.Validator;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Pin;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.enumeration.TipoAzienda;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.profiloutente.model.view.ProfiloUtenteModel;
import it.osapulie.profiloutente.utils.ProfiloUtenteModelUtil;
import it.osapulie.profiloutente.web.portlet.profiloutente.validator.PinValidator;
import it.osapulie.profiloutente.web.portlet.profiloutente.validator.ProfiloUtenteValidator;
import it.osapulie.profiloutente.web.utils.PortletUtils;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneService;
import it.osapulie.service.PinService;
import it.osapulie.shared.enumeration.DeploymentScenario;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la gestione del profilo utente.
 *
 * @author Gianluca Pindinelli
 */
@Controller("profiloUtentePortletController")
@RequestMapping("view")
@SessionAttributes({ "profiloUtenteModel" })
public class ProfiloUtentePortletController {

	private final Logger log = LoggerFactory.getLogger(ProfiloUtentePortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ComuneService comuneService;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	@Inject
	private PinService pinService;

	@Autowired
	@Qualifier("profiloUtenteValidator")
	private ProfiloUtenteValidator profiloUtenteValidator;

	@Autowired
	@Qualifier("pinValidator")
	private PinValidator pinValidator;

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

		try {
			PortletUtils.setDateIntoLiferayInputDate(request, new Date(), "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
		}
		catch (Exception e) {
			log.error("homePage :: " + e.getMessage(), e);
		}

		// Controllo disabilitazione campi in base allo scenario (se IDP -> disabilitazione sezioni
		// in JSP)
		boolean disableForIdp = false;
		if (helper.getDeploymentScenario() == DeploymentScenario.SCENARIO_3.getScenario()) {
			disableForIdp = true;
		}
		model.addAttribute("disableForIdp", disableForIdp);

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "profiloUtente")
	public ProfiloUtenteModel getProfiloUtenteModel(PortletRequest request) {

		ProfiloUtenteModel profiloUtenteModel = null;
		try {
			OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

			X509Certificate certificato = osApulieUserDetails.getCertificato();
			try {
				osApulieUserDetails = profilazioneUtenteService.getById(osApulieUserDetails.getUsername());
				osApulieUserDetails.setCertificato(certificato);
			}
			catch (ProfilazioneUtenteException e) {
				log.error("getProfiloUtenteModel :: " + e.getMessage(), e);
			}

			if (Validator.isNotNull(osApulieUserDetails)) {
				profiloUtenteModel = ProfiloUtenteModelUtil.getProfiloUtenteFromOSApulieUserDetails(osApulieUserDetails);
			}

			// Controllo esistenza certificato: se l'utente ha effettuato un'autenticazione forte ->
			// salvo la data su DB
			ProfiloUtenteCittadino profiloUtenteCittadino = osApulieUserDetails.getProfiloUtenteCittadino();
			if ((osApulieUserDetails.getCertificato() != null) && (profiloUtenteCittadino.getDataAutenticazioneForte() == null)) {
				profiloUtenteCittadino.setDataAutenticazioneForte(new Date());
				try {
					profilazioneUtenteService.update(ProfiloUtenteModelUtil.getOSApulieUserDetailsFromProfiloUtente(profiloUtenteModel, osApulieUserDetails));
					profiloUtenteModel = ProfiloUtenteModelUtil.getProfiloUtenteFromOSApulieUserDetails(osApulieUserDetails);
				}
				catch (Throwable e) {
					log.error("getProfiloUtenteModel :: " + e.getMessage(), e);
				}

				// Setto ultimo pin
				Pin pin = pinService.getLastPin(Long.parseLong(osApulieUserDetails.getUsername()));
				if (pin != null) {
					profiloUtenteModel.setStatoPin(pin.getStato());
				}
			}

			// Setto il liferay date time
			try {
				PortletUtils.setDateIntoLiferayInputDate(request, profiloUtenteModel.getDataNascita(), "inputDataNascitaDay", "inputDataNascitaMonth", "inputDataNascitaYear");
			}
			catch (Exception e) {
				log.warn("getProfiloUtenteModel :: " + e.getMessage());
			}
		}
		catch (Exception e) {
			log.error("getProfiloUtenteModel :: " + e.getMessage(), e);
		}

		return profiloUtenteModel;
	}

	@RenderMapping(params = "action=renderRichiediPin")
	public String renderRichiediPin(Model model, PortletRequest request) {

		Pin pin = null;

		// Verifica esistenza pin in base allo stato
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		pin = pinService.getLastPin(Long.parseLong(osApulieUserDetails.getUsername()));

		if (pin == null) {
			pin = new Pin();
		}

		model.addAttribute("pin", pin);
		model.addAttribute("comuneIsaList", getComuneISAList());

		// Controllo disabilitazione campi in base allo scenario (se IDP -> disabilitazione sezioni
		// in JSP)
		boolean disableForIdp = false;
		if (helper.getDeploymentScenario() == DeploymentScenario.SCENARIO_3.getScenario()) {
			disableForIdp = true;
		}
		model.addAttribute("disableForIdp", disableForIdp);

		return toLocalViewPath("richiestaPin");
	}

	/**
	 * Modifica il PIN in stato di "richiesta".
	 *
	 * @param pin
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=updatePin")
	public void updatePin(@ModelAttribute("pin") Pin pin, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("updatePin :: entering method");

		pinValidator.validate(pin, bindingResult);
		if (bindingResult.hasErrors()) {
			response.setRenderParameter("action", "renderRichiediPin");
			return;
		}

		// Salvataggio nuova richiesta Pin
		// Caricamento profilo utente cittadino
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		pin.setProfiloUtenteCittadino(osApulieUserDetails.getProfiloUtenteCittadino());

		// Caricamento ComuneIsa
		ComuneISA comuneISA = comuneISAService.getComuneByPk(pin.getComuneIsa().getId());
		pin.setComuneIsa(comuneISA);
		pin.setDataRichiesta(new Date());
		pin.setStato(PortletConstants.STATO_PIN_RICHIESTO);

		pinService.savePin(pin);

		// TODO : invio email di PEC al comune

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", "home");
		sessionStatus.setComplete();

	}

	/**
	 * Annulla una richiesta di PIN.
	 *
	 * @param pin
	 * @param bindingResult
	 * @param model
	 * @param request
	 * @param response
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=annullaPin")
	public void annullaPin(@RequestParam(required = true) String idPin, Model model, ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("annullaPin :: entering method :: idPin : " + idPin);

		Pin pin = pinService.getPinByPk(Long.parseLong(idPin));

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		if (osApulieUserDetails.getProfiloUtenteCittadino().getId().longValue() == pin.getProfiloUtenteCittadino().getId().longValue()) {
			pin.setStato(PortletConstants.STATO_PIN_ANNULLATO);
			pinService.savePin(pin);
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", "home");
		sessionStatus.setComplete();
	}

	@ModelAttribute(value = "comuneISAList")
	public List<ComuneISA> getComuneISAList() {
		try {

			List<ComuneISA> comuneISAs = comuneISAService.getComuniAttivi();
			return comuneISAs;
		}
		catch (Exception e) {
			log.error("getComuneISAList :: " + e.getMessage(), e);
		}
		return null;
	}

	@ModelAttribute(value = "canaliComunicazione")
	public List<String> getCanaliComunicazioneList() {

		List<String> canaliComunicazione = new ArrayList<String>();

		canaliComunicazione.add(PortletConstants.CANALE_COMUNICAZIONE_EMAIL);
		canaliComunicazione.add(PortletConstants.CANALE_COMUNICAZIONE_PEC);

		return canaliComunicazione;
	}

	@ModelAttribute(value = "tipiAzienda")
	public TipoAzienda[] getTipiAzienda() {
		return TipoAzienda.values();
	}

	@ModelAttribute(value = "comuneList")
	public List<Comune> getComuneList() {

		try {
			return comuneService.getAllComuni();
		}
		catch (Exception e) {
			log.error("getComuneList :: " + e.getMessage(), e);
		}
		return null;
	}

	@ActionMapping(params = "action=updateProfiloUtente")
	public void updateProfiloUtente(@ModelAttribute("profiloUtente") ProfiloUtenteModel profiloUtenteModel, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response)
			throws Exception {

		profiloUtenteValidator.validate(profiloUtenteModel, bindingResult);
		if (bindingResult.hasErrors()) {
			response.setRenderParameter("action", "home");
			model.addAttribute("profiloUtente", getProfiloUtenteModel(request));
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			return;
		}

		try {
			OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
			osApulieUserDetails = profilazioneUtenteService.getById(osApulieUserDetails.getUsername());

			Date dataNascita = PortletUtils.getDateFromLiferayInputDate(request, "dataNascitaDay", "dataNascitaMonth", "dataNascitaYear");
			profiloUtenteModel.setDataNascita(dataNascita);

			try {
				// Caricamento Comune ISA
				ComuneISA comuneSelezionato = null;
				if (profiloUtenteModel.getComuneIsa() != 0) {
					comuneSelezionato = comuneISAService.getComuneByPk(profiloUtenteModel.getComuneIsa());
				}
				else {
					comuneSelezionato = comuneISAService.getComuneByPk(-1);
				}
				profiloUtenteModel.setComuneISAObject(comuneSelezionato);
				profilazioneUtenteService.update(ProfiloUtenteModelUtil.getOSApulieUserDetailsFromProfiloUtente(profiloUtenteModel, osApulieUserDetails));

				// Aggiornamento oggetto Comune ISA in memoria
				if (profiloUtenteModel.getComuneIsa() != 0) {

					UserPreferences userPreferences = helper.getUserPreferences(request);
					userPreferences.setIdComuneIsa(comuneSelezionato.getId());
					userPreferences.setCodiceIstatComune(comuneSelezionato.getCodiceIstat());
					userPreferences.setUriServizioGateway(comuneSelezionato.getUriServizioGateway());
					userPreferences.setNomeComune(comuneSelezionato.getNome());

					// Resetto la delega al cambio di comune
					userPreferences.setIdDelega(null);

					// Resetto il codice fiscale dell'utente
					userPreferences.setCodiceFiscaleServizio(helper.getOsApulieUserDetails().getProfiloUtenteCittadino().getCodiceFiscale());
				}
			}
			catch (Throwable e) {
				log.error("updateProfiloUtente :: " + e.getMessage(), e);

				String message = e.getCause().getCause().getCause().getCause().getMessage();
				// Errore chiave duplicata CF
				if (message.contains("Duplicate entry") && message.contains(osApulieUserDetails.getProfiloUtenteCittadino().getCodiceFiscale())) {

					bindingResult.rejectValue("username", "error.label.usernameDuplicate");
					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					response.setRenderParameter("action", "home");
					return;
				}
				// Errore chiave duplicata Email
				if (message.contains("Duplicate entry") && message.contains(osApulieUserDetails.getProfiloUtenteCittadino().getMail())) {
					bindingResult.rejectValue("mail", "error.label.mailDuplicate");
					model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
					response.setRenderParameter("action", "home");
					return;
				}
			}

			model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
			model.addAttribute("profiloUtente", getProfiloUtenteModel(request));
			response.setRenderParameter("action", "home");
		}
		catch (Exception e) {
			log.error("updateProfiloUtente :: " + e.getMessage(), e);
		}
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
		return "profiloutente/" + viewName;
	}
}
