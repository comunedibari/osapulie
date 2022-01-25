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
@Table(name = "tb_dwh_servizio_attribute")
public class DwhServizioAttribute extends AbstractPersistable<Long>{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private Long id; 
	@Column(name = "uuid")
	private String uuid;
	@Column(name = "data_evento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_evento;
	@Column(name = "comune")
	private String comune;
	@Column(name = "host_app")
	private String host_app;
	@Column(name = "servizio_code")
	private String servizio_code;
	@Column(name = "servizio_nome")
	private String servizio_nome;
	@Column(name = "cittadino_userid")
	private String cittadino_userid;
	@Column(name = "cittadino_eta")
	private String cittadino_eta;
	@Column(name = "cittadino_sesso")
	private String cittadino_sesso;
	@Column(name = "cittadino_comune")
	private String cittadino_comune;
	@Column(name = "cittadino_provincia")
	private String cittadino_provincia;
	@Column(name = "cittadino_regione")
	private String cittadino_regione;
	@Column(name = "cittadino_autenticazione_forte")
	private boolean cittadino_autenticazione_forte;
	@Column(name = "cittadino_livello_autenticazione")
	private int cittadino_livello_autenticazione;
	@Column(name = "cittadino_canale_autenticazione")
	private String cittadino_canale_autenticazione;
	@Column(name = "ente_tipo")
	private String ente_tipo;
	@Column(name = "ente_partita_iva")
	private String ente_partita_iva;
	@Column(name = "ente_userid")
	private String ente_userid;
	@Column(name = "ente_comune")
	private String ente_comune;
	@Column(name = "ente_provincia")
	private String ente_provincia;
	@Column(name = "ente_regione")
	private String ente_regione;
	@Column(name = "servizio_parametro1")
	private String servizio_parametro1;
	@Column(name = "servizio_parametro2")
	private String servizio_parametro2;
	@Column(name = "servizio_parametro3")
	private String servizio_parametro3;
	@Column(name = "servizio_uri")
	private String servizio_uri;
	@Column(name = "servizio_protocollo")
	private String servizio_protocollo;
	@Column(name = "servizio_data_richiesta")
	@Temporal(TemporalType.TIMESTAMP)
	private Date servizio_data_richiesta;
	@Column(name = "servizio_autenticazone")
	private boolean servizio_autenticazone;
	@Column(name = "servizio_inizio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date servizio_inizio;
	@Column(name = "servizio_fine")
	@Temporal(TemporalType.TIMESTAMP)
	private Date servizio_fine;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public Date getData_evento() {
		return data_evento;
	}
	public void setData_evento(Date data_evento) {
		this.data_evento = data_evento;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getHost_app() {
		return host_app;
	}
	public void setHost_app(String host_app) {
		this.host_app = host_app;
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
	public String getCittadino_userid() {
		return cittadino_userid;
	}
	public void setCittadino_userid(String cittadino_userid) {
		this.cittadino_userid = cittadino_userid;
	}
	public String getCittadino_eta() {
		return cittadino_eta;
	}
	public void setCittadino_eta(String cittadino_eta) {
		this.cittadino_eta = cittadino_eta;
	}
	public String getCittadino_sesso() {
		return cittadino_sesso;
	}
	public void setCittadino_sesso(String cittadino_sesso) {
		this.cittadino_sesso = cittadino_sesso;
	}
	public String getCittadino_comune() {
		return cittadino_comune;
	}
	public void setCittadino_comune(String cittadino_comune) {
		this.cittadino_comune = cittadino_comune;
	}
	public String getCittadino_provincia() {
		return cittadino_provincia;
	}
	public void setCittadino_provincia(String cittadino_provincia) {
		this.cittadino_provincia = cittadino_provincia;
	}
	public String getCittadino_regione() {
		return cittadino_regione;
	}
	public void setCittadino_regione(String cittadino_regione) {
		this.cittadino_regione = cittadino_regione;
	}
	public boolean isCittadino_autenticazione_forte() {
		return cittadino_autenticazione_forte;
	}
	public void setCittadino_autenticazione_forte(boolean cittadino_autenticazione_forte) {
		this.cittadino_autenticazione_forte = cittadino_autenticazione_forte;
	}
	public int getCittadino_livello_autenticazione() {
		return cittadino_livello_autenticazione;
	}
	public void setCittadino_livello_autenticazione(int cittadino_livello_autenticazione) {
		this.cittadino_livello_autenticazione = cittadino_livello_autenticazione;
	}
	public String getCittadino_canale_autenticazione() {
		return cittadino_canale_autenticazione;
	}
	public void setCittadino_canale_autenticazione(String cittadino_canale_autenticazione) {
		this.cittadino_canale_autenticazione = cittadino_canale_autenticazione;
	}
	public String getEnte_tipo() {
		return ente_tipo;
	}
	public void setEnte_tipo(String ente_tipo) {
		this.ente_tipo = ente_tipo;
	}
	public String getEnte_partita_iva() {
		return ente_partita_iva;
	}
	public void setEnte_partita_iva(String ente_partita_iva) {
		this.ente_partita_iva = ente_partita_iva;
	}
	public String getEnte_userid() {
		return ente_userid;
	}
	public void setEnte_userid(String ente_userid) {
		this.ente_userid = ente_userid;
	}
	public String getEnte_comune() {
		return ente_comune;
	}
	public void setEnte_comune(String ente_comune) {
		this.ente_comune = ente_comune;
	}
	public String getEnte_provincia() {
		return ente_provincia;
	}
	public void setEnte_provincia(String ente_provincia) {
		this.ente_provincia = ente_provincia;
	}
	public String getEnte_regione() {
		return ente_regione;
	}
	public void setEnte_regione(String ente_regione) {
		this.ente_regione = ente_regione;
	}
	public String getServizio_parametro1() {
		return servizio_parametro1;
	}
	public void setServizio_parametro1(String servizio_parametro1) {
		this.servizio_parametro1 = servizio_parametro1;
	}
	public String getServizio_parametro2() {
		return servizio_parametro2;
	}
	public void setServizio_parametro2(String servizio_parametro2) {
		this.servizio_parametro2 = servizio_parametro2;
	}
	public String getServizio_parametro3() {
		return servizio_parametro3;
	}
	public void setServizio_parametro3(String servizio_parametro3) {
		this.servizio_parametro3 = servizio_parametro3;
	}
	public String getServizio_uri() {
		return servizio_uri;
	}
	public void setServizio_uri(String servizio_uri) {
		this.servizio_uri = servizio_uri;
	}
	public String getServizio_protocollo() {
		return servizio_protocollo;
	}
	public void setServizio_protocollo(String servizio_protocollo) {
		this.servizio_protocollo = servizio_protocollo;
	}
	public Date getServizio_data_richiesta() {
		return servizio_data_richiesta;
	}
	public void setServizio_data_richiesta(Date servizio_data_richiesta) {
		this.servizio_data_richiesta = servizio_data_richiesta;
	}
	public boolean isServizio_autenticazone() {
		return servizio_autenticazone;
	}
	public void setServizio_autenticazone(boolean servizio_autenticazone) {
		this.servizio_autenticazone = servizio_autenticazone;
	}
	public Date getServizio_inizio() {
		return servizio_inizio;
	}
	public void setServizio_inizio(Date servizio_inizio) {
		this.servizio_inizio = servizio_inizio;
	}
	public Date getServizio_fine() {
		return servizio_fine;
	}
	public void setServizio_fine(Date servizio_fine) {
		this.servizio_fine = servizio_fine;
	}
	
	
}
