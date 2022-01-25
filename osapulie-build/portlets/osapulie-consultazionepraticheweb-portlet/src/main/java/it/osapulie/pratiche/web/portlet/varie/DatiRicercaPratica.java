package it.osapulie.pratiche.web.portlet.varie;

import java.io.Serializable;

/**
 * Classe contenente i filtri per la ricerca front-end delle pratiche
 * 
 * @author Maria Michela Birtolo
 * 
 */
public class DatiRicercaPratica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String oggetto;
	private String numero;
	private String statopratica;
	private String utente;
	private String tipologia;
	
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getUtente() {
		return utente;
	}
	public void setUtente(String utente) {
		this.utente = utente;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getStatopratica() {
		return statopratica;
	}
	public void setStatopratica(String statopratica) {
		this.statopratica = statopratica;
	}	
}
