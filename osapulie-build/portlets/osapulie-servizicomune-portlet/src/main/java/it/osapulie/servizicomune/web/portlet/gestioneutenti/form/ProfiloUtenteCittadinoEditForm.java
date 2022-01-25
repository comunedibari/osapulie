/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
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
public class ProfiloUtenteCittadinoEditForm implements Serializable {

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
	public long getIdProfiloUtenteCittadino() {
		return idProfiloUtenteCittadino;
	}
	public void setIdProfiloUtenteCittadino(long idProfiloUtenteCittadino) {
		this.idProfiloUtenteCittadino = idProfiloUtenteCittadino;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPec() {
		return pec;
	}
	public void setPec(String pec) {
		this.pec = pec;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfermaPassword() {
		return confermaPassword;
	}
	public void setConfermaPassword(String confermaPassword) {
		this.confermaPassword = confermaPassword;
	}

	

}
