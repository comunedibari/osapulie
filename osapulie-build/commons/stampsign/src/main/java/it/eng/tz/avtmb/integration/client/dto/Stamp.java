/**
 * Stamp.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class Stamp  implements java.io.Serializable {
    private org.apache.axis.types.NormalizedString authority;

    private org.apache.axis.types.NormalizedString identificativoDocumento;

    private it.eng.tz.avtmb.integration.client.dto.ComposizioneDocumento composizioneDocumento;

    private java.util.Calendar dataDocumento;

    private org.apache.axis.types.URI iriAmministrazione;

    private org.apache.axis.types.NormalizedString oggettoDocumento;

    private it.eng.tz.avtmb.integration.client.dto.TipoDocumento tipoDocumento;

    private byte[] fileDocumento;

    private org.apache.axis.types.URI iriDocumento;

    private it.eng.tz.avtmb.integration.client.dto.InfoDoc[] infoDoc;

    private it.eng.tz.avtmb.integration.client.dto.ExtraMeta extraMetaDoc;

    private it.eng.tz.avtmb.integration.client.dto.DocumentInResponseType documentInResponse;

    private it.eng.tz.avtmb.integration.client.dto.SingleStampSizeType singleStampSize;

    public Stamp() {
    }

    public Stamp(
           org.apache.axis.types.NormalizedString authority,
           org.apache.axis.types.NormalizedString identificativoDocumento,
           it.eng.tz.avtmb.integration.client.dto.ComposizioneDocumento composizioneDocumento,
           java.util.Calendar dataDocumento,
           org.apache.axis.types.URI iriAmministrazione,
           org.apache.axis.types.NormalizedString oggettoDocumento,
           it.eng.tz.avtmb.integration.client.dto.TipoDocumento tipoDocumento,
           byte[] fileDocumento,
           org.apache.axis.types.URI iriDocumento,
           it.eng.tz.avtmb.integration.client.dto.InfoDoc[] infoDoc,
           it.eng.tz.avtmb.integration.client.dto.ExtraMeta extraMetaDoc,
           it.eng.tz.avtmb.integration.client.dto.DocumentInResponseType documentInResponse,
           it.eng.tz.avtmb.integration.client.dto.SingleStampSizeType singleStampSize) {
           this.authority = authority;
           this.identificativoDocumento = identificativoDocumento;
           this.composizioneDocumento = composizioneDocumento;
           this.dataDocumento = dataDocumento;
           this.iriAmministrazione = iriAmministrazione;
           this.oggettoDocumento = oggettoDocumento;
           this.tipoDocumento = tipoDocumento;
           this.fileDocumento = fileDocumento;
           this.iriDocumento = iriDocumento;
           this.infoDoc = infoDoc;
           this.extraMetaDoc = extraMetaDoc;
           this.documentInResponse = documentInResponse;
           this.singleStampSize = singleStampSize;
    }


    /**
     * Gets the authority value for this Stamp.
     * 
     * @return authority
     */
    public org.apache.axis.types.NormalizedString getAuthority() {
        return authority;
    }


    /**
     * Sets the authority value for this Stamp.
     * 
     * @param authority
     */
    public void setAuthority(org.apache.axis.types.NormalizedString authority) {
        this.authority = authority;
    }


    /**
     * Gets the identificativoDocumento value for this Stamp.
     * 
     * @return identificativoDocumento
     */
    public org.apache.axis.types.NormalizedString getIdentificativoDocumento() {
        return identificativoDocumento;
    }


    /**
     * Sets the identificativoDocumento value for this Stamp.
     * 
     * @param identificativoDocumento
     */
    public void setIdentificativoDocumento(org.apache.axis.types.NormalizedString identificativoDocumento) {
        this.identificativoDocumento = identificativoDocumento;
    }


    /**
     * Gets the composizioneDocumento value for this Stamp.
     * 
     * @return composizioneDocumento
     */
    public it.eng.tz.avtmb.integration.client.dto.ComposizioneDocumento getComposizioneDocumento() {
        return composizioneDocumento;
    }


    /**
     * Sets the composizioneDocumento value for this Stamp.
     * 
     * @param composizioneDocumento
     */
    public void setComposizioneDocumento(it.eng.tz.avtmb.integration.client.dto.ComposizioneDocumento composizioneDocumento) {
        this.composizioneDocumento = composizioneDocumento;
    }


    /**
     * Gets the dataDocumento value for this Stamp.
     * 
     * @return dataDocumento
     */
    public java.util.Calendar getDataDocumento() {
        return dataDocumento;
    }


    /**
     * Sets the dataDocumento value for this Stamp.
     * 
     * @param dataDocumento
     */
    public void setDataDocumento(java.util.Calendar dataDocumento) {
        this.dataDocumento = dataDocumento;
    }


    /**
     * Gets the iriAmministrazione value for this Stamp.
     * 
     * @return iriAmministrazione
     */
    public org.apache.axis.types.URI getIriAmministrazione() {
        return iriAmministrazione;
    }


    /**
     * Sets the iriAmministrazione value for this Stamp.
     * 
     * @param iriAmministrazione
     */
    public void setIriAmministrazione(org.apache.axis.types.URI iriAmministrazione) {
        this.iriAmministrazione = iriAmministrazione;
    }


    /**
     * Gets the oggettoDocumento value for this Stamp.
     * 
     * @return oggettoDocumento
     */
    public org.apache.axis.types.NormalizedString getOggettoDocumento() {
        return oggettoDocumento;
    }


    /**
     * Sets the oggettoDocumento value for this Stamp.
     * 
     * @param oggettoDocumento
     */
    public void setOggettoDocumento(org.apache.axis.types.NormalizedString oggettoDocumento) {
        this.oggettoDocumento = oggettoDocumento;
    }


    /**
     * Gets the tipoDocumento value for this Stamp.
     * 
     * @return tipoDocumento
     */
    public it.eng.tz.avtmb.integration.client.dto.TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }


    /**
     * Sets the tipoDocumento value for this Stamp.
     * 
     * @param tipoDocumento
     */
    public void setTipoDocumento(it.eng.tz.avtmb.integration.client.dto.TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    /**
     * Gets the fileDocumento value for this Stamp.
     * 
     * @return fileDocumento
     */
    public byte[] getFileDocumento() {
        return fileDocumento;
    }


    /**
     * Sets the fileDocumento value for this Stamp.
     * 
     * @param fileDocumento
     */
    public void setFileDocumento(byte[] fileDocumento) {
        this.fileDocumento = fileDocumento;
    }


    /**
     * Gets the iriDocumento value for this Stamp.
     * 
     * @return iriDocumento
     */
    public org.apache.axis.types.URI getIriDocumento() {
        return iriDocumento;
    }


    /**
     * Sets the iriDocumento value for this Stamp.
     * 
     * @param iriDocumento
     */
    public void setIriDocumento(org.apache.axis.types.URI iriDocumento) {
        this.iriDocumento = iriDocumento;
    }


    /**
     * Gets the infoDoc value for this Stamp.
     * 
     * @return infoDoc
     */
    public it.eng.tz.avtmb.integration.client.dto.InfoDoc[] getInfoDoc() {
        return infoDoc;
    }


    /**
     * Sets the infoDoc value for this Stamp.
     * 
     * @param infoDoc
     */
    public void setInfoDoc(it.eng.tz.avtmb.integration.client.dto.InfoDoc[] infoDoc) {
        this.infoDoc = infoDoc;
    }

    public it.eng.tz.avtmb.integration.client.dto.InfoDoc getInfoDoc(int i) {
        return this.infoDoc[i];
    }

    public void setInfoDoc(int i, it.eng.tz.avtmb.integration.client.dto.InfoDoc _value) {
        this.infoDoc[i] = _value;
    }


    /**
     * Gets the extraMetaDoc value for this Stamp.
     * 
     * @return extraMetaDoc
     */
    public it.eng.tz.avtmb.integration.client.dto.ExtraMeta getExtraMetaDoc() {
        return extraMetaDoc;
    }


    /**
     * Sets the extraMetaDoc value for this Stamp.
     * 
     * @param extraMetaDoc
     */
    public void setExtraMetaDoc(it.eng.tz.avtmb.integration.client.dto.ExtraMeta extraMetaDoc) {
        this.extraMetaDoc = extraMetaDoc;
    }


    /**
     * Gets the documentInResponse value for this Stamp.
     * 
     * @return documentInResponse
     */
    public it.eng.tz.avtmb.integration.client.dto.DocumentInResponseType getDocumentInResponse() {
        return documentInResponse;
    }


    /**
     * Sets the documentInResponse value for this Stamp.
     * 
     * @param documentInResponse
     */
    public void setDocumentInResponse(it.eng.tz.avtmb.integration.client.dto.DocumentInResponseType documentInResponse) {
        this.documentInResponse = documentInResponse;
    }


    /**
     * Gets the singleStampSize value for this Stamp.
     * 
     * @return singleStampSize
     */
    public it.eng.tz.avtmb.integration.client.dto.SingleStampSizeType getSingleStampSize() {
        return singleStampSize;
    }


    /**
     * Sets the singleStampSize value for this Stamp.
     * 
     * @param singleStampSize
     */
    public void setSingleStampSize(it.eng.tz.avtmb.integration.client.dto.SingleStampSizeType singleStampSize) {
        this.singleStampSize = singleStampSize;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Stamp)) return false;
        Stamp other = (Stamp) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.authority==null && other.getAuthority()==null) || 
             (this.authority!=null &&
              this.authority.equals(other.getAuthority()))) &&
            ((this.identificativoDocumento==null && other.getIdentificativoDocumento()==null) || 
             (this.identificativoDocumento!=null &&
              this.identificativoDocumento.equals(other.getIdentificativoDocumento()))) &&
            ((this.composizioneDocumento==null && other.getComposizioneDocumento()==null) || 
             (this.composizioneDocumento!=null &&
              this.composizioneDocumento.equals(other.getComposizioneDocumento()))) &&
            ((this.dataDocumento==null && other.getDataDocumento()==null) || 
             (this.dataDocumento!=null &&
              this.dataDocumento.equals(other.getDataDocumento()))) &&
            ((this.iriAmministrazione==null && other.getIriAmministrazione()==null) || 
             (this.iriAmministrazione!=null &&
              this.iriAmministrazione.equals(other.getIriAmministrazione()))) &&
            ((this.oggettoDocumento==null && other.getOggettoDocumento()==null) || 
             (this.oggettoDocumento!=null &&
              this.oggettoDocumento.equals(other.getOggettoDocumento()))) &&
            ((this.tipoDocumento==null && other.getTipoDocumento()==null) || 
             (this.tipoDocumento!=null &&
              this.tipoDocumento.equals(other.getTipoDocumento()))) &&
            ((this.fileDocumento==null && other.getFileDocumento()==null) || 
             (this.fileDocumento!=null &&
              java.util.Arrays.equals(this.fileDocumento, other.getFileDocumento()))) &&
            ((this.iriDocumento==null && other.getIriDocumento()==null) || 
             (this.iriDocumento!=null &&
              this.iriDocumento.equals(other.getIriDocumento()))) &&
            ((this.infoDoc==null && other.getInfoDoc()==null) || 
             (this.infoDoc!=null &&
              java.util.Arrays.equals(this.infoDoc, other.getInfoDoc()))) &&
            ((this.extraMetaDoc==null && other.getExtraMetaDoc()==null) || 
             (this.extraMetaDoc!=null &&
              this.extraMetaDoc.equals(other.getExtraMetaDoc()))) &&
            ((this.documentInResponse==null && other.getDocumentInResponse()==null) || 
             (this.documentInResponse!=null &&
              this.documentInResponse.equals(other.getDocumentInResponse()))) &&
            ((this.singleStampSize==null && other.getSingleStampSize()==null) || 
             (this.singleStampSize!=null &&
              this.singleStampSize.equals(other.getSingleStampSize())));
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
        if (getAuthority() != null) {
            _hashCode += getAuthority().hashCode();
        }
        if (getIdentificativoDocumento() != null) {
            _hashCode += getIdentificativoDocumento().hashCode();
        }
        if (getComposizioneDocumento() != null) {
            _hashCode += getComposizioneDocumento().hashCode();
        }
        if (getDataDocumento() != null) {
            _hashCode += getDataDocumento().hashCode();
        }
        if (getIriAmministrazione() != null) {
            _hashCode += getIriAmministrazione().hashCode();
        }
        if (getOggettoDocumento() != null) {
            _hashCode += getOggettoDocumento().hashCode();
        }
        if (getTipoDocumento() != null) {
            _hashCode += getTipoDocumento().hashCode();
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
        if (getIriDocumento() != null) {
            _hashCode += getIriDocumento().hashCode();
        }
        if (getInfoDoc() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getInfoDoc());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getInfoDoc(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getExtraMetaDoc() != null) {
            _hashCode += getExtraMetaDoc().hashCode();
        }
        if (getDocumentInResponse() != null) {
            _hashCode += getDocumentInResponse().hashCode();
        }
        if (getSingleStampSize() != null) {
            _hashCode += getSingleStampSize().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Stamp.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "stamp"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("authority");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "authority"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "identificativoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("composizioneDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "composizioneDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "composizioneDocumento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "dataDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iriAmministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "iriAmministrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggettoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "oggettoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "tipoDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "tipoDocumento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fileDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "fileDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iriDocumento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "iriDocumento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("infoDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "infoDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "infoDoc"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("extraMetaDoc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "extraMetaDoc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "extraMeta"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentInResponse");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "documentInResponse"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "documentInResponseType"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("singleStampSize");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "singleStampSize"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "singleStampSizeType"));
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
