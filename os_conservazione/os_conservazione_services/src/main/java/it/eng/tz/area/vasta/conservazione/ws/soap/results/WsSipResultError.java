package it.eng.tz.area.vasta.conservazione.ws.soap.results;

import java.io.Serializable;

public class WsSipResultError implements Serializable {

	private static final long serialVersionUID = 523248035080887601L;
	private int receiveSipErrorCode;
	private String receiveSipErrorMessage;
	
	public WsSipResultError() {
		super();
	}
	public WsSipResultError(int receiveSipErrorCode, String receiveSipErrorMessage) {
		super();
		this.receiveSipErrorCode = receiveSipErrorCode;
		this.receiveSipErrorMessage = receiveSipErrorMessage;
	}
	public int getReceiveSipErrorCode() {
		return receiveSipErrorCode;
	}
	public void setReceiveSipErrorCode(int receiveSipErrorCode) {
		this.receiveSipErrorCode = receiveSipErrorCode;
	}
	public String getReceiveSipErrorMessage() {
		return receiveSipErrorMessage;
	}
	public void setReceiveSipErrorMessage(String receiveSipErrorMessage) {
		this.receiveSipErrorMessage = receiveSipErrorMessage;
	}
	@Override
	public String toString() {
		return "ReceiveSipResultError [receiveSipErrorCode=" + receiveSipErrorCode + ", receiveSipErrorMessage="
				+ receiveSipErrorMessage + "]";
	}	
}