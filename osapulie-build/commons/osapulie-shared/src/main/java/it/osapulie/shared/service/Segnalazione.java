/************************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.shared.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe di gestione delle segnalazioni
 * contenente il servizio dal quale viene inviata la segnalazione,
 * il campo note inserite dall'utente e la lista di segnalazioni
 * @author Piano Amleto
 *
 */
public class Segnalazione implements Serializable{
	private static final long serialVersionUID = 1739998181241323851L;
	private String servizio;
	private String servizioCode;
	private String note;
	private List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();
	public String getServizio() {
		return servizio;
	}
	public void setServizio(String servizio) {
		this.servizio = servizio;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<SegnalazioneFoglia> getListaSegnalazioni() {
		return listaSegnalazioni;
	}
	public void setListaSegnalazioni(List<SegnalazioneFoglia> listaSegnalazioni) {
		this.listaSegnalazioni = listaSegnalazioni;
	}
	public String getServizioCode() {
		return servizioCode;
	}
	public void setServizioCode(String servizioCode) {
		this.servizioCode = servizioCode;
	}	
}