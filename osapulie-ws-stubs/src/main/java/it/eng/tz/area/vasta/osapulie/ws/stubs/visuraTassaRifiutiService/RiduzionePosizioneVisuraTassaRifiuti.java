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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per riduzionePosizioneVisuraTassaRifiuti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="riduzionePosizioneVisuraTassaRifiuti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codiceArticolo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneArticolo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceValore" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneValore" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="note" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "riduzionePosizioneVisuraTassaRifiuti", propOrder = {
    "id",
    "codiceArticolo",
    "descrizioneArticolo",
    "codiceValore",
    "descrizioneValore",
    "note",
    "idPosizione"
})
public class RiduzionePosizioneVisuraTassaRifiuti {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String codiceArticolo;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneArticolo;
    @XmlElement(required = true, nillable = true)
    protected String codiceValore;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneValore;
    @XmlElement(required = true, nillable = true)
    protected String note;
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
     * Recupera il valore della proprietà codiceArticolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceArticolo() {
        return codiceArticolo;
    }

    /**
     * Imposta il valore della proprietà codiceArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceArticolo(String value) {
        this.codiceArticolo = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneArticolo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneArticolo() {
        return descrizioneArticolo;
    }

    /**
     * Imposta il valore della proprietà descrizioneArticolo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneArticolo(String value) {
        this.descrizioneArticolo = value;
    }

    /**
     * Recupera il valore della proprietà codiceValore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceValore() {
        return codiceValore;
    }

    /**
     * Imposta il valore della proprietà codiceValore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceValore(String value) {
        this.codiceValore = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneValore.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneValore() {
        return descrizioneValore;
    }

    /**
     * Imposta il valore della proprietà descrizioneValore.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneValore(String value) {
        this.descrizioneValore = value;
    }

    /**
     * Recupera il valore della proprietà note.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNote() {
        return note;
    }

    /**
     * Imposta il valore della proprietà note.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNote(String value) {
        this.note = value;
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
