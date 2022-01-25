/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.web.ws.pddsintegration.pdd;

import it.puglia.rupar.egov.pdd.portaDiDominio.EccezionePDDS;
import it.puglia.rupar.egov.pdd.portaDiDominio.PortaDelegata;

/**
 * @author Gianluca Pindinelli
 *
 */
public class PortaDelegataSingleton {

	private final PortaDelegata portaDelegata;

	private static PortaDelegataSingleton istance = null;

	private PortaDelegataSingleton() throws EccezionePDDS {
		portaDelegata = new PortaDelegata();
	}

	public synchronized static PortaDelegataSingleton getIstance() throws EccezionePDDS {
		if (istance == null) {
			istance = new PortaDelegataSingleton();
		}
		return istance;
	}

	/**
	 * @return the portaDelegata
	 */
	public PortaDelegata getPortaDelegata() {
		return portaDelegata;
	}

}
