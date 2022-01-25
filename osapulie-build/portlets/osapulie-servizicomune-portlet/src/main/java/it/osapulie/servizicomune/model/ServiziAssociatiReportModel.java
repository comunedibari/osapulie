package it.osapulie.servizicomune.model;

import java.io.Serializable;

public class ServiziAssociatiReportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeServizio;
	private String descrizione;
	private String codiceServizio;
	/**
	 * @return the nomeServizio
	 */
	public String getNomeServizio() {
		return nomeServizio;
	}
	/**
	 * @param nomeServizio the nomeServizio to set
	 */
	public void setNomeServizio(String nomeServizio) {
		this.nomeServizio = nomeServizio;
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
	 * @return the codiceServizio
	 */
	public String getCodiceServizio() {
		return codiceServizio;
	}
	/**
	 * @param codiceServizio the codiceServizio to set
	 */
	public void setCodiceServizio(String codiceServizio) {
		this.codiceServizio = codiceServizio;
	}
	
	

}
