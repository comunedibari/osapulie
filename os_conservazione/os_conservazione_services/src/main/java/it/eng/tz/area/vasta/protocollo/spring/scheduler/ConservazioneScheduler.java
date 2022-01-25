package it.eng.tz.area.vasta.protocollo.spring.scheduler;

import java.io.File;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.sip.service.ConservazioneSipSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ControlloEsitoSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.ConservazioneLogSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.EsitoSipSvc;
import it.eng.tz.area.vasta.protocollo.spring.service.AuditService;
import it.eng.tz.area.vasta.protocollo.utils.DateConverter;
 


@Component
public class ConservazioneScheduler {
	private static final Logger logger = LoggerFactory.getLogger(ConservazioneScheduler.class.getName());
 

	public final String DATE_FORMAT_FILE = "ddMMyyyy";
	
	@Value("${audit.base.path}")
	private  String stringPathFolded;
	
	private String sipIdValue;
	
	@Autowired
	private ConservazioneLogSvc consSvc;
	
	@Autowired
	private EsitoSipSvc esitSvc;
	
	@Autowired
	private ConservazioneSipSvc conservazioneService;
	
	@Autowired
	private AuditService auditService;
	
	public ConservazioneScheduler() {
		 
	}
 
	/*
	 * Invia in conservazione tutti i folder che non risultano inviati e processati
	 * 
	 * */
	
	@Scheduled(cron = "${sip.cron.check.all.folder}")
	public void checkAllFolderConsonservazione() {
	
		logger.info("ConservazioneScheduler - Method[checkAllFolderConsonservazione] START");	
		
		try {
		 
			Date defaultDataConservazione=this.getDateCondervazioneSIP();
			
			List<String> allFolder=conservazioneService.recuperaAllFolder();
			
			List<String> folderAll=auditService.getListFolder(allFolder);
			
			
			
			if(folderAll!=null && folderAll.size()>0)
				for(String folder:folderAll) 
				{	
					logger.info("Method[checkAllFolderConsonservazione] CONTROLLO DATA-FOLDER [{}]  PER DATA-DEFAULT-CONSERVAZIONE [{}]",folder,defaultDataConservazione);
					Date datefold=DateConverter.parseDateNotNull(folder, DATE_FORMAT_FILE);
					if(datefold.compareTo(defaultDataConservazione)<0) 
					{
						logger.info("Method[checkAllFolderConsonservazione]  INVIO IN CONSERVAZIONE IL FOLDER [{}]",folder);
						
						this.inviaLogsConservazione(folder);
					}
				}
		
		
		
		} catch (ConservazioneSipException e) {
			logger.error("Method[checkAllFolderConsonservazione] Exception [{}]",e.getMessage());
		}catch (Exception e) {
			logger.error("Method[checkAllFolderConsonservazione] Exception [{}]",e.getMessage());
		}
		
	}
	
	/*
	 * Logga tutti gli invii con stato processato a false trascorse le 24 ore di attesa
	 * 
	 * */
	
	@Scheduled(cron = "${sip.cron.check.folder.processati}")
	public void checkFolderProcessatiCons() {
	
		logger.info("ConservazioneScheduler - Method[checkFolderProcessatiCons] START");	
		
		try {
		 
			Date defaultDataConservazione=this.getDateCondervazioneSIP();
			
			List<String> dataFolder=conservazioneService.recuperaFolderDaProcessare();
			if(dataFolder!=null && dataFolder.size()>0)
				for(String folder:dataFolder) 
				{	
					logger.info("Method[checkFolderProcessatiCons] CONTROLLO DATA-FOLDER [{}]  PER DATA-DEFAULT-CONSERVAZIONE [{}]",folder,defaultDataConservazione);
					Date datefold=DateConverter.parseDateNotNull(folder, DATE_FORMAT_FILE);
					if(datefold.compareTo(defaultDataConservazione)<0) 
					{
						//NON ATTIVARE - DA LASCIARE IL METHODO inviaLogsConservazione( ); COMMENTATO PER IL MOMENTO!!
						//logger.info("Method[checkFolderProcessatiCons] RIESEGUO INVIO IN CONSERVAZIONE PER IL FOLDER [{}]",folder);				 
						// this.inviaLogsConservazione(folder);
					
						logger.info("Method[checkFolderProcessatiCons] IL FILE INVIATO IN CONSERVAZIONE DEL FOLDER [{}] RISULTA AVERE LO STATO PROCESSATO A FALSE ",folder);
					
					}
				}
		
		
		
		} catch (ConservazioneSipException e) {
			logger.error("Method[checkFolderProcessatiCons] Exception [{}]",e.getMessage());
		}
		
	}

	
	/*
	 *Invia in conservazione il folder di default (quello del giorno prima) 
	 *
	 * */
	
