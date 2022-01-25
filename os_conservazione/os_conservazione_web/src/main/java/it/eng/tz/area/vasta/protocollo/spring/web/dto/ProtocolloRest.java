package it.eng.tz.area.vasta.protocollo.spring.web.dto;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.QueryResult;

import it.eng.tz.area.vasta.documentale.cmis.util.ProtocolloProp;
import it.eng.tz.area.vasta.protocollo.spring.dao.models.RegistroProtocolloModel;

public class ProtocolloRest {
	private String idRegistroProtocollo;
	private String puntatoreAlfresco;
	private String dataCreazione;
	private int flagGenerato;
	private String pathFileSystem;
	private String fileName;
	private String destinatario;
	private String checkSum;
	private String codiceAmministrazione;
	private String codiceAoo;
	private String soggettoProduttore2;
	private String soggettoProduttore;
	private String responsabile;
	private String oggetto;
	private String codiceRegistro;
	private String progressivo;
	private String anno;
	private String numeroPrimaReg;
	private String numeroUltimaReg;
	private String dataPrimaReg;
	private String dataUltimaReg;
	private SimpleDateFormat formatt=new SimpleDateFormat("dd/MM/yyyy");
	public ProtocolloRest() {
	 
	}

	
	public ProtocolloRest(String data) {
		this.dataPrimaReg = data;
		this.dataUltimaReg =  data;
		this.flagGenerato = 0;
		this.fileName="DOCUMENTO NON PRESENTE";
		this.idRegistroProtocollo = "0";
		this.puntatoreAlfresco = "0";
		this.dataCreazione="";
	}
	
	

	public ProtocolloRest(ProtocolloProp model) {
		super();

		this.idRegistroProtocollo = model.getDocid().toString();
		this.puntatoreAlfresco = model.getObjectId().toString();
		this.dataCreazione =  model.getDataCreazione()!=null?formatt.format(((GregorianCalendar)model.getDataCreazione()).getTime()):"";
		this.flagGenerato = 1;
		this.pathFileSystem = "C:\\D\\registro_protocollo_"+model.getDataPrimaRegistrazioneEffettuataSulProtocollo().toString().replaceAll("-", "").replaceAll("/", "")+".pdf";
		this.fileName = model.getFilename().toString();
		this.destinatario = model.getDestinatarioDenominazione().toString();
		this.codiceAmministrazione = model.getDenominazioneDellAmministrazione().toString();
		this.responsabile = model.getResponsabileProcedimento().toString();
		this.oggetto = model.getOggettodocumento().toString();
		this.codiceRegistro = model.getCodiceIdentificativoDelRegistro().toString();
		this.progressivo = model.getNumeroProgressivoDelRegistro().toString();
		this.numeroPrimaReg = model.getNumeroPrimaRegistrazioneEffettuataSulRegistro().toString();
		this.numeroUltimaReg = model.getNumeroUltimaRegistrazioneEffettuataSulRegistro().toString();
		this.dataPrimaReg = model.getDataPrimaRegistrazioneEffettuataSulProtocollo().toString();
		this.dataUltimaReg =  model.getDataUltimaRegistrazioneEffettuataSulRegistro().toString();
	}
	    public void setProtocolloRest(ProtocolloProp model) {
	    
		this.idRegistroProtocollo = model.getDocid()!=null?model.getDocid().toString():"0";
		this.puntatoreAlfresco = model.getObjectId().toString();
		this.dataCreazione = model.getDataCreazione()!=null?formatt.format(((GregorianCalendar)model.getDataCreazione()).getTime()):"";
		this.flagGenerato = 1;
		this.pathFileSystem = "C:\\D\\registro_protocollo_"+model.getDataUltimaRegistrazioneEffettuataSulRegistro().toString().replaceAll("-", "").replaceAll("/", "")+".pdf";
		this.fileName = model.getFilename()!=null?model.getFilename().toString():"registro_protocollo_"+model.getDataUltimaRegistrazioneEffettuataSulRegistro().toString().replaceAll("-", "").replaceAll("/", "")+".pdf";
		//this.destinatario = model.getDestinatarioDenominazione().toString();
//		this.codiceAmministrazione = model.getDenominazioneDellAmministrazione().toString();
//		this.responsabile = model.getResponsabileProcedimento().toString();
//		this.oggetto = model.getOggettodocumento().toString();
//		this.codiceRegistro = model.getCodiceIdentificativoDelRegistro().toString();
//		this.progressivo = model.getNumeroProgressivoDelRegistro().toString();
		//this.numeroPrimaReg = model.getNumeroPrimaRegistrazioneEffettuataSulRegistro().toString();
		//this.numeroUltimaReg = model.getNumeroUltimaRegistrazioneEffettuataSulRegistro().toString();
		//this.dataPrimaReg = model.getDataPrimaRegistrazioneEffettuataSulProtocollo().toString();
		this.dataUltimaReg =  model.getDataUltimaRegistrazioneEffettuataSulRegistro().toString();
		this.dataPrimaReg=this.dataUltimaReg;
	    }


