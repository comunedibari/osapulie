/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.Delega;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface DelegaCustomRepository {

	/**
	 * Carica le deleghe in base ai parametri in input.
	 *
	 * @param idComuneISA
	 * @param cognomeDelegante
	 * @param nomeDelegante
	 * @param codiceFiscaleDelegante
	 * @param stato
	 * @return
	 */
	List<Delega> findDeleghe(Long idComuneISA, String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato);

	/**
	 * Carica le deleghe in base ai parametri in input.
	 *
	 * @param cognomeDelegante
	 * @param nomeDelegante
	 * @param codiceFiscaleDelegante
	 * @param stato
	 * @param idDelegato
	 * @return
	 */
	List<Delega> findDeleghe(String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato, Long idDelegato);

	/**
	 *
	 * @param idServizio
	 * @return
	 */
	List<Delega> getDelegheByServizio(long idServizio);

}
