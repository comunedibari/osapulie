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
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="nome" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="valore" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
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
    "id",
    "nome",
    "valore"
})
@XmlRootElement(name = "insert_tb_agevolazione_operation")
public class InsertTbAgevolazioneOperation {

    @XmlElement(name = "ID", required = true, type = Integer.class, nillable = true)
    protected Integer id;
    @XmlElement(required = true, nillable = true)
    protected String nome;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double valore;

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

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà valore.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getValore() {
        return valore;
    }

    /**
     * Imposta il valore della proprietà valore.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setValore(Double value) {
        this.valore = value;
    }

}
