/**
 * AP3InterrogaFascRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class AP3InterrogaFascRequest  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceRichiedente;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceInterrogato;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloMitt;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloDest;

    public AP3InterrogaFascRequest() {
    }

    public AP3InterrogaFascRequest(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceRichiedente,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceInterrogato,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloMitt,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloDest) {
           this.codiceRichiedente = codiceRichiedente;
           this.codiceInterrogato = codiceInterrogato;
           this.estremiProtocolloMitt = estremiProtocolloMitt;
           this.estremiProtocolloDest = estremiProtocolloDest;
    }


    /**
     * Gets the codiceRichiedente value for this AP3InterrogaFascRequest.
     * 
     * @return codiceRichiedente
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea getCodiceRichiedente() {
        return codiceRichiedente;
    }


    /**
     * Sets the codiceRichiedente value for this AP3InterrogaFascRequest.
     * 
     * @param codiceRichiedente
     */
    public void setCodiceRichiedente(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceRichiedente) {
        this.codiceRichiedente = codiceRichiedente;
    }


    /**
     * Gets the codiceInterrogato value for this AP3InterrogaFascRequest.
     * 
     * @return codiceInterrogato
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea getCodiceInterrogato() {
        return codiceInterrogato;
    }


    /**
     * Sets the codiceInterrogato value for this AP3InterrogaFascRequest.
     * 
     * @param codiceInterrogato
     */
    public void setCodiceInterrogato(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.CodiceArea codiceInterrogato) {
        this.codiceInterrogato = codiceInterrogato;
    }


    /**
     * Gets the estremiProtocolloMitt value for this AP3InterrogaFascRequest.
     * 
     * @return estremiProtocolloMitt
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo getEstremiProtocolloMitt() {
        return estremiProtocolloMitt;
    }


    /**
     * Sets the estremiProtocolloMitt value for this AP3InterrogaFascRequest.
     * 
     * @param estremiProtocolloMitt
     */
    public void setEstremiProtocolloMitt(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloMitt) {
        this.estremiProtocolloMitt = estremiProtocolloMitt;
    }


    /**
     * Gets the estremiProtocolloDest value for this AP3InterrogaFascRequest.
     * 
     * @return estremiProtocolloDest
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo getEstremiProtocolloDest() {
        return estremiProtocolloDest;
    }


    /**
     * Sets the estremiProtocolloDest value for this AP3InterrogaFascRequest.
     * 
     * @param estremiProtocolloDest
     */
    public void setEstremiProtocolloDest(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocollo estremiProtocolloDest) {
        this.estremiProtocolloDest = estremiProtocolloDest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AP3InterrogaFascRequest)) return false;
        AP3InterrogaFascRequest other = (AP3InterrogaFascRequest) obj;
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
            ((this.estremiProtocolloMitt==null && other.getEstremiProtocolloMitt()==null) || 
             (this.estremiProtocolloMitt!=null &&
              this.estremiProtocolloMitt.equals(other.getEstremiProtocolloMitt()))) &&
            ((this.estremiProtocolloDest==null && other.getEstremiProtocolloDest()==null) || 
             (this.estremiProtocolloDest!=null &&
              this.estremiProtocolloDest.equals(other.getEstremiProtocolloDest())));
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
        if (getEstremiProtocolloMitt() != null) {
            _hashCode += getEstremiProtocolloMitt().hashCode();
        }
        if (getEstremiProtocolloDest() != null) {
            _hashCode += getEstremiProtocolloDest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AP3InterrogaFascRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaFascRequest"));
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
        elemField.setFieldName("estremiProtocolloMitt");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocolloMitt"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiProtocolloDest");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocolloDest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocollo"));
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
