package it.osapulie.persistence;

import it.osapulie.domain.servizi.ServizioErogato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServizioErogatoRepository extends JpaRepository<ServizioErogato, Long> {
	List<ServizioErogato> findAll();
}
