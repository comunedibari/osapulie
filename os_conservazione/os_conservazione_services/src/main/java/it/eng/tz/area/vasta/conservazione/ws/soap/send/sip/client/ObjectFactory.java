//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.3.0 
// Vedere <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.10.26 alle 05:16:18 PM CEST 
//


package it.eng.tz.area.vasta.conservazione.ws.soap.send.sip.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.conservazione.ws.soap.send.sip.client package. 
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

    private final static QName _SendSip_QNAME = new QName("http://sip.send.core.iris.eng.it", "sendSip");
    private final static QName _SendSipResponse_QNAME = new QName("http://sip.send.core.iris.eng.it", "sendSipResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.conservazione.ws.soap.send.sip.client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SendSip }
     * 
     */
    public SendSip createSendSip() {
        return new SendSip();
    }

    /**
     * Create an instance of {@link SendSipResponse }
     * 
     */
    public SendSipResponse createSendSipResponse() {
        return new SendSipResponse();
    }

    /**
     * Create an instance of {@link SipSendRequest }
     * 
     */
    public SipSendRequest createSipSendRequest() {
        return new SipSendRequest();
    }

    /**
     * Create an instance of {@link SipSendResponse }
     * 
     */
    public SipSendResponse createSipSendResponse() {
        return new SipSendResponse();
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
     * Create an instance of {@link SendSip.SipSend }
     * 
     */
    public SendSip.SipSend createSendSipSipSend() {
        return new SendSip.SipSend();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSip }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendSip }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.send.core.iris.eng.it", name = "sendSip")
    public JAXBElement<SendSip> createSendSip(SendSip value) {
        return new JAXBElement<SendSip>(_SendSip_QNAME, SendSip.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendSipResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link SendSipResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://sip.send.core.iris.eng.it", name = "sendSipResponse")
    public JAXBElement<SendSipResponse> createSendSipResponse(SendSipResponse value) {
        return new JAXBElement<SendSipResponse>(_SendSipResponse_QNAME, SendSipResponse.class, null, value);
    }

}
