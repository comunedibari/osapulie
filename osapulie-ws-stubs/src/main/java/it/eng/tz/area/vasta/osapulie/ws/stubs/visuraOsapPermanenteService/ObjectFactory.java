//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:41:10 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapPermanenteService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapPermanenteService package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PosizioniVisuraOsapPermanente_QNAME = new QName("http://servizi.osapulie.it", "posizioniVisuraOsapPermanente");
    private final static QName _VisureOsapPermanente_QNAME = new QName("http://servizi.osapulie.it", "visureOsapPermanente");
    private final static QName _RateVisuraOsapPermanente_QNAME = new QName("http://servizi.osapulie.it", "rateVisuraOsapPermanente");
    private final static QName _DataServiceFault_QNAME = new QName("http://ws.wso2.org/dataservice", "DataServiceFault");
    private final static QName _REQUESTSTATUS_QNAME = new QName("http://ws.wso2.org/dataservice", "REQUEST_STATUS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.visuraOsapPermanenteService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPosizioniVisuraOsapPermanente }
     * 
     */
    public GetPosizioniVisuraOsapPermanente createGetPosizioniVisuraOsapPermanente() {
        return new GetPosizioniVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link PosizioniVisuraOsapPermanente }
     * 
     */
    public PosizioniVisuraOsapPermanente createPosizioniVisuraOsapPermanente() {
        return new PosizioniVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link GetVisureOsapPermanente }
     * 
     */
    public GetVisureOsapPermanente createGetVisureOsapPermanente() {
        return new GetVisureOsapPermanente();
    }

    /**
     * Create an instance of {@link VisureOsapPermanente }
     * 
     */
    public VisureOsapPermanente createVisureOsapPermanente() {
        return new VisureOsapPermanente();
    }

    /**
     * Create an instance of {@link GetRateVisuraOsapPermanente }
     * 
     */
    public GetRateVisuraOsapPermanente createGetRateVisuraOsapPermanente() {
        return new GetRateVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link RateVisuraOsapPermanente }
     * 
     */
    public RateVisuraOsapPermanente createRateVisuraOsapPermanente() {
        return new RateVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link GetRataVisuraOsapPermanente }
     * 
     */
    public GetRataVisuraOsapPermanente createGetRataVisuraOsapPermanente() {
        return new GetRataVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link GetVisuraOsapPermanente }
     * 
     */
    public GetVisuraOsapPermanente createGetVisuraOsapPermanente() {
        return new GetVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link PosizioneVisuraOsapPermanente }
     * 
     */
    public PosizioneVisuraOsapPermanente createPosizioneVisuraOsapPermanente() {
        return new PosizioneVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link VisuraOsapPermanente }
     * 
     */
    public VisuraOsapPermanente createVisuraOsapPermanente() {
        return new VisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link RataVisuraOsapPermanente }
     * 
     */
    public RataVisuraOsapPermanente createRataVisuraOsapPermanente() {
        return new RataVisuraOsapPermanente();
    }

    /**
     * Create an instance of {@link DATASERVICERESPONSE }
     * 
     */
    public DATASERVICERESPONSE createDATASERVICERESPONSE() {
        return new DATASERVICERESPONSE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PosizioniVisuraOsapPermanente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "posizioniVisuraOsapPermanente")
    public JAXBElement<PosizioniVisuraOsapPermanente> createPosizioniVisuraOsapPermanente(PosizioniVisuraOsapPermanente value) {
        return new JAXBElement<PosizioniVisuraOsapPermanente>(_PosizioniVisuraOsapPermanente_QNAME, PosizioniVisuraOsapPermanente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisureOsapPermanente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "visureOsapPermanente")
    public JAXBElement<VisureOsapPermanente> createVisureOsapPermanente(VisureOsapPermanente value) {
        return new JAXBElement<VisureOsapPermanente>(_VisureOsapPermanente_QNAME, VisureOsapPermanente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RateVisuraOsapPermanente }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "rateVisuraOsapPermanente")
    public JAXBElement<RateVisuraOsapPermanente> createRateVisuraOsapPermanente(RateVisuraOsapPermanente value) {
        return new JAXBElement<RateVisuraOsapPermanente>(_RateVisuraOsapPermanente_QNAME, RateVisuraOsapPermanente.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wso2.org/dataservice", name = "DataServiceFault")
    public JAXBElement<String> createDataServiceFault(String value) {
        return new JAXBElement<String>(_DataServiceFault_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.wso2.org/dataservice", name = "REQUEST_STATUS")
    public JAXBElement<String> createREQUESTSTATUS(String value) {
        return new JAXBElement<String>(_REQUESTSTATUS_QNAME, String.class, null, value);
    }

}
