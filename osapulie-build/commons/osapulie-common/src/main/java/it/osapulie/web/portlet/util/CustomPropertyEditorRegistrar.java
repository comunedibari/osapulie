package it.osapulie.web.portlet.util;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

/**
 * Registra i custom property editor utilizzati spesso nel fronted web delle portlet.
 * 
 * @author Mario Scalas
 */
public class CustomPropertyEditorRegistrar implements PropertyEditorRegistrar {
	
	/* (non-Javadoc)
	 * @see org.springframework.beans.PropertyEditorRegistrar#registerCustomEditors(org.springframework.beans.PropertyEditorRegistry)
	 */
	@Override
	public void registerCustomEditors( PropertyEditorRegistry registry ) {
//		registry.registerCustomEditor( SecoloTradotto.class, new SecoloPropertyEditor() );
	}
}
