package it.osapulie.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "tb_dwh_datamining")
public class DwhDatamining extends AbstractPersistable<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column(name = "eta_richiedente")
	private int eta_richiedente;
	@Column(name = "sesso")
	private String sesso;
	@Column(name = "cod_servizio")
	private String cod_servizio;
	@Column(name = "cod_cittadino")
	private String cod_cittadino;
	@Column(name = "data_richiesta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_richiesta;
	@Column(name = "uuid_operazione")
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

	
	
	
}
