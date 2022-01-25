package it.osapulie.web.client;

import java.io.File;
import java.net.URI;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import it.osapulie.util.FirmaGrafometricaPayload;
import it.osapulie.util.FirmaGrafometricaResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GetUrlClient {
	private final Logger log = LoggerFactory.getLogger(GetUrlClient.class);
	
	
	private String token;
	
	private String url;
	
	private File file;
	
	private FirmaGrafometricaPayload payload;
	
	private FirmaGrafometricaResponse responseUrl = new FirmaGrafometricaResponse();
		
	private static final String SERVICE_NAME = "getUrl";
	
	public void start() throws Exception {
		String uri =url+"/"+SERVICE_NAME;
		log.info("GetUrl URI Complete:"+uri);
		final URI uriForPost = new URI(uri);
		RestTemplate restTemplate = new RestTemplate();
		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(payload);
		
		
		map.add("documento",new FileSystemResource(file));
		map.add("user", jsonString);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "multipart/form-data");
		headers.set("Authorization","Bearer "+ token);
		
		HttpEntity<Object> entity = new HttpEntity<Object> (map, headers);
		log.info("entity: "+entity.getBody());
		ResponseEntity<FirmaGrafometricaResponse> response = restTemplate.exchange(uriForPost, HttpMethod.POST, entity, FirmaGrafometricaResponse.class);

		BeanUtils.copyProperties(responseUrl,response.getBody());
		
		log.info("Response: "+responseUrl);
		
		
	}
	
	
	public FirmaGrafometricaResponse getUrl() {
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

	public void setFile(File file) {
		this.file = file;
	}

	public void setPayload(FirmaGrafometricaPayload payload) {
		this.payload = payload;
	}
	
	
}
