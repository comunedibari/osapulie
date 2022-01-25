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

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneEstero;
import it.osapulie.persistence.ComuneEsteroCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class ComuneEsteroCustomRepositoryImpl implements ComuneEsteroCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.ComuneEsteroCustomRepository#findByDenominazione(java.lang.String)
	 */
	@Override
	public List<ComuneEstero> findByDenominazione(String denominazione) {
		String queryString = "SELECT ce FROM ComuneEstero ce WHERE ce.denominazione LIKE lower(?1) ORDER BY ce.denominazione asc";
		Query query = em.createQuery(queryString, Comune.class);
		query.setParameter(1, "%" + denominazione.toLowerCase() + "%");			
		return query.getResultList();
	}

}
