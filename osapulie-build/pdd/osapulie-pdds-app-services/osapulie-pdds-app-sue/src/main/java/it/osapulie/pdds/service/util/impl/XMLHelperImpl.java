package it.osapulie.pdds.service.util.impl;

import it.osapulie.pdds.service.util.XMLHelper;
import it.osapulie.pdds.service.util.XMLUnmarshallingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.xml.bind.JAXBElement;
import javax.xml.transform.Result;
import javax.xml.transform.Source;

//import org.apache.log4j.Logger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.xml.transform.StringResult;
import org.springframework.xml.transform.StringSource;

/**
 * Implementazione di {@link XMLHelper}.
 *
 * @author Mario Scalas
 */
public class XMLHelperImpl implements XMLHelper {

	private static Logger log = LoggerFactory.getLogger(XMLHelperImpl.class);

	private Marshaller marshaller;

	private Unmarshaller unmarshaller;

	/**
	 * @param marshaller the marshaller to set
	 */
	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * @param unmarshaller the unmarshaller to set
	 */
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public boolean supports(Class<?> clazz) {
		return marshaller.supports(clazz); // Indifferente rispetto all'unmarshaller
	}

	public void marshal(Object graph, Result result) throws IOException, XmlMappingException {
		marshaller.marshal(graph, result);
	}

	public Object unmarshal(Source source) throws IOException, XmlMappingException {
		return unmarshaller.unmarshal(source);
	}

	public String marshal(Object graph) {
		try {
			Result result = new StringResult();
			marshal(graph, result);
			return result.toString();
		}
		catch (Exception e) {
			String message = String.format("Marshalling dell'oggetto di tipo (\"%s\")", graph.getClass().getName());
			log.error(message, e);
			throw new XMLUnmarshallingException(message, e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T unmarshal(String content) {
		try {
			return (T) unmarshal(new StringSource(content));
		}
		catch (Exception e) {
			String message = String.format("Unmarshalling del contenuto passato (\"%s\")", content.substring(1, 64) + "...");
			log.error(message, e);
			throw new XMLUnmarshallingException(message, e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T unmarshal(String content, Class<T> type) {
		try {
			Object o = unmarshal(new StringSource(content));
			if (type.isAssignableFrom(o.getClass())) {
				return (T) o;
			}
			JAXBElement<T> userElement = (JAXBElement<T>) o;
			T user = userElement.getValue();
			return user;
		}
		catch (Exception e) {
			log.error("unmarshal :: " + e.getMessage(), e);
			throw new XMLUnmarshallingException("Unmarshalling del contenuto passato ", e);
		}
	}

	public String getXmlContent(String serviceName, String classPath) {

		String thisLine;
		StringBuffer buffer = new StringBuffer();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(classPath + "/" + serviceName + ".xml")));
		try {
			while ((thisLine = bufferedReader.readLine()) != null) { // while loop begins here
				buffer.append(thisLine);
			}
		}
		catch (IOException e) {
			log.error("getXmlContent :: " + e.getMessage(), e);
		}

		return buffer.toString();
	}
}
