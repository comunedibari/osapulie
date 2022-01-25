/**
 * InterrogaDocWSServerImplServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class InterrogaDocWSServerImplServiceLocator extends org.apache.axis.client.Service implements it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplService {

    public InterrogaDocWSServerImplServiceLocator() {
    }


    public InterrogaDocWSServerImplServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InterrogaDocWSServerImplServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InterrogaDocWSServerImplPort
    private java.lang.String InterrogaDocWSServerImplPort_address = "http://10.0.5.62:8081/adoc/interroga_doc";

    public java.lang.String getInterrogaDocWSServerImplPortAddress() {
        return InterrogaDocWSServerImplPort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InterrogaDocWSServerImplPortWSDDServiceName = "InterrogaDocWSServerImplPort";

    public java.lang.String getInterrogaDocWSServerImplPortWSDDServiceName() {
        return InterrogaDocWSServerImplPortWSDDServiceName;
    }

    public void setInterrogaDocWSServerImplPortWSDDServiceName(java.lang.String name) {
        InterrogaDocWSServerImplPortWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWS getInterrogaDocWSServerImplPort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InterrogaDocWSServerImplPort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInterrogaDocWSServerImplPort(endpoint);
    }

    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWS getInterrogaDocWSServerImplPort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplPortBindingStub _stub = new it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplPortBindingStub(portAddress, this);
            _stub.setPortName(getInterrogaDocWSServerImplPortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInterrogaDocWSServerImplPortEndpointAddress(java.lang.String address) {
        InterrogaDocWSServerImplPort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWS.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplPortBindingStub _stub = new it.osapulie.pdds.ws.client.protocollo.interrogaDoc.InterrogaDocWSServerImplPortBindingStub(new java.net.URL(InterrogaDocWSServerImplPort_address), this);
                _stub.setPortName(getInterrogaDocWSServerImplPortWSDDServiceName());
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
        if ("InterrogaDocWSServerImplPort".equals(inputPortName)) {
            return getInterrogaDocWSServerImplPort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/interrogaDocWS", "InterrogaDocWSServerImplService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/interrogaDocWS", "InterrogaDocWSServerImplPort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InterrogaDocWSServerImplPort".equals(portName)) {
            setInterrogaDocWSServerImplPortEndpointAddress(address);
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
