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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * Impronta di un file: valore e funzione con cui è stato calcolato il valore
 * 
 * <p>Classe Java per Hash complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Hash"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema&gt;string"&gt;
 *       &lt;attribute name="encoding" use="required" type="{urn:IRIS:SIPResult.xsd}DigestEncoding" /&gt;
 *       &lt;attribute name="func" use="required" type="{urn:IRIS:SIPResult.xsd}HashFunc" /&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Hash", propOrder = {
    "value"
})
@XmlSeeAlso({
    it.eng.tz.area.vasta.conservazione.ws.sip.result.client.File.Hash.class
})
public class Hash {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "encoding", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    protected DigestEncoding encoding;
    @XmlAttribute(name = "func", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    protected HashFunc func;

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
     * Recupera il valore della proprietà encoding.
     * 
     * @return
     *     possible object is
     *     {@link DigestEncoding }
     *     
     */
    public DigestEncoding getEncoding() {
        return encoding;
    }

    /**
     * Imposta il valore della proprietà encoding.
     * 
     * @param value
     *     allowed object is
     *     {@link DigestEncoding }
     *     
     */
    public void setEncoding(DigestEncoding value) {
        this.encoding = value;
    }

    /**
     * Recupera il valore della proprietà func.
     * 
     * @return
     *     possible object is
     *     {@link HashFunc }
     *     
     */
    public HashFunc getFunc() {
        return func;
    }

    /**
     * Imposta il valore della proprietà func.
     * 
     * @param value
     *     allowed object is
     *     {@link HashFunc }
     *     
     */
    public void setFunc(HashFunc value) {
        this.func = value;
    }

}
