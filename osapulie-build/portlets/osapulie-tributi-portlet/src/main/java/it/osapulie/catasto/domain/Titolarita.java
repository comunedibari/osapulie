package it.osapulie.catasto.domain;

import java.io.Serializable;

public class Titolarita implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1504538839119396197L;
	
	
	private String codiceAmministrativo;
	private String sezione;
	private Terreno terreno;

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

	public Terreno getTerreno() {
		return terreno;
	}

	public void setTerreno(Terreno terreno) {
		this.terreno = terreno;
	}
    
}
