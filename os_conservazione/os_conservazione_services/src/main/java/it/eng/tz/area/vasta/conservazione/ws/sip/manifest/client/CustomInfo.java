//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Metadati espressi secondo un tracciato "custom" corrispondente ad uno schema XML registrato nel sistema di conservazione
 * 
 * <p>Classe Java per CustomInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="CustomInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="XMLSchema" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="EmbeddedMetadata" type="{urn:IRIS:SIPManifest.xsd}EmbeddedMetadata"/&gt;
 *           &lt;element name="ExternalMetadata" type="{urn:IRIS:SIPManifest.xsd}File"/&gt;
 *         &lt;/choice&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomInfo", propOrder = {
    "xmlSchema",
    "embeddedMetadata",
    "externalMetadata"
})
public class CustomInfo {

    @XmlElement(name = "XMLSchema")
    protected XMLSchema xmlSchema;
    @XmlElement(name = "EmbeddedMetadata")
    protected EmbeddedMetadata embeddedMetadata;
    @XmlElement(name = "ExternalMetadata")
    protected File externalMetadata;

    /**
     * Recupera il valore della proprietà xmlSchema.
     * 
     * @return
     *     possible object is
     *     {@link XMLSchema }
     *     
     */
    public XMLSchema getXMLSchema() {
        return xmlSchema;
    }

    /**
     * Imposta il valore della proprietà xmlSchema.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLSchema }
     *     
     */
    public void setXMLSchema(XMLSchema value) {
        this.xmlSchema = value;
    }

    /**
     * Recupera il valore della proprietà embeddedMetadata.
     * 
     * @return
     *     possible object is
     *     {@link EmbeddedMetadata }
     *     
     */
    public EmbeddedMetadata getEmbeddedMetadata() {
        return embeddedMetadata;
    }

    /**
     * Imposta il valore della proprietà embeddedMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link EmbeddedMetadata }
     *     
     */
    public void setEmbeddedMetadata(EmbeddedMetadata value) {
        this.embeddedMetadata = value;
    }

    /**
     * Recupera il valore della proprietà externalMetadata.
     * 
     * @return
     *     possible object is
     *     {@link File }
     *     
     */
    public File getExternalMetadata() {
        return externalMetadata;
    }

    /**
     * Imposta il valore della proprietà externalMetadata.
     * 
     * @param value
     *     allowed object is
     *     {@link File }
     *     
     */
    public void setExternalMetadata(File value) {
        this.externalMetadata = value;
    }

}
