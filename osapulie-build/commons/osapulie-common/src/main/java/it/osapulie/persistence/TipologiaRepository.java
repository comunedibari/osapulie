package it.osapulie.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import it.osapulie.domain.servizi.Tipologia;

public interface TipologiaRepository extends JpaRepository<Tipologia, Long> {

	@Override
	List<Tipologia> findAll();
}
