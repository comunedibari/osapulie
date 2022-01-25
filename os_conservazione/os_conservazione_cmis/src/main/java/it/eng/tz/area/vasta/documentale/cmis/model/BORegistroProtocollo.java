package it.eng.tz.area.vasta.documentale.cmis.model;

import java.util.Date;

public class BORegistroProtocollo {
	/*DB*/
	private int idRegistroProtocollo;
	private String repositoryPointer;
	private Date dataCreazione;
	private Boolean flagGenerato;
	private String fileName;
	private String pathFileSystem;
	private String denominazioneDestinatario;
	private String pivaDestinatario;
	private String checksum;
	private String codiceAmm;
	private String codiceAoo;
	private String denominazioneSoggettoProduttore;
	private String nomeSoggettoProduttore2;
	private String cognomeSoggettoProduttore2;
	private String cfSoggettoProduttore2;
	private String nomeResponsabile;
	private String cognomeResponsabile;
	private String cfResponsabile;
	private String oggetto;
	private String codiceRegistro;
	private int progressivo;
	private String anno;
	private long numeroPrimaReg;
	private long numeroUltimaReg;
	private Date dataPrimaReg;
	private Date dataUltimaReg;
	
	
	/*REPO*/
	private String classeDocumentale;
	private String statoVersamento;
	private Boolean versamento;
	private String pdvId;
	private String docId;
	private String docFilename;
	private Date dataVersamento;
	private String denominazioneEnte;
	
	
	

