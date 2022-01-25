/**
 * Errore.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class Errore  implements java.io.Serializable {
    private int codiceErrore;

    private java.lang.String msgErrore;

    public Errore() {
    }

    public Errore(
           int codiceErrore,
           java.lang.String msgErrore) {
           this.codiceErrore = codiceErrore;
           this.msgErrore = msgErrore;
    }


    /**
     * Gets the codiceErrore value for this Errore.
     * 
     * @return codiceErrore
     */
    public int getCodiceErrore() {
        return codiceErrore;
    }


    /**
     * Sets the codiceErrore value for this Errore.
     * 
     * @param codiceErrore
     */
    public void setCodiceErrore(int codiceErrore) {
        this.codiceErrore = codiceErrore;
    }


    /**
     * Gets the msgErrore value for this Errore.
     * 
     * @return msgErrore
     */
    public java.lang.String getMsgErrore() {
        return msgErrore;
    }


    /**
     * Sets the msgErrore value for this Errore.
     * 
     * @param msgErrore
     */
    public void setMsgErrore(java.lang.String msgErrore) {
        this.msgErrore = msgErrore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Errore)) return false;
        Errore other = (Errore) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.codiceErrore == other.getCodiceErrore() &&
            ((this.msgErrore==null && other.getMsgErrore()==null) || 
             (this.msgErrore!=null &&
              this.msgErrore.equals(other.getMsgErrore())));
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
        _hashCode += getCodiceErrore();
        if (getMsgErrore() != null) {
            _hashCode += getMsgErrore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Errore.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "errore"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceErrore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceErrore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("msgErrore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "msgErrore"));
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
