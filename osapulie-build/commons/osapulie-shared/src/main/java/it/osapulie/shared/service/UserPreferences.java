/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.shared.service;

import java.io.Serializable;

/**
 * Classe contenente le preferenze dell'utente loggato.
 *
 * @author Gianluca Pindinelli
 *
 */
public class UserPreferences implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 8811350315135430441L;

	private Long idComuneIsa;
	private String codiceIstatComune;
	private String uriServizioGateway;
	private String nomeComune;

	/**
	 * Definisce il codice fiscale con il quale si richiama un servizio. Questa variabile è (e deve
	 * essere) <u>sempre valorizzata</u> dal sistema con il codice fiscale dell'utente attualmente
	 * loggato.
	 */
	private String codiceFiscaleServizio;

	/**
	 * Definisce la Partita IVA con la quale l'utente effettua le operazioni relative
	 * all'azienda/professionista corrispondente.
	 */
	private String partitaIvaServizio;
	/**
	 * Id della delega selezionata dal professionista loggato nel sistema: definisce la delega con
	 * la quale si sta operando sul portale ISA.
	 */
	private Long idDelega;

	public String getNomeComune() {
		return nomeComune;
	}

	public void setNomeComune(String nomeComune) {
		this.nomeComune = nomeComune;
	}

	public String getUriServizioGateway() {
		return uriServizioGateway;
	}

	public void setUriServizioGateway(String uriServizioGateway) {
		this.uriServizioGateway = uriServizioGateway;
	}

	public String getCodiceIstatComune() {
		return codiceIstatComune;
	}

	public void setCodiceIstatComune(String codiceIstatComune) {
		this.codiceIstatComune = codiceIstatComune;
	}

	public Long getIdDelega() {
		return idDelega;
	}

	public void setIdDelega(Long idDelega) {
		this.idDelega = idDelega;
	}

	public Long getIdComuneIsa() {
		return idComuneIsa;
	}

	public void setIdComuneIsa(Long idComuneIsa) {
		this.idComuneIsa = idComuneIsa;
	}

	public String getCodiceFiscaleServizio() {
		return codiceFiscaleServizio;
	}

	public void setCodiceFiscaleServizio(String codiceFiscaleServizio) {
		this.codiceFiscaleServizio = codiceFiscaleServizio;
	}

	/**
	 * @return the partitaIvaServizio
	 */
	public String getPartitaIvaServizio() {
		return partitaIvaServizio;
	}

	/**
	 * @param partitaIvaServizio the partitaIvaServizio to set
	 */
	public void setPartitaIvaServizio(String partitaIvaServizio) {
		this.partitaIvaServizio = partitaIvaServizio;
	}

}
