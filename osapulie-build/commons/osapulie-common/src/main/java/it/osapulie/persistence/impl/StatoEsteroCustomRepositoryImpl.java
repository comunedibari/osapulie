/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
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

import it.osapulie.domain.StatoEstero;
import it.osapulie.persistence.StatoEsteroCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class StatoEsteroCustomRepositoryImpl implements StatoEsteroCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.StatoEsteroCustomRepository#findByDenominazione(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<StatoEstero> findByDenominazione(String denominazione) {
		String queryString = "SELECT se FROM StatoEstero se WHERE se.denominazione LIKE lower(?1) ORDER BY se.denominazione asc";
		Query query = em.createQuery(queryString, StatoEstero.class);
		query.setParameter(1, "%" + denominazione.toLowerCase() + "%");
		return query.getResultList();
	}

}
