
package ws.it.eng.iris.core.send.sip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendSip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendSip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sipSend" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendSip", propOrder = {
    "sipSend"
})
public class SendSip {

    @XmlElement(namespace = "http://sip.send.core.iris.eng.it")
    protected SendSip.SipSend sipSend;

    /**
     * Gets the value of the sipSend property.
     * 
     * @return
     *     possible object is
     *     {@link SendSip.SipSend }
     *     
     */
    public SendSip.SipSend getSipSend() {
        return sipSend;
    }

    /**
     * Sets the value of the sipSend property.
     * 
     * @param value
     *     allowed object is
     *     {@link SendSip.SipSend }
     *     
     */
    public void setSipSend(SendSip.SipSend value) {
        this.sipSend = value;
    }


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
     *         &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "idSip"
    })
    public static class SipSend {

        @XmlElement(required = true)
        protected String idSip;

        /**
         * Gets the value of the idSip property.
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
         * Sets the value of the idSip property.
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

}
