/**
 * StampSignRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class StampSignRequest  implements java.io.Serializable {
    private it.eng.tz.avtmb.integration.client.dto.Stamp stamp;

    private it.eng.tz.avtmb.integration.client.dto.Sign sign;

    private StampSign stampSign;

    private it.eng.tz.avtmb.integration.client.dto.RequestType requestType;  // attribute

    private org.apache.axis.types.Id id;  // attribute

    public StampSignRequest() {
    }

    public StampSignRequest(
           it.eng.tz.avtmb.integration.client.dto.Stamp stamp,
           it.eng.tz.avtmb.integration.client.dto.Sign sign,
           StampSign stampSign,
           it.eng.tz.avtmb.integration.client.dto.RequestType requestType,
           org.apache.axis.types.Id id) {
           this.stamp = stamp;
           this.sign = sign;
           this.stampSign = stampSign;
           this.requestType = requestType;
           this.id = id;
    }


    /**
     * Gets the stamp value for this StampSignRequest.
     * 
     * @return stamp
     */
    public it.eng.tz.avtmb.integration.client.dto.Stamp getStamp() {
        return stamp;
    }


    /**
     * Sets the stamp value for this StampSignRequest.
     * 
     * @param stamp
     */
    public void setStamp(it.eng.tz.avtmb.integration.client.dto.Stamp stamp) {
        this.stamp = stamp;
    }


    /**
     * Gets the sign value for this StampSignRequest.
     * 
     * @return sign
     */
    public it.eng.tz.avtmb.integration.client.dto.Sign getSign() {
        return sign;
    }


    /**
     * Sets the sign value for this StampSignRequest.
     * 
     * @param sign
     */
    public void setSign(it.eng.tz.avtmb.integration.client.dto.Sign sign) {
        this.sign = sign;
    }


    /**
     * Gets the stampSign value for this StampSignRequest.
     * 
     * @return stampSign
     */
    public StampSign getStampSign() {
        return stampSign;
    }


    /**
     * Sets the stampSign value for this StampSignRequest.
     * 
     * @param stampSign
     */
    public void setStampSign(StampSign stampSign) {
        this.stampSign = stampSign;
    }


    /**
     * Gets the requestType value for this StampSignRequest.
     * 
     * @return requestType
     */
    public it.eng.tz.avtmb.integration.client.dto.RequestType getRequestType() {
        return requestType;
    }


    /**
     * Sets the requestType value for this StampSignRequest.
     * 
     * @param requestType
     */
    public void setRequestType(it.eng.tz.avtmb.integration.client.dto.RequestType requestType) {
        this.requestType = requestType;
    }


    /**
     * Gets the id value for this StampSignRequest.
     * 
     * @return id
     */
    public org.apache.axis.types.Id getId() {
        return id;
    }


    /**
     * Sets the id value for this StampSignRequest.
     * 
     * @param id
     */
    public void setId(org.apache.axis.types.Id id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StampSignRequest)) return false;
        StampSignRequest other = (StampSignRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.stamp==null && other.getStamp()==null) || 
             (this.stamp!=null &&
              this.stamp.equals(other.getStamp()))) &&
            ((this.sign==null && other.getSign()==null) || 
             (this.sign!=null &&
              this.sign.equals(other.getSign()))) &&
            ((this.stampSign==null && other.getStampSign()==null) || 
             (this.stampSign!=null &&
              this.stampSign.equals(other.getStampSign()))) &&
            ((this.requestType==null && other.getRequestType()==null) || 
             (this.requestType!=null &&
              this.requestType.equals(other.getRequestType()))) &&
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId())));
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
        if (getStamp() != null) {
            _hashCode += getStamp().hashCode();
        }
        if (getSign() != null) {
            _hashCode += getSign().hashCode();
        }
        if (getStampSign() != null) {
            _hashCode += getStampSign().hashCode();
        }
        if (getRequestType() != null) {
            _hashCode += getRequestType().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StampSignRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", ">stampSignRequest"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("requestType");
        attrField.setXmlName(new javax.xml.namespace.QName("", "requestType"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "requestType"));
        typeDesc.addFieldDesc(attrField);
        attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "ID"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stamp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "stamp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "stamp"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sign");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "sign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "sign"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("stampSign");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "stampSign"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "stampSign1"));
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
