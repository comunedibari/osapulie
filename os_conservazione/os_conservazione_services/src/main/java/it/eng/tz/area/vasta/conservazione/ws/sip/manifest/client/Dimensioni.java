//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Dimensioni (altezza, lunghezza e larghezza) di un contenitore (espresse in mm)
 * 
 * <p>Classe Java per Dimensioni complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Dimensioni"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="X" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="Y" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *         &lt;element name="Z" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Dimensioni", propOrder = {
    "x",
    "y",
    "z"
})
public class Dimensioni {

    @XmlElement(name = "X")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger x;
    @XmlElement(name = "Y")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger y;
    @XmlElement(name = "Z")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger z;

    /**
     * Recupera il valore della proprietà x.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getX() {
        return x;
    }

    /**
     * Imposta il valore della proprietà x.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setX(BigInteger value) {
        this.x = value;
    }

    /**
     * Recupera il valore della proprietà y.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getY() {
        return y;
    }

    /**
     * Imposta il valore della proprietà y.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setY(BigInteger value) {
        this.y = value;
    }

    /**
     * Recupera il valore della proprietà z.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getZ() {
        return z;
    }

    /**
     * Imposta il valore della proprietà z.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setZ(BigInteger value) {
        this.z = value;
    }

}
