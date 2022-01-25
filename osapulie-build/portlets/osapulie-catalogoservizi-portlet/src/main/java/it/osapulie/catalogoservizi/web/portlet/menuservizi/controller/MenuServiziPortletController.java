/*******************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.catalogoservizi.web.portlet.menuservizi.controller;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.servizi.AreaTematica;
import it.osapulie.domain.servizi.Tipologia;
import it.osapulie.service.AreaTematicaService;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.TipologiaService;

import java.util.List;

import javax.inject.Inject;
import javax.portlet.PortletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

/**
 * Portlet controller per la visualizzazione del menu di raggruppamento dei servizi di front end.
 *
 * @author Gianluca Pindinelli
 */
@Controller("menuServiziPortletController")
@RequestMapping("view")
public class MenuServiziPortletController {

	private final Logger log = LoggerFactory.getLogger(MenuServiziPortletController.class);

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private TipologiaService tipologiaService;

	@Inject
	private AreaTematicaService areaTematicaService;

	public static final String SERVIZI_PUBLIC_PAGE = "/servizi";

	/**
	 * Default entry quando la portlet viene visualizzata per la prima volta.
	 *
	 * @param model il view model da popolare
	 * @return il view ID da renderizzare
	 */
	@RenderMapping
	public String homePage(Model model, PortletRequest request) {

		String currentUrl = PortalUtil.getCurrentURL(request);

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		if (themeDisplay.getLayout().isPublicLayout()) {
			currentUrl = SERVIZI_PUBLIC_PAGE;
		}

		if (currentUrl.contains("?")) {
			currentUrl = currentUrl.substring(0, currentUrl.indexOf("?"));
		}

		model.addAttribute("currentUrl", currentUrl);

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "tipiServizio")
	public List<Tipologia> getTipiServizio() {

		List<Tipologia> tipiServizio = tipologiaService.getAllTipologia();

		return tipiServizio;
	}

	@ModelAttribute(value = "areeTematiche")
	public List<AreaTematica> getAreeTematiche() {

		List<AreaTematica> areeTematiche = areaTematicaService.getAllAreaTematica();

		return areeTematiche;
	}

	@ModelAttribute(value = "comuni")
	public List<ComuneISA> getComuni(PortletRequest request) {

		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		List<ComuneISA> allComuni = null;
		if (themeDisplay.getLayout().isPublicLayout()) {
			allComuni = comuneISAService.getComuniAttivi();
		}

		return allComuni;
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/menuservizi/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "menuservizi/" + viewName;
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {

		log.error("onError :: " + e.getMessage(), e);

		return toLocalViewPath("../common/defError");
	}
}
