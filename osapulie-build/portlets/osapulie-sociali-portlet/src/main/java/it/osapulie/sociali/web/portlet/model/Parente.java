package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;

public class Parente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Dati Anagrafici richiedente*/
	private String cf;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String codiceIstatComuneNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String comuneResidenza;
	private String provinciaResidenza;
	private String civicoResidenza;
	private String indirizzoResidenza;
	
	private String nominativo;
	private String parentela;
	private String reddito;
	private boolean alimenti;
	
	public String getReddito() {
		return reddito;
	}
	public void setReddito(String reddito) {
		this.reddito = reddito;
	}
	public String getParentela() {
		return parentela;
	}
	public void setParentela(String parentela) {
		this.parentela = parentela;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getNominativo() {
		return nominativo;
	}
	public void setNominativo(String nominativo) {
		this.nominativo = nominativo;
	}
	public boolean isAlimenti() {
		return alimenti;
	}
	public void setAlimenti(boolean alimenti) {
		this.alimenti = alimenti;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getComuneNascita() {
		return comuneNascita;
	}
	public String getProvinciaNascita() {
		return provinciaNascita;
	}
	public String getComuneResidenza() {
		return comuneResidenza;
	}
	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}
	public String getCivicoResidenza() {
		return civicoResidenza;
	}
	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}
	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}
	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}
	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}
	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}
	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getCodiceIstatComuneNascita() {
		return codiceIstatComuneNascita;
	}
	public void setCodiceIstatComuneNascita(String codiceIstatComuneNascita) {
		this.codiceIstatComuneNascita = codiceIstatComuneNascita;
	}
}
