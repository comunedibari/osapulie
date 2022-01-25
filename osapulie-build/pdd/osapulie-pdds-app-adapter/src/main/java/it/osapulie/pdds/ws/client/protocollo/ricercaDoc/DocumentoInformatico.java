/**
 * DocumentoInformatico.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class DocumentoInformatico  implements java.io.Serializable {
    private java.lang.String codiceDocumentoInformatico;

    private java.lang.String flPrimario;

    private java.lang.String descrizioneDocumentoInformatico;

    public DocumentoInformatico() {
    }

    public DocumentoInformatico(
           java.lang.String codiceDocumentoInformatico,
           java.lang.String flPrimario,
           java.lang.String descrizioneDocumentoInformatico) {
           this.codiceDocumentoInformatico = codiceDocumentoInformatico;
           this.flPrimario = flPrimario;
           this.descrizioneDocumentoInformatico = descrizioneDocumentoInformatico;
    }


    /**
     * Gets the codiceDocumentoInformatico value for this DocumentoInformatico.
     * 
     * @return codiceDocumentoInformatico
     */
    public java.lang.String getCodiceDocumentoInformatico() {
        return codiceDocumentoInformatico;
    }


    /**
     * Sets the codiceDocumentoInformatico value for this DocumentoInformatico.
     * 
     * @param codiceDocumentoInformatico
     */
    public void setCodiceDocumentoInformatico(java.lang.String codiceDocumentoInformatico) {
        this.codiceDocumentoInformatico = codiceDocumentoInformatico;
    }


    /**
     * Gets the flPrimario value for this DocumentoInformatico.
     * 
     * @return flPrimario
     */
    public java.lang.String getFlPrimario() {
        return flPrimario;
    }


    /**
     * Sets the flPrimario value for this DocumentoInformatico.
     * 
     * @param flPrimario
     */
    public void setFlPrimario(java.lang.String flPrimario) {
        this.flPrimario = flPrimario;
    }


    /**
     * Gets the descrizioneDocumentoInformatico value for this DocumentoInformatico.
     * 
     * @return descrizioneDocumentoInformatico
     */
    public java.lang.String getDescrizioneDocumentoInformatico() {
        return descrizioneDocumentoInformatico;
    }


    /**
     * Sets the descrizioneDocumentoInformatico value for this DocumentoInformatico.
     * 
     * @param descrizioneDocumentoInformatico
     */
    public void setDescrizioneDocumentoInformatico(java.lang.String descrizioneDocumentoInformatico) {
        this.descrizioneDocumentoInformatico = descrizioneDocumentoInformatico;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentoInformatico)) return false;
        DocumentoInformatico other = (DocumentoInformatico) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceDocumentoInformatico==null && other.getCodiceDocumentoInformatico()==null) || 
             (this.codiceDocumentoInformatico!=null &&
              this.codiceDocumentoInformatico.equals(other.getCodiceDocumentoInformatico()))) &&
            ((this.flPrimario==null && other.getFlPrimario()==null) || 
             (this.flPrimario!=null &&
              this.flPrimario.equals(other.getFlPrimario()))) &&
            ((this.descrizioneDocumentoInformatico==null && other.getDescrizioneDocumentoInformatico()==null) || 
             (this.descrizioneDocumentoInformatico!=null &&
              this.descrizioneDocumentoInformatico.equals(other.getDescrizioneDocumentoInformatico())));
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
        if (getCodiceDocumentoInformatico() != null) {
            _hashCode += getCodiceDocumentoInformatico().hashCode();
        }
        if (getFlPrimario() != null) {
            _hashCode += getFlPrimario().hashCode();
        }
        if (getDescrizioneDocumentoInformatico() != null) {
            _hashCode += getDescrizioneDocumentoInformatico().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(DocumentoInformatico.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documentoInformatico"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceDocumentoInformatico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceDocumentoInformatico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("flPrimario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "flPrimario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneDocumentoInformatico");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneDocumentoInformatico"));
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
