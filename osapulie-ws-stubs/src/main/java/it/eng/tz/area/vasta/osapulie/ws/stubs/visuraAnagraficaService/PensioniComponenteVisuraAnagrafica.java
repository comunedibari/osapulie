//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:36:47 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per pensioniComponenteVisuraAnagrafica complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="pensioniComponenteVisuraAnagrafica"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="pensioneComponenteVisuraAnagrafica" type="{http://servizi.osapulie.it}pensioneComponenteVisuraAnagrafica" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "pensioniComponenteVisuraAnagrafica", propOrder = {
    "pensioneComponenteVisuraAnagrafica"
})
public class PensioniComponenteVisuraAnagrafica {

    protected List<PensioneComponenteVisuraAnagrafica> pensioneComponenteVisuraAnagrafica;

    /**
     * Gets the value of the pensioneComponenteVisuraAnagrafica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pensioneComponenteVisuraAnagrafica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPensioneComponenteVisuraAnagrafica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PensioneComponenteVisuraAnagrafica }
     * 
     * 
     */
    public List<PensioneComponenteVisuraAnagrafica> getPensioneComponenteVisuraAnagrafica() {
        if (pensioneComponenteVisuraAnagrafica == null) {
            pensioneComponenteVisuraAnagrafica = new ArrayList<PensioneComponenteVisuraAnagrafica>();
        }
        return this.pensioneComponenteVisuraAnagrafica;
    }

}
