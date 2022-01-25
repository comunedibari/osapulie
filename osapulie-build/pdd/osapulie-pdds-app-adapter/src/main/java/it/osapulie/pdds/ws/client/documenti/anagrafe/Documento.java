/**
 * Documento.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.documenti.anagrafe;

public class Documento  implements java.io.Serializable {
    private java.lang.String id;

    private java.lang.String codiceFiscale;

    private java.lang.String codiceServizio;

    private java.math.BigInteger anno;

    private java.lang.String nome;

    private java.lang.String descrizione;

    private java.lang.String contentType;

    private byte[] binario;

    public Documento() {
    }

    public Documento(
           java.lang.String id,
           java.lang.String codiceFiscale,
           java.lang.String codiceServizio,
           java.math.BigInteger anno,
           java.lang.String nome,
           java.lang.String descrizione,
           java.lang.String contentType,
           byte[] binario) {
           this.id = id;
           this.codiceFiscale = codiceFiscale;
           this.codiceServizio = codiceServizio;
           this.anno = anno;
           this.nome = nome;
           this.descrizione = descrizione;
           this.contentType = contentType;
           this.binario = binario;
    }


    /**
     * Gets the id value for this Documento.
     * 
     * @return id
     */
    public java.lang.String getId() {
        return id;
    }


    /**
     * Sets the id value for this Documento.
     * 
     * @param id
     */
    public void setId(java.lang.String id) {
        this.id = id;
    }


    /**
     * Gets the codiceFiscale value for this Documento.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this Documento.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the codiceServizio value for this Documento.
     * 
     * @return codiceServizio
     */
    public java.lang.String getCodiceServizio() {
        return codiceServizio;
    }


    /**
     * Sets the codiceServizio value for this Documento.
     * 
     * @param codiceServizio
     */
    public void setCodiceServizio(java.lang.String codiceServizio) {
        this.codiceServizio = codiceServizio;
    }


    /**
     * Gets the anno value for this Documento.
     * 
     * @return anno
     */
    public java.math.BigInteger getAnno() {
        return anno;
    }


    /**
     * Sets the anno value for this Documento.
     * 
     * @param anno
     */
    public void setAnno(java.math.BigInteger anno) {
        this.anno = anno;
    }


    /**
     * Gets the nome value for this Documento.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Documento.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the descrizione value for this Documento.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Documento.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the contentType value for this Documento.
     * 
     * @return contentType
     */
    public java.lang.String getContentType() {
        return contentType;
    }


    /**
     * Sets the contentType value for this Documento.
     * 
     * @param contentType
     */
    public void setContentType(java.lang.String contentType) {
        this.contentType = contentType;
    }


    /**
     * Gets the binario value for this Documento.
     * 
     * @return binario
     */
    public byte[] getBinario() {
        return binario;
    }


    /**
     * Sets the binario value for this Documento.
     * 
     * @param binario
     */
    public void setBinario(byte[] binario) {
        this.binario = binario;
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
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.codiceServizio==null && other.getCodiceServizio()==null) || 
             (this.codiceServizio!=null &&
              this.codiceServizio.equals(other.getCodiceServizio()))) &&
            ((this.anno==null && other.getAnno()==null) || 
             (this.anno!=null &&
              this.anno.equals(other.getAnno()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.contentType==null && other.getContentType()==null) || 
             (this.contentType!=null &&
              this.contentType.equals(other.getContentType()))) &&
            ((this.binario==null && other.getBinario()==null) || 
             (this.binario!=null &&
              java.util.Arrays.equals(this.binario, other.getBinario())));
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
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getCodiceServizio() != null) {
            _hashCode += getCodiceServizio().hashCode();
        }
        if (getAnno() != null) {
            _hashCode += getAnno().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getContentType() != null) {
            _hashCode += getContentType().hashCode();
        }
        if (getBinario() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getBinario());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getBinario(), i);
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
        new org.apache.axis.description.TypeDesc(Documento.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "documento"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceServizio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceServizio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("anno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "anno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("contentType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "contentType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("binario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "binario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "base64Binary"));
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
