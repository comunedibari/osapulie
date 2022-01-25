package it.osapulie.sociali.web.portlet.model;

import java.io.Serializable;
import java.util.List;

/**
 * Classe contenente i dati per la generazione della dichiarazione per l'inserimento
 * di anziani presso case di riposo
 * 
 * @author Federico Melli
 * 
 */
public class DatiDichiarazioneCaseRiposo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Parente richiedente;
	String telefono;
	
	/*In questo caso, "ruolo" sta a specificare se il richiedente 
	 * 	effettua la richiesta a titolo personale oppure in qualità di parente o affine*/
	String ruolo;
	String affinita;
	
	/*Se il richiedente è differente dall'anziano, inserisco i dati di quest'ultimo*/
	String anzNome;
	String anzCognome;
	String anzDataNascita;
	String anzComuneNascita;
	String anzProvinciaNascita;
	String anzComuneResidenza;
	String anzProvinciaResidenza;
	String anzCivicoResidenza;
	String anzIndirizzoResidenza;
	String anzTelefono;
	
	/* Tipologia: "casa di riposo" o "casa protetta" */
	String tipologia;
	String denominazione;
	String ubicazione;
	
	/*ALLEGATO 1:Dichiarazione sostitutiva di certificazione e di 
	 * atto di notorietà resa ai sensi degli artt. 46 e 47 del D.P.R. 445/2000  */
	
	String aUnoNome;
	String aUnoCognome;
	String aUnoComuneNascita;
	String aUnoProvinciaNascita;
	String aUnoDataNascita;
	
	 boolean autosufficiente=false;
	 boolean parzialmenteAutosuf=false;
	 boolean patolTemporanea=false;
	 boolean serviziSimiliEnte=false;
	 boolean serviziSimiliComune=false;
	
	String serviziComune;
	String serviziEntiPubblici;
	List<Parente> parenti;

	boolean alimenti;
	boolean appartamentoProprio;
	String canoneAppartamento;
	
	/*ALLEGATO 2:  Dichiarazione sostitutiva di atto di notorietà 
	 * resa ai sensi dell’art.47 del D.P.R. 445/2000 da parte del tenuto 
	 * per legge agli alimenti ai sensi dell’art. 433 c.c.*/
	
//	String aDueNome;
//	String aDueCognome;
//	String aDueComuneNascita;
//	String aDueProvinciaNascita;
//	String aDueDataNascita;
//	String aDueRuolo;
//	String reddito;
//
//	String altro;

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getRuolo() {
		return ruolo;
	}
	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}
	public String getAnzNome() {
		return anzNome;
	}
	public void setAnzNome(String anzNome) {
		this.anzNome = anzNome;
	}
	public String getAnzCognome() {
		return anzCognome;
	}
	public void setAnzCognome(String anzCognome) {
		this.anzCognome = anzCognome;
	}
	public String getAnzDataNascita() {
		return anzDataNascita;
	}
	public void setAnzDataNascita(String anzDataNascita) {
		this.anzDataNascita = anzDataNascita;
	}
	public String getAnzComuneNascita() {
		return anzComuneNascita;
	}
	public void setAnzComuneNascita(String anzComuneNascita) {
		this.anzComuneNascita = anzComuneNascita;
	}
	public String getAnzProvinciaNascita() {
		return anzProvinciaNascita;
	}
	public void setAnzProvinciaNascita(String anzProvinciaNascita) {
		this.anzProvinciaNascita = anzProvinciaNascita;
	}
	public String getAnzComuneResidenza() {
		return anzComuneResidenza;
	}
	public void setAnzComuneResidenza(String anzComuneResidenza) {
		this.anzComuneResidenza = anzComuneResidenza;
	}
	public String getAnzCivicoResidenza() {
		return anzCivicoResidenza;
	}
	public void setAnzCivicoResidenza(String anzCivicoResidenza) {
		this.anzCivicoResidenza = anzCivicoResidenza;
	}
	public String getAnzIndirizzoResidenza() {
		return anzIndirizzoResidenza;
	}
	public void setAnzIndirizzoResidenza(String anzIndirizzoResidenza) {
		this.anzIndirizzoResidenza = anzIndirizzoResidenza;
	}
	public String getAnzTelefono() {
		return anzTelefono;
	}
	public void setAnzTelefono(String anzTelefono) {
		this.anzTelefono = anzTelefono;
	}
	public String getTipologia() {
		return tipologia;
	}
	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}
	public String getDenominazione() {
		return denominazione;
	}
	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}
	public String getUbicazione() {
		return ubicazione;
	}
	public void setUbicazione(String ubicazione) {
		this.ubicazione = ubicazione;
	}
	public String getaUnoNome() {
		return aUnoNome;
	}
	public void setaUnoNome(String aUnoNome) {
		this.aUnoNome = aUnoNome;
	}
	public String getaUnoCognome() {
		return aUnoCognome;
	}
	public void setaUnoCognome(String aUnoCognome) {
		this.aUnoCognome = aUnoCognome;
	}
	public String getaUnoComuneNascita() {
		return aUnoComuneNascita;
	}
	public void setaUnoComuneNascita(String aUnoComuneNascita) {
		this.aUnoComuneNascita = aUnoComuneNascita;
	}
	public String getaUnoProvinciaNascita() {
		return aUnoProvinciaNascita;
	}
	public void setaUnoProvinciaNascita(String aUnoProvinciaNascita) {
		this.aUnoProvinciaNascita = aUnoProvinciaNascita;
	}
	public String getaUnoDataNascita() {
		return aUnoDataNascita;
	}
	public void setaUnoDataNascita(String aUnoDataNascita) {
		this.aUnoDataNascita = aUnoDataNascita;
	} 
	public boolean isAlimenti() {
		return alimenti;
	}
	public void setAlimenti(boolean alimenti) {
		this.alimenti = alimenti;
	}
	public boolean isAppartamentoProprio() {
		return appartamentoProprio;
	}
	public void setAppartamentoProprio(boolean appartamentoProprio) {
		this.appartamentoProprio = appartamentoProprio;
	}
	public String getCanoneAppartamento() {
		return canoneAppartamento;
	}
	public void setCanoneAppartamento(String canoneAppartamento) {
		this.canoneAppartamento = canoneAppartamento;
	}
