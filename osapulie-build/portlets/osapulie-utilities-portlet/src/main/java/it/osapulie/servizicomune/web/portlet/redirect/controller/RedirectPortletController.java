/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.redirect.controller;

import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.util.PortalUtil;

/**
 * Portlet controller per la portlet di redirect.
 *
 * @author Gianluca Pindinelli
 */
@Controller("redirectPortletController")
@RequestMapping("view")
public class RedirectPortletController {

	private final Logger log = LoggerFactory.getLogger(RedirectPortletController.class);

	@Value("#{applicationProperties['redirectPortlet.return.url.parameter']}")
	private String redirectPortletReturnUrlParameter;

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(PortletRequest portletRequest, Model model) {

		HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
		String url = httpServletRequest.getParameter(redirectPortletReturnUrlParameter);
		if (url != null && !url.isEmpty()) {
			model.addAttribute("redirectUrl", url);
		}

		return toLocalViewPath("home");
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
		return "redirect/" + viewName;
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {

		log.error("onError :: " + e.getMessage(), e);

		return toLocalViewPath("../common/defError");
	}
}
