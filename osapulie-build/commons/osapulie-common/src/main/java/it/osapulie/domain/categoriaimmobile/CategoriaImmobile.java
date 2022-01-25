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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
@Entity
@Table(name = "tb_categoria_immobile")
public class CategoriaImmobile extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = -4805855595279428317L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "coefficiente_moltiplicazione", nullable = true)
	private Double coefficienteMoltiplicazione;

	@OneToMany(mappedBy = "categoriaImmobile", cascade = { CascadeType.PERSIST, CascadeType.REMOVE})
	private List<CategoriaImmobileTributo> categorieImmobiliTributi;

	@ManyToOne
	@JoinColumn(name = "fk_basecalcolo", referencedColumnName = "ID")
	private BaseDiCalcolo baseDiCalcolo;

	@ManyToOne
	@JoinColumn(name = "fk_tipologiacategoriaimmobile", referencedColumnName = "ID")
	private TipologiaCategoriaImmobile tipologiaCategoriaImmobile;

	/**
	 * @return the categorieImmobiliTributi
	 */
	public List<CategoriaImmobileTributo> getCategorieImmobiliTributi() {
		return categorieImmobiliTributi;
	}

	/**
	 * @param categorieImmobiliTributi the categorieImmobiliTributi to set
	 */
	public void setCategorieImmobiliTributi(List<CategoriaImmobileTributo> categorieImmobiliTributi) {
		this.categorieImmobiliTributi = categorieImmobiliTributi;
	}

	/**
	 * @return the baseDiCalcolo
	 */
	public BaseDiCalcolo getBaseDiCalcolo() {
		return baseDiCalcolo;
	}

	/**
	 * @param baseDiCalcolo the baseDiCalcolo to set
	 */
	public void setBaseDiCalcolo(BaseDiCalcolo baseDiCalcolo) {
		this.baseDiCalcolo = baseDiCalcolo;
	}

	/**
	 * @return the tipologiaCategoriaImmobile
	 */
	public TipologiaCategoriaImmobile getTipologiaCategoriaImmobile() {
		return tipologiaCategoriaImmobile;
	}

	/**
	 * @param tipologiaCategoriaImmobile the tipologiaCategoriaImmobile to set
	 */
	public void setTipologiaCategoriaImmobile(TipologiaCategoriaImmobile tipologiaCategoriaImmobile) {
		this.tipologiaCategoriaImmobile = tipologiaCategoriaImmobile;
	}

	/**
	 * @return the coefficienteMoltiplicazione
	 */
	public Double getCoefficienteMoltiplicazione() {
		return coefficienteMoltiplicazione;
	}

	/**
	 * @param coefficienteMoltiplicazione the coefficienteMoltiplicazione to set
	 */
	public void setCoefficienteMoltiplicazione(Double coefficienteMoltiplicazione) {
		this.coefficienteMoltiplicazione = coefficienteMoltiplicazione;
	}

}