//	public String getaDueNome() {
//		return aDueNome;
//	}
//	public void setaDueNome(String aDueNome) {
//		this.aDueNome = aDueNome;
//	}
//	public String getaDueCognome() {
//		return aDueCognome;
//	}
//	public void setaDueCognome(String aDueCognome) {
//		this.aDueCognome = aDueCognome;
//	}
//	public String getaDueComuneNascita() {
//		return aDueComuneNascita;
//	}
//	public void setaDueComuneNascita(String aDueComuneNascita) {
//		this.aDueComuneNascita = aDueComuneNascita;
//	}
//	public String getaDueProvinciaNascita() {
//		return aDueProvinciaNascita;
//	}
//	public void setaDueProvinciaNascita(String aDueProvinciaNascita) {
//		this.aDueProvinciaNascita = aDueProvinciaNascita;
//	}
//	public String getaDueDataNascita() {
//		return aDueDataNascita;
//	}
//	public void setaDueDataNascita(String aDueDataNascita) {
//		this.aDueDataNascita = aDueDataNascita;
//	}
//	public String getaDueRuolo() {
//		return aDueRuolo;
//	}
//	public void setaDueRuolo(String aDueRuolo) {
//		this.aDueRuolo = aDueRuolo;
//	}
//	public String getReddito() {
//		return reddito;
//	}
//	public void setReddito(String reddito) {
//		this.reddito = reddito;
//	}
//	public String getAltro() {
//		return altro;
//	}
//	public void setAltro(String altro) {
//		this.altro = altro;
//	}
	public String getAffinita() {
		return affinita;
	}
	public void setAffinita(String affinita) {
		this.affinita = affinita;
	}
	public String getAnzProvinciaResidenza() {
		return anzProvinciaResidenza;
	}
	public void setAnzProvinciaResidenza(String anzProvinciaResidenza) {
		this.anzProvinciaResidenza = anzProvinciaResidenza;
	}
	public List<Parente> getParenti() {
		return parenti;
	}
	public void setParenti(List<Parente> parenti) {
		this.parenti = parenti;
	}
	public Parente getRichiedente() {
		return richiedente;
	}
	public void setRichiedente(Parente richiedente) {
		this.richiedente = richiedente;
	}
	public String getServiziComune() {
		return serviziComune;
	}
	public String getServiziEntiPubblici() {
		return serviziEntiPubblici;
	}
	public void setServiziComune(String serviziComune) {
		this.serviziComune = serviziComune;
	}
	public void setServiziEntiPubblici(String serviziEntiPubblici) {
		this.serviziEntiPubblici = serviziEntiPubblici;
	}
	public boolean isAutosufficiente() {
		return autosufficiente;
	}
	public void setAutosufficiente(boolean autosufficiente) {
		this.autosufficiente = autosufficiente;
	}
	public boolean isParzialmenteAutosuf() {
		return parzialmenteAutosuf;
	}
	public void setParzialmenteAutosuf(boolean parzialmenteAutosuf) {
		this.parzialmenteAutosuf = parzialmenteAutosuf;
	}
	public boolean isServiziSimiliEnte() {
		return serviziSimiliEnte;
	}
	public void setServiziSimiliEnte(boolean serviziSimiliEnte) {
		this.serviziSimiliEnte = serviziSimiliEnte;
	}
	public boolean isServiziSimiliComune() {
		return serviziSimiliComune;
	}
	public void setServiziSimiliComune(boolean serviziSimiliComune) {
		this.serviziSimiliComune = serviziSimiliComune;
	}
	public boolean isPatolTemporanea() {
		return patolTemporanea;
	}
	public void setPatolTemporanea(boolean patolTemporanea) {
		this.patolTemporanea = patolTemporanea;
	}
}
