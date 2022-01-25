//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:49:40 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraVariazioniDomicilioService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per visuraVariazioneDomicilio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="visuraVariazioneDomicilio"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataInizioDomicilio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="comune" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataIscrizioneComune" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="toponimoIndirizzo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="indirizzo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroCivico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="esponente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="piano" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="scala" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visuraVariazioneDomicilio", propOrder = {
    "id",
    "codiceFiscale",
    "dataInizioDomicilio",
    "comune",
    "dataIscrizioneComune",
    "toponimoIndirizzo",
    "indirizzo",
    "numeroCivico",
    "esponente",
    "piano",
    "scala",
    "interno"
})
public class VisuraVariazioneDomicilio {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String codiceFiscale;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioDomicilio;
    @XmlElement(required = true, nillable = true)
    protected String comune;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataIscrizioneComune;
    @XmlElement(required = true, nillable = true)
    protected String toponimoIndirizzo;
    @XmlElement(required = true, nillable = true)
    protected String indirizzo;
    @XmlElement(required = true, nillable = true)
    protected String numeroCivico;
    @XmlElement(required = true, nillable = true)
    protected String esponente;
    @XmlElement(required = true, nillable = true)
    protected BigInteger piano;
    @XmlElement(required = true, nillable = true)
    protected String scala;
    @XmlElement(required = true, nillable = true)
    protected String interno;

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
     * Recupera il valore della proprietà dataInizioDomicilio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioDomicilio() {
        return dataInizioDomicilio;
    }

    /**
     * Imposta il valore della proprietà dataInizioDomicilio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioDomicilio(XMLGregorianCalendar value) {
        this.dataInizioDomicilio = value;
    }

    /**
     * Recupera il valore della proprietà comune.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComune() {
        return comune;
    }

    /**
     * Imposta il valore della proprietà comune.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComune(String value) {
        this.comune = value;
    }

    /**
     * Recupera il valore della proprietà dataIscrizioneComune.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataIscrizioneComune() {
        return dataIscrizioneComune;
    }

    /**
     * Imposta il valore della proprietà dataIscrizioneComune.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataIscrizioneComune(XMLGregorianCalendar value) {
        this.dataIscrizioneComune = value;
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
     * Recupera il valore della proprietà indirizzo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzo() {
        return indirizzo;
    }

    /**
     * Imposta il valore della proprietà indirizzo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzo(String value) {
        this.indirizzo = value;
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
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPiano() {
        return piano;
    }

    /**
     * Imposta il valore della proprietà piano.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPiano(BigInteger value) {
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

}
