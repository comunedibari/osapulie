/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.osapulie.domain.ComuneISAServizio;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class ComuneISAServizioCustomRepository implements it.osapulie.persistence.ComuneISAServizioCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.ComuneISAServizioCustomRepository#deleteComuneISAServizio(long,
	 * long)
	 */
	@Override
	public void deleteComuneISAServizio(long idComuneISA, long idServizio) {

		String queryString = "DELETE FROM ComuneISAServizio cis WHERE cis.idComuneISA = ?1";

		if (idServizio != 0) {
			queryString += " AND cis.idServizio = ?2";
		}

		Query query = em.createQuery(queryString, ComuneISAServizio.class);
		query.setParameter(1, idComuneISA);
		if (idServizio != 0) {
			query.setParameter(2, idServizio);
		}

		query.executeUpdate();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.ComuneISAServizioCustomRepository#findByIdComuneISAIdServizio(long,
	 * long)
	 */
	@Override
	public ComuneISAServizio findByIdComuneISAIdServizio(long idComuneISA, long idServizio) {

		StringBuilder queryStringBuilder = new StringBuilder("SELECT cis FROM ComuneISAServizio cis WHERE 1=1");

		queryStringBuilder.append(" AND cis.idComuneISA = ?1");
		queryStringBuilder.append(" AND cis.idServizio = ?2");

		Query query = em.createQuery(queryStringBuilder.toString(), ComuneISAServizio.class);

		query.setParameter(1, idComuneISA);
		query.setParameter(2, idServizio);
		ComuneISAServizio result = null;
		if (query.getSingleResult() != null) {
			result = (ComuneISAServizio) query.getSingleResult();
		}

		return result;
	}

}
