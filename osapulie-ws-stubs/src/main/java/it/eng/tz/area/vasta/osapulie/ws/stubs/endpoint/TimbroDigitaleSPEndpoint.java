package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import java.io.File;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.ObjectFactory;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SPServiceResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizePDF;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizePDFResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizePDFWithClosingDocument;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizePDFWithClosingDocumentResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizeXML;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizeXMLResponse;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizeXMLWithClosingDocument;
import it.eng.tz.area.vasta.osapulie.ws.stubs.sp.SecurizeXMLWithClosingDocumentResponse;

@Endpoint
public class TimbroDigitaleSPEndpoint {
	private static final Logger logger = LoggerFactory.getLogger(TimbroDigitaleSPEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://spservice.securepaperappliance.land.it";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="securizeXML")
	@ResponsePayload
	public SecurizeXMLResponse securizeXML(@RequestPayload SecurizeXML request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO securizeXML CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SecurizeXMLResponse result = of.createSecurizeXMLResponse();
		File file = new File("file");
		JAXBElement<byte[]> jaxbByte = new JAXBElement<byte[]>(new QName("http://spservice.securepaperappliance.land.it/xsd", "securedDocument"), byte[].class, new byte[(int)file.length()]);
		SPServiceResponse securedDocument = new SPServiceResponse();
		securedDocument.setSecuredDocument(jaxbByte);
		securedDocument.setReason(of.createSPServiceResponseReason("prova"));
		JAXBElement<SPServiceResponse> prova = new JAXBElement<SPServiceResponse>(new QName("http://spservice.securepaperappliance.land.it", "return"), SPServiceResponse.class, securedDocument);
		result.setReturn(prova);	
		return result;
	}

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="securizeXMLWithClosingDocument")
	@ResponsePayload
	public SecurizeXMLWithClosingDocumentResponse securizeXMLWithClosingDocument(@RequestPayload SecurizeXMLWithClosingDocument request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO securizeXMLWithClosingDocument CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SecurizeXMLWithClosingDocumentResponse result = of.createSecurizeXMLWithClosingDocumentResponse();
		result.setReturn(null);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="securizePDF")
	@ResponsePayload
	public SecurizePDFResponse securizePDF(@RequestPayload SecurizePDF request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO securizePDF CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SecurizePDFResponse result = of.createSecurizePDFResponse();
		result.setReturn(null);	
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="securizePDFWithClosingDocument")
	@ResponsePayload
	public SecurizePDFWithClosingDocumentResponse securizePDFWithClosingDocument(@RequestPayload SecurizePDFWithClosingDocument request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO securizePDFWithClosingDocument CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SecurizePDFWithClosingDocumentResponse result = of.createSecurizePDFWithClosingDocumentResponse();
		result.setReturn(null);
		return result;
	}

}