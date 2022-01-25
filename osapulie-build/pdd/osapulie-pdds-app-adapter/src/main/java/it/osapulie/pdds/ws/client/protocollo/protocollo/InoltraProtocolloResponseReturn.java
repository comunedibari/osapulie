/**
 * InoltraProtocolloResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class InoltraProtocolloResponseReturn  implements java.io.Serializable {
    private java.lang.Integer id;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore;

    public InoltraProtocolloResponseReturn() {
    }

    public InoltraProtocolloResponseReturn(
           java.lang.Integer id,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
           this.id = id;
           this.errore = errore;
    }


    /**
     * Gets the id value for this InoltraProtocolloResponseReturn.
     * 
     * @return id
     */
    public java.lang.Integer getId() {
        return id;
    }


    /**
     * Sets the id value for this InoltraProtocolloResponseReturn.
     * 
     * @param id
     */
    public void setId(java.lang.Integer id) {
        this.id = id;
    }


    /**
     * Gets the errore value for this InoltraProtocolloResponseReturn.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this InoltraProtocolloResponseReturn.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
        this.errore = errore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InoltraProtocolloResponseReturn)) return false;
        InoltraProtocolloResponseReturn other = (InoltraProtocolloResponseReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InoltraProtocolloResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocolloResponse>return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Errore"));
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
