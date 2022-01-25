/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.utils;

/**
 * Classe per lo store delle costanti relative la portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortletConstants extends it.osapulie.web.portlet.util.PortletConstants {

	public static final String CODICE_SERVIZIO_PAGAMENTO_AFFISSIONI = "TP17";
	public static final String CODICE_SERVIZIO_PAGAMENTO_ICI = "TP03";
	public static final String CODICE_SERVIZIO_PAGAMENTO_PUBBLICITA = "TP15";
	public static final String CODICE_SERVIZIO_PAGAMENTO_SERVIZI_CIMITERIALI = "TP13";
	public static final String CODICE_SERVIZIO_PAGAMENTO_TARSU = "TP06";
	public static final String CODICE_SERVIZIO_PAGAMENTO_COSAP_TOSAP_PERMANENTE = "TP11";
	public static final String CODICE_SERVIZIO_PAGAMENTO_COSAP_TOSAP_TEMPORANEA = "TP10";
	public static final String CODICE_SERVIZIO_VISURA_POSIZIONE_TRIBUTARIA = "TV19";
	public static final String CODICE_SERVIZIO_VISURA_POSIZIONE_SERVIZI_CIMITERIALI = "TV12";
	public static final String CODICE_SERVIZIO_VISURA_COSAP_TOSAP_PERMANENTE = "TV08";
	public static final String CODICE_SERVIZIO_VISURA_COSAP_TOSAP_TEMPORANEA = "TV09";

	public static final String PAGAMENTO_ID_PAGAMENTO_JSON_KEY = "identificativoPagamento";
	public static final String PAGAMENTO_ID_CREDITO_JSON_KEY = "identificativoCredito";
	public static final String PAGAMENTO_RICEVUTA_PAGAMENTO_ORGANIZZAZIONE_JSON_KEY = "ricevutaPagamentoOrganizzazione";
	public static final String PAGAMENTO_STATO_JSON_KEY = "stato_pagamento";
	public static final String PAGAMENTO_STATO_IN_ATTESA = "IN ATTESA";
	public static final String PAGAMENTO_STATO_PAGATO = "PAGATO";

}
