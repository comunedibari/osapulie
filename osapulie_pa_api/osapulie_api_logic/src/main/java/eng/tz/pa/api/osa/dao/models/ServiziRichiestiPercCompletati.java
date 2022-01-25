package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class ServiziRichiestiPercCompletati implements Serializable{

	private static final long serialVersionUID = 1L;

	private String totale_richieste;
	private String completati;
	private String incompleti;
	private String percentuale_completamento;
	private String servizio_nome;
	private String servizio_code;
	
	
	
	public String getTotale_richieste() {
		return totale_richieste;
	}
	public void setTotale_richieste(String totale_richieste) {
		this.totale_richieste = totale_richieste;
	}
	public String getCompletati() {
		return completati;
	}
	public void setCompletati(String completati) {
		this.completati = completati;
	}
	public String getIncompleti() {
		return incompleti;
	}
	public void setIncompleti(String incompleti) {
		this.incompleti = incompleti;
	}
	public String getPercentuale_completamento() {
		return percentuale_completamento;
	}
	public void setPercentuale_completamento(String percentuale_completamento) {
		this.percentuale_completamento = percentuale_completamento;
	}
	public String getServizio_nome() {
		return servizio_nome;
	}
	public void setServizio_nome(String servizio_nome) {
		this.servizio_nome = servizio_nome;
	}
	public String getServizio_code() {
		return servizio_code;
	}
	public void setServizio_code(String servizio_code) {
		this.servizio_code = servizio_code;
	}
	
	
	
	
}
