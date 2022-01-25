/**
 * CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest  implements java.io.Serializable {
    private long numeroProtocollo;

    private java.lang.String anno;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento;

    private java.lang.String amministrazione;

    private java.lang.String areaOrganizzativaOmogenea;

    private java.lang.Integer idUtente;

    public CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest() {
    }

    public CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest(
           long numeroProtocollo,
           java.lang.String anno,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento,
           java.lang.String amministrazione,
           java.lang.String areaOrganizzativaOmogenea,
           java.lang.Integer idUtente) {
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
           this.documento = documento;
           this.amministrazione = amministrazione;
           this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
           this.idUtente = idUtente;
    }


    /**
     * Gets the numeroProtocollo value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return numeroProtocollo
     */
    public long getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(long numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }


    /**
     * Gets the documento value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return documento
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Documento getDocumento() {
        return documento;
    }


    /**
     * Sets the documento value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param documento
     */
    public void setDocumento(it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento) {
        this.documento = documento;
    }


    /**
     * Gets the amministrazione value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return amministrazione
     */
    public java.lang.String getAmministrazione() {
        return amministrazione;
    }


    /**
     * Sets the amministrazione value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param amministrazione
     */
    public void setAmministrazione(java.lang.String amministrazione) {
        this.amministrazione = amministrazione;
    }


    /**
     * Gets the areaOrganizzativaOmogenea value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return areaOrganizzativaOmogenea
     */
    public java.lang.String getAreaOrganizzativaOmogenea() {
        return areaOrganizzativaOmogenea;
    }


    /**
     * Sets the areaOrganizzativaOmogenea value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param areaOrganizzativaOmogenea
     */
    public void setAreaOrganizzativaOmogenea(java.lang.String areaOrganizzativaOmogenea) {
        this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
    }


    /**
     * Gets the idUtente value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @return idUtente
     */
    public java.lang.Integer getIdUtente() {
        return idUtente;
    }


    /**
     * Sets the idUtente value for this CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.
     * 
     * @param idUtente
     */
    public void setIdUtente(java.lang.Integer idUtente) {
        this.idUtente = idUtente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest)) return false;
        CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest other = (CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.numeroProtocollo == other.getNumeroProtocollo() &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.documento==null && other.getDocumento()==null) || 
             (this.documento!=null &&
              this.documento.equals(other.getDocumento()))) &&
            ((this.amministrazione==null && other.getAmministrazione()==null) || 
             (this.amministrazione!=null &&
              this.amministrazione.equals(other.getAmministrazione()))) &&
            ((this.areaOrganizzativaOmogenea==null && other.getAreaOrganizzativaOmogenea()==null) || 
             (this.areaOrganizzativaOmogenea!=null &&
              this.areaOrganizzativaOmogenea.equals(other.getAreaOrganizzativaOmogenea()))) &&
            ((this.idUtente==null && other.getIdUtente()==null) || 
             (this.idUtente!=null &&
              this.idUtente.equals(other.getIdUtente())));
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
        _hashCode += new Long(getNumeroProtocollo()).hashCode();
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getDocumento() != null) {
            _hashCode += getDocumento().hashCode();
        }
        if (getAmministrazione() != null) {
            _hashCode += getAmministrazione().hashCode();
        }
        if (getAreaOrganizzativaOmogenea() != null) {
            _hashCode += getAreaOrganizzativaOmogenea().hashCode();
        }
        if (getIdUtente() != null) {
            _hashCode += getIdUtente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorio>protocolloDaCompletareRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Documento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amministrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaOrganizzativaOmogenea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaOrganizzativaOmogenea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUtente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUtente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
