//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:40:13 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per posizioneVisuraImposteInsegnePubblicita complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="posizioneVisuraImposteInsegnePubblicita"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="indirizzoPubblicita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizionePubblicita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mq" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="mesi" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoPubblicita" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
@XmlType(name = "posizioneVisuraImposteInsegnePubblicita", propOrder = {
    "id",
    "indirizzoPubblicita",
    "descrizionePubblicita",
    "mq",
    "mesi",
    "importoPubblicita",
    "idVisura"
})
public class PosizioneVisuraImposteInsegnePubblicita {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String indirizzoPubblicita;
    @XmlElement(required = true, nillable = true)
    protected String descrizionePubblicita;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double mq;
    @XmlElement(required = true, nillable = true)
    protected BigInteger mesi;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoPubblicita;
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
     * Recupera il valore della proprietà indirizzoPubblicita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoPubblicita() {
        return indirizzoPubblicita;
    }

    /**
     * Imposta il valore della proprietà indirizzoPubblicita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoPubblicita(String value) {
        this.indirizzoPubblicita = value;
    }

    /**
     * Recupera il valore della proprietà descrizionePubblicita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizionePubblicita() {
        return descrizionePubblicita;
    }

    /**
     * Imposta il valore della proprietà descrizionePubblicita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizionePubblicita(String value) {
        this.descrizionePubblicita = value;
    }

    /**
     * Recupera il valore della proprietà mq.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMq() {
        return mq;
    }

    /**
     * Imposta il valore della proprietà mq.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMq(Double value) {
        this.mq = value;
    }

    /**
     * Recupera il valore della proprietà mesi.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMesi() {
        return mesi;
    }

    /**
     * Imposta il valore della proprietà mesi.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMesi(BigInteger value) {
        this.mesi = value;
    }

    /**
     * Recupera il valore della proprietà importoPubblicita.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoPubblicita() {
        return importoPubblicita;
    }

    /**
     * Imposta il valore della proprietà importoPubblicita.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoPubblicita(Double value) {
        this.importoPubblicita = value;
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
