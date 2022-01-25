/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.json.configurazione;

import java.util.List;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class Servizio {

	private String codice;
	private List<Configurazione> configurazioni;

	/**
	 * @return the codice
	 */
	public String getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(String codice) {
		this.codice = codice;
	}

	/**
	 * @return the configurazioni
	 */
	public List<Configurazione> getConfigurazioni() {
		return configurazioni;
	}

	/**
	 * @param configurazioni the configurazioni to set
	 */
	public void setConfigurazioni(List<Configurazione> configurazioni) {
		this.configurazioni = configurazioni;
	}

}
