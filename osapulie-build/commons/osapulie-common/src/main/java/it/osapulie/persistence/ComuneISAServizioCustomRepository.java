/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import it.osapulie.domain.ComuneISAServizio;
import it.osapulie.service.ComuneISAServizioService;

/**
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneISAServizioCustomRepository {

	/**
	 * Elimina la relazione {@link ComuneISAServizioService} in base ai parametri in input.
	 *
	 * @param idComuneISA
	 * @param idServizio
	 */
	void deleteComuneISAServizio(long idComuneISA, long idServizio);

	/**
	 * Carica la relazione in base agli id in input.
	 * 
	 * @param idComuneISA
	 * @param idServizio
	 * @return
	 */
	ComuneISAServizio findByIdComuneISAIdServizio(long idComuneISA, long idServizio);

}
