/**
 * Token.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.tz.avtmb.integration.client.dto;

public class Token  implements java.io.Serializable {
    private org.apache.axis.types.NormalizedString accessToken;

    private org.apache.axis.types.NormalizedString tokenType;

    private org.apache.axis.types.NormalizedString expiresIn;

    private org.apache.axis.types.NormalizedString scope;

    private org.apache.axis.types.NormalizedString organization;

    private org.apache.axis.types.NormalizedString jti;

    public Token() {
    }

    public Token(
           org.apache.axis.types.NormalizedString accessToken,
           org.apache.axis.types.NormalizedString tokenType,
           org.apache.axis.types.NormalizedString expiresIn,
           org.apache.axis.types.NormalizedString scope,
           org.apache.axis.types.NormalizedString organization,
           org.apache.axis.types.NormalizedString jti) {
           this.accessToken = accessToken;
           this.tokenType = tokenType;
           this.expiresIn = expiresIn;
           this.scope = scope;
           this.organization = organization;
           this.jti = jti;
    }


    /**
     * Gets the accessToken value for this Token.
     * 
     * @return accessToken
     */
    public org.apache.axis.types.NormalizedString getAccessToken() {
        return accessToken;
    }


    /**
     * Sets the accessToken value for this Token.
     * 
     * @param accessToken
     */
    public void setAccessToken(org.apache.axis.types.NormalizedString accessToken) {
        this.accessToken = accessToken;
    }


    /**
     * Gets the tokenType value for this Token.
     * 
     * @return tokenType
     */
    public org.apache.axis.types.NormalizedString getTokenType() {
        return tokenType;
    }


    /**
     * Sets the tokenType value for this Token.
     * 
     * @param tokenType
     */
    public void setTokenType(org.apache.axis.types.NormalizedString tokenType) {
        this.tokenType = tokenType;
    }


    /**
     * Gets the expiresIn value for this Token.
     * 
     * @return expiresIn
     */
    public org.apache.axis.types.NormalizedString getExpiresIn() {
        return expiresIn;
    }


    /**
     * Sets the expiresIn value for this Token.
     * 
     * @param expiresIn
     */
    public void setExpiresIn(org.apache.axis.types.NormalizedString expiresIn) {
        this.expiresIn = expiresIn;
    }


    /**
     * Gets the scope value for this Token.
     * 
     * @return scope
     */
    public org.apache.axis.types.NormalizedString getScope() {
        return scope;
    }


    /**
     * Sets the scope value for this Token.
     * 
     * @param scope
     */
    public void setScope(org.apache.axis.types.NormalizedString scope) {
        this.scope = scope;
    }


    /**
     * Gets the organization value for this Token.
     * 
     * @return organization
     */
    public org.apache.axis.types.NormalizedString getOrganization() {
        return organization;
    }


    /**
     * Sets the organization value for this Token.
     * 
     * @param organization
     */
    public void setOrganization(org.apache.axis.types.NormalizedString organization) {
        this.organization = organization;
    }


    /**
     * Gets the jti value for this Token.
     * 
     * @return jti
     */
    public org.apache.axis.types.NormalizedString getJti() {
        return jti;
    }


    /**
     * Sets the jti value for this Token.
     * 
     * @param jti
     */
    public void setJti(org.apache.axis.types.NormalizedString jti) {
        this.jti = jti;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof Token)) return false;
        Token other = (Token) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.accessToken==null && other.getAccessToken()==null) || 
             (this.accessToken!=null &&
              this.accessToken.equals(other.getAccessToken()))) &&
            ((this.tokenType==null && other.getTokenType()==null) || 
             (this.tokenType!=null &&
              this.tokenType.equals(other.getTokenType()))) &&
            ((this.expiresIn==null && other.getExpiresIn()==null) || 
             (this.expiresIn!=null &&
              this.expiresIn.equals(other.getExpiresIn()))) &&
            ((this.scope==null && other.getScope()==null) || 
             (this.scope!=null &&
              this.scope.equals(other.getScope()))) &&
            ((this.organization==null && other.getOrganization()==null) || 
             (this.organization!=null &&
              this.organization.equals(other.getOrganization()))) &&
            ((this.jti==null && other.getJti()==null) || 
             (this.jti!=null &&
              this.jti.equals(other.getJti())));
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
        if (getAccessToken() != null) {
            _hashCode += getAccessToken().hashCode();
        }
        if (getTokenType() != null) {
            _hashCode += getTokenType().hashCode();
        }
        if (getExpiresIn() != null) {
            _hashCode += getExpiresIn().hashCode();
        }
        if (getScope() != null) {
            _hashCode += getScope().hashCode();
        }
        if (getOrganization() != null) {
            _hashCode += getOrganization().hashCode();
        }
        if (getJti() != null) {
            _hashCode += getJti().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(Token.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "token"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("accessToken");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "accessToken"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tokenType");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "tokenType"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("expiresIn");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "expiresIn"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scope");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "scope"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("organization");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "organization"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("jti");
        elemField.setXmlName(new javax.xml.namespace.QName("http://stampsign.xsd.avtmb.tz.eng.it", "jti"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "normalizedString"));
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
