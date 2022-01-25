//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:47:47 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraPubblicheAffissioniService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per posizioneVisuraPubblicheAffissioni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="posizioneVisuraPubblicheAffissioni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="numeroFogli" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="numeroManifesti" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="giorniAffissione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="tariffa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dimensioneManifesti" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "posizioneVisuraPubblicheAffissioni", propOrder = {
    "id",
    "numeroFogli",
    "numeroManifesti",
    "giorniAffissione",
    "tariffa",
    "dimensioneManifesti",
    "idVisura"
})
public class PosizioneVisuraPubblicheAffissioni {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroFogli;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroManifesti;
    @XmlElement(required = true, nillable = true)
    protected BigInteger giorniAffissione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double tariffa;
    @XmlElement(required = true, nillable = true)
    protected String dimensioneManifesti;
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
     * Recupera il valore della proprietà numeroFogli.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroFogli() {
        return numeroFogli;
    }

    /**
     * Imposta il valore della proprietà numeroFogli.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroFogli(BigInteger value) {
        this.numeroFogli = value;
    }

    /**
     * Recupera il valore della proprietà numeroManifesti.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroManifesti() {
        return numeroManifesti;
    }

    /**
     * Imposta il valore della proprietà numeroManifesti.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroManifesti(BigInteger value) {
        this.numeroManifesti = value;
    }

    /**
     * Recupera il valore della proprietà giorniAffissione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getGiorniAffissione() {
        return giorniAffissione;
    }

    /**
     * Imposta il valore della proprietà giorniAffissione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setGiorniAffissione(BigInteger value) {
        this.giorniAffissione = value;
    }

    /**
     * Recupera il valore della proprietà tariffa.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTariffa() {
        return tariffa;
    }

    /**
     * Imposta il valore della proprietà tariffa.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTariffa(Double value) {
        this.tariffa = value;
    }

    /**
     * Recupera il valore della proprietà dimensioneManifesti.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDimensioneManifesti() {
        return dimensioneManifesti;
    }

    /**
     * Imposta il valore della proprietà dimensioneManifesti.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDimensioneManifesti(String value) {
        this.dimensioneManifesti = value;
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
