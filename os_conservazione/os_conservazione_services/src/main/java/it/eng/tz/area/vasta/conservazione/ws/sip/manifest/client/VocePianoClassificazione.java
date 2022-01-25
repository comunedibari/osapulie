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
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Estremi di una voce di un piano di classificazione
 * 
 * <p>Classe Java per VocePianoClassificazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="VocePianoClassificazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Piano" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
 *         &lt;element name="Voce" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"/&gt;
 *         &lt;element name="Indice"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
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
@XmlType(name = "VocePianoClassificazione", propOrder = {
    "piano",
    "voce",
    "indice"
})
public class VocePianoClassificazione {

    @XmlElement(name = "Piano")
    protected RifEntitaContesto piano;
    @XmlElement(name = "Voce", required = true)
    protected RifEntitaContesto voce;
    @XmlElement(name = "Indice", required = true)
    protected VocePianoClassificazione.Indice indice;

    /**
     * Recupera il valore della proprietà piano.
     * 
     * @return
     *     possible object is
     *     {@link RifEntitaContesto }
     *     
     */
    public RifEntitaContesto getPiano() {
        return piano;
    }

    /**
     * Imposta il valore della proprietà piano.
     * 
     * @param value
     *     allowed object is
     *     {@link RifEntitaContesto }
     *     
     */
    public void setPiano(RifEntitaContesto value) {
        this.piano = value;
    }

    /**
     * Recupera il valore della proprietà voce.
     * 
     * @return
     *     possible object is
     *     {@link RifEntitaContesto }
     *     
     */
    public RifEntitaContesto getVoce() {
        return voce;
    }

    /**
     * Imposta il valore della proprietà voce.
     * 
     * @param value
     *     allowed object is
     *     {@link RifEntitaContesto }
     *     
     */
    public void setVoce(RifEntitaContesto value) {
        this.voce = value;
    }

    /**
     * Recupera il valore della proprietà indice.
     * 
     * @return
     *     possible object is
     *     {@link VocePianoClassificazione.Indice }
     *     
     */
    public VocePianoClassificazione.Indice getIndice() {
        return indice;
    }

    /**
     * Imposta il valore della proprietà indice.
     * 
     * @param value
     *     allowed object is
     *     {@link VocePianoClassificazione.Indice }
     *     
     */
    public void setIndice(VocePianoClassificazione.Indice value) {
        this.indice = value;
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
        "parte"
    })
    public static class Indice {

        @XmlElement(name = "Parte", required = true)
        protected List<VocePianoClassificazione.Indice.Parte> parte;

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
         * {@link VocePianoClassificazione.Indice.Parte }
         * 
         * 
         */
        public List<VocePianoClassificazione.Indice.Parte> getParte() {
            if (parte == null) {
                parte = new ArrayList<VocePianoClassificazione.Indice.Parte>();
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
