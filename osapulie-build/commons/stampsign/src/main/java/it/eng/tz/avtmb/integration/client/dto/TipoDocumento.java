/**
 * TipoDocumento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class TipoDocumento implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected TipoDocumento(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _principale = "principale";
    public static final java.lang.String _allegato = "allegato";
    public static final java.lang.String _estratto = "estratto";
    public static final TipoDocumento principale = new TipoDocumento(_principale);
    public static final TipoDocumento allegato = new TipoDocumento(_allegato);
    public static final TipoDocumento estratto = new TipoDocumento(_estratto);
    public java.lang.String getValue() { return _value_;}
    public static TipoDocumento fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        TipoDocumento enumeration = (TipoDocumento)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static TipoDocumento fromString(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        return fromValue(value);
    }
    public boolean equals(java.lang.Object obj) {return (obj == this);}
    public int hashCode() { return toString().hashCode();}
    public java.lang.String toString() { return _value_;}
    public java.lang.Object readResolve() throws java.io.ObjectStreamException { return fromValue(_value_);}
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumSerializer(
            _javaType, _xmlType);
    }
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new org.apache.axis.encoding.ser.EnumDeserializer(
            _javaType, _xmlType);
    }
    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(TipoDocumento.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "tipoDocumento"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
