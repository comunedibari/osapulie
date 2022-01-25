/**
 * EstremiFascicoloVirtuale.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class EstremiFascicoloVirtuale  implements java.io.Serializable {
    private java.lang.String codiceUfficio;

    private java.lang.String descrizioneUfficio;

    private java.lang.String codiceRegistro;

    private java.lang.String descrizioneRegistro;

    private java.lang.String anno;

    private java.lang.String numero;

    public EstremiFascicoloVirtuale() {
    }

    public EstremiFascicoloVirtuale(
           java.lang.String codiceUfficio,
           java.lang.String descrizioneUfficio,
           java.lang.String codiceRegistro,
           java.lang.String descrizioneRegistro,
           java.lang.String anno,
           java.lang.String numero) {
           this.codiceUfficio = codiceUfficio;
           this.descrizioneUfficio = descrizioneUfficio;
           this.codiceRegistro = codiceRegistro;
           this.descrizioneRegistro = descrizioneRegistro;
           this.anno = anno;
           this.numero = numero;
    }


    /**
     * Gets the codiceUfficio value for this EstremiFascicoloVirtuale.
     * 
     * @return codiceUfficio
     */
    public java.lang.String getCodiceUfficio() {
        return codiceUfficio;
    }


    /**
     * Sets the codiceUfficio value for this EstremiFascicoloVirtuale.
     * 
     * @param codiceUfficio
     */
    public void setCodiceUfficio(java.lang.String codiceUfficio) {
        this.codiceUfficio = codiceUfficio;
    }


    /**
     * Gets the descrizioneUfficio value for this EstremiFascicoloVirtuale.
     * 
     * @return descrizioneUfficio
     */
    public java.lang.String getDescrizioneUfficio() {
        return descrizioneUfficio;
    }


    /**
     * Sets the descrizioneUfficio value for this EstremiFascicoloVirtuale.
     * 
     * @param descrizioneUfficio
     */
    public void setDescrizioneUfficio(java.lang.String descrizioneUfficio) {
        this.descrizioneUfficio = descrizioneUfficio;
    }


    /**
     * Gets the codiceRegistro value for this EstremiFascicoloVirtuale.
     * 
     * @return codiceRegistro
     */
    public java.lang.String getCodiceRegistro() {
        return codiceRegistro;
    }


    /**
     * Sets the codiceRegistro value for this EstremiFascicoloVirtuale.
     * 
     * @param codiceRegistro
     */
    public void setCodiceRegistro(java.lang.String codiceRegistro) {
        this.codiceRegistro = codiceRegistro;
    }


    /**
     * Gets the descrizioneRegistro value for this EstremiFascicoloVirtuale.
     * 
     * @return descrizioneRegistro
     */
    public java.lang.String getDescrizioneRegistro() {
        return descrizioneRegistro;
    }


    /**
     * Sets the descrizioneRegistro value for this EstremiFascicoloVirtuale.
     * 
     * @param descrizioneRegistro
     */
    public void setDescrizioneRegistro(java.lang.String descrizioneRegistro) {
        this.descrizioneRegistro = descrizioneRegistro;
    }


    /**
     * Gets the anno value for this EstremiFascicoloVirtuale.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this EstremiFascicoloVirtuale.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }


    /**
     * Gets the numero value for this EstremiFascicoloVirtuale.
     * 
     * @return numero
     */
    public java.lang.String getNumero() {
        return numero;
    }


    /**
     * Sets the numero value for this EstremiFascicoloVirtuale.
     * 
     * @param numero
     */
    public void setNumero(java.lang.String numero) {
        this.numero = numero;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof EstremiFascicoloVirtuale)) return false;
        EstremiFascicoloVirtuale other = (EstremiFascicoloVirtuale) obj;
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
            ((this.codiceRegistro==null && other.getCodiceRegistro()==null) || 
             (this.codiceRegistro!=null &&
              this.codiceRegistro.equals(other.getCodiceRegistro()))) &&
            ((this.descrizioneRegistro==null && other.getDescrizioneRegistro()==null) || 
             (this.descrizioneRegistro!=null &&
              this.descrizioneRegistro.equals(other.getDescrizioneRegistro()))) &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.numero==null && other.getNumero()==null) || 
             (this.numero!=null &&
              this.numero.equals(other.getNumero())));
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
        if (getCodiceRegistro() != null) {
            _hashCode += getCodiceRegistro().hashCode();
        }
        if (getDescrizioneRegistro() != null) {
            _hashCode += getDescrizioneRegistro().hashCode();
        }
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getNumero() != null) {
            _hashCode += getNumero().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(EstremiFascicoloVirtuale.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicoloVirtuale"));
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
        elemField.setFieldName("codiceRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
