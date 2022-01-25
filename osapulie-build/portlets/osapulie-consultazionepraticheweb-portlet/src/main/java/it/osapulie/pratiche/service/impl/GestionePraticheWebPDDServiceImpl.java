package it.osapulie.pratiche.service.impl;

import it.osapulie.pratiche.service.GestionePraticheWebService;
import it.osapulie.pratiche.service.ServiziSUE;
import it.osapulie.pratiche.web.ws.types.AllegatiDto;
import it.osapulie.pratiche.web.ws.types.CategoriePraticheDto;
import it.osapulie.pratiche.web.ws.types.ComuneDto;
import it.osapulie.pratiche.web.ws.types.DatiRicercaPraticaWeb;
import it.osapulie.pratiche.web.ws.types.DocInfo;
import it.osapulie.pratiche.web.ws.types.DocumentiContenuti;
import it.osapulie.pratiche.web.ws.types.DocumentiContenutiResponse;
import it.osapulie.pratiche.web.ws.types.DownloadDocumento;
import it.osapulie.pratiche.web.ws.types.DownloadDocumentoResponse;
import it.osapulie.pratiche.web.ws.types.EliminaAllegatoResponse;
import it.osapulie.pratiche.web.ws.types.EliminaDocumento;
import it.osapulie.pratiche.web.ws.types.EliminaDocumentoResponse;
import it.osapulie.pratiche.web.ws.types.GetTipoImmobileByPk;
import it.osapulie.pratiche.web.ws.types.GetTipoImmobileByPkResponse;
import it.osapulie.pratiche.web.ws.types.GetTipoPraticaByPk;
import it.osapulie.pratiche.web.ws.types.GetTipoPraticaByPkResponse;
import it.osapulie.pratiche.web.ws.types.PraticaDto;
import it.osapulie.pratiche.web.ws.types.PraticaWebDto;
import it.osapulie.pratiche.web.ws.types.ProfiloUtenteDto;
import it.osapulie.pratiche.web.ws.types.SavePratica;
import it.osapulie.pratiche.web.ws.types.SavePraticaResponse;
import it.osapulie.pratiche.web.ws.types.SearchAllPraticheWeb;
import it.osapulie.pratiche.web.ws.types.SearchAllPraticheWebResponse;
import it.osapulie.pratiche.web.ws.types.SearchAllegatiTipoPratica;
import it.osapulie.pratiche.web.ws.types.SearchAllegatiTipoPraticaResponse;
import it.osapulie.pratiche.web.ws.types.SearchCategoriePratiche;
import it.osapulie.pratiche.web.ws.types.SearchCategoriePraticheResponse;
import it.osapulie.pratiche.web.ws.types.SearchPraticaByPk;
import it.osapulie.pratiche.web.ws.types.SearchPraticaByPkResponse;
import it.osapulie.pratiche.web.ws.types.SearchPraticaWebByPk;
import it.osapulie.pratiche.web.ws.types.SearchPraticaWebByPkResponse;
import it.osapulie.pratiche.web.ws.types.SearchPratiche;
import it.osapulie.pratiche.web.ws.types.SearchPraticheResponse;
import it.osapulie.pratiche.web.ws.types.SearchPraticheWebByUtente;
import it.osapulie.pratiche.web.ws.types.SearchPraticheWebByUtenteResponse;
import it.osapulie.pratiche.web.ws.types.SearchStatoPratiche;
import it.osapulie.pratiche.web.ws.types.SearchStatoPraticheResponse;
import it.osapulie.pratiche.web.ws.types.SearchTipoImmobile;
import it.osapulie.pratiche.web.ws.types.SearchTipoImmobileResponse;
import it.osapulie.pratiche.web.ws.types.SearchTipoPraticheFrontend;
import it.osapulie.pratiche.web.ws.types.SearchTipoPraticheFrontendResponse;
import it.osapulie.pratiche.web.ws.types.StatoPraticaDto;
import it.osapulie.pratiche.web.ws.types.TipoImmobileDto;
import it.osapulie.pratiche.web.ws.types.TipoPraticaDto;
import it.osapulie.pratiche.web.ws.types.UploadAllegatiToPratica;
import it.osapulie.pratiche.web.ws.types.UploadAllegatiToPraticaResponse;
import it.osapulie.pratiche.web.ws.types.UploadFile;
import it.osapulie.pratiche.web.ws.types.UploadFileResponse;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.service.impl.AbstractServiceImpl;
import it.osapulie.shared.service.UserPreferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementazione di {@link GestionePraticheWebService}
 *
 * @author Maria Michela Birtolo
 */
