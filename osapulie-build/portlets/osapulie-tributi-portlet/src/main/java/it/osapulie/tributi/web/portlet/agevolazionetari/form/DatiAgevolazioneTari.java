/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.agevolazionetari.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe contenente i dati per la generazione della richiesta di rimborso ICI
 *
 */
public class DatiAgevolazioneTari implements Serializable {

	private static final long serialVersionUID = 1L;

	// ContribuenteVariazione -> idContribuente
	private String idContribuente;

	// ContribuenteVariazione -> PersonaFisica
	private String cognome;
	private String nome;
	private String codiceFiscale;
	private String partitaIVA;
	private String comuneNascita;
	private String comuneNascitaHidden;
	private String provinciaNascita;

	private String statoEstero;
	private String denominazioneEstero;
	private String comuneNascitaEstero;
	private String codiceNascitaEstero;

	private String dataNascita;
	private String sesso;

	// dati non presenti in ContribuenteVariazione
	private String comuneResidenza;
	private String comuneResidenzaHidden;
	private String provinciaResidenza;
	private String indirizzoResidenza;
	private String civicoResidenza;
	private String indirizzoResidenzaTextHidden;
	private String civicoResidenzaTextHidden;

	private String esponenteResidenza;
	private String scalaResidenza;
	private String pianoResidenza;
	private String internoResidenza;

	// ContribuenteVariazione -> telefono
	private String telefono;

	private String dataDomanda;
	// dati Abitazione - Pertinenza1 - Pertinenza2
	private List<DatiImmobileAgevolazioneTari> immobili = new ArrayList<DatiImmobileAgevolazioneTari>();

	private boolean stradarioEnabled;
	private boolean indirizzoResidenzaConStradario;

	private boolean limitatoIscrizione;

	// agevolazioni
	private String dataDecorrenza;
	private String codiceAgevolazione;

	// ModelloIsee -> NucleoFamiliare
	private String numComponentiNucleoFamiliare;
	private String numSoggettiHandicap;
	private boolean presenzaFigliUnGenitore;
	private boolean presenzaFigliAttivitaGenitori;

	// ModelloIsee -> Patrimonio
	private String rc;
	private String pag;
	private String rcpag;
	private String rpm;
	private String dc;
	private String pmo;
	private String dpm;
	private String pim;
	private String dpi;
	private String dataAttestazione;
	private String dataScadenza;
	private String codiceFiscaleContribuente;
	private String valoreISE;
	private String scalaEquivalenza;
	private String valoreISEE;

	// CAF
	private String codiceFiscaleCAF;
	private String partitaIvaCAF;
	private String nominativoCAF;
	private String pecCAF;
	private boolean presaVisione;
	private String uuidOperazione;
	
	private boolean dichiarazioneCompletata;

	public String getIdContribuente() {
		return idContribuente;
	}

