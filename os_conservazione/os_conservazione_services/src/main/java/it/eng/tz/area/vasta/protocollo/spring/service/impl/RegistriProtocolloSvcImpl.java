package it.eng.tz.area.vasta.protocollo.spring.service.impl;

import static it.eng.tz.area.vasta.protocollo.utils.Utils.formatDateTime;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

import it.eng.tz.area.vasta.protocollo.exception.ProtocolloDbException;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;
import it.eng.tz.area.vasta.protocollo.spring.dao.repository.RegistroProtocolloDao;
import it.eng.tz.area.vasta.protocollo.utils.DateConverter;

@Component
public class RegistriProtocolloSvcImpl {
	private static final Logger logger = LoggerFactory.getLogger(RegistriProtocolloSvcImpl.class.getName());
	public final String DATE_FORMAT_FILE = "yyyyMMdd";
	public final String DATE_FORMAT_TITLE = "dd/MM/yyyy";
	public final String TIME_FORMAT = "HH:mm:ss";
	public final String REG_PROTOCOL = "registro_protocollo_";
	public static final String FILE_EXTENSION_PDF = "pdf";
	
	@Autowired
	private Environment env;
	@Autowired
	private RegistroProtocolloDao regProtDao;

	
	private Date startDate;
	private Date endDate;
	private Date dataGeneraProto;
	private String regProtocolDiskFolder;
	private long primaReg;
	private long ultimaReg;

	
	public RegistriProtocolloSvcImpl(){}
	
	
	@PostConstruct
	public void init() {
		 this.regProtocolDiskFolder =env.getProperty("area.vasta.protocollo.cmis.system.folder.path.protocolli");
		 if(this.regProtocolDiskFolder!=null && !this.regProtocolDiskFolder.endsWith(File.separator))
			 this.regProtocolDiskFolder=this.regProtocolDiskFolder+File.separator;
		 
		 logger.debug("PATH SYSTEM LOCAL FOLDER [{}] ",this.regProtocolDiskFolder);

	}
	
 
	@Transactional(value = "txMgr", rollbackFor = { ProtocolloDbException.class }, readOnly = false)
	public List<RegistroProtocolloModel> ricercaRegistriProtocollo(DateTime from, DateTime to)
			throws ProtocolloDbException {
		if (from.compareTo(to) == 0) {
			if (logger.isDebugEnabled()) {
				logger.debug("DATA FROM [{}] DATA TO [{}]. DATE IDENTICHE. AGGIUNGO UN GIORNO ALLA DATA TO",
						formatDateTime(from, "dd/MM/yyyy"), formatDateTime(to, "dd/MM/yyyy"));
			}
			to = to.plusDays(1);
		}
		return regProtDao.ricercaRegistriProtocollo(from.toDate(), to.toDate());
	}

 
	public RegistroProtocolloModel generaRegistroProtocollo(String data) throws ProtocolloDbException {

		 

		 
		 Date date_ = null;
		try {
			date_ = new SimpleDateFormat("yyyy-MM-dd").parse(data);
		} catch (ParseException e) {
			logger.error("STRINGA DATA REGISTRO PROTOCOLLO NON VALIDA ERRORE NEL PARSING: ParseException",e);

		}
		
		this.setDataGeneraProto(date_);
		
		List<RegistroProtocolloModel> regModel=regProtDao.getRegistroProtocollo(date_);
		RegistroProtocolloModel registroProtocollo =null;
		if(regModel!=null && regModel.size()>0)
			registroProtocollo=regModel.get(0);
		
	 
		if (registroProtocollo != null && registroProtocollo.getIdRegistroProtocollo() > 0) {
			logger.info("DB: REGISTRO PROTOCOLLO ESISTENTE PER LA DATA_PRIMA_REG [{}]",data);
			
		}else {
			 registroProtocollo = new RegistroProtocolloModel();
			 registroProtocollo.setDataCreazione(new Date());
			 logger.info("NESSUN REGISTRO PROTOCOLLO TROVATO PER LA DATA [{}]",data);
			 logger.info("A FINE PROCESSO SI CREARA SUL DB UN NUOVO REGISTRO PROTOCOLLO PER LA DATA_PRIMA_REG [{}]",data);
		}
		 registroProtocollo.setFlagGenerato(false);
		 registroProtocollo.setRepositoryPointer(null);
		 registroProtocollo.setStatoVersamento("DA_VERSARE"); 
	 
			 
			
			return registroProtocollo;
		 

	 
	}

