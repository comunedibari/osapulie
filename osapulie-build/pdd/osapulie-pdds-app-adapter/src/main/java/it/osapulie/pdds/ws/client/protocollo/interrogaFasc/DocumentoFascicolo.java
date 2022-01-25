/**
 * DocumentoFascicolo.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.interrogaFasc;

public class DocumentoFascicolo  implements java.io.Serializable {
    private java.lang.String codiceAmm;

    private java.lang.String codiceAoo;

    private java.lang.String descrCorrispondente;

    private java.lang.String dataRegistrazione;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocolloEstesi estremiProtocolloEstesi;

    private java.lang.String codiceUfficio;

    private java.lang.String descrizioneUfficio;

    private java.lang.String codiceRegistro;

    private java.lang.String descrizioneRegistro;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica;

    private java.lang.String oggetto;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiInformatici documentiInformatici;

    private it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaResponsabiliTrattamento responsabiliTrattamento;

    public DocumentoFascicolo() {
    }

    public DocumentoFascicolo(
           java.lang.String codiceAmm,
           java.lang.String codiceAoo,
           java.lang.String descrCorrispondente,
           java.lang.String dataRegistrazione,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocolloEstesi estremiProtocolloEstesi,
           java.lang.String codiceUfficio,
           java.lang.String descrizioneUfficio,
           java.lang.String codiceRegistro,
           java.lang.String descrizioneRegistro,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica,
           java.lang.String oggetto,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiInformatici documentiInformatici,
           it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaResponsabiliTrattamento responsabiliTrattamento) {
           this.codiceAmm = codiceAmm;
           this.codiceAoo = codiceAoo;
           this.descrCorrispondente = descrCorrispondente;
           this.dataRegistrazione = dataRegistrazione;
           this.estremiProtocolloEstesi = estremiProtocolloEstesi;
           this.codiceUfficio = codiceUfficio;
           this.descrizioneUfficio = descrizioneUfficio;
           this.codiceRegistro = codiceRegistro;
           this.descrizioneRegistro = descrizioneRegistro;
           this.classifica = classifica;
           this.oggetto = oggetto;
           this.documentiInformatici = documentiInformatici;
           this.responsabiliTrattamento = responsabiliTrattamento;
    }


    /**
     * Gets the codiceAmm value for this DocumentoFascicolo.
     * 
     * @return codiceAmm
     */
    public java.lang.String getCodiceAmm() {
        return codiceAmm;
    }


    /**
     * Sets the codiceAmm value for this DocumentoFascicolo.
     * 
     * @param codiceAmm
     */
    public void setCodiceAmm(java.lang.String codiceAmm) {
        this.codiceAmm = codiceAmm;
    }


    /**
     * Gets the codiceAoo value for this DocumentoFascicolo.
     * 
     * @return codiceAoo
     */
    public java.lang.String getCodiceAoo() {
        return codiceAoo;
    }


    /**
     * Sets the codiceAoo value for this DocumentoFascicolo.
     * 
     * @param codiceAoo
     */
    public void setCodiceAoo(java.lang.String codiceAoo) {
        this.codiceAoo = codiceAoo;
    }


    /**
     * Gets the descrCorrispondente value for this DocumentoFascicolo.
     * 
     * @return descrCorrispondente
     */
    public java.lang.String getDescrCorrispondente() {
        return descrCorrispondente;
    }


    /**
     * Sets the descrCorrispondente value for this DocumentoFascicolo.
     * 
     * @param descrCorrispondente
     */
    public void setDescrCorrispondente(java.lang.String descrCorrispondente) {
        this.descrCorrispondente = descrCorrispondente;
    }


    /**
     * Gets the dataRegistrazione value for this DocumentoFascicolo.
     * 
     * @return dataRegistrazione
     */
    public java.lang.String getDataRegistrazione() {
        return dataRegistrazione;
    }


    /**
     * Sets the dataRegistrazione value for this DocumentoFascicolo.
     * 
     * @param dataRegistrazione
     */
    public void setDataRegistrazione(java.lang.String dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }


    /**
     * Gets the estremiProtocolloEstesi value for this DocumentoFascicolo.
     * 
     * @return estremiProtocolloEstesi
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocolloEstesi getEstremiProtocolloEstesi() {
        return estremiProtocolloEstesi;
    }


    /**
     * Sets the estremiProtocolloEstesi value for this DocumentoFascicolo.
     * 
     * @param estremiProtocolloEstesi
     */
    public void setEstremiProtocolloEstesi(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.EstremiProtocolloEstesi estremiProtocolloEstesi) {
        this.estremiProtocolloEstesi = estremiProtocolloEstesi;
    }


    /**
     * Gets the codiceUfficio value for this DocumentoFascicolo.
     * 
     * @return codiceUfficio
     */
    public java.lang.String getCodiceUfficio() {
        return codiceUfficio;
    }


    /**
     * Sets the codiceUfficio value for this DocumentoFascicolo.
     * 
     * @param codiceUfficio
     */
    public void setCodiceUfficio(java.lang.String codiceUfficio) {
        this.codiceUfficio = codiceUfficio;
    }


    /**
     * Gets the descrizioneUfficio value for this DocumentoFascicolo.
     * 
     * @return descrizioneUfficio
     */
    public java.lang.String getDescrizioneUfficio() {
        return descrizioneUfficio;
    }


    /**
     * Sets the descrizioneUfficio value for this DocumentoFascicolo.
     * 
     * @param descrizioneUfficio
     */
    public void setDescrizioneUfficio(java.lang.String descrizioneUfficio) {
        this.descrizioneUfficio = descrizioneUfficio;
    }


    /**
     * Gets the codiceRegistro value for this DocumentoFascicolo.
     * 
     * @return codiceRegistro
     */
    public java.lang.String getCodiceRegistro() {
        return codiceRegistro;
    }


    /**
     * Sets the codiceRegistro value for this DocumentoFascicolo.
     * 
     * @param codiceRegistro
     */
    public void setCodiceRegistro(java.lang.String codiceRegistro) {
        this.codiceRegistro = codiceRegistro;
    }


    /**
     * Gets the descrizioneRegistro value for this DocumentoFascicolo.
     * 
     * @return descrizioneRegistro
     */
    public java.lang.String getDescrizioneRegistro() {
        return descrizioneRegistro;
    }


    /**
     * Sets the descrizioneRegistro value for this DocumentoFascicolo.
     * 
     * @param descrizioneRegistro
     */
    public void setDescrizioneRegistro(java.lang.String descrizioneRegistro) {
        this.descrizioneRegistro = descrizioneRegistro;
    }


    /**
     * Gets the classifica value for this DocumentoFascicolo.
     * 
     * @return classifica
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica getClassifica() {
        return classifica;
    }


    /**
     * Sets the classifica value for this DocumentoFascicolo.
     * 
     * @param classifica
     */
    public void setClassifica(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.Classifica classifica) {
        this.classifica = classifica;
    }


    /**
     * Gets the oggetto value for this DocumentoFascicolo.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this DocumentoFascicolo.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the documentiInformatici value for this DocumentoFascicolo.
     * 
     * @return documentiInformatici
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiInformatici getDocumentiInformatici() {
        return documentiInformatici;
    }


    /**
     * Sets the documentiInformatici value for this DocumentoFascicolo.
     * 
     * @param documentiInformatici
     */
    public void setDocumentiInformatici(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaDocumentiInformatici documentiInformatici) {
        this.documentiInformatici = documentiInformatici;
    }


    /**
     * Gets the responsabiliTrattamento value for this DocumentoFascicolo.
     * 
     * @return responsabiliTrattamento
     */
    public it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaResponsabiliTrattamento getResponsabiliTrattamento() {
        return responsabiliTrattamento;
    }


    /**
     * Sets the responsabiliTrattamento value for this DocumentoFascicolo.
     * 
     * @param responsabiliTrattamento
     */
    public void setResponsabiliTrattamento(it.osapulie.pdds.ws.client.protocollo.interrogaFasc.ListaResponsabiliTrattamento responsabiliTrattamento) {
        this.responsabiliTrattamento = responsabiliTrattamento;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof DocumentoFascicolo)) return false;
        DocumentoFascicolo other = (DocumentoFascicolo) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.codiceAmm==null && other.getCodiceAmm()==null) || 
             (this.codiceAmm!=null &&
              this.codiceAmm.equals(other.getCodiceAmm()))) &&
            ((this.codiceAoo==null && other.getCodiceAoo()==null) || 
             (this.codiceAoo!=null &&
              this.codiceAoo.equals(other.getCodiceAoo()))) &&
            ((this.descrCorrispondente==null && other.getDescrCorrispondente()==null) || 
             (this.descrCorrispondente!=null &&
              this.descrCorrispondente.equals(other.getDescrCorrispondente()))) &&
            ((this.dataRegistrazione==null && other.getDataRegistrazione()==null) || 
             (this.dataRegistrazione!=null &&
              this.dataRegistrazione.equals(other.getDataRegistrazione()))) &&
            ((this.estremiProtocolloEstesi==null && other.getEstremiProtocolloEstesi()==null) || 
             (this.estremiProtocolloEstesi!=null &&
              this.estremiProtocolloEstesi.equals(other.getEstremiProtocolloEstesi()))) &&
            ((this.codiceUfficio==null && other.getCodiceUfficio()==null) || 
             (this.codiceUfficio!=null &&
              this.codiceUfficio.equals(other.getCodiceUfficio()))) &&
            ((this.descrizioneUfficio==null && other.getDescrizioneUfficio()==null) || 
             (this.descrizioneUfficio!=null &&
              this.descrizioneUfficio.equals(other.getDescrizioneUfficio()))) &&
            ((this.codiceRegistro==null && other.getCodiceRegistro()==null) || 
             (this.codiceRegistro!=null &&
              this.codiceRegistro.equals(other.getCodiceRegistro()))) &&
            ((this.descrizioneRegistro==null && other.getDescrizioneRegistro()==null) || 
             (this.descrizioneRegistro!=null &&
              this.descrizioneRegistro.equals(other.getDescrizioneRegistro()))) &&
            ((this.classifica==null && other.getClassifica()==null) || 
             (this.classifica!=null &&
              this.classifica.equals(other.getClassifica()))) &&
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
        if (getCodiceAmm() != null) {
            _hashCode += getCodiceAmm().hashCode();
        }
        if (getCodiceAoo() != null) {
            _hashCode += getCodiceAoo().hashCode();
        }
        if (getDescrCorrispondente() != null) {
            _hashCode += getDescrCorrispondente().hashCode();
        }
        if (getDataRegistrazione() != null) {
            _hashCode += getDataRegistrazione().hashCode();
        }
        if (getEstremiProtocolloEstesi() != null) {
            _hashCode += getEstremiProtocolloEstesi().hashCode();
        }
        if (getCodiceUfficio() != null) {
            _hashCode += getCodiceUfficio().hashCode();
        }
        if (getDescrizioneUfficio() != null) {
            _hashCode += getDescrizioneUfficio().hashCode();
        }
        if (getCodiceRegistro() != null) {
            _hashCode += getCodiceRegistro().hashCode();
        }
        if (getDescrizioneRegistro() != null) {
            _hashCode += getDescrizioneRegistro().hashCode();
        }
        if (getClassifica() != null) {
            _hashCode += getClassifica().hashCode();
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
        new org.apache.axis.description.TypeDesc(DocumentoFascicolo.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "documentoFascicolo"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAmm");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceAmm"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceAoo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceAoo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrCorrispondente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrCorrispondente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
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
        elemField.setFieldName("codiceUfficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceUfficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneUfficio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneUfficio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "codiceRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneRegistro");
        elemField.setXmlName(new javax.xml.namespace.QName("", "descrizioneRegistro"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("classifica");
        elemField.setXmlName(new javax.xml.namespace.QName("", "classifica"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://progettoicar.it/AP3interprotoQry/types", "classifica"));
        elemField.setNillable(true);
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
