/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.persistence;

import static org.junit.Assert.assertEquals;
import it.osapulie.domain.servizi.Servizio;
import it.osapulie.persistence.ServizioRepository;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit tests per l'implementazione JPA di {@link ServizioRepository}.
 * 
 * @author Mario Scalas
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@Transactional
// Questo è necessario altrimenti l'embedded DB non viene distrutto e i test successivi falliscono
// perchè le tabelle esistono ancora (vedi http://forum.springsource.org/showthread.php?78980-Embedded-DataSources-are-not-dropped-when-context-is-destroyed. )
@DirtiesContext( classMode = ClassMode.AFTER_CLASS )
public class JpaServizioRepositoryTests {
	@Inject
	private ServizioRepository repository;

	@Test 
	public void deveTrovareTuttiIServizi() throws Throwable {
		//given
		long expectedNrObjects = repository.count();

		//when
		List<Servizio> servizi = repository.findAll();
		
		//then
		assertEquals( expectedNrObjects, servizi.size() );
	}	
}
