package it.eng.tz.area.vasta.conservazione.ws.soap.results;

import java.io.Serializable;

import org.springframework.util.StringUtils;

import it.eng.tz.area.vasta.conservazione.ws.sip.result.client.SIPResult;

public class ControlloEsitoSipResult implements Serializable {

	private static final long serialVersionUID = 3650948797808138580L;
	private boolean ricevutoErrore;
	private WsControlloEsitoSipResultSuccess successo;
	private WsSipResultError errore;
	private SIPResult sipResult;
	
	public ControlloEsitoSipResult(WsControlloEsitoSipResultSuccess successo, WsSipResultError errore, SIPResult sipResult) {
		super();
		if( errore != null && errore.getReceiveSipErrorCode() > 0 && StringUtils.hasText(errore.getReceiveSipErrorMessage()) && successo != null && successo.getSipCode() > 0 && StringUtils.hasText(successo.getSipMessage()) ) {
			
			throw new IllegalArgumentException("Impossibile prosegure. Passato sia errore che successo valorizzati");
		}
		this.successo = successo;
		this.errore = errore;
		this.sipResult = sipResult;
		if( errore.getReceiveSipErrorCode() > 0 && StringUtils.hasLength(errore.getReceiveSipErrorMessage()) )
		{
			this.ricevutoErrore = true;
		}
	}
	
	public SIPResult getSipResult() {
		return sipResult;
	}

	public void setSipResult(SIPResult sipResult) {
		this.sipResult = sipResult;
	}

	public boolean isRicevutoErrore() {
		return ricevutoErrore;
	}
	public void setRicevutoErrore(boolean ricevutoErrore) {
		this.ricevutoErrore = ricevutoErrore;
	}
	public WsControlloEsitoSipResultSuccess getSuccesso() {
		return successo;
	}
	public void setSuccesso(WsControlloEsitoSipResultSuccess successo) {
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
