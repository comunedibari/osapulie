package it.osapulie.servizicomune.service.impl;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.eng.tz.avtmb.integration.client.GetTokenService;
import it.eng.tz.avtmb.integration.client.dto.StampSignAuthResponse;
import it.osapulie.servizicomune.model.DelegaReportModel;
import it.osapulie.servizicomune.service.FirmaGrafometricaService;
import it.osapulie.util.FirmaGrafometricaPayload;
import it.osapulie.util.FirmaGrafometricaResponse;
import it.osapulie.web.client.GetUrlClient;
import it.osapulie.web.client.WaitSignedDocClient;

@Service("firmaGrafometricaService")
public class FirmaGrafometricaServiceImpl implements FirmaGrafometricaService {
	private final Logger log = LoggerFactory.getLogger(FirmaGrafometricaServiceImpl.class);
	
	 @Value("${area.vasta.audit.service.aoo}")
	 private String aoo;
	
	 @Value("${area.vasta.firma.service.url}")
	 private String url;
			
	 @Value("${area.vasta.temp.path.file}")
	 private String pathFile;
	 
	 @Value("${area.vasta.firma.ws.client.id}")
	 private String clientId;
			
	 @Value("${area.vasta.firma.ws.client.secret}")
	 private String clientSecret;
	 
	@Override
	public FirmaGrafometricaResponse invokeFirmaService(DelegaReportModel reportModel, byte[] file) throws Exception {
		WaitSignedDocClient waitClient = new WaitSignedDocClient();
		FirmaGrafometricaResponse response = new FirmaGrafometricaResponse();
		FirmaGrafometricaPayload  payload = new FirmaGrafometricaPayload();
		BeanUtils.copyProperties(payload, reportModel);
		GetUrlClient client = new GetUrlClient();
		StampSignAuthResponse stampSignAuthResponse = null;
		try {
			log.info("FirmaGrafometricaService - CALL STAMP SIGN AUTH WITH AOO:" + aoo);
			GetTokenService stampsignClientService = new GetTokenService();	
			stampsignClientService.setStampsignClientId(clientId);
			stampsignClientService.setStampsignClientSecret(clientSecret);
			stampSignAuthResponse = stampsignClientService.stampSignAuth(aoo);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
		String nomeFile = "Delega_"+payload.getCfDelegante();
		
		File initialFile = new File(pathFile+nomeFile+".pdf");
		FileUtils.writeByteArrayToFile(initialFile,file);
		client.setFile(initialFile);
		
		client.setUrl(url);
		
		client.setToken(stampSignAuthResponse.getToken().getAccessToken().toString());
		//client.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGltYnJvX2RpZ2l0YWxlX3Jlc291cmNlX3NlcnZlciJdLCJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6InRlc3RfZGdzc3NSUSIsImV4cCI6MTkyMzg1MTA5MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9URF9DTElFTlQiXSwianRpIjoiYTgwZmE3YzctODAzMy00Y2MzLTg5N2YtZGYzMDk3N2UyNGQ1IiwiY2xpZW50X2lkIjoidGVzdF9kZ3MifQ.Xp4psMgE2T6_sCg2UV9oBq9lrGK1Jkg6zdIXs3iRLGA");
		client.setPayload(payload);
		
		response = client.getUrl();
		
		
		return response;
	}

	@Override
	public byte[] waitAndSigned(DelegaReportModel delegaReport, String token)
			throws Exception {
		
		WaitSignedDocClient waitClient = new WaitSignedDocClient();
		FirmaGrafometricaResponse response = new FirmaGrafometricaResponse();
		FirmaGrafometricaPayload  payload = new FirmaGrafometricaPayload();
		BeanUtils.copyProperties(payload, delegaReport);
		
		if(token != null) {

			StampSignAuthResponse stampSignAuthResponse = null;
			try {
				log.info("FirmaGrafometricaService - CALL STAMP SIGN AUTH WITH AOO:" + aoo);
				GetTokenService stampsignClientService = new GetTokenService();	
				stampsignClientService.setStampsignClientId(clientId);
				stampsignClientService.setStampsignClientSecret(clientSecret);
				stampSignAuthResponse = stampsignClientService.stampSignAuth(aoo);
			} catch (Exception e) {
				log.error(e.getMessage());
				throw e;
			}
			
	    	payload.setToken(token);
	    	waitClient.setPayload(payload);
	    	waitClient.setToken(stampSignAuthResponse.getToken().getAccessToken().toString());
	    	waitClient.setUrl(url);
	    	
	    	try {
	    		response = waitClient.waitSignedDoc();
			} catch (Exception e) {
				log.error("Errore nellinvocazione del client: {}-{}",e.getClass().getName(),e.getLocalizedMessage(),e);
			}
	    
		}
		
		if(response.getFileDownload().length > 0)
			return response.getFileDownload();
		else
			return null;
	}
}
