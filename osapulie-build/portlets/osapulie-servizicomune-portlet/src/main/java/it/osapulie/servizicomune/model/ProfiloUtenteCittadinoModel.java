/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.model;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe model per il profilo utente del cittadino.
 *
 * @author Antonio Magrì
 *
 */
public class ProfiloUtenteCittadinoModel implements Serializable {

	private static final long serialVersionUID = -805256810660972588L;
	/**
	 *
	 */
	private Long id;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String mail;
	private String mailPec;

	private String canaleComunicazione;
	private Date dataAutenticazioneForte;
	private boolean autenticazioneForte = false;
	private Integer livelloAutenticazione;
	private String canaleAutenticazione;

	private String numeroCivico;
	private String indirizzo;

	private String password;
	private String confermaPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMailPec() {
		return mailPec;
	}

	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}

	public String getCanaleComunicazione() {
		return canaleComunicazione;
	}

	public void setCanaleComunicazione(String canaleComunicazione) {
		this.canaleComunicazione = canaleComunicazione;
	}

	public Date getDataAutenticazioneForte() {
		return dataAutenticazioneForte;
	}

	public void setDataAutenticazioneForte(Date dataAutenticazioneForte) {
		this.dataAutenticazioneForte = dataAutenticazioneForte;
	}

	public boolean isAutenticazioneForte() {
		return autenticazioneForte;
	}

	public void setAutenticazioneForte(boolean autenticazioneForte) {
		this.autenticazioneForte = autenticazioneForte;
	}

	public Integer getLivelloAutenticazione() {
		return livelloAutenticazione;
	}

	public void setLivelloAutenticazione(Integer livelloAutenticazione) {
		this.livelloAutenticazione = livelloAutenticazione;
	}

	public String getCanaleAutenticazione() {
		return canaleAutenticazione;
	}

	public void setCanaleAutenticazione(String canaleAutenticazione) {
		this.canaleAutenticazione = canaleAutenticazione;
	}

	public String getNumeroCivico() {
		return numeroCivico;
	}

	public void setNumeroCivico(String numeroCivico) {
		this.numeroCivico = numeroCivico;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
