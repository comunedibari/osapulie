/**
 * Destinatario.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class Destinatario  implements java.io.Serializable {
    private java.lang.String areaOrganizzativaOmogenea;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica;

    private java.lang.String amministrazione;

    public Destinatario() {
    }

    public Destinatario(
           java.lang.String areaOrganizzativaOmogenea,
           it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica,
           java.lang.String amministrazione) {
           this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
           this.personaFisica = personaFisica;
           this.amministrazione = amministrazione;
    }


    /**
     * Gets the areaOrganizzativaOmogenea value for this Destinatario.
     * 
     * @return areaOrganizzativaOmogenea
     */
    public java.lang.String getAreaOrganizzativaOmogenea() {
        return areaOrganizzativaOmogenea;
    }


    /**
     * Sets the areaOrganizzativaOmogenea value for this Destinatario.
     * 
     * @param areaOrganizzativaOmogenea
     */
    public void setAreaOrganizzativaOmogenea(java.lang.String areaOrganizzativaOmogenea) {
        this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
    }


    /**
     * Gets the personaFisica value for this Destinatario.
     * 
     * @return personaFisica
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica getPersonaFisica() {
        return personaFisica;
    }


    /**
     * Sets the personaFisica value for this Destinatario.
     * 
     * @param personaFisica
     */
    public void setPersonaFisica(it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
    }


    /**
     * Gets the amministrazione value for this Destinatario.
     * 
     * @return amministrazione
     */
    public java.lang.String getAmministrazione() {
        return amministrazione;
    }


    /**
     * Sets the amministrazione value for this Destinatario.
     * 
     * @param amministrazione
     */
    public void setAmministrazione(java.lang.String amministrazione) {
        this.amministrazione = amministrazione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Destinatario)) return false;
        Destinatario other = (Destinatario) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.areaOrganizzativaOmogenea==null && other.getAreaOrganizzativaOmogenea()==null) || 
             (this.areaOrganizzativaOmogenea!=null &&
              this.areaOrganizzativaOmogenea.equals(other.getAreaOrganizzativaOmogenea()))) &&
            ((this.personaFisica==null && other.getPersonaFisica()==null) || 
             (this.personaFisica!=null &&
              this.personaFisica.equals(other.getPersonaFisica()))) &&
            ((this.amministrazione==null && other.getAmministrazione()==null) || 
             (this.amministrazione!=null &&
              this.amministrazione.equals(other.getAmministrazione())));
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
        if (getAreaOrganizzativaOmogenea() != null) {
            _hashCode += getAreaOrganizzativaOmogenea().hashCode();
        }
        if (getPersonaFisica() != null) {
            _hashCode += getPersonaFisica().hashCode();
        }
        if (getAmministrazione() != null) {
            _hashCode += getAmministrazione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Destinatario.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Destinatario"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaOrganizzativaOmogenea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaOrganizzativaOmogenea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaFisica"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amministrazione"));
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
