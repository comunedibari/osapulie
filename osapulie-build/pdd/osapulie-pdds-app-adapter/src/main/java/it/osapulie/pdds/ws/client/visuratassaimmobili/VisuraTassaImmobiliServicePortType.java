/**
 * VisuraTassaImmobiliServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuratassaimmobili;

public interface VisuraTassaImmobiliServicePortType extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobili[] getVisureTassaImmobili(java.lang.String codiceFiscale, int annoDa, int annoA) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassaimmobili.PosizioneVisuraTassaImmobili[] getPosizioniVisuraTassaImmobili(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassaimmobili.RataVisuraTassaImmobili[] getRateVisuraTassaImmobili(int idVisura) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassaimmobili.VisuraTassaImmobili[] getVisuraTassaImmobili(int id) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.visuratassaimmobili.RataVisuraTassaImmobili[] getRataVisuraTassaImmobili(java.lang.String identificativoRata) throws java.rmi.RemoteException;
}
