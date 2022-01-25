/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.util;

import javax.inject.Inject;

import it.osapulie.service.ConfigurazioneService;
import it.osapulie.service.ServizioService;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class ApplicationBootstrap {

	public static ApplicationBootstrap INSTANCE;

	@Inject
	private PortletHelper portletHelper;

	@Inject
	private ServizioService servizioService;

	@Inject
	private ConfigurazioneService configurazioneService;

	public ApplicationBootstrap() {
		INSTANCE = this;
	}

	/**
	 * @return the portletHelper
	 */
	public PortletHelper getPortletHelper() {
		return portletHelper;
	}

	/**
	 * @return the servizioService
	 */
	public ServizioService getServizioService() {
		return servizioService;
	}

	/**
	 * @return the configurazioneService
	 */
	public ConfigurazioneService getConfigurazioneService() {
		return configurazioneService;
	}

}
