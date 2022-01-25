package it.osapulie.pratiche.service;

import java.util.List;

import it.osapulie.pratiche.web.ws.types.AllegatiDto;
import it.osapulie.pratiche.web.ws.types.CategoriePraticheDto;
import it.osapulie.pratiche.web.ws.types.ComuneDto;
import it.osapulie.pratiche.web.ws.types.DatiRicercaPraticaWeb;
import it.osapulie.pratiche.web.ws.types.DocInfo;
import it.osapulie.pratiche.web.ws.types.EliminaAllegatoResponse;
import it.osapulie.pratiche.web.ws.types.PraticaDto;
import it.osapulie.pratiche.web.ws.types.PraticaWebDto;
import it.osapulie.pratiche.web.ws.types.ProfiloUtenteDto;
import it.osapulie.pratiche.web.ws.types.SavePraticaResponse;
import it.osapulie.pratiche.web.ws.types.StatoPraticaDto;
import it.osapulie.pratiche.web.ws.types.TipoImmobileDto;
import it.osapulie.pratiche.web.ws.types.TipoPraticaDto;
import it.osapulie.pratiche.web.ws.types.UploadAllegatiToPraticaResponse;
import it.osapulie.pratiche.web.ws.types.UploadFileResponse;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.shared.service.UserPreferences;

/**
 * 
 * @author Maria Michela Birtolo
 * 
 */
public interface GestionePraticheWebService {
	
	/**
	 * Metodo che recupera l'elenco delle pratiche relative ai parametri di ricerca inseriti
	 * @param filtriRicercaPraticheOnline
	 * @return List<Pratica>
	 */
	List<PraticaDto> searchPratiche(DatiRicercaPraticaWeb filtriRicercaPraticheOnline,UserPreferences userPreferences);
	
	/**
	 * Ritorna la pratica a partire dal suo ID.
	 * 
	 * @param pk
	 * @return Pratica
	 * @throws ServiceLayerException
	 */
	public PraticaDto getPraticaByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Ritorna la praticaweb a partire dal suo ID.
	 * 
	 * @param pk
	 * @return PraticaWeb
	 * @throws ServiceLayerException
	 */
	public PraticaWebDto getPraticaWebByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Ritorna la tipologia di praticaweb a partire dal suo ID.
	 * 
	 * @param pk
	 * @return PraticaWeb
	 * @throws ServiceLayerException
	 */
	public TipoPraticaDto getTipoPraticaByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Ritorna la tipologia di immobile a partire dal suo ID.
	 * 
	 * @param pk
	 * @return PraticaWeb
	 * @throws ServiceLayerException
	 */
	public TipoImmobileDto getTipoImmobileByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Ritorna la lista di allegati di una tipologia di pratica.
	 * 
	 * @param tipo
	 * @return List<Allegati>
	 * @throws ServiceLayerException
	 */
	public List<AllegatiDto> searchAllegatiTipoPratica(TipoPraticaDto tipo,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Salva una pratica nel sistema.
	 * 
	 * @param pratica
	 * @throws ServiceLayerException
	 */
	public SavePraticaResponse savePratica(PraticaWebDto pratica,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Metodo che recupera l'elenco delle pratiche relative ai parametri di ricerca inseriti
	 * @param profiloutente
	 * @return List<PraticaWeb>
	 */
	public List<PraticaWebDto> searchPraticheWeb(ProfiloUtenteDto profiloutente ,UserPreferences userPreferences);
	
	/**
	 * Metodo che recupera l'elenco delle richieste di pratiche
	 * @return List<PraticaWeb>
	 */
	public List<PraticaWebDto> searchPraticheWeb(UserPreferences userPreferences);
	
	/**
	 * Metodo che si occupa di gestire l'inoltra della pratica alla pec del comune
	 * @param tipologia
	 * @param uploadItem
	 * @param profiloUtente
	 * @throws ServiceLayerException
	 */
	public UploadFileResponse processaUploadPratica(String tipologia, String fileName, byte[] content, ProfiloUtenteDto profiloUtente,String idPratica, String uuidContenitorePratica,UserPreferences userPreferences) throws ServiceLayerException,Exception;
	
	/**
	 * Metodo che si occupa di gestire l'upload di un allegato in una specifica pratica
	 * @param fileName
	 * @param content
	 * @param numeroPratica
	 * @param idAllegato
	 * @param userPreferences
	 * @return UploadAllegatiToPraticaResponse
	 * @throws ServiceLayerException
	 * @throws Exception
	 */
	public UploadAllegatiToPraticaResponse uploadAllegatiToPratica(String fileName, byte[] content, String numeroPratica,Long idAllegato,UserPreferences userPreferences) throws ServiceLayerException,Exception;

	/**
	 * Metodo per effettuare il download di un allegato di una pratica
	 * @param uuidContenitore - Folder contenente il documento da scaricare
	 * @param uuidDocumento - documento da scaricare
	 * @return byte[]
	 * @throws ServiceLayerException
	 * 
	 */
	public byte[] downloadDocumento(String uuidContenitore,String uuidDocumento,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Metodo che recupera le tipologie di pratica relative al frontend
	 * @return List<TipoPratica>
	 */
	public List<TipoPraticaDto> searchTipoPraticheFrontEnd(UserPreferences userPreferences);
	
	/**
	 * metodo che recupera l'elenco delle categorie di pratica corrispondenti alle tipologie visibili nel front-end nel formato json
	 * @param userPreferences
	 * @return String
	 */
	public String searchCategoriePratiche(UserPreferences userPreferences);
	
	public List<ComuneDto> searchComuni(UserPreferences userPreferences);
	
	/**
	 * Metodo che recupera la lista di TipoImmobile
	 * @return List<TipoImmobile>
	 */
	public List<TipoImmobileDto> searchTipoImmobile(UserPreferences userPreferences);
	
	/**
	 * Metodo che recupera la lista degli stati possibile per una pratica
	 * @return List<StatoPratica>
	 */
	public List<StatoPraticaDto> searchStatoPratica(UserPreferences userPreferences);
	
	/**
	 * Restituisce i doc contenuti nella cartella il cui uuid e' fornita in input.
	 * @param uuidContenitore
	 * @return List<DocInfo>
	 * @throws ServiceLayerException
	 */
	public List<DocInfo> documentiContenuti(String uuidContenitore,UserPreferences userPreferences) throws ServiceLayerException;
	
	/**
	 * Metodo che elimina un allegato
	 * @return Boolean
	 */
	public EliminaAllegatoResponse eliminaDocumento(String uuidAllegato,UserPreferences userPreferences) throws ServiceLayerException;
}
