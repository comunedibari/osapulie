/**
 * PddsIntegrationSOAPSkeleton.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.web.ws.pddsintegration;

public class PddsIntegrationSOAPSkeleton implements it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType, org.apache.axis.wsdl.Skeleton {
    private it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType impl;
    private static java.util.Map _myOperations = new java.util.Hashtable();
    private static java.util.Collection _myOperationsList = new java.util.ArrayList();

    /**
    * Returns List of OperationDesc objects with this name
    */
    public static java.util.List getOperationDescByName(java.lang.String methodName) {
        return (java.util.List)_myOperations.get(methodName);
    }

    /**
    * Returns Collection of OperationDescs
    */
    public static java.util.Collection getOperationDescs() {
        return _myOperationsList;
    }

    static {
        org.apache.axis.description.OperationDesc _oper;
        org.apache.axis.description.FaultDesc _fault;
        org.apache.axis.description.ParameterDesc [] _params;
        _params = new org.apache.axis.description.ParameterDesc [] {
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "richiesta"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "servizio"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
            new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "url"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false), 
        };
        _oper = new org.apache.axis.description.OperationDesc("getRichiestaPdd", _params, new javax.xml.namespace.QName("", "out"));
        _oper.setReturnType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        _oper.setElementQName(new javax.xml.namespace.QName("http://pddsintegration.osapulie.it", "getRichiestaPdd"));
        _oper.setSoapAction("http://pddsintegration.osapulie.it/getRichiestaPdd");
        _myOperationsList.add(_oper);
        if (_myOperations.get("getRichiestaPdd") == null) {
            _myOperations.put("getRichiestaPdd", new java.util.ArrayList());
        }
        ((java.util.List)_myOperations.get("getRichiestaPdd")).add(_oper);
    }

    public PddsIntegrationSOAPSkeleton() {
        this.impl = new it.osapulie.web.ws.pddsintegration.PddsIntegrationSOAPImpl();
    }

    public PddsIntegrationSOAPSkeleton(it.osapulie.web.ws.pddsintegration.PddsIntegration_PortType impl) {
        this.impl = impl;
    }
    public java.lang.String getRichiestaPdd(java.lang.String richiesta, java.lang.String servizio, java.lang.String url) throws java.rmi.RemoteException
    {
        java.lang.String ret = impl.getRichiestaPdd(richiesta, servizio, url);
        return ret;
    }

}
