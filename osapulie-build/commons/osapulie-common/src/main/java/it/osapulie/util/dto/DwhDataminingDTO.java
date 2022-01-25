package it.osapulie.util.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwhDataminingDTO {

	
	private Long id;
	private int eta_richiedente;
	private String sesso;
	private String cod_servizio;
	private String cod_cittadino;
	private Date data_richiesta;
	private String uuid_operazione;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getEta_richiedente() {
		return eta_richiedente;
	}
	public void setEta_richiedente(int eta_richiedente) {
		this.eta_richiedente = eta_richiedente;
	}
	public String getSesso() {
		return sesso;
	}
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}
	public String getCod_servizio() {
		return cod_servizio;
	}
	public void setCod_servizio(String cod_servizio) {
		this.cod_servizio = cod_servizio;
	}
	public String getCod_cittadino() {
		return cod_cittadino;
	}
	public void setCod_cittadino(String cod_cittadino) {
		this.cod_cittadino = cod_cittadino;
	}
	public Date getData_richiesta() {
		return data_richiesta;
	}
	public void setData_richiesta(Date data_richiesta) {
		this.data_richiesta = data_richiesta;
	}
	public String getUuid_operazione() {
		return uuid_operazione;
	}
	public void setUuid_operazione(String uuid_operazione) {
		this.uuid_operazione = uuid_operazione;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DwhDataminingDTO [id=");
		builder.append(id);
		builder.append(", eta_richiedente=");
		builder.append(eta_richiedente);
		builder.append(", sesso=");
		builder.append(sesso);
		builder.append(", cod_servizio=");
		builder.append(cod_servizio);
		builder.append(", cod_cittadino=");
		builder.append(cod_cittadino);
		builder.append(", data_richiesta=");
		builder.append(data_richiesta);
		builder.append(", uuid_operazione=");
		builder.append(uuid_operazione);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
