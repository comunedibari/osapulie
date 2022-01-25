/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.test;

import it.osapulie.domain.Comune;
import it.osapulie.domain.ComuneISA;
import it.osapulie.domain.Indirizzo;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;
import it.osapulie.domain.fascicoloutente.RichiestaServizio;
import it.osapulie.persistence.ComuneRepository;
import it.osapulie.persistence.FascicoloUtenteRepository;
import it.osapulie.persistence.ProfiloUtenteCittadinoRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

/**
 * Definisce un DB in memoria contenente dati di test.
 * 
 * @author Mario Scalas
 */
@Component("testHelper")
public class RepositoryTestHelper {

	private Map<Long, Comune> comuni;

	private Map<Long, ProfiloUtenteCittadino> cittadini;

	@Inject
	private ComuneRepository comuneRepository;

	@Inject
	private ProfiloUtenteCittadinoRepository cittadinoRepository;

	@Inject
	private FascicoloUtenteRepository fascicoloRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@PostConstruct
	public void populate() {
		cacheComuni(10);
		cacheCittadini(10);
	}

	/**
	 * @return
	 * @throws Exception
	 */
	private void cacheComuni(int nrComuni) {
		comuni = new HashMap<Long, Comune>();
		for (Comune o : comuneRepository.findAll()) {
			comuni.put(o.getId(), o);
		}
	}

	/**
	 * @param i
	 */
	private void cacheCittadini(int nrCittadini) {
		cittadini = new HashMap<Long, ProfiloUtenteCittadino>();
		for (ProfiloUtenteCittadino o : cittadinoRepository.findAll()) {
			cittadini.put(o.getId(), o);
		}
	}

	/**
	 * Verifica che un oggetto sia un'effettiva JPA entity correntemente gestita dall'
	 * {@link EntityManager}.
	 * 
	 * @param entity
	 * @return <code>true</code> se l'oggetto è una entity JPA correntemente gestita,
	 *         <code>false</code> altrimenti
	 */
	public boolean isManaged(Object entity) {
		return entityManager.contains(entity);
	}

	/**
	 * @param i
	 * @return
	 */
	public ProfiloUtenteCittadino getOrCreateMockCittadino(long i) {
		ProfiloUtenteCittadino utente = cittadini.get(i);
		if (utente == null) {
			utente = new ProfiloUtenteCittadino();
		}
		utente.setNome("John #" + i);
		utente.setCognome("Doe #" + i);
		utente.setMailPec(String.format("citt%d@pecmail.com", i));
		utente.setCodiceFiscale(String.format("%16d", i));

		Indirizzo indirizzo = new Indirizzo();
		indirizzo.setVia("via De Pippis # " + i);
		indirizzo.setNrCivico("" + i);
		indirizzo.setComune(comuni.get(i % comuni.size() + 1));

		utente.setResidenza(indirizzo);

		return utente;
	}

	/**
	 * @return
	 * @throws IOException
	 */
	public ComuneISA createMockComune(long n) {
		ComuneISA object = new ComuneISA();
		// object.setLogo( ImageUtils.getImageBytes( RepositoryTestHelper.class.getResourceAsStream(
		// "/images/test_image.png" ) ) );
		object.setNome("Città #" + n);
		object.setDescrizione("ComuneISA di " + object.getNome());
		object.setCap(String.format("%06d", n));
		object.setCodiceIstat(String.format("%06d", n));
		object.setUriServizioGateway("http://www/foo/services");

		return object;
	}

	/**
	 * Restituisce il numero di comuni nel repository.
	 * 
	 * @return
	 */
	public long getNrComuni() {
		return comuneRepository.count();
	}

	/**
	 * @return
	 */
	public long getNrCittadini() {
		return cittadinoRepository.count();
	}

	/**
	 * @param i
	 * @return
	 */
	public FascicoloUtente createMockFascicolo(long i) {
		FascicoloUtente fascicolo = new FascicoloUtente();

		ProfiloUtenteCittadino cittadino = cittadini.get(i % cittadini.size() + 1);
		fascicolo.setCittadino(cittadino);
		fascicolo.setRichieste(new ArrayList<RichiestaServizio>());

		return fascicolo;
	}

	/**
	 * Restituisce il numero di fascicoli presenti nel repository.
	 * 
	 * @return
	 */
	public long getNrFascicoli() {
		return fascicoloRepository.count();
	}
}
