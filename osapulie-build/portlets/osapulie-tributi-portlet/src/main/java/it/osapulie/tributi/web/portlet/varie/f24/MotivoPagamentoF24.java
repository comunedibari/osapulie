/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.varie.f24;

/**
 * @author Gianluca Pindinelli
 *
 */
public class MotivoPagamentoF24 {

	String sezione;
	String codiceTributo;
	String codiceEnte;
	Boolean ravvedimento;
	Boolean acconto;
	Boolean saldo;
	Boolean immobiliVariati;
	String numeroImmobili;
	String rateazioneMeseRiferimento;
	String annoRiferimento;
	String detrazione;
	String importiADebitoVersati;
	String importiACreditoCompensati;

	/**
	 * @return the sezione
	 */
	public String getSezione() {
		return sezione;
	}

	/**
	 * @param sezione the sezione to set
	 */
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	/**
	 * @return the codiceTributo
	 */
	public String getCodiceTributo() {
		return codiceTributo;
	}

	/**
	 * @param codiceTributo the codiceTributo to set
	 */
	public void setCodiceTributo(String codiceTributo) {
		this.codiceTributo = codiceTributo;
	}

	/**
	 * @return the codiceEnte
	 */
	public String getCodiceEnte() {
		return codiceEnte;
	}

	/**
	 * @param codiceEnte the codiceEnte to set
	 */
	public void setCodiceEnte(String codiceEnte) {
		this.codiceEnte = codiceEnte;
	}

	/**
	 * @return the ravvedimento
	 */
	public Boolean getRavvedimento() {
		return ravvedimento;
	}

	/**
	 * @param ravvedimento the ravvedimento to set
	 */
	public void setRavvedimento(Boolean ravvedimento) {
		this.ravvedimento = ravvedimento;
	}

	/**
	 * @return the acconto
	 */
	public Boolean getAcconto() {
		return acconto;
	}

	/**
	 * @param acconto the acconto to set
	 */
	public void setAcconto(Boolean acconto) {
		this.acconto = acconto;
	}

	/**
	 * @return the saldo
	 */
	public Boolean getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Boolean saldo) {
		this.saldo = saldo;
	}

	/**
	 * @return the immobiliVariati
	 */
	public Boolean getImmobiliVariati() {
		return immobiliVariati;
	}

	/**
	 * @param immobiliVariati the immobiliVariati to set
	 */
	public void setImmobiliVariati(Boolean immobiliVariati) {
		this.immobiliVariati = immobiliVariati;
	}

	/**
	 * @return the numeroImmobili
	 */
	public String getNumeroImmobili() {
		return numeroImmobili;
	}

	/**
	 * @param numeroImmobili the numeroImmobili to set
	 */
	public void setNumeroImmobili(String numeroImmobili) {
		this.numeroImmobili = numeroImmobili;
	}

	/**
	 * @return the rateazioneMeseRiferimento
	 */
	public String getRateazioneMeseRiferimento() {
		return rateazioneMeseRiferimento;
	}

	/**
	 * @param rateazioneMeseRiferimento the rateazioneMeseRiferimento to set
	 */
	public void setRateazioneMeseRiferimento(String rateazioneMeseRiferimento) {
		this.rateazioneMeseRiferimento = rateazioneMeseRiferimento;
	}

	/**
	 * @return the annoRiferimento
	 */
	public String getAnnoRiferimento() {
		return annoRiferimento;
	}

	/**
	 * @param annoRiferimento the annoRiferimento to set
	 */
	public void setAnnoRiferimento(String annoRiferimento) {
		this.annoRiferimento = annoRiferimento;
	}

	/**
	 * @return the detrazione
	 */
	public String getDetrazione() {
		return detrazione;
	}

	/**
	 * @param detrazione the detrazione to set
	 */
	public void setDetrazione(String detrazione) {
		this.detrazione = detrazione;
	}

	/**
	 * @return the importiADebitoVersati
	 */
	public String getImportiADebitoVersati() {
		return importiADebitoVersati;
	}

	/**
	 * @param importiADebitoVersati the importiADebitoVersati to set
	 */
	public void setImportiADebitoVersati(String importiADebitoVersati) {
		this.importiADebitoVersati = importiADebitoVersati;
	}

	/**
	 * @return the importiACreditoCompensati
	 */
	public String getImportiACreditoCompensati() {
		return importiACreditoCompensati;
	}

	/**
	 * @param importiACreditoCompensati the importiACreditoCompensati to set
	 */
	public void setImportiACreditoCompensati(String importiACreditoCompensati) {
		this.importiACreditoCompensati = importiACreditoCompensati;
	}
}
