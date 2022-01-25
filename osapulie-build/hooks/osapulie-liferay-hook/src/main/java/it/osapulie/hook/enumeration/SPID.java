/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.hook.enumeration;

/**
 * Definisce i livelli di autenticazione previsti da SPID.
 *
 * @author Gianluca Pindinelli
 *
 */
public enum SPID {

	AUTHENTICATION_LEVEL_1("urn:oasis:names:tc:SAML:2.0:ac:classes:SpidL1"), AUTHENTICATION_LEVEL_2("urn:oasis:names:tc:SAML:2.0:ac:classes:SpidL2"), AUTHENTICATION_LEVEL_3(
			"urn:oasis:names:tc:SAML:2.0:ac:classes:SpidL3");

	private final String authValue;

	/**
	 *
	 */
	private SPID(String authLevel) {
		this.authValue = authLevel;
	}

	/**
	 * @return the authValue
	 */
	public String getAuthLevel() {
		return authValue;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return authValue;
	}

}
