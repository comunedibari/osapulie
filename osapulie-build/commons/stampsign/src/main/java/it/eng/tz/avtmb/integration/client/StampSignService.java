/**
 * Stampsign.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client;

interface StampSignService extends javax.xml.rpc.Service {
    public java.lang.String getdocumentStampSignAuthBindingAddress();

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt getdocumentStampSignAuthBinding() throws javax.xml.rpc.ServiceException;

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt getdocumentStampSignAuthBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
    public java.lang.String getdocumentStampSignBindingAddress();

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignPt getdocumentStampSignBinding() throws javax.xml.rpc.ServiceException;

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignPt getdocumentStampSignBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
