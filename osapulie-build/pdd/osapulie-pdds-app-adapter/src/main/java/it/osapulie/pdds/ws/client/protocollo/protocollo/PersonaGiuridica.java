/**
 * PersonaGiuridica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class PersonaGiuridica  implements java.io.Serializable {
    private java.lang.String ragioneSociale;

    private java.lang.String partitaIVA;

    public PersonaGiuridica() {
    }

    public PersonaGiuridica(
           java.lang.String ragioneSociale,
           java.lang.String partitaIVA) {
           this.ragioneSociale = ragioneSociale;
           this.partitaIVA = partitaIVA;
    }


    /**
     * Gets the ragioneSociale value for this PersonaGiuridica.
     * 
     * @return ragioneSociale
     */
    public java.lang.String getRagioneSociale() {
        return ragioneSociale;
    }


    /**
     * Sets the ragioneSociale value for this PersonaGiuridica.
     * 
     * @param ragioneSociale
     */
    public void setRagioneSociale(java.lang.String ragioneSociale) {
        this.ragioneSociale = ragioneSociale;
    }


    /**
     * Gets the partitaIVA value for this PersonaGiuridica.
     * 
     * @return partitaIVA
     */
    public java.lang.String getPartitaIVA() {
        return partitaIVA;
    }


    /**
     * Sets the partitaIVA value for this PersonaGiuridica.
     * 
     * @param partitaIVA
     */
    public void setPartitaIVA(java.lang.String partitaIVA) {
        this.partitaIVA = partitaIVA;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PersonaGiuridica)) return false;
        PersonaGiuridica other = (PersonaGiuridica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ragioneSociale==null && other.getRagioneSociale()==null) || 
             (this.ragioneSociale!=null &&
              this.ragioneSociale.equals(other.getRagioneSociale()))) &&
            ((this.partitaIVA==null && other.getPartitaIVA()==null) || 
             (this.partitaIVA!=null &&
              this.partitaIVA.equals(other.getPartitaIVA())));
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
        if (getRagioneSociale() != null) {
            _hashCode += getRagioneSociale().hashCode();
        }
        if (getPartitaIVA() != null) {
            _hashCode += getPartitaIVA().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PersonaGiuridica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaGiuridica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ragioneSociale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "ragioneSociale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("partitaIVA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "partitaIVA"));
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
