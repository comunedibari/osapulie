package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaLightService.*;

public class VisuraAnagraficaLightServiceEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(VisuraAnagraficaLightServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraAnagraficaLight")
	@ResponsePayload
	public VisureAnagrafiche getVisuraAnagraficaLight(@RequestPayload GetVisuraAnagraficaLight request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraAnagraficaLight CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureAnagrafiche result = of.createVisureAnagrafiche();
		return result;
	}
}
