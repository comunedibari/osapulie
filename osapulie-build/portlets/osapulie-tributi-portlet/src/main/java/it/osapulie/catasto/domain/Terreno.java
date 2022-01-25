package it.osapulie.catasto.domain;

import java.io.Serializable;

public class Terreno implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7895527657122764347L;
	
	private String codiceAmministrativo;
	private String sezione;
	private String foglio;
	private String particella;
	private String subalterno;
	private String classe;
	private String tipoNota;
	private String numeroNota;
	private String progressivoNota;
	private int  annoNota;
	private String superficie;
	private String deduzione;
	private double redditoDominicale;
	private double redditoAgrario;

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
	public String getSubalterno() {
		return subalterno;
	}
	public void setSubalterno(String subalterno) {
		this.subalterno = subalterno;
	}
	public String getClasse() {
		return classe;
	}
	public void setClasse(String classe) {
		this.classe = classe;
	}
	public String getNumeroNota() {
		return numeroNota;
	}
	public void setNumeroNota(String numeroNota) {
		this.numeroNota = numeroNota;
	}
	public String getProgressivoNota() {
		return progressivoNota;
	}
	public void setProgressivoNota(String progressivoNota) {
		this.progressivoNota = progressivoNota;
	}
	public String getFoglio() {
		return foglio;
	}
	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}
	public String getParticella() {
		return particella;
	}
	public void setParticella(String particella) {
		this.particella = particella;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}
	public int getAnnoNota() {
		return annoNota;
	}
	public void setAnnoNota(int annoNota) {
		this.annoNota = annoNota;
	}
	public String getSuperficie() {
		return superficie;
	}
	public void setSuperficie(String superficie) {
		this.superficie = superficie;
	}
	public String getDeduzione() {
		return deduzione;
	}
	public void setDeduzione(String deduzione) {
		this.deduzione = deduzione;
	}
	public double getRedditoDominicale() {
		return redditoDominicale;
	}
	public void setRedditoDominicale(double redditoDominicale) {
		this.redditoDominicale = redditoDominicale;
	}
	public double getRedditoAgrario() {
		return redditoAgrario;
	}
	public void setRedditoAgrario(double redditoAgrario) {
		this.redditoAgrario = redditoAgrario;
	}
}
