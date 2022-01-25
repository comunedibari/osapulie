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
 * The persistent class for the comune_estero database table.
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_comune_estero")
public class ComuneEstero extends AbstractPersistable<Long> implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 6177345486054492620L;

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.jpa.domain.AbstractPersistable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "codice")
	private Integer codice;

	@Column(name = "denominazione", length = 100)
	private String denominazione;

	/**
	 * @return the codice
	 */
	public Integer getCodice() {
		return codice;
	}

	/**
	 * @param codice the codice to set
	 */
	public void setCodice(Integer codice) {
		this.codice = codice;
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

}