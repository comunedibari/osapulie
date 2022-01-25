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
 * <p>Classe Java per tb_categoriaimmobile_detrazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tb_categoriaimmobile_detrazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="fk_categoriaimmobile" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="fk_tributo" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="fk_detrazione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tb_categoriaimmobile_detrazione", propOrder = {
    "fkCategoriaimmobile",
    "fkTributo",
    "fkDetrazione"
})
public class TbCategoriaimmobileDetrazione {

    @XmlElement(name = "fk_categoriaimmobile", required = true, nillable = true)
    protected BigInteger fkCategoriaimmobile;
    @XmlElement(name = "fk_tributo", required = true, nillable = true)
    protected BigInteger fkTributo;
    @XmlElement(name = "fk_detrazione", required = true, nillable = true)
    protected BigInteger fkDetrazione;

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
     * Recupera il valore della proprietà fkDetrazione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFkDetrazione() {
        return fkDetrazione;
    }

    /**
     * Imposta il valore della proprietà fkDetrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFkDetrazione(BigInteger value) {
        this.fkDetrazione = value;
    }

}
