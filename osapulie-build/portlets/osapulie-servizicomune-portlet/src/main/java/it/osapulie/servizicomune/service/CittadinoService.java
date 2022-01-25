package it.osapulie.servizicomune.service;

import org.springframework.ui.Model;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.RichiestaDatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.shared.service.UserPreferences;

public interface CittadinoService {

	/**
	 * Metodo che invoca la porta e restituisce i dati necesari per la circolarità anagrafica
	 *
	 * @param richiesta
	 * @param userPreferences
	 * @return DatiAnagrafici
	 */
	
	DatiAnagrafici richiediDatiAnagrafici(RichiestaDatiAnagrafici richiesta, UserPreferences userPreferences);	
	
	/**
	 * Aggiunge al {@link Model} le informazioni aggiuntive riguardanti un componente del nucleo
	 * familiare.
	 *
	 * @param model
	 * @param componente
	 */
	void addInfoAggiuntiveComponenteToModel(Model model, ComponentiNucleoFamiliare componente);
}
