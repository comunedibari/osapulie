/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.catalogoservizi.web.portlet.infoservizio.controller;

import javax.inject.Inject;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;

import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione delle info di un servizio nel front end.
 *
 * @author Gianluca Pindinelli
 */
@Controller("infoServizioPortletController")
@RequestMapping("view")
public class InfoServizioPortletController {

	private final Logger log = LoggerFactory.getLogger(InfoServizioPortletController.class);

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ServizioService servizioService;

	@Inject
	private PortletHelper helper;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage() {
		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "servizio")
	public Servizio getServizio(PortletRequest portletRequest) {

		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String pageUrl = null;

		Servizio servizio = null;
		if (layout.getFriendlyURL() != null && !layout.getFriendlyURL().equals("")) {
			pageUrl = layout.getFriendlyURL();
			servizio = servizioService.getServizioByUriScheda(pageUrl);
		}

		return servizio;
	}

	@ModelAttribute("authenticationChannels")
	public AuthenticationChannel[] authenticationChannels() {
		return helper.getScenario3AuthenticationChannels();
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
		return "infoservizio/" + viewName;
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {

		log.error("onError :: " + e.getMessage(), e);

		return toLocalViewPath("../common/defError");
	}
}
