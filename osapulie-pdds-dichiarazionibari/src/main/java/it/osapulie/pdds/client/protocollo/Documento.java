/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.client.protocollo;

/**
 * @author Gianluca Pindinelli
 *
 */
public class Documento {

	private String titolo;
	private String nome;
	private String descrizione;
	private byte[] contenuto;
	private boolean principale;
	private String classificazione;

	/**
	 * @return the titolo
	 */
	public String getTitolo() {
		return titolo;
	}

	/**
	 * @param titolo the titolo to set
	 */
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @return the contenuto
	 */
	public byte[] getContenuto() {
		return contenuto;
	}

	/**
	 * @param contenuto the contenuto to set
	 */
	public void setContenuto(byte[] contenuto) {
		this.contenuto = contenuto;
	}

	/**
	 * @return the principale
	 */
	public boolean isPrincipale() {
		return principale;
	}

	/**
	 * @param principale the principale to set
	 */
	public void setPrincipale(boolean principale) {
		this.principale = principale;
	}

	/**
	 * @return the classificazione
	 */
	public String getClassificazione() {
		return classificazione;
	}

	/**
	 * @param classificazione the classificazione to set
	 */
	public void setClassificazione(String classificazione) {
		this.classificazione = classificazione;
	}

}
