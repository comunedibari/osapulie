/**
 * ProtocolloServerImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class ProtocolloServerImplServiceLocator extends org.apache.axis.client.Service implements it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplService {

    public ProtocolloServerImplServiceLocator() {
    }


    public ProtocolloServerImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ProtocolloServerImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for ProtocolloServerImplPort
    private java.lang.String ProtocolloServerImplPort_address = "http://web38.linksmt.it:80/adoc/protocollo";

    public java.lang.String getProtocolloServerImplPortAddress() {
        return ProtocolloServerImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ProtocolloServerImplPortWSDDServiceName = "ProtocolloServerImplPort";

    public java.lang.String getProtocolloServerImplPortWSDDServiceName() {
        return ProtocolloServerImplPortWSDDServiceName;
    }

    public void setProtocolloServerImplPortWSDDServiceName(java.lang.String name) {
        ProtocolloServerImplPortWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer getProtocolloServerImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(ProtocolloServerImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getProtocolloServerImplPort(endpoint);
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer getProtocolloServerImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplPortBindingStub _stub = new it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplPortBindingStub(portAddress, this);
            _stub.setPortName(getProtocolloServerImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setProtocolloServerImplPortEndpointAddress(java.lang.String address) {
        ProtocolloServerImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplPortBindingStub _stub = new it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServerImplPortBindingStub(new java.net.URL(ProtocolloServerImplPort_address), this);
                _stub.setPortName(getProtocolloServerImplPortWSDDServiceName());
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
        if ("ProtocolloServerImplPort".equals(inputPortName)) {
            return getProtocolloServerImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://impl.server.ws.protocollo.linksmt.it/", "ProtocolloServerImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://impl.server.ws.protocollo.linksmt.it/", "ProtocolloServerImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("ProtocolloServerImplPort".equals(portName)) {
            setProtocolloServerImplPortEndpointAddress(address);
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
