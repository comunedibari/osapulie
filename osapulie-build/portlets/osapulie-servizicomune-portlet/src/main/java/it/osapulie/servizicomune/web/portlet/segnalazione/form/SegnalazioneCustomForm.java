/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.segnalazione.form;

import java.io.Serializable;
import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public class SegnalazioneCustomForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4977611640146466999L;
	private String descrizione;
	private String motivazione;
	private String note;
	private String codiceServizio;

	private List<String> motivazioni;

	private String email;

	private String codiceFiscale;
	private String nome;
	private String cognome;

	private String oggetto;

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
	 * @return the motivazione
	 */
	public String getMotivazione() {
		return motivazione;
	}

	/**
	 * @param motivazione the motivazione to set
	 */
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}

	/**
	 * @return the motivazioni
	 */
	public List<String> getMotivazioni() {
		return motivazioni;
	}

	/**
	 * @param motivazioni the motivazioni to set
	 */
	public void setMotivazioni(List<String> motivazioni) {
		this.motivazioni = motivazioni;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the oggetto
	 */
	public String getOggetto() {
		return oggetto;
	}

	/**
	 * @param oggetto the oggetto to set
	 */
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	/**
	 * @return the codiceServizio
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}

	/**
	 * @param codiceServizio the codiceServizio to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
}
