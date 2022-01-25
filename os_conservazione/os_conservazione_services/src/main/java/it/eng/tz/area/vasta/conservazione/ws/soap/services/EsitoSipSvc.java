package it.eng.tz.area.vasta.conservazione.ws.soap.services;

import javax.activation.DataHandler;

import it.eng.tz.area.vasta.conservazione.ws.exception.WsIrisException;
import it.eng.tz.area.vasta.conservazione.ws.sip.result.client.SIPResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ControlloEsitoSipResult;

/**
 * Controlla l'esito della conservazione 
 * inviata in precedenza con la classe {@link ReceiveSipSvc}
 *
 */
public interface EsitoSipSvc {
	/**
	 * Verifica lo stato del SIP con ID passato in ingresso
	 * @param idSip -l'id della conservazione. Se nullo o vuoto viene sollevata una {@link IllegalArgumentException}
	 * @return -Il {@link ControlloEsitoSipResult}
	 * @throws WsIrisException -sollevata in caso di errore.
	 */
	ControlloEsitoSipResult controllaConservazione(String idSip) throws WsIrisException;
	/**
	 * Partendo dal {@link DataHandler} in ingresso viene restituito il {@link SIPResult}
	 * @param dh -il {@link DataHandler}. Se nullo o vuoto viene restituito null
	 * @return -il {@link SIPResult}
	 * @throws WsIrisException -sollevata in caso di errore
	 */
	SIPResult toSipResult( DataHandler dh ) throws WsIrisException;
}
