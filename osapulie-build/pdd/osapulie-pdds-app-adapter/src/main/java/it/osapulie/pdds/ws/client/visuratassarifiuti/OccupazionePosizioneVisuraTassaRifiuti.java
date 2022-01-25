/**
 * OccupazionePosizioneVisuraTassaRifiuti.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuratassarifiuti;

public class OccupazionePosizioneVisuraTassaRifiuti  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.util.Date dataInizioOccupazione;

    private java.util.Date dataFineOccupazione;

    private java.math.BigInteger numeroComponenti;

    private java.lang.Double importoTariffa;

    private java.math.BigInteger idPosizione;

    public OccupazionePosizioneVisuraTassaRifiuti() {
    }

    public OccupazionePosizioneVisuraTassaRifiuti(
           java.math.BigInteger id,
           java.util.Date dataInizioOccupazione,
           java.util.Date dataFineOccupazione,
           java.math.BigInteger numeroComponenti,
           java.lang.Double importoTariffa,
           java.math.BigInteger idPosizione) {
           this.id = id;
           this.dataInizioOccupazione = dataInizioOccupazione;
           this.dataFineOccupazione = dataFineOccupazione;
           this.numeroComponenti = numeroComponenti;
           this.importoTariffa = importoTariffa;
           this.idPosizione = idPosizione;
    }


    /**
     * Gets the id value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the dataInizioOccupazione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return dataInizioOccupazione
     */
    public java.util.Date getDataInizioOccupazione() {
        return dataInizioOccupazione;
    }


    /**
     * Sets the dataInizioOccupazione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param dataInizioOccupazione
     */
    public void setDataInizioOccupazione(java.util.Date dataInizioOccupazione) {
        this.dataInizioOccupazione = dataInizioOccupazione;
    }


    /**
     * Gets the dataFineOccupazione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return dataFineOccupazione
     */
    public java.util.Date getDataFineOccupazione() {
        return dataFineOccupazione;
    }


    /**
     * Sets the dataFineOccupazione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param dataFineOccupazione
     */
    public void setDataFineOccupazione(java.util.Date dataFineOccupazione) {
        this.dataFineOccupazione = dataFineOccupazione;
    }


    /**
     * Gets the numeroComponenti value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return numeroComponenti
     */
    public java.math.BigInteger getNumeroComponenti() {
        return numeroComponenti;
    }


    /**
     * Sets the numeroComponenti value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param numeroComponenti
     */
    public void setNumeroComponenti(java.math.BigInteger numeroComponenti) {
        this.numeroComponenti = numeroComponenti;
    }


    /**
     * Gets the importoTariffa value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return importoTariffa
     */
    public java.lang.Double getImportoTariffa() {
        return importoTariffa;
    }


    /**
     * Sets the importoTariffa value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param importoTariffa
     */
    public void setImportoTariffa(java.lang.Double importoTariffa) {
        this.importoTariffa = importoTariffa;
    }


    /**
     * Gets the idPosizione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @return idPosizione
     */
    public java.math.BigInteger getIdPosizione() {
        return idPosizione;
    }


    /**
     * Sets the idPosizione value for this OccupazionePosizioneVisuraTassaRifiuti.
     * 
     * @param idPosizione
     */
    public void setIdPosizione(java.math.BigInteger idPosizione) {
        this.idPosizione = idPosizione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof OccupazionePosizioneVisuraTassaRifiuti)) return false;
        OccupazionePosizioneVisuraTassaRifiuti other = (OccupazionePosizioneVisuraTassaRifiuti) obj;
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
            ((this.dataInizioOccupazione==null && other.getDataInizioOccupazione()==null) || 
             (this.dataInizioOccupazione!=null &&
              this.dataInizioOccupazione.equals(other.getDataInizioOccupazione()))) &&
            ((this.dataFineOccupazione==null && other.getDataFineOccupazione()==null) || 
             (this.dataFineOccupazione!=null &&
              this.dataFineOccupazione.equals(other.getDataFineOccupazione()))) &&
            ((this.numeroComponenti==null && other.getNumeroComponenti()==null) || 
             (this.numeroComponenti!=null &&
              this.numeroComponenti.equals(other.getNumeroComponenti()))) &&
            ((this.importoTariffa==null && other.getImportoTariffa()==null) || 
             (this.importoTariffa!=null &&
              this.importoTariffa.equals(other.getImportoTariffa()))) &&
            ((this.idPosizione==null && other.getIdPosizione()==null) || 
             (this.idPosizione!=null &&
              this.idPosizione.equals(other.getIdPosizione())));
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
        if (getDataInizioOccupazione() != null) {
            _hashCode += getDataInizioOccupazione().hashCode();
        }
        if (getDataFineOccupazione() != null) {
            _hashCode += getDataFineOccupazione().hashCode();
        }
        if (getNumeroComponenti() != null) {
            _hashCode += getNumeroComponenti().hashCode();
        }
        if (getImportoTariffa() != null) {
            _hashCode += getImportoTariffa().hashCode();
        }
        if (getIdPosizione() != null) {
            _hashCode += getIdPosizione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(OccupazionePosizioneVisuraTassaRifiuti.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "occupazionePosizioneVisuraTassaRifiuti"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataInizioOccupazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataInizioOccupazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineOccupazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataFineOccupazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroComponenti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroComponenti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importoTariffa");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "importoTariffa"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idPosizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idPosizione"));
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
