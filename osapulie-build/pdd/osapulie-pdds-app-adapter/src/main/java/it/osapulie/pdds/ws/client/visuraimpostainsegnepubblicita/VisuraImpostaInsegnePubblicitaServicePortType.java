/**
 * VisuraImpostaInsegnePubblicitaServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita;

public interface VisuraImpostaInsegnePubblicitaServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.RataVisuraImposteInsegnePubblicita[] getRataVisuraImposteInsegnePubblicita(java.lang.String identificativoRata) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.PosizioneVisuraImposteInsegnePubblicita[] getPosizioniVisuraImposteInsegnePubblicita(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImposteInsegnePubblicita[] getVisureImposteInsegnePubblicita(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.VisuraImposteInsegnePubblicita[] getVisuraImposteInsegnePubblicita(int id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraimpostainsegnepubblicita.RataVisuraImposteInsegnePubblicita[] getRateVisuraImposteInsegnePubblicita(int idVisura) throws java.rmi.RemoteException;
}
