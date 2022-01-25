/**
 * VisuraTassaRifiutiServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuratassarifiuti;

public interface VisuraTassaRifiutiServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuratassarifiuti.OccupazionePosizioneVisuraTassaRifiuti[] getOccupazioniPosizioneVisuraTassaRifiuti(int idPosizione) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.RataVisuraTassaRifiuti[] getRateVisuraTassaRifiuti(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiuti[] getVisuraTassaRifiuti(int id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.RataVisuraTassaRifiuti[] getRataVisuraTassaRifiuti(java.lang.String identificativoRata) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.VisuraTassaRifiuti[] getVisureTassaRifiuti(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.RiduzionePosizioneVisuraTassaRifiuti[] getRiduzioniVisuraTassaRifiuti(int idPosizione) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassarifiuti.PosizioneVisuraTassaRifiuti[] getPosizioniVisuraTassaRifiuti(int idVisura) throws java.rmi.RemoteException;
}
