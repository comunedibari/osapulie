/************************************************************************************
 * Copyright (c) 2011, 2014 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.service.impl.protocollo;

import it.osapulie.domain.ComuneISA;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioAllegatoResponse;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.DettaglioProtocolloResponse;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloRequest;
import it.osapulie.protocollo.web.ws.types.NuovoProtocolloResponse;
import it.osapulie.service.ProtocolloService;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.web.portlet.util.CommunicationException;

import org.springframework.stereotype.Service;

/**
 * Implementazione per la comunicazione con il protocollo informatico tramite PDD.
 *
 * @author Gianluca Pindinelli
 *
 */
@Service("protocolloServicePDDImpl")
public class ProtocolloServicePDDImpl extends AbstractServiceImpl implements ProtocolloService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * it.osapulie.service.ProtocolloService#sendProtocollo(it.osapulie.protocollo.web.ws.types.
	 * NuovoProtocolloRequest, it.osapulie.domain.ComuneISA)
	 */
	@Override
	public NuovoProtocolloResponse sendProtocollo(NuovoProtocolloRequest protocolloRequest, ComuneISA comuneISA) throws CommunicationException {

		NuovoProtocolloResponse nuovoProtocolloResponse = esegui("nuovoProtocollo", protocolloRequest, NuovoProtocolloResponse.class, comuneISA.getUriServizioGateway());

		return nuovoProtocolloResponse;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.ProtocolloService#getProtocollo(it.osapulie.protocollo.web.ws.types.
	 * DettaglioProtocolloRequest, it.osapulie.domain.ComuneISA)
	 */
	@Override
	public DettaglioProtocolloResponse getProtocollo(DettaglioProtocolloRequest protocolloRequest, ComuneISA comuneISA) throws CommunicationException {

		DettaglioProtocolloResponse dettaglioProtocolloResponse = esegui("dettaglioProtocollo", protocolloRequest, DettaglioProtocolloResponse.class, comuneISA.getUriServizioGateway());

		return dettaglioProtocolloResponse;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.ProtocolloService#getAllegato(it.osapulie.protocollo.web.ws.types.
	 * DettaglioAllegatoRequest, it.osapulie.domain.ComuneISA)
	 */
	@Override
	public DettaglioAllegatoResponse getAllegato(DettaglioAllegatoRequest allegatoRequest, ComuneISA comuneISA) throws CommunicationException {

		DettaglioAllegatoResponse dettaglioAllegatoResponse = esegui("dettaglioAllegato", allegatoRequest, DettaglioAllegatoResponse.class, comuneISA.getUriServizioGateway());

		return dettaglioAllegatoResponse;
	}

}
