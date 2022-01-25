package it.osapulie.test;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestTagLib {
	private static final Logger logger = LoggerFactory.getLogger(TestTagLib.class.getName());
	@Test
	public void writeFakeHtml()
	{
		try {
			VelocityEngine velocityEngine = new VelocityEngine();
			velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
			velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
			velocityEngine.init();
			final String templatePath = "template/stradario.vm";
			Template template = velocityEngine.getTemplate(templatePath);
			VelocityContext context = new VelocityContext();
			context.put("stradarioEnable", true);
			context.put("viaName", "viaName");
			context.put("civicoName", "civicoName");
			context.put("esponenteName", "esponenteName");
			context.put("capName", "capName");
			context.put("id", "id");
			context.put("selezionaLabel", "selezionaLabel");
			context.put("cercaLabel", "cercaLabel");
			context.put("viaLabel", "viaLabel");
			context.put("esponenteLabel", "esponenteLabel");
			context.put("capLabel", "capLabel");
			context.put("viaClass", "viaClass");
			context.put("civicoLabel", "civicoLabel");
			context.put("civicoClass", "civicoClass");
			context.put("numCaratteriMin", "numCaratteriMin");
			context.put("resourceURL", "resourceURL");
			context.put("portletNamespace", "portletNamespace");
			context.put("loadingImageSrc", "loadingImageSrc");
			context.put("viaOptionValue", "viaOptionValue");
			context.put("viaOptionText", "viaOptionText");
			context.put("civicoOptionValue", "civicoOptionValue");
			context.put("civicoOptionText", "civicoOptionText");
			context.put("esponenteValue", "esponenteValue");
			context.put("capValue", "capValue");
			context.put("localitaValue", "localitaValue");
			context.put("codiceViaValue", "codiceViaValue");
			context.put("viaTextHiddenName", "viaTextHiddenName");
			context.put("civicoTextHiddenName", "civicoTextHiddenName");
			context.put("localitaHiddenName", "localitaHiddenName");
			context.put("codiceViaHiddenName", "codiceViaHiddenName");

			StringWriter writer = new StringWriter();
			template.merge(context, writer);

			logger.info(writer.toString());
		} catch (Exception e) {
			logger.error("Err", e);
		}
	}
}
