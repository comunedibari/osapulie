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
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * Dati di descrizione di un'unità di aggregazione espressi secondo tracciato standard
 * 
 * <p>Classe Java per UAStdInfo complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="UAStdInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tipo"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
 *                 &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="TipoContenuti" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;extension base="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto"&gt;
 *                 &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
 *               &lt;/extension&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Intitolazione" type="{urn:IRIS:SIPManifest.xsd}ShortDescription"/&gt;
 *         &lt;element name="Identificativo"&gt;
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
 *         &lt;element name="AltroIdentificativo" type="{urn:IRIS:SIPManifest.xsd}Label" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="SezioneArchivio" type="{urn:IRIS:SIPManifest.xsd}SezioneArchivio" minOccurs="0"/&gt;
 *         &lt;element name="EstremiCronologici"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="Inizio" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
 *                   &lt;element name="Fine" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="Procedimento" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="TipoProc" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
 *                   &lt;element name="Responsabilita" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
 *                   &lt;element name="Iter" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
 *                   &lt;element name="DataAvvio" type="{urn:IRIS:SIPManifest.xsd}DateRef" minOccurs="0"/&gt;
 *                   &lt;element name="DataConclusione" type="{urn:IRIS:SIPManifest.xsd}DateRef" minOccurs="0"/&gt;
 *                   &lt;element name="Esito" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ProduttoreOrig" type="{urn:IRIS:SIPManifest.xsd}ProduttoreDoc" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="Classifica" type="{urn:IRIS:SIPManifest.xsd}VocePianoClassificazione" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="DescrizioneContenuto" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
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
 *         &lt;element name="Conservazione" type="{urn:IRIS:SIPManifest.xsd}DatiConservazione"/&gt;
 *         &lt;element name="NormativaCollegata" type="{urn:IRIS:SIPManifest.xsd}Label" maxOccurs="unbounded" minOccurs="0"/&gt;
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
@XmlType(name = "UAStdInfo", propOrder = {
    "tipo",
    "tipoContenuti",
    "intitolazione",
    "identificativo",
    "altroIdentificativo",
    "sezioneArchivio",
    "estremiCronologici",
    "procedimento",
    "produttoreOrig",
    "classifica",
    "descrizioneContenuto",
    "uaCorrelate",
    "ucAppartenenza",
    "conservazione",
    "normativaCollegata",
    "annotazioni",
    "attributoCustom"
})
public class UAStdInfo {

    @XmlElement(name = "Tipo", required = true)
    protected UAStdInfo.Tipo tipo;
    @XmlElement(name = "TipoContenuti")
    protected UAStdInfo.TipoContenuti tipoContenuti;
    @XmlElement(name = "Intitolazione", required = true)
    protected ShortDescription intitolazione;
    @XmlElement(name = "Identificativo", required = true)
    protected UAStdInfo.Identificativo identificativo;
    @XmlElement(name = "AltroIdentificativo")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected List<String> altroIdentificativo;
    @XmlElement(name = "SezioneArchivio", defaultValue = "C")
    @XmlSchemaType(name = "string")
    protected SezioneArchivio sezioneArchivio;
    @XmlElement(name = "EstremiCronologici", required = true)
    protected UAStdInfo.EstremiCronologici estremiCronologici;
    @XmlElement(name = "Procedimento")
    protected UAStdInfo.Procedimento procedimento;
    @XmlElement(name = "ProduttoreOrig")
    protected List<ProduttoreDoc> produttoreOrig;
    @XmlElement(name = "Classifica")
    protected List<VocePianoClassificazione> classifica;
    @XmlElement(name = "DescrizioneContenuto")
    protected LongDescription descrizioneContenuto;
    @XmlElement(name = "UACorrelate")
    protected UAStdInfo.UACorrelate uaCorrelate;
    @XmlElement(name = "UCAppartenenza")
    protected UAStdInfo.UCAppartenenza ucAppartenenza;
    @XmlElement(name = "Conservazione", required = true)
    protected DatiConservazione conservazione;
    @XmlElement(name = "NormativaCollegata")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    @XmlSchemaType(name = "normalizedString")
    protected List<String> normativaCollegata;
    @XmlElement(name = "Annotazioni")
    protected LongDescription annotazioni;
    @XmlElement(name = "AttributoCustom")
    protected List<ValoriAttributo> attributoCustom;

