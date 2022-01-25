/**
 * ImprontaMIME.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class ImprontaMIME  implements java.io.Serializable, org.apache.axis.encoding.SimpleType {
    private java.lang.String _value;

    private java.lang.String algoritmo;  // attribute

    private java.lang.String codifica;  // attribute

    public ImprontaMIME() {
    }

    // Simple Types must have a String constructor
    public ImprontaMIME(java.lang.String _value) {
        this._value = _value;
    }
    // Simple Types must have a toString for serializing the value
    public java.lang.String toString() {
        return _value;
    }


    /**
     * Gets the _value value for this ImprontaMIME.
     * 
     * @return _value
     */
    public java.lang.String get_value() {
        return _value;
    }


    /**
     * Sets the _value value for this ImprontaMIME.
     * 
     * @param _value
     */
    public void set_value(java.lang.String _value) {
        this._value = _value;
    }


    /**
     * Gets the algoritmo value for this ImprontaMIME.
     * 
     * @return algoritmo
     */
    public java.lang.String getAlgoritmo() {
        return algoritmo;
    }


    /**
     * Sets the algoritmo value for this ImprontaMIME.
     * 
     * @param algoritmo
     */
    public void setAlgoritmo(java.lang.String algoritmo) {
        this.algoritmo = algoritmo;
    }


    /**
     * Gets the codifica value for this ImprontaMIME.
     * 
     * @return codifica
     */
    public java.lang.String getCodifica() {
        return codifica;
    }


    /**
     * Sets the codifica value for this ImprontaMIME.
     * 
     * @param codifica
     */
    public void setCodifica(java.lang.String codifica) {
        this.codifica = codifica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ImprontaMIME)) return false;
        ImprontaMIME other = (ImprontaMIME) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this._value==null && other.get_value()==null) || 
             (this._value!=null &&
              this._value.equals(other.get_value()))) &&
            ((this.algoritmo==null && other.getAlgoritmo()==null) || 
             (this.algoritmo!=null &&
              this.algoritmo.equals(other.getAlgoritmo()))) &&
            ((this.codifica==null && other.getCodifica()==null) || 
             (this.codifica!=null &&
              this.codifica.equals(other.getCodifica())));
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
        if (get_value() != null) {
            _hashCode += get_value().hashCode();
        }
        if (getAlgoritmo() != null) {
            _hashCode += getAlgoritmo().hashCode();
        }
        if (getCodifica() != null) {
            _hashCode += getCodifica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ImprontaMIME.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "ImprontaMIME"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("algoritmo");
        attrField.setXmlName(new javax.xml.namespace.QName("", "algoritmo"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("codifica");
        attrField.setXmlName(new javax.xml.namespace.QName("", "codifica"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("_value");
        elemField.setXmlName(new javax.xml.namespace.QName("", "_value"));
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
          new  org.apache.axis.encoding.ser.SimpleSerializer(
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
          new  org.apache.axis.encoding.ser.SimpleDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
