/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class TributoForm {

	private String nome;
	private Double aliquota;
	
	private long idTributo;

	private List<AgevolazioneForm> agevolazioni;
	private List<EsenzioneForm> esenzioni;
	private List<DetrazioneForm> detrazioni;

	/**
	 * @return the aliquota
	 */
	public Double getAliquota() {
		return aliquota;
	}

	/**
	 * @param aliquota the aliquota to set
	 */
	public void setAliquota(Double aliquota) {
		this.aliquota = aliquota;
	}


	/**
	 * @return the agevolazioni
	 */
	public List<AgevolazioneForm> getAgevolazioni() {
		return agevolazioni;
	}

	/**
	 * @param agevolazioni the agevolazioni to set
	 */
	public void setAgevolazioni(List<AgevolazioneForm> agevolazioni) {
		this.agevolazioni = agevolazioni;
	}

	/**
	 * @return the esenzioni
	 */
	public List<EsenzioneForm> getEsenzioni() {
		return esenzioni;
	}

	/**
	 * @param esenzioni the esenzioni to set
	 */
	public void setEsenzioni(List<EsenzioneForm> esenzioni) {
		this.esenzioni = esenzioni;
	}

	/**
	 * @return the detrazioni
	 */
	public List<DetrazioneForm> getDetrazioni() {
		return detrazioni;
	}

	/**
	 * @param detrazioni the detrazioni to set
	 */
	public void setDetrazioni(List<DetrazioneForm> detrazioni) {
		this.detrazioni = detrazioni;
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

	public long getIdTributo() {
		return idTributo;
	}

	public void setIdTributo(long idTributo) {
		this.idTributo = idTributo;
	}

}
