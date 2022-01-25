package it.eng.tz.area.vasta.conservazione.ws.soap.services;

import java.io.File;

import it.eng.tz.area.vasta.conservazione.ws.exception.WsIrisException;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;
/**
 * Service per l'invio in conservazione di un archivio ZIP contenente i log
 * applicativi
 *
 */
public interface ReceiveSipSvc {
	/**
	 * Si preoccupa di inviare in conservazione l'archivio ZIP
	 * @param tipoSip -indica il tipo di Sip; se nullo o vuoto non sarà valorizzato
	 * @param tipoProtezione -indica il tipo di protezione; se nullo o vuoto non sarà valorizzato
	 * @param improntaAlgoritmo; indica l'impronta dell'algoritmo. Se nullo o vuono viene generata una {@link IllegalArgumentException}
	 * @param improntaCodifica; indica l'impronta della codifica. Se nullo o vuono viene generata una {@link IllegalArgumentException}
	 * @param impronta; indica l'impronta del file da conservare. Se nullo o vuono viene generata una {@link IllegalArgumentException}
	 * @param fileDaConservare; l'archivio zip da conservare. Se nullo o vuono viene generata una {@link IllegalArgumentException}
	 * @return -l'esito dell'invio
	 * @throws WsIrisException Sollevata in caso di errore
	 */
	ReceiveSipResult inviaConservazione( String tipoSip, String tipoProtezione, String improntaAlgoritmo, String improntaCodifica, String impronta, File fileDaConservare,String folder) throws WsIrisException;
}
