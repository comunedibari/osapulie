/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.json.configurazione;

import com.google.gson.annotations.SerializedName;

/**
 * @author Gianluca Pindinelli
 *
 */
public class InfoAggiuntive {

	@SerializedName("configurazione_servizi")
	private ConfigurazioneServizi configurazioneServizi;

	/**
	 * @return the configurazioneServizi
	 */
	public ConfigurazioneServizi getConfigurazioneServizi() {
		return configurazioneServizi;
	}

	/**
	 * @param configurazioneServizi the configurazioneServizi to set
	 */
	public void setConfigurazioneServizi(ConfigurazioneServizi configurazioneServizi) {
		this.configurazioneServizi = configurazioneServizi;
	}

}
