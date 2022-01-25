/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class CategoriaImmobileForm {

	private long idCategoriaImmobile;
	private long idTipologiaCategoriaImmobile;
	private long idBaseCalcolo;
	private String descrizione;

	private Double coefficienteMoltiplicazione;

	private List<TributoForm> tributi;
	
	private Integer anno;

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
	 * @return the idTipologiaCategoriaImmobile
	 */
	public long getIdTipologiaCategoriaImmobile() {
		return idTipologiaCategoriaImmobile;
	}

	/**
	 * @param idTipologiaCategoriaImmobile the idTipologiaCategoriaImmobile to set
	 */
	public void setIdTipologiaCategoriaImmobile(long idTipologiaCategoriaImmobile) {
		this.idTipologiaCategoriaImmobile = idTipologiaCategoriaImmobile;
	}

	/**
	 * @return the idBaseCalcolo
	 */
	public long getIdBaseCalcolo() {
		return idBaseCalcolo;
	}

	/**
	 * @param idBaseCalcolo the idBaseCalcolo to set
	 */
	public void setIdBaseCalcolo(long idBaseCalcolo) {
		this.idBaseCalcolo = idBaseCalcolo;
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

	/**
	 * @return the tributi
	 */
	public List<TributoForm> getTributi() {
		return tributi;
	}

	/**
	 * @param tributi the tributi to set
	 */
	public void setTributi(List<TributoForm> tributi) {
		this.tributi = tributi;
	}

	public Integer getAnno() {
		return anno;
	}

	public void setAnno(Integer anno) {
		this.anno = anno;
	}

}
