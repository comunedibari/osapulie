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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per sendSipResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="sendSipResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://sip.send.core.iris.eng.it}sipSendResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSipResponse", propOrder = {
    "sipSendResponse"
})
public class SendSipResponse {

    @XmlElement(namespace = "http://sip.send.core.iris.eng.it")
    protected SipSendResponse sipSendResponse;

    /**
     * Recupera il valore della proprietà sipSendResponse.
     * 
     * @return
     *     possible object is
     *     {@link SipSendResponse }
     *     
     */
    public SipSendResponse getSipSendResponse() {
        return sipSendResponse;
    }

    /**
     * Imposta il valore della proprietà sipSendResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link SipSendResponse }
     *     
     */
    public void setSipSendResponse(SipSendResponse value) {
        this.sipSendResponse = value;
    }

}
