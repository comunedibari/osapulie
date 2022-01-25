/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_agevolazione")
public class Agevolazione extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -7259562346916291261L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "nome", updatable = true, nullable = false, length = 45)
	private String nome;

	@Column(name = "valore", nullable = true)
	private Double valore;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "fk_categoriaimmobile", referencedColumnName = "fk_categoriaimmobile"),
		@JoinColumn(name = "fk_tributo",  referencedColumnName = "fk_tributo"),
		@JoinColumn(name = "fk_comuneisa", referencedColumnName = "fk_comuneisa") })
	private CategoriaImmobileTributo categoriaImmobileTributo;

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
	 * @return the categoriaImmobileTributo
	 */
	public CategoriaImmobileTributo getCategoriaImmobileTributo() {
		return categoriaImmobileTributo;
	}

	/**
	 * @param categoriaImmobileTributo the categoriaImmobileTributo to set
	 */
	public void setCategoriaImmobileTributo(CategoriaImmobileTributo categoriaImmobileTributo) {
		this.categoriaImmobileTributo = categoriaImmobileTributo;
	}

}
