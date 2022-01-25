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
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Indica chi è il/un produttore - come persona giuridica - di un documento o agggregato di documenti inviato in conservazione
 * 
 * <p>Classe Java per ProduttoreDoc complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProduttoreDoc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo" type="{urn:IRIS:SIPManifest.xsd}FormaGiuridica"/&gt;
 *         &lt;element name="Intestazione" type="{urn:IRIS:SIPManifest.xsd}Nome"/&gt;
 *         &lt;element name="CodFiscale" type="{urn:IRIS:SIPManifest.xsd}CodiceFiscale" minOccurs="0"/&gt;
 *         &lt;element name="PartitaIVA" type="{urn:IRIS:SIPManifest.xsd}PartitaIVA" minOccurs="0"/&gt;
 *         &lt;element name="Dal" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
 *         &lt;element name="Al" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
 *         &lt;element name="ArticolazioniProduttore" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="UO" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;extension base="{urn:IRIS:SIPManifest.xsd}ArticolazioneProduttore"&gt;
 *                           &lt;sequence minOccurs="0"&gt;
 *                             &lt;element name="Dal" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
 *                             &lt;element name="Al" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="CodiceAmministrazione" type="{urn:IRIS:SIPManifest.xsd}CodiceAmministrazione" /&gt;
 *       &lt;attribute name="CodiceAOO" type="{urn:IRIS:SIPManifest.xsd}CodiceAOO" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProduttoreDoc", propOrder = {
    "tipo",
    "intestazione",
    "codFiscale",
    "partitaIVA",
    "dal",
    "al",
    "articolazioniProduttore"
})
public class ProduttoreDoc {

    @XmlElement(name = "Tipo", required = true, defaultValue = "PA")
    @XmlSchemaType(name = "string")
    protected FormaGiuridica tipo;
    @XmlElement(name = "Intestazione", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String intestazione;
    @XmlElement(name = "CodFiscale")
    protected String codFiscale;
    @XmlElement(name = "PartitaIVA")
    protected String partitaIVA;
    @XmlElement(name = "Dal")
    protected EstremoCronologico dal;
    @XmlElement(name = "Al")
    protected EstremoCronologico al;
    @XmlElement(name = "ArticolazioniProduttore")
    protected ProduttoreDoc.ArticolazioniProduttore articolazioniProduttore;
    @XmlAttribute(name = "CodiceAmministrazione", namespace = "urn:IRIS:SIPManifest.xsd")
    protected String codiceAmministrazione;
    @XmlAttribute(name = "CodiceAOO", namespace = "urn:IRIS:SIPManifest.xsd")
    protected String codiceAOO;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link FormaGiuridica }
     *     
     */
    public FormaGiuridica getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaGiuridica }
     *     
     */
    public void setTipo(FormaGiuridica value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà intestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntestazione() {
        return intestazione;
    }

    /**
     * Imposta il valore della proprietà intestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntestazione(String value) {
        this.intestazione = value;
    }

    /**
     * Recupera il valore della proprietà codFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * Imposta il valore della proprietà codFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodFiscale(String value) {
        this.codFiscale = value;
    }

    /**
     * Recupera il valore della proprietà partitaIVA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIVA() {
        return partitaIVA;
    }

    /**
     * Imposta il valore della proprietà partitaIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIVA(String value) {
        this.partitaIVA = value;
    }

    /**
     * Recupera il valore della proprietà dal.
     * 
     * @return
     *     possible object is
     *     {@link EstremoCronologico }
     *     
     */
    public EstremoCronologico getDal() {
        return dal;
    }

    /**
     * Imposta il valore della proprietà dal.
     * 
     * @param value
     *     allowed object is
     *     {@link EstremoCronologico }
     *     
     */
    public void setDal(EstremoCronologico value) {
        this.dal = value;
    }

    /**
     * Recupera il valore della proprietà al.
     * 
     * @return
     *     possible object is
     *     {@link EstremoCronologico }
     *     
     */
    public EstremoCronologico getAl() {
        return al;
    }

    /**
     * Imposta il valore della proprietà al.
     * 
     * @param value
     *     allowed object is
     *     {@link EstremoCronologico }
     *     
     */
    public void setAl(EstremoCronologico value) {
        this.al = value;
    }

    /**
     * Recupera il valore della proprietà articolazioniProduttore.
     * 
     * @return
     *     possible object is
     *     {@link ProduttoreDoc.ArticolazioniProduttore }
     *     
     */
    public ProduttoreDoc.ArticolazioniProduttore getArticolazioniProduttore() {
        return articolazioniProduttore;
    }

    /**
     * Imposta il valore della proprietà articolazioniProduttore.
     * 
     * @param value
     *     allowed object is
     *     {@link ProduttoreDoc.ArticolazioniProduttore }
     *     
     */
    public void setArticolazioniProduttore(ProduttoreDoc.ArticolazioniProduttore value) {
        this.articolazioniProduttore = value;
    }

    /**
     * Recupera il valore della proprietà codiceAmministrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAmministrazione() {
        return codiceAmministrazione;
    }

    /**
     * Imposta il valore della proprietà codiceAmministrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAmministrazione(String value) {
        this.codiceAmministrazione = value;
    }

    /**
     * Recupera il valore della proprietà codiceAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAOO() {
        return codiceAOO;
    }

    /**
     * Imposta il valore della proprietà codiceAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAOO(String value) {
        this.codiceAOO = value;
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
     *         &lt;element name="UO" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}ArticolazioneProduttore"&gt;
     *                 &lt;sequence minOccurs="0"&gt;
     *                   &lt;element name="Dal" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
     *                   &lt;element name="Al" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/extension&gt;
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
        "uo"
    })
    public static class ArticolazioniProduttore {

        @XmlElement(name = "UO", required = true)
        protected List<ProduttoreDoc.ArticolazioniProduttore.UO> uo;

        /**
         * Gets the value of the uo property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the uo property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUO().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ProduttoreDoc.ArticolazioniProduttore.UO }
         * 
         * 
         */
        public List<ProduttoreDoc.ArticolazioniProduttore.UO> getUO() {
            if (uo == null) {
                uo = new ArrayList<ProduttoreDoc.ArticolazioniProduttore.UO>();
            }
            return this.uo;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;extension base="{urn:IRIS:SIPManifest.xsd}ArticolazioneProduttore"&gt;
         *       &lt;sequence minOccurs="0"&gt;
         *         &lt;element name="Dal" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
         *         &lt;element name="Al" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
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
            "dal",
            "al"
        })
        public static class UO
            extends ArticolazioneProduttore
        {

            @XmlElement(name = "Dal")
            protected EstremoCronologico dal;
            @XmlElement(name = "Al")
            protected EstremoCronologico al;

            /**
             * Recupera il valore della proprietà dal.
             * 
             * @return
             *     possible object is
             *     {@link EstremoCronologico }
             *     
             */
            public EstremoCronologico getDal() {
                return dal;
            }

            /**
             * Imposta il valore della proprietà dal.
             * 
             * @param value
             *     allowed object is
             *     {@link EstremoCronologico }
             *     
             */
            public void setDal(EstremoCronologico value) {
                this.dal = value;
            }

            /**
             * Recupera il valore della proprietà al.
             * 
             * @return
             *     possible object is
             *     {@link EstremoCronologico }
             *     
             */
            public EstremoCronologico getAl() {
                return al;
            }

            /**
             * Imposta il valore della proprietà al.
             * 
             * @param value
             *     allowed object is
             *     {@link EstremoCronologico }
             *     
             */
            public void setAl(EstremoCronologico value) {
                this.al = value;
            }

        }

    }

}
