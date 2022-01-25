//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:36:47 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per visuraAnagrafica complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="visuraAnagrafica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataNascita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="sesso" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="toponimoIndirizzo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneVia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroCivico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="esponente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="piano" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="scala" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cognomeNomeConiuge" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="statoCivile" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="relazioneParentela" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cittadinanzaItaliana" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="codiceIstatComuneNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneComuneEsteroNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="statoEsteroNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroAttoNascita" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoAttoNascita" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ufficioNascita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroAttoNascitaTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteNascitaTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieNascitaTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoNascitaTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceIstatComuneNascitaTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroAttoMatrimonio" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoMatrimonio" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="ufficioMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceIstatComuneMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="luogoMatrimonio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataMatrimonio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroAttoMatrimonioTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteMatrimonioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieMatrimonioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoMatrimonioTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceIstatComuneMatrimonioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataDecorrenzaDivorzio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataSentenzaDivorzio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroSentenzaDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceIstatComuneTribunaleDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataAttoDivorzio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroAttoDivorzio" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="volumeDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ufficioDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataAttoDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroAttoDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="volumeDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ufficioDivorzioTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceIstatComuneTrascrizioneDivorzio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataAttoVedovanza" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroAttoVedovanza" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteVedovanza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieVedovanza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="volumeVedovanza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ufficioVedovanza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceIstatComuneMorte" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataMorte" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroAttoMorte" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteMorte" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieMorte" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ufficioMorte" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoMorte" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceIstatComuneMorteConiugeTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroAttoMorteConiugeTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="parteMorteConiugeTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="serieMorteConiugeTrascritto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoMorteConiugeTrascritto" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="professione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="titoloStudio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroCartaIdentita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataCartaIdentita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="validaCartaIdentita" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="identificativoNucleoFamiliare" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visuraAnagrafica", propOrder = {
    "id",
    "codiceFiscale",
    "cognome",
    "nome",
    "dataNascita",
    "sesso",
    "cap",
    "toponimoIndirizzo",
    "descrizioneVia",
    "numeroCivico",
    "esponente",
    "piano",
    "scala",
    "interno",
    "cognomeNomeConiuge",
    "statoCivile",
    "relazioneParentela",
    "cittadinanzaItaliana",
    "codiceIstatComuneNascita",
    "descrizioneComuneEsteroNascita",
    "statoEsteroNascita",
    "numeroAttoNascita",
    "parteNascita",
    "serieNascita",
    "annoAttoNascita",
    "ufficioNascita",
    "numeroAttoNascitaTrascritto",
    "parteNascitaTrascritto",
    "serieNascitaTrascritto",
    "annoNascitaTrascritto",
    "codiceIstatComuneNascitaTrascritto",
    "numeroAttoMatrimonio",
    "parteMatrimonio",
    "serieMatrimonio",
    "annoMatrimonio",
    "ufficioMatrimonio",
    "codiceIstatComuneMatrimonio",
    "luogoMatrimonio",
    "dataMatrimonio",
    "numeroAttoMatrimonioTrascritto",
    "parteMatrimonioTrascritto",
    "serieMatrimonioTrascritto",
    "annoMatrimonioTrascritto",
    "codiceIstatComuneMatrimonioTrascritto",
    "dataDecorrenzaDivorzio",
    "dataSentenzaDivorzio",
    "numeroSentenzaDivorzio",
    "codiceIstatComuneTribunaleDivorzio",
    "tipoDivorzio",
    "dataAttoDivorzio",
    "numeroAttoDivorzio",
    "parteDivorzio",
    "serieDivorzio",
    "volumeDivorzio",
    "ufficioDivorzio",
    "dataAttoDivorzioTrascritto",
    "numeroAttoDivorzioTrascritto",
    "parteDivorzioTrascritto",
    "serieDivorzioTrascritto",
    "volumeDivorzioTrascritto",
    "ufficioDivorzioTrascritto",
    "codiceIstatComuneTrascrizioneDivorzio",
    "dataAttoVedovanza",
    "numeroAttoVedovanza",
    "parteVedovanza",
    "serieVedovanza",
    "volumeVedovanza",
    "ufficioVedovanza",
    "codiceIstatComuneMorte",
    "dataMorte",
    "numeroAttoMorte",
    "parteMorte",
    "serieMorte",
    "ufficioMorte",
    "annoMorte",
    "codiceIstatComuneMorteConiugeTrascritto",
    "numeroAttoMorteConiugeTrascritto",
    "parteMorteConiugeTrascritto",
    "serieMorteConiugeTrascritto",
    "annoMorteConiugeTrascritto",
    "professione",
    "titoloStudio",
    "numeroCartaIdentita",
    "dataCartaIdentita",
    "validaCartaIdentita",
    "identificativoNucleoFamiliare"
})
public class VisuraAnagrafica {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String codiceFiscale;
    @XmlElement(required = true, nillable = true)
    protected String cognome;
    @XmlElement(required = true, nillable = true)
    protected String nome;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascita;
    @XmlElement(required = true, nillable = true)
    protected String sesso;
    @XmlElement(required = true, nillable = true)
    protected String cap;
    @XmlElement(required = true, nillable = true)
    protected String toponimoIndirizzo;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneVia;
    @XmlElement(required = true, nillable = true)
    protected String numeroCivico;
    @XmlElement(required = true, nillable = true)
    protected String esponente;
    @XmlElement(required = true, nillable = true)
    protected String piano;
    @XmlElement(required = true, nillable = true)
    protected String scala;
    @XmlElement(required = true, nillable = true)
    protected String interno;
    @XmlElement(required = true, nillable = true)
    protected String cognomeNomeConiuge;
    @XmlElement(required = true, nillable = true)
    protected String statoCivile;
    @XmlElement(required = true, nillable = true)
    protected String relazioneParentela;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean cittadinanzaItaliana;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneNascita;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneComuneEsteroNascita;
    @XmlElement(required = true, nillable = true)
    protected String statoEsteroNascita;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoNascita;
    @XmlElement(required = true, nillable = true)
    protected String parteNascita;
    @XmlElement(required = true, nillable = true)
    protected String serieNascita;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoAttoNascita;
    @XmlElement(required = true, nillable = true)
    protected String ufficioNascita;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoNascitaTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String parteNascitaTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String serieNascitaTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoNascitaTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneNascitaTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected String parteMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected String serieMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected String ufficioMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected String luogoMatrimonio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataMatrimonio;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoMatrimonioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String parteMatrimonioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String serieMatrimonioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoMatrimonioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneMatrimonioTrascritto;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataDecorrenzaDivorzio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataSentenzaDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String numeroSentenzaDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneTribunaleDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String tipoDivorzio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataAttoDivorzio;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String parteDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String serieDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String volumeDivorzio;
    @XmlElement(required = true, nillable = true)
    protected String ufficioDivorzio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataAttoDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String parteDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String serieDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String volumeDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String ufficioDivorzioTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneTrascrizioneDivorzio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataAttoVedovanza;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoVedovanza;
    @XmlElement(required = true, nillable = true)
    protected String parteVedovanza;
    @XmlElement(required = true, nillable = true)
    protected String serieVedovanza;
    @XmlElement(required = true, nillable = true)
    protected String volumeVedovanza;
    @XmlElement(required = true, nillable = true)
    protected String ufficioVedovanza;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneMorte;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataMorte;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoMorte;
    @XmlElement(required = true, nillable = true)
    protected String parteMorte;
    @XmlElement(required = true, nillable = true)
    protected String serieMorte;
    @XmlElement(required = true, nillable = true)
    protected String ufficioMorte;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoMorte;
    @XmlElement(required = true, nillable = true)
    protected String codiceIstatComuneMorteConiugeTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroAttoMorteConiugeTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String parteMorteConiugeTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String serieMorteConiugeTrascritto;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoMorteConiugeTrascritto;
    @XmlElement(required = true, nillable = true)
    protected String professione;
    @XmlElement(required = true, nillable = true)
    protected String titoloStudio;
    @XmlElement(required = true, nillable = true)
    protected String numeroCartaIdentita;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataCartaIdentita;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean validaCartaIdentita;
    @XmlElement(required = true, nillable = true)
    protected BigInteger identificativoNucleoFamiliare;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà codiceFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceFiscale() {
        return codiceFiscale;
    }

