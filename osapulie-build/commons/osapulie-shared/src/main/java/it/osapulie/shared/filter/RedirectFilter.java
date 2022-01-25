package it.osapulie.shared.filter;
/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.servlet.BaseFilter;

import it.osapulie.shared.constant.PortalConstants;

/**
 * Filtro per il redirect in base al parametro settato in querystring..
 *
 * @author Gianluca Pindinelli
 *
 */
public class RedirectFilter extends BaseFilter {

	private static Log log = LogFactoryUtil.getLog(RedirectFilter.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.kernel.servlet.BaseFilter#getLog()
	 */
	@Override
	protected Log getLog() {
		return log;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see com.liferay.portal.kernel.servlet.BaseFilter#processFilter(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	protected void processFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws Exception {

		try {
			String queryString = request.getQueryString();

			if (queryString != null && queryString.contains(PortalConstants.REDIRECT_FILTER_PARAMETER_NAME)) {

				String redirectUrl = queryString.substring(queryString.indexOf(PortalConstants.REDIRECT_FILTER_PARAMETER_NAME) + PortalConstants.REDIRECT_FILTER_PARAMETER_NAME.length() + 1);
				if (log.isDebugEnabled()) {
					log.debug("processFilter :: redirect to : " + redirectUrl);
				}
				response.sendRedirect(redirectUrl);

				return;
			}
		}
		catch (Exception e) {
			log.error("processFilter :: " + e.getMessage(), e);
		}

		processFilter(RedirectFilter.class, request, response, filterChain);

	}
}
