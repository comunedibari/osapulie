/**
 * ListaDocumenti.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class ListaDocumenti  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento[] documento;

    public ListaDocumenti() {
    }

    public ListaDocumenti(
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento[] documento) {
           this.documento = documento;
    }


    /**
     * Gets the documento value for this ListaDocumenti.
     * 
     * @return documento
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento[] getDocumento() {
        return documento;
    }


    /**
     * Sets the documento value for this ListaDocumenti.
     * 
     * @param documento
     */
    public void setDocumento(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento[] documento) {
        this.documento = documento;
    }

    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento getDocumento(int i) {
        return this.documento[i];
    }

    public void setDocumento(int i, it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Documento _value) {
        this.documento[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaDocumenti)) return false;
        ListaDocumenti other = (ListaDocumenti) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documento==null && other.getDocumento()==null) || 
             (this.documento!=null &&
              java.util.Arrays.equals(this.documento, other.getDocumento())));
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
        if (getDocumento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumento(), i);
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
        new org.apache.axis.description.TypeDesc(ListaDocumenti.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumenti"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documento"));
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
