/**
 * Tb_categoriaimmobile.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public class Tb_categoriaimmobile  implements java.io.Serializable {
    private java.math.BigInteger ID;

    private java.lang.String codice;

    private java.lang.String descrizione;

    private java.lang.Double coefficienteRivalutazione;

    private java.lang.Double coefficienteMoltiplicazione;

    private java.lang.Double percentualeProprietario;

    private java.lang.Double percentualeInquilinoComodatario;

    public Tb_categoriaimmobile() {
    }

    public Tb_categoriaimmobile(
           java.math.BigInteger ID,
           java.lang.String codice,
           java.lang.String descrizione,
           java.lang.Double coefficienteRivalutazione,
           java.lang.Double coefficienteMoltiplicazione,
           java.lang.Double percentualeProprietario,
           java.lang.Double percentualeInquilinoComodatario) {
           this.ID = ID;
           this.codice = codice;
           this.descrizione = descrizione;
           this.coefficienteRivalutazione = coefficienteRivalutazione;
           this.coefficienteMoltiplicazione = coefficienteMoltiplicazione;
           this.percentualeProprietario = percentualeProprietario;
           this.percentualeInquilinoComodatario = percentualeInquilinoComodatario;
    }


    /**
     * Gets the ID value for this Tb_categoriaimmobile.
     * 
     * @return ID
     */
    public java.math.BigInteger getID() {
        return ID;
    }


    /**
     * Sets the ID value for this Tb_categoriaimmobile.
     * 
     * @param ID
     */
    public void setID(java.math.BigInteger ID) {
        this.ID = ID;
    }


    /**
     * Gets the codice value for this Tb_categoriaimmobile.
     * 
     * @return codice
     */
    public java.lang.String getCodice() {
        return codice;
    }


    /**
     * Sets the codice value for this Tb_categoriaimmobile.
     * 
     * @param codice
     */
    public void setCodice(java.lang.String codice) {
        this.codice = codice;
    }


    /**
     * Gets the descrizione value for this Tb_categoriaimmobile.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Tb_categoriaimmobile.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the coefficienteRivalutazione value for this Tb_categoriaimmobile.
     * 
     * @return coefficienteRivalutazione
     */
    public java.lang.Double getCoefficienteRivalutazione() {
        return coefficienteRivalutazione;
    }


    /**
     * Sets the coefficienteRivalutazione value for this Tb_categoriaimmobile.
     * 
     * @param coefficienteRivalutazione
     */
    public void setCoefficienteRivalutazione(java.lang.Double coefficienteRivalutazione) {
        this.coefficienteRivalutazione = coefficienteRivalutazione;
    }


    /**
     * Gets the coefficienteMoltiplicazione value for this Tb_categoriaimmobile.
     * 
     * @return coefficienteMoltiplicazione
     */
    public java.lang.Double getCoefficienteMoltiplicazione() {
        return coefficienteMoltiplicazione;
    }


    /**
     * Sets the coefficienteMoltiplicazione value for this Tb_categoriaimmobile.
     * 
     * @param coefficienteMoltiplicazione
     */
    public void setCoefficienteMoltiplicazione(java.lang.Double coefficienteMoltiplicazione) {
        this.coefficienteMoltiplicazione = coefficienteMoltiplicazione;
    }


    /**
     * Gets the percentualeProprietario value for this Tb_categoriaimmobile.
     * 
     * @return percentualeProprietario
     */
    public java.lang.Double getPercentualeProprietario() {
        return percentualeProprietario;
    }


    /**
     * Sets the percentualeProprietario value for this Tb_categoriaimmobile.
     * 
     * @param percentualeProprietario
     */
    public void setPercentualeProprietario(java.lang.Double percentualeProprietario) {
        this.percentualeProprietario = percentualeProprietario;
    }


    /**
     * Gets the percentualeInquilinoComodatario value for this Tb_categoriaimmobile.
     * 
     * @return percentualeInquilinoComodatario
     */
    public java.lang.Double getPercentualeInquilinoComodatario() {
        return percentualeInquilinoComodatario;
    }


    /**
     * Sets the percentualeInquilinoComodatario value for this Tb_categoriaimmobile.
     * 
     * @param percentualeInquilinoComodatario
     */
    public void setPercentualeInquilinoComodatario(java.lang.Double percentualeInquilinoComodatario) {
        this.percentualeInquilinoComodatario = percentualeInquilinoComodatario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tb_categoriaimmobile)) return false;
        Tb_categoriaimmobile other = (Tb_categoriaimmobile) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.codice==null && other.getCodice()==null) || 
             (this.codice!=null &&
              this.codice.equals(other.getCodice()))) &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.coefficienteRivalutazione==null && other.getCoefficienteRivalutazione()==null) || 
             (this.coefficienteRivalutazione!=null &&
              this.coefficienteRivalutazione.equals(other.getCoefficienteRivalutazione()))) &&
            ((this.coefficienteMoltiplicazione==null && other.getCoefficienteMoltiplicazione()==null) || 
             (this.coefficienteMoltiplicazione!=null &&
              this.coefficienteMoltiplicazione.equals(other.getCoefficienteMoltiplicazione()))) &&
            ((this.percentualeProprietario==null && other.getPercentualeProprietario()==null) || 
             (this.percentualeProprietario!=null &&
              this.percentualeProprietario.equals(other.getPercentualeProprietario()))) &&
            ((this.percentualeInquilinoComodatario==null && other.getPercentualeInquilinoComodatario()==null) || 
             (this.percentualeInquilinoComodatario!=null &&
              this.percentualeInquilinoComodatario.equals(other.getPercentualeInquilinoComodatario())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getCodice() != null) {
            _hashCode += getCodice().hashCode();
        }
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getCoefficienteRivalutazione() != null) {
            _hashCode += getCoefficienteRivalutazione().hashCode();
        }
        if (getCoefficienteMoltiplicazione() != null) {
            _hashCode += getCoefficienteMoltiplicazione().hashCode();
        }
        if (getPercentualeProprietario() != null) {
            _hashCode += getPercentualeProprietario().hashCode();
        }
        if (getPercentualeInquilinoComodatario() != null) {
            _hashCode += getPercentualeInquilinoComodatario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tb_categoriaimmobile.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_categoriaimmobile"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codice");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "codice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coefficienteRivalutazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteRivalutazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("coefficienteMoltiplicazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "coefficienteMoltiplicazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualeProprietario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeProprietario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("percentualeInquilinoComodatario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "percentualeInquilinoComodatario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
