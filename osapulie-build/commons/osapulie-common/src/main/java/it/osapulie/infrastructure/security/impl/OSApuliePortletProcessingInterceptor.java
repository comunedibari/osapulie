/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security.impl;

import java.io.IOException;

import javax.portlet.PortletRequest;
import javax.portlet.PortletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.extensions.portlet.PortletProcessingInterceptor;

/**
 * 
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public class OSApuliePortletProcessingInterceptor extends PortletProcessingInterceptor {

	private Logger log = LoggerFactory.getLogger(OSApuliePortletProcessingInterceptor.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.extensions.portlet.PortletProcessingInterceptor#
	 * onSuccessfulAuthentication(javax.portlet.PortletRequest, javax.portlet.PortletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	protected void onSuccessfulAuthentication(PortletRequest request, PortletResponse response, Authentication authResult) throws IOException {

		log.debug("onSuccessfulAuthentication :: entering method");

		super.onSuccessfulAuthentication(request, response, authResult);

	}
}
