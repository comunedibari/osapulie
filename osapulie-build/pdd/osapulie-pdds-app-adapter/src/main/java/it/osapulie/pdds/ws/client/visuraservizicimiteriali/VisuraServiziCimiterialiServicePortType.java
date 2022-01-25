/**
 * VisuraServiziCimiterialiServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraservizicimiteriali;

public interface VisuraServiziCimiterialiServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.RataVisuraServiziCimiteriali[] getRataVisuraServiziCimiteriali(java.lang.String identificativoRata) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.DefuntoPosizioneVisuraServiziCimiteriali[] getDefuntiPosizioneVisuraServiziCimiteriali(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.RataVisuraServiziCimiteriali[] getRateVisuraServiziCimiteriali(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.PosizioneVisuraServiziCimiteriali[] getPosizioniVisuraServiziCimiteriali(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiteriali[] getVisuraServiziCimiteriali(int id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.LampadaVotivaPosizioneVisuraServiziCimiteriali[] getLampadeVotivePosizioneVisuraServiziCimiteriali(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuraservizicimiteriali.VisuraServiziCimiteriali[] getVisureServiziCimiteriali(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
}
