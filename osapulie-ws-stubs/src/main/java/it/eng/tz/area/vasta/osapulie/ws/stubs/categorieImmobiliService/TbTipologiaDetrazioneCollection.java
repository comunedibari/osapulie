//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:21:01 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.categorieImmobiliService;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tb_tipologia_detrazioneCollection complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tb_tipologia_detrazioneCollection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tb_tipologia_detrazione" type="{http://ws.wso2.org/dataservice}tb_tipologia_detrazione" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tb_tipologia_detrazioneCollection", propOrder = {
    "tbTipologiaDetrazione"
})
public class TbTipologiaDetrazioneCollection {

    @XmlElement(name = "tb_tipologia_detrazione")
    protected List<TbTipologiaDetrazione> tbTipologiaDetrazione;

    /**
     * Gets the value of the tbTipologiaDetrazione property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tbTipologiaDetrazione property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTbTipologiaDetrazione().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TbTipologiaDetrazione }
     * 
     * 
     */
    public List<TbTipologiaDetrazione> getTbTipologiaDetrazione() {
        if (tbTipologiaDetrazione == null) {
            tbTipologiaDetrazione = new ArrayList<TbTipologiaDetrazione>();
        }
        return this.tbTipologiaDetrazione;
    }

}
