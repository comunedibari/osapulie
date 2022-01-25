package it.eng.tz.area.vasta.conservazione.ws.soap.results;

import java.io.Serializable;

public class WsSipResultSuccess implements Serializable {

	private static final long serialVersionUID = 523248035080887601L;
	private String sipId;
	private int sipCode;
	private String sipMessage;
	
	public WsSipResultSuccess() {
		super();
	}
	public WsSipResultSuccess(String sipId, int sipCode, String sipMessage) {
		super();
		this.sipId = sipId;
		this.sipCode = sipCode;
		this.sipMessage = sipMessage;
	}
	public String getSipId() {
		return sipId;
	}
	public void setSipId(String sipId) {
		this.sipId = sipId;
	}
	public int getSipCode() {
		return sipCode;
	}
	public void setSipCode(int sipCode) {
		this.sipCode = sipCode;
	}
	public String getSipMessage() {
		return sipMessage;
	}
	public void setSipMessage(String sipMessage) {
		this.sipMessage = sipMessage;
	}
	@Override
	public String toString() {
		return "WsSipResultSuccess [sipId=" + sipId + ", sipCode=" + sipCode + ", sipMessage=" + sipMessage + "]";
	}	
}