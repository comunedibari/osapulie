/*******************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.servizicomune.web.portlet.logocomune.controller;

import java.io.IOException;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import it.osapulie.domain.ComuneISA;
import it.osapulie.service.ComuneISAService;
import it.osapulie.servizicomune.web.portlet.BaseController;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione del logo comunale.
 *
 * @author Gianluca Pindinelli
 */
@Controller("logoComunePortletController")
@RequestMapping("view")
public class LogoComunePortletController extends BaseController {

	private final Logger log = LoggerFactory.getLogger(LogoComunePortletController.class);

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {

		UserPreferences userPreferences = helper.getUserPreferences(request);
		if (userPreferences != null) {
			Long idComuneIsa = userPreferences.getIdComuneIsa();
			String nomeComune = userPreferences.getNomeComune();

			model.addAttribute("idComuneIsa", idComuneIsa);
			if (nomeComune != null && !nomeComune.equals("-")) {
				model.addAttribute("nomeComune", new String[] { nomeComune });
			}
		}

		return toLocalViewPath("home");
	}

	@ResourceMapping("showLogo")
	public void showLogo(@RequestParam("id") Long id, ResourceRequest request, ResourceResponse response) {

		if (id != null) {
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
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/logocomune/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "logocomune/" + viewName;
	}
}
