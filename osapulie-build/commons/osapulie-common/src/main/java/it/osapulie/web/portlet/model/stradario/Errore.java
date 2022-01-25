/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.model.stradario;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Errore {

	private String messaggio;
	private int codice;

	/**
	 * @return the messaggio
	 */
	public String getMessaggio() {
		return messaggio;
	}

	/**
	 * @param messaggio the messaggio to set
	 */
	public void setMessaggio(String messaggio) {
		this.messaggio = messaggio;
	}

	/**
	 * @return the codice
	 */
	public int getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(int codice) {
		this.codice = codice;
	}

}
