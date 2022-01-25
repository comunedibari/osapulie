package eng.tz.pa.api.osa.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/pages")
public class MainPagesController {
	private static final Logger logger = LoggerFactory.getLogger(MainPagesController.class.getName());
	@Autowired
	private HttpServletRequest req;
	@Autowired
	private HttpSession sessione;
	 
	private long dimensioneFile=2048;


	@RequestMapping(method = { RequestMethod.GET }, value = {"/accessDenied"})
	public String accessDenied()
	{
		return "views/accessDenied";
	}	
	@RequestMapping(method = { RequestMethod.GET }, value = {"/loginPage"})
	public String loginPage()
	{
		return "views/loginPage";
	}	
	@RequestMapping(method = { RequestMethod.GET }, value = { "/logout" })
	public String logout(HttpServletResponse resp) throws Exception
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/pages/loginPage?logout";
	}
	
	@RequestMapping(method = { RequestMethod.GET }, value = {"/api"})
	public String api()
	{
		return "views/api";
	}
	
}
