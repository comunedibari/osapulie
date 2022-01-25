/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.enumeration;

/**
 * @author Gianluca Pindinelli
 *
 */
public enum TipoRata {

	SALDO("Saldo"), ACCONTO("Acconto"), UNICA_SOLUZIONE("Unica soluzione");

	private String value;

	/**
	 *
	 */
	private TipoRata(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

}
