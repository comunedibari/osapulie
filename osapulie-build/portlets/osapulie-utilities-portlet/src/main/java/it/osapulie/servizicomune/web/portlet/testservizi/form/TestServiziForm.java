/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.testservizi.form;

import java.io.Serializable;
import java.util.List;

import it.osapulie.domain.ComuneISA;

/**
 * @author Gianluca Pindinelli
 *
 */
public class TestServiziForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 867600612876991224L;

	private String codiceFiscale;
	private String partitaIva;
	private String idComune;
	private List<ComuneISA> comuni;

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	/**
	 * @return the idComune
	 */
	public String getIdComune() {
		return idComune;
	}

	/**
	 * @param idComune the idComune to set
	 */
	public void setIdComune(String idComune) {
		this.idComune = idComune;
	}

	/**
	 * @return the comuni
	 */
	public List<ComuneISA> getComuni() {
		return comuni;
	}

	/**
	 * @param comuni the comuni to set
	 */
	public void setComuni(List<ComuneISA> comuni) {
		this.comuni = comuni;
	}

}
