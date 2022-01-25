//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * Estremi con cui uno schema XML - xsd - è identificato all'interno del sistema di conservazione
 * 
 * <p>Classe Java per XMLSchema complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="XMLSchema"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;attribute name="nome" use="required" type="{urn:IRIS:SIPManifest.xsd}NomeSchema" /&gt;
 *       &lt;attribute name="majorVer" use="required" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" /&gt;
 *       &lt;attribute name="minorVer" use="required" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XMLSchema")
public class XMLSchema {

    @XmlAttribute(name = "nome", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    protected String nome;
    @XmlAttribute(name = "majorVer", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger majorVer;
    @XmlAttribute(name = "minorVer", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
    @XmlSchemaType(name = "nonNegativeInteger")
    protected BigInteger minorVer;

    /**
     * Recupera il valore della proprietà nome.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNome() {
        return nome;
    }

    /**
     * Imposta il valore della proprietà nome.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNome(String value) {
        this.nome = value;
    }

    /**
     * Recupera il valore della proprietà majorVer.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMajorVer() {
        return majorVer;
    }

    /**
     * Imposta il valore della proprietà majorVer.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMajorVer(BigInteger value) {
        this.majorVer = value;
    }

    /**
     * Recupera il valore della proprietà minorVer.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinorVer() {
        return minorVer;
    }

    /**
     * Imposta il valore della proprietà minorVer.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinorVer(BigInteger value) {
        this.minorVer = value;
    }

}
