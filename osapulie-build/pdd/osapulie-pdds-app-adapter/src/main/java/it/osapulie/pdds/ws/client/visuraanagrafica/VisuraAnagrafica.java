/**
 * VisuraAnagrafica.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.visuraanagrafica;

public class VisuraAnagrafica  implements java.io.Serializable {
    private java.math.BigInteger id;

    private java.lang.String codiceFiscale;

    private java.lang.String cognome;

    private java.lang.String nome;

    private java.util.Date dataNascita;

    private java.lang.String sesso;

    private java.lang.String cap;

    private java.lang.String toponimoIndirizzo;

    private java.lang.String descrizioneVia;

    private java.lang.String numeroCivico;

    private java.lang.String esponente;

    private java.lang.String piano;

    private java.lang.String scala;

    private java.lang.String interno;

    private java.lang.String cognomeNomeConiuge;

    private java.lang.String statoCivile;

    private java.lang.String relazioneParentela;

    private java.lang.Boolean cittadinanzaItaliana;

    private java.lang.String codiceIstatComuneNascita;

    private java.lang.String descrizioneComuneEsteroNascita;

    private java.lang.String statoEsteroNascita;

    private java.math.BigInteger numeroAttoNascita;

    private java.lang.String parteNascita;

    private java.lang.String serieNascita;

    private java.math.BigInteger annoAttoNascita;

    private java.lang.String ufficioNascita;

    private java.math.BigInteger numeroAttoNascitaTrascritto;

    private java.lang.String parteNascitaTrascritto;

    private java.lang.String serieNascitaTrascritto;

    private java.math.BigInteger annoNascitaTrascritto;

    private java.lang.String codiceIstatComuneNascitaTrascritto;

    private java.math.BigInteger numeroAttoMatrimonio;

    private java.lang.String parteMatrimonio;

    private java.lang.String serieMatrimonio;

    private java.math.BigInteger annoMatrimonio;

    private java.lang.String ufficioMatrimonio;

    private java.lang.String codiceIstatComuneMatrimonio;

    private java.lang.String luogoMatrimonio;

    private java.util.Date dataMatrimonio;

    private java.math.BigInteger numeroAttoMatrimonioTrascritto;

    private java.lang.String parteMatrimonioTrascritto;

    private java.lang.String serieMatrimonioTrascritto;

    private java.math.BigInteger annoMatrimonioTrascritto;

    private java.lang.String codiceIstatComuneMatrimonioTrascritto;

    private java.util.Date dataDecorrenzaDivorzio;

    private java.util.Date dataSentenzaDivorzio;

    private java.lang.String numeroSentenzaDivorzio;

    private java.lang.String codiceIstatComuneTribunaleDivorzio;

    private java.lang.String tipoDivorzio;

    private java.util.Date dataAttoDivorzio;

    private java.math.BigInteger numeroAttoDivorzio;

    private java.lang.String parteDivorzio;

    private java.lang.String serieDivorzio;

    private java.lang.String volumeDivorzio;

    private java.lang.String ufficioDivorzio;

    private java.util.Date dataAttoDivorzioTrascritto;

    private java.math.BigInteger numeroAttoDivorzioTrascritto;

    private java.lang.String parteDivorzioTrascritto;

    private java.lang.String serieDivorzioTrascritto;

    private java.lang.String volumeDivorzioTrascritto;

    private java.lang.String ufficioDivorzioTrascritto;

    private java.lang.String codiceIstatComuneTrascrizioneDivorzio;

    private java.util.Date dataAttoVedovanza;

    private java.math.BigInteger numeroAttoVedovanza;

    private java.lang.String parteVedovanza;

    private java.lang.String serieVedovanza;

    private java.lang.String volumeVedovanza;

    private java.lang.String ufficioVedovanza;

    private java.lang.String codiceIstatComuneMorte;

    private java.util.Date dataMorte;

    private java.math.BigInteger numeroAttoMorte;

    private java.lang.String parteMorte;

    private java.lang.String serieMorte;

    private java.lang.String ufficioMorte;

    private java.math.BigInteger annoMorte;

    private java.lang.String codiceIstatComuneMorteConiugeTrascritto;

    private java.math.BigInteger numeroAttoMorteConiugeTrascritto;

    private java.lang.String parteMorteConiugeTrascritto;

    private java.lang.String serieMorteConiugeTrascritto;

    private java.math.BigInteger annoMorteConiugeTrascritto;

    private java.lang.String professione;

    private java.lang.String titoloStudio;

    private java.lang.String numeroCartaIdentita;

    private java.util.Date dataCartaIdentita;

    private java.lang.Boolean validaCartaIdentita;

    private java.math.BigInteger identificativoNucleoFamiliare;

    public VisuraAnagrafica() {
    }

    public VisuraAnagrafica(
           java.math.BigInteger id,
           java.lang.String codiceFiscale,
           java.lang.String cognome,
           java.lang.String nome,
           java.util.Date dataNascita,
           java.lang.String sesso,
           java.lang.String cap,
           java.lang.String toponimoIndirizzo,
           java.lang.String descrizioneVia,
           java.lang.String numeroCivico,
           java.lang.String esponente,
           java.lang.String piano,
           java.lang.String scala,
           java.lang.String interno,
           java.lang.String cognomeNomeConiuge,
           java.lang.String statoCivile,
           java.lang.String relazioneParentela,
           java.lang.Boolean cittadinanzaItaliana,
           java.lang.String codiceIstatComuneNascita,
           java.lang.String descrizioneComuneEsteroNascita,
           java.lang.String statoEsteroNascita,
           java.math.BigInteger numeroAttoNascita,
           java.lang.String parteNascita,
           java.lang.String serieNascita,
           java.math.BigInteger annoAttoNascita,
           java.lang.String ufficioNascita,
           java.math.BigInteger numeroAttoNascitaTrascritto,
           java.lang.String parteNascitaTrascritto,
           java.lang.String serieNascitaTrascritto,
           java.math.BigInteger annoNascitaTrascritto,
           java.lang.String codiceIstatComuneNascitaTrascritto,
           java.math.BigInteger numeroAttoMatrimonio,
           java.lang.String parteMatrimonio,
           java.lang.String serieMatrimonio,
           java.math.BigInteger annoMatrimonio,
           java.lang.String ufficioMatrimonio,
           java.lang.String codiceIstatComuneMatrimonio,
           java.lang.String luogoMatrimonio,
           java.util.Date dataMatrimonio,
           java.math.BigInteger numeroAttoMatrimonioTrascritto,
           java.lang.String parteMatrimonioTrascritto,
           java.lang.String serieMatrimonioTrascritto,
           java.math.BigInteger annoMatrimonioTrascritto,
           java.lang.String codiceIstatComuneMatrimonioTrascritto,
           java.util.Date dataDecorrenzaDivorzio,
           java.util.Date dataSentenzaDivorzio,
           java.lang.String numeroSentenzaDivorzio,
           java.lang.String codiceIstatComuneTribunaleDivorzio,
           java.lang.String tipoDivorzio,
           java.util.Date dataAttoDivorzio,
           java.math.BigInteger numeroAttoDivorzio,
           java.lang.String parteDivorzio,
           java.lang.String serieDivorzio,
           java.lang.String volumeDivorzio,
           java.lang.String ufficioDivorzio,
           java.util.Date dataAttoDivorzioTrascritto,
           java.math.BigInteger numeroAttoDivorzioTrascritto,
           java.lang.String parteDivorzioTrascritto,
           java.lang.String serieDivorzioTrascritto,
           java.lang.String volumeDivorzioTrascritto,
           java.lang.String ufficioDivorzioTrascritto,
           java.lang.String codiceIstatComuneTrascrizioneDivorzio,
           java.util.Date dataAttoVedovanza,
           java.math.BigInteger numeroAttoVedovanza,
           java.lang.String parteVedovanza,
           java.lang.String serieVedovanza,
           java.lang.String volumeVedovanza,
           java.lang.String ufficioVedovanza,
           java.lang.String codiceIstatComuneMorte,
           java.util.Date dataMorte,
           java.math.BigInteger numeroAttoMorte,
           java.lang.String parteMorte,
           java.lang.String serieMorte,
           java.lang.String ufficioMorte,
           java.math.BigInteger annoMorte,
           java.lang.String codiceIstatComuneMorteConiugeTrascritto,
           java.math.BigInteger numeroAttoMorteConiugeTrascritto,
           java.lang.String parteMorteConiugeTrascritto,
           java.lang.String serieMorteConiugeTrascritto,
           java.math.BigInteger annoMorteConiugeTrascritto,
           java.lang.String professione,
           java.lang.String titoloStudio,
           java.lang.String numeroCartaIdentita,
           java.util.Date dataCartaIdentita,
           java.lang.Boolean validaCartaIdentita,
           java.math.BigInteger identificativoNucleoFamiliare) {
           this.id = id;
           this.codiceFiscale = codiceFiscale;
           this.cognome = cognome;
           this.nome = nome;
           this.dataNascita = dataNascita;
           this.sesso = sesso;
           this.cap = cap;
           this.toponimoIndirizzo = toponimoIndirizzo;
           this.descrizioneVia = descrizioneVia;
           this.numeroCivico = numeroCivico;
           this.esponente = esponente;
           this.piano = piano;
           this.scala = scala;
           this.interno = interno;
           this.cognomeNomeConiuge = cognomeNomeConiuge;
           this.statoCivile = statoCivile;
           this.relazioneParentela = relazioneParentela;
           this.cittadinanzaItaliana = cittadinanzaItaliana;
           this.codiceIstatComuneNascita = codiceIstatComuneNascita;
           this.descrizioneComuneEsteroNascita = descrizioneComuneEsteroNascita;
           this.statoEsteroNascita = statoEsteroNascita;
           this.numeroAttoNascita = numeroAttoNascita;
           this.parteNascita = parteNascita;
           this.serieNascita = serieNascita;
           this.annoAttoNascita = annoAttoNascita;
           this.ufficioNascita = ufficioNascita;
           this.numeroAttoNascitaTrascritto = numeroAttoNascitaTrascritto;
           this.parteNascitaTrascritto = parteNascitaTrascritto;
           this.serieNascitaTrascritto = serieNascitaTrascritto;
           this.annoNascitaTrascritto = annoNascitaTrascritto;
           this.codiceIstatComuneNascitaTrascritto = codiceIstatComuneNascitaTrascritto;
           this.numeroAttoMatrimonio = numeroAttoMatrimonio;
           this.parteMatrimonio = parteMatrimonio;
           this.serieMatrimonio = serieMatrimonio;
           this.annoMatrimonio = annoMatrimonio;
           this.ufficioMatrimonio = ufficioMatrimonio;
           this.codiceIstatComuneMatrimonio = codiceIstatComuneMatrimonio;
           this.luogoMatrimonio = luogoMatrimonio;
           this.dataMatrimonio = dataMatrimonio;
           this.numeroAttoMatrimonioTrascritto = numeroAttoMatrimonioTrascritto;
           this.parteMatrimonioTrascritto = parteMatrimonioTrascritto;
           this.serieMatrimonioTrascritto = serieMatrimonioTrascritto;
           this.annoMatrimonioTrascritto = annoMatrimonioTrascritto;
           this.codiceIstatComuneMatrimonioTrascritto = codiceIstatComuneMatrimonioTrascritto;
           this.dataDecorrenzaDivorzio = dataDecorrenzaDivorzio;
           this.dataSentenzaDivorzio = dataSentenzaDivorzio;
           this.numeroSentenzaDivorzio = numeroSentenzaDivorzio;
           this.codiceIstatComuneTribunaleDivorzio = codiceIstatComuneTribunaleDivorzio;
           this.tipoDivorzio = tipoDivorzio;
           this.dataAttoDivorzio = dataAttoDivorzio;
           this.numeroAttoDivorzio = numeroAttoDivorzio;
           this.parteDivorzio = parteDivorzio;
           this.serieDivorzio = serieDivorzio;
           this.volumeDivorzio = volumeDivorzio;
           this.ufficioDivorzio = ufficioDivorzio;
           this.dataAttoDivorzioTrascritto = dataAttoDivorzioTrascritto;
           this.numeroAttoDivorzioTrascritto = numeroAttoDivorzioTrascritto;
           this.parteDivorzioTrascritto = parteDivorzioTrascritto;
           this.serieDivorzioTrascritto = serieDivorzioTrascritto;
           this.volumeDivorzioTrascritto = volumeDivorzioTrascritto;
           this.ufficioDivorzioTrascritto = ufficioDivorzioTrascritto;
           this.codiceIstatComuneTrascrizioneDivorzio = codiceIstatComuneTrascrizioneDivorzio;
           this.dataAttoVedovanza = dataAttoVedovanza;
           this.numeroAttoVedovanza = numeroAttoVedovanza;
           this.parteVedovanza = parteVedovanza;
           this.serieVedovanza = serieVedovanza;
           this.volumeVedovanza = volumeVedovanza;
           this.ufficioVedovanza = ufficioVedovanza;
           this.codiceIstatComuneMorte = codiceIstatComuneMorte;
           this.dataMorte = dataMorte;
           this.numeroAttoMorte = numeroAttoMorte;
           this.parteMorte = parteMorte;
           this.serieMorte = serieMorte;
           this.ufficioMorte = ufficioMorte;
           this.annoMorte = annoMorte;
           this.codiceIstatComuneMorteConiugeTrascritto = codiceIstatComuneMorteConiugeTrascritto;
           this.numeroAttoMorteConiugeTrascritto = numeroAttoMorteConiugeTrascritto;
           this.parteMorteConiugeTrascritto = parteMorteConiugeTrascritto;
           this.serieMorteConiugeTrascritto = serieMorteConiugeTrascritto;
           this.annoMorteConiugeTrascritto = annoMorteConiugeTrascritto;
           this.professione = professione;
           this.titoloStudio = titoloStudio;
           this.numeroCartaIdentita = numeroCartaIdentita;
           this.dataCartaIdentita = dataCartaIdentita;
           this.validaCartaIdentita = validaCartaIdentita;
           this.identificativoNucleoFamiliare = identificativoNucleoFamiliare;
    }


    /**
     * Gets the id value for this VisuraAnagrafica.
     * 
     * @return id
     */
    public java.math.BigInteger getId() {
        return id;
    }


    /**
     * Sets the id value for this VisuraAnagrafica.
     * 
     * @param id
     */
    public void setId(java.math.BigInteger id) {
        this.id = id;
    }


    /**
     * Gets the codiceFiscale value for this VisuraAnagrafica.
     * 
     * @return codiceFiscale
     */
    public java.lang.String getCodiceFiscale() {
        return codiceFiscale;
    }


    /**
     * Sets the codiceFiscale value for this VisuraAnagrafica.
     * 
     * @param codiceFiscale
     */
    public void setCodiceFiscale(java.lang.String codiceFiscale) {
        this.codiceFiscale = codiceFiscale;
    }


    /**
     * Gets the cognome value for this VisuraAnagrafica.
     * 
     * @return cognome
     */
    public java.lang.String getCognome() {
        return cognome;
    }


    /**
     * Sets the cognome value for this VisuraAnagrafica.
     * 
     * @param cognome
     */
    public void setCognome(java.lang.String cognome) {
        this.cognome = cognome;
    }


    /**
     * Gets the nome value for this VisuraAnagrafica.
     * 
     * @return nome
     */
    public java.lang.String getNome() {
        return nome;
    }


    /**
     * Sets the nome value for this VisuraAnagrafica.
     * 
     * @param nome
     */
    public void setNome(java.lang.String nome) {
        this.nome = nome;
    }


    /**
     * Gets the dataNascita value for this VisuraAnagrafica.
     * 
     * @return dataNascita
     */
    public java.util.Date getDataNascita() {
        return dataNascita;
    }


    /**
     * Sets the dataNascita value for this VisuraAnagrafica.
     * 
     * @param dataNascita
     */
    public void setDataNascita(java.util.Date dataNascita) {
        this.dataNascita = dataNascita;
    }


    /**
     * Gets the sesso value for this VisuraAnagrafica.
     * 
     * @return sesso
     */
    public java.lang.String getSesso() {
        return sesso;
    }


    /**
     * Sets the sesso value for this VisuraAnagrafica.
     * 
     * @param sesso
     */
    public void setSesso(java.lang.String sesso) {
        this.sesso = sesso;
    }


    /**
     * Gets the cap value for this VisuraAnagrafica.
     * 
     * @return cap
     */
    public java.lang.String getCap() {
        return cap;
    }


    /**
     * Sets the cap value for this VisuraAnagrafica.
     * 
     * @param cap
     */
    public void setCap(java.lang.String cap) {
        this.cap = cap;
    }


    /**
     * Gets the toponimoIndirizzo value for this VisuraAnagrafica.
     * 
     * @return toponimoIndirizzo
     */
    public java.lang.String getToponimoIndirizzo() {
        return toponimoIndirizzo;
    }


    /**
     * Sets the toponimoIndirizzo value for this VisuraAnagrafica.
     * 
     * @param toponimoIndirizzo
     */
    public void setToponimoIndirizzo(java.lang.String toponimoIndirizzo) {
        this.toponimoIndirizzo = toponimoIndirizzo;
    }


    /**
     * Gets the descrizioneVia value for this VisuraAnagrafica.
     * 
     * @return descrizioneVia
     */
    public java.lang.String getDescrizioneVia() {
        return descrizioneVia;
    }


    /**
     * Sets the descrizioneVia value for this VisuraAnagrafica.
     * 
     * @param descrizioneVia
     */
    public void setDescrizioneVia(java.lang.String descrizioneVia) {
        this.descrizioneVia = descrizioneVia;
    }


    /**
     * Gets the numeroCivico value for this VisuraAnagrafica.
     * 
     * @return numeroCivico
     */
    public java.lang.String getNumeroCivico() {
        return numeroCivico;
    }


    /**
     * Sets the numeroCivico value for this VisuraAnagrafica.
     * 
     * @param numeroCivico
     */
    public void setNumeroCivico(java.lang.String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }


    /**
     * Gets the esponente value for this VisuraAnagrafica.
     * 
     * @return esponente
     */
    public java.lang.String getEsponente() {
        return esponente;
    }


    /**
     * Sets the esponente value for this VisuraAnagrafica.
     * 
     * @param esponente
     */
    public void setEsponente(java.lang.String esponente) {
        this.esponente = esponente;
    }


    /**
     * Gets the piano value for this VisuraAnagrafica.
     * 
     * @return piano
     */
    public java.lang.String getPiano() {
        return piano;
    }


    /**
     * Sets the piano value for this VisuraAnagrafica.
     * 
     * @param piano
     */
    public void setPiano(java.lang.String piano) {
        this.piano = piano;
    }


    /**
     * Gets the scala value for this VisuraAnagrafica.
     * 
     * @return scala
     */
    public java.lang.String getScala() {
        return scala;
    }


    /**
     * Sets the scala value for this VisuraAnagrafica.
     * 
     * @param scala
     */
    public void setScala(java.lang.String scala) {
        this.scala = scala;
    }


    /**
     * Gets the interno value for this VisuraAnagrafica.
     * 
     * @return interno
     */
    public java.lang.String getInterno() {
        return interno;
    }


    /**
     * Sets the interno value for this VisuraAnagrafica.
     * 
     * @param interno
     */
    public void setInterno(java.lang.String interno) {
        this.interno = interno;
    }


    /**
     * Gets the cognomeNomeConiuge value for this VisuraAnagrafica.
     * 
     * @return cognomeNomeConiuge
     */
    public java.lang.String getCognomeNomeConiuge() {
        return cognomeNomeConiuge;
    }


    /**
     * Sets the cognomeNomeConiuge value for this VisuraAnagrafica.
     * 
     * @param cognomeNomeConiuge
     */
    public void setCognomeNomeConiuge(java.lang.String cognomeNomeConiuge) {
        this.cognomeNomeConiuge = cognomeNomeConiuge;
    }


    /**
     * Gets the statoCivile value for this VisuraAnagrafica.
     * 
     * @return statoCivile
     */
    public java.lang.String getStatoCivile() {
        return statoCivile;
    }


    /**
     * Sets the statoCivile value for this VisuraAnagrafica.
     * 
     * @param statoCivile
     */
    public void setStatoCivile(java.lang.String statoCivile) {
        this.statoCivile = statoCivile;
    }


    /**
     * Gets the relazioneParentela value for this VisuraAnagrafica.
     * 
     * @return relazioneParentela
     */
    public java.lang.String getRelazioneParentela() {
        return relazioneParentela;
    }


    /**
     * Sets the relazioneParentela value for this VisuraAnagrafica.
     * 
     * @param relazioneParentela
     */
    public void setRelazioneParentela(java.lang.String relazioneParentela) {
        this.relazioneParentela = relazioneParentela;
    }


    /**
     * Gets the cittadinanzaItaliana value for this VisuraAnagrafica.
     * 
     * @return cittadinanzaItaliana
     */
    public java.lang.Boolean getCittadinanzaItaliana() {
        return cittadinanzaItaliana;
    }


    /**
     * Sets the cittadinanzaItaliana value for this VisuraAnagrafica.
     * 
     * @param cittadinanzaItaliana
     */
    public void setCittadinanzaItaliana(java.lang.Boolean cittadinanzaItaliana) {
        this.cittadinanzaItaliana = cittadinanzaItaliana;
    }


    /**
     * Gets the codiceIstatComuneNascita value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneNascita
     */
    public java.lang.String getCodiceIstatComuneNascita() {
        return codiceIstatComuneNascita;
    }


    /**
     * Sets the codiceIstatComuneNascita value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneNascita
     */
    public void setCodiceIstatComuneNascita(java.lang.String codiceIstatComuneNascita) {
        this.codiceIstatComuneNascita = codiceIstatComuneNascita;
    }


    /**
     * Gets the descrizioneComuneEsteroNascita value for this VisuraAnagrafica.
     * 
     * @return descrizioneComuneEsteroNascita
     */
    public java.lang.String getDescrizioneComuneEsteroNascita() {
        return descrizioneComuneEsteroNascita;
    }


    /**
     * Sets the descrizioneComuneEsteroNascita value for this VisuraAnagrafica.
     * 
     * @param descrizioneComuneEsteroNascita
     */
    public void setDescrizioneComuneEsteroNascita(java.lang.String descrizioneComuneEsteroNascita) {
        this.descrizioneComuneEsteroNascita = descrizioneComuneEsteroNascita;
    }


    /**
     * Gets the statoEsteroNascita value for this VisuraAnagrafica.
     * 
     * @return statoEsteroNascita
     */
    public java.lang.String getStatoEsteroNascita() {
        return statoEsteroNascita;
    }


    /**
     * Sets the statoEsteroNascita value for this VisuraAnagrafica.
     * 
     * @param statoEsteroNascita
     */
    public void setStatoEsteroNascita(java.lang.String statoEsteroNascita) {
        this.statoEsteroNascita = statoEsteroNascita;
    }


    /**
     * Gets the numeroAttoNascita value for this VisuraAnagrafica.
     * 
     * @return numeroAttoNascita
     */
    public java.math.BigInteger getNumeroAttoNascita() {
        return numeroAttoNascita;
    }


    /**
     * Sets the numeroAttoNascita value for this VisuraAnagrafica.
     * 
     * @param numeroAttoNascita
     */
    public void setNumeroAttoNascita(java.math.BigInteger numeroAttoNascita) {
        this.numeroAttoNascita = numeroAttoNascita;
    }


    /**
     * Gets the parteNascita value for this VisuraAnagrafica.
     * 
     * @return parteNascita
     */
    public java.lang.String getParteNascita() {
        return parteNascita;
    }


    /**
     * Sets the parteNascita value for this VisuraAnagrafica.
     * 
     * @param parteNascita
     */
    public void setParteNascita(java.lang.String parteNascita) {
        this.parteNascita = parteNascita;
    }


    /**
     * Gets the serieNascita value for this VisuraAnagrafica.
     * 
     * @return serieNascita
     */
    public java.lang.String getSerieNascita() {
        return serieNascita;
    }


    /**
     * Sets the serieNascita value for this VisuraAnagrafica.
     * 
     * @param serieNascita
     */
    public void setSerieNascita(java.lang.String serieNascita) {
        this.serieNascita = serieNascita;
    }


    /**
     * Gets the annoAttoNascita value for this VisuraAnagrafica.
     * 
     * @return annoAttoNascita
     */
    public java.math.BigInteger getAnnoAttoNascita() {
        return annoAttoNascita;
    }


    /**
     * Sets the annoAttoNascita value for this VisuraAnagrafica.
     * 
     * @param annoAttoNascita
     */
    public void setAnnoAttoNascita(java.math.BigInteger annoAttoNascita) {
        this.annoAttoNascita = annoAttoNascita;
    }


    /**
     * Gets the ufficioNascita value for this VisuraAnagrafica.
     * 
     * @return ufficioNascita
     */
    public java.lang.String getUfficioNascita() {
        return ufficioNascita;
    }


    /**
     * Sets the ufficioNascita value for this VisuraAnagrafica.
     * 
     * @param ufficioNascita
     */
    public void setUfficioNascita(java.lang.String ufficioNascita) {
        this.ufficioNascita = ufficioNascita;
    }


    /**
     * Gets the numeroAttoNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @return numeroAttoNascitaTrascritto
     */
    public java.math.BigInteger getNumeroAttoNascitaTrascritto() {
        return numeroAttoNascitaTrascritto;
    }


    /**
     * Sets the numeroAttoNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @param numeroAttoNascitaTrascritto
     */
    public void setNumeroAttoNascitaTrascritto(java.math.BigInteger numeroAttoNascitaTrascritto) {
        this.numeroAttoNascitaTrascritto = numeroAttoNascitaTrascritto;
    }


    /**
     * Gets the parteNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @return parteNascitaTrascritto
     */
    public java.lang.String getParteNascitaTrascritto() {
        return parteNascitaTrascritto;
    }


    /**
     * Sets the parteNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @param parteNascitaTrascritto
     */
    public void setParteNascitaTrascritto(java.lang.String parteNascitaTrascritto) {
        this.parteNascitaTrascritto = parteNascitaTrascritto;
    }


    /**
     * Gets the serieNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @return serieNascitaTrascritto
     */
    public java.lang.String getSerieNascitaTrascritto() {
        return serieNascitaTrascritto;
    }


    /**
     * Sets the serieNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @param serieNascitaTrascritto
     */
    public void setSerieNascitaTrascritto(java.lang.String serieNascitaTrascritto) {
        this.serieNascitaTrascritto = serieNascitaTrascritto;
    }


    /**
     * Gets the annoNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @return annoNascitaTrascritto
     */
    public java.math.BigInteger getAnnoNascitaTrascritto() {
        return annoNascitaTrascritto;
    }


    /**
     * Sets the annoNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @param annoNascitaTrascritto
     */
    public void setAnnoNascitaTrascritto(java.math.BigInteger annoNascitaTrascritto) {
        this.annoNascitaTrascritto = annoNascitaTrascritto;
    }


    /**
     * Gets the codiceIstatComuneNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneNascitaTrascritto
     */
    public java.lang.String getCodiceIstatComuneNascitaTrascritto() {
        return codiceIstatComuneNascitaTrascritto;
    }


    /**
     * Sets the codiceIstatComuneNascitaTrascritto value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneNascitaTrascritto
     */
    public void setCodiceIstatComuneNascitaTrascritto(java.lang.String codiceIstatComuneNascitaTrascritto) {
        this.codiceIstatComuneNascitaTrascritto = codiceIstatComuneNascitaTrascritto;
    }


    /**
     * Gets the numeroAttoMatrimonio value for this VisuraAnagrafica.
     * 
     * @return numeroAttoMatrimonio
     */
    public java.math.BigInteger getNumeroAttoMatrimonio() {
        return numeroAttoMatrimonio;
    }


    /**
     * Sets the numeroAttoMatrimonio value for this VisuraAnagrafica.
     * 
     * @param numeroAttoMatrimonio
     */
    public void setNumeroAttoMatrimonio(java.math.BigInteger numeroAttoMatrimonio) {
        this.numeroAttoMatrimonio = numeroAttoMatrimonio;
    }


    /**
     * Gets the parteMatrimonio value for this VisuraAnagrafica.
     * 
     * @return parteMatrimonio
     */
    public java.lang.String getParteMatrimonio() {
        return parteMatrimonio;
    }


    /**
     * Sets the parteMatrimonio value for this VisuraAnagrafica.
     * 
     * @param parteMatrimonio
     */
    public void setParteMatrimonio(java.lang.String parteMatrimonio) {
        this.parteMatrimonio = parteMatrimonio;
    }


    /**
     * Gets the serieMatrimonio value for this VisuraAnagrafica.
     * 
     * @return serieMatrimonio
     */
    public java.lang.String getSerieMatrimonio() {
        return serieMatrimonio;
    }


    /**
     * Sets the serieMatrimonio value for this VisuraAnagrafica.
     * 
     * @param serieMatrimonio
     */
    public void setSerieMatrimonio(java.lang.String serieMatrimonio) {
        this.serieMatrimonio = serieMatrimonio;
    }


    /**
     * Gets the annoMatrimonio value for this VisuraAnagrafica.
     * 
     * @return annoMatrimonio
     */
    public java.math.BigInteger getAnnoMatrimonio() {
        return annoMatrimonio;
    }


    /**
     * Sets the annoMatrimonio value for this VisuraAnagrafica.
     * 
     * @param annoMatrimonio
     */
    public void setAnnoMatrimonio(java.math.BigInteger annoMatrimonio) {
        this.annoMatrimonio = annoMatrimonio;
    }


    /**
     * Gets the ufficioMatrimonio value for this VisuraAnagrafica.
     * 
     * @return ufficioMatrimonio
     */
    public java.lang.String getUfficioMatrimonio() {
        return ufficioMatrimonio;
    }


    /**
     * Sets the ufficioMatrimonio value for this VisuraAnagrafica.
     * 
     * @param ufficioMatrimonio
     */
    public void setUfficioMatrimonio(java.lang.String ufficioMatrimonio) {
        this.ufficioMatrimonio = ufficioMatrimonio;
    }


    /**
     * Gets the codiceIstatComuneMatrimonio value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneMatrimonio
     */
    public java.lang.String getCodiceIstatComuneMatrimonio() {
        return codiceIstatComuneMatrimonio;
    }


    /**
     * Sets the codiceIstatComuneMatrimonio value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneMatrimonio
     */
    public void setCodiceIstatComuneMatrimonio(java.lang.String codiceIstatComuneMatrimonio) {
        this.codiceIstatComuneMatrimonio = codiceIstatComuneMatrimonio;
    }


    /**
     * Gets the luogoMatrimonio value for this VisuraAnagrafica.
     * 
     * @return luogoMatrimonio
     */
    public java.lang.String getLuogoMatrimonio() {
        return luogoMatrimonio;
    }


    /**
     * Sets the luogoMatrimonio value for this VisuraAnagrafica.
     * 
     * @param luogoMatrimonio
     */
    public void setLuogoMatrimonio(java.lang.String luogoMatrimonio) {
        this.luogoMatrimonio = luogoMatrimonio;
    }


    /**
     * Gets the dataMatrimonio value for this VisuraAnagrafica.
     * 
     * @return dataMatrimonio
     */
    public java.util.Date getDataMatrimonio() {
        return dataMatrimonio;
    }


    /**
     * Sets the dataMatrimonio value for this VisuraAnagrafica.
     * 
     * @param dataMatrimonio
     */
    public void setDataMatrimonio(java.util.Date dataMatrimonio) {
        this.dataMatrimonio = dataMatrimonio;
    }


    /**
     * Gets the numeroAttoMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @return numeroAttoMatrimonioTrascritto
     */
    public java.math.BigInteger getNumeroAttoMatrimonioTrascritto() {
        return numeroAttoMatrimonioTrascritto;
    }


    /**
     * Sets the numeroAttoMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @param numeroAttoMatrimonioTrascritto
     */
    public void setNumeroAttoMatrimonioTrascritto(java.math.BigInteger numeroAttoMatrimonioTrascritto) {
        this.numeroAttoMatrimonioTrascritto = numeroAttoMatrimonioTrascritto;
    }


    /**
     * Gets the parteMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @return parteMatrimonioTrascritto
     */
    public java.lang.String getParteMatrimonioTrascritto() {
        return parteMatrimonioTrascritto;
    }


    /**
     * Sets the parteMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @param parteMatrimonioTrascritto
     */
    public void setParteMatrimonioTrascritto(java.lang.String parteMatrimonioTrascritto) {
        this.parteMatrimonioTrascritto = parteMatrimonioTrascritto;
    }


    /**
     * Gets the serieMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @return serieMatrimonioTrascritto
     */
    public java.lang.String getSerieMatrimonioTrascritto() {
        return serieMatrimonioTrascritto;
    }


    /**
     * Sets the serieMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @param serieMatrimonioTrascritto
     */
    public void setSerieMatrimonioTrascritto(java.lang.String serieMatrimonioTrascritto) {
        this.serieMatrimonioTrascritto = serieMatrimonioTrascritto;
    }


    /**
     * Gets the annoMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @return annoMatrimonioTrascritto
     */
    public java.math.BigInteger getAnnoMatrimonioTrascritto() {
        return annoMatrimonioTrascritto;
    }


    /**
     * Sets the annoMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @param annoMatrimonioTrascritto
     */
    public void setAnnoMatrimonioTrascritto(java.math.BigInteger annoMatrimonioTrascritto) {
        this.annoMatrimonioTrascritto = annoMatrimonioTrascritto;
    }


    /**
     * Gets the codiceIstatComuneMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneMatrimonioTrascritto
     */
    public java.lang.String getCodiceIstatComuneMatrimonioTrascritto() {
        return codiceIstatComuneMatrimonioTrascritto;
    }


    /**
     * Sets the codiceIstatComuneMatrimonioTrascritto value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneMatrimonioTrascritto
     */
    public void setCodiceIstatComuneMatrimonioTrascritto(java.lang.String codiceIstatComuneMatrimonioTrascritto) {
        this.codiceIstatComuneMatrimonioTrascritto = codiceIstatComuneMatrimonioTrascritto;
    }


    /**
     * Gets the dataDecorrenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @return dataDecorrenzaDivorzio
     */
    public java.util.Date getDataDecorrenzaDivorzio() {
        return dataDecorrenzaDivorzio;
    }


    /**
     * Sets the dataDecorrenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @param dataDecorrenzaDivorzio
     */
    public void setDataDecorrenzaDivorzio(java.util.Date dataDecorrenzaDivorzio) {
        this.dataDecorrenzaDivorzio = dataDecorrenzaDivorzio;
    }


    /**
     * Gets the dataSentenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @return dataSentenzaDivorzio
     */
    public java.util.Date getDataSentenzaDivorzio() {
        return dataSentenzaDivorzio;
    }


    /**
     * Sets the dataSentenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @param dataSentenzaDivorzio
     */
    public void setDataSentenzaDivorzio(java.util.Date dataSentenzaDivorzio) {
        this.dataSentenzaDivorzio = dataSentenzaDivorzio;
    }


    /**
     * Gets the numeroSentenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @return numeroSentenzaDivorzio
     */
    public java.lang.String getNumeroSentenzaDivorzio() {
        return numeroSentenzaDivorzio;
    }


    /**
     * Sets the numeroSentenzaDivorzio value for this VisuraAnagrafica.
     * 
     * @param numeroSentenzaDivorzio
     */
    public void setNumeroSentenzaDivorzio(java.lang.String numeroSentenzaDivorzio) {
        this.numeroSentenzaDivorzio = numeroSentenzaDivorzio;
    }


    /**
     * Gets the codiceIstatComuneTribunaleDivorzio value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneTribunaleDivorzio
     */
    public java.lang.String getCodiceIstatComuneTribunaleDivorzio() {
        return codiceIstatComuneTribunaleDivorzio;
    }


    /**
     * Sets the codiceIstatComuneTribunaleDivorzio value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneTribunaleDivorzio
     */
    public void setCodiceIstatComuneTribunaleDivorzio(java.lang.String codiceIstatComuneTribunaleDivorzio) {
        this.codiceIstatComuneTribunaleDivorzio = codiceIstatComuneTribunaleDivorzio;
    }


    /**
     * Gets the tipoDivorzio value for this VisuraAnagrafica.
     * 
     * @return tipoDivorzio
     */
    public java.lang.String getTipoDivorzio() {
        return tipoDivorzio;
    }


    /**
     * Sets the tipoDivorzio value for this VisuraAnagrafica.
     * 
     * @param tipoDivorzio
     */
    public void setTipoDivorzio(java.lang.String tipoDivorzio) {
        this.tipoDivorzio = tipoDivorzio;
    }


    /**
     * Gets the dataAttoDivorzio value for this VisuraAnagrafica.
     * 
     * @return dataAttoDivorzio
     */
    public java.util.Date getDataAttoDivorzio() {
        return dataAttoDivorzio;
    }


    /**
     * Sets the dataAttoDivorzio value for this VisuraAnagrafica.
     * 
     * @param dataAttoDivorzio
     */
    public void setDataAttoDivorzio(java.util.Date dataAttoDivorzio) {
        this.dataAttoDivorzio = dataAttoDivorzio;
    }


    /**
     * Gets the numeroAttoDivorzio value for this VisuraAnagrafica.
     * 
     * @return numeroAttoDivorzio
     */
    public java.math.BigInteger getNumeroAttoDivorzio() {
        return numeroAttoDivorzio;
    }


    /**
     * Sets the numeroAttoDivorzio value for this VisuraAnagrafica.
     * 
     * @param numeroAttoDivorzio
     */
    public void setNumeroAttoDivorzio(java.math.BigInteger numeroAttoDivorzio) {
        this.numeroAttoDivorzio = numeroAttoDivorzio;
    }


    /**
     * Gets the parteDivorzio value for this VisuraAnagrafica.
     * 
     * @return parteDivorzio
     */
    public java.lang.String getParteDivorzio() {
        return parteDivorzio;
    }


    /**
     * Sets the parteDivorzio value for this VisuraAnagrafica.
     * 
     * @param parteDivorzio
     */
    public void setParteDivorzio(java.lang.String parteDivorzio) {
        this.parteDivorzio = parteDivorzio;
    }


    /**
     * Gets the serieDivorzio value for this VisuraAnagrafica.
     * 
     * @return serieDivorzio
     */
    public java.lang.String getSerieDivorzio() {
        return serieDivorzio;
    }


    /**
     * Sets the serieDivorzio value for this VisuraAnagrafica.
     * 
     * @param serieDivorzio
     */
    public void setSerieDivorzio(java.lang.String serieDivorzio) {
        this.serieDivorzio = serieDivorzio;
    }


    /**
     * Gets the volumeDivorzio value for this VisuraAnagrafica.
     * 
     * @return volumeDivorzio
     */
    public java.lang.String getVolumeDivorzio() {
        return volumeDivorzio;
    }


    /**
     * Sets the volumeDivorzio value for this VisuraAnagrafica.
     * 
     * @param volumeDivorzio
     */
    public void setVolumeDivorzio(java.lang.String volumeDivorzio) {
        this.volumeDivorzio = volumeDivorzio;
    }


    /**
     * Gets the ufficioDivorzio value for this VisuraAnagrafica.
     * 
     * @return ufficioDivorzio
     */
    public java.lang.String getUfficioDivorzio() {
        return ufficioDivorzio;
    }


    /**
     * Sets the ufficioDivorzio value for this VisuraAnagrafica.
     * 
     * @param ufficioDivorzio
     */
    public void setUfficioDivorzio(java.lang.String ufficioDivorzio) {
        this.ufficioDivorzio = ufficioDivorzio;
    }


    /**
     * Gets the dataAttoDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return dataAttoDivorzioTrascritto
     */
    public java.util.Date getDataAttoDivorzioTrascritto() {
        return dataAttoDivorzioTrascritto;
    }


    /**
     * Sets the dataAttoDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param dataAttoDivorzioTrascritto
     */
    public void setDataAttoDivorzioTrascritto(java.util.Date dataAttoDivorzioTrascritto) {
        this.dataAttoDivorzioTrascritto = dataAttoDivorzioTrascritto;
    }


    /**
     * Gets the numeroAttoDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return numeroAttoDivorzioTrascritto
     */
    public java.math.BigInteger getNumeroAttoDivorzioTrascritto() {
        return numeroAttoDivorzioTrascritto;
    }


    /**
     * Sets the numeroAttoDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param numeroAttoDivorzioTrascritto
     */
    public void setNumeroAttoDivorzioTrascritto(java.math.BigInteger numeroAttoDivorzioTrascritto) {
        this.numeroAttoDivorzioTrascritto = numeroAttoDivorzioTrascritto;
    }


    /**
     * Gets the parteDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return parteDivorzioTrascritto
     */
    public java.lang.String getParteDivorzioTrascritto() {
        return parteDivorzioTrascritto;
    }


    /**
     * Sets the parteDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param parteDivorzioTrascritto
     */
    public void setParteDivorzioTrascritto(java.lang.String parteDivorzioTrascritto) {
        this.parteDivorzioTrascritto = parteDivorzioTrascritto;
    }


    /**
     * Gets the serieDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return serieDivorzioTrascritto
     */
    public java.lang.String getSerieDivorzioTrascritto() {
        return serieDivorzioTrascritto;
    }


    /**
     * Sets the serieDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param serieDivorzioTrascritto
     */
    public void setSerieDivorzioTrascritto(java.lang.String serieDivorzioTrascritto) {
        this.serieDivorzioTrascritto = serieDivorzioTrascritto;
    }


    /**
     * Gets the volumeDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return volumeDivorzioTrascritto
     */
    public java.lang.String getVolumeDivorzioTrascritto() {
        return volumeDivorzioTrascritto;
    }


    /**
     * Sets the volumeDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param volumeDivorzioTrascritto
     */
    public void setVolumeDivorzioTrascritto(java.lang.String volumeDivorzioTrascritto) {
        this.volumeDivorzioTrascritto = volumeDivorzioTrascritto;
    }


    /**
     * Gets the ufficioDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @return ufficioDivorzioTrascritto
     */
    public java.lang.String getUfficioDivorzioTrascritto() {
        return ufficioDivorzioTrascritto;
    }


    /**
     * Sets the ufficioDivorzioTrascritto value for this VisuraAnagrafica.
     * 
     * @param ufficioDivorzioTrascritto
     */
    public void setUfficioDivorzioTrascritto(java.lang.String ufficioDivorzioTrascritto) {
        this.ufficioDivorzioTrascritto = ufficioDivorzioTrascritto;
    }


    /**
     * Gets the codiceIstatComuneTrascrizioneDivorzio value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneTrascrizioneDivorzio
     */
    public java.lang.String getCodiceIstatComuneTrascrizioneDivorzio() {
        return codiceIstatComuneTrascrizioneDivorzio;
    }


    /**
     * Sets the codiceIstatComuneTrascrizioneDivorzio value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneTrascrizioneDivorzio
     */
    public void setCodiceIstatComuneTrascrizioneDivorzio(java.lang.String codiceIstatComuneTrascrizioneDivorzio) {
        this.codiceIstatComuneTrascrizioneDivorzio = codiceIstatComuneTrascrizioneDivorzio;
    }


    /**
     * Gets the dataAttoVedovanza value for this VisuraAnagrafica.
     * 
     * @return dataAttoVedovanza
     */
    public java.util.Date getDataAttoVedovanza() {
        return dataAttoVedovanza;
    }


    /**
     * Sets the dataAttoVedovanza value for this VisuraAnagrafica.
     * 
     * @param dataAttoVedovanza
     */
    public void setDataAttoVedovanza(java.util.Date dataAttoVedovanza) {
        this.dataAttoVedovanza = dataAttoVedovanza;
    }


    /**
     * Gets the numeroAttoVedovanza value for this VisuraAnagrafica.
     * 
     * @return numeroAttoVedovanza
     */
    public java.math.BigInteger getNumeroAttoVedovanza() {
        return numeroAttoVedovanza;
    }


    /**
     * Sets the numeroAttoVedovanza value for this VisuraAnagrafica.
     * 
     * @param numeroAttoVedovanza
     */
    public void setNumeroAttoVedovanza(java.math.BigInteger numeroAttoVedovanza) {
        this.numeroAttoVedovanza = numeroAttoVedovanza;
    }


    /**
     * Gets the parteVedovanza value for this VisuraAnagrafica.
     * 
     * @return parteVedovanza
     */
    public java.lang.String getParteVedovanza() {
        return parteVedovanza;
    }


    /**
     * Sets the parteVedovanza value for this VisuraAnagrafica.
     * 
     * @param parteVedovanza
     */
    public void setParteVedovanza(java.lang.String parteVedovanza) {
        this.parteVedovanza = parteVedovanza;
    }


    /**
     * Gets the serieVedovanza value for this VisuraAnagrafica.
     * 
     * @return serieVedovanza
     */
    public java.lang.String getSerieVedovanza() {
        return serieVedovanza;
    }


    /**
     * Sets the serieVedovanza value for this VisuraAnagrafica.
     * 
     * @param serieVedovanza
     */
    public void setSerieVedovanza(java.lang.String serieVedovanza) {
        this.serieVedovanza = serieVedovanza;
    }


    /**
     * Gets the volumeVedovanza value for this VisuraAnagrafica.
     * 
     * @return volumeVedovanza
     */
    public java.lang.String getVolumeVedovanza() {
        return volumeVedovanza;
    }


    /**
     * Sets the volumeVedovanza value for this VisuraAnagrafica.
     * 
     * @param volumeVedovanza
     */
    public void setVolumeVedovanza(java.lang.String volumeVedovanza) {
        this.volumeVedovanza = volumeVedovanza;
    }


    /**
     * Gets the ufficioVedovanza value for this VisuraAnagrafica.
     * 
     * @return ufficioVedovanza
     */
    public java.lang.String getUfficioVedovanza() {
        return ufficioVedovanza;
    }


    /**
     * Sets the ufficioVedovanza value for this VisuraAnagrafica.
     * 
     * @param ufficioVedovanza
     */
    public void setUfficioVedovanza(java.lang.String ufficioVedovanza) {
        this.ufficioVedovanza = ufficioVedovanza;
    }


    /**
     * Gets the codiceIstatComuneMorte value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneMorte
     */
    public java.lang.String getCodiceIstatComuneMorte() {
        return codiceIstatComuneMorte;
    }


    /**
     * Sets the codiceIstatComuneMorte value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneMorte
     */
    public void setCodiceIstatComuneMorte(java.lang.String codiceIstatComuneMorte) {
        this.codiceIstatComuneMorte = codiceIstatComuneMorte;
    }


    /**
     * Gets the dataMorte value for this VisuraAnagrafica.
     * 
     * @return dataMorte
     */
    public java.util.Date getDataMorte() {
        return dataMorte;
    }


    /**
     * Sets the dataMorte value for this VisuraAnagrafica.
     * 
     * @param dataMorte
     */
    public void setDataMorte(java.util.Date dataMorte) {
        this.dataMorte = dataMorte;
    }


    /**
     * Gets the numeroAttoMorte value for this VisuraAnagrafica.
     * 
     * @return numeroAttoMorte
     */
    public java.math.BigInteger getNumeroAttoMorte() {
        return numeroAttoMorte;
    }


    /**
     * Sets the numeroAttoMorte value for this VisuraAnagrafica.
     * 
     * @param numeroAttoMorte
     */
    public void setNumeroAttoMorte(java.math.BigInteger numeroAttoMorte) {
        this.numeroAttoMorte = numeroAttoMorte;
    }


    /**
     * Gets the parteMorte value for this VisuraAnagrafica.
     * 
     * @return parteMorte
     */
    public java.lang.String getParteMorte() {
        return parteMorte;
    }


    /**
     * Sets the parteMorte value for this VisuraAnagrafica.
     * 
     * @param parteMorte
     */
    public void setParteMorte(java.lang.String parteMorte) {
        this.parteMorte = parteMorte;
    }


    /**
     * Gets the serieMorte value for this VisuraAnagrafica.
     * 
     * @return serieMorte
     */
    public java.lang.String getSerieMorte() {
        return serieMorte;
    }


    /**
     * Sets the serieMorte value for this VisuraAnagrafica.
     * 
     * @param serieMorte
     */
    public void setSerieMorte(java.lang.String serieMorte) {
        this.serieMorte = serieMorte;
    }


    /**
     * Gets the ufficioMorte value for this VisuraAnagrafica.
     * 
     * @return ufficioMorte
     */
    public java.lang.String getUfficioMorte() {
        return ufficioMorte;
    }


    /**
     * Sets the ufficioMorte value for this VisuraAnagrafica.
     * 
     * @param ufficioMorte
     */
    public void setUfficioMorte(java.lang.String ufficioMorte) {
        this.ufficioMorte = ufficioMorte;
    }


    /**
     * Gets the annoMorte value for this VisuraAnagrafica.
     * 
     * @return annoMorte
     */
    public java.math.BigInteger getAnnoMorte() {
        return annoMorte;
    }


    /**
     * Sets the annoMorte value for this VisuraAnagrafica.
     * 
     * @param annoMorte
     */
    public void setAnnoMorte(java.math.BigInteger annoMorte) {
        this.annoMorte = annoMorte;
    }


    /**
     * Gets the codiceIstatComuneMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @return codiceIstatComuneMorteConiugeTrascritto
     */
    public java.lang.String getCodiceIstatComuneMorteConiugeTrascritto() {
        return codiceIstatComuneMorteConiugeTrascritto;
    }


    /**
     * Sets the codiceIstatComuneMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @param codiceIstatComuneMorteConiugeTrascritto
     */
    public void setCodiceIstatComuneMorteConiugeTrascritto(java.lang.String codiceIstatComuneMorteConiugeTrascritto) {
        this.codiceIstatComuneMorteConiugeTrascritto = codiceIstatComuneMorteConiugeTrascritto;
    }


    /**
     * Gets the numeroAttoMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @return numeroAttoMorteConiugeTrascritto
     */
    public java.math.BigInteger getNumeroAttoMorteConiugeTrascritto() {
        return numeroAttoMorteConiugeTrascritto;
    }


    /**
     * Sets the numeroAttoMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @param numeroAttoMorteConiugeTrascritto
     */
    public void setNumeroAttoMorteConiugeTrascritto(java.math.BigInteger numeroAttoMorteConiugeTrascritto) {
        this.numeroAttoMorteConiugeTrascritto = numeroAttoMorteConiugeTrascritto;
    }


    /**
     * Gets the parteMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @return parteMorteConiugeTrascritto
     */
    public java.lang.String getParteMorteConiugeTrascritto() {
        return parteMorteConiugeTrascritto;
    }


    /**
     * Sets the parteMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @param parteMorteConiugeTrascritto
     */
    public void setParteMorteConiugeTrascritto(java.lang.String parteMorteConiugeTrascritto) {
        this.parteMorteConiugeTrascritto = parteMorteConiugeTrascritto;
    }


    /**
     * Gets the serieMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @return serieMorteConiugeTrascritto
     */
    public java.lang.String getSerieMorteConiugeTrascritto() {
        return serieMorteConiugeTrascritto;
    }


    /**
     * Sets the serieMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @param serieMorteConiugeTrascritto
     */
    public void setSerieMorteConiugeTrascritto(java.lang.String serieMorteConiugeTrascritto) {
        this.serieMorteConiugeTrascritto = serieMorteConiugeTrascritto;
    }


    /**
     * Gets the annoMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @return annoMorteConiugeTrascritto
     */
    public java.math.BigInteger getAnnoMorteConiugeTrascritto() {
        return annoMorteConiugeTrascritto;
    }


    /**
     * Sets the annoMorteConiugeTrascritto value for this VisuraAnagrafica.
     * 
     * @param annoMorteConiugeTrascritto
     */
    public void setAnnoMorteConiugeTrascritto(java.math.BigInteger annoMorteConiugeTrascritto) {
        this.annoMorteConiugeTrascritto = annoMorteConiugeTrascritto;
    }


    /**
     * Gets the professione value for this VisuraAnagrafica.
     * 
     * @return professione
     */
    public java.lang.String getProfessione() {
        return professione;
    }


    /**
     * Sets the professione value for this VisuraAnagrafica.
     * 
     * @param professione
     */
    public void setProfessione(java.lang.String professione) {
        this.professione = professione;
    }


    /**
     * Gets the titoloStudio value for this VisuraAnagrafica.
     * 
     * @return titoloStudio
     */
    public java.lang.String getTitoloStudio() {
        return titoloStudio;
    }


    /**
     * Sets the titoloStudio value for this VisuraAnagrafica.
     * 
     * @param titoloStudio
     */
    public void setTitoloStudio(java.lang.String titoloStudio) {
        this.titoloStudio = titoloStudio;
    }


    /**
     * Gets the numeroCartaIdentita value for this VisuraAnagrafica.
     * 
     * @return numeroCartaIdentita
     */
    public java.lang.String getNumeroCartaIdentita() {
        return numeroCartaIdentita;
    }


    /**
     * Sets the numeroCartaIdentita value for this VisuraAnagrafica.
     * 
     * @param numeroCartaIdentita
     */
    public void setNumeroCartaIdentita(java.lang.String numeroCartaIdentita) {
        this.numeroCartaIdentita = numeroCartaIdentita;
    }


    /**
     * Gets the dataCartaIdentita value for this VisuraAnagrafica.
     * 
     * @return dataCartaIdentita
     */
    public java.util.Date getDataCartaIdentita() {
        return dataCartaIdentita;
    }


    /**
     * Sets the dataCartaIdentita value for this VisuraAnagrafica.
     * 
     * @param dataCartaIdentita
     */
    public void setDataCartaIdentita(java.util.Date dataCartaIdentita) {
        this.dataCartaIdentita = dataCartaIdentita;
    }


    /**
     * Gets the validaCartaIdentita value for this VisuraAnagrafica.
     * 
     * @return validaCartaIdentita
     */
    public java.lang.Boolean getValidaCartaIdentita() {
        return validaCartaIdentita;
    }


    /**
     * Sets the validaCartaIdentita value for this VisuraAnagrafica.
     * 
     * @param validaCartaIdentita
     */
    public void setValidaCartaIdentita(java.lang.Boolean validaCartaIdentita) {
        this.validaCartaIdentita = validaCartaIdentita;
    }


    /**
     * Gets the identificativoNucleoFamiliare value for this VisuraAnagrafica.
     * 
     * @return identificativoNucleoFamiliare
     */
    public java.math.BigInteger getIdentificativoNucleoFamiliare() {
        return identificativoNucleoFamiliare;
    }


    /**
     * Sets the identificativoNucleoFamiliare value for this VisuraAnagrafica.
     * 
     * @param identificativoNucleoFamiliare
     */
    public void setIdentificativoNucleoFamiliare(java.math.BigInteger identificativoNucleoFamiliare) {
        this.identificativoNucleoFamiliare = identificativoNucleoFamiliare;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VisuraAnagrafica)) return false;
        VisuraAnagrafica other = (VisuraAnagrafica) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.id==null && other.getId()==null) || 
             (this.id!=null &&
              this.id.equals(other.getId()))) &&
            ((this.codiceFiscale==null && other.getCodiceFiscale()==null) || 
             (this.codiceFiscale!=null &&
              this.codiceFiscale.equals(other.getCodiceFiscale()))) &&
            ((this.cognome==null && other.getCognome()==null) || 
             (this.cognome!=null &&
              this.cognome.equals(other.getCognome()))) &&
            ((this.nome==null && other.getNome()==null) || 
             (this.nome!=null &&
              this.nome.equals(other.getNome()))) &&
            ((this.dataNascita==null && other.getDataNascita()==null) || 
             (this.dataNascita!=null &&
              this.dataNascita.equals(other.getDataNascita()))) &&
            ((this.sesso==null && other.getSesso()==null) || 
             (this.sesso!=null &&
              this.sesso.equals(other.getSesso()))) &&
            ((this.cap==null && other.getCap()==null) || 
             (this.cap!=null &&
              this.cap.equals(other.getCap()))) &&
            ((this.toponimoIndirizzo==null && other.getToponimoIndirizzo()==null) || 
             (this.toponimoIndirizzo!=null &&
              this.toponimoIndirizzo.equals(other.getToponimoIndirizzo()))) &&
            ((this.descrizioneVia==null && other.getDescrizioneVia()==null) || 
             (this.descrizioneVia!=null &&
              this.descrizioneVia.equals(other.getDescrizioneVia()))) &&
            ((this.numeroCivico==null && other.getNumeroCivico()==null) || 
             (this.numeroCivico!=null &&
              this.numeroCivico.equals(other.getNumeroCivico()))) &&
            ((this.esponente==null && other.getEsponente()==null) || 
             (this.esponente!=null &&
              this.esponente.equals(other.getEsponente()))) &&
            ((this.piano==null && other.getPiano()==null) || 
             (this.piano!=null &&
              this.piano.equals(other.getPiano()))) &&
            ((this.scala==null && other.getScala()==null) || 
             (this.scala!=null &&
              this.scala.equals(other.getScala()))) &&
            ((this.interno==null && other.getInterno()==null) || 
             (this.interno!=null &&
              this.interno.equals(other.getInterno()))) &&
            ((this.cognomeNomeConiuge==null && other.getCognomeNomeConiuge()==null) || 
             (this.cognomeNomeConiuge!=null &&
              this.cognomeNomeConiuge.equals(other.getCognomeNomeConiuge()))) &&
            ((this.statoCivile==null && other.getStatoCivile()==null) || 
             (this.statoCivile!=null &&
              this.statoCivile.equals(other.getStatoCivile()))) &&
            ((this.relazioneParentela==null && other.getRelazioneParentela()==null) || 
             (this.relazioneParentela!=null &&
              this.relazioneParentela.equals(other.getRelazioneParentela()))) &&
            ((this.cittadinanzaItaliana==null && other.getCittadinanzaItaliana()==null) || 
             (this.cittadinanzaItaliana!=null &&
              this.cittadinanzaItaliana.equals(other.getCittadinanzaItaliana()))) &&
            ((this.codiceIstatComuneNascita==null && other.getCodiceIstatComuneNascita()==null) || 
             (this.codiceIstatComuneNascita!=null &&
              this.codiceIstatComuneNascita.equals(other.getCodiceIstatComuneNascita()))) &&
            ((this.descrizioneComuneEsteroNascita==null && other.getDescrizioneComuneEsteroNascita()==null) || 
             (this.descrizioneComuneEsteroNascita!=null &&
              this.descrizioneComuneEsteroNascita.equals(other.getDescrizioneComuneEsteroNascita()))) &&
            ((this.statoEsteroNascita==null && other.getStatoEsteroNascita()==null) || 
             (this.statoEsteroNascita!=null &&
              this.statoEsteroNascita.equals(other.getStatoEsteroNascita()))) &&
            ((this.numeroAttoNascita==null && other.getNumeroAttoNascita()==null) || 
             (this.numeroAttoNascita!=null &&
              this.numeroAttoNascita.equals(other.getNumeroAttoNascita()))) &&
            ((this.parteNascita==null && other.getParteNascita()==null) || 
             (this.parteNascita!=null &&
              this.parteNascita.equals(other.getParteNascita()))) &&
            ((this.serieNascita==null && other.getSerieNascita()==null) || 
             (this.serieNascita!=null &&
              this.serieNascita.equals(other.getSerieNascita()))) &&
            ((this.annoAttoNascita==null && other.getAnnoAttoNascita()==null) || 
             (this.annoAttoNascita!=null &&
              this.annoAttoNascita.equals(other.getAnnoAttoNascita()))) &&
            ((this.ufficioNascita==null && other.getUfficioNascita()==null) || 
             (this.ufficioNascita!=null &&
              this.ufficioNascita.equals(other.getUfficioNascita()))) &&
            ((this.numeroAttoNascitaTrascritto==null && other.getNumeroAttoNascitaTrascritto()==null) || 
             (this.numeroAttoNascitaTrascritto!=null &&
              this.numeroAttoNascitaTrascritto.equals(other.getNumeroAttoNascitaTrascritto()))) &&
            ((this.parteNascitaTrascritto==null && other.getParteNascitaTrascritto()==null) || 
             (this.parteNascitaTrascritto!=null &&
              this.parteNascitaTrascritto.equals(other.getParteNascitaTrascritto()))) &&
            ((this.serieNascitaTrascritto==null && other.getSerieNascitaTrascritto()==null) || 
             (this.serieNascitaTrascritto!=null &&
              this.serieNascitaTrascritto.equals(other.getSerieNascitaTrascritto()))) &&
            ((this.annoNascitaTrascritto==null && other.getAnnoNascitaTrascritto()==null) || 
             (this.annoNascitaTrascritto!=null &&
              this.annoNascitaTrascritto.equals(other.getAnnoNascitaTrascritto()))) &&
            ((this.codiceIstatComuneNascitaTrascritto==null && other.getCodiceIstatComuneNascitaTrascritto()==null) || 
             (this.codiceIstatComuneNascitaTrascritto!=null &&
              this.codiceIstatComuneNascitaTrascritto.equals(other.getCodiceIstatComuneNascitaTrascritto()))) &&
            ((this.numeroAttoMatrimonio==null && other.getNumeroAttoMatrimonio()==null) || 
             (this.numeroAttoMatrimonio!=null &&
              this.numeroAttoMatrimonio.equals(other.getNumeroAttoMatrimonio()))) &&
            ((this.parteMatrimonio==null && other.getParteMatrimonio()==null) || 
             (this.parteMatrimonio!=null &&
              this.parteMatrimonio.equals(other.getParteMatrimonio()))) &&
            ((this.serieMatrimonio==null && other.getSerieMatrimonio()==null) || 
             (this.serieMatrimonio!=null &&
              this.serieMatrimonio.equals(other.getSerieMatrimonio()))) &&
            ((this.annoMatrimonio==null && other.getAnnoMatrimonio()==null) || 
             (this.annoMatrimonio!=null &&
              this.annoMatrimonio.equals(other.getAnnoMatrimonio()))) &&
            ((this.ufficioMatrimonio==null && other.getUfficioMatrimonio()==null) || 
             (this.ufficioMatrimonio!=null &&
              this.ufficioMatrimonio.equals(other.getUfficioMatrimonio()))) &&
            ((this.codiceIstatComuneMatrimonio==null && other.getCodiceIstatComuneMatrimonio()==null) || 
             (this.codiceIstatComuneMatrimonio!=null &&
              this.codiceIstatComuneMatrimonio.equals(other.getCodiceIstatComuneMatrimonio()))) &&
            ((this.luogoMatrimonio==null && other.getLuogoMatrimonio()==null) || 
             (this.luogoMatrimonio!=null &&
              this.luogoMatrimonio.equals(other.getLuogoMatrimonio()))) &&
            ((this.dataMatrimonio==null && other.getDataMatrimonio()==null) || 
             (this.dataMatrimonio!=null &&
              this.dataMatrimonio.equals(other.getDataMatrimonio()))) &&
            ((this.numeroAttoMatrimonioTrascritto==null && other.getNumeroAttoMatrimonioTrascritto()==null) || 
             (this.numeroAttoMatrimonioTrascritto!=null &&
              this.numeroAttoMatrimonioTrascritto.equals(other.getNumeroAttoMatrimonioTrascritto()))) &&
            ((this.parteMatrimonioTrascritto==null && other.getParteMatrimonioTrascritto()==null) || 
             (this.parteMatrimonioTrascritto!=null &&
              this.parteMatrimonioTrascritto.equals(other.getParteMatrimonioTrascritto()))) &&
            ((this.serieMatrimonioTrascritto==null && other.getSerieMatrimonioTrascritto()==null) || 
             (this.serieMatrimonioTrascritto!=null &&
              this.serieMatrimonioTrascritto.equals(other.getSerieMatrimonioTrascritto()))) &&
            ((this.annoMatrimonioTrascritto==null && other.getAnnoMatrimonioTrascritto()==null) || 
             (this.annoMatrimonioTrascritto!=null &&
              this.annoMatrimonioTrascritto.equals(other.getAnnoMatrimonioTrascritto()))) &&
            ((this.codiceIstatComuneMatrimonioTrascritto==null && other.getCodiceIstatComuneMatrimonioTrascritto()==null) || 
             (this.codiceIstatComuneMatrimonioTrascritto!=null &&
              this.codiceIstatComuneMatrimonioTrascritto.equals(other.getCodiceIstatComuneMatrimonioTrascritto()))) &&
            ((this.dataDecorrenzaDivorzio==null && other.getDataDecorrenzaDivorzio()==null) || 
             (this.dataDecorrenzaDivorzio!=null &&
              this.dataDecorrenzaDivorzio.equals(other.getDataDecorrenzaDivorzio()))) &&
            ((this.dataSentenzaDivorzio==null && other.getDataSentenzaDivorzio()==null) || 
             (this.dataSentenzaDivorzio!=null &&
              this.dataSentenzaDivorzio.equals(other.getDataSentenzaDivorzio()))) &&
            ((this.numeroSentenzaDivorzio==null && other.getNumeroSentenzaDivorzio()==null) || 
             (this.numeroSentenzaDivorzio!=null &&
              this.numeroSentenzaDivorzio.equals(other.getNumeroSentenzaDivorzio()))) &&
            ((this.codiceIstatComuneTribunaleDivorzio==null && other.getCodiceIstatComuneTribunaleDivorzio()==null) || 
             (this.codiceIstatComuneTribunaleDivorzio!=null &&
              this.codiceIstatComuneTribunaleDivorzio.equals(other.getCodiceIstatComuneTribunaleDivorzio()))) &&
            ((this.tipoDivorzio==null && other.getTipoDivorzio()==null) || 
             (this.tipoDivorzio!=null &&
              this.tipoDivorzio.equals(other.getTipoDivorzio()))) &&
            ((this.dataAttoDivorzio==null && other.getDataAttoDivorzio()==null) || 
             (this.dataAttoDivorzio!=null &&
              this.dataAttoDivorzio.equals(other.getDataAttoDivorzio()))) &&
            ((this.numeroAttoDivorzio==null && other.getNumeroAttoDivorzio()==null) || 
             (this.numeroAttoDivorzio!=null &&
              this.numeroAttoDivorzio.equals(other.getNumeroAttoDivorzio()))) &&
            ((this.parteDivorzio==null && other.getParteDivorzio()==null) || 
             (this.parteDivorzio!=null &&
              this.parteDivorzio.equals(other.getParteDivorzio()))) &&
            ((this.serieDivorzio==null && other.getSerieDivorzio()==null) || 
             (this.serieDivorzio!=null &&
              this.serieDivorzio.equals(other.getSerieDivorzio()))) &&
            ((this.volumeDivorzio==null && other.getVolumeDivorzio()==null) || 
             (this.volumeDivorzio!=null &&
              this.volumeDivorzio.equals(other.getVolumeDivorzio()))) &&
            ((this.ufficioDivorzio==null && other.getUfficioDivorzio()==null) || 
             (this.ufficioDivorzio!=null &&
              this.ufficioDivorzio.equals(other.getUfficioDivorzio()))) &&
            ((this.dataAttoDivorzioTrascritto==null && other.getDataAttoDivorzioTrascritto()==null) || 
             (this.dataAttoDivorzioTrascritto!=null &&
              this.dataAttoDivorzioTrascritto.equals(other.getDataAttoDivorzioTrascritto()))) &&
            ((this.numeroAttoDivorzioTrascritto==null && other.getNumeroAttoDivorzioTrascritto()==null) || 
             (this.numeroAttoDivorzioTrascritto!=null &&
              this.numeroAttoDivorzioTrascritto.equals(other.getNumeroAttoDivorzioTrascritto()))) &&
            ((this.parteDivorzioTrascritto==null && other.getParteDivorzioTrascritto()==null) || 
             (this.parteDivorzioTrascritto!=null &&
              this.parteDivorzioTrascritto.equals(other.getParteDivorzioTrascritto()))) &&
            ((this.serieDivorzioTrascritto==null && other.getSerieDivorzioTrascritto()==null) || 
             (this.serieDivorzioTrascritto!=null &&
              this.serieDivorzioTrascritto.equals(other.getSerieDivorzioTrascritto()))) &&
            ((this.volumeDivorzioTrascritto==null && other.getVolumeDivorzioTrascritto()==null) || 
             (this.volumeDivorzioTrascritto!=null &&
              this.volumeDivorzioTrascritto.equals(other.getVolumeDivorzioTrascritto()))) &&
            ((this.ufficioDivorzioTrascritto==null && other.getUfficioDivorzioTrascritto()==null) || 
             (this.ufficioDivorzioTrascritto!=null &&
              this.ufficioDivorzioTrascritto.equals(other.getUfficioDivorzioTrascritto()))) &&
            ((this.codiceIstatComuneTrascrizioneDivorzio==null && other.getCodiceIstatComuneTrascrizioneDivorzio()==null) || 
             (this.codiceIstatComuneTrascrizioneDivorzio!=null &&
              this.codiceIstatComuneTrascrizioneDivorzio.equals(other.getCodiceIstatComuneTrascrizioneDivorzio()))) &&
            ((this.dataAttoVedovanza==null && other.getDataAttoVedovanza()==null) || 
             (this.dataAttoVedovanza!=null &&
              this.dataAttoVedovanza.equals(other.getDataAttoVedovanza()))) &&
            ((this.numeroAttoVedovanza==null && other.getNumeroAttoVedovanza()==null) || 
             (this.numeroAttoVedovanza!=null &&
              this.numeroAttoVedovanza.equals(other.getNumeroAttoVedovanza()))) &&
            ((this.parteVedovanza==null && other.getParteVedovanza()==null) || 
             (this.parteVedovanza!=null &&
              this.parteVedovanza.equals(other.getParteVedovanza()))) &&
            ((this.serieVedovanza==null && other.getSerieVedovanza()==null) || 
             (this.serieVedovanza!=null &&
              this.serieVedovanza.equals(other.getSerieVedovanza()))) &&
            ((this.volumeVedovanza==null && other.getVolumeVedovanza()==null) || 
             (this.volumeVedovanza!=null &&
              this.volumeVedovanza.equals(other.getVolumeVedovanza()))) &&
            ((this.ufficioVedovanza==null && other.getUfficioVedovanza()==null) || 
             (this.ufficioVedovanza!=null &&
              this.ufficioVedovanza.equals(other.getUfficioVedovanza()))) &&
            ((this.codiceIstatComuneMorte==null && other.getCodiceIstatComuneMorte()==null) || 
             (this.codiceIstatComuneMorte!=null &&
              this.codiceIstatComuneMorte.equals(other.getCodiceIstatComuneMorte()))) &&
            ((this.dataMorte==null && other.getDataMorte()==null) || 
             (this.dataMorte!=null &&
              this.dataMorte.equals(other.getDataMorte()))) &&
            ((this.numeroAttoMorte==null && other.getNumeroAttoMorte()==null) || 
             (this.numeroAttoMorte!=null &&
              this.numeroAttoMorte.equals(other.getNumeroAttoMorte()))) &&
            ((this.parteMorte==null && other.getParteMorte()==null) || 
             (this.parteMorte!=null &&
              this.parteMorte.equals(other.getParteMorte()))) &&
            ((this.serieMorte==null && other.getSerieMorte()==null) || 
             (this.serieMorte!=null &&
              this.serieMorte.equals(other.getSerieMorte()))) &&
            ((this.ufficioMorte==null && other.getUfficioMorte()==null) || 
             (this.ufficioMorte!=null &&
              this.ufficioMorte.equals(other.getUfficioMorte()))) &&
            ((this.annoMorte==null && other.getAnnoMorte()==null) || 
             (this.annoMorte!=null &&
              this.annoMorte.equals(other.getAnnoMorte()))) &&
            ((this.codiceIstatComuneMorteConiugeTrascritto==null && other.getCodiceIstatComuneMorteConiugeTrascritto()==null) || 
             (this.codiceIstatComuneMorteConiugeTrascritto!=null &&
              this.codiceIstatComuneMorteConiugeTrascritto.equals(other.getCodiceIstatComuneMorteConiugeTrascritto()))) &&
            ((this.numeroAttoMorteConiugeTrascritto==null && other.getNumeroAttoMorteConiugeTrascritto()==null) || 
             (this.numeroAttoMorteConiugeTrascritto!=null &&
              this.numeroAttoMorteConiugeTrascritto.equals(other.getNumeroAttoMorteConiugeTrascritto()))) &&
            ((this.parteMorteConiugeTrascritto==null && other.getParteMorteConiugeTrascritto()==null) || 
             (this.parteMorteConiugeTrascritto!=null &&
              this.parteMorteConiugeTrascritto.equals(other.getParteMorteConiugeTrascritto()))) &&
            ((this.serieMorteConiugeTrascritto==null && other.getSerieMorteConiugeTrascritto()==null) || 
             (this.serieMorteConiugeTrascritto!=null &&
              this.serieMorteConiugeTrascritto.equals(other.getSerieMorteConiugeTrascritto()))) &&
            ((this.annoMorteConiugeTrascritto==null && other.getAnnoMorteConiugeTrascritto()==null) || 
             (this.annoMorteConiugeTrascritto!=null &&
              this.annoMorteConiugeTrascritto.equals(other.getAnnoMorteConiugeTrascritto()))) &&
            ((this.professione==null && other.getProfessione()==null) || 
             (this.professione!=null &&
              this.professione.equals(other.getProfessione()))) &&
            ((this.titoloStudio==null && other.getTitoloStudio()==null) || 
             (this.titoloStudio!=null &&
              this.titoloStudio.equals(other.getTitoloStudio()))) &&
            ((this.numeroCartaIdentita==null && other.getNumeroCartaIdentita()==null) || 
             (this.numeroCartaIdentita!=null &&
              this.numeroCartaIdentita.equals(other.getNumeroCartaIdentita()))) &&
            ((this.dataCartaIdentita==null && other.getDataCartaIdentita()==null) || 
             (this.dataCartaIdentita!=null &&
              this.dataCartaIdentita.equals(other.getDataCartaIdentita()))) &&
            ((this.validaCartaIdentita==null && other.getValidaCartaIdentita()==null) || 
             (this.validaCartaIdentita!=null &&
              this.validaCartaIdentita.equals(other.getValidaCartaIdentita()))) &&
            ((this.identificativoNucleoFamiliare==null && other.getIdentificativoNucleoFamiliare()==null) || 
             (this.identificativoNucleoFamiliare!=null &&
              this.identificativoNucleoFamiliare.equals(other.getIdentificativoNucleoFamiliare())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getId() != null) {
            _hashCode += getId().hashCode();
        }
        if (getCodiceFiscale() != null) {
            _hashCode += getCodiceFiscale().hashCode();
        }
        if (getCognome() != null) {
            _hashCode += getCognome().hashCode();
        }
        if (getNome() != null) {
            _hashCode += getNome().hashCode();
        }
        if (getDataNascita() != null) {
            _hashCode += getDataNascita().hashCode();
        }
        if (getSesso() != null) {
            _hashCode += getSesso().hashCode();
        }
        if (getCap() != null) {
            _hashCode += getCap().hashCode();
        }
        if (getToponimoIndirizzo() != null) {
            _hashCode += getToponimoIndirizzo().hashCode();
        }
        if (getDescrizioneVia() != null) {
            _hashCode += getDescrizioneVia().hashCode();
        }
        if (getNumeroCivico() != null) {
            _hashCode += getNumeroCivico().hashCode();
        }
        if (getEsponente() != null) {
            _hashCode += getEsponente().hashCode();
        }
        if (getPiano() != null) {
            _hashCode += getPiano().hashCode();
        }
        if (getScala() != null) {
            _hashCode += getScala().hashCode();
        }
        if (getInterno() != null) {
            _hashCode += getInterno().hashCode();
        }
        if (getCognomeNomeConiuge() != null) {
            _hashCode += getCognomeNomeConiuge().hashCode();
        }
        if (getStatoCivile() != null) {
            _hashCode += getStatoCivile().hashCode();
        }
        if (getRelazioneParentela() != null) {
            _hashCode += getRelazioneParentela().hashCode();
        }
        if (getCittadinanzaItaliana() != null) {
            _hashCode += getCittadinanzaItaliana().hashCode();
        }
        if (getCodiceIstatComuneNascita() != null) {
            _hashCode += getCodiceIstatComuneNascita().hashCode();
        }
        if (getDescrizioneComuneEsteroNascita() != null) {
            _hashCode += getDescrizioneComuneEsteroNascita().hashCode();
        }
        if (getStatoEsteroNascita() != null) {
            _hashCode += getStatoEsteroNascita().hashCode();
        }
        if (getNumeroAttoNascita() != null) {
            _hashCode += getNumeroAttoNascita().hashCode();
        }
        if (getParteNascita() != null) {
            _hashCode += getParteNascita().hashCode();
        }
        if (getSerieNascita() != null) {
            _hashCode += getSerieNascita().hashCode();
        }
        if (getAnnoAttoNascita() != null) {
            _hashCode += getAnnoAttoNascita().hashCode();
        }
        if (getUfficioNascita() != null) {
            _hashCode += getUfficioNascita().hashCode();
        }
        if (getNumeroAttoNascitaTrascritto() != null) {
            _hashCode += getNumeroAttoNascitaTrascritto().hashCode();
        }
        if (getParteNascitaTrascritto() != null) {
            _hashCode += getParteNascitaTrascritto().hashCode();
        }
        if (getSerieNascitaTrascritto() != null) {
            _hashCode += getSerieNascitaTrascritto().hashCode();
        }
        if (getAnnoNascitaTrascritto() != null) {
            _hashCode += getAnnoNascitaTrascritto().hashCode();
        }
        if (getCodiceIstatComuneNascitaTrascritto() != null) {
            _hashCode += getCodiceIstatComuneNascitaTrascritto().hashCode();
        }
        if (getNumeroAttoMatrimonio() != null) {
            _hashCode += getNumeroAttoMatrimonio().hashCode();
        }
        if (getParteMatrimonio() != null) {
            _hashCode += getParteMatrimonio().hashCode();
        }
        if (getSerieMatrimonio() != null) {
            _hashCode += getSerieMatrimonio().hashCode();
        }
        if (getAnnoMatrimonio() != null) {
            _hashCode += getAnnoMatrimonio().hashCode();
        }
        if (getUfficioMatrimonio() != null) {
            _hashCode += getUfficioMatrimonio().hashCode();
        }
        if (getCodiceIstatComuneMatrimonio() != null) {
            _hashCode += getCodiceIstatComuneMatrimonio().hashCode();
        }
        if (getLuogoMatrimonio() != null) {
            _hashCode += getLuogoMatrimonio().hashCode();
        }
        if (getDataMatrimonio() != null) {
            _hashCode += getDataMatrimonio().hashCode();
        }
        if (getNumeroAttoMatrimonioTrascritto() != null) {
            _hashCode += getNumeroAttoMatrimonioTrascritto().hashCode();
        }
        if (getParteMatrimonioTrascritto() != null) {
            _hashCode += getParteMatrimonioTrascritto().hashCode();
        }
        if (getSerieMatrimonioTrascritto() != null) {
            _hashCode += getSerieMatrimonioTrascritto().hashCode();
        }
        if (getAnnoMatrimonioTrascritto() != null) {
            _hashCode += getAnnoMatrimonioTrascritto().hashCode();
        }
        if (getCodiceIstatComuneMatrimonioTrascritto() != null) {
            _hashCode += getCodiceIstatComuneMatrimonioTrascritto().hashCode();
        }
        if (getDataDecorrenzaDivorzio() != null) {
            _hashCode += getDataDecorrenzaDivorzio().hashCode();
        }
        if (getDataSentenzaDivorzio() != null) {
            _hashCode += getDataSentenzaDivorzio().hashCode();
        }
        if (getNumeroSentenzaDivorzio() != null) {
            _hashCode += getNumeroSentenzaDivorzio().hashCode();
        }
        if (getCodiceIstatComuneTribunaleDivorzio() != null) {
            _hashCode += getCodiceIstatComuneTribunaleDivorzio().hashCode();
        }
        if (getTipoDivorzio() != null) {
            _hashCode += getTipoDivorzio().hashCode();
        }
        if (getDataAttoDivorzio() != null) {
            _hashCode += getDataAttoDivorzio().hashCode();
        }
        if (getNumeroAttoDivorzio() != null) {
            _hashCode += getNumeroAttoDivorzio().hashCode();
        }
        if (getParteDivorzio() != null) {
            _hashCode += getParteDivorzio().hashCode();
        }
        if (getSerieDivorzio() != null) {
            _hashCode += getSerieDivorzio().hashCode();
        }
        if (getVolumeDivorzio() != null) {
            _hashCode += getVolumeDivorzio().hashCode();
        }
        if (getUfficioDivorzio() != null) {
            _hashCode += getUfficioDivorzio().hashCode();
        }
        if (getDataAttoDivorzioTrascritto() != null) {
            _hashCode += getDataAttoDivorzioTrascritto().hashCode();
        }
        if (getNumeroAttoDivorzioTrascritto() != null) {
            _hashCode += getNumeroAttoDivorzioTrascritto().hashCode();
        }
        if (getParteDivorzioTrascritto() != null) {
            _hashCode += getParteDivorzioTrascritto().hashCode();
        }
        if (getSerieDivorzioTrascritto() != null) {
            _hashCode += getSerieDivorzioTrascritto().hashCode();
        }
        if (getVolumeDivorzioTrascritto() != null) {
            _hashCode += getVolumeDivorzioTrascritto().hashCode();
        }
        if (getUfficioDivorzioTrascritto() != null) {
            _hashCode += getUfficioDivorzioTrascritto().hashCode();
        }
        if (getCodiceIstatComuneTrascrizioneDivorzio() != null) {
            _hashCode += getCodiceIstatComuneTrascrizioneDivorzio().hashCode();
        }
        if (getDataAttoVedovanza() != null) {
            _hashCode += getDataAttoVedovanza().hashCode();
        }
        if (getNumeroAttoVedovanza() != null) {
            _hashCode += getNumeroAttoVedovanza().hashCode();
        }
        if (getParteVedovanza() != null) {
            _hashCode += getParteVedovanza().hashCode();
        }
        if (getSerieVedovanza() != null) {
            _hashCode += getSerieVedovanza().hashCode();
        }
        if (getVolumeVedovanza() != null) {
            _hashCode += getVolumeVedovanza().hashCode();
        }
        if (getUfficioVedovanza() != null) {
            _hashCode += getUfficioVedovanza().hashCode();
        }
        if (getCodiceIstatComuneMorte() != null) {
            _hashCode += getCodiceIstatComuneMorte().hashCode();
        }
        if (getDataMorte() != null) {
            _hashCode += getDataMorte().hashCode();
        }
        if (getNumeroAttoMorte() != null) {
            _hashCode += getNumeroAttoMorte().hashCode();
        }
        if (getParteMorte() != null) {
            _hashCode += getParteMorte().hashCode();
        }
        if (getSerieMorte() != null) {
            _hashCode += getSerieMorte().hashCode();
        }
        if (getUfficioMorte() != null) {
            _hashCode += getUfficioMorte().hashCode();
        }
        if (getAnnoMorte() != null) {
            _hashCode += getAnnoMorte().hashCode();
        }
        if (getCodiceIstatComuneMorteConiugeTrascritto() != null) {
            _hashCode += getCodiceIstatComuneMorteConiugeTrascritto().hashCode();
        }
        if (getNumeroAttoMorteConiugeTrascritto() != null) {
            _hashCode += getNumeroAttoMorteConiugeTrascritto().hashCode();
        }
        if (getParteMorteConiugeTrascritto() != null) {
            _hashCode += getParteMorteConiugeTrascritto().hashCode();
        }
        if (getSerieMorteConiugeTrascritto() != null) {
            _hashCode += getSerieMorteConiugeTrascritto().hashCode();
        }
        if (getAnnoMorteConiugeTrascritto() != null) {
            _hashCode += getAnnoMorteConiugeTrascritto().hashCode();
        }
        if (getProfessione() != null) {
            _hashCode += getProfessione().hashCode();
        }
        if (getTitoloStudio() != null) {
            _hashCode += getTitoloStudio().hashCode();
        }
        if (getNumeroCartaIdentita() != null) {
            _hashCode += getNumeroCartaIdentita().hashCode();
        }
        if (getDataCartaIdentita() != null) {
            _hashCode += getDataCartaIdentita().hashCode();
        }
        if (getValidaCartaIdentita() != null) {
            _hashCode += getValidaCartaIdentita().hashCode();
        }
        if (getIdentificativoNucleoFamiliare() != null) {
            _hashCode += getIdentificativoNucleoFamiliare().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VisuraAnagrafica.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://servizi.osapulie.it", "visuraAnagrafica"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("id");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "id"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceFiscale");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceFiscale"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "cognome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nome");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "nome"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("sesso");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "sesso"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cap");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "cap"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("toponimoIndirizzo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "toponimoIndirizzo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneVia");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneVia"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCivico");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroCivico"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("esponente");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "esponente"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("piano");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "piano"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("scala");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "scala"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("interno");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "interno"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cognomeNomeConiuge");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "cognomeNomeConiuge"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoCivile");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "statoCivile"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("relazioneParentela");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "relazioneParentela"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("cittadinanzaItaliana");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "cittadinanzaItaliana"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("descrizioneComuneEsteroNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "descrizioneComuneEsteroNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("statoEsteroNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "statoEsteroNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoAttoNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoAttoNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioNascita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioNascita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoNascitaTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoNascitaTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteNascitaTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteNascitaTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieNascitaTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieNascitaTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoNascitaTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoNascitaTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneNascitaTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneNascitaTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("luogoMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "luogoMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataMatrimonio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataMatrimonio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoMatrimonioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoMatrimonioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteMatrimonioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteMatrimonioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieMatrimonioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieMatrimonioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoMatrimonioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoMatrimonioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneMatrimonioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneMatrimonioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataDecorrenzaDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataDecorrenzaDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataSentenzaDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataSentenzaDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroSentenzaDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroSentenzaDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneTribunaleDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneTribunaleDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("tipoDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "tipoDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAttoDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataAttoDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volumeDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "volumeDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAttoDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataAttoDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volumeDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "volumeDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioDivorzioTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioDivorzioTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneTrascrizioneDivorzio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneTrascrizioneDivorzio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataAttoVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataAttoVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("volumeVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "volumeVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioVedovanza");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioVedovanza"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ufficioMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "ufficioMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoMorte");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoMorte"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("codiceIstatComuneMorteConiugeTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "codiceIstatComuneMorteConiugeTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroAttoMorteConiugeTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroAttoMorteConiugeTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("parteMorteConiugeTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "parteMorteConiugeTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("serieMorteConiugeTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "serieMorteConiugeTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("annoMorteConiugeTrascritto");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "annoMorteConiugeTrascritto"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("professione");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "professione"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("titoloStudio");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "titoloStudio"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("numeroCartaIdentita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "numeroCartaIdentita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("dataCartaIdentita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "dataCartaIdentita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "date"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validaCartaIdentita");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "validaCartaIdentita"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "boolean"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("identificativoNucleoFamiliare");
        elemField.setXmlName(new javax.xml.namespace.QName("http://servizi.osapulie.it", "identificativoNucleoFamiliare"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "integer"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
