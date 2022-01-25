/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.interceptor;

import javax.inject.Inject;
import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.portlet.handler.HandlerInterceptorAdapter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.portal.util.PortalUtil;

import it.osapulie.domain.ComuneISA;
import it.osapulie.service.ComuneISAService;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.web.portlet.util.PortletConstants;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 * Interceptor per editare il Comune preferito dell'utente se è presente come parametro in
 * querystring.
 *
 * @author Gianluca Pindinelli
 *
 */
public class EditFavoriteComuneHandlerInterceptorAdapter extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(EditFavoriteComuneHandlerInterceptorAdapter.class.getName());

	@Inject
	private PortletHelper helper;

	@Inject
	private ComuneISAService comuneISAService;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.portlet.handler.HandlerInterceptorAdapter#preHandle(javax.portlet.
	 * PortletRequest, javax.portlet.PortletResponse, java.lang.Object)
	 */
	@Override
	protected boolean preHandle(PortletRequest request, PortletResponse response, Object handler) throws Exception {

		HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));

		String comuneIsaParameter = httpServletRequest.getParameter(PortletConstants.COMUNE_ISA_PARAMETER_NAME);

		if (comuneIsaParameter != null && !comuneIsaParameter.trim().equals("")) {
			UserPreferences userPreferences = helper.getUserPreferences(request);
			if (!comuneIsaParameter.equals(userPreferences.getCodiceIstatComune())) {
				try {
					ComuneISA comuneByCodiceIstat = comuneISAService.getComuneByCodiceIstat(comuneIsaParameter);
					if (comuneByCodiceIstat != null) {
						userPreferences.setIdComuneIsa(comuneByCodiceIstat.getId());
						userPreferences.setCodiceIstatComune(comuneByCodiceIstat.getCodiceIstat());
						userPreferences.setUriServizioGateway(comuneByCodiceIstat.getUriServizioGateway());
						userPreferences.setNomeComune(comuneByCodiceIstat.getNome());
					}
				}
				catch (ServiceLayerException e) {
					log.error("preHandle :: " + e.getMessage(), e);
				}
			}
		}

		return super.preHandle(request, response, handler);
	}
}