    /**
     * Imposta il valore della proprietà codiceFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceFiscale(String value) {
        this.codiceFiscale = value;
    }

    /**
     * Recupera il valore della proprietà cognome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognome() {
        return cognome;
    }

    /**
     * Imposta il valore della proprietà cognome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognome(String value) {
        this.cognome = value;
    }

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà dataNascita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataNascita() {
        return dataNascita;
    }

    /**
     * Imposta il valore della proprietà dataNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataNascita(XMLGregorianCalendar value) {
        this.dataNascita = value;
    }

    /**
     * Recupera il valore della proprietà sesso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSesso() {
        return sesso;
    }

    /**
     * Imposta il valore della proprietà sesso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSesso(String value) {
        this.sesso = value;
    }

    /**
     * Recupera il valore della proprietà cap.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta il valore della proprietà cap.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Recupera il valore della proprietà toponimoIndirizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getToponimoIndirizzo() {
        return toponimoIndirizzo;
    }

    /**
     * Imposta il valore della proprietà toponimoIndirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setToponimoIndirizzo(String value) {
        this.toponimoIndirizzo = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneVia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneVia() {
        return descrizioneVia;
    }

    /**
     * Imposta il valore della proprietà descrizioneVia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneVia(String value) {
        this.descrizioneVia = value;
    }

    /**
     * Recupera il valore della proprietà numeroCivico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCivico() {
        return numeroCivico;
    }

    /**
     * Imposta il valore della proprietà numeroCivico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCivico(String value) {
        this.numeroCivico = value;
    }

    /**
     * Recupera il valore della proprietà esponente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsponente() {
        return esponente;
    }

    /**
     * Imposta il valore della proprietà esponente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsponente(String value) {
        this.esponente = value;
    }

    /**
     * Recupera il valore della proprietà piano.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPiano() {
        return piano;
    }

    /**
     * Imposta il valore della proprietà piano.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPiano(String value) {
        this.piano = value;
    }

    /**
     * Recupera il valore della proprietà scala.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScala() {
        return scala;
    }

    /**
     * Imposta il valore della proprietà scala.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScala(String value) {
        this.scala = value;
    }

    /**
     * Recupera il valore della proprietà interno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterno() {
        return interno;
    }

    /**
     * Imposta il valore della proprietà interno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterno(String value) {
        this.interno = value;
    }

    /**
     * Recupera il valore della proprietà cognomeNomeConiuge.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCognomeNomeConiuge() {
        return cognomeNomeConiuge;
    }

    /**
     * Imposta il valore della proprietà cognomeNomeConiuge.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCognomeNomeConiuge(String value) {
        this.cognomeNomeConiuge = value;
    }

    /**
     * Recupera il valore della proprietà statoCivile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoCivile() {
        return statoCivile;
    }

    /**
     * Imposta il valore della proprietà statoCivile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoCivile(String value) {
        this.statoCivile = value;
    }

    /**
     * Recupera il valore della proprietà relazioneParentela.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelazioneParentela() {
        return relazioneParentela;
    }

    /**
     * Imposta il valore della proprietà relazioneParentela.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelazioneParentela(String value) {
        this.relazioneParentela = value;
    }

    /**
     * Recupera il valore della proprietà cittadinanzaItaliana.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCittadinanzaItaliana() {
        return cittadinanzaItaliana;
    }

    /**
     * Imposta il valore della proprietà cittadinanzaItaliana.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCittadinanzaItaliana(Boolean value) {
        this.cittadinanzaItaliana = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneNascita() {
        return codiceIstatComuneNascita;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneNascita(String value) {
        this.codiceIstatComuneNascita = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneComuneEsteroNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneComuneEsteroNascita() {
        return descrizioneComuneEsteroNascita;
    }

    /**
     * Imposta il valore della proprietà descrizioneComuneEsteroNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneComuneEsteroNascita(String value) {
        this.descrizioneComuneEsteroNascita = value;
    }

    /**
     * Recupera il valore della proprietà statoEsteroNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoEsteroNascita() {
        return statoEsteroNascita;
    }

    /**
     * Imposta il valore della proprietà statoEsteroNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoEsteroNascita(String value) {
        this.statoEsteroNascita = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoNascita.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoNascita() {
        return numeroAttoNascita;
    }

    /**
     * Imposta il valore della proprietà numeroAttoNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoNascita(BigInteger value) {
        this.numeroAttoNascita = value;
    }

    /**
     * Recupera il valore della proprietà parteNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteNascita() {
        return parteNascita;
    }

    /**
     * Imposta il valore della proprietà parteNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteNascita(String value) {
        this.parteNascita = value;
    }

    /**
     * Recupera il valore della proprietà serieNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieNascita() {
        return serieNascita;
    }

    /**
     * Imposta il valore della proprietà serieNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieNascita(String value) {
        this.serieNascita = value;
    }

    /**
     * Recupera il valore della proprietà annoAttoNascita.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoAttoNascita() {
        return annoAttoNascita;
    }

    /**
     * Imposta il valore della proprietà annoAttoNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoAttoNascita(BigInteger value) {
        this.annoAttoNascita = value;
    }

    /**
     * Recupera il valore della proprietà ufficioNascita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioNascita() {
        return ufficioNascita;
    }

    /**
     * Imposta il valore della proprietà ufficioNascita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioNascita(String value) {
        this.ufficioNascita = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoNascitaTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoNascitaTrascritto() {
        return numeroAttoNascitaTrascritto;
    }

    /**
     * Imposta il valore della proprietà numeroAttoNascitaTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoNascitaTrascritto(BigInteger value) {
        this.numeroAttoNascitaTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà parteNascitaTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteNascitaTrascritto() {
        return parteNascitaTrascritto;
    }

    /**
     * Imposta il valore della proprietà parteNascitaTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteNascitaTrascritto(String value) {
        this.parteNascitaTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà serieNascitaTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieNascitaTrascritto() {
        return serieNascitaTrascritto;
    }

    /**
     * Imposta il valore della proprietà serieNascitaTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieNascitaTrascritto(String value) {
        this.serieNascitaTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà annoNascitaTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoNascitaTrascritto() {
        return annoNascitaTrascritto;
    }

    /**
     * Imposta il valore della proprietà annoNascitaTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoNascitaTrascritto(BigInteger value) {
        this.annoNascitaTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneNascitaTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneNascitaTrascritto() {
        return codiceIstatComuneNascitaTrascritto;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneNascitaTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneNascitaTrascritto(String value) {
        this.codiceIstatComuneNascitaTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoMatrimonio() {
        return numeroAttoMatrimonio;
    }

    /**
     * Imposta il valore della proprietà numeroAttoMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoMatrimonio(BigInteger value) {
        this.numeroAttoMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà parteMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteMatrimonio() {
        return parteMatrimonio;
    }

    /**
     * Imposta il valore della proprietà parteMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteMatrimonio(String value) {
        this.parteMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà serieMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieMatrimonio() {
        return serieMatrimonio;
    }

    /**
     * Imposta il valore della proprietà serieMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieMatrimonio(String value) {
        this.serieMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà annoMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoMatrimonio() {
        return annoMatrimonio;
    }

    /**
     * Imposta il valore della proprietà annoMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoMatrimonio(BigInteger value) {
        this.annoMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà ufficioMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioMatrimonio() {
        return ufficioMatrimonio;
    }

    /**
     * Imposta il valore della proprietà ufficioMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioMatrimonio(String value) {
        this.ufficioMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneMatrimonio() {
        return codiceIstatComuneMatrimonio;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneMatrimonio(String value) {
        this.codiceIstatComuneMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà luogoMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLuogoMatrimonio() {
        return luogoMatrimonio;
    }

    /**
     * Imposta il valore della proprietà luogoMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLuogoMatrimonio(String value) {
        this.luogoMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà dataMatrimonio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMatrimonio() {
        return dataMatrimonio;
    }

    /**
     * Imposta il valore della proprietà dataMatrimonio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMatrimonio(XMLGregorianCalendar value) {
        this.dataMatrimonio = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoMatrimonioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoMatrimonioTrascritto() {
        return numeroAttoMatrimonioTrascritto;
    }

    /**
     * Imposta il valore della proprietà numeroAttoMatrimonioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoMatrimonioTrascritto(BigInteger value) {
        this.numeroAttoMatrimonioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà parteMatrimonioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteMatrimonioTrascritto() {
        return parteMatrimonioTrascritto;
    }

    /**
     * Imposta il valore della proprietà parteMatrimonioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteMatrimonioTrascritto(String value) {
        this.parteMatrimonioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà serieMatrimonioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieMatrimonioTrascritto() {
        return serieMatrimonioTrascritto;
    }

    /**
     * Imposta il valore della proprietà serieMatrimonioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieMatrimonioTrascritto(String value) {
        this.serieMatrimonioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà annoMatrimonioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoMatrimonioTrascritto() {
        return annoMatrimonioTrascritto;
    }

    /**
     * Imposta il valore della proprietà annoMatrimonioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoMatrimonioTrascritto(BigInteger value) {
        this.annoMatrimonioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneMatrimonioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneMatrimonioTrascritto() {
        return codiceIstatComuneMatrimonioTrascritto;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneMatrimonioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneMatrimonioTrascritto(String value) {
        this.codiceIstatComuneMatrimonioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà dataDecorrenzaDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataDecorrenzaDivorzio() {
        return dataDecorrenzaDivorzio;
    }

    /**
     * Imposta il valore della proprietà dataDecorrenzaDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataDecorrenzaDivorzio(XMLGregorianCalendar value) {
        this.dataDecorrenzaDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà dataSentenzaDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataSentenzaDivorzio() {
        return dataSentenzaDivorzio;
    }

    /**
     * Imposta il valore della proprietà dataSentenzaDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataSentenzaDivorzio(XMLGregorianCalendar value) {
        this.dataSentenzaDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà numeroSentenzaDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroSentenzaDivorzio() {
        return numeroSentenzaDivorzio;
    }

    /**
     * Imposta il valore della proprietà numeroSentenzaDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroSentenzaDivorzio(String value) {
        this.numeroSentenzaDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneTribunaleDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneTribunaleDivorzio() {
        return codiceIstatComuneTribunaleDivorzio;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneTribunaleDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneTribunaleDivorzio(String value) {
        this.codiceIstatComuneTribunaleDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà tipoDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoDivorzio() {
        return tipoDivorzio;
    }

    /**
     * Imposta il valore della proprietà tipoDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoDivorzio(String value) {
        this.tipoDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà dataAttoDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAttoDivorzio() {
        return dataAttoDivorzio;
    }

    /**
     * Imposta il valore della proprietà dataAttoDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAttoDivorzio(XMLGregorianCalendar value) {
        this.dataAttoDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoDivorzio() {
        return numeroAttoDivorzio;
    }

    /**
     * Imposta il valore della proprietà numeroAttoDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoDivorzio(BigInteger value) {
        this.numeroAttoDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà parteDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteDivorzio() {
        return parteDivorzio;
    }

    /**
     * Imposta il valore della proprietà parteDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteDivorzio(String value) {
        this.parteDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà serieDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieDivorzio() {
        return serieDivorzio;
    }

    /**
     * Imposta il valore della proprietà serieDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieDivorzio(String value) {
        this.serieDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà volumeDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeDivorzio() {
        return volumeDivorzio;
    }

    /**
     * Imposta il valore della proprietà volumeDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeDivorzio(String value) {
        this.volumeDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà ufficioDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioDivorzio() {
        return ufficioDivorzio;
    }

    /**
     * Imposta il valore della proprietà ufficioDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioDivorzio(String value) {
        this.ufficioDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà dataAttoDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAttoDivorzioTrascritto() {
        return dataAttoDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà dataAttoDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAttoDivorzioTrascritto(XMLGregorianCalendar value) {
        this.dataAttoDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoDivorzioTrascritto() {
        return numeroAttoDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà numeroAttoDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoDivorzioTrascritto(BigInteger value) {
        this.numeroAttoDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà parteDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteDivorzioTrascritto() {
        return parteDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà parteDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteDivorzioTrascritto(String value) {
        this.parteDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà serieDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieDivorzioTrascritto() {
        return serieDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà serieDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieDivorzioTrascritto(String value) {
        this.serieDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà volumeDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeDivorzioTrascritto() {
        return volumeDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà volumeDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeDivorzioTrascritto(String value) {
        this.volumeDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà ufficioDivorzioTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioDivorzioTrascritto() {
        return ufficioDivorzioTrascritto;
    }

    /**
     * Imposta il valore della proprietà ufficioDivorzioTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioDivorzioTrascritto(String value) {
        this.ufficioDivorzioTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneTrascrizioneDivorzio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneTrascrizioneDivorzio() {
        return codiceIstatComuneTrascrizioneDivorzio;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneTrascrizioneDivorzio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneTrascrizioneDivorzio(String value) {
        this.codiceIstatComuneTrascrizioneDivorzio = value;
    }

    /**
     * Recupera il valore della proprietà dataAttoVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAttoVedovanza() {
        return dataAttoVedovanza;
    }

    /**
     * Imposta il valore della proprietà dataAttoVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAttoVedovanza(XMLGregorianCalendar value) {
        this.dataAttoVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoVedovanza() {
        return numeroAttoVedovanza;
    }

    /**
     * Imposta il valore della proprietà numeroAttoVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoVedovanza(BigInteger value) {
        this.numeroAttoVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà parteVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteVedovanza() {
        return parteVedovanza;
    }

    /**
     * Imposta il valore della proprietà parteVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteVedovanza(String value) {
        this.parteVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà serieVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieVedovanza() {
        return serieVedovanza;
    }

    /**
     * Imposta il valore della proprietà serieVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieVedovanza(String value) {
        this.serieVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà volumeVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVolumeVedovanza() {
        return volumeVedovanza;
    }

    /**
     * Imposta il valore della proprietà volumeVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVolumeVedovanza(String value) {
        this.volumeVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà ufficioVedovanza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioVedovanza() {
        return ufficioVedovanza;
    }

    /**
     * Imposta il valore della proprietà ufficioVedovanza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioVedovanza(String value) {
        this.ufficioVedovanza = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneMorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneMorte() {
        return codiceIstatComuneMorte;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneMorte(String value) {
        this.codiceIstatComuneMorte = value;
    }

    /**
     * Recupera il valore della proprietà dataMorte.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataMorte() {
        return dataMorte;
    }

    /**
     * Imposta il valore della proprietà dataMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataMorte(XMLGregorianCalendar value) {
        this.dataMorte = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoMorte.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoMorte() {
        return numeroAttoMorte;
    }

    /**
     * Imposta il valore della proprietà numeroAttoMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoMorte(BigInteger value) {
        this.numeroAttoMorte = value;
    }

    /**
     * Recupera il valore della proprietà parteMorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteMorte() {
        return parteMorte;
    }

    /**
     * Imposta il valore della proprietà parteMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteMorte(String value) {
        this.parteMorte = value;
    }

    /**
     * Recupera il valore della proprietà serieMorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieMorte() {
        return serieMorte;
    }

    /**
     * Imposta il valore della proprietà serieMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieMorte(String value) {
        this.serieMorte = value;
    }

    /**
     * Recupera il valore della proprietà ufficioMorte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUfficioMorte() {
        return ufficioMorte;
    }

    /**
     * Imposta il valore della proprietà ufficioMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUfficioMorte(String value) {
        this.ufficioMorte = value;
    }

    /**
     * Recupera il valore della proprietà annoMorte.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoMorte() {
        return annoMorte;
    }

    /**
     * Imposta il valore della proprietà annoMorte.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoMorte(BigInteger value) {
        this.annoMorte = value;
    }

    /**
     * Recupera il valore della proprietà codiceIstatComuneMorteConiugeTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceIstatComuneMorteConiugeTrascritto() {
        return codiceIstatComuneMorteConiugeTrascritto;
    }

    /**
     * Imposta il valore della proprietà codiceIstatComuneMorteConiugeTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceIstatComuneMorteConiugeTrascritto(String value) {
        this.codiceIstatComuneMorteConiugeTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà numeroAttoMorteConiugeTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroAttoMorteConiugeTrascritto() {
        return numeroAttoMorteConiugeTrascritto;
    }

    /**
     * Imposta il valore della proprietà numeroAttoMorteConiugeTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroAttoMorteConiugeTrascritto(BigInteger value) {
        this.numeroAttoMorteConiugeTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà parteMorteConiugeTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParteMorteConiugeTrascritto() {
        return parteMorteConiugeTrascritto;
    }

    /**
     * Imposta il valore della proprietà parteMorteConiugeTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParteMorteConiugeTrascritto(String value) {
        this.parteMorteConiugeTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà serieMorteConiugeTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerieMorteConiugeTrascritto() {
        return serieMorteConiugeTrascritto;
    }

    /**
     * Imposta il valore della proprietà serieMorteConiugeTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerieMorteConiugeTrascritto(String value) {
        this.serieMorteConiugeTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà annoMorteConiugeTrascritto.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoMorteConiugeTrascritto() {
        return annoMorteConiugeTrascritto;
    }

    /**
     * Imposta il valore della proprietà annoMorteConiugeTrascritto.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoMorteConiugeTrascritto(BigInteger value) {
        this.annoMorteConiugeTrascritto = value;
    }

    /**
     * Recupera il valore della proprietà professione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProfessione() {
        return professione;
    }

    /**
     * Imposta il valore della proprietà professione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProfessione(String value) {
        this.professione = value;
    }

    /**
     * Recupera il valore della proprietà titoloStudio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitoloStudio() {
        return titoloStudio;
    }

    /**
     * Imposta il valore della proprietà titoloStudio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitoloStudio(String value) {
        this.titoloStudio = value;
    }

    /**
     * Recupera il valore della proprietà numeroCartaIdentita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroCartaIdentita() {
        return numeroCartaIdentita;
    }

    /**
     * Imposta il valore della proprietà numeroCartaIdentita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroCartaIdentita(String value) {
        this.numeroCartaIdentita = value;
    }

    /**
     * Recupera il valore della proprietà dataCartaIdentita.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataCartaIdentita() {
        return dataCartaIdentita;
    }

    /**
     * Imposta il valore della proprietà dataCartaIdentita.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataCartaIdentita(XMLGregorianCalendar value) {
        this.dataCartaIdentita = value;
    }

    /**
     * Recupera il valore della proprietà validaCartaIdentita.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isValidaCartaIdentita() {
        return validaCartaIdentita;
    }

    /**
     * Imposta il valore della proprietà validaCartaIdentita.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setValidaCartaIdentita(Boolean value) {
        this.validaCartaIdentita = value;
    }

    /**
     * Recupera il valore della proprietà identificativoNucleoFamiliare.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdentificativoNucleoFamiliare() {
        return identificativoNucleoFamiliare;
    }

    /**
     * Imposta il valore della proprietà identificativoNucleoFamiliare.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdentificativoNucleoFamiliare(BigInteger value) {
        this.identificativoNucleoFamiliare = value;
    }

}
