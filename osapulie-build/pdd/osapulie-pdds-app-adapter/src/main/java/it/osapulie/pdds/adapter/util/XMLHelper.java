/**
 * Copyright (c) 2011 Links Management & Technology S.p.A.
 */

package it.osapulie.pdds.adapter.util;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

/**
 * Utility per il marshalling e unmarshalling dei DTO trattati nell'applicazione.<br/> Questi DTO
 * sono tipicamente (ma non necessariamente) generati automaticamente attraverso JAXB (Java
 * Architecture for XML Binding).
 *
 * @author Mario Scalas
 * @author Gianluca Pindinelli
 */
public interface XMLHelper extends Marshaller, Unmarshaller {

	/**
	 * Restituisce la rappresentazione XML (marshalled) dell'oggetto indicato.
	 *
	 * @param graph l'oggetto di cui effettuare il marshalling
	 * @return una stringa contenente la rappresentazione XML dell'oggetto.
	 */
	String marshal(Object graph);

	/**
	 * Restituisce l'oggetto rappresentanto dalla sorgente XML.<br/> <strong>Nota</strong> che
	 * l'element type specificato nell'XSD associato a questo XML deve essere associato ad una
	 * classe annotata con l'annotazione {@link XmlRootElement} o la deserializzazione fallirà.
	 * Vedere <a href="http://springwstemplatejax2bxmlrootmissing.blogspot.com/">qui</a> per
	 * ulteriori dettagli.
	 *
	 * @param source una stringa sorgente contenente l'XML da deserializzare
	 * @return l'oggetto deserializzato
	 */
	<T> T unmarshal(String source);

	/**
	 * Quando {@link XmlRootElement} non è presente sulla classe che si conosce essere rappresentata
	 * nell'XML (vedi {@link #unmarshal(String)}, è possibile forzarlo specificando la classe:
	 * questo metodo semplifica l'utilizzo dell'API JAXB.
	 *
	 * @param content una stringa sorgente contenente l'XML da deserializzare
	 * @param type la classe del tipo serializzata in XML
	 * @return l'oggetto deserializzato
	 */
	<T> T unmarshal(String content, Class<T> type);

	/**
	 * Ritorna il contenuto di un file xml a partire dal nome del servizio (il nome del servizio
	 * deve corrispondere al nome del file xml presente nel package passato in input)
	 *
	 * @param serviceName
	 * @param classPath
	 * @return
	 */
	String getXmlContent(String serviceName, String classPath);
}
