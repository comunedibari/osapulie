/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.shared.enumeration;

/**
 * Definisce i differenti canali di autenticazione disponibili per la piattaforma.
 *
 * @author Gianluca Pindinelli
 *
 */
public enum AuthenticationChannel {

	CAS("CAS"), REGIONE_PUGLIA("REGIONE_PUGLIA"), SPID("SPID");

	private final String name;

	/**
	 *
	 */
	private AuthenticationChannel(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}

}
