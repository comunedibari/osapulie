/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_tipologia_detrazione")
public class TipologiaDetrazione extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3785328938787209170L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "nome", length = 45, nullable = true)
	private String nome;

	@Column(name = "codice", length = 45, nullable = true)
	private String codice;

	@OneToMany(mappedBy = "tipologiaDetrazione", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Detrazione> detrazioni;

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
	 * @return the detrazioni
	 */
	public List<Detrazione> getDetrazioni() {
		return detrazioni;
	}

	/**
	 * @param detrazioni the detrazioni to set
	 */
	public void setDetrazioni(List<Detrazione> detrazioni) {
		this.detrazioni = detrazioni;
	}

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

}
