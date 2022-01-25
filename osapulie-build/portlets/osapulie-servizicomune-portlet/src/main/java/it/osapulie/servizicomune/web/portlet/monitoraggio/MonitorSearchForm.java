/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.monitoraggio;


import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class MonitorSearchForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7733564611763694033L;

	private String partitaIva;
	private String ragioneSociale;
	private String codiceFiscaleResponsabile;
	private String stato;
	private Long comune;
	private Long intermediario;
	private Long tipologia;
	private String dataRichiestaDa;
	private String dataRichiestaA;

	public Long getComune() {
		return comune;
	}

	public void setComune(Long comune) {
		this.comune = comune;
	}

	public Long getIntermediario() {
		return intermediario;
	}

	public void setIntermediario(Long intermediario) {
		this.intermediario = intermediario;
	}

	public String getDataRichiestaDa() {
		return dataRichiestaDa;
	}

	public void setDataRichiestaDa(String dataRichiestaDa) {
		this.dataRichiestaDa = dataRichiestaDa;
	}

	public String getDataRichiestaA() {
		return dataRichiestaA;
	}

	public void setDataRichiestaA(String dataRichiestaA) {
		this.dataRichiestaA = dataRichiestaA;
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

	public Long getTipologia() {
		return tipologia;
	}

	public void setTipologia(Long tipologia) {
		this.tipologia = tipologia;
	}

}
