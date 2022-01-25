/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.web.portlet.statoutente.controller;

import java.security.cert.X509Certificate;
import java.util.List;

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

import it.osapulie.domain.Azienda;
import it.osapulie.domain.Delega;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.infrastructure.security.ProfilazioneUtenteException;
import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.profiloutente.model.view.ProfiloUtenteModel;
import it.osapulie.profiloutente.utils.ProfiloUtenteModelUtil;
import it.osapulie.service.DelegaService;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione dello stato utente.
 *
 * @author Gianluca Pindinelli
 */
@Controller("statoUtentePortletController")
@RequestMapping("view")
public class StatoUtentePortletController {

	private static Logger log = LoggerFactory.getLogger(StatoUtentePortletController.class);
	@Inject
	private PortletHelper helper;

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

			UserPreferences userPreferences = helper.getUserPreferences(request);
			if (Validator.isNotNull(osApulieUserDetails)) {
				profiloUtenteModel = ProfiloUtenteModelUtil.getProfiloUtenteFromOSApulieUserDetails(osApulieUserDetails);
				profiloUtenteModel.setComuneIsa(userPreferences.getIdComuneIsa());
				profiloUtenteModel.setComuneIsaString(userPreferences.getNomeComune());
			}

			// Verifica visualizzazione sezione profilo
			List<Azienda> aziende = osApulieUserDetails.getAziende();
			if (aziende != null && !aziende.isEmpty()) {
				profiloUtenteModel.setProfiloEnable(true);
			}

			if (userPreferences.getPartitaIvaServizio() != null && !userPreferences.getPartitaIvaServizio().equals("")) {
				profiloUtenteModel.setProfiloAzienda(true);
			}

			if (userPreferences.getIdDelega() != null && userPreferences.getIdDelega() != 0) {
				profiloUtenteModel.setProfiloDelega(true);
				Delega delegaByPk = delegaService.getDelegaByPk(userPreferences.getIdDelega());
				profiloUtenteModel.setDelega(delegaByPk);
			}
		}
		catch (Exception e) {
			log.warn("getProfiloUtenteModel :: " + e.getMessage(), e);
		}

		return profiloUtenteModel;
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
		return "statoutente/" + viewName;
	}
}
