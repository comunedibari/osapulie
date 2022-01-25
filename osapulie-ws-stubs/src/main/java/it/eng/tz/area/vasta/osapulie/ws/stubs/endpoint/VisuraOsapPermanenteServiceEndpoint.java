package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapPermanenteService.*;

public class VisuraOsapPermanenteServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraOsapPermanenteServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraOsapPermanente")
	@ResponsePayload
	public RateVisuraOsapPermanente getRateVisuraOsapPermanente(@RequestPayload GetRateVisuraOsapPermanente request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRateVisuraOsapPermanente CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraOsapPermanente result = of.createRateVisuraOsapPermanente();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraOsapPermanente")
	@ResponsePayload
	public PosizioniVisuraOsapPermanente getPosizioniVisuraOsapPermanente(@RequestPayload GetPosizioniVisuraOsapPermanente request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraOsapPermanente CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraOsapPermanente result = of.createPosizioniVisuraOsapPermanente();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraOsapPermanente")
	@ResponsePayload
	public VisureOsapPermanente getVisuraOsapPermanente(@RequestPayload GetVisuraOsapPermanente request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraOsapPermanente CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureOsapPermanente result = of.createVisureOsapPermanente();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraOsapPermanente")
	@ResponsePayload
	public RateVisuraOsapPermanente getRataVisuraOsapPermanente(@RequestPayload GetRataVisuraOsapPermanente request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraOsapPermanente CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraOsapPermanente result = of.createRateVisuraOsapPermanente();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureOsapPermanente")
	@ResponsePayload
	public VisureOsapPermanente getVisureOsapPermanente(@RequestPayload GetVisureOsapPermanente request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureOsapPermanente CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureOsapPermanente result = of.createVisureOsapPermanente();
		return result;
	}
}
