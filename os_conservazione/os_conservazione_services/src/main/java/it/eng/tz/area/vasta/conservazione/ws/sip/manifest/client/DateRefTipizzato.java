//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Riferimento temporale con indicazione di che tipo di riferimento si tratta
 * 
 * <p>Classe Java per DateRefTipizzato complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DateRefTipizzato"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Data" type="{urn:IRIS:SIPManifest.xsd}DateRef"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="TipoRif" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DateRefTipizzato", propOrder = {
    "data"
})
public class DateRefTipizzato {

    @XmlElement(name = "Data", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar data;
    @XmlAttribute(name = "TipoRif", namespace = "urn:IRIS:SIPManifest.xsd")
    @XmlSchemaType(name = "anySimpleType")
    protected String tipoRif;

    /**
     * Recupera il valore della proprietà data.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getData() {
        return data;
    }

    /**
     * Imposta il valore della proprietà data.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setData(XMLGregorianCalendar value) {
        this.data = value;
    }

    /**
     * Recupera il valore della proprietà tipoRif.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoRif() {
        return tipoRif;
    }

    /**
     * Imposta il valore della proprietà tipoRif.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoRif(String value) {
        this.tipoRif = value;
    }

}
