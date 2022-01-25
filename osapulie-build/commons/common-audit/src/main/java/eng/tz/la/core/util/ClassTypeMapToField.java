package eng.tz.la.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;

import eng.tz.la.annotation.AuditField;
import eng.tz.la.core.AnnotationUtil;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class ClassTypeMapToField {

	public static MetaLine typeMap(Object object, MetaLine builder, Object request, Line<?> line,Field f,AuditField aud,LogSettyng settyng)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException,
			InvocationTargetException {
		if(object!=null)
		if (f.getType().equals(LinkedHashMap.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				LinkedHashMap<?,?> l = (LinkedHashMap<?,?>) obj;
				Object[] key=l.keySet().toArray();
				for (int i = 0; i < key.length; i++) {
					Object elm = l.get(key[i]);
					if (elm != null) {
						Class<?> type = elm.getClass();
						if (CheckTypeUtil.isCheckTypeWrite(type)) {
							builder.add(new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
											: (aud.key() + "_" + i), elm, CheckTypeUtil.printType(settyng, type, aud)));
						} else {
							if(aud.key()!=null)
								builder.add(new MetaField(f.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
							
							builder = AnnotationUtil.audit(elm, builder, request, line,settyng);
						}
					}
				}
			}
		}
		else if (f.getType().equals(HashMap.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				HashMap<?,?> l = (HashMap<?,?>) obj;
				Object[] key=l.keySet().toArray();
				for (int i = 0; i < key.length; i++) {
					Object elm = l.get(key[i]);
					if (elm != null) {
						Class<?> type = elm.getClass();
						if (CheckTypeUtil.isCheckTypeWrite(type)) {
							builder.add(
									new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
											: (aud.key() + "_" + i), elm, CheckTypeUtil.printType(settyng, type, aud)));

						} else {
							if(aud.key()!=null)
								builder.add(new MetaField(f.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
								
							builder = AnnotationUtil.audit(elm, builder, request, line,settyng);
						}
					}
				}
			}
		}
		else if (f.getType().equals(Hashtable.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Hashtable<?,?> l = (Hashtable<?,?>) obj;
				Object[] key=l.keySet().toArray();
				for (int i = 0; i < key.length; i++) {
					Object elm = l.get(key[i]);
					if (elm != null) {
						Class<?> type = elm.getClass();
						if (CheckTypeUtil.isCheckTypeWrite(type)) {
							builder.add(
									new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
											: (aud.key() + "_" + i), elm, CheckTypeUtil.printType(settyng, type, aud)));

						} else {
							if(aud.key()!=null)
								builder.add(new MetaField(f.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
							
							builder = AnnotationUtil.audit(elm, builder, request, line,settyng);
						}
					}
				}
			}
		}else if (f.getType().equals(Map.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Map<?,?> l = (Map<?,?>) obj;
				Object[] key=l.keySet().toArray();
				for (int i = 0; i < key.length; i++) {
					Object elm = l.get(key[i]);
					if (elm != null) {
						Class<?> type = elm.getClass();
						if (CheckTypeUtil.isCheckTypeWrite(type)) {
							builder.add(
									new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
											: (aud.key() + "_" + i), elm, CheckTypeUtil.printType(settyng, type, aud)));

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
