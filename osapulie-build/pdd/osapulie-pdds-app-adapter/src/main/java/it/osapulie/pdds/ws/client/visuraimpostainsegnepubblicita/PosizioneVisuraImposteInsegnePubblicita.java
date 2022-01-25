/**
 * PosizioneVisuraImposteInsegnePubblicita.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita;

public class PosizioneVisuraImposteInsegnePubblicita  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String indirizzoPubblicita;

    private java.lang.String descrizionePubblicita;

    private java.lang.Double mq;

    private java.math.BigInteger mesi;

    private java.lang.Double importoPubblicita;

    private java.math.BigInteger idVisura;

    public PosizioneVisuraImposteInsegnePubblicita() {
    }

    public PosizioneVisuraImposteInsegnePubblicita(
           java.math.BigInteger id,
           java.lang.String indirizzoPubblicita,
           java.lang.String descrizionePubblicita,
           java.lang.Double mq,
           java.math.BigInteger mesi,
           java.lang.Double importoPubblicita,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.indirizzoPubblicita = indirizzoPubblicita;
           this.descrizionePubblicita = descrizionePubblicita;
           this.mq = mq;
           this.mesi = mesi;
           this.importoPubblicita = importoPubblicita;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the indirizzoPubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return indirizzoPubblicita
     */
    public java.lang.String getIndirizzoPubblicita() {
        return indirizzoPubblicita;
    }


    /**
     * Sets the indirizzoPubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param indirizzoPubblicita
     */
    public void setIndirizzoPubblicita(java.lang.String indirizzoPubblicita) {
        this.indirizzoPubblicita = indirizzoPubblicita;
    }


    /**
     * Gets the descrizionePubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return descrizionePubblicita
     */
    public java.lang.String getDescrizionePubblicita() {
        return descrizionePubblicita;
    }


    /**
     * Sets the descrizionePubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param descrizionePubblicita
     */
    public void setDescrizionePubblicita(java.lang.String descrizionePubblicita) {
        this.descrizionePubblicita = descrizionePubblicita;
    }


    /**
     * Gets the mq value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return mq
     */
    public java.lang.Double getMq() {
        return mq;
    }


    /**
     * Sets the mq value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param mq
     */
    public void setMq(java.lang.Double mq) {
        this.mq = mq;
    }


    /**
     * Gets the mesi value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return mesi
     */
    public java.math.BigInteger getMesi() {
        return mesi;
    }


    /**
     * Sets the mesi value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param mesi
     */
    public void setMesi(java.math.BigInteger mesi) {
        this.mesi = mesi;
    }


    /**
     * Gets the importoPubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return importoPubblicita
     */
    public java.lang.Double getImportoPubblicita() {
        return importoPubblicita;
    }


    /**
     * Sets the importoPubblicita value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param importoPubblicita
     */
    public void setImportoPubblicita(java.lang.Double importoPubblicita) {
        this.importoPubblicita = importoPubblicita;
    }


    /**
     * Gets the idVisura value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this PosizioneVisuraImposteInsegnePubblicita.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizioneVisuraImposteInsegnePubblicita)) return false;
        PosizioneVisuraImposteInsegnePubblicita other = (PosizioneVisuraImposteInsegnePubblicita) obj;
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
            ((this.indirizzoPubblicita==null && other.getIndirizzoPubblicita()==null) || 
             (this.indirizzoPubblicita!=null &&
              this.indirizzoPubblicita.equals(other.getIndirizzoPubblicita()))) &&
            ((this.descrizionePubblicita==null && other.getDescrizionePubblicita()==null) || 
             (this.descrizionePubblicita!=null &&
              this.descrizionePubblicita.equals(other.getDescrizionePubblicita()))) &&
            ((this.mq==null && other.getMq()==null) || 
             (this.mq!=null &&
              this.mq.equals(other.getMq()))) &&
            ((this.mesi==null && other.getMesi()==null) || 
             (this.mesi!=null &&
              this.mesi.equals(other.getMesi()))) &&
            ((this.importoPubblicita==null && other.getImportoPubblicita()==null) || 
             (this.importoPubblicita!=null &&
              this.importoPubblicita.equals(other.getImportoPubblicita()))) &&
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
        if (getIndirizzoPubblicita() != null) {
            _hashCode += getIndirizzoPubblicita().hashCode();
        }
        if (getDescrizionePubblicita() != null) {
            _hashCode += getDescrizionePubblicita().hashCode();
        }
        if (getMq() != null) {
            _hashCode += getMq().hashCode();
        }
        if (getMesi() != null) {
            _hashCode += getMesi().hashCode();
        }
        if (getImportoPubblicita() != null) {
            _hashCode += getImportoPubblicita().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizioneVisuraImposteInsegnePubblicita.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraImposteInsegnePubblicita"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzoPubblicita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "indirizzoPubblicita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizionePubblicita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizionePubblicita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mq");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "mq"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mesi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "mesi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoPubblicita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoPubblicita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
