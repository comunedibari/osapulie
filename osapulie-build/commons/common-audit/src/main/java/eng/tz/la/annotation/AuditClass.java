package eng.tz.la.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author s.mariniello
 */

@Retention(value = RUNTIME)
@Target(value = { ElementType.TYPE })
public @interface AuditClass {
	public String key() default "";
	public boolean print() default true;
}