    /**
     * Recupera il valore della proprietà tipo.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.Tipo }
     *     
     */
    public UAStdInfo.Tipo getTipo() {
        return tipo;
    }

    /**
     * Imposta il valore della proprietà tipo.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.Tipo }
     *     
     */
    public void setTipo(UAStdInfo.Tipo value) {
        this.tipo = value;
    }

    /**
     * Recupera il valore della proprietà tipoContenuti.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.TipoContenuti }
     *     
     */
    public UAStdInfo.TipoContenuti getTipoContenuti() {
        return tipoContenuti;
    }

    /**
     * Imposta il valore della proprietà tipoContenuti.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.TipoContenuti }
     *     
     */
    public void setTipoContenuti(UAStdInfo.TipoContenuti value) {
        this.tipoContenuti = value;
    }

    /**
     * Recupera il valore della proprietà intitolazione.
     * 
     * @return
     *     possible object is
     *     {@link ShortDescription }
     *     
     */
    public ShortDescription getIntitolazione() {
        return intitolazione;
    }

    /**
     * Imposta il valore della proprietà intitolazione.
     * 
     * @param value
     *     allowed object is
     *     {@link ShortDescription }
     *     
     */
    public void setIntitolazione(ShortDescription value) {
        this.intitolazione = value;
    }

    /**
     * Recupera il valore della proprietà identificativo.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.Identificativo }
     *     
     */
    public UAStdInfo.Identificativo getIdentificativo() {
        return identificativo;
    }

    /**
     * Imposta il valore della proprietà identificativo.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.Identificativo }
     *     
     */
    public void setIdentificativo(UAStdInfo.Identificativo value) {
        this.identificativo = value;
    }

    /**
     * Gets the value of the altroIdentificativo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the altroIdentificativo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAltroIdentificativo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getAltroIdentificativo() {
        if (altroIdentificativo == null) {
            altroIdentificativo = new ArrayList<String>();
        }
        return this.altroIdentificativo;
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
     * Recupera il valore della proprietà estremiCronologici.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.EstremiCronologici }
     *     
     */
    public UAStdInfo.EstremiCronologici getEstremiCronologici() {
        return estremiCronologici;
    }

    /**
     * Imposta il valore della proprietà estremiCronologici.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.EstremiCronologici }
     *     
     */
    public void setEstremiCronologici(UAStdInfo.EstremiCronologici value) {
        this.estremiCronologici = value;
    }

    /**
     * Recupera il valore della proprietà procedimento.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.Procedimento }
     *     
     */
    public UAStdInfo.Procedimento getProcedimento() {
        return procedimento;
    }

    /**
     * Imposta il valore della proprietà procedimento.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.Procedimento }
     *     
     */
    public void setProcedimento(UAStdInfo.Procedimento value) {
        this.procedimento = value;
    }

    /**
     * Gets the value of the produttoreOrig property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the produttoreOrig property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProduttoreOrig().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProduttoreDoc }
     * 
     * 
     */
    public List<ProduttoreDoc> getProduttoreOrig() {
        if (produttoreOrig == null) {
            produttoreOrig = new ArrayList<ProduttoreDoc>();
        }
        return this.produttoreOrig;
    }

    /**
     * Gets the value of the classifica property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the classifica property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getClassifica().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link VocePianoClassificazione }
     * 
     * 
     */
    public List<VocePianoClassificazione> getClassifica() {
        if (classifica == null) {
            classifica = new ArrayList<VocePianoClassificazione>();
        }
        return this.classifica;
    }

    /**
     * Recupera il valore della proprietà descrizioneContenuto.
     * 
     * @return
     *     possible object is
     *     {@link LongDescription }
     *     
     */
    public LongDescription getDescrizioneContenuto() {
        return descrizioneContenuto;
    }

    /**
     * Imposta il valore della proprietà descrizioneContenuto.
     * 
     * @param value
     *     allowed object is
     *     {@link LongDescription }
     *     
     */
    public void setDescrizioneContenuto(LongDescription value) {
        this.descrizioneContenuto = value;
    }

