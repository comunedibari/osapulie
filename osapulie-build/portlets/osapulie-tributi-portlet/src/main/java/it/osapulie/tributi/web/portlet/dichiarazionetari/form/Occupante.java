/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

import java.io.Serializable;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Occupante implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String dataNascita;
	private String dataInizioOccupazione;
	private String dataFineOccupazione;
	private String tipoModifica;
	private String tipoModificaDescrizione;

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

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
	 * @return the dataNascita
	 */
	public String getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the dataInizioOccupazione
	 */
	public String getDataInizioOccupazione() {
		return dataInizioOccupazione;
	}

	/**
	 * @param dataInizioOccupazione the dataInizioOccupazione to set
	 */
	public void setDataInizioOccupazione(String dataInizioOccupazione) {
		this.dataInizioOccupazione = dataInizioOccupazione;
	}

	/**
	 * @return the dataFineOccupazione
	 */
	public String getDataFineOccupazione() {
		return dataFineOccupazione;
	}

	/**
	 * @param dataFineOccupazione the dataFineOccupazione to set
	 */
	public void setDataFineOccupazione(String dataFineOccupazione) {
		this.dataFineOccupazione = dataFineOccupazione;
	}

	public String getTipoModifica() {
		return tipoModifica;
	}

	public void setTipoModifica(String tipoModifica) {
		this.tipoModifica = tipoModifica;
	}

	public String getTipoModificaDescrizione() {
		return tipoModificaDescrizione;
	}

	public void setTipoModificaDescrizione(String tipoModificaDescrizione) {
		this.tipoModificaDescrizione = tipoModificaDescrizione;
	}

}
