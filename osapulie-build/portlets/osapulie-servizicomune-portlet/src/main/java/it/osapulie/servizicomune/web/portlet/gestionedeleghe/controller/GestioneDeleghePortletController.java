/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionedeleghe.controller;

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

import com.liferay.portal.model.RoleConstants;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Delega;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.web.portlet.util.CommunicationException;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;
import it.osapulie.web.portlet.util.SenderHelper;

/**
 * Portlet controller per la gestione delle deleghe richieste dai cittadini.
 *
 * @author Gianluca Pindinelli
 */
@Controller("gestioneDeleghePortletController")
@RequestMapping("view")
public class GestioneDeleghePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(GestioneDeleghePortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private SenderHelper senderHelper;

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

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access) {
			if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR)) {
				model.addAttribute("comuneIsa", osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa());
			}
			model.addAttribute("delegaList", getListaDeleghe(model, request));
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

	@RenderMapping(params = "action=detailDelega")
	public String detailDelega(@ModelAttribute(value = "delega") Delega delega, PortletRequest request) {
		return toLocalViewPath("detailDelega");
	}

	public List<Delega> getListaDeleghe(Model model, PortletRequest request) {

		log.debug("homePage :: entering method");

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		String nome = request.getParameter("delegante.nome");
		String cognome = request.getParameter("delegante.cognome");
		String cf = request.getParameter("delegante.codifceFiscale");
		String stato = request.getParameter("statoDelegaSelect");

		model.addAttribute("nomeDelegante", nome);
		model.addAttribute("cognomeDelegante", cognome);
		model.addAttribute("cfDelegante", cf);
		model.addAttribute("statoDelegaSelect", stato);

		Boolean statoBoolean = null;
		if ((stato != null) && !stato.equals("")) {
			if (stato.equals(PortletConstants.STATO_DELEGA_ATTIVA)) {
				statoBoolean = true;
			}
			else {
				statoBoolean = false;
			}
		}

		Long id = null;
		if (!helper.userHasRole(osApulieUserDetails.getLiferayUser(), RoleConstants.ADMINISTRATOR) && osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa() != null) {
			id = osApulieUserDetails.getProfiloUtenteCittadino().getGestoreComuneIsa().getId();
		}

		List<Delega> delegaList = delegaService.searchDeleghe(id, cognome, nome, cf, statoBoolean);

		return delegaList;
	}

	@ModelAttribute(value = "delega")
	public Delega getDettaglioDelega(@RequestParam(required = false) String idDelega, PortletRequest request) throws Exception {

		log.debug("homePage :: entering method");

		Delega delega = null;

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		// Controllo che l'utente sia un gestore comunale (!= Superadmin)
		boolean access = accessToService(osApulieUserDetails);

		if (access && (idDelega != null)) {
			long idDelegaLong = Long.parseLong(idDelega);
			delega = delegaService.getDelegaByPk(idDelegaLong);
		}

		return delega;
	}

	@ActionMapping(params = "action=updateDelega")
	public void updateDelega(@RequestParam(required = true) String idDelega, @RequestParam(required = true) String stato, Model model, ActionRequest request, ActionResponse response,
			SessionStatus sessionStatus) {

		log.debug("updateDelega :: entering method");

		boolean attiva = Boolean.parseBoolean(stato);

		Delega delega = delegaService.getDelegaByPk(Long.parseLong(idDelega));
		delega.setAttiva(attiva);
		delega.setDataAttivazione(new Date());

		try {
			delegaService.updateDelega(delega);
		}
		catch (ServiceLayerException e) {
			log.error("updateDelega :: " + e.getMessage(), e);
			response.setRenderParameter("action", "common/defError");
		}

		// Invio email a delegante e delegato
		// Delegante
		String subjectDelegante = messageSource.getMessage("mail.delegante.subject", new String[] { delega.getComuneIsa().getNome() }, request.getLocale());
		Map<String, String> emailModel = new HashMap<String, String>();
		emailModel.put("cognome", delega.getDelegante().getCognome());
		emailModel.put("nome", delega.getDelegante().getNome());
		if (attiva) {
			emailModel.put("stato", "ACCETTATA");
		}
		else {
			emailModel.put("stato", "RIFIUTATA");
		}

		Azienda azienda = delega.getDelegato();
		String delegatoDescrizione = azienda.getRagioneSociale();
		emailModel.put("delegatoDescrizione", delegatoDescrizione);
		String partitaIva = azienda.getPartitaIva();
		emailModel.put("delegatoPiva", partitaIva);

		String delegatoRagioneSociale = "";
		if ((azienda.getRagioneSociale() != null) && !azienda.getRagioneSociale().equals("")) {
			delegatoRagioneSociale += "(" + azienda.getRagioneSociale() + ")";
		}
		emailModel.put("delegatoRagioneSociale", delegatoRagioneSociale);

		List<Servizio> serviziList = delega.getServizi();
		String servizi = "";
		for (Servizio servizio : serviziList) {
			servizi += "<li>" + servizio.getNomeServizio() + "</li>";
		}

		emailModel.put("servizi", servizi);
		emailModel.put("firmaComune", "Comune di " + delega.getComuneIsa().getNome());

		try {
			senderHelper.sendCommunicationToCittadino(subjectDelegante, "velocityTemplate/delegante.vm", emailModel, null, delega.getDelegante());
		}
		catch (CommunicationException e) {
			log.error("updateDelega :: " + e.getMessage(), e);
		}

		// Delegato
		// Invio solo quando si attiva la delega
		if (attiva) {
			String subjectDelegato = messageSource.getMessage("mail.delegato.subject", new String[] { delega.getComuneIsa().getNome() }, request.getLocale());
			emailModel = new HashMap<String, String>();
			emailModel.put("delegato", azienda.getRagioneSociale());
			emailModel.put("delegante", delega.getDelegante().getCognome() + " " + delega.getDelegante().getNome());
			emailModel.put("servizi", servizi);
			emailModel.put("firmaComune", "Comune di " + delega.getComuneIsa().getNome());
			try {

				senderHelper.sendCommunicationToAzienda(subjectDelegato, "velocityTemplate/delegato.vm", emailModel, null, azienda);
			}
			catch (CommunicationException e) {
				log.error("updateDelega :: " + e.getMessage(), e);
			}
		}

		model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", toLocalViewPath("home"));

		sessionStatus.setComplete();

	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/gestionedeleghe/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "gestionedeleghe/" + viewName;
	}
}
