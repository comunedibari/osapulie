package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraVariazioniDomicilioService.*;

public class VisuraVariazioniDomicilioServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraVariazioniDomicilioServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureVariazioniDomicilio")
	@ResponsePayload
	public VisureVariazioniDomicilio getVisureVariazioniDomicilio(@RequestPayload GetVisureVariazioniDomicilio request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO chiamaEnte CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureVariazioniDomicilio result = of.createVisureVariazioniDomicilio();
		return result;
	}
}
