/**
 * Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocResponse AP3InterrogaDocResponse;

    public Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type() {
    }

    public Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type(
           it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocResponse AP3InterrogaDocResponse) {
           this.AP3InterrogaDocResponse = AP3InterrogaDocResponse;
    }


    /**
     * Gets the AP3InterrogaDocResponse value for this Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type.
     * 
     * @return AP3InterrogaDocResponse
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocResponse getAP3InterrogaDocResponse() {
        return AP3InterrogaDocResponse;
    }


    /**
     * Sets the AP3InterrogaDocResponse value for this Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type.
     * 
     * @param AP3InterrogaDocResponse
     */
    public void setAP3InterrogaDocResponse(it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocResponse AP3InterrogaDocResponse) {
        this.AP3InterrogaDocResponse = AP3InterrogaDocResponse;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type)) return false;
        Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type other = (Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AP3InterrogaDocResponse==null && other.getAP3InterrogaDocResponse()==null) || 
             (this.AP3InterrogaDocResponse!=null &&
              this.AP3InterrogaDocResponse.equals(other.getAP3InterrogaDocResponse())));
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
        if (getAP3InterrogaDocResponse() != null) {
            _hashCode += getAP3InterrogaDocResponse().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Risposta_RichiestaRispostaSincrona_InterrogaDoc_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "risposta_RichiestaRispostaSincrona_InterrogaDoc_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AP3InterrogaDocResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocResponse"));
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
