/**
 * CompletamentoProtocolloProvvisorioResponseReturn.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class CompletamentoProtocolloProvvisorioResponseReturn  implements java.io.Serializable {
    private java.lang.Long numeroProtocollo;

    private int anno;

    private java.util.Calendar dataProtocollo;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari;

    public CompletamentoProtocolloProvvisorioResponseReturn() {
    }

    public CompletamentoProtocolloProvvisorioResponseReturn(
           java.lang.Long numeroProtocollo,
           int anno,
           java.util.Calendar dataProtocollo,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari) {
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
           this.dataProtocollo = dataProtocollo;
           this.errore = errore;
           this.mittente = mittente;
           this.destinatari = destinatari;
    }


    /**
     * Gets the numeroProtocollo value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return numeroProtocollo
     */
    public java.lang.Long getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(java.lang.Long numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return anno
     */
    public int getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param anno
     */
    public void setAnno(int anno) {
        this.anno = anno;
    }


    /**
     * Gets the dataProtocollo value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return dataProtocollo
     */
    public java.util.Calendar getDataProtocollo() {
        return dataProtocollo;
    }


    /**
     * Sets the dataProtocollo value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param dataProtocollo
     */
    public void setDataProtocollo(java.util.Calendar dataProtocollo) {
        this.dataProtocollo = dataProtocollo;
    }


    /**
     * Gets the errore value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.protocollo.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the mittente value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return mittente
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente getMittente() {
        return mittente;
    }


    /**
     * Sets the mittente value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param mittente
     */
    public void setMittente(it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente) {
        this.mittente = mittente;
    }


    /**
     * Gets the destinatari value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @return destinatari
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] getDestinatari() {
        return destinatari;
    }


    /**
     * Sets the destinatari value for this CompletamentoProtocolloProvvisorioResponseReturn.
     * 
     * @param destinatari
     */
    public void setDestinatari(it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari) {
        this.destinatari = destinatari;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario getDestinatari(int i) {
        return this.destinatari[i];
    }

    public void setDestinatari(int i, it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario _value) {
        this.destinatari[i] = _value;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof CompletamentoProtocolloProvvisorioResponseReturn)) return false;
        CompletamentoProtocolloProvvisorioResponseReturn other = (CompletamentoProtocolloProvvisorioResponseReturn) obj;
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
            ((this.mittente==null && other.getMittente()==null) || 
             (this.mittente!=null &&
              this.mittente.equals(other.getMittente()))) &&
            ((this.destinatari==null && other.getDestinatari()==null) || 
             (this.destinatari!=null &&
              java.util.Arrays.equals(this.destinatari, other.getDestinatari())));
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
        if (getMittente() != null) {
            _hashCode += getMittente().hashCode();
        }
        if (getDestinatari() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestinatari());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestinatari(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(CompletamentoProtocolloProvvisorioResponseReturn.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">completamentoProtocolloProvvisorioResponse>return"));
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
        elemField.setFieldName("mittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Mittente"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatari");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatari"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Destinatario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
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
