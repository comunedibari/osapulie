//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 05:38:19 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.visuraDocumentiAnagrafeService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.visuraDocumentiAnagrafeService package. 
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

    private final static QName _Documenti_QNAME = new QName("http://servizi.osapulie.it", "documenti");
    private final static QName _DataServiceFault_QNAME = new QName("http://ws.wso2.org/dataservice", "DataServiceFault");
    private final static QName _REQUESTSTATUS_QNAME = new QName("http://ws.wso2.org/dataservice", "REQUEST_STATUS");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.visuraDocumentiAnagrafeService
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDocumenti }
     * 
     */
    public GetDocumenti createGetDocumenti() {
        return new GetDocumenti();
    }

    /**
     * Create an instance of {@link Documenti }
     * 
     */
    public Documenti createDocumenti() {
        return new Documenti();
    }

    /**
     * Create an instance of {@link GetDocumento }
     * 
     */
    public GetDocumento createGetDocumento() {
        return new GetDocumento();
    }

    /**
     * Create an instance of {@link Documento }
     * 
     */
    public Documento createDocumento() {
        return new Documento();
    }

    /**
     * Create an instance of {@link DATASERVICERESPONSE }
     * 
     */
    public DATASERVICERESPONSE createDATASERVICERESPONSE() {
        return new DATASERVICERESPONSE();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Documenti }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://servizi.osapulie.it", name = "documenti")
    public JAXBElement<Documenti> createDocumenti(Documenti value) {
        return new JAXBElement<Documenti>(_Documenti_QNAME, Documenti.class, null, value);
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
