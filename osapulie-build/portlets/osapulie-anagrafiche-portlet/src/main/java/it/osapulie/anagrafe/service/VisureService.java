package it.osapulie.anagrafe.service;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.DatiIndirizzo;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiElettorali;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaVariazioniDomiciliari;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;

/**
 *
 * @author Maria Michela Birtolo
 *
 */
public interface VisureService {

	/**
	 * Metodo che invoca la porta e restituisce i dati necesari per la visura anagrafica
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return DatiAnagrafici
	 */
	DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati necesari per la visura elettorale
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return ComponenteElettorale
	 */
	DatiElettorali richiediDatiElettorali(RichiestaDatiElettorali richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati di base dell'utente
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return DatiUtente
	 */
	DatiUtente richiediDatiAnagraficiAltriServizi(DatiAnagraficiGenerali richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati relativi alla variazioni di domicilio
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return DatiIndirizzo
	 */
	DatiIndirizzo richiediDatiVariazioniDomiciliari(RichiestaVariazioniDomiciliari richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta per l'invio di una dichiarazione tassa rifiuti.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	DichiarazioneTassaRifiutiInputRisposta inviaDichiarazioneTassaRifiuti(DichiarazioneTassaRifiutiInputRichiesta richiesta, UserPreferences userPreferences);

}
