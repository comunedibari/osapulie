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

import it.osapulie.domain.Pin;
import it.osapulie.persistence.PinCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class PinCustomRepositoryImpl implements PinCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.PinCustomRepository#findPins(java.lang.Long, java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<Pin> findPins(Long idComuneISA, String cognome, String nome, String codiceFiscale, String stato) {
		String queryString = "SELECT p FROM Pin p WHERE 1=1";

		if (idComuneISA != null) {
			queryString += " AND p.comuneIsa.id = ?1";
		}
		if (cognome != null && !cognome.equals("")) {
			queryString += " AND p.profiloUtenteCittadino.cognome LIKE lower(?2)";
		}
		if (nome != null && !nome.equals("")) {
			queryString += " AND p.profiloUtenteCittadino.nome LIKE lower(?3)";
		}

		if (codiceFiscale != null && !codiceFiscale.equals("")) {
			queryString += " AND p.profiloUtenteCittadino.codiceFiscale LIKE lower(?4)";
		}
		if (stato != null && !stato.equals("")) {
			queryString += " AND p.stato = ?5";
		}

		Query query = em.createQuery(queryString, Pin.class);

		if (idComuneISA != null) {
			query.setParameter(1, idComuneISA);
		}
		if (cognome != null && !cognome.equals("")) {
			query.setParameter(2, "%" + cognome.toLowerCase() + "%");
		}
		if (nome != null && !nome.equals("")) {
			query.setParameter(3, "%" + nome.toLowerCase() + "%");
		}
		if (codiceFiscale != null && !codiceFiscale.equals("")) {
			query.setParameter(4, "%" + codiceFiscale.toLowerCase() + "%");
		}
		if (stato != null && !stato.equals("")) {
			query.setParameter(5, stato);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.PinCustomRepository#findLastPin(long)
	 */
	@Override
	public Pin findLastPin(long idUtente) {
		String queryString = "SELECT p FROM Pin p WHERE p.profiloUtenteCittadino.id = ?1  ORDER BY p.id desc";

		Query query = em.createQuery(queryString, Pin.class);
		query.setParameter(1, idUtente);
		query.setMaxResults(1);
		Pin result = null;

		List<Pin> resultList = query.getResultList();
		if (resultList != null && !resultList.isEmpty()) {
			result = resultList.get(0);
		}

		return result;
	}

}
