/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_tipologia_categoriaimmobile")
public class TipologiaCategoriaImmobile extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3785328938787209170L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "codice", length = 45, nullable = true)
	private String codice;

	@Column(name = "descrizione", length = 255, nullable = false)
	private String descrizione;

	@OneToMany(mappedBy = "tipologiaCategoriaImmobile", fetch = FetchType.EAGER)
	private List<CategoriaImmobile> categorieImmobili;

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
	 * @return the categorieImmobili
	 */
	public List<CategoriaImmobile> getCategorieImmobili() {
		return categorieImmobili;
	}

	/**
	 * @param categorieImmobili the categorieImmobili to set
	 */
	public void setCategorieImmobili(List<CategoriaImmobile> categorieImmobili) {
		this.categorieImmobili = categorieImmobili;
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
