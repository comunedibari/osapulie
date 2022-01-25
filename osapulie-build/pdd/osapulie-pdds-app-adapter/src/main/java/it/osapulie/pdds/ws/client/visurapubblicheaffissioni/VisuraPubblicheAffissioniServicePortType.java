/**
 * VisuraPubblicheAffissioniServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visurapubblicheaffissioni;

public interface VisuraPubblicheAffissioniServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[] getRateVisuraPubblicheAffissioni(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.PosizioneVisuraPubblicheAffissioni[] getPosizioniVisuraPubblicheAffissioni(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[] getVisurePubblicheAffissioni(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.RataVisuraPubblicheAffissioni[] getRataVisuraPubblicheAffissioni(java.lang.String identificativoRata) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visurapubblicheaffissioni.VisuraPubblicheAffissioni[] getVisuraPubblicheAffissioni(int id) throws java.rmi.RemoteException;
}
