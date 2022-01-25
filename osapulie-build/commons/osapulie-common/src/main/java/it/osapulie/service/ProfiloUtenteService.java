/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;

/**
 * Service per la gestione dei {@link ProfiloUtenteCittadino}.
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ProfiloUtenteService {

	public List<ProfiloUtenteCittadino> getAllProfiloUtenteCittadino() throws ServiceLayerException;

	public ProfiloUtenteCittadino getProfiloUtenteCittadinoById(long pk) throws ServiceLayerException;

	public ProfiloUtenteCittadino getProfiloUtenteCittadinoByCf(String cf) throws ServiceLayerException;

	public long countAllProfiloUtenteCittadino() throws ServiceLayerException;

	/**
	 * Aggiorna il profilo utente cittadino passato in input.
	 *
	 * @param profiloUtenteCittadino
	 * @throws ServiceLayerException
	 */
	void updateProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) throws ServiceLayerException;

	/**
	 * Crea nel sistema il profilo utente cittadino passato in input.
	 *
	 * @param profiloUtenteCittadino
	 * @return
	 * @throws ServiceLayerException
	 */
	long saveProfiloUtenteCittadino(ProfiloUtenteCittadino profiloUtenteCittadino) throws ServiceLayerException;

	/**
	 * Elimina l'associazione {@link ProfiloUtenteCittadinoAzienda}.
	 *
	 * @param idProfiloUtenteCittadino
	 * @param idAzienda
	 * @return
	 * @throws ServiceLayerException
	 */
	long deleteProfiloUtenteCittadinoAzienda(Long idProfiloUtenteCittadino, Long idAzienda) throws ServiceLayerException;

	/**
	 * @param idAzienda
	 * @param codiceFiscaleDelegato
	 * @param nomeDelegato
	 * @param cognomeDelegato
	 * @return
	 */
	List<ProfiloUtenteCittadino> searchUtentiAssociatiAzienda(Long idAzienda, String codiceFiscaleDelegato, String nomeDelegato, String cognomeDelegato);

}
