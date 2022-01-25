/**
 * VisuraAnagraficaServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraanagrafica;

public interface VisuraAnagraficaServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuraanagrafica.VisuraAnagrafica[] getVisuraAnagrafica(java.lang.String codiceFiscale) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraanagrafica.VisuraAnagrafica[] getVisureAnagraficheNucleoFamiliare(int identificativoNucleoFamiliare) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraanagrafica.PensioneComponenteVisuraAnagrafica[] getPensioniComponenteVisuraAnagrafica(int idComponente) throws java.rmi.RemoteException;
}
