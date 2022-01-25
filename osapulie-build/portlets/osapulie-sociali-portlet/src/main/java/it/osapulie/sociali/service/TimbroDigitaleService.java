package it.osapulie.sociali.service;

import it.osapulie.shared.service.UserPreferences;

/**
 *
 * @author Maria Michela Birtolo
 *
 */
public interface TimbroDigitaleService {

	byte[] richiediCertificatoTimbrato(String xml, UserPreferences userPreferences);

}
