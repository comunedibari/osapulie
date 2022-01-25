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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Applicazione/sistema che versa documentazione o dati o che in qualche modo interagisce con il sistema di conservazione
 * 
 * <p>Classe Java per Applicazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="Applicazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence minOccurs="0"&gt;
 *         &lt;element name="Descrizione" type="{urn:IRIS:SIPResult.xsd}Description" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute name="nome" use="required" type="{urn:IRIS:SIPResult.xsd}Nome" /&gt;
 *       &lt;attribute name="ver" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" /&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Applicazione", propOrder = {
    "descrizione"
})
public class Applicazione {

    @XmlElement(name = "Descrizione")
    protected Description descrizione;
    @XmlAttribute(name = "nome", namespace = "urn:IRIS:SIPResult.xsd", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String nome;
    @XmlAttribute(name = "ver", namespace = "urn:IRIS:SIPResult.xsd")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NMTOKEN")
    protected String ver;

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescrizione(Description value) {
        this.descrizione = value;
    }

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
     * Recupera il valore della proprietà ver.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVer() {
        return ver;
    }

    /**
     * Imposta il valore della proprietà ver.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVer(String value) {
        this.ver = value;
    }

}
