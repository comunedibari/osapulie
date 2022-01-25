package it.osapulie.persistence;

import it.osapulie.domain.servizi.AreaTematica;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AreaTematicaRepository extends JpaRepository<AreaTematica, Long> {

	@Override
	List<AreaTematica> findAll();

}
