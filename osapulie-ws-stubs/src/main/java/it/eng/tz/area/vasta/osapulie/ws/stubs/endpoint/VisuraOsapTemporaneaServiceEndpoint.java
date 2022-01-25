package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapTemporaneaService.*;

public class VisuraOsapTemporaneaServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraOsapTemporaneaServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureOsapTemporanea")
	@ResponsePayload
	public VisureOsapTemporanea getVisureOsapTemporanea(@RequestPayload GetVisureOsapTemporanea request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureOsapTemporanea CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureOsapTemporanea result = of.createVisureOsapTemporanea();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraOsapTemporanea")
	@ResponsePayload
	public PosizioniVisuraOsapTemporanea getPosizioniVisuraOsapTemporanea(@RequestPayload GetPosizioniVisuraOsapTemporanea request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraOsapTemporanea CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraOsapTemporanea result = of.createPosizioniVisuraOsapTemporanea();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraOsapTemporanea")
	@ResponsePayload
	public VisuraOsapTemporanea getVisuraOsapTemporanea(@RequestPayload GetVisuraOsapTemporanea request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraOsapTemporanea CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisuraOsapTemporanea result = of.createVisuraOsapTemporanea();
		result.setNumeroDocumento("get Visura Osap Temporanea");
		return result;
	}
}
