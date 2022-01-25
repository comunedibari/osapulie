//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.12 alle 12:32:25 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.sp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
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
 *         &lt;element name="return" type="{http://spservice.securepaperappliance.land.it/xsd}SPServiceResponse" minOccurs="0"/&gt;
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
    "_return"
})
@XmlRootElement(name = "securizeXMLResponse")
public class SecurizeXMLResponse {

    @XmlElementRef(name = "return", namespace = "http://spservice.securepaperappliance.land.it", type = JAXBElement.class, required = false)
    protected JAXBElement<SPServiceResponse> _return;

    /**
     * Recupera il valore della proprietà return.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}
     *     
     */
    public JAXBElement<SPServiceResponse> getReturn() {
        return _return;
    }

    /**
     * Imposta il valore della proprietà return.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}
     *     
     */
    public void setReturn(JAXBElement<SPServiceResponse> value) {
        this._return = value;
    }

}
