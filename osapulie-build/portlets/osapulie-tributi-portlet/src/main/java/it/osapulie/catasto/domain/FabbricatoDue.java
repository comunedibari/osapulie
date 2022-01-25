package it.osapulie.catasto.domain;

import java.io.Serializable;

public class FabbricatoDue implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2204614564065484044L;
	
	private String codiceAmministrativo;
	private String sezione;
	private String foglio;
	private String numero;
	private String sublaterno;
	
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
	public String getFoglio() {
		return foglio;
	}
	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getSublaterno() {
		return sublaterno;
	}
	public void setSublaterno(String sublaterno) {
		this.sublaterno = sublaterno;
	}
	
	
}
