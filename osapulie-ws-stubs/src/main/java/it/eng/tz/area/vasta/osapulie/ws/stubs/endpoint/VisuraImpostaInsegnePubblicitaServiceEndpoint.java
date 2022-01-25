package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.GetPosizioniVisuraImposteInsegnePubblicita;
import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.GetRataVisuraImposteInsegnePubblicita;
import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.GetRateVisuraImposteInsegnePubblicita;
import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.GetVisuraImposteInsegnePubblicita;
import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.GetVisureImposteInsegnePubblicita;
import it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService.ObjectFactory;

public class VisuraImpostaInsegnePubblicitaServiceEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(VisuraImpostaInsegnePubblicitaServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://servizi.osapulie.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRataVisuraImposteInsegnePubblicita")
	@ResponsePayload
	public GetRataVisuraImposteInsegnePubblicita getRataVisuraImposteInsegnePubblicita(@RequestPayload GetRataVisuraImposteInsegnePubblicita request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getRataVisuraImposteInsegnePubblicita CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetRataVisuraImposteInsegnePubblicita result = of.createGetRataVisuraImposteInsegnePubblicita();
		result.setIdentificativoRata("get Rata Visura Imposte Insegne Pubblicita");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getPosizioniVisuraImposteInsegnePubblicita")
	@ResponsePayload
	public GetPosizioniVisuraImposteInsegnePubblicita getPosizioniVisuraImposteInsegnePubblicita(@RequestPayload GetPosizioniVisuraImposteInsegnePubblicita request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getPosizioniVisuraImposteInsegnePubblicita CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetPosizioniVisuraImposteInsegnePubblicita result = of.createGetPosizioniVisuraImposteInsegnePubblicita();
		result.setIdVisura(1);
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisureImposteInsegnePubblicita")
	@ResponsePayload
	public GetVisureImposteInsegnePubblicita getVisureImposteInsegnePubblicita(@RequestPayload GetVisureImposteInsegnePubblicita request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisureImposteInsegnePubblicita CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetVisureImposteInsegnePubblicita result = of.createGetVisureImposteInsegnePubblicita();
		result.setCodiceFiscale("get Visure Imposte Insegne Pubblicita");
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getVisuraImposteInsegnePubblicita")
	@ResponsePayload
	public GetVisuraImposteInsegnePubblicita getVisuraImposteInsegnePubblicita(@RequestPayload GetVisuraImposteInsegnePubblicita request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraImposteInsegnePubblicita CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetVisuraImposteInsegnePubblicita result = of.createGetVisuraImposteInsegnePubblicita();
		result.setId(1);
		return result;
	}
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="getRateVisuraImposteInsegnePubblicita")
	@ResponsePayload
	public GetRateVisuraImposteInsegnePubblicita getRateVisuraImposteInsegnePubblicita(@RequestPayload GetRateVisuraImposteInsegnePubblicita request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO getVisuraImposteInsegnePubblicita CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		GetRateVisuraImposteInsegnePubblicita result = of.createGetRateVisuraImposteInsegnePubblicita();
		result.setIdVisura(1);
		return result;
	}
}
