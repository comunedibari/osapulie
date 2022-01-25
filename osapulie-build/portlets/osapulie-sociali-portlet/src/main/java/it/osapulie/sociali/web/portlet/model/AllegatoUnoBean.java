package it.osapulie.sociali.web.portlet.model;

import java.util.List;

public class AllegatoUnoBean {
	
	private String nome;
	private String cognome;
	private String comuneNascita;
	private String provinciaNascita;
	private String dataNascita;
	boolean autosufficiente=false;
	boolean parzialmenteAutosuf=false;
	boolean patolTemporanea=false;
	boolean serviziSimiliEnte=false;
	boolean serviziSimiliComune=false;	
	private String serviziComune;
	private String serviziEntiPubblici;
	private List<Parente> parenti;
	private boolean alimenti;
	private boolean appartamentoProprio;
	private String canoneAppartamento;
	
	public String getServiziComune() {
		return serviziComune;
	}
	public String getServiziEntiPubblici() {
		return serviziEntiPubblici;
	}
	public boolean isAlimenti() {
		return alimenti;
	}
	public boolean isAppartamentoProprio() {
		return appartamentoProprio;
	}
	public String getCanoneAppartamento() {
		return canoneAppartamento;
	}
	public void setServiziComune(String serviziComune) {
		this.serviziComune = serviziComune;
	}
	public void setServiziEntiPubblici(String serviziEntiPubblici) {
		this.serviziEntiPubblici = serviziEntiPubblici;
	}
	public void setAlimenti(boolean alimenti) {
		this.alimenti = alimenti;
	}
	public void setAppartamentoProprio(boolean appartamentoProprio) {
		this.appartamentoProprio = appartamentoProprio;
	}
	public void setCanoneAppartamento(String canoneAppartamento) {
		this.canoneAppartamento = canoneAppartamento;
	}
	public List<Parente> getParenti() {
		return parenti;
	}
	public void setParenti(List<Parente> parenti) {
		this.parenti = parenti;
	}
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
	public String getComuneNascita() {
		return comuneNascita;
	}
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}
	public String getProvinciaNascita() {
		return provinciaNascita;
	}
	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}
	public String getDataNascita() {
		return dataNascita;
	}
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}
	public boolean isAutosufficiente() {
		return autosufficiente;
	}
	public void setAutosufficiente(boolean autosufficiente) {
		this.autosufficiente = autosufficiente;
	}
	public boolean isParzialmenteAutosuf() {
		return parzialmenteAutosuf;
	}
	public void setParzialmenteAutosuf(boolean parzialmenteAutosuf) {
		this.parzialmenteAutosuf = parzialmenteAutosuf;
	}
	public boolean isServiziSimiliEnte() {
		return serviziSimiliEnte;
	}
	public void setServiziSimiliEnte(boolean serviziSimiliEnte) {
		this.serviziSimiliEnte = serviziSimiliEnte;
	}
	public boolean isServiziSimiliComune() {
		return serviziSimiliComune;
	}
	public void setServiziSimiliComune(boolean serviziSimiliComune) {
		this.serviziSimiliComune = serviziSimiliComune;
	}
	public boolean isPatolTemporanea() {
		return patolTemporanea;
	}
	public void setPatolTemporanea(boolean patolTemporanea) {
		this.patolTemporanea = patolTemporanea;
	}

}
