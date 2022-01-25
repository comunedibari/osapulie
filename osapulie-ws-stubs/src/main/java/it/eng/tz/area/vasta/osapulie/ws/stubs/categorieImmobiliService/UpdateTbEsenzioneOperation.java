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
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fk_categoriaimmobile" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="fk_tributo" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "descrizione",
    "fkCategoriaimmobile",
    "fkTributo",
    "id"
})
@XmlRootElement(name = "update_tb_esenzione_operation")
public class UpdateTbEsenzioneOperation {

    @XmlElement(required = true, nillable = true)
    protected String descrizione;
    @XmlElement(name = "fk_categoriaimmobile", required = true, type = Integer.class, nillable = true)
    protected Integer fkCategoriaimmobile;
    @XmlElement(name = "fk_tributo", required = true, type = Integer.class, nillable = true)
    protected Integer fkTributo;
    @XmlElement(name = "ID", required = true, type = Integer.class, nillable = true)
    protected Integer id;

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
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
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getID() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setID(Integer value) {
        this.id = value;
    }

}
