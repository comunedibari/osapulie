/**
 * DatiEmail.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class DatiEmail  implements java.io.Serializable {
    private java.lang.String oggetto;

    private java.lang.String testo;

    private java.lang.String from;

    private java.lang.Integer tipoAllegati;

    public DatiEmail() {
    }

    public DatiEmail(
           java.lang.String oggetto,
           java.lang.String testo,
           java.lang.String from,
           java.lang.Integer tipoAllegati) {
           this.oggetto = oggetto;
           this.testo = testo;
           this.from = from;
           this.tipoAllegati = tipoAllegati;
    }


    /**
     * Gets the oggetto value for this DatiEmail.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this DatiEmail.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the testo value for this DatiEmail.
     * 
     * @return testo
     */
    public java.lang.String getTesto() {
        return testo;
    }


    /**
     * Sets the testo value for this DatiEmail.
     * 
     * @param testo
     */
    public void setTesto(java.lang.String testo) {
        this.testo = testo;
    }


    /**
     * Gets the from value for this DatiEmail.
     * 
     * @return from
     */
    public java.lang.String getFrom() {
        return from;
    }


    /**
     * Sets the from value for this DatiEmail.
     * 
     * @param from
     */
    public void setFrom(java.lang.String from) {
        this.from = from;
    }


    /**
     * Gets the tipoAllegati value for this DatiEmail.
     * 
     * @return tipoAllegati
     */
    public java.lang.Integer getTipoAllegati() {
        return tipoAllegati;
    }


    /**
     * Sets the tipoAllegati value for this DatiEmail.
     * 
     * @param tipoAllegati
     */
    public void setTipoAllegati(java.lang.Integer tipoAllegati) {
        this.tipoAllegati = tipoAllegati;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DatiEmail)) return false;
        DatiEmail other = (DatiEmail) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto()))) &&
            ((this.testo==null && other.getTesto()==null) || 
             (this.testo!=null &&
              this.testo.equals(other.getTesto()))) &&
            ((this.from==null && other.getFrom()==null) || 
             (this.from!=null &&
              this.from.equals(other.getFrom()))) &&
            ((this.tipoAllegati==null && other.getTipoAllegati()==null) || 
             (this.tipoAllegati!=null &&
              this.tipoAllegati.equals(other.getTipoAllegati())));
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
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        if (getTesto() != null) {
            _hashCode += getTesto().hashCode();
        }
        if (getFrom() != null) {
            _hashCode += getFrom().hashCode();
        }
        if (getTipoAllegati() != null) {
            _hashCode += getTipoAllegati().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DatiEmail.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "DatiEmail"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("testo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "testo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("from");
        elemField.setXmlName(new javax.xml.namespace.QName("", "from"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoAllegati");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoAllegati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
