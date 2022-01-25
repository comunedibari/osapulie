/**
 * GetAllegatoDettaglioAllegatoRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class GetAllegatoDettaglioAllegatoRequest  implements java.io.Serializable {
    private java.lang.String identificativo;

    public GetAllegatoDettaglioAllegatoRequest() {
    }

    public GetAllegatoDettaglioAllegatoRequest(
           java.lang.String identificativo) {
           this.identificativo = identificativo;
    }


    /**
     * Gets the identificativo value for this GetAllegatoDettaglioAllegatoRequest.
     * 
     * @return identificativo
     */
    public java.lang.String getIdentificativo() {
        return identificativo;
    }


    /**
     * Sets the identificativo value for this GetAllegatoDettaglioAllegatoRequest.
     * 
     * @param identificativo
     */
    public void setIdentificativo(java.lang.String identificativo) {
        this.identificativo = identificativo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAllegatoDettaglioAllegatoRequest)) return false;
        GetAllegatoDettaglioAllegatoRequest other = (GetAllegatoDettaglioAllegatoRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.identificativo==null && other.getIdentificativo()==null) || 
             (this.identificativo!=null &&
              this.identificativo.equals(other.getIdentificativo())));
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
        if (getIdentificativo() != null) {
            _hashCode += getIdentificativo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllegatoDettaglioAllegatoRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegato>dettaglioAllegatoRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "identificativo"));
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
