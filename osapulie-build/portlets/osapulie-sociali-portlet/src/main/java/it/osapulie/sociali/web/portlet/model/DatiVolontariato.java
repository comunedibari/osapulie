package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;

public class DatiVolontariato implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cf;
	private String nome;
	private String cognome;
	private String ruolo;
	private String organizzazione;
	private String cap;
	private String indirizzo;
	private String telefono;
	private String cfAssociazione;
	private String dataCostituzione;
	private String comuneCostituzione;
	private String provCostituzione;
	private String[] aree;

	//Articoli
	private String artDemocraticita;
	private String artNoFiniLucro;
	private String artElettivitaCariche;
	private String artGratuitaPrestaz;
	private String artCriteriAmmissione;
	private String artDirittiEObblighi;
	private String artFormazione;
	private String artDevoluzione;
	
	/*
	 * Campi utilizzati nella portlet di iscrizione delle Associazioni di Promozione Sociale
	 * */
	private String artSedeLegale;
	private String artRappLegale;
	private String artReinvAvanzi;
	private String artUtilSociale;
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
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
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getOrganizzazione() {
		return organizzazione;
	}
	public void setOrganizzazione(String organizzazione) {
		this.organizzazione = organizzazione;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getCfAssociazione() {
		return cfAssociazione;
	}
	public void setCfAssociazione(String cfAssociazione) {
		this.cfAssociazione = cfAssociazione;
	}
	public String getDataCostituzione() {
		return dataCostituzione;
	}
	public void setDataCostituzione(String dataCostituzione) {
		this.dataCostituzione = dataCostituzione;
	}
	public String getComuneCostituzione() {
		return comuneCostituzione;
	}
	public void setComuneCostituzione(String comuneCostituzione) {
		this.comuneCostituzione = comuneCostituzione;
	}
	public String getProvCostituzione() {
		return provCostituzione;
	}
	public void setProvCostituzione(String provCostituzione) {
		this.provCostituzione = provCostituzione;
	}
	public String[] getAree() {
		return aree;
	}
	public void setAree(String[] aree) {
		this.aree = aree;
	}
	public String getArtDemocraticita() {
		return artDemocraticita;
	}
	public void setArtDemocraticita(String artDemocraticita) {
		this.artDemocraticita = artDemocraticita;
	}
	public String getArtNoFiniLucro() {
		return artNoFiniLucro;
	}
	public void setArtNoFiniLucro(String artNoFiniLucro) {
		this.artNoFiniLucro = artNoFiniLucro;
	}
	public String getArtElettivitaCariche() {
		return artElettivitaCariche;
	}
	public void setArtElettivitaCariche(String artElettivitaCariche) {
		this.artElettivitaCariche = artElettivitaCariche;
	}
	public String getArtGratuitaPrestaz() {
		return artGratuitaPrestaz;
	}
	public void setArtGratuitaPrestaz(String artGratuitaPrestaz) {
		this.artGratuitaPrestaz = artGratuitaPrestaz;
	}
	public String getArtCriteriAmmissione() {
		return artCriteriAmmissione;
	}
	public void setArtCriteriAmmissione(String artCriteriAmmissione) {
		this.artCriteriAmmissione = artCriteriAmmissione;
	}
	public String getArtDirittiEObblighi() {
		return artDirittiEObblighi;
	}
	public void setArtDirittiEObblighi(String artDirittiEObblighi) {
		this.artDirittiEObblighi = artDirittiEObblighi;
	}
	public String getArtFormazione() {
		return artFormazione;
	}
	public void setArtFormazione(String artFormazione) {
		this.artFormazione = artFormazione;
	}
	public String getArtDevoluzione() {
		return artDevoluzione;
	}
	public void setArtDevoluzione(String artDevoluzione) {
		this.artDevoluzione = artDevoluzione;
	}
	public String getArtSedeLegale() {
		return artSedeLegale;
	}
	public void setArtSedeLegale(String artSedeLegale) {
		this.artSedeLegale = artSedeLegale;
	}
	public String getArtRappLegale() {
		return artRappLegale;
	}
	public void setArtRappLegale(String artRappLegale) {
		this.artRappLegale = artRappLegale;
	}
	public String getArtReinvAvanzi() {
		return artReinvAvanzi;
	}
	public void setArtReinvAvanzi(String artReinvAvanzi) {
		this.artReinvAvanzi = artReinvAvanzi;
	}
	public String getArtUtilSociale() {
		return artUtilSociale;
	}
	public void setArtUtilSociale(String artUtilSociale) {
		this.artUtilSociale = artUtilSociale;
	}
	
}
