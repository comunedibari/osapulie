/**
 * AP3InterrogaDocRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class AP3InterrogaDocRequest  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceRichiedente;

    private it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceInterrogato;

    private java.lang.String codiceDocInf;

    public AP3InterrogaDocRequest() {
    }

    public AP3InterrogaDocRequest(
           it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceRichiedente,
           it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceInterrogato,
           java.lang.String codiceDocInf) {
           this.codiceRichiedente = codiceRichiedente;
           this.codiceInterrogato = codiceInterrogato;
           this.codiceDocInf = codiceDocInf;
    }


    /**
     * Gets the codiceRichiedente value for this AP3InterrogaDocRequest.
     * 
     * @return codiceRichiedente
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea getCodiceRichiedente() {
        return codiceRichiedente;
    }


    /**
     * Sets the codiceRichiedente value for this AP3InterrogaDocRequest.
     * 
     * @param codiceRichiedente
     */
    public void setCodiceRichiedente(it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceRichiedente) {
        this.codiceRichiedente = codiceRichiedente;
    }


    /**
     * Gets the codiceInterrogato value for this AP3InterrogaDocRequest.
     * 
     * @return codiceInterrogato
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea getCodiceInterrogato() {
        return codiceInterrogato;
    }


    /**
     * Sets the codiceInterrogato value for this AP3InterrogaDocRequest.
     * 
     * @param codiceInterrogato
     */
    public void setCodiceInterrogato(it.osapulie.pdds.ws.client.protocollo.interrogaDoc.CodiceArea codiceInterrogato) {
        this.codiceInterrogato = codiceInterrogato;
    }


    /**
     * Gets the codiceDocInf value for this AP3InterrogaDocRequest.
     * 
     * @return codiceDocInf
     */
    public java.lang.String getCodiceDocInf() {
        return codiceDocInf;
    }


    /**
     * Sets the codiceDocInf value for this AP3InterrogaDocRequest.
     * 
     * @param codiceDocInf
     */
    public void setCodiceDocInf(java.lang.String codiceDocInf) {
        this.codiceDocInf = codiceDocInf;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3InterrogaDocRequest)) return false;
        AP3InterrogaDocRequest other = (AP3InterrogaDocRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceRichiedente==null && other.getCodiceRichiedente()==null) || 
             (this.codiceRichiedente!=null &&
              this.codiceRichiedente.equals(other.getCodiceRichiedente()))) &&
            ((this.codiceInterrogato==null && other.getCodiceInterrogato()==null) || 
             (this.codiceInterrogato!=null &&
              this.codiceInterrogato.equals(other.getCodiceInterrogato()))) &&
            ((this.codiceDocInf==null && other.getCodiceDocInf()==null) || 
             (this.codiceDocInf!=null &&
              this.codiceDocInf.equals(other.getCodiceDocInf())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCodiceRichiedente() != null) {
            _hashCode += getCodiceRichiedente().hashCode();
        }
        if (getCodiceInterrogato() != null) {
            _hashCode += getCodiceInterrogato().hashCode();
        }
        if (getCodiceDocInf() != null) {
            _hashCode += getCodiceDocInf().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AP3InterrogaDocRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceRichiedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceRichiedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceInterrogato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceInterrogato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceDocInf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceDocInf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
