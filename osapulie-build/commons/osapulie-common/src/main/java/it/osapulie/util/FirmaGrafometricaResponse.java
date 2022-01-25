package it.osapulie.util;

import java.io.File;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class FirmaGrafometricaResponse {

	
	public String esito;
	public String errorMessage;
	public String errorCode;
	public String token;
	public String url;
	public byte[] fileDownload;
	
	
	public String getEsito() {
		return esito;
	}
	public void setEsito(String esito) {
		this.esito = esito;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public byte[] getFileDownload() {
		return fileDownload;
	}
	public void setFileDownload(byte[] fileDownload) {
		this.fileDownload = fileDownload;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FirmaGrafometricaResponse [esito=");
		builder.append(esito);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", token=");
		builder.append(token);
		builder.append(", url=");
		builder.append(url);
		builder.append(", fileDownload=");
		builder.append(fileDownload);
		builder.append("]");
		return builder.toString();
	}

	
}
