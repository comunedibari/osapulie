package it.eng.tz.area.vasta.protocollo.spring.web.dto;

public class Esito{

	private int code;
	private String docId;
	private String message;
	
	public Esito() {
		super();
	}
	public Esito(int code) {
		super();
		this.code = code;
	}
	public Esito(int code, String docId) {
		super();
		this.code = code;
		this.docId = docId; 
	}
	public Esito(int code, String docId, String message) {
		super();
		this.code = code;
		this.docId = docId;
		this.message = message;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
