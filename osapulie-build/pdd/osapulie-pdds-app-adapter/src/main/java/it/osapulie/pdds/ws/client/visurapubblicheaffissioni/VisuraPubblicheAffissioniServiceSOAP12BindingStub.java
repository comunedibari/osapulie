/**
 * VisuraPubblicheAffissioniServiceSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visurapubblicheaffissioni;

public class VisuraPubblicheAffissioniServiceSOAP12BindingStub extends org.apache.axis.client.Stub implements it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioniServicePortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[5];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRateVisuraPubblicheAffissioni");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idVisura"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getPosizioniVisuraPubblicheAffissioni");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idVisura"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraPubblicheAffissioni"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraPubblicheAffissioni"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVisurePubblicheAffissioni");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceFiscale"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoDa"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoA"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getRataVisuraPubblicheAffissioni");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "identificativoRata"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getVisuraPubblicheAffissioni");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

    }

    public VisuraPubblicheAffissioniServiceSOAP12BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public VisuraPubblicheAffissioniServiceSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public VisuraPubblicheAffissioniServiceSOAP12BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
        if (service == null) {
            super.service = new org.apache.axis.client.Service();
        } else {
            super.service = service;
        }
        ((org.apache.axis.client.Service)super.service).setTypeMappingVersion("1.1");
            java.lang.Class cls;
            javax.xml.namespace.QName qName;
            javax.xml.namespace.QName qName2;
            java.lang.Class beansf = org.apache.axis.encoding.ser.BeanSerializerFactory.class;
            java.lang.Class beandf = org.apache.axis.encoding.ser.BeanDeserializerFactory.class;
            java.lang.Class enumsf = org.apache.axis.encoding.ser.EnumSerializerFactory.class;
            java.lang.Class enumdf = org.apache.axis.encoding.ser.EnumDeserializerFactory.class;
            java.lang.Class arraysf = org.apache.axis.encoding.ser.ArraySerializerFactory.class;
            java.lang.Class arraydf = org.apache.axis.encoding.ser.ArrayDeserializerFactory.class;
            java.lang.Class simplesf = org.apache.axis.encoding.ser.SimpleSerializerFactory.class;
            java.lang.Class simpledf = org.apache.axis.encoding.ser.SimpleDeserializerFactory.class;
            java.lang.Class simplelistsf = org.apache.axis.encoding.ser.SimpleListSerializerFactory.class;
            java.lang.Class simplelistdf = org.apache.axis.encoding.ser.SimpleListDeserializerFactory.class;
            qName = new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraPubblicheAffissioni");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

    }

    protected org.apache.axis.client.Call createCall() throws java.rmi.RemoteException {
        try {
            org.apache.axis.client.Call _call = super._createCall();
            if (super.maintainSessionSet) {
                _call.setMaintainSession(super.maintainSession);
            }
            if (super.cachedUsername != null) {
                _call.setUsername(super.cachedUsername);
            }
            if (super.cachedPassword != null) {
                _call.setPassword(super.cachedPassword);
            }
            if (super.cachedEndpoint != null) {
                _call.setTargetEndpointAddress(super.cachedEndpoint);
            }
            if (super.cachedTimeout != null) {
                _call.setTimeout(super.cachedTimeout);
            }
            if (super.cachedPortName != null) {
                _call.setPortName(super.cachedPortName);
            }
            java.util.Enumeration keys = super.cachedProperties.keys();
            while (keys.hasMoreElements()) {
                java.lang.String key = (java.lang.String) keys.nextElement();
                _call.setProperty(key, super.cachedProperties.get(key));
            }
            // All the type mapping information is registered
            // when the first call is made.
            // The type mapping information is actually registered in
            // the TypeMappingRegistry of the service, which
            // is the reason why registration is only needed for the first call.
            synchronized (this) {
                if (firstCall()) {
                    // must set encoding style before registering serializers
                    _call.setEncodingStyle(null);
                    for (int i = 0; i < cachedSerFactories.size(); ++i) {
                        java.lang.Class cls = (java.lang.Class) cachedSerClasses.get(i);
                        javax.xml.namespace.QName qName =
                                (javax.xml.namespace.QName) cachedSerQNames.get(i);
                        java.lang.Object x = cachedSerFactories.get(i);
                        if (x instanceof Class) {
                            java.lang.Class sf = (java.lang.Class)
                                 cachedSerFactories.get(i);
                            java.lang.Class df = (java.lang.Class)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                        else if (x instanceof javax.xml.rpc.encoding.SerializerFactory) {
                            org.apache.axis.encoding.SerializerFactory sf = (org.apache.axis.encoding.SerializerFactory)
                                 cachedSerFactories.get(i);
                            org.apache.axis.encoding.DeserializerFactory df = (org.apache.axis.encoding.DeserializerFactory)
                                 cachedDeserFactories.get(i);
                            _call.registerTypeMapping(cls, qName, sf, df, false);
                        }
                    }
                }
            }
            return _call;
        }
        catch (java.lang.Throwable _t) {
            throw new org.apache.axis.AxisFault("Failure trying to get the Call object", _t);
        }
    }

    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[] getRateVisuraPubblicheAffissioni(int idVisura) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getRateVisuraPubblicheAffissioni");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "getRateVisuraPubblicheAffissioni"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idVisura)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[] getPosizioniVisuraPubblicheAffissioni(int idVisura) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getPosizioniVisuraPubblicheAffissioni");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "getPosizioniVisuraPubblicheAffissioni"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(idVisura)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[] getVisurePubblicheAffissioni(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getVisurePubblicheAffissioni");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "getVisurePubblicheAffissioni"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {codiceFiscale, new java.lang.Integer(annoDa), new java.lang.Integer(annoA)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[] getRataVisuraPubblicheAffissioni(java.lang.String identificativoRata) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getRataVisuraPubblicheAffissioni");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "getRataVisuraPubblicheAffissioni"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {identificativoRata});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[] getVisuraPubblicheAffissioni(int id) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:getVisuraPubblicheAffissioni");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "getVisuraPubblicheAffissioni"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(id)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
