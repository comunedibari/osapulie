/**
 * Tb_agevolazione.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public class Tb_agevolazione  implements java.io.Serializable {
    private java.math.BigInteger ID;

    private java.lang.String nome;

    private java.lang.Double valore;

    public Tb_agevolazione() {
    }

    public Tb_agevolazione(
           java.math.BigInteger ID,
           java.lang.String nome,
           java.lang.Double valore) {
           this.ID = ID;
           this.nome = nome;
           this.valore = valore;
    }


    /**
     * Gets the ID value for this Tb_agevolazione.
     * 
     * @return ID
     */
    public java.math.BigInteger getID() {
        return ID;
    }


    /**
     * Sets the ID value for this Tb_agevolazione.
     * 
     * @param ID
     */
    public void setID(java.math.BigInteger ID) {
        this.ID = ID;
    }


    /**
     * Gets the nome value for this Tb_agevolazione.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this Tb_agevolazione.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the valore value for this Tb_agevolazione.
     * 
     * @return valore
     */
    public java.lang.Double getValore() {
        return valore;
    }


    /**
     * Sets the valore value for this Tb_agevolazione.
     * 
     * @param valore
     */
    public void setValore(java.lang.Double valore) {
        this.valore = valore;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Tb_agevolazione)) return false;
        Tb_agevolazione other = (Tb_agevolazione) obj;
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
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.valore==null && other.getValore()==null) || 
             (this.valore!=null &&
              this.valore.equals(other.getValore())));
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
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getValore() != null) {
            _hashCode += getValore().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Tb_agevolazione.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "tb_agevolazione"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ID");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "ID"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("valore");
        elemField.setXmlName(new javax.xml.namespace.QName("http://ws.wso2.org/dataservice", "valore"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "double"));
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
