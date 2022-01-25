/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PddApplicativaSingleton {

	private static PddApplicativaSingleton instance;

	private final Map<String, PddService> responseClassMap = new HashMap<String, PddService>();

	/**
	 *
	 */
	private PddApplicativaSingleton() {
	}

	/**
	 * @return the responseClassMap
	 */
	public Map<String, PddService> getResponseClassMap() {
		return responseClassMap;
	}

	/**
	 * @return the instance
	 */
	public static PddApplicativaSingleton getInstance() {
		if (instance == null) {
			instance = new PddApplicativaSingleton();
		}
		return instance;
	}

}
