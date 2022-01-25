//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:46:21 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraPosizioneElettoraleService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per visuraPosizioneElettorale complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="visuraPosizioneElettorale"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cognome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataNascita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="toponimoIndirizzo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneVia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroCivico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="piano" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="scala" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="esponente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataVerbaleIscrizione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numVerbaleIscrizione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="annoIscrizioneElett" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="numeroFascicolo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroListaGenerale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroListaSezionale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneLista" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoElettore" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroSezione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="numeroTesseraElettorale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataRilascioTesseraElett" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="iscrizioneAlboPresidente" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="iscrizioneAlboScrutatori" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="numVerbIscrAlboScrut" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dataVerbIscrAlboScrut" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visuraPosizioneElettorale", propOrder = {
    "codiceFiscale",
    "cognome",
    "nome",
    "dataNascita",
    "toponimoIndirizzo",
    "descrizioneVia",
    "numeroCivico",
    "piano",
    "scala",
    "interno",
    "esponente",
    "dataVerbaleIscrizione",
    "numVerbaleIscrizione",
    "annoIscrizioneElett",
    "numeroFascicolo",
    "numeroListaGenerale",
    "numeroListaSezionale",
    "descrizioneLista",
    "tipoElettore",
    "numeroSezione",
    "numeroTesseraElettorale",
    "dataRilascioTesseraElett",
    "iscrizioneAlboPresidente",
    "iscrizioneAlboScrutatori",
    "numVerbIscrAlboScrut",
    "dataVerbIscrAlboScrut"
})
public class VisuraPosizioneElettorale {

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
    protected String toponimoIndirizzo;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneVia;
    @XmlElement(required = true, nillable = true)
    protected String numeroCivico;
    @XmlElement(required = true, nillable = true)
    protected String piano;
    @XmlElement(required = true, nillable = true)
    protected String scala;
    @XmlElement(required = true, nillable = true)
    protected String interno;
    @XmlElement(required = true, nillable = true)
    protected String esponente;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataVerbaleIscrizione;
    @XmlElement(required = true, nillable = true)
    protected String numVerbaleIscrizione;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoIscrizioneElett;
    @XmlElement(required = true, nillable = true)
    protected String numeroFascicolo;
    @XmlElement(required = true, nillable = true)
    protected String numeroListaGenerale;
    @XmlElement(required = true, nillable = true)
    protected String numeroListaSezionale;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneLista;
    @XmlElement(required = true, nillable = true)
    protected String tipoElettore;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroSezione;
    @XmlElement(required = true, nillable = true)
    protected String numeroTesseraElettorale;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataRilascioTesseraElett;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean iscrizioneAlboPresidente;
    @XmlElement(required = true, type = Boolean.class, nillable = true)
    protected Boolean iscrizioneAlboScrutatori;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numVerbIscrAlboScrut;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataVerbIscrAlboScrut;

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
     * Recupera il valore della proprietà dataVerbaleIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataVerbaleIscrizione() {
        return dataVerbaleIscrizione;
    }

    /**
     * Imposta il valore della proprietà dataVerbaleIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataVerbaleIscrizione(XMLGregorianCalendar value) {
        this.dataVerbaleIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà numVerbaleIscrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumVerbaleIscrizione() {
        return numVerbaleIscrizione;
    }

    /**
     * Imposta il valore della proprietà numVerbaleIscrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumVerbaleIscrizione(String value) {
        this.numVerbaleIscrizione = value;
    }

    /**
     * Recupera il valore della proprietà annoIscrizioneElett.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoIscrizioneElett() {
        return annoIscrizioneElett;
    }

    /**
     * Imposta il valore della proprietà annoIscrizioneElett.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoIscrizioneElett(BigInteger value) {
        this.annoIscrizioneElett = value;
    }

    /**
     * Recupera il valore della proprietà numeroFascicolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroFascicolo() {
        return numeroFascicolo;
    }

    /**
     * Imposta il valore della proprietà numeroFascicolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroFascicolo(String value) {
        this.numeroFascicolo = value;
    }

    /**
     * Recupera il valore della proprietà numeroListaGenerale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroListaGenerale() {
        return numeroListaGenerale;
    }

    /**
     * Imposta il valore della proprietà numeroListaGenerale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroListaGenerale(String value) {
        this.numeroListaGenerale = value;
    }

    /**
     * Recupera il valore della proprietà numeroListaSezionale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroListaSezionale() {
        return numeroListaSezionale;
    }

    /**
     * Imposta il valore della proprietà numeroListaSezionale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroListaSezionale(String value) {
        this.numeroListaSezionale = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneLista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneLista() {
        return descrizioneLista;
    }

    /**
     * Imposta il valore della proprietà descrizioneLista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneLista(String value) {
        this.descrizioneLista = value;
    }

    /**
     * Recupera il valore della proprietà tipoElettore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoElettore() {
        return tipoElettore;
    }

    /**
     * Imposta il valore della proprietà tipoElettore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoElettore(String value) {
        this.tipoElettore = value;
    }

    /**
     * Recupera il valore della proprietà numeroSezione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroSezione() {
        return numeroSezione;
    }

    /**
     * Imposta il valore della proprietà numeroSezione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroSezione(BigInteger value) {
        this.numeroSezione = value;
    }

    /**
     * Recupera il valore della proprietà numeroTesseraElettorale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroTesseraElettorale() {
        return numeroTesseraElettorale;
    }

    /**
     * Imposta il valore della proprietà numeroTesseraElettorale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroTesseraElettorale(String value) {
        this.numeroTesseraElettorale = value;
    }

    /**
     * Recupera il valore della proprietà dataRilascioTesseraElett.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataRilascioTesseraElett() {
        return dataRilascioTesseraElett;
    }

    /**
     * Imposta il valore della proprietà dataRilascioTesseraElett.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataRilascioTesseraElett(XMLGregorianCalendar value) {
        this.dataRilascioTesseraElett = value;
    }

    /**
     * Recupera il valore della proprietà iscrizioneAlboPresidente.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIscrizioneAlboPresidente() {
        return iscrizioneAlboPresidente;
    }

    /**
     * Imposta il valore della proprietà iscrizioneAlboPresidente.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIscrizioneAlboPresidente(Boolean value) {
        this.iscrizioneAlboPresidente = value;
    }

    /**
     * Recupera il valore della proprietà iscrizioneAlboScrutatori.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIscrizioneAlboScrutatori() {
        return iscrizioneAlboScrutatori;
    }

    /**
     * Imposta il valore della proprietà iscrizioneAlboScrutatori.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIscrizioneAlboScrutatori(Boolean value) {
        this.iscrizioneAlboScrutatori = value;
    }

    /**
     * Recupera il valore della proprietà numVerbIscrAlboScrut.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumVerbIscrAlboScrut() {
        return numVerbIscrAlboScrut;
    }

    /**
     * Imposta il valore della proprietà numVerbIscrAlboScrut.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumVerbIscrAlboScrut(BigInteger value) {
        this.numVerbIscrAlboScrut = value;
    }

    /**
     * Recupera il valore della proprietà dataVerbIscrAlboScrut.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataVerbIscrAlboScrut() {
        return dataVerbIscrAlboScrut;
    }

    /**
     * Imposta il valore della proprietà dataVerbIscrAlboScrut.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataVerbIscrAlboScrut(XMLGregorianCalendar value) {
        this.dataVerbIscrAlboScrut = value;
    }

}
