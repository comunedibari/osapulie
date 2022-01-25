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
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Valore/i da assegnare ad un dato attributo
 * 
 * <p>Classe Java per ValoriAttributo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ValoriAttributo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="MatriceValori"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Rec" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Cols"&gt;
 *                               &lt;complexType&gt;
 *                                 &lt;complexContent&gt;
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                                     &lt;sequence&gt;
 *                                       &lt;element name="Col" maxOccurs="unbounded"&gt;
 *                                         &lt;complexType&gt;
 *                                           &lt;simpleContent&gt;
 *                                             &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *                                               &lt;attribute name="NomeSottoAttributo" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
 *                                             &lt;/extension&gt;
 *                                           &lt;/simpleContent&gt;
 *                                         &lt;/complexType&gt;
 *                                       &lt;/element&gt;
 *                                     &lt;/sequence&gt;
 *                                   &lt;/restriction&gt;
 *                                 &lt;/complexContent&gt;
 *                               &lt;/complexType&gt;
 *                             &lt;/element&gt;
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
 *         &lt;element name="ValoreSemplice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/choice&gt;
 *       &lt;attribute name="NomeAttributo" use="required" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValoriAttributo", propOrder = {
    "matriceValori",
    "valoreSemplice"
})
public class ValoriAttributo {

    @XmlElement(name = "MatriceValori")
    protected ValoriAttributo.MatriceValori matriceValori;
    @XmlElement(name = "ValoreSemplice")
    protected String valoreSemplice;
    @XmlAttribute(name = "NomeAttributo", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "token")
    protected String nomeAttributo;

    /**
     * Recupera il valore della proprietà matriceValori.
     * 
     * @return
     *     possible object is
     *     {@link ValoriAttributo.MatriceValori }
     *     
     */
    public ValoriAttributo.MatriceValori getMatriceValori() {
        return matriceValori;
    }

    /**
     * Imposta il valore della proprietà matriceValori.
     * 
     * @param value
     *     allowed object is
     *     {@link ValoriAttributo.MatriceValori }
     *     
     */
    public void setMatriceValori(ValoriAttributo.MatriceValori value) {
        this.matriceValori = value;
    }

    /**
     * Recupera il valore della proprietà valoreSemplice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValoreSemplice() {
        return valoreSemplice;
    }

    /**
     * Imposta il valore della proprietà valoreSemplice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValoreSemplice(String value) {
        this.valoreSemplice = value;
    }

    /**
     * Recupera il valore della proprietà nomeAttributo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNomeAttributo() {
        return nomeAttributo;
    }

    /**
     * Imposta il valore della proprietà nomeAttributo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNomeAttributo(String value) {
        this.nomeAttributo = value;
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
     *         &lt;element name="Rec" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Cols"&gt;
     *                     &lt;complexType&gt;
     *                       &lt;complexContent&gt;
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                           &lt;sequence&gt;
     *                             &lt;element name="Col" maxOccurs="unbounded"&gt;
     *                               &lt;complexType&gt;
     *                                 &lt;simpleContent&gt;
     *                                   &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
     *                                     &lt;attribute name="NomeSottoAttributo" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
     *                                   &lt;/extension&gt;
     *                                 &lt;/simpleContent&gt;
     *                               &lt;/complexType&gt;
     *                             &lt;/element&gt;
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
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "rec"
    })
    public static class MatriceValori {

        @XmlElement(name = "Rec")
        protected List<ValoriAttributo.MatriceValori.Rec> rec;

        /**
         * Gets the value of the rec property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the rec property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRec().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ValoriAttributo.MatriceValori.Rec }
         * 
         * 
         */
        public List<ValoriAttributo.MatriceValori.Rec> getRec() {
            if (rec == null) {
                rec = new ArrayList<ValoriAttributo.MatriceValori.Rec>();
            }
            return this.rec;
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
         *         &lt;element name="Cols"&gt;
         *           &lt;complexType&gt;
         *             &lt;complexContent&gt;
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *                 &lt;sequence&gt;
         *                   &lt;element name="Col" maxOccurs="unbounded"&gt;
         *                     &lt;complexType&gt;
         *                       &lt;simpleContent&gt;
         *                         &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
         *                           &lt;attribute name="NomeSottoAttributo" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
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
        @XmlType(name = "", propOrder = {
            "cols"
        })
        public static class Rec {

            @XmlElement(name = "Cols", required = true)
            protected ValoriAttributo.MatriceValori.Rec.Cols cols;

            /**
             * Recupera il valore della proprietà cols.
             * 
             * @return
             *     possible object is
             *     {@link ValoriAttributo.MatriceValori.Rec.Cols }
             *     
             */
            public ValoriAttributo.MatriceValori.Rec.Cols getCols() {
                return cols;
            }

            /**
             * Imposta il valore della proprietà cols.
             * 
             * @param value
             *     allowed object is
             *     {@link ValoriAttributo.MatriceValori.Rec.Cols }
             *     
             */
            public void setCols(ValoriAttributo.MatriceValori.Rec.Cols value) {
                this.cols = value;
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
             *         &lt;element name="Col" maxOccurs="unbounded"&gt;
             *           &lt;complexType&gt;
             *             &lt;simpleContent&gt;
             *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
             *                 &lt;attribute name="NomeSottoAttributo" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
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
                "col"
            })
            public static class Cols {

                @XmlElement(name = "Col", required = true)
                protected List<ValoriAttributo.MatriceValori.Rec.Cols.Col> col;

                /**
                 * Gets the value of the col property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the col property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCol().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link ValoriAttributo.MatriceValori.Rec.Cols.Col }
                 * 
                 * 
                 */
                public List<ValoriAttributo.MatriceValori.Rec.Cols.Col> getCol() {
                    if (col == null) {
                        col = new ArrayList<ValoriAttributo.MatriceValori.Rec.Cols.Col>();
                    }
                    return this.col;
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
                 *       &lt;attribute name="NomeSottoAttributo" type="{http://www.w3.org/2001/XMLSchema}token" /&gt;
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
                public static class Col {

                    @XmlValue
                    protected String value;
                    @XmlAttribute(name = "NomeSottoAttributo", namespace = "urn:IRIS:SIPManifest.xsd")
                    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
                    @XmlSchemaType(name = "token")
                    protected String nomeSottoAttributo;

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
                     * Recupera il valore della proprietà nomeSottoAttributo.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getNomeSottoAttributo() {
                        return nomeSottoAttributo;
                    }

                    /**
                     * Imposta il valore della proprietà nomeSottoAttributo.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setNomeSottoAttributo(String value) {
                        this.nomeSottoAttributo = value;
                    }

                }

            }

        }

    }

}
