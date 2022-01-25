/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.json.configurazione;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ConfigurazioneServizi {

	private List<Servizio> servizi;

	/**
	 * @return the servizi
	 */
	public List<Servizio> getServizi() {
		return servizi;
	}

	/**
	 * @param servizi the servizi to set
	 */
	public void setServizi(List<Servizio> servizi) {
		this.servizi = servizi;
	}
}
