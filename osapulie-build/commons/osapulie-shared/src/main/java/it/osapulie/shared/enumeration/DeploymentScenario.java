/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.shared.enumeration;

/**
 * Enumeration per il portale.
 *
 * @author Gianluca Pindinelli
 *
 */
public enum DeploymentScenario {

	/**
	 * Scenario 0: login delegata a Liferay
	 */
	SCENARIO_DEFAULT(0),
	/**
	 * Scenario 1: login delegata a CAS esterno a Liferay (AS separati)
	 */
	SCENARIO_1(1),
	/**
	 * Scenario 2: login delegata a CAS deployato con Liferay (stesso AS)
	 */
	SCENARIO_2(2),
	/**
	 * Scenario 3: login delegata ad IDP
	 */
	SCENARIO_3(3);

	private final int scenario;

	/**
	 *
	 */
	private DeploymentScenario(int scenario) {
		this.scenario = scenario;
	}

	public int getScenario() {
		return scenario;
	}

}
