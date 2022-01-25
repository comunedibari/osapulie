/**
 * VisuraOsapPermanenteServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraosappermanente;

public interface VisuraOsapPermanenteServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuraosappermanente.RataVisuraOsapPermanente[] getRateVisuraOsapPermanente(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosappermanente.PosizioneVisuraOsapPermanente[] getPosizioniVisuraOsapPermanente(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanente[] getVisuraOsapPermanente(int id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosappermanente.RataVisuraOsapPermanente[] getRataVisuraOsapPermanente(java.lang.String identificativoRata) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraosappermanente.VisuraOsapPermanente[] getVisureOsapPermanente(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
}
