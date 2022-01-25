/**
 * PosizioneVisuraServiziCimiteriali.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraservizicimiteriali;

public class PosizioneVisuraServiziCimiteriali  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String nomecimitero;

    private java.lang.String posizione;

    private java.math.BigInteger idVisura;

    public PosizioneVisuraServiziCimiteriali() {
    }

    public PosizioneVisuraServiziCimiteriali(
           java.math.BigInteger id,
           java.lang.String nomecimitero,
           java.lang.String posizione,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.nomecimitero = nomecimitero;
           this.posizione = posizione;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the nomecimitero value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @return nomecimitero
     */
    public java.lang.String getNomecimitero() {
        return nomecimitero;
    }


    /**
     * Sets the nomecimitero value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @param nomecimitero
     */
    public void setNomecimitero(java.lang.String nomecimitero) {
        this.nomecimitero = nomecimitero;
    }


    /**
     * Gets the posizione value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @return posizione
     */
    public java.lang.String getPosizione() {
        return posizione;
    }


    /**
     * Sets the posizione value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @param posizione
     */
    public void setPosizione(java.lang.String posizione) {
        this.posizione = posizione;
    }


    /**
     * Gets the idVisura value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this PosizioneVisuraServiziCimiteriali.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizioneVisuraServiziCimiteriali)) return false;
        PosizioneVisuraServiziCimiteriali other = (PosizioneVisuraServiziCimiteriali) obj;
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
            ((this.nomecimitero==null && other.getNomecimitero()==null) || 
             (this.nomecimitero!=null &&
              this.nomecimitero.equals(other.getNomecimitero()))) &&
            ((this.posizione==null && other.getPosizione()==null) || 
             (this.posizione!=null &&
              this.posizione.equals(other.getPosizione()))) &&
            ((this.idVisura==null && other.getIdVisura()==null) || 
             (this.idVisura!=null &&
              this.idVisura.equals(other.getIdVisura())));
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
        if (getNomecimitero() != null) {
            _hashCode += getNomecimitero().hashCode();
        }
        if (getPosizione() != null) {
            _hashCode += getPosizione().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizioneVisuraServiziCimiteriali.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraServiziCimiteriali"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomecimitero");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "nomecimitero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVisura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idVisura"));
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
