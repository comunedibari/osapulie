//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 05:16:18 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.send.sip.client;

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
 *         &lt;element name="serviceReturn" type="{http://sip.send.core.iris.eng.it}wsGenericServiceOutput"/&gt;
 *         &lt;element name="sipFile" type="{http://sip.send.core.iris.eng.it}attachment"/&gt;
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
    "sipFile"
})
@XmlRootElement(name = "sipSendResponse")
public class SipSendResponse {

    @XmlElement(required = true)
    protected WsGenericServiceOutput serviceReturn;
    @XmlElement(required = true)
    protected Attachment sipFile;

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
     * Recupera il valore della proprietà sipFile.
     * 
     * @return
     *     possible object is
     *     {@link Attachment }
     *     
     */
    public Attachment getSipFile() {
        return sipFile;
    }

    /**
     * Imposta il valore della proprietà sipFile.
     * 
     * @param value
     *     allowed object is
     *     {@link Attachment }
     *     
     */
    public void setSipFile(Attachment value) {
        this.sipFile = value;
    }

}
