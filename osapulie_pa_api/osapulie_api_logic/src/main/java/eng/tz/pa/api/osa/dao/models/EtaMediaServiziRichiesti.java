package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class EtaMediaServiziRichiesti implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String eta;
	private String sesso;
	private String servizio_code;
	private String servizio_nome;
	
	public EtaMediaServiziRichiesti() {
		 
	}

	public String getEta() {
		return eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getServizio_code() {
		return servizio_code;
	}

	public void setServizio_code(String servizio_code) {
		this.servizio_code = servizio_code;
	}

	public String getServizio_nome() {
		return servizio_nome;
	}

	public void setServizio_nome(String servizio_nome) {
		this.servizio_nome = servizio_nome;
	}
	
	 
	
}
