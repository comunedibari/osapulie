/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */
package it.osapulie.servizicomune.web.portlet.gestionecomuni.controller;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.RenderRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.liferay.portal.kernel.util.PrefsPropsUtil;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ProfiloUtenteService;
import it.osapulie.servizi.web.ws.types.Servizio;
import it.osapulie.servizicomune.service.ServiziAttiviService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.servizicomune.web.utils.PortletUtils;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.RoleConstants;

/**
 * Portlet per gestire l'elenco dei comuni ed il loro dettaglio.
 *
 * @author Mario Scalas, Gianluca Pindinelli
 */
@Controller("gestioneComuniPortletController")
@RequestMapping("view")
@SessionAttributes({ "elencoComuni", "entity" })
public class GestioneComuniPortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneComuniPortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ProfiloUtenteService profiloUtenteService;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	@Inject
	private ServiziAttiviService serviziAttiviService;

	@Inject
	@Named("comuneValidator")
	private Validator validator;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String mostraPaginaElenco(@RequestParam(value = "page", required = false) Integer page, RenderRequest request, Model model) {
		return renderHome(request, model);
	}

	@ActionMapping(params = "action=edit")
	public void onEdit(@RequestParam("id") Long id, Model model, ActionResponse response) {
		ComuneISA comuneISA = comuneISAService.getComuneByPk(id);
		if (comuneISA == null) {
			log.warn(String.format("onEdit( %d ): Entity not found", id));
			return;
		}

		if (comuneISA.getGestoreComune() == null) {
			ProfiloUtenteCittadino gestoreComune = new ProfiloUtenteCittadino();
			comuneISA.setGestoreComune(gestoreComune);
		}

		model.addAttribute("entity", comuneISA);

		response.setRenderParameter("action", "renderEditForm");
	}

	@ActionMapping(params = "action=new")
	public void onNew(Model model, ActionResponse response) {
		ComuneISA comuneISA = new ComuneISA();
		ProfiloUtenteCittadino gestoreComune = new ProfiloUtenteCittadino();
		comuneISA.setGestoreComune(gestoreComune);

		model.addAttribute("entity", comuneISA);

		response.setRenderParameter("action", "renderEditForm");
	}

	@ActionMapping(params = "action=delete")
	public void onDelete(@RequestParam("id") Long id, Model model, ActionRequest actionRequest, ActionResponse response) {
		ComuneISA comuneISA = comuneISAService.getComuneByPk(id);
		if (comuneISA == null) {
			log.warn(String.format("onEdit( %d ): Entity not found", id));
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, actionRequest.getLocale()));
			return;
		}

		comuneISAService.deleteComuneISA(id);
		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
		model.addAttribute("elencoComuni", comuneISAService.getComuniAttivi());
	}

	/**
	 * Aggiorna il catalogo servizi del comune, conttattando la PDD applicativa corrispondente.
	 *
	 * @param id
	 * @param model
	 * @param actionRequest
	 * @param response
	 */
	@ActionMapping(params = "action=updateCatalogoServizi")
	public void updateCatalogoServizi(@RequestParam("id") Long id, Model model, ActionRequest actionRequest, ActionResponse response) {

		ComuneISA comuneISA = comuneISAService.getComuneByPk(id);
		if (comuneISA == null) {
			log.warn(String.format("onEdit( %d ): Entity not found", id));
			model.addAttribute("formError", messageSource.getMessage("error.campiObbligatori.required", null, actionRequest.getLocale()));
			return;
		}

		try {
			log.info("updateCatalogoServizi :: INIZIO aggiornamento relazioni Comuni <-> Servizi per " + comuneISA.getNome() + "...");

			log.info("updateCatalogoServizi :: caricamento servizi attivi per: " + comuneISA.getNome() + " (" + comuneISA.getUriServizioGateway() + ")");

			List<Servizio> serviziAttiviComuneISA = serviziAttiviService.getServiziAttiviComuneISA(comuneISA);
			serviziAttiviService.updateServiziAttiviComuneISA(serviziAttiviComuneISA, comuneISA);

			int totalServices = serviziAttiviComuneISA.size();
			int activeServices = PortletUtils.countActiveServices(serviziAttiviComuneISA);
			int inactiveServices = totalServices - activeServices;
			int strongAuthenticationServices = PortletUtils.countStrongAutenticationServices(serviziAttiviComuneISA);

			log.info("updateCatalogoServizi :: aggiornamento relazioni Comuni <-> Servizi per " + comuneISA.getNome() + " TERMINATO (totali: " + totalServices + ", attivi: " + activeServices
					+ ", non attivi: " + inactiveServices + ", con autenticazione forte: " + strongAuthenticationServices + ")");

			model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
		}
		catch (Exception e) {
			log.error("updateCatalogoServizi :: aggiornamento relazioni Comuni <-> Servizi per " + comuneISA.getNome() + " FALLITO", e);
			model.addAttribute("formError", messageSource.getMessage("gestoreComune.aggiornaCatalogoServizi.error", new String[] { comuneISA.getNome(), e.getMessage() }, actionRequest.getLocale()));
		}

		model.addAttribute("elencoComuni", comuneISAService.getComuniAttivi());

		response.setRenderParameter("action", "renderHome");
	}

	@ResourceMapping("showLogo")
	public void showLogo(@RequestParam("id") Long id, ResourceRequest request, ResourceResponse response) {
		ComuneISA comuneISA = comuneISAService.getComuneByPk(id);
		byte[] logo = comuneISA.getLogo();
		if (logo != null && logo.length > 0) {
			response.setContentType(MediaType.IMAGE_GIF.getType() + "," + MediaType.IMAGE_PNG.getType() + "," + MediaType.IMAGE_JPEG.getType());
			try {
				response.getPortletOutputStream().write(logo);
				response.getPortletOutputStream().flush();
				response.getPortletOutputStream().close();
			}
			catch (IOException e) {
				log.error("showLogo :: " + e.getMessage(), e);
			}
		}
	}

	@ActionMapping(params = "action=deleteLogo")
	public void deleteLogo(@ModelAttribute("entity") ComuneISA entity, Model model, ActionRequest actionRequest, ActionResponse response) {

		// Eliminazione logo
		entity.setLogo(null);
		comuneISAService.saveComuneISA(entity);

		response.setRenderParameter("action", "renderEditForm");
	}

	@ActionMapping(params = "action=save")
	public void onSaveEntity(@ModelAttribute("entity") ComuneISA entity, BindingResult bindingResult, Model model, ActionRequest actionRequest, ActionResponse response) {

		try {
			validator.validate(entity, bindingResult);

			if (bindingResult.hasErrors()) {
				response.setRenderParameter("action", "renderEditForm");
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, actionRequest.getLocale()));
			}
			else {
				// Caricamento utente gestore comune a partire dal codice fiscale
				if ((entity.getGestoreComune() != null) && (entity.getGestoreComune().getCodiceFiscale() != null) && !entity.getGestoreComune().getCodiceFiscale().equals("")) {
					ProfiloUtenteCittadino gestoreComune = null;
					try {
						gestoreComune = profiloUtenteService.getProfiloUtenteCittadinoByCf(entity.getGestoreComune().getCodiceFiscale());
						OSApulieUserDetails osApulieUserDetails = profilazioneUtenteService.getById(String.valueOf(gestoreComune.getId()));
						// Controllo ruolo
						if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.GESTORE_COMUNE)) {
							gestoreComune = null;
						}
					}
					catch (Exception e) {
					}

					if (gestoreComune != null) {
						entity.setGestoreComune(gestoreComune);
					}
					// Utente non trovato -> errore
					else {
						FieldError fieldError = new FieldError("entity", "gestoreComune.codiceFiscale", messageSource.getMessage("gestoreComune.field.notFound", null, actionRequest.getLocale()));
						bindingResult.addError(fieldError);
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, actionRequest.getLocale()));
						response.setRenderParameter("action", "renderEditForm");
						return;
					}

				}
				else {
					entity.setGestoreComune(null);
				}

				// Salvataggio logo
				if (entity.getLogoFile().getBytes() != null && entity.getLogoFile().getBytes().length > 0) {
					entity.setLogo(entity.getLogoFile().getBytes());
				}

				comuneISAService.saveComuneISA(entity);
				model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, actionRequest.getLocale()));
			}
		}
		catch (Throwable e) {
			log.error("onSaveEntity :: " + e.getMessage(), e);
		}
	}

	// Nota: Il parametro model serve anche se non ce lo filiamo perchè altrimenti L'id della entity
	// viene passato come "null" anche nel caso di salvataggio di una entity già esistente
	@RenderMapping(params = "action=renderEditForm")
	public String renderEditForm(PortletRequest portletRequest, Model model) {
		log.info(String.format("renderEditForm( %s )", model.asMap().get("entity")));

		// Controllo impostazioni portale ISA
		String pecHostIsa = null;
		String emailHostIsa = null;
		boolean pecEnableBoolean = false;
		try {
			String pecEnable = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.enable");
			pecEnableBoolean = Boolean.parseBoolean(pecEnable);
			emailHostIsa = PrefsPropsUtil.getString("mail.session.mail.smtp.host");
			pecHostIsa = PrefsPropsUtil.getString("pec.mail.session.mail.smtp.host");
		}
		catch (Exception e) {
			log.error("renderEditForm :: " + e.getMessage(), e);
		}

		model.addAttribute("emailHostIsa", emailHostIsa);

		if (pecEnableBoolean) {
			model.addAttribute("pecHostIsa", pecHostIsa);
		}
		else {
			model.addAttribute("pecHostIsa", messageSource.getMessage("message.label.noPecMailServerSetted", null, portletRequest.getLocale()));
		}

		return "gestionecomuni/editComune";
	}

	@RenderMapping(params = "action=renderHome")
	public String renderHome(RenderRequest request, Model model) {

		List<ComuneISA> comuneList = comuneISAService.getAllComuni();
		model.addAttribute("elencoComuni", comuneList);

		return "gestionecomuni/elencoComuni";
	}
}
