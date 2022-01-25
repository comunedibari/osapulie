/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Veicolo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 7697978885772460277L;
	private String tipo;
	private String tipoHidden;
	private String targa;

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the targa
	 */
	public String getTarga() {
		return targa;
	}

	/**
	 * @param targa the targa to set
	 */
	public void setTarga(String targa) {
		this.targa = targa;
	}

	/**
	 * @return the tipoHidden
	 */
	public String getTipoHidden() {
		return tipoHidden;
	}

	/**
	 * @param tipoHidden the tipoHidden to set
	 */
	public void setTipoHidden(String tipoHidden) {
		this.tipoHidden = tipoHidden;
	}

}
