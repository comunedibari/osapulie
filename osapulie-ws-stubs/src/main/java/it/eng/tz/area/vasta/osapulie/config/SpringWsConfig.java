package it.eng.tz.area.vasta.osapulie.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.soap.SoapMessageFactory;
import org.springframework.ws.soap.axiom.AxiomSoapMessageFactory;
import org.springframework.ws.wsdl.wsdl11.SimpleWsdl11Definition;

@Configuration
@ComponentScan(basePackages ={ "it.eng.tz.area.vasta.osapulie" })
@EnableWs
public class SpringWsConfig
{
	private static final Logger logger = LoggerFactory.getLogger(SpringWsConfig.class.getName());
	@Bean(name="categorieImmobiliService")
	public SimpleWsdl11Definition categorieImmobiliServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/categorieImmobiliService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="pddsAdapter")
	public SimpleWsdl11Definition pddsAdapterDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/PDDSAdapter.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="pddsIntegrationSOAP")
	public SimpleWsdl11Definition pddsIntegrationSOAPDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/PddsIntegrationSOAP.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraAnagraficaLightService")
	public SimpleWsdl11Definition visuraAnagraficaLightServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraAnagraficaLightService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraAnagraficaService")
	public SimpleWsdl11Definition visuraAnagraficaServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraAnagraficaService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraDocumentiAnagrafeService")
	public SimpleWsdl11Definition visuraDocumentiAnagrafeServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraDocumentiAnagrafeService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraDocumentiTributiService")
	public SimpleWsdl11Definition visuraDocumentiTributiServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraDocumentiTributiService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraImpostaInsegnePubblicitaService")
	public SimpleWsdl11Definition visuraImpostaInsegnePubblicitaServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraImpostaInsegnePubblicitaService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraOsapPermanenteService")
	public SimpleWsdl11Definition visuraOsapPermanenteServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraOsapPermanenteService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraOsapTemporaneaService")
	public SimpleWsdl11Definition visuraOsapTemporaneaServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraOsapTemporaneaService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraPosizioneElettoraleService")
	public SimpleWsdl11Definition visuraPosizioneElettoraleServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraPosizioneElettoraleService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraPubblicheAffissioniService")
	public SimpleWsdl11Definition visuraPubblicheAffissioniServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraPubblicheAffissioniService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraServiziCimiterialiService")
	public SimpleWsdl11Definition visuraServiziCimiterialiServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraServiziCimiterialiService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraTassaImmobiliService")
	public SimpleWsdl11Definition visuraTassaImmobiliServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraTassaImmobiliService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraTassaRifiutiService")
	public SimpleWsdl11Definition visuraTassaRifiutiServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraTassaRifiutiService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="visuraVariazioniDomicilioService")
	public SimpleWsdl11Definition visuraVariazioniDomicilioServiceDefinition()
	{
		SimpleWsdl11Definition result = new SimpleWsdl11Definition();
		Resource wsdl = new ClassPathResource("wsdl/visuraVariazioniDomicilioService.wsdl");
		if( logger.isDebugEnabled() )
		{
			logger.debug("WSDL FILE NAME "+wsdl.getFilename());
		}
		result.setWsdl(wsdl);
		return result;
	}
	@Bean(name="messageFactory")
	public SoapMessageFactory messageFactory()
	{
		AxiomSoapMessageFactory asmf = new AxiomSoapMessageFactory();
		asmf.setPayloadCaching(true);
		return asmf;
	}
}