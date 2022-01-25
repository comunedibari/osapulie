package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraServiziCimiterialiService.*;

public class VisuraServiziCimiterialiServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraServiziCimiterialiServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraServiziCimiteriali")
	@ResponsePayload
	public RataVisuraServiziCimiteriali getRataVisuraServiziCimiteriali(@RequestPayload GetRataVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RataVisuraServiziCimiteriali result = of.createRataVisuraServiziCimiteriali();
		result.setIdentificativoRata("get Rata Visura Servizi Cimiteriali");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getDefuntiPosizioneVisuraServiziCimiteriali")
	@ResponsePayload
	public DefuntiPosizioneVisuraServiziCimiteriali getDefuntiPosizioneVisuraServiziCimiteriali(@RequestPayload GetDefuntiPosizioneVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getDefuntiPosizioneVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		DefuntiPosizioneVisuraServiziCimiteriali result = of.createDefuntiPosizioneVisuraServiziCimiteriali();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraServiziCimiteriali")
	@ResponsePayload
	public RateVisuraServiziCimiteriali getRateVisuraServiziCimiteriali(@RequestPayload GetRateVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRateVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		RateVisuraServiziCimiteriali result = of.createRateVisuraServiziCimiteriali();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraServiziCimiteriali")
	@ResponsePayload
	public PosizioniVisuraServiziCimiteriali getPosizioniVisuraServiziCimiteriali(@RequestPayload GetPosizioniVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		PosizioniVisuraServiziCimiteriali result = of.createPosizioniVisuraServiziCimiteriali();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraServiziCimiteriali")
	@ResponsePayload
	public VisuraServiziCimiteriali getVisuraServiziCimiteriali(@RequestPayload GetVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisuraServiziCimiteriali result = of.createVisuraServiziCimiteriali();
		result.setCodiceFiscale("get Visura Servizi Cimiteriali");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getLampadeVotivePosizioneVisuraServiziCimiteriali")
	@ResponsePayload
	public LampadeVotivePosizioneVisuraServiziCimiteriali getLampadeVotivePosizioneVisuraServiziCimiteriali(@RequestPayload GetLampadeVotivePosizioneVisuraServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getLampadeVotivePosizioneVisuraServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		LampadeVotivePosizioneVisuraServiziCimiteriali result = of.createLampadeVotivePosizioneVisuraServiziCimiteriali();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureServiziCimiteriali")
	@ResponsePayload
	public VisureServiziCimiteriali getVisureServiziCimiteriali(@RequestPayload GetVisureServiziCimiteriali request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureServiziCimiteriali CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureServiziCimiteriali result = of.createVisureServiziCimiteriali();
		return result;
	}
}
