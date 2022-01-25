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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="SelfDescription" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Produttore" type="{urn:IRIS:SIPManifest.xsd}ProduttoreSIP"/&gt;
 *                   &lt;element name="CreatoDaApplicazione" type="{urn:IRIS:SIPManifest.xsd}Applicazione"/&gt;
 *                   &lt;element name="TsGenerazione" type="{urn:IRIS:SIPManifest.xsd}TimeRef"/&gt;
 *                   &lt;element name="Label" type="{urn:IRIS:SIPManifest.xsd}Label"/&gt;
 *                   &lt;element name="Descrizione" type="{urn:IRIS:SIPManifest.xsd}ShortDescription" minOccurs="0"/&gt;
 *                   &lt;element name="MoreInfo" type="{urn:IRIS:SIPManifest.xsd}CustomInfo" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Contenuti"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="CustomInfoSchemas" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="UA" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
 *                             &lt;element name="SD" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
 *                             &lt;element name="DigDoc" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
 *                             &lt;element name="UC" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="Item" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence minOccurs="0"&gt;
 *                             &lt;choice minOccurs="0"&gt;
 *                               &lt;element name="UAStdInfo" type="{urn:IRIS:SIPManifest.xsd}UAStdInfo"/&gt;
 *                               &lt;element name="SDStdInfo" type="{urn:IRIS:SIPManifest.xsd}SDStdInfo"/&gt;
 *                               &lt;element name="UCStdInfo" type="{urn:IRIS:SIPManifest.xsd}UCStdInfo"/&gt;
 *                             &lt;/choice&gt;
 *                             &lt;element name="CustomInfo" type="{urn:IRIS:SIPManifest.xsd}CustomInfo" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
 *                           &lt;attribute name="type" use="required" type="{urn:IRIS:SIPManifest.xsd}ItemType" /&gt;
 *                           &lt;attribute name="Op" type="{urn:IRIS:SIPManifest.xsd}SIPItemOper" default="I" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{urn:IRIS:SIPManifest.xsd}SIPver"/&gt;
 *       &lt;attribute ref="{urn:IRIS:SIPManifest.xsd}SIPurl"/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "selfDescription",
    "contenuti"
})
@XmlRootElement(name = "SIP")
public class SIP {

