/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.common;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface PddService {

	/**
	 * Ritorna la risposta del servizio in base all'XML passato in input.
	 *
	 * @param xml
	 * @return
	 */
	String getResponse(String xml);

	/**
	 * Ritorna il nome identificativo del servizio.
	 *
	 * @return
	 */
	String getName();

}
