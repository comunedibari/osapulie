/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * The persistent class for the tb_stato_estero database table.
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_stato_estero")
public class StatoEstero extends AbstractPersistable<Long> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 69955298317664502L;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.jpa.domain.AbstractPersistable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "codice_stato")
	private Integer codiceStato;

	@Column(name = "codice_area")
	private Integer codiceArea;

	@Column(name = "continente")
	private Integer continente;

	@Column(name = "denominazione", length = 100)
	private String denominazione;

	@Column(name = "denominazione_eng", length = 100)
	private String denominazioneEng;

	/**
	 * @return the codiceStato
	 */
	public Integer getCodiceStato() {
		return codiceStato;
	}

	/**
	 * @param codiceStato the codiceStato to set
	 */
	public void setCodiceStato(Integer codiceStato) {
		this.codiceStato = codiceStato;
	}

	/**
	 * @return the codiceArea
	 */
	public Integer getCodiceArea() {
		return codiceArea;
	}

	/**
	 * @param codiceArea the codiceArea to set
	 */
	public void setCodiceArea(Integer codiceArea) {
		this.codiceArea = codiceArea;
	}

	/**
	 * @return the continente
	 */
	public Integer getContinente() {
		return continente;
	}

	/**
	 * @param continente the continente to set
	 */
	public void setContinente(Integer continente) {
		this.continente = continente;
	}

	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}

	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	/**
	 * @return the denominazioneEng
	 */
	public String getDenominazioneEng() {
		return denominazioneEng;
	}

	/**
	 * @param denominazioneEng the denominazioneEng to set
	 */
	public void setDenominazioneEng(String denominazioneEng) {
		this.denominazioneEng = denominazioneEng;
	}

}