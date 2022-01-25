/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.persistence;

import java.util.Collection;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import it.osapulie.domain.ComuneISA;

/**
 * Repository di esempio. <strong>Nota</strong> questo repository è implementato tramite Spring Data
 * JPA.
 *
 * @author Mario Scalas
 */
public interface ComuneISARepository extends PagingAndSortingRepository<ComuneISA, Long> {

	/**
	 * Ritorna l'elenco di tutti i comuni presenti.
	 *
	 * @return
	 */
	@Override
	Collection<ComuneISA> findAll();

	/**
	 * @param codiceIstat
	 * @return
	 */
	ComuneISA findByCodiceIstat(String codiceIstat);

	/**
	 * @param cap
	 * @return
	 */
	ComuneISA findByCap(String cap);

	/**
	 * Ritorna l'elenco di tutti i comuni attivi presenti.
	 *
	 * @return
	 */
	List<ComuneISA> findByAttivo(Boolean attivo);

	/**
	 * Ritorna l'elenco di tutti i comuni attivi presenti, ordinata con il primo elemento in cui
	 * "ordine = 1" e gli altri per "nome".
	 *
	 * @return
	 */
	@Query("SELECT c FROM ComuneISA c WHERE c.attivo = ?1 ORDER BY CASE WHEN c.ordine = 1 THEN 1 ELSE 2 END, c.nome")
	List<ComuneISA> findByAttivoOrderByFirstOrdineNomeAsc(Boolean attivo);

	/**
	 * @param codiceIPA
	 * @return
	 */
	ComuneISA findByAmministrazione(String codiceIPA);

}
