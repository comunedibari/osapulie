/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.segnalazione.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.util.PortalUtil;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.service.CommonService;
import it.osapulie.service.FascicoloUtenteService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.portlet.segnalazione.form.SegnalazioneCustomForm;
import it.osapulie.shared.service.Segnalazione;
import it.osapulie.shared.service.SegnalazioneFoglia;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.SenderHelper;
import it.osapulie.web.portlet.util.UploadItem;

/**
 * Portlet per l'invio di una segnalazione.
 *
 * @author Amleto Piano
 * @author Gianluca Pindinelli
 */
@Controller("invioSegnalazionePortletController")
@RequestMapping("view")
@SessionAttributes({ "segnalazione", "segnalazioneCustomForm", PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER })
public class InvioSegnalazionePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(InvioSegnalazionePortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private SenderHelper senderHelper;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Inject
	private FascicoloUtenteService fascicoloService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	public CommonService commonService;

	@Inject
	@Named("segnalazioneCustomValidator")
	private Validator segnalazioneCustomValidator;

	@Value("#{applicationProperties['send.reports.enable']}")
	protected Boolean sendReportsEnable;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta. Renderizza la pagina
	 * di invio segnalazione per il cittadino.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String renderHome(PortletSession session, PortletRequest portletRequest, Model model) throws Exception {
		Segnalazione segnalazione = (Segnalazione) session.getAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);
		session.removeAttribute(PortletConstants.SEGNALAZIONE_SHARED_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);

		if (sendReportsEnable) {
			if (segnalazione == null) {
				model.addAttribute("errore", "true");
				model.addAttribute("formError", messageSource.getMessage("exception.model", null, portletRequest.getLocale()));
			}
			else {
				model.addAttribute("segnalazione", segnalazione);
				ProfiloUtenteCittadino profiloUtenteCittadino = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
				model.addAttribute("profiloUtente", profiloUtenteCittadino);
			}
		}
		else {
			model.addAttribute("errore", "true");
			model.addAttribute("formError", messageSource.getMessage("exception.service.disable", null, portletRequest.getLocale()));
		}
		return toLocalViewPath("elencoSegnalazioni");
	}

	/**
	 * Presenta a video la pagina di invio segnalazione custom.
	 *
	 * @param model
	 * @return l'ID della view da ritornare a video
	 */
	@RenderMapping(params = "action=segnalazioneCustom")
	public String renderInvioSegnalazioneCustom(@RequestParam(value = PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, required = false) String successReturnUrl,
			@RequestParam(value = PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER, required = false) String cancelReturnUrl, Model model, PortletRequest portletRequest,
			PortletSession session) {

		SegnalazioneCustom segnalazioneCustom = commonService.getSegnalazioneCustomFromSession(session);

		if (successReturnUrl == null || cancelReturnUrl == null || segnalazioneCustom == null) {
			model.addAttribute("formError", messageSource.getMessage("exception.notAvailable.title", null, portletRequest.getLocale()));
		}
		else {
			// Caricamento form in base a request params
			SegnalazioneCustomForm segnalazioneCustomForm = new SegnalazioneCustomForm();

			segnalazioneCustomForm.setCodiceServizio(segnalazioneCustom.getCodiceServizio());
			segnalazioneCustomForm.setDescrizione(segnalazioneCustom.getDescrizione());
			segnalazioneCustomForm.setEmail(segnalazioneCustom.getEmail());
			segnalazioneCustomForm.setNote(segnalazioneCustom.getNote());
			segnalazioneCustomForm.setMotivazioni(segnalazioneCustom.getMotivazioni());

			segnalazioneCustomForm.setCodiceFiscale(segnalazioneCustom.getCodiceFiscale());
			segnalazioneCustomForm.setNome(segnalazioneCustom.getNome());
			segnalazioneCustomForm.setCognome(segnalazioneCustom.getCognome());

			segnalazioneCustomForm.setOggetto(segnalazioneCustom.getOggetto());

			try {
				model.addAttribute(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, URLDecoder.decode(successReturnUrl, "UTF-8"));
				model.addAttribute(PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER, URLDecoder.decode(cancelReturnUrl, "UTF-8"));
			}
			catch (UnsupportedEncodingException e) {
				log.error("renderInvioSegnalazioneCustom :: " + e.getMessage(), e);
			}

			if (segnalazioneCustomForm.getEmail() == null || !com.liferay.portal.kernel.util.Validator.isEmailAddress(segnalazioneCustomForm.getEmail())) {
				model.addAttribute("formError", messageSource.getMessage("error.email.required", null, portletRequest.getLocale()));
				return toLocalViewPath("segnalazioneCustom");
			}

			model.addAttribute("segnalazioneCustomForm", segnalazioneCustomForm);
		}

		return renderSegnalazioneCustomForm();
	}

	@RenderMapping(params = "action=segnalazioneCustomForm")
	public String renderSegnalazioneCustomForm() {
		return toLocalViewPath("segnalazioneCustom");
	}

	@ActionMapping(params = "action=invioSegnalazioneCustom")
	public void invioSegnalazioneCustom(@ModelAttribute SegnalazioneCustomForm segnalazioneCustomForm, BindingResult bindingResult, Model model, ActionRequest request, ActionResponse response,
			PortletSession session, SessionStatus sessionStatus) {

		segnalazioneCustomValidator.validate(segnalazioneCustomForm, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
			response.setRenderParameter("action", "segnalazioneCustomForm");
			return;
		}

		// Invio segnalazione
		String osapulieHost = request.getServerName();
		List<String> parametriOggetto = new ArrayList<String>();
		parametriOggetto.add(osapulieHost);
		parametriOggetto.add(segnalazioneCustomForm.getOggetto());

		String oggetto = messageSource.getMessage("label.segnalazionecustom.oggetto", parametriOggetto.toArray(), request.getLocale());

		Map<String, String> modelMail = new HashMap<String, String>();
		modelMail.put("nome", segnalazioneCustomForm.getNome());
		modelMail.put("cognome", segnalazioneCustomForm.getCognome());
		modelMail.put("codiceFiscale", segnalazioneCustomForm.getCodiceFiscale());
		modelMail.put("motivazione", segnalazioneCustomForm.getMotivazione());
		modelMail.put("oggetto", segnalazioneCustomForm.getOggetto());
		if (segnalazioneCustomForm.getNote() != null && !segnalazioneCustomForm.getNote().isEmpty()) {
			modelMail.put("note", segnalazioneCustomForm.getNote());
		}

		try {
			senderHelper.sendMail(oggetto, "velocityTemplate/segnalazioneCustom.vm", modelMail, null, segnalazioneCustomForm.getEmail());
			model.addAttribute("message", messageSource.getMessage("label.segnalazione.ok", null, request.getLocale()));

			String successUrl = (String) model.asMap().get(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER);
			response.setRenderParameter(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, successUrl);
			cleanSessionAttribute(model, session);
			sessionStatus.setComplete();
			// Aggiunta informazione in sessione (per verifica puntuale su invio segnalazione da
			// altre portlet)
			if (segnalazioneCustomForm.getCodiceServizio() != null) {
				session.setAttribute(PortletConstants.SEGNALAZIONE_SERVIZIO_SHARED_ATTRIBUTE + segnalazioneCustomForm.getCodiceServizio() + "_" + segnalazioneCustomForm.getCodiceFiscale(), true,
						PortletSession.APPLICATION_SCOPE);
			}
			response.setRenderParameter("action", "returnAction");
		}
		catch (CommunicationException e) {
			log.error("invioSegnalazioneCustom :: " + e.getMessage(), e);
			model.addAttribute("formError", e.getMessage());
			response.setRenderParameter("action", "segnalazioneCustomForm");
		}
	}

	@RenderMapping(params = "action=returnAction")
	public String returnAction(@RequestParam(value = PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER) String successReturnUrl, Model model, PortletRequest portletRequest) {
		model.addAttribute("segnalazioneInviata", true);
		model.addAttribute(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, successReturnUrl);
		return toLocalViewPath("segnalazioneCustom");
	}

	@ActionMapping(params = "action=annullaSegnalazione")
	public void annullaSegnalazione(Model model, ActionRequest request, ActionResponse response, PortletSession session, SessionStatus sessionStatus) throws IOException {

		String cancelUrl = (String) model.asMap().get(PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER);

		cleanSessionAttribute(model, session);

		sessionStatus.setComplete();
		response.sendRedirect(cancelUrl);
	}

	/**
	 * Pulisce gli attributi in sessione.
	 *
	 * @param model
	 */
	private void cleanSessionAttribute(Model model, PortletSession session) {
		model.addAttribute("segnalazioneCustomForm", null);
		model.addAttribute(PortletConstants.SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER, null);
		model.addAttribute(PortletConstants.SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER, null);
		session.removeAttribute(PortletConstants.SEGNALAZIONE_CUSTOM_SHARED_ATTRIBUTE, PortletSession.APPLICATION_SCOPE);
	}

	/**
	 * Action per confermare i dati da inviare nella segnalazione.
	 *
	 * @param segnalazione
	 * @param model
	 * @param response
	 * @param session
	 * @param portletRequest
	 */
	@ActionMapping(params = "action=confermaSegnalazione")
	public void confermaSegnalazione(@ModelAttribute Segnalazione segnalazione, Model model, ActionRequest request, ActionResponse response) {

		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(request);

		@SuppressWarnings("unchecked")
		Enumeration<String> en = httpServletRequest.getParameterNames();
		boolean isCheck = false;
		boolean isValue = true;
		ProfiloUtenteCittadino profiloUtenteCittadino = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
		model.addAttribute("profiloUtente", profiloUtenteCittadino);
		segnalazione.setNote(httpServletRequest.getParameter("commento_segnalazione"));

		// Azzeramento segnalazioni foglia
		List<SegnalazioneFoglia> listaSegnalazioni = segnalazione.getListaSegnalazioni();
		if (listaSegnalazioni != null) {
			for (SegnalazioneFoglia segnalazioneFoglia : listaSegnalazioni) {
				clearSegnalazioniFoglia(segnalazioneFoglia);
			}
		}

		while (en.hasMoreElements()) {
			// Ricavo l'elemento chiave
			String key = en.nextElement();
			// Ricavo il valore
			if (key.toUpperCase().contains("CHECK_")) {
				isCheck = true;
				String[] split = key.split("_");
				SegnalazioneFoglia segnalazioneFoglia = listaSegnalazioni.get(Integer.valueOf(split[1]));
				for (int i = 2; i < split.length; i++) {
					segnalazioneFoglia = segnalazioneFoglia.getListaSegnalazioni().get(Integer.valueOf(split[i]));
				}
				segnalazioneFoglia.setCheck(true);
				String valore = httpServletRequest.getParameter("valore_new_" + key.substring(6));
				if ((valore == null) || valore.equals("")) {
					isValue = false;
				}
				segnalazioneFoglia.setValore_new(valore);

			}
		}

		model.addAttribute("segnalazione", segnalazione);
		if ((segnalazione.getNote() == null) || segnalazione.getNote().equals("")) {
			model.addAttribute("formError", messageSource.getMessage("exception.note", null, request.getLocale()));
			response.setRenderParameter("action", "confermaInvioNuovaSegnalazione");
		}
		else if (isCheck && isValue) {
			response.setRenderParameter("action", "invioNuovaSegnalazione");
		}
		else {
			if (!isCheck) {
				model.addAttribute("formError", messageSource.getMessage("exception.required", null, request.getLocale()));
			}
			if (!isValue) {
				model.addAttribute("formError", messageSource.getMessage("exception.value", null, request.getLocale()));
			}
			response.setRenderParameter("action", "confermaInvioNuovaSegnalazione");
		}
	}

	/**
	 * @param segnalazioneFoglia
	 */
	private void clearSegnalazioniFoglia(SegnalazioneFoglia segnalazioneFoglia) {

		segnalazioneFoglia.setCheck(false);
		List<SegnalazioneFoglia> listaSegnalazioni = segnalazioneFoglia.getListaSegnalazioni();
		if (listaSegnalazioni != null) {
			for (SegnalazioneFoglia innerSegnalazioneFoglia : listaSegnalazioni) {
				clearSegnalazioniFoglia(innerSegnalazioneFoglia);
			}
		}

	}

	/**
	 * Action per inviare la segnalazione.
	 *
	 * @param segnalazione
	 * @param model
	 * @param back
	 * @param response
	 * @param session
	 * @param actionRequest
	 * @param sessionStatus
	 */
	@ActionMapping(params = "action=invioSegnalazione")
	public void inviaSegnalazione(@ModelAttribute Segnalazione segnalazione, Model model, @RequestParam(required = false, value = "back") String back, ActionRequest actionRequest,
			ActionResponse response, SessionStatus sessionStatus) {

		UserPreferences userPreferences = helper.getUserPreferences(actionRequest);
		ProfiloUtenteCittadino profiloUtenteCittadino = helper.getOsApulieUserDetails().getProfiloUtenteCittadino();
		if (back == null) {
			try {

				InvioSegnalazioneUtility segnalazioneUtility = new InvioSegnalazioneUtility();
				String mail = "";
				for (SegnalazioneFoglia nodo : segnalazione.getListaSegnalazioni()) {
					mail += segnalazioneUtility.stampaNodo(nodo);
				}

				Map<String, String> modelMail = new HashMap<String, String>();
				modelMail.put("nome", profiloUtenteCittadino.getNome());
				modelMail.put("cognome", profiloUtenteCittadino.getCognome());
				modelMail.put("segnalazione", mail);
				modelMail.put("servizio", segnalazione.getServizio());
				modelMail.put("note", segnalazione.getNote());

				// Caricamento nome portale
				String osapuliePortalName = null;
				try {
					osapuliePortalName = PrefsPropsUtil.getString("osapulie.portal.name");
				}
				catch (Exception e1) {
					log.error("inviaSegnalazione :: " + e1.getMessage(), e1);
				}

				String oggetto = "";
				if (osapuliePortalName != null) {
					oggetto += osapuliePortalName + " - ";
				}

				UploadItem file = null;
				ComuneISA comuneErogatore = comuneISARepository.findOne(userPreferences.getIdComuneIsa());
				oggetto += messageSource.getMessage("label.oggetto", null, actionRequest.getLocale()) + " " + segnalazione.getServizio() + " "
						+ messageSource.getMessage("label.utente", null, actionRequest.getLocale()) + " " + profiloUtenteCittadino.getCognome() + " " + profiloUtenteCittadino.getNome();
				String numeroProtocollo = senderHelper.sendCommunicationToComuneISA(oggetto, "velocityTemplate/segnalazione.vm", modelMail, file, comuneErogatore);

				model.addAttribute("segnalazione", segnalazione);
				model.addAttribute("profiloUtente", profiloUtenteCittadino);
				model.addAttribute("cntrSegnalazione", true);

				model.addAttribute("message", messageSource.getMessage("label.segnalazione.ok", null, actionRequest.getLocale()));

				try {
					String[] arg = { segnalazione.getServizio() };

					Fascicolo fascicolo = new Fascicolo();
					fascicolo.setIdProfiloUtente(profiloUtenteCittadino);

					String servizio = messageSource.getMessage("label.segnalazione.fascicolazione.invio", arg, actionRequest.getLocale());

					fascicolo.setServizio(servizio);
					fascicolo.setUserPreferences(helper.getUserPreferences(actionRequest));
					fascicolo.setRicercabileDaComune(true);
					fascicolo.setNumeroProtocollo(null);
					fascicolo.setChecksum(null);
					fascicolo.setCodiceServizio(segnalazione.getServizioCode());
					fascicoloService.saveNuovaRichiesta(fascicolo);

					if (com.liferay.portal.kernel.util.Validator.isNotNull(numeroProtocollo)) {
						Fascicolo fascicoloProtocollo = new Fascicolo();
						fascicoloProtocollo.setIdProfiloUtente(profiloUtenteCittadino);
						fascicoloProtocollo.setServizio(messageSource.getMessage("label.segnalazione.fascicolazione.risposta", arg, actionRequest.getLocale()));
						fascicoloProtocollo.setUserPreferences(helper.getUserPreferences(actionRequest));
						fascicoloProtocollo.setRicercabileDaComune(true);
						fascicoloProtocollo.setNumeroProtocollo(numeroProtocollo);
						fascicoloProtocollo.setChecksum(null);
						fascicoloProtocollo.setCodiceServizio(segnalazione.getServizioCode());
						fascicoloService.saveNuovaRichiesta(fascicoloProtocollo);
					}

					// Invio email a cittadino
					try {
						String servizio2 = messageSource.getMessage("label.segnalazione.fascicolazione.invio", arg, new Locale("it"));
						String subject = messageSource.getMessage("mail.cittadino.communicationSubject", new String[] { servizio2 }, new Locale("it"));

						Map<String, String> emailModel = new HashMap<String, String>();
						emailModel.put("cognome", profiloUtenteCittadino.getCognome());
						emailModel.put("nome", profiloUtenteCittadino.getNome());
						emailModel.put("nomeServizio", servizio2);
						emailModel.put("dataRichiesta", DateUtil.getCurrentDate("dd/MM/yyyy - HH:mm", actionRequest.getLocale()));
						if (numeroProtocollo != null) {
							emailModel.put("protocollo", numeroProtocollo);
						}
						senderHelper.sendCommunicationToCittadino(subject, "velocityTemplate/cittadino.vm", emailModel, null, profiloUtenteCittadino);
					}
					catch (Exception e) {
						log.error("inviaSegnalazione :: " + e.getMessage(), e);
						model.addAttribute("formError", messageSource.getMessage("label.invioemail.ko", null, actionRequest.getLocale()));
					}

					sessionStatus.setComplete();

				}
				catch (Exception e) {
					log.error("inviaSegnalazione :: " + e.getMessage(), e);
					model.addAttribute("formError", messageSource.getMessage("label.fascicolazione.ko", null, actionRequest.getLocale()));
				}
			}
			catch (Exception e) {
				log.error("inviaSegnalazione :: " + e.getMessage(), e);
				model.addAttribute("formError", messageSource.getMessage("label.segnalazione.ko", null, actionRequest.getLocale()));
			}

			response.setRenderParameter("action", "invioNuovaSegnalazione");
		}
		else {
			model.addAttribute("profiloUtente", profiloUtenteCittadino);
			model.addAttribute("segnalazione", segnalazione);
			response.setRenderParameter("action", "confermaInvioNuovaSegnalazione");
		}

	}

	/**
	 * Presenta a video la segnalazione
	 *
	 * @param model
	 * @return l'ID della view per la conferma dell'invio della segnalazione
	 */
	@RenderMapping(params = "action=confermaInvioNuovaSegnalazione")
	public String renderConfermaInvioSegnalazione(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("elencoSegnalazioni");
	}

	/**
	 * Presenta a video la segnalazione
	 *
	 * @param model
	 * @return l'ID della view per l'invio della segnalazione
	 */
	@RenderMapping(params = "action=invioNuovaSegnalazione")
	public String renderInvioSegnalazione(Model model) {
		log.debug("Model = " + model);
		return toLocalViewPath("invioSegnalazione");
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/segnalazione/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "segnalazione/" + viewName;
	}

}