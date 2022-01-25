//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

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
 *         &lt;element name="RifSIP"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Produttore" type="{urn:IRIS:SIPResult.xsd}ProduttoreSIP"/&gt;
 *                   &lt;element name="CreatoDaApplicazione" type="{urn:IRIS:SIPResult.xsd}Applicazione"/&gt;
 *                   &lt;element name="TsGenerazione" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
 *                   &lt;element name="TsVersamento" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
 *                   &lt;element name="Label" type="{urn:IRIS:SIPResult.xsd}Label"/&gt;
 *                   &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}ShortDescription" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *                 &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPResult.xsd}ProvId" /&gt;
 *                 &lt;attribute name="RegId" use="required" type="{urn:IRIS:SIPResult.xsd}UUID" /&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ElaborazioneSIP"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TsInizio" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
 *                   &lt;element name="TsFine" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
 *                   &lt;element name="Esito"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}LongDescription"/&gt;
 *                             &lt;element name="Errore" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                           &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabSIP" /&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="InfoRiepilogo" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="RiepilogoUA" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
 *                             &lt;element name="RepilogoSD" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
 *                             &lt;element name="ReipilogoUC" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                   &lt;element name="InfoDettaglio" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Item" type="{urn:IRIS:SIPResult.xsd}Item" maxOccurs="unbounded"/&gt;
 *                           &lt;/sequence&gt;
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
 *       &lt;attribute ref="{urn:IRIS:SIPResult.xsd}SIPResulturl use="required""/&gt;
 *       &lt;attribute ref="{urn:IRIS:SIPResult.xsd}SIPResultver use="required""/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rifSIP",
    "elaborazioneSIP"
})
@XmlRootElement(name = "SIPResult")
public class SIPResult {

