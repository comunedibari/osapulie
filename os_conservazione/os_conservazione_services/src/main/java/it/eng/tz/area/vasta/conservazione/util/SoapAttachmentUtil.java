package it.eng.tz.area.vasta.conservazione.util;

import java.io.Serializable;

import javax.activation.DataHandler;

public class SoapAttachmentUtil implements Serializable {

	private static final long serialVersionUID = 1751497234462221822L;
	private String cid;
	private DataHandler dh;
	
	public SoapAttachmentUtil(String cid, DataHandler dh) {
		super();
		this.cid = cid;
		this.dh = dh;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public DataHandler getDh() {
		return dh;
	}
	public void setDh(DataHandler dh) {
		this.dh = dh;
	}
	
}
