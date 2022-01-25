package it.eng.tz.area.vasta.conservazione.jms;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import it.eng.tz.area.vasta.protocollo.spring.service.AttributiServiziService;
import it.eng.tz.area.vasta.protocollo.spring.service.DwhGeolocalizzazioneService;



@Component
public class MessageReceiver implements MessageListener{

	private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);
	
	@Value("${audit.base.path}")
	private  String stringPathFolded;
	
	@Value("${api.geo.key}")
	private String apiGeoKey;
	
	@Autowired
	AttributiServiziService attrService;
	
	@Autowired
	DwhGeolocalizzazioneService geoService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@Override
	public void onMessage(Message message) {
		try {
			logger.info("----------------------------------------------------------------------");
			TextMessage messageText = (TextMessage) message; 
			JmsWriteUtil.getPrintMessageDefault(messageText, stringPathFolded,attrService);
			
			JmsWriteUtil.popolaDwhGeolocalizzazione(messageText, restTemplate, geoService, apiGeoKey);
			logger.info("----------------------------------------------------------------------");
		} catch (Exception e) {
			logger.error("MessageReceiver onMessage Exception: ",e);
		}
		
	}
}

