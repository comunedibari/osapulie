package it.eng.tz.area.vasta.protocollo.spring.configuration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer
{
	@Autowired
	private Environment env;
	@Bean(name="multipartResolver") 
    public CommonsMultipartResolver getResolver() throws IOException
	{
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxUploadSizePerFile(Long.parseLong(env.getProperty("area.vasta.protocollo.max.file.dimension")));
        
        return resolver;
    }
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("resources/**").addResourceLocations(new String[] { "resources/" });
		registry.addResourceHandler("adminWebTheme/**").addResourceLocations(new String[] { "adminWebTheme/" });
	}
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer)
	{
		// Ignoro gli header a considero solo l'estensione del path; per default
		// restituisco un HTML
		Map<String, MediaType> medias = new HashMap<String, MediaType>();
		// la key è l'estensione del path; il valore il media type restituito
		// medias.put("html", MediaType.TEXT_HTML);
		medias.put("json", MediaType.APPLICATION_JSON);
		configurer.favorPathExtension(true).ignoreAcceptHeader(true).defaultContentType(MediaType.TEXT_HTML).mediaTypes(medias);
	}

	@Bean
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager)
	{
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> viewResolvers = new ArrayList<ViewResolver>(2);
		viewResolvers.add(internalResourceViewResolver());
		viewResolvers.add(beanNameViewResolver());
		resolver.setViewResolvers(viewResolvers);
		List<View> defaultViews = new ArrayList<View>(1);
		defaultViews.add(new MappingJackson2JsonView());
		resolver.setDefaultViews(defaultViews);
		return resolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer()
	{
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions(new String[] { "/WEB-INF/tiles/tiles-definitions.xml" });
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	@Bean("beanNameViewResolver")
	public ViewResolver beanNameViewResolver()
	{
		BeanNameViewResolver bnvr = new BeanNameViewResolver();
		return bnvr;
	}

	@Bean("internalViewResolver")
	public ViewResolver internalResourceViewResolver()
	{
		InternalResourceViewResolver tvr = new InternalResourceViewResolver();
		tvr.setPrefix("/WEB-INF/jsp/");
		tvr.setSuffix(".jsp");
		return tvr;
	}

	// Resource bundle
	@Bean
	public MessageSource messageSource()
	{
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/bundle/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setFallbackToSystemLocale(false);
		return messageSource;
	}
}