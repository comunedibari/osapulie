/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence;

import java.util.List;

import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;

/**
 * @author Gianluca Pindinelli
 */
public interface CategoriaImmobileCustomRepository {

	/**
	 * Carica le categorie immobili in base ai parametri passati in input.
	 *
	 * @param idComuneISA
	 * @param anno
	 * @return
	 */
	List<CategoriaImmobile> findCategorieImmobiliByComuneISAAndAnno(Long idComuneISA, Integer anno);

	/**
	 * Carica le categorie immobili in base ai parametri passati in input.
	 * 
	 * @param idComuneISA
	 * @param anno
	 * @param codServizio
	 * @return
	 */
	List<CategoriaImmobile> findCategorieImmobiliByComuneISAAndAnnoAndServizio(Long idComuneISA, Integer anno, String codServizio);

}
