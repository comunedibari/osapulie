/**
 * VisuraVariazioniDomicilioServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuravariazionidomicilioservice;

public interface VisuraVariazioniDomicilioServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuravariazionidomicilioservice.VisuraVariazioneDomicilio[] getVisureVariazioniDomicilio(java.lang.String codiceFiscale, java.util.Date dataInizio, java.util.Date dataFine) throws java.rmi.RemoteException;
}
