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
 * <p>Classe Java per posizioneVisuraTassaRifiuti complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="posizioneVisuraTassaRifiuti"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="identificativoUtenza" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceVia" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codiceCivico" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="via" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="civico" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="esponente" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="localita" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="cap" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="piano" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="interno" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="scala" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="suffisso" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="superficie" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="numero" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="sezione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="foglio" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="particella" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="subalterno" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="destinazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="categoriaImmobile" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="aliquota" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="agevolazione" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="importoAgevolazione" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoRiduzione" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoTariffa" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="importoDaPagare" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="addizionaleComunale" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="maggiorazioneStato" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="addizionaleProvinciale" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="dataInizioOccupazione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="dataFineOccupazione" type="{http://www.w3.org/2001/XMLSchema}date"/&gt;
 *         &lt;element name="idVisura" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "posizioneVisuraTassaRifiuti", propOrder = {
    "id",
    "identificativoUtenza",
    "codiceVia",
    "codiceCivico",
    "via",
    "civico",
    "esponente",
    "localita",
    "cap",
    "piano",
    "interno",
    "scala",
    "suffisso",
    "superficie",
    "numero",
    "sezione",
    "foglio",
    "particella",
    "subalterno",
    "destinazione",
    "tipo",
    "categoriaImmobile",
    "aliquota",
    "agevolazione",
    "importoAgevolazione",
    "importoRiduzione",
    "importoTariffa",
    "importoDaPagare",
    "addizionaleComunale",
    "maggiorazioneStato",
    "addizionaleProvinciale",
    "dataInizioOccupazione",
    "dataFineOccupazione",
    "idVisura"
})
public class PosizioneVisuraTassaRifiuti {

