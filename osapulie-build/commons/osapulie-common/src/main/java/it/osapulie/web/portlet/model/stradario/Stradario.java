/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.model.stradario;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Stradario {

	private List<Via> vie;
	private Errore errore;

	/**
	 * @return the vie
	 */
	public List<Via> getVie() {
		return vie;
	}

	/**
	 * @param vie the vie to set
	 */
	public void setVie(List<Via> vie) {
		this.vie = vie;
	}

	/**
	 * @return the errore
	 */
	public Errore getErrore() {
		return errore;
	}

	/**
	 * @param errore the errore to set
	 */
	public void setErrore(Errore errore) {
		this.errore = errore;
	}

}
