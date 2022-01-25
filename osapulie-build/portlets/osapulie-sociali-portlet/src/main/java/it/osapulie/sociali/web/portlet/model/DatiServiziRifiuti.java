package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;

public class DatiServiziRifiuti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cf;
	private String nome;
	private String cognome;
	private String comune;
	private String provincia;
	private String indirizzo;
	private String modalitaContatto;
	private String telefono;
	private String email;
	
	private String[] materiali;
	private String altro;
	private String note;

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
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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
	public String getAltro() {
		return altro;
	}
	public void setAltro(String altro) {
		this.altro = altro;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getModalitaContatto() {
		return modalitaContatto;
	}
	public void setModalitaContatto(String modalitaContatto) {
		this.modalitaContatto = modalitaContatto;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String[] getMateriali() {
		return materiali;
	}
	public void setMateriali(String[] materiali) {
		this.materiali = materiali;
	}
	
	
}
