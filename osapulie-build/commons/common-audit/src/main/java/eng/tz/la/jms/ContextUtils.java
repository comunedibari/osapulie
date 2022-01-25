package eng.tz.la.jms;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtils {
	
	public static Context getInitialContext() throws NamingException {
		Properties props = new Properties();
		props.setProperty("java.naming.factory.initial",
				"org.jnp.interfaces.NamingContextFactory");
		props.setProperty("java.naming.factory.url.pkgs", "org.jboss.naming");
		props.setProperty("java.naming.provider.url", "jnp://localhost:1099");
		Context context = new InitialContext(props);
		return context;
	}
}
 