//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene esito e dettagli dell'elaborazione di un item del SIP
 * 
 * <p>Classe Java per Item complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Item"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Esito"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Errore" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                   &lt;element name="Warning" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" maxOccurs="unbounded" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabItemDoc" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Doc" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Esito"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Errore" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
 *                                     &lt;sequence minOccurs="0"&gt;
 *                                       &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/extension&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                             &lt;element name="Warning" maxOccurs="unbounded" minOccurs="0"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
 *                                     &lt;sequence minOccurs="0"&gt;
 *                                       &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/extension&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabItemDoc" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="RelVsSD" use="required" type="{urn:IRIS:SIPResult.xsd}RelVsSD" /&gt;
 *                 &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPResult.xsd}ProvId" /&gt;
 *                 &lt;attribute name="Op" use="required" type="{urn:IRIS:SIPResult.xsd}SIPItemOper" /&gt;
 *                 &lt;attribute name="NroAllegato" type="{urn:IRIS:SIPResult.xsd}NroAllegato" /&gt;
 *                 &lt;attribute name="RegId" type="{urn:IRIS:SIPResult.xsd}UUID" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPResult.xsd}ProvId" /&gt;
 *       &lt;attribute name="type" use="required" type="{urn:IRIS:SIPResult.xsd}ItemType" /&gt;
 *       &lt;attribute name="Op" use="required" type="{urn:IRIS:SIPResult.xsd}SIPItemOper" /&gt;
 *       &lt;attribute name="RegId" type="{urn:IRIS:SIPResult.xsd}UUID" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Item", propOrder = {
    "esito",
    "doc"
})
public class Item {

