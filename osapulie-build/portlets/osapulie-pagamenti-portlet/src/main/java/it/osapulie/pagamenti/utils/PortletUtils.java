/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pagamenti.utils;

import it.osapulie.tributi.web.ws.output.types.Codifica;
import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili.Posizioni;
import it.osapulie.tributi.web.ws.output.types.Indirizzo;
import it.osapulie.tributi.web.ws.output.types.Indirizzo.Civico;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PortletUtils {

	/**
	 * @param posizioni
	 * @return
	 */
	public static String getIndirizzoFromPosizione(Posizioni posizioni) {
		String indirizzo = "";
		Indirizzo indirizzoUtenza = posizioni.getIndirizzoUtenza();
		if (indirizzoUtenza != null) {
			Codifica via = indirizzoUtenza.getVia();
			if (via != null) {
				indirizzo = via.getDescrizione();
				Civico civico = indirizzoUtenza.getCivico();
				if (civico != null) {
					indirizzo += ", " + civico.getNumero();
					if (civico.getEsponente() != null) {
						indirizzo += "/" + civico.getEsponente();
					}
				}
				if (indirizzoUtenza.getLocalita() != null) {
					indirizzo += ", località " + indirizzoUtenza.getLocalita();
				}
				if (indirizzoUtenza.getCap() != null) {
					indirizzo += ", CAP: " + indirizzoUtenza.getCap();
				}
			}
		}
		return indirizzo;
	}

	/**
	 * @param posizioni
	 * @return
	 */
	public static String getIndirizzoFromPosizione(it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti.Posizioni posizioni) {
		String indirizzo = "";
		Indirizzo indirizzoUtenza = posizioni.getIndirizzoUtenza();
		if (indirizzoUtenza != null) {
			Codifica via = indirizzoUtenza.getVia();
			if (via != null) {
				indirizzo = via.getDescrizione();
				Civico civico = indirizzoUtenza.getCivico();
				if (civico != null) {
					indirizzo += ", " + civico.getNumero();
					if (civico.getEsponente() != null) {
						indirizzo += "/" + civico.getEsponente();
					}
				}
				if (indirizzoUtenza.getLocalita() != null) {
					indirizzo += ", località " + indirizzoUtenza.getLocalita();
				}
				if (indirizzoUtenza.getCap() != null) {
					indirizzo += ", CAP: " + indirizzoUtenza.getCap();
				}
			}
		}
		return indirizzo;
	}
}
