package it.osapulie.tributi.web.portlet.rimborsoservizicimiteriali.form;

import java.io.Serializable;

import it.osapulie.tributi.web.portlet.varie.DatiRimborsoGenerali;

/**
 * Classe contenente i dati per la generazione della richiesta di rimborso servizi cimiteriali.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public class DatiRimborsoServiziCimiteriali extends DatiRimborsoGenerali implements Serializable {

	private static final long serialVersionUID = 1L;

	private String numeroDocumento;
	private String contoCorrente;
	private Double importoDocumento;

	private Double importoDovuto;

	private String descrizione;

	public String getNumeroDocumento() {
		return numeroDocumento;
	}

	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}

	public String getContoCorrente() {
		return contoCorrente;
	}

	public void setContoCorrente(String contoCorrente) {
		this.contoCorrente = contoCorrente;
	}

	public Double getImportoDocumento() {
		return importoDocumento;
	}

	public void setImportoDocumento(Double importoDocumento) {
		this.importoDocumento = importoDocumento;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public void setImportoDovuto(Double importoDovuto) {
		this.importoDovuto = importoDovuto;
	}

	public Double getImportoDovuto() {
		return importoDovuto;
	}
}
