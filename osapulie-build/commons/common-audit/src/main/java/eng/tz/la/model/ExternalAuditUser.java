package eng.tz.la.model;

import java.io.Serializable;

public class ExternalAuditUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String timeString;
	private String origin;
	private String actor;
	private String operazioneRichiesta;
	private String urlRichiesta;
	private String paginaCorrente;
	private String numeroProcollo;
	private String improntaDelega;
	private String esito;
	private String uuid;
	private String firmaGrafoAcquisita;
	private String delegante;
	
	public ExternalAuditUser() {
		 
	}


	public String getTimeString() {
		return timeString;
	}


	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}


	public String getOrigin() {
		return origin;
	}


	public void setOrigin(String origin) {
		this.origin = origin;
	}


	public String getActor() {
		return actor;
	}


	public void setActor(String actor) {
		this.actor = actor;
	}


	public String getOperazioneRichiesta() {
		return operazioneRichiesta;
	}


	public void setOperazioneRichiesta(String operazioneRichiesta) {
		this.operazioneRichiesta = operazioneRichiesta;
	}


	public String getUrlRichiesta() {
		return urlRichiesta;
	}


	public void setUrlRichiesta(String urlRichiesta) {
		this.urlRichiesta = urlRichiesta;
	}


	public String getPaginaCorrente() {
		return paginaCorrente;
	}


	public void setPaginaCorrente(String paginaCorrente) {
		this.paginaCorrente = paginaCorrente;
	}


	public String getNumeroProcollo() {
		return numeroProcollo;
	}


	public void setNumeroProcollo(String numeroProcollo) {
		this.numeroProcollo = numeroProcollo;
	}


	public String getImprontaDelega() {
		return improntaDelega;
	}


	public void setImprontaDelega(String improntaDelega) {
		this.improntaDelega = improntaDelega;
	}


	public String getEsito() {
		return esito;
	}


	public void setEsito(String esito) {
		this.esito = esito;
	}
	
	public String getUuid() {
		return uuid;
	}
	
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
	public String getFirmaGrafoAcquisita() {
		return firmaGrafoAcquisita;
	}
	
	public void setFirmaGrafoAcquisita(String firmaGrafoAcquisita) {
		this.firmaGrafoAcquisita = firmaGrafoAcquisita;
	}
	
	public void setDelegante(String delegante) {
		this.delegante = delegante;
	}
	
	public String getDelegante() {
		return delegante;
	}


	@Override
	public String toString() {
		return "AuditUser [timeString=" + timeString + ", origin=" + origin + ", actor=" + actor
				+ ", operazioneRichiesta=" + operazioneRichiesta + ", urlRichiesta=" + urlRichiesta
				+ ", paginaCorrente=" + paginaCorrente + ", numeroProcollo=" + numeroProcollo + ", improntaDelega="
				+ improntaDelega + ", esito=" + esito + ", uuid=" + uuid + ", firmaGrafoAcquisita="
				+ firmaGrafoAcquisita + ", delegante=" + delegante + "]";
	}
	
	
}
