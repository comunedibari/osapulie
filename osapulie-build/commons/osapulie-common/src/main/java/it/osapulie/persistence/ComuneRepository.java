/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.persistence;

import java.util.Collection;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import it.osapulie.domain.Comune;

/**
 * Repository di esempio. <strong>Nota</strong> questo repository è implementato tramite Spring Data
 * JPA.
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public interface ComuneRepository extends PagingAndSortingRepository<Comune, Long> {

	/**
	 * Ritorna l'elenco di tutti i comuni presenti.
	 *
	 * @return
	 */
	@Override
	Collection<Comune> findAll();

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework
	 * .data.domain.Sort)
	 */
	@Override
	Iterable<Comune> findAll(Sort sort);

	/**
	 *
	 * @param codiceIstat
	 * @return
	 */
	Comune findByCodiceIstat1(String codiceIstat);

	/**
	 *
	 * @param codiceIstat
	 * @return
	 */
	Comune findByCodiceIstatAN(String codiceIstatAN);

	/**
	 * Caricamento comune in base al codice istat ricercato nelle colonne
	 * <code>codiceIstatSN</code>,<code>codiceIstat1</code>, <code>codiceIstat103</code> e
	 * <code>codiceIstat107</code>.
	 *
	 * @param codiceIstat
	 * @return
	 */
	@Query("SELECT c from Comune c WHERE c.codiceIstat1 = ?1 OR c.codiceIstat103 = ?1 OR c.codiceIstat107 = ?1 OR c.codiceIstatAN = ?1")
	Comune findByCodiciIstat(String codiceIstat);

	/**
	 *
	 * @param codiceCatastale
	 * @return
	 */
	Comune findByCodiceCatastale(String codiceCatastale);

	/**
	 * Ritorna l'elenco di tutti i comuni appartenenti ad una data provincia.
	 *
	 * @param idProvincia
	 * @param sort
	 * @return
	 */
	@Query("SELECT c from Comune c WHERE c.provincia.id = ?1")
	Collection<Comune> findByIdProvincia(long idProvincia, Sort sort);

}
