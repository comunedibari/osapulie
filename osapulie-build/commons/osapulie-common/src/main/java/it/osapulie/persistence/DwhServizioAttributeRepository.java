package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import it.osapulie.domain.DwhServizioAttribute;

public interface DwhServizioAttributeRepository  extends CrudRepository<DwhServizioAttribute, Long> {
	
	@Query("SELECT a FROM DwhServizioAttribute a WHERE a.uuid = ?1")
	List<DwhServizioAttribute> findByUuid(String uuid);
}
