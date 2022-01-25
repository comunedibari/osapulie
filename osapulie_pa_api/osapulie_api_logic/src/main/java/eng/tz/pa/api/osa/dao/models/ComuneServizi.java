package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;
//Dettaglio Servizi Attivi Per Comune
public class ComuneServizi implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private String descrizione;
	private String servizio;
	
	public ComuneServizi() {
		 
	}
	
	public ComuneServizi(String nome, String descrizione, String servizio) {
		super();
		this.nome = nome;
		this.descrizione = descrizione;
		this.servizio = servizio;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getServizio() {
		return servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}
	
	
}
