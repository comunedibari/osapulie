/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.osapulie.domain.categoriaimmobile.CategoriaImmobile;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class CategoriaImmobileCustomRepository implements it.osapulie.persistence.CategoriaImmobileCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.CategoriaImmobileCustomRepository#
	 * findCategorieImmobiliByComuneISAAndAnno(java.lang.Long, java.lang.Integer)
	 */
	@Override
	public List<CategoriaImmobile> findCategorieImmobiliByComuneISAAndAnno(Long idComuneISA, Integer anno) {

		StringBuilder queryStringBuilder = new StringBuilder("SELECT DISTINCT(ci) FROM CategoriaImmobile ci JOIN ci.categorieImmobiliTributi cit WHERE 1=1");

		if (idComuneISA != null) {
			queryStringBuilder.append(" AND cit.comuneISA.id = ?1");
		}
		if (anno != null) {
			queryStringBuilder.append(" AND cit.anno = ?2");
		}

		Query query = em.createQuery(queryStringBuilder.toString(), CategoriaImmobile.class);

		if (idComuneISA != null) {
			query.setParameter(1, idComuneISA);
		}
		if (anno != null) {
			query.setParameter(2, anno);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.CategoriaImmobileCustomRepository#
	 * findCategorieImmobiliByComuneISAAndAnnoAndServizio(java.lang.Long, java.lang.Integer,
	 * java.lang.String)
	 */
	@Override
	public List<CategoriaImmobile> findCategorieImmobiliByComuneISAAndAnnoAndServizio(Long idComuneISA, Integer anno, String codServizio) {

		StringBuilder queryStringBuilder = new StringBuilder("SELECT DISTINCT(ci) FROM CategoriaImmobile ci JOIN ci.categorieImmobiliTributi cit WHERE 1=1");

		if (idComuneISA != null) {
			queryStringBuilder.append(" AND cit.comuneISA.id = ?1");
		}
		if (anno != null) {
			queryStringBuilder.append(" AND cit.anno = ?2");
		}
		if (codServizio != null) {
			queryStringBuilder.append(" AND cit.tributo.servizio.codiceServizio = ?3");
		}

		Query query = em.createQuery(queryStringBuilder.toString(), CategoriaImmobile.class);

		if (idComuneISA != null) {
			query.setParameter(1, idComuneISA);
		}
		if (anno != null) {
			query.setParameter(2, anno);
		}
		if (codServizio != null) {
			query.setParameter(3, codServizio);
		}

		return query.getResultList();
	}

}
