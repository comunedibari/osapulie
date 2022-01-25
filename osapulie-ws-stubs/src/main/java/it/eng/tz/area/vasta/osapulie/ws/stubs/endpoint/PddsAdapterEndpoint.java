package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter.ChiamaEnte;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter.ChiamaEnteResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter.EseguiRichiestaServizio;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter.EseguiRichiestaServizioResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter.ObjectFactory;

@Endpoint
public class PddsAdapterEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(PddsAdapterEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://pdd.adapter.ws.web.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="chiamaEnte")
	@ResponsePayload
	public ChiamaEnteResponse chiamaEnte(@RequestPayload ChiamaEnte request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO chiamaEnte CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		ChiamaEnteResponse result = of.createChiamaEnteResponse();
		result.setChiamaEnteReturn("Chiama ente");
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="eseguiRichiestaServizio")
	@ResponsePayload
	public EseguiRichiestaServizioResponse eseguiRichiestaServizio(@RequestPayload EseguiRichiestaServizio request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO eseguiRichiestaServizio CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		EseguiRichiestaServizioResponse result = of.createEseguiRichiestaServizioResponse();
		result.setEseguiRichiestaServizioReturn("Esegui Richiesta Servizio");
		return result;
	}

}
