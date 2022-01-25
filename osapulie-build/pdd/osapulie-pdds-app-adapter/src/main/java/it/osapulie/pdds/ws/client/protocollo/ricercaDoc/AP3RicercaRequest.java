/**
 * AP3RicercaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class AP3RicercaRequest  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceRichiedente;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceInterrogato;

    private java.util.Date dataDa;

    private java.util.Date dataA;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloDest;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloMitt;

    private java.lang.String oggetto;

    public AP3RicercaRequest() {
    }

    public AP3RicercaRequest(
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceRichiedente,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceInterrogato,
           java.util.Date dataDa,
           java.util.Date dataA,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloDest,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloMitt,
           java.lang.String oggetto) {
           this.codiceRichiedente = codiceRichiedente;
           this.codiceInterrogato = codiceInterrogato;
           this.dataDa = dataDa;
           this.dataA = dataA;
           this.estremiProtocolloDest = estremiProtocolloDest;
           this.estremiProtocolloMitt = estremiProtocolloMitt;
           this.oggetto = oggetto;
    }


    /**
     * Gets the codiceRichiedente value for this AP3RicercaRequest.
     * 
     * @return codiceRichiedente
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea getCodiceRichiedente() {
        return codiceRichiedente;
    }


    /**
     * Sets the codiceRichiedente value for this AP3RicercaRequest.
     * 
     * @param codiceRichiedente
     */
    public void setCodiceRichiedente(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceRichiedente) {
        this.codiceRichiedente = codiceRichiedente;
    }


    /**
     * Gets the codiceInterrogato value for this AP3RicercaRequest.
     * 
     * @return codiceInterrogato
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea getCodiceInterrogato() {
        return codiceInterrogato;
    }


    /**
     * Sets the codiceInterrogato value for this AP3RicercaRequest.
     * 
     * @param codiceInterrogato
     */
    public void setCodiceInterrogato(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.CodiceArea codiceInterrogato) {
        this.codiceInterrogato = codiceInterrogato;
    }


    /**
     * Gets the dataDa value for this AP3RicercaRequest.
     * 
     * @return dataDa
     */
    public java.util.Date getDataDa() {
        return dataDa;
    }


    /**
     * Sets the dataDa value for this AP3RicercaRequest.
     * 
     * @param dataDa
     */
    public void setDataDa(java.util.Date dataDa) {
        this.dataDa = dataDa;
    }


    /**
     * Gets the dataA value for this AP3RicercaRequest.
     * 
     * @return dataA
     */
    public java.util.Date getDataA() {
        return dataA;
    }


    /**
     * Sets the dataA value for this AP3RicercaRequest.
     * 
     * @param dataA
     */
    public void setDataA(java.util.Date dataA) {
        this.dataA = dataA;
    }


    /**
     * Gets the estremiProtocolloDest value for this AP3RicercaRequest.
     * 
     * @return estremiProtocolloDest
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo getEstremiProtocolloDest() {
        return estremiProtocolloDest;
    }


    /**
     * Sets the estremiProtocolloDest value for this AP3RicercaRequest.
     * 
     * @param estremiProtocolloDest
     */
    public void setEstremiProtocolloDest(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloDest) {
        this.estremiProtocolloDest = estremiProtocolloDest;
    }


    /**
     * Gets the estremiProtocolloMitt value for this AP3RicercaRequest.
     * 
     * @return estremiProtocolloMitt
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo getEstremiProtocolloMitt() {
        return estremiProtocolloMitt;
    }


    /**
     * Sets the estremiProtocolloMitt value for this AP3RicercaRequest.
     * 
     * @param estremiProtocolloMitt
     */
    public void setEstremiProtocolloMitt(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocollo estremiProtocolloMitt) {
        this.estremiProtocolloMitt = estremiProtocolloMitt;
    }


    /**
     * Gets the oggetto value for this AP3RicercaRequest.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this AP3RicercaRequest.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3RicercaRequest)) return false;
        AP3RicercaRequest other = (AP3RicercaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceRichiedente==null && other.getCodiceRichiedente()==null) || 
             (this.codiceRichiedente!=null &&
              this.codiceRichiedente.equals(other.getCodiceRichiedente()))) &&
            ((this.codiceInterrogato==null && other.getCodiceInterrogato()==null) || 
             (this.codiceInterrogato!=null &&
              this.codiceInterrogato.equals(other.getCodiceInterrogato()))) &&
            ((this.dataDa==null && other.getDataDa()==null) || 
             (this.dataDa!=null &&
              this.dataDa.equals(other.getDataDa()))) &&
            ((this.dataA==null && other.getDataA()==null) || 
             (this.dataA!=null &&
              this.dataA.equals(other.getDataA()))) &&
            ((this.estremiProtocolloDest==null && other.getEstremiProtocolloDest()==null) || 
             (this.estremiProtocolloDest!=null &&
              this.estremiProtocolloDest.equals(other.getEstremiProtocolloDest()))) &&
            ((this.estremiProtocolloMitt==null && other.getEstremiProtocolloMitt()==null) || 
             (this.estremiProtocolloMitt!=null &&
              this.estremiProtocolloMitt.equals(other.getEstremiProtocolloMitt()))) &&
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto())));
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
        if (getCodiceRichiedente() != null) {
            _hashCode += getCodiceRichiedente().hashCode();
        }
        if (getCodiceInterrogato() != null) {
            _hashCode += getCodiceInterrogato().hashCode();
        }
        if (getDataDa() != null) {
            _hashCode += getDataDa().hashCode();
        }
        if (getDataA() != null) {
            _hashCode += getDataA().hashCode();
        }
        if (getEstremiProtocolloDest() != null) {
            _hashCode += getEstremiProtocolloDest().hashCode();
        }
        if (getEstremiProtocolloMitt() != null) {
            _hashCode += getEstremiProtocolloMitt().hashCode();
        }
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AP3RicercaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3RicercaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceRichiedente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceRichiedente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceInterrogato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceInterrogato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "codiceArea"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDa");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataDa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataA");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataA"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiProtocolloDest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocolloDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiProtocolloMitt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocolloMitt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
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
