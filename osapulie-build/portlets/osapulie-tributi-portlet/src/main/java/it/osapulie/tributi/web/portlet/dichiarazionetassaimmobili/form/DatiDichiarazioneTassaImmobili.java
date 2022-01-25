package it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import it.osapulie.tributi.web.ws.output.types.DatiTassaImmobili;

/**
 * Classe contenente i dati per la generazione della dichiarazione tassa sugli immobili.
 *
 * @author Maria Michela Birtolo
 * @author Gianluca Pindinelli
 *
 */
public class DatiDichiarazioneTassaImmobili implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// CAMPI NUOVA VERSIONE
	String civico;
	String scala;
	String piano;
	String interno;
	String dataNascitaDic;
	String comuneNascitaDic;
	String provinciaNascitaDic;
	String sessoDic;
	String statoDic;
	String indirizzoStatoEsteroDic;
	String localitaEsteroDic;
	String codiceStatoEsteroDic;
	String codiceFiscaleSocietaEnteDic;
	String tipoPresentazioneDic;
	String codiceFiscaleIntermediarioDic;
	String numIscrizioneAlboCAFDic;
	boolean impegnoPresentazioneTelematicaDic;
	String dataImpegnoPresentazioneDic;
	Double imuDovuta;
	Double eccedenzaImuPrecedenteDichiarazione;
	Double eccedenzaImuPrecedenteDichiarazioneCompensataF24;
	Double rateVersateImu;
	Double imuDebito;
	Double imuCredito;
	Double tasiDovuta;
	Double eccedenzaTasiPrecedenteDichiarazione;
	Double eccedenzaTasiPrecedenteDichiarazioneCompensataF24;
	Double rateVersateTasi;
	Double tasiDebito;
	Double tasiCredito;
	Double imuCreditoPrecedenteDichiarazione;
	Double imuCreditoRimborso;
	Double imuCreditoCompensazione;
	Double tasiCreditoPrecedenteDichiarazione;
	Double tasiCreditoRimborso;
	Double tasiCreditoCompensazione;
	String denominazione;
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	SimpleDateFormat sdfOld = new SimpleDateFormat("dd-MM-yyyy");

	String nome;
	String cognome;
	String codiceFiscale;
	String telefono;
	String email;
	String dataNascita;
	String comuneNascita;
	String provinciaNascita;
	String sesso;
	String comuneResidenza;
	String indirizzo;
	String capResidenza;
	String provResidenza;

	String nomeDic;
	String cognomeDic;
	String codiceFiscaleDic;
	String telefonoDic;
	String emailDic;
	String naturaCarica;
	String comuneResidenzaDic;
	String indirizzoDic;
	String civicoDic;
	String capResidenzaDic;
	String provResidenzaDic;

	private String partitaIva;

	// SEMBRA NON ESSERCI PIU LA SEZIONE CONTITOLARE
	// String contitolare;
	// String codiceFiscaleContit;
	// String percPoss;
	// String mesiPoss;
	// String detrazione;
	// String dataNascitaContit;
	// String comuneNascitaContit;
	// String provinciaNascitaContit;
	// String sessoContit;
	// String comuneResidenzaContit;
	// String indirizzoContit;
	// String capResidenzaContit;
	// String provResidenzaContit;
	// boolean posseduto;
	// boolean escluso;
	// boolean riduzione;
	// boolean abitazionePrincipale;
	// String contitolare1;
	// String codiceFiscaleContit1;
	// String percPoss1;
	// String mesiPoss1;
	// String detrazione1;
	// String dataNascitaContit1;
	// String comuneNascitaContit1;
	// String provinciaNascitaContit1;
	// String sessoContit1;
	// String comuneResidenzaContit1;
	// String indirizzoContit1;
	// String capResidenzaContit1;
	// String provResidenzaContit1;
	// boolean posseduto1;
	// boolean escluso1;
	// boolean riduzione1;
	// boolean abitazionePrincipale1;

	String note;

	// List<PosizioniTassaImmobiliDichiarazione> posizioni;
	List<PosizioniTassaImmobiliDichiarazione> posizioniTotalmenteImponibili;
	List<PosizioniTassaImmobiliDichiarazione> posizioniParzialmenteImponibiliOEsenti;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDataNascita() {
		if (dataNascita != null && !dataNascita.equalsIgnoreCase("")) {
			try {
				return sdf.format(sdfOld.parse(dataNascita));
			}
			catch (ParseException e) {
				return dataNascita;
			}
		}
		else {
			return dataNascita;
		}
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

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getCapResidenza() {
		return capResidenza;
	}

	public void setCapResidenza(String capResidenza) {
		this.capResidenza = capResidenza;
	}

	public String getProvResidenza() {
		return provResidenza;
	}

	public void setProvResidenza(String provResidenza) {
		this.provResidenza = provResidenza;
	}

	public String getNomeDic() {
		return nomeDic;
	}

	public void setNomeDic(String nomeDic) {
		this.nomeDic = nomeDic;
	}

	public String getCognomeDic() {
		return cognomeDic;
	}

	public void setCognomeDic(String cognomeDic) {
		this.cognomeDic = cognomeDic;
	}

	public String getCodiceFiscaleDic() {
		return codiceFiscaleDic;
	}

	public void setCodiceFiscaleDic(String codiceFiscaleDic) {
		this.codiceFiscaleDic = codiceFiscaleDic;
	}

	public String getTelefonoDic() {
		return telefonoDic;
	}

	public void setTelefonoDic(String telefonoDic) {
		this.telefonoDic = telefonoDic;
	}

	public String getEmailDic() {
		return emailDic;
	}

	public void setEmailDic(String emailDic) {
		this.emailDic = emailDic;
	}

	public String getNaturaCarica() {
		return naturaCarica;
	}

	public void setNaturaCarica(String naturaCarica) {
		this.naturaCarica = naturaCarica;
	}

	public String getComuneResidenzaDic() {
		return comuneResidenzaDic;
	}

	public void setComuneResidenzaDic(String comuneResidenzaDic) {
		this.comuneResidenzaDic = comuneResidenzaDic;
	}

	public String getIndirizzoDic() {
		return indirizzoDic;
	}

	public void setIndirizzoDic(String indirizzoDic) {
		this.indirizzoDic = indirizzoDic;
	}

	public String getCapResidenzaDic() {
		return capResidenzaDic;
	}

	public void setCapResidenzaDic(String capResidenzaDic) {
		this.capResidenzaDic = capResidenzaDic;
	}

	public String getProvResidenzaDic() {
		return provResidenzaDic;
	}

	public void setProvResidenzaDic(String provResidenzaDic) {
		this.provResidenzaDic = provResidenzaDic;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	// public List<PosizioniTassaImmobiliDichiarazione> getPosizioni() {
	// return posizioni;
	// }
	//
	// public void setPosizioni(List<PosizioniTassaImmobiliDichiarazione> posizioni) {
	// this.posizioni = posizioni;
	// }

	/**
	 *
	 * @author Maria Michela Birtolo
	 *
	 */
	public static class PosizioniTassaImmobiliDichiarazione extends DatiTassaImmobili.Posizioni implements Serializable {

		private final static long serialVersionUID = 1L;

		// CAMPI NUOVA VERSIONE
		protected String dataInizioPossesso;
		protected String annotazioni;
		protected String tipoCatasto;
		protected String codiceCatasto;
		protected boolean attivitaSvolta1;
		protected boolean attivitaSvolta2;
		protected boolean attivitaSvolta3;
		protected boolean attivitaSvolta4;
		protected boolean attivitaSvolta5;
		protected boolean attivitaSvolta6;
		protected boolean attivitaSvolta7;
		protected boolean attivitaSvolta8;
		protected boolean attivitaSvolta9;
		protected boolean attivitaSvolta10;

		// Per gli immobili esenti o non totalmente imponibili
		protected boolean inagibile;
		protected boolean esenzione;
		protected Long cmAttivitaDidattica;
		protected Long cmsAttivitaDidattica;
		protected Double aAttivitaDidattica;
		protected Double a1AttivitaDidattica;
		protected Double bAttivitaDidattica;
		protected Double b1AttivitaDidattica;
		protected Double cAttivitaDidattica;
		protected Double dAttivitaDidattica;
		protected Double eAttivitaDidattica;
		protected Double fAttivitaDidattica;
		protected boolean gAttivitaDidattica;
		protected boolean hAttivitaDidattica;
		protected Double iAttivitaDidattica;
		protected Double jAttivitaDidattica;
		protected Double kAttivitaDidattica;

		protected Long cencAltreAttivita;
		protected Long cmAltreAttivita;
		protected Double aAltreAttivita;
		protected Double a1AltreAttivita;
		protected Double bAltreAttivita;
		protected Double b1AltreAttivita;
		protected Double cAltreAttivita;
		protected Double dAltreAttivita;
		protected Double eAltreAttivita;

		protected String classe;
		// inseriti solo se estremi catastali non noti
		protected String numProt;
		protected String anno;

		// inserito solo se si tratta di immobile storico
		protected boolean immobileStorico;

		protected Integer mesiEsclusione;

		// se il contribuente ha ceduto il diritto sull'immobile
		protected boolean diCessione;
		// se il contribuente ha acquistato il diritto sull'immobile
		protected boolean diAcquisto;
		// va indicata l'agenzia o in assenza estremi dell'atto, del contratto o della concessione.
		protected String agenziaEntrate;
		protected String estremiTitolo;

		// escluso, esente o rurale
		protected boolean escluso;

		protected String dataUltimazioneLavori;

		// Campi aggiunti per variazione TASI
		protected String dataInizioOccupazione; // Data Decorrenza
		protected Double superficieOccupata;
		protected Double superficiEscluse;
		protected Double areeScoperteOperative;
		protected String nomeProprietario;
		protected String cognomeProprietario;
		protected String comuneResidenzaProprietario;
		protected String indirizzoResidenzaProprietario;
		protected String provinciaResidenzaProprietario;
		protected String civicoProprietario;
		protected String titoloProprietà;
		protected String altroTitoloProprietà;
		protected String tipologiaUtenza;
		protected String destinazioneProprietà;
		protected String altroDestinazioneProprietà;

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfOld = new SimpleDateFormat("dd-MM-yyyy");

		public String getClasse() {
			return classe;
		}

		public void setClasse(String classe) {
			this.classe = classe;
		}

		public String getNumProt() {
			return numProt;
		}

		public void setNumProt(String numProt) {
			this.numProt = numProt;
		}

		public String getAnno() {
			return anno;
		}

		public void setAnno(String anno) {
			this.anno = anno;
		}

		public boolean isImmobileStorico() {
			return immobileStorico;
		}

		public void setImmobileStorico(boolean immobileStorico) {
			this.immobileStorico = immobileStorico;
		}

		public Integer getMesiEsclusione() {
			return mesiEsclusione;
		}

		public void setMesiEsclusione(Integer mesiEsclusione) {
			this.mesiEsclusione = mesiEsclusione;
		}

		public boolean isDiCessione() {
			return diCessione;
		}

		public void setDiCessione(boolean diCessione) {
			this.diCessione = diCessione;
		}

		public boolean isDiAcquisto() {
			return diAcquisto;
		}

		public void setDiAcquisto(boolean diAcquisto) {
			this.diAcquisto = diAcquisto;
		}

		public String getAgenziaEntrate() {
			return agenziaEntrate;
		}

		public void setAgenziaEntrate(String agenziaEntrate) {
			this.agenziaEntrate = agenziaEntrate;
		}

		public String getEstremiTitolo() {
			return estremiTitolo;
		}

		public void setEstremiTitolo(String estremiTitolo) {
			this.estremiTitolo = estremiTitolo;
		}

		public boolean isEscluso() {
			return escluso;
		}

		public void setEscluso(boolean escluso) {
			this.escluso = escluso;
		}

		public String getDataInizioPossesso() {
			if (dataInizioPossesso != null && !dataInizioPossesso.equalsIgnoreCase("")) {
				try {
					return sdf.format(sdfOld.parse(dataInizioPossesso));
				}
				catch (ParseException e) {
					return dataInizioPossesso;
				}
			}
			else {
				return dataInizioPossesso;
			}
		}

		public void setDataInizioPossesso(String dataInizioPossesso) {
			this.dataInizioPossesso = dataInizioPossesso;
		}

		public String getAnnotazioni() {
			return annotazioni;
		}

		public void setAnnotazioni(String annotazioni) {
			this.annotazioni = annotazioni;
		}

		public String getTipoCatasto() {
			return tipoCatasto;
		}

		public void setTipoCatasto(String tipoCatasto) {
			this.tipoCatasto = tipoCatasto;
		}

		public String getCodiceCatasto() {
			return codiceCatasto;
		}

		public void setCodiceCatasto(String codiceCatasto) {
			this.codiceCatasto = codiceCatasto;
		}

		public boolean isInagibile() {
			return inagibile;
		}

		public void setInagibile(boolean inagibile) {
			this.inagibile = inagibile;
		}

		public boolean isEsenzione() {
			return esenzione;
		}

		public void setEsenzione(boolean esenzione) {
			this.esenzione = esenzione;
		}

		public Long getCmAttivitaDidattica() {
			return cmAttivitaDidattica;
		}

		public void setCmAttivitaDidattica(Long cmAttivitaDidattica) {
			this.cmAttivitaDidattica = cmAttivitaDidattica;
		}

		public Long getCmsAttivitaDidattica() {
			return cmsAttivitaDidattica;
		}

		public void setCmsAttivitaDidattica(Long cmsAttivitaDidattica) {
			this.cmsAttivitaDidattica = cmsAttivitaDidattica;
		}

		public Double getaAttivitaDidattica() {
			return aAttivitaDidattica;
		}

		public void setaAttivitaDidattica(Double aAttivitaDidattica) {
			this.aAttivitaDidattica = aAttivitaDidattica;
		}

		public Double getA1AttivitaDidattica() {
			return a1AttivitaDidattica;
		}

		public void setA1AttivitaDidattica(Double a1AttivitaDidattica) {
			this.a1AttivitaDidattica = a1AttivitaDidattica;
		}

		public Double getbAttivitaDidattica() {
			return bAttivitaDidattica;
		}

		public void setbAttivitaDidattica(Double bAttivitaDidattica) {
			this.bAttivitaDidattica = bAttivitaDidattica;
		}

		public Double getB1AttivitaDidattica() {
			return b1AttivitaDidattica;
		}

		public void setB1AttivitaDidattica(Double b1AttivitaDidattica) {
			this.b1AttivitaDidattica = b1AttivitaDidattica;
		}

		public Double getcAttivitaDidattica() {
			return cAttivitaDidattica;
		}

		public void setcAttivitaDidattica(Double cAttivitaDidattica) {
			this.cAttivitaDidattica = cAttivitaDidattica;
		}

		public Double getdAttivitaDidattica() {
			return dAttivitaDidattica;
		}

		public void setdAttivitaDidattica(Double dAttivitaDidattica) {
			this.dAttivitaDidattica = dAttivitaDidattica;
		}

		public Double geteAttivitaDidattica() {
			return eAttivitaDidattica;
		}

		public void seteAttivitaDidattica(Double eAttivitaDidattica) {
			this.eAttivitaDidattica = eAttivitaDidattica;
		}

		public Double getfAttivitaDidattica() {
			return fAttivitaDidattica;
		}

		public void setfAttivitaDidattica(Double fAttivitaDidattica) {
			this.fAttivitaDidattica = fAttivitaDidattica;
		}

		public boolean isgAttivitaDidattica() {
			return gAttivitaDidattica;
		}

		public void setgAttivitaDidattica(boolean gAttivitaDidattica) {
			this.gAttivitaDidattica = gAttivitaDidattica;
		}

		public boolean ishAttivitaDidattica() {
			return hAttivitaDidattica;
		}

		public void sethAttivitaDidattica(boolean hAttivitaDidattica) {
			this.hAttivitaDidattica = hAttivitaDidattica;
		}

		public Double getiAttivitaDidattica() {
			return iAttivitaDidattica;
		}

		public void setiAttivitaDidattica(Double iAttivitaDidattica) {
			this.iAttivitaDidattica = iAttivitaDidattica;
		}

		public Double getjAttivitaDidattica() {
			return jAttivitaDidattica;
		}

		public void setjAttivitaDidattica(Double jAttivitaDidattica) {
			this.jAttivitaDidattica = jAttivitaDidattica;
		}

		public Double getkAttivitaDidattica() {
			return kAttivitaDidattica;
		}

		public void setkAttivitaDidattica(Double kAttivitaDidattica) {
			this.kAttivitaDidattica = kAttivitaDidattica;
		}

		public Long getCencAltreAttivita() {
			return cencAltreAttivita;
		}

		public void setCencAltreAttivita(Long cencAltreAttivita) {
			this.cencAltreAttivita = cencAltreAttivita;
		}

		public Long getCmAltreAttivita() {
			return cmAltreAttivita;
		}

		public void setCmAltreAttivita(Long cmAltreAttivita) {
			this.cmAltreAttivita = cmAltreAttivita;
		}

		public Double getaAltreAttivita() {
			return aAltreAttivita;
		}

		public void setaAltreAttivita(Double aAltreAttivita) {
			this.aAltreAttivita = aAltreAttivita;
		}

		public Double getA1AltreAttivita() {
			return a1AltreAttivita;
		}

		public void setA1AltreAttivita(Double a1AltreAttivita) {
			this.a1AltreAttivita = a1AltreAttivita;
		}

		public Double getbAltreAttivita() {
			return bAltreAttivita;
		}

		public void setbAltreAttivita(Double bAltreAttivita) {
			this.bAltreAttivita = bAltreAttivita;
		}

		public Double getB1AltreAttivita() {
			return b1AltreAttivita;
		}

		public void setB1AltreAttivita(Double b1AltreAttivita) {
			this.b1AltreAttivita = b1AltreAttivita;
		}

		public Double getcAltreAttivita() {
			return cAltreAttivita;
		}

		public void setcAltreAttivita(Double cAltreAttivita) {
			this.cAltreAttivita = cAltreAttivita;
		}

		public Double getdAltreAttivita() {
			return dAltreAttivita;
		}

		public void setdAltreAttivita(Double dAltreAttivita) {
			this.dAltreAttivita = dAltreAttivita;
		}

		public Double geteAltreAttivita() {
			return eAltreAttivita;
		}

		public void seteAltreAttivita(Double eAltreAttivita) {
			this.eAltreAttivita = eAltreAttivita;
		}

		public boolean isAttivitaSvolta1() {
			return attivitaSvolta1;
		}

		public void setAttivitaSvolta1(boolean attivitaSvolta1) {
			this.attivitaSvolta1 = attivitaSvolta1;
		}

		public boolean isAttivitaSvolta2() {
			return attivitaSvolta2;
		}

		public void setAttivitaSvolta2(boolean attivitaSvolta2) {
			this.attivitaSvolta2 = attivitaSvolta2;
		}

		public boolean isAttivitaSvolta3() {
			return attivitaSvolta3;
		}

		public void setAttivitaSvolta3(boolean attivitaSvolta3) {
			this.attivitaSvolta3 = attivitaSvolta3;
		}

		public boolean isAttivitaSvolta4() {
			return attivitaSvolta4;
		}

		public void setAttivitaSvolta4(boolean attivitaSvolta4) {
			this.attivitaSvolta4 = attivitaSvolta4;
		}

		public boolean isAttivitaSvolta5() {
			return attivitaSvolta5;
		}

		public void setAttivitaSvolta5(boolean attivitaSvolta5) {
			this.attivitaSvolta5 = attivitaSvolta5;
		}

		public boolean isAttivitaSvolta6() {
			return attivitaSvolta6;
		}

		public void setAttivitaSvolta6(boolean attivitaSvolta6) {
			this.attivitaSvolta6 = attivitaSvolta6;
		}

		public boolean isAttivitaSvolta7() {
			return attivitaSvolta7;
		}

		public void setAttivitaSvolta7(boolean attivitaSvolta7) {
			this.attivitaSvolta7 = attivitaSvolta7;
		}

		public boolean isAttivitaSvolta8() {
			return attivitaSvolta8;
		}

		public void setAttivitaSvolta8(boolean attivitaSvolta8) {
			this.attivitaSvolta8 = attivitaSvolta8;
		}

		public boolean isAttivitaSvolta9() {
			return attivitaSvolta9;
		}

		public void setAttivitaSvolta9(boolean attivitaSvolta9) {
			this.attivitaSvolta9 = attivitaSvolta9;
		}

		public boolean isAttivitaSvolta10() {
			return attivitaSvolta10;
		}

		public void setAttivitaSvolta10(boolean attivitaSvolta10) {
			this.attivitaSvolta10 = attivitaSvolta10;
		}

		public String getDataUltimazioneLavori() {
			return dataUltimazioneLavori;
		}

		public void setDataUltimazioneLavori(String dataUltimazioneLavori) {
			this.dataUltimazioneLavori = dataUltimazioneLavori;
		}

		public String getDataInizioOccupazione() {
			return dataInizioOccupazione;
		}

		public void setDataInizioOccupazione(String dataInizioOccupazione) {
			this.dataInizioOccupazione = dataInizioOccupazione;
		}

		public Double getSuperficieOccupata() {
			return superficieOccupata;
		}

		public void setSuperficieOccupata(Double superficieOccupata) {
			this.superficieOccupata = superficieOccupata;
		}

		public Double getSuperficiEscluse() {
			return superficiEscluse;
		}

		public void setSuperficiEscluse(Double superficiEscluse) {
			this.superficiEscluse = superficiEscluse;
		}

		public Double getAreeScoperteOperative() {
			return areeScoperteOperative;
		}

		public void setAreeScoperteOperative(Double areeScoperteOperative) {
			this.areeScoperteOperative = areeScoperteOperative;
		}

		public String getNomeProprietario() {
			return nomeProprietario;
		}

		public void setNomeProprietario(String nomeProprietario) {
			this.nomeProprietario = nomeProprietario;
		}

		public String getCognomeProprietario() {
			return cognomeProprietario;
		}

		public void setCognomeProprietario(String cognomeProprietario) {
			this.cognomeProprietario = cognomeProprietario;
		}

		public String getComuneResidenzaProprietario() {
			return comuneResidenzaProprietario;
		}

		public void setComuneResidenzaProprietario(String comuneResidenzaProprietario) {
			this.comuneResidenzaProprietario = comuneResidenzaProprietario;
		}

		public String getIndirizzoResidenzaProprietario() {
			return indirizzoResidenzaProprietario;
		}

		public void setIndirizzoResidenzaProprietario(String indirizzoResidenzaProprietario) {
			this.indirizzoResidenzaProprietario = indirizzoResidenzaProprietario;
		}

		public String getCivicoProprietario() {
			return civicoProprietario;
		}

		public void setCivicoProprietario(String civicoProprietario) {
			this.civicoProprietario = civicoProprietario;
		}

		public String getTitoloProprietà() {
			return titoloProprietà;
		}

		public void setTitoloProprietà(String titoloProprietà) {
			this.titoloProprietà = titoloProprietà;
		}

		public String getAltroTitoloProprietà() {
			return altroTitoloProprietà;
		}

		public void setAltroTitoloProprietà(String altroTitoloProprietà) {
			this.altroTitoloProprietà = altroTitoloProprietà;
		}

		public String getTipologiaUtenza() {
			return tipologiaUtenza;
		}

		public void setTipologiaUtenza(String tipologiaUtenza) {
			this.tipologiaUtenza = tipologiaUtenza;
		}

		public String getDestinazioneProprietà() {
			return destinazioneProprietà;
		}

		public void setDestinazioneProprietà(String destinazioneProprietà) {
			this.destinazioneProprietà = destinazioneProprietà;
		}

		public String getAltroDestinazioneProprietà() {
			return altroDestinazioneProprietà;
		}

		public void setAltroDestinazioneProprietà(String altroDestinazioneProprietà) {
			this.altroDestinazioneProprietà = altroDestinazioneProprietà;
		}

		public String getProvinciaResidenzaProprietario() {
			return provinciaResidenzaProprietario;
		}

		public void setProvinciaResidenzaProprietario(String provinciaResidenzaProprietario) {
			this.provinciaResidenzaProprietario = provinciaResidenzaProprietario;
		}

	}

	public String getScala() {
		return scala;
	}

	public void setScala(String scala) {
		this.scala = scala;
	}

	public String getPiano() {
		return piano;
	}

	public void setPiano(String piano) {
		this.piano = piano;
	}

	public String getInterno() {
		return interno;
	}

	public void setInterno(String interno) {
		this.interno = interno;
	}

	public String getDataNascitaDic() {
		if (dataNascitaDic != null && !dataNascitaDic.equalsIgnoreCase("")) {
			try {
				return sdf.format(sdfOld.parse(dataNascitaDic));
			}
			catch (ParseException e) {
				return dataNascitaDic;
			}
		}
		else {
			return dataNascitaDic;
		}
	}

	public void setDataNascitaDic(String dataNascitaDic) {
		this.dataNascitaDic = dataNascitaDic;
	}

	public String getComuneNascitaDic() {
		return comuneNascitaDic;
	}

	public void setComuneNascitaDic(String comuneNascitaDic) {
		this.comuneNascitaDic = comuneNascitaDic;
	}

	public String getProvinciaNascitaDic() {
		return provinciaNascitaDic;
	}

	public void setProvinciaNascitaDic(String provinciaNascitaDic) {
		this.provinciaNascitaDic = provinciaNascitaDic;
	}

	public String getSessoDic() {
		return sessoDic;
	}

	public void setSessoDic(String sessoDic) {
		this.sessoDic = sessoDic;
	}

	public String getStatoDic() {
		return statoDic;
	}

	public void setStatoDic(String statoDic) {
		this.statoDic = statoDic;
	}

	public String getIndirizzoStatoEsteroDic() {
		return indirizzoStatoEsteroDic;
	}

	public void setIndirizzoStatoEsteroDic(String indirizzoStatoEsteroDic) {
		this.indirizzoStatoEsteroDic = indirizzoStatoEsteroDic;
	}

	public String getCodiceStatoEsteroDic() {
		return codiceStatoEsteroDic;
	}

	public void setCodiceStatoEsteroDic(String codiceStatoEsteroDic) {
		this.codiceStatoEsteroDic = codiceStatoEsteroDic;
	}

	public String getCodiceFiscaleSocietaEnteDic() {
		return codiceFiscaleSocietaEnteDic;
	}

	public void setCodiceFiscaleSocietaEnteDic(String codiceFiscaleSocietaEnteDic) {
		this.codiceFiscaleSocietaEnteDic = codiceFiscaleSocietaEnteDic;
	}

	public String getTipoPresentazioneDic() {
		return tipoPresentazioneDic;
	}

	public void setTipoPresentazioneDic(String tipoPresentazioneDic) {
		this.tipoPresentazioneDic = tipoPresentazioneDic;
	}

	public String getCodiceFiscaleIntermediarioDic() {
		return codiceFiscaleIntermediarioDic;
	}

	public void setCodiceFiscaleIntermediarioDic(String codiceFiscaleIntermediarioDic) {
		this.codiceFiscaleIntermediarioDic = codiceFiscaleIntermediarioDic;
	}

	public String getNumIscrizioneAlboCAFDic() {
		return numIscrizioneAlboCAFDic;
	}

	public void setNumIscrizioneAlboCAFDic(String numIscrizioneAlboCAFDic) {
		this.numIscrizioneAlboCAFDic = numIscrizioneAlboCAFDic;
	}

	public boolean isImpegnoPresentazioneTelematicaDic() {
		return impegnoPresentazioneTelematicaDic;
	}

	public void setImpegnoPresentazioneTelematicaDic(boolean impegnoPresentazioneTelematicaDic) {
		this.impegnoPresentazioneTelematicaDic = impegnoPresentazioneTelematicaDic;
	}

	public String getDataImpegnoPresentazioneDic() {
		if (dataImpegnoPresentazioneDic != null && !dataImpegnoPresentazioneDic.equalsIgnoreCase("")) {
			try {
				return sdf.format(sdfOld.parse(dataImpegnoPresentazioneDic));
			}
			catch (ParseException e) {
				return dataImpegnoPresentazioneDic;
			}
		}
		else {
			return dataImpegnoPresentazioneDic;
		}
	}

	public void setDataImpegnoPresentazioneDic(String dataImpegnoPresentazioneDic) {
		this.dataImpegnoPresentazioneDic = dataImpegnoPresentazioneDic;
	}

	public Double getImuDovuta() {
		return imuDovuta;
	}

	public void setImuDovuta(Double imuDovuta) {
		this.imuDovuta = imuDovuta;
	}

	public Double getEccedenzaImuPrecedenteDichiarazione() {
		return eccedenzaImuPrecedenteDichiarazione;
	}

	public void setEccedenzaImuPrecedenteDichiarazione(Double eccedenzaImuPrecedenteDichiarazione) {
		this.eccedenzaImuPrecedenteDichiarazione = eccedenzaImuPrecedenteDichiarazione;
	}

	public Double getEccedenzaImuPrecedenteDichiarazioneCompensataF24() {
		return eccedenzaImuPrecedenteDichiarazioneCompensataF24;
	}

	public void setEccedenzaImuPrecedenteDichiarazioneCompensataF24(Double eccedenzaImuPrecedenteDichiarazioneCompensataF24) {
		this.eccedenzaImuPrecedenteDichiarazioneCompensataF24 = eccedenzaImuPrecedenteDichiarazioneCompensataF24;
	}

	public Double getRateVersateImu() {
		return rateVersateImu;
	}

	public void setRateVersateImu(Double rateVersateImu) {
		this.rateVersateImu = rateVersateImu;
	}

	public Double getImuDebito() {
		return imuDebito;
	}

	public void setImuDebito(Double imuDebito) {
		this.imuDebito = imuDebito;
	}

	public Double getImuCredito() {
		return imuCredito;
	}

	public void setImuCredito(Double imuCredito) {
		this.imuCredito = imuCredito;
	}

	public Double getTasiDovuta() {
		return tasiDovuta;
	}

	public void setTasiDovuta(Double tasiDovuta) {
		this.tasiDovuta = tasiDovuta;
	}

	public Double getEccedenzaTasiPrecedenteDichiarazione() {
		return eccedenzaTasiPrecedenteDichiarazione;
	}

	public void setEccedenzaTasiPrecedenteDichiarazione(Double eccedenzaTasiPrecedenteDichiarazione) {
		this.eccedenzaTasiPrecedenteDichiarazione = eccedenzaTasiPrecedenteDichiarazione;
	}

	public Double getEccedenzaTasiPrecedenteDichiarazioneCompensataF24() {
		return eccedenzaTasiPrecedenteDichiarazioneCompensataF24;
	}

	public void setEccedenzaTasiPrecedenteDichiarazioneCompensataF24(Double eccedenzaTasiPrecedenteDichiarazioneCompensataF24) {
		this.eccedenzaTasiPrecedenteDichiarazioneCompensataF24 = eccedenzaTasiPrecedenteDichiarazioneCompensataF24;
	}

	public Double getRateVersateTasi() {
		return rateVersateTasi;
	}

	public void setRateVersateTasi(Double rateVersateTasi) {
		this.rateVersateTasi = rateVersateTasi;
	}

	public Double getTasiDebito() {
		return tasiDebito;
	}

	public void setTasiDebito(Double tasiDebito) {
		this.tasiDebito = tasiDebito;
	}

	public Double getTasiCredito() {
		return tasiCredito;
	}

	public void setTasiCredito(Double tasiCredito) {
		this.tasiCredito = tasiCredito;
	}

	public Double getImuCreditoPrecedenteDichiarazione() {
		return imuCreditoPrecedenteDichiarazione;
	}

	public void setImuCreditoPrecedenteDichiarazione(Double imuCreditoPrecedenteDichiarazione) {
		this.imuCreditoPrecedenteDichiarazione = imuCreditoPrecedenteDichiarazione;
	}

	public Double getImuCreditoRimborso() {
		return imuCreditoRimborso;
	}

	public void setImuCreditoRimborso(Double imuCreditoRimborso) {
		this.imuCreditoRimborso = imuCreditoRimborso;
	}

	public Double getImuCreditoCompensazione() {
		return imuCreditoCompensazione;
	}

	public void setImuCreditoCompensazione(Double imuCreditoCompensazione) {
		this.imuCreditoCompensazione = imuCreditoCompensazione;
	}

	public Double getTasiCreditoPrecedenteDichiarazione() {
		return tasiCreditoPrecedenteDichiarazione;
	}

	public void setTasiCreditoPrecedenteDichiarazione(Double tasiCreditoPrecedenteDichiarazione) {
		this.tasiCreditoPrecedenteDichiarazione = tasiCreditoPrecedenteDichiarazione;
	}

	public Double getTasiCreditoRimborso() {
		return tasiCreditoRimborso;
	}

	public void setTasiCreditoRimborso(Double tasiCreditoRimborso) {
		this.tasiCreditoRimborso = tasiCreditoRimborso;
	}

	public Double getTasiCreditoCompensazione() {
		return tasiCreditoCompensazione;
	}

	public void setTasiCreditoCompensazione(Double tasiCreditoCompensazione) {
		this.tasiCreditoCompensazione = tasiCreditoCompensazione;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
	}

	public String getCivicoDic() {
		return civicoDic;
	}

	public void setCivicoDic(String civicoDic) {
		this.civicoDic = civicoDic;
	}

	public String getLocalitaEsteroDic() {
		return localitaEsteroDic;
	}

	public void setLocalitaEsteroDic(String localitaEsteroDic) {
		this.localitaEsteroDic = localitaEsteroDic;
	}

	public List<PosizioniTassaImmobiliDichiarazione> getPosizioniTotalmenteImponibili() {
		return posizioniTotalmenteImponibili;
	}

	public void setPosizioniTotalmenteImponibili(List<PosizioniTassaImmobiliDichiarazione> posizioniTotalmenteImponibili) {
		this.posizioniTotalmenteImponibili = posizioniTotalmenteImponibili;
	}

	public List<PosizioniTassaImmobiliDichiarazione> getPosizioniParzialmenteImponibiliOEsenti() {
		return posizioniParzialmenteImponibiliOEsenti;
	}

	public void setPosizioniParzialmenteImponibiliOEsenti(List<PosizioniTassaImmobiliDichiarazione> posizioniParzialmenteImponibiliOEsenti) {
		this.posizioniParzialmenteImponibiliOEsenti = posizioniParzialmenteImponibiliOEsenti;
	}

	public String getDenominazione() {
		return nome + " " + cognome;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
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
}
