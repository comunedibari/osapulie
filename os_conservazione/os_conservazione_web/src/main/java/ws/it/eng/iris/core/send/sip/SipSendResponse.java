
package ws.it.eng.iris.core.send.sip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceReturn" type="{http://sip.send.core.iris.eng.it}wsGenericServiceOutput"/>
 *         &lt;element name="sipFile" type="{http://sip.send.core.iris.eng.it}attachment"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the serviceReturn property.
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
     * Sets the value of the serviceReturn property.
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
     * Gets the value of the sipFile property.
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
     * Sets the value of the sipFile property.
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
