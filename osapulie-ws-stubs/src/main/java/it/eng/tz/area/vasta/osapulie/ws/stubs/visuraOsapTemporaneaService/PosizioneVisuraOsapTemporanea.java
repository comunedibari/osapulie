//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:42:59 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapTemporaneaService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per posizioneVisuraOsapTemporanea complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="posizioneVisuraOsapTemporanea"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="indirizzoUtenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="superficie" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="zona" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizioneTariffa" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="durataOccupazione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoDaPagare" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
@XmlType(name = "posizioneVisuraOsapTemporanea", propOrder = {
    "id",
    "indirizzoUtenza",
    "superficie",
    "zona",
    "descrizioneTariffa",
    "durataOccupazione",
    "importoDaPagare",
    "importoPagato",
    "dataPagamento",
    "idVisura"
})
public class PosizioneVisuraOsapTemporanea {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String indirizzoUtenza;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double superficie;
    @XmlElement(required = true, nillable = true)
    protected String zona;
    @XmlElement(required = true, nillable = true)
    protected String descrizioneTariffa;
    @XmlElement(required = true, nillable = true)
    protected BigInteger durataOccupazione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoDaPagare;
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
     * Recupera il valore della proprietà zona.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZona() {
        return zona;
    }

    /**
     * Imposta il valore della proprietà zona.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZona(String value) {
        this.zona = value;
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
     * Recupera il valore della proprietà durataOccupazione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDurataOccupazione() {
        return durataOccupazione;
    }

    /**
     * Imposta il valore della proprietà durataOccupazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDurataOccupazione(BigInteger value) {
        this.durataOccupazione = value;
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
