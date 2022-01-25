/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import it.osapulie.domain.fascicoloutente.FascicoloUtente;

/**
 * Ogni cittadino ha associato un {@link ProfiloUtenteCittadino}, identificato dal codice utente del
 * cittadino (che è unico all'interno del sistema).
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */

@Entity
@Table(name = "tb_profilo_utente_cittadino")
public class ProfiloUtenteCittadino extends AbstractPersistable<Long> {

	private static final long serialVersionUID = -1021919152275272114L;

	@Column(name = "cognome", nullable = false, length = 128)
	private String cognome;
 
	@Column(name = "nome", nullable = false, length = 128)
	private String nome;

	@Column(name = "codice_fiscale", unique = true, nullable = false, length = 16)
	private String codiceFiscale;

	@Column(name = "mail", nullable = false, length = 128)
	private String mail;

	/**
	 * Campo mail di PEC
	 */
	@Column(name = "mail_pec", nullable = true, length = 128)
	private String mailPec;

	/**
	 * Stato autenticazione forte: è il campo che indica se un cittadino è autenticato in modo forte
	 * (perchè ha assegnata una nuova password da parte del comune di appartenenza)
	 */
	@Column(name = "autenticazione_forte")
	private boolean autenticazioneForte;

	/**
	 * Livello autenticazione: è il campo che indica il livello di autenticazione con cui il
	 * cittadino ha effettuato l'accesso.
	 */
	@Column(name = "livello_autenticazione")
	private Integer livelloAutenticazione;

	/**
	 * Stringa che identifica il canale di comunicazione preferito dal comune. Per la codifica
	 * vedere la classe <code>PorteltConstants</code>.
	 */
	@Column(name = "canale_comunicazione", nullable = true)
	private String canaleComunicazione;

	/**
	 * Data di aggancio smart card al profilo
	 */
	@Temporal(TemporalType.DATE)
	@Column(name = "data_autenticazione_forte", nullable = true)
	private Date dataAutenticazioneForte;

	/**
	 * Identifica il canale con cui l'utente ha effettuato l'autenticazione.
	 */
	@Column(name = "canale_autenticazione", nullable = true)
	private String canaleAutenticazione;

	/**
	 * Informazioni aggiuntive in formato JSON.
	 */
	@Column(name = "info_aggiuntive", nullable = true)
	private String infoAggiuntive;

	@Transient
	private String password;

	@Embedded
	private Indirizzo residenza;

	@JoinColumn(name = "fk_comune_isa", nullable = false)
	private ComuneISA comuneIsa;

	@OneToOne(mappedBy = "gestoreComune", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private ComuneISA gestoreComuneIsa;
	@JsonIgnore
	@OneToMany(mappedBy = "profiloUtenteCittadino", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende;
	@JsonIgnore
	@OneToMany(mappedBy = "delegante", cascade = CascadeType.ALL)
	private List<Delega> delegheDelegati;
	@JsonIgnore
	@OneToMany(mappedBy = "profiloUtenteCittadino", cascade = CascadeType.ALL)
	private List<Pin> pinList;
	@JsonIgnore
	@OneToOne(mappedBy = "cittadino", cascade = CascadeType.ALL)
	private FascicoloUtente fascicoloUtente;
	@JsonIgnore
	@OneToMany(mappedBy = "responsabile", cascade = CascadeType.REMOVE)
	private List<Azienda> aziende;
	@JsonIgnore
	@OneToMany(mappedBy = "profiloUtenteCittadino", cascade = CascadeType.REMOVE)
	private List<Bozza> bozze;

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProfiloUtenteCittadino [id=" + getId() + ", cognome=" + getCognome() + ", nome=" + getNome() + ", codiceFiscale=" + getCodiceFiscale() + ", residenza=" + getResidenza() + "]";
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.data.jpa.domain.AbstractPersistable#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return this.getId().longValue() == ((ProfiloUtenteCittadino) obj).getId().longValue();
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
	 * @return the residenza
	 */
	public Indirizzo getResidenza() {
		return residenza;
	}

	/**
	 * @param residenza the residenza to set
	 */
	public void setResidenza(Indirizzo residenza) {
		this.residenza = residenza;
	}

	/**
	 * @return the comuneIsa
	 */
	public ComuneISA getComuneIsa() {
		return comuneIsa;
	}

	/**
	 * @param comuneIsa the comuneIsa to set
	 */
	public void setComuneIsa(ComuneISA comuneIsa) {
		this.comuneIsa = comuneIsa;
	}

	/**
	 * @return the gestoreComuneIsa
	 */
	public ComuneISA getGestoreComuneIsa() {
		return gestoreComuneIsa;
	}

	/**
	 * @param gestoreComuneIsa the gestoreComuneIsa to set
	 */
	public void setGestoreComuneIsa(ComuneISA gestoreComuneIsa) {
		this.gestoreComuneIsa = gestoreComuneIsa;
	}

	/**
	 * @return the delegheDelegati
	 */
	public List<Delega> getDelegheDelegati() {
		return delegheDelegati;
	}

	/**
	 * @param delegheDelegati the delegheDelegati to set
	 */
	public void setDelegheDelegati(List<Delega> delegheDelegati) {
		this.delegheDelegati = delegheDelegati;
	}

	/**
	 * @return the pinList
	 */
	public List<Pin> getPinList() {
		return pinList;
	}

	/**
	 * @param pinList the pinList to set
	 */
	public void setPinList(List<Pin> pinList) {
		this.pinList = pinList;
	}

	/**
	 * @return the fascicoloUtente
	 */
	public FascicoloUtente getFascicoloUtente() {
		return fascicoloUtente;
	}

	/**
	 * @param fascicoloUtente the fascicoloUtente to set
	 */
	public void setFascicoloUtente(FascicoloUtente fascicoloUtente) {
		this.fascicoloUtente = fascicoloUtente;
	}

	/**
	 * @return the aziende
	 */
	public List<Azienda> getAziende() {
		return aziende;
	}

	/**
	 * @param aziende the aziende to set
	 */
	public void setAziende(List<Azienda> aziende) {
		this.aziende = aziende;
	}

	/**
	 * @return the profiliUtenteCittadinoAziende
	 */
	public List<ProfiloUtenteCittadinoAzienda> getProfiliUtenteCittadinoAziende() {
		return profiliUtenteCittadinoAziende;
	}

	/**
	 * @param profiliUtenteCittadinoAziende the profiliUtenteCittadinoAziende to set
	 */
	public void setProfiliUtenteCittadinoAziende(List<ProfiloUtenteCittadinoAzienda> profiliUtenteCittadinoAziende) {
		this.profiliUtenteCittadinoAziende = profiliUtenteCittadinoAziende;
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
	 * @return the bozze
	 */
	public List<Bozza> getBozze() {
		return bozze;
	}

	/**
	 * @param bozze the bozze to set
	 */
	public void setBozze(List<Bozza> bozze) {
		this.bozze = bozze;
	}

}
