/**
 * AP3InterrogaFascResponse.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class AP3InterrogaFascResponse  implements java.io.Serializable {
    private java.lang.Integer numeroDocumenti;

    private java.lang.Integer numeroDocumentiNonVisibili;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicoloVirtuale estremiFascicoloVirtuale;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicolo estremiFascicolo;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiFascicolo documentiFascicolo;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Errore errore;

    private boolean esito;  // attribute

    public AP3InterrogaFascResponse() {
    }

    public AP3InterrogaFascResponse(
           java.lang.Integer numeroDocumenti,
           java.lang.Integer numeroDocumentiNonVisibili,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicoloVirtuale estremiFascicoloVirtuale,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicolo estremiFascicolo,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiFascicolo documentiFascicolo,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Errore errore,
           boolean esito) {
           this.numeroDocumenti = numeroDocumenti;
           this.numeroDocumentiNonVisibili = numeroDocumentiNonVisibili;
           this.estremiFascicoloVirtuale = estremiFascicoloVirtuale;
           this.estremiFascicolo = estremiFascicolo;
           this.documentiFascicolo = documentiFascicolo;
           this.errore = errore;
           this.esito = esito;
    }


    /**
     * Gets the numeroDocumenti value for this AP3InterrogaFascResponse.
     * 
     * @return numeroDocumenti
     */
    public java.lang.Integer getNumeroDocumenti() {
        return numeroDocumenti;
    }


    /**
     * Sets the numeroDocumenti value for this AP3InterrogaFascResponse.
     * 
     * @param numeroDocumenti
     */
    public void setNumeroDocumenti(java.lang.Integer numeroDocumenti) {
        this.numeroDocumenti = numeroDocumenti;
    }


    /**
     * Gets the numeroDocumentiNonVisibili value for this AP3InterrogaFascResponse.
     * 
     * @return numeroDocumentiNonVisibili
     */
    public java.lang.Integer getNumeroDocumentiNonVisibili() {
        return numeroDocumentiNonVisibili;
    }


    /**
     * Sets the numeroDocumentiNonVisibili value for this AP3InterrogaFascResponse.
     * 
     * @param numeroDocumentiNonVisibili
     */
    public void setNumeroDocumentiNonVisibili(java.lang.Integer numeroDocumentiNonVisibili) {
        this.numeroDocumentiNonVisibili = numeroDocumentiNonVisibili;
    }


    /**
     * Gets the estremiFascicoloVirtuale value for this AP3InterrogaFascResponse.
     * 
     * @return estremiFascicoloVirtuale
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicoloVirtuale getEstremiFascicoloVirtuale() {
        return estremiFascicoloVirtuale;
    }


    /**
     * Sets the estremiFascicoloVirtuale value for this AP3InterrogaFascResponse.
     * 
     * @param estremiFascicoloVirtuale
     */
    public void setEstremiFascicoloVirtuale(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicoloVirtuale estremiFascicoloVirtuale) {
        this.estremiFascicoloVirtuale = estremiFascicoloVirtuale;
    }


    /**
     * Gets the estremiFascicolo value for this AP3InterrogaFascResponse.
     * 
     * @return estremiFascicolo
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicolo getEstremiFascicolo() {
        return estremiFascicolo;
    }


    /**
     * Sets the estremiFascicolo value for this AP3InterrogaFascResponse.
     * 
     * @param estremiFascicolo
     */
    public void setEstremiFascicolo(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiFascicolo estremiFascicolo) {
        this.estremiFascicolo = estremiFascicolo;
    }


    /**
     * Gets the documentiFascicolo value for this AP3InterrogaFascResponse.
     * 
     * @return documentiFascicolo
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiFascicolo getDocumentiFascicolo() {
        return documentiFascicolo;
    }


    /**
     * Sets the documentiFascicolo value for this AP3InterrogaFascResponse.
     * 
     * @param documentiFascicolo
     */
    public void setDocumentiFascicolo(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiFascicolo documentiFascicolo) {
        this.documentiFascicolo = documentiFascicolo;
    }


    /**
     * Gets the errore value for this AP3InterrogaFascResponse.
     * 
     * @return errore
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Errore getErrore() {
        return errore;
    }


    /**
     * Sets the errore value for this AP3InterrogaFascResponse.
     * 
     * @param errore
     */
    public void setErrore(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Errore errore) {
        this.errore = errore;
    }


    /**
     * Gets the esito value for this AP3InterrogaFascResponse.
     * 
     * @return esito
     */
    public boolean isEsito() {
        return esito;
    }


    /**
     * Sets the esito value for this AP3InterrogaFascResponse.
     * 
     * @param esito
     */
    public void setEsito(boolean esito) {
        this.esito = esito;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3InterrogaFascResponse)) return false;
        AP3InterrogaFascResponse other = (AP3InterrogaFascResponse) obj;
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
            ((this.numeroDocumentiNonVisibili==null && other.getNumeroDocumentiNonVisibili()==null) || 
             (this.numeroDocumentiNonVisibili!=null &&
              this.numeroDocumentiNonVisibili.equals(other.getNumeroDocumentiNonVisibili()))) &&
            ((this.estremiFascicoloVirtuale==null && other.getEstremiFascicoloVirtuale()==null) || 
             (this.estremiFascicoloVirtuale!=null &&
              this.estremiFascicoloVirtuale.equals(other.getEstremiFascicoloVirtuale()))) &&
            ((this.estremiFascicolo==null && other.getEstremiFascicolo()==null) || 
             (this.estremiFascicolo!=null &&
              this.estremiFascicolo.equals(other.getEstremiFascicolo()))) &&
            ((this.documentiFascicolo==null && other.getDocumentiFascicolo()==null) || 
             (this.documentiFascicolo!=null &&
              this.documentiFascicolo.equals(other.getDocumentiFascicolo()))) &&
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
        if (getNumeroDocumentiNonVisibili() != null) {
            _hashCode += getNumeroDocumentiNonVisibili().hashCode();
        }
        if (getEstremiFascicoloVirtuale() != null) {
            _hashCode += getEstremiFascicoloVirtuale().hashCode();
        }
        if (getEstremiFascicolo() != null) {
            _hashCode += getEstremiFascicolo().hashCode();
        }
        if (getDocumentiFascicolo() != null) {
            _hashCode += getDocumentiFascicolo().hashCode();
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
        new org.apache.axis.description.TypeDesc(AP3InterrogaFascResponse.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaFascResponse"));
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
        elemField.setFieldName("numeroDocumentiNonVisibili");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroDocumentiNonVisibili"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiFascicoloVirtuale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiFascicoloVirtuale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicoloVirtuale"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiFascicolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiFascicolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicolo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentiFascicolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentiFascicolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumentiFascicolo"));
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
