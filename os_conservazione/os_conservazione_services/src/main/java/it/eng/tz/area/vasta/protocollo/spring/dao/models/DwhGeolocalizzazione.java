package it.eng.tz.area.vasta.protocollo.spring.dao.models;

import java.io.Serializable;
import java.util.Date;

 
public class DwhGeolocalizzazione  implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
 
	private String indirizzo_ip;
 
	private String latitudine;
 
	private String longitudine;
 
	private String citta;
 
	private String regione;
 
	private String cap;
 
	private boolean is_europeo;
 
	private String cod_servizio;
 
	private String cod_user;
 
	private Date data_creazione;
 
	private String uuid_operazione;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIndirizzo_ip() {
		return indirizzo_ip;
	}
	public void setIndirizzo_ip(String indirizzo_ip) {
		this.indirizzo_ip = indirizzo_ip;
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
	public String getCitta() {
		return citta;
	}
	public void setCitta(String citta) {
		this.citta = citta;
	}
	public String getRegione() {
		return regione;
	}
	public void setRegione(String regione) {
		this.regione = regione;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public boolean isIs_europeo() {
		return is_europeo;
	}
	public void setIs_europeo(boolean is_europeo) {
		this.is_europeo = is_europeo;
	}
	public String getCod_servizio() {
		return cod_servizio;
	}
	public void setCod_servizio(String cod_servizio) {
		this.cod_servizio = cod_servizio;
	}
	public String getCod_user() {
		return cod_user;
	}
	public void setCod_user(String cod_user) {
		this.cod_user = cod_user;
	}
	public Date getData_creazione() {
		return data_creazione;
	}
	public void setData_creazione(Date data_creazione) {
		this.data_creazione = data_creazione;
	}
	public String getUuid_operazione() {
		return uuid_operazione;
	}
	public void setUuid_operazione(String uuid_operazione) {
		this.uuid_operazione = uuid_operazione;
	}

	
	
}
