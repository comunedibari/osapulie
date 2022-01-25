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
import com.liferay.portal.kernel.util.PrefsPropsUtil;

import it.osapulie.shared.constant.PortalConstants;
import it.osapulie.shared.enumeration.AuthenticationChannel;
import it.osapulie.shared.enumeration.SPID;

/**
 * Filtro per la gestione dell'SSO mediante SPID.
 *
 * @author Gianluca Pindinelli
 *
 */
public class SSOSpidFilter extends BaseFilter {

	private static Log log = LogFactoryUtil.getLog(SSOSpidFilter.class);

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
			String pathInfo = request.getPathInfo();
			String queryString = request.getQueryString();
			boolean spidLogout = true;
			if (queryString != null && queryString.contains("from=spidLogout")) {
				spidLogout = false;
			}

			if (isAuthenticationChannelEnable(AuthenticationChannel.SPID) && pathInfo.indexOf("/portal/logout") != -1 && spidLogout) {

				// Controllo se la url di logout è destinata al redirect verso un servizio che
				// richiede autenticazione con livello superiore
				String serviceCallbackUrl = null;
				if (queryString != null && queryString.contains(SPID.SERVICES_RETURN_URL_PARAMETER.toString())) {
					serviceCallbackUrl = queryString.substring(queryString.indexOf(SPID.SERVICES_RETURN_URL_PARAMETER.toString()) + SPID.SERVICES_RETURN_URL_PARAMETER.toString().length() + 1);
				}

				String url = PrefsPropsUtil.getString(PortalConstants.SCENARIO_DEPLOYMENT_SPID_SINGLELOGOUTSERVICE_PORTAL_EXT_PROP);
				if (serviceCallbackUrl != null) {
					// Attenzione: da avviso n° 6 SPID non si dovrebbe chiudere la sessione di
					// autenticazione, al momento non esiste una soluzione alternativa
					url += "?" + SPID.SHIBBOLETH_SLO_RETURN_URL_PARAMETER.toString() + "=" + getBaseUrl(request) + serviceCallbackUrl;
				}
				else {
					url += "?" + SPID.SHIBBOLETH_SLO_RETURN_URL_PARAMETER.toString() + "=" + getBaseUrl(request) + "/c/portal/logout" + "?from=spidLogout";
				}
				if (log.isDebugEnabled()) {
					log.debug("processFilter :: redirect to : " + url);
				}
				request.getSession().invalidate();
				response.sendRedirect(url);

				return;
			}
		}
		catch (Exception e) {
			log.error("processFilter :: " + e.getMessage(), e);
		}

		processFilter(SSOSpidFilter.class, request, response, filterChain);

	}

	private String getBaseUrl(HttpServletRequest request) {
		String scheme = "https://";
		String serverName = request.getServerName();
		String serverPort = (request.getServerPort() == 80) ? "" : ":" + request.getServerPort();
		String contextPath = request.getContextPath();
		return scheme + serverName + serverPort + contextPath;
	}

	/**
	 * Verifica se il canale di autenticazione passato in input è attivo o meno.
	 *
	 * @param authenticationChannel
	 * @return
	 */
	private boolean isAuthenticationChannelEnable(AuthenticationChannel authenticationChannel) {

		AuthenticationChannel[] authenticationChannels = null;

		try {
			String scenario3AuthenticationChannelsString = PrefsPropsUtil.getString(PortalConstants.SCENARIO_DEPLOYMENT_AUTHENTICATION_CHANNELS_PORTAL_EXT_PROP);
			if (scenario3AuthenticationChannelsString != null) {
				String[] scenario3AuthenticationChannelsStrings = scenario3AuthenticationChannelsString.split(",");
				authenticationChannels = new AuthenticationChannel[scenario3AuthenticationChannelsStrings.length];
				for (int i = 0; i < scenario3AuthenticationChannelsStrings.length; i++) {
					String scenario3AuthenticationChannelString = scenario3AuthenticationChannelsStrings[i];
					AuthenticationChannel innerAuthenticationChannel = AuthenticationChannel.valueOf(scenario3AuthenticationChannelString.toUpperCase());
					authenticationChannels[i] = innerAuthenticationChannel;
				}
			}
		}
		catch (Exception e) {
			log.error("isAuthenticationChannelEnable :: " + e.getMessage(), e);
		}

		if (authenticationChannels != null) {
			for (int i = 0; i < authenticationChannels.length; i++) {
				if (authenticationChannels[i] == authenticationChannel) {
					return true;
				}
			}
		}
		return false;
	}

}
