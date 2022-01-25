package it.eng.tz.area.vasta.protocollo.spring.configuration.util;

import org.apache.chemistry.opencmis.client.api.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import it.eng.tz.area.vasta.documentale.cmis.config.CMISQuery;
import it.eng.tz.area.vasta.documentale.cmis.exception.DocumentaleCmisException;
import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.IRegistroProtocollo;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.ISearchDocument;
import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.Esito;

public class GeneraRegistroProtocolloUtil {
	
	public static final int GENERATO=1;
	public static final int FILE_PRESENTE=2;
	public static final int ERROR=0;
	private static final Logger logger = LoggerFactory.getLogger(GeneraRegistroProtocolloUtil.class.getName());
	
	
	public static Esito generaRegistro(String data_YYYY_MM_DD,ISearchDocument cmisSearch,Object registroProtoSvc,IRegistroProtocollo registroProtocolloService) throws DocumentaleCmisException, ProtocolloDbException {
			
		if(data_YYYY_MM_DD==null || data_YYYY_MM_DD.replaceAll("[^0-9\\-]", "").length()!=10) {
			logger.debug("RESPONS RITORNO GENERAZIONE REGISTRO PROTOCOLLO - DATA GENERAZIONE INVALID DATA [{}] ",data_YYYY_MM_DD);
			return new Esito(GeneraRegistroProtocolloUtil.ERROR);
			}
		
			//la stringa data deve essere del formato yyyy-MM-dd  - esempio:( 2018-05-28 )								
		    String dataString=data_YYYY_MM_DD.trim().replaceAll("-", "");
		    
			String nomeFileDaGenerare= "registro_protocollo_"+dataString+".pdf";
			
			if(cmisSearch.getQueryResult(CMISQuery.SEARCH_REGISTRO_DI_PROTOCOLLO(nomeFileDaGenerare)).getPageNumItems()<1) {
				
		 
			BORegistroProtocollo boRegistroProtocollo=null;//ConvertUtil.convert(regProtModel);
			
			Document doc=registroProtocolloService.saveRegistroProtocollo(boRegistroProtocollo);
			if(doc!=null) {
				 
				logger.debug("RESPONS RITORNO GENERAZIONE REGISTRO PROTOCOLLO [ FILE GENERATO {}] REPOSITORY [{}] ",nomeFileDaGenerare,"");
				return new Esito(GeneraRegistroProtocolloUtil.GENERATO,doc.getId());
			}
 
			} else {
				logger.debug("RESPONS RITORNO GENERAZIONE REGISTRO PROTOCOLLO [ FILE PRESENTE {}] ",nomeFileDaGenerare);
				return new Esito(GeneraRegistroProtocolloUtil.FILE_PRESENTE);
			}
			
			logger.debug("RESPONS RITORNO GENERAZIONE REGISTRO PROTOCOLLO [{}] ",GeneraRegistroProtocolloUtil.ERROR);		
		return new Esito(GeneraRegistroProtocolloUtil.ERROR);
	}
}
