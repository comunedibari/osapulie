/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form;

/**
 * @author Gianluca Pindinelli
 *
 */
public class AgevolazioneForm {

	private long id;
	private String nome;
	private Double valore;

	/**
	 * @return the valore
	 */
	public Double getValore() {
		return valore;
	}

	/**
	 * @param valore the valore to set
	 */
	public void setValore(Double valore) {
		this.valore = valore;
	}

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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
