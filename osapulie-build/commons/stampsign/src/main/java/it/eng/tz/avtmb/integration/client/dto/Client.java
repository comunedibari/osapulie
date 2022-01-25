/**
 * Client.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class Client  implements java.io.Serializable {
    private org.apache.axis.types.NormalizedString aoo;

    private org.apache.axis.types.NormalizedString clientId;

    public Client() {
    }

    public Client(
           org.apache.axis.types.NormalizedString aoo,
           org.apache.axis.types.NormalizedString clientId) {
           this.aoo = aoo;
           this.clientId = clientId;
    }


    /**
     * Gets the aoo value for this Client.
     * 
     * @return aoo
     */
    public org.apache.axis.types.NormalizedString getAoo() {
        return aoo;
    }


    /**
     * Sets the aoo value for this Client.
     * 
     * @param aoo
     */
    public void setAoo(org.apache.axis.types.NormalizedString aoo) {
        this.aoo = aoo;
    }


    /**
     * Gets the clientId value for this Client.
     * 
     * @return clientId
     */
    public org.apache.axis.types.NormalizedString getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Client.
     * 
     * @param clientId
     */
    public void setClientId(org.apache.axis.types.NormalizedString clientId) {
        this.clientId = clientId;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Client)) return false;
        Client other = (Client) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.aoo==null && other.getAoo()==null) || 
             (this.aoo!=null &&
              this.aoo.equals(other.getAoo()))) &&
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId())));
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
        if (getAoo() != null) {
            _hashCode += getAoo().hashCode();
        }
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Client.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "client"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("aoo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "aoo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "clientId"));
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
