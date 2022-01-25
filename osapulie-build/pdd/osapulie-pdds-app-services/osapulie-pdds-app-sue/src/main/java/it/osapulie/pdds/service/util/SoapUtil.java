package it.osapulie.pdds.service.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;
import java.nio.charset.Charset;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SoapUtil {

	protected static Logger log = LoggerFactory.getLogger(SoapUtil.class.getName());

	public SoapUtil() {
	}

	public static String convertToString(String soapEnvelope) {
		String convertedResult = null;
		if (soapEnvelope.startsWith("<soap:Envelope")) {
			try {
				MessageFactory mf = MessageFactory.newInstance();
				SOAPMessage msg = mf.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapEnvelope.getBytes(Charset.forName("UTF-8"))));
				SOAPBody sb = msg.getSOAPBody();
				org.w3c.dom.Document doc = sb.extractContentAsDocument();
				javax.xml.transform.Source source = new DOMSource(doc);
				StringWriter stringWriter = new StringWriter();
				javax.xml.transform.Result result = new StreamResult(stringWriter);
				TransformerFactory factory = TransformerFactory.newInstance();
				Transformer transformer = factory.newTransformer();
				transformer.transform(source, result);
				convertedResult = stringWriter.getBuffer().toString();
			}
			catch (Exception e) {
				log.error((new StringBuilder()).append("convertToString :: ").append(e.getMessage()).toString(), e);
			}
		}
		return convertedResult;
	}

}