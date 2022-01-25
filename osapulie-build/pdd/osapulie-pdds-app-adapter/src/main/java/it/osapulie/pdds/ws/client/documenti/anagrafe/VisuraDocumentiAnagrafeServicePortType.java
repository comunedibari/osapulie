/**
 * VisuraDocumentiAnagrafeServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.documenti.anagrafe;

public interface VisuraDocumentiAnagrafeServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.documenti.anagrafe.Documento[] getDocumento(java.lang.String id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.documenti.anagrafe.Documento[] getDocumenti(java.lang.String codiceFiscale, java.lang.String codiceServizio, int anno) throws java.rmi.RemoteException;
}
