//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 05:15:52 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client;

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
 *         &lt;element name="serviceReturn" type="{http://sip.esito.core.iris.eng.it}wsGenericServiceOutput"/&gt;
 *         &lt;element name="statoSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="esitoSip" type="{http://sip.esito.core.iris.eng.it}attachment"/&gt;
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
    "statoSip",
    "esitoSip"
})
@XmlRootElement(name = "sipEsitoResponse")
public class SipEsitoResponse {

    @XmlElement(required = true)
    protected WsGenericServiceOutput serviceReturn;
    @XmlElement(required = true)
    protected String statoSip;
    @XmlElement(required = true)
    protected Attachment esitoSip;

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
     * Recupera il valore della proprietà statoSip.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatoSip() {
        return statoSip;
    }

    /**
     * Imposta il valore della proprietà statoSip.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatoSip(String value) {
        this.statoSip = value;
    }

    /**
     * Recupera il valore della proprietà esitoSip.
     * 
     * @return
     *     possible object is
     *     {@link Attachment }
     *     
     */
    public Attachment getEsitoSip() {
        return esitoSip;
    }

    /**
     * Imposta il valore della proprietà esitoSip.
     * 
     * @param value
     *     allowed object is
     *     {@link Attachment }
     *     
     */
    public void setEsitoSip(Attachment value) {
        this.esitoSip = value;
    }

}
