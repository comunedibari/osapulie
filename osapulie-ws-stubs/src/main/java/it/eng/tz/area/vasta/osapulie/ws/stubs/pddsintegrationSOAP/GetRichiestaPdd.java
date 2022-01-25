//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.12 alle 12:34:16 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.pddsintegrationSOAP;

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
 *         &lt;element name="richiesta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="servizio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "richiesta",
    "servizio",
    "url"
})
@XmlRootElement(name = "getRichiestaPdd")
public class GetRichiestaPdd {

    @XmlElement(required = true)
    protected String richiesta;
    @XmlElement(required = true)
    protected String servizio;
    @XmlElement(required = true)
    protected String url;

    /**
     * Recupera il valore della proprietà richiesta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRichiesta() {
        return richiesta;
    }

    /**
     * Imposta il valore della proprietà richiesta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRichiesta(String value) {
        this.richiesta = value;
    }

    /**
     * Recupera il valore della proprietà servizio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServizio() {
        return servizio;
    }

    /**
     * Imposta il valore della proprietà servizio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServizio(String value) {
        this.servizio = value;
    }

    /**
     * Recupera il valore della proprietà url.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Imposta il valore della proprietà url.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
