package eng.tz.la.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import eng.tz.la.annotation.AuditField;
import eng.tz.la.core.AnnotationUtil;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class ClassTypeListToField {

	public static MetaLine typeList(Object object, MetaLine builder, Object request, Line<?> line, Field f,
			AuditField aud,LogSettyng settyng) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		if(object!=null)
		if (f.getType().equals(LinkedList.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				LinkedList<?> l = (LinkedList<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);
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
		} else if (f.getType().equals(ArrayList.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				ArrayList<?> l = (ArrayList<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);
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
		} else if (f.getType().equals(List.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				List<?> l = (List<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);
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
		} else if (f.getType().equals(Collection.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Collection<?> cl = (Collection<?>) obj;
				Iterator<?> iterator = cl.iterator();
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Object elm = iterator.next();
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
		} else if (f.getType().equals(Iterator.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Iterator<?> iterator = (Iterator<?>) obj;
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Object elm = iterator.next();
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
		} else if (f.getType().equals(LinkedHashSet.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				LinkedHashSet<?> cl = (LinkedHashSet<?>) obj;
				Iterator<?> iterator = cl.iterator();
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Object elm = iterator.next();
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
		} else if (f.getType().equals(HashSet.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				HashSet<?> cl = (HashSet<?>) obj;
				Iterator<?> iterator = cl.iterator();
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Object elm = iterator.next();
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
		} else if (f.getType().equals(Set.class)) {
			AnnotationUtil.renderAccessible(f);
			Object obj = f.get(object);
			if (obj != null) {
				Set<?> cl = (Set<?>) obj;
				Iterator<?> iterator = cl.iterator();
				int i = 0;
				while (iterator.hasNext()) {
					i++;
					Object elm = iterator.next();
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

		//////////////////

		return builder;
	}

}
