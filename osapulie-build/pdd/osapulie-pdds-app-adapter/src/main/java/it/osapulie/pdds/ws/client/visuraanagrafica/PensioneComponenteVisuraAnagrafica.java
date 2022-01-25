/**
 * PensioneComponenteVisuraAnagrafica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraanagrafica;

public class PensioneComponenteVisuraAnagrafica  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String numeroPensione;

    private java.lang.String codicePensione;

    private java.math.BigInteger idComponente;

    public PensioneComponenteVisuraAnagrafica() {
    }

    public PensioneComponenteVisuraAnagrafica(
           java.math.BigInteger id,
           java.lang.String numeroPensione,
           java.lang.String codicePensione,
           java.math.BigInteger idComponente) {
           this.id = id;
           this.numeroPensione = numeroPensione;
           this.codicePensione = codicePensione;
           this.idComponente = idComponente;
    }


    /**
     * Gets the id value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the numeroPensione value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @return numeroPensione
     */
    public java.lang.String getNumeroPensione() {
        return numeroPensione;
    }


    /**
     * Sets the numeroPensione value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @param numeroPensione
     */
    public void setNumeroPensione(java.lang.String numeroPensione) {
        this.numeroPensione = numeroPensione;
    }


    /**
     * Gets the codicePensione value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @return codicePensione
     */
    public java.lang.String getCodicePensione() {
        return codicePensione;
    }


    /**
     * Sets the codicePensione value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @param codicePensione
     */
    public void setCodicePensione(java.lang.String codicePensione) {
        this.codicePensione = codicePensione;
    }


    /**
     * Gets the idComponente value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @return idComponente
     */
    public java.math.BigInteger getIdComponente() {
        return idComponente;
    }


    /**
     * Sets the idComponente value for this PensioneComponenteVisuraAnagrafica.
     * 
     * @param idComponente
     */
    public void setIdComponente(java.math.BigInteger idComponente) {
        this.idComponente = idComponente;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof PensioneComponenteVisuraAnagrafica)) return false;
        PensioneComponenteVisuraAnagrafica other = (PensioneComponenteVisuraAnagrafica) obj;
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
            ((this.numeroPensione==null && other.getNumeroPensione()==null) || 
             (this.numeroPensione!=null &&
              this.numeroPensione.equals(other.getNumeroPensione()))) &&
            ((this.codicePensione==null && other.getCodicePensione()==null) || 
             (this.codicePensione!=null &&
              this.codicePensione.equals(other.getCodicePensione()))) &&
            ((this.idComponente==null && other.getIdComponente()==null) || 
             (this.idComponente!=null &&
              this.idComponente.equals(other.getIdComponente())));
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
        if (getNumeroPensione() != null) {
            _hashCode += getNumeroPensione().hashCode();
        }
        if (getCodicePensione() != null) {
            _hashCode += getCodicePensione().hashCode();
        }
        if (getIdComponente() != null) {
            _hashCode += getIdComponente().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(PensioneComponenteVisuraAnagrafica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "pensioneComponenteVisuraAnagrafica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroPensione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroPensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codicePensione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codicePensione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idComponente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "idComponente"));
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
