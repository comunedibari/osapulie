package it.osapulie.servizicomune.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class DatiRicercaCircolaritaAnagrafica implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1144107219923104831L;
	
	@NotNull
	@NotEmpty
	private String codiceIstat;
	@NotNull	
	@NotEmpty
	private String codiceFiscale;
	
	public String getCodiceIstat() {
		return codiceIstat;
	}
	public void setCodiceIstat(String codiceIstat) {
		this.codiceIstat = codiceIstat;
	}
	public String getCodiceFiscale() {
		return codiceFiscale;
	}
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	} 
	
}