    @XmlElement(name = "SelfDescription")
    protected SIP.SelfDescription selfDescription;
    @XmlElement(name = "Contenuti", required = true)
    protected SIP.Contenuti contenuti;
    @XmlAttribute(name = "SIPver", namespace = "urn:IRIS:SIPManifest.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String siPver;
    @XmlAttribute(name = "SIPurl", namespace = "urn:IRIS:SIPManifest.xsd")
    @XmlSchemaType(name = "anyURI")
    protected String siPurl;

    /**
     * Recupera il valore della proprietà selfDescription.
     * 
     * @return
     *     possible object is
     *     {@link SIP.SelfDescription }
     *     
     */
    public SIP.SelfDescription getSelfDescription() {
        return selfDescription;
    }

    /**
     * Imposta il valore della proprietà selfDescription.
     * 
     * @param value
     *     allowed object is
     *     {@link SIP.SelfDescription }
     *     
     */
    public void setSelfDescription(SIP.SelfDescription value) {
        this.selfDescription = value;
    }

    /**
     * Recupera il valore della proprietà contenuti.
     * 
     * @return
     *     possible object is
     *     {@link SIP.Contenuti }
     *     
     */
    public SIP.Contenuti getContenuti() {
        return contenuti;
    }

    /**
     * Imposta il valore della proprietà contenuti.
     * 
     * @param value
     *     allowed object is
     *     {@link SIP.Contenuti }
     *     
     */
    public void setContenuti(SIP.Contenuti value) {
        this.contenuti = value;
    }

    /**
     * Recupera il valore della proprietà siPver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIPver() {
        if (siPver == null) {
            return "1.0";
        } else {
            return siPver;
        }
    }

    /**
     * Imposta il valore della proprietà siPver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIPver(String value) {
        this.siPver = value;
    }

    /**
     * Recupera il valore della proprietà siPurl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIPurl() {
        if (siPurl == null) {
            return "http:/eng.it/IRIS/";
        } else {
            return siPurl;
        }
    }

    /**
     * Imposta il valore della proprietà siPurl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIPurl(String value) {
        this.siPurl = value;
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
     *         &lt;element name="CustomInfoSchemas" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="UA" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
     *                   &lt;element name="SD" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
     *                   &lt;element name="DigDoc" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
     *                   &lt;element name="UC" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="Item" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence minOccurs="0"&gt;
     *                   &lt;choice minOccurs="0"&gt;
     *                     &lt;element name="UAStdInfo" type="{urn:IRIS:SIPManifest.xsd}UAStdInfo"/&gt;
     *                     &lt;element name="SDStdInfo" type="{urn:IRIS:SIPManifest.xsd}SDStdInfo"/&gt;
     *                     &lt;element name="UCStdInfo" type="{urn:IRIS:SIPManifest.xsd}UCStdInfo"/&gt;
     *                   &lt;/choice&gt;
     *                   &lt;element name="CustomInfo" type="{urn:IRIS:SIPManifest.xsd}CustomInfo" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
     *                 &lt;attribute name="type" use="required" type="{urn:IRIS:SIPManifest.xsd}ItemType" /&gt;
     *                 &lt;attribute name="Op" type="{urn:IRIS:SIPManifest.xsd}SIPItemOper" default="I" /&gt;
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
    @XmlType(name = "", propOrder = {
        "customInfoSchemas",
        "item"
    })
    public static class Contenuti {

        @XmlElement(name = "CustomInfoSchemas")
        protected SIP.Contenuti.CustomInfoSchemas customInfoSchemas;
        @XmlElement(name = "Item", required = true)
        protected List<SIP.Contenuti.Item> item;

        /**
         * Recupera il valore della proprietà customInfoSchemas.
         * 
         * @return
         *     possible object is
         *     {@link SIP.Contenuti.CustomInfoSchemas }
         *     
         */
        public SIP.Contenuti.CustomInfoSchemas getCustomInfoSchemas() {
            return customInfoSchemas;
        }

        /**
         * Imposta il valore della proprietà customInfoSchemas.
         * 
         * @param value
         *     allowed object is
         *     {@link SIP.Contenuti.CustomInfoSchemas }
         *     
         */
        public void setCustomInfoSchemas(SIP.Contenuti.CustomInfoSchemas value) {
            this.customInfoSchemas = value;
        }

        /**
         * Gets the value of the item property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the item property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getItem().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SIP.Contenuti.Item }
         * 
         * 
         */
        public List<SIP.Contenuti.Item> getItem() {
            if (item == null) {
                item = new ArrayList<SIP.Contenuti.Item>();
            }
            return this.item;
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
         *         &lt;element name="UA" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
         *         &lt;element name="SD" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
         *         &lt;element name="DigDoc" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
         *         &lt;element name="UC" type="{urn:IRIS:SIPManifest.xsd}XMLSchema" minOccurs="0"/&gt;
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
            "ua",
            "sd",
            "digDoc",
            "uc"
        })
        public static class CustomInfoSchemas {

            @XmlElement(name = "UA")
            protected XMLSchema ua;
            @XmlElement(name = "SD")
            protected XMLSchema sd;
            @XmlElement(name = "DigDoc")
            protected XMLSchema digDoc;
            @XmlElement(name = "UC")
            protected XMLSchema uc;

            /**
             * Recupera il valore della proprietà ua.
             * 
             * @return
             *     possible object is
             *     {@link XMLSchema }
             *     
             */
            public XMLSchema getUA() {
                return ua;
            }

            /**
             * Imposta il valore della proprietà ua.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLSchema }
             *     
             */
            public void setUA(XMLSchema value) {
                this.ua = value;
            }

            /**
             * Recupera il valore della proprietà sd.
             * 
             * @return
             *     possible object is
             *     {@link XMLSchema }
             *     
             */
            public XMLSchema getSD() {
                return sd;
            }

            /**
             * Imposta il valore della proprietà sd.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLSchema }
             *     
             */
            public void setSD(XMLSchema value) {
                this.sd = value;
            }

            /**
             * Recupera il valore della proprietà digDoc.
             * 
             * @return
             *     possible object is
             *     {@link XMLSchema }
             *     
             */
            public XMLSchema getDigDoc() {
                return digDoc;
            }

            /**
             * Imposta il valore della proprietà digDoc.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLSchema }
             *     
             */
            public void setDigDoc(XMLSchema value) {
                this.digDoc = value;
            }

            /**
             * Recupera il valore della proprietà uc.
             * 
             * @return
             *     possible object is
             *     {@link XMLSchema }
             *     
             */
            public XMLSchema getUC() {
                return uc;
            }

