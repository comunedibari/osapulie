package it.eng.tz.area.vasta.conservazione.util;

import java.io.Serializable;

public class HttpHeaderUtil implements Serializable {

	private static final long serialVersionUID = -2721770203235254351L;
	private String headerKey;
	private String headerValue;
	
	public HttpHeaderUtil() {
		super();
	}
	
	public HttpHeaderUtil(String headerKey, String headerValue) {
		super();
		this.headerKey = headerKey;
		this.headerValue = headerValue;
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
	
}