    /**
     * Recupera il valore della proprietà uaCorrelate.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.UACorrelate }
     *     
     */
    public UAStdInfo.UACorrelate getUACorrelate() {
        return uaCorrelate;
    }

    /**
     * Imposta il valore della proprietà uaCorrelate.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.UACorrelate }
     *     
     */
    public void setUACorrelate(UAStdInfo.UACorrelate value) {
        this.uaCorrelate = value;
    }

    /**
     * Recupera il valore della proprietà ucAppartenenza.
     * 
     * @return
     *     possible object is
     *     {@link UAStdInfo.UCAppartenenza }
     *     
     */
    public UAStdInfo.UCAppartenenza getUCAppartenenza() {
        return ucAppartenenza;
    }

    /**
     * Imposta il valore della proprietà ucAppartenenza.
     * 
     * @param value
     *     allowed object is
     *     {@link UAStdInfo.UCAppartenenza }
     *     
     */
    public void setUCAppartenenza(UAStdInfo.UCAppartenenza value) {
        this.ucAppartenenza = value;
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
     * Gets the value of the normativaCollegata property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the normativaCollegata property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNormativaCollegata().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNormativaCollegata() {
        if (normativaCollegata == null) {
            normativaCollegata = new ArrayList<String>();
        }
        return this.normativaCollegata;
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
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="Inizio" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico" minOccurs="0"/&gt;
     *         &lt;element name="Fine" type="{urn:IRIS:SIPManifest.xsd}EstremoCronologico"/&gt;
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
        "inizio",
        "fine"
    })
    public static class EstremiCronologici {

        @XmlElement(name = "Inizio")
        protected EstremoCronologico inizio;
        @XmlElement(name = "Fine", required = true)
        protected EstremoCronologico fine;

        /**
         * Recupera il valore della proprietà inizio.
         * 
         * @return
         *     possible object is
         *     {@link EstremoCronologico }
         *     
         */
        public EstremoCronologico getInizio() {
            return inizio;
        }

        /**
         * Imposta il valore della proprietà inizio.
         * 
         * @param value
         *     allowed object is
         *     {@link EstremoCronologico }
         *     
         */
        public void setInizio(EstremoCronologico value) {
            this.inizio = value;
        }

        /**
         * Recupera il valore della proprietà fine.
         * 
         * @return
         *     possible object is
         *     {@link EstremoCronologico }
         *     
         */
        public EstremoCronologico getFine() {
            return fine;
        }

        /**
         * Imposta il valore della proprietà fine.
         * 
         * @param value
         *     allowed object is
         *     {@link EstremoCronologico }
         *     
         */
        public void setFine(EstremoCronologico value) {
            this.fine = value;
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
     *         &lt;element name="TipoProc" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
     *         &lt;element name="Responsabilita" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
     *         &lt;element name="Iter" type="{urn:IRIS:SIPManifest.xsd}LongDescription" minOccurs="0"/&gt;
     *         &lt;element name="DataAvvio" type="{urn:IRIS:SIPManifest.xsd}DateRef" minOccurs="0"/&gt;
     *         &lt;element name="DataConclusione" type="{urn:IRIS:SIPManifest.xsd}DateRef" minOccurs="0"/&gt;
     *         &lt;element name="Esito" type="{urn:IRIS:SIPManifest.xsd}RifEntitaContesto" minOccurs="0"/&gt;
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
        "tipoProc",
        "responsabilita",
        "iter",
        "dataAvvio",
        "dataConclusione",
        "esito"
    })
    public static class Procedimento {

        @XmlElement(name = "TipoProc")
        protected RifEntitaContesto tipoProc;
        @XmlElement(name = "Responsabilita")
        protected LongDescription responsabilita;
        @XmlElement(name = "Iter")
        protected LongDescription iter;
        @XmlElement(name = "DataAvvio")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataAvvio;
        @XmlElement(name = "DataConclusione")
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar dataConclusione;
        @XmlElement(name = "Esito")
        protected RifEntitaContesto esito;

