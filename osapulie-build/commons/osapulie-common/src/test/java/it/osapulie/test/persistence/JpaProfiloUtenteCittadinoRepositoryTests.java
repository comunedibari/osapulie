/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;
import it.osapulie.test.RepositoryTestHelper;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Unit tests per l'implementazione JPA di {@link ProfiloUtenteCittadinoRepository}.
 * 
 * @author Mario Scalas
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
// Questo è necessario altrimenti l'embedded DB non viene distrutto e i test successivi falliscono
// perchè le tabelle esistono ancora (vedi
// http://forum.springsource.org/showthread.php?78980-Embedded-DataSources-are-not-dropped-when-context-is-destroyed.
// )
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class JpaProfiloUtenteCittadinoRepositoryTests {

	@Inject
	private ProfiloUtenteCittadinoRepository repository;

	@Inject
	private RepositoryTestHelper testHelper;

	@Test
	@Rollback(false)
	public void deveSalvareUnCittadino() throws Throwable {
		// given
		ProfiloUtenteCittadino utente = testHelper.getOrCreateMockCittadino(9999);

		// when
		ProfiloUtenteCittadino saved = repository.save(utente);

		// then
		assertTrue(testHelper.isManaged(saved));
	}

	@Test
	public void deveTrovareUnCittadinoById() throws Throwable {
		// given
		String id = "cittadino_1";

		// when
		ProfiloUtenteCittadino utente = repository.findOne(Long.parseLong(id));

		// then
		assertNotNull(utente);
	}

	@Test
	public void deveTrovareUnCittadinoByCodiceFiscale() throws Throwable {
		// given
		String codiceFiscale = "               1";

		// when
		ProfiloUtenteCittadino utente = repository.findByCodiceFiscale(codiceFiscale);

		// then
		assertNotNull(utente);
	}

	@Test
	public void deveTrovareUnCittadinoByCodiceFiscale_RitornaNullSeInesistente() throws Throwable {
		// given
		String codiceFiscale = "NON ESISTE CF  1";

		// when
		ProfiloUtenteCittadino utente = repository.findByCodiceFiscale(codiceFiscale);

		// then
		assertNull(utente);
	}

	@Test
	public void deveTrovareTuttiICittadini() throws Throwable {
		// given
		long expectedNrObjects = testHelper.getNrCittadini();

		// when
		long actualNr = repository.count();

		// then
		assertEquals(expectedNrObjects, actualNr);
	}
}
