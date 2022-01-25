/**
 * Tb_esenzione.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public class Tb_esenzione  implements java.io.Serializable {
    private java.math.BigInteger ID;

    private java.lang.String descrizione;

    private java.math.BigInteger fk_categoriaimmobile;

    private java.math.BigInteger fk_tributo;

    public Tb_esenzione() {
    }

    public Tb_esenzione(
           java.math.BigInteger ID,
           java.lang.String descrizione,
           java.math.BigInteger fk_categoriaimmobile,
           java.math.BigInteger fk_tributo) {
           this.ID = ID;
           this.descrizione = descrizione;
           this.fk_categoriaimmobile = fk_categoriaimmobile;
           this.fk_tributo = fk_tributo;
    }


    /**
     * Gets the ID value for this Tb_esenzione.
     * 
     * @return ID
     */
    public java.math.BigInteger getID() {
        return ID;
    }


    /**
     * Sets the ID value for this Tb_esenzione.
     * 
     * @param ID
     */
    public void setID(java.math.BigInteger ID) {
        this.ID = ID;
    }


    /**
     * Gets the descrizione value for this Tb_esenzione.
     * 
     * @return descrizione
     */
    public java.lang.String getDescrizione() {
        return descrizione;
    }


    /**
     * Sets the descrizione value for this Tb_esenzione.
     * 
     * @param descrizione
     */
    public void setDescrizione(java.lang.String descrizione) {
        this.descrizione = descrizione;
    }


    /**
     * Gets the fk_categoriaimmobile value for this Tb_esenzione.
     * 
     * @return fk_categoriaimmobile
     */
    public java.math.BigInteger getFk_categoriaimmobile() {
        return fk_categoriaimmobile;
    }


    /**
     * Sets the fk_categoriaimmobile value for this Tb_esenzione.
     * 
     * @param fk_categoriaimmobile
     */
    public void setFk_categoriaimmobile(java.math.BigInteger fk_categoriaimmobile) {
        this.fk_categoriaimmobile = fk_categoriaimmobile;
    }


    /**
     * Gets the fk_tributo value for this Tb_esenzione.
     * 
     * @return fk_tributo
     */
    public java.math.BigInteger getFk_tributo() {
        return fk_tributo;
    }


    /**
     * Sets the fk_tributo value for this Tb_esenzione.
     * 
     * @param fk_tributo
     */
    public void setFk_tributo(java.math.BigInteger fk_tributo) {
        this.fk_tributo = fk_tributo;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tb_esenzione)) return false;
        Tb_esenzione other = (Tb_esenzione) obj;
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
            ((this.fk_categoriaimmobile==null && other.getFk_categoriaimmobile()==null) || 
             (this.fk_categoriaimmobile!=null &&
              this.fk_categoriaimmobile.equals(other.getFk_categoriaimmobile()))) &&
            ((this.fk_tributo==null && other.getFk_tributo()==null) || 
             (this.fk_tributo!=null &&
              this.fk_tributo.equals(other.getFk_tributo())));
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
        if (getFk_categoriaimmobile() != null) {
            _hashCode += getFk_categoriaimmobile().hashCode();
        }
        if (getFk_tributo() != null) {
            _hashCode += getFk_tributo().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tb_esenzione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_esenzione"));
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
        elemField.setFieldName("fk_categoriaimmobile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_categoriaimmobile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("fk_tributo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "fk_tributo"));
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
