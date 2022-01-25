/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.la.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import eng.tz.la.jms.IPrint;
import eng.tz.la.jms.Producer;
import eng.tz.la.jms.Reciever;
import eng.tz.la.model.AttributiServizio;
import eng.tz.la.model.ExternalAuditUser;
import eng.tz.la.model.Info;
import eng.tz.la.model.Line;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaInfo;
import eng.tz.la.model.MetaLine;
import eng.tz.la.model.OperationExportFile;
import eng.tz.la.model.Userlog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author s.mariniello
 */

public class AuditManager extends EntitySetting {
	private static final Logger logger = LoggerFactory.getLogger(AuditManager.class.getName());
	private static final long serialVersionUID = 1436749079910022271L;
	private static AuditManager auditFactory;

	private boolean encoded;
	private String keyUser = "Audit";
	private String dataFold;
	private Line<MetaLine> lineAudit;
	private MetaLine metaLine;
	private String origin;
	private String actor;
	private IMetaActor auditMetaActor;
	private MetaInfo metaInfo;
	private Userlog userlog;
	// private ObjectMapper objectMapper;
	private IOutputConverter iOutputConverter;
	private IWriteline iWriteline;
	private IPrint iPrint;
	private IAuditConfiguration auditConfiguration;
	private ExternalAuditUser auditUser;
	private AttributiServizio attrServizio;
	private MetaInfo attributeInfo;
	
	private AuditManager() {
		this.checkAllDir();
		this.auditInit();
		this.checkDirConservazione();
		this.dataFold = this.getFolderDay();
		 
		// this.objectMapper = new ObjectMapper();
		
	}

	private AuditManager(String keyUser) {
		this.actor = keyUser;
		this.dataFold = this.getFolderDay();

		this.checkAllDir();
		auditInit();
		// this.objectMapper = new ObjectMapper();
		this.checkDirConservazione();

	}

	private void checkService(IAuditConfiguration auditConfiguration) {
		this.auditConfiguration = auditConfiguration;

		if (auditConfiguration.initialSettyngs() != null)
			this.setSettyng(auditConfiguration.initialSettyngs());

		if (auditConfiguration.initialMetaActor() != null) {
			this.setMetaActor(auditConfiguration.initialMetaActor());
			this.auditUser=new ExternalAuditUser();
			this.keyUser = this.auditMetaActor.getActor();
			this.auditUser.setActor(this.auditMetaActor.getActor());
			this.auditUser.setOrigin(this.auditMetaActor.getActor());
		}
		if (auditConfiguration.initialJMSPrint() != null)
			this.setPrint(auditConfiguration.initialJMSPrint());

		if (auditConfiguration.initialOutputConverter() != null)
			this.setOutputConverter(auditConfiguration.initialOutputConverter());

		if (auditConfiguration.initialWriteline() != null)
			this.setWriteline(auditConfiguration.initialWriteline());

	}

	private AuditManager(IAuditConfiguration auditConfiguration) {
		checkService(auditConfiguration);
		checkAllDir();
		auditInit();
		// this.objectMapper = new ObjectMapper();
		this.checkDirConservazione();
		this.dataFold = this.getFolderDay();
 
	}

	private AuditManager(Settyngs settyngs) {
		this.setSettyng(settyngs);
		checkAllDir();
		auditInit();
		// this.objectMapper = new ObjectMapper();
		this.checkDirConservazione();
		this.dataFold = this.getFolderDay();

	}

	private AuditManager(Settyngs settyngs, String keyUser) {
		this.actor = keyUser;
		this.setSettyng(settyngs);
		this.checkAllDir();
		this.auditInit();
		// this.objectMapper = new ObjectMapper();
		this.checkDirConservazione();
		this.dataFold = this.getFolderDay();
	}

