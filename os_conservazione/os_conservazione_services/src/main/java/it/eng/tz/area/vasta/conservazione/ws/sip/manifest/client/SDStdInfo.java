//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.29 alle 09:06:59 AM CET 
//


package it.eng.tz.area.vasta.conservazione.ws.sip.manifest.client;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * Dati di descrizione di una scheda documento espressi secondo tracciato standard
 * 
 * <p>Classe Java per SDStdInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="SDStdInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Identificativo" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="FormaNativa" type="{urn:IRIS:SIPManifest.xsd}Label"/&gt;
 *                   &lt;element name="ConvertitaXOrdinamento" type="{urn:IRIS:SIPManifest.xsd}Label" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RegistrazioniNumerazioni" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Registrazione" type="{urn:IRIS:SIPManifest.xsd}RegistrazioneNumerazione" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="RifCronologici" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="DataRif" type="{urn:IRIS:SIPManifest.xsd}DateRefTipizzato" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="DesOgg" type="{urn:IRIS:SIPManifest.xsd}LongDescription"/&gt;
 *         &lt;element name="DigDocs" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}DocStdInfo"&gt;
 *                 &lt;sequence minOccurs="0"&gt;
 *                   &lt;element name="ComponentiDigitali" type="{urn:IRIS:SIPManifest.xsd}ComponentiDigitaliDoc" minOccurs="0"/&gt;
 *                   &lt;element name="GruppoDocInVolume" type="{urn:IRIS:SIPManifest.xsd}GruppoDigDoc" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="AnalogDocs" type="{urn:IRIS:SIPManifest.xsd}DocStdInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SezioneArchivio" type="{urn:IRIS:SIPManifest.xsd}SezioneArchivio" minOccurs="0"/&gt;
 *         &lt;element name="ProduttoreOrig" type="{urn:IRIS:SIPManifest.xsd}ProduttoreDoc" minOccurs="0"/&gt;
 *         &lt;element name="Classifica" type="{urn:IRIS:SIPManifest.xsd}VocePianoClassificazione" minOccurs="0"/&gt;
 *         &lt;element name="UACorrelate" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="UA" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
 *                             &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
 *                             &lt;element name="RuoloUACorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="UCAppartenenza" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="UCId" type="{urn:IRIS:SIPManifest.xsd}ProvId" maxOccurs="unbounded"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SDCorrelate" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SD" maxOccurs="unbounded"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
 *                             &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
 *                             &lt;element name="RuoloSDCorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Conservazione" type="{urn:IRIS:SIPManifest.xsd}DatiConservazione"/&gt;
 *         &lt;element name="Annotazioni" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
 *         &lt;element name="AttributoCustom" type="{urn:IRIS:SIPManifest.xsd}ValoriAttributo" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SDStdInfo", propOrder = {
    "tipo",
    "identificativo",
    "registrazioniNumerazioni",
    "rifCronologici",
    "desOgg",
    "digDocs",
    "analogDocs",
    "sezioneArchivio",
    "produttoreOrig",
    "classifica",
    "uaCorrelate",
    "ucAppartenenza",
    "sdCorrelate",
    "conservazione",
    "annotazioni",
    "attributoCustom"
})
public class SDStdInfo {

