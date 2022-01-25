/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.profiloutente.model.view;

import java.io.Serializable;
import java.util.Date;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;

/**
 * Classe model per il binding delle informazioni riguardanti il profilo utente.
 *
 * @author Gianluca Pindinelli
 *
 */
public class ProfiloUtenteModel implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7232739192102047851L;
	private String nome;
	private String cognome;
	private String secondoNome;
	private String mail;
	private String tipoAzienda;
	private Date dataNascita;
	private boolean uomo;
	private String viaResidenza;
	private String nrCivicoResidenza;
	private long comuneResidenza;
	private String comuneResidenzaString;
	private String codiceFiscale;
	private String partitaIva;
	private String mailPec;
	private String canaleComunicazione;

	/**
	 * @return the canaleComunicazione
	 */
	public String getCanaleComunicazione() {
		return canaleComunicazione;
	}

	/**
	 * @param canaleComunicazione the canaleComunicazione to set
	 */
	public void setCanaleComunicazione(String canaleComunicazione) {
		this.canaleComunicazione = canaleComunicazione;
	}

	private String username;
	private String password;
	private String rePassword;

	private String canaleAutenticazione;

	private String mailSmartCard;
	private boolean autenticatoForte = false;
	private boolean autenticazioneForte = false;

	private Integer livelloAutenticazione;

	private Date dataAutenticazioneForte;

	private long comuneIsa;
	private ComuneISA comuneISAObject;
	private String comuneIsaString;
	private long idAzienda;

	/**
	 * <code>true</code> se la portlet di stato utente deve visualizzare il profilo selezionato dal
	 * cittadino, <code>false</code> altrimenti.
	 */
	private boolean profiloEnable;
	/**
	 * <code>true</code> se l'utente loggato ha scelto un'azienda/professionista come profilo
	 * utente, <code>false</code> altrimenti.
	 */
	private boolean profiloAzienda;
	private boolean profiloDelega;

	private Delega delega;

	/**
	 * Stato ultimo pin richiesto. <code>null</code> se non è stato mai richiesto.
	 */
	private String statoPin;

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
	 * @return the secondoNome
	 */
	public String getSecondoNome() {
		return secondoNome;
	}

	/**
	 * @param secondoNome the secondoNome to set
	 */
	public void setSecondoNome(String secondoNome) {
		this.secondoNome = secondoNome;
	}

	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @param mail the mail to set
	 */
	public void setMail(String mail) {
		this.mail = mail;
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
	 * @return the dataNascita
	 */
	public Date getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the uomo
	 */
	public boolean isUomo() {
		return uomo;
	}

	/**
	 * @param uomo the uomo to set
	 */
	public void setUomo(boolean uomo) {
		this.uomo = uomo;
	}

	/**
	 * @return the viaResidenza
	 */
	public String getViaResidenza() {
		return viaResidenza;
	}

	/**
	 * @param viaResidenza the viaResidenza to set
	 */
	public void setViaResidenza(String viaResidenza) {
		this.viaResidenza = viaResidenza;
	}

	/**
	 * @return the nrCivicoResidenza
	 */
	public String getNrCivicoResidenza() {
		return nrCivicoResidenza;
	}

	/**
	 * @param nrCivicoResidenza the nrCivicoResidenza to set
	 */
	public void setNrCivicoResidenza(String nrCivicoResidenza) {
		this.nrCivicoResidenza = nrCivicoResidenza;
	}

	/**
	 * @return the comuneResidenza
	 */
	public long getComuneResidenza() {
		return comuneResidenza;
	}

	/**
	 * @param comuneResidenza the comuneResidenza to set
	 */
	public void setComuneResidenza(long comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	/**
	 * @return the comuneResidenzaString
	 */
	public String getComuneResidenzaString() {
		return comuneResidenzaString;
	}

	/**
	 * @param comuneResidenzaString the comuneResidenzaString to set
	 */
	public void setComuneResidenzaString(String comuneResidenzaString) {
		this.comuneResidenzaString = comuneResidenzaString;
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
	 * @return the mailPec
	 */
	public String getMailPec() {
		return mailPec;
	}

	/**
	 * @param mailPec the mailPec to set
	 */
	public void setMailPec(String mailPec) {
		this.mailPec = mailPec;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
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
	 * @return the rePassword
	 */
	public String getRePassword() {
		return rePassword;
	}

	/**
	 * @param rePassword the rePassword to set
	 */
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}

	/**
	 * @return the canaleAutenticazione
	 */
	public String getCanaleAutenticazione() {
		return canaleAutenticazione;
	}

	/**
	 * @param canaleAutenticazione the canaleAutenticazione to set
	 */
	public void setCanaleAutenticazione(String canaleAutenticazione) {
		this.canaleAutenticazione = canaleAutenticazione;
	}

	/**
	 * @return the mailSmartCard
	 */
	public String getMailSmartCard() {
		return mailSmartCard;
	}

	/**
	 * @param mailSmartCard the mailSmartCard to set
	 */
	public void setMailSmartCard(String mailSmartCard) {
		this.mailSmartCard = mailSmartCard;
	}

	/**
	 * @return the autenticatoForte
	 */
	public boolean isAutenticatoForte() {
		return autenticatoForte;
	}

	/**
	 * @param autenticatoForte the autenticatoForte to set
	 */
	public void setAutenticatoForte(boolean autenticatoForte) {
		this.autenticatoForte = autenticatoForte;
	}

	/**
	 * @return the autenticazioneForte
	 */
	public boolean isAutenticazioneForte() {
		return autenticazioneForte;
	}

	/**
	 * @param autenticazioneForte the autenticazioneForte to set
	 */
	public void setAutenticazioneForte(boolean autenticazioneForte) {
		this.autenticazioneForte = autenticazioneForte;
	}

	/**
	 * @return the livelloAutenticazione
	 */
	public Integer getLivelloAutenticazione() {
		return livelloAutenticazione;
	}

	/**
	 * @param livelloAutenticazione the livelloAutenticazione to set
	 */
	public void setLivelloAutenticazione(Integer livelloAutenticazione) {
		this.livelloAutenticazione = livelloAutenticazione;
	}

	/**
	 * @return the dataAutenticazioneForte
	 */
	public Date getDataAutenticazioneForte() {
		return dataAutenticazioneForte;
	}

	/**
	 * @param dataAutenticazioneForte the dataAutenticazioneForte to set
	 */
	public void setDataAutenticazioneForte(Date dataAutenticazioneForte) {
		this.dataAutenticazioneForte = dataAutenticazioneForte;
	}

	/**
	 * @return the comuneIsa
	 */
	public long getComuneIsa() {
		return comuneIsa;
	}

	/**
	 * @param comuneIsa the comuneIsa to set
	 */
	public void setComuneIsa(long comuneIsa) {
		this.comuneIsa = comuneIsa;
	}

	/**
	 * @return the comuneIsaString
	 */
	public String getComuneIsaString() {
		return comuneIsaString;
	}

	/**
	 * @param comuneIsaString the comuneIsaString to set
	 */
	public void setComuneIsaString(String comuneIsaString) {
		this.comuneIsaString = comuneIsaString;
	}

	/**
	 * @return the idAzienda
	 */
	public long getIdAzienda() {
		return idAzienda;
	}

	/**
	 * @param idAzienda the idAzienda to set
	 */
	public void setIdAzienda(long idAzienda) {
		this.idAzienda = idAzienda;
	}

	/**
	 * @return the profiloEnable
	 */
	public boolean isProfiloEnable() {
		return profiloEnable;
	}

	/**
	 * @param profiloEnable the profiloEnable to set
	 */
	public void setProfiloEnable(boolean profiloEnable) {
		this.profiloEnable = profiloEnable;
	}

	/**
	 * @return the profiloAzienda
	 */
	public boolean isProfiloAzienda() {
		return profiloAzienda;
	}

	/**
	 * @param profiloAzienda the profiloAzienda to set
	 */
	public void setProfiloAzienda(boolean profiloAzienda) {
		this.profiloAzienda = profiloAzienda;
	}

	/**
	 * @return the profiloDelega
	 */
	public boolean isProfiloDelega() {
		return profiloDelega;
	}

	/**
	 * @param profiloDelega the profiloDelega to set
	 */
	public void setProfiloDelega(boolean profiloDelega) {
		this.profiloDelega = profiloDelega;
	}

	/**
	 * @return the delega
	 */
	public Delega getDelega() {
		return delega;
	}

	/**
	 * @param delega the delega to set
	 */
	public void setDelega(Delega delega) {
		this.delega = delega;
	}

	/**
	 * @return the statoPin
	 */
	public String getStatoPin() {
		return statoPin;
	}

	/**
	 * @param statoPin the statoPin to set
	 */
	public void setStatoPin(String statoPin) {
		this.statoPin = statoPin;
	}

	/**
	 * @return the comuneISAObject
	 */
	public ComuneISA getComuneISAObject() {
		return comuneISAObject;
	}

	/**
	 * @param comuneISAObject the comuneISAObject to set
	 */
	public void setComuneISAObject(ComuneISA comuneISAObject) {
		this.comuneISAObject = comuneISAObject;
	}

}
