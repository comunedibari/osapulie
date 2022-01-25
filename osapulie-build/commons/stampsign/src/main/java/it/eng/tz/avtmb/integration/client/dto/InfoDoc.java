/**
 * InfoDoc.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class InfoDoc  implements java.io.Serializable {
    private org.apache.axis.types.NormalizedString infoDocKey;

    private org.apache.axis.types.NormalizedString infoDocValue;

    public InfoDoc() {
    }

    public InfoDoc(
           org.apache.axis.types.NormalizedString infoDocKey,
           org.apache.axis.types.NormalizedString infoDocValue) {
           this.infoDocKey = infoDocKey;
           this.infoDocValue = infoDocValue;
    }


    /**
     * Gets the infoDocKey value for this InfoDoc.
     * 
     * @return infoDocKey
     */
    public org.apache.axis.types.NormalizedString getInfoDocKey() {
        return infoDocKey;
    }


    /**
     * Sets the infoDocKey value for this InfoDoc.
     * 
     * @param infoDocKey
     */
    public void setInfoDocKey(org.apache.axis.types.NormalizedString infoDocKey) {
        this.infoDocKey = infoDocKey;
    }


    /**
     * Gets the infoDocValue value for this InfoDoc.
     * 
     * @return infoDocValue
     */
    public org.apache.axis.types.NormalizedString getInfoDocValue() {
        return infoDocValue;
    }


    /**
     * Sets the infoDocValue value for this InfoDoc.
     * 
     * @param infoDocValue
     */
    public void setInfoDocValue(org.apache.axis.types.NormalizedString infoDocValue) {
        this.infoDocValue = infoDocValue;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InfoDoc)) return false;
        InfoDoc other = (InfoDoc) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.infoDocKey==null && other.getInfoDocKey()==null) || 
             (this.infoDocKey!=null &&
              this.infoDocKey.equals(other.getInfoDocKey()))) &&
            ((this.infoDocValue==null && other.getInfoDocValue()==null) || 
             (this.infoDocValue!=null &&
              this.infoDocValue.equals(other.getInfoDocValue())));
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
        if (getInfoDocKey() != null) {
            _hashCode += getInfoDocKey().hashCode();
        }
        if (getInfoDocValue() != null) {
            _hashCode += getInfoDocValue().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InfoDoc.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "infoDoc"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoDocKey");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "infoDocKey"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoDocValue");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "infoDocValue"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
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
