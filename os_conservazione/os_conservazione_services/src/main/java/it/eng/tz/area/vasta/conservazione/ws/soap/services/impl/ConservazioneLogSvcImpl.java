package it.eng.tz.area.vasta.conservazione.ws.soap.services.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.xml.transform.stream.StreamResult;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.util.StringUtils;

import it.eng.tz.area.vasta.conservazione.util.TimeUtil;
import it.eng.tz.area.vasta.conservazione.ws.exception.ConservazioneSipException;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.Applicazione;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.ComponentiDigitaliDoc;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.DatiConservazione;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.Description;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.DigestEncoding;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.File.Hash;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.FormaGiuridica;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.HashFunc;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.ItemType;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.LongDescription;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.ObjectFactory;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.ProduttoreSIP;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.RelVsSD;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.RifDizionario;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.RifEntitaContesto;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SDStdInfo;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SDStdInfo.DigDocs;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SIP;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SIP.Contenuti;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SIP.Contenuti.Item;
import it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SIP.SelfDescription;
import it.eng.tz.area.vasta.conservazione.ws.soap.results.ReceiveSipResult;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.ConservazioneLogSvc;
import it.eng.tz.area.vasta.conservazione.ws.soap.services.ReceiveSipSvc;
import it.eng.tz.area.vasta.protocollo.utils.Utils;

