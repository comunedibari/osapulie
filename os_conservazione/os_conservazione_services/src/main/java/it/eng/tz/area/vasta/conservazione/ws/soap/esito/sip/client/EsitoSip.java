//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 05:15:52 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per esitoSip complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="esitoSip"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="sipEsito" minOccurs="0" form="qualified"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
@XmlType(name = "esitoSip", propOrder = {
    "sipEsito"
})
public class EsitoSip {

    @XmlElement(namespace = "http://sip.esito.core.iris.eng.it")
    protected EsitoSip.SipEsito sipEsito;

    /**
     * Recupera il valore della proprietà sipEsito.
     * 
     * @return
     *     possible object is
     *     {@link EsitoSip.SipEsito }
     *     
     */
    public EsitoSip.SipEsito getSipEsito() {
        return sipEsito;
    }

    /**
     * Imposta il valore della proprietà sipEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link EsitoSip.SipEsito }
     *     
     */
    public void setSipEsito(EsitoSip.SipEsito value) {
        this.sipEsito = value;
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
     *         &lt;element name="idSip" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "idSip"
    })
    public static class SipEsito {

        @XmlElement(required = true)
        protected String idSip;

        /**
         * Recupera il valore della proprietà idSip.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIdSip() {
            return idSip;
        }

        /**
         * Imposta il valore della proprietà idSip.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIdSip(String value) {
            this.idSip = value;
        }

    }

}
