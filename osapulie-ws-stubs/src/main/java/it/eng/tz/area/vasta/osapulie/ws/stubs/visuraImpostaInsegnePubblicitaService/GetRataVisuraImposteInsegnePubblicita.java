//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:40:13 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraImpostaInsegnePubblicitaService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="identificativoRata" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "identificativoRata"
})
@XmlRootElement(name = "getRataVisuraImposteInsegnePubblicita")
public class GetRataVisuraImposteInsegnePubblicita {

    @XmlElement(required = true, nillable = true)
    protected String identificativoRata;

    /**
     * Recupera il valore della proprietà identificativoRata.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoRata() {
        return identificativoRata;
    }

    /**
     * Imposta il valore della proprietà identificativoRata.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoRata(String value) {
        this.identificativoRata = value;
    }

}