	public String getIdRegistroProtocollo() {
		return idRegistroProtocollo;
	}

	public void setIdRegistroProtocollo(String idRegistroProtocollo) {
		this.idRegistroProtocollo = idRegistroProtocollo;
	}

	public String getPuntatoreAlfresco() {
		return puntatoreAlfresco;
	}

	public void setPuntatoreAlfresco(String puntatoreAlfresco) {
		this.puntatoreAlfresco = puntatoreAlfresco;
	}

	public String getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(String dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public int getFlagGenerato() {
		return flagGenerato;
	}

	public void setFlagGenerato(int flagGenerato) {
		this.flagGenerato = flagGenerato;
	}

	public String getPathFileSystem() {
		return pathFileSystem;
	}

	public void setPathFileSystem(String pathFileSystem) {
		this.pathFileSystem = pathFileSystem;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}

	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}

	public String getCodiceAmministrazione() {
		return codiceAmministrazione;
	}

	public void setCodiceAmministrazione(String codiceAmministrazione) {
		this.codiceAmministrazione = codiceAmministrazione;
	}

	public String getCodiceAoo() {
		return codiceAoo;
	}

	public void setCodiceAoo(String codiceAoo) {
		this.codiceAoo = codiceAoo;
	}

	public String getSoggettoProduttore2() {
		return soggettoProduttore2;
	}

	public void setSoggettoProduttore2(String soggettoProduttore2) {
		this.soggettoProduttore2 = soggettoProduttore2;
	}

	public String getSoggettoProduttore() {
		return soggettoProduttore;
	}

	public void setSoggettoProduttore(String soggettoProduttore) {
		this.soggettoProduttore = soggettoProduttore;
	}

	public String getResponsabile() {
		return responsabile;
	}

	public void setResponsabile(String responsabile) {
		this.responsabile = responsabile;
	}

	public String getOggetto() {
		return oggetto;
	}

	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}

	public String getCodiceRegistro() {
		return codiceRegistro;
	}

	public void setCodiceRegistro(String codiceRegistro) {
		this.codiceRegistro = codiceRegistro;
	}

	public String getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(String progressivo) {
		this.progressivo = progressivo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getNumeroPrimaReg() {
		return numeroPrimaReg;
	}

	public void setNumeroPrimaReg(String numeroPrimaReg) {
		this.numeroPrimaReg = numeroPrimaReg;
	}

	public String getNumeroUltimaReg() {
		return numeroUltimaReg;
	}

	public void setNumeroUltimaReg(String numeroUltimaReg) {
		this.numeroUltimaReg = numeroUltimaReg;
	}

	public String getDataPrimaReg() {
		return dataPrimaReg;
	}

	public void setDataPrimaReg(String dataPrimaReg) {
		this.dataPrimaReg = dataPrimaReg;
	}

	public String getDataUltimaReg() {
		return dataUltimaReg;
	}

	public void setDataUltimaReg(String dataUltimaReg) {
		this.dataUltimaReg = dataUltimaReg;
	}



}
