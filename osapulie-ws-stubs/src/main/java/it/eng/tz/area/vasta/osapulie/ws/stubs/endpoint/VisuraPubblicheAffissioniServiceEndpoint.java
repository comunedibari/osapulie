package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraPubblicheAffissioniService.*;

public class VisuraPubblicheAffissioniServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraPubblicheAffissioniServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraPubblicheAffissioni")
	@ResponsePayload
	public RateVisuraPubblicheAffissioni getRateVisuraPubblicheAffissioni(@RequestPayload GetRateVisuraPubblicheAffissioni request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRateVisuraPubblicheAffissioni CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraPubblicheAffissioni result = of.createRateVisuraPubblicheAffissioni();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraPubblicheAffissioni")
	@ResponsePayload
	public PosizioniVisuraPubblicheAffissioni getPosizioniVisuraPubblicheAffissioni(@RequestPayload GetPosizioniVisuraPubblicheAffissioni request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraPubblicheAffissioni CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraPubblicheAffissioni result = of.createPosizioniVisuraPubblicheAffissioni();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisurePubblicheAffissioni")
	@ResponsePayload
	public VisurePubblicheAffissioni getVisurePubblicheAffissioni(@RequestPayload GetVisurePubblicheAffissioni request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisurePubblicheAffissioni CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisurePubblicheAffissioni result = of.createVisurePubblicheAffissioni();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraPubblicheAffissioni")
	@ResponsePayload
	public RataVisuraPubblicheAffissioni getRataVisuraPubblicheAffissioni(@RequestPayload GetRataVisuraPubblicheAffissioni request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraPubblicheAffissioni CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RataVisuraPubblicheAffissioni result = of.createRataVisuraPubblicheAffissioni();
		result.setIdentificativoRata("get Rata Visura Pubbliche Affissioni");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraPubblicheAffissioni")
	@ResponsePayload
	public VisuraPubblicheAffissioni getVisuraPubblicheAffissioni(@RequestPayload GetVisuraPubblicheAffissioni request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraPubblicheAffissioni CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisuraPubblicheAffissioni result = of.createVisuraPubblicheAffissioni();
		result.setCodiceFiscale("get Visura Pubbliche Affissioni");
		return result;
	}
}
