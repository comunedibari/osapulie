/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.varie.f24;

import java.util.List;

/**
 * Bean per il modulo F24.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DatiF24 {

	String delegaIrrevocabileA;
	String agenzia;
	String agenziaProv;

	String codFiscale;
	String codUfficio;
	String codAtto;
	String cognome;
	String nome;
	String dataDiNascita;
	String sesso;
	String comuneDiNascita;
	String provDiNascita;
	String codFiscaleAltro;
	String codIdentificativo;
	String iban;
	String totale;

	List<MotivoPagamentoF24> motiviPagamento;

	/**
	 * @return the delegaIrrevocabileA
	 */
	public String getDelegaIrrevocabileA() {
		return delegaIrrevocabileA;
	}

	/**
	 * @param delegaIrrevocabileA the delegaIrrevocabileA to set
	 */
	public void setDelegaIrrevocabileA(String delegaIrrevocabileA) {
		this.delegaIrrevocabileA = delegaIrrevocabileA;
	}

	/**
	 * @return the agenzia
	 */
	public String getAgenzia() {
		return agenzia;
	}

	/**
	 * @param agenzia the agenzia to set
	 */
	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}

	/**
	 * @return the agenziaProv
	 */
	public String getAgenziaProv() {
		return agenziaProv;
	}

	/**
	 * @param agenziaProv the agenziaProv to set
	 */
	public void setAgenziaProv(String agenziaProv) {
		this.agenziaProv = agenziaProv;
	}

	/**
	 * @return the codFiscale
	 */
	public String getCodFiscale() {
		return codFiscale;
	}

	/**
	 * @param codFiscale the codFiscale to set
	 */
	public void setCodFiscale(String codFiscale) {
		this.codFiscale = codFiscale;
	}

	/**
	 * @return the codUfficio
	 */
	public String getCodUfficio() {
		return codUfficio;
	}

	/**
	 * @param codUfficio the codUfficio to set
	 */
	public void setCodUfficio(String codUfficio) {
		this.codUfficio = codUfficio;
	}

	/**
	 * @return the codAtto
	 */
	public String getCodAtto() {
		return codAtto;
	}

	/**
	 * @param codAtto the codAtto to set
	 */
	public void setCodAtto(String codAtto) {
		this.codAtto = codAtto;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
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
	 * @return the dataDiNascita
	 */
	public String getDataDiNascita() {
		return dataDiNascita;
	}

	/**
	 * @param dataDiNascita the dataDiNascita to set
	 */
	public void setDataDiNascita(String dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the comuneDiNascita
	 */
	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	/**
	 * @param comuneDiNascita the comuneDiNascita to set
	 */
	public void setComuneDiNascita(String comuneDiNascita) {
		this.comuneDiNascita = comuneDiNascita;
	}

	/**
	 * @return the provDiNascita
	 */
	public String getProvDiNascita() {
		return provDiNascita;
	}

	/**
	 * @param provDiNascita the provDiNascita to set
	 */
	public void setProvDiNascita(String provDiNascita) {
		this.provDiNascita = provDiNascita;
	}

	/**
	 * @return the codFiscaleAltro
	 */
	public String getCodFiscaleAltro() {
		return codFiscaleAltro;
	}

	/**
	 * @param codFiscaleAltro the codFiscaleAltro to set
	 */
	public void setCodFiscaleAltro(String codFiscaleAltro) {
		this.codFiscaleAltro = codFiscaleAltro;
	}

	/**
	 * @return the codIdentificativo
	 */
	public String getCodIdentificativo() {
		return codIdentificativo;
	}

	/**
	 * @param codIdentificativo the codIdentificativo to set
	 */
	public void setCodIdentificativo(String codIdentificativo) {
		this.codIdentificativo = codIdentificativo;
	}

	/**
	 * @return the iban
	 */
	public String getIban() {
		return iban;
	}

	/**
	 * @param iban the iban to set
	 */
	public void setIban(String iban) {
		this.iban = iban;
	}

	/**
	 * @return the totale
	 */
	public String getTotale() {
		return totale;
	}

	/**
	 * @param totale the totale to set
	 */
	public void setTotale(String totale) {
		this.totale = totale;
	}

	/**
	 * @return the motiviPagamento
	 */
	public List<MotivoPagamentoF24> getMotiviPagamento() {
		return motiviPagamento;
	}

	/**
	 * @param motiviPagamento the motiviPagamento to set
	 */
	public void setMotiviPagamento(List<MotivoPagamentoF24> motiviPagamento) {
		this.motiviPagamento = motiviPagamento;
	}

}
