/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.ricercaDoc;

public class Documento  implements java.io.Serializable {
    private java.lang.String dataRegistrazione;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocolloEstesi estremiProtocolloEstesi;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Classifica classifica;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicoloVirtuale estremiFascicoloVirtuale;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo estremiFascicolo;

    private java.lang.String oggetto;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumentiInformatici documentiInformatici;

    private it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaResponsabiliTrattamento responsabiliTrattamento;

    public Documento() {
    }

    public Documento(
           java.lang.String dataRegistrazione,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocolloEstesi estremiProtocolloEstesi,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Classifica classifica,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicoloVirtuale estremiFascicoloVirtuale,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo estremiFascicolo,
           java.lang.String oggetto,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumentiInformatici documentiInformatici,
           it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaResponsabiliTrattamento responsabiliTrattamento) {
           this.dataRegistrazione = dataRegistrazione;
           this.estremiProtocolloEstesi = estremiProtocolloEstesi;
           this.classifica = classifica;
           this.estremiFascicoloVirtuale = estremiFascicoloVirtuale;
           this.estremiFascicolo = estremiFascicolo;
           this.oggetto = oggetto;
           this.documentiInformatici = documentiInformatici;
           this.responsabiliTrattamento = responsabiliTrattamento;
    }


    /**
     * Gets the dataRegistrazione value for this Documento.
     * 
     * @return dataRegistrazione
     */
    public java.lang.String getDataRegistrazione() {
        return dataRegistrazione;
    }


    /**
     * Sets the dataRegistrazione value for this Documento.
     * 
     * @param dataRegistrazione
     */
    public void setDataRegistrazione(java.lang.String dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }


    /**
     * Gets the estremiProtocolloEstesi value for this Documento.
     * 
     * @return estremiProtocolloEstesi
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocolloEstesi getEstremiProtocolloEstesi() {
        return estremiProtocolloEstesi;
    }


    /**
     * Sets the estremiProtocolloEstesi value for this Documento.
     * 
     * @param estremiProtocolloEstesi
     */
    public void setEstremiProtocolloEstesi(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiProtocolloEstesi estremiProtocolloEstesi) {
        this.estremiProtocolloEstesi = estremiProtocolloEstesi;
    }


    /**
     * Gets the classifica value for this Documento.
     * 
     * @return classifica
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Classifica getClassifica() {
        return classifica;
    }


    /**
     * Sets the classifica value for this Documento.
     * 
     * @param classifica
     */
    public void setClassifica(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.Classifica classifica) {
        this.classifica = classifica;
    }


    /**
     * Gets the estremiFascicoloVirtuale value for this Documento.
     * 
     * @return estremiFascicoloVirtuale
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicoloVirtuale getEstremiFascicoloVirtuale() {
        return estremiFascicoloVirtuale;
    }


    /**
     * Sets the estremiFascicoloVirtuale value for this Documento.
     * 
     * @param estremiFascicoloVirtuale
     */
    public void setEstremiFascicoloVirtuale(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicoloVirtuale estremiFascicoloVirtuale) {
        this.estremiFascicoloVirtuale = estremiFascicoloVirtuale;
    }


    /**
     * Gets the estremiFascicolo value for this Documento.
     * 
     * @return estremiFascicolo
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo getEstremiFascicolo() {
        return estremiFascicolo;
    }


    /**
     * Sets the estremiFascicolo value for this Documento.
     * 
     * @param estremiFascicolo
     */
    public void setEstremiFascicolo(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.EstremiFascicolo estremiFascicolo) {
        this.estremiFascicolo = estremiFascicolo;
    }


    /**
     * Gets the oggetto value for this Documento.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this Documento.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the documentiInformatici value for this Documento.
     * 
     * @return documentiInformatici
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumentiInformatici getDocumentiInformatici() {
        return documentiInformatici;
    }


    /**
     * Sets the documentiInformatici value for this Documento.
     * 
     * @param documentiInformatici
     */
    public void setDocumentiInformatici(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaDocumentiInformatici documentiInformatici) {
        this.documentiInformatici = documentiInformatici;
    }


    /**
     * Gets the responsabiliTrattamento value for this Documento.
     * 
     * @return responsabiliTrattamento
     */
    public it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaResponsabiliTrattamento getResponsabiliTrattamento() {
        return responsabiliTrattamento;
    }


    /**
     * Sets the responsabiliTrattamento value for this Documento.
     * 
     * @param responsabiliTrattamento
     */
    public void setResponsabiliTrattamento(it.osapulie.pdds.ws.client.protocollo.ricercaDoc.ListaResponsabiliTrattamento responsabiliTrattamento) {
        this.responsabiliTrattamento = responsabiliTrattamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Documento)) return false;
        Documento other = (Documento) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.dataRegistrazione==null && other.getDataRegistrazione()==null) || 
             (this.dataRegistrazione!=null &&
              this.dataRegistrazione.equals(other.getDataRegistrazione()))) &&
            ((this.estremiProtocolloEstesi==null && other.getEstremiProtocolloEstesi()==null) || 
             (this.estremiProtocolloEstesi!=null &&
              this.estremiProtocolloEstesi.equals(other.getEstremiProtocolloEstesi()))) &&
            ((this.classifica==null && other.getClassifica()==null) || 
             (this.classifica!=null &&
              this.classifica.equals(other.getClassifica()))) &&
            ((this.estremiFascicoloVirtuale==null && other.getEstremiFascicoloVirtuale()==null) || 
             (this.estremiFascicoloVirtuale!=null &&
              this.estremiFascicoloVirtuale.equals(other.getEstremiFascicoloVirtuale()))) &&
            ((this.estremiFascicolo==null && other.getEstremiFascicolo()==null) || 
             (this.estremiFascicolo!=null &&
              this.estremiFascicolo.equals(other.getEstremiFascicolo()))) &&
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto()))) &&
            ((this.documentiInformatici==null && other.getDocumentiInformatici()==null) || 
             (this.documentiInformatici!=null &&
              this.documentiInformatici.equals(other.getDocumentiInformatici()))) &&
            ((this.responsabiliTrattamento==null && other.getResponsabiliTrattamento()==null) || 
             (this.responsabiliTrattamento!=null &&
              this.responsabiliTrattamento.equals(other.getResponsabiliTrattamento())));
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
        if (getDataRegistrazione() != null) {
            _hashCode += getDataRegistrazione().hashCode();
        }
        if (getEstremiProtocolloEstesi() != null) {
            _hashCode += getEstremiProtocolloEstesi().hashCode();
        }
        if (getClassifica() != null) {
            _hashCode += getClassifica().hashCode();
        }
        if (getEstremiFascicoloVirtuale() != null) {
            _hashCode += getEstremiFascicoloVirtuale().hashCode();
        }
        if (getEstremiFascicolo() != null) {
            _hashCode += getEstremiFascicolo().hashCode();
        }
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        if (getDocumentiInformatici() != null) {
            _hashCode += getDocumentiInformatici().hashCode();
        }
        if (getResponsabiliTrattamento() != null) {
            _hashCode += getResponsabiliTrattamento().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Documento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataRegistrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "dataRegistrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiProtocolloEstesi");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiProtocolloEstesi"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiProtocolloEstesi"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "classifica"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiFascicoloVirtuale");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiFascicoloVirtuale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicoloVirtuale"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("estremiFascicolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "estremiFascicolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "estremiFascicolo"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documentiInformatici");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documentiInformatici"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaDocumentiInformatici"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("responsabiliTrattamento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "responsabiliTrattamento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "listaResponsabiliTrattamento"));
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
