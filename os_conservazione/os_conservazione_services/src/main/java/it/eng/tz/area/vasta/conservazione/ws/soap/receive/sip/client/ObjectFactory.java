//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 06:14:03 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client package. 
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

    private final static QName _ReceiveSip_QNAME = new QName("http://sip.receive.core.iris.eng.it", "receiveSip");
    private final static QName _ReceiveSipResponse_QNAME = new QName("http://sip.receive.core.iris.eng.it", "receiveSipResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.conservazione.ws.soap.receive.sip.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceiveSip }
     * 
     */
    public ReceiveSip createReceiveSip() {
        return new ReceiveSip();
    }

    /**
     * Create an instance of {@link ReceiveSipResponse }
     * 
     */
    public ReceiveSipResponse createReceiveSipResponse() {
        return new ReceiveSipResponse();
    }

    /**
     * Create an instance of {@link SipReceiveRequest }
     * 
     */
    public SipReceiveRequest createSipReceiveRequest() {
        return new SipReceiveRequest();
    }

    /**
     * Create an instance of {@link SipReceiveResponse }
     * 
     */
    public SipReceiveResponse createSipReceiveResponse() {
        return new SipReceiveResponse();
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
     * Create an instance of {@link ReceiveSip.SipReceive }
     * 
     */
    public ReceiveSip.SipReceive createReceiveSipSipReceive() {
        return new ReceiveSip.SipReceive();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveSip }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReceiveSip }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.receive.core.iris.eng.it", name = "receiveSip")
    public JAXBElement<ReceiveSip> createReceiveSip(ReceiveSip value) {
        return new JAXBElement<ReceiveSip>(_ReceiveSip_QNAME, ReceiveSip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveSipResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ReceiveSipResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.receive.core.iris.eng.it", name = "receiveSipResponse")
    public JAXBElement<ReceiveSipResponse> createReceiveSipResponse(ReceiveSipResponse value) {
        return new JAXBElement<ReceiveSipResponse>(_ReceiveSipResponse_QNAME, ReceiveSipResponse.class, null, value);
    }

}
