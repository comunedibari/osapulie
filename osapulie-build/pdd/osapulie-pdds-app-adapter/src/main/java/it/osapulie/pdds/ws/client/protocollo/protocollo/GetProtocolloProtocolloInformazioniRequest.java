/**
 * GetProtocolloProtocolloInformazioniRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class GetProtocolloProtocolloInformazioniRequest  implements java.io.Serializable {
    private long numeroProtocollo;

    private java.lang.String anno;

    public GetProtocolloProtocolloInformazioniRequest() {
    }

    public GetProtocolloProtocolloInformazioniRequest(
           long numeroProtocollo,
           java.lang.String anno) {
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
    }


    /**
     * Gets the numeroProtocollo value for this GetProtocolloProtocolloInformazioniRequest.
     * 
     * @return numeroProtocollo
     */
    public long getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this GetProtocolloProtocolloInformazioniRequest.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(long numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this GetProtocolloProtocolloInformazioniRequest.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this GetProtocolloProtocolloInformazioniRequest.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof GetProtocolloProtocolloInformazioniRequest)) return false;
        GetProtocolloProtocolloInformazioniRequest other = (GetProtocolloProtocolloInformazioniRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.numeroProtocollo == other.getNumeroProtocollo() &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno())));
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
        _hashCode += new Long(getNumeroProtocollo()).hashCode();
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(GetProtocolloProtocolloInformazioniRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">getProtocollo>protocolloInformazioniRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
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
