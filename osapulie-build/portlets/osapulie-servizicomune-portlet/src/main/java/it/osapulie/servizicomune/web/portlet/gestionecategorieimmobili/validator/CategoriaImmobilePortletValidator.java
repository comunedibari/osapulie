/************************************************************************************
 * Copyright (c) 2011, 2015 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.validator;

import it.osapulie.servizicomune.web.portlet.gestionecategorieimmobili.form.CategoriaImmobileForm;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Form validator per {@link CategoriaImmobileForm}.
 *
 * @author Gianluca Pindinelli
 */
@Component("categoriaImmobilePortletValidator")
public class CategoriaImmobilePortletValidator implements Validator {

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaImmobileForm.class.isAssignableFrom(clazz);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.validation.Validator#validate(java.lang.Object,
	 * org.springframework.validation.Errors)
	 */
	@Override
	public void validate(Object target, Errors errors) {

		CategoriaImmobileForm categoriaImmobileForm =  (CategoriaImmobileForm) target;
		
		if(categoriaImmobileForm.getIdTipologiaCategoriaImmobile()<1){
			errors.rejectValue("idTipologiaCategoriaImmobile", "NotEmpty.field.required" );
		}
		
		if(categoriaImmobileForm.getIdBaseCalcolo()<1){
			errors.rejectValue("idBaseCalcolo", "NotEmpty.field.required" );
		}
		
		if(categoriaImmobileForm.getCoefficienteMoltiplicazione()==null){
			errors.rejectValue("coefficienteMoltiplicazione", "NotEmpty.field.required" );
		}
		
	}
}
