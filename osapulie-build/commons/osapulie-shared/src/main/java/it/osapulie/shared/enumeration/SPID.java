/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.shared.enumeration;

/**
 * Definisce le differenti proprietà dedicate al sistema di autenticazione SPID.
 *
 * @author Gianluca Pindinelli
 *
 */
public enum SPID {

	SERVICES_LEVEL_1_PATH("/group/guest/servicesSPIDLevel1"),
	SERVICES_LEVEL_2_PATH("/group/guest/servicesSPIDLevel3"),
	SERVICES_LEVEL_3_PATH("/group/guest/servicesSPIDLevel3"),
	SERVICES_LEVEL_PATH_PATTERN("/group/guest/servicesSPIDLevel"),
	SERVICES_RETURN_URL_PARAMETER("returnToService"),
	SHIBBOLETH_SLO_RETURN_URL_PARAMETER("return"),
	ATTRIBUTE_NAME("name"),
	ATTRIBUTE_FAMILYNAME("familyName"),
	ATTRIBUTE_SHIB_AUTHENTICATION_METHOD("Shib-Authentication-Method"),
	ATTRIBUTE_EMAIL("email"),
	ATTRIBUTE_DIGITALADDRESS("digitalAddress"),
	ATTRIBUTE_ADDRESS("address"),
	ATTRIBUTE_MOBILEPHONE("mobilePhone"),
	ATTRIBUTE_SPIDCODE("spidCode"),
	ATTRIBUTE_PLACEOFBIRTH("placeOfBirth"),
	ATTRIBUTE_IDCARD("idCard"),
	ATTRIBUTE_DATEOFBIRTH("dateOfBirth"),
	ATTRIBUTE_GENDER("gender"),
	ATTRIBUTE_COMPANYNAME("companyName"),
	ATTRIBUTE_REGISTEREDOFFICE("registeredOffice"),
	ATTRIBUTE_IVACODE("ivaCode");

	private final String text;

	/**
	 *
	 */
	private SPID(String text) {
		this.text = text;
	}

	/**
	 * @return the name
	 * 
	 * @deprecated use {@link #toString()} instead. 
	 */
	@Deprecated
	public String getName() {
		return text;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return text;
	}

}
