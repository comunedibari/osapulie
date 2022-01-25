/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.List;

import it.osapulie.domain.Bozza;
import it.osapulie.domain.BozzaDocumenti;
import it.osapulie.domain.Delega;
/**
 * Service per gli oggetti {@link Delega}.
 *
 * @author Gianluca De Felice
 *
 */
public interface BozzaDocumentiService {

	/**
	 *
	 * @param idBozzaDocumenti
	 * @return
	 * @throws ServiceLayerException
	 */
	BozzaDocumenti getBozzaDocumentiByPk(long idBozzaDocumenti) throws ServiceLayerException;

	/**
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<BozzaDocumenti> getAllBozzaDocumenti() throws ServiceLayerException;

	/**
	 *
	 *
	 * @param bozzaDocumenti
	 * @throws ServiceLayerException
	 */
	void saveBozzaDocumenti(BozzaDocumenti bozzaDocumenti) throws ServiceLayerException;


	/**
	 *
	 *
	 * @param bozzaDocumenti
	 * @throws ServiceLayerException
	 */
	void saveListBozzaDocumenti(List<BozzaDocumenti> listBozzaDocumenti) throws ServiceLayerException;

	/**
	 *
	 * @param idBozzaDocumenti
	 * @throws ServiceLayerException
	 */
	void deleteBozzaDocumenti(long idBozzaDocumenti) throws ServiceLayerException;

	/**
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	BozzaDocumenti updateBozzaDocumenti(BozzaDocumenti bozzaDocumenti) throws ServiceLayerException;

	/**
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<BozzaDocumenti> findByIdBozza(Long idBozza) throws ServiceLayerException;


}
