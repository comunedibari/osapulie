//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Rappresenta un componente digitale (i.e. file)  che è una firme o una marca temporale detached o un Indice di Conservazione (fatto da sistema di conservazione diverso dal SCN)
 * 
 * <p>Classe Java per ComponenteDigitaleDetached complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComponenteDigitaleDetached"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="File" type="{urn:IRIS:SIPManifest.xsd}File"/&gt;
 *         &lt;element name="ComponentiDigitaliDetached" type="{urn:IRIS:SIPManifest.xsd}ComponentiDigitaliDetached" minOccurs="0"/&gt;
 *         &lt;element name="CRL" type="{urn:IRIS:SIPManifest.xsd}File" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="type" use="required"&gt;
 *         &lt;simpleType&gt;
 *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;enumeration value="F"/&gt;
 *             &lt;enumeration value="M"/&gt;
 *             &lt;enumeration value="IdC"/&gt;
 *           &lt;/restriction&gt;
 *         &lt;/simpleType&gt;
 *       &lt;/attribute&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponenteDigitaleDetached", propOrder = {
    "file",
    "componentiDigitaliDetached",
    "crl"
})
public class ComponenteDigitaleDetached {

    @XmlElement(name = "File", required = true)
    protected File file;
    @XmlElement(name = "ComponentiDigitaliDetached")
    protected ComponentiDigitaliDetached componentiDigitaliDetached;
    @XmlElement(name = "CRL")
    protected File crl;
    @XmlAttribute(name = "type", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    protected String type;

    /**
     * Recupera il valore della proprietà file.
     * 
     * @return
     *     possible object is
     *     {@link File }
     *     
     */
    public File getFile() {
        return file;
    }

    /**
     * Imposta il valore della proprietà file.
     * 
     * @param value
     *     allowed object is
     *     {@link File }
     *     
     */
    public void setFile(File value) {
        this.file = value;
    }

    /**
     * Recupera il valore della proprietà componentiDigitaliDetached.
     * 
     * @return
     *     possible object is
     *     {@link ComponentiDigitaliDetached }
     *     
     */
    public ComponentiDigitaliDetached getComponentiDigitaliDetached() {
        return componentiDigitaliDetached;
    }

    /**
     * Imposta il valore della proprietà componentiDigitaliDetached.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentiDigitaliDetached }
     *     
     */
    public void setComponentiDigitaliDetached(ComponentiDigitaliDetached value) {
        this.componentiDigitaliDetached = value;
    }

    /**
     * Recupera il valore della proprietà crl.
     * 
     * @return
     *     possible object is
     *     {@link File }
     *     
     */
    public File getCRL() {
        return crl;
    }

    /**
     * Imposta il valore della proprietà crl.
     * 
     * @param value
     *     allowed object is
     *     {@link File }
     *     
     */
    public void setCRL(File value) {
        this.crl = value;
    }

    /**
     * Recupera il valore della proprietà type.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getType() {
        return type;
    }

    /**
     * Imposta il valore della proprietà type.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setType(String value) {
        this.type = value;
    }

}
