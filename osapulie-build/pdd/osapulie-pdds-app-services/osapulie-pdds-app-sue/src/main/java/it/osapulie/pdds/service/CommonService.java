/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.service;

import it.osapulie.pdds.service.util.XMLHelper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Gianluca Pindinelli
 *
 */
public class CommonService {

	protected final XMLHelper xmlHelper;

	/**
	 *
	 */
	public CommonService() {
		ApplicationContext appContext = new ClassPathXmlApplicationContext("/META-INF/spring/pratiche-application-context.xml");
		xmlHelper = (XMLHelper) appContext.getBean("xmlHelperPratiche");
	}

}
