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
@Table(name = "tb_base_calcolo")
public class BaseDiCalcolo extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -3785328938787209170L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "descrizione", length = 100, nullable = true)
	private String descrizione;

	@Column(name = "valore", nullable = true)
	private Double valore;

	@OneToMany(mappedBy = "baseDiCalcolo", cascade = CascadeType.ALL, orphanRemoval = true)
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

}
