/**
 * Tb_detrazione.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public class Tb_detrazione  implements java.io.Serializable {
    private java.math.BigInteger ID;

    private java.lang.String descrizione;

    private java.lang.Double importo;

    private java.lang.String info;

    private java.math.BigInteger fk_tipologia_detrazione;

    public Tb_detrazione() {
    }

    public Tb_detrazione(
           java.math.BigInteger ID,
           java.lang.String descrizione,
           java.lang.Double importo,
           java.lang.String info,
           java.math.BigInteger fk_tipologia_detrazione) {
           this.ID = ID;
           this.descrizione = descrizione;
           this.importo = importo;
           this.info = info;
           this.fk_tipologia_detrazione = fk_tipologia_detrazione;
    }


    /**
     * Gets the ID value for this Tb_detrazione.
     * 
     * @return ID
     */
    public java.math.BigInteger getID() {
        return ID;
    }


    /**
     * Sets the ID value for this Tb_detrazione.
     * 
     * @param ID
     */
    public void setID(java.math.BigInteger ID) {
        this.ID = ID;
    }


    /**
     * Gets the descrizione value for this Tb_detrazione.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Tb_detrazione.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the importo value for this Tb_detrazione.
     * 
     * @return importo
     */
    public java.lang.Double getImporto() {
        return importo;
    }


    /**
     * Sets the importo value for this Tb_detrazione.
     * 
     * @param importo
     */
    public void setImporto(java.lang.Double importo) {
        this.importo = importo;
    }


    /**
     * Gets the info value for this Tb_detrazione.
     * 
     * @return info
     */
    public java.lang.String getInfo() {
        return info;
    }


    /**
     * Sets the info value for this Tb_detrazione.
     * 
     * @param info
     */
    public void setInfo(java.lang.String info) {
        this.info = info;
    }


    /**
     * Gets the fk_tipologia_detrazione value for this Tb_detrazione.
     * 
     * @return fk_tipologia_detrazione
     */
    public java.math.BigInteger getFk_tipologia_detrazione() {
        return fk_tipologia_detrazione;
    }


    /**
     * Sets the fk_tipologia_detrazione value for this Tb_detrazione.
     * 
     * @param fk_tipologia_detrazione
     */
    public void setFk_tipologia_detrazione(java.math.BigInteger fk_tipologia_detrazione) {
        this.fk_tipologia_detrazione = fk_tipologia_detrazione;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tb_detrazione)) return false;
        Tb_detrazione other = (Tb_detrazione) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.ID==null && other.getID()==null) || 
             (this.ID!=null &&
              this.ID.equals(other.getID()))) &&
            ((this.descrizione==null && other.getDescrizione()==null) || 
             (this.descrizione!=null &&
              this.descrizione.equals(other.getDescrizione()))) &&
            ((this.importo==null && other.getImporto()==null) || 
             (this.importo!=null &&
              this.importo.equals(other.getImporto()))) &&
            ((this.info==null && other.getInfo()==null) || 
             (this.info!=null &&
              this.info.equals(other.getInfo()))) &&
            ((this.fk_tipologia_detrazione==null && other.getFk_tipologia_detrazione()==null) || 
             (this.fk_tipologia_detrazione!=null &&
              this.fk_tipologia_detrazione.equals(other.getFk_tipologia_detrazione())));
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
        if (getID() != null) {
            _hashCode += getID().hashCode();
        }
        if (getDescrizione() != null) {
            _hashCode += getDescrizione().hashCode();
        }
        if (getImporto() != null) {
            _hashCode += getImporto().hashCode();
        }
        if (getInfo() != null) {
            _hashCode += getInfo().hashCode();
        }
        if (getFk_tipologia_detrazione() != null) {
            _hashCode += getFk_tipologia_detrazione().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tb_detrazione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_detrazione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "descrizione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("importo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "importo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("info");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "info"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fk_tipologia_detrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tipologia_detrazione"));
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
