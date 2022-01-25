/************************************************************************************
 * Copyright (c) 2011, 2017 Link Management & Technology S.p.A. via R. Scotellaro, 55 73100 - Lecce
 * - http://www.linksmt.it - All rights reserved.
 *
 * Contributors: Links Management & Technology S.p.A. - initial API and implementation
 *************************************************************************************/
package it.osapulie.servizicomune.web.portlet;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.portlet.ModelAndView;

/**
 * @author Gianluca Pindinelli
 *
 */
public class BaseController {

	private final Logger log = LoggerFactory.getLogger(BaseController.class.getName());

	@SuppressWarnings("rawtypes")
	@ExceptionHandler(Exception.class)
	public ModelAndView genericException(Exception e) {
		log.error("genericException :: " + e.getMessage(), e);

		Map<Object, Object> model = new HashMap<Object, Object>();
		model.put("formError", "Unexpected error: " + e.getMessage());

		return new ModelAndView("common/defError", (Map) model);
	}

}
