/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.protocollo;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ProtocolloClientException extends Exception {

	private static final long serialVersionUID = -8543148580029644934L;

	/**
	 * @param string
	 */
	public ProtocolloClientException(String message) {
		super(message);
	}

	public ProtocolloClientException(String message, Throwable cause) {
		super(message, cause);
	}

}
