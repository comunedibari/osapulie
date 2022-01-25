/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.model.stradario;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Civico implements Serializable, Comparable<Civico> {

	/**
	 *
	 */
	private static final long serialVersionUID = 694615653575612131L;

	private String identificativo;
	private Integer numero;
	private String esponente;
	private String localita;
	private String cap;

	/**
	 * @return the identificativo
	 */
	public String getIdentificativo() {
		return identificativo;
	}

	/**
	 * @param identificativo the identificativo to set
	 */
	public void setIdentificativo(String identificativo) {
		this.identificativo = identificativo;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the esponente
	 */
	public String getEsponente() {
		return esponente;
	}

	/**
	 * @param esponente the esponente to set
	 */
	public void setEsponente(String esponente) {
		this.esponente = esponente;
	}

	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}

	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Civico o) {
		return Integer.valueOf(this.getNumero()).compareTo(Integer.valueOf(o.getNumero()));
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

}
