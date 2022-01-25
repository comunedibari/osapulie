/**
 * Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaDoc;

public class Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest AP3InterrogaDocRequest;

    public Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type() {
    }

    public Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type(
           it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest AP3InterrogaDocRequest) {
           this.AP3InterrogaDocRequest = AP3InterrogaDocRequest;
    }


    /**
     * Gets the AP3InterrogaDocRequest value for this Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type.
     * 
     * @return AP3InterrogaDocRequest
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest getAP3InterrogaDocRequest() {
        return AP3InterrogaDocRequest;
    }


    /**
     * Sets the AP3InterrogaDocRequest value for this Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type.
     * 
     * @param AP3InterrogaDocRequest
     */
    public void setAP3InterrogaDocRequest(it.osapulie.pdds.ws.client.protocollo.interrogaDoc.AP3InterrogaDocRequest AP3InterrogaDocRequest) {
        this.AP3InterrogaDocRequest = AP3InterrogaDocRequest;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type)) return false;
        Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type other = (Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.AP3InterrogaDocRequest==null && other.getAP3InterrogaDocRequest()==null) || 
             (this.AP3InterrogaDocRequest!=null &&
              this.AP3InterrogaDocRequest.equals(other.getAP3InterrogaDocRequest())));
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
        if (getAP3InterrogaDocRequest() != null) {
            _hashCode += getAP3InterrogaDocRequest().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "richiesta_RichiestaRispostaSincrona_InterrogaDoc_Type"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("AP3InterrogaDocRequest");
        elemField.setXmlName(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocRequest"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "AP3InterrogaDocRequest"));
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
