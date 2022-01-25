//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:36:47 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService package. 
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

    private final static QName _VisureAnagrafiche_QNAME = new QName("http://servizi.osapulie.it", "visureAnagrafiche");
    private final static QName _PensioniComponenteVisuraAnagrafica_QNAME = new QName("http://servizi.osapulie.it", "pensioniComponenteVisuraAnagrafica");
    private final static QName _DataServiceFault_QNAME = new QName("http://ws.wso2.org/dataservice", "DataServiceFault");
    private final static QName _REQUESTSTATUS_QNAME = new QName("http://ws.wso2.org/dataservice", "REQUEST_STATUS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.visuraAnagraficaService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetVisuraAnagrafica }
     * 
     */
    public GetVisuraAnagrafica createGetVisuraAnagrafica() {
        return new GetVisuraAnagrafica();
    }

    /**
     * Create an instance of {@link VisureAnagrafiche }
     * 
     */
    public VisureAnagrafiche createVisureAnagrafiche() {
        return new VisureAnagrafiche();
    }

    /**
     * Create an instance of {@link GetVisureAnagraficheNucleoFamiliare }
     * 
     */
    public GetVisureAnagraficheNucleoFamiliare createGetVisureAnagraficheNucleoFamiliare() {
        return new GetVisureAnagraficheNucleoFamiliare();
    }

    /**
     * Create an instance of {@link GetPensioniComponenteVisuraAnagrafica }
     * 
     */
    public GetPensioniComponenteVisuraAnagrafica createGetPensioniComponenteVisuraAnagrafica() {
        return new GetPensioniComponenteVisuraAnagrafica();
    }

    /**
     * Create an instance of {@link PensioniComponenteVisuraAnagrafica }
     * 
     */
    public PensioniComponenteVisuraAnagrafica createPensioniComponenteVisuraAnagrafica() {
        return new PensioniComponenteVisuraAnagrafica();
    }

    /**
     * Create an instance of {@link VisuraAnagrafica }
     * 
     */
    public VisuraAnagrafica createVisuraAnagrafica() {
        return new VisuraAnagrafica();
    }

    /**
     * Create an instance of {@link PensioneComponenteVisuraAnagrafica }
     * 
     */
    public PensioneComponenteVisuraAnagrafica createPensioneComponenteVisuraAnagrafica() {
        return new PensioneComponenteVisuraAnagrafica();
    }

    /**
     * Create an instance of {@link DATASERVICERESPONSE }
     * 
     */
    public DATASERVICERESPONSE createDATASERVICERESPONSE() {
        return new DATASERVICERESPONSE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VisureAnagrafiche }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "visureAnagrafiche")
    public JAXBElement<VisureAnagrafiche> createVisureAnagrafiche(VisureAnagrafiche value) {
        return new JAXBElement<VisureAnagrafiche>(_VisureAnagrafiche_QNAME, VisureAnagrafiche.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PensioniComponenteVisuraAnagrafica }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "pensioniComponenteVisuraAnagrafica")
    public JAXBElement<PensioniComponenteVisuraAnagrafica> createPensioniComponenteVisuraAnagrafica(PensioniComponenteVisuraAnagrafica value) {
        return new JAXBElement<PensioniComponenteVisuraAnagrafica>(_PensioniComponenteVisuraAnagrafica_QNAME, PensioniComponenteVisuraAnagrafica.class, null, value);
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