	public int getIdRegistroProtocollo() {
		return idRegistroProtocollo;
	}
	public void setIdRegistroProtocollo(int idRegistroProtocollo) {
		this.idRegistroProtocollo = idRegistroProtocollo;
	}
	public String getRepositoryPointer() {
		return repositoryPointer;
	}
	public void setRepositoryPointer(String repositoryPointer) {
		this.repositoryPointer = repositoryPointer;
	}
	public Boolean getFlagGenerato() {
		return flagGenerato;
	}
	public void setFlagGenerato(Boolean flagGenerato) {
		this.flagGenerato = flagGenerato;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getClasseDocumentale() {
		return classeDocumentale;
	}
	public void setClasseDocumentale(String classeDocumentale) {
		this.classeDocumentale = classeDocumentale;
	}
	public String getStatoVersamento() {
		return statoVersamento;
	}
	public void setStatoVersamento(String statoVersamento) {
		this.statoVersamento = statoVersamento;
	}
	public Boolean getVersamento() {
		return versamento;
	}
	public void setVersamento(Boolean versamento) {
		this.versamento = versamento;
	}
	public String getPdvId() {
		return pdvId;
	}
	public void setPdvId(String pdvId) {
		this.pdvId = pdvId;
	}
	public String getDocId() {
		return docId;
	}
	public void setDocId(String docId) {
		this.docId = docId;
	}
	public String getDocFilename() {
		return docFilename;
	}
	public void setDocFilename(String docFilename) {
		this.docFilename = docFilename;
	}
	public Date getDataVersamento() {
		return dataVersamento;
	}
	public void setDataVersamento(Date dataVersamento) {
		this.dataVersamento = dataVersamento;
	}
	public String getPathFileSystem() {
		return pathFileSystem;
	}
	public void setPathFileSystem(String pathFileSystem) {
		this.pathFileSystem = pathFileSystem;
	}
	public Date getDataCreazione() {
		return dataCreazione;
	}
	public void setDataCreazione(Date dataCreazione) {
		this.dataCreazione = dataCreazione;
	}
	public String getDestinatario() {
		return denominazioneDestinatario+" "+pivaDestinatario;
	}
	public void setDestinatario(String destinatario) {
		// dal db piva e dest sono unite
		this.denominazioneDestinatario=destinatario;
	}
	public String getChecksum() {
		return checksum;
	}
	public void setChecksum(String checksum) {
		this.checksum = checksum;
	}
	public String getCodiceAmm() {
		return codiceAmm;
	}
	public void setCodiceAmm(String codiceAmm) {
		this.codiceAmm = codiceAmm;
	}
	public String getCodiceAoo() {
		return codiceAoo;
	}
	public void setCodiceAoo(String codiceAoo) {
		this.codiceAoo = codiceAoo;
	}
	public String getSoggettoProduttore2() {
		return nomeSoggettoProduttore2 +" "+ cognomeSoggettoProduttore2 +" "+ cfSoggettoProduttore2;
	}
	public void setSoggettoProduttore2(String soggettoProduttore2) {
		//Quando Leggo dal db, setto il nome 
		this.nomeSoggettoProduttore2 = soggettoProduttore2;
	}
	public String getResponsabile() {
		return nomeResponsabile + " "+ cognomeResponsabile+ " "+ cfResponsabile; 
	}
	public void setResponsabile(String responsabile) {
		this.nomeResponsabile = responsabile;
	}
	public String getOggetto() {
		return oggetto;
	}
	public void setOggetto(String oggetto) {
		this.oggetto = oggetto;
	}
	public int getProgressivo() {
		return progressivo;
	}
	public void setProgressivo(int progressivo) {
		this.progressivo = progressivo;
	}
	public String getAnno() {
		return anno;
	}
	public void setAnno(String anno) {
		this.anno = anno;
	}
	public String getCodiceRegistro() {
		return codiceRegistro;
	}
	public void setCodiceRegistro(String codiceRegistro) {
		this.codiceRegistro = codiceRegistro;
	}
	public long getNumeroPrimaReg() {
		return numeroPrimaReg;
	}
	public void setNumeroPrimaReg(long numeroPrimaReg) {
		this.numeroPrimaReg = numeroPrimaReg;
	}
	public long getNumeroUltimaReg() {
		return numeroUltimaReg;
	}
	public void setNumeroUltimaReg(long numeroUltimaReg) {
		this.numeroUltimaReg = numeroUltimaReg;
	}
	public Date getDataPrimaReg() {
		return dataPrimaReg;
	}
	public void setDataPrimaReg(Date dataPrimaReg) {
		this.dataPrimaReg = dataPrimaReg;
	}
	public Date getDataUltimaReg() {
		return dataUltimaReg;
	}
	public void setDataUltimaReg(Date dataUltimaReg) {
		this.dataUltimaReg = dataUltimaReg;
	}
	public String getDenominazioneDestinatario() {
		return denominazioneDestinatario;
	}
	public void setDenominazioneDestinatario(String denominazioneDestinatario) {
		this.denominazioneDestinatario = denominazioneDestinatario;
	}
	public String getPivaDestinatario() {
		return pivaDestinatario;
	}
	public void setPivaDestinatario(String pivaDestinatario) {
		this.pivaDestinatario = pivaDestinatario;
	}
	public String getNomeSoggettoProduttore2() {
		return nomeSoggettoProduttore2;
	}
	public void setNomeSoggettoProduttore2(String nomeSoggettoProduttore2) {
		this.nomeSoggettoProduttore2 = nomeSoggettoProduttore2;
	}
	public String getCognomeSoggettoProduttore2() {
		return cognomeSoggettoProduttore2;
	}
	public void setCognomeSoggettoProduttore2(String cognomeSoggettoProduttore2) {
		this.cognomeSoggettoProduttore2 = cognomeSoggettoProduttore2;
	}
	public String getCfSoggettoProduttore2() {
		return cfSoggettoProduttore2;
	}
	public void setCfSoggettoProduttore2(String cfSoggettoProduttore2) {
		this.cfSoggettoProduttore2 = cfSoggettoProduttore2;
	}
	public String getNomeResponsabile() {
		return nomeResponsabile;
	}
	public void setNomeResponsabile(String nomeResponsabile) {
		this.nomeResponsabile = nomeResponsabile;
	}
	public String getCognomeResponsabile() {
		return cognomeResponsabile;
	}
	public void setCognomeResponsabile(String cognomeResponsabile) {
		this.cognomeResponsabile = cognomeResponsabile;
	}
	public String getCfResponsabile() {
		return cfResponsabile;
	}
	public void setCfResponsabile(String cfResponsabile) {
		this.cfResponsabile = cfResponsabile;
	}
	public String getDenominazioneEnte() {
		return denominazioneEnte;
	}
	public void setDenominazioneEnte(String denominazioneEnte) {
		this.denominazioneEnte = denominazioneEnte;
	}
	public String getDenominazioneSoggettoProduttore() {
		return denominazioneSoggettoProduttore;
	}
	public void setDenominazioneSoggettoProduttore(
			String denominazioneSoggettoProduttore) {
		this.denominazioneSoggettoProduttore = denominazioneSoggettoProduttore;
	}
	
}
