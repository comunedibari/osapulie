package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraDocumentiTributiService.*;

public class VisuraDocumentiTributiServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraDocumentiTributiServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getDocumento")
	@ResponsePayload
	public Documenti getDocumento(@RequestPayload GetDocumento request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getDocumento CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		Documenti result = of.createDocumenti();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getDocumenti")
	@ResponsePayload
	public Documenti getDocumenti(@RequestPayload GetDocumenti request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getDocumenti CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		Documenti result = of.createDocumenti();
		return result;
	}
}
