/**
 * PosizioneVisuraOsapPermanente.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraosappermanente;

public class PosizioneVisuraOsapPermanente  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String indirizzoUtenza;

    private java.lang.Double superficie;

    private java.lang.String zonaUtenza;

    private java.lang.String descrizioneTariffa;

    private java.lang.Double importoDaPagare;

    private java.math.BigInteger mesi;

    private java.math.BigInteger idVisura;

    public PosizioneVisuraOsapPermanente() {
    }

    public PosizioneVisuraOsapPermanente(
           java.math.BigInteger id,
           java.lang.String indirizzoUtenza,
           java.lang.Double superficie,
           java.lang.String zonaUtenza,
           java.lang.String descrizioneTariffa,
           java.lang.Double importoDaPagare,
           java.math.BigInteger mesi,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.indirizzoUtenza = indirizzoUtenza;
           this.superficie = superficie;
           this.zonaUtenza = zonaUtenza;
           this.descrizioneTariffa = descrizioneTariffa;
           this.importoDaPagare = importoDaPagare;
           this.mesi = mesi;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this PosizioneVisuraOsapPermanente.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PosizioneVisuraOsapPermanente.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the indirizzoUtenza value for this PosizioneVisuraOsapPermanente.
     * 
     * @return indirizzoUtenza
     */
    public java.lang.String getIndirizzoUtenza() {
        return indirizzoUtenza;
    }


    /**
     * Sets the indirizzoUtenza value for this PosizioneVisuraOsapPermanente.
     * 
     * @param indirizzoUtenza
     */
    public void setIndirizzoUtenza(java.lang.String indirizzoUtenza) {
        this.indirizzoUtenza = indirizzoUtenza;
    }


    /**
     * Gets the superficie value for this PosizioneVisuraOsapPermanente.
     * 
     * @return superficie
     */
    public java.lang.Double getSuperficie() {
        return superficie;
    }


    /**
     * Sets the superficie value for this PosizioneVisuraOsapPermanente.
     * 
     * @param superficie
     */
    public void setSuperficie(java.lang.Double superficie) {
        this.superficie = superficie;
    }


    /**
     * Gets the zonaUtenza value for this PosizioneVisuraOsapPermanente.
     * 
     * @return zonaUtenza
     */
    public java.lang.String getZonaUtenza() {
        return zonaUtenza;
    }


    /**
     * Sets the zonaUtenza value for this PosizioneVisuraOsapPermanente.
     * 
     * @param zonaUtenza
     */
    public void setZonaUtenza(java.lang.String zonaUtenza) {
        this.zonaUtenza = zonaUtenza;
    }


    /**
     * Gets the descrizioneTariffa value for this PosizioneVisuraOsapPermanente.
     * 
     * @return descrizioneTariffa
     */
    public java.lang.String getDescrizioneTariffa() {
        return descrizioneTariffa;
    }


    /**
     * Sets the descrizioneTariffa value for this PosizioneVisuraOsapPermanente.
     * 
     * @param descrizioneTariffa
     */
    public void setDescrizioneTariffa(java.lang.String descrizioneTariffa) {
        this.descrizioneTariffa = descrizioneTariffa;
    }


    /**
     * Gets the importoDaPagare value for this PosizioneVisuraOsapPermanente.
     * 
     * @return importoDaPagare
     */
    public java.lang.Double getImportoDaPagare() {
        return importoDaPagare;
    }


    /**
     * Sets the importoDaPagare value for this PosizioneVisuraOsapPermanente.
     * 
     * @param importoDaPagare
     */
    public void setImportoDaPagare(java.lang.Double importoDaPagare) {
        this.importoDaPagare = importoDaPagare;
    }


    /**
     * Gets the mesi value for this PosizioneVisuraOsapPermanente.
     * 
     * @return mesi
     */
    public java.math.BigInteger getMesi() {
        return mesi;
    }


    /**
     * Sets the mesi value for this PosizioneVisuraOsapPermanente.
     * 
     * @param mesi
     */
    public void setMesi(java.math.BigInteger mesi) {
        this.mesi = mesi;
    }


    /**
     * Gets the idVisura value for this PosizioneVisuraOsapPermanente.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this PosizioneVisuraOsapPermanente.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizioneVisuraOsapPermanente)) return false;
        PosizioneVisuraOsapPermanente other = (PosizioneVisuraOsapPermanente) obj;
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
            ((this.indirizzoUtenza==null && other.getIndirizzoUtenza()==null) || 
             (this.indirizzoUtenza!=null &&
              this.indirizzoUtenza.equals(other.getIndirizzoUtenza()))) &&
            ((this.superficie==null && other.getSuperficie()==null) || 
             (this.superficie!=null &&
              this.superficie.equals(other.getSuperficie()))) &&
            ((this.zonaUtenza==null && other.getZonaUtenza()==null) || 
             (this.zonaUtenza!=null &&
              this.zonaUtenza.equals(other.getZonaUtenza()))) &&
            ((this.descrizioneTariffa==null && other.getDescrizioneTariffa()==null) || 
             (this.descrizioneTariffa!=null &&
              this.descrizioneTariffa.equals(other.getDescrizioneTariffa()))) &&
            ((this.importoDaPagare==null && other.getImportoDaPagare()==null) || 
             (this.importoDaPagare!=null &&
              this.importoDaPagare.equals(other.getImportoDaPagare()))) &&
            ((this.mesi==null && other.getMesi()==null) || 
             (this.mesi!=null &&
              this.mesi.equals(other.getMesi()))) &&
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
        if (getIndirizzoUtenza() != null) {
            _hashCode += getIndirizzoUtenza().hashCode();
        }
        if (getSuperficie() != null) {
            _hashCode += getSuperficie().hashCode();
        }
        if (getZonaUtenza() != null) {
            _hashCode += getZonaUtenza().hashCode();
        }
        if (getDescrizioneTariffa() != null) {
            _hashCode += getDescrizioneTariffa().hashCode();
        }
        if (getImportoDaPagare() != null) {
            _hashCode += getImportoDaPagare().hashCode();
        }
        if (getMesi() != null) {
            _hashCode += getMesi().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizioneVisuraOsapPermanente.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraOsapPermanente"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzoUtenza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "indirizzoUtenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("superficie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "superficie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zonaUtenza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "zonaUtenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneTariffa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneTariffa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoDaPagare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoDaPagare"));
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
