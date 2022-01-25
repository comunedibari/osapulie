package it.eng.tz.area.vasta.protocollo.spring.configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;
import org.springframework.util.StringUtils;

@SuppressWarnings("deprecation")
@Configuration
@ComponentScan(basePackages = { "it.eng.tz.area.vasta.protocollo.spring.ldap" })
public class LdapConfiguration
{
	@Autowired
	private Environment env;
	// Configurazione LDAP
	@Bean
	public LdapContextSource mutablePoolingLdapCtxSource()
	{
		LdapContextSource dirCtx = new LdapContextSource();
		dirCtx.setUrl(env.getProperty("area.vasta.protocollo.ldap.url"));
		String base = env.getProperty("area.vasta.protocollo.ldap.base");
		if( StringUtils.hasText(base) )
		{
			dirCtx.setBase(base);
		}
		dirCtx.setPassword(env.getProperty("area.vasta.protocollo.ldap.password"));
		dirCtx.setUserDn(env.getProperty("area.vasta.protocollo.ldap.username"));
		dirCtx.setReferral("ignore");
		return dirCtx;
	}

	@Bean
	public LdapTemplate ldapTemplate()
	{
		LdapTemplate result = new LdapTemplate(mutablePoolingLdapCtxSource());
		return result;
	}
	@Bean("ldapPwdEncoder")
	public LdapShaPasswordEncoder ldapPwdEncoder()
	{
		return new LdapShaPasswordEncoder();
	}
}