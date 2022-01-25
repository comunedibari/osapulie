/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 * 
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service;

import it.osapulie.domain.ComuneISA;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloResponse;
import it.osapulie.web.portlet.util.CommunicationException;

/**
 * @author Gianluca Pindinelli
 * 
 */
public interface ProtocolloService {

	/**
	 * Effettua la protocollazione.
	 * 
	 * @param protocolloRequest
	 * @param comuneISA
	 * @return
	 * @throws CommunicationException
	 */
	public NuovoProtocolloResponse sendProtocollo(NuovoProtocolloRequest protocolloRequest, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * Ritorna il dettaglio di un protocollo.
	 * 
	 * @param protocolloRequest
	 * @param comuneISA
	 * @return
	 * @throws CommunicationException
	 */
	public DettaglioProtocolloResponse getProtocollo(DettaglioProtocolloRequest protocolloRequest, ComuneISA comuneISA) throws CommunicationException;

	/**
	 * Ritorna un allegato.
	 * 
	 * @param allegatoRequest
	 * @param comuneISA
	 * @return
	 * @throws CommunicationException
	 */
	public DettaglioAllegatoResponse getAllegato(DettaglioAllegatoRequest allegatoRequest, ComuneISA comuneISA) throws CommunicationException;

}
