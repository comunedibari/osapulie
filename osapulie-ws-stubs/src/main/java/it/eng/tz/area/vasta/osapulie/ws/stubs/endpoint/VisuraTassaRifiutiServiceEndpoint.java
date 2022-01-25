package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaRifiutiService.*;

public class VisuraTassaRifiutiServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraTassaRifiutiServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getOccupazioniPosizioneVisuraTassaRifiuti")
	@ResponsePayload
	public OccupazioniPosizioneVisuraTassaRifiuti getOccupazioniPosizioneVisuraTassaRifiuti(@RequestPayload GetOccupazioniPosizioneVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getOccupazioniPosizioneVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		OccupazioniPosizioneVisuraTassaRifiuti result = of.createOccupazioniPosizioneVisuraTassaRifiuti();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraTassaRifiuti")
	@ResponsePayload
	public RateVisuraTassaRifiuti getRateVisuraTassaRifiuti(@RequestPayload GetRateVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRateVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraTassaRifiuti result = of.createRateVisuraTassaRifiuti();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraTassaRifiuti")
	@ResponsePayload
	public VisuraTassaRifiuti getVisuraTassaRifiuti(@RequestPayload GetVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisuraTassaRifiuti result = of.createVisuraTassaRifiuti();
		result.setCodiceFiscale("get Visura Tassa Rifiuti");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraTassaRifiuti")
	@ResponsePayload
	public RataVisuraTassaRifiuti getRataVisuraTassaRifiuti(@RequestPayload GetRataVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RataVisuraTassaRifiuti result = of.createRataVisuraTassaRifiuti();
		result.setIdentificativoRata("get Rata Visura Tassa Rifiuti");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureTassaRifiuti")
	@ResponsePayload
	public VisureTassaRifiuti getVisureTassaRifiuti(@RequestPayload GetVisureTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureTassaRifiuti result = of.createVisureTassaRifiuti();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRiduzioniVisuraTassaRifiuti")
	@ResponsePayload
	public RiduzioniPosizioneVisuraTassaRifiuti getRiduzioniVisuraTassaRifiuti(@RequestPayload GetRiduzioniVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRiduzioniVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RiduzioniPosizioneVisuraTassaRifiuti result = of.createRiduzioniPosizioneVisuraTassaRifiuti();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraTassaRifiuti")
	@ResponsePayload
	public PosizioniVisuraTassaRifiuti getPosizioniVisuraTassaRifiuti(@RequestPayload GetPosizioniVisuraTassaRifiuti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraTassaRifiuti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraTassaRifiuti result = of.createPosizioniVisuraTassaRifiuti();
		return result;
	}
}
