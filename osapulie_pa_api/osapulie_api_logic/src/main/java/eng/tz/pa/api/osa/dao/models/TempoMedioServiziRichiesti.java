package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class TempoMedioServiziRichiesti implements Serializable{

	private static final long serialVersionUID = 1L;

	private String tempo_medio;
	private String servizio_code;
	private String servizio_nome;
	
	public TempoMedioServiziRichiesti() {
		 
	}

	 public String getTempo_medio() {
		return tempo_medio;
	}
	 
	 public void setTempo_medio(String tempo_medio) {
		this.tempo_medio = tempo_medio;
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
