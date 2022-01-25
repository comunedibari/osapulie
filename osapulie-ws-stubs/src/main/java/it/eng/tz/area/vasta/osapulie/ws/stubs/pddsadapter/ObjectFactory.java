//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.09 alle 10:37:13 AM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter package. 
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

    private final static QName _Fault_QNAME = new QName("http://localhost:8080/pdds/services/PDDSAdapter", "fault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.pddsadapter
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ChiamaEnte }
     * 
     */
    public ChiamaEnte createChiamaEnte() {
        return new ChiamaEnte();
    }

    /**
     * Create an instance of {@link ChiamaEnteResponse }
     * 
     */
    public ChiamaEnteResponse createChiamaEnteResponse() {
        return new ChiamaEnteResponse();
    }

    /**
     * Create an instance of {@link EseguiRichiestaServizio }
     * 
     */
    public EseguiRichiestaServizio createEseguiRichiestaServizio() {
        return new EseguiRichiestaServizio();
    }

    /**
     * Create an instance of {@link EseguiRichiestaServizioResponse }
     * 
     */
    public EseguiRichiestaServizioResponse createEseguiRichiestaServizioResponse() {
        return new EseguiRichiestaServizioResponse();
    }

    /**
     * Create an instance of {@link EccezionePDDS }
     * 
     */
    public EccezionePDDS createEccezionePDDS() {
        return new EccezionePDDS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EccezionePDDS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8080/pdds/services/PDDSAdapter", name = "fault")
    public JAXBElement<EccezionePDDS> createFault(EccezionePDDS value) {
        return new JAXBElement<EccezionePDDS>(_Fault_QNAME, EccezionePDDS.class, null, value);
    }

}
