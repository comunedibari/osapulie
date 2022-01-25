/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Delega;

/**
 * Service per gli oggetti {@link Delega}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface DelegaService {

	/**
	 * Carica la delega a partire dalla PK.
	 *
	 * @param idDelega
	 * @return
	 * @throws ServiceLayerException
	 */
	Delega getDelegaByPk(long idDelega) throws ServiceLayerException;

	/**
	 * Carica tutte le deleghe presenti sul sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> getAllDelega() throws ServiceLayerException;

	/**
	 * Carica tutte le deleghe presenti sul sistema appartenenti ad un determinato comune.
	 *
	 * @param comuneISA
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> getAllDelegaByComuneIsa(ComuneISA comuneISA) throws ServiceLayerException;

	/**
	 * Ricerca le deleghe in base ai parametri di input.
	 *
	 * @param idComuneISA
	 * @param cognomeDelegante
	 * @param nomeDelegante
	 * @param codiceFiscaleDelegante
	 * @param stato
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> searchDeleghe(Long idComuneISA, String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato) throws ServiceLayerException;

	/**
	 * Ricerca le deleghe in base ai parametri di input.
	 *
	 * @param cognomeDelegante
	 * @param nomeDelegante
	 * @param codiceFiscaleDelegante
	 * @param stato
	 * @param idDelegato
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> searchDeleghe(String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato, Long idDelegato) throws ServiceLayerException;

	/**
	 *
	 * Salva una delega su db.
	 *
	 * @param delega
	 * @throws ServiceLayerException
	 */
	void saveDelega(Delega delega) throws ServiceLayerException;

	/**
	 * Elimina una delega dal sistema.
	 *
	 * @param idDelega
	 * @throws ServiceLayerException
	 */
	void deleteDelega(long idDelega) throws ServiceLayerException;

	/**
	 * Aggiorna una delega nel sistema.
	 *
	 * @param idDelega
	 * @return
	 * @throws ServiceLayerException
	 */
	Delega updateDelega(Delega delega) throws ServiceLayerException;

	/**
	 * Carica tutte le deleghe presenti sul sistema sulla base dell'id delegato.
	 *
	 * @param idDelegato
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> getDelegheByDelegato(long idDelegato) throws ServiceLayerException;

	/**
	 * Carica le deleghe associate al servizio passato in input.
	 *
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Delega> getDelegheByServizio(long idServizio) throws ServiceLayerException;

}
