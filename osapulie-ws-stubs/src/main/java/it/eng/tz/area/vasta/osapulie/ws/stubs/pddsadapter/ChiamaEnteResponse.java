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
 *         &lt;element name="chiamaEnteReturn" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "chiamaEnteReturn"
})
@XmlRootElement(name = "chiamaEnteResponse")
public class ChiamaEnteResponse {

    @XmlElement(required = true)
    protected String chiamaEnteReturn;

    /**
     * Recupera il valore della proprietà chiamaEnteReturn.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChiamaEnteReturn() {
        return chiamaEnteReturn;
    }

    /**
     * Imposta il valore della proprietà chiamaEnteReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChiamaEnteReturn(String value) {
        this.chiamaEnteReturn = value;
    }

}
