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

import it.osapulie.domain.servizi.Servizio;
import it.osapulie.persistence.ServizioCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class ServizioCustomRepositoryImpl implements ServizioCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.persistence.ServizioCustomRepository#findByParameters(java.lang.Long,
	 * java.lang.Long, java.lang.Long, java.lang.Long, java.lang.Boolean, java.lang.Boolean,
	 * boolean)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Servizio> findByParameters(Long idComuneISA, Long idAreaTematica, Long idTipologia, Long idDelega, Boolean cittadino, Boolean azienda, boolean attivo) {

		String queryString = "SELECT DISTINCT s FROM Servizio s ";

		if (idComuneISA != null) {
			queryString += " JOIN s.comuni c";
		}

		if (idTipologia != null) {
			queryString += " JOIN s.tipologie t";
		}

		if (idDelega != null) {
			queryString += " JOIN s.deleghe d";
		}

		queryString += " WHERE 1=1 ";

		if (idComuneISA != null) {
			queryString += " AND c.idComuneISA = :idComuneISA";
			queryString += " AND c.attivo = true";
			queryString += " AND c.comuneISA.attivo = true";
		}

		if (idAreaTematica != null) {
			queryString += " AND s.areaTematica.id = :idAreaTematica";
		}

		if (idTipologia != null) {
			queryString += " AND t.id = :idTipologia";
		}

		if (idDelega != null) {
			// Se esiste la delega allora filtro a monte solo i servizi abilitati con delega
			queryString += " AND s.delega = true";
			queryString += " AND d.id = :idDelega";
			// Se esiste il comuneISA
			if (idComuneISA != null) {
				queryString += " AND d.comuneIsa.id = :idComuneISA2 ";
			}
		}

		if (cittadino != null) {
			queryString += " AND s.cittadino = :cittadino";
		}

		if (azienda != null) {
			queryString += " AND s.azienda = :azienda";
		}

		queryString += " AND s.attivo = :attivo ORDER BY s.nomeServizio ASC";

		Query query = em.createQuery(queryString, Servizio.class);

		if (idAreaTematica != null) {
			query.setParameter("idAreaTematica", idAreaTematica);
		}

		if (idTipologia != null) {
			query.setParameter("idTipologia", idTipologia);
		}

		if (idComuneISA != null) {
			query.setParameter("idComuneISA", idComuneISA);
		}

		if (idDelega != null) {
			query.setParameter("idDelega", idDelega);
			if (idComuneISA != null) {
				query.setParameter("idComuneISA2", idComuneISA);
			}
		}

		if (cittadino != null) {
			query.setParameter("cittadino", cittadino);
		}

		if (azienda != null) {
			query.setParameter("azienda", azienda);
		}

		query.setParameter("attivo", attivo);

		return query.getResultList();
	}

}
