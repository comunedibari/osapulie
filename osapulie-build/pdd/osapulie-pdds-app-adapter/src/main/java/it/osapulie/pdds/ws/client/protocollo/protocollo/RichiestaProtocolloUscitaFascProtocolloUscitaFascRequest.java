/**
 * RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public class RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest  implements java.io.Serializable {
    private it.osapulie.pdds.ws.client.protocollo.protocollo.MittenteProtUscita mittente;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario[] destinatari;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati;

    private java.lang.String areaOrganizzativaOmogenea;

    private java.lang.String amministrazione;

    private java.lang.String oggetto;

    private java.lang.Integer idUtente;

    private java.lang.Boolean riservato;

    private java.lang.Boolean invio;

    private it.osapulie.pdds.ws.client.protocollo.protocollo.DatiEmail email;

    private java.lang.String idFascicolo;

    private java.lang.String idTitolario;

    public RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest() {
    }

    public RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest(
           it.osapulie.pdds.ws.client.protocollo.protocollo.MittenteProtUscita mittente,
           it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario[] destinatari,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento,
           it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati,
           java.lang.String areaOrganizzativaOmogenea,
           java.lang.String amministrazione,
           java.lang.String oggetto,
           java.lang.Integer idUtente,
           java.lang.Boolean riservato,
           java.lang.Boolean invio,
           it.osapulie.pdds.ws.client.protocollo.protocollo.DatiEmail email,
           java.lang.String idFascicolo,
           java.lang.String idTitolario) {
           this.mittente = mittente;
           this.destinatari = destinatari;
           this.documento = documento;
           this.allegati = allegati;
           this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
           this.amministrazione = amministrazione;
           this.oggetto = oggetto;
           this.idUtente = idUtente;
           this.riservato = riservato;
           this.invio = invio;
           this.email = email;
           this.idFascicolo = idFascicolo;
           this.idTitolario = idTitolario;
    }


    /**
     * Gets the mittente value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return mittente
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.MittenteProtUscita getMittente() {
        return mittente;
    }


    /**
     * Sets the mittente value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param mittente
     */
    public void setMittente(it.osapulie.pdds.ws.client.protocollo.protocollo.MittenteProtUscita mittente) {
        this.mittente = mittente;
    }


    /**
     * Gets the destinatari value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return destinatari
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario[] getDestinatari() {
        return destinatari;
    }


    /**
     * Sets the destinatari value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param destinatari
     */
    public void setDestinatari(it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario[] destinatari) {
        this.destinatari = destinatari;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario getDestinatari(int i) {
        return this.destinatari[i];
    }

    public void setDestinatari(int i, it.osapulie.pdds.ws.client.protocollo.protocollo.ContattoDestinatario _value) {
        this.destinatari[i] = _value;
    }


    /**
     * Gets the documento value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return documento
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Documento getDocumento() {
        return documento;
    }


    /**
     * Sets the documento value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param documento
     */
    public void setDocumento(it.osapulie.pdds.ws.client.protocollo.protocollo.Documento documento) {
        this.documento = documento;
    }


    /**
     * Gets the allegati value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return allegati
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] getAllegati() {
        return allegati;
    }


    /**
     * Sets the allegati value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param allegati
     */
    public void setAllegati(it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato[] allegati) {
        this.allegati = allegati;
    }

    public it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato getAllegati(int i) {
        return this.allegati[i];
    }

    public void setAllegati(int i, it.osapulie.pdds.ws.client.protocollo.protocollo.Allegato _value) {
        this.allegati[i] = _value;
    }


    /**
     * Gets the areaOrganizzativaOmogenea value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return areaOrganizzativaOmogenea
     */
    public java.lang.String getAreaOrganizzativaOmogenea() {
        return areaOrganizzativaOmogenea;
    }


    /**
     * Sets the areaOrganizzativaOmogenea value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param areaOrganizzativaOmogenea
     */
    public void setAreaOrganizzativaOmogenea(java.lang.String areaOrganizzativaOmogenea) {
        this.areaOrganizzativaOmogenea = areaOrganizzativaOmogenea;
    }


    /**
     * Gets the amministrazione value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return amministrazione
     */
    public java.lang.String getAmministrazione() {
        return amministrazione;
    }


    /**
     * Sets the amministrazione value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param amministrazione
     */
    public void setAmministrazione(java.lang.String amministrazione) {
        this.amministrazione = amministrazione;
    }


    /**
     * Gets the oggetto value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return oggetto
     */
    public java.lang.String getOggetto() {
        return oggetto;
    }


    /**
     * Sets the oggetto value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param oggetto
     */
    public void setOggetto(java.lang.String oggetto) {
        this.oggetto = oggetto;
    }


    /**
     * Gets the idUtente value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return idUtente
     */
    public java.lang.Integer getIdUtente() {
        return idUtente;
    }


    /**
     * Sets the idUtente value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param idUtente
     */
    public void setIdUtente(java.lang.Integer idUtente) {
        this.idUtente = idUtente;
    }


    /**
     * Gets the riservato value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return riservato
     */
    public java.lang.Boolean getRiservato() {
        return riservato;
    }


    /**
     * Sets the riservato value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param riservato
     */
    public void setRiservato(java.lang.Boolean riservato) {
        this.riservato = riservato;
    }


    /**
     * Gets the invio value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return invio
     */
    public java.lang.Boolean getInvio() {
        return invio;
    }


    /**
     * Sets the invio value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param invio
     */
    public void setInvio(java.lang.Boolean invio) {
        this.invio = invio;
    }


    /**
     * Gets the email value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return email
     */
    public it.osapulie.pdds.ws.client.protocollo.protocollo.DatiEmail getEmail() {
        return email;
    }


    /**
     * Sets the email value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param email
     */
    public void setEmail(it.osapulie.pdds.ws.client.protocollo.protocollo.DatiEmail email) {
        this.email = email;
    }


    /**
     * Gets the idFascicolo value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return idFascicolo
     */
    public java.lang.String getIdFascicolo() {
        return idFascicolo;
    }


    /**
     * Sets the idFascicolo value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param idFascicolo
     */
    public void setIdFascicolo(java.lang.String idFascicolo) {
        this.idFascicolo = idFascicolo;
    }


    /**
     * Gets the idTitolario value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @return idTitolario
     */
    public java.lang.String getIdTitolario() {
        return idTitolario;
    }


    /**
     * Sets the idTitolario value for this RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.
     * 
     * @param idTitolario
     */
    public void setIdTitolario(java.lang.String idTitolario) {
        this.idTitolario = idTitolario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest)) return false;
        RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest other = (RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.mittente==null && other.getMittente()==null) || 
             (this.mittente!=null &&
              this.mittente.equals(other.getMittente()))) &&
            ((this.destinatari==null && other.getDestinatari()==null) || 
             (this.destinatari!=null &&
              java.util.Arrays.equals(this.destinatari, other.getDestinatari()))) &&
            ((this.documento==null && other.getDocumento()==null) || 
             (this.documento!=null &&
              this.documento.equals(other.getDocumento()))) &&
            ((this.allegati==null && other.getAllegati()==null) || 
             (this.allegati!=null &&
              java.util.Arrays.equals(this.allegati, other.getAllegati()))) &&
            ((this.areaOrganizzativaOmogenea==null && other.getAreaOrganizzativaOmogenea()==null) || 
             (this.areaOrganizzativaOmogenea!=null &&
              this.areaOrganizzativaOmogenea.equals(other.getAreaOrganizzativaOmogenea()))) &&
            ((this.amministrazione==null && other.getAmministrazione()==null) || 
             (this.amministrazione!=null &&
              this.amministrazione.equals(other.getAmministrazione()))) &&
            ((this.oggetto==null && other.getOggetto()==null) || 
             (this.oggetto!=null &&
              this.oggetto.equals(other.getOggetto()))) &&
            ((this.idUtente==null && other.getIdUtente()==null) || 
             (this.idUtente!=null &&
              this.idUtente.equals(other.getIdUtente()))) &&
            ((this.riservato==null && other.getRiservato()==null) || 
             (this.riservato!=null &&
              this.riservato.equals(other.getRiservato()))) &&
            ((this.invio==null && other.getInvio()==null) || 
             (this.invio!=null &&
              this.invio.equals(other.getInvio()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.idFascicolo==null && other.getIdFascicolo()==null) || 
             (this.idFascicolo!=null &&
              this.idFascicolo.equals(other.getIdFascicolo()))) &&
            ((this.idTitolario==null && other.getIdTitolario()==null) || 
             (this.idTitolario!=null &&
              this.idTitolario.equals(other.getIdTitolario())));
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
        if (getMittente() != null) {
            _hashCode += getMittente().hashCode();
        }
        if (getDestinatari() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getDestinatari());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getDestinatari(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getDocumento() != null) {
            _hashCode += getDocumento().hashCode();
        }
        if (getAllegati() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getAllegati());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getAllegati(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        if (getAreaOrganizzativaOmogenea() != null) {
            _hashCode += getAreaOrganizzativaOmogenea().hashCode();
        }
        if (getAmministrazione() != null) {
            _hashCode += getAmministrazione().hashCode();
        }
        if (getOggetto() != null) {
            _hashCode += getOggetto().hashCode();
        }
        if (getIdUtente() != null) {
            _hashCode += getIdUtente().hashCode();
        }
        if (getRiservato() != null) {
            _hashCode += getRiservato().hashCode();
        }
        if (getInvio() != null) {
            _hashCode += getInvio().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getIdFascicolo() != null) {
            _hashCode += getIdFascicolo().hashCode();
        }
        if (getIdTitolario() != null) {
            _hashCode += getIdTitolario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://server.ws.protocollo.linksmt.it/", ">richiestaProtocolloUscitaFasc>protocolloUscitaFascRequest"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("mittente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "mittente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "MittenteProtUscita"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("destinatari");
        elemField.setXmlName(new javax.xml.namespace.QName("", "destinatari"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "ContattoDestinatario"));
        elemField.setNillable(false);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("documento");
        elemField.setXmlName(new javax.xml.namespace.QName("", "documento"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Documento"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("allegati");
        elemField.setXmlName(new javax.xml.namespace.QName("", "allegati"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "Allegato"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        elemField.setMaxOccursUnbounded(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("areaOrganizzativaOmogenea");
        elemField.setXmlName(new javax.xml.namespace.QName("", "areaOrganizzativaOmogenea"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("amministrazione");
        elemField.setXmlName(new javax.xml.namespace.QName("", "amministrazione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("oggetto");
        elemField.setXmlName(new javax.xml.namespace.QName("", "oggetto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idUtente");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idUtente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("riservato");
        elemField.setXmlName(new javax.xml.namespace.QName("", "riservato"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("invio");
        elemField.setXmlName(new javax.xml.namespace.QName("", "invio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://protocollo.linksmt.it/RichiestaSOAP", "DatiEmail"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idFascicolo");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idFascicolo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("idTitolario");
        elemField.setXmlName(new javax.xml.namespace.QName("", "idTitolario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
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
