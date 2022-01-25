//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:48:15 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraServiziCimiterialiService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per defuntoPosizioneVisuraServiziCimiteriali complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="defuntoPosizioneVisuraServiziCimiteriali"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="nomeDefunto" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dataNascita" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataMorte" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="idPosizione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "defuntoPosizioneVisuraServiziCimiteriali", propOrder = {
    "id",
    "nomeDefunto",
    "dataNascita",
    "dataMorte",
    "idPosizione"
})
public class DefuntoPosizioneVisuraServiziCimiteriali {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String nomeDefunto;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataNascita;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataMorte;
    @XmlElement(required = true, nillable = true)
    protected BigInteger idPosizione;

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
     * Recupera il valore della proprietà nomeDefunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeDefunto() {
        return nomeDefunto;
    }

    /**
     * Imposta il valore della proprietà nomeDefunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeDefunto(String value) {
        this.nomeDefunto = value;
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
     * Recupera il valore della proprietà idPosizione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdPosizione() {
        return idPosizione;
    }

    /**
     * Imposta il valore della proprietà idPosizione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdPosizione(BigInteger value) {
        this.idPosizione = value;
    }

}
