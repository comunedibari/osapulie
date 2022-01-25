/************************************************************************************
 * Copyright (c) 2011, 2016 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.utils;

import java.util.Comparator;

import it.osapulie.domain.ComuneISA;

/**
 * @author Gianluca Pindinelli
 *
 */
public class ComuneISAComparator implements Comparator<ComuneISA> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(ComuneISA o1, ComuneISA o2) {
		return o1.getNome().compareTo(o2.getNome());
	}

}