    @XmlElement(name = "Esito", required = true)
    protected Item.Esito esito;
    @XmlElement(name = "Doc")
    protected List<Item.Doc> doc;
    @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    protected String id;
    @XmlAttribute(name = "type", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    protected ItemType type;
    @XmlAttribute(name = "Op", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    protected SIPItemOper op;
    @XmlAttribute(name = "RegId", namespace = "urn:IRIS:SIPResult.xsd")
    protected String regId;

    /**
     * Recupera il valore della proprietà esito.
     * 
     * @return
     *     possible object is
     *     {@link Item.Esito }
     *     
     */
    public Item.Esito getEsito() {
        return esito;
    }

    /**
     * Imposta il valore della proprietà esito.
     * 
     * @param value
     *     allowed object is
     *     {@link Item.Esito }
     *     
     */
    public void setEsito(Item.Esito value) {
        this.esito = value;
    }

    /**
     * Gets the value of the doc property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the doc property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDoc().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Item.Doc }
     * 
     * 
     */
    public List<Item.Doc> getDoc() {
        if (doc == null) {
            doc = new ArrayList<Item.Doc>();
        }
        return this.doc;
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
        return op;
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

    /**
     * Recupera il valore della proprietà regId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegId() {
        return regId;
    }

    /**
     * Imposta il valore della proprietà regId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegId(String value) {
        this.regId = value;
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
     *         &lt;element name="Esito"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Errore" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
     *                           &lt;sequence minOccurs="0"&gt;
     *                             &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/extension&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                   &lt;element name="Warning" maxOccurs="unbounded" minOccurs="0"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
     *                           &lt;sequence minOccurs="0"&gt;
     *                             &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
     *                           &lt;/sequence&gt;
     *                         &lt;/extension&gt;
     *                       &lt;/complexContent&gt;
     *                     &lt;/complexType&gt;
     *                   &lt;/element&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabItemDoc" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="RelVsSD" use="required" type="{urn:IRIS:SIPResult.xsd}RelVsSD" /&gt;
     *       &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPResult.xsd}ProvId" /&gt;
     *       &lt;attribute name="Op" use="required" type="{urn:IRIS:SIPResult.xsd}SIPItemOper" /&gt;
     *       &lt;attribute name="NroAllegato" type="{urn:IRIS:SIPResult.xsd}NroAllegato" /&gt;
     *       &lt;attribute name="RegId" type="{urn:IRIS:SIPResult.xsd}UUID" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "esito"
    })
    public static class Doc {

        @XmlElement(name = "Esito", required = true)
        protected Item.Doc.Esito esito;
        @XmlAttribute(name = "RelVsSD", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected RelVsSD relVsSD;
        @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected String id;
        @XmlAttribute(name = "Op", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected SIPItemOper op;
        @XmlAttribute(name = "NroAllegato", namespace = "urn:IRIS:SIPResult.xsd")
        protected BigInteger nroAllegato;
        @XmlAttribute(name = "RegId", namespace = "urn:IRIS:SIPResult.xsd")
        protected String regId;

        /**
         * Recupera il valore della proprietà esito.
         * 
         * @return
         *     possible object is
         *     {@link Item.Doc.Esito }
         *     
         */
        public Item.Doc.Esito getEsito() {
            return esito;
        }

        /**
         * Imposta il valore della proprietà esito.
         * 
         * @param value
         *     allowed object is
         *     {@link Item.Doc.Esito }
         *     
         */
        public void setEsito(Item.Doc.Esito value) {
            this.esito = value;
        }

        /**
         * Recupera il valore della proprietà relVsSD.
         * 
         * @return
         *     possible object is
         *     {@link RelVsSD }
         *     
         */
        public RelVsSD getRelVsSD() {
            return relVsSD;
        }

        /**
         * Imposta il valore della proprietà relVsSD.
         * 
         * @param value
         *     allowed object is
         *     {@link RelVsSD }
         *     
         */
        public void setRelVsSD(RelVsSD value) {
            this.relVsSD = value;
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
         * Recupera il valore della proprietà op.
         * 
         * @return
         *     possible object is
         *     {@link SIPItemOper }
         *     
         */
        public SIPItemOper getOp() {
            return op;
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

        /**
         * Recupera il valore della proprietà nroAllegato.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNroAllegato() {
            return nroAllegato;
        }

        /**
         * Imposta il valore della proprietà nroAllegato.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNroAllegato(BigInteger value) {
            this.nroAllegato = value;
        }

        /**
         * Recupera il valore della proprietà regId.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRegId() {
            return regId;
        }

        /**
         * Imposta il valore della proprietà regId.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRegId(String value) {
            this.regId = value;
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
         *         &lt;element name="Errore" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
         *                 &lt;sequence minOccurs="0"&gt;
         *                   &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/extension&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *         &lt;element name="Warning" maxOccurs="unbounded" minOccurs="0"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
         *                 &lt;sequence minOccurs="0"&gt;
         *                   &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
         *                 &lt;/sequence&gt;
         *               &lt;/extension&gt;
         *             &lt;/complexContent&gt;
         *           &lt;/complexType&gt;
         *         &lt;/element&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabItemDoc" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "errore",
            "warning"
        })
        public static class Esito {

            @XmlElement(name = "Errore")
            protected List<Item.Doc.Esito.Errore> errore;
            @XmlElement(name = "Warning")
            protected List<Item.Doc.Esito.Warning> warning;
            @XmlAttribute(name = "Codice", namespace = "urn:IRIS:SIPResult.xsd", required = true)
            protected CodiceEsitoElabItemDoc codice;

            /**
             * Gets the value of the errore property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the errore property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getErrore().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Item.Doc.Esito.Errore }
             * 
             * 
             */
            public List<Item.Doc.Esito.Errore> getErrore() {
                if (errore == null) {
                    errore = new ArrayList<Item.Doc.Esito.Errore>();
                }
                return this.errore;
            }

            /**
             * Gets the value of the warning property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the warning property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getWarning().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Item.Doc.Esito.Warning }
             * 
             * 
             */
            public List<Item.Doc.Esito.Warning> getWarning() {
                if (warning == null) {
                    warning = new ArrayList<Item.Doc.Esito.Warning>();
                }
                return this.warning;
            }

            /**
             * Recupera il valore della proprietà codice.
             * 
             * @return
             *     possible object is
             *     {@link CodiceEsitoElabItemDoc }
             *     
             */
            public CodiceEsitoElabItemDoc getCodice() {
                return codice;
            }

            /**
             * Imposta il valore della proprietà codice.
             * 
             * @param value
             *     allowed object is
             *     {@link CodiceEsitoElabItemDoc }
             *     
             */
            public void setCodice(CodiceEsitoElabItemDoc value) {
                this.codice = value;
            }


            /**
             * <p>Classe Java per anonymous complex type.
             * 
             * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
             * 
             * <pre>
             * &lt;complexType&gt;
             *   &lt;complexContent&gt;
             *     &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
             *       &lt;sequence minOccurs="0"&gt;
             *         &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/extension&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "suFile"
            })
            public static class Errore
                extends ErroreWarning
            {

                @XmlElement(name = "SuFile")
                protected File suFile;

                /**
                 * Recupera il valore della proprietà suFile.
                 * 
                 * @return
                 *     possible object is
                 *     {@link File }
                 *     
                 */
                public File getSuFile() {
                    return suFile;
                }

                /**
                 * Imposta il valore della proprietà suFile.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link File }
                 *     
                 */
                public void setSuFile(File value) {
                    this.suFile = value;
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
             *     &lt;extension base="{urn:IRIS:SIPResult.xsd}ErroreWarning"&gt;
             *       &lt;sequence minOccurs="0"&gt;
             *         &lt;element name="SuFile" type="{urn:IRIS:SIPResult.xsd}File" minOccurs="0"/&gt;
             *       &lt;/sequence&gt;
             *     &lt;/extension&gt;
             *   &lt;/complexContent&gt;
             * &lt;/complexType&gt;
             * </pre>
             * 
             * 
             */
            @XmlAccessorType(XmlAccessType.FIELD)
            @XmlType(name = "", propOrder = {
                "suFile"
            })
            public static class Warning
                extends ErroreWarning
            {

                @XmlElement(name = "SuFile")
                protected File suFile;

                /**
                 * Recupera il valore della proprietà suFile.
                 * 
                 * @return
                 *     possible object is
                 *     {@link File }
                 *     
                 */
                public File getSuFile() {
                    return suFile;
                }

                /**
                 * Imposta il valore della proprietà suFile.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link File }
                 *     
                 */
                public void setSuFile(File value) {
                    this.suFile = value;
                }

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
     *         &lt;element name="Errore" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" maxOccurs="unbounded" minOccurs="0"/&gt;
     *         &lt;element name="Warning" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" maxOccurs="unbounded" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabItemDoc" /&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "errore",
        "warning"
    })
    public static class Esito {

        @XmlElement(name = "Errore")
        protected List<ErroreWarning> errore;
        @XmlElement(name = "Warning")
        protected List<ErroreWarning> warning;
        @XmlAttribute(name = "Codice", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected CodiceEsitoElabItemDoc codice;

        /**
         * Gets the value of the errore property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the errore property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getErrore().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ErroreWarning }
         * 
         * 
         */
        public List<ErroreWarning> getErrore() {
            if (errore == null) {
                errore = new ArrayList<ErroreWarning>();
            }
            return this.errore;
        }

        /**
         * Gets the value of the warning property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the warning property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getWarning().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ErroreWarning }
         * 
         * 
         */
        public List<ErroreWarning> getWarning() {
            if (warning == null) {
                warning = new ArrayList<ErroreWarning>();
            }
            return this.warning;
        }

        /**
         * Recupera il valore della proprietà codice.
         * 
         * @return
         *     possible object is
         *     {@link CodiceEsitoElabItemDoc }
         *     
         */
        public CodiceEsitoElabItemDoc getCodice() {
            return codice;
        }

        /**
         * Imposta il valore della proprietà codice.
         * 
         * @param value
         *     allowed object is
         *     {@link CodiceEsitoElabItemDoc }
         *     
         */
        public void setCodice(CodiceEsitoElabItemDoc value) {
            this.codice = value;
        }

    }

}
