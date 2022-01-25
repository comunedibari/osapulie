/**
 * ResponsabileTrattamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class ResponsabileTrattamento  implements java.io.Serializable {
    private java.lang.String codice_RresponsabileTrattamento;

    private java.lang.String descrizioneResponsabileTrattamento;

    public ResponsabileTrattamento() {
    }

    public ResponsabileTrattamento(
           java.lang.String codice_RresponsabileTrattamento,
           java.lang.String descrizioneResponsabileTrattamento) {
           this.codice_RresponsabileTrattamento = codice_RresponsabileTrattamento;
           this.descrizioneResponsabileTrattamento = descrizioneResponsabileTrattamento;
    }


    /**
     * Gets the codice_RresponsabileTrattamento value for this ResponsabileTrattamento.
     * 
     * @return codice_RresponsabileTrattamento
     */
    public java.lang.String getCodice_RresponsabileTrattamento() {
        return codice_RresponsabileTrattamento;
    }


    /**
     * Sets the codice_RresponsabileTrattamento value for this ResponsabileTrattamento.
     * 
     * @param codice_RresponsabileTrattamento
     */
    public void setCodice_RresponsabileTrattamento(java.lang.String codice_RresponsabileTrattamento) {
        this.codice_RresponsabileTrattamento = codice_RresponsabileTrattamento;
    }


    /**
     * Gets the descrizioneResponsabileTrattamento value for this ResponsabileTrattamento.
     * 
     * @return descrizioneResponsabileTrattamento
     */
    public java.lang.String getDescrizioneResponsabileTrattamento() {
        return descrizioneResponsabileTrattamento;
    }


    /**
     * Sets the descrizioneResponsabileTrattamento value for this ResponsabileTrattamento.
     * 
     * @param descrizioneResponsabileTrattamento
     */
    public void setDescrizioneResponsabileTrattamento(java.lang.String descrizioneResponsabileTrattamento) {
        this.descrizioneResponsabileTrattamento = descrizioneResponsabileTrattamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ResponsabileTrattamento)) return false;
        ResponsabileTrattamento other = (ResponsabileTrattamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codice_RresponsabileTrattamento==null && other.getCodice_RresponsabileTrattamento()==null) || 
             (this.codice_RresponsabileTrattamento!=null &&
              this.codice_RresponsabileTrattamento.equals(other.getCodice_RresponsabileTrattamento()))) &&
            ((this.descrizioneResponsabileTrattamento==null && other.getDescrizioneResponsabileTrattamento()==null) || 
             (this.descrizioneResponsabileTrattamento!=null &&
              this.descrizioneResponsabileTrattamento.equals(other.getDescrizioneResponsabileTrattamento())));
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
        if (getCodice_RresponsabileTrattamento() != null) {
            _hashCode += getCodice_RresponsabileTrattamento().hashCode();
        }
        if (getDescrizioneResponsabileTrattamento() != null) {
            _hashCode += getDescrizioneResponsabileTrattamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ResponsabileTrattamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "responsabileTrattamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codice_RresponsabileTrattamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codice_RresponsabileTrattamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneResponsabileTrattamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneResponsabileTrattamento"));
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
