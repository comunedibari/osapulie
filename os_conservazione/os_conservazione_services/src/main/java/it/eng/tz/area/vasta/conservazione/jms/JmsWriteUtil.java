package it.eng.tz.area.vasta.conservazione.jms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import it.eng.tz.area.vasta.protocollo.spring.dao.models.AttributiServizi;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.DwhGeolocalizzazione;
import it.eng.tz.area.vasta.protocollo.spring.service.AttributiServiziService;
import it.eng.tz.area.vasta.protocollo.spring.service.DwhGeolocalizzazioneService;
import it.eng.tz.area.vasta.protocollo.utils.DateConverter;


public class JmsWriteUtil {
	private static final Logger logger = LoggerFactory.getLogger(JmsWriteUtil.class);
	
	static String ACTOR="ACTOR";
	static String DATA_FOLD="DATAFOLD";
	
	private static boolean checkPathFolder(String path) {
		File f = new File(path);
		if (!f.exists()) {
			return f.mkdirs();
		}
		return true;
	}
	
	private static boolean checkUserLog(String path) {
		File f = new File(path);
		if (!f.exists()) {
			return JmsWriteUtil.writeClear(path);
		}
		return true;
	}

	private static boolean writeClear(String path) {
		BufferedWriter in = null;
		try {
			File f = new File(path);
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
			 
		}
		return true;

	}
	
