//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Riferimenti di un errore o warning
 * 
 * <p>Classe Java per ErroreWarning complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ErroreWarning"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Messaggio" type="{urn:IRIS:SIPResult.xsd}MessaggioErrore"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="Contesto" type="{urn:IRIS:SIPResult.xsd}ContestoErrore" /&gt;
 *       &lt;attribute name="Codice" type="{urn:IRIS:SIPResult.xsd}CodiceErrore" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ErroreWarning", propOrder = {
    "messaggio"
})
@XmlSeeAlso({
    it.eng.tz.area.vasta.conservazione.ws.sip.result.client.Item.Doc.Esito.Errore.class,
    it.eng.tz.area.vasta.conservazione.ws.sip.result.client.Item.Doc.Esito.Warning.class
})
public class ErroreWarning {

    @XmlElement(name = "Messaggio", required = true)
    protected MessaggioErrore messaggio;
    @XmlAttribute(name = "Contesto", namespace = "urn:IRIS:SIPResult.xsd")
    protected String contesto;
    @XmlAttribute(name = "Codice", namespace = "urn:IRIS:SIPResult.xsd")
    protected String codice;

    /**
     * Recupera il valore della proprietà messaggio.
     * 
     * @return
     *     possible object is
     *     {@link MessaggioErrore }
     *     
     */
    public MessaggioErrore getMessaggio() {
        return messaggio;
    }

    /**
     * Imposta il valore della proprietà messaggio.
     * 
     * @param value
     *     allowed object is
     *     {@link MessaggioErrore }
     *     
     */
    public void setMessaggio(MessaggioErrore value) {
        this.messaggio = value;
    }

    /**
     * Recupera il valore della proprietà contesto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContesto() {
        return contesto;
    }

    /**
     * Imposta il valore della proprietà contesto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContesto(String value) {
        this.contesto = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

}
