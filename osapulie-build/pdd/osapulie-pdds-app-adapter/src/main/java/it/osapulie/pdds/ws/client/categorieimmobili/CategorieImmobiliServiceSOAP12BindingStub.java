/**
 * CategorieImmobiliServiceSOAP12BindingStub.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public class CategorieImmobiliServiceSOAP12BindingStub extends org.apache.axis.client.Stub implements it.osapulie.pdds.ws.client.categorieimmobili.CategorieImmobiliServicePortType {
    private java.util.Vector cachedSerClasses = new java.util.Vector();
    private java.util.Vector cachedSerQNames = new java.util.Vector();
    private java.util.Vector cachedSerFactories = new java.util.Vector();
    private java.util.Vector cachedDeserFactories = new java.util.Vector();

    static org.apache.axis.description.OperationDesc [] _operations;

    static {
        _operations = new org.apache.axis.description.OperationDesc[47];
        _initOperationDesc1();
        _initOperationDesc2();
        _initOperationDesc3();
        _initOperationDesc4();
        _initOperationDesc5();
    }

    private static void _initOperationDesc1(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "nome"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "valore"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[0] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_categoriaimmobile_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[1] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_agevolazione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_agevolazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_agevolazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[2] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_categoriaimmobile_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[3] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_by_anno_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "anno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[4] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_tipologia_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[5] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_esenzione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[6] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_categoriaimmobile_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_agevolazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[7] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_tipologia_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tipologia_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tipologia_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[8] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_detrazione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[9] = oper;

    }

    private static void _initOperationDesc2(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "nome"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[10] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_tipologia_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[11] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[12] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[13] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_categoriaimmobile_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_agevolazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[14] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "importo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "info"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tipologia_detrazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[15] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_categoriaimmobile_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteRivalutazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteMoltiplicazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeProprietario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeInquilinoComodatario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[16] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_tipologia_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[17] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_categoriaimmobile_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_agevolazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_agevolazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[18] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_esenzione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[19] = oper;

    }

    private static void _initOperationDesc3(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tributo"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tributo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[20] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[21] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_tributo_by_anno_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "anno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[22] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "nome"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[23] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_tributo_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[24] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_esenzione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[25] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "importo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "info"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tipologia_detrazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[26] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_categoriaimmobile_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "aliquota"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "anno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[27] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_categoriaimmobile_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "aliquota"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "anno"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[28] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[29] = oper;

    }

    private static void _initOperationDesc4(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_categoriaimmobile_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_detrazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[30] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_categoriaimmobile_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[31] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_categoriaimmobile_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_detrazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[32] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_categoriaimmobile_tributo_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[33] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_agevolazione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[34] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_categoriaimmobile_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[35] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("insert_tb_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "nome"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "valore"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[36] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_categoriaimmobile_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_agevolazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[37] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("delete_tb_agevolazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[38] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_with_key_tb_categoriaimmobile_detrazione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[39] = oper;

    }

    private static void _initOperationDesc5(){
        org.apache.axis.description.OperationDesc oper;
        org.apache.axis.description.ParameterDesc param;
        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_categoriaimmobile_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[40] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_tributo_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tributo"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tributo"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[41] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_categoriaimmobile_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteRivalutazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteMoltiplicazione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeProprietario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeInquilinoComodatario"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"), double.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[42] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("update_tb_esenzione_operation");
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"), java.lang.String.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        param = new org.apache.axis.description.ParameterDesc(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"), org.apache.axis.description.ParameterDesc.IN, new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"), int.class, false, false);
        param.setNillable(true);
        oper.addParameter(param);
        oper.setReturnType(org.apache.axis.encoding.XMLType.AXIS_VOID);
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[43] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_tipologia_detrazione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tipologia_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tipologia_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[44] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_detrazione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[45] = oper;

        oper = new org.apache.axis.description.OperationDesc();
        oper.setName("select_all_tb_esenzione_operation");
        oper.setReturnType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione"));
        oper.setReturnClass(it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[].class);
        oper.setReturnQName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione"));
        oper.setStyle(org.apache.axis.constants.Style.WRAPPED);
        oper.setUse(org.apache.axis.constants.Use.LITERAL);
        _operations[46] = oper;

    }

    public CategorieImmobiliServiceSOAP12BindingStub() throws org.apache.axis.AxisFault {
         this(null);
    }

    public CategorieImmobiliServiceSOAP12BindingStub(java.net.URL endpointURL, javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
         this(service);
         super.cachedEndpoint = endpointURL;
    }

    public CategorieImmobiliServiceSOAP12BindingStub(javax.xml.rpc.Service service) throws org.apache.axis.AxisFault {
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
            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_agevolazione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_detrazione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile_tributo");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tipologia_detrazione");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione.class;
            cachedSerClasses.add(cls);
            cachedSerFactories.add(beansf);
            cachedDeserFactories.add(beandf);

            qName = new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_tributo");
            cachedSerQNames.add(qName);
            cls = it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo.class;
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

    public void update_tb_agevolazione_operation(java.lang.String nome, double valore, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[0]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {nome, new java.lang.Double(valore), new java.lang.Integer(ID)});

    }

    public void delete_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[1]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_categoriaimmobile_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_categoriaimmobile_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[] select_all_tb_categoriaimmobile_agevolazione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[2]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_with_key_tb_categoriaimmobile_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[3]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_categoriaimmobile_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_categoriaimmobile_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(ID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_all_tb_categoriaimmobile_by_anno_operation(int anno) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[4]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_by_anno_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_by_anno_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(anno)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void delete_tb_tipologia_detrazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[5]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_tipologia_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_tipologia_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public void insert_tb_esenzione_operation(int ID, java.lang.String descrizione, int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[6]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_esenzione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_esenzione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), descrizione, new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo)});

    }

    public void insert_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_agevolazione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[7]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_categoriaimmobile_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_categoriaimmobile_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo), new java.lang.Integer(fk_agevolazione)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[] select_with_key_tb_tipologia_detrazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[8]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_tipologia_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_tipologia_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(ID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[] select_all_tb_categoriaimmobile_detrazione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[9]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void insert_tb_tributo_operation(int ID, java.lang.String nome) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[10]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), nome});

    }

    public void update_tb_tipologia_detrazione_operation(java.lang.String codice, java.lang.String descrizione, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[11]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_tipologia_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_tipologia_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {codice, descrizione, new java.lang.Integer(ID)});

    }

    public void delete_tb_detrazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[12]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[] select_with_key_tb_detrazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[13]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(ID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void update_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_agevolazione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[14]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_categoriaimmobile_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_categoriaimmobile_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo), new java.lang.Integer(fk_agevolazione)});

    }

    public void insert_tb_detrazione_operation(int ID, java.lang.String descrizione, double importo, java.lang.String info, int fk_tipologia_detrazione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[15]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), descrizione, new java.lang.Double(importo), info, new java.lang.Integer(fk_tipologia_detrazione)});

    }

    public void insert_tb_categoriaimmobile_operation(int ID, java.lang.String codice, java.lang.String descrizione, double coefficienteRivalutazione, double coefficienteMoltiplicazione, double percentualeProprietario, double percentualeInquilinoComodatario) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[16]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_categoriaimmobile_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_categoriaimmobile_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), codice, descrizione, new java.lang.Double(coefficienteRivalutazione), new java.lang.Double(coefficienteMoltiplicazione), new java.lang.Double(percentualeProprietario), new java.lang.Double(percentualeInquilinoComodatario)});

    }

    public void insert_tb_tipologia_detrazione_operation(int ID, java.lang.String codice, java.lang.String descrizione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[17]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_tipologia_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_tipologia_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), codice, descrizione});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[] select_with_key_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[18]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_categoriaimmobile_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_categoriaimmobile_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[] select_with_key_tb_esenzione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[19]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_esenzione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_esenzione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[] select_with_key_tb_tributo_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[20]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(ID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[] select_with_key_tb_agevolazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[21]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(ID)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_all_tb_categoriaimmobile_tributo_by_anno_operation(int anno) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[22]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_tributo_by_anno_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_tributo_by_anno_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(anno)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void update_tb_tributo_operation(java.lang.String nome, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[23]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {nome, new java.lang.Integer(ID)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_all_tb_categoriaimmobile_tributo_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[24]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void delete_tb_esenzione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[25]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_esenzione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_esenzione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public void update_tb_detrazione_operation(java.lang.String descrizione, double importo, java.lang.String info, int fk_tipologia_detrazione, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[26]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {descrizione, new java.lang.Double(importo), info, new java.lang.Integer(fk_tipologia_detrazione), new java.lang.Integer(ID)});

    }

    public void update_tb_categoriaimmobile_tributo_operation(int fk_tributo, int codice_tributo, double aliquota, int anno, int fk_categoriaimmobile) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[27]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_categoriaimmobile_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_categoriaimmobile_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_tributo), new java.lang.Integer(codice_tributo), new java.lang.Double(aliquota), new java.lang.Integer(anno), new java.lang.Integer(fk_categoriaimmobile)});

    }

    public void insert_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile, int fk_tributo, int codice_tributo, double aliquota, int anno) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[28]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_categoriaimmobile_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_categoriaimmobile_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo), new java.lang.Integer(codice_tributo), new java.lang.Double(aliquota), new java.lang.Integer(anno)});

    }

    public void delete_tb_tributo_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[29]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public void insert_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_detrazione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[30]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_categoriaimmobile_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_categoriaimmobile_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo), new java.lang.Integer(fk_detrazione)});

    }

    public void delete_tb_categoriaimmobile_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[31]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_categoriaimmobile_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_categoriaimmobile_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public void update_tb_categoriaimmobile_detrazione_operation(int fk_tributo, int fk_detrazione, int fk_categoriaimmobile) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[32]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_categoriaimmobile_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_categoriaimmobile_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_tributo), new java.lang.Integer(fk_detrazione), new java.lang.Integer(fk_categoriaimmobile)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_with_key_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[33]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_categoriaimmobile_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_categoriaimmobile_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[] select_all_tb_agevolazione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[34]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void delete_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[35]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_categoriaimmobile_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_categoriaimmobile_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile)});

    }

    public void insert_tb_agevolazione_operation(int ID, java.lang.String nome, double valore) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[36]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:insert_tb_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "insert_tb_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID), nome, new java.lang.Double(valore)});

    }

    public void delete_tb_categoriaimmobile_agevolazione_operation(int fk_agevolazione) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[37]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_categoriaimmobile_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_categoriaimmobile_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(fk_agevolazione)});

    }

    public void delete_tb_agevolazione_operation(int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[38]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:delete_tb_agevolazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "delete_tb_agevolazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {new java.lang.Integer(ID)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[] select_with_key_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[39]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_with_key_tb_categoriaimmobile_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_with_key_tb_categoriaimmobile_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo)});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_all_tb_categoriaimmobile_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[40]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_categoriaimmobile_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_categoriaimmobile_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[] select_all_tb_tributo_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[41]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_tributo_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_tributo_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public void update_tb_categoriaimmobile_operation(java.lang.String codice, java.lang.String descrizione, double coefficienteRivalutazione, double coefficienteMoltiplicazione, double percentualeProprietario, double percentualeInquilinoComodatario, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[42]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_categoriaimmobile_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_categoriaimmobile_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {codice, descrizione, new java.lang.Double(coefficienteRivalutazione), new java.lang.Double(coefficienteMoltiplicazione), new java.lang.Double(percentualeProprietario), new java.lang.Double(percentualeInquilinoComodatario), new java.lang.Integer(ID)});

    }

    public void update_tb_esenzione_operation(java.lang.String descrizione, int fk_categoriaimmobile, int fk_tributo, int ID) throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[43]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:update_tb_esenzione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "update_tb_esenzione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
        _call.invokeOneWay(new java.lang.Object[] {descrizione, new java.lang.Integer(fk_categoriaimmobile), new java.lang.Integer(fk_tributo), new java.lang.Integer(ID)});

    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[] select_all_tb_tipologia_detrazione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[44]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_tipologia_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_tipologia_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[] select_all_tb_detrazione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[45]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_detrazione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_detrazione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[] select_all_tb_esenzione_operation() throws java.rmi.RemoteException {
        if (super.cachedEndpoint == null) {
            throw new org.apache.axis.NoEndPointException();
        }
        org.apache.axis.client.Call _call = createCall();
        _call.setOperation(_operations[46]);
        _call.setUseSOAPAction(true);
        _call.setSOAPActionURI("urn:select_all_tb_esenzione_operation");
        _call.setEncodingStyle(null);
        _call.setProperty(org.apache.axis.client.Call.SEND_TYPE_ATTR, Boolean.FALSE);
        _call.setProperty(org.apache.axis.AxisEngine.PROP_DOMULTIREFS, Boolean.FALSE);
        _call.setSOAPVersion(org.apache.axis.soap.SOAPConstants.SOAP12_CONSTANTS);
        _call.setOperationName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "select_all_tb_esenzione_operation"));

        setRequestHeaders(_call);
        setAttachments(_call);
 try {        java.lang.Object _resp = _call.invoke(new java.lang.Object[] {});

        if (_resp instanceof java.rmi.RemoteException) {
            throw (java.rmi.RemoteException)_resp;
        }
        else {
            extractAttachments(_call);
            try {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[]) _resp;
            } catch (java.lang.Exception _exception) {
                return (it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[]) org.apache.axis.utils.JavaUtils.convert(_resp, it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[].class);
            }
        }
  } catch (org.apache.axis.AxisFault axisFaultException) {
  throw axisFaultException;
}
    }

}
