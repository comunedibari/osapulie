/**
 * EstremiProtocolloEstesi.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class EstremiProtocolloEstesi  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocollo;

    private java.lang.String arrivoPartenza;

    public EstremiProtocolloEstesi() {
    }

    public EstremiProtocolloEstesi(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocollo,
           java.lang.String arrivoPartenza) {
           this.estremiProtocollo = estremiProtocollo;
           this.arrivoPartenza = arrivoPartenza;
    }


    /**
     * Gets the estremiProtocollo value for this EstremiProtocolloEstesi.
     * 
     * @return estremiProtocollo
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo getEstremiProtocollo() {
        return estremiProtocollo;
    }


    /**
     * Sets the estremiProtocollo value for this EstremiProtocolloEstesi.
     * 
     * @param estremiProtocollo
     */
    public void setEstremiProtocollo(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocollo) {
        this.estremiProtocollo = estremiProtocollo;
    }


    /**
     * Gets the arrivoPartenza value for this EstremiProtocolloEstesi.
     * 
     * @return arrivoPartenza
     */
    public java.lang.String getArrivoPartenza() {
        return arrivoPartenza;
    }


    /**
     * Sets the arrivoPartenza value for this EstremiProtocolloEstesi.
     * 
     * @param arrivoPartenza
     */
    public void setArrivoPartenza(java.lang.String arrivoPartenza) {
        this.arrivoPartenza = arrivoPartenza;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstremiProtocolloEstesi)) return false;
        EstremiProtocolloEstesi other = (EstremiProtocolloEstesi) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.estremiProtocollo==null && other.getEstremiProtocollo()==null) || 
             (this.estremiProtocollo!=null &&
              this.estremiProtocollo.equals(other.getEstremiProtocollo()))) &&
            ((this.arrivoPartenza==null && other.getArrivoPartenza()==null) || 
             (this.arrivoPartenza!=null &&
              this.arrivoPartenza.equals(other.getArrivoPartenza())));
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
        if (getEstremiProtocollo() != null) {
            _hashCode += getEstremiProtocollo().hashCode();
        }
        if (getArrivoPartenza() != null) {
            _hashCode += getArrivoPartenza().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstremiProtocolloEstesi.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocolloEstesi"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("arrivoPartenza");
        elemField.setXmlName(new javax.xml.namespace.QName("", "arrivoPartenza"));
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
