/**
 * ExtraMeta.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class ExtraMeta  implements java.io.Serializable {
    private java.lang.String titoloAmministrazioneProdotto;

    private org.apache.axis.types.URI iriAmministrazioneConservato;

    private java.lang.String titoloAmministrazioneConservato;

    private java.lang.String modalitaVerifica;

    private java.util.Calendar dataFineValidita;

    private java.util.Calendar dataFineVerifica;

    private java.util.Calendar dataProtocolloRicevuto;

    private org.apache.axis.types.NormalizedString numeroProtocolloRicevuto;

    private it.eng.tz.avtmb.integration.client.dto.PosizioneTimbro posizioneTimbro;

    public ExtraMeta() {
    }

    public ExtraMeta(
           java.lang.String titoloAmministrazioneProdotto,
           org.apache.axis.types.URI iriAmministrazioneConservato,
           java.lang.String titoloAmministrazioneConservato,
           java.lang.String modalitaVerifica,
           java.util.Calendar dataFineValidita,
           java.util.Calendar dataFineVerifica,
           java.util.Calendar dataProtocolloRicevuto,
           org.apache.axis.types.NormalizedString numeroProtocolloRicevuto,
           it.eng.tz.avtmb.integration.client.dto.PosizioneTimbro posizioneTimbro) {
           this.titoloAmministrazioneProdotto = titoloAmministrazioneProdotto;
           this.iriAmministrazioneConservato = iriAmministrazioneConservato;
           this.titoloAmministrazioneConservato = titoloAmministrazioneConservato;
           this.modalitaVerifica = modalitaVerifica;
           this.dataFineValidita = dataFineValidita;
           this.dataFineVerifica = dataFineVerifica;
           this.dataProtocolloRicevuto = dataProtocolloRicevuto;
           this.numeroProtocolloRicevuto = numeroProtocolloRicevuto;
           this.posizioneTimbro = posizioneTimbro;
    }


    /**
     * Gets the titoloAmministrazioneProdotto value for this ExtraMeta.
     * 
     * @return titoloAmministrazioneProdotto
     */
    public java.lang.String getTitoloAmministrazioneProdotto() {
        return titoloAmministrazioneProdotto;
    }


    /**
     * Sets the titoloAmministrazioneProdotto value for this ExtraMeta.
     * 
     * @param titoloAmministrazioneProdotto
     */
    public void setTitoloAmministrazioneProdotto(java.lang.String titoloAmministrazioneProdotto) {
        this.titoloAmministrazioneProdotto = titoloAmministrazioneProdotto;
    }


    /**
     * Gets the iriAmministrazioneConservato value for this ExtraMeta.
     * 
     * @return iriAmministrazioneConservato
     */
    public org.apache.axis.types.URI getIriAmministrazioneConservato() {
        return iriAmministrazioneConservato;
    }


    /**
     * Sets the iriAmministrazioneConservato value for this ExtraMeta.
     * 
     * @param iriAmministrazioneConservato
     */
    public void setIriAmministrazioneConservato(org.apache.axis.types.URI iriAmministrazioneConservato) {
        this.iriAmministrazioneConservato = iriAmministrazioneConservato;
    }


    /**
     * Gets the titoloAmministrazioneConservato value for this ExtraMeta.
     * 
     * @return titoloAmministrazioneConservato
     */
    public java.lang.String getTitoloAmministrazioneConservato() {
        return titoloAmministrazioneConservato;
    }


    /**
     * Sets the titoloAmministrazioneConservato value for this ExtraMeta.
     * 
     * @param titoloAmministrazioneConservato
     */
    public void setTitoloAmministrazioneConservato(java.lang.String titoloAmministrazioneConservato) {
        this.titoloAmministrazioneConservato = titoloAmministrazioneConservato;
    }


    /**
     * Gets the modalitaVerifica value for this ExtraMeta.
     * 
     * @return modalitaVerifica
     */
    public java.lang.String getModalitaVerifica() {
        return modalitaVerifica;
    }


    /**
     * Sets the modalitaVerifica value for this ExtraMeta.
     * 
     * @param modalitaVerifica
     */
    public void setModalitaVerifica(java.lang.String modalitaVerifica) {
        this.modalitaVerifica = modalitaVerifica;
    }


    /**
     * Gets the dataFineValidita value for this ExtraMeta.
     * 
     * @return dataFineValidita
     */
    public java.util.Calendar getDataFineValidita() {
        return dataFineValidita;
    }


    /**
     * Sets the dataFineValidita value for this ExtraMeta.
     * 
     * @param dataFineValidita
     */
    public void setDataFineValidita(java.util.Calendar dataFineValidita) {
        this.dataFineValidita = dataFineValidita;
    }


    /**
     * Gets the dataFineVerifica value for this ExtraMeta.
     * 
     * @return dataFineVerifica
     */
    public java.util.Calendar getDataFineVerifica() {
        return dataFineVerifica;
    }


    /**
     * Sets the dataFineVerifica value for this ExtraMeta.
     * 
     * @param dataFineVerifica
     */
    public void setDataFineVerifica(java.util.Calendar dataFineVerifica) {
        this.dataFineVerifica = dataFineVerifica;
    }


    /**
     * Gets the dataProtocolloRicevuto value for this ExtraMeta.
     * 
     * @return dataProtocolloRicevuto
     */
    public java.util.Calendar getDataProtocolloRicevuto() {
        return dataProtocolloRicevuto;
    }


    /**
     * Sets the dataProtocolloRicevuto value for this ExtraMeta.
     * 
     * @param dataProtocolloRicevuto
     */
    public void setDataProtocolloRicevuto(java.util.Calendar dataProtocolloRicevuto) {
        this.dataProtocolloRicevuto = dataProtocolloRicevuto;
    }


    /**
     * Gets the numeroProtocolloRicevuto value for this ExtraMeta.
     * 
     * @return numeroProtocolloRicevuto
     */
    public org.apache.axis.types.NormalizedString getNumeroProtocolloRicevuto() {
        return numeroProtocolloRicevuto;
    }


    /**
     * Sets the numeroProtocolloRicevuto value for this ExtraMeta.
     * 
     * @param numeroProtocolloRicevuto
     */
    public void setNumeroProtocolloRicevuto(org.apache.axis.types.NormalizedString numeroProtocolloRicevuto) {
        this.numeroProtocolloRicevuto = numeroProtocolloRicevuto;
    }


    /**
     * Gets the posizioneTimbro value for this ExtraMeta.
     * 
     * @return posizioneTimbro
     */
    public it.eng.tz.avtmb.integration.client.dto.PosizioneTimbro getPosizioneTimbro() {
        return posizioneTimbro;
    }


    /**
     * Sets the posizioneTimbro value for this ExtraMeta.
     * 
     * @param posizioneTimbro
     */
    public void setPosizioneTimbro(it.eng.tz.avtmb.integration.client.dto.PosizioneTimbro posizioneTimbro) {
        this.posizioneTimbro = posizioneTimbro;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ExtraMeta)) return false;
        ExtraMeta other = (ExtraMeta) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.titoloAmministrazioneProdotto==null && other.getTitoloAmministrazioneProdotto()==null) || 
             (this.titoloAmministrazioneProdotto!=null &&
              this.titoloAmministrazioneProdotto.equals(other.getTitoloAmministrazioneProdotto()))) &&
            ((this.iriAmministrazioneConservato==null && other.getIriAmministrazioneConservato()==null) || 
             (this.iriAmministrazioneConservato!=null &&
              this.iriAmministrazioneConservato.equals(other.getIriAmministrazioneConservato()))) &&
            ((this.titoloAmministrazioneConservato==null && other.getTitoloAmministrazioneConservato()==null) || 
             (this.titoloAmministrazioneConservato!=null &&
              this.titoloAmministrazioneConservato.equals(other.getTitoloAmministrazioneConservato()))) &&
            ((this.modalitaVerifica==null && other.getModalitaVerifica()==null) || 
             (this.modalitaVerifica!=null &&
              this.modalitaVerifica.equals(other.getModalitaVerifica()))) &&
            ((this.dataFineValidita==null && other.getDataFineValidita()==null) || 
             (this.dataFineValidita!=null &&
              this.dataFineValidita.equals(other.getDataFineValidita()))) &&
            ((this.dataFineVerifica==null && other.getDataFineVerifica()==null) || 
             (this.dataFineVerifica!=null &&
              this.dataFineVerifica.equals(other.getDataFineVerifica()))) &&
            ((this.dataProtocolloRicevuto==null && other.getDataProtocolloRicevuto()==null) || 
             (this.dataProtocolloRicevuto!=null &&
              this.dataProtocolloRicevuto.equals(other.getDataProtocolloRicevuto()))) &&
            ((this.numeroProtocolloRicevuto==null && other.getNumeroProtocolloRicevuto()==null) || 
             (this.numeroProtocolloRicevuto!=null &&
              this.numeroProtocolloRicevuto.equals(other.getNumeroProtocolloRicevuto()))) &&
            ((this.posizioneTimbro==null && other.getPosizioneTimbro()==null) || 
             (this.posizioneTimbro!=null &&
              this.posizioneTimbro.equals(other.getPosizioneTimbro())));
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
        if (getTitoloAmministrazioneProdotto() != null) {
            _hashCode += getTitoloAmministrazioneProdotto().hashCode();
        }
        if (getIriAmministrazioneConservato() != null) {
            _hashCode += getIriAmministrazioneConservato().hashCode();
        }
        if (getTitoloAmministrazioneConservato() != null) {
            _hashCode += getTitoloAmministrazioneConservato().hashCode();
        }
        if (getModalitaVerifica() != null) {
            _hashCode += getModalitaVerifica().hashCode();
        }
        if (getDataFineValidita() != null) {
            _hashCode += getDataFineValidita().hashCode();
        }
        if (getDataFineVerifica() != null) {
            _hashCode += getDataFineVerifica().hashCode();
        }
        if (getDataProtocolloRicevuto() != null) {
            _hashCode += getDataProtocolloRicevuto().hashCode();
        }
        if (getNumeroProtocolloRicevuto() != null) {
            _hashCode += getNumeroProtocolloRicevuto().hashCode();
        }
        if (getPosizioneTimbro() != null) {
            _hashCode += getPosizioneTimbro().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ExtraMeta.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "extraMeta"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titoloAmministrazioneProdotto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "titoloAmministrazioneProdotto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("iriAmministrazioneConservato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "iriAmministrazioneConservato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "anyURI"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titoloAmministrazioneConservato");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "titoloAmministrazioneConservato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("modalitaVerifica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "modalitaVerifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineValidita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "dataFineValidita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataFineVerifica");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "dataFineVerifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataProtocolloRicevuto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "dataProtocolloRicevuto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "dateTime"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroProtocolloRicevuto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "numeroProtocolloRicevuto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("posizioneTimbro");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "posizioneTimbro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "posizioneTimbro"));
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
