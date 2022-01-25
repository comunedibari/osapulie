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
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per SPServiceResponse complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SPServiceResponse"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="archiveKey" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="reason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="securedDocument" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SPServiceResponse", namespace = "http://spservice.securepaperappliance.land.it/xsd", propOrder = {
    "archiveKey",
    "reason",
    "securedDocument",
    "status"
})
public class SPServiceResponse {

    @XmlElementRef(name = "archiveKey", namespace = "http://spservice.securepaperappliance.land.it/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> archiveKey;
    @XmlElementRef(name = "reason", namespace = "http://spservice.securepaperappliance.land.it/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<String> reason;
    @XmlElementRef(name = "securedDocument", namespace = "http://spservice.securepaperappliance.land.it/xsd", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> securedDocument;
    protected Integer status;

    /**
     * Recupera il valore della proprietà archiveKey.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getArchiveKey() {
        return archiveKey;
    }

    /**
     * Imposta il valore della proprietà archiveKey.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setArchiveKey(JAXBElement<String> value) {
        this.archiveKey = value;
    }

    /**
     * Recupera il valore della proprietà reason.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getReason() {
        return reason;
    }

    /**
     * Imposta il valore della proprietà reason.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setReason(JAXBElement<String> value) {
        this.reason = value;
    }

    /**
     * Recupera il valore della proprietà securedDocument.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getSecuredDocument() {
        return securedDocument;
    }

    /**
     * Imposta il valore della proprietà securedDocument.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setSecuredDocument(JAXBElement<byte[]> value) {
        this.securedDocument = value;
    }

    /**
     * Recupera il valore della proprietà status.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * Imposta il valore della proprietà status.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setStatus(Integer value) {
        this.status = value;
    }

}