	public void setIdContribuente(String idContribuente) {
		this.idContribuente = idContribuente;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	public String getComuneNascita() {
		return comuneNascita;
	}

	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	public String getComuneNascitaHidden() {
		return comuneNascitaHidden;
	}

	public void setComuneNascitaHidden(String comuneNascitaHidden) {
		this.comuneNascitaHidden = comuneNascitaHidden;
	}

	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	public String getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	public String getStatoEstero() {
		return statoEstero;
	}

	public void setStatoEstero(String statoEstero) {
		this.statoEstero = statoEstero;
	}

	public String getDenominazioneEstero() {
		return denominazioneEstero;
	}

	public void setDenominazioneEstero(String denominazioneEstero) {
		this.denominazioneEstero = denominazioneEstero;
	}

	public String getComuneNascitaEstero() {
		return comuneNascitaEstero;
	}

	public void setComuneNascitaEstero(String comuneNascitaEstero) {
		this.comuneNascitaEstero = comuneNascitaEstero;
	}

	public String getCodiceNascitaEstero() {
		return codiceNascitaEstero;
	}

	public void setCodiceNascitaEstero(String codiceNascitaEstero) {
		this.codiceNascitaEstero = codiceNascitaEstero;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public String getComuneResidenza() {
		return comuneResidenza;
	}

	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	public String getComuneResidenzaHidden() {
		return comuneResidenzaHidden;
	}

	public void setComuneResidenzaHidden(String comuneResidenzaHidden) {
		this.comuneResidenzaHidden = comuneResidenzaHidden;
	}

	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	public String getIndirizzoResidenzaTextHidden() {
		return indirizzoResidenzaTextHidden;
	}

	public void setIndirizzoResidenzaTextHidden(String indirizzoResidenzaTextHidden) {
		this.indirizzoResidenzaTextHidden = indirizzoResidenzaTextHidden;
	}

	public String getCivicoResidenzaTextHidden() {
		return civicoResidenzaTextHidden;
	}

	public void setCivicoResidenzaTextHidden(String civicoResidenzaTextHidden) {
		this.civicoResidenzaTextHidden = civicoResidenzaTextHidden;
	}

	public String getEsponenteResidenza() {
		return esponenteResidenza;
	}

	public void setEsponenteResidenza(String esponenteResidenza) {
		this.esponenteResidenza = esponenteResidenza;
	}

	public String getScalaResidenza() {
		return scalaResidenza;
	}

	public void setScalaResidenza(String scalaResidenza) {
		this.scalaResidenza = scalaResidenza;
	}

	public String getPianoResidenza() {
		return pianoResidenza;
	}

	public void setPianoResidenza(String pianoResidenza) {
		this.pianoResidenza = pianoResidenza;
	}

	public String getInternoResidenza() {
		return internoResidenza;
	}

	public void setInternoResidenza(String internoResidenza) {
		this.internoResidenza = internoResidenza;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<DatiImmobileAgevolazioneTari> getImmobili() {
		return immobili;
	}

	public void setImmobili(List<DatiImmobileAgevolazioneTari> immobili) {
		this.immobili = immobili;
	}

	public boolean getStradarioEnabled() {
		return stradarioEnabled;
	}

	public void setStradarioEnabled(boolean stradarioEnabled) {
		this.stradarioEnabled = stradarioEnabled;
	}

	public String getDataDecorrenza() {
		return dataDecorrenza;
	}

	public void setDataDecorrenza(String dataDecorrenza) {
		this.dataDecorrenza = dataDecorrenza;
	}

	public String getCodiceAgevolazione() {
		return codiceAgevolazione;
	}

	public void setCodiceAgevolazione(String codiceAgevolazione) {
		this.codiceAgevolazione = codiceAgevolazione;
	}

	public String getNumComponentiNucleoFamiliare() {
		return numComponentiNucleoFamiliare;
	}

	public void setNumComponentiNucleoFamiliare(String numComponentiNucleoFamiliare) {
		this.numComponentiNucleoFamiliare = numComponentiNucleoFamiliare;
	}

	public String getNumSoggettiHandicap() {
		return numSoggettiHandicap;
	}

	public void setNumSoggettiHandicap(String numSoggettiHandicap) {
		this.numSoggettiHandicap = numSoggettiHandicap;
	}

	public boolean getPresenzaFigliUnGenitore() {
		return presenzaFigliUnGenitore;
	}

	public void setPresenzaFigliUnGenitore(boolean presenzaFigliUnGenitore) {
		this.presenzaFigliUnGenitore = presenzaFigliUnGenitore;
	}

	public boolean getPresenzaFigliAttivitaGenitori() {
		return presenzaFigliAttivitaGenitori;
	}

	public void setPresenzaFigliAttivitaGenitori(boolean presenzaFigliAttivitaGenitori) {
		this.presenzaFigliAttivitaGenitori = presenzaFigliAttivitaGenitori;
	}

	public String getRc() {
		return rc;
	}

	public void setRc(String rc) {
		this.rc = rc;
	}

	public String getPag() {
		return pag;
	}

	public void setPag(String pag) {
		this.pag = pag;
	}

	public String getRcpag() {
		return rcpag;
	}

	public void setRcpag(String rcpag) {
		this.rcpag = rcpag;
	}

	public String getRpm() {
		return rpm;
	}

	public void setRpm(String rpm) {
		this.rpm = rpm;
	}

	public String getDc() {
		return dc;
	}

	public void setDc(String dc) {
		this.dc = dc;
	}

	public String getPmo() {
		return pmo;
	}

	public void setPmo(String pmo) {
		this.pmo = pmo;
	}

	public String getDpm() {
		return dpm;
	}

	public void setDpm(String dpm) {
		this.dpm = dpm;
	}

	public String getPim() {
		return pim;
	}

	public void setPim(String pim) {
		this.pim = pim;
	}

	public String getDpi() {
		return dpi;
	}

	public void setDpi(String dpi) {
		this.dpi = dpi;
	}

	public String getDataAttestazione() {
		return dataAttestazione;
	}

	public void setDataAttestazione(String dataAttestazione) {
		this.dataAttestazione = dataAttestazione;
	}

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getCodiceFiscaleContribuente() {
		return codiceFiscaleContribuente;
	}

	public void setCodiceFiscaleContribuente(String codiceFiscaleContribuente) {
		this.codiceFiscaleContribuente = codiceFiscaleContribuente;
	}

	public String getValoreISE() {
		return valoreISE;
	}

	public void setValoreISE(String valoreISE) {
		this.valoreISE = valoreISE;
	}

	public String getScalaEquivalenza() {
		return scalaEquivalenza;
	}

	public void setScalaEquivalenza(String scalaEquivalenza) {
		this.scalaEquivalenza = scalaEquivalenza;
	}

	public String getValoreISEE() {
		return valoreISEE;
	}

	public void setValoreISEE(String valoreISEE) {
		this.valoreISEE = valoreISEE;
	}

	public String getCodiceFiscaleCAF() {
		return codiceFiscaleCAF;
	}

	public void setCodiceFiscaleCAF(String codiceFiscaleCAF) {
		this.codiceFiscaleCAF = codiceFiscaleCAF;
	}

	public String getPartitaIvaCAF() {
		return partitaIvaCAF;
	}

	public void setPartitaIvaCAF(String partitaIvaCAF) {
		this.partitaIvaCAF = partitaIvaCAF;
	}

	public String getNominativoCAF() {
		return nominativoCAF;
	}

	public void setNominativoCAF(String nominativoCAF) {
		this.nominativoCAF = nominativoCAF;
	}

	public boolean getPresaVisione() {
		return presaVisione;
	}

	public void setPresaVisione(boolean presaVisione) {
		this.presaVisione = presaVisione;
	}

	public String getDataDomanda() {
		return dataDomanda;
	}

	public void setDataDomanda(String dataDomanda) {
		this.dataDomanda = dataDomanda;
	}

	/**
	 * @return the indirizzoResidenzaConStradario
	 */
	public boolean isIndirizzoResidenzaConStradario() {
		return indirizzoResidenzaConStradario;
	}

	/**
	 * @param indirizzoResidenzaConStradario the indirizzoResidenzaConStradario to set
	 */
	public void setIndirizzoResidenzaConStradario(boolean indirizzoResidenzaConStradario) {
		this.indirizzoResidenzaConStradario = indirizzoResidenzaConStradario;
	}

	public boolean isLimitatoIscrizione() {
		return limitatoIscrizione;
	}

	public void setLimitatoIscrizione(boolean limitatoIscrizione) {
		this.limitatoIscrizione = limitatoIscrizione;
	}

	public String getPecCAF() {
		return pecCAF;
	}

	public void setPecCAF(String pecCAF) {
		this.pecCAF = pecCAF;
	}

	/**
	 * @return the dichiarazioneCompletata
	 */
	public boolean isDichiarazioneCompletata() {
		return dichiarazioneCompletata;
	}

	/**
	 * @param dichiarazioneCompletata the dichiarazioneCompletata to set
	 */
	public void setDichiarazioneCompletata(boolean dichiarazioneCompletata) {
		this.dichiarazioneCompletata = dichiarazioneCompletata;
	}

	public String getUuidOperazione() {
		return uuidOperazione;
	}
	
	public void setUuidOperazione(String uuidOperazione) {
		this.uuidOperazione = uuidOperazione;
	}
	
}
