package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.osapulie.domain.Audit;

public interface AuditRepository extends CrudRepository<Audit, Long> {

	@Query("SELECT a FROM Audit a WHERE a.userCod = ?1")
	List<Audit> findByUserCod(String userCod);

	@Query("SELECT a FROM Audit a WHERE a.fileName = ?1")
	List<Audit> findByFileName(String fileName);
	
	@Query("SELECT a FROM Audit a")
	List<Audit> getAllAudit();

	@Query("SELECT a FROM Audit a WHERE a.id = ?1")
	Audit getAuditByPk(long idAudit);
	
	@Query("SELECT a FROM Audit a WHERE a.giornoMeseAnno = ?1")
	List<Audit> findByGiornoMeseAnno(String giornoMeseAnno);
}
