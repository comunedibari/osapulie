/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.hook.utils;

import javax.inject.Inject;

import it.osapulie.infrastructure.security.ProfilazioneUtenteService;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.persistence.ComuneRepository;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;
import it.osapulie.service.PinService;
import it.osapulie.web.portlet.util.PortletHelper;

/**
 *
 * @author Gianluca Pindinelli
 * @author Gianni Barone
 *
 */
public class ApplicationBootstrap {

	public static ApplicationBootstrap INSTANCE;

	@Inject
	private PortletHelper portletHelper;

	@Inject
	private ProfiloUtenteCittadinoRepository profiloUtenteCittadinoRepository;

	@Inject
	private ComuneRepository comuneRepository;

	@Inject
	private ComuneISARepository comuneISARepository;

	@Inject
	private ProfilazioneUtenteService profilazioneUtenteService;

	@Inject
	private PinService pinService;

	public ApplicationBootstrap() {
		INSTANCE = this;
	}

	/**
	 * @return the portletHelper
	 */
	public PortletHelper getPortletHelper() {
		return portletHelper;
	}

	public ProfiloUtenteCittadinoRepository getProfiloUtenteCittadinoRepository() {
		return profiloUtenteCittadinoRepository;
	}

	public ProfilazioneUtenteService getProfilazioneUtenteService() {
		return profilazioneUtenteService;
	}

	public ComuneISARepository getComuneISARepository() {
		return comuneISARepository;
	}

	public ComuneRepository getComuneRepository() {
		return comuneRepository;
	}

	public PinService getPinService() {
		return pinService;
	}

}
