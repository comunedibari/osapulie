package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//NumeroServiziRichiestiUtente
public class RichiesteServiziPerAzienda implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long totale; 
	private String data_richiesta;
	private String servizio;
	private String ragione_sociale;
	private String piva;
	
	public RichiesteServiziPerAzienda() {
		 
	}

	public Long getTotale() {
		return totale;
	}

	public void setTotale(Long totale) {
		this.totale = totale;
	}

	public String getData_richiesta() {
		return data_richiesta;
	}

	public void setData_richiesta(String data_richiesta) {
		this.data_richiesta = data_richiesta;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public String getRagione_sociale() {
		return ragione_sociale;
	}

	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}

	public String getPiva() {
		return piva;
	}

	public void setPiva(String piva) {
		this.piva = piva;
	}

 
	
 

 
	
	 
	
	
}
