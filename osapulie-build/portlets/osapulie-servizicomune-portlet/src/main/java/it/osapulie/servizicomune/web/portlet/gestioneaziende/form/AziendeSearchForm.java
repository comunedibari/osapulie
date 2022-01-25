/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneaziende.form;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class AziendeSearchForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7733564611763694033L;

	private String partitaIva;
	private String ragioneSociale;
	private String codiceFiscaleResponsabile;
	private String stato;

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
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {
		return ragioneSociale;
	}

	/**
	 * @param ragioneSociale the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * @return the stato
	 */
	public String getStato() {
		return stato;
	}

	/**
	 * @param stato the stato to set
	 */
	public void setStato(String stato) {
		this.stato = stato;
	}

	/**
	 * @return the codiceFiscaleResponsabile
	 */
	public String getCodiceFiscaleResponsabile() {
		return codiceFiscaleResponsabile;
	}

	/**
	 * @param codiceFiscaleResponsabile the codiceFiscaleResponsabile to set
	 */
	public void setCodiceFiscaleResponsabile(String codiceFiscaleResponsabile) {
		this.codiceFiscaleResponsabile = codiceFiscaleResponsabile;
	}

}
