package  it.eng.tz.area.vasta.protocollo.spring.service.impl;


import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import eng.tz.la.core.AuditManager;
import eng.tz.la.model.OperationExportFile;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.Audit;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroAuditModel;
import it.eng.tz.area.vasta.protocollo.spring.service.AuditService;
import it.eng.tz.area.vasta.protocollo.spring.service.RegistroAuditModService;
import it.eng.tz.area.vasta.protocollo.spring.service.RegistroGiornalieroAuditService;
import it.eng.tz.area.vasta.protocollo.utils.AuditCustomSettyng;
import it.eng.tz.area.vasta.protocollo.utils.DateConverter;

@Service("registroGiornalieroAuditService")
public class RegistroGiornalieroAuditServiceImpl implements RegistroGiornalieroAuditService {
 
public final String DATE_FORMAT_FILE = "ddMMyyyy";
public final String DATE_FORMAT_TITLE = "dd/MM/yyyy";
public final String TIME_FORMAT = "HH:mm:ss";
public final String REG_PROTOCOL = "audit_registro_giornaliero_";
public static final String FILE_EXTENSION_PDF = "pdf";
private static final Logger logger = LoggerFactory.getLogger(RegistroGiornalieroAuditServiceImpl.class.getName());

 
@Autowired
private AuditService auditService;
@Autowired
private RegistroAuditModService registroAuditService;

@Override
public RegistroAuditModel generaRegistro() throws Exception {

		 Date dataDiRegistro=getDateRegistro();

		 String dataGGmmAAAA=DateConverter.getFormattedDate(dataDiRegistro, DATE_FORMAT_FILE);
		 List<RegistroAuditModel> ltsReg= registroAuditService.getRegistroAudit(dataGGmmAAAA);
		 List<Audit> ltsAuditConStato_0=auditService.getAudits(dataGGmmAAAA,"0");
		 if(ltsReg!=null && ltsReg.size()>0 && (ltsAuditConStato_0==null || ltsAuditConStato_0!=null && ltsAuditConStato_0.size()<1)) {
			RegistroAuditModel registro=ltsReg.get(0);
			if(registro.getFlagGenerato().equals("1")) {
				logger.info("REGISTRO GIORNALIERO AUDIT -> ESISTENTE FLAG GENERATO == 1 PER IL CODICE "+dataGGmmAAAA);
				return null;
			}
				 
		 }
		 
		 
		logger.info("START RegistroGiornalieroAuditServiceImpl -> ESEGUO LA RICERCA PER LA DATA DA GENERARE "+dataGGmmAAAA);
		List<Audit> ltsAudit =auditService.getAudits(dataGGmmAAAA);
	 
		return  writeRegistro(eseguiControlloImprontaFile(ltsAudit),dataDiRegistro);
	 
	
 
}	

@Override
public RegistroAuditModel generaRegistro(String ggmmaaaa) throws Exception {
	 
	 Date dataDiRegistro= DateConverter.parseDate(ggmmaaaa, DATE_FORMAT_FILE); //getDateRegistro();
	 if(dataDiRegistro!=null) {
	//DateConverter.getFormattedDate(dataDiRegistro, DATE_FORMAT_FILE);
	 List<RegistroAuditModel> ltsReg= registroAuditService.getRegistroAudit(ggmmaaaa);
	 List<Audit> ltsAuditConStato_0=auditService.getAudits(ggmmaaaa,"0");
	 if(ltsReg!=null && ltsReg.size()>0 && (ltsAuditConStato_0==null || ltsAuditConStato_0!=null && ltsAuditConStato_0.size()<1)) {
		RegistroAuditModel registro=ltsReg.get(0);
		if(registro.getFlagGenerato().equals("1")) {
			logger.info("REGISTRO GIORNALIERO AUDIT -> ESISTENTE FLAG GENERATO == 1 PER IL CODICE "+ggmmaaaa);
			return null;
		}
			 
	 }
	 
	 
	logger.info("START RegistroGiornalieroAuditServiceImpl -> ESEGUO LA RICERCA PER LA DATA DA GENERARE "+ggmmaaaa);
	List<Audit> ltsAudit =auditService.getAudits(ggmmaaaa);

	return  writeRegistro(eseguiControlloImprontaFile(ltsAudit),dataDiRegistro);
	 
	 }else { return null; }
	
	}


private List<Audit> eseguiControlloImprontaFile(List<Audit> ltsAudit){
	logger.info("START RegistroGiornalieroAuditServiceImpl -> eseguiControlloImprontaFile");
	List<Audit> audits= new ArrayList<Audit>();	
	AuditManager auditManager=AuditManager.audit(AuditCustomSettyng.get()).setActor("Sistema");
	if(ltsAudit!=null && ltsAudit.size()>0) 
	{
		
		for(Audit a:ltsAudit)
		{   
			try {
			if(a.getStato()==null || (a.getStato()!=null && a.getStato().equals("0"))) 
			{
			File fileLog=new File(a.getPathFilesystem()+a.getFileName()+".txt");
			if(fileLog.exists()) 
			{
			OperationExportFile operationExportFile= new OperationExportFile(fileLog, auditManager);
			a.setChecksum(operationExportFile.getSecurityImpronta());	
			a.setStato("1");//generato
			a.setCodiceRegistro(a.getGiornoMeseAnno());
			auditService.updateAudit(a);
			audits.add(a);
			}else { logger.info("Audit File Log Non Esistente:: "+a.getPathFilesystem()+a.getFileName()+".txt"); }
			}else {
				audits.add(a);	
			}
		}catch (Exception e) { logger.error("GeneraRegistro > EseguiControlloImprontaFile:: Exception ",e);  }
			
		}
		
		
	}
	
	
	return audits;
}	


private RegistroAuditModel writeRegistro(List<Audit> ltsAudit,Date dateReg) throws Exception {
	logger.info("START RegistroGiornalieroAuditServiceImpl -> writeRegistro"); 
	String dataFold=DateConverter.getFormattedDate(dateReg, DATE_FORMAT_FILE);
	AuditManager auditManager=AuditManager.audit(AuditCustomSettyng.get());
	try {
	File fCheck=new File("");//auditManager.getPath_Log_User(dataFold));
	if(!fCheck.exists()) { fCheck.mkdirs(); }
	}catch (Exception e) { logger.error("writeRegistro -> Exception creazione directory",e); }
	
	Document document = new Document(PageSize.A4.rotate());
	String dateRegistro = DateConverter.getFormattedDate(dateReg, DATE_FORMAT_FILE);
	String fileName = REG_PROTOCOL + dateRegistro + "."	+ FILE_EXTENSION_PDF;
	String fileNameTmpCompletePath =""; //auditManager.getPath_Log_User(dataFold) + fileName;

	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileNameTmpCompletePath));
	writer.setPdfVersion(PdfWriter.VERSION_1_6);

	String dateRegistroTitle = DateConverter.getFormattedDate(dateReg, DATE_FORMAT_TITLE);
	String title = "Audit Registro giornaliero del " + dateRegistroTitle + "\n";
	document.addTitle(title);
	document.addAuthor("Sistema");
	document.setPageCount(0);
	document.setFooter(new HeaderFooter(new Phrase("Pagina "), true));
	document.open();
	logger.info("START -> "+title);
	Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

	document.add(new Paragraph(title, font));
	document.add(new Paragraph(" \n"));

	PdfPTable table = new PdfPTable(5);
	table.setWidthPercentage(100);
	table.setHeaderRows(1);
	
	//creo l'intestazione header
	if(ltsAudit!=null && ltsAudit.size()>0) {
		PdfPCell segnaturaHeaderCell = new PdfPCell(new Paragraph("Segnatura", font));
		PdfPCell fileNameHeaderCell = new PdfPCell(new Paragraph("File Name", font));
		fileNameHeaderCell.setColspan(2);
		PdfPCell codUtenteHeaderCell = new PdfPCell(new Paragraph("Cod. Utente", font));
		PdfPCell dataAuditHeaderCell = new PdfPCell(new Paragraph("Data Audit", font));

		table.addCell(segnaturaHeaderCell);
		table.addCell(fileNameHeaderCell);
		table.addCell(codUtenteHeaderCell);
		table.addCell(dataAuditHeaderCell);	
		logger.info("RegistroGiornalieroAuditServiceImpl -> writeRegistro Sono presenti ["+ltsAudit.size()+"] log audit"); 
		for(Audit audit:ltsAudit) {
		writeRowsPdfTable(table,audit);
		}
		
		
	document.add(table);	
	}else {
	logger.info("RegistroGiornalieroAuditServiceImpl -> writeRegistro Nessun log audit presente");  
	document.add(new Paragraph("Nessun file log audit presente."));
	 
	}
	
	document.close();

	RegistroAuditModel registroAudit= new RegistroAuditModel();
	registroAudit.setCodiceRegistro(DateConverter.getFormattedDate(dateReg, DATE_FORMAT_FILE));
	registroAudit.setDataCreazione(new Date());
	registroAudit.setFileName(fileName);
	registroAudit.setPathFileSystem(fileNameTmpCompletePath);
	registroAudit.setFlagGenerato("0");
	registroAudit.setAnno(""+getYearReg(dateReg));
	registroAudit.setChecksum("0");
	
	return registroAudit;
}

	private int getYearReg(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);
	}
 
	private Date getDateRegistro() {
		Calendar calendar= Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, -1);
		Date dataDiRegistro=calendar.getTime();
		return dataDiRegistro;
	}
	
	private void writeRowsPdfTable(PdfPTable table, Audit audit) throws Exception {

		Font fontDati = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
	 
		String segnatura = audit.getChecksum();
		String fileName = audit.getFileName();
		String codUtente = audit.getUserCod();
		String dataAudit = DateConverter.getFormattedDate(audit.getDataCreazione(), DATE_FORMAT_FILE);
		  
		table.addCell(getDatiCell(segnatura,fontDati));

		PdfPCell cellFileName = getDatiCell(fileName,fontDati);
		cellFileName.setColspan(2);
		table.addCell(cellFileName);
		table.addCell(getDatiCell(codUtente,fontDati));
		table.addCell(getDatiCell(dataAudit,fontDati));

		
		PdfPCell cellSeparator = new PdfPCell();
		cellSeparator.setFixedHeight(0.1f);
		cellSeparator.setColspan(5);
		cellSeparator.setBackgroundColor(Color.BLACK);
		table.addCell(cellSeparator);
		
	}
	
	private PdfPCell getDatiCell(String dato, Font fontDati) {
		return new PdfPCell(new Phrase(dato,fontDati));
	}	



}
