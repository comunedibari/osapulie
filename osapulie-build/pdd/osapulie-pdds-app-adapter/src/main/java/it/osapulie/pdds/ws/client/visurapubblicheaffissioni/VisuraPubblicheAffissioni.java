/**
 * VisuraPubblicheAffissioni.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visurapubblicheaffissioni;

public class VisuraPubblicheAffissioni  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String codiceFiscale;

    private java.util.Date dataAggiornamento;

    private java.lang.String contoCorrente;

    private java.lang.Double importoDocumento;

    private java.lang.String numeroDocumento;

    private java.math.BigInteger annoRiferimento;

    public VisuraPubblicheAffissioni() {
    }

    public VisuraPubblicheAffissioni(
           java.math.BigInteger id,
           java.lang.String codiceFiscale,
           java.util.Date dataAggiornamento,
           java.lang.String contoCorrente,
           java.lang.Double importoDocumento,
           java.lang.String numeroDocumento,
           java.math.BigInteger annoRiferimento) {
           this.id = id;
           this.codiceFiscale = codiceFiscale;
           this.dataAggiornamento = dataAggiornamento;
           this.contoCorrente = contoCorrente;
           this.importoDocumento = importoDocumento;
           this.numeroDocumento = numeroDocumento;
           this.annoRiferimento = annoRiferimento;
    }


    /**
     * Gets the id value for this VisuraPubblicheAffissioni.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this VisuraPubblicheAffissioni.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the codiceFiscale value for this VisuraPubblicheAffissioni.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this VisuraPubblicheAffissioni.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the dataAggiornamento value for this VisuraPubblicheAffissioni.
     * 
     * @return dataAggiornamento
     */
    public java.util.Date getDataAggiornamento() {
        return dataAggiornamento;
    }


    /**
     * Sets the dataAggiornamento value for this VisuraPubblicheAffissioni.
     * 
     * @param dataAggiornamento
     */
    public void setDataAggiornamento(java.util.Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }


    /**
     * Gets the contoCorrente value for this VisuraPubblicheAffissioni.
     * 
     * @return contoCorrente
     */
    public java.lang.String getContoCorrente() {
        return contoCorrente;
    }


    /**
     * Sets the contoCorrente value for this VisuraPubblicheAffissioni.
     * 
     * @param contoCorrente
     */
    public void setContoCorrente(java.lang.String contoCorrente) {
        this.contoCorrente = contoCorrente;
    }


    /**
     * Gets the importoDocumento value for this VisuraPubblicheAffissioni.
     * 
     * @return importoDocumento
     */
    public java.lang.Double getImportoDocumento() {
        return importoDocumento;
    }


    /**
     * Sets the importoDocumento value for this VisuraPubblicheAffissioni.
     * 
     * @param importoDocumento
     */
    public void setImportoDocumento(java.lang.Double importoDocumento) {
        this.importoDocumento = importoDocumento;
    }


    /**
     * Gets the numeroDocumento value for this VisuraPubblicheAffissioni.
     * 
     * @return numeroDocumento
     */
    public java.lang.String getNumeroDocumento() {
        return numeroDocumento;
    }


    /**
     * Sets the numeroDocumento value for this VisuraPubblicheAffissioni.
     * 
     * @param numeroDocumento
     */
    public void setNumeroDocumento(java.lang.String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }


    /**
     * Gets the annoRiferimento value for this VisuraPubblicheAffissioni.
     * 
     * @return annoRiferimento
     */
    public java.math.BigInteger getAnnoRiferimento() {
        return annoRiferimento;
    }


    /**
     * Sets the annoRiferimento value for this VisuraPubblicheAffissioni.
     * 
     * @param annoRiferimento
     */
    public void setAnnoRiferimento(java.math.BigInteger annoRiferimento) {
        this.annoRiferimento = annoRiferimento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VisuraPubblicheAffissioni)) return false;
        VisuraPubblicheAffissioni other = (VisuraPubblicheAffissioni) obj;
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
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.dataAggiornamento==null && other.getDataAggiornamento()==null) || 
             (this.dataAggiornamento!=null &&
              this.dataAggiornamento.equals(other.getDataAggiornamento()))) &&
            ((this.contoCorrente==null && other.getContoCorrente()==null) || 
             (this.contoCorrente!=null &&
              this.contoCorrente.equals(other.getContoCorrente()))) &&
            ((this.importoDocumento==null && other.getImportoDocumento()==null) || 
             (this.importoDocumento!=null &&
              this.importoDocumento.equals(other.getImportoDocumento()))) &&
            ((this.numeroDocumento==null && other.getNumeroDocumento()==null) || 
             (this.numeroDocumento!=null &&
              this.numeroDocumento.equals(other.getNumeroDocumento()))) &&
            ((this.annoRiferimento==null && other.getAnnoRiferimento()==null) || 
             (this.annoRiferimento!=null &&
              this.annoRiferimento.equals(other.getAnnoRiferimento())));
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
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getDataAggiornamento() != null) {
            _hashCode += getDataAggiornamento().hashCode();
        }
        if (getContoCorrente() != null) {
            _hashCode += getContoCorrente().hashCode();
        }
        if (getImportoDocumento() != null) {
            _hashCode += getImportoDocumento().hashCode();
        }
        if (getNumeroDocumento() != null) {
            _hashCode += getNumeroDocumento().hashCode();
        }
        if (getAnnoRiferimento() != null) {
            _hashCode += getAnnoRiferimento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VisuraPubblicheAffissioni.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraPubblicheAffissioni"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
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
        elemField.setFieldName("dataAggiornamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataAggiornamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contoCorrente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "contoCorrente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoRiferimento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoRiferimento"));
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
