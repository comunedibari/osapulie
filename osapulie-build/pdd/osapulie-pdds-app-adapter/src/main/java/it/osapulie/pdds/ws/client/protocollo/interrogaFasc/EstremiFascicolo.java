/**
 * EstremiFascicolo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class EstremiFascicolo  implements java.io.Serializable {
    private java.lang.String codiceUfficio;

    private java.lang.String descrizioneUfficio;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica;

    private java.lang.String anno;

    private java.lang.String numero;

    private java.lang.String subnumero;

    public EstremiFascicolo() {
    }

    public EstremiFascicolo(
           java.lang.String codiceUfficio,
           java.lang.String descrizioneUfficio,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica,
           java.lang.String anno,
           java.lang.String numero,
           java.lang.String subnumero) {
           this.codiceUfficio = codiceUfficio;
           this.descrizioneUfficio = descrizioneUfficio;
           this.classifica = classifica;
           this.anno = anno;
           this.numero = numero;
           this.subnumero = subnumero;
    }


    /**
     * Gets the codiceUfficio value for this EstremiFascicolo.
     * 
     * @return codiceUfficio
     */
    public java.lang.String getCodiceUfficio() {
        return codiceUfficio;
    }


    /**
     * Sets the codiceUfficio value for this EstremiFascicolo.
     * 
     * @param codiceUfficio
     */
    public void setCodiceUfficio(java.lang.String codiceUfficio) {
        this.codiceUfficio = codiceUfficio;
    }


    /**
     * Gets the descrizioneUfficio value for this EstremiFascicolo.
     * 
     * @return descrizioneUfficio
     */
    public java.lang.String getDescrizioneUfficio() {
        return descrizioneUfficio;
    }


    /**
     * Sets the descrizioneUfficio value for this EstremiFascicolo.
     * 
     * @param descrizioneUfficio
     */
    public void setDescrizioneUfficio(java.lang.String descrizioneUfficio) {
        this.descrizioneUfficio = descrizioneUfficio;
    }


    /**
     * Gets the classifica value for this EstremiFascicolo.
     * 
     * @return classifica
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica getClassifica() {
        return classifica;
    }


    /**
     * Sets the classifica value for this EstremiFascicolo.
     * 
     * @param classifica
     */
    public void setClassifica(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica) {
        this.classifica = classifica;
    }


    /**
     * Gets the anno value for this EstremiFascicolo.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this EstremiFascicolo.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }


    /**
     * Gets the numero value for this EstremiFascicolo.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this EstremiFascicolo.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }


    /**
     * Gets the subnumero value for this EstremiFascicolo.
     * 
     * @return subnumero
     */
    public java.lang.String getSubnumero() {
        return subnumero;
    }


    /**
     * Sets the subnumero value for this EstremiFascicolo.
     * 
     * @param subnumero
     */
    public void setSubnumero(java.lang.String subnumero) {
        this.subnumero = subnumero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstremiFascicolo)) return false;
        EstremiFascicolo other = (EstremiFascicolo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceUfficio==null && other.getCodiceUfficio()==null) || 
             (this.codiceUfficio!=null &&
              this.codiceUfficio.equals(other.getCodiceUfficio()))) &&
            ((this.descrizioneUfficio==null && other.getDescrizioneUfficio()==null) || 
             (this.descrizioneUfficio!=null &&
              this.descrizioneUfficio.equals(other.getDescrizioneUfficio()))) &&
            ((this.classifica==null && other.getClassifica()==null) || 
             (this.classifica!=null &&
              this.classifica.equals(other.getClassifica()))) &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero()))) &&
            ((this.subnumero==null && other.getSubnumero()==null) || 
             (this.subnumero!=null &&
              this.subnumero.equals(other.getSubnumero())));
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
        if (getCodiceUfficio() != null) {
            _hashCode += getCodiceUfficio().hashCode();
        }
        if (getDescrizioneUfficio() != null) {
            _hashCode += getDescrizioneUfficio().hashCode();
        }
        if (getClassifica() != null) {
            _hashCode += getClassifica().hashCode();
        }
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        if (getSubnumero() != null) {
            _hashCode += getSubnumero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstremiFascicolo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicolo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceUfficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceUfficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneUfficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneUfficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "classifica"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("subnumero");
        elemField.setXmlName(new javax.xml.namespace.QName("", "subnumero"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