	private Date getStartDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getDataGeneraProto());
		cal.set(Calendar.MILLISECOND, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.HOUR, 0);
		// perchè interessa il giorno prima
		//cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	/**
	 * Calcola la data finale per il filtro dei protocolli da stampare. Poichè il
	 * servizio stampa le movimentazioni giornaliere, la data di fine è 23:59:59 del
	 * giorno prima
	 *
	 * @return la data finale per la ricerca dei protocolli
	 */
	private Date getEndDate() {

		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getDataGeneraProto());
		cal.set(Calendar.MILLISECOND, 999);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.HOUR, 23);
		// perchè interessa il giorno prima
		//cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	private int getYearReg() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(this.getDataGeneraProto());
		return cal.get(Calendar.YEAR);
	}
	
	
	private String generateRegistroProtocolloPdf(Date dataRegistro,String data) throws Throwable{
	    File fl=new File(this.getregProtocolDiskFolder());
	    if(!fl.exists()) {
	    	 logger.info("PATH DIRECTORY FOLDER DISK NON ESISTE [{}]",this.getregProtocolDiskFolder());	
	 	     logger.info("CREO LA DIRECTORY [{}]",this.getregProtocolDiskFolder());
	    	if(fl.mkdirs()) {
	    		logger.info("DIRECTORY CREATA [{}]",this.getregProtocolDiskFolder());
	    	}else {
	    	logger.error("NON E STATO POSSIBILE  CREARE LA DIRECTORY [{}]",this.getregProtocolDiskFolder());	
	    	}
	    }
//		List<ProtocolloModel> protocolloModels = protocolloDao.getListaProtocolliGeneraRegistro(data);
//		 
		Document document = new Document(PageSize.A4.rotate());
		String dateRegistro = DateConverter.getFormattedDate(dataRegistro, DATE_FORMAT_FILE);
		String fileName = REG_PROTOCOL + dateRegistro + "."	+ FILE_EXTENSION_PDF;
		String fileNameTmpCompletePath = this.getregProtocolDiskFolder() + fileName;

		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(fileNameTmpCompletePath));
		writer.setPdfVersion(PdfWriter.VERSION_1_6);

		String dateRegistroTitle = DateConverter.getFormattedDate(dataRegistro, DATE_FORMAT_TITLE);
		String title = "Registro giornaliero di Protocollo del " + dateRegistroTitle + "\n";
		document.addTitle(title);
		document.addAuthor("Sistema");
		document.setPageCount(0);
		document.setFooter(new HeaderFooter(new Phrase("Pagina "), true));
		document.open();
		
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD);

		document.add(new Paragraph(title, font));
		document.add(new Paragraph(" \n"));

		PdfPTable table = new PdfPTable(5);
		table.setWidthPercentage(100);
		table.setHeaderRows(1);

		PdfPTable protocolliAnnullatiTable = new PdfPTable(7);
		protocolliAnnullatiTable.setWidthPercentage(100);
		protocolliAnnullatiTable.setHeaderRows(1);

		 

		document.close();
		
		return fileName;
	}
 

	 
	private void createProtocolliPdfTable(PdfPTable table, Object protocollo, Map<String, List<Object>> map) throws Exception {

		String mittente = "";
		String destinatari = "";
		Font fontDati = FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL);
		Font fontDatiTitle = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE, 10, Font.NORMAL);

		// Mittente
	 		
		PdfPCell cellSeparator = new PdfPCell();
		cellSeparator.setFixedHeight(0.1f);
		cellSeparator.setColspan(5);
		cellSeparator.setBackgroundColor(Color.BLACK);
		table.addCell(cellSeparator);
		
	}
	public static final int AZIONE_PROTOCOLLO_ANNULLAMENTO = 12;
 
	
	
	

	private PdfPCell getDatiCell(String dato, Font fontDati) {
		return new PdfPCell(new Phrase(dato,fontDati));
	}
	
	public String getregProtocolDiskFolder() {
		return regProtocolDiskFolder;
	}
	
	
	 
	@Transactional(value = "txMgr", rollbackFor = { ProtocolloDbException.class }, readOnly = false)
	public int generaNewRegistroProtocollo(Object m) throws ProtocolloDbException {

		return 0;
	}
	
 
	
	public void setDataGeneraProto(Date dataGeneraProto) {
		this.dataGeneraProto = dataGeneraProto;
	}
	public Date getDataGeneraProto() {
		return dataGeneraProto;
	}
}
