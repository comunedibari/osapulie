package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.osapulie.domain.servizi.Servizio;

public interface ServizioRepository extends JpaRepository<Servizio, Long> {

	@Override
	List<Servizio> findAll();

	/**
	 * Recupera il {@link Servizio} dato il codice.
	 *
	 * @param codiceServizio
	 * @return Servizio
	 */
	Servizio findByCodiceServizio(String codiceServizio);

	/**
	 * Recupera la lista dei {@link Servizio} in base alla sua area tematica.
	 *
	 * @param idAreaTematica
	 * @return
	 */
	List<Servizio> findByAreaTematica(int idAreaTematica);

	/**
	 * Recupera il {@link Servizio} dato l'uri del servizio.
	 *
	 * @param uri
	 * @return
	 */
	Servizio findByUri(String uri);

	/**
	 * Recupera il {@link Servizio} dato l'uri della scheda servizio.
	 *
	 * @param uriScheda
	 * @return
	 */
	Servizio findByUriScheda(String uriScheda);

	/**
	 * Recupera la lista dei {@link Servizio} in base al suo stato.
	 *
	 * @param attivo
	 * @param sort
	 * @return
	 */
	List<Servizio> findByAttivo(boolean attivo, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} abilitati ai cittadini.
	 *
	 * @param cittadino
	 * @param sort
	 * @return
	 */
	List<Servizio> findByCittadino(boolean cittadino, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} abilitati alle aziende.
	 *
	 * @param azienda
	 * @param sort
	 * @return
	 */
	List<Servizio> findByAzienda(boolean azienda, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} in base allo stato delega.
	 *
	 * @param delega
	 * @param sort
	 * @return
	 */
	@Query("SELECT s FROM Servizio s WHERE s.attivo = true AND s.delega = :delega")
	List<Servizio> findByDelega(@Param("delega") boolean delega, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} in base allo stato delega.
	 *
	 * @param delega
	 * @param sort
	 * @return
	 */
	@Query("SELECT s FROM Servizio s INNER JOIN s.comuni c WHERE c.idComuneISA = :id AND s.attivo = true AND s.delega = :delega")
	List<Servizio> findByComuneISAAndDelega(@Param("id") long idComune, @Param("delega") boolean delega, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} attivi appartenenti ad un dato comune ISA.
	 *
	 * @param id
	 * @param sort
	 * @return
	 */
	@Query("SELECT s FROM Servizio s INNER JOIN s.comuni c WHERE c.idComuneISA = :id AND s.attivo = true AND c.attivo = true AND c.comuneISA.attivo = true")
	List<Servizio> findByComuneISA(@Param("id") long id, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} attivi appartenenti ad un dato comune ISA, abilitati
	 * ai cittadini.
	 *
	 * @param id
	 * @param sort
	 * @return
	 */
	@Query("SELECT s FROM Servizio s INNER JOIN s.comuni c WHERE c.idComuneISA = :id AND s.attivo = true AND c.attivo = true AND s.cittadino = true AND c.comuneISA.attivo = true")
	List<Servizio> findByComuneISAForCittadino(@Param("id") long id, Sort sort);

	/**
	 * Recupera la lista dei {@link Servizio} attivi appartenenti ad un dato comune ISA, abilitati
	 * alle aziende.
	 *
	 * @param id
	 * @param sort
	 * @return
	 */
	@Query("SELECT s FROM Servizio s INNER JOIN s.comuni c WHERE c.idComuneISA = :id AND s.attivo = true AND c.attivo = true AND s.azienda = true AND c.comuneISA.attivo = true")
	List<Servizio> findByComuneISAForAzienda(@Param("id") long id, Sort sort);
}
