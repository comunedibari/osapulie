package it.osapulie.util;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwhAuditResponse {

	public String esito;
	public String errorMessage;
	public String errorCode;
	public String uuid;
	
	
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
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DwhAuditResponse [esito=");
		builder.append(esito);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
