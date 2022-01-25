package it.eng.tz.area.vasta.conservazione.ws.soap.callbacks;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.transform.TransformerException;

import org.apache.http.client.methods.HttpPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.transport.WebServiceConnection;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.ClientHttpRequestConnection;
import org.springframework.ws.transport.http.HttpComponentsConnection;

import it.eng.tz.area.vasta.conservazione.util.HttpHeaderUtil;
import it.eng.tz.area.vasta.conservazione.util.SoapHeaderUtil;

public class IrisWsCallback implements WebServiceMessageCallback {
	private static final Logger logger = LoggerFactory.getLogger(IrisWsCallback.class.getName());
	private List<HttpHeaderUtil> httpHeaders;
	private List<SoapHeaderUtil> soapHeaders;
	private String soapAction;

	public IrisWsCallback(List<HttpHeaderUtil> httpHeaders, List<SoapHeaderUtil> soapHeaders, String soapAction) {
		super();
		this.httpHeaders = httpHeaders;
		this.soapHeaders = soapHeaders;
		if( StringUtils.hasText(soapAction) ) {

			this.soapAction = soapAction;
		}
		else {
			this.soapAction = "\"\"";
		}
	}
	private void addRequestHeader(String headerKey, String headerValue)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("HTTP HEADER KEY [{}] HTTP HEADER VALUE [{}]", headerKey, headerValue);
		}
		if( StringUtils.hasText(headerKey) && StringUtils.hasText(headerValue) )
		{
			TransportContext context = TransportContextHolder.getTransportContext();
			WebServiceConnection connection = context.getConnection();
			if (connection instanceof HttpComponentsConnection)
			{
				HttpComponentsConnection conn = (HttpComponentsConnection) connection;
				HttpPost post = conn.getHttpPost();
				post.addHeader(headerKey, headerValue);	
			}
			else if( connection instanceof ClientHttpRequestConnection )
			{
				ClientHttpRequestConnection conn = (ClientHttpRequestConnection)connection;
				conn.getClientHttpRequest().getHeaders().add(headerKey, headerValue);
			}
		}
	}
	@Override
	public void doWithMessage(WebServiceMessage message) throws IOException, TransformerException {
		if( this.httpHeaders != null && !this.httpHeaders.isEmpty() )
		{
			this.httpHeaders.forEach(httpHead -> {
				addRequestHeader(httpHead.getHeaderKey(), httpHead.getHeaderValue());
			});
		}

		if( this.soapHeaders != null && !this.soapHeaders.isEmpty())
		{
			SoapMessage msgSoap = (SoapMessage)message;
			SoapHeader soapHeader = msgSoap.getSoapHeader();
			
			this.soapHeaders.forEach(shu -> {
				
				SoapHeaderElement username = soapHeader.addHeaderElement(new QName(shu.getHeaderNameSpace(), shu.getHeaderKey()));
				username.setText(shu.getHeaderValue());
			});			
		}
		if( StringUtils.hasText(this.soapAction) )
		{

			SoapMessage soapMsg = (SoapMessage) message;
			soapMsg.setSoapAction(this.soapAction);
		}
	}
}