        /**
         * Recupera il valore della proprietà tipoProc.
         * 
         * @return
         *     possible object is
         *     {@link RifEntitaContesto }
         *     
         */
        public RifEntitaContesto getTipoProc() {
            return tipoProc;
        }

        /**
         * Imposta il valore della proprietà tipoProc.
         * 
         * @param value
         *     allowed object is
         *     {@link RifEntitaContesto }
         *     
         */
        public void setTipoProc(RifEntitaContesto value) {
            this.tipoProc = value;
        }

        /**
         * Recupera il valore della proprietà responsabilita.
         * 
         * @return
         *     possible object is
         *     {@link LongDescription }
         *     
         */
        public LongDescription getResponsabilita() {
            return responsabilita;
        }

        /**
         * Imposta il valore della proprietà responsabilita.
         * 
         * @param value
         *     allowed object is
         *     {@link LongDescription }
         *     
         */
        public void setResponsabilita(LongDescription value) {
            this.responsabilita = value;
        }

        /**
         * Recupera il valore della proprietà iter.
         * 
         * @return
         *     possible object is
         *     {@link LongDescription }
         *     
         */
        public LongDescription getIter() {
            return iter;
        }

        /**
         * Imposta il valore della proprietà iter.
         * 
         * @param value
         *     allowed object is
         *     {@link LongDescription }
         *     
         */
        public void setIter(LongDescription value) {
            this.iter = value;
        }

        /**
         * Recupera il valore della proprietà dataAvvio.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataAvvio() {
            return dataAvvio;
        }

        /**
         * Imposta il valore della proprietà dataAvvio.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataAvvio(XMLGregorianCalendar value) {
            this.dataAvvio = value;
        }

        /**
         * Recupera il valore della proprietà dataConclusione.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDataConclusione() {
            return dataConclusione;
        }

        /**
         * Imposta il valore della proprietà dataConclusione.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDataConclusione(XMLGregorianCalendar value) {
            this.dataConclusione = value;
        }

        /**
         * Recupera il valore della proprietà esito.
         * 
         * @return
         *     possible object is
         *     {@link RifEntitaContesto }
         *     
         */
        public RifEntitaContesto getEsito() {
            return esito;
        }

        /**
         * Imposta il valore della proprietà esito.
         * 
         * @param value
         *     allowed object is
         *     {@link RifEntitaContesto }
         *     
         */
        public void setEsito(RifEntitaContesto value) {
            this.esito = value;
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
     *       &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
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

        @XmlAttribute(name = "Cat", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
        protected CategoriaUA cat;

        /**
         * Recupera il valore della proprietà cat.
         * 
         * @return
         *     possible object is
         *     {@link CategoriaUA }
         *     
         */
        public CategoriaUA getCat() {
            return cat;
        }

        /**
         * Imposta il valore della proprietà cat.
         * 
         * @param value
         *     allowed object is
         *     {@link CategoriaUA }
         *     
         */
        public void setCat(CategoriaUA value) {
            this.cat = value;
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
     *       &lt;attribute name="Cat" use="required" type="{urn:IRIS:SIPManifest.xsd}CategoriaUA" /&gt;
     *     &lt;/extension&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class TipoContenuti
        extends RifEntitaContesto
    {

        @XmlAttribute(name = "Cat", namespace = "urn:IRIS:SIPManifest.xsd", required = true)
        protected CategoriaUA cat;

        /**
         * Recupera il valore della proprietà cat.
         * 
         * @return
         *     possible object is
         *     {@link CategoriaUA }
         *     
         */
        public CategoriaUA getCat() {
            return cat;
        }

        /**
         * Imposta il valore della proprietà cat.
         * 
         * @param value
         *     allowed object is
         *     {@link CategoriaUA }
         *     
         */
        public void setCat(CategoriaUA value) {
            this.cat = value;
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
        protected List<UAStdInfo.UACorrelate.UA> ua;

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
         * {@link UAStdInfo.UACorrelate.UA }
         * 
         * 
         */
        public List<UAStdInfo.UACorrelate.UA> getUA() {
            if (ua == null) {
                ua = new ArrayList<UAStdInfo.UACorrelate.UA>();
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
