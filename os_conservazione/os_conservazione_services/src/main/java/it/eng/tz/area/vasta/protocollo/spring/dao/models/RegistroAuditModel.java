package it.eng.tz.area.vasta.protocollo.spring.dao.models;

import java.io.Serializable;
import java.util.Date;

public class RegistroAuditModel implements Serializable {

	private static final long serialVersionUID = 6344000579270835604L;
	private int id;
	private String puntatoreAlfresco;
	private Date dataCreazione;
	private String flagGenerato;
	private String pathFileSystem;
	private String fileName;
	private String destinatario;
	private String checksum;
	private String codiceAmministrazione;
	private String codiceAoo;
	private String soggettoProduttore2;
	private String soggettoProduttore;
	private String responsabile;
	private String oggetto;
	private String codiceRegistro;
	private int progressivo;
	private String anno;

	public RegistroAuditModel() {
		 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPuntatoreAlfresco() {
		return puntatoreAlfresco;
	}

	public void setPuntatoreAlfresco(String puntatoreAlfresco) {
		this.puntatoreAlfresco = puntatoreAlfresco;
	}

	public Date getDataCreazione() {
		return dataCreazione;
	}

	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public String getFlagGenerato() {
		return flagGenerato;
	}

	public void setFlagGenerato(String flagGenerato) {
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

	public String getChecksum() {
		return checksum;
	}

	public void setChecksum(String checksum) {
		this.checksum = checksum;
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

	public int getProgressivo() {
		return progressivo;
	}

	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

 
	
}
