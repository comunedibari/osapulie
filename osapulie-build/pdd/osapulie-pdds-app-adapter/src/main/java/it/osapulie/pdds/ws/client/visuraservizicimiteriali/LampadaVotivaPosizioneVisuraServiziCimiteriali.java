/**
 * LampadaVotivaPosizioneVisuraServiziCimiteriali.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraservizicimiteriali;

public class LampadaVotivaPosizioneVisuraServiziCimiteriali  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.math.BigInteger mesi;

    private java.lang.String tariffa;

    private java.lang.Double importoLampada;

    private java.math.BigInteger idPosizione;

    public LampadaVotivaPosizioneVisuraServiziCimiteriali() {
    }

    public LampadaVotivaPosizioneVisuraServiziCimiteriali(
           java.math.BigInteger id,
           java.math.BigInteger mesi,
           java.lang.String tariffa,
           java.lang.Double importoLampada,
           java.math.BigInteger idPosizione) {
           this.id = id;
           this.mesi = mesi;
           this.tariffa = tariffa;
           this.importoLampada = importoLampada;
           this.idPosizione = idPosizione;
    }


    /**
     * Gets the id value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the mesi value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @return mesi
     */
    public java.math.BigInteger getMesi() {
        return mesi;
    }


    /**
     * Sets the mesi value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @param mesi
     */
    public void setMesi(java.math.BigInteger mesi) {
        this.mesi = mesi;
    }


    /**
     * Gets the tariffa value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @return tariffa
     */
    public java.lang.String getTariffa() {
        return tariffa;
    }


    /**
     * Sets the tariffa value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @param tariffa
     */
    public void setTariffa(java.lang.String tariffa) {
        this.tariffa = tariffa;
    }


    /**
     * Gets the importoLampada value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @return importoLampada
     */
    public java.lang.Double getImportoLampada() {
        return importoLampada;
    }


    /**
     * Sets the importoLampada value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @param importoLampada
     */
    public void setImportoLampada(java.lang.Double importoLampada) {
        this.importoLampada = importoLampada;
    }


    /**
     * Gets the idPosizione value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @return idPosizione
     */
    public java.math.BigInteger getIdPosizione() {
        return idPosizione;
    }


    /**
     * Sets the idPosizione value for this LampadaVotivaPosizioneVisuraServiziCimiteriali.
     * 
     * @param idPosizione
     */
    public void setIdPosizione(java.math.BigInteger idPosizione) {
        this.idPosizione = idPosizione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LampadaVotivaPosizioneVisuraServiziCimiteriali)) return false;
        LampadaVotivaPosizioneVisuraServiziCimiteriali other = (LampadaVotivaPosizioneVisuraServiziCimiteriali) obj;
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
            ((this.mesi==null && other.getMesi()==null) || 
             (this.mesi!=null &&
              this.mesi.equals(other.getMesi()))) &&
            ((this.tariffa==null && other.getTariffa()==null) || 
             (this.tariffa!=null &&
              this.tariffa.equals(other.getTariffa()))) &&
            ((this.importoLampada==null && other.getImportoLampada()==null) || 
             (this.importoLampada!=null &&
              this.importoLampada.equals(other.getImportoLampada()))) &&
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
        if (getMesi() != null) {
            _hashCode += getMesi().hashCode();
        }
        if (getTariffa() != null) {
            _hashCode += getTariffa().hashCode();
        }
        if (getImportoLampada() != null) {
            _hashCode += getImportoLampada().hashCode();
        }
        if (getIdPosizione() != null) {
            _hashCode += getIdPosizione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LampadaVotivaPosizioneVisuraServiziCimiteriali.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "lampadaVotivaPosizioneVisuraServiziCimiteriali"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mesi");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "mesi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tariffa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "tariffa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoLampada");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoLampada"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
