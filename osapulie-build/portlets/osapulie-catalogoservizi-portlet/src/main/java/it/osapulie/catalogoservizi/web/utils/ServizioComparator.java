/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.catalogoservizi.web.utils;

import it.osapulie.domain.servizi.Servizio;

import java.util.Comparator;

/**
 * {@link Comparator} per l'ordinamento dei servizi in base al nome servizio.
 * 
 * @author Gianluca Pindinelli
 *
 */
public class ServizioComparator implements Comparator<Servizio> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Servizio o1, Servizio o2) {

		if (o1 != null && o2 != null) {
			return o1.getNomeServizio().compareTo(o2.getNomeServizio());
		}

		return 0;
	}

}
