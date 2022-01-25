/**
 * ProtocolloServer.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.protocollo.protocollo;

public interface ProtocolloServer extends java.rmi.Remote {
    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloResponseReturn getProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetProtocolloProtocolloInformazioniRequest protocolloInformazioniRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloResponseReturn getDestinatariProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetDestinatariProtocolloDestinatarioProtocolloRequest destinatarioProtocolloRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioResponseReturn completamentoProtocolloProvvisorio(it.osapulie.pdds.ws.client.protocollo.protocollo.CompletamentoProtocolloProvvisorioProtocolloDaCompletareRequest protocolloDaCompletareRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloResponseReturn richiestaProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProtocolloRequest protocolloRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoResponseReturn getAllegato(it.osapulie.pdds.ws.client.protocollo.protocollo.GetAllegatoDettaglioAllegatoRequest dettaglioAllegatoRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloResponseReturn inoltraProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.InoltraProtocolloInoltroProtocolloRequest inoltroProtocolloRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaResponseReturn richiestaProtocolloUscita(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaProtocolloUscitaRequest protocolloUscitaRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascResponseReturn richiestaProtocolloUscitaFasc(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloUscitaFascProtocolloUscitaFascRequest protocolloUscitaFascRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascResponseReturn richiestaProtocolloFasc(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloFascProtocolloFascRequest protocolloFascRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaResponseReturn addAllegatura(it.osapulie.pdds.ws.client.protocollo.protocollo.AddAllegaturaAllegaturaRequest allegaturaRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloResponseReturn getMittenteProtocollo(it.osapulie.pdds.ws.client.protocollo.protocollo.GetMittenteProtocolloMittenteProtocolloRequest mittenteProtocolloRequest) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioResponseReturn richiestaProtocolloProvvisorio(it.osapulie.pdds.ws.client.protocollo.protocollo.RichiestaProtocolloProvvisorioProtocolloProvvisorioRequest protocolloProvvisorioRequest) throws java.rmi.RemoteException;
}