    @XmlElement(required = true, nillable = true)
    protected BigInteger id;
    @XmlElement(required = true, nillable = true)
    protected String identificativoUtenza;
    @XmlElement(required = true, nillable = true)
    protected String codiceVia;
    @XmlElement(required = true, nillable = true)
    protected String codiceCivico;
    @XmlElement(required = true, nillable = true)
    protected String via;
    @XmlElement(required = true, nillable = true)
    protected BigInteger civico;
    @XmlElement(required = true, nillable = true)
    protected String esponente;
    @XmlElement(required = true, nillable = true)
    protected String localita;
    @XmlElement(required = true, nillable = true)
    protected String cap;
    @XmlElement(required = true, nillable = true)
    protected String piano;
    @XmlElement(required = true, nillable = true)
    protected String interno;
    @XmlElement(required = true, nillable = true)
    protected String scala;
    @XmlElement(required = true, nillable = true)
    protected String suffisso;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double superficie;
    @XmlElement(required = true, nillable = true)
    protected BigInteger numero;
    @XmlElement(required = true, nillable = true)
    protected String sezione;
    @XmlElement(required = true, nillable = true)
    protected BigInteger foglio;
    @XmlElement(required = true, nillable = true)
    protected BigInteger particella;
    @XmlElement(required = true, nillable = true)
    protected BigInteger subalterno;
    @XmlElement(required = true, nillable = true)
    protected String destinazione;
    @XmlElement(required = true, nillable = true)
    protected String tipo;
    @XmlElement(required = true, nillable = true)
    protected String categoriaImmobile;
    @XmlElement(required = true, nillable = true)
    protected String aliquota;
    @XmlElement(required = true, nillable = true)
    protected String agevolazione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoAgevolazione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoRiduzione;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoTariffa;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double importoDaPagare;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double addizionaleComunale;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double maggiorazioneStato;
    @XmlElement(required = true, type = Double.class, nillable = true)
    protected Double addizionaleProvinciale;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataInizioOccupazione;
    @XmlElement(required = true, nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataFineOccupazione;
    @XmlElement(required = true, nillable = true)
    protected BigInteger idVisura;

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
     * Recupera il valore della proprietà identificativoUtenza.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUtenza() {
        return identificativoUtenza;
    }

    /**
     * Imposta il valore della proprietà identificativoUtenza.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUtenza(String value) {
        this.identificativoUtenza = value;
    }

    /**
     * Recupera il valore della proprietà codiceVia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceVia() {
        return codiceVia;
    }

    /**
     * Imposta il valore della proprietà codiceVia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceVia(String value) {
        this.codiceVia = value;
    }

    /**
     * Recupera il valore della proprietà codiceCivico.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceCivico() {
        return codiceCivico;
    }

    /**
     * Imposta il valore della proprietà codiceCivico.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceCivico(String value) {
        this.codiceCivico = value;
    }

    /**
     * Recupera il valore della proprietà via.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVia() {
        return via;
    }

    /**
     * Imposta il valore della proprietà via.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVia(String value) {
        this.via = value;
    }

    /**
     * Recupera il valore della proprietà civico.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCivico() {
        return civico;
    }

    /**
     * Imposta il valore della proprietà civico.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCivico(BigInteger value) {
        this.civico = value;
    }

    /**
     * Recupera il valore della proprietà esponente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsponente() {
        return esponente;
    }

    /**
     * Imposta il valore della proprietà esponente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsponente(String value) {
        this.esponente = value;
    }

    /**
     * Recupera il valore della proprietà localita.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalita() {
        return localita;
    }

    /**
     * Imposta il valore della proprietà localita.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalita(String value) {
        this.localita = value;
    }

    /**
     * Recupera il valore della proprietà cap.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCap() {
        return cap;
    }

    /**
     * Imposta il valore della proprietà cap.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCap(String value) {
        this.cap = value;
    }

    /**
     * Recupera il valore della proprietà piano.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPiano() {
        return piano;
    }

    /**
     * Imposta il valore della proprietà piano.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPiano(String value) {
        this.piano = value;
    }

    /**
     * Recupera il valore della proprietà interno.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInterno() {
        return interno;
    }

    /**
     * Imposta il valore della proprietà interno.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInterno(String value) {
        this.interno = value;
    }

    /**
     * Recupera il valore della proprietà scala.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScala() {
        return scala;
    }

    /**
     * Imposta il valore della proprietà scala.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScala(String value) {
        this.scala = value;
    }

    /**
     * Recupera il valore della proprietà suffisso.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSuffisso() {
        return suffisso;
    }

    /**
     * Imposta il valore della proprietà suffisso.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSuffisso(String value) {
        this.suffisso = value;
    }

    /**
     * Recupera il valore della proprietà superficie.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSuperficie() {
        return superficie;
    }

    /**
     * Imposta il valore della proprietà superficie.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSuperficie(Double value) {
        this.superficie = value;
    }

    /**
     * Recupera il valore della proprietà numero.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getNumero() {
        return numero;
    }

    /**
     * Imposta il valore della proprietà numero.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setNumero(BigInteger value) {
        this.numero = value;
    }

    /**
     * Recupera il valore della proprietà sezione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSezione() {
        return sezione;
    }

    /**
     * Imposta il valore della proprietà sezione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSezione(String value) {
        this.sezione = value;
    }

    /**
     * Recupera il valore della proprietà foglio.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFoglio() {
        return foglio;
    }

    /**
     * Imposta il valore della proprietà foglio.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFoglio(BigInteger value) {
        this.foglio = value;
    }

    /**
     * Recupera il valore della proprietà particella.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getParticella() {
        return particella;
    }

    /**
     * Imposta il valore della proprietà particella.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setParticella(BigInteger value) {
        this.particella = value;
    }

    /**
     * Recupera il valore della proprietà subalterno.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getSubalterno() {
        return subalterno;
    }

    /**
     * Imposta il valore della proprietà subalterno.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setSubalterno(BigInteger value) {
        this.subalterno = value;
    }

    /**
     * Recupera il valore della proprietà destinazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinazione() {
        return destinazione;
    }

    /**
     * Imposta il valore della proprietà destinazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinazione(String value) {
        this.destinazione = value;
    }

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipo(String value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà categoriaImmobile.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoriaImmobile() {
        return categoriaImmobile;
    }

    /**
     * Imposta il valore della proprietà categoriaImmobile.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoriaImmobile(String value) {
        this.categoriaImmobile = value;
    }

    /**
     * Recupera il valore della proprietà aliquota.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAliquota() {
        return aliquota;
    }

    /**
     * Imposta il valore della proprietà aliquota.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAliquota(String value) {
        this.aliquota = value;
    }

    /**
     * Recupera il valore della proprietà agevolazione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgevolazione() {
        return agevolazione;
    }

    /**
     * Imposta il valore della proprietà agevolazione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgevolazione(String value) {
        this.agevolazione = value;
    }

    /**
     * Recupera il valore della proprietà importoAgevolazione.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoAgevolazione() {
        return importoAgevolazione;
    }

    /**
     * Imposta il valore della proprietà importoAgevolazione.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoAgevolazione(Double value) {
        this.importoAgevolazione = value;
    }

    /**
     * Recupera il valore della proprietà importoRiduzione.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoRiduzione() {
        return importoRiduzione;
    }

    /**
     * Imposta il valore della proprietà importoRiduzione.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoRiduzione(Double value) {
        this.importoRiduzione = value;
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
     * Recupera il valore della proprietà importoDaPagare.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getImportoDaPagare() {
        return importoDaPagare;
    }

    /**
     * Imposta il valore della proprietà importoDaPagare.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setImportoDaPagare(Double value) {
        this.importoDaPagare = value;
    }

    /**
     * Recupera il valore della proprietà addizionaleComunale.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAddizionaleComunale() {
        return addizionaleComunale;
    }

    /**
     * Imposta il valore della proprietà addizionaleComunale.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAddizionaleComunale(Double value) {
        this.addizionaleComunale = value;
    }

    /**
     * Recupera il valore della proprietà maggiorazioneStato.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMaggiorazioneStato() {
        return maggiorazioneStato;
    }

    /**
     * Imposta il valore della proprietà maggiorazioneStato.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMaggiorazioneStato(Double value) {
        this.maggiorazioneStato = value;
    }

    /**
     * Recupera il valore della proprietà addizionaleProvinciale.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAddizionaleProvinciale() {
        return addizionaleProvinciale;
    }

    /**
     * Imposta il valore della proprietà addizionaleProvinciale.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAddizionaleProvinciale(Double value) {
        this.addizionaleProvinciale = value;
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
     * Recupera il valore della proprietà idVisura.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getIdVisura() {
        return idVisura;
    }

    /**
     * Imposta il valore della proprietà idVisura.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setIdVisura(BigInteger value) {
        this.idVisura = value;
    }

}
