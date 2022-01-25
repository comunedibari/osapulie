/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.profiloutente.web.portlet.fascicoloutente.form;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Gianluca Pindinelli
 *
 */
public class RicevutaForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9108617472914323620L;

	private String utente;
	private String nomeServizio;
	private String numeroProtocollo;
	private String operatore;
	private String azienda;
	private String tipoAzienda;
	private String idPratica;
	private String comune;
	private Date dataOperazione;

	/**
	 * @return the utente
	 */
	public String getUtente() {
		return utente;
	}

	/**
	 * @param utente the utente to set
	 */
	public void setUtente(String utente) {
		this.utente = utente;
	}

	/**
	 * @return the nomeServizio
	 */
	public String getNomeServizio() {
		return nomeServizio;
	}

	/**
	 * @param nomeServizio the nomeServizio to set
	 */
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
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
	 * @return the idPratica
	 */
	public String getIdPratica() {
		return idPratica;
	}

	/**
	 * @param idPratica the idPratica to set
	 */
	public void setIdPratica(String idPratica) {
		this.idPratica = idPratica;
	}

	/**
	 * @return the dataOperazione
	 */
	public Date getDataOperazione() {
		return dataOperazione;
	}

	/**
	 * @param dataOperazione the dataOperazione to set
	 */
	public void setDataOperazione(Date dataOperazione) {
		this.dataOperazione = dataOperazione;
	}

	/**
	 * @return the azienda
	 */
	public String getAzienda() {
		return azienda;
	}

	/**
	 * @param azienda the azienda to set
	 */
	public void setAzienda(String azienda) {
		this.azienda = azienda;
	}

	/**
	 * @return the tipoAzienda
	 */
	public String getTipoAzienda() {
		return tipoAzienda;
	}

	/**
	 * @param tipoAzienda the tipoAzienda to set
	 */
	public void setTipoAzienda(String tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
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

}
