/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form;

/**
 * @author Gianluca Pindinelli
 *
 */
public class DetrazioneForm {

	private long id;
	private String descrizione;
	private Double importo;
	private String info;
	private long idTipologiaDetrazione;

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
	 * @return the idTipologiaDetrazione
	 */
	public long getIdTipologiaDetrazione() {
		return idTipologiaDetrazione;
	}

	/**
	 * @param idTipologiaDetrazione the idTipologiaDetrazione to set
	 */
	public void setIdTipologiaDetrazione(long idTipologiaDetrazione) {
		this.idTipologiaDetrazione = idTipologiaDetrazione;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

}
