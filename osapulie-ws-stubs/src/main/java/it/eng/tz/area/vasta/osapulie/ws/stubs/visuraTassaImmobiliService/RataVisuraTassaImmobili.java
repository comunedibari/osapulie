//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:48:41 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaImmobiliService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per rataVisuraTassaImmobili complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="rataVisuraTassaImmobili"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoAbitazionePrincipale" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoAreaFabbricabile" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoTerreniAgricoli" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoAltriFabbricati" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="numeroFabbricati" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoDaPagare" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="identificativoRata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoRata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "rataVisuraTassaImmobili", propOrder = {
    "id",
    "importoAbitazionePrincipale",
    "importoAreaFabbricabile",
    "importoTerreniAgricoli",
    "importoAltriFabbricati",
    "numeroFabbricati",
    "importoDaPagare",
    "identificativoRata",
    "tipoRata",
    "importoPagato",
    "dataPagamento",
    "idVisura"
})
public class RataVisuraTassaImmobili {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoAbitazionePrincipale;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoAreaFabbricabile;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoTerreniAgricoli;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoAltriFabbricati;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroFabbricati;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoDaPagare;
    @XmlElement(required = true, nillable = true)
    protected String identificativoRata;
    @XmlElement(required = true, nillable = true)
    protected String tipoRata;
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
     * Recupera il valore della proprietà importoAbitazionePrincipale.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoAbitazionePrincipale() {
        return importoAbitazionePrincipale;
    }

    /**
     * Imposta il valore della proprietà importoAbitazionePrincipale.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoAbitazionePrincipale(Double value) {
        this.importoAbitazionePrincipale = value;
    }

    /**
     * Recupera il valore della proprietà importoAreaFabbricabile.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoAreaFabbricabile() {
        return importoAreaFabbricabile;
    }

    /**
     * Imposta il valore della proprietà importoAreaFabbricabile.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoAreaFabbricabile(Double value) {
        this.importoAreaFabbricabile = value;
    }

    /**
     * Recupera il valore della proprietà importoTerreniAgricoli.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoTerreniAgricoli() {
        return importoTerreniAgricoli;
    }

    /**
     * Imposta il valore della proprietà importoTerreniAgricoli.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoTerreniAgricoli(Double value) {
        this.importoTerreniAgricoli = value;
    }

    /**
     * Recupera il valore della proprietà importoAltriFabbricati.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoAltriFabbricati() {
        return importoAltriFabbricati;
    }

    /**
     * Imposta il valore della proprietà importoAltriFabbricati.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoAltriFabbricati(Double value) {
        this.importoAltriFabbricati = value;
    }

    /**
     * Recupera il valore della proprietà numeroFabbricati.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroFabbricati() {
        return numeroFabbricati;
    }

    /**
     * Imposta il valore della proprietà numeroFabbricati.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroFabbricati(BigInteger value) {
        this.numeroFabbricati = value;
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
     * Recupera il valore della proprietà tipoRata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRata() {
        return tipoRata;
    }

    /**
     * Imposta il valore della proprietà tipoRata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRata(String value) {
        this.tipoRata = value;
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
