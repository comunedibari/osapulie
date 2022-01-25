/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.service;

import java.util.Date;
import java.util.List;

import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.dto.Fascicolo;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;

/**
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 *
 */
public interface FascicoloUtenteService {

	/**
	 * Carica il fascicolo in base all'id passato in input.
	 *
	 * @param idFascicolo
	 * @return
	 * @throws ServiceLayerException
	 */
	FascicoloUtente getFascicoloUtenteById(Long idFascicolo) throws ServiceLayerException;

	/**
	 * Carica il fascicolo di un cittadino.
	 *
	 * @param codiceFiscale
	 * @return
	 * @throws ServiceLayerException
	 */
	FascicoloUtente getFascicoloUtenteByCfCittadino(String codiceFiscale) throws ServiceLayerException;

	/**
	 * Carica le richieste servizio in base ai parametri in input.
	 * 
	 * @param codiceFiscale
	 * @param codiceServizio
	 * @param from
	 * @param to
	 * @return
	 * @throws ServiceLayerException
	 */
	List<RichiestaServizio> getRichiesteServizioByCfCittadinoAndServizio(String codiceFiscale, String codiceServizio, Date from, Date to) throws ServiceLayerException;

	/**
	 * Carica le richieste servizio in base ai parametri in input.
	 * 
	 * @param codiceFiscale
	 * @param codiceServizio
	 * @param nomeServizio
	 * @param from
	 * @param to
	 * @return
	 * @throws ServiceLayerException
	 */
	List<RichiestaServizio> getRichiesteServizioByCfCittadinoAndServizio(String codiceFiscale, String codiceServizio, String nomeServizio, Date from, Date to) throws ServiceLayerException;

	/**
	 * Carica il fascicolo di un'azienda.
	 *
	 * @param partitaIva
	 * @return
	 * @throws ServiceLayerException
	 */
	FascicoloUtente getFascicoloUtenteByPIvaAzienda(String partitaIva) throws ServiceLayerException;

	/**
	 * Salva una nuova richiesta di operazione nel fascicolo del cittadino.
	 *
	 * @param idProfiloUtente
	 * @param servizio
	 * @param codFiscDelegante
	 * @param userPreferences
	 * @param codiceServizio
	 * @param ricercabileDaComune
	 * @param checksum
	 */
	FascicoloUtente saveNuovaRichiesta(Fascicolo fascicolo);

	/**
	 * Ritorna la richiesta servizio dal fascicolo utente in base al campo infoAggiuntive.
	 *
	 * @param profiloUtenteCittadino
	 * @param infoAggiuntive
	 * @return
	 */
	RichiestaServizio getRichiestaServizioFascicolUtente(ProfiloUtenteCittadino profiloUtenteCittadino, String infoAggiuntive);

	/**
	 * Aggiorna una richiesta di servizio
	 *
	 * @param richiestaServizio
	 * @return
	 */
	RichiestaServizio updateRichiestaServizio(RichiestaServizio richiestaServizio);

	/**
	 * Conta tutte le richieste di servizio associate ai fascicoli presenti.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	long countAllRichiesteServizioFascicolo() throws ServiceLayerException;

	/**
	 * Conta tutte le richieste di servizio associate ai fascicoli in base al servizio.
	 *
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	long countAllRichiesteServizioFascicoloByServizio(long idServizio) throws ServiceLayerException;

	/**
	 * Conta tutte le richieste di servizio associate ai fascicoli in base al nome servizio passato
	 * in input (Like).
	 *
	 * @param nomeServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	long countAllRichiesteServizioFascicoloByLikeServizio(String nomeServizio) throws ServiceLayerException;

}
