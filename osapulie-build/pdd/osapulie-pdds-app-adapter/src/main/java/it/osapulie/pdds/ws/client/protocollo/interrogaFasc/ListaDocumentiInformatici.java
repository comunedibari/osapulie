/**
 * ListaDocumentiInformatici.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class ListaDocumentiInformatici  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico[] documentoInformatico;

    public ListaDocumentiInformatici() {
    }

    public ListaDocumentiInformatici(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico[] documentoInformatico) {
           this.documentoInformatico = documentoInformatico;
    }


    /**
     * Gets the documentoInformatico value for this ListaDocumentiInformatici.
     * 
     * @return documentoInformatico
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico[] getDocumentoInformatico() {
        return documentoInformatico;
    }


    /**
     * Sets the documentoInformatico value for this ListaDocumentiInformatici.
     * 
     * @param documentoInformatico
     */
    public void setDocumentoInformatico(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico[] documentoInformatico) {
        this.documentoInformatico = documentoInformatico;
    }

    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico getDocumentoInformatico(int i) {
        return this.documentoInformatico[i];
    }

    public void setDocumentoInformatico(int i, it.osapulie.pdds.ws.client.protocollo.interrogaFasc.DocumentoInformatico _value) {
        this.documentoInformatico[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaDocumentiInformatici)) return false;
        ListaDocumentiInformatici other = (ListaDocumentiInformatici) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.documentoInformatico==null && other.getDocumentoInformatico()==null) || 
             (this.documentoInformatico!=null &&
              java.util.Arrays.equals(this.documentoInformatico, other.getDocumentoInformatico())));
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
        if (getDocumentoInformatico() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDocumentoInformatico());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDocumentoInformatico(), i);
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
        new org.apache.axis.description.TypeDesc(ListaDocumentiInformatici.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumentiInformatici"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentoInformatico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentoInformatico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documentoInformatico"));
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
