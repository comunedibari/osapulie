/**
 * DefuntoPosizioneVisuraServiziCimiteriali.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraservizicimiteriali;

public class DefuntoPosizioneVisuraServiziCimiteriali  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String nomeDefunto;

    private java.util.Date dataNascita;

    private java.util.Date dataMorte;

    private java.math.BigInteger idPosizione;

    public DefuntoPosizioneVisuraServiziCimiteriali() {
    }

    public DefuntoPosizioneVisuraServiziCimiteriali(
           java.math.BigInteger id,
           java.lang.String nomeDefunto,
           java.util.Date dataNascita,
           java.util.Date dataMorte,
           java.math.BigInteger idPosizione) {
           this.id = id;
           this.nomeDefunto = nomeDefunto;
           this.dataNascita = dataNascita;
           this.dataMorte = dataMorte;
           this.idPosizione = idPosizione;
    }


    /**
     * Gets the id value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the nomeDefunto value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @return nomeDefunto
     */
    public java.lang.String getNomeDefunto() {
        return nomeDefunto;
    }


    /**
     * Sets the nomeDefunto value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @param nomeDefunto
     */
    public void setNomeDefunto(java.lang.String nomeDefunto) {
        this.nomeDefunto = nomeDefunto;
    }


    /**
     * Gets the dataNascita value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @return dataNascita
     */
    public java.util.Date getDataNascita() {
        return dataNascita;
    }


    /**
     * Sets the dataNascita value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @param dataNascita
     */
    public void setDataNascita(java.util.Date dataNascita) {
        this.dataNascita = dataNascita;
    }


    /**
     * Gets the dataMorte value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @return dataMorte
     */
    public java.util.Date getDataMorte() {
        return dataMorte;
    }


    /**
     * Sets the dataMorte value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @param dataMorte
     */
    public void setDataMorte(java.util.Date dataMorte) {
        this.dataMorte = dataMorte;
    }


    /**
     * Gets the idPosizione value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @return idPosizione
     */
    public java.math.BigInteger getIdPosizione() {
        return idPosizione;
    }


    /**
     * Sets the idPosizione value for this DefuntoPosizioneVisuraServiziCimiteriali.
     * 
     * @param idPosizione
     */
    public void setIdPosizione(java.math.BigInteger idPosizione) {
        this.idPosizione = idPosizione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DefuntoPosizioneVisuraServiziCimiteriali)) return false;
        DefuntoPosizioneVisuraServiziCimiteriali other = (DefuntoPosizioneVisuraServiziCimiteriali) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.nomeDefunto==null && other.getNomeDefunto()==null) || 
             (this.nomeDefunto!=null &&
              this.nomeDefunto.equals(other.getNomeDefunto()))) &&
            ((this.dataNascita==null && other.getDataNascita()==null) || 
             (this.dataNascita!=null &&
              this.dataNascita.equals(other.getDataNascita()))) &&
            ((this.dataMorte==null && other.getDataMorte()==null) || 
             (this.dataMorte!=null &&
              this.dataMorte.equals(other.getDataMorte()))) &&
            ((this.idPosizione==null && other.getIdPosizione()==null) || 
             (this.idPosizione!=null &&
              this.idPosizione.equals(other.getIdPosizione())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getNomeDefunto() != null) {
            _hashCode += getNomeDefunto().hashCode();
        }
        if (getDataNascita() != null) {
            _hashCode += getDataNascita().hashCode();
        }
        if (getDataMorte() != null) {
            _hashCode += getDataMorte().hashCode();
        }
        if (getIdPosizione() != null) {
            _hashCode += getIdPosizione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DefuntoPosizioneVisuraServiziCimiteriali.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "defuntoPosizioneVisuraServiziCimiteriali"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeDefunto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "nomeDefunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPosizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idPosizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
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
