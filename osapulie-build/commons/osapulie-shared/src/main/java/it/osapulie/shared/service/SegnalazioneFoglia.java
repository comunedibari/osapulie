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
 * Bean relativo al singolo valore da segnalare relativo al campo (chiave) con valore (valore_old)
 * ed il valore corretto(valore_new) Questa classe gestisce il check, il booleano in caso di
 * elemento foglia.
 * 
 * @author Piano Amleto
 */
public class SegnalazioneFoglia implements Serializable {

	private static final long serialVersionUID = -7547163673369170563L;
	private String chiave;
	private String valore_old = "";
	private String valore_new = "";
	private boolean check;
	private List<SegnalazioneFoglia> listaSegnalazioni = new ArrayList<SegnalazioneFoglia>();

	public String getChiave() {
		return chiave;
	}

	public boolean isCheck() {
		if (isFoglia())
			return check;
		else {
			for (SegnalazioneFoglia segnalazione : listaSegnalazioni) {
				if (segnalazione.isCheck())
					return true;
			}
			return false;
		}
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public void setChiave(String chiave) {
		this.chiave = chiave;
	}

	public String getValore_old() {
		return valore_old;
	}

	public void setValore_old(String valore_old) {
		this.valore_old = valore_old;
	}

	public String getValore_new() {
		return valore_new;
	}

	public void setValore_new(String valore_new) {
		this.valore_new = valore_new;
	}

	public boolean isFoglia() {
		if (listaSegnalazioni != null && listaSegnalazioni.size() > 0)
			return false;
		else
			return true;
	}

	public List<SegnalazioneFoglia> getListaSegnalazioni() {
		return listaSegnalazioni;
	}

	public void setListaSegnalazioni(List<SegnalazioneFoglia> listaSegnalazioni) {
		this.listaSegnalazioni = listaSegnalazioni;
	}

	public SegnalazioneFoglia(String chiave) {
		this.chiave = chiave;
	}

	public SegnalazioneFoglia(String chiave, String valore_old) {
		if (chiave != null)
			this.chiave = chiave;
		if (valore_old != null)
			this.valore_old = valore_old;
	}
}
