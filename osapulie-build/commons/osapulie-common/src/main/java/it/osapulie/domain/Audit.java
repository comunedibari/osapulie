package it.osapulie.domain;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.data.jpa.domain.AbstractPersistable;


@Entity
@Table(name = "tb_audit")
public class Audit extends AbstractPersistable<Long> {


	private static final long serialVersionUID = 1L;
	@Column(name = "userCod", nullable = true)
	private String userCod;
	@Column(name = "giorno_mese_anno", nullable = true)
	private String giornoMeseAnno;
	@Column(name = "file_name", nullable = true)
	private String fileName;
	@Column(name = "path_filesystem", nullable = true)
	private String pathFilesystem;
	@Column(name = "checksum", nullable = true)
	private String checksum;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_creazione", nullable = false)
	private Date dataCreazione;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_ultima_modifica", nullable = true)
	private Date dataUltimaModifica;
	@Column(name = "codice_registro", nullable = true)
	private String codiceRegistro;
	@Column(name = "stato", nullable = true)
	private String stato;
	@Column(name = "cons", nullable = true)
	private String cons;
	
 
	
	@Override
	public void setId(Long id) {
		super.setId(id);
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
