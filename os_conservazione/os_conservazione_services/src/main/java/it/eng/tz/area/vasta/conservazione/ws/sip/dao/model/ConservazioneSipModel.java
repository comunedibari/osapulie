package it.eng.tz.area.vasta.conservazione.ws.sip.dao.model;

import java.io.Serializable;
import java.util.Date;

public class ConservazioneSipModel implements Serializable {

	private static final long serialVersionUID = -1666439696699702994L;
	private String id;
	private boolean processato;
	private boolean errore;
	private String sipResultXml;
	private Date dataProcessamento;
	private String folder;
	
	public ConservazioneSipModel(String id, boolean processato, boolean errore, String sipResultXml,
			Date dataProcessamento,String folder) {
		super();
		this.id = id;
		this.processato = processato;
		this.errore = errore;
		this.sipResultXml = sipResultXml;
		this.dataProcessamento = dataProcessamento;
		this.folder = folder;
	}
	public ConservazioneSipModel() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public boolean isProcessato() {
		return processato;
	}
	public void setProcessato(boolean processato) {
		this.processato = processato;
	}
	public boolean isErrore() {
		return errore;
	}
	public void setErrore(boolean errore) {
		this.errore = errore;
	}
	public String getSipResultXml() {
		return sipResultXml;
	}
	public void setSipResultXml(String sipResultXml) {
		this.sipResultXml = sipResultXml;
	}
	public Date getDataProcessamento() {
		return dataProcessamento;
	}
	public void setDataProcessamento(Date dataProcessamento) {
		this.dataProcessamento = dataProcessamento;
	}
	
	public String getFolder() {
		return folder;
	}
	
	public void setFolder(String folder) {
		this.folder = folder;
	}
	
	@Override
	public String toString() {
		return "ConservazioneSipModel [id=" + id + ", processato=" + processato + ", errore=" + errore
				+ ", sipResultXml=" + sipResultXml + ", dataProcessamento=" + dataProcessamento + ", folder= "+ folder + "]";
	}
}