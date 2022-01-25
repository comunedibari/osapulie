/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.persistence.FascicoloUtenteRepository;
import it.osapulie.test.RepositoryTestHelper;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit tests per l'implementazione JPA di {@link FascicoloUtenteRepository}.
 * 
 * @author Mario Scalas
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@Transactional
//Questo è necessario altrimenti l'embedded DB non viene distrutto e i test successivi falliscono
//perchè le tabelle esistono ancora (vedi http://forum.springsource.org/showthread.php?78980-Embedded-DataSources-are-not-dropped-when-context-is-destroyed. )
@DirtiesContext( classMode = ClassMode.AFTER_CLASS )
public class JpaFascicoloUtenteRepositoryTests {
	@Inject
	private FascicoloUtenteRepository repository;
	
	@Inject
	private RepositoryTestHelper testHelper;
	
	@Test 
	public void deveSalvareUnNuovoFascicolo() throws Throwable {
		//given
		FascicoloUtente fascicolo = testHelper.createMockFascicolo( 9999 );
		
		//when
		FascicoloUtente saved = repository.save( fascicolo );
		
		//then
		assertTrue( saved.getId() > 0 );
	}

//	@Test 
	public void deveTrovareUnFascicoloById() throws Throwable {
		//given
		Long id = 1L;

		//when
		FascicoloUtente fascicolo = repository.findOne( id );
		
		//then
		assertNotNull( fascicolo );
	}

	@Test 
	public void deveTrovareTuttiIFascicoli() throws Throwable {
		//given
		long expectedNrObjects = testHelper.getNrFascicoli();

		//when
		long actualNr = repository.count();
		
		//then
		assertEquals( expectedNrObjects, actualNr );
	}	
}
