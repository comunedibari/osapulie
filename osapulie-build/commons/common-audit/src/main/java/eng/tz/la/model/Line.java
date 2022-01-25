/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eng.tz.la.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import eng.tz.la.core.IMetaActor;

/**
 *
 * @author s.mariniello
 */
public class Line<T>  implements Serializable {

	private static final long serialVersionUID = -4701530830771819006L;

	private String timeString;
	private Date time;
	private IMetaActor metaActor;
	private String inizioOperazione;
	private String fineOperazione;
	private String esito;
	private ExternalAuditUser auditUser;
	private MetaInfo metaInfo;
	private T metaField;
	private String uuidOperazione;
	private AttributiServizio attrServizio;
	
	public Line() {
		
	}

	public Line(T t) {
		this.metaField = t;
		this.time = new Date();
		this.timeString=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(time);
	}
 
	public T getMetaField() {
		return metaField;
	}

	public void setT(T t) {
		this.metaField = t;
		this.time = new Date();
		this.timeString=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(time);
	}

	public void setMetaActor(IMetaActor metaActor) {
		this.metaActor = metaActor;
	}
	
	public IMetaActor getMetaActor() {
		return metaActor;
	}
	
	public void setAuditUser(ExternalAuditUser auditUser) {
		this.auditUser = auditUser;
	}
	
	public ExternalAuditUser getAuditUser() {
		return auditUser;
	}
	
	public void setMetaInfo(MetaInfo metaInfo) {
		this.metaInfo = metaInfo;
	}
	
	public MetaInfo getMetaInfo() {
		return metaInfo;
	}
	
	public Date getTime() {
		return time;
	}
	
	public void setTime(Date time) {
		this.time = time;
	}
	
	public String getTimeString() {
		return timeString;
	}
	
	 public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
	 public void setInizioOperazione(String inizioOperazione) {
		this.inizioOperazione = inizioOperazione;
	}
	 public String getInizioOperazione() {
		return inizioOperazione;
	}
	 
	 public void setFineOperazione(String fineOperazione) {
		this.fineOperazione = fineOperazione;
	}
	 public String getFineOperazione() {
		return fineOperazione;
	}
	 
	public void setUuidOperazione(String uuidOperazione) {
		this.uuidOperazione = uuidOperazione;
	}
	
	public String getUuidOperazione() {
		return uuidOperazione;
	}
	
	public String getEsito() {
		return esito;
	}
	
	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	public AttributiServizio getAttrServizio() {
		return attrServizio;
	}
	
	public void setAttrServizio(AttributiServizio attrServizio) {
		this.attrServizio = attrServizio;
	}
	
	@Override
	public String toString() {
		//return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss.SSS").format(time) + " " +(auditMeta!=null ?auditMeta.toPrint():" ") + " " +(metaInfo!=null ?metaInfo.toString():" ")+ (t != null ? t.toString() : "-");
		return (timeString!=null ?timeString:"") + " " +(uuidOperazione!=null ?("uuid=> "+uuidOperazione+" "):"") + "" +(metaActor!=null ?(metaActor.toPrint()+" "):"") + "" +(metaInfo!=null ?(metaInfo.toString()+" "):"")+ (metaField != null ? (metaField.toString()+" ") : "")+ "" +(inizioOperazione!=null ?("inizioOperazione=> "+inizioOperazione+" "):"") + "" +(fineOperazione!=null ?("fineOperazione=> "+fineOperazione):"") + "" +(attrServizio!=null ? attrServizio.toString():"");

	}

}
