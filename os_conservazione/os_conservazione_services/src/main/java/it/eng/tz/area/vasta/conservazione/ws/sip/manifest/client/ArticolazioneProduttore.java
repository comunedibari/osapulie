//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Indica una qualsiasi articolazione - in particolare ad esempio un'Unità Organizzativa - di un soggetto produttore della documentazione conservata
 * 
 * <p>Classe Java per ArticolazioneProduttore complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ArticolazioneProduttore"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UO" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"/&gt;
 *         &lt;element name="Codice"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Organigramma" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
 *                   &lt;element name="Parte" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                           &lt;attribute name="Livello" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
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
@XmlType(name = "ArticolazioneProduttore", propOrder = {
    "uo",
    "codice"
})
@XmlSeeAlso({
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.ProduttoreDoc.ArticolazioniProduttore.UO.class
})
public class ArticolazioneProduttore {

    @XmlElement(name = "UO", required = true)
    protected RifEntitaContesto uo;
    @XmlElement(name = "Codice", required = true)
    protected ArticolazioneProduttore.Codice codice;

    /**
     * Recupera il valore della proprietà uo.
     * 
     * @return
     *     possible object is
     *     {@link RifEntitaContesto }
     *     
     */
    public RifEntitaContesto getUO() {
        return uo;
    }

    /**
     * Imposta il valore della proprietà uo.
     * 
     * @param value
     *     allowed object is
     *     {@link RifEntitaContesto }
     *     
     */
    public void setUO(RifEntitaContesto value) {
        this.uo = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link ArticolazioneProduttore.Codice }
     *     
     */
    public ArticolazioneProduttore.Codice getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link ArticolazioneProduttore.Codice }
     *     
     */
    public void setCodice(ArticolazioneProduttore.Codice value) {
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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Organigramma" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
     *         &lt;element name="Parte" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                 &lt;attribute name="Livello" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
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
        "organigramma",
        "parte"
    })
    public static class Codice {

        @XmlElement(name = "Organigramma")
        protected RifEntitaContesto organigramma;
        @XmlElement(name = "Parte", required = true)
        protected List<ArticolazioneProduttore.Codice.Parte> parte;

        /**
         * Recupera il valore della proprietà organigramma.
         * 
         * @return
         *     possible object is
         *     {@link RifEntitaContesto }
         *     
         */
        public RifEntitaContesto getOrganigramma() {
            return organigramma;
        }

        /**
         * Imposta il valore della proprietà organigramma.
         * 
         * @param value
         *     allowed object is
         *     {@link RifEntitaContesto }
         *     
         */
        public void setOrganigramma(RifEntitaContesto value) {
            this.organigramma = value;
        }

        /**
         * Gets the value of the parte property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the parte property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getParte().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ArticolazioneProduttore.Codice.Parte }
         * 
         * 
         */
        public List<ArticolazioneProduttore.Codice.Parte> getParte() {
            if (parte == null) {
                parte = new ArrayList<ArticolazioneProduttore.Codice.Parte>();
            }
            return this.parte;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *       &lt;attribute name="Livello" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
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
        public static class Parte {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "Livello", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
            @XmlSchemaType(name = "positiveInteger")
            protected BigInteger livello;

            /**
             * Recupera il valore della proprietà value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Imposta il valore della proprietà value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * Recupera il valore della proprietà livello.
             * 
             * @return
             *     possible object is
             *     {@link BigInteger }
             *     
             */
            public BigInteger getLivello() {
                return livello;
            }

            /**
             * Imposta il valore della proprietà livello.
             * 
             * @param value
             *     allowed object is
             *     {@link BigInteger }
             *     
             */
            public void setLivello(BigInteger value) {
                this.livello = value;
            }

        }

    }

}
