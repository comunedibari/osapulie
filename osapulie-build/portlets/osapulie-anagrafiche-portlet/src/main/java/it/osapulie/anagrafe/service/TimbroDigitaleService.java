package it.osapulie.anagrafe.service;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici;
import it.osapulie.anagrafe.web.ws.output.types.DatiAnagraficiGenerali;
import it.osapulie.anagrafe.web.ws.output.types.DatiUtente;
import it.osapulie.shared.service.UserPreferences;

import java.util.Map;

/**
 * @author Maria Michela Birtolo
 */
public interface TimbroDigitaleService {

    byte[] richiediCertificatoTimbrato(String xml, UserPreferences userPreferences);

    byte[] richiediCertificatoTimbrato(Map<String,String> servizio, String codiceFiscale, UserPreferences userPreferences, String utilizzoBollo, String numeroBollo, DatiAnagrafici.ComponentiNucleoFamiliare componenteSelezionato, DatiAnagrafici dati) throws Exception;

    byte[] richiediCertificatoTimbrato(Map<String,String> servizio, String codiceFiscale, UserPreferences userPreferences, String utilizzoBollo, String numeroBollo, DatiAnagrafici.ComponentiNucleoFamiliare componenteSelezionato, boolean subReport, DatiAnagrafici dati) throws Exception;
}