@Service
public class ConservazioneLogSvcImpl implements ConservazioneLogSvc {
	private static final Logger logger = LoggerFactory.getLogger(ConservazioneLogSvcImpl.class.getName());
	private static final String TASK_ID = ConservazioneLogSvcImpl.class.getSimpleName();
	@Autowired
	private ReceiveSipSvc receiveSipSvc;
	@Autowired
	@Qualifier("manifestSipMarshaller")
	private Jaxb2Marshaller manifestMarshaller;
	@Value("${area.vasta.conservazione.sip.nome.applicazione}")
	private String nomeApplicazione;
	@Value("${area.vasta.conservazione.sip.tipo.produttore}")
	private String tipoProduttore;
	@Value("${area.vasta.conservazione.sip.intestazione.produttore}")
	private String intestazioneProduttore;
	@Value("${area.vasta.conservazione.sip.codice.fiscale.produttore}")
	private String cfProduttore;
	@Value("${area.vasta.conservazione.sip.codice.amministrazione.produttore}")
	private String codiceAmministrazione;
	@Value("${area.vasta.conservazione.sip.codice.aoo.produttore}")
	private String codiceAoo;
	@Value("#{'${area.vasta.conservazione.sip.estensioni.file}'.split(',')}") 
	private List<String> estensioniFile;
	@Value("${area.vasta.conservazione.sip.intestazione.item}") 
	private String intestazioneItem;
	/**
	 * {@inheritDoc}
	 */
	@Override
	public ReceiveSipResult conservaLog(String path) throws ConservazioneSipException {
		StopWatch sw = new StopWatch(TASK_ID);
		String errorMessage = null;
		try {
			sw.start("CREAZIONE ARCHIOVIO ZIP");
			File fileDaConservare = this.createArchivio(path);
			sw.stop();
			sw.start("INVIO CONSERVAZIONE");
			String dataFolder="";
			if(path!=null && path.length()>8)
			dataFolder=path.substring(path.length()-8, path.length());
			
			ReceiveSipResult result = receiveSipSvc.inviaConservazione("", "", "SHA-256", "hex", DigestUtils.sha256Hex(new FileInputStream(fileDaConservare)), fileDaConservare,dataFolder);
			sw.stop();
			if( logger.isInfoEnabled() )
			{
				logger.info("Conservazione terminata. Esito invio {}"+result);
			}
		 
			return result;
		} 
		catch (Exception e) {
			errorMessage = "Errore nella conservazione; "+e.getMessage();
			logger.error(errorMessage, e);
			throw new ConservazioneSipException(errorMessage);
		}
		finally {
			boolean forcedStop = false;
			if( sw.isRunning() )
			{
				forcedStop = true;
				sw.stop();
			}
			Utils.printTaskInfo(logger, sw, forcedStop, errorMessage);
		}
	}
	private File createArchivio( String path ) throws Exception {
		if( !StringUtils.hasText(path) )
		{
			throw new IllegalArgumentException("Impossibile proseguire. Passato un path nullo o vuoto");
		}
		if( logger.isDebugEnabled() )
		{
			logger.debug("Creazione archivio ZIP per i log al path {}", path);
		}
		return execute(path);
	}
	private File execute( String path ) throws Exception{
		
		String dataFolder=path.substring(path.length()-8, path.length());
		// elimino lo zip generato precedentemente se presente.
		File checkZip=new File(path+File.separator+"consevazioneLog_"+dataFolder+".zip");
		if(checkZip.exists()) 
		{
		String pathFile=checkZip.getAbsolutePath();
		boolean isdelete=checkZip.delete();
		logger.info("Method[createArchivio] [execute] Check-Zip - PATH[{}] ELIMINATO[{}]",pathFile,isdelete);
		//?? se il file non è stato eliminato, forzo l'eliminazione
		if(!isdelete)
		FileUtils.forceDeleteOnExit(checkZip);
		}
		File toZip = new File(path);
		if( !toZip.isDirectory() )
		{
			throw new IllegalAccessError("Impossibile proseguire. Non è stata passata una directory <"+path+">");
		}
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		DateTime now = new DateTime();
		BufferedWriter bw = null;
		OutputStreamWriter osw = null;
		FileOutputStream xmlFos = null;
		try {
			String zipName = "consevazioneLog_"+dataFolder+".zip";//TimeUtil.formatDateTime(now, "dd_MM_yyyy")+".zip";
			File parent = new File(path);
			File zip = new File(parent, zipName);
			fos = new FileOutputStream(zip);
			zos = new ZipOutputStream(fos);
			ObjectFactory of = new ObjectFactory();
			SIP sip = of.createSIP();
			SelfDescription sd = of.createSIPSelfDescription();
			//Dati dell'applicazione
			Applicazione applicazione = of.createApplicazione();
			applicazione.setNome(nomeApplicazione);
			//La versione non cambierà
			applicazione.setVer("1.0");
			Description descrizione = of.createDescription();
			descrizione.setLang("IT");
			descrizione.setValue(nomeApplicazione);
			applicazione.setDescrizione(descrizione);
			sd.setCreatoDaApplicazione(applicazione);
			//Dati del produttore
			ProduttoreSIP produttoresip = of.createProduttoreSIP();
			produttoresip.setCodFiscale(cfProduttore);
			if( StringUtils.hasText(codiceAmministrazione) )
			{

				produttoresip.setCodiceAmministrazione(codiceAmministrazione);
			}
			if( StringUtils.hasText(codiceAoo) )
			{

				produttoresip.setCodiceAOO(codiceAoo);
			}
			produttoresip.setIntestazione(intestazioneProduttore);
			//Sarà sempre una PA a creare questo file
			produttoresip.setTipo(FormaGiuridica.PA);
			sd.setProduttore(produttoresip);
			String giornoConservazione = TimeUtil.formatDateTime(now, "dd/MM/yyyy 'ore' HH:mm:ss:sss");
			sd.setTsGenerazione(TimeUtil.dateTOXMLGregorianCalendar(now));
			sd.setLabel("Log applicativo del "+giornoConservazione);
			sip.setSelfDescription(sd);
			Contenuti contenuti = of.createSIPContenuti();
			List<Item> items = contenuti.getItem();
			File[] files = toZip.listFiles();
			for (int i = 0; i < files.length; i++) {

				File ilFile = files[i];
				if(!ilFile.getName().contains("Audit") && !ilFile.getName().contains("Sistem")&& !ilFile.getName().contains("anonimus"))
				if(FilenameUtils.isExtension(ilFile.getName(), estensioniFile))
				{
					items.add(creaItemDaFile(ilFile, of, giornoConservazione));
					addFileToZip(ilFile, zos);
				}
				else
				{
					if( logger.isWarnEnabled() )
					{
						logger.warn("NOME FILE {} ESTENSIONE FILE {}. ESTENSIONI AMMESSE {} FILE NON AGGIUNTO ALLO ZIP", ilFile.getName(), FilenameUtils.getExtension(ilFile.getName()),estensioniFile);
					}
				}
			}
			sip.setContenuti(contenuti);
			//Creo l'XML e lo aggiungo allo zip
			File manifestXml = new File(toZip, "SIPManifest.xml");
			xmlFos = new FileOutputStream(manifestXml);
			osw = new OutputStreamWriter(xmlFos);
			bw = new BufferedWriter(osw);

			this.manifestMarshaller.marshal(sip, new StreamResult(bw));
			addFileToZip(manifestXml, zos);
			return zip;
		}
		finally {

			if(zos != null)
			{
				zos.close();
			}
			if( fos != null )
			{
				fos.close();
			}
			if( bw != null )
			{
				bw.close();
			}
			if( osw != null )
			{
				osw.close();
			}
			if( xmlFos != null )
			{
				xmlFos.close();
			}
		}
	}
	private void addFileToZip( File ilFile, ZipOutputStream zos ) throws Exception {
		ZipEntry elementoZip = new ZipEntry(ilFile.getName());
		FileInputStream fis = null; 
		try {
			zos.putNextEntry(elementoZip);
			fis = new FileInputStream(ilFile);
			IOUtils.copy(fis, zos);
		}
		finally
		{
			if( fis != null )
			{
				fis.close();
			}
		}
	}
	private Item creaItemDaFile( File ilFile, ObjectFactory of, String giornoConservazione )
	{
		Item item = of.createSIPContenutiItem();
		item.setType(ItemType.SD);
		item.setId(UUID.randomUUID().toString());
		SDStdInfo sdsTdInfo = of.createSDStdInfo();
		LongDescription descrizioneItem = of.createLongDescription();
		descrizioneItem.setLang("IT");
		descrizioneItem.setValue("Log applicativo "+ilFile.getName()+" del "+ giornoConservazione);
		sdsTdInfo.setDesOgg(descrizioneItem);
		DigDocs digDoc = of.createSDStdInfoDigDocs();
		digDoc.setRelVsSD(RelVsSD.P);
		digDoc.setNroAllegato(BigInteger.ONE);
		digDoc.setId(UUID.randomUUID().toString());
		RifDizionario supportoFisico = of.createRifDizionario();
		supportoFisico.setCodice("D");
		digDoc.setSupportoFisico(supportoFisico);
		RifEntitaContesto tipo = of.createRifEntitaContesto();
		tipo.setIntestazione(intestazioneItem);
		digDoc.setTipo(tipo);
		ComponentiDigitaliDoc componentiDigitali = of.createComponentiDigitaliDoc();
		it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.File filePrincipale = of.createFile();
		filePrincipale.setPath(ilFile.getName());
		Hash hash = of.createFileHash();
		hash.setEncoding(DigestEncoding.HEX);
		hash.setFunc(HashFunc.SHA_256);
		try {
			hash.setValue(DigestUtils.sha256Hex(new FileInputStream(ilFile)));
		} catch (Exception e) {

			throw new IllegalStateException(e);
		}
		filePrincipale.setHash(hash);
		componentiDigitali.setFilePrincipale(filePrincipale);
		digDoc.setComponentiDigitali(componentiDigitali);
		sdsTdInfo.getDigDocs().add(digDoc);
		DatiConservazione datiConservazione = of.createDatiConservazione();
		datiConservazione.setConservazioneIllimitata(BigInteger.ONE);
		sdsTdInfo.setConservazione(datiConservazione);
		item.setSDStdInfo(sdsTdInfo);
		return item;
	}
}