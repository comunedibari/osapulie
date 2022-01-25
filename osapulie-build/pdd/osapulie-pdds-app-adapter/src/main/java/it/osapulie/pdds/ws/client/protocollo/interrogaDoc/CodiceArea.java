/**
 * CodiceArea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class CodiceArea  implements java.io.Serializable {
    private java.lang.String codiceAmm;

    private java.lang.String codiceAoo;

    public CodiceArea() {
    }

    public CodiceArea(
           java.lang.String codiceAmm,
           java.lang.String codiceAoo) {
           this.codiceAmm = codiceAmm;
           this.codiceAoo = codiceAoo;
    }


    /**
     * Gets the codiceAmm value for this CodiceArea.
     * 
     * @return codiceAmm
     */
    public java.lang.String getCodiceAmm() {
        return codiceAmm;
    }


    /**
     * Sets the codiceAmm value for this CodiceArea.
     * 
     * @param codiceAmm
     */
    public void setCodiceAmm(java.lang.String codiceAmm) {
        this.codiceAmm = codiceAmm;
    }


    /**
     * Gets the codiceAoo value for this CodiceArea.
     * 
     * @return codiceAoo
     */
    public java.lang.String getCodiceAoo() {
        return codiceAoo;
    }


    /**
     * Sets the codiceAoo value for this CodiceArea.
     * 
     * @param codiceAoo
     */
    public void setCodiceAoo(java.lang.String codiceAoo) {
        this.codiceAoo = codiceAoo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CodiceArea)) return false;
        CodiceArea other = (CodiceArea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceAmm==null && other.getCodiceAmm()==null) || 
             (this.codiceAmm!=null &&
              this.codiceAmm.equals(other.getCodiceAmm()))) &&
            ((this.codiceAoo==null && other.getCodiceAoo()==null) || 
             (this.codiceAoo!=null &&
              this.codiceAoo.equals(other.getCodiceAoo())));
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
        if (getCodiceAmm() != null) {
            _hashCode += getCodiceAmm().hashCode();
        }
        if (getCodiceAoo() != null) {
            _hashCode += getCodiceAoo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CodiceArea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAmm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceAmm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAoo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceAoo"));
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
