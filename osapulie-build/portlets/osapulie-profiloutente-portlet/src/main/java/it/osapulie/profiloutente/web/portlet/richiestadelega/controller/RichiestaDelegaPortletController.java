/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.web.portlet.richiestadelega.controller;

import java.util.ArrayList;
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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.portlet.bind.annotation.ActionMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.profiloutente.model.view.DelegaModel;
import it.osapulie.profiloutente.utils.DelegaModelUtil;
import it.osapulie.profiloutente.web.portlet.richiestadelega.validator.RichiestaDelegaValidator;
import it.osapulie.service.AziendaService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.DelegaService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.ServizioService;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la richiesta di delega da parte dell'utente cittadino.
 *
 * @author Gianluca Pindinelli
 */
@Controller("richiestaDelegaPortletController")
@RequestMapping("view")
public class RichiestaDelegaPortletController {

	private final Logger log = LoggerFactory.getLogger(RichiestaDelegaPortletController.class);
	@Inject
	private PortletHelper helper;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	@Inject
	private ServizioService servizioService;

	@Inject
	private DelegaService delegaService;

	@Inject
	private ComuneISAService comuneISAService;

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Autowired
	@Qualifier("richiestaDelegaValidator")
	private RichiestaDelegaValidator richiestaDelegaValidator;

	@Autowired
	private AziendaService aziendaService;

	@ExceptionHandler(Exception.class)
	public String handleNullPointerExceptions(Exception e) {

		log.error("handleNullPointerExceptions :: " + e.getMessage(), e);

		return "common/defError";
	}

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {
		model.addAttribute("delegaList", getListaDeleghe(request));
		return toLocalViewPath("home");
	}

	private List<Delega> getListaDeleghe(PortletRequest request) {

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		try {
			osApulieUserDetails = profilazioneUtenteService.getById(osApulieUserDetails.getUsername());
		}
		catch (ProfilazioneUtenteException e) {
			log.error("getListaDelegati :: " + e.getMessage(), e);
		}
		List<Delega> delegaList = null;
		if (osApulieUserDetails.getProfiloUtenteCittadino().getComuneIsa() != null) {
			delegaList = osApulieUserDetails.getProfiloUtenteCittadino().getDelegheDelegati();
			if (delegaList != null) {
				for (Delega delega : delegaList) {
					delega.getDelegato();
				}
			}
		}

		return delegaList;
	}

	@RenderMapping(params = "action=editDelega")
	public String editDelegaPage(Model model, PortletRequest request) {

		model.addAttribute("comuniIsaList", getComuniIsaList());

		List<Azienda> profiliUtenteProfessionistaAttivi = aziendaService.getAziendeAttive();
		model.addAttribute("profiliUtenteProfessionistaAttivi", profiliUtenteProfessionistaAttivi);

		return toLocalViewPath("editDelega");
	}

