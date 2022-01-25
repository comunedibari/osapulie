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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per lampadaVotivaPosizioneVisuraServiziCimiteriali complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="lampadaVotivaPosizioneVisuraServiziCimiteriali"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="mesi" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="tariffa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importoLampada" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
@XmlType(name = "lampadaVotivaPosizioneVisuraServiziCimiteriali", propOrder = {
    "id",
    "mesi",
    "tariffa",
    "importoLampada",
    "idPosizione"
})
public class LampadaVotivaPosizioneVisuraServiziCimiteriali {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected BigInteger mesi;
    @XmlElement(required = true, nillable = true)
    protected String tariffa;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoLampada;
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
     * Recupera il valore della proprietà tariffa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffa() {
        return tariffa;
    }

    /**
     * Imposta il valore della proprietà tariffa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffa(String value) {
        this.tariffa = value;
    }

    /**
     * Recupera il valore della proprietà importoLampada.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoLampada() {
        return importoLampada;
    }

    /**
     * Imposta il valore della proprietà importoLampada.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoLampada(Double value) {
        this.importoLampada = value;
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
