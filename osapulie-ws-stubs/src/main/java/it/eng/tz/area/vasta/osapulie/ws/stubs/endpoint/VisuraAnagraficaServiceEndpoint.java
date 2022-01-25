package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService.*;

public class VisuraAnagraficaServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraAnagraficaServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraAnagrafica")
	@ResponsePayload
	public VisureAnagrafiche getVisuraAnagrafica(@RequestPayload GetVisuraAnagrafica request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraAnagrafica CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureAnagrafiche result = of.createVisureAnagrafiche();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureAnagraficheNucleoFamiliare")
	@ResponsePayload
	public VisureAnagrafiche getVisureAnagraficheNucleoFamiliare(@RequestPayload GetVisureAnagraficheNucleoFamiliare request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureAnagraficheNucleoFamiliare CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		VisureAnagrafiche result = of.createVisureAnagrafiche();
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPensioniComponenteVisuraAnagrafica")
	@ResponsePayload
	public GetPensioniComponenteVisuraAnagrafica getPensioniComponenteVisuraAnagrafica(@RequestPayload GetPensioniComponenteVisuraAnagrafica request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPensioniComponenteVisuraAnagrafica CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetPensioniComponenteVisuraAnagrafica result = of.createGetPensioniComponenteVisuraAnagrafica();
		result.setIdComponente(1);
		return result;
	}
}
