package it.osapulie.tributi.service;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.infrastructure.security.OSApulieUserDetails;
import it.osapulie.shared.service.UserPreferences;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRichiesta;
import it.osapulie.tributi.web.ws.input.types.DichiarazioneTassaRifiutiInputRisposta;
import it.osapulie.tributi.web.ws.input.types.Tracciamento;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.CategorieImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaImmobiliRisposta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.DichiarazioneTassaRifiutiRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraPosizioneTributariaRisposta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRichiesta;
import it.osapulie.tributi.web.ws.output.types.VisuraTassaRifiutiRisposta;

/**
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 *
 */
public interface TributiService {

	/**
	 * Metodo che invoca la porta e restituisce i dati riguardanti la dichiarazione tassa rifiuti.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	DichiarazioneTassaRifiutiRisposta richiediSituazioneTassaRifiuti(DichiarazioneTassaRifiutiRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati riguardanti la visura tassa rifiuti.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	VisuraTassaRifiutiRisposta richiediVisuraTassaRifiuti(VisuraTassaRifiutiRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati riguardanti la dichiarazione tassa immobili.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	DichiarazioneTassaImmobiliRisposta richiediSituazioneTassaImmobili(DichiarazioneTassaImmobiliRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati necessari per la visura anagrafica.
	 *
	 * @param richiesta
	 * @return
	 */
	DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati di base dell'utente.
	 *
	 * @param richiesta
	 * @return
	 */
	DatiUtente richiediDatiAnagraficiAltriServizi(DatiAnagraficiGenerali richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce la posizione tributaria.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	VisuraPosizioneTributariaRisposta richiediPosizioneTributaria(VisuraPosizioneTributariaRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta e restituisce i dati utili al calcolo tassa sugli immobili.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	CategorieImmobiliRisposta richiediDatiCategorieImmobili(CategorieImmobiliRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Metodo che invoca la porta per l'invio di una dichiarazione tassa rifiuti.
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return
	 */
	DichiarazioneTassaRifiutiInputRisposta inviaDichiarazione(DichiarazioneTassaRifiutiInputRichiesta richiesta, UserPreferences userPreferences);

	/**
	 * Ritorna il {@link Tracciamento} in funzione dell'utente loggato.
	 *
	 * @param osApulieUserDetails
	 * @param userPreferences
	 * @return
	 */
	Tracciamento getTracciamento(OSApulieUserDetails osApulieUserDetails, UserPreferences userPreferences);

	/**
	 * @param richiesta
	 * @return
	 */
	String getDichiarazioneXml(DichiarazioneTassaRifiutiInputRichiesta richiesta);
}