	@Scheduled(cron = "${sip.cron.genera.conservazione}")
	public void generaConservazioneAudit() {
		 //ogni 30 minuti   "0 */30 * ? * *"
		 //0 0 */3 ? * *     ogni 3 ore
		 //0 0/9 * * * ?		ogni 9 minuti
		 //ogni giorno all'una del mattino   "0 0 1 * * ?"
	   
		 logger.debug("ConservazioneScheduler - Method[generaConservazioneAudit] START");
		 
		 try {
			  
			    Date dataDiRegistro=this.getDateCondervazioneSIP();
				String dataFold=DateConverter.getFormattedDate(dataDiRegistro, DATE_FORMAT_FILE);
				boolean folderProcessato=conservazioneService.isFolderExist(dataFold);
				logger.info("Method[generaConservazioneAudit] CHECK FOLDER[{}] EXIST[ {} ] ",dataFold,folderProcessato);			
				if(!folderProcessato) 
				{
				logger.info("Method[generaConservazioneAudit] INVIO IN CONSERVAZIONE CHECK FOLDER[{}] EXIST[ {} ] [ INIZIO FLUSSO ]",dataFold,folderProcessato);		
				this.inviaLogsConservazione(dataFold);
				}
				else 
				{
				logger.info("Method[generaConservazioneAudit] CHECK FOLDER[{}] EXIST[ {} ] [ IL FILE RISULTA INVIATO PRECEDENTEMENTE ]",dataFold,folderProcessato);			
				}
 
		} catch (Exception e) {
			logger.error("ConservazioneScheduler  Method[generaConservazioneAudit] - Exception[{}] ",e.getMessage());
		}
 
		
	}
	
 
	private void inviaLogsConservazione(String folderLogs) {
		
		
		
		 logger.debug("Method[inviaLogsConservazione] START");
		 
		 try {
			 	int countFile=0;
	 
				String pathFolder=this.stringPathFolded+File.separator+folderLogs;
				logger.info("Method[inviaLogsConservazione]  PATH-FOLDER[{}]",pathFolder);
				File checkFolderCons= new File(pathFolder);
				if(checkFolderCons.exists() && checkFolderCons.isDirectory()) { 
				for(String fileName:checkFolderCons.list()) {
				if(fileName.contains(".txt") 
						&& !fileName.contains("Audit") 
						&& !fileName.contains("Sistem")
						&& !fileName.contains("anonimus"))
						{ countFile++; }
					 
					}
				}else 
				{
				
					 logger.info("Method[inviaLogsConservazione]  LA DIRECTORY NON ESISTE  [{}]",pathFolder);		
					
				}
				
				 if(countFile>0) 
				 {
		
			logger.info("Method[inviaLogsConservazione] AVVIO CONSERVAZIONE PER IL FOLDER LOG [{}] FILE DA PRCOCESSARE [{}]",pathFolder,countFile);	
			
			ReceiveSipResult risultato = consSvc.conservaLog(pathFolder); 
			
			if(risultato.isRicevutoErrore()) 
			{
				logger.info("ERROR SIP-CODE {}",risultato.getErrore().getReceiveSipErrorCode()+"");
				logger.info("ERROR SIP MESSAGE {}",risultato.getErrore().getReceiveSipErrorMessage());
			}else 
			{
				logger.info("SUCCESS SIP-CODE {}",risultato.getSuccesso().getSipCode()+"");
				logger.info("SUCCESS SIP MESSAGE {}",risultato.getSuccesso().getSipMessage());
				logger.info("SUCCESS SIP-ID {}",risultato.getSuccesso().getSipId());
				this.sipIdValue=risultato.getSuccesso().getSipId();
			}
			 
				 }else 
				 {
					 logger.info("Method[inviaLogsConservazione] NESSUN FILE LOG DA PROCESSARE PER IL FOLDER [{}] FILE DA PRCOCESSARE [{}]",pathFolder,countFile);	 
				 }

		} catch (Exception e) {
			logger.error("Method[inviaLogsConservazione] - Exception[{}] ",e.getMessage());
		}	
		
 
 
	}
	
	
	
	/*
	 *Controlla l'esito sip dei folder inviati con stato processato a false 
	 *ed aggiorna lo stato processato a true
	 *
	 * */
	
	@Scheduled(cron = "${sip.cron.check.esito.conservazione}")
	public void controllaEsitoConservazione() {
	 
		logger.info("ConservazioneScheduler - Method[controllaEsitoConservazione] START");			
	 
		try {
			
		List<String> listString= conservazioneService.daProcessare();
		if(listString!=null && listString.size()>0) 
		{
		for(String sipId:listString) 
		{
		logger.info("ConservazioneScheduler - Method[controllaEsitoConservazione] controlloEsitoSip[{}]" ,sipId);			
			this.controlloEsitoSip(sipId);		
		}
		}
		} catch (ConservazioneSipException e) {
		logger.error("ConservazioneScheduler - Method[controllaEsitoConservazione] - Exception[{}] ",e.getMessage());
			
		}	
		
	} 

	public void controlloEsitoSip(String idSip) {
		try {
			ControlloEsitoSipResult cesr = esitSvc.controllaConservazione(idSip);
			logger.info("ConservazioneScheduler - Method[controlloEsitoSip] EsitoSipResult[{}]" ,cesr);		
		} catch (Exception e) {
			logger.error("ConservazioneScheduler - Method[controlloEsitoSip] - Exception[{}] ",e.getMessage());
		}
	}

  
  public Date getDateCondervazioneSIP() {
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		Date dataDiRegistro=calendar.getTime();
		return dataDiRegistro;
	}
  
	
}
