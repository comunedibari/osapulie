/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.infrastructure.security;

import org.springframework.security.core.userdetails.User;

/**
 * Interfaccia per la gestione (CRUD) degli oggetti che descrivono la profilazione di un utente.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ProfilazioneUtenteService {

	/**
	 * Esegue l'aggiornamento dell'oggetto {@link OSApulieUserDetails}. L'aggiornamento avviene su
	 * tutta l'infrastruttuta (LDAP, ISA e portale Liferay).
	 *
	 * @param osApulieUserDetails l'oggetto rappresentante il profilo dell'utente
	 * @return l'oggetto aggiornato
	 * @throws ProfilazioneUtenteException
	 */
	OSApulieUserDetails update(OSApulieUserDetails osApulieUserDetails) throws ProfilazioneUtenteException;

	/**
	 * Restituisce l'oggetto {@link OSApulieUserDetails} caricandolo dal sistema. L'oggetto
	 * ritornato NON avrà al suo interno l'oggetto {@link User}.
	 *
	 * @param id l'id del quale si vuole caricare l'oggetto
	 * @return
	 * @throws ProfilazioneUtenteException
	 */
	OSApulieUserDetails getById(String id) throws ProfilazioneUtenteException;

	/**
	 * Elimina l'oggetto {@link OSApulieUserDetails} da tutta l'infrastruttura (LDAP ed ISA).
	 *
	 * @param id
	 * @throws ProfilazioneUtenteException
	 */
	void deleteById(String id) throws ProfilazioneUtenteException;

}
