/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.pdds.util;

import java.util.Comparator;

import it.osapulie.servizi.Indirizzo;

/**
 * @author Gianluca Pindinelli
 *
 */
public class IndirizzoComparator implements Comparator<Indirizzo> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Indirizzo o1, Indirizzo o2) {
		return o1.getNumeroCivico().compareTo(o2.getNumeroCivico());
	}

}
