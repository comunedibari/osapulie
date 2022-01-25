//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Rappresenta i file che costituiscono le firme e le marche temporali detached del file con il contenuto informativo o un Indice di Conservazione (fatto da sistema di conservazione diverso dal SCN) in cui esso è referenziato
 * 
 * <p>Classe Java per ComponentiDigitaliDetached complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ComponentiDigitaliDetached"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ComponenteDigitaleDetached" type="{urn:IRIS:SIPManifest.xsd}ComponenteDigitaleDetached" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComponentiDigitaliDetached", propOrder = {
    "componenteDigitaleDetached"
})
public class ComponentiDigitaliDetached {

    @XmlElement(name = "ComponenteDigitaleDetached", required = true)
    protected List<ComponenteDigitaleDetached> componenteDigitaleDetached;

    /**
     * Gets the value of the componenteDigitaleDetached property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componenteDigitaleDetached property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponenteDigitaleDetached().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComponenteDigitaleDetached }
     * 
     * 
     */
    public List<ComponenteDigitaleDetached> getComponenteDigitaleDetached() {
        if (componenteDigitaleDetached == null) {
            componenteDigitaleDetached = new ArrayList<ComponenteDigitaleDetached>();
        }
        return this.componenteDigitaleDetached;
    }

}
