/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.json.configurazione;

import java.util.Map;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class Condizione {

	private String nome;
	private String condizione;
	private String campo;
	private String valore;

	@SerializedName("info_aggiuntive")
	private Map<String, String> infoAggiuntive;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the condizione
	 */
	public String getCondizione() {
		return condizione;
	}

	/**
	 * @param condizione the condizione to set
	 */
	public void setCondizione(String condizione) {
		this.condizione = condizione;
	}

	/**
	 * @return the campo
	 */
	public String getCampo() {
		return campo;
	}

	/**
	 * @param campo the campo to set
	 */
	public void setCampo(String campo) {
		this.campo = campo;
	}

	/**
	 * @return the valore
	 */
	public String getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(String valore) {
		this.valore = valore;
	}

	/**
	 * @return the infoAggiuntive
	 */
	public Map<String, String> getInfoAggiuntive() {
		return infoAggiuntive;
	}

	/**
	 * @param infoAggiuntive the infoAggiuntive to set
	 */
	public void setInfoAggiuntive(Map<String, String> infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}

}
