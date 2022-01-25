/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

/**
 * Eccezione sollevata nel caso in cui un servizio risulti non raggiungibile.
 *
 * @author Gianluca Pindinelli
 *
 */
public class ServiceUnreachableException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 6023385679066739463L;

	/**
	 *
	 */
	public ServiceUnreachableException() {
		super();
	}

	public ServiceUnreachableException(String message, Throwable e) {
		super(message, e);
	}

	public ServiceUnreachableException(String message) {
		super(message);
	}

	/**
	 * @param e
	 */
	public ServiceUnreachableException(Throwable e) {
		super(e);
	}

}
