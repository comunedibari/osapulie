/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.web.ws.domain;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Richiesta di pagamento.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PagamentoTributoRequest {

	private String codiceIstatComune;
	private String codiceFiscale;
	private String codiceTributo;
	private String idPagamento;
	private BigDecimal importoTotale;
	private BigDecimal commissioni;
	private String idTransazione;
	private Date dataPagamento;
	private String canale;
	private String mezzoPagamento;
	private String idMovimento;

	/**
	 * @return the codiceIstatComune
	 */
	public String getCodiceIstatComune() {
		return codiceIstatComune;
	}

	/**
	 * @param codiceIstatComune the codiceIstatComune to set
	 */
	public void setCodiceIstatComune(String codiceIstatComune) {
		this.codiceIstatComune = codiceIstatComune;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
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
	 * @return the idPagamento
	 */
	public String getIdPagamento() {
		return idPagamento;
	}

	/**
	 * @param idPagamento the idPagamento to set
	 */
	public void setIdPagamento(String idPagamento) {
		this.idPagamento = idPagamento;
	}

	/**
	 * @return the idTransazione
	 */
	public String getIdTransazione() {
		return idTransazione;
	}

	/**
	 * @param idTransazione the idTransazione to set
	 */
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}

	/**
	 * @return the dataPagamento
	 */
	public Date getDataPagamento() {
		return dataPagamento;
	}

	/**
	 * @param dataPagamento the dataPagamento to set
	 */
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	/**
	 * @return the canale
	 */
	public String getCanale() {
		return canale;
	}

	/**
	 * @param canale the canale to set
	 */
	public void setCanale(String canale) {
		this.canale = canale;
	}

	/**
	 * @return the mezzoPagamento
	 */
	public String getMezzoPagamento() {
		return mezzoPagamento;
	}

	/**
	 * @param mezzoPagamento the mezzoPagamento to set
	 */
	public void setMezzoPagamento(String mezzoPagamento) {
		this.mezzoPagamento = mezzoPagamento;
	}

	/**
	 * @return the idMovimento
	 */
	public String getIdMovimento() {
		return idMovimento;
	}

	/**
	 * @param idMovimento the idMovimento to set
	 */
	public void setIdMovimento(String idMovimento) {
		this.idMovimento = idMovimento;
	}

	/**
	 * @return the commissioni
	 */
	public BigDecimal getCommissioni() {
		return commissioni;
	}

	/**
	 * @param commissioni the commissioni to set
	 */
	public void setCommissioni(BigDecimal commissioni) {
		this.commissioni = commissioni;
	}

	/**
	 * @return the importoTotale
	 */
	public BigDecimal getImportoTotale() {
		return importoTotale;
	}

	/**
	 * @param importoTotale the importoTotale to set
	 */
	public void setImportoTotale(BigDecimal importoTotale) {
		this.importoTotale = importoTotale;
	}

}
