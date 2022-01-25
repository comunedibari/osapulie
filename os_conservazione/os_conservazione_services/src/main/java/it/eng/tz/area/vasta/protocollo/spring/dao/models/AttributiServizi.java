package it.eng.tz.area.vasta.protocollo.spring.dao.models;

import java.io.Serializable;
import java.util.Date;

public class AttributiServizi implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String nomeAttributo;
	private String valoreAttributo;
	private String idTransazione;
	private Date dataCreazione;
	private Long  idServizio;
	
   public AttributiServizi() {
	 
   }	
 
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNomeAttributo() {
		return nomeAttributo;
	}
	public void setNomeAttributo(String nomeAttributo) {
		this.nomeAttributo = nomeAttributo;
	}
	public String getValoreAttributo() {
		return valoreAttributo;
	}
	public void setValoreAttributo(String valoreAttributo) {
		this.valoreAttributo = valoreAttributo;
	}
	public String getIdTransazione() {
		return idTransazione;
	}
	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public Long getIdServizio() {
		return idServizio;
	}
	public void setIdServizio(Long idServizio) {
		this.idServizio = idServizio;
	}
	
}
