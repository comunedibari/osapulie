/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import java.util.Map;

import it.osapulie.anagrafe.web.ws.output.types.DatiAnagrafici.ComponentiNucleoFamiliare;
import it.osapulie.domain.json.configurazione.Configurazione;
import it.osapulie.domain.json.configurazione.ConfigurazioneServizi;
import it.osapulie.domain.json.configurazione.InfoAggiuntive;
import it.osapulie.domain.json.segnalazione.SegnalazioneCustom;

/**
 * Servizi per il caricamento delle configurazioni di sistema (solitamente salvate come JSON).
 *
 * @author Gianluca Pindinelli
 *
 */
public interface ConfigurazioneService {

	/**
	 * Carica la mappa delle condizioni (parametri booleani) per visualizzare/nascondere
	 * funzionalità riguardanti la generazione dei certificati (sulla base del JSON
	 * "configurazioneServizi" in "info_aggiuntive" di un dato Comune ISA).
	 *
	 * @param componente
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	Map<String, Boolean> getCondizioniGenerazioneCertificati(ComponentiNucleoFamiliare componente, String codiceServizio, long idComuneISA);

	/**
	 * Carica il valore della condizione in base ai parametri passati in input.
	 *
	 * @param conditionName
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	String getCondizioneGenerazioneCertficiati(String conditionName, String codiceServizio, long idComuneISA);

	/**
	 * Carica la URL del servizio successivo a quello passato in input (sulla base del JSON
	 * "configurazioneServizi" in "info_aggiuntive" di un dato Comune ISA --> redirect_servizio).
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	String getRedirectServizioUrl(String codiceServizio, long idComuneISA);

	/**
	 * Verifica se lo stradario risulta abilitato o meno per il comune ed il servizio passati in
	 * input (sulla base del JSON "configurazioneServizi" in "info_aggiuntive" di un dato Comune
	 * ISA).
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	boolean isStradarioEnable(String codiceServizio, long idComuneISA);

	/**
	 * Verifica se l'interoperabilità (invio dell'XML di input alla PDD) è abilitata o meno per il
	 * comune ed il servizio (sulla base del JSON "configurazioneServizi" in "info_aggiuntive" di un
	 * dato Comune ISA).
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	boolean isInteroperabilitaEnable(String codiceServizio, long idComuneISA);

	/**
	 * Carica la particolare configurazione servizio correlata ad un dato comune.
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @param configurazione
	 * @return
	 */
	Configurazione getConfigurazioneServizioComune(String codiceServizio, long idComuneISA, String configurazione);

	/**
	 * Carica le info aggiuntive correlate ad un dato comune.
	 *
	 * @param idComuneISA
	 * @return
	 */
	InfoAggiuntive getInfoAggiuntiveComune(long idComuneISA);

	/**
	 * Carica le configurazioni servizi (da info aggiuntive) correlate ad un dato comune.
	 *
	 * @param idComuneISA
	 * @return
	 */
	ConfigurazioneServizi getConfigurazioneServiziComune(long idComuneISA);

	/**
	 *
	 * Verifica se il servizio risulta abilitato o meno per essere utilizzato dal CAF (sulla base
	 * del JSON "configurazioneServizi" in "info_aggiuntive" di un dato Comune ISA).
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	boolean isServizioEnableForCAF(String codiceServizio, long idComuneISA);

	/**
	 * Carica, se presente, la segnalazione custom di un servizio (da info aggiuntive) correlate ad
	 * un dato comune.
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	SegnalazioneCustom getSegnalazioneCustomServizio(String codiceServizio, long idComuneISA);

	/**
	 * Verifica se il servizio risulta abilitato o meno ad utilizzare la posizione tributaria
	 * (condizione <code>visura_posizione_tributaria</code>). Il metodo, se la configurazione se non
	 * è settata, ritorna <code>true</code>.
	 *
	 * @param codiceServizio
	 * @param idComuneISA
	 * @return
	 */
	boolean isVisuraPosizioneTributariaEnable(String codiceServizio, long idComuneISA);

}
