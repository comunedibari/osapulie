/**
 * PosizioneVisuraOsapTemporanea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraosaptemporanea;

public class PosizioneVisuraOsapTemporanea  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String indirizzoUtenza;

    private java.lang.Double superficie;

    private java.lang.String zona;

    private java.lang.String descrizioneTariffa;

    private java.math.BigInteger durataOccupazione;

    private java.lang.Double importoDaPagare;

    private java.lang.Double importoPagato;

    private java.util.Date dataPagamento;

    private java.math.BigInteger idVisura;

    public PosizioneVisuraOsapTemporanea() {
    }

    public PosizioneVisuraOsapTemporanea(
           java.math.BigInteger id,
           java.lang.String indirizzoUtenza,
           java.lang.Double superficie,
           java.lang.String zona,
           java.lang.String descrizioneTariffa,
           java.math.BigInteger durataOccupazione,
           java.lang.Double importoDaPagare,
           java.lang.Double importoPagato,
           java.util.Date dataPagamento,
           java.math.BigInteger idVisura) {
           this.id = id;
           this.indirizzoUtenza = indirizzoUtenza;
           this.superficie = superficie;
           this.zona = zona;
           this.descrizioneTariffa = descrizioneTariffa;
           this.durataOccupazione = durataOccupazione;
           this.importoDaPagare = importoDaPagare;
           this.importoPagato = importoPagato;
           this.dataPagamento = dataPagamento;
           this.idVisura = idVisura;
    }


    /**
     * Gets the id value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the indirizzoUtenza value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return indirizzoUtenza
     */
    public java.lang.String getIndirizzoUtenza() {
        return indirizzoUtenza;
    }


    /**
     * Sets the indirizzoUtenza value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param indirizzoUtenza
     */
    public void setIndirizzoUtenza(java.lang.String indirizzoUtenza) {
        this.indirizzoUtenza = indirizzoUtenza;
    }


    /**
     * Gets the superficie value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return superficie
     */
    public java.lang.Double getSuperficie() {
        return superficie;
    }


    /**
     * Sets the superficie value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param superficie
     */
    public void setSuperficie(java.lang.Double superficie) {
        this.superficie = superficie;
    }


    /**
     * Gets the zona value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return zona
     */
    public java.lang.String getZona() {
        return zona;
    }


    /**
     * Sets the zona value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param zona
     */
    public void setZona(java.lang.String zona) {
        this.zona = zona;
    }


    /**
     * Gets the descrizioneTariffa value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return descrizioneTariffa
     */
    public java.lang.String getDescrizioneTariffa() {
        return descrizioneTariffa;
    }


    /**
     * Sets the descrizioneTariffa value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param descrizioneTariffa
     */
    public void setDescrizioneTariffa(java.lang.String descrizioneTariffa) {
        this.descrizioneTariffa = descrizioneTariffa;
    }


    /**
     * Gets the durataOccupazione value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return durataOccupazione
     */
    public java.math.BigInteger getDurataOccupazione() {
        return durataOccupazione;
    }


    /**
     * Sets the durataOccupazione value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param durataOccupazione
     */
    public void setDurataOccupazione(java.math.BigInteger durataOccupazione) {
        this.durataOccupazione = durataOccupazione;
    }


    /**
     * Gets the importoDaPagare value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return importoDaPagare
     */
    public java.lang.Double getImportoDaPagare() {
        return importoDaPagare;
    }


    /**
     * Sets the importoDaPagare value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param importoDaPagare
     */
    public void setImportoDaPagare(java.lang.Double importoDaPagare) {
        this.importoDaPagare = importoDaPagare;
    }


    /**
     * Gets the importoPagato value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return importoPagato
     */
    public java.lang.Double getImportoPagato() {
        return importoPagato;
    }


    /**
     * Sets the importoPagato value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param importoPagato
     */
    public void setImportoPagato(java.lang.Double importoPagato) {
        this.importoPagato = importoPagato;
    }


    /**
     * Gets the dataPagamento value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return dataPagamento
     */
    public java.util.Date getDataPagamento() {
        return dataPagamento;
    }


    /**
     * Sets the dataPagamento value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param dataPagamento
     */
    public void setDataPagamento(java.util.Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }


    /**
     * Gets the idVisura value for this PosizioneVisuraOsapTemporanea.
     * 
     * @return idVisura
     */
    public java.math.BigInteger getIdVisura() {
        return idVisura;
    }


    /**
     * Sets the idVisura value for this PosizioneVisuraOsapTemporanea.
     * 
     * @param idVisura
     */
    public void setIdVisura(java.math.BigInteger idVisura) {
        this.idVisura = idVisura;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PosizioneVisuraOsapTemporanea)) return false;
        PosizioneVisuraOsapTemporanea other = (PosizioneVisuraOsapTemporanea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.indirizzoUtenza==null && other.getIndirizzoUtenza()==null) || 
             (this.indirizzoUtenza!=null &&
              this.indirizzoUtenza.equals(other.getIndirizzoUtenza()))) &&
            ((this.superficie==null && other.getSuperficie()==null) || 
             (this.superficie!=null &&
              this.superficie.equals(other.getSuperficie()))) &&
            ((this.zona==null && other.getZona()==null) || 
             (this.zona!=null &&
              this.zona.equals(other.getZona()))) &&
            ((this.descrizioneTariffa==null && other.getDescrizioneTariffa()==null) || 
             (this.descrizioneTariffa!=null &&
              this.descrizioneTariffa.equals(other.getDescrizioneTariffa()))) &&
            ((this.durataOccupazione==null && other.getDurataOccupazione()==null) || 
             (this.durataOccupazione!=null &&
              this.durataOccupazione.equals(other.getDurataOccupazione()))) &&
            ((this.importoDaPagare==null && other.getImportoDaPagare()==null) || 
             (this.importoDaPagare!=null &&
              this.importoDaPagare.equals(other.getImportoDaPagare()))) &&
            ((this.importoPagato==null && other.getImportoPagato()==null) || 
             (this.importoPagato!=null &&
              this.importoPagato.equals(other.getImportoPagato()))) &&
            ((this.dataPagamento==null && other.getDataPagamento()==null) || 
             (this.dataPagamento!=null &&
              this.dataPagamento.equals(other.getDataPagamento()))) &&
            ((this.idVisura==null && other.getIdVisura()==null) || 
             (this.idVisura!=null &&
              this.idVisura.equals(other.getIdVisura())));
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
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getIndirizzoUtenza() != null) {
            _hashCode += getIndirizzoUtenza().hashCode();
        }
        if (getSuperficie() != null) {
            _hashCode += getSuperficie().hashCode();
        }
        if (getZona() != null) {
            _hashCode += getZona().hashCode();
        }
        if (getDescrizioneTariffa() != null) {
            _hashCode += getDescrizioneTariffa().hashCode();
        }
        if (getDurataOccupazione() != null) {
            _hashCode += getDurataOccupazione().hashCode();
        }
        if (getImportoDaPagare() != null) {
            _hashCode += getImportoDaPagare().hashCode();
        }
        if (getImportoPagato() != null) {
            _hashCode += getImportoPagato().hashCode();
        }
        if (getDataPagamento() != null) {
            _hashCode += getDataPagamento().hashCode();
        }
        if (getIdVisura() != null) {
            _hashCode += getIdVisura().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PosizioneVisuraOsapTemporanea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "posizioneVisuraOsapTemporanea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("indirizzoUtenza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "indirizzoUtenza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("superficie");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "superficie"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("zona");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "zona"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneTariffa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneTariffa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("durataOccupazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "durataOccupazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoDaPagare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoDaPagare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoPagato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoPagato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataPagamento");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataPagamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idVisura");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idVisura"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
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
