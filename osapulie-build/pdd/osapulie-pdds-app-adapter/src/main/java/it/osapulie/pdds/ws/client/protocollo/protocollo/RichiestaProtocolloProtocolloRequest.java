/**
 * RichiestaProtocolloProtocolloRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class RichiestaProtocolloProtocolloRequest  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati;

    private java.lang.String areaOrganizzativaOmogenea;

    private java.lang.String amministrazione;

    private java.lang.String oggetto;

    private java.lang.Integer idUtente;

    public RichiestaProtocolloProtocolloRequest() {
    }

    public RichiestaProtocolloProtocolloRequest(
           it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati,
           java.lang.String areaOrganizzativaOmogenea,
           java.lang.String amministrazione,
           java.lang.String oggetto,
           java.lang.Integer idUtente) {
           this.mittente = mittente;
           this.destinatari = destinatari;
           this.documento = documento;
           this.allegati = allegati;
           this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
           this.amministrazione = amministrazione;
           this.oggetto = oggetto;
           this.idUtente = idUtente;
    }


    /**
     * Gets the mittente value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return mittente
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente getMittente() {
        return mittente;
    }


    /**
     * Sets the mittente value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param mittente
     */
    public void setMittente(it.osapulie.pdds.ws.client.protocollo.protocollo.Mittente mittente) {
        this.mittente = mittente;
    }


    /**
     * Gets the destinatari value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return destinatari
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] getDestinatari() {
        return destinatari;
    }


    /**
     * Sets the destinatari value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param destinatari
     */
    public void setDestinatari(it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario[] destinatari) {
        this.destinatari = destinatari;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario getDestinatari(int i) {
        return this.destinatari[i];
    }

    public void setDestinatari(int i, it.osapulie.pdds.ws.client.protocollo.protocollo.Destinatario _value) {
        this.destinatari[i] = _value;
    }


    /**
     * Gets the documento value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return documento
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Documento getDocumento() {
        return documento;
    }


    /**
     * Sets the documento value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param documento
     */
    public void setDocumento(it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento) {
        this.documento = documento;
    }


    /**
     * Gets the allegati value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return allegati
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] getAllegati() {
        return allegati;
    }


    /**
     * Sets the allegati value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param allegati
     */
    public void setAllegati(it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati) {
        this.allegati = allegati;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato getAllegati(int i) {
        return this.allegati[i];
    }

    public void setAllegati(int i, it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato _value) {
        this.allegati[i] = _value;
    }


    /**
     * Gets the areaOrganizzativaOmogenea value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return areaOrganizzativaOmogenea
     */
    public java.lang.String getAreaOrganizzativaOmogenea() {
        return areaOrganizzativaOmogenea;
    }


    /**
     * Sets the areaOrganizzativaOmogenea value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param areaOrganizzativaOmogenea
     */
    public void setAreaOrganizzativaOmogenea(java.lang.String areaOrganizzativaOmogenea) {
        this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
    }


    /**
     * Gets the amministrazione value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return amministrazione
     */
    public java.lang.String getAmministrazione() {
        return amministrazione;
    }


    /**
     * Sets the amministrazione value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param amministrazione
     */
    public void setAmministrazione(java.lang.String amministrazione) {
        this.amministrazione = amministrazione;
    }


    /**
     * Gets the oggetto value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the idUtente value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @return idUtente
     */
    public java.lang.Integer getIdUtente() {
        return idUtente;
    }


    /**
     * Sets the idUtente value for this RichiestaProtocolloProtocolloRequest.
     * 
     * @param idUtente
     */
    public void setIdUtente(java.lang.Integer idUtente) {
        this.idUtente = idUtente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RichiestaProtocolloProtocolloRequest)) return false;
        RichiestaProtocolloProtocolloRequest other = (RichiestaProtocolloProtocolloRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mittente==null && other.getMittente()==null) || 
             (this.mittente!=null &&
              this.mittente.equals(other.getMittente()))) &&
            ((this.destinatari==null && other.getDestinatari()==null) || 
             (this.destinatari!=null &&
              java.util.Arrays.equals(this.destinatari, other.getDestinatari()))) &&
            ((this.documento==null && other.getDocumento()==null) || 
             (this.documento!=null &&
              this.documento.equals(other.getDocumento()))) &&
            ((this.allegati==null && other.getAllegati()==null) || 
             (this.allegati!=null &&
              java.util.Arrays.equals(this.allegati, other.getAllegati()))) &&
            ((this.areaOrganizzativaOmogenea==null && other.getAreaOrganizzativaOmogenea()==null) || 
             (this.areaOrganizzativaOmogenea!=null &&
              this.areaOrganizzativaOmogenea.equals(other.getAreaOrganizzativaOmogenea()))) &&
            ((this.amministrazione==null && other.getAmministrazione()==null) || 
             (this.amministrazione!=null &&
              this.amministrazione.equals(other.getAmministrazione()))) &&
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto()))) &&
            ((this.idUtente==null && other.getIdUtente()==null) || 
             (this.idUtente!=null &&
              this.idUtente.equals(other.getIdUtente())));
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
        if (getMittente() != null) {
            _hashCode += getMittente().hashCode();
        }
        if (getDestinatari() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestinatari());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestinatari(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumento() != null) {
            _hashCode += getDocumento().hashCode();
        }
        if (getAllegati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAllegati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAllegati(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAreaOrganizzativaOmogenea() != null) {
            _hashCode += getAreaOrganizzativaOmogenea().hashCode();
        }
        if (getAmministrazione() != null) {
            _hashCode += getAmministrazione().hashCode();
        }
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        if (getIdUtente() != null) {
            _hashCode += getIdUtente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RichiestaProtocolloProtocolloRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocollo>protocolloRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Mittente"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatari");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatari"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Destinatario"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Documento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegati");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allegati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Allegato"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaOrganizzativaOmogenea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaOrganizzativaOmogenea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amministrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUtente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUtente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
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
