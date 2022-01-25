/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestioneutenti.form;

import java.io.Serializable;

/**
 * @author Antonio Magrì
 *
 */
public class ProfiloUtenteCittadinoForm implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1015134468942421021L;
	/**
	 *
	 */

	private long idProfiloUtenteCittadino;
	private String codiceFiscale;
	private String cognome;
	private String nome;
	private String email;
	private String pec;
	private String password;
	private String confermaPassword;

	/**
	 * @return the idProfiloUtenteCittadino
	 */
	public long getIdProfiloUtenteCittadino() {
		return idProfiloUtenteCittadino;
	}

	/**
	 * @param idProfiloUtenteCittadino the idProfiloUtenteCittadino to set
	 */
	public void setIdProfiloUtenteCittadino(long idProfiloUtenteCittadino) {
		this.idProfiloUtenteCittadino = idProfiloUtenteCittadino;
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
	 * @return the pec
	 */
	public String getPec() {
		return pec;
	}

	/**
	 * @param pec the pec to set
	 */
	public void setPec(String pec) {
		this.pec = pec;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the confermaPassword
	 */
	public String getConfermaPassword() {
		return confermaPassword;
	}

	/**
	 * @param confermaPassword the confermaPassword to set
	 */
	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

}
