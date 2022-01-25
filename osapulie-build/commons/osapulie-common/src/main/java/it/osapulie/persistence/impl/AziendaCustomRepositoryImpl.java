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

import it.osapulie.domain.Azienda;
import it.osapulie.persistence.AziendaCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class AziendaCustomRepositoryImpl implements AziendaCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.AziendaCustomRepository#searchAziende(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<Azienda> findAziende(String piva, String ragioneSociale, String responsabile, Boolean stato) {
		String queryString = "SELECT a FROM Azienda a WHERE 1=1";

		if (piva != null && !piva.equals("")) {
			queryString += " AND a.partitaIva = ?1";
		}
		if (ragioneSociale != null && !ragioneSociale.equals("")) {
			queryString += " AND a.ragioneSociale LIKE lower(?2)";
		}
		if (responsabile != null && !responsabile.equals("")) {
			queryString += " AND a.responsabile.codiceFiscale = ?3";
		}
		if (stato != null) {
			queryString += " AND a.attiva = ?4";
		}

		Query query = em.createQuery(queryString, Azienda.class);

		if (piva != null && !piva.equals("")) {
			query.setParameter(1, piva);
		}
		if (ragioneSociale != null && !ragioneSociale.equals("")) {
			query.setParameter(2, "%" + ragioneSociale.toLowerCase() + "%");
		}
		if (responsabile != null && !responsabile.equals("")) {
			query.setParameter(3, responsabile);
		}
		if (stato != null) {
			query.setParameter(4, stato);
		}

		return query.getResultList();
	}

}
