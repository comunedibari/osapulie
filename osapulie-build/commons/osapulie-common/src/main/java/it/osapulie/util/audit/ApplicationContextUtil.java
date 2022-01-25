package it.osapulie.util.audit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.portlet.PortletRequest;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationContextUtil {

	private static ApplicationContextUtil applicationContextUtil;
	private static final Logger logger = LoggerFactory.getLogger(ApplicationContextUtil.class.getName());	

	public static ApplicationContextUtil getContex() {
		if (applicationContextUtil == null) {
			applicationContextUtil = new ApplicationContextUtil();
		}
		 
		return applicationContextUtil;
	}

	public ApplicationContext getApplicationContext() {
		ApplicationContext appContext = WebApplicationContextUtils
				.getWebApplicationContext(ContextLoaderListener.getCurrentWebApplicationContext().getServletContext());
		return appContext;
	}

	public static Object getBean(String bean) {

		return getContex().getApplicationContext().getBean(bean);
	}

	public static Object getBean(String bean, Class<?> klass) {

		return getContex().getApplicationContext().getBean(bean, klass);
	}

	public static Object getBean(Class<?> klass) {

		return getContex().getApplicationContext().getBean(klass);
	}

	public static Object getBean(String bean, Object obj) {

		return getContex().getApplicationContext().getBean(bean, obj);
	}
	public static Properties getResource() {
		return getContex().getProperties();
	}

	//classpath*:conf/*.properties
	private Properties getProperties() {
		try {
			PropertiesFactoryBean config = new PropertiesFactoryBean();
			PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			List<Resource> resouceList = new ArrayList<Resource>();

			Resource[] resources = resolver.getResources("classpath*:application.properties");
			for (Resource resource : resources) {
				resouceList.add(resource);
			}
			config.setLocations(resouceList.toArray(new Resource[]{}));
			config.afterPropertiesSet();
			return config.getObject();

		} catch (IOException e) {
			logger.error("ApplicationContextUtil-> ", e);
		} catch (Exception e) {
			logger.error("ApplicationContextUtil-> ", e);
		}

		return new Properties();
	}
	 
	public static String getCurrentUrl(PortletRequest request) {
	ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	String currentUrl="";
	if(themeDisplay!=null)
	currentUrl = themeDisplay.getURLCurrent();	
	return currentUrl;
	}
	
}
