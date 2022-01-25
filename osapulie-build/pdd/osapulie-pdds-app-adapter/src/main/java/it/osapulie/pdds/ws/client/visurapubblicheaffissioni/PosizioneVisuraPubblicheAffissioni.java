/**
 * PosizioneVisuraPubblicheAffissioni.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visurapubblicheaffissioni;

public class PosizioneVisuraPubblicheAffissioni  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.math.BigInteger numeroFogli;

    private java.math.BigInteger numeroManifesti;

    private java.math.BigInteger giorniAffissione;

    private java.lang.Double tariffa;

    private java.lang.String dimensioneManifesti;

    private java.math.BigInteger idVisura;

    public PosizioneVisuraPubblicheAffissioni() {
    }

    public PosizioneVisuraPubblicheAffissioni(
           java.math.BigInteger id,
           java.math.BigInteger numeroFogli,
           java.math.BigInteger numeroManifesti,
           java.math.BigInteger giorniAffissione,
           java.lang.Double tariffa,
           java.lang.String dimensioneManifesti,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.numeroFogli = numeroFogli;
           this.numeroManifesti = numeroManifesti;
           this.giorniAffissione = giorniAffissione;
           this.tariffa = tariffa;
           this.dimensioneManifesti = dimensioneManifesti;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the numeroFogli value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return numeroFogli
     */
    public java.math.BigInteger getNumeroFogli() {
        return numeroFogli;
    }


    /**
     * Sets the numeroFogli value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param numeroFogli
     */
    public void setNumeroFogli(java.math.BigInteger numeroFogli) {
        this.numeroFogli = numeroFogli;
    }


    /**
     * Gets the numeroManifesti value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return numeroManifesti
     */
    public java.math.BigInteger getNumeroManifesti() {
        return numeroManifesti;
    }


    /**
     * Sets the numeroManifesti value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param numeroManifesti
     */
    public void setNumeroManifesti(java.math.BigInteger numeroManifesti) {
        this.numeroManifesti = numeroManifesti;
    }


    /**
     * Gets the giorniAffissione value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return giorniAffissione
     */
    public java.math.BigInteger getGiorniAffissione() {
        return giorniAffissione;
    }


    /**
     * Sets the giorniAffissione value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param giorniAffissione
     */
    public void setGiorniAffissione(java.math.BigInteger giorniAffissione) {
        this.giorniAffissione = giorniAffissione;
    }


    /**
     * Gets the tariffa value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return tariffa
     */
    public java.lang.Double getTariffa() {
        return tariffa;
    }


    /**
     * Sets the tariffa value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param tariffa
     */
    public void setTariffa(java.lang.Double tariffa) {
        this.tariffa = tariffa;
    }


    /**
     * Gets the dimensioneManifesti value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return dimensioneManifesti
     */
    public java.lang.String getDimensioneManifesti() {
        return dimensioneManifesti;
    }


    /**
     * Sets the dimensioneManifesti value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param dimensioneManifesti
     */
    public void setDimensioneManifesti(java.lang.String dimensioneManifesti) {
        this.dimensioneManifesti = dimensioneManifesti;
    }


    /**
     * Gets the idVisura value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this PosizioneVisuraPubblicheAffissioni.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizioneVisuraPubblicheAffissioni)) return false;
        PosizioneVisuraPubblicheAffissioni other = (PosizioneVisuraPubblicheAffissioni) obj;
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
            ((this.numeroFogli==null && other.getNumeroFogli()==null) || 
             (this.numeroFogli!=null &&
              this.numeroFogli.equals(other.getNumeroFogli()))) &&
            ((this.numeroManifesti==null && other.getNumeroManifesti()==null) || 
             (this.numeroManifesti!=null &&
              this.numeroManifesti.equals(other.getNumeroManifesti()))) &&
            ((this.giorniAffissione==null && other.getGiorniAffissione()==null) || 
             (this.giorniAffissione!=null &&
              this.giorniAffissione.equals(other.getGiorniAffissione()))) &&
            ((this.tariffa==null && other.getTariffa()==null) || 
             (this.tariffa!=null &&
              this.tariffa.equals(other.getTariffa()))) &&
            ((this.dimensioneManifesti==null && other.getDimensioneManifesti()==null) || 
             (this.dimensioneManifesti!=null &&
              this.dimensioneManifesti.equals(other.getDimensioneManifesti()))) &&
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
        if (getNumeroFogli() != null) {
            _hashCode += getNumeroFogli().hashCode();
        }
        if (getNumeroManifesti() != null) {
            _hashCode += getNumeroManifesti().hashCode();
        }
        if (getGiorniAffissione() != null) {
            _hashCode += getGiorniAffissione().hashCode();
        }
        if (getTariffa() != null) {
            _hashCode += getTariffa().hashCode();
        }
        if (getDimensioneManifesti() != null) {
            _hashCode += getDimensioneManifesti().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizioneVisuraPubblicheAffissioni.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraPubblicheAffissioni"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroFogli");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroFogli"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroManifesti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroManifesti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("giorniAffissione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "giorniAffissione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tariffa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "tariffa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dimensioneManifesti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dimensioneManifesti"));
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