	public static void writeLineaUser(String pathFile, String lineLog) {
		if(lineLog!=null && pathFile!=null) {
		JmsWriteUtil.checkUserLog(pathFile);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pathFile, true);
		} catch (FileNotFoundException e) {
			 
		}
		PrintWriter pw = new PrintWriter(fos);
		pw.println(lineLog);
		pw.flush();
		pw.close();
		
		}
		 
	}
	
	public static void getPrintMessageDefault(TextMessage textMessage,String stringPathFolded,AttributiServiziService attrService) throws JMSException {
		        logger.info("JmsWriteUtil Method[getPrintMessageDefault] TextMessage Received: [{}] Path-Folder: [{}]",textMessage,stringPathFolded); 
				if(textMessage!=null && stringPathFolded!=null) {
				String actorId = textMessage.getStringProperty(JmsWriteUtil.ACTOR);
				if(actorId==null || (actorId!=null && actorId.trim().length()<2))
				 actorId="anonimus";
				 
				String dataFolder = textMessage.getStringProperty(JmsWriteUtil.DATA_FOLD); 
				if(dataFolder==null || (dataFolder!=null && dataFolder.trim().length()<2))
				dataFolder=DateConverter.getFormattedDate(new Date(), "ddMMyyyy");
				
				String jsonAudit=textMessage.getText();
				 
				String pathFolder=stringPathFolded+File.separator+dataFolder+File.separator;
				String pathComplete=pathFolder+dataFolder+"CU"+actorId+".txt";
				JmsWriteUtil.checkPathFolder(pathFolder);
				logger.info("JmsWriteUtil Method[getLineLogAuditUser] Path-Complete: [{}]",pathComplete); 
				JmsWriteUtil.writeLineaUser(pathComplete, JmsWriteUtil.getLineLogAuditUser(jsonAudit));
				
				
				popolaAttributeServizio(jsonAudit, attrService);
				
				}
				}
	 
		
 	
	
	  private static String getLineLogAuditUser(String stringJson) {

	    	try {
	    		if(stringJson==null) { return "{}"; }
	    		
	    		if(!stringJson.contains("{") && !stringJson.contains("}")) { return stringJson; }
	    		
	    		JSONObject obj = new JSONObject(stringJson);
	    		JSONObject auditUser =obj.getJSONObject("auditUser");
	    		if(auditUser!=null) {
	    			 
	    		return auditUser.toString();
	    		}
	    	}catch (Exception e) {
	    		 logger.info("JmsWriteUtil Method[getLineLogAuditUser] Exception[{}] RITORNA-STRINGJSON[{}]",e.getMessage(),stringJson); 
	    		return stringJson;
			}
	    	return stringJson;
	    	
	    	
	    }
	  
	  private static String popolaAttributeServizio(String stringJson,AttributiServiziService attrService) {

	    	try {
	    		if(stringJson==null) { return "{}"; }
	    		 
	    		if(!stringJson.contains("{") && !stringJson.contains("}")) { return "{}"; }
	    		
	    		JSONObject obj = new JSONObject(stringJson);
	    		JSONObject attributeServizio =obj.getJSONObject("attrServizio");
	    		if(attributeServizio!=null) {
	    		Long idServizio=Long.getLong("0");
	    		if(attributeServizio.get("idServizio")!=null)
	    		 idServizio=attributeServizio.optLong("idServizio", 0L);//.getLong("idServizio");
	    		String uuidTransazione="-"; 
	    		if(attributeServizio.get("uuidTransazione")!=null)
	    		uuidTransazione=attributeServizio.optString("uuidTransazione","-");//.getString("uuidTransazione");	
	    		
	    		JSONArray values = attributeServizio.getJSONArray("attribute");
	    			
	    			if(values!=null && values.length()>0 && idServizio!=null && idServizio.intValue()>0)
	    			  for (int i = 0; i < values.length(); i++) {
	    			    
	    			    JSONObject attrib = values.getJSONObject(i); 
	    			    if(attrib!=null && attrib.get("name")!=null && attrib.get("value")!=null) {

	    			    String name = attrib.optString("name","-");//.getString("name");
	    			    String value = attrib.optString("value","-");//.getString("value");
	    			    
	    				AttributiServizi attr= new AttributiServizi();
		    			attr.setDataCreazione(new Date());
		    			attr.setId(UUID.randomUUID().toString());
		    			attr.setIdTransazione(uuidTransazione);
		    			attr.setNomeAttributo(name);
		    			attr.setValoreAttributo(value);
		    			attr.setIdServizio(idServizio);
		    			attrService.saveAttributeServizio(attr);
	    			    }
	    			  
	    			  }

	    			
	    		return "{}";//attributeServizio.toString();
	    		}
	    	}catch (Exception e) {
	    		 logger.info("JmsWriteUtil Method[popolaAttributeServizio] Exception[{}]",e.getMessage()); 
	    		return stringJson;
			}
	    	return "{}";
	    	
	    	
	    }
	  
	  
	  
	  public static String popolaDwhGeolocalizzazione(TextMessage textMessage,RestTemplate restTemplate,DwhGeolocalizzazioneService geoService,String apiKey) {

	    	try {
	    		
	    		if(textMessage!=null) {
	    		String stringJson=textMessage.getText();
	    		
	    		if(stringJson==null) { return "{}"; }
	    		 
	    		 
	    		if(!stringJson.contains("{") && !stringJson.contains("}")) { return "{}"; }
	    		
	    		JSONObject obj = new JSONObject(stringJson);
	    		JSONObject attributeServizio =obj.getJSONObject("attrServizio");
	    		if(attributeServizio!=null) {
	    		String callGeo="",codiceServizio="",actor="",originIp="",uuidTransazione="-";
	    		if(attributeServizio.get("callGeo")!=null) {
	    		callGeo=attributeServizio.optString("callGeo", ""); 
	    		if(!callGeo.isEmpty()) {	 
	    		codiceServizio=attributeServizio.optString("codServizio", ""); 
	    		actor=attributeServizio.optString("actor", "");
	    		originIp=attributeServizio.optString("origin", ""); 
	    		 
	    		uuidTransazione=attributeServizio.optString("uuidTransazione","-"); 
	    		
	    		String URL_="http://api.ipstack.com/"+originIp+"?access_key="+apiKey;// 144d0075dbf903a65650175acfb7e049"; 
	    		
	    		ResponseEntity<String> jsd = restTemplate.exchange(URL_, HttpMethod.GET, null, String.class);
	    		
	    		String jsonStr=jsd.getBody();
	    		
	    		logger.info("API-IP PARAM-URL[{}]  RESPONSBODY-STRING[{}] ",URL_,jsonStr); 
	    	 
	    			if(jsonStr!=null && jsonStr.contains("{") && jsonStr.contains("}"))
	    			{
	    			    
	    			    
	    				JSONObject jso=new JSONObject(jsonStr.trim());
	    				DwhGeolocalizzazione geo= new DwhGeolocalizzazione();
	    				geo.setCap(jso.optString("zip", ""));
	    				geo.setCitta(jso.optString("city", ""));
	    				geo.setCod_servizio(codiceServizio); 
	    				geo.setCod_user(actor);
	    				geo.setData_creazione(new Date());
	    				geo.setIndirizzo_ip(jso.optString("ip", ""));
	    				geo.setIs_europeo(jso.optBoolean("is_eu", false));
	    				geo.setLatitudine(jso.optString("latitude", ""));
	    				geo.setLongitudine(jso.optString("longitude", ""));
	    				geo.setRegione(jso.optString("region_name", ""));
	    				geo.setUuid_operazione(uuidTransazione);
	    				
	    				geoService.saveDwhGeolocalizzazione(geo);
	    			 
	    			  
	    			  }

	    		}
	    		}
	    		return "{}";
	    		}
	    		}
	    	}catch (Exception e) {
	    		 logger.info("JmsWriteUtil Method[popolaDwhGeolocalizzazione] Exception[{}]",e.getMessage()); 
	    		return "{}";
			}
	    	return "{}";
	    	
	    	
	    }
	  
	  
	
}
