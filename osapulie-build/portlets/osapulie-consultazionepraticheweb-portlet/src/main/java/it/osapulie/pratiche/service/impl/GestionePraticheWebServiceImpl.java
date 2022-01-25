package it.osapulie.pratiche.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import it.osapulie.pratiche.service.GestionePraticheWebService;
import it.osapulie.pratiche.service.ServiziSUE;
import it.osapulie.pratiche.web.ws.types.AllegatiDto;
import it.osapulie.pratiche.web.ws.types.ComuneDto;
import it.osapulie.pratiche.web.ws.types.DatiRicercaPraticaWeb;
import it.osapulie.pratiche.web.ws.types.DocInfo;
import it.osapulie.pratiche.web.ws.types.DocumentiContenuti;
import it.osapulie.pratiche.web.ws.types.DocumentiContenutiResponse;
import it.osapulie.pratiche.web.ws.types.DownloadDocumento;
import it.osapulie.pratiche.web.ws.GestionePraticheWS;
import it.osapulie.pratiche.web.ws.types.CategoriePraticheDto;
import it.osapulie.pratiche.web.ws.types.EliminaAllegatoResponse;
import it.osapulie.pratiche.web.ws.types.GetTipoImmobileByPk;
import it.osapulie.pratiche.web.ws.types.GetTipoPraticaByPk;
import it.osapulie.pratiche.web.ws.types.GetTipoPraticaByPkResponse;
import it.osapulie.pratiche.web.ws.types.PraticaDto;
import it.osapulie.pratiche.web.ws.types.PraticaWebDto;
import it.osapulie.pratiche.web.ws.types.ProfiloUtenteDto;
import it.osapulie.pratiche.web.ws.types.SavePratica;
import it.osapulie.pratiche.web.ws.types.SavePraticaResponse;
import it.osapulie.pratiche.web.ws.types.SearchAllPraticheWeb;
import it.osapulie.pratiche.web.ws.types.SearchAllegatiTipoPratica;
import it.osapulie.pratiche.web.ws.types.SearchCategoriePraticheResponse;
import it.osapulie.pratiche.web.ws.types.SearchPraticaByPk;
import it.osapulie.pratiche.web.ws.types.SearchPraticaResponse;
import it.osapulie.pratiche.web.ws.types.SearchPraticaWebByPk;
import it.osapulie.pratiche.web.ws.types.SearchPraticaWebByPkResponse;
import it.osapulie.pratiche.web.ws.types.SearchPratiche;
import it.osapulie.pratiche.web.ws.types.SearchPraticheWebByUtente;
import it.osapulie.pratiche.web.ws.types.SearchStatoPratiche;
import it.osapulie.pratiche.web.ws.types.SearchTipoImmobile;
import it.osapulie.pratiche.web.ws.types.SearchTipoPraticheFrontend;
import it.osapulie.pratiche.web.ws.types.StatoPraticaDto;
import it.osapulie.pratiche.web.ws.types.TipoImmobileDto;
import it.osapulie.pratiche.web.ws.types.TipoPraticaDto;
import it.osapulie.pratiche.web.ws.types.UploadAllegatiToPraticaResponse;
import it.osapulie.pratiche.web.ws.types.UploadFile;
import it.osapulie.pratiche.web.ws.types.UploadFileResponse;
import it.osapulie.service.ServiceLayerException;
import it.osapulie.shared.service.UserPreferences;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Implementazione di {@link GestionePraticheWebService}
 * 
 * @author Maria Michela Birtolo
 */
//@Service("gestionePraticheWebService")
public class GestionePraticheWebServiceImpl implements GestionePraticheWebService {

	private static Logger log = LoggerFactory
			.getLogger(GestionePraticheWebServiceImpl.class);

	
	@Inject
	GestionePraticheWS gestionePraticheClient;
	
