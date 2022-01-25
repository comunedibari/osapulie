/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.persistence;

import static org.junit.Assert.assertEquals;
import it.osapulie.domain.servizi.ServizioErogato;
import it.osapulie.persistence.ServizioErogatoRepository;

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
 * Unit tests per l'implementazione JPA di {@link ServizioErogatoRepository}.
 * 
 * @author Mario Scalas
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@Transactional
// Questo è necessario altrimenti l'embedded DB non viene distrutto e i test successivi falliscono
// perchè le tabelle esistono ancora (vedi http://forum.springsource.org/showthread.php?78980-Embedded-DataSources-are-not-dropped-when-context-is-destroyed. )
@DirtiesContext( classMode = ClassMode.AFTER_CLASS )
public class JpaServizioErogatoRepositoryTests {
	@Inject
	private ServizioErogatoRepository repository;

	@Test 
	public void deveTrovareTuttiIComuni() throws Throwable {
		//given
		long expectedNrObjects = repository.count();

		//when
		List<ServizioErogato> serviziErogati = repository.findAll();
		
		//then
		assertEquals( expectedNrObjects, serviziErogati.size() );
	}	
}
