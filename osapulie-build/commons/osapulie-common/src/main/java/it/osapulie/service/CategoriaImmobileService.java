/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.categoriaimmobile.Agevolazione;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;
import it.osapulie.domain.categoriaimmobile.CategoriaImmobileTributo;
import it.osapulie.domain.categoriaimmobile.Detrazione;
import it.osapulie.domain.categoriaimmobile.Esenzione;
import it.osapulie.domain.categoriaimmobile.Tributo;

import java.util.List;

/**
 * @author Gianluca Pindinelli
 *
 */
public interface CategoriaImmobileService {

	/**
	 * Ritorna la lista di tutte le categorie di immobili.
	 *
	 * @return
	 * @throws ServiceLayerException
	 */
	List<CategoriaImmobile> getAllCategorieImmobili() throws ServiceLayerException;

	/**
	 * Ritorna la lista delle categorie di immobili in base al Comune ed all'anno di riferimento.
	 *
	 * @param id
	 * @param anno
	 * @return
	 * @throws ServiceLayerException
	 */
	List<CategoriaImmobile> getCategorieImmobiliByComuneISAAndAnno(Long idComuneISA, Integer anno) throws ServiceLayerException;

	/**
	 * Ritorna la categoria immobiile dato il suo id.
	 *
	 * @param id
	 * @return
	 * @throws ServiceLayerException
	 */
	CategoriaImmobile getCategoriaImmobileById(long id) throws ServiceLayerException;

	/**
	 * Elimina la categoria immobile. <u>N.B.: l'operazione eliminerà le entità correlate (
	 * {@link CategoriaImmobile} e le liste di {@link Detrazione}, {@link Agevolazione} ed
	 * {@link Esenzione}) </u>.
	 *
	 * @param categoriaImmobileTributo oggetto di relazione tra {@link CategoriaImmobile} e
	 *        {@link Tributo}.
	 * @throws ServiceLayerException
	 */
	void deleteCategoriaImmobile(CategoriaImmobileTributo categoriaImmobileTributo) throws ServiceLayerException;

	/**
	 * Salva nel sistema la {@link CategoriaImmobile} passata in input.
	 *
	 * @param categoriaImmobile
	 * @return
	 * @throws ServiceLayerException
	 */
	CategoriaImmobile saveCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException;
	
	/**
	 * mellif 17/04/2015: metodo alternativo per il salvataggio della categoria immobile
	 * Salva nel sistema la {@link CategoriaImmobile} passata in input.
	 *
	 * @param categoriaImmobile
	 * @return
	 * @throws ServiceLayerException
	 */
	CategoriaImmobile salvaCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException;

	/**
	 * Salva nel sistema la {@link CategoriaImmobileTributo} passata in input.
	 *
	 * @param categoriaImmobileTributo
	 * @return
	 * @throws ServiceLayerException
	 */
	CategoriaImmobileTributo saveCategoriaImmobileTributo(CategoriaImmobileTributo categoriaImmobileTributo) throws ServiceLayerException;

	/**
	 * Elimina la categoria immobile. <u>N.B.: l'operazione eliminerà le entità correlate!</u>
	 * 
	 * @param categoriaImmobile
	 * @throws ServiceLayerException
	 */
	void deleteCategoriaImmobile(CategoriaImmobile categoriaImmobile) throws ServiceLayerException;

	/**
	 * Ritorna la lista delle categorie di immobili in base al Comune,  all'anno di riferimento, al tipo di Servizio(TASI,IMU)
	 * 
	 * @param idComuneISA
	 * @param anno
	 * @param codServizio
	 * @return
	 * @throws ServiceLayerException
	 */
	List<CategoriaImmobile> getCategorieImmobiliByComuneISAAndAnnoAndServizio(
			Long idComuneISA, Integer anno, String codServizio)
			throws ServiceLayerException;
}
