//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:21:01 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.categorieImmobiliService;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per tb_categoriaimmobile complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="tb_categoriaimmobile"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="codice" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="descrizione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="coefficienteRivalutazione" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="coefficienteMoltiplicazione" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="percentualeProprietario" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="percentualeInquilinoComodatario" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "tb_categoriaimmobile", propOrder = {
    "id",
    "codice",
    "descrizione",
    "coefficienteRivalutazione",
    "coefficienteMoltiplicazione",
    "percentualeProprietario",
    "percentualeInquilinoComodatario"
})
public class TbCategoriaimmobile {

    @XmlElement(name = "ID", required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String codice;
    @XmlElement(required = true, nillable = true)
    protected String descrizione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double coefficienteRivalutazione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double coefficienteMoltiplicazione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double percentualeProprietario;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double percentualeInquilinoComodatario;

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getID() {
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
    public void setID(BigInteger value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà codice.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodice() {
        return codice;
    }

    /**
     * Imposta il valore della proprietà codice.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodice(String value) {
        this.codice = value;
    }

    /**
     * Recupera il valore della proprietà descrizione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescrizione() {
        return descrizione;
    }

    /**
     * Imposta il valore della proprietà descrizione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescrizione(String value) {
        this.descrizione = value;
    }

    /**
     * Recupera il valore della proprietà coefficienteRivalutazione.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCoefficienteRivalutazione() {
        return coefficienteRivalutazione;
    }

    /**
     * Imposta il valore della proprietà coefficienteRivalutazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCoefficienteRivalutazione(Double value) {
        this.coefficienteRivalutazione = value;
    }

    /**
     * Recupera il valore della proprietà coefficienteMoltiplicazione.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCoefficienteMoltiplicazione() {
        return coefficienteMoltiplicazione;
    }

    /**
     * Imposta il valore della proprietà coefficienteMoltiplicazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCoefficienteMoltiplicazione(Double value) {
        this.coefficienteMoltiplicazione = value;
    }

    /**
     * Recupera il valore della proprietà percentualeProprietario.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPercentualeProprietario() {
        return percentualeProprietario;
    }

    /**
     * Imposta il valore della proprietà percentualeProprietario.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPercentualeProprietario(Double value) {
        this.percentualeProprietario = value;
    }

    /**
     * Recupera il valore della proprietà percentualeInquilinoComodatario.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPercentualeInquilinoComodatario() {
        return percentualeInquilinoComodatario;
    }

    /**
     * Imposta il valore della proprietà percentualeInquilinoComodatario.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPercentualeInquilinoComodatario(Double value) {
        this.percentualeInquilinoComodatario = value;
    }

}
