package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;

public class DatiDisabiliParcheggio implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982297520946698290L;
	
	/*Dati richiedente*/
	private String cf;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String cittadinanza;
	private String stato;
	private String capResidenza;
	private String comuneResidenza;
	private String provinciaResidenza;
	private String civicoResidenza;
	private String indirizzoResidenza;
	private String telefono;
	private String email;
	private String fax;
	
	private String ruolo;
	
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
	private String disCapRes;
	private String disEmail;
	private String disFax;
	private String disCittadinanza;
	private String disStato;
	
	private String richiesta;
	private String numeroPass;
	private String scadenzaPass;
	
	/*dati della persona delegata*/
	private String delNome;
	private String delCognome;
	private String delDataNascita;
	private String delComuneNasc;
	private String delProvinciaNasc;
	private String delComuneRes;
	private String delProvinciaRes;
	private String delIndirizzoRes;
	private String delNumCivico;
	private String delTelefono;

	
	private boolean trattDatiPersonali;

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

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getCittadinanza() {
		return cittadinanza;
	}

	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
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

	public String getDisNumCivico() {
		return disNumCivico;
	}

	public void setDisNumCivico(String disNumCivico) {
		this.disNumCivico = disNumCivico;
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

	public String getDisFax() {
		return disFax;
	}

	public void setDisFax(String disFax) {
		this.disFax = disFax;
	}

	public String getRichiesta() {
		return richiesta;
	}

	public void setRichiesta(String richiesta) {
		this.richiesta = richiesta;
	}

	public String getNumeroPass() {
		return numeroPass;
	}

	public void setNumeroPass(String numeroPass) {
		this.numeroPass = numeroPass;
	}

	public String getScadenzaPass() {
		return scadenzaPass;
	}

	public void setScadenzaPass(String scadenzaPass) {
		this.scadenzaPass = scadenzaPass;
	}

	public String getDelNome() {
		return delNome;
	}

	public void setDelNome(String delNome) {
		this.delNome = delNome;
	}

	public String getDelCognome() {
		return delCognome;
	}

	public void setDelCognome(String delCognome) {
		this.delCognome = delCognome;
	}

	public String getDelDataNascita() {
		return delDataNascita;
	}

	public void setDelDataNascita(String delDataNascita) {
		this.delDataNascita = delDataNascita;
	}

	public String getDelComuneNasc() {
		return delComuneNasc;
	}

	public void setDelComuneNasc(String delComuneNasc) {
		this.delComuneNasc = delComuneNasc;
	}

	public String getDelProvinciaNasc() {
		return delProvinciaNasc;
	}

	public void setDelProvinciaNasc(String delProvinciaNasc) {
		this.delProvinciaNasc = delProvinciaNasc;
	}

	public String getDelComuneRes() {
		return delComuneRes;
	}

	public void setDelComuneRes(String delComuneRes) {
		this.delComuneRes = delComuneRes;
	}

	public String getDelProvinciaRes() {
		return delProvinciaRes;
	}

	public void setDelProvinciaRes(String delProvinciaRes) {
		this.delProvinciaRes = delProvinciaRes;
	}

	public String getDelIndirizzoRes() {
		return delIndirizzoRes;
	}

	public void setDelIndirizzoRes(String delIndirizzoRes) {
		this.delIndirizzoRes = delIndirizzoRes;
	}

	public String getDelNumCivico() {
		return delNumCivico;
	}

	public void setDelNumCivico(String delNumCivico) {
		this.delNumCivico = delNumCivico;
	}

	public String getDelTelefono() {
		return delTelefono;
	}

	public void setDelTelefono(String delTelefono) {
		this.delTelefono = delTelefono;
	}

	public boolean isTrattDatiPersonali() {
		return trattDatiPersonali;
	}

	public void setTrattDatiPersonali(boolean trattDatiPersonali) {
		this.trattDatiPersonali = trattDatiPersonali;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getDisCapRes() {
		return disCapRes;
	}

	public void setDisCapRes(String disCapRes) {
		this.disCapRes = disCapRes;
	}

	public String getDisCittadinanza() {
		return disCittadinanza;
	}

	public void setDisCittadinanza(String disCittadinanza) {
		this.disCittadinanza = disCittadinanza;
	}

	public String getDisStato() {
		return disStato;
	}

	public void setDisStato(String disStato) {
		this.disStato = disStato;
	}
	
}
