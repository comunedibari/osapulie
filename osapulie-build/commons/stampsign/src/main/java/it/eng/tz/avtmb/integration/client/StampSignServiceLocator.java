/**
 * StampsignLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client;

class StampSignServiceLocator extends org.apache.axis.client.Service implements StampSignService {

    public StampSignServiceLocator() {
    }


    public StampSignServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public StampSignServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for documentStampSignAuthBinding
    private java.lang.String documentStampSignAuthBinding_address = "http://timbro.avmtb/ressvr/soap/stampsignauth";

    public java.lang.String getdocumentStampSignAuthBindingAddress() {
        return documentStampSignAuthBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String documentStampSignAuthBindingWSDDServiceName = "documentStampSignAuthBinding";

    public java.lang.String getdocumentStampSignAuthBindingWSDDServiceName() {
        return documentStampSignAuthBindingWSDDServiceName;
    }

    public void setdocumentStampSignAuthBindingWSDDServiceName(java.lang.String name) {
        documentStampSignAuthBindingWSDDServiceName = name;
    }

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt getdocumentStampSignAuthBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(documentStampSignAuthBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdocumentStampSignAuthBinding(endpoint);
    }

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt getdocumentStampSignAuthBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthBindingStub _stub = new it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthBindingStub(portAddress, this);
            _stub.setPortName(getdocumentStampSignAuthBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdocumentStampSignAuthBindingEndpointAddress(java.lang.String address) {
        documentStampSignAuthBinding_address = address;
    }


    // Use to get a proxy class for documentStampSignBinding
    private java.lang.String documentStampSignBinding_address = "http://timbro.avmtb/ressvr/soap/stampsign";

    public java.lang.String getdocumentStampSignBindingAddress() {
        return documentStampSignBinding_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String documentStampSignBindingWSDDServiceName = "documentStampSignBinding";

    public java.lang.String getdocumentStampSignBindingWSDDServiceName() {
        return documentStampSignBindingWSDDServiceName;
    }

    public void setdocumentStampSignBindingWSDDServiceName(java.lang.String name) {
        documentStampSignBindingWSDDServiceName = name;
    }

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignPt getdocumentStampSignBinding() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(documentStampSignBinding_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getdocumentStampSignBinding(endpoint);
    }

    public it.eng.tz.avtmb.integration.client.dto.DocumentStampSignPt getdocumentStampSignBinding(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            it.eng.tz.avtmb.integration.client.dto.DocumentStampSignBindingStub _stub = new it.eng.tz.avtmb.integration.client.dto.DocumentStampSignBindingStub(portAddress, this);
            _stub.setPortName(getdocumentStampSignBindingWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setdocumentStampSignBindingEndpointAddress(java.lang.String address) {
        documentStampSignBinding_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthPt.class.isAssignableFrom(serviceEndpointInterface)) {
                it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthBindingStub _stub = new it.eng.tz.avtmb.integration.client.dto.DocumentStampSignAuthBindingStub(new java.net.URL(documentStampSignAuthBinding_address), this);
                _stub.setPortName(getdocumentStampSignAuthBindingWSDDServiceName());
                return _stub;
            }
            if (it.eng.tz.avtmb.integration.client.dto.DocumentStampSignPt.class.isAssignableFrom(serviceEndpointInterface)) {
                it.eng.tz.avtmb.integration.client.dto.DocumentStampSignBindingStub _stub = new it.eng.tz.avtmb.integration.client.dto.DocumentStampSignBindingStub(new java.net.URL(documentStampSignBinding_address), this);
                _stub.setPortName(getdocumentStampSignBindingWSDDServiceName());
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
        if ("documentStampSignAuthBinding".equals(inputPortName)) {
            return getdocumentStampSignAuthBinding();
        }
        else if ("documentStampSignBinding".equals(inputPortName)) {
            return getdocumentStampSignBinding();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://stampsign.wsdl.avtmb.tz.eng.it", "stampsign");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://stampsign.wsdl.avtmb.tz.eng.it", "documentStampSignAuthBinding"));
            ports.add(new javax.xml.namespace.QName("http://stampsign.wsdl.avtmb.tz.eng.it", "documentStampSignBinding"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("documentStampSignAuthBinding".equals(portName)) {
            setdocumentStampSignAuthBindingEndpointAddress(address);
        }
        else 
if ("documentStampSignBinding".equals(portName)) {
            setdocumentStampSignBindingEndpointAddress(address);
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
