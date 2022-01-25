package it.osapulie.util;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FirmaGrafometricaPayload {
	
	//Info delegante
	private String cognomeDelegante;
	private String nomeDelegante;
	private String nDocumento;
	private String dataScadenzaDocumento;
	private String rilasciatoDa;
	private String cfDelegante;
	private InputStream firmaDelegante;
	private List<ServiziReportModel> servizi;
		
	//Info delegato
	private String cognomeDelegato;
	private String nomeDelegato;
	private String cfDelegato;
	private String tipoAzienda; //CAF O AZIENDA
	private String denominazione;
	private String cfAzienda;
	private String comuneAzienda;
	private String viaAzienda;
	private String telAzienda;
	private String pecMailAzienda;
	
	
	
	private String token;
	private String url;
	
	
	public String getCognomeDelegante() {
		return cognomeDelegante;
	}
	public void setCognomeDelegante(String cognomeDelegante) {
		this.cognomeDelegante = cognomeDelegante;
	}
	public String getNomeDelegante() {
		return nomeDelegante;
	}
	public void setNomeDelegante(String nomeDelegante) {
		this.nomeDelegante = nomeDelegante;
	}
	public String getnDocumento() {
		return nDocumento;
	}
	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}
	public String getDataScadenzaDocumento() {
		return dataScadenzaDocumento;
	}
	public void setDataScadenzaDocumento(String dataScadenzaDocumento) {
		this.dataScadenzaDocumento = dataScadenzaDocumento;
	}
	public String getRilasciatoDa() {
		return rilasciatoDa;
	}
	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}
	public String getCfDelegante() {
		return cfDelegante;
	}
	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}
	public InputStream getFirmaDelegante() {
		return firmaDelegante;
	}
	public void setFirmaDelegante(InputStream firmaDelegante) {
		this.firmaDelegante = firmaDelegante;
	}
	
	public String getCognomeDelegato() {
		return cognomeDelegato;
	}
	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}
	public String getNomeDelegato() {
		return nomeDelegato;
	}
	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}
	public String getCfDelegato() {
		return cfDelegato;
	}
	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}
	public String getTipoAzienda() {
		return tipoAzienda;
	}
	public void setTipoAzienda(String tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
	}
	public String getDenominazione() {
		return denominazione;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FirmaGrafometricaPayload [cognomeDelegante=");
		builder.append(cognomeDelegante);
		builder.append(", nomeDelegante=");
		builder.append(nomeDelegante);
		builder.append(", nDocumento=");
		builder.append(nDocumento);
		builder.append(", dataScadenzaDocumento=");
		builder.append(dataScadenzaDocumento);
		builder.append(", rilasciatoDa=");
		builder.append(rilasciatoDa);
		builder.append(", cfDelegante=");
		builder.append(cfDelegante);
		builder.append(", firmaDelegante=");
		builder.append(firmaDelegante);
		builder.append(", servizi=");
		builder.append(servizi);
		builder.append(", cognomeDelegato=");
		builder.append(cognomeDelegato);
		builder.append(", nomeDelegato=");
		builder.append(nomeDelegato);
		builder.append(", cfDelegato=");
		builder.append(cfDelegato);
		builder.append(", tipoAzienda=");
		builder.append(tipoAzienda);
		builder.append(", denominazione=");
		builder.append(denominazione);
		builder.append(", cfAzienda=");
		builder.append(cfAzienda);
		builder.append(", comuneAzienda=");
		builder.append(comuneAzienda);
		builder.append(", viaAzienda=");
		builder.append(viaAzienda);
		builder.append(", telAzienda=");
		builder.append(telAzienda);
		builder.append(", pecMailAzienda=");
		builder.append(pecMailAzienda);
		builder.append(", token=");
		builder.append(token);
		builder.append(", url=");
		builder.append(url);
		builder.append("]");
		return builder.toString();
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getCfAzienda() {
		return cfAzienda;
	}
	public void setCfAzienda(String cfAzienda) {
		this.cfAzienda = cfAzienda;
	}
	public String getComuneAzienda() {
		return comuneAzienda;
	}
	public void setComuneAzienda(String comuneAzienda) {
		this.comuneAzienda = comuneAzienda;
	}
	public String getViaAzienda() {
		return viaAzienda;
	}
	public void setViaAzienda(String viaAzienda) {
		this.viaAzienda = viaAzienda;
	}
	public String getTelAzienda() {
		return telAzienda;
	}
	public void setTelAzienda(String telAzienda) {
		this.telAzienda = telAzienda;
	}
	public String getPecMailAzienda() {
		return pecMailAzienda;
	}
	public void setPecMailAzienda(String pecMailAzienda) {
		this.pecMailAzienda = pecMailAzienda;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<ServiziReportModel> getServizi() {
		return servizi;
	}
	public void setServizi(List<ServiziReportModel> servizi) {
		this.servizi = servizi;
	}
	
	
	
	
	
	

}
