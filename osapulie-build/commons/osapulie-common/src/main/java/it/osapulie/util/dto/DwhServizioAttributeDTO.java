package it.osapulie.util.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DwhServizioAttributeDTO {

	private Long id; 
	private String uuid;
	private Date data_evento;
	private String comune;
	private String host_app;
	private String servizio_code;
	private String servizio_nome;
	private String cittadino_userid;
	private String cittadino_eta;
	private String cittadino_sesso;
	private String cittadino_comune;
	private String cittadino_provincia;
	private String cittadino_regione;
	private boolean cittadino_autenticazione_forte;
	private int cittadino_livello_autenticazione;
	private String cittadino_canale_autenticazione;
	private String ente_tipo;
	private String ente_partita_iva;
	private String ente_userid;
	private String ente_comune;
	private String ente_provincia;
	private String ente_regione;
	private String servizio_parametro1;
	private String servizio_parametro2;
	private String servizio_parametro3;
	private String servizio_uri;
	private String servizio_protocollo;
	private Date servizio_data_richiesta;
	private boolean servizio_autenticazone;
	private Date servizio_inizio;
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
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DwhServizioAttributeDTO [id=");
		builder.append(id);
		builder.append(", uuid=");
		builder.append(uuid);
		builder.append(", data_evento=");
		builder.append(data_evento);
		builder.append(", comune=");
		builder.append(comune);
		builder.append(", host_app=");
		builder.append(host_app);
		builder.append(", servizio_code=");
		builder.append(servizio_code);
		builder.append(", servizio_nome=");
		builder.append(servizio_nome);
		builder.append(", cittadino_userid=");
		builder.append(cittadino_userid);
		builder.append(", cittadino_eta=");
		builder.append(cittadino_eta);
		builder.append(", cittadino_sesso=");
		builder.append(cittadino_sesso);
		builder.append(", cittadino_comune=");
		builder.append(cittadino_comune);
		builder.append(", cittadino_provincia=");
		builder.append(cittadino_provincia);
		builder.append(", cittadino_regione=");
		builder.append(cittadino_regione);
		builder.append(", cittadino_autenticazione_forte=");
		builder.append(cittadino_autenticazione_forte);
		builder.append(", cittadino_livello_autenticazione=");
		builder.append(cittadino_livello_autenticazione);
		builder.append(", cittadino_canale_autenticazione=");
		builder.append(cittadino_canale_autenticazione);
		builder.append(", ente_tipo=");
		builder.append(ente_tipo);
		builder.append(", ente_partita_iva=");
		builder.append(ente_partita_iva);
		builder.append(", ente_userid=");
		builder.append(ente_userid);
		builder.append(", ente_comune=");
		builder.append(ente_comune);
		builder.append(", ente_provincia=");
		builder.append(ente_provincia);
		builder.append(", ente_regione=");
		builder.append(ente_regione);
		builder.append(", servizio_parametro1=");
		builder.append(servizio_parametro1);
		builder.append(", servizio_parametro2=");
		builder.append(servizio_parametro2);
		builder.append(", servizio_parametro3=");
		builder.append(servizio_parametro3);
		builder.append(", servizio_uri=");
		builder.append(servizio_uri);
		builder.append(", servizio_protocollo=");
		builder.append(servizio_protocollo);
		builder.append(", servizio_data_richiesta=");
		builder.append(servizio_data_richiesta);
		builder.append(", servizio_autenticazone=");
		builder.append(servizio_autenticazone);
		builder.append(", servizio_inizio=");
		builder.append(servizio_inizio);
		builder.append(", servizio_fine=");
		builder.append(servizio_fine);
		builder.append("]");
		return builder.toString();
	}
	
	
}
