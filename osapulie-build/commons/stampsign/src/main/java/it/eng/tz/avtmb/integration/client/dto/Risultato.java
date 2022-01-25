/**
 * Risultato.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class Risultato  implements java.io.Serializable {
    private org.apache.axis.types.URI iriDownload;

    private java.lang.String downloadFileName;

    private byte[] downloadFileContent;

    public Risultato() {
    }

    public Risultato(
           org.apache.axis.types.URI iriDownload,
           java.lang.String downloadFileName,
           byte[] downloadFileContent) {
           this.iriDownload = iriDownload;
           this.downloadFileName = downloadFileName;
           this.downloadFileContent = downloadFileContent;
    }


    /**
     * Gets the iriDownload value for this Risultato.
     * 
     * @return iriDownload
     */
    public org.apache.axis.types.URI getIriDownload() {
        return iriDownload;
    }


    /**
     * Sets the iriDownload value for this Risultato.
     * 
     * @param iriDownload
     */
    public void setIriDownload(org.apache.axis.types.URI iriDownload) {
        this.iriDownload = iriDownload;
    }


    /**
     * Gets the downloadFileName value for this Risultato.
     * 
     * @return downloadFileName
     */
    public java.lang.String getDownloadFileName() {
        return downloadFileName;
    }


    /**
     * Sets the downloadFileName value for this Risultato.
     * 
     * @param downloadFileName
     */
    public void setDownloadFileName(java.lang.String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }


    /**
     * Gets the downloadFileContent value for this Risultato.
     * 
     * @return downloadFileContent
     */
    public byte[] getDownloadFileContent() {
        return downloadFileContent;
    }


    /**
     * Sets the downloadFileContent value for this Risultato.
     * 
     * @param downloadFileContent
     */
    public void setDownloadFileContent(byte[] downloadFileContent) {
        this.downloadFileContent = downloadFileContent;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Risultato)) return false;
        Risultato other = (Risultato) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.iriDownload==null && other.getIriDownload()==null) || 
             (this.iriDownload!=null &&
              this.iriDownload.equals(other.getIriDownload()))) &&
            ((this.downloadFileName==null && other.getDownloadFileName()==null) || 
             (this.downloadFileName!=null &&
              this.downloadFileName.equals(other.getDownloadFileName()))) &&
            ((this.downloadFileContent==null && other.getDownloadFileContent()==null) || 
             (this.downloadFileContent!=null &&
              java.util.Arrays.equals(this.downloadFileContent, other.getDownloadFileContent())));
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
        if (getIriDownload() != null) {
            _hashCode += getIriDownload().hashCode();
        }
        if (getDownloadFileName() != null) {
            _hashCode += getDownloadFileName().hashCode();
        }
        if (getDownloadFileContent() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDownloadFileContent());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDownloadFileContent(), i);
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
        new org.apache.axis.description.TypeDesc(Risultato.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "risultato"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iriDownload");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "iriDownload"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downloadFileName");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "downloadFileName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("downloadFileContent");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "downloadFileContent"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
