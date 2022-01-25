/**
 * InoltraProtocolloInoltroProtocolloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class InoltraProtocolloInoltroProtocolloRequest  implements java.io.Serializable {
    private int numeroProtocollo;

    private java.lang.String anno;

    private java.lang.String destinatarioUser;

    private java.lang.String mittenteUser;

    private java.lang.String tipoInoltro;

    private java.lang.String note;

    public InoltraProtocolloInoltroProtocolloRequest() {
    }

    public InoltraProtocolloInoltroProtocolloRequest(
           int numeroProtocollo,
           java.lang.String anno,
           java.lang.String destinatarioUser,
           java.lang.String mittenteUser,
           java.lang.String tipoInoltro,
           java.lang.String note) {
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
           this.destinatarioUser = destinatarioUser;
           this.mittenteUser = mittenteUser;
           this.tipoInoltro = tipoInoltro;
           this.note = note;
    }


    /**
     * Gets the numeroProtocollo value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return numeroProtocollo
     */
    public int getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(int numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }


    /**
     * Gets the destinatarioUser value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return destinatarioUser
     */
    public java.lang.String getDestinatarioUser() {
        return destinatarioUser;
    }


    /**
     * Sets the destinatarioUser value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param destinatarioUser
     */
    public void setDestinatarioUser(java.lang.String destinatarioUser) {
        this.destinatarioUser = destinatarioUser;
    }


    /**
     * Gets the mittenteUser value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return mittenteUser
     */
    public java.lang.String getMittenteUser() {
        return mittenteUser;
    }


    /**
     * Sets the mittenteUser value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param mittenteUser
     */
    public void setMittenteUser(java.lang.String mittenteUser) {
        this.mittenteUser = mittenteUser;
    }


    /**
     * Gets the tipoInoltro value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return tipoInoltro
     */
    public java.lang.String getTipoInoltro() {
        return tipoInoltro;
    }


    /**
     * Sets the tipoInoltro value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param tipoInoltro
     */
    public void setTipoInoltro(java.lang.String tipoInoltro) {
        this.tipoInoltro = tipoInoltro;
    }


    /**
     * Gets the note value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @return note
     */
    public java.lang.String getNote() {
        return note;
    }


    /**
     * Sets the note value for this InoltraProtocolloInoltroProtocolloRequest.
     * 
     * @param note
     */
    public void setNote(java.lang.String note) {
        this.note = note;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InoltraProtocolloInoltroProtocolloRequest)) return false;
        InoltraProtocolloInoltroProtocolloRequest other = (InoltraProtocolloInoltroProtocolloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.numeroProtocollo == other.getNumeroProtocollo() &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.destinatarioUser==null && other.getDestinatarioUser()==null) || 
             (this.destinatarioUser!=null &&
              this.destinatarioUser.equals(other.getDestinatarioUser()))) &&
            ((this.mittenteUser==null && other.getMittenteUser()==null) || 
             (this.mittenteUser!=null &&
              this.mittenteUser.equals(other.getMittenteUser()))) &&
            ((this.tipoInoltro==null && other.getTipoInoltro()==null) || 
             (this.tipoInoltro!=null &&
              this.tipoInoltro.equals(other.getTipoInoltro()))) &&
            ((this.note==null && other.getNote()==null) || 
             (this.note!=null &&
              this.note.equals(other.getNote())));
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
        _hashCode += getNumeroProtocollo();
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getDestinatarioUser() != null) {
            _hashCode += getDestinatarioUser().hashCode();
        }
        if (getMittenteUser() != null) {
            _hashCode += getMittenteUser().hashCode();
        }
        if (getTipoInoltro() != null) {
            _hashCode += getTipoInoltro().hashCode();
        }
        if (getNote() != null) {
            _hashCode += getNote().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InoltraProtocolloInoltroProtocolloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">inoltraProtocollo>inoltroProtocolloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatarioUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatarioUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mittenteUser");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mittenteUser"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoInoltro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "tipoInoltro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("note");
        elemField.setXmlName(new javax.xml.namespace.QName("", "note"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
