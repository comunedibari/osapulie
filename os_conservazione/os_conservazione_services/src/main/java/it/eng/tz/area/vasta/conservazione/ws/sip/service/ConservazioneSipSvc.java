package it.eng.tz.area.vasta.conservazione.ws.sip.service;

import java.util.List;

import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.sip.dao.model.ConservazioneSipModel;

/**
 * Si preoccupa di inserire e/o modificare i record della conservazione
 *
 */
public interface ConservazioneSipSvc {
	/**
	 * Salva il record della conservazione utilizzando il {@link ConservazioneSipModel} passato in ingresso
	 * {@link ConservazioneSipModel#getId()} restituisce l'ID del WS
	 * @param model -il model da salvare
	 * @throws ConservazioneSipException -sollevata in caso di errore
	 */
	void salvaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException;
	/**
	 * Aggiorna il record specificato dall'ID {@link ConservazioneSipModel#getId()} restituisce l'ID del WS 
	 * settando l'esito del controllo SIP 
	 * @param model -il model contenente i dati
	 * @throws ConservazioneSipException -sollevata in caso di errore
	 */
	void aggiornaConservazioneSip(ConservazioneSipModel model) throws ConservazioneSipException;
	/**
	 * Restituiscel il {@link List} di record da processare per controllare l'esito dell'elaborazione SIP
	 * @return -il {@link List}
	 * @throws ConservazioneSipException -sollevata in caso di errore
	 */
	List<String> daProcessare() throws ConservazioneSipException;
	
	List<String> recuperaFolderProcessati() throws ConservazioneSipException;
	
	List<String> recuperaFolderDaProcessare() throws ConservazioneSipException;
	
	List<String> recuperaAllFolder() throws ConservazioneSipException;
	
	boolean isFolderExist(String folder) throws ConservazioneSipException;
	
}
