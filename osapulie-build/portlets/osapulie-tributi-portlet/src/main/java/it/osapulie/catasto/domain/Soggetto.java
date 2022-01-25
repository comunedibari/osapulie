package it.osapulie.catasto.domain;

import java.io.Serializable;

public class Soggetto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1708756348254792982L;
	
	private int idSoggetto;

	private String codiceAmministrativo;
	private String sezione;
	private String cognome;
	private String nome;	
	private String sesso;
	private String dataNascita;
	private String luogoNascita;
	private String codiceFiscale;

	public String getCodiceAmministrativo() {
		return codiceAmministrativo;
	}

	public void setCodiceAmministrativo(String codiceAmministrativo) {
		this.codiceAmministrativo = codiceAmministrativo;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
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

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}
	
}