    @XmlElement(name = "Tipo")
    protected SDStdInfo.Tipo tipo;
    @XmlElement(name = "Identificativo")
    protected SDStdInfo.Identificativo identificativo;
    @XmlElement(name = "RegistrazioniNumerazioni")
    protected SDStdInfo.RegistrazioniNumerazioni registrazioniNumerazioni;
    @XmlElement(name = "RifCronologici")
    protected SDStdInfo.RifCronologici rifCronologici;
    @XmlElement(name = "DesOgg", required = true)
    protected LongDescription desOgg;
    @XmlElement(name = "DigDocs")
    protected List<SDStdInfo.DigDocs> digDocs;
    @XmlElement(name = "AnalogDocs")
    protected List<DocStdInfo> analogDocs;
    @XmlElement(name = "SezioneArchivio", defaultValue = "C")
    @XmlSchemaType(name = "string")
    protected SezioneArchivio sezioneArchivio;
    @XmlElement(name = "ProduttoreOrig")
    protected ProduttoreDoc produttoreOrig;
    @XmlElement(name = "Classifica")
    protected VocePianoClassificazione classifica;
    @XmlElement(name = "UACorrelate")
    protected SDStdInfo.UACorrelate uaCorrelate;
    @XmlElement(name = "UCAppartenenza")
    protected SDStdInfo.UCAppartenenza ucAppartenenza;
    @XmlElement(name = "SDCorrelate")
    protected SDStdInfo.SDCorrelate sdCorrelate;
    @XmlElement(name = "Conservazione", required = true)
    protected DatiConservazione conservazione;
    @XmlElement(name = "Annotazioni")
    protected LongDescription annotazioni;
    @XmlElement(name = "AttributoCustom")
    protected List<ValoriAttributo> attributoCustom;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.Tipo }
     *     
     */
    public SDStdInfo.Tipo getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.Tipo }
     *     
     */
    public void setTipo(SDStdInfo.Tipo value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà identificativo.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.Identificativo }
     *     
     */
    public SDStdInfo.Identificativo getIdentificativo() {
        return identificativo;
    }

    /**
     * Imposta il valore della proprietà identificativo.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.Identificativo }
     *     
     */
    public void setIdentificativo(SDStdInfo.Identificativo value) {
        this.identificativo = value;
    }

    /**
     * Recupera il valore della proprietà registrazioniNumerazioni.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.RegistrazioniNumerazioni }
     *     
     */
    public SDStdInfo.RegistrazioniNumerazioni getRegistrazioniNumerazioni() {
        return registrazioniNumerazioni;
    }

    /**
     * Imposta il valore della proprietà registrazioniNumerazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.RegistrazioniNumerazioni }
     *     
     */
    public void setRegistrazioniNumerazioni(SDStdInfo.RegistrazioniNumerazioni value) {
        this.registrazioniNumerazioni = value;
    }

    /**
     * Recupera il valore della proprietà rifCronologici.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.RifCronologici }
     *     
     */
    public SDStdInfo.RifCronologici getRifCronologici() {
        return rifCronologici;
    }

    /**
     * Imposta il valore della proprietà rifCronologici.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.RifCronologici }
     *     
     */
    public void setRifCronologici(SDStdInfo.RifCronologici value) {
        this.rifCronologici = value;
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
     * Gets the value of the digDocs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the digDocs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDigDocs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SDStdInfo.DigDocs }
     * 
     * 
     */
    public List<SDStdInfo.DigDocs> getDigDocs() {
        if (digDocs == null) {
            digDocs = new ArrayList<SDStdInfo.DigDocs>();
        }
        return this.digDocs;
    }

    /**
     * Gets the value of the analogDocs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the analogDocs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAnalogDocs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DocStdInfo }
     * 
     * 
     */
    public List<DocStdInfo> getAnalogDocs() {
        if (analogDocs == null) {
            analogDocs = new ArrayList<DocStdInfo>();
        }
        return this.analogDocs;
    }

    /**
     * Recupera il valore della proprietà sezioneArchivio.
     * 
     * @return
     *     possible object is
     *     {@link SezioneArchivio }
     *     
     */
    public SezioneArchivio getSezioneArchivio() {
        return sezioneArchivio;
    }

    /**
     * Imposta il valore della proprietà sezioneArchivio.
     * 
     * @param value
     *     allowed object is
     *     {@link SezioneArchivio }
     *     
     */
    public void setSezioneArchivio(SezioneArchivio value) {
        this.sezioneArchivio = value;
    }

    /**
     * Recupera il valore della proprietà produttoreOrig.
     * 
     * @return
     *     possible object is
     *     {@link ProduttoreDoc }
     *     
     */
    public ProduttoreDoc getProduttoreOrig() {
        return produttoreOrig;
    }

    /**
     * Imposta il valore della proprietà produttoreOrig.
     * 
     * @param value
     *     allowed object is
     *     {@link ProduttoreDoc }
     *     
     */
    public void setProduttoreOrig(ProduttoreDoc value) {
        this.produttoreOrig = value;
    }

    /**
     * Recupera il valore della proprietà classifica.
     * 
     * @return
     *     possible object is
     *     {@link VocePianoClassificazione }
     *     
     */
    public VocePianoClassificazione getClassifica() {
        return classifica;
    }

    /**
     * Imposta il valore della proprietà classifica.
     * 
     * @param value
     *     allowed object is
     *     {@link VocePianoClassificazione }
     *     
     */
    public void setClassifica(VocePianoClassificazione value) {
        this.classifica = value;
    }

    /**
     * Recupera il valore della proprietà uaCorrelate.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.UACorrelate }
     *     
     */
    public SDStdInfo.UACorrelate getUACorrelate() {
        return uaCorrelate;
    }

    /**
     * Imposta il valore della proprietà uaCorrelate.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.UACorrelate }
     *     
     */
    public void setUACorrelate(SDStdInfo.UACorrelate value) {
        this.uaCorrelate = value;
    }

    /**
     * Recupera il valore della proprietà ucAppartenenza.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.UCAppartenenza }
     *     
     */
    public SDStdInfo.UCAppartenenza getUCAppartenenza() {
        return ucAppartenenza;
    }

    /**
     * Imposta il valore della proprietà ucAppartenenza.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.UCAppartenenza }
     *     
     */
    public void setUCAppartenenza(SDStdInfo.UCAppartenenza value) {
        this.ucAppartenenza = value;
    }

    /**
     * Recupera il valore della proprietà sdCorrelate.
     * 
     * @return
     *     possible object is
     *     {@link SDStdInfo.SDCorrelate }
     *     
     */
    public SDStdInfo.SDCorrelate getSDCorrelate() {
        return sdCorrelate;
    }

    /**
     * Imposta il valore della proprietà sdCorrelate.
     * 
     * @param value
     *     allowed object is
     *     {@link SDStdInfo.SDCorrelate }
     *     
     */
    public void setSDCorrelate(SDStdInfo.SDCorrelate value) {
        this.sdCorrelate = value;
    }

    /**
     * Recupera il valore della proprietà conservazione.
     * 
     * @return
     *     possible object is
     *     {@link DatiConservazione }
     *     
     */
    public DatiConservazione getConservazione() {
        return conservazione;
    }

    /**
     * Imposta il valore della proprietà conservazione.
     * 
     * @param value
     *     allowed object is
     *     {@link DatiConservazione }
     *     
     */
    public void setConservazione(DatiConservazione value) {
        this.conservazione = value;
    }

    /**
     * Recupera il valore della proprietà annotazioni.
     * 
     * @return
     *     possible object is
     *     {@link LongDescription }
     *     
     */
    public LongDescription getAnnotazioni() {
        return annotazioni;
    }

    /**
     * Imposta il valore della proprietà annotazioni.
     * 
     * @param value
     *     allowed object is
     *     {@link LongDescription }
     *     
     */
    public void setAnnotazioni(LongDescription value) {
        this.annotazioni = value;
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
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{urn:IRIS:SIPManifest.xsd}DocStdInfo"&gt;
     *       &lt;sequence minOccurs="0"&gt;
     *         &lt;element name="ComponentiDigitali" type="{urn:IRIS:SIPManifest.xsd}ComponentiDigitaliDoc" minOccurs="0"/&gt;
     *         &lt;element name="GruppoDocInVolume" type="{urn:IRIS:SIPManifest.xsd}GruppoDigDoc" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "componentiDigitali",
        "gruppoDocInVolume"
    })
    public static class DigDocs
        extends DocStdInfo
    {

        @XmlElement(name = "ComponentiDigitali")
        protected ComponentiDigitaliDoc componentiDigitali;
        @XmlElement(name = "GruppoDocInVolume")
        protected String gruppoDocInVolume;

        /**
         * Recupera il valore della proprietà componentiDigitali.
         * 
         * @return
         *     possible object is
         *     {@link ComponentiDigitaliDoc }
         *     
         */
        public ComponentiDigitaliDoc getComponentiDigitali() {
            return componentiDigitali;
        }

        /**
         * Imposta il valore della proprietà componentiDigitali.
         * 
         * @param value
         *     allowed object is
         *     {@link ComponentiDigitaliDoc }
         *     
         */
        public void setComponentiDigitali(ComponentiDigitaliDoc value) {
            this.componentiDigitali = value;
        }

        /**
         * Recupera il valore della proprietà gruppoDocInVolume.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getGruppoDocInVolume() {
            return gruppoDocInVolume;
        }

        /**
         * Imposta il valore della proprietà gruppoDocInVolume.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setGruppoDocInVolume(String value) {
            this.gruppoDocInVolume = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="FormaNativa" type="{urn:IRIS:SIPManifest.xsd}Label"/&gt;
     *         &lt;element name="ConvertitaXOrdinamento" type="{urn:IRIS:SIPManifest.xsd}Label" minOccurs="0"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "formaNativa",
        "convertitaXOrdinamento"
    })
    public static class Identificativo {

        @XmlElement(name = "FormaNativa", required = true)
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String formaNativa;
        @XmlElement(name = "ConvertitaXOrdinamento")
        @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
        @XmlSchemaType(name = "normalizedString")
        protected String convertitaXOrdinamento;

        /**
         * Recupera il valore della proprietà formaNativa.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormaNativa() {
            return formaNativa;
        }

        /**
         * Imposta il valore della proprietà formaNativa.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormaNativa(String value) {
            this.formaNativa = value;
        }

        /**
         * Recupera il valore della proprietà convertitaXOrdinamento.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getConvertitaXOrdinamento() {
            return convertitaXOrdinamento;
        }

        /**
         * Imposta il valore della proprietà convertitaXOrdinamento.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setConvertitaXOrdinamento(String value) {
            this.convertitaXOrdinamento = value;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Registrazione" type="{urn:IRIS:SIPManifest.xsd}RegistrazioneNumerazione" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "registrazione"
    })
    public static class RegistrazioniNumerazioni {

        @XmlElement(name = "Registrazione", required = true)
        protected List<RegistrazioneNumerazione> registrazione;

        /**
         * Gets the value of the registrazione property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the registrazione property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRegistrazione().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RegistrazioneNumerazione }
         * 
         * 
         */
        public List<RegistrazioneNumerazione> getRegistrazione() {
            if (registrazione == null) {
                registrazione = new ArrayList<RegistrazioneNumerazione>();
            }
            return this.registrazione;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="DataRif" type="{urn:IRIS:SIPManifest.xsd}DateRefTipizzato" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "dataRif"
    })
    public static class RifCronologici {

        @XmlElement(name = "DataRif", required = true)
        protected List<DateRefTipizzato> dataRif;

        /**
         * Gets the value of the dataRif property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the dataRif property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getDataRif().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DateRefTipizzato }
         * 
         * 
         */
        public List<DateRefTipizzato> getDataRif() {
            if (dataRif == null) {
                dataRif = new ArrayList<DateRefTipizzato>();
            }
            return this.dataRif;
        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="SD" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
     *                   &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
     *                   &lt;element name="RuoloSDCorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
    @XmlType(name = "", propOrder = {
        "sd"
    })
    public static class SDCorrelate {

        @XmlElement(name = "SD", required = true)
        protected List<SDStdInfo.SDCorrelate.SD> sd;

        /**
         * Gets the value of the sd property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the sd property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getSD().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SDStdInfo.SDCorrelate.SD }
         * 
         * 
         */
        public List<SDStdInfo.SDCorrelate.SD> getSD() {
            if (sd == null) {
                sd = new ArrayList<SDStdInfo.SDCorrelate.SD>();
            }
            return this.sd;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
         *         &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
         *         &lt;element name="RuoloSDCorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "id",
            "categoriaRel",
            "ruoloSDCorrelata"
        })
        public static class SD {

            @XmlElement(name = "Id", required = true)
            protected String id;
            @XmlElement(name = "CategoriaRel", required = true)
            @XmlSchemaType(name = "string")
            protected CategoriaRelDoc categoriaRel;
            @XmlElement(name = "RuoloSDCorrelata")
            @XmlSchemaType(name = "string")
            protected RuoloDocCollegata ruoloSDCorrelata;

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
             * Recupera il valore della proprietà categoriaRel.
             * 
             * @return
             *     possible object is
             *     {@link CategoriaRelDoc }
             *     
             */
            public CategoriaRelDoc getCategoriaRel() {
                return categoriaRel;
            }

            /**
             * Imposta il valore della proprietà categoriaRel.
             * 
             * @param value
             *     allowed object is
             *     {@link CategoriaRelDoc }
             *     
             */
            public void setCategoriaRel(CategoriaRelDoc value) {
                this.categoriaRel = value;
            }

            /**
             * Recupera il valore della proprietà ruoloSDCorrelata.
             * 
             * @return
             *     possible object is
             *     {@link RuoloDocCollegata }
             *     
             */
            public RuoloDocCollegata getRuoloSDCorrelata() {
                return ruoloSDCorrelata;
            }

            /**
             * Imposta il valore della proprietà ruoloSDCorrelata.
             * 
             * @param value
             *     allowed object is
             *     {@link RuoloDocCollegata }
             *     
             */
            public void setRuoloSDCorrelata(RuoloDocCollegata value) {
                this.ruoloSDCorrelata = value;
            }

        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class Tipo
        extends RifEntitaContesto
    {


    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="UA" maxOccurs="unbounded"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
     *                   &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
     *                   &lt;element name="RuoloUACorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
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
    @XmlType(name = "", propOrder = {
        "ua"
    })
    public static class UACorrelate {

        @XmlElement(name = "UA", required = true)
        protected List<SDStdInfo.UACorrelate.UA> ua;

        /**
         * Gets the value of the ua property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ua property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUA().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SDStdInfo.UACorrelate.UA }
         * 
         * 
         */
        public List<SDStdInfo.UACorrelate.UA> getUA() {
            if (ua == null) {
                ua = new ArrayList<SDStdInfo.UACorrelate.UA>();
            }
            return this.ua;
        }


        /**
         * <p>Classe Java per anonymous complex type.
         * 
         * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="Id" type="{urn:IRIS:SIPManifest.xsd}ProvId"/&gt;
         *         &lt;element name="CategoriaRel" type="{urn:IRIS:SIPManifest.xsd}CategoriaRelDoc"/&gt;
         *         &lt;element name="RuoloUACorrelata" type="{urn:IRIS:SIPManifest.xsd}RuoloDocCollegata" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "id",
            "categoriaRel",
            "ruoloUACorrelata"
        })
        public static class UA {

            @XmlElement(name = "Id", required = true)
            protected String id;
            @XmlElement(name = "CategoriaRel", required = true)
            @XmlSchemaType(name = "string")
            protected CategoriaRelDoc categoriaRel;
            @XmlElement(name = "RuoloUACorrelata")
            @XmlSchemaType(name = "string")
            protected RuoloDocCollegata ruoloUACorrelata;

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
             * Recupera il valore della proprietà categoriaRel.
             * 
             * @return
             *     possible object is
             *     {@link CategoriaRelDoc }
             *     
             */
            public CategoriaRelDoc getCategoriaRel() {
                return categoriaRel;
            }

            /**
             * Imposta il valore della proprietà categoriaRel.
             * 
             * @param value
             *     allowed object is
             *     {@link CategoriaRelDoc }
             *     
             */
            public void setCategoriaRel(CategoriaRelDoc value) {
                this.categoriaRel = value;
            }

            /**
             * Recupera il valore della proprietà ruoloUACorrelata.
             * 
             * @return
             *     possible object is
             *     {@link RuoloDocCollegata }
             *     
             */
            public RuoloDocCollegata getRuoloUACorrelata() {
                return ruoloUACorrelata;
            }

            /**
             * Imposta il valore della proprietà ruoloUACorrelata.
             * 
             * @param value
             *     allowed object is
             *     {@link RuoloDocCollegata }
             *     
             */
            public void setRuoloUACorrelata(RuoloDocCollegata value) {
                this.ruoloUACorrelata = value;
            }

        }

    }


    /**
     * <p>Classe Java per anonymous complex type.
     * 
     * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="UCId" type="{urn:IRIS:SIPManifest.xsd}ProvId" maxOccurs="unbounded"/&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "ucId"
    })
    public static class UCAppartenenza {

        @XmlElement(name = "UCId", required = true)
        protected List<String> ucId;

        /**
         * Gets the value of the ucId property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the ucId property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getUCId().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getUCId() {
            if (ucId == null) {
                ucId = new ArrayList<String>();
            }
            return this.ucId;
        }

    }

}
