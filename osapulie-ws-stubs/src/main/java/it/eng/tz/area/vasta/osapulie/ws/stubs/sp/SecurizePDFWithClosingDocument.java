//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.12 alle 12:32:25 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.sp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="inXml" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="closingDocumentXml" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
 *         &lt;element name="signers" type="{http://spservice.securepaperappliance.land.it/xsd}SignerBean" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "serviceId",
    "inXml",
    "closingDocumentXml",
    "signers"
})
@XmlRootElement(name = "securizePDFWithClosingDocument")
public class SecurizePDFWithClosingDocument {

    @XmlElementRef(name = "serviceId", namespace = "http://spservice.securepaperappliance.land.it", type = JAXBElement.class, required = false)
    protected JAXBElement<String> serviceId;
    @XmlElementRef(name = "inXml", namespace = "http://spservice.securepaperappliance.land.it", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> inXml;
    @XmlElementRef(name = "closingDocumentXml", namespace = "http://spservice.securepaperappliance.land.it", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> closingDocumentXml;
    @XmlElement(nillable = true)
    protected List<SignerBean> signers;

    /**
     * Recupera il valore della proprietà serviceId.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public JAXBElement<String> getServiceId() {
        return serviceId;
    }

    /**
     * Imposta il valore della proprietà serviceId.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link String }{@code >}
     *     
     */
    public void setServiceId(JAXBElement<String> value) {
        this.serviceId = value;
    }

    /**
     * Recupera il valore della proprietà inXml.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getInXml() {
        return inXml;
    }

    /**
     * Imposta il valore della proprietà inXml.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setInXml(JAXBElement<byte[]> value) {
        this.inXml = value;
    }

    /**
     * Recupera il valore della proprietà closingDocumentXml.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getClosingDocumentXml() {
        return closingDocumentXml;
    }

    /**
     * Imposta il valore della proprietà closingDocumentXml.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setClosingDocumentXml(JAXBElement<byte[]> value) {
        this.closingDocumentXml = value;
    }

    /**
     * Gets the value of the signers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the signers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSigners().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SignerBean }
     * 
     * 
     */
    public List<SignerBean> getSigners() {
        if (signers == null) {
            signers = new ArrayList<SignerBean>();
        }
        return this.signers;
    }

}
