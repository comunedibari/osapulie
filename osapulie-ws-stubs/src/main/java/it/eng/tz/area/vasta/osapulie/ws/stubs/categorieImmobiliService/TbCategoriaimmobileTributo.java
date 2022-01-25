//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:21:01 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.categorieImmobiliService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tb_categoriaimmobile_tributo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tb_categoriaimmobile_tributo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fk_categoriaimmobile" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="fk_tributo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codice_tributo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="aliquota" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="anno" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tb_categoriaimmobile_tributo", propOrder = {
    "fkCategoriaimmobile",
    "fkTributo",
    "codiceTributo",
    "aliquota",
    "anno"
})
public class TbCategoriaimmobileTributo {

    @XmlElement(name = "fk_categoriaimmobile", required = true, nillable = true)
    protected BigInteger fkCategoriaimmobile;
    @XmlElement(name = "fk_tributo", required = true, nillable = true)
    protected BigInteger fkTributo;
    @XmlElement(name = "codice_tributo", required = true, nillable = true)
    protected BigInteger codiceTributo;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double aliquota;
    @XmlElement(required = true, nillable = true)
    protected BigInteger anno;

    /**
     * Recupera il valore della proprietà fkCategoriaimmobile.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFkCategoriaimmobile() {
        return fkCategoriaimmobile;
    }

    /**
     * Imposta il valore della proprietà fkCategoriaimmobile.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFkCategoriaimmobile(BigInteger value) {
        this.fkCategoriaimmobile = value;
    }

    /**
     * Recupera il valore della proprietà fkTributo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFkTributo() {
        return fkTributo;
    }

    /**
     * Imposta il valore della proprietà fkTributo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFkTributo(BigInteger value) {
        this.fkTributo = value;
    }

    /**
     * Recupera il valore della proprietà codiceTributo.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCodiceTributo() {
        return codiceTributo;
    }

    /**
     * Imposta il valore della proprietà codiceTributo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCodiceTributo(BigInteger value) {
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
     *     {@link BigInteger }
     *     
     */
    public BigInteger getAnno() {
        return anno;
    }

    /**
     * Imposta il valore della proprietà anno.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setAnno(BigInteger value) {
        this.anno = value;
    }

}
