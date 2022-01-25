package it.eng.tz.area.vasta.protocollo.spring.dao.models;

import java.io.Serializable;
import java.util.Date;
 

public class Audit implements Serializable {


	private static final long serialVersionUID = 1L;
 
	private long id;
	
	private String userCod;
	 
	private String giornoMeseAnno;
	 
	private String fileName;
	 
	private String pathFilesystem;
	 
	private String checksum;
 
	private Date dataCreazione;
	 
	private Date dataUltimaModifica;
	 
	private String codiceRegistro;
	 
	private String stato;
	 
	private String cons;
 

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}


	public String getUserCod() {
		return userCod;
	}



	public void setUserCod(String userCod) {
		this.userCod = userCod;
	}



	public String getGiornoMeseAnno() {
		return giornoMeseAnno;
	}



	public void setGiornoMeseAnno(String giornoMeseAnno) {
		this.giornoMeseAnno = giornoMeseAnno;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getPathFilesystem() {
		return pathFilesystem;
	}



	public void setPathFilesystem(String pathFilesystem) {
		this.pathFilesystem = pathFilesystem;
	}



	public String getChecksum() {
		return checksum;
	}



	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}



	public Date getDataCreazione() {
		return dataCreazione;
	}



	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}

	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getCodiceRegistro() {
		return codiceRegistro;
	}


	public void setCodiceRegistro(String codiceRegistro) {
		this.codiceRegistro = codiceRegistro;
	}



	public String getStato() {
		return stato;
	}



	public void setStato(String stato) {
		this.stato = stato;
	}



	public String getCons() {
		return cons;
	}



	public void setCons(String cons) {
		this.cons = cons;
	}


 
	

}
