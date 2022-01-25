/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.web.portlet.fascicoloutente.form;

import java.util.Date;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ReportAziendaForm {

	private Long id;
	private Date dataRichiesta;
	private String cittadino;
	private String servizio;
	private String comune;
	private String operatore;
	private String numeroProtocollo;
	private String infoAggiuntive;

	/**
	 * @return the dataRichiesta
	 */
	public Date getDataRichiesta() {
		return dataRichiesta;
	}

	/**
	 * @param dataRichiesta the dataRichiesta to set
	 */
	public void setDataRichiesta(Date dataRichiesta) {
		this.dataRichiesta = dataRichiesta;
	}

	/**
	 * @return the cittadino
	 */
	public String getCittadino() {
		return cittadino;
	}

	/**
	 * @param cittadino the cittadino to set
	 */
	public void setCittadino(String cittadino) {
		this.cittadino = cittadino;
	}

	/**
	 * @return the servizio
	 */
	public String getServizio() {
		return servizio;
	}

	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	/**
	 * @return the comune
	 */
	public String getComune() {
		return comune;
	}

	/**
	 * @param comune the comune to set
	 */
	public void setComune(String comune) {
		this.comune = comune;
	}

	/**
	 * @return the operatore
	 */
	public String getOperatore() {
		return operatore;
	}

	/**
	 * @param operatore the operatore to set
	 */
	public void setOperatore(String operatore) {
		this.operatore = operatore;
	}

	/**
	 * @return the infoAggiuntive
	 */
	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}

	/**
	 * @param infoAggiuntive the infoAggiuntive to set
	 */
	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}
}
