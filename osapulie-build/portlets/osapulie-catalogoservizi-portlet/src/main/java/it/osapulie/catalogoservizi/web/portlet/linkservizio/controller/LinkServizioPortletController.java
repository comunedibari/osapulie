/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.catalogoservizi.web.portlet.linkservizio.controller;

import java.util.Locale;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.bind.annotation.RenderMapping;

import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Layout;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;

import it.osapulie.catalogoservizi.web.utils.PortletConstants;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ComuneISAServizioService;
import it.osapulie.service.ServizioService;
import it.osapulie.shared.constant.PortalConstants;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.SPID;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Portlet controller per la visualizzazione nel front end del link di accesso ad un servizio.
 *
 * @author Gianluca Pindinelli
 */
@Controller("linkServizioPortletController")
@RequestMapping("view")
public class LinkServizioPortletController {

	private final Logger log = LoggerFactory.getLogger(LinkServizioPortletController.class);

	@Autowired
	public ResourceBundleMessageSource messageSource;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ComuneISAService comuneISAService;

	@Inject
	private ComuneISAServizioService comuneISAServizioService;

	@Inject
	private PortletHelper helper;

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
		String comune = httpServletRequest.getParameter(PortletConstants.PARAMETRO_ID_COMUNE);

		if (comune != null && !comune.equals("")) {
			ComuneISA comuneByPk = comuneISAService.getComuneByPk(Long.parseLong(comune));
			model.addAttribute("comuneSelezionato", comuneByPk);
		}

		return toLocalViewPath("home");
	}

	@ModelAttribute(value = "servizioUrl")
	public String getServizioUrl(PortletRequest portletRequest) {

		String servizioUrl = "";

		ThemeDisplay themeDisplay = (ThemeDisplay) portletRequest.getAttribute(WebKeys.THEME_DISPLAY);

		Layout layout = themeDisplay.getLayout();

		String pageUrl = null;

		Servizio servizio = null;
		if (layout.getFriendlyURL() != null && !layout.getFriendlyURL().equals("")) {
			pageUrl = layout.getFriendlyURL();
			servizio = servizioService.getServizioByUriScheda(pageUrl);

			if (servizio != null) {
				Locale defaultLocale = LocaleUtil.getDefault();
				servizioUrl = servizio.getUri();
				String language = themeDisplay.getLocale().getLanguage();
				if (!defaultLocale.getLanguage().equals(language)) {
					servizioUrl = "/" + language + servizioUrl;
				}
			}

			// Check SPID authentication level
			try {
				AuthenticationChannel authenticationChannel = AuthenticationChannel.SPID;
				if (helper.isAuthenticationChannelEnable(authenticationChannel)) {

					HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(portletRequest));
					String comune = httpServletRequest.getParameter(PortletConstants.PARAMETRO_ID_COMUNE);

					if (comune != null && !comune.equals("") && servizio != null) {
						ComuneISAServizio comuneISAServizio = comuneISAServizioService.getComuneISAServizio(Long.parseLong(comune), servizio.getId());
						Integer livelloAutenticazione = comuneISAServizio.getLivelloAutenticazione();

						if (livelloAutenticazione != null) {
							servizioUrl = SPID.SERVICES_LEVEL_PATH_PATTERN.getName() + livelloAutenticazione.toString() + "?" + PortalConstants.REDIRECT_FILTER_PARAMETER_NAME + "=" + servizioUrl;
						}
					}
				}
			}
			catch (Exception e) {
				log.error("getServizioUrl :: " + e.getMessage(), e);
			}
		}

		return servizioUrl;
	}

	/**
	 * Utility method per calcolare e restituire il view ID all'interno della corretta alberatura di
	 * sottodirectory delle JSP: in un certo senso si tratta del namespace delle view locali a
	 * questo controller. Ad esempio: <code> toLocalViewPath( "home" ) </code> restituirà <code>
	 * "/linkservizio/home" </code>
	 *
	 * @param viewName l'ID della view locale
	 * @return il view ID completo del path a cui la vista appartiene
	 */
	private static String toLocalViewPath(String viewName) {
		return "linkservizio/" + viewName;
	}

	@ExceptionHandler(Exception.class)
	public String onError(Exception e) {

		log.error("onError :: " + e.getMessage(), e);

		return toLocalViewPath("../common/defError");
	}
}
