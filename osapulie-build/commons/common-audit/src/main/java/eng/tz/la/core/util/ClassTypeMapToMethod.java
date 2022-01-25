package eng.tz.la.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.core.AnnotationUtil;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class ClassTypeMapToMethod {

	public static MetaLine typeMap(Object object, MetaLine builder, Object request, Line<?> line, Method m,
			AuditMethod aud,LogSettyng settyng) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		if(object!=null)
		if (m.getReturnType().equals(LinkedHashMap.class) || aud.type().equals(LinkedHashMap.class)) {
			AnnotationUtil.renderAccessible(m);
			Object obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				LinkedHashMap<?, ?> l = (LinkedHashMap<?, ?>) obj;
				Object[] key = l.keySet().toArray();
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(key[i]);
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
											if(aud.key()!=null)
												builder.add(new MetaField(m.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
												
											builder = AnnotationUtil.audit(objVal, builder, request, line,settyng);
										}
									}
								}
							} else {
								Class<?> type = elm.getClass();
								if (CheckTypeUtil.isCheckTypeWrite(type)) {
									builder.add(new MetaField(
											aud.key().equals("") ? (m.getName() + "_" + i) : (aud.key() + "_" + i), elm,
													aud.printType()?type:null));

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
		} else if (m.getReturnType().equals(HashMap.class) || aud.type().equals(HashMap.class)) {
			AnnotationUtil.renderAccessible(m);
			Object obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				HashMap<?, ?> l = (HashMap<?, ?>) obj;
				Object[] key = l.keySet().toArray();
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(key[i]);
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
											if(aud.key()!=null)
												builder.add(new MetaField(m.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
												
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
		} else if (m.getReturnType().equals(Hashtable.class) || aud.type().equals(Hashtable.class)) {
			AnnotationUtil.renderAccessible(m);
			Object obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Hashtable<?, ?> l = (Hashtable<?, ?>) obj;
				Object[] key = l.keySet().toArray();
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(key[i]);
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
											if(aud.key()!=null)
												builder.add(new MetaField(m.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
												
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
		} else if (m.getReturnType().equals(Map.class) || aud.type().equals(Map.class)) {
			AnnotationUtil.renderAccessible(m);
			Object obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Map<?, ?> l = (Map<?, ?>) obj;
				Object[] key = l.keySet().toArray();
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(key[i]);
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
											if(aud.key()!=null)
												builder.add(new MetaField(m.getName(), aud.key(),CheckTypeUtil.printType(settyng, type, aud)));
												
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
