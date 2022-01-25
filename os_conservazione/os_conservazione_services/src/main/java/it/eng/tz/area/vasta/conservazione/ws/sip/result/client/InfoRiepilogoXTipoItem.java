//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Contiene i contatori relativi agli item di un dato tipo - UA, SD o UC - presenti nel SIP: n.ro totale, n.ro accettati senza e con avvertimenti, n.ro rifiutati
 * 
 * <p>Classe Java per InfoRiepilogoXTipoItem complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="InfoRiepilogoXTipoItem"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="NroTotale" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
 *         &lt;element name="NroAccettati"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="NroSenzaAvvertimenti" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
 *                   &lt;element name="NroConAvvertimenti" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="NroRifiutati" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "InfoRiepilogoXTipoItem", propOrder = {
    "nroTotale",
    "nroAccettati",
    "nroRifiutati"
})
public class InfoRiepilogoXTipoItem {

    @XmlElement(name = "NroTotale", required = true)
    protected BigInteger nroTotale;
    @XmlElement(name = "NroAccettati", required = true)
    protected InfoRiepilogoXTipoItem.NroAccettati nroAccettati;
    @XmlElement(name = "NroRifiutati", required = true)
    protected BigInteger nroRifiutati;

    /**
     * Recupera il valore della proprietà nroTotale.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroTotale() {
        return nroTotale;
    }

    /**
     * Imposta il valore della proprietà nroTotale.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroTotale(BigInteger value) {
        this.nroTotale = value;
    }

    /**
     * Recupera il valore della proprietà nroAccettati.
     * 
     * @return
     *     possible object is
     *     {@link InfoRiepilogoXTipoItem.NroAccettati }
     *     
     */
    public InfoRiepilogoXTipoItem.NroAccettati getNroAccettati() {
        return nroAccettati;
    }

    /**
     * Imposta il valore della proprietà nroAccettati.
     * 
     * @param value
     *     allowed object is
     *     {@link InfoRiepilogoXTipoItem.NroAccettati }
     *     
     */
    public void setNroAccettati(InfoRiepilogoXTipoItem.NroAccettati value) {
        this.nroAccettati = value;
    }

    /**
     * Recupera il valore della proprietà nroRifiutati.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroRifiutati() {
        return nroRifiutati;
    }

    /**
     * Imposta il valore della proprietà nroRifiutati.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroRifiutati(BigInteger value) {
        this.nroRifiutati = value;
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
     *         &lt;element name="NroSenzaAvvertimenti" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
     *         &lt;element name="NroConAvvertimenti" type="{urn:IRIS:SIPResult.xsd}Contatore"/&gt;
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
        "nroSenzaAvvertimenti",
        "nroConAvvertimenti"
    })
    public static class NroAccettati {

        @XmlElement(name = "NroSenzaAvvertimenti", required = true)
        protected BigInteger nroSenzaAvvertimenti;
        @XmlElement(name = "NroConAvvertimenti", required = true)
        protected BigInteger nroConAvvertimenti;

        /**
         * Recupera il valore della proprietà nroSenzaAvvertimenti.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNroSenzaAvvertimenti() {
            return nroSenzaAvvertimenti;
        }

        /**
         * Imposta il valore della proprietà nroSenzaAvvertimenti.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNroSenzaAvvertimenti(BigInteger value) {
            this.nroSenzaAvvertimenti = value;
        }

        /**
         * Recupera il valore della proprietà nroConAvvertimenti.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getNroConAvvertimenti() {
            return nroConAvvertimenti;
        }

        /**
         * Imposta il valore della proprietà nroConAvvertimenti.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setNroConAvvertimenti(BigInteger value) {
            this.nroConAvvertimenti = value;
        }

    }

}
