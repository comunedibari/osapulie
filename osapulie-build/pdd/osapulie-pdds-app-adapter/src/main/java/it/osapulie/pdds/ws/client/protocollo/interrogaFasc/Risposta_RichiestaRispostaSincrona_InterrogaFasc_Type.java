/**
 * Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascResponse AP3InterrogaFascResponse;

    public Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type() {
    }

    public Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type(
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascResponse AP3InterrogaFascResponse) {
           this.AP3InterrogaFascResponse = AP3InterrogaFascResponse;
    }


    /**
     * Gets the AP3InterrogaFascResponse value for this Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type.
     * 
     * @return AP3InterrogaFascResponse
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascResponse getAP3InterrogaFascResponse() {
        return AP3InterrogaFascResponse;
    }


    /**
     * Sets the AP3InterrogaFascResponse value for this Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type.
     * 
     * @param AP3InterrogaFascResponse
     */
    public void setAP3InterrogaFascResponse(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.AP3InterrogaFascResponse AP3InterrogaFascResponse) {
        this.AP3InterrogaFascResponse = AP3InterrogaFascResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type)) return false;
        Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type other = (Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AP3InterrogaFascResponse==null && other.getAP3InterrogaFascResponse()==null) || 
             (this.AP3InterrogaFascResponse!=null &&
              this.AP3InterrogaFascResponse.equals(other.getAP3InterrogaFascResponse())));
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
        if (getAP3InterrogaFascResponse() != null) {
            _hashCode += getAP3InterrogaFascResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Risposta_RichiestaRispostaSincrona_InterrogaFasc_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "risposta_RichiestaRispostaSincrona_InterrogaFasc_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AP3InterrogaFascResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaFascResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaFascResponse"));
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
