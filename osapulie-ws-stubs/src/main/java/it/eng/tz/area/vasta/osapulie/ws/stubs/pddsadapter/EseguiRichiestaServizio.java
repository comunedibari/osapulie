//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 10:37:13 AM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter;

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
 *         &lt;element name="xmlRichiesta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="nomeServizio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="indirizzoPddRemota" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "xmlRichiesta",
    "nomeServizio",
    "indirizzoPddRemota"
})
@XmlRootElement(name = "eseguiRichiestaServizio")
public class EseguiRichiestaServizio {

    @XmlElement(required = true)
    protected String xmlRichiesta;
    @XmlElement(required = true)
    protected String nomeServizio;
    @XmlElement(required = true)
    protected String indirizzoPddRemota;

    /**
     * Recupera il valore della proprietà xmlRichiesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlRichiesta() {
        return xmlRichiesta;
    }

    /**
     * Imposta il valore della proprietà xmlRichiesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlRichiesta(String value) {
        this.xmlRichiesta = value;
    }

    /**
     * Recupera il valore della proprietà nomeServizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeServizio() {
        return nomeServizio;
    }

    /**
     * Imposta il valore della proprietà nomeServizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeServizio(String value) {
        this.nomeServizio = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoPddRemota.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoPddRemota() {
        return indirizzoPddRemota;
    }

    /**
     * Imposta il valore della proprietà indirizzoPddRemota.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoPddRemota(String value) {
        this.indirizzoPddRemota = value;
    }

}
