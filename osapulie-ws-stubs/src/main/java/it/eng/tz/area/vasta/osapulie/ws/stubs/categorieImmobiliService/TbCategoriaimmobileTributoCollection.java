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
 * <p>Classe Java per tb_categoriaimmobile_tributoCollection complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tb_categoriaimmobile_tributoCollection"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="tb_categoriaimmobile_tributo" type="{http://ws.wso2.org/dataservice}tb_categoriaimmobile_tributo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tb_categoriaimmobile_tributoCollection", propOrder = {
    "tbCategoriaimmobileTributo"
})
public class TbCategoriaimmobileTributoCollection {

    @XmlElement(name = "tb_categoriaimmobile_tributo")
    protected List<TbCategoriaimmobileTributo> tbCategoriaimmobileTributo;

    /**
     * Gets the value of the tbCategoriaimmobileTributo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tbCategoriaimmobileTributo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTbCategoriaimmobileTributo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TbCategoriaimmobileTributo }
     * 
     * 
     */
    public List<TbCategoriaimmobileTributo> getTbCategoriaimmobileTributo() {
        if (tbCategoriaimmobileTributo == null) {
            tbCategoriaimmobileTributo = new ArrayList<TbCategoriaimmobileTributo>();
        }
        return this.tbCategoriaimmobileTributo;
    }

}
