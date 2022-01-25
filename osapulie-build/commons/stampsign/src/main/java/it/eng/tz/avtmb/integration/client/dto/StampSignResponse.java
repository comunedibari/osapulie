/**
 * StampSignResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class StampSignResponse  implements java.io.Serializable {
    private it.eng.tz.avtmb.integration.client.dto.Errore errore;

    private it.eng.tz.avtmb.integration.client.dto.Risultato risultato;

    private org.apache.axis.types.Id id;  // attribute

    public StampSignResponse() {
    }

    public StampSignResponse(
           it.eng.tz.avtmb.integration.client.dto.Errore errore,
           it.eng.tz.avtmb.integration.client.dto.Risultato risultato,
           org.apache.axis.types.Id id) {
           this.errore = errore;
           this.risultato = risultato;
           this.id = id;
    }


    /**
     * Gets the errore value for this StampSignResponse.
     * 
     * @return errore
     */
    public it.eng.tz.avtmb.integration.client.dto.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this StampSignResponse.
     * 
     * @param errore
     */
    public void setErrore(it.eng.tz.avtmb.integration.client.dto.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the risultato value for this StampSignResponse.
     * 
     * @return risultato
     */
    public it.eng.tz.avtmb.integration.client.dto.Risultato getRisultato() {
        return risultato;
    }


    /**
     * Sets the risultato value for this StampSignResponse.
     * 
     * @param risultato
     */
    public void setRisultato(it.eng.tz.avtmb.integration.client.dto.Risultato risultato) {
        this.risultato = risultato;
    }


    /**
     * Gets the id value for this StampSignResponse.
     * 
     * @return id
     */
    public org.apache.axis.types.Id getId() {
        return id;
    }


    /**
     * Sets the id value for this StampSignResponse.
     * 
     * @param id
     */
    public void setId(org.apache.axis.types.Id id) {
        this.id = id;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof StampSignResponse)) return false;
        StampSignResponse other = (StampSignResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore()))) &&
            ((this.risultato==null && other.getRisultato()==null) || 
             (this.risultato!=null &&
              this.risultato.equals(other.getRisultato()))) &&
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
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        if (getRisultato() != null) {
            _hashCode += getRisultato().hashCode();
        }
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(StampSignResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", ">stampSignResponse"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("id");
        attrField.setXmlName(new javax.xml.namespace.QName("", "id"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "ID"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "errore"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("risultato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "risultato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "risultato"));
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
