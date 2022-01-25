package it.osapulie.tributi.web.portlet.dichiarazionetari.form;

import java.io.Serializable;
import java.util.List;

/**
 * Classe contenente i dati per la generazione dell'iscrizione all'anagrafe.
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

	/* Dati Anagrafici richiedente */

	private String identificativoUtente;
	private String nome;
	private String cognome;
	private String sesso;
	private String codiceFiscale;
	private String dataNascita;
	private String comuneNascita;
	private String provinciaNascita;
	private String comuneNascitaEstero;
	private String comuneNascitaEsteroHidden;
	private String comuneResidenza;
	private String comuneResidenzaHidden;
	private String provinciaResidenza;
	private String cittadinanza;

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
	private String pianoResidenza;
	private String statoEstero;
	private String statoEsteroHidden;
	private String indirizzoEstero;

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

	/* Dati non obbligatori */

	private List<Componente> familiari;
	/*
	 * Numero di parenti associati al richiedente della dichiarazione di cambio residenza. Serve per
	 * aggiungere dinamicamente i campi dei dati anagrafici dei parenti.
	 */
	private long numeroParenti;

	/* Altri anagrafica */
	private String statoCivile;
	private String professione;
	private String condNonProfessionale;
	private String titoloStudio;

	/* Dati Ministero dei Trasporti */
	private String tipoPatente;
	private String numPatente;
	private String dataRilascioPatente;
	private String organoRilascioPatente;
	private String provPatente;
	// Possibilita di inserire più di una targa, separata da ";"
	private List<Veicolo> veicoli;

	/* Recapiti */
	private String recComune;
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

	private boolean flagIscritto;

	private String identificativoFamiglia;

	public class Veicolo implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 7697978885772460277L;
		private String tipo;
		private String targa;

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
		 * @return the targa
		 */
		public String getTarga() {
			return targa;
		}

		/**
		 * @param targa the targa to set
		 */
		public void setTarga(String targa) {
			this.targa = targa;
		}

	}

	public class Componente implements Serializable {

		/**
		 *
		 */
		private static final long serialVersionUID = 1L;
		private String identificativoUtente;
		private String codiceFiscale;
		private String nome;
		private String cognome;
		private String dataNascitaString;
		private String telefono;
		private String cellulare;
		private String email;
		private String pec;

		private String comuneNascita;
		private String provinciaNascita;
		private String sesso;
		private String cittadinanza;
		private String parentela;
		private String statoCivile;
		private String professione;
		private String condNonProfessionale;
		private String titoloStudio;
		private String tipoPatente;
		private String numPatente;
		private String dataRilascioPatente;
		private String organoRilascioPatente;
		private String provPatente;
		private List<Veicolo> veicoli;

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
		 * @return the parentela
		 */
		public String getParentela() {
			return parentela;
		}

		/**
		 * @param parentela the parentela to set
		 */
		public void setParentela(String parentela) {
			this.parentela = parentela;
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
		 * @return the dataNascitaString
		 */
		public String getDataNascitaString() {
			return dataNascitaString;
		}

		/**
		 * @param dataNascitaString the dataNascitaString to set
		 */
		public void setDataNascitaString(String dataNascitaString) {
			this.dataNascitaString = dataNascitaString;
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
	 * @return the indirizzoEstero
	 */
	public String getIndirizzoEstero() {
		return indirizzoEstero;
	}

	/**
	 * @param indirizzoEstero the indirizzoEstero to set
	 */
	public void setIndirizzoEstero(String indirizzoEstero) {
		this.indirizzoEstero = indirizzoEstero;
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

}
