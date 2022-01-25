package it.osapulie.persistence;
 

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.osapulie.domain.DwhTempiMedi;

 
public interface DwhTempiMediRepository extends CrudRepository<DwhTempiMedi, Long> {

	@Query("SELECT a FROM DwhTempiMedi a WHERE a.uuid_operazione = ?1")
	List<DwhTempiMedi> findByUuidOperazione(String uuidOperazione);
	 
}
