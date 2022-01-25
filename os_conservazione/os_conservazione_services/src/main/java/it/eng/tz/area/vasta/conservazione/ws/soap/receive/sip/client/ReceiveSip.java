//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 06:14:03 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per receiveSip complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="receiveSip"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sipReceive" minOccurs="0" form="qualified"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="tipoSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="tipoProtezione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="improntaAlgoritmo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="improntaCodifica" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="impronta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="arg1" type="{http://sip.receive.core.iris.eng.it}attachment" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveSip", propOrder = {
    "sipReceive",
    "arg1"
})
public class ReceiveSip {

    @XmlElement(namespace = "http://sip.receive.core.iris.eng.it")
    protected ReceiveSip.SipReceive sipReceive;
    @XmlElement(nillable = true)
    protected List<Attachment> arg1;

    /**
     * Recupera il valore della proprietà sipReceive.
     * 
     * @return
     *     possible object is
     *     {@link ReceiveSip.SipReceive }
     *     
     */
    public ReceiveSip.SipReceive getSipReceive() {
        return sipReceive;
    }

    /**
     * Imposta il valore della proprietà sipReceive.
     * 
     * @param value
     *     allowed object is
     *     {@link ReceiveSip.SipReceive }
     *     
     */
    public void setSipReceive(ReceiveSip.SipReceive value) {
        this.sipReceive = value;
    }

    /**
     * Gets the value of the arg1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the arg1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getArg1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attachment }
     * 
     * 
     */
    public List<Attachment> getArg1() {
        if (arg1 == null) {
            arg1 = new ArrayList<Attachment>();
        }
        return this.arg1;
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
     *         &lt;element name="tipoSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="tipoProtezione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="improntaAlgoritmo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="improntaCodifica" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="impronta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "tipoSip",
        "tipoProtezione",
        "improntaAlgoritmo",
        "improntaCodifica",
        "impronta"
    })
    public static class SipReceive {

        @XmlElement(required = true)
        protected String tipoSip;
        @XmlElement(required = true)
        protected String tipoProtezione;
        @XmlElement(required = true)
        protected String improntaAlgoritmo;
        @XmlElement(required = true)
        protected String improntaCodifica;
        @XmlElement(required = true)
        protected String impronta;

        /**
         * Recupera il valore della proprietà tipoSip.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoSip() {
            return tipoSip;
        }

        /**
         * Imposta il valore della proprietà tipoSip.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoSip(String value) {
            this.tipoSip = value;
        }

        /**
         * Recupera il valore della proprietà tipoProtezione.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTipoProtezione() {
            return tipoProtezione;
        }

        /**
         * Imposta il valore della proprietà tipoProtezione.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTipoProtezione(String value) {
            this.tipoProtezione = value;
        }

        /**
         * Recupera il valore della proprietà improntaAlgoritmo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImprontaAlgoritmo() {
            return improntaAlgoritmo;
        }

        /**
         * Imposta il valore della proprietà improntaAlgoritmo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImprontaAlgoritmo(String value) {
            this.improntaAlgoritmo = value;
        }

        /**
         * Recupera il valore della proprietà improntaCodifica.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImprontaCodifica() {
            return improntaCodifica;
        }

        /**
         * Imposta il valore della proprietà improntaCodifica.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImprontaCodifica(String value) {
            this.improntaCodifica = value;
        }

        /**
         * Recupera il valore della proprietà impronta.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getImpronta() {
            return impronta;
        }

        /**
         * Imposta il valore della proprietà impronta.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setImpronta(String value) {
            this.impronta = value;
        }

    }

}
