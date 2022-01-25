package it.osapulie.persistence;
 

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.osapulie.domain.DwhGeolocalizzazione;


 
public interface DwhGeolocalizzazioneRepository extends CrudRepository<DwhGeolocalizzazione, Long> {

	@Query("SELECT a FROM DwhGeolocalizzazione a WHERE a.cod_user = ?1")
	List<DwhGeolocalizzazione> findByUserCod(String userCod);
	 
}
