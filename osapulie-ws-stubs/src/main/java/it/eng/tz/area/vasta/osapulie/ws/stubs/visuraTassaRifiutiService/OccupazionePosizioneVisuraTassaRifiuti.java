//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:49:08 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaRifiutiService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per occupazionePosizioneVisuraTassaRifiuti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="occupazionePosizioneVisuraTassaRifiuti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dataInizioOccupazione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataFineOccupazione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="numeroComponenti" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="importoTariffa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="idPosizione" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "occupazionePosizioneVisuraTassaRifiuti", propOrder = {
    "id",
    "dataInizioOccupazione",
    "dataFineOccupazione",
    "numeroComponenti",
    "importoTariffa",
    "idPosizione"
})
public class OccupazionePosizioneVisuraTassaRifiuti {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioOccupazione;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineOccupazione;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numeroComponenti;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoTariffa;
    @XmlElement(required = true, nillable = true)
    protected BigInteger idPosizione;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà dataInizioOccupazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataInizioOccupazione() {
        return dataInizioOccupazione;
    }

    /**
     * Imposta il valore della proprietà dataInizioOccupazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataInizioOccupazione(XMLGregorianCalendar value) {
        this.dataInizioOccupazione = value;
    }

    /**
     * Recupera il valore della proprietà dataFineOccupazione.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataFineOccupazione() {
        return dataFineOccupazione;
    }

    /**
     * Imposta il valore della proprietà dataFineOccupazione.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataFineOccupazione(XMLGregorianCalendar value) {
        this.dataFineOccupazione = value;
    }

    /**
     * Recupera il valore della proprietà numeroComponenti.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumeroComponenti() {
        return numeroComponenti;
    }

    /**
     * Imposta il valore della proprietà numeroComponenti.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumeroComponenti(BigInteger value) {
        this.numeroComponenti = value;
    }

    /**
     * Recupera il valore della proprietà importoTariffa.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoTariffa() {
        return importoTariffa;
    }

    /**
     * Imposta il valore della proprietà importoTariffa.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoTariffa(Double value) {
        this.importoTariffa = value;
    }

    /**
     * Recupera il valore della proprietà idPosizione.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdPosizione() {
        return idPosizione;
    }

    /**
     * Imposta il valore della proprietà idPosizione.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdPosizione(BigInteger value) {
        this.idPosizione = value;
    }

}
