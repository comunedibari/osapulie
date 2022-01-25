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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Termine e note sulla conservazione della documentazione
 * 
 * <p>Classe Java per DatiConservazione complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="DatiConservazione"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice minOccurs="0"&gt;
 *           &lt;element name="ConservazioneFinoAl" type="{urn:IRIS:SIPManifest.xsd}TermineConservazione"/&gt;
 *           &lt;element name="ConservazionePerAnni"&gt;
 *             &lt;simpleType&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger"&gt;
 *                 &lt;maxInclusive value="999"/&gt;
 *               &lt;/restriction&gt;
 *             &lt;/simpleType&gt;
 *           &lt;/element&gt;
 *           &lt;element name="ConservazioneIllimitata" type="{urn:IRIS:SIPManifest.xsd}FlagOn"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element name="NoteConservazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiConservazione", propOrder = {
    "conservazioneFinoAl",
    "conservazionePerAnni",
    "conservazioneIllimitata",
    "noteConservazione"
})
public class DatiConservazione {

    @XmlElement(name = "ConservazioneFinoAl")
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar conservazioneFinoAl;
    @XmlElement(name = "ConservazionePerAnni")
    protected Integer conservazionePerAnni;
    @XmlElement(name = "ConservazioneIllimitata")
    @XmlSchemaType(name = "positiveInteger")
    protected BigInteger conservazioneIllimitata;
    @XmlElement(name = "NoteConservazione")
    protected String noteConservazione;

    /**
     * Recupera il valore della proprietà conservazioneFinoAl.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getConservazioneFinoAl() {
        return conservazioneFinoAl;
    }

    /**
     * Imposta il valore della proprietà conservazioneFinoAl.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setConservazioneFinoAl(XMLGregorianCalendar value) {
        this.conservazioneFinoAl = value;
    }

    /**
     * Recupera il valore della proprietà conservazionePerAnni.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getConservazionePerAnni() {
        return conservazionePerAnni;
    }

    /**
     * Imposta il valore della proprietà conservazionePerAnni.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setConservazionePerAnni(Integer value) {
        this.conservazionePerAnni = value;
    }

    /**
     * Recupera il valore della proprietà conservazioneIllimitata.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getConservazioneIllimitata() {
        return conservazioneIllimitata;
    }

    /**
     * Imposta il valore della proprietà conservazioneIllimitata.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setConservazioneIllimitata(BigInteger value) {
        this.conservazioneIllimitata = value;
    }

    /**
     * Recupera il valore della proprietà noteConservazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoteConservazione() {
        return noteConservazione;
    }

    /**
     * Imposta il valore della proprietà noteConservazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoteConservazione(String value) {
        this.noteConservazione = value;
    }

}
