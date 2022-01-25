/**
 * ListaLivelli.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class ListaLivelli  implements java.io.Serializable {
    private java.lang.String[] livelloClassifica;

    public ListaLivelli() {
    }

    public ListaLivelli(
           java.lang.String[] livelloClassifica) {
           this.livelloClassifica = livelloClassifica;
    }


    /**
     * Gets the livelloClassifica value for this ListaLivelli.
     * 
     * @return livelloClassifica
     */
    public java.lang.String[] getLivelloClassifica() {
        return livelloClassifica;
    }


    /**
     * Sets the livelloClassifica value for this ListaLivelli.
     * 
     * @param livelloClassifica
     */
    public void setLivelloClassifica(java.lang.String[] livelloClassifica) {
        this.livelloClassifica = livelloClassifica;
    }

    public java.lang.String getLivelloClassifica(int i) {
        return this.livelloClassifica[i];
    }

    public void setLivelloClassifica(int i, java.lang.String _value) {
        this.livelloClassifica[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ListaLivelli)) return false;
        ListaLivelli other = (ListaLivelli) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.livelloClassifica==null && other.getLivelloClassifica()==null) || 
             (this.livelloClassifica!=null &&
              java.util.Arrays.equals(this.livelloClassifica, other.getLivelloClassifica())));
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
        if (getLivelloClassifica() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getLivelloClassifica());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getLivelloClassifica(), i);
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
        new org.apache.axis.description.TypeDesc(ListaLivelli.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaLivelli"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livelloClassifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "livelloClassifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
