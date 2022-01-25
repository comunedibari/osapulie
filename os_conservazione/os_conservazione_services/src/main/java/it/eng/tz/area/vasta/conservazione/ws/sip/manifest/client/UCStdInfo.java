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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Dati di descrizione di un'unità di conservazione espressi secondo tracciato standard
 * 
 * <p>Classe Java per UCStdInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="UCStdInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
 *                 &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DesContenuto" type="{urn:IRIS:SIPManifest.xsd}LongDescription"/&gt;
 *         &lt;element name="Dimensioni" type="{urn:IRIS:SIPManifest.xsd}Dimensioni" minOccurs="0"/&gt;
 *         &lt;element name="Annotazioni" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
 *         &lt;element name="AttributoCustom" type="{urn:IRIS:SIPManifest.xsd}ValoriAttributo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "UCStdInfo", propOrder = {
    "tipo",
    "desContenuto",
    "dimensioni",
    "annotazioni",
    "attributoCustom"
})
public class UCStdInfo {

    @XmlElement(name = "Tipo", required = true)
    protected UCStdInfo.Tipo tipo;
    @XmlElement(name = "DesContenuto", required = true)
    protected LongDescription desContenuto;
    @XmlElement(name = "Dimensioni")
    protected Dimensioni dimensioni;
    @XmlElement(name = "Annotazioni")
    protected LongDescription annotazioni;
    @XmlElement(name = "AttributoCustom")
    protected List<ValoriAttributo> attributoCustom;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link UCStdInfo.Tipo }
     *     
     */
    public UCStdInfo.Tipo getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link UCStdInfo.Tipo }
     *     
     */
    public void setTipo(UCStdInfo.Tipo value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà desContenuto.
     * 
     * @return
     *     possible object is
     *     {@link LongDescription }
     *     
     */
    public LongDescription getDesContenuto() {
        return desContenuto;
    }

    /**
     * Imposta il valore della proprietà desContenuto.
     * 
     * @param value
     *     allowed object is
     *     {@link LongDescription }
     *     
     */
    public void setDesContenuto(LongDescription value) {
        this.desContenuto = value;
    }

    /**
     * Recupera il valore della proprietà dimensioni.
     * 
     * @return
     *     possible object is
     *     {@link Dimensioni }
     *     
     */
    public Dimensioni getDimensioni() {
        return dimensioni;
    }

    /**
     * Imposta il valore della proprietà dimensioni.
     * 
     * @param value
     *     allowed object is
     *     {@link Dimensioni }
     *     
     */
    public void setDimensioni(Dimensioni value) {
        this.dimensioni = value;
    }

    /**
     * Recupera il valore della proprietà annotazioni.
     * 
     * @return
     *     possible object is
     *     {@link LongDescription }
     *     
     */
    public LongDescription getAnnotazioni() {
        return annotazioni;
    }

    /**
     * Imposta il valore della proprietà annotazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link LongDescription }
     *     
     */
    public void setAnnotazioni(LongDescription value) {
        this.annotazioni = value;
    }

    /**
     * Gets the value of the attributoCustom property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attributoCustom property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttributoCustom().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValoriAttributo }
     * 
     * 
     */
    public List<ValoriAttributo> getAttributoCustom() {
        if (attributoCustom == null) {
            attributoCustom = new ArrayList<ValoriAttributo>();
        }
        return this.attributoCustom;
    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
     *       &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Tipo
        extends RifEntitaContesto
    {

        @XmlAttribute(name = "Cat", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
        protected CategoriaUA cat;

        /**
         * Recupera il valore della proprietà cat.
         * 
         * @return
         *     possible object is
         *     {@link CategoriaUA }
         *     
         */
        public CategoriaUA getCat() {
            return cat;
        }

        /**
         * Imposta il valore della proprietà cat.
         * 
         * @param value
         *     allowed object is
         *     {@link CategoriaUA }
         *     
         */
        public void setCat(CategoriaUA value) {
            this.cat = value;
        }

    }

}
