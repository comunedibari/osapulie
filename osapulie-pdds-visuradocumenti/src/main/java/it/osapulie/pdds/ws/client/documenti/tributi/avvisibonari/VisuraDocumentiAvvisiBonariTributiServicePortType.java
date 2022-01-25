/**
 * VisuraDocumentiAvvisiBonariTributiServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari;

public interface VisuraDocumentiAvvisiBonariTributiServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento[] getDocumento(int matricola, int anno, int tipo) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.documenti.tributi.avvisibonari.Documento[] getDocumenti(java.lang.String codiceFiscale, int anno) throws java.rmi.RemoteException;
}
