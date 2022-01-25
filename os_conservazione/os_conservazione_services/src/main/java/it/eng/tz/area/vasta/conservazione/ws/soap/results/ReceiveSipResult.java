package it.eng.tz.area.vasta.conservazione.ws.soap.results;

import java.io.Serializable;

import org.springframework.util.StringUtils;

public class ReceiveSipResult implements Serializable {

	private static final long serialVersionUID = 7088387740825289013L;
	private boolean ricevutoErrore;
	private WsSipResultSuccess successo;
	private WsSipResultError errore;
	
	public ReceiveSipResult(WsSipResultSuccess successo, WsSipResultError errore) {
		super();
		if( errore != null && errore.getReceiveSipErrorCode() > 0 && StringUtils.hasText(errore.getReceiveSipErrorMessage()) && successo != null && successo.getSipCode() > 0 && StringUtils.hasText(successo.getSipMessage()) ) {
			
			throw new IllegalArgumentException("Impossibile prosegure. Passato sia errore che successo valorizzati");
		}
		this.successo = successo;
		this.errore = errore;
		if( errore.getReceiveSipErrorCode() > 0 && StringUtils.hasLength(errore.getReceiveSipErrorMessage()) )
		{
			this.ricevutoErrore = true;
		}
	}
	public boolean isRicevutoErrore() {
		return ricevutoErrore;
	}
	public void setRicevutoErrore(boolean ricevutoErrore) {
		this.ricevutoErrore = ricevutoErrore;
	}
	public WsSipResultSuccess getSuccesso() {
		return successo;
	}
	public void setSuccesso(WsSipResultSuccess successo) {
		this.successo = successo;
	}
	public WsSipResultError getErrore() {
		return errore;
	}
	public void setErrore(WsSipResultError errore) {
		this.errore = errore;
	}
	@Override
	public String toString() {
		return "ReceiveSipResult [ricevutoErrore=" + ricevutoErrore + ", successo=" + successo + ", errore=" + errore
				+ "]";
	}
}
