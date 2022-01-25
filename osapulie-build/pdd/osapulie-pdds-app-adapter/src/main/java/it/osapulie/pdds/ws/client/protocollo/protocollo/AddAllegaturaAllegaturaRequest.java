/**
 * AddAllegaturaAllegaturaRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class AddAllegaturaAllegaturaRequest  implements java.io.Serializable {
    private java.lang.String amministrazione;

    private java.lang.String areaOrganizzativaOmogenea;

    private long numeroProtocollo;

    private java.lang.String anno;

    private java.lang.Integer idUtente;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati;

    public AddAllegaturaAllegaturaRequest() {
    }

    public AddAllegaturaAllegaturaRequest(
           java.lang.String amministrazione,
           java.lang.String areaOrganizzativaOmogenea,
           long numeroProtocollo,
           java.lang.String anno,
           java.lang.Integer idUtente,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati) {
           this.amministrazione = amministrazione;
           this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
           this.numeroProtocollo = numeroProtocollo;
           this.anno = anno;
           this.idUtente = idUtente;
           this.allegati = allegati;
    }


    /**
     * Gets the amministrazione value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return amministrazione
     */
    public java.lang.String getAmministrazione() {
        return amministrazione;
    }


    /**
     * Sets the amministrazione value for this AddAllegaturaAllegaturaRequest.
     * 
     * @param amministrazione
     */
    public void setAmministrazione(java.lang.String amministrazione) {
        this.amministrazione = amministrazione;
    }


    /**
     * Gets the areaOrganizzativaOmogenea value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return areaOrganizzativaOmogenea
     */
    public java.lang.String getAreaOrganizzativaOmogenea() {
        return areaOrganizzativaOmogenea;
    }


    /**
     * Sets the areaOrganizzativaOmogenea value for this AddAllegaturaAllegaturaRequest.
     * 
     * @param areaOrganizzativaOmogenea
     */
    public void setAreaOrganizzativaOmogenea(java.lang.String areaOrganizzativaOmogenea) {
        this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
    }


    /**
     * Gets the numeroProtocollo value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return numeroProtocollo
     */
    public long getNumeroProtocollo() {
        return numeroProtocollo;
    }


    /**
     * Sets the numeroProtocollo value for this AddAllegaturaAllegaturaRequest.
     * 
     * @param numeroProtocollo
     */
    public void setNumeroProtocollo(long numeroProtocollo) {
        this.numeroProtocollo = numeroProtocollo;
    }


    /**
     * Gets the anno value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return anno
     */
    public java.lang.String getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this AddAllegaturaAllegaturaRequest.
     * 
     * @param anno
     */
    public void setAnno(java.lang.String anno) {
        this.anno = anno;
    }


    /**
     * Gets the idUtente value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return idUtente
     */
    public java.lang.Integer getIdUtente() {
        return idUtente;
    }


    /**
     * Sets the idUtente value for this AddAllegaturaAllegaturaRequest.
     * 
     * @param idUtente
     */
    public void setIdUtente(java.lang.Integer idUtente) {
        this.idUtente = idUtente;
    }


    /**
     * Gets the allegati value for this AddAllegaturaAllegaturaRequest.
     * 
     * @return allegati
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] getAllegati() {
        return allegati;
    }


    /**
     * Sets the allegati value for this AddAllegaturaAllegaturaRequest.
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

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof AddAllegaturaAllegaturaRequest)) return false;
        AddAllegaturaAllegaturaRequest other = (AddAllegaturaAllegaturaRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.amministrazione==null && other.getAmministrazione()==null) || 
             (this.amministrazione!=null &&
              this.amministrazione.equals(other.getAmministrazione()))) &&
            ((this.areaOrganizzativaOmogenea==null && other.getAreaOrganizzativaOmogenea()==null) || 
             (this.areaOrganizzativaOmogenea!=null &&
              this.areaOrganizzativaOmogenea.equals(other.getAreaOrganizzativaOmogenea()))) &&
            this.numeroProtocollo == other.getNumeroProtocollo() &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.idUtente==null && other.getIdUtente()==null) || 
             (this.idUtente!=null &&
              this.idUtente.equals(other.getIdUtente()))) &&
            ((this.allegati==null && other.getAllegati()==null) || 
             (this.allegati!=null &&
              java.util.Arrays.equals(this.allegati, other.getAllegati())));
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
        if (getAmministrazione() != null) {
            _hashCode += getAmministrazione().hashCode();
        }
        if (getAreaOrganizzativaOmogenea() != null) {
            _hashCode += getAreaOrganizzativaOmogenea().hashCode();
        }
        _hashCode += new Long(getNumeroProtocollo()).hashCode();
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getIdUtente() != null) {
            _hashCode += getIdUtente().hashCode();
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
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(AddAllegaturaAllegaturaRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">addAllegatura>allegaturaRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amministrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaOrganizzativaOmogenea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaOrganizzativaOmogenea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocollo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "numeroProtocollo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "long"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("", "anno"));
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
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegati");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allegati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Allegato"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
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
