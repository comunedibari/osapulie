/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.infrastructure;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class XMLMarshallingException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 6202500789976753231L;

	public XMLMarshallingException(String message, Throwable e) {
		super(message, e);
	}
}
