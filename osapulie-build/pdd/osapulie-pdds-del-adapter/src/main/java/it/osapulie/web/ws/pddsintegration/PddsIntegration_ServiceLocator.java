/**
 * PddsIntegration_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.web.ws.pddsintegration;

public class PddsIntegration_ServiceLocator extends org.apache.axis.client.Service implements it.osapulie.web.ws.pddsintegration.PddsIntegration_Service {

    public PddsIntegration_ServiceLocator() {
    }


    public PddsIntegration_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public PddsIntegration_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for PddsIntegrationSOAP
    private java.lang.String PddsIntegrationSOAP_address = "http://192.0.0.17:8080/pdds_integration/services/PddsIntegration";

    public java.lang.String getPddsIntegrationSOAPAddress() {
        return PddsIntegrationSOAP_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String PddsIntegrationSOAPWSDDServiceName = "PddsIntegrationSOAP";

    public java.lang.String getPddsIntegrationSOAPWSDDServiceName() {
        return PddsIntegrationSOAPWSDDServiceName;
    }

    public void setPddsIntegrationSOAPWSDDServiceName(java.lang.String name) {
        PddsIntegrationSOAPWSDDServiceName = name;
    }

    public it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType getPddsIntegrationSOAP() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(PddsIntegrationSOAP_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getPddsIntegrationSOAP(endpoint);
    }

    public it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType getPddsIntegrationSOAP(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.web.ws.pddsintegration.PddsIntegrationSOAPStub _stub = new it.osapulie.web.ws.pddsintegration.PddsIntegrationSOAPStub(portAddress, this);
            _stub.setPortName(getPddsIntegrationSOAPWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setPddsIntegrationSOAPEndpointAddress(java.lang.String address) {
        PddsIntegrationSOAP_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.web.ws.pddsintegration.PddsIntegrationSOAPStub _stub = new it.osapulie.web.ws.pddsintegration.PddsIntegrationSOAPStub(new java.net.URL(PddsIntegrationSOAP_address), this);
                _stub.setPortName(getPddsIntegrationSOAPWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("PddsIntegrationSOAP".equals(inputPortName)) {
            return getPddsIntegrationSOAP();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://pddsintegration.osapulie.it", "PddsIntegration");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://pddsintegration.osapulie.it", "PddsIntegrationSOAP"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("PddsIntegrationSOAP".equals(portName)) {
            setPddsIntegrationSOAPEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
