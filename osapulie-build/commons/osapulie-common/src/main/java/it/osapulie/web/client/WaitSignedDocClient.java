package it.osapulie.web.client;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import it.osapulie.util.FirmaGrafometricaPayload;
import it.osapulie.util.FirmaGrafometricaResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WaitSignedDocClient {
	private final Logger log = LoggerFactory.getLogger(WaitSignedDocClient.class);
	
	
	private String token;
	
	private String url;
	
	private FirmaGrafometricaPayload payload;
	
	private FirmaGrafometricaResponse responseUrl = new FirmaGrafometricaResponse();
		
	private static final String SERVICE_NAME = "waitSignedDoc";
	
	public void start() throws Exception {
		String uri =url+"/"+SERVICE_NAME;
		log.info("GetUrl URI Complete:"+uri);
		final URI uriForPost = new URI(uri);
		RestTemplate restTemplate = new RestTemplate();
		
				
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=utf-8");
		headers.set("Authorization","Bearer "+ token);
		
		HttpEntity<Object> entity = new HttpEntity<Object> (payload, headers);
		log.info("entity: "+entity.getBody());
		ResponseEntity<byte[]> response = restTemplate.exchange(uriForPost, HttpMethod.POST, entity, byte[].class);

		if(response.getBody() != null)
			responseUrl.setFileDownload(response.getBody());
		log.info("Response: "+responseUrl);
		
		
	}
	
	
	public FirmaGrafometricaResponse waitSignedDoc() {
		
		try {
			start();
		} catch (Exception e) {
			log.error("Errore nellinvocazione del client: " + e.getLocalizedMessage());
		}
		
		return responseUrl;
		
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setPayload(FirmaGrafometricaPayload payload) {
		this.payload = payload;
	}
	
	
}
