/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.portlet.PortletRequest;
import javax.portlet.PortletSession;

import it.osapulie.domain.Backup;
import it.osapulie.domain.BozzaDocumenti;
import it.osapulie.domain.Delega;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;
import it.osapulie.shared.service.UserPreferences;

/**
 * Servizi comuni alle portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface CommonService {

	/**
	 * Carica il {@link ProfiloUtenteCittadino} in base al profilo utente selezionato.
	 *
	 * @param userPreferences
	 * @return
	 */
	ProfiloUtenteCittadino getCurrentProfiloUtenteCittadino(UserPreferences userPreferences);

	/**
	 * Elimina il ruolo "Responsabile CAF" dell'utente (solo se non esistono altri CAF associate).
	 *
	 * @param idProfiloUtenteCittadino
	 */
	void removeResponsabileCAFRole(long idProfiloUtenteCittadino);
	
	/**
	 * Elimina il ruolo "Responsabile Azienda" dell'utente (solo se non esistono altre AZIENDE associate).
	 *
	 * @param idProfiloUtenteCittadino
	 */
	void removeResponsabileAZIENDARole(long idProfiloUtenteCittadino);

	/**
	 * Elimina il ruolo "Operatore CAF" dell'utente (solo se non esistono altri CAF associate).
	 *
	 * @param idAzienda
	 * @param idProfiloUtenteCittadino
	 */
	void removeOperatoreCAFRole(long idAzienda, long idProfiloUtenteCittadino);

	/**
	 * Elimina il ruolo "Operatore AZIENDA" dell'utente (solo se non esistono altre AZIENDA associate).
	 *
	 * @param idAzienda
	 * @param idProfiloUtenteCittadino
	 */
	void removeOperatoreAZIENDARole(long idAzienda, long idProfiloUtenteCittadino);
	
	/**
	 * Salva la bozza passata in input sulla base dei parametri passati in input.
	 *
	 * @param serializable
	 * @param userPreferences
	 * @param idServizio
	 */
	void saveBozza(Serializable serializable, UserPreferences userPreferences, long idServizio);

	/**
	 * Salva la bozza passata in input sulla base dei parametri passati in input.
	 *
	 * @param serializable
	 * @param userPreferences
	 * @param idServizio
	 */
	void saveBozzaDocumenti(Serializable serializable, UserPreferences userPreferences, long idServizio, BozzaDocumenti bozzaDocumenti);

	/**
	 * Elimina una bozza dal sistema sulla base dei parametri passati in input.
	 *
	 * @param userPreferences
	 * @param idServizio
	 */
	void deleteBozza(UserPreferences userPreferences, long idServizio);
	
	/**
	 * Elimina una bozzaDocumenti dal sistema sulla base dei parametri passati in input.
	 *
	 * @param userPreferences
	 * @param idServizio
	 */
	void deleteBozzaDocumenti(UserPreferences userPreferences, long idServizio);

	/**
	 * Carica la bozza (oggetto {@link Serializable}) sulla base dei parametri passati in input.
	 *
	 * @param <T>
	 * @param userPreferences
	 * @param idServizio
	 * @return
	 */
	<T> T getBozza(UserPreferences userPreferences, long idServizio, Class<T> classType);

	/**
	 * Genera la URL per accedere alla pagina contenente la portlet di gestione utenti. Genera la
	 * url di callback utile a ritornare nella pagina da cui si è partiti.
	 *
	 * @param request
	 * @param codiceFiscale
	 * @param sourceParameters
	 * @param sourceLifecycle
	 * @return
	 */
	String getGestioneUtentiUrl(PortletRequest request, String codiceFiscale, Map<String, String> sourceParameters, String sourceLifecycle);

	/**
	 * Genera la URL per accedere alla pagina contenente la portlet utile ad inviare una
	 * segnalazione custom. Genera le url di callback ("success" e "cancel") utili a ritornare nella
	 * pagina da cui si è partiti.
	 *
	 * @param request
	 * @param successReturnRequestParameters
	 * @param successReturnLifecycle
	 * @param cancelReturnRequestParameters
	 * @param cancelReturnLifecycle
	 * @return
	 */
	String getInvioSegnalazioneCustomUrl(PortletRequest request, Map<String, String> successReturnRequestParameters, String successReturnLifecycle, Map<String, String> cancelReturnRequestParameters,
			String cancelReturnLifecycle);

	/**
	 * Aggiunge in {@link PortletSession} l'istanza {@link SegnalazioneCustom} serializzata.
	 *
	 * @param session
	 * @param segnalazioneCustom
	 */
	void addSegnalazioneCustomToSession(PortletSession session, SegnalazioneCustom segnalazioneCustom);

	/**
	 * Ritorna da {@link PortletSession} l'istanza {@link SegnalazioneCustom} deserializzata.
	 *
	 * @param session
	 * @return
	 */
	SegnalazioneCustom getSegnalazioneCustomFromSession(PortletSession session);

	/**
	 * Salva un backup dell'oggetto passato in input nella tabella backup.
	 *
	 * @param xml
	 * @param userPreferences
	 * @param idServizio
	 */
	Backup saveBackup(String xml, UserPreferences userPreferences, long idServizio);

	/**
	 * Elimina un backup dal sistema.
	 *
	 * @param idBackup
	 */
	void deleteBackup(long idBackup);

}
