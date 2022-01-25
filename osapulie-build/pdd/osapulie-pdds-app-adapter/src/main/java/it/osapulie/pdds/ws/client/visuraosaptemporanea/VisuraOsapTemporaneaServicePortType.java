/**
 * VisuraOsapTemporaneaServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraosaptemporanea;

public interface VisuraOsapTemporaneaServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporanea[] getVisureOsapTemporanea(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosaptemporanea.PosizioneVisuraOsapTemporanea[] getPosizioniVisuraOsapTemporanea(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosaptemporanea.VisuraOsapTemporanea[] getVisuraOsapTemporanea(java.lang.String numeroDocumento) throws java.rmi.RemoteException;
}
