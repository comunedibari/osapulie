
package ws.it.eng.iris.core.send.sip;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for wsGenericServiceOutput complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="wsGenericServiceOutput">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codEsito" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="desEsito" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errCode" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="errContext" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="errMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warnMessage" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
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
     * Gets the value of the codEsito property.
     * 
     */
    public int getCodEsito() {
        return codEsito;
    }

    /**
     * Sets the value of the codEsito property.
     * 
     */
    public void setCodEsito(int value) {
        this.codEsito = value;
    }

    /**
     * Gets the value of the desEsito property.
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
     * Sets the value of the desEsito property.
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
     * Gets the value of the errCode property.
     * 
     */
    public int getErrCode() {
        return errCode;
    }

    /**
     * Sets the value of the errCode property.
     * 
     */
    public void setErrCode(int value) {
        this.errCode = value;
    }

    /**
     * Gets the value of the errContext property.
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
     * Sets the value of the errContext property.
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
     * Gets the value of the errMessage property.
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
     * Sets the value of the errMessage property.
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
     * Gets the value of the warnMessage property.
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
     * Sets the value of the warnMessage property.
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
