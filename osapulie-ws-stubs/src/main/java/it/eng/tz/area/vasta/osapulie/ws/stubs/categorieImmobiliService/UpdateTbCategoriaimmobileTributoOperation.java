//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:21:01 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.categorieImmobiliService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fk_tributo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="codice_tributo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="aliquota" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fk_categoriaimmobile" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "fkTributo",
    "codiceTributo",
    "aliquota",
    "anno",
    "fkCategoriaimmobile"
})
@XmlRootElement(name = "update_tb_categoriaimmobile_tributo_operation")
public class UpdateTbCategoriaimmobileTributoOperation {

    @XmlElement(name = "fk_tributo", required = true, type = Integer.class, nillable = true)
    protected Integer fkTributo;
    @XmlElement(name = "codice_tributo", required = true, type = Integer.class, nillable = true)
    protected Integer codiceTributo;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double aliquota;
    @XmlElement(required = true, type = Integer.class, nillable = true)
    protected Integer anno;
    @XmlElement(name = "fk_categoriaimmobile", required = true, type = Integer.class, nillable = true)
    protected Integer fkCategoriaimmobile;

    /**
     * Recupera il valore della proprietà fkTributo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFkTributo() {
        return fkTributo;
    }

    /**
     * Imposta il valore della proprietà fkTributo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFkTributo(Integer value) {
        this.fkTributo = value;
    }

    /**
     * Recupera il valore della proprietà codiceTributo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCodiceTributo() {
        return codiceTributo;
    }

    /**
     * Imposta il valore della proprietà codiceTributo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCodiceTributo(Integer value) {
        this.codiceTributo = value;
    }

    /**
     * Recupera il valore della proprietà aliquota.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAliquota() {
        return aliquota;
    }

    /**
     * Imposta il valore della proprietà aliquota.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAliquota(Double value) {
        this.aliquota = value;
    }

    /**
     * Recupera il valore della proprietà anno.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della proprietà anno.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAnno(Integer value) {
        this.anno = value;
    }

    /**
     * Recupera il valore della proprietà fkCategoriaimmobile.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getFkCategoriaimmobile() {
        return fkCategoriaimmobile;
    }

    /**
     * Imposta il valore della proprietà fkCategoriaimmobile.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setFkCategoriaimmobile(Integer value) {
        this.fkCategoriaimmobile = value;
    }

}
