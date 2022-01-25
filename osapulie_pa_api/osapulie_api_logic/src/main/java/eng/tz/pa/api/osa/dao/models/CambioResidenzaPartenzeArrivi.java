package eng.tz.pa.api.osa.dao.models;

import java.io.Serializable;

public class CambioResidenzaPartenzeArrivi implements Serializable{

	private static final long serialVersionUID = 1L;
	 
	private Long totale;
	private String comune_partenza;
	private String cod_istat_partenza;
	private String comune_arrivo;
	private String cod_istat_arrivo;
	
	public CambioResidenzaPartenzeArrivi() {
		 
	}

	public Long getTotale() {
		return totale;
	}

	public void setTotale(Long totale) {
		this.totale = totale;
	}

	public String getComune_partenza() {
		return comune_partenza;
	}

	public void setComune_partenza(String comune_partenza) {
		this.comune_partenza = comune_partenza;
	}

	public String getCod_istat_partenza() {
		return cod_istat_partenza;
	}

	public void setCod_istat_partenza(String cod_istat_partenza) {
		this.cod_istat_partenza = cod_istat_partenza;
	}

	public String getComune_arrivo() {
		return comune_arrivo;
	}

	public void setComune_arrivo(String comune_arrivo) {
		this.comune_arrivo = comune_arrivo;
	}

	public String getCod_istat_arrivo() {
		return cod_istat_arrivo;
	}

	public void setCod_istat_arrivo(String cod_istat_arrivo) {
		this.cod_istat_arrivo = cod_istat_arrivo;
	}


 
	
 

 
	
	 
	
	
}
