package it.eng.tz.area.vasta.conservazione.util;

import java.io.Serializable;

public class SoapHeaderUtil implements Serializable {

	private static final long serialVersionUID = -1395075292399133797L;
	private String headerKey;
	private String headerValue;
	private String headerNameSpace;
	
	public SoapHeaderUtil() {
		super();
	}
	
	public SoapHeaderUtil(String headerKey, String headerValue, String headerNameSpace) {
		super();
		this.headerKey = headerKey;
		this.headerValue = headerValue;
		this.headerNameSpace = headerNameSpace;
	}

	public String getHeaderKey() {
		return headerKey;
	}
	public void setHeaderKey(String headerKey) {
		this.headerKey = headerKey;
	}
	public String getHeaderValue() {
		return headerValue;
	}
	public void setHeaderValue(String headerValue) {
		this.headerValue = headerValue;
	}

	public String getHeaderNameSpace() {
		return headerNameSpace;
	}

	public void setHeaderNameSpace(String headerNameSpace) {
		this.headerNameSpace = headerNameSpace;
	}
}
