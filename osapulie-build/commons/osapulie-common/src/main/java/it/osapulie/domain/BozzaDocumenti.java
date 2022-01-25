/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 * @author Gianluca De Felice
 *
 */
@Entity
@Table(name = "tb_bozza_documenti")
public class BozzaDocumenti extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -4234465119259411371L;

	@OneToOne
	@JoinColumn(name = "fk_bozza", nullable = false)
	private Bozza bozza;

	@Lob
	@Column(name = "allegato")
	private byte[] allegato;

	@Column(name = "nome_allegato", nullable = true, length = 128)
	private String nomeAllegato;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	
	public Bozza getBozza() {
		return bozza;
	}

	public void setBozza(Bozza bozza) {
		this.bozza = bozza;
	}


	public byte[] getAllegato() {
		return allegato;
	}


	public void setAllegato(byte[] allegato) {
		this.allegato = allegato;
	}


	public String getNomeAllegato() {
		return nomeAllegato;
	}


	public void setNomeAllegato(String nomeAllegato) {
		this.nomeAllegato = nomeAllegato;
	}

}
