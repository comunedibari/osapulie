package it.eng.tz.area.vasta.protocollo.spring.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.StrictHttpFirewall;
import it.eng.tz.area.vasta.conservazione.jms.conf.MessagingConfiguration;
import it.eng.tz.area.vasta.documentale.cmis.config.CMISProperties;


 
@Configuration
@EnableWebSecurity
@ComponentScan(basePackages= {"it.eng.tz.area.vasta.protocollo","it.eng.tz.area.vasta.conservazione.jms"})
@Import(value= {WebMvcConfig.class, MessagingConfiguration.class,LdapConfiguration.class, DBConfiguration.class,CMISProperties.class})
@PropertySource( value={"classpath:config.properties"}, encoding="UTF-8", ignoreResourceNotFound=false)
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled=true)
@EnableScheduling
public class WebSecurityCfg extends WebSecurityConfigurerAdapter
{

	@Override
	public void configure(WebSecurity web) throws Exception {
		
		super.configure(web);
		web.httpFirewall(this.allowUrlEncodedSlashHttpFirewall());
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
	{
		auth
			.inMemoryAuthentication()
			.withUser("a.defano")
			.password("{noop}a.defano")
			.roles("ADMIN","USER","SUPER_ADMIN");
	}
	private CsrfTokenRepository csrfTokenRepository() 
	{ 
	    HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository(); 
	    repository.setSessionAttributeName("_csrf");
	    return repository; 
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception
	{

		http
		.authorizeRequests()
		.antMatchers("/adminWebTheme/**")
		.permitAll()
		.antMatchers("/pages/protected/**", "/rest/protected/**")
		.access("hasAnyRole('ADMIN','USER','SUPER_ADMIN')")
		.and()
		.formLogin()
		.loginPage("/pages/loginPage")
		.permitAll()
		.usernameParameter("username")
		.passwordParameter("password")
		.defaultSuccessUrl("/pages/protected/adminHome")
		.failureUrl("/pages/loginPage?error")
		.loginProcessingUrl("/login")
		.and()
		.logout()
		.permitAll()
		.logoutSuccessUrl("/pages/loginPage?logout")
		.and()
		.csrf()
		.csrfTokenRepository(this.csrfTokenRepository())
		.and()
		.exceptionHandling()
		.accessDeniedPage("/pages/accessDenied");
	}
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		BCryptPasswordEncoder webPwdEnc = new BCryptPasswordEncoder();
		return webPwdEnc;
	}
	@Bean
    public HttpFirewall allowUrlEncodedSlashHttpFirewall()
    {

      StrictHttpFirewall firewall = new StrictHttpFirewall();
      firewall.setAllowUrlEncodedSlash(true);
      firewall.setAllowSemicolon(true);
      return firewall;

    } 
}