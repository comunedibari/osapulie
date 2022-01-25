package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

import java.io.Serializable;
import java.util.List;

public class DatiImmobile implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String identificativoUtenza;
	private String indirizzo;
	private String indirizzoTextHidden;
	private String civico;
	private String civicoTextHidden;
	private String localita;
	private String localitaHidden;
	private String codiceViaHidden;
	private String tipo;
	private String mq;
	private String foglio;
	private String particella;
	private String subalterno;
	private String dataOccupazione;
	private String numOccupanti;
	private String categoria;
	private String scala;
	private String piano;
	private String interno;
	private String esponente;
	private String cap;
	private String suffisso;
	private String sezione;
	private String codAteco;
	private String utenzaConRiduzione;
	private String tipologiaSuperficie;
	private String superficieTotale;
	private String intassabileAltre;
	private String intassabileFissi;
	private String superficieTassabile;
	private String varDiSuperficieDa;
	private String varDiSuperficieA;
	private String varDiCategoriaDa;
	private String varDiCategoriaA;

	private boolean stradarioEnabled;

	private List<Riduzione> riduzioni;

	/**
	 * @return the identificativoUtenza
	 */
	public String getIdentificativoUtenza() {
		return identificativoUtenza;
	}

	/**
	 * @param identificativoUtenza the identificativoUtenza to set
	 */
	public void setIdentificativoUtenza(String identificativoUtenza) {
		this.identificativoUtenza = identificativoUtenza;
	}

	/**
	 * @return the indirizzo
	 */
	public String getIndirizzo() {
		return indirizzo;
	}

	/**
	 * @param indirizzo the indirizzo to set
	 */
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	/**
	 * @return the indirizzoTextHidden
	 */
	public String getIndirizzoTextHidden() {
		return indirizzoTextHidden;
	}

	/**
	 * @param indirizzoTextHidden the indirizzoTextHidden to set
	 */
	public void setIndirizzoTextHidden(String indirizzoTextHidden) {
		this.indirizzoTextHidden = indirizzoTextHidden;
	}

	/**
	 * @return the civico
	 */
	public String getCivico() {
		return civico;
	}

	/**
	 * @param civico the civico to set
	 */
	public void setCivico(String civico) {
		this.civico = civico;
	}

	/**
	 * @return the civicoTextHidden
	 */
	public String getCivicoTextHidden() {
		return civicoTextHidden;
	}

	/**
	 * @param civicoTextHidden the civicoTextHidden to set
	 */
	public void setCivicoTextHidden(String civicoTextHidden) {
		this.civicoTextHidden = civicoTextHidden;
	}

	/**
	 * @return the localita
	 */
	public String getLocalita() {
		return localita;
	}

	/**
	 * @param localita the localita to set
	 */
	public void setLocalita(String localita) {
		this.localita = localita;
	}

	/**
	 * @return the localitaHidden
	 */
	public String getLocalitaHidden() {
		return localitaHidden;
	}

	/**
	 * @param localitaHidden the localitaHidden to set
	 */
	public void setLocalitaHidden(String localitaHidden) {
		this.localitaHidden = localitaHidden;
	}

	/**
	 * @return the codiceViaHidden
	 */
	public String getCodiceViaHidden() {
		return codiceViaHidden;
	}

	/**
	 * @param codiceViaHidden the codiceViaHidden to set
	 */
	public void setCodiceViaHidden(String codiceViaHidden) {
		this.codiceViaHidden = codiceViaHidden;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the mq
	 */
	public String getMq() {
		return mq;
	}

	/**
	 * @param mq the mq to set
	 */
	public void setMq(String mq) {
		this.mq = mq;
	}

	/**
	 * @return the foglio
	 */
	public String getFoglio() {
		return foglio;
	}

	/**
	 * @param foglio the foglio to set
	 */
	public void setFoglio(String foglio) {
		this.foglio = foglio;
	}

	/**
	 * @return the particella
	 */
	public String getParticella() {
		return particella;
	}

	/**
	 * @param particella the particella to set
	 */
	public void setParticella(String particella) {
		this.particella = particella;
	}

	/**
	 * @return the subalterno
	 */
	public String getSubalterno() {
		return subalterno;
	}

	/**
	 * @param subalterno the subalterno to set
	 */
	public void setSubalterno(String subalterno) {
		this.subalterno = subalterno;
	}

	/**
	 * @return the dataOccupazione
	 */
	public String getDataOccupazione() {
		return dataOccupazione;
	}

	/**
	 * @param dataOccupazione the dataOccupazione to set
	 */
	public void setDataOccupazione(String dataOccupazione) {
		this.dataOccupazione = dataOccupazione;
	}

	/**
	 * @return the numOccupanti
	 */
	public String getNumOccupanti() {
		return numOccupanti;
	}

	/**
	 * @param numOccupanti the numOccupanti to set
	 */
	public void setNumOccupanti(String numOccupanti) {
		this.numOccupanti = numOccupanti;
	}

	/**
	 * @return the scala
	 */
	public String getScala() {
		return scala;
	}

	/**
	 * @param scala the scala to set
	 */
	public void setScala(String scala) {
		this.scala = scala;
	}

	/**
	 * @return the piano
	 */
	public String getPiano() {
		return piano;
	}

	/**
	 * @param piano the piano to set
	 */
	public void setPiano(String piano) {
		this.piano = piano;
	}

	/**
	 * @return the interno
	 */
	public String getInterno() {
		return interno;
	}

	/**
	 * @param interno the interno to set
	 */
	public void setInterno(String interno) {
		this.interno = interno;
	}

	/**
	 * @return the esponente
	 */
	public String getEsponente() {
		return esponente;
	}

	/**
	 * @param esponente the esponente to set
	 */
	public void setEsponente(String esponente) {
		this.esponente = esponente;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {
		return cap;
	}

	/**
	 * @param cap the cap to set
	 */
	public void setCap(String cap) {
		this.cap = cap;
	}

	/**
	 * @return the suffisso
	 */
	public String getSuffisso() {
		return suffisso;
	}

	/**
	 * @param suffisso the suffisso to set
	 */
	public void setSuffisso(String suffisso) {
		this.suffisso = suffisso;
	}

	/**
	 * @return the sezione
	 */
	public String getSezione() {
		return sezione;
	}

	/**
	 * @param sezione the sezione to set
	 */
	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	/**
	 * @return the codAteco
	 */
	public String getCodAteco() {
		return codAteco;
	}

	/**
	 * @param codAteco the codAteco to set
	 */
	public void setCodAteco(String codAteco) {
		this.codAteco = codAteco;
	}

	/**
	 * @return the utenzaConRiduzione
	 */
	public String getUtenzaConRiduzione() {
		return utenzaConRiduzione;
	}

	/**
	 * @param utenzaConRiduzione the utenzaConRiduzione to set
	 */
	public void setUtenzaConRiduzione(String utenzaConRiduzione) {
		this.utenzaConRiduzione = utenzaConRiduzione;
	}

	/**
	 * @return the tipologiaSuperficie
	 */
	public String getTipologiaSuperficie() {
		return tipologiaSuperficie;
	}

	/**
	 * @param tipologiaSuperficie the tipologiaSuperficie to set
	 */
	public void setTipologiaSuperficie(String tipologiaSuperficie) {
		this.tipologiaSuperficie = tipologiaSuperficie;
	}

	/**
	 * @return the superficieTotale
	 */
	public String getSuperficieTotale() {
		return superficieTotale;
	}

	/**
	 * @param superficieTotale the superficieTotale to set
	 */
	public void setSuperficieTotale(String superficieTotale) {
		this.superficieTotale = superficieTotale;
	}

	/**
	 * @return the intassabileAltre
	 */
	public String getIntassabileAltre() {
		return intassabileAltre;
	}

	/**
	 * @param intassabileAltre the intassabileAltre to set
	 */
	public void setIntassabileAltre(String intassabileAltre) {
		this.intassabileAltre = intassabileAltre;
	}

	/**
	 * @return the intassabileFissi
	 */
	public String getIntassabileFissi() {
		return intassabileFissi;
	}

	/**
	 * @param intassabileFissi the intassabileFissi to set
	 */
	public void setIntassabileFissi(String intassabileFissi) {
		this.intassabileFissi = intassabileFissi;
	}

	/**
	 * @return the superficieTassabile
	 */
	public String getSuperficieTassabile() {
		return superficieTassabile;
	}

	/**
	 * @param superficieTassabile the superficieTassabile to set
	 */
	public void setSuperficieTassabile(String superficieTassabile) {
		this.superficieTassabile = superficieTassabile;
	}

	/**
	 * @return the varDiSuperficieDa
	 */
	public String getVarDiSuperficieDa() {
		return varDiSuperficieDa;
	}

	/**
	 * @param varDiSuperficieDa the varDiSuperficieDa to set
	 */
	public void setVarDiSuperficieDa(String varDiSuperficieDa) {
		this.varDiSuperficieDa = varDiSuperficieDa;
	}

	/**
	 * @return the varDiSuperficieA
	 */
	public String getVarDiSuperficieA() {
		return varDiSuperficieA;
	}

	/**
	 * @param varDiSuperficieA the varDiSuperficieA to set
	 */
	public void setVarDiSuperficieA(String varDiSuperficieA) {
		this.varDiSuperficieA = varDiSuperficieA;
	}

	/**
	 * @return the varDiCategoriaDa
	 */
	public String getVarDiCategoriaDa() {
		return varDiCategoriaDa;
	}

	/**
	 * @param varDiCategoriaDa the varDiCategoriaDa to set
	 */
	public void setVarDiCategoriaDa(String varDiCategoriaDa) {
		this.varDiCategoriaDa = varDiCategoriaDa;
	}

	/**
	 * @return the varDiCategoriaA
	 */
	public String getVarDiCategoriaA() {
		return varDiCategoriaA;
	}

	/**
	 * @param varDiCategoriaA the varDiCategoriaA to set
	 */
	public void setVarDiCategoriaA(String varDiCategoriaA) {
		this.varDiCategoriaA = varDiCategoriaA;
	}

	/**
	 * @return the stradarioEnabled
	 */
	public boolean isStradarioEnabled() {
		return stradarioEnabled;
	}

	/**
	 * @param stradarioEnabled the stradarioEnabled to set
	 */
	public void setStradarioEnabled(boolean stradarioEnabled) {
		this.stradarioEnabled = stradarioEnabled;
	}

	/**
	 * @return the riduzioni
	 */
	public List<Riduzione> getRiduzioni() {
		return riduzioni;
	}

	/**
	 * @param riduzioni the riduzioni to set
	 */
	public void setRiduzioni(List<Riduzione> riduzioni) {
		this.riduzioni = riduzioni;
	}

	/**
	 * @return the categoria
	 */
	public String getCategoria() {
		return categoria;
	}

	/**
	 * @param categoria the categoria to set
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

}
