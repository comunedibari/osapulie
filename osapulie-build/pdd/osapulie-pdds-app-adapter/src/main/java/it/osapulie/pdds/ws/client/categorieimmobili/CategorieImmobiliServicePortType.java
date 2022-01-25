/**
 * CategorieImmobiliServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.osapulie.pdds.ws.client.categorieimmobili;

public interface CategorieImmobiliServicePortType extends java.rmi.Remote {
    public void update_tb_agevolazione_operation(java.lang.String nome, double valore, int ID) throws java.rmi.RemoteException;
    public void delete_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[] select_all_tb_categoriaimmobile_agevolazione_operation() throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_with_key_tb_categoriaimmobile_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_all_tb_categoriaimmobile_by_anno_operation(int anno) throws java.rmi.RemoteException;
    public void delete_tb_tipologia_detrazione_operation(int ID) throws java.rmi.RemoteException;
    public void insert_tb_esenzione_operation(int ID, java.lang.String descrizione, int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException;
    public void insert_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_agevolazione) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[] select_with_key_tb_tipologia_detrazione_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[] select_all_tb_categoriaimmobile_detrazione_operation() throws java.rmi.RemoteException;
    public void insert_tb_tributo_operation(int ID, java.lang.String nome) throws java.rmi.RemoteException;
    public void update_tb_tipologia_detrazione_operation(java.lang.String codice, java.lang.String descrizione, int ID) throws java.rmi.RemoteException;
    public void delete_tb_detrazione_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[] select_with_key_tb_detrazione_operation(int ID) throws java.rmi.RemoteException;
    public void update_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_agevolazione) throws java.rmi.RemoteException;
    public void insert_tb_detrazione_operation(int ID, java.lang.String descrizione, double importo, java.lang.String info, int fk_tipologia_detrazione) throws java.rmi.RemoteException;
    public void insert_tb_categoriaimmobile_operation(int ID, java.lang.String codice, java.lang.String descrizione, double coefficienteRivalutazione, double coefficienteMoltiplicazione, double percentualeProprietario, double percentualeInquilinoComodatario) throws java.rmi.RemoteException;
    public void insert_tb_tipologia_detrazione_operation(int ID, java.lang.String codice, java.lang.String descrizione) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_agevolazione[] select_with_key_tb_categoriaimmobile_agevolazione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[] select_with_key_tb_esenzione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[] select_with_key_tb_tributo_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[] select_with_key_tb_agevolazione_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_all_tb_categoriaimmobile_tributo_by_anno_operation(int anno) throws java.rmi.RemoteException;
    public void update_tb_tributo_operation(java.lang.String nome, int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_all_tb_categoriaimmobile_tributo_operation() throws java.rmi.RemoteException;
    public void delete_tb_esenzione_operation(int ID) throws java.rmi.RemoteException;
    public void update_tb_detrazione_operation(java.lang.String descrizione, double importo, java.lang.String info, int fk_tipologia_detrazione, int ID) throws java.rmi.RemoteException;
    public void update_tb_categoriaimmobile_tributo_operation(int fk_tributo, int codice_tributo, double aliquota, int anno, int fk_categoriaimmobile) throws java.rmi.RemoteException;
    public void insert_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile, int fk_tributo, int codice_tributo, double aliquota, int anno) throws java.rmi.RemoteException;
    public void delete_tb_tributo_operation(int ID) throws java.rmi.RemoteException;
    public void insert_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile, int fk_tributo, int fk_detrazione) throws java.rmi.RemoteException;
    public void delete_tb_categoriaimmobile_operation(int ID) throws java.rmi.RemoteException;
    public void update_tb_categoriaimmobile_detrazione_operation(int fk_tributo, int fk_detrazione, int fk_categoriaimmobile) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_tributo[] select_with_key_tb_categoriaimmobile_tributo_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_agevolazione[] select_all_tb_agevolazione_operation() throws java.rmi.RemoteException;
    public void delete_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile) throws java.rmi.RemoteException;
    public void insert_tb_agevolazione_operation(int ID, java.lang.String nome, double valore) throws java.rmi.RemoteException;
    public void delete_tb_categoriaimmobile_agevolazione_operation(int fk_agevolazione) throws java.rmi.RemoteException;
    public void delete_tb_agevolazione_operation(int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile_detrazione[] select_with_key_tb_categoriaimmobile_detrazione_operation(int fk_categoriaimmobile, int fk_tributo) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_categoriaimmobile[] select_all_tb_categoriaimmobile_operation() throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tributo[] select_all_tb_tributo_operation() throws java.rmi.RemoteException;
    public void update_tb_categoriaimmobile_operation(java.lang.String codice, java.lang.String descrizione, double coefficienteRivalutazione, double coefficienteMoltiplicazione, double percentualeProprietario, double percentualeInquilinoComodatario, int ID) throws java.rmi.RemoteException;
    public void update_tb_esenzione_operation(java.lang.String descrizione, int fk_categoriaimmobile, int fk_tributo, int ID) throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_tipologia_detrazione[] select_all_tb_tipologia_detrazione_operation() throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_detrazione[] select_all_tb_detrazione_operation() throws java.rmi.RemoteException;
    public it.osapulie.pdds.ws.client.categorieimmobili.Tb_esenzione[] select_all_tb_esenzione_operation() throws java.rmi.RemoteException;
}
