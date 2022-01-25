//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.31 alle 06:29:40 PM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.result.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Indica chi è il produttore - come persona giuridica - della documentazione contenuta nel SIP
 * 
 * <p>Classe Java per ProduttoreSIP complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ProduttoreSIP"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo" type="{urn:IRIS:SIPResult.xsd}FormaGiuridica"/&gt;
 *         &lt;element name="Intestazione" type="{urn:IRIS:SIPResult.xsd}Nome"/&gt;
 *         &lt;element name="CodFiscale" type="{urn:IRIS:SIPResult.xsd}CodiceFiscale" minOccurs="0"/&gt;
 *         &lt;element name="PartitaIVA" type="{urn:IRIS:SIPResult.xsd}PartitaIVA" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="CodiceAmministrazione" type="{urn:IRIS:SIPResult.xsd}CodiceAmministrazione" /&gt;
 *       &lt;attribute name="CodiceAOO" type="{urn:IRIS:SIPResult.xsd}CodiceAOO" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProduttoreSIP", propOrder = {
    "tipo",
    "intestazione",
    "codFiscale",
    "partitaIVA"
})
public class ProduttoreSIP {

    @XmlElement(name = "Tipo", required = true, defaultValue = "altro")
    @XmlSchemaType(name = "string")
    protected FormaGiuridica tipo;
    @XmlElement(name = "Intestazione", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String intestazione;
    @XmlElement(name = "CodFiscale")
    protected String codFiscale;
    @XmlElement(name = "PartitaIVA")
    protected String partitaIVA;
    @XmlAttribute(name = "CodiceAmministrazione", namespace = "urn:IRIS:SIPResult.xsd")
    protected String codiceAmministrazione;
    @XmlAttribute(name = "CodiceAOO", namespace = "urn:IRIS:SIPResult.xsd")
    protected String codiceAOO;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link FormaGiuridica }
     *     
     */
    public FormaGiuridica getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link FormaGiuridica }
     *     
     */
    public void setTipo(FormaGiuridica value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà intestazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIntestazione() {
        return intestazione;
    }

    /**
     * Imposta il valore della proprietà intestazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIntestazione(String value) {
        this.intestazione = value;
    }

    /**
     * Recupera il valore della proprietà codFiscale.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodFiscale() {
        return codFiscale;
    }

    /**
     * Imposta il valore della proprietà codFiscale.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodFiscale(String value) {
        this.codFiscale = value;
    }

    /**
     * Recupera il valore della proprietà partitaIVA.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartitaIVA() {
        return partitaIVA;
    }

    /**
     * Imposta il valore della proprietà partitaIVA.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartitaIVA(String value) {
        this.partitaIVA = value;
    }

    /**
     * Recupera il valore della proprietà codiceAmministrazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAmministrazione() {
        return codiceAmministrazione;
    }

    /**
     * Imposta il valore della proprietà codiceAmministrazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAmministrazione(String value) {
        this.codiceAmministrazione = value;
    }

    /**
     * Recupera il valore della proprietà codiceAOO.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceAOO() {
        return codiceAOO;
    }

    /**
     * Imposta il valore della proprietà codiceAOO.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceAOO(String value) {
        this.codiceAOO = value;
    }

}
