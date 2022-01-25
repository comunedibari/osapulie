/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.domain.dto;

import java.util.HashMap;
import java.util.Map;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.shared.service.UserPreferences;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public class Fascicolo {

	private String servizio;
	private UserPreferences userPreferences;
	private String codiceServizio;
	private boolean ricercabileDaComune;
	private String numeroProtocollo;
	private String checksum;
	private String infoAggiuntive;

	private Map<String, String> infoAggiuntiveMap = new HashMap<String, String>();

	public void addInfoAggiuntive(String key, String value) {
		infoAggiuntiveMap.put(key, value);
	}

	/**
	 * Utente loggato che effettua l'operazione
	 */
	private ProfiloUtenteCittadino idProfiloUtente;

	/**
	 * @return the servizio
	 */
	public String getServizio() {
		return servizio;
	}

	/**
	 * @param servizio the servizio to set
	 */
	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	/**
	 * @return the userPreferences
	 */
	public UserPreferences getUserPreferences() {
		return userPreferences;
	}

	/**
	 * @param userPreferences the userPreferences to set
	 */
	public void setUserPreferences(UserPreferences userPreferences) {
		this.userPreferences = userPreferences;
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

	/**
	 * @return the ricercabileDaComune
	 */
	public boolean isRicercabileDaComune() {
		return ricercabileDaComune;
	}

	/**
	 * @param ricercabileDaComune the ricercabileDaComune to set
	 */
	public void setRicercabileDaComune(boolean ricercabileDaComune) {
		this.ricercabileDaComune = ricercabileDaComune;
	}

	/**
	 * @return the numeroProtocollo
	 */
	public String getNumeroProtocollo() {
		return numeroProtocollo;
	}

	/**
	 * @param numeroProtocollo the numeroProtocollo to set
	 */
	public void setNumeroProtocollo(String numeroProtocollo) {
		this.numeroProtocollo = numeroProtocollo;
	}

	/**
	 * @return the checksum
	 */
	public String getChecksum() {
		return checksum;
	}

	/**
	 * @param checksum the checksum to set
	 */
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}

	/**
	 * @return the infoAggiuntive
	 */
	public String getInfoAggiuntive() {
		return infoAggiuntive;
	}

	/**
	 * @param infoAggiuntive the infoAggiuntive to set
	 */
	public void setInfoAggiuntive(String infoAggiuntive) {
		this.infoAggiuntive = infoAggiuntive;
	}

	/**
	 * @return the idProfiloUtente
	 */
	public ProfiloUtenteCittadino getIdProfiloUtente() {
		return idProfiloUtente;
	}

	/**
	 * @param idProfiloUtente the idProfiloUtente to set
	 */
	public void setIdProfiloUtente(ProfiloUtenteCittadino idProfiloUtente) {
		this.idProfiloUtente = idProfiloUtente;
	}

	/**
	 * @return the infoAggiuntiveMap
	 */
	public Map<String, String> getInfoAggiuntiveMap() {
		return infoAggiuntiveMap;
	}

	/**
	 * @param infoAggiuntiveMap the infoAggiuntiveMap to set
	 */
	public void setInfoAggiuntiveMap(Map<String, String> infoAggiuntiveMap) {
		this.infoAggiuntiveMap = infoAggiuntiveMap;
	}

}
