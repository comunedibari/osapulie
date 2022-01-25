/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.test.web.portlet.util;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import it.osapulie.web.portlet.util.CommonServiceUtil;

/**
 * @author Gianluca Pindinelli
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:META-INF/spring/test-context.xml" })
public class EvaluationServiceTest {

	@Inject
	private CommonServiceUtil commonServiceUtil;

	@Test
	public void getToken() {

		String evaluationServiceUrl = commonServiceUtil.getEvaluationServiceUrl("AAAAAA");

		System.out.println(evaluationServiceUrl);

	}

}
