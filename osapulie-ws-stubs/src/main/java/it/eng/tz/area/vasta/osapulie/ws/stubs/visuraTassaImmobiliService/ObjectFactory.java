//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:48:41 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaImmobiliService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaImmobiliService package. 
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

    private final static QName _RateVisuraTassaImmobili_QNAME = new QName("http://servizi.osapulie.it", "rateVisuraTassaImmobili");
    private final static QName _VisureTassaImmobili_QNAME = new QName("http://servizi.osapulie.it", "visureTassaImmobili");
    private final static QName _PosizioniVisuraTassaImmobili_QNAME = new QName("http://servizi.osapulie.it", "posizioniVisuraTassaImmobili");
    private final static QName _DataServiceFault_QNAME = new QName("http://ws.wso2.org/dataservice", "DataServiceFault");
    private final static QName _REQUESTSTATUS_QNAME = new QName("http://ws.wso2.org/dataservice", "REQUEST_STATUS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.visuraTassaImmobiliService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetRateVisuraTassaImmobili }
     * 
     */
    public GetRateVisuraTassaImmobili createGetRateVisuraTassaImmobili() {
        return new GetRateVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link RateVisuraTassaImmobili }
     * 
     */
    public RateVisuraTassaImmobili createRateVisuraTassaImmobili() {
        return new RateVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link GetVisuraTassaImmobili }
     * 
     */
    public GetVisuraTassaImmobili createGetVisuraTassaImmobili() {
        return new GetVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link VisureTassaImmobili }
     * 
     */
    public VisureTassaImmobili createVisureTassaImmobili() {
        return new VisureTassaImmobili();
    }

    /**
     * Create an instance of {@link GetRataVisuraTassaImmobili }
     * 
     */
    public GetRataVisuraTassaImmobili createGetRataVisuraTassaImmobili() {
        return new GetRataVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link GetVisureTassaImmobili }
     * 
     */
    public GetVisureTassaImmobili createGetVisureTassaImmobili() {
        return new GetVisureTassaImmobili();
    }

    /**
     * Create an instance of {@link GetPosizioniVisuraTassaImmobili }
     * 
     */
    public GetPosizioniVisuraTassaImmobili createGetPosizioniVisuraTassaImmobili() {
        return new GetPosizioniVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link PosizioniVisuraTassaImmobili }
     * 
     */
    public PosizioniVisuraTassaImmobili createPosizioniVisuraTassaImmobili() {
        return new PosizioniVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link RataVisuraTassaImmobili }
     * 
     */
    public RataVisuraTassaImmobili createRataVisuraTassaImmobili() {
        return new RataVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link VisuraTassaImmobili }
     * 
     */
    public VisuraTassaImmobili createVisuraTassaImmobili() {
        return new VisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link PosizioneVisuraTassaImmobili }
     * 
     */
    public PosizioneVisuraTassaImmobili createPosizioneVisuraTassaImmobili() {
        return new PosizioneVisuraTassaImmobili();
    }

    /**
     * Create an instance of {@link DATASERVICERESPONSE }
     * 
     */
    public DATASERVICERESPONSE createDATASERVICERESPONSE() {
        return new DATASERVICERESPONSE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RateVisuraTassaImmobili }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "rateVisuraTassaImmobili")
    public JAXBElement<RateVisuraTassaImmobili> createRateVisuraTassaImmobili(RateVisuraTassaImmobili value) {
        return new JAXBElement<RateVisuraTassaImmobili>(_RateVisuraTassaImmobili_QNAME, RateVisuraTassaImmobili.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisureTassaImmobili }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "visureTassaImmobili")
    public JAXBElement<VisureTassaImmobili> createVisureTassaImmobili(VisureTassaImmobili value) {
        return new JAXBElement<VisureTassaImmobili>(_VisureTassaImmobili_QNAME, VisureTassaImmobili.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PosizioniVisuraTassaImmobili }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "posizioniVisuraTassaImmobili")
    public JAXBElement<PosizioniVisuraTassaImmobili> createPosizioniVisuraTassaImmobili(PosizioniVisuraTassaImmobili value) {
        return new JAXBElement<PosizioniVisuraTassaImmobili>(_PosizioniVisuraTassaImmobili_QNAME, PosizioniVisuraTassaImmobili.class, null, value);
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
