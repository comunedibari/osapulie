/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.web.portlet.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import it.osapulie.domain.ComuneISA;
import it.osapulie.persistence.ComuneISARepository;
import it.osapulie.test.RepositoryTestHelper;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import static org.mockito.Mockito.*;

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
public class ComuneValidatorTests {
	@Inject @Named( "comuneValidator" )
	private Validator validator;
	
	@Inject
	private RepositoryTestHelper testHelper;

	@Test 
	public void deveValidareUnComuneEsistente() throws Throwable {
		//given
		ComuneISA object = testHelper.createMockComune( 999 );
		
		Errors errors = Mockito.mock( Errors.class );
		
		//when
		validator.validate( object, errors );
		
		//then
		verify( errors, never() ).reject( anyString(), anyString() );
	}

	@Test 
	public void deveRespingereUnComuneInvalido() throws Throwable {
		//given
		ComuneISA object = new ComuneISA();
		
		Errors errors = Mockito.mock( Errors.class );
		
		//when
		validator.validate( object, errors );
		
		//then
		verify( errors, atLeastOnce() ).rejectValue( anyString(), anyString(), (Object[]) isNull(), (String) isNull() );
	}
}
