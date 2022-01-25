/**
 * ProtocolloServerImplPortBindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class ProtocolloServerImplPortBindingStub extends org.apache.axis.client.Stub implements it.osapulie.pdds.ws.client.protocollo.protocollo.ProtocolloServer {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[12];
        _initOperationDesc1();
        _initOperationDesc2();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getProtocollo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloInformazioniRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getProtocollo>protocolloInformazioniRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloProtocolloInformazioniRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getProtocolloResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getDestinatariProtocollo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "destinatarioProtocolloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getDestinatariProtocollo>destinatarioProtocolloRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloDestinatarioProtocolloRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getDestinatariProtocolloResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("completamentoProtocolloProvvisorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloDaCompletareRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorio>protocolloDaCompletareRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorioResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaProtocollo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocollo>protocolloRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProtocolloRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getAllegato");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "dettaglioAllegatoRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegato>dettaglioAllegatoRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoDettaglioAllegatoRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegatoResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("inoltraProtocollo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "inoltroProtocolloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocollo>inoltroProtocolloRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloInoltroProtocolloRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocolloResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaProtocolloUscita");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloUscitaRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscita>protocolloUscitaRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaProtocolloUscitaRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaProtocolloUscitaFasc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloUscitaFascRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaFasc>protocolloUscitaFascRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaFascResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaProtocolloFasc");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloFascRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloFasc>protocolloFascRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascProtocolloFascRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloFascResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("addAllegatura");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "allegaturaRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">addAllegatura>allegaturaRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaAllegaturaRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">addAllegaturaResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("getMittenteProtocollo");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "mittenteProtocolloRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getMittenteProtocollo>mittenteProtocolloRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloMittenteProtocolloRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getMittenteProtocolloResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("richiestaProtocolloProvvisorio");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("", "protocolloProvvisorioRequest"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloProvvisorio>protocolloProvvisorioRequest"), it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioProtocolloProvvisorioRequest.class, false, false);
        param.setOmittable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloProvvisorioResponse>return"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn.class);
        oper.setReturnQName(new javax.xml.namespace.QName("", "return"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

    }

    public ProtocolloServerImplPortBindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public ProtocolloServerImplPortBindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public ProtocolloServerImplPortBindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Allegato");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "ContattoDestinatario");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "DatiEmail");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.DatiEmail.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Destinatario");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Documento");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.Documento.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Errore");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.Errore.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "ImprontaMIME");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(org.apache.axis.encoding.ser.BaseSerializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleSerializerFactory.class, cls, qName));
            cachedDeserFactories.add(org.apache.axis.encoding.ser.BaseDeserializerFactory.createFactory(org.apache.axis.encoding.ser.SimpleDeserializerFactory.class, cls, qName));

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Mittente");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "MittenteProtUscita");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.MittenteProtUscita.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaFisica");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaGiuridica");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">addAllegatura>allegaturaRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaAllegaturaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">addAllegaturaResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorio>protocolloDaCompletareRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorioResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegato>dettaglioAllegatoRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoDettaglioAllegatoRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegatoResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getDestinatariProtocollo>destinatarioProtocolloRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloDestinatarioProtocolloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getDestinatariProtocolloResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getMittenteProtocollo>mittenteProtocolloRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloMittenteProtocolloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getMittenteProtocolloResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getProtocollo>protocolloInformazioniRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloProtocolloInformazioniRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getProtocolloResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocollo>inoltroProtocolloRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloInoltroProtocolloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocolloResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocollo>protocolloRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProtocolloRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloFasc>protocolloFascRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascProtocolloFascRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloFascResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloProvvisorio>protocolloProvvisorioRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioProtocolloProvvisorioRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloProvvisorioResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscita>protocolloUscitaRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaProtocolloUscitaRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaFasc>protocolloUscitaFascRequest");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaFascResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaResponse>return");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn.class;
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

    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn getProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloProtocolloInformazioniRequest protocolloInformazioniRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "getProtocollo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloInformazioniRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn getDestinatariProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloDestinatarioProtocolloRequest destinatarioProtocolloRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "getDestinatariProtocollo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {destinatarioProtocolloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn completamentoProtocolloProvvisorio(it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest protocolloDaCompletareRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "completamentoProtocolloProvvisorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloDaCompletareRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn richiestaProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProtocolloRequest protocolloRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "richiestaProtocollo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn getAllegato(it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoDettaglioAllegatoRequest dettaglioAllegatoRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "getAllegato"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {dettaglioAllegatoRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn inoltraProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloInoltroProtocolloRequest inoltroProtocolloRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "inoltraProtocollo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {inoltroProtocolloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn richiestaProtocolloUscita(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaProtocolloUscitaRequest protocolloUscitaRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "richiestaProtocolloUscita"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloUscitaRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn richiestaProtocolloUscitaFasc(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest protocolloUscitaFascRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "richiestaProtocolloUscitaFasc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloUscitaFascRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn richiestaProtocolloFasc(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascProtocolloFascRequest protocolloFascRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "richiestaProtocolloFasc"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloFascRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn addAllegatura(it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaAllegaturaRequest allegaturaRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "addAllegatura"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {allegaturaRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn getMittenteProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloMittenteProtocolloRequest mittenteProtocolloRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "getMittenteProtocollo"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {mittenteProtocolloRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn richiestaProtocolloProvvisorio(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioProtocolloProvvisorioRequest protocolloProvvisorioRequest) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("return");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP11_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", "richiestaProtocolloProvvisorio"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {protocolloProvvisorioRequest});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn.class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
