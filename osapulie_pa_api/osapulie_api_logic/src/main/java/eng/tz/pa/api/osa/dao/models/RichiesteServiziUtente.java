package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//NumeroServiziRichiestiUtente
public class RichiesteServiziUtente implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long totale;
	private String servizio;
	private String user;
	
	public RichiesteServiziUtente() {
		 
	}
	
	public RichiesteServiziUtente(Long totale, String servizio, String user) {
		super();
		this.totale = totale;
		this.servizio = servizio;
		this.user = user;
	}


	public Long getTotale() {
		return totale;
	}
	public void setTotale(Long totale) {
		this.totale = totale;
	}
	public String getServizio() {
		return servizio;
	}
	public void setServizio(String servizio) {
		this.servizio = servizio;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	 
	
	
}
