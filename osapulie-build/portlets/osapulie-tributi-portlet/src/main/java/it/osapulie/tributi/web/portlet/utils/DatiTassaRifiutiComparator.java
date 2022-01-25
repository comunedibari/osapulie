/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.utils;

import java.util.Comparator;

import it.osapulie.tributi.web.ws.output.types.DatiTassaRifiuti;

/**
 * Classe comparator per {@link DatiTassaRifiuti}.
 *
 * @author Gianluca Pindinelli
 *
 */
public class DatiTassaRifiutiComparator implements Comparator<DatiTassaRifiuti> {

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(DatiTassaRifiuti o1, DatiTassaRifiuti o2) {
		return o1.getAnnoRiferimento() - o2.getAnnoRiferimento();
	}

}
