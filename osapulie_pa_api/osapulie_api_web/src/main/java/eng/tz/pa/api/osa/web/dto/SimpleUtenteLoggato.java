package eng.tz.pa.api.osa.web.dto;

import java.io.Serializable;

public class SimpleUtenteLoggato implements Serializable {

	private static final long serialVersionUID = -7474736024150722177L;
	private String nome;
	private String cognome;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	@Override
	public String toString() {
		return "SimpleUtenteLoggato [nome=" + nome + ", cognome=" + cognome + "]";
	}
	
}