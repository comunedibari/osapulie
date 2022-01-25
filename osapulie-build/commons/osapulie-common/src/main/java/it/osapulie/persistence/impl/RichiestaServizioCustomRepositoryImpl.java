/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.persistence.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.persistence.RichiestaServizioCustomRepository;

/**
 * @author Gianluca Pindinelli
 *
 */
@Repository
public class RichiestaServizioCustomRepositoryImpl implements RichiestaServizioCustomRepository {

	@PersistenceContext
	private EntityManager em;

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.RichiestaServizioCustomRepository#findRichiestaServizio(java.lang.
	 * Long, java.lang.String, java.lang.String, java.lang.String, java.lang.Long, java.lang.Long,
	 * java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> findRichiestaServizio(Long idComuneISA, String cognome, String nome, String codiceFiscale, Long idServizio, Long idAzienda, Date da, Date a) {
		String queryString = "SELECT rs FROM RichiestaServizio rs JOIN rs.fascicolo f LEFT JOIN f.cittadino c LEFT JOIN f.azienda a WHERE 1=1";

		if (idComuneISA != null) {
			queryString += " AND rs.comuneErogatore.id = ?1";
		}
		if (cognome != null && !cognome.equals("")) {
			queryString += " AND (c.cognome LIKE lower(?2) OR a.ragioneSociale LIKE lower(?2))";
		}
		if (nome != null && !nome.equals("")) {
			queryString += " AND c.nome LIKE lower(?3)";
		}

		if (codiceFiscale != null && !codiceFiscale.equals("")) {
			queryString += " AND (c.codiceFiscale LIKE lower(?4) OR a.partitaIva LIKE lower(?4))";
		}

		if (idServizio != null) {
			queryString += " AND rs.servizio.id = ?5";
		}

		if (idAzienda != null) {
			queryString += " AND rs.delegato.azienda.id = ?6";
		}

		if (da != null && a == null) {
			queryString += " AND rs.dataRichiesta >= ?7";
		}
		else if (da == null && a != null) {
			queryString += " AND rs.dataRichiesta <= ?8";
		}
		else if (da != null && a != null) {
			queryString += " AND rs.dataRichiesta BETWEEN ?7 AND ?8";
		}

		//queryString += " AND rs.ricercabileDaComune = true";

		Query query = em.createQuery(queryString, RichiestaServizio.class);

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
		if (idServizio != null) {
			query.setParameter(5, idServizio);
		}
		if (idAzienda != null) {
			query.setParameter(6, idAzienda);
		}
		if (da != null && a == null) {
			query.setParameter(7, da);
		}
		else if (da == null && a != null) {
			query.setParameter(8, a);
		}
		else if (da != null && a != null) {
			query.setParameter(7, da);
			query.setParameter(8, a);
		}

		return query.getResultList();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * it.osapulie.persistence.RichiestaServizioCustomRepository#findRichiestaServizio(java.lang.
	 * Long, java.lang.Long, java.util.Date, java.util.Date)
	 */
	@Override
	public List<RichiestaServizio> findRichiestaServizio(Long idAzienda, Long idProfiloUtenteCittadino, Date da, Date a, Long idServizio) {
		String queryString = "SELECT rs FROM RichiestaServizio rs WHERE 1=1";

		if (idAzienda != null) {
			queryString += " AND rs.delegato.azienda.id = ?1";
		}
		if (idProfiloUtenteCittadino != null) {
			queryString += " AND rs.delegato.profiloUtenteCittadino.id = ?2";
		}
		if (da != null && a == null) {
			queryString += " AND rs.dataRichiesta >= ?3";
		}
		else if (da == null && a != null) {
			queryString += " AND rs.dataRichiesta <= ?4";
		}
		else if (da != null && a != null) {
			queryString += " AND rs.dataRichiesta BETWEEN ?3 AND ?4";
		}
		if (idServizio != null) {
			queryString += " AND rs.servizio.id = ?5";
		}

//		queryString += " AND rs.ricercabileDaComune = true ORDER BY rs.dataRichiesta DESC";
		queryString += " ORDER BY rs.dataRichiesta DESC";

		Query query = em.createQuery(queryString, RichiestaServizio.class);

		if (idAzienda != null) {
			query.setParameter(1, idAzienda);
		}
		if (idProfiloUtenteCittadino != null) {
			query.setParameter(2, idProfiloUtenteCittadino);
		}
		if (da != null && a == null) {
			query.setParameter(3, da);
		}
		else if (da == null && a != null) {
			query.setParameter(4, a);
		}
		else if (da != null && a != null) {
			query.setParameter(3, da);
			query.setParameter(4, a);
		}

		if (idServizio != null) {
			query.setParameter(5, idServizio);
		}

		return query.getResultList();
	}

	@Override
	public List<RichiestaServizio> findRichiestaServizioByFilters(Long idComuneISA,  String codiceFiscale, Long tipologia, Long idAzienda, Date da, Date a) {
		String queryString = "SELECT rs FROM RichiestaServizio rs";
		
		if(idAzienda != null)
			queryString += " JOIN rs.delegato.azienda a";
		
		if(codiceFiscale != null && !codiceFiscale.equals(""))
			queryString += " JOIN rs.delegato.profiloUtenteCittadino c";
		
		if(tipologia != null)
			queryString += " JOIN rs.servizio.tipologie t";
		
		queryString += " WHERE 1=1";
		
		if (idComuneISA != null) {
			queryString += " AND rs.comuneErogatore.id = ?1";
		}
		
		if (codiceFiscale != null && !codiceFiscale.equals("")) {
			queryString += " AND (c.codiceFiscale LIKE lower(?2))";
		}

		if (tipologia != null) {
			queryString += " AND t.id = ?3";
		}

		if (idAzienda != null) {
			queryString += " AND a.id = ?4";
		}

		if (da != null && a == null) {
			queryString += " AND rs.dataRichiesta >= ?5";
		}
		else if (da == null && a != null) {
			queryString += " AND rs.dataRichiesta <= ?6";
		}
		else if (da != null && a != null) {
			queryString += " AND rs.dataRichiesta BETWEEN ?5 AND ?6";
		}

		queryString += " AND rs.ricercabileDaComune = true";

		Query query = em.createQuery(queryString, RichiestaServizio.class);

		if (idComuneISA != null) {
			query.setParameter(1, idComuneISA);
		}
	
		if (codiceFiscale != null && !codiceFiscale.equals("")) {
			query.setParameter(2, "%" + codiceFiscale.toLowerCase() + "%");
		}
		if (tipologia != null) {
			query.setParameter(3, tipologia);
		}
		if (idAzienda != null) {
			query.setParameter(4, idAzienda);
		}
		if (da != null && a == null) {
			query.setParameter(5, da);
		}
		else if (da == null && a != null) {
			query.setParameter(6, a);
		}
		else if (da != null && a != null) {
			query.setParameter(5, da);
			query.setParameter(6, a);
		}

		return query.getResultList();
	}
	
}
