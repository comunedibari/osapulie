/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

/**
 * @author Gianluca Pindinelli
 * 
 */
public class CommunicationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1948956494231241848L;

	public CommunicationException(Exception e) {
		super(e);
	}

	public CommunicationException(String msg) {
		super(msg);
	}

	public CommunicationException(String message, Throwable e) {
		super(message, e);
	}

}