	@ActionMapping(params = "action=saveDelega")
	public void saveDelega(@ModelAttribute("delega") DelegaModel delegaModel, BindingResult bindingResult, @RequestParam(required = false, value = "cambio") String cambio, Model model,
			ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("saveDelega :: entering method");

		// Cambio comune
		if (cambio != null) {
			Delega delega = null;

			if (delegaModel.getIdDelega() != 0) {
				delega = delegaService.getDelegaByPk(delegaModel.getIdDelega());
			}

			if (delega == null) {
				delega = new Delega();
				ProfiloUtenteCittadino profiloUtenteCittadino = new ProfiloUtenteCittadino();
				Azienda azienda = new Azienda();
				delega.setDelegante(profiloUtenteCittadino);
				delega.setDelegato(azienda);
			}

			setDelegaModel(request, delega, delegaModel);

			response.setRenderParameter("action", "editDelega");
			return;
		}
		// Salvataggio
		else {

			richiestaDelegaValidator.validate(delegaModel, bindingResult);
			if (bindingResult.hasErrors()) {
				// Caricamento delegaModel
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "editDelega");
				return;
			}

			// Controllo PIVA (esistenza delegato nel sistema)
			String piva = delegaModel.getPiva();

			Azienda delegato = aziendaService.getAziendaByPiva(piva);
			if (delegato == null) {
				FieldError error = new FieldError("delega", "piva", messageSource.getMessage("error.delegatoNotFound", null, request.getLocale()));
				bindingResult.addError(error);
				model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
				response.setRenderParameter("action", "editDelega");
				return;
			}

			List<String> serviziAssociatiStrings = delegaModel.getServiziAssociatiStrings();

			// Caricamento utente attuale (delegante)
			OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

			ProfiloUtenteCittadino delegante = osApulieUserDetails.getProfiloUtenteCittadino();
			Delega delega = null;
			// Update delega
			if (delegaModel.getIdDelega() != 0) {
				delega = delegaService.getDelegaByPk(delegaModel.getIdDelega());

				List<Delega> delegaList = delegante.getDelegheDelegati();
				for (Delega delega2 : delegaList) {
					// Controllo che non sia possibile modificare il comune di una delega se ne
					// esiste un'altra che contiene la stessa PIVA
					if (delega.getId().longValue() != delega2.getId().longValue() && delegaModel.getIdComuneIsa() == delega2.getComuneIsa().getId()
							&& piva.equals(delega2.getDelegato().getPartitaIva())) {
						FieldError error = new FieldError("delega", "piva", messageSource.getMessage("error.delegatoJustAdded", null, request.getLocale()));
						bindingResult.addError(error);
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						response.setRenderParameter("action", "editDelega");
						return;
					}
				}
			}
			// New Delega
			else {

				delega = new Delega();
				delega.setIdDelegante(delegante.getId());
				delega.setIdDelegato(delegato.getId());
				delega.setDelegante(delegante);
				delega.setDelegato(delegato);
				delega.setDataRichiesta(new Date());

				List<Delega> delegaList = delegante.getDelegheDelegati();
				for (Delega delega2 : delegaList) {
					// Controllo esistenza stessa delega per stesso comune
					if (delega.getIdDelegato() == delega2.getIdDelegato() && delegaModel.getIdComuneIsa() == delega2.getComuneIsa().getId()) {
						FieldError error = new FieldError("delega", "piva", messageSource.getMessage("error.delegatoJustAdded", null, request.getLocale()));
						bindingResult.addError(error);
						model.addAttribute("formError", messageSource.getMessage("error.verificareDatiInseriti", null, request.getLocale()));
						response.setRenderParameter("action", "editDelega");
						return;
					}
				}
			}

			// Setto il Comune ISA
			ComuneISA comuneISA = comuneISAService.getComuneByPk(delegaModel.getIdComuneIsa());
			delega.setComuneIsa(comuneISA);

			List<Servizio> serviziAssociati = new ArrayList<Servizio>();

			// Aggiunta servizi
			if (serviziAssociatiStrings != null) {
				for (String string : serviziAssociatiStrings) {
					Servizio servizio = servizioService.getServizioById(Long.parseLong(string));
					serviziAssociati.add(servizio);
				}
			}

			delega.setServizi(serviziAssociati);

			// Setto lo stato in NON ATTIVA
			delega.setAttiva(false);

			try {
				delegaService.saveDelega(delega);
			}
			catch (ServiceLayerException e) {
				log.error("saveDelega :: " + e.getMessage(), e);
				response.setRenderParameter("action", "editDelega");
				return;
			}

			model.addAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
			response.setRenderParameter("action", toLocalViewPath("home"));

			sessionStatus.setComplete();
		}

	}

	@ActionMapping(params = "action=deleteDelega")
	public void deleteDelega(@RequestParam(required = true) String idDelega, ActionRequest request, ActionResponse response, SessionStatus sessionStatus) {

		log.debug("deleteDelega :: entering method");

		// Controllo eliminazione delega appartenente all'utente
		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();

		ProfiloUtenteCittadino delegante = osApulieUserDetails.getProfiloUtenteCittadino();

		List<Delega> delegaList = delegante.getDelegheDelegati();
		for (Delega delega : delegaList) {
			if (delega.getId() == Long.parseLong(idDelega)) {
				delegaService.deleteDelega(delega.getId());
			}
		}

		request.setAttribute("message", messageSource.getMessage("message.label.operazioneEseguitaCorrettamente", null, request.getLocale()));
		response.setRenderParameter("action", toLocalViewPath("home"));
		sessionStatus.setComplete();
	}

	private List<ComuneISA> getComuniIsaList() {

		List<ComuneISA> comuniList = null;

		try {
			comuniList = comuneISAService.getAllComuni();
		}
		catch (ServiceLayerException e) {
			log.warn("getComuniUtenteModel :: " + e.getMessage());
		}

		return comuniList;
	}

	@ModelAttribute(value = "delega")
	public DelegaModel getDelega(@RequestParam(required = false) String idDelega, PortletRequest request) {

		Delega delega = null;

		if (idDelega != null) {
			delega = delegaService.getDelegaByPk(Long.parseLong(idDelega));
		}

		if (delega == null) {
			delega = new Delega();
			ProfiloUtenteCittadino profiloUtenteCittadino = new ProfiloUtenteCittadino();
			Azienda azienda = new Azienda();
			delega.setDelegante(profiloUtenteCittadino);
			delega.setDelegato(azienda);
		}

		OSApulieUserDetails osApulieUserDetails = helper.getOsApulieUserDetails();
		delega.setComuneIsa(osApulieUserDetails.getProfiloUtenteCittadino().getComuneIsa());

		// Distinzione tra servizi disponibili ed associati
		DelegaModel delegaModel = DelegaModelUtil.getDelegaModelByDelega(delega);

		if (delegaModel.getIdDelega() == 0) {
			delegaModel.setIdComuneIsa(osApulieUserDetails.getProfiloUtenteCittadino().getComuneIsa().getId());
		}

		if (delegaModel != null) {
			setDelegaModel(request, delega, delegaModel);
		}
		return delegaModel;

	}

	/**
	 * @param request
	 * @param delega
	 * @param delegaModel
	 */
	private void setDelegaModel(PortletRequest request, Delega delega, DelegaModel delegaModel) {

		// Caricamento servizi: filtro in base al comune di appartenenza (chiamata al WS)
		List<Servizio> serviziComuneIsaList = getServiziAttiviList(request, delegaModel.getIdComuneIsa());
		request.setAttribute("serviziComuneIsaList", serviziComuneIsaList);

		List<Servizio> serviziDelegaList = delega.getServizi();
		// Creazione mappe
		// Mappa servizi attualmente associati alla delega
		Map<Long, Servizio> serviziDelegaMap = new HashMap<Long, Servizio>();
		if (serviziDelegaList != null) {
			for (Servizio servizio : serviziDelegaList) {
				serviziDelegaMap.put(servizio.getId(), servizio);
			}
		}

		request.setAttribute("serviziDelegaMap", serviziDelegaMap);

	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/richiestadelega/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "richiestadelega/" + viewName;
	}

	/**
	 * @param request
	 * @return
	 */
	private List<Servizio> getServiziAttiviList(PortletRequest request, long idComuneIsa) {

		List<Servizio> elencoServizi = null;
		try {
			elencoServizi = servizioService.getServiziAttiviForDelega();
		}
		catch (Exception e) {
			log.error("getListaServizi :: " + e.getMessage(), e);
		}
		return elencoServizi;
	}
}
