package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;
import java.util.List;

public class DatiAnzianiAssistenza implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cf;
	private String nome;
	private String cognome;
	private String dataNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String comuneResidenza;
	private String provinciaResidenza;
	private String civicoResidenza;
	private String indirizzoResidenza;
	private String codTesseraSanitaria;
	private String telefono;
	
	//tipoAffine: può essere "parente" o "vicino"
//	private String tipoAffine;
	private String telefonoAffine;
	private String cognomeAffine;
	private String nomeAffine;
	private String viaAffine;
	private String civicoAffine;
	
	private boolean etaPensionabile;
	private boolean coniugato;
	private boolean serviziSimiliComune;
	private boolean serviziSimiliEnte;
	
	private String motiviConiugato;
	private String serviziComune;
	private String serviziEntiPubblici;
	
	private List<Parente> parenti;

	public String getCf() {
		return cf;
	}

	public void setCf(String cf) {
		this.cf = cf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getCodTesseraSanitaria() {
		return codTesseraSanitaria;
	}

	public void setCodTesseraSanitaria(String codTesseraSanitaria) {
		this.codTesseraSanitaria = codTesseraSanitaria;
	}

//	public String getTipoAffine() {
//		return tipoAffine;
//	}
//
//	public void setTipoAffine(String tipoAffine) {
//		this.tipoAffine = tipoAffine;
//	}

	public String getTelefonoAffine() {
		return telefonoAffine;
	}

	public void setTelefonoAffine(String telefonoAffine) {
		this.telefonoAffine = telefonoAffine;
	}

	public String getCognomeAffine() {
		return cognomeAffine;
	}

	public void setCognomeAffine(String cognomeAffine) {
		this.cognomeAffine = cognomeAffine;
	}

	public String getNomeAffine() {
		return nomeAffine;
	}

	public void setNomeAffine(String nomeAffine) {
		this.nomeAffine = nomeAffine;
	}

	public String getViaAffine() {
		return viaAffine;
	}

	public void setViaAffine(String viaAffine) {
		this.viaAffine = viaAffine;
	}

	public String getCivicoAffine() {
		return civicoAffine;
	}

	public void setCivicoAffine(String civicoAffine) {
		this.civicoAffine = civicoAffine;
	}

	public boolean isEtaPensionabile() {
		return etaPensionabile;
	}

	public void setEtaPensionabile(boolean etaPensionabile) {
		this.etaPensionabile = etaPensionabile;
	}

	public boolean isConiugato() {
		return coniugato;
	}

	public void setConiugato(boolean coniugato) {
		this.coniugato = coniugato;
	}

	public boolean isServiziSimiliComune() {
		return serviziSimiliComune;
	}

	public void setServiziSimiliComune(boolean serviziSimiliComune) {
		this.serviziSimiliComune = serviziSimiliComune;
	}

	public boolean isServiziSimiliEnte() {
		return serviziSimiliEnte;
	}

	public void setServiziSimiliEnte(boolean serviziSimiliEnte) {
		this.serviziSimiliEnte = serviziSimiliEnte;
	}

	public String getServiziComune() {
		return serviziComune;
	}

	public void setServiziComune(String serviziComune) {
		this.serviziComune = serviziComune;
	}

	public String getServiziEntiPubblici() {
		return serviziEntiPubblici;
	}

	public void setServiziEntiPubblici(String serviziEntiPubblici) {
		this.serviziEntiPubblici = serviziEntiPubblici;
	}

	public List<Parente> getParenti() {
		return parenti;
	}

	public void setParenti(List<Parente> parenti) {
		this.parenti = parenti;
	}

	public String getMotiviConiugato() {
		return motiviConiugato;
	}

	public void setMotiviConiugato(String motiviConiugato) {
		this.motiviConiugato = motiviConiugato;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
}
