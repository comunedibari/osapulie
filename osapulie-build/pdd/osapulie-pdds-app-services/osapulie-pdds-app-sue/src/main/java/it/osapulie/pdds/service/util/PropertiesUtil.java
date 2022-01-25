/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service.util;

import java.io.IOException;
import java.util.Properties;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PropertiesUtil {

	public static String getSuePath() throws IOException {

		Properties properties = new Properties();
		properties.load(PropertiesUtil.class.getResourceAsStream("/services.properties"));
		String url = properties.getProperty("URL_SUE");

		return url;
	}
}
