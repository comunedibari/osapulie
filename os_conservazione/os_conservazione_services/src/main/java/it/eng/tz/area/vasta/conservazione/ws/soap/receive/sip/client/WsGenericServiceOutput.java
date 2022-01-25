//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 06:14:03 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per wsGenericServiceOutput complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="wsGenericServiceOutput"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codEsito" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="desEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="errContext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="errMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="warnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "wsGenericServiceOutput", propOrder = {
    "codEsito",
    "desEsito",
    "errCode",
    "errContext",
    "errMessage",
    "warnMessage"
})
public class WsGenericServiceOutput {

    protected int codEsito;
    protected String desEsito;
    protected int errCode;
    protected String errContext;
    protected String errMessage;
    protected String warnMessage;

    /**
     * Recupera il valore della proprietà codEsito.
     * 
     */
    public int getCodEsito() {
        return codEsito;
    }

    /**
     * Imposta il valore della proprietà codEsito.
     * 
     */
    public void setCodEsito(int value) {
        this.codEsito = value;
    }

    /**
     * Recupera il valore della proprietà desEsito.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDesEsito() {
        return desEsito;
    }

    /**
     * Imposta il valore della proprietà desEsito.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDesEsito(String value) {
        this.desEsito = value;
    }

    /**
     * Recupera il valore della proprietà errCode.
     * 
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * Imposta il valore della proprietà errCode.
     * 
     */
    public void setErrCode(int value) {
        this.errCode = value;
    }

    /**
     * Recupera il valore della proprietà errContext.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrContext() {
        return errContext;
    }

    /**
     * Imposta il valore della proprietà errContext.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrContext(String value) {
        this.errContext = value;
    }

    /**
     * Recupera il valore della proprietà errMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getErrMessage() {
        return errMessage;
    }

    /**
     * Imposta il valore della proprietà errMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setErrMessage(String value) {
        this.errMessage = value;
    }

    /**
     * Recupera il valore della proprietà warnMessage.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarnMessage() {
        return warnMessage;
    }

    /**
     * Imposta il valore della proprietà warnMessage.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarnMessage(String value) {
        this.warnMessage = value;
    }

}
