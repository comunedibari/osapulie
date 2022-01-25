package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//Totale_Servizi_Attivati_Per_Comune
public class TotaleServiziComune implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String descrizione;
	private Long totale;
	
	public TotaleServiziComune() {
		 
	}
	
	public TotaleServiziComune(String descrizione, Long totale) {
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
