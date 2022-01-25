/**
 * AP3InterrogaDocResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class AP3InterrogaDocResponse  implements java.io.Serializable {
    private java.lang.String mimeType;

    private java.lang.String nomeDocInf;

    private it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Errore errore;

    private boolean esito;  // attribute

    public AP3InterrogaDocResponse() {
    }

    public AP3InterrogaDocResponse(
           java.lang.String mimeType,
           java.lang.String nomeDocInf,
           it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Errore errore,
           boolean esito) {
           this.mimeType = mimeType;
           this.nomeDocInf = nomeDocInf;
           this.errore = errore;
           this.esito = esito;
    }


    /**
     * Gets the mimeType value for this AP3InterrogaDocResponse.
     * 
     * @return mimeType
     */
    public java.lang.String getMimeType() {
        return mimeType;
    }


    /**
     * Sets the mimeType value for this AP3InterrogaDocResponse.
     * 
     * @param mimeType
     */
    public void setMimeType(java.lang.String mimeType) {
        this.mimeType = mimeType;
    }


    /**
     * Gets the nomeDocInf value for this AP3InterrogaDocResponse.
     * 
     * @return nomeDocInf
     */
    public java.lang.String getNomeDocInf() {
        return nomeDocInf;
    }


    /**
     * Sets the nomeDocInf value for this AP3InterrogaDocResponse.
     * 
     * @param nomeDocInf
     */
    public void setNomeDocInf(java.lang.String nomeDocInf) {
        this.nomeDocInf = nomeDocInf;
    }


    /**
     * Gets the errore value for this AP3InterrogaDocResponse.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this AP3InterrogaDocResponse.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.interrogaDoc.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the esito value for this AP3InterrogaDocResponse.
     * 
     * @return esito
     */
    public boolean isEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this AP3InterrogaDocResponse.
     * 
     * @param esito
     */
    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3InterrogaDocResponse)) return false;
        AP3InterrogaDocResponse other = (AP3InterrogaDocResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mimeType==null && other.getMimeType()==null) || 
             (this.mimeType!=null &&
              this.mimeType.equals(other.getMimeType()))) &&
            ((this.nomeDocInf==null && other.getNomeDocInf()==null) || 
             (this.nomeDocInf!=null &&
              this.nomeDocInf.equals(other.getNomeDocInf()))) &&
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore()))) &&
            this.esito == other.isEsito();
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
        if (getMimeType() != null) {
            _hashCode += getMimeType().hashCode();
        }
        if (getNomeDocInf() != null) {
            _hashCode += getNomeDocInf().hashCode();
        }
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        _hashCode += (isEsito() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AP3InterrogaDocResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocResponse"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("esito");
        attrField.setXmlName(new javax.xml.namespace.QName("", "esito"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mimeType");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mimeType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeDocInf");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeDocInf"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "errore"));
        elemField.setMinOccurs(0);
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
