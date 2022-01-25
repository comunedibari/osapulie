package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//Totale_accessi_ai_servizi_eGov_esposti
public class TotaleAccessiServizi implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String descrizione;
	private Long totale;
	
	public TotaleAccessiServizi() {
		 
	}
	
	public TotaleAccessiServizi(String descrizione, Long totale) {
		super();
		this.descrizione = descrizione;
		this.totale = totale;
	}


	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Long getTotale() {
		return totale;
	}

	public void setTotale(Long totale) {
		this.totale = totale;
	}

 
	
	
}
