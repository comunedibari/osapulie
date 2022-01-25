//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:49:08 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaRifiutiService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per visuraTassaRifiuti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="visuraTassaRifiuti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceFiscale" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataInizio" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataFine" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataAggiornamento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="contoCorrente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="numeroDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importoDocumento" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="annoRiferimento" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="descrizioneTassa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "visuraTassaRifiuti", propOrder = {
    "id",
    "codiceFiscale",
    "dataInizio",
    "dataFine",
    "dataAggiornamento",
    "contoCorrente",
    "numeroDocumento",
    "importoDocumento",
    "annoRiferimento",
    "descrizioneTassa"
})
public class VisuraTassaRifiuti {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String codiceFiscale;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizio;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFine;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataAggiornamento;
    @XmlElement(required = true, nillable = true)
    protected String contoCorrente;
    @XmlElement(required = true, nillable = true)
    protected String numeroDocumento;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoDocumento;
    @XmlElement(required = true, nillable = true)
    protected BigInteger annoRiferimento;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneTassa;

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
     * Recupera il valore della proprietà dataInizio.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizio() {
        return dataInizio;
    }

    /**
     * Imposta il valore della proprietà dataInizio.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizio(XMLGregorianCalendar value) {
        this.dataInizio = value;
    }

    /**
     * Recupera il valore della proprietà dataFine.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFine() {
        return dataFine;
    }

    /**
     * Imposta il valore della proprietà dataFine.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFine(XMLGregorianCalendar value) {
        this.dataFine = value;
    }

    /**
     * Recupera il valore della proprietà dataAggiornamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataAggiornamento() {
        return dataAggiornamento;
    }

    /**
     * Imposta il valore della proprietà dataAggiornamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataAggiornamento(XMLGregorianCalendar value) {
        this.dataAggiornamento = value;
    }

    /**
     * Recupera il valore della proprietà contoCorrente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContoCorrente() {
        return contoCorrente;
    }

    /**
     * Imposta il valore della proprietà contoCorrente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContoCorrente(String value) {
        this.contoCorrente = value;
    }

    /**
     * Recupera il valore della proprietà numeroDocumento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    /**
     * Imposta il valore della proprietà numeroDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNumeroDocumento(String value) {
        this.numeroDocumento = value;
    }

    /**
     * Recupera il valore della proprietà importoDocumento.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoDocumento() {
        return importoDocumento;
    }

    /**
     * Imposta il valore della proprietà importoDocumento.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoDocumento(Double value) {
        this.importoDocumento = value;
    }

    /**
     * Recupera il valore della proprietà annoRiferimento.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnnoRiferimento() {
        return annoRiferimento;
    }

    /**
     * Imposta il valore della proprietà annoRiferimento.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnnoRiferimento(BigInteger value) {
        this.annoRiferimento = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneTassa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTassa() {
        return descrizioneTassa;
    }

    /**
     * Imposta il valore della proprietà descrizioneTassa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTassa(String value) {
        this.descrizioneTassa = value;
    }

}
