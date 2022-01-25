/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 - 73100 -
 * Lecce - http://www.linksmt.it All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.ComuneISAServizio;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface ComuneISAServizioService {

	/**
	 * Elimina la relazione {@link ComuneISAServizioService}.
	 *
	 * @param idComuneISA
	 * @param idServizio
	 * @throws ServiceLayerException
	 */
	void deleteComuneISAServizio(long idComuneISA, long idServizio) throws ServiceLayerException;

	/**
	 * Carica la relazione in base agli id.
	 * 
	 * @param idComuneISA
	 * @param idServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	ComuneISAServizio getComuneISAServizio(long idComuneISA, long idServizio) throws ServiceLayerException;

}
