/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class Documento  implements java.io.Serializable {
    private java.lang.String titolo;

    private java.lang.String sunto;

    private java.lang.String dettaglio;

    private java.lang.String nomeFile;

    private java.lang.String classifica;

    private byte[] contenuto;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME improntaMIME;

    private java.lang.String collocazioneTelematica;

    private java.lang.String tipoRiferimento;  // attribute

    public Documento() {
    }

    public Documento(
           java.lang.String titolo,
           java.lang.String sunto,
           java.lang.String dettaglio,
           java.lang.String nomeFile,
           java.lang.String classifica,
           byte[] contenuto,
           it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME improntaMIME,
           java.lang.String collocazioneTelematica,
           java.lang.String tipoRiferimento) {
           this.titolo = titolo;
           this.sunto = sunto;
           this.dettaglio = dettaglio;
           this.nomeFile = nomeFile;
           this.classifica = classifica;
           this.contenuto = contenuto;
           this.improntaMIME = improntaMIME;
           this.collocazioneTelematica = collocazioneTelematica;
           this.tipoRiferimento = tipoRiferimento;
    }


    /**
     * Gets the titolo value for this Documento.
     * 
     * @return titolo
     */
    public java.lang.String getTitolo() {
        return titolo;
    }


    /**
     * Sets the titolo value for this Documento.
     * 
     * @param titolo
     */
    public void setTitolo(java.lang.String titolo) {
        this.titolo = titolo;
    }


    /**
     * Gets the sunto value for this Documento.
     * 
     * @return sunto
     */
    public java.lang.String getSunto() {
        return sunto;
    }


    /**
     * Sets the sunto value for this Documento.
     * 
     * @param sunto
     */
    public void setSunto(java.lang.String sunto) {
        this.sunto = sunto;
    }


    /**
     * Gets the dettaglio value for this Documento.
     * 
     * @return dettaglio
     */
    public java.lang.String getDettaglio() {
        return dettaglio;
    }


    /**
     * Sets the dettaglio value for this Documento.
     * 
     * @param dettaglio
     */
    public void setDettaglio(java.lang.String dettaglio) {
        this.dettaglio = dettaglio;
    }


    /**
     * Gets the nomeFile value for this Documento.
     * 
     * @return nomeFile
     */
    public java.lang.String getNomeFile() {
        return nomeFile;
    }


    /**
     * Sets the nomeFile value for this Documento.
     * 
     * @param nomeFile
     */
    public void setNomeFile(java.lang.String nomeFile) {
        this.nomeFile = nomeFile;
    }


    /**
     * Gets the classifica value for this Documento.
     * 
     * @return classifica
     */
    public java.lang.String getClassifica() {
        return classifica;
    }


    /**
     * Sets the classifica value for this Documento.
     * 
     * @param classifica
     */
    public void setClassifica(java.lang.String classifica) {
        this.classifica = classifica;
    }


    /**
     * Gets the contenuto value for this Documento.
     * 
     * @return contenuto
     */
    public byte[] getContenuto() {
        return contenuto;
    }


    /**
     * Sets the contenuto value for this Documento.
     * 
     * @param contenuto
     */
    public void setContenuto(byte[] contenuto) {
        this.contenuto = contenuto;
    }


    /**
     * Gets the improntaMIME value for this Documento.
     * 
     * @return improntaMIME
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME getImprontaMIME() {
        return improntaMIME;
    }


    /**
     * Sets the improntaMIME value for this Documento.
     * 
     * @param improntaMIME
     */
    public void setImprontaMIME(it.osapulie.pdds.ws.client.protocollo.protocollo.ImprontaMIME improntaMIME) {
        this.improntaMIME = improntaMIME;
    }


    /**
     * Gets the collocazioneTelematica value for this Documento.
     * 
     * @return collocazioneTelematica
     */
    public java.lang.String getCollocazioneTelematica() {
        return collocazioneTelematica;
    }


    /**
     * Sets the collocazioneTelematica value for this Documento.
     * 
     * @param collocazioneTelematica
     */
    public void setCollocazioneTelematica(java.lang.String collocazioneTelematica) {
        this.collocazioneTelematica = collocazioneTelematica;
    }


    /**
     * Gets the tipoRiferimento value for this Documento.
     * 
     * @return tipoRiferimento
     */
    public java.lang.String getTipoRiferimento() {
        return tipoRiferimento;
    }


    /**
     * Sets the tipoRiferimento value for this Documento.
     * 
     * @param tipoRiferimento
     */
    public void setTipoRiferimento(java.lang.String tipoRiferimento) {
        this.tipoRiferimento = tipoRiferimento;
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
            ((this.titolo==null && other.getTitolo()==null) || 
             (this.titolo!=null &&
              this.titolo.equals(other.getTitolo()))) &&
            ((this.sunto==null && other.getSunto()==null) || 
             (this.sunto!=null &&
              this.sunto.equals(other.getSunto()))) &&
            ((this.dettaglio==null && other.getDettaglio()==null) || 
             (this.dettaglio!=null &&
              this.dettaglio.equals(other.getDettaglio()))) &&
            ((this.nomeFile==null && other.getNomeFile()==null) || 
             (this.nomeFile!=null &&
              this.nomeFile.equals(other.getNomeFile()))) &&
            ((this.classifica==null && other.getClassifica()==null) || 
             (this.classifica!=null &&
              this.classifica.equals(other.getClassifica()))) &&
            ((this.contenuto==null && other.getContenuto()==null) || 
             (this.contenuto!=null &&
              java.util.Arrays.equals(this.contenuto, other.getContenuto()))) &&
            ((this.improntaMIME==null && other.getImprontaMIME()==null) || 
             (this.improntaMIME!=null &&
              this.improntaMIME.equals(other.getImprontaMIME()))) &&
            ((this.collocazioneTelematica==null && other.getCollocazioneTelematica()==null) || 
             (this.collocazioneTelematica!=null &&
              this.collocazioneTelematica.equals(other.getCollocazioneTelematica()))) &&
            ((this.tipoRiferimento==null && other.getTipoRiferimento()==null) || 
             (this.tipoRiferimento!=null &&
              this.tipoRiferimento.equals(other.getTipoRiferimento())));
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
        if (getTitolo() != null) {
            _hashCode += getTitolo().hashCode();
        }
        if (getSunto() != null) {
            _hashCode += getSunto().hashCode();
        }
        if (getDettaglio() != null) {
            _hashCode += getDettaglio().hashCode();
        }
        if (getNomeFile() != null) {
            _hashCode += getNomeFile().hashCode();
        }
        if (getClassifica() != null) {
            _hashCode += getClassifica().hashCode();
        }
        if (getContenuto() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getContenuto());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getContenuto(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getImprontaMIME() != null) {
            _hashCode += getImprontaMIME().hashCode();
        }
        if (getCollocazioneTelematica() != null) {
            _hashCode += getCollocazioneTelematica().hashCode();
        }
        if (getTipoRiferimento() != null) {
            _hashCode += getTipoRiferimento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Documento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Documento"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("tipoRiferimento");
        attrField.setXmlName(new javax.xml.namespace.QName("", "tipoRiferimento"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "titolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sunto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "sunto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dettaglio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dettaglio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nomeFile");
        elemField.setXmlName(new javax.xml.namespace.QName("", "nomeFile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contenuto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "contenuto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("improntaMIME");
        elemField.setXmlName(new javax.xml.namespace.QName("", "improntaMIME"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "ImprontaMIME"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("collocazioneTelematica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "collocazioneTelematica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
