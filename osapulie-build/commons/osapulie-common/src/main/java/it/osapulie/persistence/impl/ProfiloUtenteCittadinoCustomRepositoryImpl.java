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

import it.osapulie.domain.ProfiloUtenteCittadinoAzienda;
import it.osapulie.persistence.ProfiloUtenteCittadinoCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class ProfiloUtenteCittadinoCustomRepositoryImpl implements ProfiloUtenteCittadinoCustomRepository {

	@PersistenceContext
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.ProfiloUtenteCittadinoCustomRepository#
	 * deleteProfiloUtenteCittadinoAzienda(java.lang.Long, java.lang.Long)
	 */
	@Override
	public long deleteProfiloUtenteCittadinoAzienda(Long idProfiloUtenteCittadino, Long idAzienda) {

		Query query = entityManager.createQuery(
				"DELETE FROM ProfiloUtenteCittadinoAzienda p WHERE p.pk.idProfiloUtenteCittadino = " + idProfiloUtenteCittadino + " AND p.pk.idProfiloUtenteProfessionista = " + idAzienda);
		int deletedCount = query.executeUpdate();

		return deletedCount;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.ProfiloUtenteCittadinoCustomRepository#searchUtentiAssociatiAzienda(
	 * java.lang.Long, java.lang.String, java.lang.String, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<ProfiloUtenteCittadinoAzienda> searchUtentiAssociatiAzienda(Long idAzienda, String codiceFiscaleDelegato, String nomeDelegato, String cognomeDelegato) {
		String queryString = "SELECT p FROM ProfiloUtenteCittadinoAzienda p WHERE 1=1";

		if (idAzienda != null) {
			queryString += " AND p.azienda.id = ?1";
		}

		if (codiceFiscaleDelegato != null) {
			queryString += " AND p.profiloUtenteCittadino.codiceFiscale LIKE lower(?2)";
		}

		if (nomeDelegato != null) {
			queryString += " AND p.profiloUtenteCittadino.nome LIKE lower(?3)";
		}

		if (cognomeDelegato != null) {
			queryString += " AND p.profiloUtenteCittadino.cognome LIKE lower(?4)";
		}

		Query query = entityManager.createQuery(queryString, ProfiloUtenteCittadinoAzienda.class);

		if (idAzienda != null) {
			query.setParameter(1, idAzienda);
		}

		if (codiceFiscaleDelegato != null) {
			query.setParameter(2, "%" + codiceFiscaleDelegato + "%");
		}

		if (nomeDelegato != null) {
			query.setParameter(3, "%" + nomeDelegato + "%");
		}

		if (cognomeDelegato != null) {
			query.setParameter(4, "%" + cognomeDelegato + "%");
		}

		return query.getResultList();
	}

}
