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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per receiveSipResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="receiveSipResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://sip.receive.core.iris.eng.it}sipReceiveResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveSipResponse", propOrder = {
    "sipReceiveResponse"
})
public class ReceiveSipResponse {

    @XmlElement(namespace = "http://sip.receive.core.iris.eng.it")
    protected SipReceiveResponse sipReceiveResponse;

    /**
     * Recupera il valore della proprietà sipReceiveResponse.
     * 
     * @return
     *     possible object is
     *     {@link SipReceiveResponse }
     *     
     */
    public SipReceiveResponse getSipReceiveResponse() {
        return sipReceiveResponse;
    }

    /**
     * Imposta il valore della proprietà sipReceiveResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link SipReceiveResponse }
     *     
     */
    public void setSipReceiveResponse(SipReceiveResponse value) {
        this.sipReceiveResponse = value;
    }

}
