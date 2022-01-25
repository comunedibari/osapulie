package it.osapulie.tributi.web.portlet.cessazionetari.form;

import java.io.Serializable;

public class DatiCessazioneTari implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String telefono;
	private String fax;
	private String email;
	private String pec;
	private String dataNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String comuneResidenza;
	private String indirizzoResidenza;
	private String provResidenza;
	private String civicoResidenza;
	private String capResidenza;

	private String tipoUtenza; // DOMESTICA o NON DOMESTICA

	private String ruolo;
	private String nomeSocieta;
	private String partitaIva;
	private String cittaSedeLegale;
	private String civicoSedeLegale;
	private String capSedeLegale;
	private String viaSedeLegale;

	private String viaUnitaImmobiliare;
	private String civicoUnitaImmobiliare;
	private String fgUnitaImmobiliare;
	private String numUnitaImmobiliare;
	private String subUnitaImmobiliare;

	private String[] motivi;
	private String altriMotivi;
	private String soggettoDuplicazione;
	private String comuneEmigrazione;
	private String provEmigrazione;
	private String viaEmigrazione;
	private String civicoEmigrazione;

	// Caso utenze DOMESTICHE
	private String nominativoDeceduto;
	private String dataDecesso;

	// Caso utenze NON DOMESTICHE
	private String attivitaCessata;

	private String[] allegati;
	private String altriAllegati;
	private String estremiDocumento;

	private boolean flagDelega;
	private String nominativoDelegato;
	private String dataCompilazioneRichiesta;

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

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPec() {
		return pec;
	}

	public void setPec(String pec) {
		this.pec = pec;
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

	public String getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(String indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getProvResidenza() {
		return provResidenza;
	}

	public void setProvResidenza(String provResidenza) {
		this.provResidenza = provResidenza;
	}

	public String getCivicoResidenza() {
		return civicoResidenza;
	}

	public void setCivicoResidenza(String civicoResidenza) {
		this.civicoResidenza = civicoResidenza;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getTipoUtenza() {
		return tipoUtenza;
	}

	public void setTipoUtenza(String tipoUtenza) {
		this.tipoUtenza = tipoUtenza;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getNomeSocieta() {
		return nomeSocieta;
	}

	public void setNomeSocieta(String nomeSocieta) {
		this.nomeSocieta = nomeSocieta;
	}

	public String getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getCivicoSedeLegale() {
		return civicoSedeLegale;
	}

	public void setCivicoSedeLegale(String civicoSedeLegale) {
		this.civicoSedeLegale = civicoSedeLegale;
	}

	public String getCapSedeLegale() {
		return capSedeLegale;
	}

	public void setCapSedeLegale(String capSedeLegale) {
		this.capSedeLegale = capSedeLegale;
	}

	public String getViaSedeLegale() {
		return viaSedeLegale;
	}

	public void setViaSedeLegale(String viaSedeLegale) {
		this.viaSedeLegale = viaSedeLegale;
	}

	public String getViaUnitaImmobiliare() {
		return viaUnitaImmobiliare;
	}

	public void setViaUnitaImmobiliare(String viaUnitaImmobiliare) {
		this.viaUnitaImmobiliare = viaUnitaImmobiliare;
	}

	public String getCivicoUnitaImmobiliare() {
		return civicoUnitaImmobiliare;
	}

	public void setCivicoUnitaImmobiliare(String civicoUnitaImmobiliare) {
		this.civicoUnitaImmobiliare = civicoUnitaImmobiliare;
	}

	public String getFgUnitaImmobiliare() {
		return fgUnitaImmobiliare;
	}

	public void setFgUnitaImmobiliare(String fgUnitaImmobiliare) {
		this.fgUnitaImmobiliare = fgUnitaImmobiliare;
	}

	public String getNumUnitaImmobiliare() {
		return numUnitaImmobiliare;
	}

	public void setNumUnitaImmobiliare(String numUnitaImmobiliare) {
		this.numUnitaImmobiliare = numUnitaImmobiliare;
	}

	public String getSubUnitaImmobiliare() {
		return subUnitaImmobiliare;
	}

	public void setSubUnitaImmobiliare(String subUnitaImmobiliare) {
		this.subUnitaImmobiliare = subUnitaImmobiliare;
	}

	public String[] getMotivi() {
		return motivi;
	}

	public void setMotivi(String[] motivi) {
		this.motivi = motivi;
	}

	public String getAltriMotivi() {
		return altriMotivi;
	}

	public void setAltriMotivi(String altriMotivi) {
		this.altriMotivi = altriMotivi;
	}

	public String getSoggettoDuplicazione() {
		return soggettoDuplicazione;
	}

	public void setSoggettoDuplicazione(String soggettoDuplicazione) {
		this.soggettoDuplicazione = soggettoDuplicazione;
	}

	public String getComuneEmigrazione() {
		return comuneEmigrazione;
	}

	public void setComuneEmigrazione(String comuneEmigrazione) {
		this.comuneEmigrazione = comuneEmigrazione;
	}

	public String getProvEmigrazione() {
		return provEmigrazione;
	}

	public void setProvEmigrazione(String provEmigrazione) {
		this.provEmigrazione = provEmigrazione;
	}

	public String getViaEmigrazione() {
		return viaEmigrazione;
	}

	public void setViaEmigrazione(String viaEmigrazione) {
		this.viaEmigrazione = viaEmigrazione;
	}

	public String getCivicoEmigrazione() {
		return civicoEmigrazione;
	}

	public void setCivicoEmigrazione(String civicoEmigrazione) {
		this.civicoEmigrazione = civicoEmigrazione;
	}

	public String getNominativoDeceduto() {
		return nominativoDeceduto;
	}

	public void setNominativoDeceduto(String nominativoDeceduto) {
		this.nominativoDeceduto = nominativoDeceduto;
	}

	public String getDataDecesso() {
		return dataDecesso;
	}

	public void setDataDecesso(String dataDecesso) {
		this.dataDecesso = dataDecesso;
	}

	public String getAttivitaCessata() {
		return attivitaCessata;
	}

	public void setAttivitaCessata(String attivitaCessata) {
		this.attivitaCessata = attivitaCessata;
	}

	public String[] getAllegati() {
		return allegati;
	}

	public void setAllegati(String[] allegati) {
		this.allegati = allegati;
	}

	public String getAltriAllegati() {
		return altriAllegati;
	}

	public void setAltriAllegati(String altriAllegati) {
		this.altriAllegati = altriAllegati;
	}

	public String getEstremiDocumento() {
		return estremiDocumento;
	}

	public void setEstremiDocumento(String estremiDocumento) {
		this.estremiDocumento = estremiDocumento;
	}

	public String getCittaSedeLegale() {
		return cittaSedeLegale;
	}

	public void setCittaSedeLegale(String cittaSedeLegale) {
		this.cittaSedeLegale = cittaSedeLegale;
	}

	public boolean isFlagDelega() {
		return flagDelega;
	}

	public void setFlagDelega(boolean flagDelega) {
		this.flagDelega = flagDelega;
	}

	public String getNominativoDelegato() {
		return nominativoDelegato;
	}

	public void setNominativoDelegato(String nominativoDelegato) {
		this.nominativoDelegato = nominativoDelegato;
	}

	public String getDataCompilazioneRichiesta() {
		return dataCompilazioneRichiesta;
	}

	public void setDataCompilazioneRichiesta(String dataCompilazioneRichiesta) {
		this.dataCompilazioneRichiesta = dataCompilazioneRichiesta;
	}
}
