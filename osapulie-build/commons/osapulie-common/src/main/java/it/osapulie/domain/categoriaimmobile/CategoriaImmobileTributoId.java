/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.categoriaimmobile;

import it.osapulie.domain.ComuneISA;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * Tabella di relazione ternaria tra {@link CategoriaImmobile}, {@link Tributo} e {@link ComuneISA}.
 *
 * @author Gianluca Pindinelli
 */
@Embeddable
public class CategoriaImmobileTributoId implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 2264747686408059992L;

	/**
	 *
	 */
	public CategoriaImmobileTributoId() {
	}

	public CategoriaImmobileTributoId(long idComuneISA, long idCategoriaImmobile, long idTributo) {

		this.idComuneISA = idComuneISA;
		this.idCategoriaImmobile = idCategoriaImmobile;
		this.idTributo = idTributo;
	}

	@Column(name = "fk_categoriaimmobile", nullable = false, updatable = false)
	private long idCategoriaImmobile;

	@Column(name = "fk_tributo", nullable = false, updatable = false)
	private long idTributo;

	@Column(name = "fk_comuneisa", nullable = false, updatable = false)
	private long idComuneISA;

	/**
	 * @return the idCategoriaImmobile
	 */
	public long getIdCategoriaImmobile() {
		return idCategoriaImmobile;
	}

	/**
	 * @param idCategoriaImmobile the idCategoriaImmobile to set
	 */
	public void setIdCategoriaImmobile(long idCategoriaImmobile) {
		this.idCategoriaImmobile = idCategoriaImmobile;
	}

	/**
	 * @return the idTributo
	 */
	public long getIdTributo() {
		return idTributo;
	}

	/**
	 * @param idTributo the idTributo to set
	 */
	public void setIdTributo(long idTributo) {
		this.idTributo = idTributo;
	}

	/**
	 * @return the idComuneISA
	 */
	public long getIdComuneISA() {
		return idComuneISA;
	}

	/**
	 * @param idComuneISA the idComuneISA to set
	 */
	public void setIdComuneISA(long idComuneISA) {
		this.idComuneISA = idComuneISA;
	}

}
