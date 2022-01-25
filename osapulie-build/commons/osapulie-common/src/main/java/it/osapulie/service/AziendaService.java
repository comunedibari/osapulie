/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.shared.service.UserPreferences;

/**
 * Service per la gestione dei {@link ProfiloUtenteCittadino}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface AziendaService {

	public Azienda getAziendaByPk(long pk) throws ServiceLayerException;

	public Azienda getAziendaByPiva(String piva) throws ServiceLayerException;

	/**
	 *
	 * @param idAzienda
	 * @throws ServiceLayerException
	 */
	public void deleteAzienda(long idAzienda) throws ServiceLayerException;

	/**
	 * Aggiorna l'azienda passata in input.
	 *
	 * @param azienda
	 * @throws ServiceLayerException
	 */
	void updateAzienda(Azienda azienda) throws ServiceLayerException;

	/**
	 * Crea nel sistema l'azienda passata in input.
	 *
	 * @param azienda
	 * @return
	 * @throws ServiceLayerException
	 */
	long saveAzienda(Azienda azienda) throws ServiceLayerException;

	/**
	 * Setta la proprietà <code>inUso</code>.
	 *
	 * @param azienda
	 */
	void setInUso(Azienda azienda);

	/**
	 * Carica la lista delle {@link Azienda} attive presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Azienda> getAziendeAttive() throws ServiceLayerException;

	/**
	 * Ricerca le aziende in base ai parametri di input.
	 *
	 * @param piva
	 * @param ragioneSociale
	 * @param responsabile
	 * @param attivo
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Azienda> searchAziende(String piva, String ragioneSociale, String responsabile, Boolean attivo) throws ServiceLayerException;

	/**
	 * Invia la mail di conferma al responsabile comunale.
	 *
	 * @param userPreferences
	 * @param subject
	 * @param nomeServizio
	 * @param numeroProtocollo
	 * @throws Exception
	 */
	void sendMailToUser(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo, List<ProfiloUtenteCittadino> listEmail, String emailComune, String ragSociale) throws Exception;

	void sendMailToAziendaNew(UserPreferences userPreferences, String subject, String nomeServizio, String numeroProtocollo, List<ProfiloUtenteCittadino> listEmail, String emailComune, String ragSociale)throws Exception;

}