	/**
	 * Metodo che ricerca le pratiche
	 */
	public List<PraticaDto> searchPratiche(DatiRicercaPraticaWeb filtriRicerca,UserPreferences userPreferences) {
		
		SearchPratiche req = new SearchPratiche();
		req.setArg0( null );
		req.setArg1( filtriRicerca );
		
		SearchPraticaResponse resp = gestionePraticheClient.searchPratiche( req ).getReturn();
		
		return (List<PraticaDto>) resp.getPratiche();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.PraticaService#getPraticaByPk(long)
	 */
	public PraticaDto getPraticaByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException {
		
		SearchPraticaByPk req = new SearchPraticaByPk();
		req.setArg0( null );
		req.setArg1( pk );
		PraticaDto p = gestionePraticheClient.searchPraticaByPk( req ).getReturn();
		return p;
	}
	
	@Override
	public String searchCategoriePratiche(UserPreferences userPreferences) {
		SearchCategoriePraticheResponse resp = new SearchCategoriePraticheResponse();

		return resp.getReturn().getCategorie();

	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see it.osapulie.service.PraticaService#getPraticaByPk(long)
	 */
	public PraticaWebDto getPraticaWebByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException {
		
		SearchPraticaWebByPk req = new SearchPraticaWebByPk();
		req.setArg0( null );
		req.setArg1( pk );
		PraticaWebDto p = null;
		try {
		SearchPraticaWebByPkResponse resp = gestionePraticheClient.searchPraticaWebByPk( req );
		p = resp.getReturn();
		
		JAXBContext jc = JAXBContext.newInstance(new Class[]{SearchPraticaWebByPkResponse.class});
        log.debug("getPraticaWebByPk");
        Marshaller u = jc.createMarshaller();
        u.marshal(resp, new File("/opt/getPraticaWebByPk.xml"));
		} catch (Exception e) {
			throw new ServiceLayerException(
					"There was an error when saving an object of type Pratica :: "
							+ e.getMessage(), e);
		}
		return p;		
	}
	
	public SavePraticaResponse savePratica(PraticaWebDto pratica,UserPreferences userPreferences)
			throws ServiceLayerException {
		
		SavePratica req = new SavePratica();
		req.setArg0( null );
		req.setArg1( pratica );
		try {
			return gestionePraticheClient.savePratica( req );
			
			
		} catch (Exception e) {
			throw new ServiceLayerException(
					"There was an error when saving an object of type Pratica :: "
							+ e.getMessage(), e);
		}
	}
	
	public List<PraticaWebDto> searchPraticheWeb(ProfiloUtenteDto profiloutente ,UserPreferences userPreferences){
		
		SearchPraticheWebByUtente req = new SearchPraticheWebByUtente();
		req.setArg0( null );
		req.setArg1( profiloutente );
		
		List<PraticaWebDto> resp = gestionePraticheClient.searchPraticheWebByUtente( req ).getReturn();
		
		return resp;	
		
	}
	
	public List<PraticaWebDto> searchPraticheWeb(UserPreferences userPreferences){
		
		SearchAllPraticheWeb req = new SearchAllPraticheWeb();
		req.setArg0( null );
		
		List<PraticaWebDto> resp = gestionePraticheClient.searchAllPraticheWeb( req ).getReturn();
		return resp;
	}
	
	public UploadFileResponse processaUploadPratica(String tipologia, String fileName, byte[] content, ProfiloUtenteDto profiloUtente,String idPratica, String uuidContenitorePratica,UserPreferences userPreferences) throws ServiceLayerException,Exception{
		
		
		try {
			UploadFile req = new UploadFile();
			req.setArg0( null );
			req.setArg1( tipologia );
			req.setArg2( fileName );
			req.setArg3( content );
			req.setArg4( profiloUtente );
			req.setArg5( idPratica );
			req.setArg6( uuidContenitorePratica );
			UploadFileResponse resp = gestionePraticheClient.uploadFile( req );
			
			JAXBContext jc = JAXBContext.newInstance(new Class[]{UploadFileResponse.class});
            log.debug("processaUploadPratica");
            Marshaller u = jc.createMarshaller();
            u.marshal(resp, new File("/opt/uploadFileResponse.xml"));
            return resp;
		}
		catch(Exception e){
			log.error("processaUploadPratica :: " + e.getMessage(), e);
			throw new IOException("processaUploadPratica" , e);
		}
		
	}

	public byte[] downloadDocumento(String uuidContenitore,String uuidDocumento,UserPreferences userPreferences) throws ServiceLayerException
	{
		byte[] result = null;
		DownloadDocumento req = new DownloadDocumento();
		req.setArg0( null );
		req.setArg1( uuidContenitore );
		req.setArg2( uuidDocumento );

		try{			
			result = gestionePraticheClient.downloadDocumento(req).getReturn();
		}catch (Exception e) {
			throw new ServiceLayerException(
					"There was an error when upload an object of type File :: "
							+ e.getMessage(), e);
		}
		return result;
	}
	
	public List<TipoPraticaDto> searchTipoPraticheFrontEnd(UserPreferences userPreferences) {
		log.debug("searchTipoPraticheFrontEnd:: entering method");
		SearchTipoPraticheFrontend req = new SearchTipoPraticheFrontend();
		req.setArg0( null );
		return gestionePraticheClient.searchTipoPraticheFrontend( req ).getReturn().getTipoPratica();
	}

	public List<ComuneDto> searchComuni(UserPreferences userPreferences) {
		log.debug("searchComuni:: entering method");
		
		return null;
	}

	public List<TipoImmobileDto> searchTipoImmobile(UserPreferences userPreferences) {
		log.debug("searchTipoImmobile :: entering method");
		
		SearchTipoImmobile req = new SearchTipoImmobile();
		req.setArg0( null );
		
		return gestionePraticheClient.searchTipoImmobile( req ).getReturn().getTipoImmobile();
	}
	
	public List<StatoPraticaDto> searchStatoPratica(UserPreferences userPreferences) {
		log.debug("searchStatoPratica :: entering method");
		
		SearchStatoPratiche req = new SearchStatoPratiche();
		req.setArg0( null );
		
		return gestionePraticheClient.searchStatoPratiche( req ).getReturn();
	}
	
	public List<AllegatiDto> searchAllegatiTipoPratica(TipoPraticaDto tipo,UserPreferences userPreferences) throws ServiceLayerException{
		List<AllegatiDto> result = null;
		
		SearchAllegatiTipoPratica req = new SearchAllegatiTipoPratica();
		req.setArg0( null );
		req.setArg1( tipo );
		
		try{							
			result = gestionePraticheClient.searchAllegatiTipoPratica(  req).getReturn();
		}catch (Exception e) {
			throw new ServiceLayerException(
					"There was an error when upload an object of type File :: "
							+ e.getMessage(), e);
		}
		return result;
	}
	public List<DocInfo> documentiContenuti(String uuidContenitore,UserPreferences userPreferences) throws ServiceLayerException
	{
		List<DocInfo> result = null;
		
		DocumentiContenuti req = new DocumentiContenuti();
		req.setArg0( null );
		req.setArg1( uuidContenitore );

		try{		
			DocumentiContenutiResponse resp = gestionePraticheClient.documentiContenuti( req);
			result = resp.getReturn();			
			
			JAXBContext jc = JAXBContext.newInstance(new Class[]{DocumentiContenutiResponse.class});
            log.debug("DocumentiContenutiResponse");
            Marshaller u = jc.createMarshaller();
            u.marshal(resp, new File("/opt/documentiContenutiResponse.xml"));
		}catch (Exception e) {
			throw new ServiceLayerException(
					"There was an error when upload an object of type File :: "
							+ e.getMessage(), e);
		}
		return result;
	}
	
	public TipoPraticaDto getTipoPraticaByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException{
		
		GetTipoPraticaByPk req = new GetTipoPraticaByPk();
		req.setArg0( null );
		req.setArg1( pk );
		
		GetTipoPraticaByPkResponse resp = gestionePraticheClient.getTipoPraticaByPk( req );
		try{
			JAXBContext jc = JAXBContext.newInstance(new Class[]{GetTipoPraticaByPkResponse.class});
	        log.debug("getTipoPraticaByPk");
	        Marshaller u = jc.createMarshaller();
	        u.marshal(resp, new File("/opt/getTipoPraticaByPk.xml"));
		}catch (Exception e) {
			// TODO: handle exception
		}    
        return resp.getReturn();
	}
	
	public TipoImmobileDto getTipoImmobileByPk(long pk,UserPreferences userPreferences) throws ServiceLayerException{
		
		GetTipoImmobileByPk req = new GetTipoImmobileByPk();
		req.setArg0( null );
		req.setArg1( pk );
		return gestionePraticheClient.getTipoImmobileByPk( req ).getReturn();
	}
	
	public EliminaAllegatoResponse eliminaDocumento(String uuidAllegato,UserPreferences userPreferences) throws ServiceLayerException{
		return null;
	}
	public UploadAllegatiToPraticaResponse uploadAllegatiToPratica(String fileName, byte[] content, String numeroPratica,Long idAllegato,UserPreferences userPreferences) throws ServiceLayerException,Exception{
		return null;
	}
}
