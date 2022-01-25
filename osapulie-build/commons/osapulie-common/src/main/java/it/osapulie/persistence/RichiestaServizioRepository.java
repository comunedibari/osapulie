/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.sun.istack.Nullable;

import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.domain.servizi.Servizio;

/**
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 */
public interface RichiestaServizioRepository extends CrudRepository<RichiestaServizio, Long> {

	/**
	 * Recupera il {@link RichiestaServizio}.
	 *
	 * @param fascicolo
	 * @param checksum
	 * @return RichiestaServizio
	 */
	@Nullable
	RichiestaServizio findByFascicoloAndChecksumAndServizio(FascicoloUtente fascicolo, String checksum, Servizio servizio);

	@Nullable
	RichiestaServizio findByFascicoloAndInfoAggiuntiveLike(FascicoloUtente fascicolo, String infoAggiuntive);

	@Query("SELECT COUNT(r) FROM RichiestaServizio r WHERE r.servizio.id = ?1 AND r.nomeServizio NOT LIKE '%DOWNLOAD PDF' AND r.nomeServizio NOT LIKE 'Invio segnalazione%'")
	long countByServizio(Long idServizio);

	@Query("SELECT COUNT(r) FROM RichiestaServizio r WHERE r.nomeServizio LIKE ?1 AND r.ricercabileDaComune = true")
	long countByLikeNomeServizio(String nomeServizio);

	@Query("SELECT COUNT(r) FROM RichiestaServizio r WHERE r.fascicolo.cittadino.codiceFiscale = ?1 AND r.servizio.codiceServizio = ?2 AND r.dataRichiesta BETWEEN ?3 AND ?4 AND r.ricercabileDaComune = true")
	List<RichiestaServizio> findByCodiceFiscaleAndServizio(String codiceFiscale, String codiceServizio, Date from, Date to);

	@Query("SELECT COUNT(r) FROM RichiestaServizio r WHERE r.fascicolo.cittadino.codiceFiscale = ?1 AND r.servizio.codiceServizio = ?2 AND r.nomeServizio = ?3 AND r.dataRichiesta BETWEEN ?4 AND ?5")
	List<RichiestaServizio> findByCodiceFiscaleAndServizio(String codiceFiscale, String codiceServizio, String nomeServizio, Date from, Date to);

}