    @XmlElement(name = "RifSIP", required = true)
    protected SIPResult.RifSIP rifSIP;
    @XmlElement(name = "ElaborazioneSIP", required = true)
    protected SIPResult.ElaborazioneSIP elaborazioneSIP;
    @XmlAttribute(name = "SIPResulturl", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    @XmlSchemaType(name = "anyURI")
    protected String sipResulturl;
    @XmlAttribute(name = "SIPResultver", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String sipResultver;

    /**
     * Recupera il valore della proprietà rifSIP.
     * 
     * @return
     *     possible object is
     *     {@link SIPResult.RifSIP }
     *     
     */
    public SIPResult.RifSIP getRifSIP() {
        return rifSIP;
    }

    /**
     * Imposta il valore della proprietà rifSIP.
     * 
     * @param value
     *     allowed object is
     *     {@link SIPResult.RifSIP }
     *     
     */
    public void setRifSIP(SIPResult.RifSIP value) {
        this.rifSIP = value;
    }

    /**
     * Recupera il valore della proprietà elaborazioneSIP.
     * 
     * @return
     *     possible object is
     *     {@link SIPResult.ElaborazioneSIP }
     *     
     */
    public SIPResult.ElaborazioneSIP getElaborazioneSIP() {
        return elaborazioneSIP;
    }

    /**
     * Imposta il valore della proprietà elaborazioneSIP.
     * 
     * @param value
     *     allowed object is
     *     {@link SIPResult.ElaborazioneSIP }
     *     
     */
    public void setElaborazioneSIP(SIPResult.ElaborazioneSIP value) {
        this.elaborazioneSIP = value;
    }

    /**
     * Recupera il valore della proprietà sipResulturl.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIPResulturl() {
        if (sipResulturl == null) {
            return "http:/eng.it/IRIS/";
        } else {
            return sipResulturl;
        }
    }

    /**
     * Imposta il valore della proprietà sipResulturl.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIPResulturl(String value) {
        this.sipResulturl = value;
    }

    /**
     * Recupera il valore della proprietà sipResultver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSIPResultver() {
        if (sipResultver == null) {
            return "1.0";
        } else {
            return sipResultver;
        }
    }

    /**
     * Imposta il valore della proprietà sipResultver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSIPResultver(String value) {
        this.sipResultver = value;
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
     *         &lt;element name="TsInizio" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
     *         &lt;element name="TsFine" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
     *         &lt;element name="Esito"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}LongDescription"/&gt;
     *                   &lt;element name="Errore" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *                 &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabSIP" /&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="InfoRiepilogo" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="RiepilogoUA" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
     *                   &lt;element name="RepilogoSD" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
     *                   &lt;element name="ReipilogoUC" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *         &lt;element name="InfoDettaglio" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Item" type="{urn:IRIS:SIPResult.xsd}Item" maxOccurs="unbounded"/&gt;
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
    @XmlType(name = "", propOrder = {
        "tsInizio",
        "tsFine",
        "esito",
        "infoRiepilogo",
        "infoDettaglio"
    })
    public static class ElaborazioneSIP {

        @XmlElement(name = "TsInizio", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsInizio;
        @XmlElement(name = "TsFine", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsFine;
        @XmlElement(name = "Esito", required = true)
        protected SIPResult.ElaborazioneSIP.Esito esito;
        @XmlElement(name = "InfoRiepilogo")
        protected SIPResult.ElaborazioneSIP.InfoRiepilogo infoRiepilogo;
        @XmlElement(name = "InfoDettaglio")
        protected SIPResult.ElaborazioneSIP.InfoDettaglio infoDettaglio;

        /**
         * Recupera il valore della proprietà tsInizio.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTsInizio() {
            return tsInizio;
        }

        /**
         * Imposta il valore della proprietà tsInizio.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTsInizio(XMLGregorianCalendar value) {
            this.tsInizio = value;
        }

        /**
         * Recupera il valore della proprietà tsFine.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTsFine() {
            return tsFine;
        }

        /**
         * Imposta il valore della proprietà tsFine.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTsFine(XMLGregorianCalendar value) {
            this.tsFine = value;
        }

        /**
         * Recupera il valore della proprietà esito.
         * 
         * @return
         *     possible object is
         *     {@link SIPResult.ElaborazioneSIP.Esito }
         *     
         */
        public SIPResult.ElaborazioneSIP.Esito getEsito() {
            return esito;
        }

        /**
         * Imposta il valore della proprietà esito.
         * 
         * @param value
         *     allowed object is
         *     {@link SIPResult.ElaborazioneSIP.Esito }
         *     
         */
        public void setEsito(SIPResult.ElaborazioneSIP.Esito value) {
            this.esito = value;
        }

        /**
         * Recupera il valore della proprietà infoRiepilogo.
         * 
         * @return
         *     possible object is
         *     {@link SIPResult.ElaborazioneSIP.InfoRiepilogo }
         *     
         */
        public SIPResult.ElaborazioneSIP.InfoRiepilogo getInfoRiepilogo() {
            return infoRiepilogo;
        }

        /**
         * Imposta il valore della proprietà infoRiepilogo.
         * 
         * @param value
         *     allowed object is
         *     {@link SIPResult.ElaborazioneSIP.InfoRiepilogo }
         *     
         */
        public void setInfoRiepilogo(SIPResult.ElaborazioneSIP.InfoRiepilogo value) {
            this.infoRiepilogo = value;
        }

        /**
         * Recupera il valore della proprietà infoDettaglio.
         * 
         * @return
         *     possible object is
         *     {@link SIPResult.ElaborazioneSIP.InfoDettaglio }
         *     
         */
        public SIPResult.ElaborazioneSIP.InfoDettaglio getInfoDettaglio() {
            return infoDettaglio;
        }

        /**
         * Imposta il valore della proprietà infoDettaglio.
         * 
         * @param value
         *     allowed object is
         *     {@link SIPResult.ElaborazioneSIP.InfoDettaglio }
         *     
         */
        public void setInfoDettaglio(SIPResult.ElaborazioneSIP.InfoDettaglio value) {
            this.infoDettaglio = value;
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
         *         &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}LongDescription"/&gt;
         *         &lt;element name="Errore" type="{urn:IRIS:SIPResult.xsd}ErroreWarning" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *       &lt;attribute name="Codice" use="required" type="{urn:IRIS:SIPResult.xsd}CodiceEsitoElabSIP" /&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "descrizione",
            "errore"
        })
        public static class Esito {

            @XmlElement(name = "Descrizione", required = true)
            protected LongDescription descrizione;
            @XmlElement(name = "Errore")
            protected ErroreWarning errore;
            @XmlAttribute(name = "Codice", namespace = "urn:IRIS:SIPResult.xsd", required = true)
            protected CodiceEsitoElabSIP codice;

            /**
             * Recupera il valore della proprietà descrizione.
             * 
             * @return
             *     possible object is
             *     {@link LongDescription }
             *     
             */
            public LongDescription getDescrizione() {
                return descrizione;
            }

            /**
             * Imposta il valore della proprietà descrizione.
             * 
             * @param value
             *     allowed object is
             *     {@link LongDescription }
             *     
             */
            public void setDescrizione(LongDescription value) {
                this.descrizione = value;
            }

            /**
             * Recupera il valore della proprietà errore.
             * 
             * @return
             *     possible object is
             *     {@link ErroreWarning }
             *     
             */
            public ErroreWarning getErrore() {
                return errore;
            }

            /**
             * Imposta il valore della proprietà errore.
             * 
             * @param value
             *     allowed object is
             *     {@link ErroreWarning }
             *     
             */
            public void setErrore(ErroreWarning value) {
                this.errore = value;
            }

            /**
             * Recupera il valore della proprietà codice.
             * 
             * @return
             *     possible object is
             *     {@link CodiceEsitoElabSIP }
             *     
             */
            public CodiceEsitoElabSIP getCodice() {
                return codice;
            }

