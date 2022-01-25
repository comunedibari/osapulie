/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

import java.io.Serializable;
import java.util.List;

/**
 * Classe contenente i dati per la generazione della dichiarazione TARI
 *
 * @author Gianluca Pindinelli
 *
 */
public class DatiDichiarazioneTari implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String tipoPersona; // Fisica o Giuridica
	private String tipoUtenza; // Domestica o non domestica
	private String tipoDichiarazione; // Iscrizione, Variazione, Cessazione, Trasferimento(solo in
										// caso di
	// utenza non domestica)
	private String aDecorrereDa;

	// Anagrafica
	private String nome;
	private String cognome;
	private String sesso;
	private String codiceFiscale;
	private String telefono;
	private String email;
	private String dataNascita;
	private String comuneNascita;
	private String comuneNascitaHidden;
	private String provinciaNascita;
	private String comuneResidenza;
	private String comuneResidenzaHidden;
	private String indirizzoResidenza;
	private String civicoResidenza;
	private String esponenteResidenza;
	private String capResidenza;
	private String indirizzoResidenzaTextHidden;
	private String civicoResidenzaTextHidden;
	private String provResidenza;
	private String statoEstero;
	private String denominazioneEstero;
	private String comuneNascitaEstero;
	private String codiceNascitaEstero;
	private String pec;
	private String fax; // se tipoUtenza=domestica
	private String numRea;
	private String provinciaRea;
	private boolean flagDelega;
	private String nominativoDelegato;

	// residenza
	private String resNum;
	private String resEsp;
	private String resSc;
	private String resP;
	private String resInt;
	private boolean residente;

	private String modalitaInvio; // residenza o altro
	private String indirizzoSpedizione;// indirizzo invio cartella alternativo
	private String indirizzoSpedizioneTextHidden;
	private String civicoSpedizione;
	private String civicoSpedizioneTextHidden;
	private String espSpedizione;
	private String comuneSpedizione;
	private String comuneSpedizioneHidden;
	private String provinciaSpedizione;
	private String capSpedizione;

	private String ruolo;
	private String nomeSocieta;
	private String partitaIva;
	private String comuneSedeLegale;
	private String comuneSedeLegaleHidden;
	private String numeroSedeLegale;
	private String numeroSedeLegaleTextHidden;
	private String espSedeLegale;
	private String scSedeLegale;
	private String pSedeLegale;
	private String intSedeLegale;
	private String viaSedeLegale;
	private String capSedeLegale;
	private String viaSedeLegaleTextHidden;

	// Iscrizioni - Domestiche
	private boolean pertinenza;
	private boolean nonResidente;
	private String totaleNucleoInResidenza;
	private boolean nonResidenteNucleoFam;
	private String noteNonResidente;
	private String totaleNucleoFamiliare;
	private boolean detenzioneImmobile;

	// Iscrizioni - Non domestiche
	private String noteRiduzione;
	private String attivitaPrevalente;
	private String codiceAteco;

	// Variazioni - Domestiche
	private boolean diSuperficie;
	private boolean varCompNucleoFam;
	private String varCompNuclDa;
	private String varCompNuclA;
	private String varCompNuclNote;
	private boolean varDatiAnagrafici;
	private String varDatiAnagraficiNote;
	private String[] tipologiaRiduzioneDom;
	private String tipologiaRichiestaDom;
	// private boolean revocaDom;
	// private boolean agevolazioniDom;
	private String agevolazioniTipologiaDom;
	private boolean altroDom;
	private String altroNoteDom;

	// Variazioni - non domestiche
	// private boolean revocaNonDom;
	// private boolean agevolazioniNonDom;
	private String agevolazioniTipologiaNonDom;
	private boolean altroNonDom;
	private String altroNoteNonDom;
	private String[] tipologiaRiduzioneNonDom;
	private String tipologiaRichiestaNonDom;

	// Cessazione - Domestica
	private String motivoCessazioneDom;
	private String altriMotiviDom;
	private String nominativoDeceduto;
	private String dataDecesso;
	private String indirizzoCoabitazione;
	private String civicoCoabitazione;
	private String indirizzoCoabitazioneTextHidden;
	private String civicoCoabitazioneTextHidden;
	private String scalaCoabitazione;
	private String pianoCoabitazione;
	private String intCoabitazione;
	private String espCoabitazione;
	private String capCoabitazione;
	private String suffCoabitazione;
	private String coabitanteDom;
	private String comuneEmigrazioneDom;
	private String comuneEmigrazioneDomHidden;
	private String provEmigrazioneDom;
	private String viaEmigrazioneDom;
	private String civicoEmigrazioneDom;
	private String capEmigrazioneDom;
	private String esponenteEmigrazioneDom;
	private String concessioneQuoteDom;
	private String modalitaRimborsoDom;
	private String ibanDom;

	// Cessazione - Non domestica
	private String motivoCessazioneNonDom;
	private String altriMotiviNonDom;
	private String coabitanteNonDom;
	private String comuneEmigrazioneNonDom;
	private String provEmigrazioneNonDom;
	private String viaEmigrazioneNonDom;
	private String civicoEmigrazioneNonDom;
	private String capEmigrazioneNonDom;
	private String esponenteEmigrazioneNonDom;
	private String concessioneQuoteNonDom;
	private String modalitaRimborsoNonDom;
	private String ibanNonDom;
	private String attivitaCessata;
	private String specificaRilascioImmobile;
	private String altroSpecificaRilascioImmobile;
	private String vecchiaDenominazione;
	private String nuovaDenominazione;

	private String allegatoUno;
	private String allegatoDue;
	private String allegatoTre;
	private String allegatoQuattro;
	private String estremiDocumento;

	private List<Occupante> occupanti;
	private List<Occupante> variazioneOccupanti;

	private String dataOccupazione;

	private List<DatiImmobile> iscrizioneDom;
	private List<DatiImmobile> cessazioneDom;
	private List<DatiImmobile> variazioneDom;
	private List<DatiImmobile> iscrizioneNonDom;
	private List<DatiImmobile> cessazioneNonDom;
	private List<DatiImmobile> variazioneNonDom;

	private List<Riduzione> riduzioniDomestiche;
	private List<Riduzione> riduzioniDomesticheIscrizione;
	private List<Riduzione> riduzioniDomesticheVariazione;

	private List<Riduzione> riduzioniNonDomestiche;
	private List<Riduzione> riduzioniNonDomesticheIscrizione;
	private List<Riduzione> riduzioniNonDomesticheVariazione;

	// Utilizzo una lista perchè in futuro la scelta potrà riguardare più utenze (da radiobutton a
	// checkbox)
	private String identificativoUtenzeSelezionateCessazione;
	private String identificativoUtenzeSelezionateVariazione;

	private String noteGenerali;

	private boolean stradarioEnabled;
	private boolean indirizzoResidenzaConStradario;
	private boolean indirizzoSpedizioneConStradario;

	private boolean limitatoIscrizione;

	private boolean escludiDomestiche;

	private boolean escludiCommerciali;

	private String idContribuente;

	private boolean dichiarazioneCompletata;

	private Integer identificativoNucleoFamiliare;
	private String uuidOperazione;
	private String ipAddress;
	
	/**
	 * @return the tipoPersona
	 */
	public String getTipoPersona() {
		return tipoPersona;
	}

	/**
	 * @param tipoPersona the tipoPersona to set
	 */
	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	/**
	 * @return the tipoUtenza
	 */
	public String getTipoUtenza() {
		return tipoUtenza;
	}

	/**
	 * @param tipoUtenza the tipoUtenza to set
	 */
	public void setTipoUtenza(String tipoUtenza) {
		this.tipoUtenza = tipoUtenza;
	}

	/**
	 * @return the tipoDichiarazione
	 */
	public String getTipoDichiarazione() {
		return tipoDichiarazione;
	}

	/**
	 * @param tipoDichiarazione the tipoDichiarazione to set
	 */
	public void setTipoDichiarazione(String tipoDichiarazione) {
		this.tipoDichiarazione = tipoDichiarazione;
	}

	/**
	 * @return the aDecorrereDa
	 */
	public String getaDecorrereDa() {
		return aDecorrereDa;
	}

	/**
	 * @param aDecorrereDa the aDecorrereDa to set
	 */
	public void setaDecorrereDa(String aDecorrereDa) {
		this.aDecorrereDa = aDecorrereDa;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}

	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	/**
	 * @param codiceFiscale the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the dataNascita
	 */
	public String getDataNascita() {
		return dataNascita;
	}

	/**
	 * @param dataNascita the dataNascita to set
	 */
	public void setDataNascita(String dataNascita) {
		this.dataNascita = dataNascita;
	}

	/**
	 * @return the comuneNascita
	 */
	public String getComuneNascita() {
		return comuneNascita;
	}

	/**
	 * @param comuneNascita the comuneNascita to set
	 */
	public void setComuneNascita(String comuneNascita) {
		this.comuneNascita = comuneNascita;
	}

	/**
	 * @return the provinciaNascita
	 */
	public String getProvinciaNascita() {
		return provinciaNascita;
	}

	/**
	 * @param provinciaNascita the provinciaNascita to set
	 */
	public void setProvinciaNascita(String provinciaNascita) {
		this.provinciaNascita = provinciaNascita;
	}

	/**
	 * @return the comuneResidenza
	 */
	public String getComuneResidenza() {
		return comuneResidenza;
	}

	/**
	 * @param comuneResidenza the comuneResidenza to set
	 */
	public void setComuneResidenza(String comuneResidenza) {
		this.comuneResidenza = comuneResidenza;
	}

	/**
	 * @return the indirizzoResidenza
	 */
	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	/**
	 * @param indirizzoResidenza the indirizzoResidenza to set
	 */
	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	/**
	 * @return the civicoResidenza
	 */
	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	/**
	 * @param civicoResidenza the civicoResidenza to set
	 */
	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	/**
	 * @return the esponenteResidenza
	 */
	public String getEsponenteResidenza() {
		return esponenteResidenza;
	}

	/**
	 * @param esponenteResidenza the esponenteResidenza to set
	 */
	public void setEsponenteResidenza(String esponenteResidenza) {
		this.esponenteResidenza = esponenteResidenza;
	}

	/**
	 * @return the indirizzoResidenzaTextHidden
	 */
	public String getIndirizzoResidenzaTextHidden() {
		return indirizzoResidenzaTextHidden;
	}

	/**
	 * @param indirizzoResidenzaTextHidden the indirizzoResidenzaTextHidden to set
	 */
	public void setIndirizzoResidenzaTextHidden(String indirizzoResidenzaTextHidden) {
		this.indirizzoResidenzaTextHidden = indirizzoResidenzaTextHidden;
	}

	/**
	 * @return the civicoResidenzaTextHidden
	 */
	public String getCivicoResidenzaTextHidden() {
		return civicoResidenzaTextHidden;
	}

	/**
	 * @param civicoResidenzaTextHidden the civicoResidenzaTextHidden to set
	 */
	public void setCivicoResidenzaTextHidden(String civicoResidenzaTextHidden) {
		this.civicoResidenzaTextHidden = civicoResidenzaTextHidden;
	}

	/**
	 * @return the provResidenza
	 */
	public String getProvResidenza() {
		return provResidenza;
	}

	/**
	 * @param provResidenza the provResidenza to set
	 */
	public void setProvResidenza(String provResidenza) {
		this.provResidenza = provResidenza;
	}

	/**
	 * @return the statoEstero
	 */
	public String getStatoEstero() {
		return statoEstero;
	}

	/**
	 * @param statoEstero the statoEstero to set
	 */
	public void setStatoEstero(String statoEstero) {
		this.statoEstero = statoEstero;
	}

	/**
	 * @return the statoEstero
	 */
	public String getDenominazioneEstero() {
		return denominazioneEstero;
	}

	/**
	 * @param statoEstero the statoEstero to set
	 */
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

	/**
	 * @return the pec
	 */
	public String getPec() {
		return pec;
	}

	/**
	 * @param pec the pec to set
	 */
	public void setPec(String pec) {
		this.pec = pec;
	}

	/**
	 * @return the fax
	 */
	public String getFax() {
		return fax;
	}

	/**
	 * @param fax the fax to set
	 */
	public void setFax(String fax) {
		this.fax = fax;
	}

	/**
	 * @return the numRea
	 */
	public String getNumRea() {
		return numRea;
	}

	/**
	 * @param numRea the numRea to set
	 */
	public void setNumRea(String numRea) {
		this.numRea = numRea;
	}

	/**
	 * @return the flagDelega
	 */
	public boolean isFlagDelega() {
		return flagDelega;
	}

	/**
	 * @param flagDelega the flagDelega to set
	 */
	public void setFlagDelega(boolean flagDelega) {
		this.flagDelega = flagDelega;
	}

	/**
	 * @return the nominativoDelegato
	 */
	public String getNominativoDelegato() {
		return nominativoDelegato;
	}

	/**
	 * @param nominativoDelegato the nominativoDelegato to set
	 */
	public void setNominativoDelegato(String nominativoDelegato) {
		this.nominativoDelegato = nominativoDelegato;
	}

	/**
	 * @return the resNum
	 */
	public String getResNum() {
		return resNum;
	}

	/**
	 * @param resNum the resNum to set
	 */
	public void setResNum(String resNum) {
		this.resNum = resNum;
	}

	/**
	 * @return the resEsp
	 */
	public String getResEsp() {
		return resEsp;
	}

	/**
	 * @param resEsp the resEsp to set
	 */
	public void setResEsp(String resEsp) {
		this.resEsp = resEsp;
	}

	/**
	 * @return the resSc
	 */
	public String getResSc() {
		return resSc;
	}

	/**
	 * @param resSc the resSc to set
	 */
	public void setResSc(String resSc) {
		this.resSc = resSc;
	}

	/**
	 * @return the resP
	 */
	public String getResP() {
		return resP;
	}

	/**
	 * @param resP the resP to set
	 */
	public void setResP(String resP) {
		this.resP = resP;
	}

	/**
	 * @return the resInt
	 */
	public String getResInt() {
		return resInt;
	}

	/**
	 * @param resInt the resInt to set
	 */
	public void setResInt(String resInt) {
		this.resInt = resInt;
	}

	/**
	 * @return the modalitaInvio
	 */
	public String getModalitaInvio() {
		return modalitaInvio;
	}

	/**
	 * @param modalitaInvio the modalitaInvio to set
	 */
	public void setModalitaInvio(String modalitaInvio) {
		this.modalitaInvio = modalitaInvio;
	}

	/**
	 * @return the indirizzoSpedizione
	 */
	public String getIndirizzoSpedizione() {
		return indirizzoSpedizione;
	}

	/**
	 * @param indirizzoSpedizione the indirizzoSpedizione to set
	 */
	public void setIndirizzoSpedizione(String indirizzoSpedizione) {
		this.indirizzoSpedizione = indirizzoSpedizione;
	}

	/**
	 * @return the civicoSpedizione
	 */
	public String getCivicoSpedizione() {
		return civicoSpedizione;
	}

	/**
	 * @param civicoSpedizione the civicoSpedizione to set
	 */
	public void setCivicoSpedizione(String civicoSpedizione) {
		this.civicoSpedizione = civicoSpedizione;
	}

	/**
	 * @return the espSpedizione
	 */
	public String getEspSpedizione() {
		return espSpedizione;
	}

	/**
	 * @param espSpedizione the espSpedizione to set
	 */
	public void setEspSpedizione(String espSpedizione) {
		this.espSpedizione = espSpedizione;
	}

	/**
	 * @return the comuneSpedizione
	 */
	public String getComuneSpedizione() {
		return comuneSpedizione;
	}

	/**
	 * @param comuneSpedizione the comuneSpedizione to set
	 */
	public void setComuneSpedizione(String comuneSpedizione) {
		this.comuneSpedizione = comuneSpedizione;
	}

	/**
	 * @return the provinciaSpedizione
	 */
	public String getProvinciaSpedizione() {
		return provinciaSpedizione;
	}

	/**
	 * @param provinciaSpedizione the provinciaSpedizione to set
	 */
	public void setProvinciaSpedizione(String provinciaSpedizione) {
		this.provinciaSpedizione = provinciaSpedizione;
	}

	/**
	 * @return the ruolo
	 */
	public String getRuolo() {
		return ruolo;
	}

	/**
	 * @param ruolo the ruolo to set
	 */
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	/**
	 * @return the nomeSocieta
	 */
	public String getNomeSocieta() {
		return nomeSocieta;
	}

	/**
	 * @param nomeSocieta the nomeSocieta to set
	 */
	public void setNomeSocieta(String nomeSocieta) {
		this.nomeSocieta = nomeSocieta;
	}

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {
		return partitaIva;
	}

	/**
	 * @param partitaIva the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	/**
	 * @return the comuneSedeLegale
	 */
	public String getComuneSedeLegale() {
		return comuneSedeLegale;
	}

	/**
	 * @param comuneSedeLegale the comuneSedeLegale to set
	 */
	public void setComuneSedeLegale(String comuneSedeLegale) {
		this.comuneSedeLegale = comuneSedeLegale;
	}

	/**
	 * @return the numeroSedeLegale
	 */
	public String getNumeroSedeLegale() {
		return numeroSedeLegale;
	}

	/**
	 * @param numeroSedeLegale the numeroSedeLegale to set
	 */
	public void setNumeroSedeLegale(String numeroSedeLegale) {
		this.numeroSedeLegale = numeroSedeLegale;
	}

	/**
	 * @return the espSedeLegale
	 */
	public String getEspSedeLegale() {
		return espSedeLegale;
	}

	/**
	 * @param espSedeLegale the espSedeLegale to set
	 */
	public void setEspSedeLegale(String espSedeLegale) {
		this.espSedeLegale = espSedeLegale;
	}

	/**
	 * @return the scSedeLegale
	 */
	public String getScSedeLegale() {
		return scSedeLegale;
	}

	/**
	 * @param scSedeLegale the scSedeLegale to set
	 */
	public void setScSedeLegale(String scSedeLegale) {
		this.scSedeLegale = scSedeLegale;
	}

	/**
	 * @return the pSedeLegale
	 */
	public String getpSedeLegale() {
		return pSedeLegale;
	}

	/**
	 * @param pSedeLegale the pSedeLegale to set
	 */
	public void setpSedeLegale(String pSedeLegale) {
		this.pSedeLegale = pSedeLegale;
	}

	/**
	 * @return the intSedeLegale
	 */
	public String getIntSedeLegale() {
		return intSedeLegale;
	}

	/**
	 * @param intSedeLegale the intSedeLegale to set
	 */
	public void setIntSedeLegale(String intSedeLegale) {
		this.intSedeLegale = intSedeLegale;
	}

	/**
	 * @return the viaSedeLegale
	 */
	public String getViaSedeLegale() {
		return viaSedeLegale;
	}

	/**
	 * @param viaSedeLegale the viaSedeLegale to set
	 */
	public void setViaSedeLegale(String viaSedeLegale) {
		this.viaSedeLegale = viaSedeLegale;
	}

	/**
	 * @return the pertinenza
	 */
	public boolean isPertinenza() {
		return pertinenza;
	}

	/**
	 * @param pertinenza the pertinenza to set
	 */
	public void setPertinenza(boolean pertinenza) {
		this.pertinenza = pertinenza;
	}

	/**
	 * @return the nonResidente
	 */
	public boolean isNonResidente() {
		return nonResidente;
	}

	/**
	 * @param nonResidente the nonResidente to set
	 */
	public void setNonResidente(boolean nonResidente) {
		this.nonResidente = nonResidente;
	}

	/**
	 * @return the totaleNucleoInResidenza
	 */
	public String getTotaleNucleoInResidenza() {
		return totaleNucleoInResidenza;
	}

	/**
	 * @param totaleNucleoInResidenza the totaleNucleoInResidenza to set
	 */
	public void setTotaleNucleoInResidenza(String totaleNucleoInResidenza) {
		this.totaleNucleoInResidenza = totaleNucleoInResidenza;
	}

	/**
	 * @return the nonResidenteNucleoFam
	 */
	public boolean isNonResidenteNucleoFam() {
		return nonResidenteNucleoFam;
	}

	/**
	 * @param nonResidenteNucleoFam the nonResidenteNucleoFam to set
	 */
	public void setNonResidenteNucleoFam(boolean nonResidenteNucleoFam) {
		this.nonResidenteNucleoFam = nonResidenteNucleoFam;
	}

	/**
	 * @return the noteNonResidente
	 */
	public String getNoteNonResidente() {
		return noteNonResidente;
	}

	/**
	 * @param noteNonResidente the noteNonResidente to set
	 */
	public void setNoteNonResidente(String noteNonResidente) {
		this.noteNonResidente = noteNonResidente;
	}

	/**
	 * @return the totaleNucleoFamiliare
	 */
	public String getTotaleNucleoFamiliare() {
		return totaleNucleoFamiliare;
	}

	/**
	 * @param totaleNucleoFamiliare the totaleNucleoFamiliare to set
	 */
	public void setTotaleNucleoFamiliare(String totaleNucleoFamiliare) {
		this.totaleNucleoFamiliare = totaleNucleoFamiliare;
	}

	/**
	 * @return the detenzioneImmobile
	 */
	public boolean isDetenzioneImmobile() {
		return detenzioneImmobile;
	}

	/**
	 * @param detenzioneImmobile the detenzioneImmobile to set
	 */
	public void setDetenzioneImmobile(boolean detenzioneImmobile) {
		this.detenzioneImmobile = detenzioneImmobile;
	}

	/**
	 * @return the noteRiduzione
	 */
	public String getNoteRiduzione() {
		return noteRiduzione;
	}

	/**
	 * @param noteRiduzione the noteRiduzione to set
	 */
	public void setNoteRiduzione(String noteRiduzione) {
		this.noteRiduzione = noteRiduzione;
	}

	/**
	 * @return the attivitaPrevalente
	 */
	public String getAttivitaPrevalente() {
		return attivitaPrevalente;
	}

	/**
	 * @param attivitaPrevalente the attivitaPrevalente to set
	 */
	public void setAttivitaPrevalente(String attivitaPrevalente) {
		this.attivitaPrevalente = attivitaPrevalente;
	}

	/**
	 * @return the codiceAteco
	 */
	public String getCodiceAteco() {
		return codiceAteco;
	}

	/**
	 * @param codiceAteco the codiceAteco to set
	 */
	public void setCodiceAteco(String codiceAteco) {
		this.codiceAteco = codiceAteco;
	}

	/**
	 * @return the diSuperficie
	 */
	public boolean isDiSuperficie() {
		return diSuperficie;
	}

	/**
	 * @param diSuperficie the diSuperficie to set
	 */
	public void setDiSuperficie(boolean diSuperficie) {
		this.diSuperficie = diSuperficie;
	}

	/**
	 * @return the varCompNucleoFam
	 */
	public boolean isVarCompNucleoFam() {
		return varCompNucleoFam;
	}

	/**
	 * @param varCompNucleoFam the varCompNucleoFam to set
	 */
	public void setVarCompNucleoFam(boolean varCompNucleoFam) {
		this.varCompNucleoFam = varCompNucleoFam;
	}

	/**
	 * @return the varCompNuclDa
	 */
	public String getVarCompNuclDa() {
		return varCompNuclDa;
	}

	/**
	 * @param varCompNuclDa the varCompNuclDa to set
	 */
	public void setVarCompNuclDa(String varCompNuclDa) {
		this.varCompNuclDa = varCompNuclDa;
	}

	/**
	 * @return the varCompNuclA
	 */
	public String getVarCompNuclA() {
		return varCompNuclA;
	}

	/**
	 * @param varCompNuclA the varCompNuclA to set
	 */
	public void setVarCompNuclA(String varCompNuclA) {
		this.varCompNuclA = varCompNuclA;
	}

	/**
	 * @return the varCompNuclNote
	 */
	public String getVarCompNuclNote() {
		return varCompNuclNote;
	}

	/**
	 * @param varCompNuclNote the varCompNuclNote to set
	 */
	public void setVarCompNuclNote(String varCompNuclNote) {
		this.varCompNuclNote = varCompNuclNote;
	}

	/**
	 * @return the varDatiAnagrafici
	 */
	public boolean isVarDatiAnagrafici() {
		return varDatiAnagrafici;
	}

	/**
	 * @param varDatiAnagrafici the varDatiAnagrafici to set
	 */
	public void setVarDatiAnagrafici(boolean varDatiAnagrafici) {
		this.varDatiAnagrafici = varDatiAnagrafici;
	}

	/**
	 * @return the varDatiAnagraficiNote
	 */
	public String getVarDatiAnagraficiNote() {
		return varDatiAnagraficiNote;
	}

	/**
	 * @param varDatiAnagraficiNote the varDatiAnagraficiNote to set
	 */
	public void setVarDatiAnagraficiNote(String varDatiAnagraficiNote) {
		this.varDatiAnagraficiNote = varDatiAnagraficiNote;
	}

	/**
	 * @return the agevolazioniTipologiaDom
	 */
	public String getAgevolazioniTipologiaDom() {
		return agevolazioniTipologiaDom;
	}

	/**
	 * @param agevolazioniTipologiaDom the agevolazioniTipologiaDom to set
	 */
	public void setAgevolazioniTipologiaDom(String agevolazioniTipologiaDom) {
		this.agevolazioniTipologiaDom = agevolazioniTipologiaDom;
	}

	/**
	 * @return the altroDom
	 */
	public boolean isAltroDom() {
		return altroDom;
	}

	/**
	 * @param altroDom the altroDom to set
	 */
	public void setAltroDom(boolean altroDom) {
		this.altroDom = altroDom;
	}

	/**
	 * @return the altroNoteDom
	 */
	public String getAltroNoteDom() {
		return altroNoteDom;
	}

	/**
	 * @param altroNoteDom the altroNoteDom to set
	 */
	public void setAltroNoteDom(String altroNoteDom) {
		this.altroNoteDom = altroNoteDom;
	}

	/**
	 * @return the agevolazioniTipologiaNonDom
	 */
	public String getAgevolazioniTipologiaNonDom() {
		return agevolazioniTipologiaNonDom;
	}

	/**
	 * @param agevolazioniTipologiaNonDom the agevolazioniTipologiaNonDom to set
	 */
	public void setAgevolazioniTipologiaNonDom(String agevolazioniTipologiaNonDom) {
		this.agevolazioniTipologiaNonDom = agevolazioniTipologiaNonDom;
	}

	/**
	 * @return the altroNonDom
	 */
	public boolean isAltroNonDom() {
		return altroNonDom;
	}

	/**
	 * @param altroNonDom the altroNonDom to set
	 */
	public void setAltroNonDom(boolean altroNonDom) {
		this.altroNonDom = altroNonDom;
	}

	/**
	 * @return the altroNoteNonDom
	 */
	public String getAltroNoteNonDom() {
		return altroNoteNonDom;
	}

	/**
	 * @param altroNoteNonDom the altroNoteNonDom to set
	 */
	public void setAltroNoteNonDom(String altroNoteNonDom) {
		this.altroNoteNonDom = altroNoteNonDom;
	}

	/**
	 * @return the altriMotiviDom
	 */
	public String getAltriMotiviDom() {
		return altriMotiviDom;
	}

	/**
	 * @param altriMotiviDom the altriMotiviDom to set
	 */
	public void setAltriMotiviDom(String altriMotiviDom) {
		this.altriMotiviDom = altriMotiviDom;
	}

	/**
	 * @return the nominativoDeceduto
	 */
	public String getNominativoDeceduto() {
		return nominativoDeceduto;
	}

	/**
	 * @param nominativoDeceduto the nominativoDeceduto to set
	 */
	public void setNominativoDeceduto(String nominativoDeceduto) {
		this.nominativoDeceduto = nominativoDeceduto;
	}

	/**
	 * @return the dataDecesso
	 */
	public String getDataDecesso() {
		return dataDecesso;
	}

	/**
	 * @param dataDecesso the dataDecesso to set
	 */
	public void setDataDecesso(String dataDecesso) {
		this.dataDecesso = dataDecesso;
	}

	/**
	 * @return the civicoCoabitazione
	 */
	public String getCivicoCoabitazione() {
		return civicoCoabitazione;
	}

	/**
	 * @param civicoCoabitazione the civicoCoabitazione to set
	 */
	public void setCivicoCoabitazione(String civicoCoabitazione) {
		this.civicoCoabitazione = civicoCoabitazione;
	}

	/**
	 * @return the indirizzoCoabitazione
	 */
	public String getIndirizzoCoabitazione() {
		return indirizzoCoabitazione;
	}

	/**
	 * @param indirizzoCoabitazione the indirizzoCoabitazione to set
	 */
	public void setIndirizzoCoabitazione(String indirizzoCoabitazione) {
		this.indirizzoCoabitazione = indirizzoCoabitazione;
	}

	/**
	 * @return the pianoCoabitazione
	 */
	public String getPianoCoabitazione() {
		return pianoCoabitazione;
	}

	/**
	 * @param pianoCoabitazione the pianoCoabitazione to set
	 */
	public void setPianoCoabitazione(String pianoCoabitazione) {
		this.pianoCoabitazione = pianoCoabitazione;
	}

	/**
	 * @return the intCoabitazione
	 */
	public String getIntCoabitazione() {
		return intCoabitazione;
	}

	/**
	 * @param intCoabitazione the intCoabitazione to set
	 */
	public void setIntCoabitazione(String intCoabitazione) {
		this.intCoabitazione = intCoabitazione;
	}

	/**
	 * @return the espCoabitazione
	 */
	public String getEspCoabitazione() {
		return espCoabitazione;
	}

	/**
	 * @param espCoabitazione the espCoabitazione to set
	 */
	public void setEspCoabitazione(String espCoabitazione) {
		this.espCoabitazione = espCoabitazione;
	}

	/**
	 * @return the suffCoabitazione
	 */
	public String getSuffCoabitazione() {
		return suffCoabitazione;
	}

	/**
	 * @param suffCoabitazione the suffCoabitazione to set
	 */
	public void setSuffCoabitazione(String suffCoabitazione) {
		this.suffCoabitazione = suffCoabitazione;
	}

	/**
	 * @return the coabitanteDom
	 */
	public String getCoabitanteDom() {
		return coabitanteDom;
	}

	/**
	 * @param coabitanteDom the coabitanteDom to set
	 */
	public void setCoabitanteDom(String coabitanteDom) {
		this.coabitanteDom = coabitanteDom;
	}

	/**
	 * @return the comuneEmigrazioneDom
	 */
	public String getComuneEmigrazioneDom() {
		return comuneEmigrazioneDom;
	}

	/**
	 * @param comuneEmigrazioneDom the comuneEmigrazioneDom to set
	 */
	public void setComuneEmigrazioneDom(String comuneEmigrazioneDom) {
		this.comuneEmigrazioneDom = comuneEmigrazioneDom;
	}

	/**
	 * @return the provEmigrazioneDom
	 */
	public String getProvEmigrazioneDom() {
		return provEmigrazioneDom;
	}

	/**
	 * @param provEmigrazioneDom the provEmigrazioneDom to set
	 */
	public void setProvEmigrazioneDom(String provEmigrazioneDom) {
		this.provEmigrazioneDom = provEmigrazioneDom;
	}

	/**
	 * @return the viaEmigrazioneDom
	 */
	public String getViaEmigrazioneDom() {
		return viaEmigrazioneDom;
	}

	/**
	 * @param viaEmigrazioneDom the viaEmigrazioneDom to set
	 */
	public void setViaEmigrazioneDom(String viaEmigrazioneDom) {
		this.viaEmigrazioneDom = viaEmigrazioneDom;
	}

	/**
	 * @return the civicoEmigrazioneDom
	 */
	public String getCivicoEmigrazioneDom() {
		return civicoEmigrazioneDom;
	}

	/**
	 * @param civicoEmigrazioneDom the civicoEmigrazioneDom to set
	 */
	public void setCivicoEmigrazioneDom(String civicoEmigrazioneDom) {
		this.civicoEmigrazioneDom = civicoEmigrazioneDom;
	}

	/**
	 * @return the concessioneQuoteDom
	 */
	public String getConcessioneQuoteDom() {
		return concessioneQuoteDom;
	}

	/**
	 * @param concessioneQuoteDom the concessioneQuoteDom to set
	 */
	public void setConcessioneQuoteDom(String concessioneQuoteDom) {
		this.concessioneQuoteDom = concessioneQuoteDom;
	}

	/**
	 * @return the modalitaRimborsoDom
	 */
	public String getModalitaRimborsoDom() {
		return modalitaRimborsoDom;
	}

	/**
	 * @param modalitaRimborsoDom the modalitaRimborsoDom to set
	 */
	public void setModalitaRimborsoDom(String modalitaRimborsoDom) {
		this.modalitaRimborsoDom = modalitaRimborsoDom;
	}

	/**
	 * @return the ibanDom
	 */
	public String getIbanDom() {
		return ibanDom;
	}

	/**
	 * @param ibanDom the ibanDom to set
	 */
	public void setIbanDom(String ibanDom) {
		this.ibanDom = ibanDom;
	}

	/**
	 * @return the altriMotiviNonDom
	 */
	public String getAltriMotiviNonDom() {
		return altriMotiviNonDom;
	}

	/**
	 * @param altriMotiviNonDom the altriMotiviNonDom to set
	 */
	public void setAltriMotiviNonDom(String altriMotiviNonDom) {
		this.altriMotiviNonDom = altriMotiviNonDom;
	}

	/**
	 * @return the coabitanteNonDom
	 */
	public String getCoabitanteNonDom() {
		return coabitanteNonDom;
	}

	/**
	 * @param coabitanteNonDom the coabitanteNonDom to set
	 */
	public void setCoabitanteNonDom(String coabitanteNonDom) {
		this.coabitanteNonDom = coabitanteNonDom;
	}

	/**
	 * @return the comuneEmigrazioneNonDom
	 */
	public String getComuneEmigrazioneNonDom() {
		return comuneEmigrazioneNonDom;
	}

	/**
	 * @param comuneEmigrazioneNonDom the comuneEmigrazioneNonDom to set
	 */
	public void setComuneEmigrazioneNonDom(String comuneEmigrazioneNonDom) {
		this.comuneEmigrazioneNonDom = comuneEmigrazioneNonDom;
	}

	/**
	 * @return the provEmigrazioneNonDom
	 */
	public String getProvEmigrazioneNonDom() {
		return provEmigrazioneNonDom;
	}

	/**
	 * @param provEmigrazioneNonDom the provEmigrazioneNonDom to set
	 */
	public void setProvEmigrazioneNonDom(String provEmigrazioneNonDom) {
		this.provEmigrazioneNonDom = provEmigrazioneNonDom;
	}

	/**
	 * @return the viaEmigrazioneNonDom
	 */
	public String getViaEmigrazioneNonDom() {
		return viaEmigrazioneNonDom;
	}

	/**
	 * @param viaEmigrazioneNonDom the viaEmigrazioneNonDom to set
	 */
	public void setViaEmigrazioneNonDom(String viaEmigrazioneNonDom) {
		this.viaEmigrazioneNonDom = viaEmigrazioneNonDom;
	}

	/**
	 * @return the civicoEmigrazioneNonDom
	 */
	public String getCivicoEmigrazioneNonDom() {
		return civicoEmigrazioneNonDom;
	}

	/**
	 * @param civicoEmigrazioneNonDom the civicoEmigrazioneNonDom to set
	 */
	public void setCivicoEmigrazioneNonDom(String civicoEmigrazioneNonDom) {
		this.civicoEmigrazioneNonDom = civicoEmigrazioneNonDom;
	}

	/**
	 * @return the concessioneQuoteNonDom
	 */
	public String getConcessioneQuoteNonDom() {
		return concessioneQuoteNonDom;
	}

	/**
	 * @param concessioneQuoteNonDom the concessioneQuoteNonDom to set
	 */
	public void setConcessioneQuoteNonDom(String concessioneQuoteNonDom) {
		this.concessioneQuoteNonDom = concessioneQuoteNonDom;
	}

	/**
	 * @return the modalitaRimborsoNonDom
	 */
	public String getModalitaRimborsoNonDom() {
		return modalitaRimborsoNonDom;
	}

	/**
	 * @param modalitaRimborsoNonDom the modalitaRimborsoNonDom to set
	 */
	public void setModalitaRimborsoNonDom(String modalitaRimborsoNonDom) {
		this.modalitaRimborsoNonDom = modalitaRimborsoNonDom;
	}

	/**
	 * @return the ibanNonDom
	 */
	public String getIbanNonDom() {
		return ibanNonDom;
	}

	/**
	 * @param ibanNonDom the ibanNonDom to set
	 */
	public void setIbanNonDom(String ibanNonDom) {
		this.ibanNonDom = ibanNonDom;
	}

	/**
	 * @return the attivitaCessata
	 */
	public String getAttivitaCessata() {
		return attivitaCessata;
	}

	/**
	 * @param attivitaCessata the attivitaCessata to set
	 */
	public void setAttivitaCessata(String attivitaCessata) {
		this.attivitaCessata = attivitaCessata;
	}

	/**
	 * @return the altroSpecificaRilascioImmobile
	 */
	public String getAltroSpecificaRilascioImmobile() {
		return altroSpecificaRilascioImmobile;
	}

	/**
	 * @param altroSpecificaRilascioImmobile the altroSpecificaRilascioImmobile to set
	 */
	public void setAltroSpecificaRilascioImmobile(String altroSpecificaRilascioImmobile) {
		this.altroSpecificaRilascioImmobile = altroSpecificaRilascioImmobile;
	}

	/**
	 * @return the vecchiaDenominazione
	 */
	public String getVecchiaDenominazione() {
		return vecchiaDenominazione;
	}

	/**
	 * @param vecchiaDenominazione the vecchiaDenominazione to set
	 */
	public void setVecchiaDenominazione(String vecchiaDenominazione) {
		this.vecchiaDenominazione = vecchiaDenominazione;
	}

	/**
	 * @return the nuovaDenominazione
	 */
	public String getNuovaDenominazione() {
		return nuovaDenominazione;
	}

	/**
	 * @param nuovaDenominazione the nuovaDenominazione to set
	 */
	public void setNuovaDenominazione(String nuovaDenominazione) {
		this.nuovaDenominazione = nuovaDenominazione;
	}

	/**
	 * @return the allegatoUno
	 */
	public String getAllegatoUno() {
		return allegatoUno;
	}

	/**
	 * @param allegatoUno the allegatoUno to set
	 */
	public void setAllegatoUno(String allegatoUno) {
		this.allegatoUno = allegatoUno;
	}

	/**
	 * @return the allegatoDue
	 */
	public String getAllegatoDue() {
		return allegatoDue;
	}

	/**
	 * @param allegatoDue the allegatoDue to set
	 */
	public void setAllegatoDue(String allegatoDue) {
		this.allegatoDue = allegatoDue;
	}

	/**
	 * @return the allegatoTre
	 */
	public String getAllegatoTre() {
		return allegatoTre;
	}

	/**
	 * @param allegatoTre the allegatoTre to set
	 */
	public void setAllegatoTre(String allegatoTre) {
		this.allegatoTre = allegatoTre;
	}

	/**
	 * @return the allegatoQuattro
	 */
	public String getAllegatoQuattro() {
		return allegatoQuattro;
	}

	/**
	 * @param allegatoQuattro the allegatoQuattro to set
	 */
	public void setAllegatoQuattro(String allegatoQuattro) {
		this.allegatoQuattro = allegatoQuattro;
	}

	/**
	 * @return the estremiDocumento
	 */
	public String getEstremiDocumento() {
		return estremiDocumento;
	}

	/**
	 * @param estremiDocumento the estremiDocumento to set
	 */
	public void setEstremiDocumento(String estremiDocumento) {
		this.estremiDocumento = estremiDocumento;
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
	 * @return the iscrizioneDom
	 */
	public List<DatiImmobile> getIscrizioneDom() {
		return iscrizioneDom;
	}

	/**
	 * @param iscrizioneDom the iscrizioneDom to set
	 */
	public void setIscrizioneDom(List<DatiImmobile> iscrizioneDom) {
		this.iscrizioneDom = iscrizioneDom;
	}

	/**
	 * @return the cessazioneDom
	 */
	public List<DatiImmobile> getCessazioneDom() {
		return cessazioneDom;
	}

	/**
	 * @param cessazioneDom the cessazioneDom to set
	 */
	public void setCessazioneDom(List<DatiImmobile> cessazioneDom) {
		this.cessazioneDom = cessazioneDom;
	}

	/**
	 * @return the variazioneDom
	 */
	public List<DatiImmobile> getVariazioneDom() {
		return variazioneDom;
	}

	/**
	 * @param variazioneDom the variazioneDom to set
	 */
	public void setVariazioneDom(List<DatiImmobile> variazioneDom) {
		this.variazioneDom = variazioneDom;
	}

	/**
	 * @return the iscrizioneNonDom
	 */
	public List<DatiImmobile> getIscrizioneNonDom() {
		return iscrizioneNonDom;
	}

	/**
	 * @param iscrizioneNonDom the iscrizioneNonDom to set
	 */
	public void setIscrizioneNonDom(List<DatiImmobile> iscrizioneNonDom) {
		this.iscrizioneNonDom = iscrizioneNonDom;
	}

	/**
	 * @return the cessazioneNonDom
	 */
	public List<DatiImmobile> getCessazioneNonDom() {
		return cessazioneNonDom;
	}

	/**
	 * @param cessazioneNonDom the cessazioneNonDom to set
	 */
	public void setCessazioneNonDom(List<DatiImmobile> cessazioneNonDom) {
		this.cessazioneNonDom = cessazioneNonDom;
	}

	/**
	 * @return the variazioneNonDom
	 */
	public List<DatiImmobile> getVariazioneNonDom() {
		return variazioneNonDom;
	}

	/**
	 * @param variazioneNonDom the variazioneNonDom to set
	 */
	public void setVariazioneNonDom(List<DatiImmobile> variazioneNonDom) {
		this.variazioneNonDom = variazioneNonDom;
	}

	/**
	 * @return the identificativoUtenzeSelezionateCessazione
	 */
	public String getIdentificativoUtenzeSelezionateCessazione() {
		return identificativoUtenzeSelezionateCessazione;
	}

	/**
	 * @param identificativoUtenzeSelezionateCessazione the
	 *        identificativoUtenzeSelezionateCessazione to set
	 */
	public void setIdentificativoUtenzeSelezionateCessazione(String identificativoUtenzeSelezionateCessazione) {
		this.identificativoUtenzeSelezionateCessazione = identificativoUtenzeSelezionateCessazione;
	}

	/**
	 * @return the identificativoUtenzeSelezionateVariazione
	 */
	public String getIdentificativoUtenzeSelezionateVariazione() {
		return identificativoUtenzeSelezionateVariazione;
	}

	/**
	 * @param identificativoUtenzeSelezionateVariazione the
	 *        identificativoUtenzeSelezionateVariazione to set
	 */
	public void setIdentificativoUtenzeSelezionateVariazione(String identificativoUtenzeSelezionateVariazione) {
		this.identificativoUtenzeSelezionateVariazione = identificativoUtenzeSelezionateVariazione;
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
	 * @return the noteGenerali
	 */
	public String getNoteGenerali() {
		return noteGenerali;
	}

	/**
	 * @param noteGenerali the noteGenerali to set
	 */
	public void setNoteGenerali(String noteGenerali) {
		this.noteGenerali = noteGenerali;
	}

	/**
	 * @return the tipologiaRiduzioneDom
	 */
	public String[] getTipologiaRiduzioneDom() {
		return tipologiaRiduzioneDom;
	}

	/**
	 * @param tipologiaRiduzioneDom the tipologiaRiduzioneDom to set
	 */
	public void setTipologiaRiduzioneDom(String[] tipologiaRiduzioneDom) {
		this.tipologiaRiduzioneDom = tipologiaRiduzioneDom;
	}

	/**
	 * @return the tipologiaRichiestaDom
	 */
	public String getTipologiaRichiestaDom() {
		return tipologiaRichiestaDom;
	}

	/**
	 * @param tipologiaRichiestaDom the tipologiaRichiestaDom to set
	 */
	public void setTipologiaRichiestaDom(String tipologiaRichiestaDom) {
		this.tipologiaRichiestaDom = tipologiaRichiestaDom;
	}

	/**
	 * @return the tipologiaRiduzioneNonDom
	 */
	public String[] getTipologiaRiduzioneNonDom() {
		return tipologiaRiduzioneNonDom;
	}

	/**
	 * @param tipologiaRiduzioneNonDom the tipologiaRiduzioneNonDom to set
	 */
	public void setTipologiaRiduzioneNonDom(String[] tipologiaRiduzioneNonDom) {
		this.tipologiaRiduzioneNonDom = tipologiaRiduzioneNonDom;
	}

	/**
	 * @return the tipologiaRichiestaNonDom
	 */
	public String getTipologiaRichiestaNonDom() {
		return tipologiaRichiestaNonDom;
	}

	/**
	 * @param tipologiaRichiestaNonDom the tipologiaRichiestaNonDom to set
	 */
	public void setTipologiaRichiestaNonDom(String tipologiaRichiestaNonDom) {
		this.tipologiaRichiestaNonDom = tipologiaRichiestaNonDom;
	}

	/**
	 * @return the occupanti
	 */
	public List<Occupante> getOccupanti() {
		return occupanti;
	}

	/**
	 * @param occupanti the occupanti to set
	 */
	public void setOccupanti(List<Occupante> occupanti) {
		this.occupanti = occupanti;
	}

	public List<Occupante> getVariazioneOccupanti() {
		return variazioneOccupanti;
	}

	public void setVariazioneOccupanti(List<Occupante> variazioneOccupanti) {
		this.variazioneOccupanti = variazioneOccupanti;
	}

	/**
	 * @return the motivoCessazioneDom
	 */
	public String getMotivoCessazioneDom() {
		return motivoCessazioneDom;
	}

	/**
	 * @param motivoCessazioneDom the motivoCessazioneDom to set
	 */
	public void setMotivoCessazioneDom(String motivoCessazioneDom) {
		this.motivoCessazioneDom = motivoCessazioneDom;
	}

	/**
	 * @return the motivoCessazioneNonDom
	 */
	public String getMotivoCessazioneNonDom() {
		return motivoCessazioneNonDom;
	}

	/**
	 * @param motivoCessazioneNonDom the motivoCessazioneNonDom to set
	 */
	public void setMotivoCessazioneNonDom(String motivoCessazioneNonDom) {
		this.motivoCessazioneNonDom = motivoCessazioneNonDom;
	}

	/**
	 * @return the specificaRilascioImmobile
	 */
	public String getSpecificaRilascioImmobile() {
		return specificaRilascioImmobile;
	}

	/**
	 * @param specificaRilascioImmobile the specificaRilascioImmobile to set
	 */
	public void setSpecificaRilascioImmobile(String specificaRilascioImmobile) {
		this.specificaRilascioImmobile = specificaRilascioImmobile;
	}

	/**
	 * @return the indirizzoCoabitazioneTextHidden
	 */
	public String getIndirizzoCoabitazioneTextHidden() {
		return indirizzoCoabitazioneTextHidden;
	}

	/**
	 * @param indirizzoCoabitazioneTextHidden the indirizzoCoabitazioneTextHidden to set
	 */
	public void setIndirizzoCoabitazioneTextHidden(String indirizzoCoabitazioneTextHidden) {
		this.indirizzoCoabitazioneTextHidden = indirizzoCoabitazioneTextHidden;
	}

	/**
	 * @return the civicoCoabitazioneTextHidden
	 */
	public String getCivicoCoabitazioneTextHidden() {
		return civicoCoabitazioneTextHidden;
	}

	/**
	 * @param civicoCoabitazioneTextHidden the civicoCoabitazioneTextHidden to set
	 */
	public void setCivicoCoabitazioneTextHidden(String civicoCoabitazioneTextHidden) {
		this.civicoCoabitazioneTextHidden = civicoCoabitazioneTextHidden;
	}

	/**
	 * @return the scalaCoabitazione
	 */
	public String getScalaCoabitazione() {
		return scalaCoabitazione;
	}

	/**
	 * @param scalaCoabitazione the scalaCoabitazione to set
	 */
	public void setScalaCoabitazione(String scalaCoabitazione) {
		this.scalaCoabitazione = scalaCoabitazione;
	}

	/**
	 * @return the esponenteEmigrazioneDom
	 */
	public String getEsponenteEmigrazioneDom() {
		return esponenteEmigrazioneDom;
	}

	/**
	 * @param esponenteEmigrazioneDom the esponenteEmigrazioneDom to set
	 */
	public void setEsponenteEmigrazioneDom(String esponenteEmigrazioneDom) {
		this.esponenteEmigrazioneDom = esponenteEmigrazioneDom;
	}

	public String getComuneEmigrazioneDomHidden() {
		return comuneEmigrazioneDomHidden;
	}

	public void setComuneEmigrazioneDomHidden(String comuneEmigrazioneDomHidden) {
		this.comuneEmigrazioneDomHidden = comuneEmigrazioneDomHidden;
	}

	/**
	 * @return the esponenteEmigrazioneNonDom
	 */
	public String getEsponenteEmigrazioneNonDom() {
		return esponenteEmigrazioneNonDom;
	}

	/**
	 * @param esponenteEmigrazioneNonDom the esponenteEmigrazioneNonDom to set
	 */
	public void setEsponenteEmigrazioneNonDom(String esponenteEmigrazioneNonDom) {
		this.esponenteEmigrazioneNonDom = esponenteEmigrazioneNonDom;
	}

	/**
	 * @return the comuneNascitaHidden
	 */
	public String getComuneNascitaHidden() {
		return comuneNascitaHidden;
	}

	/**
	 * @param comuneNascitaHidden the comuneNascitaHidden to set
	 */
	public void setComuneNascitaHidden(String comuneNascitaHidden) {
		this.comuneNascitaHidden = comuneNascitaHidden;
	}

	/**
	 * @return the comuneResidenzaHidden
	 */
	public String getComuneResidenzaHidden() {
		return comuneResidenzaHidden;
	}

	/**
	 * @param comuneResidenzaHidden the comuneResidenzaHidden to set
	 */
	public void setComuneResidenzaHidden(String comuneResidenzaHidden) {
		this.comuneResidenzaHidden = comuneResidenzaHidden;
	}

	/**
	 * @return the comuneSedeLegaleHidden
	 */
	public String getComuneSedeLegaleHidden() {
		return comuneSedeLegaleHidden;
	}

	/**
	 * @param comuneSedeLegaleHidden the comuneSedeLegaleHidden to set
	 */
	public void setComuneSedeLegaleHidden(String comuneSedeLegaleHidden) {
		this.comuneSedeLegaleHidden = comuneSedeLegaleHidden;
	}

	/**
	 * @return the comuneSpedizioneHidden
	 */
	public String getComuneSpedizioneHidden() {
		return comuneSpedizioneHidden;
	}

	/**
	 * @param comuneSpedizioneHidden the comuneSpedizioneHidden to set
	 */
	public void setComuneSpedizioneHidden(String comuneSpedizioneHidden) {
		this.comuneSpedizioneHidden = comuneSpedizioneHidden;
	}

	/**
	 * @return the residente
	 */
	public boolean isResidente() {
		return residente;
	}

	/**
	 * @param residente the residente to set
	 */
	public void setResidente(boolean residente) {
		this.residente = residente;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {
		return sesso;
	}

	/**
	 * @param sesso the sesso to set
	 */
	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	/**
	 * @return the numeroSedeLegaleTextHidden
	 */
	public String getNumeroSedeLegaleTextHidden() {
		return numeroSedeLegaleTextHidden;
	}

	/**
	 * @param numeroSedeLegaleTextHidden the numeroSedeLegaleTextHidden to set
	 */
	public void setNumeroSedeLegaleTextHidden(String numeroSedeLegaleTextHidden) {
		this.numeroSedeLegaleTextHidden = numeroSedeLegaleTextHidden;
	}

	/**
	 * @return the viaSedeLegaleTextHidden
	 */
	public String getViaSedeLegaleTextHidden() {
		return viaSedeLegaleTextHidden;
	}

	/**
	 * @param viaSedeLegaleTextHidden the viaSedeLegaleTextHidden to set
	 */
	public void setViaSedeLegaleTextHidden(String viaSedeLegaleTextHidden) {
		this.viaSedeLegaleTextHidden = viaSedeLegaleTextHidden;
	}

	/**
	 * @return the capCoabitazione
	 */
	public String getCapCoabitazione() {
		return capCoabitazione;
	}

	/**
	 * @param capCoabitazione the capCoabitazione to set
	 */
	public void setCapCoabitazione(String capCoabitazione) {
		this.capCoabitazione = capCoabitazione;
	}

	/**
	 * @return the riduzioniDomestiche
	 */
	public List<Riduzione> getRiduzioniDomestiche() {
		return riduzioniDomestiche;
	}

	/**
	 * @return the riduzioniDomestiche
	 */
	public List<Riduzione> getRiduzioniDomesticheIscrizione() {
		return riduzioniDomesticheIscrizione;
	}

	/**
	 * @param riduzioniDomestiche the riduzioniDomestiche to set
	 */
	public void setRiduzioniDomesticheIscrizione(List<Riduzione> riduzioniDomesticheIscrizione) {
		this.riduzioniDomesticheIscrizione = riduzioniDomesticheIscrizione;
	}

	/**
	 * @return the riduzioniDomestiche
	 */
	public List<Riduzione> getRiduzioniDomesticheVariazione() {
		return riduzioniDomesticheVariazione;
	}

	/**
	 * @param riduzioniDomestiche the riduzioniDomestiche to set
	 */
	public void setRiduzioniDomesticheVariazione(List<Riduzione> riduzioniDomesticheVariazione) {
		this.riduzioniDomesticheVariazione = riduzioniDomesticheVariazione;
	}

	/**
	 * @param riduzioniDomestiche the riduzioniDomestiche to set
	 */
	public void setRiduzioniDomestiche(List<Riduzione> riduzioniDomestiche) {
		this.riduzioniDomestiche = riduzioniDomestiche;
	}

	/**
	 * @return the riduzioniNonDomestiche
	 */
	public List<Riduzione> getRiduzioniNonDomestiche() {
		return riduzioniNonDomestiche;
	}

	/**
	 * @param riduzioniNonDomestiche the riduzioniNonDomestiche to set
	 */
	public void setRiduzioniNonDomestiche(List<Riduzione> riduzioniNonDomestiche) {
		this.riduzioniNonDomestiche = riduzioniNonDomestiche;
	}

	public List<Riduzione> getRiduzioniNonDomesticheIscrizione() {
		return riduzioniNonDomesticheIscrizione;
	}

	public void setRiduzioniNonDomesticheIscrizione(List<Riduzione> riduzioniNonDomesticheIscrizione) {
		this.riduzioniNonDomesticheIscrizione = riduzioniNonDomesticheIscrizione;
	}

	public List<Riduzione> getRiduzioniNonDomesticheVariazione() {
		return riduzioniNonDomesticheVariazione;
	}

	public void setRiduzioniNonDomesticheVariazione(List<Riduzione> riduzioniNonDomesticheVariazione) {
		this.riduzioniNonDomesticheVariazione = riduzioniNonDomesticheVariazione;
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

	/**
	 * @return the provinciaRea
	 */
	public String getProvinciaRea() {
		return provinciaRea;
	}

	/**
	 * @param provinciaRea the provinciaRea to set
	 */
	public void setProvinciaRea(String provinciaRea) {
		this.provinciaRea = provinciaRea;
	}

	/**
	 * @return the capResidenza
	 */
	public String getCapResidenza() {
		return capResidenza;
	}

	/**
	 * @param capResidenza the capResidenza to set
	 */
	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	/**
	 * @return the capSpedizione
	 */
	public String getCapSpedizione() {
		return capSpedizione;
	}

	/**
	 * @param capSpedizione the capSpedizione to set
	 */
	public void setCapSpedizione(String capSpedizione) {
		this.capSpedizione = capSpedizione;
	}

	/**
	 * @return the capSedeLegale
	 */
	public String getCapSedeLegale() {
		return capSedeLegale;
	}

	/**
	 * @param capSedeLegale the capSedeLegale to set
	 */
	public void setCapSedeLegale(String capSedeLegale) {
		this.capSedeLegale = capSedeLegale;
	}

	/**
	 * @return the capEmigrazioneDom
	 */
	public String getCapEmigrazioneDom() {
		return capEmigrazioneDom;
	}

	/**
	 * @param capEmigrazioneDom the capEmigrazioneDom to set
	 */
	public void setCapEmigrazioneDom(String capEmigrazioneDom) {
		this.capEmigrazioneDom = capEmigrazioneDom;
	}

	/**
	 * @return the capEmigrazioneNonDom
	 */
	public String getCapEmigrazioneNonDom() {
		return capEmigrazioneNonDom;
	}

	/**
	 * @param capEmigrazioneNonDom the capEmigrazioneNonDom to set
	 */
	public void setCapEmigrazioneNonDom(String capEmigrazioneNonDom) {
		this.capEmigrazioneNonDom = capEmigrazioneNonDom;
	}

	public boolean isLimitatoIscrizione() {
		return limitatoIscrizione;
	}

	public void setLimitatoIscrizione(boolean limitatoIscrizione) {
		this.limitatoIscrizione = limitatoIscrizione;
	}

	public String getIdContribuente() {
		return idContribuente;
	}

	public void setIdContribuente(String idContribuente) {
		this.idContribuente = idContribuente;
	}

	public boolean isEscludiDomestiche() {
		return escludiDomestiche;
	}

	public void setEscludiDomestiche(boolean escludiDomestiche) {
		this.escludiDomestiche = escludiDomestiche;
	}

	public boolean isEscludiCommerciali() {
		return escludiCommerciali;
	}

	public void setEscludiCommerciali(boolean escludiCommerciali) {
		this.escludiCommerciali = escludiCommerciali;
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

	/**
	 * @return the identificativoNucleoFamiliare
	 */
	public Integer getIdentificativoNucleoFamiliare() {
		return identificativoNucleoFamiliare;
	}

	/**
	 * @param identificativoNucleoFamiliare the identificativoNucleoFamiliare to set
	 */
	public void setIdentificativoNucleoFamiliare(Integer identificativoNucleoFamiliare) {
		this.identificativoNucleoFamiliare = identificativoNucleoFamiliare;
	}

	/**
	 * @return the indirizzoSpedizioneConStradario
	 */
	public boolean isIndirizzoSpedizioneConStradario() {
		return indirizzoSpedizioneConStradario;
	}

	/**
	 * @param indirizzoSpedizioneConStradario the indirizzoSpedizioneConStradario to set
	 */
	public void setIndirizzoSpedizioneConStradario(boolean indirizzoSpedizioneConStradario) {
		this.indirizzoSpedizioneConStradario = indirizzoSpedizioneConStradario;
	}

	/**
	 * @return the indirizzoSpedizioneTextHidden
	 */
	public String getIndirizzoSpedizioneTextHidden() {
		return indirizzoSpedizioneTextHidden;
	}

	/**
	 * @param indirizzoSpedizioneTextHidden the indirizzoSpedizioneTextHidden to set
	 */
	public void setIndirizzoSpedizioneTextHidden(String indirizzoSpedizioneTextHidden) {
		this.indirizzoSpedizioneTextHidden = indirizzoSpedizioneTextHidden;
	}

	/**
	 * @return the civicoSpedizioneTextHidden
	 */
	public String getCivicoSpedizioneTextHidden() {
		return civicoSpedizioneTextHidden;
	}

	/**
	 * @param civicoSpedizioneTextHidden the civicoSpedizioneTextHidden to set
	 */
	public void setCivicoSpedizioneTextHidden(String civicoSpedizioneTextHidden) {
		this.civicoSpedizioneTextHidden = civicoSpedizioneTextHidden;
	}

	public void setUuidOperazione(String uuidOperazione) {
		this.uuidOperazione = uuidOperazione;
	}
	public String getUuidOperazione() {
		return uuidOperazione;
	}
	
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getIpAddress() {
		return ipAddress;
	}
}
