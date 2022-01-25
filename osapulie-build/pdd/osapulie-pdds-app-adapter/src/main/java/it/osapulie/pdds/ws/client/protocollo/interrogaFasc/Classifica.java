/**
 * Classifica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class Classifica  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaLivelli livelli;

    private java.lang.String codiceClassifica;

    private java.lang.String descrizioneClassifica;

    public Classifica() {
    }

    public Classifica(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaLivelli livelli,
           java.lang.String codiceClassifica,
           java.lang.String descrizioneClassifica) {
           this.livelli = livelli;
           this.codiceClassifica = codiceClassifica;
           this.descrizioneClassifica = descrizioneClassifica;
    }


    /**
     * Gets the livelli value for this Classifica.
     * 
     * @return livelli
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaLivelli getLivelli() {
        return livelli;
    }


    /**
     * Sets the livelli value for this Classifica.
     * 
     * @param livelli
     */
    public void setLivelli(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaLivelli livelli) {
        this.livelli = livelli;
    }


    /**
     * Gets the codiceClassifica value for this Classifica.
     * 
     * @return codiceClassifica
     */
    public java.lang.String getCodiceClassifica() {
        return codiceClassifica;
    }


    /**
     * Sets the codiceClassifica value for this Classifica.
     * 
     * @param codiceClassifica
     */
    public void setCodiceClassifica(java.lang.String codiceClassifica) {
        this.codiceClassifica = codiceClassifica;
    }


    /**
     * Gets the descrizioneClassifica value for this Classifica.
     * 
     * @return descrizioneClassifica
     */
    public java.lang.String getDescrizioneClassifica() {
        return descrizioneClassifica;
    }


    /**
     * Sets the descrizioneClassifica value for this Classifica.
     * 
     * @param descrizioneClassifica
     */
    public void setDescrizioneClassifica(java.lang.String descrizioneClassifica) {
        this.descrizioneClassifica = descrizioneClassifica;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Classifica)) return false;
        Classifica other = (Classifica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.livelli==null && other.getLivelli()==null) || 
             (this.livelli!=null &&
              this.livelli.equals(other.getLivelli()))) &&
            ((this.codiceClassifica==null && other.getCodiceClassifica()==null) || 
             (this.codiceClassifica!=null &&
              this.codiceClassifica.equals(other.getCodiceClassifica()))) &&
            ((this.descrizioneClassifica==null && other.getDescrizioneClassifica()==null) || 
             (this.descrizioneClassifica!=null &&
              this.descrizioneClassifica.equals(other.getDescrizioneClassifica())));
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
        if (getLivelli() != null) {
            _hashCode += getLivelli().hashCode();
        }
        if (getCodiceClassifica() != null) {
            _hashCode += getCodiceClassifica().hashCode();
        }
        if (getDescrizioneClassifica() != null) {
            _hashCode += getDescrizioneClassifica().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Classifica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "classifica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("livelli");
        elemField.setXmlName(new javax.xml.namespace.QName("", "livelli"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaLivelli"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceClassifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceClassifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneClassifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneClassifica"));
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
