package it.osapulie.anagrafe.web.portlet.dichiarazionecambioresidenza.form;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import it.osapulie.anagrafe.web.portlet.varie.form.Componente;

/**
 * Classe contenente i dati per la generazione dell'iscrizione all'anagrafe temporanea
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 *
 */
public class DatiDichiarazioneCambioResidenza implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/* Tipologia di dichiarazione */
	private String tipoDichiarazione;
	private String altroMotivoDichiarazione;
	private String altroMotivoDichiarazioneHidden;
	private MultipartFile generatedFile;
	private List<MultipartFile> multipartFiles;
	private MultipartFile fileIntestatarioEdiResPub;
	private MultipartFile fileUsufruttuario;
	private MultipartFile fileAssensoProprietario;
	private MultipartFile fileAmpliamentoNucleoFamiliare;
	private MultipartFile fileIntra;
	private MultipartFile fileExtra;
	

	

	/* Dati Anagrafici richiedente */
	private String personeInteressate;
	private String identificativoFamiglia;
	private String identificativoUtente;
	private String nome;
	private String cognome;
	private String codiceFiscale;
	private String dataNascita;
	private String sesso;
	private String statoEsteroNascita;
	private String statoEsteroNascitaHidden;
	private String comuneNascita;
	private String comuneNascitaHidden;
	private String comuneNascitaEstero;
	private String comuneNascitaEsteroHidden;
	private String provinciaNascita;

	private String comuneResidenza;
	private String comuneResidenzaHidden;
	private String comuneIscrizioneAIRE;
	private String comuneIscrizioneAIREHidden;
	private String provinciaResidenza;
	private String cittadinanza;
	private String cittadinanzaHidden;

	// Dati residenza
	private String indirizzoResidenza;
	private String civicoResidenza;
	private String esponenteResidenza;
	private String viaTextHidden;
	private String civicoTextHidden;
	private String localitaResidenza;
	private String codiceViaResidenza;
	private String internoResidenza;
	private String scalaResidenza;
	
	private String dettaglioPianoResidenza;
	

	private String pianoResidenza;
	private String statoEstero;
	private String statoEsteroHidden;
	private boolean stardarioResidenzaEnable;

	private String telefono;
	private String cellulare;
	private String email;
	private String pec;

	/* Dati Abitazione */
	private String nuovaVia;
	private String nuovoNum;
	private String nuovoEsp;
	private String nuovaViaTextHidden;
	private String nuovoNumTextHidden;
	private String nuovaLocalitaHidden;
	private String nuovaCorte;
	private String nuovoInterno;
	private String nuovaScala;
	private String nuovoPiano;
	private String nuovoCodiceViaHidden;
	private String sezione;
	private String foglio;
	private String particella;
	private String subalterno;

	/* Dati permesso di soggiorno */
	private String questura;
	private String dataRilascio;
	private String numSogg;
	private String dataScadenza;

	// dati stradario ASCOT
	private String nuovoIndirizzoCodiceFrazioneAscot;
	private String nuovoIndirizzoCodiceViaAscot;
	private String nuovoIndirizzoIdentificativoCivicoAscot;
	private String nuovoIndirizzoNumeroCivicoAscot;
	private String nuovoIndirizzoBarratoAscot;
	private String nuovoIndirizzoCorteAscot;

	/* Dati non obbligatori */

	private List<Componente> familiari;
	private boolean verificaFamiliari;
	/*
	 * Numero di parenti associati al richiedente della dichiarazione di cambio residenza. Serve per
	 * aggiungere dinamicamente i campi dei dati anagrafici dei parenti.
	 */
	private long numeroParenti;

	/* Altri anagrafica */
	private String statoCivile;
	private String statoCivileHidden;
	private String professione;
	private String professioneHidden;
	private String condNonProfessionale;
	private String condNonProfessionaleHidden;
	private String titoloStudio;
	private String titoloStudioHidden;

	/* Dati Ministero dei Trasporti */
	private String tipoPatente;
	private String numPatente;
	private String dataRilascioPatente;
	private String organoRilascioPatente;
	private String organoRilascioPatenteHidden;
	private String provPatente;
	// Possibilita di inserire più di una targa, separata da ";"
	private List<Veicolo> veicoli;

	/* Recapiti */
	private String recComune;
	private String recComuneHidden;
	private String recProvincia;
	private String recVia;
	private String recCivico;
	private String recEsponente;
	private String recTelefono;
	private String recCellulare;
	private String recFax;
	private String recEmail;
	private String recPec;

	private boolean dichiarazioneTitoloAbitativo;
	// path aggiuntivi per cambio compilazione Dati relativi all'abitazione
	private String titoloAbitativo1;
	private String titoloAbitativo2;
	private String titoloAbitativo3;
	private String titoloAbitativo4;
	private String titoloAbitativo5;
	private String titoloAbitativo6;
	private String titoloAbitativo7;
	private String titoloAbitativo8;
	private String titoloAbitativo9;
	

	private String titoloAbitativo;
	private String titoloAbitativoAgenzia1;
	private String titoloAbitativoAgenzia2;
	private String titoloAbitativoData1;
	private String titoloAbitativoData2;
	private String titoloAbitativoNumero1;
	private String titoloAbitativoNumero2;
	private String titoloAbitativoAltro1;
	private String titoloAbitativoAltro2;

	private String iscrittoNome;
	private String iscrittoCognome;
	private String iscrittoLuogoNascita;
	private String iscrittoDataNascita;
	private String iscrittoCodiceFiscale;
	private boolean iscrittoParentela;
	private String iscrittoTipoParentela;
	private String iscrittoTipoParentelaHidden;

	private boolean flagIscritto;
	private boolean abilitaCambioAbitazione;

	private String tipoCambioAbitazione;

	private String noteGenerali;
	private String altroAllegato;

	private boolean flagNoteInformative;

	private boolean dichiarazioneCompletata;
	
	/* controllo per stringa grafometrica */
	private String firmaGrafometricaBase64;
	private boolean firmaGrafometrica;
	private String codDelegante;
	
	private String inizioOperazione;
	private String uuidOperazione;
	private String ipAddress;
	
	private String hiddenLoadFileClick="N";
	
	private String hiddenLoadFileIntestatarioEdiResPub="N";
	private String hiddenLoadFileUsufruttuario="N";
	private String hiddenLoadFileAssensoProprietario="N";
	private String hiddenLoadFileAmpliamentoNucleoFamiliare="N";
	private String hiddenLoadFileIntra="N";
	private String hiddenLoadFileExtra="N";
	
	private String titoloAbitativointraextra;
	
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

	public String getDettaglioPianoResidenza() {
		return dettaglioPianoResidenza;
	}

	public void setDettaglioPianoResidenza(String dettaglioPianoResidenza) {
		this.dettaglioPianoResidenza = dettaglioPianoResidenza;
	}

	public String getTitoloAbitativo8() {
		return titoloAbitativo8;
	}

	public void setTitoloAbitativo8(String titoloAbitativo8) {
		this.titoloAbitativo8 = titoloAbitativo8;
	}

	/**
	 * @return the altroMotivoDichiarazione
	 */
	public String getAltroMotivoDichiarazione() {
		return altroMotivoDichiarazione;
	}

	/**
	 * @param altroMotivoDichiarazione the altroMotivoDichiarazione to set
	 */
	public void setAltroMotivoDichiarazione(String altroMotivoDichiarazione) {
		this.altroMotivoDichiarazione = altroMotivoDichiarazione;
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
	 * @return the cittadinanza
	 */
	public String getCittadinanza() {
		return cittadinanza;
	}

	/**
	 * @param cittadinanza the cittadinanza to set
	 */
	public void setCittadinanza(String cittadinanza) {
		this.cittadinanza = cittadinanza;
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
	 * @return the viaTextHidden
	 */
	public String getViaTextHidden() {
		return viaTextHidden;
	}

	/**
	 * @param viaTextHidden the viaTextHidden to set
	 */
	public void setViaTextHidden(String viaTextHidden) {
		this.viaTextHidden = viaTextHidden;
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
	 * @return the nuovaVia
	 */
	public String getNuovaVia() {
		return nuovaVia;
	}

	/**
	 * @param nuovaVia the nuovaVia to set
	 */
	public void setNuovaVia(String nuovaVia) {
		this.nuovaVia = nuovaVia;
	}

	/**
	 * @return the nuovoNum
	 */
	public String getNuovoNum() {
		return nuovoNum;
	}

	/**
	 * @param nuovoNum the nuovoNum to set
	 */
	public void setNuovoNum(String nuovoNum) {
		this.nuovoNum = nuovoNum;
	}

	/**
	 * @return the nuovaViaTextHidden
	 */
	public String getNuovaViaTextHidden() {
		return nuovaViaTextHidden;
	}

	/**
	 * @param nuovaViaTextHidden the nuovaViaTextHidden to set
	 */
	public void setNuovaViaTextHidden(String nuovaViaTextHidden) {
		this.nuovaViaTextHidden = nuovaViaTextHidden;
	}

	/**
	 * @return the nuovoNumTextHidden
	 */
	public String getNuovoNumTextHidden() {
		return nuovoNumTextHidden;
	}

	/**
	 * @param nuovoNumTextHidden the nuovoNumTextHidden to set
	 */
	public void setNuovoNumTextHidden(String nuovoNumTextHidden) {
		this.nuovoNumTextHidden = nuovoNumTextHidden;
	}

	/**
	 * @return the nuovoInterno
	 */
	public String getNuovoInterno() {
		return nuovoInterno;
	}

	/**
	 * @param nuovoInterno the nuovoInterno to set
	 */
	public void setNuovoInterno(String nuovoInterno) {
		this.nuovoInterno = nuovoInterno;
	}

	/**
	 * @return the nuovaScala
	 */
	public String getNuovaScala() {
		return nuovaScala;
	}

	/**
	 * @param nuovaScala the nuovaScala to set
	 */
	public void setNuovaScala(String nuovaScala) {
		this.nuovaScala = nuovaScala;
	}

	/**
	 * @return the nuovoPiano
	 */
	public String getNuovoPiano() {
		return nuovoPiano;
	}

	/**
	 * @param nuovoPiano the nuovoPiano to set
	 */
	public void setNuovoPiano(String nuovoPiano) {
		this.nuovoPiano = nuovoPiano;
	}

	/**
	 * @return the questura
	 */
	public String getQuestura() {
		return questura;
	}

	/**
	 * @param questura the questura to set
	 */
	public void setQuestura(String questura) {
		this.questura = questura;
	}

	/**
	 * @return the dataRilascio
	 */
	public String getDataRilascio() {
		return dataRilascio;
	}

	/**
	 * @param dataRilascio the dataRilascio to set
	 */
	public void setDataRilascio(String dataRilascio) {
		this.dataRilascio = dataRilascio;
	}

	/**
	 * @return the numSogg
	 */
	public String getNumSogg() {
		return numSogg;
	}

	/**
	 * @param numSogg the numSogg to set
	 */
	public void setNumSogg(String numSogg) {
		this.numSogg = numSogg;
	}

	/**
	 * @return the familiari
	 */
	public List<Componente> getFamiliari() {
		return familiari;
	}

	/**
	 * @param familiari the familiari to set
	 */
	public void setFamiliari(List<Componente> familiari) {
		this.familiari = familiari;
	}

	/**
	 * @return the numeroParenti
	 */
	public long getNumeroParenti() {
		return numeroParenti;
	}

	/**
	 * @param numeroParenti the numeroParenti to set
	 */
	public void setNumeroParenti(long numeroParenti) {
		this.numeroParenti = numeroParenti;
	}

	/**
	 * @return the statoCivile
	 */
	public String getStatoCivile() {
		return statoCivile;
	}

	/**
	 * @param statoCivile the statoCivile to set
	 */
	public void setStatoCivile(String statoCivile) {
		this.statoCivile = statoCivile;
	}

	/**
	 * @return the professione
	 */
	public String getProfessione() {
		return professione;
	}

	/**
	 * @param professione the professione to set
	 */
	public void setProfessione(String professione) {
		this.professione = professione;
	}

	/**
	 * @return the condNonProfessionale
	 */
	public String getCondNonProfessionale() {
		return condNonProfessionale;
	}

	/**
	 * @param condNonProfessionale the condNonProfessionale to set
	 */
	public void setCondNonProfessionale(String condNonProfessionale) {
		this.condNonProfessionale = condNonProfessionale;
	}

	/**
	 * @return the titoloStudio
	 */
	public String getTitoloStudio() {
		return titoloStudio;
	}

	/**
	 * @param titoloStudio the titoloStudio to set
	 */
	public void setTitoloStudio(String titoloStudio) {
		this.titoloStudio = titoloStudio;
	}

	/**
	 * @return the tipoPatente
	 */
	public String getTipoPatente() {
		return tipoPatente;
	}

	/**
	 * @param tipoPatente the tipoPatente to set
	 */
	public void setTipoPatente(String tipoPatente) {
		this.tipoPatente = tipoPatente;
	}

	/**
	 * @return the numPatente
	 */
	public String getNumPatente() {
		return numPatente;
	}

	/**
	 * @param numPatente the numPatente to set
	 */
	public void setNumPatente(String numPatente) {
		this.numPatente = numPatente;
	}

	/**
	 * @return the dataRilascioPatente
	 */
	public String getDataRilascioPatente() {
		return dataRilascioPatente;
	}

	/**
	 * @param dataRilascioPatente the dataRilascioPatente to set
	 */
	public void setDataRilascioPatente(String dataRilascioPatente) {
		this.dataRilascioPatente = dataRilascioPatente;
	}

	/**
	 * @return the organoRilascioPatente
	 */
	public String getOrganoRilascioPatente() {
		return organoRilascioPatente;
	}

	/**
	 * @param organoRilascioPatente the organoRilascioPatente to set
	 */
	public void setOrganoRilascioPatente(String organoRilascioPatente) {
		this.organoRilascioPatente = organoRilascioPatente;
	}

	/**
	 * @return the provPatente
	 */
	public String getProvPatente() {
		return provPatente;
	}

	/**
	 * @param provPatente the provPatente to set
	 */
	public void setProvPatente(String provPatente) {
		this.provPatente = provPatente;
	}

	/**
	 * @return the recComune
	 */
	public String getRecComune() {
		return recComune;
	}

	/**
	 * @param recComune the recComune to set
	 */
	public void setRecComune(String recComune) {
		this.recComune = recComune;
	}

	/**
	 * @return the recProvincia
	 */
	public String getRecProvincia() {
		return recProvincia;
	}

	/**
	 * @param recProvincia the recProvincia to set
	 */
	public void setRecProvincia(String recProvincia) {
		this.recProvincia = recProvincia;
	}

	/**
	 * @return the recVia
	 */
	public String getRecVia() {
		return recVia;
	}

	/**
	 * @param recVia the recVia to set
	 */
	public void setRecVia(String recVia) {
		this.recVia = recVia;
	}

	/**
	 * @return the recCivico
	 */
	public String getRecCivico() {
		return recCivico;
	}

	/**
	 * @param recCivico the recCivico to set
	 */
	public void setRecCivico(String recCivico) {
		this.recCivico = recCivico;
	}

	/**
	 * @return the recTelefono
	 */
	public String getRecTelefono() {
		return recTelefono;
	}

	/**
	 * @param recTelefono the recTelefono to set
	 */
	public void setRecTelefono(String recTelefono) {
		this.recTelefono = recTelefono;
	}

	/**
	 * @return the recCellulare
	 */
	public String getRecCellulare() {
		return recCellulare;
	}

	/**
	 * @param recCellulare the recCellulare to set
	 */
	public void setRecCellulare(String recCellulare) {
		this.recCellulare = recCellulare;
	}

	/**
	 * @return the recFax
	 */
	public String getRecFax() {
		return recFax;
	}

	/**
	 * @param recFax the recFax to set
	 */
	public void setRecFax(String recFax) {
		this.recFax = recFax;
	}

	/**
	 * @return the recEmail
	 */
	public String getRecEmail() {
		return recEmail;
	}

	/**
	 * @param recEmail the recEmail to set
	 */
	public void setRecEmail(String recEmail) {
		this.recEmail = recEmail;
	}

	/**
	 * @return the dichiarazioneTitoloAbitativo
	 */
	public boolean isDichiarazioneTitoloAbitativo() {
		return dichiarazioneTitoloAbitativo;
	}

	/**
	 * @param dichiarazioneTitoloAbitativo the dichiarazioneTitoloAbitativo to set
	 */
	public void setDichiarazioneTitoloAbitativo(boolean dichiarazioneTitoloAbitativo) {
		this.dichiarazioneTitoloAbitativo = dichiarazioneTitoloAbitativo;
	}

	/**
	 * @return the titoloAbitativo
	 */
	public String getTitoloAbitativo() {
		return titoloAbitativo;
	}

	/**
	 * @param titoloAbitativo the titoloAbitativo to set
	 */
	public void setTitoloAbitativo(String titoloAbitativo) {
		this.titoloAbitativo = titoloAbitativo;
	}

	/**
	 * @return the titoloAbitativoAgenzia1
	 */
	public String getTitoloAbitativoAgenzia1() {
		return titoloAbitativoAgenzia1;
	}

	/**
	 * @param titoloAbitativoAgenzia1 the titoloAbitativoAgenzia1 to set
	 */
	public void setTitoloAbitativoAgenzia1(String titoloAbitativoAgenzia1) {
		this.titoloAbitativoAgenzia1 = titoloAbitativoAgenzia1;
	}

	/**
	 * @return the titoloAbitativoAgenzia2
	 */
	public String getTitoloAbitativoAgenzia2() {
		return titoloAbitativoAgenzia2;
	}

	/**
	 * @param titoloAbitativoAgenzia2 the titoloAbitativoAgenzia2 to set
	 */
	public void setTitoloAbitativoAgenzia2(String titoloAbitativoAgenzia2) {
		this.titoloAbitativoAgenzia2 = titoloAbitativoAgenzia2;
	}

	/**
	 * @return the titoloAbitativoData1
	 */
	public String getTitoloAbitativoData1() {
		return titoloAbitativoData1;
	}

	/**
	 * @param titoloAbitativoData1 the titoloAbitativoData1 to set
	 */
	public void setTitoloAbitativoData1(String titoloAbitativoData1) {
		this.titoloAbitativoData1 = titoloAbitativoData1;
	}

	/**
	 * @return the titoloAbitativoData2
	 */
	public String getTitoloAbitativoData2() {
		return titoloAbitativoData2;
	}

	/**
	 * @param titoloAbitativoData2 the titoloAbitativoData2 to set
	 */
	public void setTitoloAbitativoData2(String titoloAbitativoData2) {
		this.titoloAbitativoData2 = titoloAbitativoData2;
	}

	/**
	 * @return the titoloAbitativoNumero1
	 */
	public String getTitoloAbitativoNumero1() {
		return titoloAbitativoNumero1;
	}

	/**
	 * @param titoloAbitativoNumero1 the titoloAbitativoNumero1 to set
	 */
	public void setTitoloAbitativoNumero1(String titoloAbitativoNumero1) {
		this.titoloAbitativoNumero1 = titoloAbitativoNumero1;
	}

	/**
	 * @return the titoloAbitativoNumero2
	 */
	public String getTitoloAbitativoNumero2() {
		return titoloAbitativoNumero2;
	}

	/**
	 * @param titoloAbitativoNumero2 the titoloAbitativoNumero2 to set
	 */
	public void setTitoloAbitativoNumero2(String titoloAbitativoNumero2) {
		this.titoloAbitativoNumero2 = titoloAbitativoNumero2;
	}

	/**
	 * @return the titoloAbitativoAltro1
	 */
	public String getTitoloAbitativoAltro1() {
		return titoloAbitativoAltro1;
	}

	/**
	 * @param titoloAbitativoAltro1 the titoloAbitativoAltro1 to set
	 */
	public void setTitoloAbitativoAltro1(String titoloAbitativoAltro1) {
		this.titoloAbitativoAltro1 = titoloAbitativoAltro1;
	}

	/**
	 * @return the titoloAbitativoAltro2
	 */
	public String getTitoloAbitativoAltro2() {
		return titoloAbitativoAltro2;
	}

	/**
	 * @param titoloAbitativoAltro2 the titoloAbitativoAltro2 to set
	 */
	public void setTitoloAbitativoAltro2(String titoloAbitativoAltro2) {
		this.titoloAbitativoAltro2 = titoloAbitativoAltro2;
	}

	/**
	 * @return the iscrittoNome
	 */
	public String getIscrittoNome() {
		return iscrittoNome;
	}

	/**
	 * @param iscrittoNome the iscrittoNome to set
	 */
	public void setIscrittoNome(String iscrittoNome) {
		this.iscrittoNome = iscrittoNome;
	}

	/**
	 * @return the iscrittoCognome
	 */
	public String getIscrittoCognome() {
		return iscrittoCognome;
	}

	/**
	 * @param iscrittoCognome the iscrittoCognome to set
	 */
	public void setIscrittoCognome(String iscrittoCognome) {
		this.iscrittoCognome = iscrittoCognome;
	}

	/**
	 * @return the iscrittoLuogoNascita
	 */
	public String getIscrittoLuogoNascita() {
		return iscrittoLuogoNascita;
	}

	/**
	 * @param iscrittoLuogoNascita the iscrittoLuogoNascita to set
	 */
	public void setIscrittoLuogoNascita(String iscrittoLuogoNascita) {
		this.iscrittoLuogoNascita = iscrittoLuogoNascita;
	}

	/**
	 * @return the iscrittoDataNascita
	 */
	public String getIscrittoDataNascita() {
		return iscrittoDataNascita;
	}

	/**
	 * @param iscrittoDataNascita the iscrittoDataNascita to set
	 */
	public void setIscrittoDataNascita(String iscrittoDataNascita) {
		this.iscrittoDataNascita = iscrittoDataNascita;
	}

	/**
	 * @return the iscrittoParentela
	 */
	public boolean isIscrittoParentela() {
		return iscrittoParentela;
	}

	/**
	 * @param iscrittoParentela the iscrittoParentela to set
	 */
	public void setIscrittoParentela(boolean iscrittoParentela) {
		this.iscrittoParentela = iscrittoParentela;
	}

	public String getIscrittoTipoParentelaHidden() {
		return iscrittoTipoParentelaHidden;
	}

	public void setIscrittoTipoParentelaHidden(String iscrittoTipoParentelaHidden) {
		this.iscrittoTipoParentelaHidden = iscrittoTipoParentelaHidden;
	}

	/**
	 * @return the iscrittoTipoParentela
	 */
	public String getIscrittoTipoParentela() {
		return iscrittoTipoParentela;
	}

	/**
	 * @param iscrittoTipoParentela the iscrittoTipoParentela to set
	 */
	public void setIscrittoTipoParentela(String iscrittoTipoParentela) {
		this.iscrittoTipoParentela = iscrittoTipoParentela;
	}

	/**
	 * @return the flagIscritto
	 */
	public boolean isFlagIscritto() {
		return flagIscritto;
	}

	/**
	 * @param flagIscritto the flagIscritto to set
	 */
	public void setFlagIscritto(boolean flagIscritto) {
		this.flagIscritto = flagIscritto;
	}

	/**
	 * @return the veicoli
	 */
	public List<Veicolo> getVeicoli() {
		return veicoli;
	}

	/**
	 * @param veicoli the veicoli to set
	 */
	public void setVeicoli(List<Veicolo> veicoli) {
		this.veicoli = veicoli;
	}

	/**
	 * @return the nuovoEsp
	 */
	public String getNuovoEsp() {
		return nuovoEsp;
	}

	/**
	 * @param nuovoEsp the nuovoEsp to set
	 */
	public void setNuovoEsp(String nuovoEsp) {
		this.nuovoEsp = nuovoEsp;
	}

	/**
	 * @return the identificativoUtente
	 */
	public String getIdentificativoUtente() {
		return identificativoUtente;
	}

	/**
	 * @param identificativoUtente the identificativoUtente to set
	 */
	public void setIdentificativoUtente(String identificativoUtente) {
		this.identificativoUtente = identificativoUtente;
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
	 * @return the cellulare
	 */
	public String getCellulare() {
		return cellulare;
	}

	/**
	 * @param cellulare the cellulare to set
	 */
	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
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
	 * @return the recEsponente
	 */
	public String getRecEsponente() {
		return recEsponente;
	}

	/**
	 * @param recEsponente the recEsponente to set
	 */
	public void setRecEsponente(String recEsponente) {
		this.recEsponente = recEsponente;
	}

	/**
	 * @return the recPec
	 */
	public String getRecPec() {
		return recPec;
	}

	/**
	 * @param recPec the recPec to set
	 */
	public void setRecPec(String recPec) {
		this.recPec = recPec;
	}

	/**
	 * @return the iscrittoCodiceFiscale
	 */
	public String getIscrittoCodiceFiscale() {
		return iscrittoCodiceFiscale;
	}

	/**
	 * @param iscrittoCodiceFiscale the iscrittoCodiceFiscale to set
	 */
	public void setIscrittoCodiceFiscale(String iscrittoCodiceFiscale) {
		this.iscrittoCodiceFiscale = iscrittoCodiceFiscale;
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
	 * @return the cittadinanzaHidden
	 */
	public String getCittadinanzaHidden() {
		return cittadinanzaHidden;
	}

	/**
	 * @param cittadinanzaHidden the cittadinanzaHidden to set
	 */
	public void setCittadinanzaHidden(String cittadinanzaHidden) {
		this.cittadinanzaHidden = cittadinanzaHidden;
	}

	/**
	 * @return the statoEsteroHidden
	 */
	public String getStatoEsteroHidden() {
		return statoEsteroHidden;
	}

	/**
	 * @param statoEsteroHidden the statoEsteroHidden to set
	 */
	public void setStatoEsteroHidden(String statoEsteroHidden) {
		this.statoEsteroHidden = statoEsteroHidden;
	}

	/**
	 * @return the statoCivileHidden
	 */
	public String getStatoCivileHidden() {
		return statoCivileHidden;
	}

	/**
	 * @param statoCivileHidden the statoCivileHidden to set
	 */
	public void setStatoCivileHidden(String statoCivileHidden) {
		this.statoCivileHidden = statoCivileHidden;
	}

	/**
	 * @return the professioneHidden
	 */
	public String getProfessioneHidden() {
		return professioneHidden;
	}

	/**
	 * @param professioneHidden the professioneHidden to set
	 */
	public void setProfessioneHidden(String professioneHidden) {
		this.professioneHidden = professioneHidden;
	}

	/**
	 * @return the condNonProfessionaleHidden
	 */
	public String getCondNonProfessionaleHidden() {
		return condNonProfessionaleHidden;
	}

	/**
	 * @param condNonProfessionaleHidden the condNonProfessionaleHidden to set
	 */
	public void setCondNonProfessionaleHidden(String condNonProfessionaleHidden) {
		this.condNonProfessionaleHidden = condNonProfessionaleHidden;
	}

	/**
	 * @return the titoloStudioHidden
	 */
	public String getTitoloStudioHidden() {
		return titoloStudioHidden;
	}

	/**
	 * @param titoloStudioHidden the titoloStudioHidden to set
	 */
	public void setTitoloStudioHidden(String titoloStudioHidden) {
		this.titoloStudioHidden = titoloStudioHidden;
	}

	/**
	 * @return the organoRilascioPatenteHidden
	 */
	public String getOrganoRilascioPatenteHidden() {
		return organoRilascioPatenteHidden;
	}

	/**
	 * @param organoRilascioPatenteHidden the organoRilascioPatenteHidden to set
	 */
	public void setOrganoRilascioPatenteHidden(String organoRilascioPatenteHidden) {
		this.organoRilascioPatenteHidden = organoRilascioPatenteHidden;
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
	 * @return the provinciaResidenza
	 */
	public String getProvinciaResidenza() {
		return provinciaResidenza;
	}

	/**
	 * @param provinciaResidenza the provinciaResidenza to set
	 */
	public void setProvinciaResidenza(String provinciaResidenza) {
		this.provinciaResidenza = provinciaResidenza;
	}

	/**
	 * @return the tipoCambioAbitazione
	 */
	public String getTipoCambioAbitazione() {
		return tipoCambioAbitazione;
	}

	/**
	 * @param tipoCambioAbitazione the tipoCambioAbitazione to set
	 */
	public void setTipoCambioAbitazione(String tipoCambioAbitazione) {
		this.tipoCambioAbitazione = tipoCambioAbitazione;
	}

	/**
	 * @return the altroMotivoDichiarazioneHidden
	 */
	public String getAltroMotivoDichiarazioneHidden() {
		return altroMotivoDichiarazioneHidden;
	}

	/**
	 * @param altroMotivoDichiarazioneHidden the altroMotivoDichiarazioneHidden to set
	 */
	public void setAltroMotivoDichiarazioneHidden(String altroMotivoDichiarazioneHidden) {
		this.altroMotivoDichiarazioneHidden = altroMotivoDichiarazioneHidden;
	}

	/**
	 * @return the identificativoFamiglia
	 */
	public String getIdentificativoFamiglia() {
		return identificativoFamiglia;
	}

	/**
	 * @param identificativoFamiglia the identificativoFamiglia to set
	 */
	public void setIdentificativoFamiglia(String identificativoFamiglia) {
		this.identificativoFamiglia = identificativoFamiglia;
	}

	/**
	 * @return the nuovaLocalitaHidden
	 */
	public String getNuovaLocalitaHidden() {
		return nuovaLocalitaHidden;
	}

	/**
	 * @param nuovaLocalitaHidden the nuovaLocalitaHidden to set
	 */
	public void setNuovaLocalitaHidden(String nuovaLocalitaHidden) {
		this.nuovaLocalitaHidden = nuovaLocalitaHidden;
	}

	/**
	 * @return the nuovoCodiceViaHidden
	 */
	public String getNuovoCodiceViaHidden() {
		return nuovoCodiceViaHidden;
	}

	/**
	 * @param nuovoCodiceViaHidden the nuovoCodiceViaHidden to set
	 */
	public void setNuovoCodiceViaHidden(String nuovoCodiceViaHidden) {
		this.nuovoCodiceViaHidden = nuovoCodiceViaHidden;
	}

	/**
	 * @return the nuovoIndirizzoCodiceFrazioneAscot
	 */
	public String getNuovoIndirizzoCodiceFrazioneAscot() {
		return nuovoIndirizzoCodiceFrazioneAscot;
	}

	/**
	 * @param nuovoIndirizzoCodiceFrazioneAscot the nuovoIndirizzoCodiceFrazioneAscot to set
	 */
	public void setNuovoIndirizzoCodiceFrazioneAscot(String nuovoIndirizzoCodiceFrazioneAscot) {
		this.nuovoIndirizzoCodiceFrazioneAscot = nuovoIndirizzoCodiceFrazioneAscot;
	}

	/**
	 * @return the nuovoIndirizzoCodiceViaAscot
	 */
	public String getNuovoIndirizzoCodiceViaAscot() {
		return nuovoIndirizzoCodiceViaAscot;
	}

	/**
	 * @param nuovoIndirizzoCodiceViaAscot the nuovoIndirizzoCodiceViaAscot to set
	 */
	public void setNuovoIndirizzoCodiceViaAscot(String nuovoIndirizzoCodiceViaAscot) {
		this.nuovoIndirizzoCodiceViaAscot = nuovoIndirizzoCodiceViaAscot;
	}

	/**
	 * @return the nuovoIndirizzoNumeroCivicoAscot
	 */
	public String getNuovoIndirizzoNumeroCivicoAscot() {
		return nuovoIndirizzoNumeroCivicoAscot;
	}

	/**
	 * @param nuovoIndirizzoNumeroCivicoAscot the nuovoIndirizzoNumeroCivicoAscot to set
	 */
	public void setNuovoIndirizzoNumeroCivicoAscot(String nuovoIndirizzoNumeroCivicoAscot) {
		this.nuovoIndirizzoNumeroCivicoAscot = nuovoIndirizzoNumeroCivicoAscot;
	}

	/**
	 * @return the nuovoIndirizzoCorteAscot
	 */
	public String getNuovoIndirizzoCorteAscot() {
		return nuovoIndirizzoCorteAscot;
	}

	/**
	 * @param nuovoIndirizzoCorteAscot the nuovoIndirizzoCorteAscot to set
	 */
	public void setNuovoIndirizzoCorteAscot(String nuovoIndirizzoCorteAscot) {
		this.nuovoIndirizzoCorteAscot = nuovoIndirizzoCorteAscot;
	}

	/**
	 * @return the nuovaCorte
	 */
	public String getNuovaCorte() {
		return nuovaCorte;
	}

	/**
	 * @param nuovaCorte the nuovaCorte to set
	 */
	public void setNuovaCorte(String nuovaCorte) {
		this.nuovaCorte = nuovaCorte;
	}

	/**
	 * @return the comuneNascitaEstero
	 */
	public String getComuneNascitaEstero() {
		return comuneNascitaEstero;
	}

	/**
	 * @param comuneNascitaEstero the comuneNascitaEstero to set
	 */
	public void setComuneNascitaEstero(String comuneNascitaEstero) {
		this.comuneNascitaEstero = comuneNascitaEstero;
	}

	/**
	 * @return the nuovoIndirizzoBarratoAscot
	 */
	public String getNuovoIndirizzoBarratoAscot() {
		return nuovoIndirizzoBarratoAscot;
	}

	/**
	 * @param nuovoIndirizzoBarratoAscot the nuovoIndirizzoBarratoAscot to set
	 */
	public void setNuovoIndirizzoBarratoAscot(String nuovoIndirizzoBarratoAscot) {
		this.nuovoIndirizzoBarratoAscot = nuovoIndirizzoBarratoAscot;
	}

	/**
	 * @return the statoEsteroNascita
	 */
	public String getStatoEsteroNascita() {
		return statoEsteroNascita;
	}

	/**
	 * @param statoEsteroNascita the statoEsteroNascita to set
	 */
	public void setStatoEsteroNascita(String statoEsteroNascita) {
		this.statoEsteroNascita = statoEsteroNascita;
	}

	/**
	 * @return the statoEsteroNascitaHidden
	 */
	public String getStatoEsteroNascitaHidden() {
		return statoEsteroNascitaHidden;
	}

	/**
	 * @param statoEsteroNascitaHidden the statoEsteroNascitaHidden to set
	 */
	public void setStatoEsteroNascitaHidden(String statoEsteroNascitaHidden) {
		this.statoEsteroNascitaHidden = statoEsteroNascitaHidden;
	}

	/**
	 * @return the comuneNascitaEsteroHidden
	 */
	public String getComuneNascitaEsteroHidden() {
		return comuneNascitaEsteroHidden;
	}

	/**
	 * @param comuneNascitaEsteroHidden the comuneNascitaEsteroHidden to set
	 */
	public void setComuneNascitaEsteroHidden(String comuneNascitaEsteroHidden) {
		this.comuneNascitaEsteroHidden = comuneNascitaEsteroHidden;
	}

	/**
	 * @return the localitaResidenza
	 */
	public String getLocalitaResidenza() {
		return localitaResidenza;
	}

	/**
	 * @param localitaResidenza the localitaResidenza to set
	 */
	public void setLocalitaResidenza(String localitaResidenza) {
		this.localitaResidenza = localitaResidenza;
	}

	/**
	 * @return the codiceViaResidenza
	 */
	public String getCodiceViaResidenza() {
		return codiceViaResidenza;
	}

	/**
	 * @param codiceViaResidenza the codiceViaResidenza to set
	 */
	public void setCodiceViaResidenza(String codiceViaResidenza) {
		this.codiceViaResidenza = codiceViaResidenza;
	}

	/**
	 * @return the internoResidenza
	 */
	public String getInternoResidenza() {
		return internoResidenza;
	}

	/**
	 * @param internoResidenza the internoResidenza to set
	 */
	public void setInternoResidenza(String internoResidenza) {
		this.internoResidenza = internoResidenza;
	}

	/**
	 * @return the scalaResidenza
	 */
	public String getScalaResidenza() {
		return scalaResidenza;
	}

	/**
	 * @param scalaResidenza the scalaResidenza to set
	 */
	public void setScalaResidenza(String scalaResidenza) {
		this.scalaResidenza = scalaResidenza;
	}

	/**
	 * @return the pianoResidenza
	 */
	public String getPianoResidenza() {
		return pianoResidenza;
	}

	/**
	 * @param pianoResidenza the pianoResidenza to set
	 */
	public void setPianoResidenza(String pianoResidenza) {
		this.pianoResidenza = pianoResidenza;
	}

	/**
	 * @return the stardarioResidenzaEnable
	 */
	public boolean isStardarioResidenzaEnable() {
		return stardarioResidenzaEnable;
	}

	/**
	 * @param stardarioResidenzaEnable the stardarioResidenzaEnable to set
	 */
	public void setStardarioResidenzaEnable(boolean stardarioResidenzaEnable) {
		this.stardarioResidenzaEnable = stardarioResidenzaEnable;
	}

	/**
	 * @return the abilitaCambioAbitazione
	 */
	public boolean isAbilitaCambioAbitazione() {
		return abilitaCambioAbitazione;
	}

	/**
	 * @param abilitaCambioAbitazione the abilitaCambioAbitazione to set
	 */
	public void setAbilitaCambioAbitazione(boolean abilitaCambioAbitazione) {
		this.abilitaCambioAbitazione = abilitaCambioAbitazione;
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
	 * @return the recComuneHidden
	 */
	public String getRecComuneHidden() {
		return recComuneHidden;
	}

	/**
	 * @param recComuneHidden the recComuneHidden to set
	 */
	public void setRecComuneHidden(String recComuneHidden) {
		this.recComuneHidden = recComuneHidden;
	}

	/**
	 * @return the comuneIscrizioneAIRE
	 */
	public String getComuneIscrizioneAIRE() {
		return comuneIscrizioneAIRE;
	}

	/**
	 * @param comuneIscrizioneAIRE the comuneIscrizioneAIRE to set
	 */
	public void setComuneIscrizioneAIRE(String comuneIscrizioneAIRE) {
		this.comuneIscrizioneAIRE = comuneIscrizioneAIRE;
	}

	/**
	 * @return the comuneIscrizioneAIREHidden
	 */
	public String getComuneIscrizioneAIREHidden() {
		return comuneIscrizioneAIREHidden;
	}

	/**
	 * @param comuneIscrizioneAIREHidden the comuneIscrizioneAIREHidden to set
	 */
	public void setComuneIscrizioneAIREHidden(String comuneIscrizioneAIREHidden) {
		this.comuneIscrizioneAIREHidden = comuneIscrizioneAIREHidden;
	}

	/**
	 * @return the nuovoIndirizzoIdentificativoCivicoAscot
	 */
	public String getNuovoIndirizzoIdentificativoCivicoAscot() {
		return nuovoIndirizzoIdentificativoCivicoAscot;
	}

	/**
	 * @param nuovoIndirizzoIdentificativoCivicoAscot the nuovoIndirizzoIdentificativoCivicoAscot to
	 *        set
	 */
	public void setNuovoIndirizzoIdentificativoCivicoAscot(String nuovoIndirizzoIdentificativoCivicoAscot) {
		this.nuovoIndirizzoIdentificativoCivicoAscot = nuovoIndirizzoIdentificativoCivicoAscot;
	}

	public String getAltroAllegato() {
		return altroAllegato;
	}

	public void setAltroAllegato(String altroAllegato) {
		this.altroAllegato = altroAllegato;
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
	 * @return the flagNoteInformative
	 */
	public boolean isFlagNoteInformative() {
		return flagNoteInformative;
	}

	/**
	 * @param flagNoteInformative the flagNoteInformative to set
	 */
	public void setFlagNoteInformative(boolean flagNoteInformative) {
		this.flagNoteInformative = flagNoteInformative;
	}

	/**
	 * @return the verificaFamiliari
	 */
	public boolean isVerificaFamiliari() {
		return verificaFamiliari;
	}

	/**
	 * @param verificaFamiliari the verificaFamiliari to set
	 */
	public void setVerificaFamiliari(boolean verificaFamiliari) {
		this.verificaFamiliari = verificaFamiliari;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	public MultipartFile getGeneratedFile() {
		return generatedFile;
	}

	public void setGeneratedFile(MultipartFile generatedFile) {
		this.generatedFile = generatedFile;
	}

	public String getTitoloAbitativo9() {
		return titoloAbitativo9;
	}

	public void setTitoloAbitativo9(String titoloAbitativo9) {
		this.titoloAbitativo9 = titoloAbitativo9;
	}
	public String getTitoloAbitativo2() {
		return titoloAbitativo2;
	}

	public void setTitoloAbitativo2(String titoloAbitativo2) {
		this.titoloAbitativo2 = titoloAbitativo2;
	}

	public String getTitoloAbitativo3() {
		return titoloAbitativo3;
	}

	public void setTitoloAbitativo3(String titoloAbitativo3) {
		this.titoloAbitativo3 = titoloAbitativo3;
	}

	public String getTitoloAbitativo4() {
		return titoloAbitativo4;
	}

	public void setTitoloAbitativo4(String titoloAbitativo4) {
		this.titoloAbitativo4 = titoloAbitativo4;
	}

	public String getTitoloAbitativo5() {
		return titoloAbitativo5;
	}

	public void setTitoloAbitativo5(String titoloAbitativo5) {
		this.titoloAbitativo5 = titoloAbitativo5;
	}

	public String getTitoloAbitativo6() {
		return titoloAbitativo6;
	}

	public void setTitoloAbitativo6(String titoloAbitativo6) {
		this.titoloAbitativo6 = titoloAbitativo6;
	}

	public String getTitoloAbitativo7() {
		return titoloAbitativo7;
	}

	public void setTitoloAbitativo7(String titoloAbitativo7) {
		this.titoloAbitativo7 = titoloAbitativo7;
	}

	
	public String getTitoloAbitativo1() {
		return titoloAbitativo1;
	}

	public void setTitoloAbitativo1(String titoloAbitativo1) {
		this.titoloAbitativo1 = titoloAbitativo1;
	}

	public String getPersoneInteressate() {
		return personeInteressate;
	}

	public void setPersoneInteressate(String personeInteressate) {
		this.personeInteressate = personeInteressate;
	}
	
	public void setFirmaGrafometrica(boolean firmaGrafometrica) {
		this.firmaGrafometrica = firmaGrafometrica;
	}
	
	public boolean isFirmaGrafometrica() {
		return firmaGrafometrica;
	}
	
	public void setFirmaGrafometricaBase64(String firmaGrafometricaBase64) {
		this.firmaGrafometricaBase64 = firmaGrafometricaBase64;
	}
	
	public String getFirmaGrafometricaBase64() {
		return firmaGrafometricaBase64;
	}
	
	public void setCodDelegante(String codDelegante) {
		this.codDelegante = codDelegante;
	}
	
	 public String getCodDelegante() {
		return codDelegante;
	}
	 
	 public void setInizioOperazione(String inizioOperazione) {
		this.inizioOperazione = inizioOperazione;
	}
	 
	 public String getInizioOperazione() {
		return inizioOperazione;
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

	public String getDataScadenza() {
		return dataScadenza;
	}

	public void setDataScadenza(String dataScadenza) {
		this.dataScadenza = dataScadenza;
	}

	public String getHiddenLoadFileClick() {
		return hiddenLoadFileClick;
	}

	public void setHiddenLoadFileClick(String hiddenLoadFileClick) {
		this.hiddenLoadFileClick = hiddenLoadFileClick;
	}

	public MultipartFile getFileIntestatarioEdiResPub() {
		return fileIntestatarioEdiResPub;
	}

	public void setFileIntestatarioEdiResPub(MultipartFile fileIntestatarioEdiResPub) {
		this.fileIntestatarioEdiResPub = fileIntestatarioEdiResPub;
	}

	public MultipartFile getFileUsufruttuario() {
		return fileUsufruttuario;
	}

	public void setFileUsufruttuario(MultipartFile fileUsufruttuario) {
		this.fileUsufruttuario = fileUsufruttuario;
	}

	public MultipartFile getFileAssensoProprietario() {
		return fileAssensoProprietario;
	}

	public void setFileAssensoProprietario(MultipartFile fileAssensoProprietario) {
		this.fileAssensoProprietario = fileAssensoProprietario;
	}

	public MultipartFile getFileAmpliamentoNucleoFamiliare() {
		return fileAmpliamentoNucleoFamiliare;
	}

	public void setFileAmpliamentoNucleoFamiliare(MultipartFile fileAmpliamentoNucleoFamiliare) {
		this.fileAmpliamentoNucleoFamiliare = fileAmpliamentoNucleoFamiliare;
	}

	public String getHiddenLoadFileIntestatarioEdiResPub() {
		return hiddenLoadFileIntestatarioEdiResPub;
	}

	public void setHiddenLoadFileIntestatarioEdiResPub(String hiddenLoadFileIntestatarioEdiResPub) {
		this.hiddenLoadFileIntestatarioEdiResPub = hiddenLoadFileIntestatarioEdiResPub;
	}

	public String getHiddenLoadFileUsufruttuario() {
		return hiddenLoadFileUsufruttuario;
	}

	public void setHiddenLoadFileUsufruttuario(String hiddenLoadFileUsufruttuario) {
		this.hiddenLoadFileUsufruttuario = hiddenLoadFileUsufruttuario;
	}

	public String getHiddenLoadFileAssensoProprietario() {
		return hiddenLoadFileAssensoProprietario;
	}

	public void setHiddenLoadFileAssensoProprietario(String hiddenLoadFileAssensoProprietario) {
		this.hiddenLoadFileAssensoProprietario = hiddenLoadFileAssensoProprietario;
	}

	public String getHiddenLoadFileAmpliamentoNucleoFamiliare() {
		return hiddenLoadFileAmpliamentoNucleoFamiliare;
	}

	public void setHiddenLoadFileAmpliamentoNucleoFamiliare(String hiddenLoadFileAmpliamentoNucleoFamiliare) {
		this.hiddenLoadFileAmpliamentoNucleoFamiliare = hiddenLoadFileAmpliamentoNucleoFamiliare;
	}

	public String getTitoloAbitativointraextra() {
		return titoloAbitativointraextra;
	}

	public void setTitoloAbitativointraextra(String titoloAbitativointraextra) {
		this.titoloAbitativointraextra = titoloAbitativointraextra;
	}

	public MultipartFile getFileIntra() {
		return fileIntra;
	}

	public void setFileIntra(MultipartFile fileIntra) {
		this.fileIntra = fileIntra;
	}

	public MultipartFile getFileExtra() {
		return fileExtra;
	}

	public void setFileExtra(MultipartFile fileExtra) {
		this.fileExtra = fileExtra;
	}

	public String getHiddenLoadFileIntra() {
		return hiddenLoadFileIntra;
	}

	public void setHiddenLoadFileIntra(String hiddenLoadFileIntra) {
		this.hiddenLoadFileIntra = hiddenLoadFileIntra;
	}

	public String getHiddenLoadFileExtra() {
		return hiddenLoadFileExtra;
	}

	public void setHiddenLoadFileExtra(String hiddenLoadFileExtra) {
		this.hiddenLoadFileExtra = hiddenLoadFileExtra;
	}
	
	public DatiDichiarazioneCambioResidenza clone() {
		DatiDichiarazioneCambioResidenza d = new DatiDichiarazioneCambioResidenza();
		d.setAbilitaCambioAbitazione(abilitaCambioAbitazione);
		d.setAltroAllegato(altroAllegato);
		d.setAltroMotivoDichiarazione(altroMotivoDichiarazione);
		d.setAltroMotivoDichiarazioneHidden(altroMotivoDichiarazioneHidden);
		d.setCellulare(cellulare);
		d.setCittadinanza(cittadinanza);
		d.setCittadinanzaHidden(cittadinanzaHidden);
		d.setCivicoResidenza(civicoResidenza);
		d.setCivicoTextHidden(civicoTextHidden);
		d.setCodDelegante(codDelegante);
		d.setCodiceFiscale(codiceFiscale);
		d.setCodiceViaResidenza(codiceViaResidenza);
		d.setCognome(cognome);
		d.setComuneIscrizioneAIRE(comuneIscrizioneAIRE);
		d.setComuneIscrizioneAIREHidden(comuneIscrizioneAIREHidden);
		d.setComuneNascita(comuneNascita);
		d.setComuneNascitaEstero(comuneNascitaEstero);
		d.setComuneNascitaEsteroHidden(comuneNascitaEsteroHidden);
		d.setComuneNascitaHidden(comuneNascitaHidden);
		d.setComuneResidenza(comuneResidenza);
		d.setComuneResidenzaHidden(comuneResidenzaHidden);
		d.setCondNonProfessionale(condNonProfessionale);
		d.setCondNonProfessionaleHidden(condNonProfessionaleHidden);
		d.setDataNascita(dataNascita);
		d.setDataRilascio(dataRilascio);
		d.setDataRilascioPatente(dataRilascioPatente);
		d.setDataScadenza(dataScadenza);
		d.setDettaglioPianoResidenza(dettaglioPianoResidenza);
		d.setDichiarazioneCompletata(dichiarazioneCompletata);
		d.setDichiarazioneTitoloAbitativo(dichiarazioneTitoloAbitativo);
		d.setEmail(email);
		d.setEsponenteResidenza(esponenteResidenza);
		d.setFamiliari(familiari);
		d.setFileAmpliamentoNucleoFamiliare(fileAmpliamentoNucleoFamiliare);
		d.setFileAssensoProprietario(fileAssensoProprietario);
		d.setFileExtra(fileExtra);
		d.setFileIntestatarioEdiResPub(fileIntestatarioEdiResPub);
		d.setFileIntra(fileIntra);
		d.setFileUsufruttuario(fileUsufruttuario);
		d.setFirmaGrafometrica(firmaGrafometrica);
		d.setFirmaGrafometricaBase64(firmaGrafometricaBase64);
		d.setFlagIscritto(flagIscritto);
		d.setFlagNoteInformative(flagNoteInformative);
		d.setFoglio(foglio);
		d.setGeneratedFile(generatedFile);
		d.setHiddenLoadFileAmpliamentoNucleoFamiliare(hiddenLoadFileAmpliamentoNucleoFamiliare);
		d.setHiddenLoadFileAssensoProprietario(hiddenLoadFileAssensoProprietario);
		d.setHiddenLoadFileClick(hiddenLoadFileClick);
		d.setHiddenLoadFileExtra(hiddenLoadFileExtra);
		d.setHiddenLoadFileIntestatarioEdiResPub(hiddenLoadFileIntestatarioEdiResPub);
		d.setHiddenLoadFileIntra(hiddenLoadFileIntra);
		d.setHiddenLoadFileUsufruttuario(hiddenLoadFileUsufruttuario);
		d.setIdentificativoFamiglia(identificativoFamiglia);
		d.setIdentificativoUtente(identificativoUtente);
		d.setIndirizzoResidenza(indirizzoResidenza);
		d.setInizioOperazione(inizioOperazione);
		d.setInternoResidenza(internoResidenza);
		d.setIpAddress(ipAddress);
		d.setIscrittoCodiceFiscale(iscrittoCodiceFiscale);
		d.setIscrittoCognome(iscrittoCognome);
		d.setIscrittoDataNascita(iscrittoDataNascita);
		d.setIscrittoLuogoNascita(iscrittoLuogoNascita);
		d.setIscrittoNome(iscrittoNome);
		d.setIscrittoParentela(iscrittoParentela);
		d.setIscrittoTipoParentela(iscrittoTipoParentela);
		d.setIscrittoTipoParentelaHidden(iscrittoTipoParentelaHidden);
		d.setLocalitaResidenza(localitaResidenza);
		d.setMultipartFiles(multipartFiles);
		d.setNome(cognome);
		d.setNoteGenerali(noteGenerali);
		d.setNumeroParenti(numeroParenti);
		d.setNumPatente(numPatente);
		d.setNumSogg(numSogg);
		d.setNuovaCorte(nuovaCorte);
		d.setNuovaLocalitaHidden(nuovaLocalitaHidden);
		d.setNuovaScala(nuovaScala);
		d.setNuovaVia(nuovaVia);
		d.setNuovaViaTextHidden(nuovaViaTextHidden);
		d.setNuovoCodiceViaHidden(nuovoCodiceViaHidden);
		d.setNuovoEsp(nuovoEsp);
		d.setNuovoIndirizzoBarratoAscot(nuovoIndirizzoBarratoAscot);
		d.setNuovoIndirizzoCodiceFrazioneAscot(nuovoIndirizzoCodiceFrazioneAscot);
		d.setNuovoIndirizzoCodiceViaAscot(nuovoIndirizzoCodiceViaAscot);
		d.setNuovoIndirizzoCorteAscot(nuovoIndirizzoCorteAscot);
		d.setNuovoIndirizzoIdentificativoCivicoAscot(nuovoIndirizzoIdentificativoCivicoAscot);
		d.setNuovoIndirizzoNumeroCivicoAscot(nuovoIndirizzoNumeroCivicoAscot);
		d.setNuovoInterno(nuovoInterno);
		d.setNuovoNum(nuovoNum);
		d.setNuovoNumTextHidden(nuovoNumTextHidden);
		d.setNuovoPiano(nuovoPiano);
		d.setOrganoRilascioPatente(organoRilascioPatente);
		d.setOrganoRilascioPatenteHidden(organoRilascioPatenteHidden);
		d.setParticella(particella);
		d.setPec(pec);
		d.setPersoneInteressate(personeInteressate);
		d.setPianoResidenza(dettaglioPianoResidenza);
		d.setProfessione(professione);
		d.setProfessioneHidden(professioneHidden);
		d.setProvinciaNascita(provinciaNascita);
		d.setProvinciaResidenza(provinciaResidenza);
		d.setProvPatente(provPatente);
		d.setQuestura(questura);
		d.setRecCellulare(recCellulare);
		d.setRecCivico(recCivico);
		d.setRecComune(recComune);
		d.setRecComuneHidden(recComuneHidden);
		d.setRecEmail(recEmail);
		d.setRecEsponente(recEsponente);
		d.setRecFax(recFax);
		d.setRecPec(recPec);
		d.setRecProvincia(recProvincia);
		d.setRecTelefono(recTelefono);
		d.setRecVia(recVia);
		d.setScalaResidenza(scalaResidenza);
		d.setSesso(sesso);
		d.setSezione(sezione);
		d.setStardarioResidenzaEnable(stardarioResidenzaEnable);
		d.setStatoCivile(statoCivile);
		d.setStatoCivileHidden(statoCivileHidden);
		d.setStatoEstero(statoEstero);
		d.setStatoEsteroHidden(statoEsteroHidden);
		d.setStatoEsteroNascita(statoEsteroNascita);
		d.setStatoEsteroNascitaHidden(statoEsteroNascitaHidden);
		d.setSubalterno(subalterno);
		d.setTelefono(recTelefono);
		d.setTipoCambioAbitazione(tipoCambioAbitazione);
		d.setTipoDichiarazione(tipoDichiarazione);
		d.setTipoPatente(tipoPatente);
		d.setTitoloAbitativo(titoloAbitativo);
		d.setTitoloAbitativo1(titoloAbitativo1);
		d.setTitoloAbitativo2(titoloAbitativo2);
		d.setTitoloAbitativo3(titoloAbitativo3);
		d.setTitoloAbitativo4(titoloAbitativo4);
		d.setTitoloAbitativo5(titoloAbitativo5);
		d.setTitoloAbitativo6(titoloAbitativo6);
		d.setTitoloAbitativo7(titoloAbitativo7);
		d.setTitoloAbitativo8(titoloAbitativo8);
		d.setTitoloAbitativo9(titoloAbitativo9);
		d.setTitoloAbitativoAgenzia1(titoloAbitativoAgenzia1);
		d.setTitoloAbitativoAgenzia2(titoloAbitativoAgenzia2);
		d.setTitoloAbitativoAltro1(titoloAbitativoAltro1);
		d.setTitoloAbitativoAltro2(titoloAbitativoAltro2);
		d.setTitoloAbitativoData1(titoloAbitativoData1);
		d.setTitoloAbitativoData2(titoloAbitativoData2);
		d.setTitoloAbitativointraextra(titoloAbitativointraextra);
		d.setTitoloAbitativoNumero1(titoloAbitativoNumero1);
		d.setTitoloAbitativoNumero2(titoloAbitativoNumero2);
		d.setTitoloStudio(titoloStudio);
		d.setTitoloStudioHidden(titoloStudioHidden);
		d.setUuidOperazione(uuidOperazione);
		d.setVeicoli(veicoli);
		d.setVerificaFamiliari(verificaFamiliari);
		d.setViaTextHidden(nuovaViaTextHidden);
		return d;
	}
}
