package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class Servizio implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String servizio_id;
	private String servizio_code;
	private String servizio_nome;
	
	
	public String getServizio_id() {
		return servizio_id;
	}
	public void setServizio_id(String servizio_id) {
		this.servizio_id = servizio_id;
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
