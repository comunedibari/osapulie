/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web;

import javax.portlet.PortletSecurityException;

/**
 * @author Gianluca Pindinelli
 *
 */
public class UnauthorizedException extends PortletSecurityException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param text
	 */
	public UnauthorizedException(String text) {
		super(text);
	}

}
