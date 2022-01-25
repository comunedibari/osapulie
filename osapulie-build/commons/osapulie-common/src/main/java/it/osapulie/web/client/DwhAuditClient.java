package it.osapulie.web.client;

import java.net.URI;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.osapulie.util.DwhAuditPayload;
import it.osapulie.util.DwhAuditResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwhAuditClient {
	private final Logger log = LoggerFactory.getLogger(DwhAuditClient.class);
	private DwhAuditPayload auditPayload;
	
	private String token;
	
	private String url;
	
	
	private static final String SERVICE_NAME = "dwhaudit";
	
	public DwhAuditResponse start() throws Exception {
		String uri =url+"/"+SERVICE_NAME;
		log.info("DwhAudit URI Complete:"+uri);
		final URI uriForPost = new URI(uri);
//		final URI uriForPost = new URI("http://10.10.1.12:8080/ressvr/rest/dwhaudit");
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", "application/json;charset=utf-8");
		headers.set("Authorization","Bearer "+ token);
		log.info("headers: "+headers);
		HttpEntity<DwhAuditPayload> entity = new HttpEntity<DwhAuditPayload> (auditPayload, headers);
		log.info("entity: "+entity.getBody());
		ResponseEntity<DwhAuditResponse> response = restTemplate.exchange(uriForPost, HttpMethod.POST, entity, DwhAuditResponse.class);

		log.info("Response: "+response.getBody());
		
		return response.getBody();
		
		
		
	}

	
		
	public void setToken(String token) {
		this.token = token;
	}

	
	public void setAuditPayload(DwhAuditPayload auditPayload) {
		this.auditPayload = auditPayload;
	}


	public void setUrl(String url) {
		this.url = url;
	}

}
