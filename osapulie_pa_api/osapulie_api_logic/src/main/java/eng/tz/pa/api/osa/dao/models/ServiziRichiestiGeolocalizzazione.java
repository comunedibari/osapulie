package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class ServiziRichiestiGeolocalizzazione implements Serializable{

	private static final long serialVersionUID = 1L;
//SELECT TOTALE_SERVIZIO,TOTALE,CAP,ROUND((TOTALE/TOTALE_SERVIZIO*100)) as PERCENTUALE_LOCALE,COD_SERVIZIO,SERVIZIO_NOME,CITTA,REGIONE,LATITUDINE,LONGITUDINE,ANNO 
	private String servizio_totale_richieste;
	private String totale_locale;
	private String cap_locale;
	private String percentuale_locale;
	private String servizio_code;
	private String servizio_nome;
	private String citta_locale;
	private String regione_locale;
	private String latitudine;
	private String longitudine;
	private String anno;
	
	
	public String getServizio_totale_richieste() {
		return servizio_totale_richieste;
	}
	public void setServizio_totale_richieste(String servizio_totale_richieste) {
		this.servizio_totale_richieste = servizio_totale_richieste;
	}
	public String getTotale_locale() {
		return totale_locale;
	}
	public void setTotale_locale(String totale_locale) {
		this.totale_locale = totale_locale;
	}
	public String getCap_locale() {
		return cap_locale;
	}
	public void setCap_locale(String cap_locale) {
		this.cap_locale = cap_locale;
	}
	public String getPercentuale_locale() {
		return percentuale_locale;
	}
	public void setPercentuale_locale(String percentuale_locale) {
		this.percentuale_locale = percentuale_locale;
	}
	public String getServizio_code() {
		return servizio_code;
	}
	public void setServizio_code(String servizio_code) {
		this.servizio_code = servizio_code;
	}
	public String getServizio_nome() {
		return servizio_nome;
	}
	public void setServizio_nome(String servizio_nome) {
		this.servizio_nome = servizio_nome;
	}
	public String getCitta_locale() {
		return citta_locale;
	}
	public void setCitta_locale(String citta_locale) {
		this.citta_locale = citta_locale;
	}
	public String getRegione_locale() {
		return regione_locale;
	}
	public void setRegione_locale(String regione_locale) {
		this.regione_locale = regione_locale;
	}
	public String getLatitudine() {
		return latitudine;
	}
	public void setLatitudine(String latitudine) {
		this.latitudine = latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}
	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	
	 
	
	
	
}
