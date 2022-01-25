/**
 * VisuraDocumentiAnagrafeServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.documenti.anagrafe;

public class VisuraDocumentiAnagrafeServiceLocator extends org.apache.axis.client.Service implements it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeService {

    public VisuraDocumentiAnagrafeServiceLocator() {
    }


    public VisuraDocumentiAnagrafeServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public VisuraDocumentiAnagrafeServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for SecureSOAP12Endpoint
    private java.lang.String SecureSOAP12Endpoint_address = "https://10.0.5.19:9444/services/visuraDocumentiAnagrafeService.SecureSOAP12Endpoint/";

    public java.lang.String getSecureSOAP12EndpointAddress() {
        return SecureSOAP12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SecureSOAP12EndpointWSDDServiceName = "SecureSOAP12Endpoint";

    public java.lang.String getSecureSOAP12EndpointWSDDServiceName() {
        return SecureSOAP12EndpointWSDDServiceName;
    }

    public void setSecureSOAP12EndpointWSDDServiceName(java.lang.String name) {
        SecureSOAP12EndpointWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSecureSOAP12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SecureSOAP12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSecureSOAP12Endpoint(endpoint);
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSecureSOAP12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSecureSOAP12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSecureSOAP12EndpointEndpointAddress(java.lang.String address) {
        SecureSOAP12Endpoint_address = address;
    }


    // Use to get a proxy class for SOAP11Endpoint
    private java.lang.String SOAP11Endpoint_address = "http://10.0.5.19:9764/services/visuraDocumentiAnagrafeService.SOAP11Endpoint/";

    public java.lang.String getSOAP11EndpointAddress() {
        return SOAP11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAP11EndpointWSDDServiceName = "SOAP11Endpoint";

    public java.lang.String getSOAP11EndpointWSDDServiceName() {
        return SOAP11EndpointWSDDServiceName;
    }

    public void setSOAP11EndpointWSDDServiceName(java.lang.String name) {
        SOAP11EndpointWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSOAP11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAP11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAP11Endpoint(endpoint);
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSOAP11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub(portAddress, this);
            _stub.setPortName(getSOAP11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSOAP11EndpointEndpointAddress(java.lang.String address) {
        SOAP11Endpoint_address = address;
    }


    // Use to get a proxy class for SecureSOAP11Endpoint
    private java.lang.String SecureSOAP11Endpoint_address = "https://10.0.5.19:9444/services/visuraDocumentiAnagrafeService.SecureSOAP11Endpoint/";

    public java.lang.String getSecureSOAP11EndpointAddress() {
        return SecureSOAP11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SecureSOAP11EndpointWSDDServiceName = "SecureSOAP11Endpoint";

    public java.lang.String getSecureSOAP11EndpointWSDDServiceName() {
        return SecureSOAP11EndpointWSDDServiceName;
    }

    public void setSecureSOAP11EndpointWSDDServiceName(java.lang.String name) {
        SecureSOAP11EndpointWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSecureSOAP11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SecureSOAP11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSecureSOAP11Endpoint(endpoint);
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSecureSOAP11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub(portAddress, this);
            _stub.setPortName(getSecureSOAP11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSecureSOAP11EndpointEndpointAddress(java.lang.String address) {
        SecureSOAP11Endpoint_address = address;
    }


    // Use to get a proxy class for SOAP12Endpoint
    private java.lang.String SOAP12Endpoint_address = "http://10.0.5.19:9764/services/visuraDocumentiAnagrafeService.SOAP12Endpoint/";

    public java.lang.String getSOAP12EndpointAddress() {
        return SOAP12Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String SOAP12EndpointWSDDServiceName = "SOAP12Endpoint";

    public java.lang.String getSOAP12EndpointWSDDServiceName() {
        return SOAP12EndpointWSDDServiceName;
    }

    public void setSOAP12EndpointWSDDServiceName(java.lang.String name) {
        SOAP12EndpointWSDDServiceName = name;
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSOAP12Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(SOAP12Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getSOAP12Endpoint(endpoint);
    }

    public it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType getSOAP12Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub(portAddress, this);
            _stub.setPortName(getSOAP12EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setSOAP12EndpointEndpointAddress(java.lang.String address) {
        SOAP12Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     * This service has multiple ports for a given interface;
     * the proxy implementation returned may be indeterminate.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub(new java.net.URL(SecureSOAP12Endpoint_address), this);
                _stub.setPortName(getSecureSOAP12EndpointWSDDServiceName());
                return _stub;
            }
            if (it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub(new java.net.URL(SOAP11Endpoint_address), this);
                _stub.setPortName(getSOAP11EndpointWSDDServiceName());
                return _stub;
            }
            if (it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP11BindingStub(new java.net.URL(SecureSOAP11Endpoint_address), this);
                _stub.setPortName(getSecureSOAP11EndpointWSDDServiceName());
                return _stub;
            }
            if (it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub _stub = new it.osapulie.pdds.ws.client.documenti.anagrafe.VisuraDocumentiAnagrafeServiceSOAP12BindingStub(new java.net.URL(SOAP12Endpoint_address), this);
                _stub.setPortName(getSOAP12EndpointWSDDServiceName());
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
        if ("SecureSOAP12Endpoint".equals(inputPortName)) {
            return getSecureSOAP12Endpoint();
        }
        else if ("SOAP11Endpoint".equals(inputPortName)) {
            return getSOAP11Endpoint();
        }
        else if ("SecureSOAP11Endpoint".equals(inputPortName)) {
            return getSecureSOAP11Endpoint();
        }
        else if ("SOAP12Endpoint".equals(inputPortName)) {
            return getSOAP12Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraDocumentiAnagrafeService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://servizi.osapulie.it", "SecureSOAP12Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://servizi.osapulie.it", "SOAP11Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://servizi.osapulie.it", "SecureSOAP11Endpoint"));
            ports.add(new javax.xml.namespace.QName("http://servizi.osapulie.it", "SOAP12Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("SecureSOAP12Endpoint".equals(portName)) {
            setSecureSOAP12EndpointEndpointAddress(address);
        }
        else 
if ("SOAP11Endpoint".equals(portName)) {
            setSOAP11EndpointEndpointAddress(address);
        }
        else 
if ("SecureSOAP11Endpoint".equals(portName)) {
            setSecureSOAP11EndpointEndpointAddress(address);
        }
        else 
if ("SOAP12Endpoint".equals(portName)) {
            setSOAP12EndpointEndpointAddress(address);
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
