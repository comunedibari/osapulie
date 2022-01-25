package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaImmobiliService.*;

public class VisuraTassaImmobiliServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraTassaImmobiliServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureTassaImmobili")
	@ResponsePayload
	public VisureTassaImmobili getVisureTassaImmobili(@RequestPayload GetVisureTassaImmobili request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureTassaImmobili CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureTassaImmobili result = of.createVisureTassaImmobili();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraTassaImmobili")
	@ResponsePayload
	public PosizioniVisuraTassaImmobili getPosizioniVisuraTassaImmobili(@RequestPayload GetPosizioniVisuraTassaImmobili request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraTassaImmobili CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraTassaImmobili result = of.createPosizioniVisuraTassaImmobili();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraTassaImmobili")
	@ResponsePayload
	public RateVisuraTassaImmobili getRateVisuraTassaImmobili(@RequestPayload GetRateVisuraTassaImmobili request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRateVisuraTassaImmobili CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraTassaImmobili result = of.createRateVisuraTassaImmobili();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraTassaImmobili")
	@ResponsePayload
	public VisuraTassaImmobili getVisuraTassaImmobili(@RequestPayload GetVisuraTassaImmobili request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraTassaImmobili CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisuraTassaImmobili result = of.createVisuraTassaImmobili();
		result.setCodiceFiscale("get Visura Tassa Immobili");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraTassaImmobili")
	@ResponsePayload
	public RataVisuraTassaImmobili getRataVisuraTassaImmobili(@RequestPayload GetRataVisuraTassaImmobili request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraTassaImmobili CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RataVisuraTassaImmobili result = of.createRataVisuraTassaImmobili();
		result.setIdentificativoRata("get Rata Visura Tassa Immobili");
		return result;
	}
}
