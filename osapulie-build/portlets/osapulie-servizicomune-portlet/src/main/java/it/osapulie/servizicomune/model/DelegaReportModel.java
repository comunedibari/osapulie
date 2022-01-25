package it.osapulie.servizicomune.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;


/**
 * 
 * @author sdefe
 *
 */
public class DelegaReportModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Info delegante
	private String cognomeDelegante;
	private String nomeDelegante;
	private String nDocumento;
	private String dataScadenzaDocumento;
	private String rilasciatoDa;
	private String cfDelegante;
	private InputStream firmaDelegante;
	private List<ServiziAssociatiReportModel> servizi;
	
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
	/**
	 * @return the cognomeDelegante
	 */
	public String getCognomeDelegante() {
		return cognomeDelegante;
	}
	/**
	 * @param cognomeDelegante the cognomeDelegante to set
	 */
	public void setCognomeDelegante(String cognomeDelegante) {
		this.cognomeDelegante = cognomeDelegante;
	}
	/**
	 * @return the nomeDelegante
	 */
	public String getNomeDelegante() {
		return nomeDelegante;
	}
	/**
	 * @param nomeDelegante the nomeDelegante to set
	 */
	public void setNomeDelegante(String nomeDelegante) {
		this.nomeDelegante = nomeDelegante;
	}
	/**
	 * @return the cfDelegante
	 */
	public String getCfDelegante() {
		return cfDelegante;
	}
	/**
	 * @param cfDelegante the cfDelegante to set
	 */
	public void setCfDelegante(String cfDelegante) {
		this.cfDelegante = cfDelegante;
	}
	/**
	 * @return the firmaDelegante
	 */
	public InputStream getFirmaDelegante() {
		return firmaDelegante;
	}
	/**
	 * @param firmaDelegante the firmaDelegante to set
	 */
	public void setFirmaDelegante(InputStream firmaDelegante) {
		this.firmaDelegante = firmaDelegante;
	}
	/**
	 * @return the cognomeDelegato
	 */
	public String getCognomeDelegato() {
		return cognomeDelegato;
	}
	/**
	 * @param cognomeDelegato the cognomeDelegato to set
	 */
	public void setCognomeDelegato(String cognomeDelegato) {
		this.cognomeDelegato = cognomeDelegato;
	}
	/**
	 * @return the nomeDelegato
	 */
	public String getNomeDelegato() {
		return nomeDelegato;
	}
	/**
	 * @param nomeDelegato the nomeDelegato to set
	 */
	public void setNomeDelegato(String nomeDelegato) {
		this.nomeDelegato = nomeDelegato;
	}
	/**
	 * @return the cfDelegato
	 */
	public String getCfDelegato() {
		return cfDelegato;
	}
	/**
	 * @param cfDelegato the cfDelegato to set
	 */
	public void setCfDelegato(String cfDelegato) {
		this.cfDelegato = cfDelegato;
	}
	/**
	 * @return the tipoAzienda
	 */
	public String getTipoAzienda() {
		return tipoAzienda;
	}
	/**
	 * @param tipoAzienda the tipoAzienda to set
	 */
	public void setTipoAzienda(String tipoAzienda) {
		this.tipoAzienda = tipoAzienda;
	}
	/**
	 * @return the denominazione
	 */
	public String getDenominazione() {
		return denominazione;
	}
	/**
	 * @param denominazione the denominazione to set
	 */
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	/**
	 * @return the cfAzienda
	 */
	public String getCfAzienda() {
		return cfAzienda;
	}
	/**
	 * @param cfAzienda the cfAzienda to set
	 */
	public void setCfAzienda(String cfAzienda) {
		this.cfAzienda = cfAzienda;
	}
	/**
	 * @return the comuneAzienda
	 */
	public String getComuneAzienda() {
		return comuneAzienda;
	}
	/**
	 * @param comuneAzienda the comuneAzienda to set
	 */
	public void setComuneAzienda(String comuneAzienda) {
		this.comuneAzienda = comuneAzienda;
	}
	/**
	 * @return the viaAzienda
	 */
	public String getViaAzienda() {
		return viaAzienda;
	}
	/**
	 * @param viaAzienda the viaAzienda to set
	 */
	public void setViaAzienda(String viaAzienda) {
		this.viaAzienda = viaAzienda;
	}
	/**
	 * @return the telAzienda
	 */
	public String getTelAzienda() {
		return telAzienda;
	}
	/**
	 * @param telAzienda the telAzienda to set
	 */
	public void setTelAzienda(String telAzienda) {
		this.telAzienda = telAzienda;
	}
	/**
	 * @return the pecMailAzienda
	 */
	public String getPecMailAzienda() {
		return pecMailAzienda;
	}
	/**
	 * @param pecMailAzienda the pecMailAzienda to set
	 */
	public void setPecMailAzienda(String pecMailAzienda) {
		this.pecMailAzienda = pecMailAzienda;
	}
	/**
	 * @return the nDocumento
	 */
	public String getnDocumento() {
		return nDocumento;
	}
	/**
	 * @param nDocumento the nDocumento to set
	 */
	public void setnDocumento(String nDocumento) {
		this.nDocumento = nDocumento;
	}
	/**
	 * @return the dataScadenzaDocumento
	 */
	public String getDataScadenzaDocumento() {
		return dataScadenzaDocumento;
	}
	/**
	 * @param dataScadenzaDocumento the dataScadenzaDocumento to set
	 */
	public void setDataScadenzaDocumento(String dataScadenzaDocumento) {
		this.dataScadenzaDocumento = dataScadenzaDocumento;
	}
	/**
	 * @return the rilasciatoDa
	 */
	public String getRilasciatoDa() {
		return rilasciatoDa;
	}
	/**
	 * @param rilasciatoDa the rilasciatoDa to set
	 */
	public void setRilasciatoDa(String rilasciatoDa) {
		this.rilasciatoDa = rilasciatoDa;
	}
	/**
	 * @return the servizi
	 */
	public List<ServiziAssociatiReportModel> getServizi() {
		return servizi;
	}
	/**
	 * @param servizi the servizi to set
	 */
	public void setServizi(List<ServiziAssociatiReportModel> servizi) {
		this.servizi = servizi;
	}
	
	
}
