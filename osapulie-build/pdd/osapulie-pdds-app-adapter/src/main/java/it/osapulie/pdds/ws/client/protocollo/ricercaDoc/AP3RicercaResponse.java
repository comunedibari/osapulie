/**
 * AP3RicercaResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class AP3RicercaResponse  implements java.io.Serializable {
    private java.lang.Integer numeroDocumenti;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumenti documenti;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Errore errore;

    private boolean esito;  // attribute

    public AP3RicercaResponse() {
    }

    public AP3RicercaResponse(
           java.lang.Integer numeroDocumenti,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumenti documenti,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Errore errore,
           boolean esito) {
           this.numeroDocumenti = numeroDocumenti;
           this.documenti = documenti;
           this.errore = errore;
           this.esito = esito;
    }


    /**
     * Gets the numeroDocumenti value for this AP3RicercaResponse.
     * 
     * @return numeroDocumenti
     */
    public java.lang.Integer getNumeroDocumenti() {
        return numeroDocumenti;
    }


    /**
     * Sets the numeroDocumenti value for this AP3RicercaResponse.
     * 
     * @param numeroDocumenti
     */
    public void setNumeroDocumenti(java.lang.Integer numeroDocumenti) {
        this.numeroDocumenti = numeroDocumenti;
    }


    /**
     * Gets the documenti value for this AP3RicercaResponse.
     * 
     * @return documenti
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumenti getDocumenti() {
        return documenti;
    }


    /**
     * Sets the documenti value for this AP3RicercaResponse.
     * 
     * @param documenti
     */
    public void setDocumenti(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumenti documenti) {
        this.documenti = documenti;
    }


    /**
     * Gets the errore value for this AP3RicercaResponse.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this AP3RicercaResponse.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the esito value for this AP3RicercaResponse.
     * 
     * @return esito
     */
    public boolean isEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this AP3RicercaResponse.
     * 
     * @param esito
     */
    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3RicercaResponse)) return false;
        AP3RicercaResponse other = (AP3RicercaResponse) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.numeroDocumenti==null && other.getNumeroDocumenti()==null) || 
             (this.numeroDocumenti!=null &&
              this.numeroDocumenti.equals(other.getNumeroDocumenti()))) &&
            ((this.documenti==null && other.getDocumenti()==null) || 
             (this.documenti!=null &&
              this.documenti.equals(other.getDocumenti()))) &&
            ((this.errore==null && other.getErrore()==null) || 
             (this.errore!=null &&
              this.errore.equals(other.getErrore()))) &&
            this.esito == other.isEsito();
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
        if (getNumeroDocumenti() != null) {
            _hashCode += getNumeroDocumenti().hashCode();
        }
        if (getDocumenti() != null) {
            _hashCode += getDocumenti().hashCode();
        }
        if (getErrore() != null) {
            _hashCode += getErrore().hashCode();
        }
        _hashCode += (isEsito() ? Boolean.TRUE : Boolean.FALSE).hashCode();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AP3RicercaResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3RicercaResponse"));
        org.apache.axis.description.AttributeDesc attrField = new org.apache.axis.description.AttributeDesc();
        attrField.setFieldName("esito");
        attrField.setXmlName(new javax.xml.namespace.QName("", "esito"));
        attrField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        typeDesc.addFieldDesc(attrField);
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroDocumenti");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDocumenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documenti");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumenti"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("errore");
        elemField.setXmlName(new javax.xml.namespace.QName("", "errore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "errore"));
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
