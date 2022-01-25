package it.eng.tz.area.vasta.conservazione.ws.soap.results;

public class WsControlloEsitoSipResultSuccess extends WsSipResultSuccess {

	private static final long serialVersionUID = 2742594513061219223L;
	private String statoSip;
	public WsControlloEsitoSipResultSuccess() {
		
	}

	public String getStatoSip() {
		return statoSip;
	}

	public void setStatoSip(String statoSip) {
		this.statoSip = statoSip;
	}

	@Override
	public String toString() {
		return "WsControlloEsitoSipResultSuccess [statoSip=" + statoSip + ", getSipId()=" + getSipId()
				+ ", getSipCode()=" + getSipCode() + ", getSipMessage()=" + getSipMessage() + "]";
	}
}