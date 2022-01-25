/*******************************************************************************
 * Copyright (c) 2011, 2012 Link Management & Technology S.p.A. 
 * via R. Scotellaro, 55 - 73100 - Lecce - http://www.linksmt.it
 * All rights reserved. 
 *
 * Contributors:
 *     Links Management & Technology S.p.A. - initial API and implementation
 *******************************************************************************/
package it.osapulie.web.portlet.util;

import javax.inject.Inject;

import it.osapulie.domain.ComuneISA;
import it.osapulie.persistence.ComuneISARepository;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Form validator per {@link ComuneISA}.
 * 
 * @author Mario Scalas
 */
@Component( "editComuneValidator" )
public class ComuneValidator implements Validator {

	@Inject
	private ComuneISARepository repository;
	
	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#supports(java.lang.Class)
	 */
	@Override
	public boolean supports( Class<?> clazz ) {
		return ComuneISA.class.equals( clazz );
	}

	/* (non-Javadoc)
	 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
	 */
	@Override
	public void validate( Object target, Errors errors ) {
		ComuneISA comuneISA = (ComuneISA) target;

		ValidationUtils.rejectIfEmptyOrWhitespace( errors,
				"nome", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors,
				"codiceIstat", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors,
				"cap", "NotEmpty.field.required" );
		ValidationUtils.rejectIfEmptyOrWhitespace( errors,
				"uriServizioGateway", "NotEmpty.field.required" );
		
		ComuneISA esistente = repository.findByCodiceIstat( comuneISA.getCodiceIstat() );
		if (esistente != null && !esistente.getId().equals( comuneISA.getId() )) {
			errors.rejectValue( "codiceIstat", "Unique.constraint.failure" );
		}
		esistente = repository.findByCap( comuneISA.getCap() );
		if (esistente != null && !esistente.getId().equals( comuneISA.getId() )) {
			errors.rejectValue( "cap", "Unique.constraint.failure" );
		}
	}
}
