/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Pin;

import java.util.List;

/**
 * Service per gli oggetti {@link Pin}.
 * 
 * @author Gianluca Pindinelli
 * 
 */
public interface PinService {

	/**
	 * Carica il PIN a partire dalla PK.
	 * 
	 * @param idDelega
	 * @return
	 * @throws ServiceLayerException
	 */
	public Pin getPinByPk(long id) throws ServiceLayerException;

	/**
	 * Carica tutte i PIN presenti sul sistema.
	 * 
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<Pin> getAllPin() throws ServiceLayerException;

	/**
	 * Carica tutti i PIN presenti sul sistema appartenenti ad un determinato comune.
	 * 
	 * @param comuneISA
	 * @return
	 * @throws ServiceLayerException
	 */
	public List<Pin> getAllPinByComuneIsa(ComuneISA comuneISA) throws ServiceLayerException;

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
	public List<Pin> searchPin(Long idComuneISA, String cognome, String nome, String codiceFiscale, String stato) throws ServiceLayerException;

	/**
	 * 
	 * Salva un PIN su db.
	 * 
	 * @param delega
	 * @throws ServiceLayerException
	 */
	public void savePin(Pin pin) throws ServiceLayerException;

	/**
	 * Elimina un PIN dal sistema.
	 * 
	 * @param idDelega
	 * @throws ServiceLayerException
	 */
	public void deletePin(long id) throws ServiceLayerException;

	/**
	 * Aggiorna un PIN nel sistema.
	 * 
	 * @param idDelega
	 * @return
	 * @throws ServiceLayerException
	 */
	public Pin updatePin(Pin delega) throws ServiceLayerException;

	/**
	 * Ritorna l'ultimo PIN assegnato ad un cittadino, <code>null</code> se non esistono alcun PIN
	 * associato.
	 * 
	 * @param idUtente
	 * @return
	 * @throws ServiceLayerException
	 */
	public Pin getLastPin(long idUtente) throws ServiceLayerException;

}
