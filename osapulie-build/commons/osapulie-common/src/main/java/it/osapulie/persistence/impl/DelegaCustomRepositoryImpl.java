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

import it.osapulie.domain.Delega;
import it.osapulie.persistence.DelegaCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class DelegaCustomRepositoryImpl implements DelegaCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.DelegaCustomRepository#findDeleghe(java.lang.Long,
	 * java.lang.String, java.lang.String, java.lang.String, java.lang.Boolean)
	 */
	@Override
	public List<Delega> findDeleghe(Long idComuneISA, String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato) {
		String queryString = "SELECT d FROM Delega d WHERE 1=1";

		if (idComuneISA != null) {
			queryString += " AND d.comuneIsa.id = ?1";
		}
		if (cognomeDelegante != null && !cognomeDelegante.equals("")) {
			queryString += " AND d.delegante.cognome LIKE lower(?2)";
		}
		if (nomeDelegante != null && !nomeDelegante.equals("")) {
			queryString += " AND d.delegante.nome LIKE lower(?3)";
		}

		if (codiceFiscaleDelegante != null && !codiceFiscaleDelegante.equals("")) {
			queryString += " AND d.delegante.codiceFiscale LIKE lower(?4)";
		}
		if (stato != null && !stato.equals("")) {
			queryString += " AND d.attiva = ?5";
		}

		Query query = em.createQuery(queryString, Delega.class);

		if (idComuneISA != null) {
			query.setParameter(1, idComuneISA);
		}
		if (cognomeDelegante != null && !cognomeDelegante.equals("")) {
			query.setParameter(2, "%" + cognomeDelegante.toLowerCase() + "%");
		}
		if (nomeDelegante != null && !nomeDelegante.equals("")) {
			query.setParameter(3, "%" + nomeDelegante.toLowerCase() + "%");
		}
		if (codiceFiscaleDelegante != null && !codiceFiscaleDelegante.equals("")) {
			query.setParameter(4, "%" + codiceFiscaleDelegante.toLowerCase() + "%");
		}
		if (stato != null && !stato.equals("")) {
			query.setParameter(5, stato);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.DelegaCustomRepository#findDeleghe(java.lang.String,
	 * java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Long)
	 */
	@Override
	public List<Delega> findDeleghe(String cognomeDelegante, String nomeDelegante, String codiceFiscaleDelegante, Boolean stato, Long idDelegato) {
		String queryString = "SELECT d FROM Delega d WHERE 1=1";

		if (cognomeDelegante != null && !cognomeDelegante.equals("")) {
			queryString += " AND d.delegante.cognome LIKE lower(?1)";
		}
		if (nomeDelegante != null && !nomeDelegante.equals("")) {
			queryString += " AND d.delegante.nome LIKE lower(?2)";
		}

		if (codiceFiscaleDelegante != null && !codiceFiscaleDelegante.equals("")) {
			queryString += " AND d.delegante.codiceFiscale LIKE lower(?3)";
		}
		if (stato != null && !stato.equals("")) {
			queryString += " AND d.attiva = ?4";
		}
		if (idDelegato != null) {
			queryString += " AND d.delegato.id = ?5";
		}

		Query query = em.createQuery(queryString, Delega.class);

		if (cognomeDelegante != null && !cognomeDelegante.equals("")) {
			query.setParameter(1, "%" + cognomeDelegante.toLowerCase() + "%");
		}
		if (nomeDelegante != null && !nomeDelegante.equals("")) {
			query.setParameter(2, "%" + nomeDelegante.toLowerCase() + "%");
		}
		if (codiceFiscaleDelegante != null && !codiceFiscaleDelegante.equals("")) {
			query.setParameter(3, "%" + codiceFiscaleDelegante.toLowerCase() + "%");
		}
		if (stato != null && !stato.equals("")) {
			query.setParameter(4, stato);
		}
		if (idDelegato != null) {
			query.setParameter(5, idDelegato);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.DelegaCustomRepository#getDelegheByServizio(long)
	 */
	@Override
	public List<Delega> getDelegheByServizio(long idServizio) {
		String queryString = "SELECT d FROM Delega d WHERE 1=1 AND d.servizi.ID = ?1";

		Query query = em.createQuery(queryString, Delega.class);
		query.setParameter(1, idServizio);

		return query.getResultList();
	}

}
