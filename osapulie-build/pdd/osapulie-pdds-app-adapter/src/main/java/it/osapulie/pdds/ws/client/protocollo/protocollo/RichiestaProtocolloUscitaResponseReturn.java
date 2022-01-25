/**
 * RichiestaProtocolloUscitaResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class RichiestaProtocolloUscitaResponseReturn  implements java.io.Serializable {
    private java.lang.Long numeroProtocollo;

    private int anno;

    private java.util.Calendar dataProtocollo;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore;

    private java.lang.Boolean esitoProtocollazione;

    private java.lang.Boolean esitoCompletamentoProtocollo;

    private java.lang.Boolean esitoInvio;

    public RichiestaProtocolloUscitaResponseReturn() {
    }

    public RichiestaProtocolloUscitaResponseReturn(
           java.lang.Long numeroProtocollo,
           int anno,
           java.util.Calendar dataProtocollo,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore,
           java.lang.Boolean esitoProtocollazione,
           java.lang.Boolean esitoCompletamentoProtocollo,
           java.lang.Boolean esitoInvio) {
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
           this.dataProtocollo = dataProtocollo;
           this.errore = errore;
           this.esitoProtocollazione = esitoProtocollazione;
           this.esitoCompletamentoProtocollo = esitoCompletamentoProtocollo;
           this.esitoInvio = esitoInvio;
    }


    /**
     * Gets the numeroProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return numeroProtocollo
     */
    public java.lang.Long getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(java.lang.Long numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return anno
     */
    public int getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param anno
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }


    /**
     * Gets the dataProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return dataProtocollo
     */
    public java.util.Calendar getDataProtocollo() {
        return dataProtocollo;
    }


    /**
     * Sets the dataProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param dataProtocollo
     */
    public void setDataProtocollo(java.util.Calendar dataProtocollo) {
        this.dataProtocollo = dataProtocollo;
    }


    /**
     * Gets the errore value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the esitoProtocollazione value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return esitoProtocollazione
     */
    public java.lang.Boolean getEsitoProtocollazione() {
        return esitoProtocollazione;
    }


    /**
     * Sets the esitoProtocollazione value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param esitoProtocollazione
     */
    public void setEsitoProtocollazione(java.lang.Boolean esitoProtocollazione) {
        this.esitoProtocollazione = esitoProtocollazione;
    }


    /**
     * Gets the esitoCompletamentoProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return esitoCompletamentoProtocollo
     */
    public java.lang.Boolean getEsitoCompletamentoProtocollo() {
        return esitoCompletamentoProtocollo;
    }


    /**
     * Sets the esitoCompletamentoProtocollo value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param esitoCompletamentoProtocollo
     */
    public void setEsitoCompletamentoProtocollo(java.lang.Boolean esitoCompletamentoProtocollo) {
        this.esitoCompletamentoProtocollo = esitoCompletamentoProtocollo;
    }


    /**
     * Gets the esitoInvio value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @return esitoInvio
     */
    public java.lang.Boolean getEsitoInvio() {
        return esitoInvio;
    }


    /**
     * Sets the esitoInvio value for this RichiestaProtocolloUscitaResponseReturn.
     * 
     * @param esitoInvio
     */
    public void setEsitoInvio(java.lang.Boolean esitoInvio) {
        this.esitoInvio = esitoInvio;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RichiestaProtocolloUscitaResponseReturn)) return false;
        RichiestaProtocolloUscitaResponseReturn other = (RichiestaProtocolloUscitaResponseReturn) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroProtocollo==null && other.getNumeroProtocollo()==null) || 
             (this.numeroProtocollo!=null &&
              this.numeroProtocollo.equals(other.getNumeroProtocollo()))) &&
            this.anno == other.getAnno() &&
            ((this.dataProtocollo==null && other.getDataProtocollo()==null) || 
             (this.dataProtocollo!=null &&
              this.dataProtocollo.equals(other.getDataProtocollo()))) &&
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore()))) &&
            ((this.esitoProtocollazione==null && other.getEsitoProtocollazione()==null) || 
             (this.esitoProtocollazione!=null &&
              this.esitoProtocollazione.equals(other.getEsitoProtocollazione()))) &&
            ((this.esitoCompletamentoProtocollo==null && other.getEsitoCompletamentoProtocollo()==null) || 
             (this.esitoCompletamentoProtocollo!=null &&
              this.esitoCompletamentoProtocollo.equals(other.getEsitoCompletamentoProtocollo()))) &&
            ((this.esitoInvio==null && other.getEsitoInvio()==null) || 
             (this.esitoInvio!=null &&
              this.esitoInvio.equals(other.getEsitoInvio())));
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
        if (getNumeroProtocollo() != null) {
            _hashCode += getNumeroProtocollo().hashCode();
        }
        _hashCode += getAnno();
        if (getDataProtocollo() != null) {
            _hashCode += getDataProtocollo().hashCode();
        }
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        if (getEsitoProtocollazione() != null) {
            _hashCode += getEsitoProtocollazione().hashCode();
        }
        if (getEsitoCompletamentoProtocollo() != null) {
            _hashCode += getEsitoCompletamentoProtocollo().hashCode();
        }
        if (getEsitoInvio() != null) {
            _hashCode += getEsitoInvio().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RichiestaProtocolloUscitaResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaResponse>return"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Errore"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoProtocollazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoProtocollazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoCompletamentoProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoCompletamentoProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esitoInvio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "esitoInvio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
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
