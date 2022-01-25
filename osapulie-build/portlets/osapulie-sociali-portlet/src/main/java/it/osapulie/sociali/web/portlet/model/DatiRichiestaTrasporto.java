package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;

public class DatiRichiestaTrasporto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String tipoRichiesta;

	private String cfRichiedente;
	private String nomeRichiedente;
	private String cognomeRichiedente;
	private String tipoRichiedente;
	
	/*Dati del disabile*/
	private String disNome;
	private String disCognome;
	private String disDataNascita;
	private String disComuneNasc;
	private String disProvinciaNasc;
	private String disComuneRes;
	private String disProvinciaRes;
	private String disIndirizzoRes;
	private String disNumCivico;
	private String disTelefono;
	private String disEmail;
	
	private boolean sediaARotelle;
	
	private String isee;
	private String annoIsee;
	private String tipoSportello;
	private String sportello;
	private String indirizzoSportello;
	
	public String getTipoRichiesta() {
		return tipoRichiesta;
	}
	public void setTipoRichiesta(String tipoRichiesta) {
		this.tipoRichiesta = tipoRichiesta;
	}
	public String getNomeRichiedente() {
		return nomeRichiedente;
	}
	public void setNomeRichiedente(String nomeRichiedente) {
		this.nomeRichiedente = nomeRichiedente;
	}
	public String getCognomeRichiedente() {
		return cognomeRichiedente;
	}
	public void setCognomeRichiedente(String cognomeRichiedente) {
		this.cognomeRichiedente = cognomeRichiedente;
	}
	public String getTipoRichiedente() {
		return tipoRichiedente;
	}
	public void setTipoRichiedente(String tipoRichiedente) {
		this.tipoRichiedente = tipoRichiedente;
	}
	public String getDisNome() {
		return disNome;
	}
	public void setDisNome(String disNome) {
		this.disNome = disNome;
	}
	public String getDisCognome() {
		return disCognome;
	}
	public void setDisCognome(String disCognome) {
		this.disCognome = disCognome;
	}
	public String getDisDataNascita() {
		return disDataNascita;
	}
	public void setDisDataNascita(String disDataNascita) {
		this.disDataNascita = disDataNascita;
	}
	public String getDisTelefono() {
		return disTelefono;
	}
	public void setDisTelefono(String disTelefono) {
		this.disTelefono = disTelefono;
	}
	public String getDisEmail() {
		return disEmail;
	}
	public void setDisEmail(String disEmail) {
		this.disEmail = disEmail;
	}
	public boolean isSediaARotelle() {
		return sediaARotelle;
	}
	public void setSediaARotelle(boolean sediaARotelle) {
		this.sediaARotelle = sediaARotelle;
	}
	public String getIsee() {
		return isee;
	}
	public void setIsee(String isee) {
		this.isee = isee;
	}
	public String getTipoSportello() {
		return tipoSportello;
	}
	public void setTipoSportello(String tipoSportello) {
		this.tipoSportello = tipoSportello;
	}
	public String getIndirizzoSportello() {
		return indirizzoSportello;
	}
	public void setIndirizzoSportello(String indirizzoSportello) {
		this.indirizzoSportello = indirizzoSportello;
	}
	public String getDisNumCivico() {
		return disNumCivico;
	}
	public void setDisNumCivico(String disNumCivico) {
		this.disNumCivico = disNumCivico;
	}
	public String getCfRichiedente() {
		return cfRichiedente;
	}
	public void setCfRichiedente(String cfRichiedente) {
		this.cfRichiedente = cfRichiedente;
	}
	public String getDisComuneNasc() {
		return disComuneNasc;
	}
	public void setDisComuneNasc(String disComuneNasc) {
		this.disComuneNasc = disComuneNasc;
	}
	public String getDisProvinciaNasc() {
		return disProvinciaNasc;
	}
	public void setDisProvinciaNasc(String disProvinciaNasc) {
		this.disProvinciaNasc = disProvinciaNasc;
	}
	public String getDisComuneRes() {
		return disComuneRes;
	}
	public void setDisComuneRes(String disComuneRes) {
		this.disComuneRes = disComuneRes;
	}
	public String getDisProvinciaRes() {
		return disProvinciaRes;
	}
	public void setDisProvinciaRes(String disProvinciaRes) {
		this.disProvinciaRes = disProvinciaRes;
	}
	public String getDisIndirizzoRes() {
		return disIndirizzoRes;
	}
	public void setDisIndirizzoRes(String disIndirizzoRes) {
		this.disIndirizzoRes = disIndirizzoRes;
	}
	public String getSportello() {
		return sportello;
	}
	public void setSportello(String sportello) {
		this.sportello = sportello;
	}
	public String getAnnoIsee() {
		return annoIsee;
	}
	public void setAnnoIsee(String annoIsee) {
		this.annoIsee = annoIsee;
	}
	
	
	
}