            /**
             * Imposta il valore della proprietà uc.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLSchema }
             *     
             */
            public void setUC(XMLSchema value) {
                this.uc = value;
            }

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
         *       &lt;sequence minOccurs="0"&gt;
         *         &lt;choice minOccurs="0"&gt;
         *           &lt;element name="UAStdInfo" type="{urn:IRIS:SIPManifest.xsd}UAStdInfo"/&gt;
         *           &lt;element name="SDStdInfo" type="{urn:IRIS:SIPManifest.xsd}SDStdInfo"/&gt;
         *           &lt;element name="UCStdInfo" type="{urn:IRIS:SIPManifest.xsd}UCStdInfo"/&gt;
         *         &lt;/choice&gt;
         *         &lt;element name="CustomInfo" type="{urn:IRIS:SIPManifest.xsd}CustomInfo" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
         *       &lt;attribute name="type" use="required" type="{urn:IRIS:SIPManifest.xsd}ItemType" /&gt;
         *       &lt;attribute name="Op" type="{urn:IRIS:SIPManifest.xsd}SIPItemOper" default="I" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "uaStdInfo",
            "sdStdInfo",
            "ucStdInfo",
            "customInfo"
        })
        public static class Item {

            @XmlElement(name = "UAStdInfo")
            protected UAStdInfo uaStdInfo;
            @XmlElement(name = "SDStdInfo")
            protected SDStdInfo sdStdInfo;
            @XmlElement(name = "UCStdInfo")
            protected UCStdInfo ucStdInfo;
            @XmlElement(name = "CustomInfo")
            protected CustomInfo customInfo;
            @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
            protected String id;
            @XmlAttribute(name = "type", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
            protected ItemType type;
            @XmlAttribute(name = "Op", namespace = "urn:IRIS:SIPManifest.xsd")
            protected SIPItemOper op;

            /**
             * Recupera il valore della proprietà uaStdInfo.
             * 
             * @return
             *     possible object is
             *     {@link UAStdInfo }
             *     
             */
            public UAStdInfo getUAStdInfo() {
                return uaStdInfo;
            }

            /**
             * Imposta il valore della proprietà uaStdInfo.
             * 
             * @param value
             *     allowed object is
             *     {@link UAStdInfo }
             *     
             */
            public void setUAStdInfo(UAStdInfo value) {
                this.uaStdInfo = value;
            }

            /**
             * Recupera il valore della proprietà sdStdInfo.
             * 
             * @return
             *     possible object is
             *     {@link SDStdInfo }
             *     
             */
            public SDStdInfo getSDStdInfo() {
                return sdStdInfo;
            }

            /**
             * Imposta il valore della proprietà sdStdInfo.
             * 
             * @param value
             *     allowed object is
             *     {@link SDStdInfo }
             *     
             */
            public void setSDStdInfo(SDStdInfo value) {
                this.sdStdInfo = value;
            }

            /**
             * Recupera il valore della proprietà ucStdInfo.
             * 
             * @return
             *     possible object is
             *     {@link UCStdInfo }
             *     
             */
            public UCStdInfo getUCStdInfo() {
                return ucStdInfo;
            }

            /**
             * Imposta il valore della proprietà ucStdInfo.
             * 
             * @param value
             *     allowed object is
             *     {@link UCStdInfo }
             *     
             */
            public void setUCStdInfo(UCStdInfo value) {
                this.ucStdInfo = value;
            }

            /**
             * Recupera il valore della proprietà customInfo.
             * 
             * @return
             *     possible object is
             *     {@link CustomInfo }
             *     
             */
            public CustomInfo getCustomInfo() {
                return customInfo;
            }

            /**
             * Imposta il valore della proprietà customInfo.
             * 
             * @param value
             *     allowed object is
             *     {@link CustomInfo }
             *     
             */
            public void setCustomInfo(CustomInfo value) {
                this.customInfo = value;
            }

            /**
             * Recupera il valore della proprietà id.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getId() {
                return id;
            }

            /**
             * Imposta il valore della proprietà id.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setId(String value) {
                this.id = value;
            }

            /**
             * Recupera il valore della proprietà type.
             * 
             * @return
             *     possible object is
             *     {@link ItemType }
             *     
             */
            public ItemType getType() {
                return type;
            }

            /**
             * Imposta il valore della proprietà type.
             * 
             * @param value
             *     allowed object is
             *     {@link ItemType }
             *     
             */
            public void setType(ItemType value) {
                this.type = value;
            }

            /**
             * Recupera il valore della proprietà op.
             * 
             * @return
             *     possible object is
             *     {@link SIPItemOper }
             *     
             */
            public SIPItemOper getOp() {
                if (op == null) {
                    return SIPItemOper.I;
                } else {
                    return op;
                }
            }

            /**
             * Imposta il valore della proprietà op.
             * 
             * @param value
             *     allowed object is
             *     {@link SIPItemOper }
             *     
             */
            public void setOp(SIPItemOper value) {
                this.op = value;
            }

        }

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
     *         &lt;element name="Produttore" type="{urn:IRIS:SIPManifest.xsd}ProduttoreSIP"/&gt;
     *         &lt;element name="CreatoDaApplicazione" type="{urn:IRIS:SIPManifest.xsd}Applicazione"/&gt;
     *         &lt;element name="TsGenerazione" type="{urn:IRIS:SIPManifest.xsd}TimeRef"/&gt;
     *         &lt;element name="Label" type="{urn:IRIS:SIPManifest.xsd}Label"/&gt;
     *         &lt;element name="Descrizione" type="{urn:IRIS:SIPManifest.xsd}ShortDescription" minOccurs="0"/&gt;
     *         &lt;element name="MoreInfo" type="{urn:IRIS:SIPManifest.xsd}CustomInfo" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "produttore",
        "creatoDaApplicazione",
        "tsGenerazione",
        "label",
        "descrizione",
        "moreInfo"
    })
    public static class SelfDescription {

        @XmlElement(name = "Produttore", required = true)
        protected ProduttoreSIP produttore;
        @XmlElement(name = "CreatoDaApplicazione", required = true)
        protected Applicazione creatoDaApplicazione;
        @XmlElement(name = "TsGenerazione", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsGenerazione;
        @XmlElement(name = "Label", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String label;
        @XmlElement(name = "Descrizione")
        protected ShortDescription descrizione;
        @XmlElement(name = "MoreInfo")
        protected CustomInfo moreInfo;
        @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPManifest.xsd")
        protected String id;

        /**
         * Recupera il valore della proprietà produttore.
         * 
         * @return
         *     possible object is
         *     {@link ProduttoreSIP }
         *     
         */
        public ProduttoreSIP getProduttore() {
            return produttore;
        }

        /**
         * Imposta il valore della proprietà produttore.
         * 
         * @param value
         *     allowed object is
         *     {@link ProduttoreSIP }
         *     
         */
        public void setProduttore(ProduttoreSIP value) {
            this.produttore = value;
        }

        /**
         * Recupera il valore della proprietà creatoDaApplicazione.
         * 
         * @return
         *     possible object is
         *     {@link Applicazione }
         *     
         */
        public Applicazione getCreatoDaApplicazione() {
            return creatoDaApplicazione;
        }

        /**
         * Imposta il valore della proprietà creatoDaApplicazione.
         * 
         * @param value
         *     allowed object is
         *     {@link Applicazione }
         *     
         */
        public void setCreatoDaApplicazione(Applicazione value) {
            this.creatoDaApplicazione = value;
        }

        /**
         * Recupera il valore della proprietà tsGenerazione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTsGenerazione() {
            return tsGenerazione;
        }

        /**
         * Imposta il valore della proprietà tsGenerazione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTsGenerazione(XMLGregorianCalendar value) {
            this.tsGenerazione = value;
        }

        /**
         * Recupera il valore della proprietà label.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLabel() {
            return label;
        }

        /**
         * Imposta il valore della proprietà label.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLabel(String value) {
            this.label = value;
        }

        /**
         * Recupera il valore della proprietà descrizione.
         * 
         * @return
         *     possible object is
         *     {@link ShortDescription }
         *     
         */
        public ShortDescription getDescrizione() {
            return descrizione;
        }

        /**
         * Imposta il valore della proprietà descrizione.
         * 
         * @param value
         *     allowed object is
         *     {@link ShortDescription }
         *     
         */
        public void setDescrizione(ShortDescription value) {
            this.descrizione = value;
        }

        /**
         * Recupera il valore della proprietà moreInfo.
         * 
         * @return
         *     possible object is
         *     {@link CustomInfo }
         *     
         */
        public CustomInfo getMoreInfo() {
            return moreInfo;
        }

        /**
         * Imposta il valore della proprietà moreInfo.
         * 
         * @param value
         *     allowed object is
         *     {@link CustomInfo }
         *     
         */
        public void setMoreInfo(CustomInfo value) {
            this.moreInfo = value;
        }

        /**
         * Recupera il valore della proprietà id.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getId() {
            return id;
        }

        /**
         * Imposta il valore della proprietà id.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setId(String value) {
            this.id = value;
        }

    }

}
