//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Estremi di una registrazione (es. a Protocollo Generale) o numerazione di un documento
 * 
 * <p>Classe Java per RegistrazioneNumerazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RegistrazioneNumerazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CategoriaReg" type="{urn:IRIS:SIPManifest.xsd}CategoriaRegistrazione"/&gt;
 *         &lt;element name="SiglaRegistro" type="{urn:IRIS:SIPManifest.xsd}SiglaRegistro" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element name="AnnoReg" type="{urn:IRIS:SIPManifest.xsd}YearRef"/&gt;
 *           &lt;element name="DataOraReg" type="{urn:IRIS:SIPManifest.xsd}DateRef"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="NroReg"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{urn:IRIS:SIPManifest.xsd}NroRegistrazione"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RegistrazioneNumerazione", propOrder = {
    "categoriaReg",
    "siglaRegistro",
    "annoReg",
    "dataOraReg",
    "nroReg"
})
public class RegistrazioneNumerazione {

    @XmlElement(name = "CategoriaReg", required = true)
    @XmlSchemaType(name = "string")
    protected CategoriaRegistrazione categoriaReg;
    @XmlElement(name = "SiglaRegistro")
    protected String siglaRegistro;
    @XmlElement(name = "AnnoReg")
    @XmlSchemaType(name = "gYear")
    protected XMLGregorianCalendar annoReg;
    @XmlElement(name = "DataOraReg")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataOraReg;
    @XmlElement(name = "NroReg")
    protected int nroReg;

    /**
     * Recupera il valore della proprietà categoriaReg.
     * 
     * @return
     *     possible object is
     *     {@link CategoriaRegistrazione }
     *     
     */
    public CategoriaRegistrazione getCategoriaReg() {
        return categoriaReg;
    }

    /**
     * Imposta il valore della proprietà categoriaReg.
     * 
     * @param value
     *     allowed object is
     *     {@link CategoriaRegistrazione }
     *     
     */
    public void setCategoriaReg(CategoriaRegistrazione value) {
        this.categoriaReg = value;
    }

    /**
     * Recupera il valore della proprietà siglaRegistro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSiglaRegistro() {
        return siglaRegistro;
    }

    /**
     * Imposta il valore della proprietà siglaRegistro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSiglaRegistro(String value) {
        this.siglaRegistro = value;
    }

    /**
     * Recupera il valore della proprietà annoReg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAnnoReg() {
        return annoReg;
    }

    /**
     * Imposta il valore della proprietà annoReg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAnnoReg(XMLGregorianCalendar value) {
        this.annoReg = value;
    }

    /**
     * Recupera il valore della proprietà dataOraReg.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataOraReg() {
        return dataOraReg;
    }

    /**
     * Imposta il valore della proprietà dataOraReg.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataOraReg(XMLGregorianCalendar value) {
        this.dataOraReg = value;
    }

    /**
     * Recupera il valore della proprietà nroReg.
     * 
     */
    public int getNroReg() {
        return nroReg;
    }

    /**
     * Imposta il valore della proprietà nroReg.
     * 
     */
    public void setNroReg(int value) {
        this.nroReg = value;
    }

}
