/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.shared.constant;

/**
 * Costanti di portale.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortalConstants {

	/**
	 * Proprietà da settare nel <code>portal-ext.properties</code> per la definizione dello scenario
	 * utilizzato da ISA
	 */
	public static final String SCENARIO_DEPLOYMENT_PORTAL_EXT_PROP = "osapulie.deployment.scenario";
	public static final String SCENARIO_DEPLOYMENT_IDP_FORGOT_URL_PORTAL_EXT_PROP = "osapulie.deployment.scenario3.idp.forgotpassword.url";
	public static final String SCENARIO_DEPLOYMENT_IDP_REGISTRATION_URL_PORTAL_EXT_PROP = "osapulie.deployment.scenario3.idp.registration.url";
	public static final String SCENARIO_DEPLOYMENT_AUTHENTICATION_CHANNELS_PORTAL_EXT_PROP = "osapulie.deployment.scenario3.authentication.channels";
	public static final String SCENARIO_DEPLOYMENT_SPID_SINGLELOGOUTSERVICE_PORTAL_EXT_PROP = "osapulie.deployment.scenario3.spid.singleLogoutService";

	public static final String REDIRECT_FILTER_PARAMETER_NAME = "redirectFilterUrl";

}
