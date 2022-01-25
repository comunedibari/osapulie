package it.eng.tz.area.vasta.protocollo.spring.web.controller;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.eng.tz.area.vasta.documentale.cmis.config.CMISQuery;
import it.eng.tz.area.vasta.documentale.cmis.exception.DocumentaleCmisException;
import it.eng.tz.area.vasta.documentale.cmis.model.BORegistroProtocollo;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.IRegistroProtocollo;
import it.eng.tz.area.vasta.documentale.cmis.spring.service.ISearchDocument;
import it.eng.tz.area.vasta.documentale.cmis.util.ProtocolloProp;
import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.configuration.util.ConvertUtil;
import it.eng.tz.area.vasta.protocollo.spring.configuration.util.GeneraRegistroProtocolloUtil;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.BaseResponse;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.DataTableResponse;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.Esito;
import it.eng.tz.area.vasta.protocollo.spring.web.dto.ProtocolloRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/rest")
public class RegistroAuditController {
	private static final Logger logger = LoggerFactory.getLogger(RegistroAuditController.class.getName());

//	@Autowired
//	private IRegistriProtocolloSvc registroProtoService;

	@Autowired
	private IRegistroProtocollo  registroProto;
	
	@Autowired
	private ISearchDocument cmisSearch;

//	@Autowired
//	private ISysconfigSvc sysConfigsvc;
															//"/protected/ltsProto"
	@RequestMapping(method = { RequestMethod.GET }, value = { "/protected/regaudits" }, produces = {
			"application/json" })
	public ResponseEntity<DataTableResponse<ProtocolloRest>> listaProtocolli(@RequestParam("from") String from,
			@RequestParam("to") String to, Locale locale) throws ParseException {
		DataTableResponse<ProtocolloRest> result = new DataTableResponse<ProtocolloRest>();
		String resultMsg = null;
		String message = null;
		HttpStatus status = null;
		try {
			resultMsg = "OK";
			status = HttpStatus.OK;
		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMsg = "KO";
		}
	
		 
		SimpleDateFormat dateFormat= new SimpleDateFormat("dd/MM/yyyy");
	
		
		Date from_date=dateFormat.parse(from);
		Date to_date=dateFormat.parse(to);
		
		long timeCurrent=System.currentTimeMillis();
		
		//controllo che la data inizio ricerca non sia maggiore della data odierna
		//se maggiore esco.
		if(from_date.getTime()>timeCurrent) {
			from_date.setTime(timeCurrent);
			result.setPayload(new ArrayList<ProtocolloRest>());
			return new ResponseEntity<DataTableResponse<ProtocolloRest>>(result, status);
		}
			 
		
		//controllo che la data fine ricerca non sia maggiore della data odierna
		if(to_date.getTime()>timeCurrent)
			to_date.setTime(timeCurrent);
		
		
	    List<String>listaDateProtoc=CMISQuery.getListDateQuery_IN(from_date, to_date);
		

		result.setDescrizioneOperazione(message);
		result.setEsitoOperazione(status.value());
		result.setNumeroOggettiRestituiti(listaDateProtoc.size());
		result.setNumeroOggettiTotali(listaDateProtoc.size());

		result.setPayload(this.getLtsProtocolliRest(listaDateProtoc));

		return new ResponseEntity<DataTableResponse<ProtocolloRest>>(result, status);
	}
 
	
	
	
	
	private List<ProtocolloRest> getLtsProtocolliRest(List<String> listaDate) {
		List<ProtocolloRest> protocolloRests = new ArrayList<ProtocolloRest>();
				//List<RegistroProtocolloModel> protocolloModels;
		try {
			//protocolloModels = registroProtoService.ricercaRegistriProtocollo(from, to);
		
			String querySearch_IN=CMISQuery.SEARCH_FROM_TO_REGISTRO_DI_PROTOCOLLO_IN(listaDate);
			
			ItemIterable<QueryResult> documents=cmisSearch.getQueryResult(querySearch_IN);
			LinkedHashMap<String, ProtocolloRest> mappaRest= new LinkedHashMap<>();
			
			for(String data:listaDate) {
				mappaRest.put(data, new ProtocolloRest(data));	
			}
			
			for (QueryResult model : documents) {
		
				ProtocolloProp prop=new ProtocolloProp(model);
				
				if(mappaRest.get(prop.getDataPrimaRegistrazioneEffettuataSulRegistro())!=null) {
				  mappaRest.get(prop.getDataPrimaRegistrazioneEffettuataSulRegistro()).setProtocolloRest(prop);
				}

			}
		
			protocolloRests.addAll(mappaRest.values());	
			
			Collections.sort(protocolloRests, new Comparator<ProtocolloRest>() {
				@Override
				public int compare(ProtocolloRest p1, ProtocolloRest p2) {
					return p1.getDataUltimaReg().compareTo(p2.getDataUltimaReg());
				}
			}); 
			
		 

		} 

		catch (DocumentaleCmisException e) {
			
		}
		return protocolloRests;
	}

	@RequestMapping(method = { RequestMethod.GET }, value = { "/protected/download_audit" })
	public ResponseEntity<InputStreamResource> download(@RequestParam("iddoc") String iddoc) {
		try {
			Document document = cmisSearch.getCmisDocument(iddoc);
			// Document document=cmisManager.connect("admin", "admin").getDocument(iddoc);

			InputStream inStream = document.getContentStream().getStream();

			HttpHeaders respHeaders = new HttpHeaders();

			respHeaders.setContentType(org.springframework.http.MediaType.APPLICATION_PDF);
			respHeaders.setContentLength(document.getContentStreamLength());
			respHeaders.setContentDispositionFormData("attachment", document.getContentStreamFileName());
			InputStreamResource isr = new InputStreamResource(inStream);
			return new ResponseEntity<InputStreamResource>(isr, respHeaders, HttpStatus.OK);
		} catch (Exception e) {
			String error = "Errore nel download del CV con ID " + iddoc + "; " + e.getMessage();
			logger.error(error, e);
			return new ResponseEntity<InputStreamResource>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(method = { RequestMethod.GET}, value = { "/protected/saveregistro_reg" })
	public ResponseEntity<BaseResponse<String>> saveregistro(@RequestParam("dcdata") String dcdata)
	{  
		BaseResponse<String> result = new BaseResponse<String>();
		String resultMsg = "KO";
		String message = "",docId="";
		HttpStatus status = HttpStatus.OK;
		 
		try { 

			
			int respons=0;
			Esito esito=GeneraRegistroProtocolloUtil.generaRegistro(dcdata, cmisSearch, null, registroProto);
			respons=esito.getCode();
			docId=esito.getDocId();
			 
			if(respons==GeneraRegistroProtocolloUtil.GENERATO) {
				     resultMsg = "OK";
					 status = HttpStatus.OK;
			  }else if(respons==GeneraRegistroProtocolloUtil.FILE_PRESENTE) {
				    resultMsg = "PRESENTE";
					status = HttpStatus.OK;
			  }
			
			 
		} catch (Exception e) {
			message = "Errore nell'estensione della sessione utente";
			logger.error(message, e);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			resultMsg = "KO";
		}
		result.setDescrizioneOperazione(message);
		result.setEsitoOperazione(status.value());
		result.setNumeroOggettiRestituiti(1);
		result.setNumeroOggettiTotali(1); 
		result.setPayload(Arrays.asList(resultMsg,docId));
		return new ResponseEntity<BaseResponse<String>>(result, status);
	}
	
	
}
