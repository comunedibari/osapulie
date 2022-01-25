/**
 * GetAllegatoResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class GetAllegatoResponseReturn  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato allegato;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore;

    public GetAllegatoResponseReturn() {
    }

    public GetAllegatoResponseReturn(
           it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato allegato,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
           this.allegato = allegato;
           this.errore = errore;
    }


    /**
     * Gets the allegato value for this GetAllegatoResponseReturn.
     * 
     * @return allegato
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato getAllegato() {
        return allegato;
    }


    /**
     * Sets the allegato value for this GetAllegatoResponseReturn.
     * 
     * @param allegato
     */
    public void setAllegato(it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato allegato) {
        this.allegato = allegato;
    }


    /**
     * Gets the errore value for this GetAllegatoResponseReturn.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this GetAllegatoResponseReturn.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
        this.errore = errore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetAllegatoResponseReturn)) return false;
        GetAllegatoResponseReturn other = (GetAllegatoResponseReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.allegato==null && other.getAllegato()==null) || 
             (this.allegato!=null &&
              this.allegato.equals(other.getAllegato()))) &&
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore())));
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
        if (getAllegato() != null) {
            _hashCode += getAllegato().hashCode();
        }
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetAllegatoResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getAllegatoResponse>return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allegato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Allegato"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Errore"));
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
