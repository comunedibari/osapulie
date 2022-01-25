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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per esitoSipResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="esitoSipResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://sip.esito.core.iris.eng.it}sipEsitoResponse" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "esitoSipResponse", propOrder = {
    "sipEsitoResponse"
})
public class EsitoSipResponse {

    @XmlElement(namespace = "http://sip.esito.core.iris.eng.it")
    protected SipEsitoResponse sipEsitoResponse;

    /**
     * Recupera il valore della proprietà sipEsitoResponse.
     * 
     * @return
     *     possible object is
     *     {@link SipEsitoResponse }
     *     
     */
    public SipEsitoResponse getSipEsitoResponse() {
        return sipEsitoResponse;
    }

    /**
     * Imposta il valore della proprietà sipEsitoResponse.
     * 
     * @param value
     *     allowed object is
     *     {@link SipEsitoResponse }
     *     
     */
    public void setSipEsitoResponse(SipEsitoResponse value) {
        this.sipEsitoResponse = value;
    }

}
