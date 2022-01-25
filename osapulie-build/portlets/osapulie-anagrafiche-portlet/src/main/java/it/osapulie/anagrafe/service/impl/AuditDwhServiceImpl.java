package it.osapulie.anagrafe.service.impl;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import it.eng.tz.avtmb.integration.client.StampsignClientService;
import it.eng.tz.avtmb.integration.client.dto.StampSignAuthResponse;
import it.osapulie.anagrafe.service.AuditDwhService;
import it.osapulie.domain.Audit;
import it.osapulie.service.AuditService;
import it.osapulie.util.DwhAuditPayload;
import it.osapulie.util.DwhAuditResponse;
import it.osapulie.util.dto.DwhDataminingDTO;
import it.osapulie.util.dto.DwhServizioAttributeDTO;
import it.osapulie.util.dto.DwhTempiMediDTO;
import it.osapulie.web.client.DwhAuditClient;

@Service("auditDwhService")
public class AuditDwhServiceImpl implements AuditDwhService {
	private final Logger log = LoggerFactory.getLogger(AuditDwhServiceImpl.class);
	
	@Inject
	private StampsignClientService stampsignClientService;
	
	 @Value("${area.vasta.audit.service.aoo}")
	 private String aoo;
	
	 @Value("${area.vasta.audit.service.url}")
	 private String url;
	
	@Value("${area.vasta.audit.file.name}")
	 private String filename;
		
	@Value("${audit.firma.path}")
	 private String path;
	
	@Inject
	private AuditService auditService; 
	
	 
	@Override
	public void invokeAuditService(DwhServizioAttributeDTO servizioAttributeDto, DwhDataminingDTO dataminingDto,DwhTempiMediDTO tempiMediDto) throws Exception {
		DwhAuditPayload  payload = new DwhAuditPayload();
		DwhAuditClient client = new DwhAuditClient();
		payload.setDatamining(dataminingDto);
		payload.setServizioAttribute(servizioAttributeDto);
		payload.setTempiMedi(tempiMediDto);
		DwhAuditResponse response = new DwhAuditResponse();
		
		StampSignAuthResponse stampSignAuthResponse = null;
		try {
			log.info("AUDIT DWH SERVICE - CALL STAMP SIGN AUTH WITH AOO:" + aoo);
//		StampsignClientService stampsignClientService = new StampsignClientServiceImpl();
		stampSignAuthResponse = stampsignClientService.stampSignAuth(aoo);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw e;
		}
		
		client.setUrl(url);
		
		client.setToken(stampSignAuthResponse.getToken().getAccessToken().toString());
		//client.setToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidGltYnJvX2RpZ2l0YWxlX3Jlc291cmNlX3NlcnZlciJdLCJzY29wZSI6WyJyZWFkIl0sIm9yZ2FuaXphdGlvbiI6InRlc3RfZGdzc3NSUSIsImV4cCI6MTkyMzg1MTA5MywiYXV0aG9yaXRpZXMiOlsiUk9MRV9URF9DTElFTlQiXSwianRpIjoiYTgwZmE3YzctODAzMy00Y2MzLTg5N2YtZGYzMDk3N2UyNGQ1IiwiY2xpZW50X2lkIjoidGVzdF9kZ3MifQ.Xp4psMgE2T6_sCg2UV9oBq9lrGK1Jkg6zdIXs3iRLGA");
		client.setAuditPayload(payload);
		
//		client.invoke(stampSignAuthResponse.getToken().getAccessToken().toString(),payload);
		response = client.start();
		
		saveAudit(payload, response);
		
		
	}
	
	public void saveAudit(DwhAuditPayload payload,DwhAuditResponse response) {
		Audit audit = new Audit();
		Calendar dateCreazione= Calendar.getInstance();
		String uuid = null;
		
		if(payload.getDatamining() != null)
			uuid = payload.getDatamining().getUuid_operazione();
		else if(payload.getServizioAttribute() != null)
			uuid = payload.getServizioAttribute().getUuid();
		else if(payload.getTempiMedi() != null)
			uuid = payload.getTempiMedi().getUuid_operazione();
		
		String giornoMeseAnno = ""+dateCreazione.get(Calendar.DAY_OF_MONTH)+""+dateCreazione.get(Calendar.MONTH+1)+""+dateCreazione.get(Calendar.YEAR);
		String bodyForFile = payload.toString()+"\n"+response.toString();
		if(payload.getDatamining() != null)
			audit.setUserCod(payload.getDatamining().getCod_cittadino());
		else if(payload.getServizioAttribute() != null)
			audit.setUserCod(payload.getServizioAttribute().getCittadino_userid());
		else if(payload.getTempiMedi() != null)
			audit.setUserCod(payload.getTempiMedi().getCod_user());
		audit.setGiornoMeseAnno(giornoMeseAnno);
//		a.setFileName("testAuditFile");
//		a.setPathFilesystem("/opt/osapulie/audit-log");
		audit.setFileName(filename+uuid);
		audit.setPathFilesystem(path);
		audit.setChecksum("0");
		audit.setDataCreazione(dateCreazione.getTime());
		audit.setDataUltimaModifica(dateCreazione.getTime());
		audit.setCodiceRegistro("0");
		audit.setStato("0");
		audit.setCons("0"); 
		auditService.saveAudit(audit);
		
		String nomefile=path+filename+uuid+".txt";
		
		printFile(bodyForFile, nomefile);
		
	}
	
	public void printFile(String body, String path) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(path, true);
		} catch (FileNotFoundException e) {
			log.error("DwhAuditClient printFile ", e);
		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = body;

		pw.println(lineLog);
		pw.flush();
		pw.close();
	}
	
}
