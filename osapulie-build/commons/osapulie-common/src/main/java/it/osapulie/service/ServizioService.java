/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.servizi.Servizio;

/**
 * Service per gli oggetti {@link Servizio}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ServizioService {

	/**
	 * Carica un servizio a partire dalla sua PK.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	Servizio getServizioById(long id) throws ServiceLayerException;

	/**
	 * Carica la lista di tutti i servizi presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getAllServizio() throws ServiceLayerException;

	/**
	 * Carica la lista di tutti i servizi attivi presenti nel sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziAttivi() throws ServiceLayerException;

	/**
	 * Carica un servizio a partire dal suo codice servizio.
	 *
	 * @param codiceServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	Servizio getServizioByCodiceServizio(String codiceServizio) throws ServiceLayerException;

	/**
	 * Carica un servizio a partire dal suo uri scheda servizio.
	 *
	 * @param uriScheda
	 * @return
	 * @throws ServiceLayerException
	 */
	Servizio getServizioByUriScheda(String uriScheda) throws ServiceLayerException;

	/**
	 * Carica un servizio a partire dal suo uri servizio.
	 *
	 * @param uri
	 * @return
	 * @throws ServiceLayerException
	 */
	Servizio getServizioByUri(String uri) throws ServiceLayerException;

	/**
	 * Carica la lista di tutti i servizi attivi per il comune passato in input presenti nel
	 * sistema.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziAttiviByComuneISA(long idComuneISA) throws ServiceLayerException;

	/**
	 * Carica la lista dei servizi attivi con delega in base al comune ISA passato in input.
	 *
	 * @param idComuneISA
	 * @param isAzienda
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziAttiviForDelegaByComuneISA(long idComuneISA) throws ServiceLayerException;

	/**
	 * Carica la lista dei servizi in base ai parametri in input.
	 *
	 * @param idComuneISA
	 * @param idAreaTematica
	 * @param idTipologia
	 * @param idDelega
	 * @param cittadino
	 * @param azienda
	 * @param attivo
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziByParemeters(Long idComuneISA, Long idAreaTematica, Long idTipologia, Long idDelega, Boolean cittadino, Boolean azienda, boolean attivo) throws ServiceLayerException;

	/**
	 * Carica la lista dei servizi attivi abilitati al funzionamento con delega.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<Servizio> getServiziAttiviForDelega() throws ServiceLayerException;

}
