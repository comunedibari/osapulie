package it.osapulie.tributi.web.portlet.varie;

import java.io.Serializable;

/**
 * Classe contenente i dati comuni per la generazione della richiesta di rimborso
 * 
 * @author Maria Michela Birtolo
 * 
 */
public class DatiRimborsoGenerali implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String telefono;
	private String dataNascita;
	private String comuneNascita;
	private String sesso;
	private String provinciaNascita;
	private String comuneResidenza;
	private String capResidenza;
	private String indirizzoResidenza;
	private String provResidenza;

	private String ragSociale;
	private String naturaGiuridica;
	private String sedeLegale;
	private String comune;
	private String provincia;
	private String cap;
	private String telefonoGiu;
	private String codiceFiscaleGiu;
	private String pIva;

	private String nomeRapp;
	private String cognomeRapp;
	private String codiceFiscaleRapp;
	private String telefonoRapp;
	private String dataNascitaRapp;
	private String comuneNascitaRapp;
	private String sessoRapp;
	private String provinciaNascitaRapp;
	private String comuneResidenzaRapp;
	private String capResidenzaRapp;
	private String indirizzoResidenzaRapp;
	private String provResidenzaRapp;
	private String naturaCarica;

	boolean mandato;
	boolean accredito;
	boolean assegno;

	private String banca;
	private String filiale;
	private String iban;
	private String intestatario;

	private String anno;

	private String data;
	private String motivo;
	private Double rimborso;

	private String modalitaRimborso;
	private String impostaCompensazione;
	private String annoCompensazione;
	private String comuneRiversamento;
	private String annoRiversamento;

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getRagSociale() {
		return ragSociale;
	}

	public void setRagSociale(String ragSociale) {
		this.ragSociale = ragSociale;
	}

	public String getNaturaGiuridica() {
		return naturaGiuridica;
	}

	public void setNaturaGiuridica(String naturaGiuridica) {
		this.naturaGiuridica = naturaGiuridica;
	}

	public String getSedeLegale() {
		return sedeLegale;
	}

	public void setSedeLegale(String sedeLegale) {
		this.sedeLegale = sedeLegale;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTelefonoGiu() {
		return telefonoGiu;
	}

	public void setTelefonoGiu(String telefonoGiu) {
		this.telefonoGiu = telefonoGiu;
	}

	public String getCodiceFiscaleGiu() {
		return codiceFiscaleGiu;
	}

	public void setCodiceFiscaleGiu(String codiceFiscaleGiu) {
		this.codiceFiscaleGiu = codiceFiscaleGiu;
	}

	public String getpIva() {
		return pIva;
	}

	public void setpIva(String pIva) {
		this.pIva = pIva;
	}

	public String getNomeRapp() {
		return nomeRapp;
	}

	public void setNomeRapp(String nomeRapp) {
		this.nomeRapp = nomeRapp;
	}

	public String getCognomeRapp() {
		return cognomeRapp;
	}

	public void setCognomeRapp(String cognomeRapp) {
		this.cognomeRapp = cognomeRapp;
	}

	public String getCodiceFiscaleRapp() {
		return codiceFiscaleRapp;
	}

	public void setCodiceFiscaleRapp(String codiceFiscaleRapp) {
		this.codiceFiscaleRapp = codiceFiscaleRapp;
	}

	public String getTelefonoRapp() {
		return telefonoRapp;
	}

	public void setTelefonoRapp(String telefonoRapp) {
		this.telefonoRapp = telefonoRapp;
	}

	public String getComuneNascitaRapp() {
		return comuneNascitaRapp;
	}

	public void setComuneNascitaRapp(String comuneNascitaRapp) {
		this.comuneNascitaRapp = comuneNascitaRapp;
	}

	public String getSessoRapp() {
		return sessoRapp;
	}

	public void setSessoRapp(String sessoRapp) {
		this.sessoRapp = sessoRapp;
	}

	public String getProvinciaNascitaRapp() {
		return provinciaNascitaRapp;
	}

	public void setProvinciaNascitaRapp(String provinciaNascitaRapp) {
		this.provinciaNascitaRapp = provinciaNascitaRapp;
	}

	public String getComuneResidenzaRapp() {
		return comuneResidenzaRapp;
	}

	public void setComuneResidenzaRapp(String comuneResidenzaRapp) {
		this.comuneResidenzaRapp = comuneResidenzaRapp;
	}

	public String getCapResidenzaRapp() {
		return capResidenzaRapp;
	}

	public void setCapResidenzaRapp(String capResidenzaRapp) {
		this.capResidenzaRapp = capResidenzaRapp;
	}

	public String getIndirizzoResidenzaRapp() {
		return indirizzoResidenzaRapp;
	}

	public void setIndirizzoResidenzaRapp(String indirizzoResidenzaRapp) {
		this.indirizzoResidenzaRapp = indirizzoResidenzaRapp;
	}

	public String getProvResidenzaRapp() {
		return provResidenzaRapp;
	}

	public void setProvResidenzaRapp(String provResidenzaRapp) {
		this.provResidenzaRapp = provResidenzaRapp;
	}

	public String getNaturaCarica() {
		return naturaCarica;
	}

	public void setNaturaCarica(String naturaCarica) {
		this.naturaCarica = naturaCarica;
	}

	public boolean isMandato() {
		return mandato;
	}

	public void setMandato(boolean mandato) {
		this.mandato = mandato;
	}

	public boolean isAccredito() {
		return accredito;
	}

	public void setAccredito(boolean accredito) {
		this.accredito = accredito;
	}

	public boolean isAssegno() {
		return assegno;
	}

	public void setAssegno(boolean assegno) {
		this.assegno = assegno;
	}

	public String getBanca() {
		return banca;
	}

	public void setBanca(String banca) {
		this.banca = banca;
	}

	public String getFiliale() {
		return filiale;
	}

	public void setFiliale(String filiale) {
		this.filiale = filiale;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getIntestatario() {
		return intestatario;
	}

	public void setIntestatario(String intestatario) {
		this.intestatario = intestatario;
	}

	public String getAnno() {
		return anno;
	}

	public void setAnno(String anno) {
		this.anno = anno;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public Double getRimborso() {
		return rimborso;
	}

	public void setRimborso(Double rimborso) {
		this.rimborso = rimborso;
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

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
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

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzo) {
		this.indirizzoResidenza = indirizzo;
	}

	public String getProvResidenza() {
		return provResidenza;
	}

	public void setProvResidenza(String provResidenza) {
		this.provResidenza = provResidenza;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getDataNascitaRapp() {
		return dataNascitaRapp;
	}

	public void setDataNascitaRapp(String dataNascitaRapp) {
		this.dataNascitaRapp = dataNascitaRapp;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getModalitaRimborso() {
		return modalitaRimborso;
	}

	public void setModalitaRimborso(String modalitaRimborso) {
		this.modalitaRimborso = modalitaRimborso;
	}

	public String getImpostaCompensazione() {
		return impostaCompensazione;
	}

	public void setImpostaCompensazione(String impostaCompensazione) {
		this.impostaCompensazione = impostaCompensazione;
	}

	public String getAnnoCompensazione() {
		return annoCompensazione;
	}

	public void setAnnoCompensazione(String annoCompensazione) {
		this.annoCompensazione = annoCompensazione;
	}

	public String getComuneRiversamento() {
		return comuneRiversamento;
	}

	public void setComuneRiversamento(String comuneRiversamento) {
		this.comuneRiversamento = comuneRiversamento;
	}

	public String getAnnoRiversamento() {
		return annoRiversamento;
	}

	public void setAnnoRiversamento(String annoRiversamento) {
		this.annoRiversamento = annoRiversamento;
	}

}
