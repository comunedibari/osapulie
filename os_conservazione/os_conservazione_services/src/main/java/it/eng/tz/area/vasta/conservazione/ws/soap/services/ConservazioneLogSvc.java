package it.eng.tz.area.vasta.conservazione.ws.soap.services;

import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;

/**
 * Service per la creazione del file ZIP da inviare in conservazione
 *
 */
public interface ConservazioneLogSvc {
	
	/**
	 * Si preoccupa di creare il file ZIP da conservare
	 * @param path -il path contenente i log da inviare in conservazione
	 * @return -L'esito della conservazione
	 * @throws ConservazioneSipException -sollevata in caso di errore
	 */
	ReceiveSipResult conservaLog( String path ) throws ConservazioneSipException;
}
