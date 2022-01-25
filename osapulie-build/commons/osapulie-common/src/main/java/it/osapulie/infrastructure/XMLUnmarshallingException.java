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
public class XMLUnmarshallingException extends RuntimeException {

	private static final long serialVersionUID = -2489712176959466722L;

	public XMLUnmarshallingException(String message, Throwable e) {
		super(message, e);
	}
}
