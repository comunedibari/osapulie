
package ws.it.eng.iris.core.send.sip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendSipResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendSipResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://sip.send.core.iris.eng.it}sipSendResponse" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the sipSendResponse property.
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
     * Sets the value of the sipSendResponse property.
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
