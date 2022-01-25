//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * Dati comuni sia ad un documento analogico (cartaceo o su altro supporto analogico) che digitale espressi secondo tracciato standard
 * 
 * <p>Classe Java per DocStdInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DocStdInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="SupportoFisico" type="{urn:IRIS:SIPManifest.xsd}RifDizionario"/&gt;
 *         &lt;element name="Tipo" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
 *         &lt;element name="DesOgg" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
 *         &lt;element name="AttributoCustom" type="{urn:IRIS:SIPManifest.xsd}ValoriAttributo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="RelVsSD" use="required" type="{urn:IRIS:SIPManifest.xsd}RelVsSD" /&gt;
 *       &lt;attribute name="Id" use="required" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
 *       &lt;attribute name="Op" type="{urn:IRIS:SIPManifest.xsd}SIPItemOper" default="I" /&gt;
 *       &lt;attribute name="NroAllegato" type="{urn:IRIS:SIPManifest.xsd}NroAllegato" /&gt;
 *       &lt;attribute name="IdDocVersPrec" type="{urn:IRIS:SIPManifest.xsd}ProvId" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocStdInfo", propOrder = {
    "supportoFisico",
    "tipo",
    "desOgg",
    "attributoCustom"
})
@XmlSeeAlso({
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SDStdInfo.DigDocs.class
})
public class DocStdInfo {

    @XmlElement(name = "SupportoFisico", required = true)
    protected RifDizionario supportoFisico;
    @XmlElement(name = "Tipo")
    protected RifEntitaContesto tipo;
    @XmlElement(name = "DesOgg")
    protected LongDescription desOgg;
    @XmlElement(name = "AttributoCustom")
    protected List<ValoriAttributo> attributoCustom;
    @XmlAttribute(name = "RelVsSD", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    protected RelVsSD relVsSD;
    @XmlAttribute(name = "Id", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    protected String id;
    @XmlAttribute(name = "Op", namespace = "urn:IRIS:SIPManifest.xsd")
    protected SIPItemOper op;
    @XmlAttribute(name = "NroAllegato", namespace = "urn:IRIS:SIPManifest.xsd")
    protected BigInteger nroAllegato;
    @XmlAttribute(name = "IdDocVersPrec", namespace = "urn:IRIS:SIPManifest.xsd")
    protected String idDocVersPrec;

    /**
     * Recupera il valore della proprietà supportoFisico.
     * 
     * @return
     *     possible object is
     *     {@link RifDizionario }
     *     
     */
    public RifDizionario getSupportoFisico() {
        return supportoFisico;
    }

    /**
     * Imposta il valore della proprietà supportoFisico.
     * 
     * @param value
     *     allowed object is
     *     {@link RifDizionario }
     *     
     */
    public void setSupportoFisico(RifDizionario value) {
        this.supportoFisico = value;
    }

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link RifEntitaContesto }
     *     
     */
    public RifEntitaContesto getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link RifEntitaContesto }
     *     
     */
    public void setTipo(RifEntitaContesto value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà desOgg.
     * 
     * @return
     *     possible object is
     *     {@link LongDescription }
     *     
     */
    public LongDescription getDesOgg() {
        return desOgg;
    }

    /**
     * Imposta il valore della proprietà desOgg.
     * 
     * @param value
     *     allowed object is
     *     {@link LongDescription }
     *     
     */
    public void setDesOgg(LongDescription value) {
        this.desOgg = value;
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
     * Recupera il valore della proprietà relVsSD.
     * 
     * @return
     *     possible object is
     *     {@link RelVsSD }
     *     
     */
    public RelVsSD getRelVsSD() {
        return relVsSD;
    }

    /**
     * Imposta il valore della proprietà relVsSD.
     * 
     * @param value
     *     allowed object is
     *     {@link RelVsSD }
     *     
     */
    public void setRelVsSD(RelVsSD value) {
        this.relVsSD = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà op.
     * 
     * @return
     *     possible object is
     *     {@link SIPItemOper }
     *     
     */
    public SIPItemOper getOp() {
        if (op == null) {
            return SIPItemOper.I;
        } else {
            return op;
        }
    }

    /**
     * Imposta il valore della proprietà op.
     * 
     * @param value
     *     allowed object is
     *     {@link SIPItemOper }
     *     
     */
    public void setOp(SIPItemOper value) {
        this.op = value;
    }

    /**
     * Recupera il valore della proprietà nroAllegato.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNroAllegato() {
        return nroAllegato;
    }

    /**
     * Imposta il valore della proprietà nroAllegato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNroAllegato(BigInteger value) {
        this.nroAllegato = value;
    }

    /**
     * Recupera il valore della proprietà idDocVersPrec.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdDocVersPrec() {
        return idDocVersPrec;
    }

    /**
     * Imposta il valore della proprietà idDocVersPrec.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdDocVersPrec(String value) {
        this.idDocVersPrec = value;
    }

}
