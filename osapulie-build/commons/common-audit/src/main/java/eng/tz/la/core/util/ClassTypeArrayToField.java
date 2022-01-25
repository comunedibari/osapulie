package eng.tz.la.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import eng.tz.la.annotation.AuditField;
import eng.tz.la.core.AnnotationUtil;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class ClassTypeArrayToField {

	public static MetaLine typeArray(Object object, MetaLine builder, Object request, Line<?> line, Field f,
			AuditField aud,LogSettyng settyng) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		if(object!=null)
		if (f.getType().isArray()) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Object[] l = (Object[]) obj;
				for (int i = 0; i < l.length; i++) {
					Object elm = l[i];
					if (elm != null) {
						Class<?> type = elm.getClass();
						if (CheckTypeUtil.isCheckTypeWrite(type)) {
							builder.add(new MetaField(
									aud.key().equals("") ? (f.getName() + "_" + i) : (aud.key() + "_" + i), elm, CheckTypeUtil.printType(settyng, type, aud)));

						} else {
							if(aud.key()!=null)
								builder.add(new MetaField(f.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
							
							builder = AnnotationUtil.audit(elm, builder, request, line,settyng);
						}
					}
				}
			}
		}

		return builder;
	}

}
