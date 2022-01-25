/**
 * PddsIntegration_Service.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pddsintegration;

public interface PddsIntegration_Service extends javax.xml.rpc.Service {
    public java.lang.String getPddsIntegrationSOAPAddress();

    public it.osapulie.pddsintegration.PddsIntegration_PortType getPddsIntegrationSOAP() throws javax.xml.rpc.ServiceException;

    public it.osapulie.pddsintegration.PddsIntegration_PortType getPddsIntegrationSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
