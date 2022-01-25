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
@Table(name = "tb_dwh_tempi_medi")
public class DwhTempiMedi extends AbstractPersistable<Long>  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private Long id;
	@Column(name = "data_inizio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_inizio;
	@Column(name = "data_fine")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_fine;
	@Column(name = "tempo_esecuzione")
	private double tempo_esecuzione;
	@Column(name = "cod_servizio")
	private String cod_servizio;
	@Column(name = "nome_servizio")
	private String nome_servizio;
	@Column(name = "cod_user")
	private String cod_user;
	@Column(name = "uuid_operazione")
	private String uuid_operazione;
	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData_inizio() {
		return data_inizio;
	}
	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}
	public Date getData_fine() {
		return data_fine;
	}
	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}
	public double getTempo_esecuzione() {
		return tempo_esecuzione;
	}
	public void setTempo_esecuzione(double tempo_esecuzione) {
		this.tempo_esecuzione = tempo_esecuzione;
	}
	public String getCod_servizio() {
		return cod_servizio;
	}
	public void setCod_servizio(String cod_servizio) {
		this.cod_servizio = cod_servizio;
	}
	public String getNome_servizio() {
		return nome_servizio;
	}
	public void setNome_servizio(String nome_servizio) {
		this.nome_servizio = nome_servizio;
	}
	public String getCod_user() {
		return cod_user;
	}
	public void setCod_user(String cod_user) {
		this.cod_user = cod_user;
	}
	public String getUuid_operazione() {
		return uuid_operazione;
	}
	public void setUuid_operazione(String uuid_operazione) {
		this.uuid_operazione = uuid_operazione;
	}
	
	
	
}
