/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ProfiloUtenteCittadinoCustomRepository {

	/**
	 * Elimina l'associazione {@link ProfiloUtenteCittadinoAzienda}.
	 *
	 * @param idProfiloUtenteCittadino
	 * @param idAzienda
	 * @return
	 */
	long deleteProfiloUtenteCittadinoAzienda(Long idProfiloUtenteCittadino, Long idAzienda);

	/**
	 * @param idAzienda
	 * @param codiceFiscaleDelegato
	 * @param nomeDelegato
	 * @param cognomeDelegato
	 * @return
	 */
	List<ProfiloUtenteCittadinoAzienda> searchUtentiAssociatiAzienda(Long idAzienda, String codiceFiscaleDelegato, String nomeDelegato, String cognomeDelegato);

}
