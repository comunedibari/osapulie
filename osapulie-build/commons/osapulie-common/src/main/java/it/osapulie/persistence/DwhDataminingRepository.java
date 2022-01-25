package it.osapulie.persistence;
 

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.osapulie.domain.DwhDatamining;


 
public interface DwhDataminingRepository extends CrudRepository<DwhDatamining, Long> {

	@Query("SELECT a FROM DwhDatamining a WHERE a.cod_cittadino = ?1")
	List<DwhDatamining> findByUserCod(String userCod);
	 
}
