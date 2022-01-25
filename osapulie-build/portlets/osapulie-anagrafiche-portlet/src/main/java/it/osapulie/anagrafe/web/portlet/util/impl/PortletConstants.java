/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.web.portlet.util.impl;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe per lo store delle costanti relative la portlet.
 *
 * @author Maria Michela Birtolo
 *
 */
public class PortletConstants extends it.osapulie.web.portlet.util.PortletConstants {

	public static final String CODICE_SERVIZIO_VISURA_POSIZIONE_ANAGRAFICA = "AV01";
	public static final String CODICE_SERVIZIO_VISURA_POSIZIONE_ELETTORALE = "AV02";

	public static final String CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_CONTESTUALE_MATRIMONIO = "AC03";
	public static final String CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_CITTADINANZA = "AC04";
	public static final Map<String,String> CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_IN_VITA = fillMap("AC05","CERTIFICATO DI ESISTENZA IN VITA","/reports/richiestaCertificatoInVita.jrxml","certificato-in-vita.pdf");
	public static final String CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MATRIMONIO = "AC06";
	public static final String CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_MORTE = "AC07";
	public static final Map<String,String> SERVIZIO_RICHIESTA_CERTIFICATO_NASCITA = fillMap("AC08","CERTIFICATO DI NASCITA","/reports/richiestaCertificatoNascita.jrxml","certificato-di-nascita.pdf");
	public static final Map<String,String> SERVIZIO_RICHIESTA_CERTIFICATO_RESIDENZA = fillMap("AC09","CERTIFICATO DI NASCITA","/reports/richiestaCertificatoResidenza.jrxml","certificato-di-residenza.pdf");
	public static final Map<String,String> CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_STATO_FAMIGLIA =  fillMap("AC10","CERTIFICATO DI NASCITA","/reports/richiestaCertificatoStatoFamiglia.jrxml","certificato-stato-famiglia.pdf");
	public static final Map<String,String> CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_STATO_LIBERO = fillMap("AC11","CERTIFICATO DI STATO LIBERO","/reports/richiestaCertificatoStatoLibero.jrxml","certificato-stato-libero.pdf");
	public static final Map<String,String> CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_ISCRIZIONE_LISTE_ELETTORALI = fillMap("AC12","CERTIFICATO DI ISCRIZIONE ALLE LISTE ELETTORALI","/reports/richiestaCertificatoIscrizioneListeElettorali.jrxml","certificato-iscrizione-liste-elettorali.pdf");
	public static final Map<String,String> CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VEDOVANZA = fillMap("AC13","CERTIFICATO DI VEDOVANZA","/reports/richiestaCertificatoVedovanza.jrxml","certificato-vedovanza.pdf");
	public static final String CODICE_SERVIZIO_RICHIESTA_CERTIFICATO_VARIAZIONI_DOMICILIARI = "AC14";

	public static final String CODICE_SERVIZIO_AUTOCERTIFICAZIONI = "AA15";

	public static final String CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_RESIDENZA = "AD16";
	public static final String CODICE_SERVIZIO_DICHIARAZIONE_CAMBIO_DOMICILIO = "AD17";
	public static final String CODICE_SERVIZIO_ISCRIZIONE_ANAGRAFE_TEMPORANEA = "AD18";
	public static final String CODICE_SERVIZIO_ISCRIZIONE_ALBO_SCRUTATORI = "AD19";
	public static final String CODICE_SERVIZIO_ISCRIZIONE_ALBO_PRESIDENTI = "AD20";

	public static final String CODICE_SERVIZIO_DICHIARAZIONE_TARI = "TD05";

	public static final String INTEROP_FILE_NAME = "richiesta.xml";
	public static final String DICHIARAZIONE_TARI_SHARED_ATTRIBUTE = "LIFERAY_SHARED_dichiarazione_tari_ok";

	public static final String CODICE = "CODICE";
	public static final String DESCRIZIONE = "DESCRIZIONE";
	public static final String REPORT = "REPORT";
	public static final String PDF = "PDF";


	private static Map<String,String> fillMap(String codice,String descrizione, String report, String pdfName){
		Map<String,String> map = new HashMap<String, String>();
		map.put("CODICE",codice);
		map.put("DESCRIZIONE",descrizione);
		map.put("REPORT",report);
		map.put("PDF",pdfName);
		return map;

	}
}
