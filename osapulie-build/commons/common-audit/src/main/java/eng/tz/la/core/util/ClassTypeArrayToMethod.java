package eng.tz.la.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.core.AnnotationUtil;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class ClassTypeArrayToMethod {

	public static MetaLine typeArray(Object object, MetaLine builder, Object request, Line<?> line, Method m,
			AuditMethod aud,LogSettyng settyng) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		if(object!=null)
		if (m.getReturnType().isArray()) {
			AnnotationUtil.renderAccessible(m);
			Object obj=null;
			if(m.getParameterTypes().length==0)
				obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Object[] l = (Object[]) obj;
				for (int i = 0; i < l.length; i++) {
					Object elm = l[i];
					if (elm != null) {
						Class<?> cl = elm.getClass();
						try {
							if (aud.method().length > 0) {
								for (String metodo : aud.method()) {
									Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
									AnnotationUtil.renderAccessible(mcl);
									Object objVal=null;
									if(mcl.getParameterTypes().length==0)
									objVal = mcl.invoke(elm, new Object[] {});
									if (objVal != null) {
										Class<?> type = objVal.getClass();
										if (CheckTypeUtil.isCheckTypeWrite(type)) {

											builder.add(
													new MetaField((aud.key().equals("") ? mcl.getName() : aud.key()),
															objVal, CheckTypeUtil.printType(settyng, type, aud)));
										} else {
											builder = AnnotationUtil.audit(objVal, builder, request, line,settyng);
										}
									}
								}
							} else {
								Class<?> type = elm.getClass();
								if (CheckTypeUtil.isCheckTypeWrite(type)) {
									builder.add(new MetaField(
											aud.key().equals("") ? (m.getName() + "_" + i) : (aud.key() + "_" + i), elm,
													CheckTypeUtil.printType(settyng, type, aud)));
								} else {
									if(aud.key()!=null)
										builder.add(new MetaField(m.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
									
									builder = AnnotationUtil.audit(elm, builder, request, line,settyng);
								}
							}
						} catch (NoSuchMethodException e) {
						} catch (Exception e) {
						}
					}
				}
			}
		}

		return builder;
	}

}
