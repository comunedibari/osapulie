/**
 * Sign.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class Sign  implements java.io.Serializable {
    private org.apache.axis.types.NormalizedString clientId;

    private org.apache.axis.types.NormalizedString transactionId;

    private org.apache.axis.types.NormalizedString signer;

    private java.lang.String otp;

    private byte[] fileDocumento;

    public Sign() {
    }

    public Sign(
           org.apache.axis.types.NormalizedString clientId,
           org.apache.axis.types.NormalizedString transactionId,
           org.apache.axis.types.NormalizedString signer,
           java.lang.String otp,
           byte[] fileDocumento) {
           this.clientId = clientId;
           this.transactionId = transactionId;
           this.signer = signer;
           this.otp = otp;
           this.fileDocumento = fileDocumento;
    }


    /**
     * Gets the clientId value for this Sign.
     * 
     * @return clientId
     */
    public org.apache.axis.types.NormalizedString getClientId() {
        return clientId;
    }


    /**
     * Sets the clientId value for this Sign.
     * 
     * @param clientId
     */
    public void setClientId(org.apache.axis.types.NormalizedString clientId) {
        this.clientId = clientId;
    }


    /**
     * Gets the transactionId value for this Sign.
     * 
     * @return transactionId
     */
    public org.apache.axis.types.NormalizedString getTransactionId() {
        return transactionId;
    }


    /**
     * Sets the transactionId value for this Sign.
     * 
     * @param transactionId
     */
    public void setTransactionId(org.apache.axis.types.NormalizedString transactionId) {
        this.transactionId = transactionId;
    }


    /**
     * Gets the signer value for this Sign.
     * 
     * @return signer
     */
    public org.apache.axis.types.NormalizedString getSigner() {
        return signer;
    }


    /**
     * Sets the signer value for this Sign.
     * 
     * @param signer
     */
    public void setSigner(org.apache.axis.types.NormalizedString signer) {
        this.signer = signer;
    }


    /**
     * Gets the otp value for this Sign.
     * 
     * @return otp
     */
    public java.lang.String getOtp() {
        return otp;
    }


    /**
     * Sets the otp value for this Sign.
     * 
     * @param otp
     */
    public void setOtp(java.lang.String otp) {
        this.otp = otp;
    }


    /**
     * Gets the fileDocumento value for this Sign.
     * 
     * @return fileDocumento
     */
    public byte[] getFileDocumento() {
        return fileDocumento;
    }


    /**
     * Sets the fileDocumento value for this Sign.
     * 
     * @param fileDocumento
     */
    public void setFileDocumento(byte[] fileDocumento) {
        this.fileDocumento = fileDocumento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Sign)) return false;
        Sign other = (Sign) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.clientId==null && other.getClientId()==null) || 
             (this.clientId!=null &&
              this.clientId.equals(other.getClientId()))) &&
            ((this.transactionId==null && other.getTransactionId()==null) || 
             (this.transactionId!=null &&
              this.transactionId.equals(other.getTransactionId()))) &&
            ((this.signer==null && other.getSigner()==null) || 
             (this.signer!=null &&
              this.signer.equals(other.getSigner()))) &&
            ((this.otp==null && other.getOtp()==null) || 
             (this.otp!=null &&
              this.otp.equals(other.getOtp()))) &&
            ((this.fileDocumento==null && other.getFileDocumento()==null) || 
             (this.fileDocumento!=null &&
              java.util.Arrays.equals(this.fileDocumento, other.getFileDocumento())));
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
        if (getClientId() != null) {
            _hashCode += getClientId().hashCode();
        }
        if (getTransactionId() != null) {
            _hashCode += getTransactionId().hashCode();
        }
        if (getSigner() != null) {
            _hashCode += getSigner().hashCode();
        }
        if (getOtp() != null) {
            _hashCode += getOtp().hashCode();
        }
        if (getFileDocumento() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getFileDocumento());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getFileDocumento(), i);
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
        new org.apache.axis.description.TypeDesc(Sign.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "sign"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("clientId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "clientId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("transactionId");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "transactionId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("signer");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "signer"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("otp");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "otp"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "fileDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
