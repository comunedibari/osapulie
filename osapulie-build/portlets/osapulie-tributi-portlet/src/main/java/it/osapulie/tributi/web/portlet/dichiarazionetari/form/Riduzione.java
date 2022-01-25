/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

import java.io.Serializable;
import java.util.List;

/**
 * Form object per le riduzioni associabili ad una dato {@link DatiImmobile}.
 *
 * @author Gianluca Pindinelli
 *
 */
public class Riduzione implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7055109381028155734L;
	private String codiceArticolo;
	private String descrizioneArticolo;
	private List<Valore> valori;

	/**
	 * @return the codiceArticolo
	 */
	public String getCodiceArticolo() {
		return codiceArticolo;
	}

	/**
	 * @param codiceArticolo the codiceArticolo to set
	 */
	public void setCodiceArticolo(String codiceArticolo) {
		this.codiceArticolo = codiceArticolo;
	}

	/**
	 * @return the descrizioneArticolo
	 */
	public String getDescrizioneArticolo() {
		return descrizioneArticolo;
	}

	/**
	 * @param descrizioneArticolo the descrizioneArticolo to set
	 */
	public void setDescrizioneArticolo(String descrizioneArticolo) {
		this.descrizioneArticolo = descrizioneArticolo;
	}

	/**
	 * @return the valori
	 */
	public List<Valore> getValori() {
		return valori;
	}

	/**
	 * @param valori the valori to set
	 */
	public void setValori(List<Valore> valori) {
		this.valori = valori;
	}

}
