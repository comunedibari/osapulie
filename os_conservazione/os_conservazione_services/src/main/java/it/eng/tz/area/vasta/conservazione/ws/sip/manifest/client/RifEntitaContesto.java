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
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Estremi di un'entità (tipologia documentale, tipo di unità di aggregazione, tipo di procedimento amministrativo ecc) del contesto archivistico o procedurale
 * 
 * <p>Classe Java per RifEntitaContesto complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="RifEntitaContesto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;element name="RegId" type="{urn:IRIS:SIPManifest.xsd}UUID"/&gt;
 *         &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
 *         &lt;element name="Intestazione" type="{urn:IRIS:SIPManifest.xsd}NomeEntitaContesto"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RifEntitaContesto", propOrder = {
    "regId",
    "id",
    "intestazione"
})
@XmlSeeAlso({
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.UAStdInfo.Tipo.class,
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.UAStdInfo.TipoContenuti.class,
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.SDStdInfo.Tipo.class,
    it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client.UCStdInfo.Tipo.class
})
public class RifEntitaContesto {

    @XmlElement(name = "RegId")
    protected String regId;
    @XmlElement(name = "Id")
    protected String id;
    @XmlElement(name = "Intestazione")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected String intestazione;

    /**
     * Recupera il valore della proprietà regId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegId() {
        return regId;
    }

    /**
     * Imposta il valore della proprietà regId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegId(String value) {
        this.regId = value;
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

}
