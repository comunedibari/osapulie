/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.portlet.util;

/**
 * Classe contenente le costanti proprie della portlet.
 *
 * @author Gianluca Pindinelli
 *
 */
public class PortletConstants {

	public static final String STATO_DELEGA_ATTIVA = "attiva";
	public static final String STATO_DELEGA_INATTIVA = "inattiva";

	/**
	 * Stato iniziale della richiesta (in attesa di verifica da parte del Comune)
	 */
	public static final String STATO_PIN_RICHIESTO = "RICHIESTO";
	/**
	 * Richiesta respinta dal Comune
	 */
	public static final String STATO_PIN_RESPINTO = "RESPINTO";
	/**
	 * Richiesta annullata dal Cittadino
	 */
	public static final String STATO_PIN_ANNULLATO = "ANNULLATO";
	/**
	 * Richiesta evasa positivamente dal Comune (PIN assegnato)
	 */
	public static final String STATO_PIN_ASSEGNATO = "ASSEGNATO";

	public static final String CANALE_COMUNICAZIONE_PEC = "PEC";
	public static final String CANALE_COMUNICAZIONE_EMAIL = "EMAIL";
	public static final String CANALE_COMUNICAZIONE_PROTOCOLLO = "PROTOCOLLO";

	public static final long NO_COMUNE_ISA = -1;

	public static final String COMUNE_ISA_PARAMETER_NAME = "codiceIstatComune";

	public static final String TEMPLATE_XML = "XML";
	public static final String TEMPLATE_JASPER_REPORT = "JR";

	public static final String UNAUTHORIZED_EXCEPTION_CANALE_NON_ATTIVO = "UE1";
	public static final String UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_REGIONE_PUGLIA = "UE2";
	public static final String UNAUTHORIZED_EXCEPTION_AUTORIZZAZZIONI_INSUFFICIENTI_SPID = "UE3";

	public static final String GESTIONE_UTENTI_CODICE_FISCALE_REQUEST_PARAMETER = "codiceFiscale";
	public static final String GESTIONE_UTENTI_RETURN_URL_REQUEST_PARAMETER = "returnUrl";

	public static final String SEGNALAZIONI_SUCCESS_RETURN_URL_REQUEST_PARAMETER = "successReturnUrl";
	public static final String SEGNALAZIONI_CANCEL_RETURN_URL_REQUEST_PARAMETER = "cancelReturnUrl";

	public static final String SEGNALAZIONE_SHARED_ATTRIBUTE = "LIFERAY_SHARED_segnalazione";
	public static final String SEGNALAZIONE_CUSTOM_SHARED_ATTRIBUTE = "LIFERAY_SHARED_segnalazione_custom";
	public static final String SEGNALAZIONE_SERVIZIO_SHARED_ATTRIBUTE = "LIFERAY_SHARED_segnalazione_servizio_";

}
