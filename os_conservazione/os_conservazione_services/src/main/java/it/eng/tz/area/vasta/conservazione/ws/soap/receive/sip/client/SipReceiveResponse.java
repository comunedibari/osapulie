//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 06:14:03 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client;

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
 *         &lt;element name="serviceReturn" type="{http://sip.receive.core.iris.eng.it}wsGenericServiceOutput"/&gt;
 *         &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
    "serviceReturn",
    "idSip"
})
@XmlRootElement(name = "sipReceiveResponse")
public class SipReceiveResponse {

    @XmlElement(required = true)
    protected WsGenericServiceOutput serviceReturn;
    protected String idSip;

    /**
     * Recupera il valore della proprietà serviceReturn.
     * 
     * @return
     *     possible object is
     *     {@link WsGenericServiceOutput }
     *     
     */
    public WsGenericServiceOutput getServiceReturn() {
        return serviceReturn;
    }

    /**
     * Imposta il valore della proprietà serviceReturn.
     * 
     * @param value
     *     allowed object is
     *     {@link WsGenericServiceOutput }
     *     
     */
    public void setServiceReturn(WsGenericServiceOutput value) {
        this.serviceReturn = value;
    }

    /**
     * Recupera il valore della proprietà idSip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdSip() {
        return idSip;
    }

    /**
     * Imposta il valore della proprietà idSip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdSip(String value) {
        this.idSip = value;
    }

}
