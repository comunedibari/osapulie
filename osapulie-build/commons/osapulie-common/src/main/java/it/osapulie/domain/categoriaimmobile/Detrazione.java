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
@Table(name = "tb_detrazione")
public class Detrazione extends AbstractPersistable<Long> {

	/**
	 *
	 */
	private static final long serialVersionUID = 6482550217900142698L;

	@Override
	protected void setId(Long id) {
		super.setId(id);
	}

	@Column(name = "descrizione", updatable = true, nullable = false, length = 100)
	private String descrizione;

	@Column(name = "importo", nullable = true)
	private Double importo;

	@Column(name = "info", updatable = true, nullable = false, length = 255)
	private String info;

	@ManyToOne
	@JoinColumn(name = "fk_tipologiadetrazione", referencedColumnName = "ID")
	private TipologiaDetrazione tipologiaDetrazione;

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
	 * @return the importo
	 */
	public Double getImporto() {
		return importo;
	}

	/**
	 * @param importo the importo to set
	 */
	public void setImporto(Double importo) {
		this.importo = importo;
	}

	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the tipologiaDetrazione
	 */
	public TipologiaDetrazione getTipologiaDetrazione() {
		return tipologiaDetrazione;
	}

	/**
	 * @param tipologiaDetrazione the tipologiaDetrazione to set
	 */
	public void setTipologiaDetrazione(TipologiaDetrazione tipologiaDetrazione) {
		this.tipologiaDetrazione = tipologiaDetrazione;
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