	private AuditManager writeLinea(Line<?> line) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(getPathAllCompleteLog(), true);
		} catch (FileNotFoundException e) {
			logger.error("AuditManager writeLinea ", e);
		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = getOutputConverter(line);// line.toString();

		pw.println(lineLog.toString());
		pw.flush();
		pw.close();

		return this;
	}

	public AuditManager writeLineLogUser(Line<?> lineLog) {
		this.checkUserLogginWriteLine().writeLineaUser(getOutputConverter(lineLog));
		return this;
	}

	public AuditManager writeLineLogUser(String lineLog) {
		this.checkUserLogginWriteLine().writeLineaUser(lineLog);
		return this;
	}

	public AuditManager writeLineLogUser(String pathFileComplete, String lineLog) {
		this.checkUserLogginWriteLine(pathFileComplete).writeLineaUser(pathFileComplete, lineLog);
		return this;
	}

	private AuditManager writeLineaUser(String pathFileComplete, String line) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(pathFileComplete, true);
		} catch (FileNotFoundException e) {
			logger.error("AuditManager writeLineaUser ", e);
		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = line.toString();

		pw.println(lineLog.toString());
		pw.flush();
		pw.close();

		return this;
	}

	private AuditManager writeLineaUser(String line) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(getPathUserLogginCompleteLog(), true);
		} catch (FileNotFoundException e) {
			logger.error("AuditManager writeLineaUser ", e);
		}
		PrintWriter pw = new PrintWriter(fos);
		String lineLog = line.toString();

		pw.println(lineLog.toString());
		pw.flush();
		pw.close();

		return this;
	}

	public AnnotationFactory annotation() {
		return AnnotationFactory.get(getSettyng());
	}

	public AuditManager build() {
		return this.buildAudit(false);
	}

	public AuditManager build(boolean isConsole) {
		return this.buildAudit(isConsole);
	}

	public AuditManager build(String retVal, boolean isConsole) {
		return this.buildAuditStr(retVal, isConsole);
	}

	public AuditManager build(String retVal) {
		return this.buildAuditStr(retVal, false);
	}

	
	
	private AuditManager buildAuditStr(String retVal, boolean isConsole) {
		this.checkiOutputConverter();
		this.checkiWriteline();
		MetaActorOrigin defaultMeta=new MetaActorOrigin();
		this.auditUser.setActor(defaultMeta.getActor());
		this.auditUser.setOrigin(defaultMeta.getOrigin());
		
		if (this.actor != null)
			this.keyUser = this.actor;
		if (origin != null || actor != null)
			this.lineAudit.setMetaActor(defaultMeta);
		if (this.auditMetaActor != null && this.actor == null) {
			this.lineAudit.setMetaActor(this.auditMetaActor);
			this.keyUser = this.auditMetaActor.getActor();
			this.auditUser.setActor(this.auditMetaActor.getActor());
			this.auditUser.setOrigin(this.auditMetaActor.getOrigin());
		}
		
		this.auditUser.setTimeString(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
		this.lineAudit.setT(this.metaLine);
		this.lineAudit.setMetaInfo(this.metaInfo);
		 
		this.attrServizio.setAttribute(attributeInfo);
		this.attrServizio.setActor(this.auditUser.getActor());
		this.attrServizio.setOrigin(this.auditUser.getOrigin());
		if(origin != null && !origin.isEmpty())
		{
			this.auditUser.setOrigin(origin);
			this.attrServizio.setOrigin(origin);
			final String iactor=this.auditUser.getActor();
			final String iorigin=origin;
			this.lineAudit.setMetaActor(new IMetaActor() {
				
				@Override
				public String toPrint() {
				return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "")
							+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";

				}
				
				@Override
				public String getOrigin() {
					 
					return iorigin;
				}
				
				@Override
				public String getActor() {
				 
					return iactor;
				}
			});
		}
		this.lineAudit.setAuditUser(this.auditUser);
		this.lineAudit.setAttrServizio(this.attrServizio);
		
		
		String lineContent = getOutputConverter(this.lineAudit);
		logger.info("AuditManager -> buildAuditStr  callWriteline lineContent==" + lineContent);
		this.callWriteline(lineContent, auditFactory);
		if (isConsole)
			logger.info(lineContent);

		retVal = lineContent;

		this.auditInit();

		return this;
	}

	private AuditManager buildAudit(boolean isConsole) {
		this.checkiOutputConverter();
		this.checkiWriteline();
		MetaActorOrigin defaultMeta=new MetaActorOrigin();
		
		this.auditUser.setActor(defaultMeta.getActor());
		this.auditUser.setOrigin(defaultMeta.getOrigin());
		if (this.actor != null)
			this.keyUser = this.actor;
		if (origin != null || actor != null)
			this.lineAudit.setMetaActor(defaultMeta);
		if (this.auditMetaActor != null && this.actor == null) {
			this.lineAudit.setMetaActor(this.auditMetaActor);
			this.keyUser = this.auditMetaActor.getActor();
			this.auditUser.setActor(this.auditMetaActor.getActor());
			this.auditUser.setOrigin(this.auditMetaActor.getOrigin());
		}
		
		this.auditUser.setTimeString(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
		this.lineAudit.setT(this.metaLine);
		this.lineAudit.setMetaInfo(this.metaInfo);
		 
		this.attrServizio.setAttribute(attributeInfo);
		this.attrServizio.setActor(this.auditUser.getActor());
		this.attrServizio.setOrigin(this.auditUser.getOrigin());
		if(origin != null && !origin.isEmpty())
		{
			this.auditUser.setOrigin(origin);
			this.attrServizio.setOrigin(origin);
			
			final String iactor=this.auditUser.getActor();
			final String iorigin=origin;
			this.lineAudit.setMetaActor(new IMetaActor() {
				
				@Override
				public String toPrint() {
				return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "")
							+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";

				}
				
				@Override
				public String getOrigin() {
					 
					return iorigin;
				}
				
				@Override
				public String getActor() {
				 
					return iactor;
				}
			});
			
		}
		this.lineAudit.setAuditUser(this.auditUser);
		this.lineAudit.setAttrServizio(this.attrServizio);
		

		String lineContent = getOutputConverter(this.lineAudit);
		logger.info("AuditManager -> buildAudit  callWriteline lineContent==" + lineContent);

		this.callWriteline(lineContent, auditFactory);

		if (isConsole)
			logger.info(lineContent);

		this.auditInit();

		return this;
	}

	public void startReceived() {
		checkiPrint();

		Reciever.get(this.iPrint).receive();

	}

	public void startReceivedDurable() {
		checkiPrint();
		Reciever.get(this.iPrint, this).ReceiverDurable();
	}

	public void startReceivedDurable(String brokerUrl) {
		checkiPrint();
		Reciever.get(brokerUrl, this.iPrint, this).ReceiverDurable();
	}

	public void startReceivedDurable(String brokerUrl, String topicName) {
		checkiPrint();
		Reciever.get(brokerUrl, topicName, this.iPrint, this).ReceiverDurable();
	}

	public void startReceivedDurableWhile() {
		checkiPrint();
		Reciever.get(this.iPrint, this).ReceiverDurableCls();
	}

	public void startReceivedDurableWhile(String brokerUrl) {
		checkiPrint();
		Reciever.get(brokerUrl, this.iPrint, this).ReceiverDurableCls();
	}

	public void startReceivedDurableWhile(String brokerUrl, String topicName) {
		checkiPrint();
		Reciever.get(brokerUrl, topicName, this.iPrint, this).ReceiverDurableCls();
	}

	public void startReceived(IPrint iPrint, String actorId, String dataFold) {
		this.keyUser = actorId;
		this.dataFold = dataFold;
		Reciever.get(iPrint).receive();

	}

	private void auditInit() {
		this.lineAudit = new Line<MetaLine>();
		this.metaLine = new MetaLine();
		this.metaInfo = new MetaInfo();
		this.auditUser=new ExternalAuditUser();
		this.attributeInfo=new MetaInfo();
		this.attrServizio=new AttributiServizio();
		this.origin = null;
		this.actor = null;


	}

	private String getOutputConverter(Line<?> line) {
		try {
			return this.iOutputConverter.encode(line);
		} catch (Exception e) {

		}
		return "";
	}

	private void callWriteline(String lineContent, AuditManager auditManager) {
		try {
			this.iWriteline.write(lineContent, auditManager);
		} catch (Exception e) {

		}

	}

	private void checkiWriteline() {
		if (this.iWriteline == null) {
			this.iWriteline = new IWriteline() {

				public void write(String lineContent, AuditManager auditManager) throws Exception {
					writeLineLogUser(lineContent);
				}
			};
		}
	}

	private void checkiPrint() {
		if (this.iPrint == null) {
			this.iPrint = new IPrint() {

				public <T> void print(TextMessage txtMsg, AuditManager auditManager) throws JMSException {

					String actorId = txtMsg.getStringProperty(Producer.ACTOR);
					String dataFolder = txtMsg.getStringProperty(Producer.DATA_FOLD);
					if (actorId != null && !actorId.isEmpty())
						setKeyUser(actorId);
					if (dataFolder != null && !dataFolder.isEmpty())
						setDataFold(dataFolder);
					logger.info("StartReceived IPrint -> ACTOR== " + actorId + " DATAFOLD== " + dataFolder);
					// checkWriteLine().writeLinea(new Line<String>(txtMsg.getText()));
					writeLineLogUser(txtMsg.getText());
					// callWriteline(new Line<String>(txtMsg.getText()), auditFactory);
				}

			};
		}

	}

	private void checkiOutputConverter() {
		if (this.iOutputConverter == null) {
			this.iOutputConverter = new IOutputConverter() {

				public String encode(Line<?> line) throws Exception {
					return line.toString();
				}
			};
		}
	}

	public AuditManager log(MetaLine line, Object reflection, Object request) {
		this.metaLine.addAll(line);
		if (reflection != null) {
			Line<MetaLine> lineAnn = AnnotationFactory.audit(reflection, request, getSettyng());
			if (lineAnn != null && lineAnn.getMetaField() != null)
				this.metaLine.addAll(lineAnn.getMetaField());
			if (lineAnn.getMetaActor() != null && this.auditMetaActor == null)
				this.auditMetaActor = lineAnn.getMetaActor();
		}
		return this;
	}

	public AuditManager log(Object reflection, Object request) {
		if (reflection != null) {
			Line<MetaLine> lineLog = AnnotationFactory.audit(reflection, request, getSettyng());
			if (lineLog != null && lineLog.getMetaField() != null)
				this.metaLine.addAll(lineLog.getMetaField());
			if (lineLog.getMetaActor() != null && this.auditMetaActor == null)
				this.auditMetaActor = lineLog.getMetaActor();

		}
		return this;
	}

	public AuditManager log(MetaLine line, Object reflection) {

		this.metaLine.addAll(line);
		if (reflection != null) {
			Line<MetaLine> lineAnn = AnnotationFactory.audit(reflection, getSettyng());
			if (lineAnn != null && lineAnn.getMetaField() != null)
				this.metaLine.addAll(lineAnn.getMetaField());
			if (lineAnn.getMetaActor() != null && this.auditMetaActor == null)
				this.auditMetaActor = lineAnn.getMetaActor();
		}

		return this;
	}

	public AuditManager log(Object reflection) {

		if (reflection != null) {
			Line<MetaLine> lineLog = AnnotationFactory.audit(reflection, getSettyng());
			if (lineLog != null && lineLog.getMetaField() != null)
				this.metaLine.addAll(lineLog.getMetaField());
			if (lineLog.getMetaActor() != null && this.auditMetaActor == null)
				this.auditMetaActor = lineLog.getMetaActor();
		}

		return this;
	}

	public AuditManager logRef(Object reflection) {

		if (reflection != null) {
			Line<MetaLine> lineLog = AnnotationFactory.auditNotAnnotation(reflection, getSettyng());
			if (lineLog != null && lineLog.getMetaField() != null)
				this.metaLine.addAll(lineLog.getMetaField());
			if (lineLog.getMetaActor() != null && this.auditMetaActor == null)
				this.auditMetaActor = lineLog.getMetaActor();
		}

		return this;
	}

	public AuditManager log(MetaLine line) {
		this.metaLine.addAll(line);
		return this;
	}
	public AuditManager addCallGeo() {
		 this.attrServizio.setCallGeo("1");
		return this;
	}
	public AuditManager addServizioIdServizio(String idServizio) {
		 this.attrServizio.setIdServizio(idServizio);
		return this;
	}
	public AuditManager addServizioCodiceServizio(String codServizio) {
		 this.attrServizio.setCodServizio(codServizio);
		return this;
	}
	public AuditManager addServizioUuidTransazione(String uuidTransaz) {
		 this.attrServizio.setUuidTransazione(uuidTransaz);
		return this;
	}
	
	public AuditManager addAttrServizio(String nameKey,String value) {
		 this.attributeInfo.add(new Info(nameKey, value));
		return this;
	}
 
	public AuditManager addServizioTimeString() {
	this.attrServizio.setTimeString(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
	return this;
	}
	
	public AuditManager addOperazioneRichiesta(String operazioneRichiesta) {
		this.auditUser.setOperazioneRichiesta(operazioneRichiesta);
		this.metaInfo.add(new Info("OPERAZIONE-RICHIESTA", operazioneRichiesta));
		return this;
	}
	
	public AuditManager addUrlRichiesta(String urlRichiesta) {
		this.auditUser.setUrlRichiesta(urlRichiesta);
		this.metaInfo.add(new Info("URL-RICHIESTA", urlRichiesta));
		return this;
	}
	
	public AuditManager addPaginaCorrente(String paginaCorrente) {
		this.auditUser.setPaginaCorrente(paginaCorrente);
		this.metaInfo.add(new Info("PAGINA-CORRENTE", paginaCorrente));
		return this;
	}
	
	public AuditManager addNumeroProcollo(String numeroProcollo) {
		this.auditUser.setNumeroProcollo(numeroProcollo);
		this.metaInfo.add(new Info("NUMERO-PROTOCOLLO", numeroProcollo));
		return this;
	}
	
	public AuditManager addImprontaDelega(String improntaDelega) {
		this.auditUser.setImprontaDelega(improntaDelega);
		this.metaInfo.add(new Info("IMPRONTA-DELEGA", improntaDelega));
		return this;
	}
	
	public AuditManager addFirmaGrafoAcquisita(String firmaGrafoAcquisita) {
		this.auditUser.setFirmaGrafoAcquisita(firmaGrafoAcquisita);
		return this;
	}
	
	public AuditManager addDelegante(String codDelegante) {
		this.auditUser.setDelegante(codDelegante);
		return this;
	}
	 
	public AuditManager addMetaInfo(MetaInfo info) {
		this.metaInfo.addAll(info);
		return this;
	}

	public AuditManager addInfo(Info info) {
		this.metaInfo.add(info);
		return this;
	}

	public AuditManager addInfo(String name, String value) {
		this.metaInfo.add(new Info(name, value));
		return this;
	}

	public AuditManager addInizioOperazione() {
		String timeStr=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date());
		this.lineAudit.setInizioOperazione(timeStr);
		return this;
	}

	public AuditManager addFineOperazione() {
		this.lineAudit.setFineOperazione(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(new Date()));
		return this;
	}

	public AuditManager addEsitoError() {
		this.lineAudit.setEsito("KO");
		this.auditUser.setEsito("KO");
		return this;
	}

	public AuditManager addEsitoSuccess() {
		this.lineAudit.setEsito("OK");
		this.auditUser.setEsito("OK");
		return this;
	}

	
	public AuditManager addEsitoError(String esito) {
		this.lineAudit.setEsito(esito);
		this.auditUser.setEsito(esito);
		return this;
	}

	public AuditManager addEsitoSuccess(String esito) {
		this.lineAudit.setEsito(esito);
		this.auditUser.setEsito(esito);
		return this;
	}
	
	public AuditManager addUuidOperazione(String uiidOp) {
		this.lineAudit.setUuidOperazione(uiidOp);
		this.auditUser.setUuid(uiidOp);
		this.attrServizio.setUuidTransazione(uiidOp);
		return this;
	}

	public AuditManager log(String line) {
		this.metaLine.add(new MetaField(null, line));
		return this;
	}

	public AuditManager setActor(String actor) {
		this.actor = actor;
		return this;
	}

	public AuditManager setOrigin(String origin) {
		this.origin = origin;
		return this;
	}

	public AuditManager setMetaActor(Object metaActor) {
		try {
			this.auditMetaActor = (IMetaActor) metaActor;
		} catch (Exception e) {
		}
		return this;
	}

	public AuditManager setMetaActor(IMetaActor metaActor) {
		this.auditMetaActor = metaActor;
		return this;
	}

	public AuditManager setOutputConverter(IOutputConverter iOutputConverter) {
		this.iOutputConverter = iOutputConverter;
		return this;
	}

	public AuditManager setWriteline(IWriteline iWriteline) {
		this.iWriteline = iWriteline;
		return this;
	}

	public AuditManager setPrint(IPrint iPrint) {
		this.iPrint = iPrint;
		return this;
	}

	public AuditManager setAuditConfiguration(IAuditConfiguration auditConfiguration) {
		checkService(auditConfiguration);
		return this;
	}

	public IAuditConfiguration getAuditConfiguration() {
		return auditConfiguration;
	}

	public AuditManager setMetaActor(Class<?> metaActor) {
		Object mta = null;
		try {
			mta = metaActor.newInstance();
			if (mta != null)
				this.auditMetaActor = (IMetaActor) mta;
		} catch (InstantiationException e) {
			logger.error("AuditManager setMetaActor ", e);
		} catch (IllegalAccessException e) {
			logger.error("AuditManager setMetaActor ", e);
		} catch (Exception e) {
			logger.error("AuditManager setMetaActor ", e);
		}

		return this;
	}

	public AuditManager addMetaField(String name, Object value) {
		this.metaLine.add(
				new MetaField(name, value == null ? new String() : value, value == null ? null : value.getClass()));
		return this;
	}

	public boolean isEncoded() {
		return encoded;
	}

	public AuditManager setEncoded(boolean encoded) {
		this.encoded = encoded;
		return this;
	}

	public AuditManager laSettyng(Settyngs settyngs) {
		this.setSettyng(settyngs);

		checkAllDir();
		this.checkDirConservazione();

		return this;
	}

	public AuditManager laSettyng(Class<?> settyngs) {
		try {
			this.setSettyng((Settyngs) settyngs.newInstance());

		} catch (InstantiationException e) {
			logger.error("AuditManager laSettyng ", e);
		} catch (IllegalAccessException e) {
			logger.error("AuditManager laSettyng ", e);
		}
		checkAllDir();
		this.checkDirConservazione();

		return this;
	}

	public synchronized static AuditManager audit() {
		auditFactory = new AuditManager();
		return auditFactory;

	}

	public synchronized static AuditManager audit(IAuditConfiguration auditConfiguration) {
		auditFactory = new AuditManager(auditConfiguration);
		return auditFactory;

	}

	public synchronized static AuditManager audit(Settyngs settyngs) {
		auditFactory = new AuditManager(settyngs);
		auditFactory.laSettyng(settyngs);
		return auditFactory;

	}

	public String getKeyUser() {
		return keyUser;
	}

	private AuditManager checkWriteLine() {
		File f = new File(getPathAllCompleteLog());
		if (!f.exists()) {
			return this.writeClearAll();
		}
		return checkUserLogginWriteLine();
	}

	private AuditManager checkUserLogginWriteLine(String pathComplete) {
		File f = new File(pathComplete);
		if (!f.exists()) {
			return this.writeClear(pathComplete);
		}
		return this;
	}

	private AuditManager writeClear(String pathComplete) {
		BufferedWriter in = null;
		try {
			File f = new File(pathComplete);
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
			logger.error("AuditManager writeClear ", ex);
		}
		return this;

	}

	private AuditManager checkUserLogginWriteLine() {
		File f = new File(getPathUserLogginCompleteLog());
		if (!f.exists()) {
			return this.writeClear();
		}
		return this;
	}

	private AuditManager writeClear() {
		BufferedWriter in = null;
		try {
			File f = new File(getPathUserLogginCompleteLog());
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
			logger.error("AuditManager writeClear ", ex);
		}
		return this;

	}

	private AuditManager writeClearAll() {

		BufferedWriter in = null;
		try {
			File f = new File(getPathAllCompleteLog());
			in = new BufferedWriter(new FileWriter(f));
			in.write("");
			in.flush();
			in.close();
		} catch (IOException ex) {
			logger.error("AuditManager writeClearAll ", ex);
		}
		return this;

	}

	public AuditManager crypt() {
		this.cryptFileUser();
		return this;
	}

	public void export(boolean isCryptFile, Export export) throws UnsupportedEncodingException, IOException {
		if (isCryptFile) {
			this.cryptFile();
			export.call(new OperationExportFile(new File(getPathAllCompleteLog() + getCryptExtension()), auditFactory));
		} else {
			export.call(new OperationExportFile(new File(getPathAllCompleteLog()), auditFactory));
		}
	}

	public void exportUser(boolean isCryptFile, Export export) throws UnsupportedEncodingException, IOException {
		if (isCryptFile) {
			this.cryptFileUser();
			export.call(new OperationExportFile(new File(getPathUserLogginCompleteLog() + getCryptExtension()), auditFactory));
		} else {
			export.call(new OperationExportFile(new File(getPathUserLogginCompleteLog()), auditFactory));
		}
	}

	private String getPathAllCompleteLog() {
		return getPath_Log_User() + checkLogName();
	}

	private String getPathUserLogginCompleteLog() {
		return getPath_Log_User(this.dataFold)
				+ checkLogNameUserLoggin(this.getCodLogDataFold(this.dataFold, this.keyUser));// this.getCodLog(this.keyUser));
	}

	public String encrypt(String s) {
		return Base64.encodeBytes(s.getBytes());
	}

	public String decrypt(String s) throws UnsupportedEncodingException {
		return new String(Base64.decode(s), "UTF-8");
	}

	private boolean cryptFile() {

		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedWriter out = null;

		try {

			fstream = new FileInputStream(getPathAllCompleteLog());
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder fileContent = new StringBuilder();

			while ((strLine = br.readLine()) != null) {
				fileContent.append(strLine);
				fileContent.append(LINE_SEPARATOR);
			}

			FileWriter fstreamWrite = new FileWriter(getPathAllCompleteLog() + getCryptExtension());
			out = new BufferedWriter(fstreamWrite);
			out.write(this.encrypt(fileContent.toString()));

		} catch (Exception e) {
			logger.error("AuditManager cryptFile ", e);
			return false;
		} finally {
			try {
				fstream.close();
				out.flush();
				out.close();
				in.close();
			} catch (IOException e) {
				logger.error("AuditManager cryptFile ", e);
				return false;
			}
		}

		return true;
	}

	private boolean cryptFileUser() {

		FileInputStream fstream = null;
		DataInputStream in = null;
		BufferedWriter out = null;

		try {

			fstream = new FileInputStream(getPathUserLogginCompleteLog());
			in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			StringBuilder fileContent = new StringBuilder();

			while ((strLine = br.readLine()) != null) {
				fileContent.append(strLine);
				fileContent.append(LINE_SEPARATOR);
			}

			FileWriter fstreamWrite = new FileWriter(getPathUserLogginCompleteLog() + getCryptExtension());
			out = new BufferedWriter(fstreamWrite);
			out.write(this.encrypt(fileContent.toString()));

		} catch (Exception e) {
			logger.error("AuditManager cryptFileUser ", e);
			return false;
		} finally {
			try {
				fstream.close();
				out.flush();
				out.close();
				in.close();
			} catch (IOException e) {
				logger.error("AuditManager cryptFileUser ", e);
				return false;
			}
		}

		return true;
	}

	public void setKeyUser(String keyUser) {
		this.keyUser = keyUser;
	}

	public void setDataFold(String dataFold) {
		this.dataFold = dataFold;
	}

	public String getCodFileName() {
		return this.getCodLog(this.keyUser);
	}

	private class MetaActorOrigin implements IMetaActor {

		public String getActor() {

			return actor;
		}

		public String getOrigin() {

			try {
				return InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException e) {
				logger.error("AuditManager getOrigin", e);
			} catch (Exception e) {
				logger.error("AuditManager getOrigin", e);
			}

			return origin;
		}

		@Override
		public String toString() {
			return toPrint();
		}

		public String toPrint() {
			return "" + (getOrigin() != null ? "Origin==>" + getOrigin() : "")
					+ (getActor() != null ? " Actor==>" + getActor() + " " : "") + "|";
		}

	}

}
