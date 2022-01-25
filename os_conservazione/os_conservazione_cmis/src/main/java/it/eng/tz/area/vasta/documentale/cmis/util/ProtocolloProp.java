package it.eng.tz.area.vasta.documentale.cmis.util;

import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProtocolloProp {
	private static final Logger log = LoggerFactory.getLogger(ProtocolloProp.class.getName());
	 
	public ProtocolloProp(QueryResult q) {
		super();
		this.objectId=getFilterProperty("cmis:objectId",q);
		this.docid = getFilterProperty("regProt:docid",q);
		this.dataCreazione=getFilterProperty("cmis:creationDate",q);
		this.dataPrimaRegistrazioneEffettuataSulRegistro = getFilterProperty("regProt:dataPrimaRegistrazioneEffettuataSulRegistro",q);
		this.soggettoproduttoreCognome =  getFilterProperty("regProt:soggettoproduttoreCognome",q);
		this.soggettoMittenteDenominazione =getFilterProperty("regProt:soggettoMittenteDenominazione",q);
		this.codiceIdentificativoAmministrazioneIpa = getFilterProperty("regProt:codiceIdentificativoAmministrazioneIpa",q);
		this.cfResponsabileGestioneDocumentale = getFilterProperty("regProt:cfResponsabileGestioneDocumentale",q);
		this.closingDate = getFilterProperty("regProt:closingDate",q);
		this.docId_cons = getFilterProperty("cons:docId",q);
		this.filename = getFilterProperty("regProt:filename",q)==null?getFilterProperty("cmis:name",q):getFilterProperty("regProt:filename",q);
		this.pdvId_cons = getFilterProperty("cons:pdvId",q);
		this.pdvidref = getFilterProperty("regProt:pdvidref",q);
		this.docFilename = getFilterProperty("cons:docFilename",q);
		this.soggettoMittenteCognome = getFilterProperty("regProt:soggettoMittenteCognome",q);
		this.pdvId = getFilterProperty("regProt:pdvid",q);
		this.soggettoMittentePartitaIva = getFilterProperty("regProt:soggettoMittentePartitaIva",q);
		this.statoVersamento = getFilterProperty("cons:statoVersamento",q);
		this.versato = getFilterProperty("cons:versato",q);
		this.amministrazioniPartecipanti = getFilterProperty("regProt:amministrazioniPartecipanti",q);
		this.destinatarioNome = getFilterProperty("regProt:destinatarioNome",q);
		this.soggettoMittenteCodiceFiscale = getFilterProperty("regProt:soggettoMittenteCodiceFiscale",q);
		this.soggettoproduttoreDenominazione = getFilterProperty("regProt:soggettoproduttoreDenominazione",q);
		this.codiceIdentificativoDelRegistro = getFilterProperty("regProt:codiceIdentificativoDelRegistro",q);
		this.hash = getFilterProperty("regProt:hash",q);
		this.mimetype = getFilterProperty("regProt:mimetype",q);
		this.destinatarioCognome = getFilterProperty("regProt:destinatarioCognome",q);
		this.destinatarioPartitaIva = getFilterProperty("regProt:destinatarioPartitaIva",q);
		this.dataPrimaRegistrazioneEffettuataSulProtocollo = getFilterProperty("regProt:dataPrimaRegistrazioneEffettuataSulProtocollo",q);
		this.soggettoproduttorePartitaIva = getFilterProperty("regProt:soggettoproduttorePartitaIva",q);
		this.amministrazioneTitolareDelProcedimento = getFilterProperty("regProt:amministrazioneTitolareDelProcedimento",q);
		this.numeroProgressivoDelRegistro = getFilterProperty("regProt:numeroProgressivoDelRegistro",q);
		this.soggettoproduttoreNome = getFilterProperty("regProt:soggettoproduttoreNome",q);
		this.numeroUltimaRegistrazioneEffettuataSulRegistro = getFilterProperty("regProt:numeroUltimaRegistrazioneEffettuataSulRegistro",q);
		this.destinatarioCodiceFiscale = getFilterProperty("regProt:destinatarioCodiceFiscale",q);
		this.cognomeSoggettoProduttore2 = 	getFilterProperty("regProt:cognomeSoggettoProduttore2",q);
		this.destinatarioDenominazione = getFilterProperty("regProt:destinatarioDenominazione",q);;
		this.aooDiRiferimento = getFilterProperty("regProt:aooDiRiferimento",q);;
		this.classeDocumentale = getFilterProperty("cons:classeDocumentale",q);
		this.soggettoMittenteNome = getFilterProperty("regProt:soggettoMittenteNome",q);
		this.numeroProtocollo = getFilterProperty("regProt:numeroProtocollo",q);
		this.documentiContenutiNelProcedimento = getFilterProperty("regProt:documentiContenutiNelProcedimento",q);
		this.responsabileProcedimento = getFilterProperty("regProt:responsabileProcedimento",q);
		this.dataRegistrazioneProtocollo = getFilterProperty("regProt:dataRegistrazioneProtocollo",q);
		this.oggettodocumento = getFilterProperty("regProt:oggettodocumento",q);
		this.soggettoproduttoreCodiceFiscale = 	getFilterProperty("regProt:soggettoproduttoreCodiceFiscale",q);
		this.denominazioneDellAmministrazione = getFilterProperty("regProt:denominazioneDellAmministrazione",q);
		this.codiceFiscaleSoggettoProduttore2 = getFilterProperty("regProt:codiceFiscaleSoggettoProduttore2",q);
		this.nomeResponsabileGestioneDocumentale = getFilterProperty("regProt:nomeResponsabileGestioneDocumentale",q);
		this.numeroPrimaRegistrazioneEffettuataSulRegistro = getFilterProperty("regProt:numeroPrimaRegistrazioneEffettuataSulRegistro",q);
		this.nomeSoggettoProduttore2 = getFilterProperty("regProt:nomeSoggettoProduttore2",q);
		this.oggettoProcedimento = getFilterProperty("regProt:oggettoProcedimento",q);
		this.docidref = getFilterProperty("regProt:docidref",q);
		this.cognomeResponsabileGestioneDocumentale = getFilterProperty("regProt:cognomeResponsabileGestioneDocumentale",q);
		this.dataUltimaRegistrazioneEffettuataSulRegistro = getFilterProperty("regProt:dataUltimaRegistrazioneEffettuataSulRegistro",q);
		
	}
	
	
	private Object getFilterProperty(String prop,QueryResult q) {
 
		if (q.getPropertyById(prop) != null) { 
			 return q.getPropertyById(prop).getFirstValue(); // Atom Pub binding
		} else if(q.getPropertyByQueryName("p."+prop)!=null){
			return q.getPropertyByQueryName("p."+prop).getFirstValue();// Web Services binding
		}else if(q.getPropertyByQueryName("c."+prop)!=null){
			return q.getPropertyByQueryName("c."+prop).getFirstValue();// Web Services binding
		}
		else {
			log.warn("ProtocolloProp:getFilterProperty ( "+prop+" ) = null ");
		}
		return (Object)"";
	}
	
	
	public Object getObjectId() {
		return objectId;
	}
	
	public Object getDocid() {
		return docid;
	}
	public Object getDataCreazione() {
		return dataCreazione;
	}
	public Object getDataPrimaRegistrazioneEffettuataSulRegistro() {
		return dataPrimaRegistrazioneEffettuataSulRegistro;
	}
	public Object getSoggettoproduttoreCognome() {
		return soggettoproduttoreCognome;
	}
	public Object getSoggettoMittenteDenominazione() {
		return soggettoMittenteDenominazione;
	}
	public Object getCodiceIdentificativoAmministrazioneIpa() {
		return codiceIdentificativoAmministrazioneIpa;
	}
	public Object getCfResponsabileGestioneDocumentale() {
		return cfResponsabileGestioneDocumentale;
	}
	public Object getClosingDate() {
		return closingDate;
	}
	public Object getDocId_cons() {
		return docId_cons;
	}
	public Object getFilename() {
		return filename;
	}
	public Object getPdvId() {
		return pdvId;
	}
	public Object getPdvidref() {
		return pdvidref;
	}
	public Object getDocFilename() {
		return docFilename;
	}
	public Object getSoggettoMittenteCognome() {
		return soggettoMittenteCognome;
	}
	public Object getPdvid_cons() {
		return pdvId_cons;
	}
	public Object getSoggettoMittentePartitaIva() {
		return soggettoMittentePartitaIva;
	}
	public Object getStatoVersamento() {
		return statoVersamento;
	}
	public Object getVersato() {
		return versato;
	}
	public Object getAmministrazioniPartecipanti() {
		return amministrazioniPartecipanti;
	}
	public Object getDestinatarioNome() {
		return destinatarioNome;
	}
	public Object getSoggettoMittenteCodiceFiscale() {
		return soggettoMittenteCodiceFiscale;
	}
	public Object getSoggettoproduttoreDenominazione() {
		return soggettoproduttoreDenominazione;
	}
	public Object getCodiceIdentificativoDelRegistro() {
		return codiceIdentificativoDelRegistro;
	}
	public Object getHash() {
		return hash;
	}
	public Object getMimetype() {
		return mimetype;
	}
	public Object getDestinatarioCognome() {
		return destinatarioCognome;
	}
	public Object getDestinatarioPartitaIva() {
		return destinatarioPartitaIva;
	}
	public Object getDataPrimaRegistrazioneEffettuataSulProtocollo() {
		return dataPrimaRegistrazioneEffettuataSulProtocollo;
	}
	public Object getSoggettoproduttorePartitaIva() {
		return soggettoproduttorePartitaIva;
	}
	public Object getAmministrazioneTitolareDelProcedimento() {
		return amministrazioneTitolareDelProcedimento;
	}
	public Object getNumeroProgressivoDelRegistro() {
		return numeroProgressivoDelRegistro;
	}
	public Object getSoggettoproduttoreNome() {
		return soggettoproduttoreNome;
	}
	public Object getNumeroUltimaRegistrazioneEffettuataSulRegistro() {
		return numeroUltimaRegistrazioneEffettuataSulRegistro;
	}
	public Object getDestinatarioCodiceFiscale() {
		return destinatarioCodiceFiscale;
	}
	public Object getCognomeSoggettoProduttore2() {
		return cognomeSoggettoProduttore2;
	}
	public Object getDestinatarioDenominazione() {
		return destinatarioDenominazione;
	}
	public Object getAooDiRiferimento() {
		return aooDiRiferimento;
	}
	public Object getClasseDocumentale() {
		return classeDocumentale;
	}
	public Object getSoggettoMittenteNome() {
		return soggettoMittenteNome;
	}
	public Object getNumeroProtocollo() {
		return numeroProtocollo;
	}
	public Object getDocumentiContenutiNelProcedimento() {
		return documentiContenutiNelProcedimento;
	}
	public Object getResponsabileProcedimento() {
		return responsabileProcedimento;
	}
	public Object getDataRegistrazioneProtocollo() {
		return dataRegistrazioneProtocollo;
	}
	public Object getOggettodocumento() {
		return oggettodocumento;
	}
	public Object getSoggettoproduttoreCodiceFiscale() {
		return soggettoproduttoreCodiceFiscale;
	}
	public Object getDenominazioneDellAmministrazione() {
		return denominazioneDellAmministrazione;
	}
	public Object getCodiceFiscaleSoggettoProduttore2() {
		return codiceFiscaleSoggettoProduttore2;
	}
	public Object getNomeResponsabileGestioneDocumentale() {
		return nomeResponsabileGestioneDocumentale;
	}
	public Object getNumeroPrimaRegistrazioneEffettuataSulRegistro() {
		return numeroPrimaRegistrazioneEffettuataSulRegistro;
	}
	public Object getNomeSoggettoProduttore2() {
		return nomeSoggettoProduttore2;
	}
	public Object getOggettoProcedimento() {
		return oggettoProcedimento;
	}
	public Object getDocidref() {
		return docidref;
	}
	public Object getCognomeResponsabileGestioneDocumentale() {
		return cognomeResponsabileGestioneDocumentale;
	}
	public Object getDataUltimaRegistrazioneEffettuataSulRegistro() {
		return dataUltimaRegistrazioneEffettuataSulRegistro;
	}
	 
	private Object objectId;
	private Object docid; 
	private Object dataCreazione; 
	private Object dataPrimaRegistrazioneEffettuataSulRegistro;
	private Object soggettoproduttoreCognome;
	private Object soggettoMittenteDenominazione;
	private Object codiceIdentificativoAmministrazioneIpa;
	private Object cfResponsabileGestioneDocumentale;
	private Object closingDate;
	private Object docId_cons; 
	private Object filename; 
	private Object pdvId;
	private Object pdvidref;
	private Object docFilename; 
	private Object soggettoMittenteCognome; 
	private Object pdvId_cons;
	private Object soggettoMittentePartitaIva;
	private Object statoVersamento; 
	private Object versato; 
	private Object amministrazioniPartecipanti;
	private Object destinatarioNome;
	private Object soggettoMittenteCodiceFiscale;
	private Object soggettoproduttoreDenominazione; 
	private Object codiceIdentificativoDelRegistro; 
	private Object hash; 
	private Object mimetype;
	private Object destinatarioCognome;
	private Object destinatarioPartitaIva;
	private Object dataPrimaRegistrazioneEffettuataSulProtocollo; 
	private Object soggettoproduttorePartitaIva;
	private Object amministrazioneTitolareDelProcedimento; 
	private Object numeroProgressivoDelRegistro; 
	private Object soggettoproduttoreNome;
	private Object numeroUltimaRegistrazioneEffettuataSulRegistro; 
	private Object destinatarioCodiceFiscale;
	private Object cognomeSoggettoProduttore2;
	private Object destinatarioDenominazione;
	private Object aooDiRiferimento;
	private Object classeDocumentale; 
	private Object soggettoMittenteNome;
	private Object numeroProtocollo;
	private Object documentiContenutiNelProcedimento;
	private Object responsabileProcedimento;
	private Object dataRegistrazioneProtocollo; 
	private Object oggettodocumento; 
	private Object soggettoproduttoreCodiceFiscale;
	private Object denominazioneDellAmministrazione;
	private Object codiceFiscaleSoggettoProduttore2;
	private Object nomeResponsabileGestioneDocumentale; 
	private Object numeroPrimaRegistrazioneEffettuataSulRegistro; 
	private Object nomeSoggettoProduttore2;
	private Object oggettoProcedimento; 
	private Object docidref;
	private Object cognomeResponsabileGestioneDocumentale;
	private Object dataUltimaRegistrazioneEffettuataSulRegistro; 
	
}
