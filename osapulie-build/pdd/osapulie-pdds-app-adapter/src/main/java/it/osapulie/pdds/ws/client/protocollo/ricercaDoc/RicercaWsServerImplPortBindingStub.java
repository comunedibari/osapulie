/**
 * RicercaWsServerImplPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class RicercaWsServerImplPortBindingStub extends org.apache.axis.client.Stub implements it.osapulie.pdds.ws.client.protocollo.ricercaDoc.RicercaWS {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[1];
        _initOperationDesc1();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("RichiestaRispostaSincrona_Ricerca");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "richiesta_RichiestaRispostaSincrona_Ricerca"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "richiesta_RichiestaRispostaSincrona_Ricerca_Type"), it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Richiesta_RichiestaRispostaSincrona_Ricerca_Type.class, false, false);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "risposta_RichiestaRispostaSincrona_Ricerca_Type"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type.class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "risposta_RichiestaRispostaSincrona_Ricerca"));
        oper.setStyle(org.apache.axis.constants.Style.DOCUMENT);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

    }

    public RicercaWsServerImplPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public RicercaWsServerImplPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public RicercaWsServerImplPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3RicercaRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.AP3RicercaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3RicercaResponse");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.AP3RicercaResponse.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "classifica");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Classifica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documento");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documentoInformatico");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.DocumentoInformatico.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "errore");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Errore.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicolo");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicoloVirtuale");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicoloVirtuale.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocolloEstesi");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocolloEstesi.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumenti");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumenti.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumentiInformatici");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumentiInformatici.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaLivelli");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaLivelli.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaResponsabiliTrattamento");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaResponsabiliTrattamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "responsabileTrattamento");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ResponsabileTrattamento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "richiesta_RichiestaRispostaSincrona_Ricerca_Type");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Richiesta_RichiestaRispostaSincrona_Ricerca_Type.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "risposta_RichiestaRispostaSincrona_Ricerca_Type");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type.class;
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

    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type richiestaRispostaSincrona_Ricerca(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Richiesta_RichiestaRispostaSincrona_Ricerca_Type parameters) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI(" ");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("", "RichiestaRispostaSincrona_Ricerca"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {parameters});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Risposta_RichiestaRispostaSincrona_Ricerca_Type.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
