/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.anagrafe.service.impl;

import java.io.ByteArrayOutputStream;

import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Gianluca Pindinelli
 *
 */
public class SoapHandler implements Handler<SOAPMessageContext> {

	private final Logger log = LoggerFactory.getLogger(SoapHandler.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.xml.ws.handler.Handler#handleMessage(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleMessage(SOAPMessageContext context) {
		SOAPMessage msg = context.getMessage();
		try {
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			msg.writeTo(bout);
			String message = bout.toString("UTF-8");
			log.info("handleMessage :: SOAP MESSAGE: " + message);
		}
		catch (Exception ex) {
			log.error("handleMessage :: " + ex.getMessage(), ex);
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.xml.ws.handler.Handler#handleFault(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public boolean handleFault(SOAPMessageContext context) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.xml.ws.handler.Handler#close(javax.xml.ws.handler.MessageContext)
	 */
	@Override
	public void close(MessageContext context) {
		// TODO Auto-generated method stub

	}

}
