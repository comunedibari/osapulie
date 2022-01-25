//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:41:10 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapPermanenteService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per rataVisuraOsapPermanente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rataVisuraOsapPermanente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoRata" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="scadenzaRata" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroRata" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="identificativoRata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importoPagato" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dataPagamento" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="idVisura" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rataVisuraOsapPermanente", propOrder = {
    "id",
    "importoRata",
    "scadenzaRata",
    "numeroRata",
    "identificativoRata",
    "importoPagato",
    "dataPagamento",
    "idVisura"
})
public class RataVisuraOsapPermanente {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoRata;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar scadenzaRata;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroRata;
    @XmlElement(required = true, nillable = true)
    protected String identificativoRata;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoPagato;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataPagamento;
    @XmlElement(required = true, nillable = true)
    protected BigInteger idVisura;

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
     * Recupera il valore della proprietà importoRata.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoRata() {
        return importoRata;
    }

    /**
     * Imposta il valore della proprietà importoRata.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoRata(Double value) {
        this.importoRata = value;
    }

    /**
     * Recupera il valore della proprietà scadenzaRata.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getScadenzaRata() {
        return scadenzaRata;
    }

    /**
     * Imposta il valore della proprietà scadenzaRata.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setScadenzaRata(XMLGregorianCalendar value) {
        this.scadenzaRata = value;
    }

    /**
     * Recupera il valore della proprietà numeroRata.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroRata() {
        return numeroRata;
    }

    /**
     * Imposta il valore della proprietà numeroRata.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroRata(BigInteger value) {
        this.numeroRata = value;
    }

    /**
     * Recupera il valore della proprietà identificativoRata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoRata() {
        return identificativoRata;
    }

    /**
     * Imposta il valore della proprietà identificativoRata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoRata(String value) {
        this.identificativoRata = value;
    }

    /**
     * Recupera il valore della proprietà importoPagato.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoPagato() {
        return importoPagato;
    }

    /**
     * Imposta il valore della proprietà importoPagato.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoPagato(Double value) {
        this.importoPagato = value;
    }

    /**
     * Recupera il valore della proprietà dataPagamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataPagamento() {
        return dataPagamento;
    }

    /**
     * Imposta il valore della proprietà dataPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataPagamento(XMLGregorianCalendar value) {
        this.dataPagamento = value;
    }

    /**
     * Recupera il valore della proprietà idVisura.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdVisura() {
        return idVisura;
    }

    /**
     * Imposta il valore della proprietà idVisura.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdVisura(BigInteger value) {
        this.idVisura = value;
    }

}
