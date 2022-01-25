package it.osapulie.tributi.web.portlet.variazioneimu.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import it.osapulie.tributi.web.portlet.dichiarazionetassaimmobili.form.DatiDichiarazioneTassaImmobili.PosizioniTassaImmobiliDichiarazione;

/**
 * Classe contenente i dati per la generazione della Variazione IMU.
 *
 * @author Maria Michela Birtolo
 * @author Antonio Abbinante
 *
 */
public class DatiVariazioneImu implements Serializable {

	private static final long serialVersionUID = 1L;
	String nome;
	String cognome;
	String codiceFiscale;
	String telefono;
	String email;
	String dataNascita;
	String comuneNascita;
	String provinciaNascita;
	String comuneResidenza;
	String indirizzoResidenza;
	String provResidenza;
	String capResidenza;
	String note;

	// String data;
	// String numAtto;
	// String notaio;
	// boolean acquistato;
	// boolean venduto;
	// String comuneImmobile;
	// String indirizzoImmobile;
	// String sezione;
	// String foglioCatastale;
	// String particella;
	// String sub;
	// String categoriaImmobile;
	// String renditaImmobile;
	// String partitaTerreni;

	// CAMPI NUOVA VERSIONE
	List<PosizioniTassaImmobiliDichiarazione> posizioni;
	String sesso;
	String civico;
	String scala;
	String piano;
	String interno;
	String dataNascitaDic;
	String comuneNascitaDic;
	String provinciaNascitaDic;
	String sessoDic;
	// String statoDic;
	// String indirizzoStatoEsteroDic;
	// String localitaEsteroDic;
	// String codiceStatoEsteroDic;
	String nomeDic;
	String cognomeDic;
	String codiceFiscaleDic;
	String telefonoDic;
	String emailDic;
	String naturaCarica;
	String comuneResidenzaDic;
	String indirizzoResidenzaDic;
	String civicoDic;
	String capResidenzaDic;
	String provResidenzaDic;
	List<Contitolare> contitolari = new ArrayList<DatiVariazioneImu.Contitolare>();

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

	public List<PosizioniTassaImmobiliDichiarazione> getPosizioni() {
		return posizioni;
	}

	public void setPosizioni(List<PosizioniTassaImmobiliDichiarazione> posizioni) {
		this.posizioni = posizioni;
	}

	public String getCivico() {
		return civico;
	}

	public void setCivico(String civico) {
		this.civico = civico;
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
		return dataNascitaDic;
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

	public String getIndirizzoResidenzaDic() {
		return indirizzoResidenzaDic;
	}

	public void setIndirizzoResidenzaDic(String indirizzoResidenzaDic) {
		this.indirizzoResidenzaDic = indirizzoResidenzaDic;
	}

	public String getCivicoDic() {
		return civicoDic;
	}

	public void setCivicoDic(String civicoDic) {
		this.civicoDic = civicoDic;
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

	public List<Contitolare> getContitolari() {
		return contitolari;
	}

	public void setContitolari(List<Contitolare> contitolari) {
		this.contitolari = contitolari;
	}

	/**
	 *
	 * @author Antonio Abbinante
	 *
	 */
	public static class Contitolare implements Serializable {

		private final static long serialVersionUID = 1L;

		protected String nome;
		protected String cognome;
		protected String codiceFiscale;
		protected String telefono;
		protected String email;
		protected String dataNascita;
		protected String comuneNascita;
		protected String provinciaNascita;
		protected String comuneResidenza;
		protected String indirizzoResidenza;
		protected String provResidenza;
		protected String capResidenza;
		protected String sesso;
		protected String civico;
		protected Double percentualePossesso;
		protected Double detrazioneAbitazionePrincipale;

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

		public String getCapResidenza() {
			return capResidenza;
		}

		public void setCapResidenza(String capResidenza) {
			this.capResidenza = capResidenza;
		}

		public String getSesso() {
			return sesso;
		}

		public void setSesso(String sesso) {
			this.sesso = sesso;
		}

		public String getCivico() {
			return civico;
		}

		public void setCivico(String civico) {
			this.civico = civico;
		}

		public Double getPercentualePossesso() {
			return percentualePossesso;
		}

		public void setPercentualePossesso(Double percentualePossesso) {
			this.percentualePossesso = percentualePossesso;
		}

		public Double getDetrazioneAbitazionePrincipale() {
			return detrazioneAbitazionePrincipale;
		}

		public void setDetrazioneAbitazionePrincipale(Double detrazioneAbitazionePrincipale) {
			this.detrazioneAbitazionePrincipale = detrazioneAbitazionePrincipale;
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

	}
}
