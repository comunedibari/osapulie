/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.enumeration;

/**
 * @author Gianluca Pindinelli
 *
 */
public enum TipoAzienda {

	AZIENDA("ENTE INTERMEDIARIO"), CAF("CAF"), PROFESSIONITSA("PROFESSIONITSA");

	private final String tipo;

	/**
	 *
	 */
	private TipoAzienda(String tipo) {
		this.tipo = tipo;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return tipo;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

}