// @Service("gestionePraticheWebService")
public class GestionePraticheWebPDDServiceImpl extends AbstractServiceImpl implements GestionePraticheWebService {

	private static Logger log = LoggerFactory.getLogger(GestionePraticheWebPDDServiceImpl.class);

	/**
	 * Metodo che ricerca le pratiche
	 */
	@Override
	public List<PraticaDto> searchPratiche(DatiRicercaPraticaWeb filtriRicerca, UserPreferences userPreferences) {

		log.debug("searchPratiche :: entering method");

		// List<PraticaDto> ret = new ArrayList<PraticaDto>( );
		SearchPratiche req = new SearchPratiche();
		req.setArg0(null);
		req.setArg1(filtriRicerca);

		return esegui(ServiziSUE.RICHIESTA_RICERCA_PRATICHE, req, SearchPraticheResponse.class, userPreferences).getReturn().getPratiche();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.PraticaService#getPraticaByPk(long)
	 */
	@Override
	public PraticaDto getPraticaByPk(long pk, UserPreferences userPreferences) throws ServiceLayerException {

		log.debug("getPraticaByPk :: entering method");
		SearchPraticaByPk req = new SearchPraticaByPk();
		req.setArg0(null);
		req.setArg1(pk);
		return esegui(ServiziSUE.RICHIESTA_GET_PRATICA_BYPK, req, SearchPraticaByPkResponse.class, userPreferences).getReturn();

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see it.osapulie.service.PraticaService#getPraticaByPk(long)
	 */
	@Override
	public PraticaWebDto getPraticaWebByPk(long pk, UserPreferences userPreferences) throws ServiceLayerException {

		log.debug("getPraticaWebByPk :: entering method");

		SearchPraticaWebByPk req = new SearchPraticaWebByPk();
		req.setArg0(null);
		req.setArg1(pk);

		return esegui(ServiziSUE.RICHIESTA_GET_PRATICAWEB_BYPK, req, SearchPraticaWebByPkResponse.class, userPreferences).getReturn();

	}

	@Override
	public SavePraticaResponse savePratica(PraticaWebDto pratica, UserPreferences userPreferences) throws ServiceLayerException {

		log.debug("savePratica :: entering method");
		SavePratica req = new SavePratica();
		req.setArg0(null);
		req.setArg1(pratica);
		try {
			return esegui(ServiziSUE.RICHIESTA_SALVA_PRATICA, req, SavePraticaResponse.class, userPreferences);

		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when saving an object of type Pratica :: " + e.getMessage(), e);
		}
	}

	@Override
	public List<PraticaWebDto> searchPraticheWeb(ProfiloUtenteDto profiloutente, UserPreferences userPreferences) {

		log.debug("searchPraticheWeb :: entering method");

		// List<PraticaWebDto> ret = new ArrayList<PraticaWebDto>();
		SearchPraticheWebByUtente req = new SearchPraticheWebByUtente();
		req.setArg0(null);
		req.setArg1(profiloutente);

		return esegui(ServiziSUE.RICHIESTA_RICERCA_PRATICHE_WEB, req, SearchPraticheWebByUtenteResponse.class, userPreferences).getReturn();

	}

	@Override
	public List<PraticaWebDto> searchPraticheWeb(UserPreferences userPreferences) {

		log.debug("searchPraticheWeb :: entering method");

		// List<PraticaWebDto> ret = new ArrayList<PraticaWebDto>();

		SearchAllPraticheWeb req = new SearchAllPraticheWeb();
		req.setArg0(null);

		return esegui(ServiziSUE.RICHIESTA_RICERCA_ALL_PRATICHE_WEB, req, SearchAllPraticheWebResponse.class, userPreferences).getReturn();

	}

	@Override
	public UploadFileResponse processaUploadPratica(String tipologia, String fileName, byte[] content, ProfiloUtenteDto profiloUtente, String idPratica, String uuidContenitorePratica,
			UserPreferences userPreferences) throws ServiceLayerException, Exception {

		log.debug("processaUploadPratica :: entering method");
		try {
			UploadFile req = new UploadFile();
			req.setArg0(null);
			req.setArg1(tipologia);
			req.setArg2(fileName);
			req.setArg3(content);
			req.setArg4(profiloUtente);
			req.setArg5(idPratica);
			req.setArg6(uuidContenitorePratica);

			return esegui(ServiziSUE.RICHIESTA_UPLOAD_PRATICA, req, UploadFileResponse.class, userPreferences);
		}
		catch (Exception e) {
			log.error("processaUploadPratica :: " + e.getMessage(), e);
			throw new IOException("processaUploadPratica", e);
		}

	}
	
	@Override
	public UploadAllegatiToPraticaResponse uploadAllegatiToPratica(String fileName, byte[] content, String numeroPratica,Long idAllegato,UserPreferences userPreferences) throws ServiceLayerException,Exception{
		log.debug("uploadAllegatiToPratica :: entering method");
		try {
			UploadAllegatiToPratica req = new UploadAllegatiToPratica();
			req.setArg0(null);
			req.setArg1(fileName);
			req.setArg2(content);
			req.setArg3(numeroPratica);
			req.setArg4(idAllegato);
			
			return esegui(ServiziSUE.RICHIESTA_UPLOAD_ALLEGATI_TO_PRATICA, req, UploadAllegatiToPraticaResponse.class, userPreferences);
		}
		catch (Exception e) {
			log.error("uploadAllegatiToPratica :: " + e.getMessage(), e);
			throw new IOException("uploadAllegatiToPratica", e);
		}
	}

	@Override
	public byte[] downloadDocumento(String uuidContenitore, String uuidDocumento, UserPreferences userPreferences) throws ServiceLayerException {
		log.debug("downloadDocumento :: entering method");

		DownloadDocumento req = new DownloadDocumento();
		req.setArg0(null);
		req.setArg1(uuidContenitore);
		req.setArg2(uuidDocumento);

		try {
			return esegui(ServiziSUE.RICHIESTA_DOWNLOAD_DOC, req, DownloadDocumentoResponse.class, userPreferences).getReturn();
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when upload an object of type File :: " + e.getMessage(), e);
		}
	}

	@Override
	public List<TipoPraticaDto> searchTipoPraticheFrontEnd(UserPreferences userPreferences) {
		log.debug("searchTipoPraticheFrontEnd:: entering method");

		SearchTipoPraticheFrontend req = new SearchTipoPraticheFrontend();
		req.setArg0(null);
		SearchTipoPraticheFrontendResponse resp = esegui(ServiziSUE.RICHIESTA_RICERCA_TIPO_PRATICHE, req, SearchTipoPraticheFrontendResponse.class, userPreferences);

		return resp.getReturn().getTipoPratica();
	}
	
	@Override
	public String searchCategoriePratiche(UserPreferences userPreferences) {
		log.debug("searchCategoriePratiche:: entering method");

		SearchCategoriePratiche req = new SearchCategoriePratiche();
		req.setArg0(null);
		SearchCategoriePraticheResponse resp = esegui(ServiziSUE.RICHIESTA_RICERCA_CATEGORIE_PRATICHE, req, SearchCategoriePraticheResponse.class, userPreferences);

		return resp.getReturn().getCategorie();
	}

	@Override
	public List<ComuneDto> searchComuni(UserPreferences userPreferences) {
		log.debug("searchComuni:: entering method");

		return null;
	}

	@Override
	public List<TipoImmobileDto> searchTipoImmobile(UserPreferences userPreferences) {
		log.debug("searchTipoImmobile :: entering method");

		SearchTipoImmobile req = new SearchTipoImmobile();
		req.setArg0(null);
		SearchTipoImmobileResponse resp = esegui(ServiziSUE.RICHIESTA_RICERCA_ALL_TIPO_IMMOBILE, req, SearchTipoImmobileResponse.class, userPreferences);

		return resp.getReturn().getTipoImmobile();
	}

	@Override
	public List<StatoPraticaDto> searchStatoPratica(UserPreferences userPreferences) {
		log.debug("searchStatoPratica :: entering method");

		// List<StatoPraticaDto> ret = new ArrayList<StatoPraticaDto>();

		SearchStatoPratiche req = new SearchStatoPratiche();
		req.setArg0(null);

		return esegui(ServiziSUE.RICHIESTA_RICERCA_STATO_PRATICA, req, SearchStatoPraticheResponse.class, userPreferences).getReturn();

	}

	@Override
	public List<AllegatiDto> searchAllegatiTipoPratica(TipoPraticaDto tipo, UserPreferences userPreferences) throws ServiceLayerException {

		log.debug("searchAllegatiTipoPratica :: entering method");
		List<AllegatiDto> result = new ArrayList<AllegatiDto>();

		SearchAllegatiTipoPratica req = new SearchAllegatiTipoPratica();
		req.setArg0(null);
		req.setArg1(tipo);

		try {
			result = esegui(ServiziSUE.RICHIESTA_RICERCA_ALLEGATI_TIPO_PRATICA, req, SearchAllegatiTipoPraticaResponse.class, userPreferences).getReturn();
		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when upload an object of type File :: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public List<DocInfo> documentiContenuti(String uuidContenitore, UserPreferences userPreferences) throws ServiceLayerException {
		log.debug("documentiContenuti :: entering method");
		List<DocInfo> result = null;

		DocumentiContenuti req = new DocumentiContenuti();
		req.setArg0(null);
		req.setArg1(uuidContenitore);

		try {
			result = esegui(ServiziSUE.RICHIESTA_RICERCA_DOC_CONTENUTI, req, DocumentiContenutiResponse.class, userPreferences).getReturn();

		}
		catch (Exception e) {
			throw new ServiceLayerException("There was an error when upload an object of type File :: " + e.getMessage(), e);
		}
		return result;
	}

	@Override
	public TipoPraticaDto getTipoPraticaByPk(long pk, UserPreferences userPreferences) throws ServiceLayerException {
		log.debug("getTipoPraticaByPk :: entering method");

		GetTipoPraticaByPk req = new GetTipoPraticaByPk();
		req.setArg0(null);
		req.setArg1(pk);
		return esegui(ServiziSUE.RICHIESTA_RICERCA_TIPO_PRATICA, req, GetTipoPraticaByPkResponse.class, userPreferences).getReturn();
	}

	@Override
	public TipoImmobileDto getTipoImmobileByPk(long pk, UserPreferences userPreferences) throws ServiceLayerException {
		log.debug("getTipoImmobileByPk :: entering method");
		GetTipoImmobileByPk req = new GetTipoImmobileByPk();
		req.setArg0(null);
		req.setArg1(pk);
		return esegui(ServiziSUE.RICHIESTA_RICERCA_TIPO_IMMOBILE, req, GetTipoImmobileByPkResponse.class, userPreferences).getReturn();
	}
	
	@Override
	public EliminaAllegatoResponse eliminaDocumento(String uuidAllegato,UserPreferences userPreferences) throws ServiceLayerException {
		log.debug("eliminaDocumento :: entering method");
		EliminaAllegatoResponse result = null;

		EliminaDocumento req = new EliminaDocumento();
		req.setArg0(null);
		req.setArg1(uuidAllegato);

		try {
			result = esegui(ServiziSUE.RICHIESTA_ELIMINA_DOCUMENTO, req, EliminaDocumentoResponse.class, userPreferences).getReturn();
		}
		catch (Exception e) {			 
			throw new ServiceLayerException("There was an error when upload an object of type File :: " + e.getMessage(), e);
		}
		return result;
	}

}
