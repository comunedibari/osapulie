package it.eng.tz.area.vasta.osapulie.ws.stubs.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import it.eng.tz.area.vasta.osapulie.ws.stubs.categorieImmobiliService.*;

public class CategorieImmobiliServiceEndpoint {
	
	private static final Logger logger = LoggerFactory.getLogger(CategorieImmobiliServiceEndpoint.class.getName());
	private static final String NAME_SPACE_URI = "http://ws.wso2.org/dataservice";

	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_agevolazione_operation")
	@ResponsePayload
	public void updateTbAgevolazioneOperation(@RequestPayload UpdateTbAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_categoriaimmobile_tributo_operation")
	@ResponsePayload
	public void deleteTbCategoriaimmobileTributoOperation(@RequestPayload DeleteTbCategoriaimmobileTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbCategoriaimmobileTributoOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_agevolazione_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileAgevolazioneOperation selectAllTbCategoriaimmobileAgevolazioneOperation(@RequestPayload SelectAllTbCategoriaimmobileAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileAgevolazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileAgevolazioneOperation result = of.createSelectAllTbCategoriaimmobileAgevolazioneOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_categoriaimmobile_operation")
	@ResponsePayload
	public SelectWithKeyTbCategoriaimmobileOperation selectWithKeyTbCategoriaimmobileOperation(@RequestPayload SelectWithKeyTbCategoriaimmobileOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbCategoriaimmobileOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbCategoriaimmobileOperation result = of.createSelectWithKeyTbCategoriaimmobileOperation();
		result.setID(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_by_anno_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileByAnnoOperation selectAllTbCategoriaimmobileByAnnoOperation(@RequestPayload SelectAllTbCategoriaimmobileByAnnoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileByAnnoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileByAnnoOperation result = of.createSelectAllTbCategoriaimmobileByAnnoOperation();
		result.setAnno(2018);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_tipologia_detrazione_operation")
	@ResponsePayload
	public void deleteTbTipologiaDetrazioneOperation(@RequestPayload DeleteTbTipologiaDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbTipologiaDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_esenzione_operation")
	@ResponsePayload
	public void insertTbEsenzioneOperation(@RequestPayload InsertTbEsenzioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbEsenzioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_categoriaimmobile_agevolazione_operation")
	@ResponsePayload
	public void insertTbCategoriaimmobileAgevolazioneOperation(@RequestPayload InsertTbCategoriaimmobileAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbCategoriaimmobileAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_tipologia_detrazione_operation")
	@ResponsePayload
	public SelectWithKeyTbTipologiaDetrazioneOperation selectWithKeyTbTipologiaDetrazioneOperation(@RequestPayload SelectWithKeyTbTipologiaDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbTipologiaDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbTipologiaDetrazioneOperation result = of.createSelectWithKeyTbTipologiaDetrazioneOperation();
		result.setID(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_detrazione_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileDetrazioneOperation selectAllTbCategoriaimmobileDetrazioneOperation(@RequestPayload SelectAllTbCategoriaimmobileDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileDetrazioneOperation result = of.createSelectAllTbCategoriaimmobileDetrazioneOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_tributo_operation")
	@ResponsePayload
	public void insertTbTributoOperation(@RequestPayload InsertTbTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbTributoOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_tipologia_detrazione_operation")
	@ResponsePayload
	public void updateTbTipologiaDetrazioneOperation(@RequestPayload UpdateTbTipologiaDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbTipologiaDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_detrazione_operation")
	@ResponsePayload
	public void deleteTbDetrazioneOperation(@RequestPayload DeleteTbDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_detrazione_operation")
	@ResponsePayload
	public SelectWithKeyTbDetrazioneOperation selectWithKeyTbDetrazioneOperation(@RequestPayload SelectWithKeyTbDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbDetrazioneOperation result = of.createSelectWithKeyTbDetrazioneOperation();
		result.setID(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_categoriaimmobile_agevolazione_operation")
	@ResponsePayload
	public void updateTbCategoriaimmobileAgevolazioneOperation(@RequestPayload UpdateTbCategoriaimmobileAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbCategoriaimmobileAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_detrazione_operation")
	@ResponsePayload
	public void insertTbDetrazioneOperation(@RequestPayload InsertTbDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_categoriaimmobile_operation")
	@ResponsePayload
	public void insertTbCategoriaimmobileOperation(@RequestPayload InsertTbCategoriaimmobileOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbCategoriaimmobileOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_tipologia_detrazione_operation")
	@ResponsePayload
	public void insertTbTipologiaDetrazioneOperation(@RequestPayload InsertTbTipologiaDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbTipologiaDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_categoriaimmobile_agevolazione_operation")
	@ResponsePayload
	public SelectWithKeyTbCategoriaimmobileAgevolazioneOperation selectWithKeyTbCategoriaimmobileAgevolazioneOperation(@RequestPayload SelectWithKeyTbCategoriaimmobileAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbCategoriaimmobileAgevolazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbCategoriaimmobileAgevolazioneOperation result = of.createSelectWithKeyTbCategoriaimmobileAgevolazioneOperation();
		result.setFkCategoriaimmobile(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_esenzione_operation")
	@ResponsePayload
	public SelectWithKeyTbEsenzioneOperation selectWithKeyTbEsenzioneOperation(@RequestPayload SelectWithKeyTbEsenzioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbEsenzioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbEsenzioneOperation result = of.createSelectWithKeyTbEsenzioneOperation();
		result.setFkCategoriaimmobile(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_tributo_operation")
	@ResponsePayload
	public SelectWithKeyTbTributoOperation selectWithKeyTbTributoOperation(@RequestPayload SelectWithKeyTbTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbTributoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbTributoOperation result = of.createSelectWithKeyTbTributoOperation();
		result.setID(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_agevolazione_operation")
	@ResponsePayload
	public SelectWithKeyTbAgevolazioneOperation selectWithKeyTbAgevolazioneOperation(@RequestPayload SelectWithKeyTbAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbAgevolazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbAgevolazioneOperation result = of.createSelectWithKeyTbAgevolazioneOperation();
		result.setID(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_tributo_by_anno_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileTributoByAnnoOperation selectAllTbCategoriaimmobileTributoByAnnoOperation(@RequestPayload SelectAllTbCategoriaimmobileTributoByAnnoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileTributoByAnnoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileTributoByAnnoOperation result = of.createSelectAllTbCategoriaimmobileTributoByAnnoOperation();
		result.setAnno(2018);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_tributo_operation")
	@ResponsePayload
	public void updateTbTributoOperation(@RequestPayload UpdateTbTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO chiamaEnte CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_tributo_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileTributoOperation selectAllTbCategoriaimmobileTributoOperation(@RequestPayload SelectAllTbCategoriaimmobileTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileTributoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileTributoOperation result = of.createSelectAllTbCategoriaimmobileTributoOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_esenzione_operation")
	@ResponsePayload
	public void deleteTbEsenzioneOperation(@RequestPayload DeleteTbEsenzioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbEsenzioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_detrazione_operation")
	@ResponsePayload
	public void updateTbDetrazioneOperation(@RequestPayload UpdateTbDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_categoriaimmobile_tributo_operation")
	@ResponsePayload
	public void updateTbCategoriaimmobileTributoOperation(@RequestPayload UpdateTbCategoriaimmobileTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbCategoriaimmobileTributoOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_categoriaimmobile_tributo_operation")
	@ResponsePayload
	public void insertTbCategoriaimmobileTributoOperation(@RequestPayload InsertTbCategoriaimmobileTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbCategoriaimmobileTributoOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_tributo_operation")
	@ResponsePayload
	public void deleteTbTributoOperation(@RequestPayload DeleteTbTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbTributoOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_categoriaimmobile_detrazione_operation")
	@ResponsePayload
	public void insertTbCategoriaimmobileDetrazioneOperation(@RequestPayload InsertTbCategoriaimmobileDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbCategoriaimmobileDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_categoriaimmobile_operation")
	@ResponsePayload
	public void deleteTbCategoriaimmobileOperation(@RequestPayload DeleteTbCategoriaimmobileOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbCategoriaimmobileOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_categoriaimmobile_detrazione_operation")
	@ResponsePayload
	public void updateTbCategoriaimmobileDetrazioneOperation(@RequestPayload UpdateTbCategoriaimmobileDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbCategoriaimmobileDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_categoriaimmobile_tributo_operation")
	@ResponsePayload
	public SelectWithKeyTbCategoriaimmobileTributoOperation selectWithKeyTbCategoriaimmobileTributoOperation(@RequestPayload SelectWithKeyTbCategoriaimmobileTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbCategoriaimmobileTributoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbCategoriaimmobileTributoOperation result = of.createSelectWithKeyTbCategoriaimmobileTributoOperation();
		result.setFkCategoriaimmobile(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_agevolazione_operation")
	@ResponsePayload
	public SelectAllTbAgevolazioneOperation selectAllTbAgevolazioneOperation(@RequestPayload SelectAllTbAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbAgevolazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbAgevolazioneOperation result = of.createSelectAllTbAgevolazioneOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_categoriaimmobile_detrazione_operation")
	@ResponsePayload
	public void deleteTbCategoriaimmobileDetrazioneOperation(@RequestPayload DeleteTbCategoriaimmobileDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbCategoriaimmobileDetrazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="insert_tb_agevolazione_operation")
	@ResponsePayload
	public void insertTbAgevolazioneOperation(@RequestPayload InsertTbAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO insertTbAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_categoriaimmobile_agevolazione_operation")
	@ResponsePayload
	public void deleteTbCategoriaimmobileAgevolazioneOperation(@RequestPayload DeleteTbCategoriaimmobileAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbCategoriaimmobileAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="delete_tb_agevolazione_operation")
	@ResponsePayload
	public void deleteTbAgevolazioneOperation(@RequestPayload DeleteTbAgevolazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO deleteTbAgevolazioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_with_key_tb_categoriaimmobile_detrazione_operation")
	@ResponsePayload
	public SelectWithKeyTbCategoriaimmobileDetrazioneOperation selectWithKeyTbCategoriaimmobileDetrazioneOperation(@RequestPayload SelectWithKeyTbCategoriaimmobileDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectWithKeyTbCategoriaimmobileDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectWithKeyTbCategoriaimmobileDetrazioneOperation result = of.createSelectWithKeyTbCategoriaimmobileDetrazioneOperation();
		result.setFkCategoriaimmobile(1);
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_categoriaimmobile_operation")
	@ResponsePayload
	public SelectAllTbCategoriaimmobileOperation selectAllTbCategoriaimmobileOperation(@RequestPayload SelectAllTbCategoriaimmobileOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbCategoriaimmobileOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbCategoriaimmobileOperation result = of.createSelectAllTbCategoriaimmobileOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_tributo_operation")
	@ResponsePayload
	public SelectAllTbTributoOperation selectAllTbTributoOperation(@RequestPayload SelectAllTbTributoOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbTributoOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbTributoOperation result = of.createSelectAllTbTributoOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_categoriaimmobile_operation")
	@ResponsePayload
	public void updateTbCategoriaimmobileOperation(@RequestPayload UpdateTbCategoriaimmobileOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbCategoriaimmobileOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="update_tb_esenzione_operation")
	@ResponsePayload
	public void updateTbEsenzioneOperation(@RequestPayload UpdateTbEsenzioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO updateTbEsenzioneOperation CON REQUEST {}", request);
		}
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_tipologia_detrazione_operation")
	@ResponsePayload
	public SelectAllTbTipologiaDetrazioneOperation selectAllTbTipologiaDetrazioneOperation(@RequestPayload SelectAllTbTipologiaDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbTipologiaDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbTipologiaDetrazioneOperation result = of.createSelectAllTbTipologiaDetrazioneOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_detrazione_operation")
	@ResponsePayload
	public SelectAllTbDetrazioneOperation selectAllTbDetrazioneOperation(@RequestPayload SelectAllTbDetrazioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbDetrazioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbDetrazioneOperation result = of.createSelectAllTbDetrazioneOperation();
		return result;
	}
	
	@PayloadRoot(namespace = NAME_SPACE_URI, localPart="select_all_tb_esenzione_operation")
	@ResponsePayload
	public SelectAllTbEsenzioneOperation selectAllTbEsenzioneOperation(@RequestPayload SelectAllTbEsenzioneOperation request)
	{
		if( logger.isDebugEnabled() )
		{
			logger.debug("INVOCATO selectAllTbEsenzioneOperation CON REQUEST {}", request);
		}
		ObjectFactory of = new ObjectFactory();
		SelectAllTbEsenzioneOperation result = of.createSelectAllTbEsenzioneOperation();
		return result;
	}
	
}
