//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 *  Contiente puntamenti e impronte di tutti i componenti digitali di un documento digitale nonchè le reciproche relazioni
 * 
 * <p>Classe Java per ComponentiDigitaliDoc complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComponentiDigitaliDoc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="FilePrincipale" type="{urn:IRIS:SIPManifest.xsd}File"/&gt;
 *         &lt;element name="TimeRef" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;urn:IRIS:SIPManifest.xsd&gt;TimeRef"&gt;
 *                 &lt;attribute name="Type" use="required"&gt;
 *                   &lt;simpleType&gt;
 *                     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *                       &lt;enumeration value="PG"/&gt;
 *                       &lt;enumeration value="PP"/&gt;
 *                       &lt;enumeration value="REP"/&gt;
 *                       &lt;enumeration value="PEC"/&gt;
 *                       &lt;enumeration value="RPEC"/&gt;
 *                     &lt;/restriction&gt;
 *                   &lt;/simpleType&gt;
 *                 &lt;/attribute&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ComponentiDigitaliDetached" type="{urn:IRIS:SIPManifest.xsd}ComponentiDigitaliDetached" minOccurs="0"/&gt;
 *         &lt;element name="ComponentiDigitaliCorredo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="ComponenteDigitaleCorredo" type="{urn:IRIS:SIPManifest.xsd}ComponenteDigitaleCorredo" maxOccurs="unbounded"/&gt;
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
@XmlType(name = "ComponentiDigitaliDoc", propOrder = {
    "filePrincipale",
    "timeRef",
    "componentiDigitaliDetached",
    "componentiDigitaliCorredo"
})
public class ComponentiDigitaliDoc {

    @XmlElement(name = "FilePrincipale", required = true)
    protected File filePrincipale;
    @XmlElement(name = "TimeRef")
    protected ComponentiDigitaliDoc.TimeRef timeRef;
    @XmlElement(name = "ComponentiDigitaliDetached")
    protected ComponentiDigitaliDetached componentiDigitaliDetached;
    @XmlElement(name = "ComponentiDigitaliCorredo")
    protected ComponentiDigitaliDoc.ComponentiDigitaliCorredo componentiDigitaliCorredo;

    /**
     * Recupera il valore della proprietà filePrincipale.
     * 
     * @return
     *     possible object is
     *     {@link File }
     *     
     */
    public File getFilePrincipale() {
        return filePrincipale;
    }

    /**
     * Imposta il valore della proprietà filePrincipale.
     * 
     * @param value
     *     allowed object is
     *     {@link File }
     *     
     */
    public void setFilePrincipale(File value) {
        this.filePrincipale = value;
    }

    /**
     * Recupera il valore della proprietà timeRef.
     * 
     * @return
     *     possible object is
     *     {@link ComponentiDigitaliDoc.TimeRef }
     *     
     */
    public ComponentiDigitaliDoc.TimeRef getTimeRef() {
        return timeRef;
    }

    /**
     * Imposta il valore della proprietà timeRef.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentiDigitaliDoc.TimeRef }
     *     
     */
    public void setTimeRef(ComponentiDigitaliDoc.TimeRef value) {
        this.timeRef = value;
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
     * Recupera il valore della proprietà componentiDigitaliCorredo.
     * 
     * @return
     *     possible object is
     *     {@link ComponentiDigitaliDoc.ComponentiDigitaliCorredo }
     *     
     */
    public ComponentiDigitaliDoc.ComponentiDigitaliCorredo getComponentiDigitaliCorredo() {
        return componentiDigitaliCorredo;
    }

    /**
     * Imposta il valore della proprietà componentiDigitaliCorredo.
     * 
     * @param value
     *     allowed object is
     *     {@link ComponentiDigitaliDoc.ComponentiDigitaliCorredo }
     *     
     */
    public void setComponentiDigitaliCorredo(ComponentiDigitaliDoc.ComponentiDigitaliCorredo value) {
        this.componentiDigitaliCorredo = value;
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
     *         &lt;element name="ComponenteDigitaleCorredo" type="{urn:IRIS:SIPManifest.xsd}ComponenteDigitaleCorredo" maxOccurs="unbounded"/&gt;
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
        "componenteDigitaleCorredo"
    })
    public static class ComponentiDigitaliCorredo {

        @XmlElement(name = "ComponenteDigitaleCorredo", required = true)
        protected List<ComponenteDigitaleCorredo> componenteDigitaleCorredo;

        /**
         * Gets the value of the componenteDigitaleCorredo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the componenteDigitaleCorredo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getComponenteDigitaleCorredo().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ComponenteDigitaleCorredo }
         * 
         * 
         */
        public List<ComponenteDigitaleCorredo> getComponenteDigitaleCorredo() {
            if (componenteDigitaleCorredo == null) {
                componenteDigitaleCorredo = new ArrayList<ComponenteDigitaleCorredo>();
            }
            return this.componenteDigitaleCorredo;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;urn:IRIS:SIPManifest.xsd&gt;TimeRef"&gt;
     *       &lt;attribute name="Type" use="required"&gt;
     *         &lt;simpleType&gt;
     *           &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
     *             &lt;enumeration value="PG"/&gt;
     *             &lt;enumeration value="PP"/&gt;
     *             &lt;enumeration value="REP"/&gt;
     *             &lt;enumeration value="PEC"/&gt;
     *             &lt;enumeration value="RPEC"/&gt;
     *           &lt;/restriction&gt;
     *         &lt;/simpleType&gt;
     *       &lt;/attribute&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class TimeRef {

        @XmlValue
        protected XMLGregorianCalendar value;
        @XmlAttribute(name = "Type", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
        protected String type;

        /**
         * Riferimento temporale con data e ora
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getValue() {
            return value;
        }

        /**
         * Imposta il valore della proprietà value.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setValue(XMLGregorianCalendar value) {
            this.value = value;
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

}
