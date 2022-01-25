package eng.tz.la.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * @author s.mariniello
 */

@Retention(value = RUNTIME)
@Target(value = { ElementType.FIELD, ElementType.LOCAL_VARIABLE })
@Documented
public @interface AuditField {
	public String key() default "";
	public boolean print() default true;
	public boolean printType() default false;
}
