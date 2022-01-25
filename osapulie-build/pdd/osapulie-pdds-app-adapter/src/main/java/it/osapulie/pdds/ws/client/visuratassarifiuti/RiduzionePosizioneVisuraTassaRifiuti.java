/**
 * RiduzionePosizioneVisuraTassaRifiuti.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuratassarifiuti;

public class RiduzionePosizioneVisuraTassaRifiuti  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String codiceArticolo;

    private java.lang.String descrizioneArticolo;

    private java.lang.String codiceValore;

    private java.lang.String descrizioneValore;

    private java.lang.String note;

    private java.math.BigInteger idPosizione;

    public RiduzionePosizioneVisuraTassaRifiuti() {
    }

    public RiduzionePosizioneVisuraTassaRifiuti(
           java.math.BigInteger id,
           java.lang.String codiceArticolo,
           java.lang.String descrizioneArticolo,
           java.lang.String codiceValore,
           java.lang.String descrizioneValore,
           java.lang.String note,
           java.math.BigInteger idPosizione) {
           this.id = id;
           this.codiceArticolo = codiceArticolo;
           this.descrizioneArticolo = descrizioneArticolo;
           this.codiceValore = codiceValore;
           this.descrizioneValore = descrizioneValore;
           this.note = note;
           this.idPosizione = idPosizione;
    }


    /**
     * Gets the id value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the codiceArticolo value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return codiceArticolo
     */
    public java.lang.String getCodiceArticolo() {
        return codiceArticolo;
    }


    /**
     * Sets the codiceArticolo value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param codiceArticolo
     */
    public void setCodiceArticolo(java.lang.String codiceArticolo) {
        this.codiceArticolo = codiceArticolo;
    }


    /**
     * Gets the descrizioneArticolo value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return descrizioneArticolo
     */
    public java.lang.String getDescrizioneArticolo() {
        return descrizioneArticolo;
    }


    /**
     * Sets the descrizioneArticolo value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param descrizioneArticolo
     */
    public void setDescrizioneArticolo(java.lang.String descrizioneArticolo) {
        this.descrizioneArticolo = descrizioneArticolo;
    }


    /**
     * Gets the codiceValore value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return codiceValore
     */
    public java.lang.String getCodiceValore() {
        return codiceValore;
    }


    /**
     * Sets the codiceValore value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param codiceValore
     */
    public void setCodiceValore(java.lang.String codiceValore) {
        this.codiceValore = codiceValore;
    }


    /**
     * Gets the descrizioneValore value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return descrizioneValore
     */
    public java.lang.String getDescrizioneValore() {
        return descrizioneValore;
    }


    /**
     * Sets the descrizioneValore value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param descrizioneValore
     */
    public void setDescrizioneValore(java.lang.String descrizioneValore) {
        this.descrizioneValore = descrizioneValore;
    }


    /**
     * Gets the note value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return note
     */
    public java.lang.String getNote() {
        return note;
    }


    /**
     * Sets the note value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param note
     */
    public void setNote(java.lang.String note) {
        this.note = note;
    }


    /**
     * Gets the idPosizione value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @return idPosizione
     */
    public java.math.BigInteger getIdPosizione() {
        return idPosizione;
    }


    /**
     * Sets the idPosizione value for this RiduzionePosizioneVisuraTassaRifiuti.
     * 
     * @param idPosizione
     */
    public void setIdPosizione(java.math.BigInteger idPosizione) {
        this.idPosizione = idPosizione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RiduzionePosizioneVisuraTassaRifiuti)) return false;
        RiduzionePosizioneVisuraTassaRifiuti other = (RiduzionePosizioneVisuraTassaRifiuti) obj;
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
            ((this.codiceArticolo==null && other.getCodiceArticolo()==null) || 
             (this.codiceArticolo!=null &&
              this.codiceArticolo.equals(other.getCodiceArticolo()))) &&
            ((this.descrizioneArticolo==null && other.getDescrizioneArticolo()==null) || 
             (this.descrizioneArticolo!=null &&
              this.descrizioneArticolo.equals(other.getDescrizioneArticolo()))) &&
            ((this.codiceValore==null && other.getCodiceValore()==null) || 
             (this.codiceValore!=null &&
              this.codiceValore.equals(other.getCodiceValore()))) &&
            ((this.descrizioneValore==null && other.getDescrizioneValore()==null) || 
             (this.descrizioneValore!=null &&
              this.descrizioneValore.equals(other.getDescrizioneValore()))) &&
            ((this.note==null && other.getNote()==null) || 
             (this.note!=null &&
              this.note.equals(other.getNote()))) &&
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
        if (getCodiceArticolo() != null) {
            _hashCode += getCodiceArticolo().hashCode();
        }
        if (getDescrizioneArticolo() != null) {
            _hashCode += getDescrizioneArticolo().hashCode();
        }
        if (getCodiceValore() != null) {
            _hashCode += getCodiceValore().hashCode();
        }
        if (getDescrizioneValore() != null) {
            _hashCode += getDescrizioneValore().hashCode();
        }
        if (getNote() != null) {
            _hashCode += getNote().hashCode();
        }
        if (getIdPosizione() != null) {
            _hashCode += getIdPosizione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RiduzionePosizioneVisuraTassaRifiuti.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "riduzionePosizioneVisuraTassaRifiuti"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceArticolo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceArticolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneArticolo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneArticolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceValore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceValore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneValore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneValore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("note");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
