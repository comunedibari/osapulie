package it.osapulie.catasto.domain;

import java.io.Serializable;

public class FabbricatoUno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3177733482790184235L;
	
	private String codiceAmministrativo;
	private String sezione;
	
	private String categoria;
	private String classe;
	private String tipoNota;
	private String progressivoNota;
	private String annoNota;
	private String numeroNota;

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

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getTipoNota() {
		return tipoNota;
	}

	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}

	public String getProgressivoNota() {
		return progressivoNota;
	}

	public void setProgressivoNota(String progressivoNota) {
		this.progressivoNota = progressivoNota;
	}

	public String getAnnoNota() {
		return annoNota;
	}

	public void setAnnoNota(String annoNota) {
		this.annoNota = annoNota;
	}

	public String getNumeroNota() {
		return numeroNota;
	}

	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
}
