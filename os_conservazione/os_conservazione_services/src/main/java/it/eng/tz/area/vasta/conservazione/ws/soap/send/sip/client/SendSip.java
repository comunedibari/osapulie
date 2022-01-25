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
 * <p>Classe Java per sendSip complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="sendSip"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sipSend" minOccurs="0" form="qualified"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
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
     * Recupera il valore della proprietà sipSend.
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
     * Imposta il valore della proprietà sipSend.
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "idSip"
    })
    public static class SipSend {

        @XmlElement(required = true)
        protected String idSip;

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

}
