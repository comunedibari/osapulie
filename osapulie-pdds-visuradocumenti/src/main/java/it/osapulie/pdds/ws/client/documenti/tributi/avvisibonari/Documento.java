/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari;

public class Documento  implements java.io.Serializable {
    private java.math.BigInteger matricola;

    private java.lang.String codiceFiscale;

    private java.math.BigInteger anno;

    private java.math.BigInteger tipo;

    private java.lang.String annoRiferimento;

    private java.lang.String file;

    public Documento() {
    }

    public Documento(
           java.math.BigInteger matricola,
           java.lang.String codiceFiscale,
           java.math.BigInteger anno,
           java.math.BigInteger tipo,
           java.lang.String annoRiferimento,
           java.lang.String file) {
           this.matricola = matricola;
           this.codiceFiscale = codiceFiscale;
           this.anno = anno;
           this.tipo = tipo;
           this.annoRiferimento = annoRiferimento;
           this.file = file;
    }


    /**
     * Gets the matricola value for this Documento.
     * 
     * @return matricola
     */
    public java.math.BigInteger getMatricola() {
        return matricola;
    }


    /**
     * Sets the matricola value for this Documento.
     * 
     * @param matricola
     */
    public void setMatricola(java.math.BigInteger matricola) {
        this.matricola = matricola;
    }


    /**
     * Gets the codiceFiscale value for this Documento.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this Documento.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the anno value for this Documento.
     * 
     * @return anno
     */
    public java.math.BigInteger getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this Documento.
     * 
     * @param anno
     */
    public void setAnno(java.math.BigInteger anno) {
        this.anno = anno;
    }


    /**
     * Gets the tipo value for this Documento.
     * 
     * @return tipo
     */
    public java.math.BigInteger getTipo() {
        return tipo;
    }


    /**
     * Sets the tipo value for this Documento.
     * 
     * @param tipo
     */
    public void setTipo(java.math.BigInteger tipo) {
        this.tipo = tipo;
    }


    /**
     * Gets the annoRiferimento value for this Documento.
     * 
     * @return annoRiferimento
     */
    public java.lang.String getAnnoRiferimento() {
        return annoRiferimento;
    }


    /**
     * Sets the annoRiferimento value for this Documento.
     * 
     * @param annoRiferimento
     */
    public void setAnnoRiferimento(java.lang.String annoRiferimento) {
        this.annoRiferimento = annoRiferimento;
    }


    /**
     * Gets the file value for this Documento.
     * 
     * @return file
     */
    public java.lang.String getFile() {
        return file;
    }


    /**
     * Sets the file value for this Documento.
     * 
     * @param file
     */
    public void setFile(java.lang.String file) {
        this.file = file;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Documento)) return false;
        Documento other = (Documento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.matricola==null && other.getMatricola()==null) || 
             (this.matricola!=null &&
              this.matricola.equals(other.getMatricola()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.tipo==null && other.getTipo()==null) || 
             (this.tipo!=null &&
              this.tipo.equals(other.getTipo()))) &&
            ((this.annoRiferimento==null && other.getAnnoRiferimento()==null) || 
             (this.annoRiferimento!=null &&
              this.annoRiferimento.equals(other.getAnnoRiferimento()))) &&
            ((this.file==null && other.getFile()==null) || 
             (this.file!=null &&
              this.file.equals(other.getFile())));
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
        if (getMatricola() != null) {
            _hashCode += getMatricola().hashCode();
        }
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getTipo() != null) {
            _hashCode += getTipo().hashCode();
        }
        if (getAnnoRiferimento() != null) {
            _hashCode += getAnnoRiferimento().hashCode();
        }
        if (getFile() != null) {
            _hashCode += getFile().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Documento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "documento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("matricola");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "matricola"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "tipo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoRiferimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoRiferimento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("file");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "file"));
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
