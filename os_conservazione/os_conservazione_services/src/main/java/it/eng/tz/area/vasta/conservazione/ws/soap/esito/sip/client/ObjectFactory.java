//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 05:15:52 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client package. 
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

    private final static QName _EsitoSip_QNAME = new QName("http://sip.esito.core.iris.eng.it", "esitoSip");
    private final static QName _EsitoSipResponse_QNAME = new QName("http://sip.esito.core.iris.eng.it", "esitoSipResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.conservazione.ws.soap.esito.sip.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EsitoSip }
     * 
     */
    public EsitoSip createEsitoSip() {
        return new EsitoSip();
    }

    /**
     * Create an instance of {@link EsitoSipResponse }
     * 
     */
    public EsitoSipResponse createEsitoSipResponse() {
        return new EsitoSipResponse();
    }

    /**
     * Create an instance of {@link SipEsitoRequest }
     * 
     */
    public SipEsitoRequest createSipEsitoRequest() {
        return new SipEsitoRequest();
    }

    /**
     * Create an instance of {@link SipEsitoResponse }
     * 
     */
    public SipEsitoResponse createSipEsitoResponse() {
        return new SipEsitoResponse();
    }

    /**
     * Create an instance of {@link WsGenericServiceOutput }
     * 
     */
    public WsGenericServiceOutput createWsGenericServiceOutput() {
        return new WsGenericServiceOutput();
    }

    /**
     * Create an instance of {@link Attachment }
     * 
     */
    public Attachment createAttachment() {
        return new Attachment();
    }

    /**
     * Create an instance of {@link Base64Binary }
     * 
     */
    public Base64Binary createBase64Binary() {
        return new Base64Binary();
    }

    /**
     * Create an instance of {@link HexBinary }
     * 
     */
    public HexBinary createHexBinary() {
        return new HexBinary();
    }

    /**
     * Create an instance of {@link EsitoSip.SipEsito }
     * 
     */
    public EsitoSip.SipEsito createEsitoSipSipEsito() {
        return new EsitoSip.SipEsito();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsitoSip }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EsitoSip }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.esito.core.iris.eng.it", name = "esitoSip")
    public JAXBElement<EsitoSip> createEsitoSip(EsitoSip value) {
        return new JAXBElement<EsitoSip>(_EsitoSip_QNAME, EsitoSip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EsitoSipResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link EsitoSipResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.esito.core.iris.eng.it", name = "esitoSipResponse")
    public JAXBElement<EsitoSipResponse> createEsitoSipResponse(EsitoSipResponse value) {
        return new JAXBElement<EsitoSipResponse>(_EsitoSipResponse_QNAME, EsitoSipResponse.class, null, value);
    }

}
