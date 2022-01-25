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
@Table(name = "tb_esenzione")
public class Esenzione extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 4170765666067070434L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "descrizione", updatable = true, nullable = false, length = 45)
	private String descrizione;

	@ManyToOne
	@JoinColumns({ @JoinColumn(name = "fk_categoriaimmobile", referencedColumnName = "fk_categoriaimmobile"),
		@JoinColumn(name = "fk_tributo",  referencedColumnName = "fk_tributo"),
		@JoinColumn(name = "fk_comuneisa", referencedColumnName = "fk_comuneisa") })
	private CategoriaImmobileTributo categoriaImmobileTributo;

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
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
