/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.persistence;

import org.springframework.data.repository.CrudRepository;

import com.sun.istack.Nullable;

import it.osapulie.domain.Azienda;
import it.osapulie.domain.ProfiloUtenteCittadino;
import it.osapulie.domain.fascicoloutente.FascicoloUtente;

/**
 * @author Mario Scalas
 * @author Maria Michela Birtolo
 */
public interface FascicoloUtenteRepository extends CrudRepository<FascicoloUtente, Long> {

	/**
	 * Recupera il {@link FascicoloUtente} dato il profilo utente cittadino.
	 *
	 * @param cittadino
	 * @return
	 */
	@Nullable
	FascicoloUtente findByCittadino(ProfiloUtenteCittadino cittadino);

	/**
	 * Recupera il {@link FascicoloUtente} data l'azienda passata in input.
	 *
	 * @param azienda
	 * @return
	 */
	@Nullable
	FascicoloUtente findByAzienda(Azienda azienda);
}
