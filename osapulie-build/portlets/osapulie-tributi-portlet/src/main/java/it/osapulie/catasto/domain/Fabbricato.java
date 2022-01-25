package it.osapulie.catasto.domain;

import java.io.Serializable;

public class Fabbricato implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -687900378344632985L;
	
	private String codiceAmministrativo;
	private String sezione;
	private String sezioneUrbana;
	private String foglio;
	private String particella;
	private String subalterno;
	private String zona;
	private int consistenza;
	private int superficie;
	private double rendita;
	private String categoria;
	private String classe;
	private String indirizzo;
	private String civico;
	
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
	public String getSezioneUrbana() {
		return sezioneUrbana;
	}
	public void setSezioneUrbana(String sezioneUrbana) {
		this.sezioneUrbana = sezioneUrbana;
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
	public String getSubalterno() {
		return subalterno;
	}
	public void setSubalterno(String subalterno) {
		this.subalterno = subalterno;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public int getConsistenza() {
		return consistenza;
	}
	public void setConsistenza(int i) {
		this.consistenza = i;
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
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getCivico() {
		return civico;
	}
	public void setCivico(String civico) {
		this.civico = civico;
	}
	public int getSuperficie() {
		return superficie;
	}
	public void setSuperficie(int superficie) {
		this.superficie = superficie;
	}
	public double getRendita() {
		return rendita;
	}
	public void setRendita(double rendita) {
		this.rendita = rendita;
	}

}
