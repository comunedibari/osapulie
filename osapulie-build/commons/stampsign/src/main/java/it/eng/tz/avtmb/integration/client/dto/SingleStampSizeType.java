/**
 * SingleStampSizeType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class SingleStampSizeType implements java.io.Serializable {
    private java.lang.String _value_;
    private static java.util.HashMap _table_ = new java.util.HashMap();

    // Constructor
    protected SingleStampSizeType(java.lang.String value) {
        _value_ = value;
        _table_.put(_value_,this);
    }

    public static final java.lang.String _VENTTI = "VENTTI";
    public static final java.lang.String _QUARANTA = "QUARANTA";
    public static final java.lang.String _SESSANTA = "SESSANTA";
    public static final java.lang.String _OTTANTA = "OTTANTA";
    public static final SingleStampSizeType VENTTI = new SingleStampSizeType(_VENTTI);
    public static final SingleStampSizeType QUARANTA = new SingleStampSizeType(_QUARANTA);
    public static final SingleStampSizeType SESSANTA = new SingleStampSizeType(_SESSANTA);
    public static final SingleStampSizeType OTTANTA = new SingleStampSizeType(_OTTANTA);
    public java.lang.String getValue() { return _value_;}
    public static SingleStampSizeType fromValue(java.lang.String value)
          throws java.lang.IllegalArgumentException {
        SingleStampSizeType enumeration = (SingleStampSizeType)
            _table_.get(value);
        if (enumeration==null) throw new java.lang.IllegalArgumentException();
        return enumeration;
    }
    public static SingleStampSizeType fromString(java.lang.String value)
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
        new org.apache.axis.description.TypeDesc(SingleStampSizeType.class);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "singleStampSizeType"));
    }
    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

}
