/**
 * RataVisuraPubblicheAffissioni.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visurapubblicheaffissioni;

public class RataVisuraPubblicheAffissioni  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.Double importoRata;

    private java.util.Date scadenzaRata;

    private java.math.BigInteger numeroRata;

    private java.lang.String identificativoRata;

    private java.lang.Double importoPagato;

    private java.util.Date dataPagamento;

    private java.math.BigInteger idVisura;

    public RataVisuraPubblicheAffissioni() {
    }

    public RataVisuraPubblicheAffissioni(
           java.math.BigInteger id,
           java.lang.Double importoRata,
           java.util.Date scadenzaRata,
           java.math.BigInteger numeroRata,
           java.lang.String identificativoRata,
           java.lang.Double importoPagato,
           java.util.Date dataPagamento,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.importoRata = importoRata;
           this.scadenzaRata = scadenzaRata;
           this.numeroRata = numeroRata;
           this.identificativoRata = identificativoRata;
           this.importoPagato = importoPagato;
           this.dataPagamento = dataPagamento;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this RataVisuraPubblicheAffissioni.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this RataVisuraPubblicheAffissioni.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the importoRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @return importoRata
     */
    public java.lang.Double getImportoRata() {
        return importoRata;
    }


    /**
     * Sets the importoRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @param importoRata
     */
    public void setImportoRata(java.lang.Double importoRata) {
        this.importoRata = importoRata;
    }


    /**
     * Gets the scadenzaRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @return scadenzaRata
     */
    public java.util.Date getScadenzaRata() {
        return scadenzaRata;
    }


    /**
     * Sets the scadenzaRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @param scadenzaRata
     */
    public void setScadenzaRata(java.util.Date scadenzaRata) {
        this.scadenzaRata = scadenzaRata;
    }


    /**
     * Gets the numeroRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @return numeroRata
     */
    public java.math.BigInteger getNumeroRata() {
        return numeroRata;
    }


    /**
     * Sets the numeroRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @param numeroRata
     */
    public void setNumeroRata(java.math.BigInteger numeroRata) {
        this.numeroRata = numeroRata;
    }


    /**
     * Gets the identificativoRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @return identificativoRata
     */
    public java.lang.String getIdentificativoRata() {
        return identificativoRata;
    }


    /**
     * Sets the identificativoRata value for this RataVisuraPubblicheAffissioni.
     * 
     * @param identificativoRata
     */
    public void setIdentificativoRata(java.lang.String identificativoRata) {
        this.identificativoRata = identificativoRata;
    }


    /**
     * Gets the importoPagato value for this RataVisuraPubblicheAffissioni.
     * 
     * @return importoPagato
     */
    public java.lang.Double getImportoPagato() {
        return importoPagato;
    }


    /**
     * Sets the importoPagato value for this RataVisuraPubblicheAffissioni.
     * 
     * @param importoPagato
     */
    public void setImportoPagato(java.lang.Double importoPagato) {
        this.importoPagato = importoPagato;
    }


    /**
     * Gets the dataPagamento value for this RataVisuraPubblicheAffissioni.
     * 
     * @return dataPagamento
     */
    public java.util.Date getDataPagamento() {
        return dataPagamento;
    }


    /**
     * Sets the dataPagamento value for this RataVisuraPubblicheAffissioni.
     * 
     * @param dataPagamento
     */
    public void setDataPagamento(java.util.Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


    /**
     * Gets the idVisura value for this RataVisuraPubblicheAffissioni.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this RataVisuraPubblicheAffissioni.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RataVisuraPubblicheAffissioni)) return false;
        RataVisuraPubblicheAffissioni other = (RataVisuraPubblicheAffissioni) obj;
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
            ((this.importoRata==null && other.getImportoRata()==null) || 
             (this.importoRata!=null &&
              this.importoRata.equals(other.getImportoRata()))) &&
            ((this.scadenzaRata==null && other.getScadenzaRata()==null) || 
             (this.scadenzaRata!=null &&
              this.scadenzaRata.equals(other.getScadenzaRata()))) &&
            ((this.numeroRata==null && other.getNumeroRata()==null) || 
             (this.numeroRata!=null &&
              this.numeroRata.equals(other.getNumeroRata()))) &&
            ((this.identificativoRata==null && other.getIdentificativoRata()==null) || 
             (this.identificativoRata!=null &&
              this.identificativoRata.equals(other.getIdentificativoRata()))) &&
            ((this.importoPagato==null && other.getImportoPagato()==null) || 
             (this.importoPagato!=null &&
              this.importoPagato.equals(other.getImportoPagato()))) &&
            ((this.dataPagamento==null && other.getDataPagamento()==null) || 
             (this.dataPagamento!=null &&
              this.dataPagamento.equals(other.getDataPagamento()))) &&
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
        if (getImportoRata() != null) {
            _hashCode += getImportoRata().hashCode();
        }
        if (getScadenzaRata() != null) {
            _hashCode += getScadenzaRata().hashCode();
        }
        if (getNumeroRata() != null) {
            _hashCode += getNumeroRata().hashCode();
        }
        if (getIdentificativoRata() != null) {
            _hashCode += getIdentificativoRata().hashCode();
        }
        if (getImportoPagato() != null) {
            _hashCode += getImportoPagato().hashCode();
        }
        if (getDataPagamento() != null) {
            _hashCode += getDataPagamento().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RataVisuraPubblicheAffissioni.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "rataVisuraPubblicheAffissioni"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoRata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoRata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scadenzaRata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "scadenzaRata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroRata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroRata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoRata");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "identificativoRata"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoPagato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoPagato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
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
