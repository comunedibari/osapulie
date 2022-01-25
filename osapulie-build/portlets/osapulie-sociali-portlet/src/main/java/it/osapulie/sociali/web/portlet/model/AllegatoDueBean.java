package it.osapulie.sociali.web.portlet.model;

import java.util.List;

public class AllegatoDueBean {
	
	private String nome;
	private String cognome;
	private String comuneNascita;
	private String provinciaNascita;
	private String dataNascita;
	private String ruolo;
	private String reddito;
	private String altro;
	private List<Parente> parenti;

	public String getReddito() {
		return reddito;
	}
	public void setReddito(String reddito) {
		this.reddito = reddito;
	}
	public String getAltro() {
		return altro;
	}
	public void setAltro(String altro) {
		this.altro = altro;
	}
	public List<Parente> getParenti() {
		return parenti;
	}
	public void setParenti(List<Parente> parenti) {
		this.parenti = parenti;
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
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	
}