            /**
             * Imposta il valore della proprietà codice.
             * 
             * @param value
             *     allowed object is
             *     {@link CodiceEsitoElabSIP }
             *     
             */
            public void setCodice(CodiceEsitoElabSIP value) {
                this.codice = value;
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
         *         &lt;element name="Item" type="{urn:IRIS:SIPResult.xsd}Item" maxOccurs="unbounded"/&gt;
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
            "item"
        })
        public static class InfoDettaglio {

            @XmlElement(name = "Item", required = true)
            protected List<Item> item;

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
             * {@link Item }
             * 
             * 
             */
            public List<Item> getItem() {
                if (item == null) {
                    item = new ArrayList<Item>();
                }
                return this.item;
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
         *         &lt;element name="RiepilogoUA" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
         *         &lt;element name="RepilogoSD" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
         *         &lt;element name="ReipilogoUC" type="{urn:IRIS:SIPResult.xsd}InfoRiepilogoXTipoItem"/&gt;
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
            "riepilogoUA",
            "repilogoSD",
            "reipilogoUC"
        })
        public static class InfoRiepilogo {

            @XmlElement(name = "RiepilogoUA", required = true)
            protected InfoRiepilogoXTipoItem riepilogoUA;
            @XmlElement(name = "RepilogoSD", required = true)
            protected InfoRiepilogoXTipoItem repilogoSD;
            @XmlElement(name = "ReipilogoUC", required = true)
            protected InfoRiepilogoXTipoItem reipilogoUC;

            /**
             * Recupera il valore della proprietà riepilogoUA.
             * 
             * @return
             *     possible object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public InfoRiepilogoXTipoItem getRiepilogoUA() {
                return riepilogoUA;
            }

            /**
             * Imposta il valore della proprietà riepilogoUA.
             * 
             * @param value
             *     allowed object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public void setRiepilogoUA(InfoRiepilogoXTipoItem value) {
                this.riepilogoUA = value;
            }

            /**
             * Recupera il valore della proprietà repilogoSD.
             * 
             * @return
             *     possible object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public InfoRiepilogoXTipoItem getRepilogoSD() {
                return repilogoSD;
            }

            /**
             * Imposta il valore della proprietà repilogoSD.
             * 
             * @param value
             *     allowed object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public void setRepilogoSD(InfoRiepilogoXTipoItem value) {
                this.repilogoSD = value;
            }

            /**
             * Recupera il valore della proprietà reipilogoUC.
             * 
             * @return
             *     possible object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public InfoRiepilogoXTipoItem getReipilogoUC() {
                return reipilogoUC;
            }

            /**
             * Imposta il valore della proprietà reipilogoUC.
             * 
             * @param value
             *     allowed object is
             *     {@link InfoRiepilogoXTipoItem }
             *     
             */
            public void setReipilogoUC(InfoRiepilogoXTipoItem value) {
                this.reipilogoUC = value;
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
     *         &lt;element name="Produttore" type="{urn:IRIS:SIPResult.xsd}ProduttoreSIP"/&gt;
     *         &lt;element name="CreatoDaApplicazione" type="{urn:IRIS:SIPResult.xsd}Applicazione"/&gt;
     *         &lt;element name="TsGenerazione" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
     *         &lt;element name="TsVersamento" type="{urn:IRIS:SIPResult.xsd}TimeRef"/&gt;
     *         &lt;element name="Label" type="{urn:IRIS:SIPResult.xsd}Label"/&gt;
     *         &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}ShortDescription" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *       &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPResult.xsd}ProvId" /&gt;
     *       &lt;attribute name="RegId" use="required" type="{urn:IRIS:SIPResult.xsd}UUID" /&gt;
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
        "tsVersamento",
        "label",
        "descrizione"
    })
    public static class RifSIP {

        @XmlElement(name = "Produttore", required = true)
        protected ProduttoreSIP produttore;
        @XmlElement(name = "CreatoDaApplicazione", required = true)
        protected Applicazione creatoDaApplicazione;
        @XmlElement(name = "TsGenerazione", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsGenerazione;
        @XmlElement(name = "TsVersamento", required = true)
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar tsVersamento;
        @XmlElement(name = "Label", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String label;
        @XmlElement(name = "Descrizione")
        protected ShortDescription descrizione;
        @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected String id;
        @XmlAttribute(name = "RegId", namespace = "urn:IRIS:SIPResult.xsd", required = true)
        protected String regId;

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
         * Recupera il valore della proprietà tsVersamento.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getTsVersamento() {
            return tsVersamento;
        }

        /**
         * Imposta il valore della proprietà tsVersamento.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setTsVersamento(XMLGregorianCalendar value) {
            this.tsVersamento = value;
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

    }

}
