package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//Dettaglio Servizi Richiesti Per Data
public class TotaleAccessiServiziPerData implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String data;
	private String nome_servizio;
	private Long totale;
	
	public TotaleAccessiServiziPerData() {
		 
	}
	
	public TotaleAccessiServiziPerData(String data,String nome_servizio, Long totale) {
		super();
		this.data=data;
		this.nome_servizio = nome_servizio;
		this.totale = totale;
	}

	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}

	public String getNome_Servizio() {
		return nome_servizio;
	}

	public void setNome_Servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}

	public Long getTotale() {
		return totale;
	}

	public void setTotale(Long totale) {
		this.totale = totale;
	}

 
	
	
}
