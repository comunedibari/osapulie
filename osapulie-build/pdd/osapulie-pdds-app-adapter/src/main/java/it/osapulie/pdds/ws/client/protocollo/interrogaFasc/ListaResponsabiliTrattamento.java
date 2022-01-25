/**
 * ListaResponsabiliTrattamento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class ListaResponsabiliTrattamento  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento[] responsabileTrattamento;

    public ListaResponsabiliTrattamento() {
    }

    public ListaResponsabiliTrattamento(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento[] responsabileTrattamento) {
           this.responsabileTrattamento = responsabileTrattamento;
    }


    /**
     * Gets the responsabileTrattamento value for this ListaResponsabiliTrattamento.
     * 
     * @return responsabileTrattamento
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento[] getResponsabileTrattamento() {
        return responsabileTrattamento;
    }


    /**
     * Sets the responsabileTrattamento value for this ListaResponsabiliTrattamento.
     * 
     * @param responsabileTrattamento
     */
    public void setResponsabileTrattamento(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento[] responsabileTrattamento) {
        this.responsabileTrattamento = responsabileTrattamento;
    }

    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento getResponsabileTrattamento(int i) {
        return this.responsabileTrattamento[i];
    }

    public void setResponsabileTrattamento(int i, it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ResponsabileTrattamento _value) {
        this.responsabileTrattamento[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaResponsabiliTrattamento)) return false;
        ListaResponsabiliTrattamento other = (ListaResponsabiliTrattamento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.responsabileTrattamento==null && other.getResponsabileTrattamento()==null) || 
             (this.responsabileTrattamento!=null &&
              java.util.Arrays.equals(this.responsabileTrattamento, other.getResponsabileTrattamento())));
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
        if (getResponsabileTrattamento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getResponsabileTrattamento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getResponsabileTrattamento(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ListaResponsabiliTrattamento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaResponsabiliTrattamento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsabileTrattamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responsabileTrattamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "responsabileTrattamento"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
