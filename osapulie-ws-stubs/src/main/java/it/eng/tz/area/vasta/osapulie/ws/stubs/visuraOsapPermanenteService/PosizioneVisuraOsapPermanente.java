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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per posizioneVisuraOsapPermanente complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="posizioneVisuraOsapPermanente"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="indirizzoUtenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="superficie" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="zonaUtenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneTariffa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importoDaPagare" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="mesi" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
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
@XmlType(name = "posizioneVisuraOsapPermanente", propOrder = {
    "id",
    "indirizzoUtenza",
    "superficie",
    "zonaUtenza",
    "descrizioneTariffa",
    "importoDaPagare",
    "mesi",
    "idVisura"
})
public class PosizioneVisuraOsapPermanente {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String indirizzoUtenza;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double superficie;
    @XmlElement(required = true, nillable = true)
    protected String zonaUtenza;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneTariffa;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoDaPagare;
    @XmlElement(required = true, nillable = true)
    protected BigInteger mesi;
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
     * Recupera il valore della proprietà indirizzoUtenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoUtenza() {
        return indirizzoUtenza;
    }

    /**
     * Imposta il valore della proprietà indirizzoUtenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoUtenza(String value) {
        this.indirizzoUtenza = value;
    }

    /**
     * Recupera il valore della proprietà superficie.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSuperficie() {
        return superficie;
    }

    /**
     * Imposta il valore della proprietà superficie.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSuperficie(Double value) {
        this.superficie = value;
    }

    /**
     * Recupera il valore della proprietà zonaUtenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZonaUtenza() {
        return zonaUtenza;
    }

    /**
     * Imposta il valore della proprietà zonaUtenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZonaUtenza(String value) {
        this.zonaUtenza = value;
    }

    /**
     * Recupera il valore della proprietà descrizioneTariffa.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizioneTariffa() {
        return descrizioneTariffa;
    }

    /**
     * Imposta il valore della proprietà descrizioneTariffa.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizioneTariffa(String value) {
        this.descrizioneTariffa = value;
    }

    /**
     * Recupera il valore della proprietà importoDaPagare.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoDaPagare() {
        return importoDaPagare;
    }

    /**
     * Imposta il valore della proprietà importoDaPagare.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoDaPagare(Double value) {
        this.importoDaPagare = value;
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
