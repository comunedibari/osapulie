/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.osapulie.domain.ComuneISA;
import it.osapulie.persistence.ComuneISARepository;
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
 * Unit tests per l'implementazione JPA di {@link ComuneISARepository}.
 * 
 * @author Mario Scalas
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration
@Transactional
// Questo è necessario altrimenti l'embedded DB non viene distrutto e i test successivi falliscono
// perchè le tabelle esistono ancora (vedi http://forum.springsource.org/showthread.php?78980-Embedded-DataSources-are-not-dropped-when-context-is-destroyed. )
@DirtiesContext( classMode = ClassMode.AFTER_CLASS )
public class JpaComuneRepositoryTests {
	@Inject
	private ComuneISARepository repository;
	
	@Inject
	private RepositoryTestHelper testHelper;

	@Test 
	public void deveSalvareUnComune() throws Throwable {
		//given
		ComuneISA object = testHelper.createMockComune( 999 );
		
		//when
		ComuneISA saved = repository.save( object );
		
		//then
		assertTrue( saved.getId() > 0 );
	}

	@Test
	public void deveSalvareUnComuneEsistente() throws Throwable {
		//given
		ComuneISA object = repository.findOne( 1L );
		
		//when
		object.setCodiceIstat( "999999" );
		ComuneISA saved = repository.save( object );
		
		//then
		assertTrue( saved.getId() > 0 );
	}
	
	@Test 
	public void deveTrovareUnComuneById() throws Throwable {
		//given
		Long id = 1L;

		//when
		ComuneISA object = repository.findOne( id );
		
		//then
		assertNotNull( object );
	}

	@Test 
	public void deveTrovareTuttiIComuni() throws Throwable {
		//given
		long expectedNrObjects = testHelper.getNrComuni();

		//when
		long actualNr = repository.count();
		
		//then
		assertEquals( expectedNrObjects, actualNr );
	}	
}
