//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.11 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2018.04.12 alle 12:32:25 PM CEST 
//


package it.eng.tz.area.vasta.osapulie.ws.stubs.sp;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.eng.tz.area.vasta.osapulie.ws.stubs.sp package. 
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

    private final static QName _SecurizeXMLWithClosingDocumentServiceId_QNAME = new QName("http://spservice.securepaperappliance.land.it", "serviceId");
    private final static QName _SecurizeXMLWithClosingDocumentInXml_QNAME = new QName("http://spservice.securepaperappliance.land.it", "inXml");
    private final static QName _SecurizeXMLWithClosingDocumentClosingDocumentXml_QNAME = new QName("http://spservice.securepaperappliance.land.it", "closingDocumentXml");
    private final static QName _SecurizeXMLWithClosingDocumentResponseReturn_QNAME = new QName("http://spservice.securepaperappliance.land.it", "return");
    private final static QName _ExceptionException_QNAME = new QName("http://spservice.securepaperappliance.land.it", "Exception");
    private final static QName _SPServiceResponseArchiveKey_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "archiveKey");
    private final static QName _SPServiceResponseReason_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "reason");
    private final static QName _SPServiceResponseSecuredDocument_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "securedDocument");
    private final static QName _SignerBeanDomain_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "domain");
    private final static QName _SignerBeanHsmIp_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "hsmIp");
    private final static QName _SignerBeanPassword_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "password");
    private final static QName _SignerBeanPin_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "pin");
    private final static QName _SignerBeanUser_QNAME = new QName("http://spservice.securepaperappliance.land.it/xsd", "user");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.eng.tz.area.vasta.osapulie.ws.stubs.sp
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SPWebServiceException }
     * 
     */
    public SPWebServiceException createSPWebServiceException() {
        return new SPWebServiceException();
    }

    /**
     * Create an instance of {@link SignerBean }
     * 
     */
    public SignerBean createSignerBean() {
        return new SignerBean();
    }

    /**
     * Create an instance of {@link SPServiceResponse }
     * 
     */
    public SPServiceResponse createSPServiceResponse() {
        return new SPServiceResponse();
    }

    /**
     * Create an instance of {@link SecurizeXMLWithClosingDocument }
     * 
     */
    public SecurizeXMLWithClosingDocument createSecurizeXMLWithClosingDocument() {
        return new SecurizeXMLWithClosingDocument();
    }

    /**
     * Create an instance of {@link SecurizeXMLWithClosingDocumentResponse }
     * 
     */
    public SecurizeXMLWithClosingDocumentResponse createSecurizeXMLWithClosingDocumentResponse() {
        return new SecurizeXMLWithClosingDocumentResponse();
    }

    /**
     * Create an instance of {@link SecurizeXML }
     * 
     */
    public SecurizeXML createSecurizeXML() {
        return new SecurizeXML();
    }

    /**
     * Create an instance of {@link SecurizeXMLResponse }
     * 
     */
    public SecurizeXMLResponse createSecurizeXMLResponse() {
        return new SecurizeXMLResponse();
    }

    /**
     * Create an instance of {@link SecurizePDFWithClosingDocument }
     * 
     */
    public SecurizePDFWithClosingDocument createSecurizePDFWithClosingDocument() {
        return new SecurizePDFWithClosingDocument();
    }

    /**
     * Create an instance of {@link SecurizePDFWithClosingDocumentResponse }
     * 
     */
    public SecurizePDFWithClosingDocumentResponse createSecurizePDFWithClosingDocumentResponse() {
        return new SecurizePDFWithClosingDocumentResponse();
    }

    /**
     * Create an instance of {@link SecurizePDF }
     * 
     */
    public SecurizePDF createSecurizePDF() {
        return new SecurizePDF();
    }

    /**
     * Create an instance of {@link SecurizePDFResponse }
     * 
     */
    public SecurizePDFResponse createSecurizePDFResponse() {
        return new SecurizePDFResponse();
    }

    /**
     * Create an instance of {@link Exception }
     * 
     */
    public Exception createException() {
        return new Exception();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "serviceId", scope = SecurizeXMLWithClosingDocument.class)
    public JAXBElement<String> createSecurizeXMLWithClosingDocumentServiceId(String value) {
        return new JAXBElement<String>(_SecurizeXMLWithClosingDocumentServiceId_QNAME, String.class, SecurizeXMLWithClosingDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "inXml", scope = SecurizeXMLWithClosingDocument.class)
    public JAXBElement<byte[]> createSecurizeXMLWithClosingDocumentInXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentInXml_QNAME, byte[].class, SecurizeXMLWithClosingDocument.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "closingDocumentXml", scope = SecurizeXMLWithClosingDocument.class)
    public JAXBElement<byte[]> createSecurizeXMLWithClosingDocumentClosingDocumentXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentClosingDocumentXml_QNAME, byte[].class, SecurizeXMLWithClosingDocument.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "return", scope = SecurizeXMLWithClosingDocumentResponse.class)
    public JAXBElement<SPServiceResponse> createSecurizeXMLWithClosingDocumentResponseReturn(SPServiceResponse value) {
        return new JAXBElement<SPServiceResponse>(_SecurizeXMLWithClosingDocumentResponseReturn_QNAME, SPServiceResponse.class, SecurizeXMLWithClosingDocumentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "serviceId", scope = SecurizeXML.class)
    public JAXBElement<String> createSecurizeXMLServiceId(String value) {
        return new JAXBElement<String>(_SecurizeXMLWithClosingDocumentServiceId_QNAME, String.class, SecurizeXML.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "inXml", scope = SecurizeXML.class)
    public JAXBElement<byte[]> createSecurizeXMLInXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentInXml_QNAME, byte[].class, SecurizeXML.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "return", scope = SecurizeXMLResponse.class)
    public JAXBElement<SPServiceResponse> createSecurizeXMLResponseReturn(SPServiceResponse value) {
        return new JAXBElement<SPServiceResponse>(_SecurizeXMLWithClosingDocumentResponseReturn_QNAME, SPServiceResponse.class, SecurizeXMLResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "serviceId", scope = SecurizePDFWithClosingDocument.class)
    public JAXBElement<String> createSecurizePDFWithClosingDocumentServiceId(String value) {
        return new JAXBElement<String>(_SecurizeXMLWithClosingDocumentServiceId_QNAME, String.class, SecurizePDFWithClosingDocument.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "inXml", scope = SecurizePDFWithClosingDocument.class)
    public JAXBElement<byte[]> createSecurizePDFWithClosingDocumentInXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentInXml_QNAME, byte[].class, SecurizePDFWithClosingDocument.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "closingDocumentXml", scope = SecurizePDFWithClosingDocument.class)
    public JAXBElement<byte[]> createSecurizePDFWithClosingDocumentClosingDocumentXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentClosingDocumentXml_QNAME, byte[].class, SecurizePDFWithClosingDocument.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "return", scope = SecurizePDFWithClosingDocumentResponse.class)
    public JAXBElement<SPServiceResponse> createSecurizePDFWithClosingDocumentResponseReturn(SPServiceResponse value) {
        return new JAXBElement<SPServiceResponse>(_SecurizeXMLWithClosingDocumentResponseReturn_QNAME, SPServiceResponse.class, SecurizePDFWithClosingDocumentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "serviceId", scope = SecurizePDF.class)
    public JAXBElement<String> createSecurizePDFServiceId(String value) {
        return new JAXBElement<String>(_SecurizeXMLWithClosingDocumentServiceId_QNAME, String.class, SecurizePDF.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "inXml", scope = SecurizePDF.class)
    public JAXBElement<byte[]> createSecurizePDFInXml(byte[] value) {
        return new JAXBElement<byte[]>(_SecurizeXMLWithClosingDocumentInXml_QNAME, byte[].class, SecurizePDF.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SPServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "return", scope = SecurizePDFResponse.class)
    public JAXBElement<SPServiceResponse> createSecurizePDFResponseReturn(SPServiceResponse value) {
        return new JAXBElement<SPServiceResponse>(_SecurizeXMLWithClosingDocumentResponseReturn_QNAME, SPServiceResponse.class, SecurizePDFResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it", name = "Exception", scope = Exception.class)
    public JAXBElement<Object> createExceptionException(Object value) {
        return new JAXBElement<Object>(_ExceptionException_QNAME, Object.class, Exception.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "archiveKey", scope = SPServiceResponse.class)
    public JAXBElement<String> createSPServiceResponseArchiveKey(String value) {
        return new JAXBElement<String>(_SPServiceResponseArchiveKey_QNAME, String.class, SPServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "reason", scope = SPServiceResponse.class)
    public JAXBElement<String> createSPServiceResponseReason(String value) {
        return new JAXBElement<String>(_SPServiceResponseReason_QNAME, String.class, SPServiceResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "securedDocument", scope = SPServiceResponse.class)
    public JAXBElement<byte[]> createSPServiceResponseSecuredDocument(byte[] value) {
        return new JAXBElement<byte[]>(_SPServiceResponseSecuredDocument_QNAME, byte[].class, SPServiceResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "domain", scope = SignerBean.class)
    public JAXBElement<String> createSignerBeanDomain(String value) {
        return new JAXBElement<String>(_SignerBeanDomain_QNAME, String.class, SignerBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "hsmIp", scope = SignerBean.class)
    public JAXBElement<String> createSignerBeanHsmIp(String value) {
        return new JAXBElement<String>(_SignerBeanHsmIp_QNAME, String.class, SignerBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "password", scope = SignerBean.class)
    public JAXBElement<String> createSignerBeanPassword(String value) {
        return new JAXBElement<String>(_SignerBeanPassword_QNAME, String.class, SignerBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "pin", scope = SignerBean.class)
    public JAXBElement<String> createSignerBeanPin(String value) {
        return new JAXBElement<String>(_SignerBeanPin_QNAME, String.class, SignerBean.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://spservice.securepaperappliance.land.it/xsd", name = "user", scope = SignerBean.class)
    public JAXBElement<String> createSignerBeanUser(String value) {
        return new JAXBElement<String>(_SignerBeanUser_QNAME, String.class, SignerBean.class, value);
    }

}
