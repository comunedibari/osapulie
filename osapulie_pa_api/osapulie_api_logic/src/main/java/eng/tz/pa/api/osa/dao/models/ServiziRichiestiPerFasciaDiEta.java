package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class ServiziRichiestiPerFasciaDiEta implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long totale_servizi, 
	eta_ninore_18, 
	percentuale_18, 
	eta_tra_1839, 
	percentuale_1839, 
	eta_tra_4059, 
	percentuale_4059, 
	eta_oltre_60, 
	percentuale_60,
	anno;
	private String servizio_code,
	servizio_nome;
	
	
	
	
	public Long getTotale_servizi() {
		return totale_servizi;
	}
	public void setTotale_servizi(Long totale_servizi) {
		this.totale_servizi = totale_servizi;
	}
	public Long getEta_ninore_18() {
		return eta_ninore_18;
	}
	public void setEta_ninore_18(Long eta_ninore_18) {
		this.eta_ninore_18 = eta_ninore_18;
	}
	public Long getPercentuale_18() {
		return percentuale_18;
	}
	public void setPercentuale_18(Long percentuale_18) {
		this.percentuale_18 = percentuale_18;
	}
	public Long getEta_tra_1839() {
		return eta_tra_1839;
	}
	public void setEta_tra_1839(Long eta_tra_1839) {
		this.eta_tra_1839 = eta_tra_1839;
	}
	public Long getPercentuale_1839() {
		return percentuale_1839;
	}
	public void setPercentuale_1839(Long percentuale_1839) {
		this.percentuale_1839 = percentuale_1839;
	}
	public Long getEta_tra_4059() {
		return eta_tra_4059;
	}
	public void setEta_tra_4059(Long eta_tra_4059) {
		this.eta_tra_4059 = eta_tra_4059;
	}
	public Long getPercentuale_4059() {
		return percentuale_4059;
	}
	public void setPercentuale_4059(Long percentuale_4059) {
		this.percentuale_4059 = percentuale_4059;
	}
	public Long getEta_oltre_60() {
		return eta_oltre_60;
	}
	public void setEta_oltre_60(Long eta_oltre_60) {
		this.eta_oltre_60 = eta_oltre_60;
	}
	public Long getPercentuale_60() {
		return percentuale_60;
	}
	public void setPercentuale_60(Long percentuale_60) {
		this.percentuale_60 = percentuale_60;
	}
	public Long getAnno() {
		return anno;
	}
	public void setAnno(Long anno) {
		this.anno = anno;
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
	 

	
	
	
}
