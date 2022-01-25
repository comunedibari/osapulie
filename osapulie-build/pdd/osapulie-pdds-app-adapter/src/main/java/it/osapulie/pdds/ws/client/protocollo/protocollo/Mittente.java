/**
 * Mittente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class Mittente  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica personaGiuridica;

    private java.lang.String indirizzo;

    private java.lang.String comune;

    private java.lang.String nazione;

    private java.lang.String pecEmail;

    private java.lang.String email;

    public Mittente() {
    }

    public Mittente(
           it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica,
           it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica personaGiuridica,
           java.lang.String indirizzo,
           java.lang.String comune,
           java.lang.String nazione,
           java.lang.String pecEmail,
           java.lang.String email) {
           this.personaFisica = personaFisica;
           this.personaGiuridica = personaGiuridica;
           this.indirizzo = indirizzo;
           this.comune = comune;
           this.nazione = nazione;
           this.pecEmail = pecEmail;
           this.email = email;
    }


    /**
     * Gets the personaFisica value for this Mittente.
     * 
     * @return personaFisica
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica getPersonaFisica() {
        return personaFisica;
    }


    /**
     * Sets the personaFisica value for this Mittente.
     * 
     * @param personaFisica
     */
    public void setPersonaFisica(it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaFisica personaFisica) {
        this.personaFisica = personaFisica;
    }


    /**
     * Gets the personaGiuridica value for this Mittente.
     * 
     * @return personaGiuridica
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica getPersonaGiuridica() {
        return personaGiuridica;
    }


    /**
     * Sets the personaGiuridica value for this Mittente.
     * 
     * @param personaGiuridica
     */
    public void setPersonaGiuridica(it.osapulie.pdds.ws.client.protocollo.protocollo.PersonaGiuridica personaGiuridica) {
        this.personaGiuridica = personaGiuridica;
    }


    /**
     * Gets the indirizzo value for this Mittente.
     * 
     * @return indirizzo
     */
    public java.lang.String getIndirizzo() {
        return indirizzo;
    }


    /**
     * Sets the indirizzo value for this Mittente.
     * 
     * @param indirizzo
     */
    public void setIndirizzo(java.lang.String indirizzo) {
        this.indirizzo = indirizzo;
    }


    /**
     * Gets the comune value for this Mittente.
     * 
     * @return comune
     */
    public java.lang.String getComune() {
        return comune;
    }


    /**
     * Sets the comune value for this Mittente.
     * 
     * @param comune
     */
    public void setComune(java.lang.String comune) {
        this.comune = comune;
    }


    /**
     * Gets the nazione value for this Mittente.
     * 
     * @return nazione
     */
    public java.lang.String getNazione() {
        return nazione;
    }


    /**
     * Sets the nazione value for this Mittente.
     * 
     * @param nazione
     */
    public void setNazione(java.lang.String nazione) {
        this.nazione = nazione;
    }


    /**
     * Gets the pecEmail value for this Mittente.
     * 
     * @return pecEmail
     */
    public java.lang.String getPecEmail() {
        return pecEmail;
    }


    /**
     * Sets the pecEmail value for this Mittente.
     * 
     * @param pecEmail
     */
    public void setPecEmail(java.lang.String pecEmail) {
        this.pecEmail = pecEmail;
    }


    /**
     * Gets the email value for this Mittente.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this Mittente.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Mittente)) return false;
        Mittente other = (Mittente) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.personaFisica==null && other.getPersonaFisica()==null) || 
             (this.personaFisica!=null &&
              this.personaFisica.equals(other.getPersonaFisica()))) &&
            ((this.personaGiuridica==null && other.getPersonaGiuridica()==null) || 
             (this.personaGiuridica!=null &&
              this.personaGiuridica.equals(other.getPersonaGiuridica()))) &&
            ((this.indirizzo==null && other.getIndirizzo()==null) || 
             (this.indirizzo!=null &&
              this.indirizzo.equals(other.getIndirizzo()))) &&
            ((this.comune==null && other.getComune()==null) || 
             (this.comune!=null &&
              this.comune.equals(other.getComune()))) &&
            ((this.nazione==null && other.getNazione()==null) || 
             (this.nazione!=null &&
              this.nazione.equals(other.getNazione()))) &&
            ((this.pecEmail==null && other.getPecEmail()==null) || 
             (this.pecEmail!=null &&
              this.pecEmail.equals(other.getPecEmail()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail())));
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
        if (getPersonaFisica() != null) {
            _hashCode += getPersonaFisica().hashCode();
        }
        if (getPersonaGiuridica() != null) {
            _hashCode += getPersonaGiuridica().hashCode();
        }
        if (getIndirizzo() != null) {
            _hashCode += getIndirizzo().hashCode();
        }
        if (getComune() != null) {
            _hashCode += getComune().hashCode();
        }
        if (getNazione() != null) {
            _hashCode += getNazione().hashCode();
        }
        if (getPecEmail() != null) {
            _hashCode += getPecEmail().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Mittente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Mittente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personaFisica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personaFisica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaFisica"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("personaGiuridica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "personaGiuridica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "PersonaGiuridica"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "indirizzo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("comune");
        elemField.setXmlName(new javax.xml.namespace.QName("", "comune"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("pecEmail");
        elemField.setXmlName(new javax.xml.namespace.QName("", "pecEmail"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
