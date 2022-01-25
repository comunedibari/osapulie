package eng.tz.la.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditConfig;
import eng.tz.la.annotation.AuditField;
import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.annotation.AuditRequestParam;
import eng.tz.la.core.util.CheckTypeUtil;
import eng.tz.la.core.util.ClassTypeArrayToField;
import eng.tz.la.core.util.ClassTypeArrayToMethod;
import eng.tz.la.core.util.ClassTypeListToField;
import eng.tz.la.core.util.ClassTypeListToMethod;
import eng.tz.la.core.util.ClassTypeMapToField;
import eng.tz.la.core.util.ClassTypeMapToMethod;
import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class AnnotationUtil {

	public static MetaLine audit(Object object, MetaLine builder, Object request, Line<?> line,LogSettyng settyng)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException,
			InvocationTargetException {

		try {
			if (object != null) {
				Class<?> c = object.getClass();

				if (c.isAnnotationPresent(AuditConfig.class)) {

					AuditConfig auditConf = (AuditConfig) c.getAnnotation(AuditConfig.class);
					IMetaActor iAuditMeta = (IMetaActor) auditConf.metaActor().newInstance();
					line.setMetaActor(iAuditMeta);

				}

				if (c.isAnnotationPresent(AuditClass.class)) {
					AuditClass aud = ((AuditClass) c.getAnnotation(AuditClass.class));

					if (aud.print()) {
						builder.add(new MetaField(aud.key().equals("") ? "ClassName" : aud.key(), c.getName(), null));
					}

				}

				if (c.isAnnotationPresent(AuditClass.class) || c.isAnnotationPresent(AuditField.class)
						|| c.isAnnotationPresent(AuditMethod.class) || c.isAnnotationPresent(AuditRequestParam.class)) {

					for (Field f : c.getDeclaredFields()) {
						if (f.isAnnotationPresent(AuditField.class)) {
							AuditField aud = ((AuditField) f.getAnnotation(AuditField.class));

							if (aud.print()) {

								if (CheckTypeUtil.isCheckTypeWrite(f.getType())) {
									renderAccessible(f);
									builder.add(new MetaField(aud.key().equals("") ? f.getName() : aud.key(),
											f.get(object), CheckTypeUtil.printType(settyng, f.getType(), aud)));
								} else if (f.getType().isArray()) {
								 
									builder = ClassTypeArrayToField.typeArray(object, builder, request, line, f, aud,settyng);
								} else if (CheckTypeUtil.isCheckTypeToList(f.getType())) {
								 
									builder = ClassTypeListToField.typeList(object, builder, request, line, f, aud,settyng);
								} else if (CheckTypeUtil.isCheckTypeToMap(f.getType())) {
									 
									builder = ClassTypeMapToField.typeMap(object, builder, request, line, f, aud,settyng);
								} else {
									renderAccessible(f);
									Object objc = f.get(object);
									if (objc != null)
										builder = AnnotationUtil.audit(objc, builder, request, line,settyng);

								}

							}
							// ....
						}
					}

					for (Method m : c.getDeclaredMethods()) {
						if (m.isAnnotationPresent(AuditMethod.class)) {
							AuditMethod aud = ((AuditMethod) m.getAnnotation(AuditMethod.class));

							if (aud.print()) {

								if (aud.method().length <= 0) {

									Class<?> type = aud.type().equals(AuditMethod.class) ? m.getReturnType()
											: aud.type();

									if (m.getReturnType().isArray()) {

										builder = ClassTypeArrayToMethod.typeArray(object, builder, request, line, m,
												aud,settyng);

									} else if (CheckTypeUtil.isCheckTypeToList(type)) {
										builder = ClassTypeListToMethod.typeList(object, builder, request, line, m,
												aud,settyng);

									} else if (CheckTypeUtil.isCheckTypeToMap(type)) {
										builder = ClassTypeMapToMethod.typeMap(object, builder, request, line, m,
												aud,settyng);

									} else {

										if (m.getParameterTypes().length == 0) {
											renderAccessible(m);
											Object obj__ = m.invoke(object, new Object[] {});
											if (obj__ != null)
												if (CheckTypeUtil.isCheckTypeWrite(obj__.getClass())) {
													builder.add(new MetaField(
															(aud.key().equals("") ? m.getName() : aud.key()), obj__,
															CheckTypeUtil.printType(settyng, obj__.getClass(), aud)));

												} else {
													builder = AnnotationUtil.audit(obj__, builder, request, line,settyng);
												}
										}
									}

								} // ...

								else if (aud.method().length > 0) {

									Class<?> type = aud.type().equals(AuditMethod.class) ? m.getReturnType()
											: aud.type();

									if (m.getReturnType().isArray()) {

										builder = ClassTypeArrayToMethod.typeArray(object, builder, request, line, m,
												aud,settyng);

									}

									else if (CheckTypeUtil.isCheckTypeToList(type)) {
										builder = ClassTypeListToMethod.typeList(object, builder, request, line, m,
												aud,settyng);

									} else if (CheckTypeUtil.isCheckTypeToMap(type)) {
										builder = ClassTypeMapToMethod.typeMap(object, builder, request, line, m,
												aud,settyng);

									} else {
										renderAccessible(m);
										Object objCl = m.invoke(object, new Object[] {});
										if (objCl != null) {

											Class<?> cl = objCl.getClass();
											try {
												for (String metodo : aud.method()) {
													Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
													renderAccessible(mcl);
													if (mcl.getParameterTypes().length == 0) {
														Object objVal = mcl.invoke(objCl, new Object[] {});
														
														if (objVal!=null && CheckTypeUtil.isCheckTypeWrite(objVal.getClass())) {
														builder.add(new MetaField(
																(aud.key().equals("") ? mcl.getName() : aud.key()),
																objVal,CheckTypeUtil.printType(settyng, objVal.getClass(), aud)));
														}else{
															if(objVal!=null)
															builder = AnnotationUtil.audit(objVal, builder, request, line,settyng);	
														}
													}
												}
											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}

										}
									}

								} // ...

							} // ..print
						} // .. auditmethod

						if (m.isAnnotationPresent(AuditRequestParam.class) && request != null) {
							Annotation[][] parameterAnnotations = m.getParameterAnnotations();

							for (Annotation[] annotations : parameterAnnotations) {
								for (Annotation annot : annotations) {
									if (annot instanceof AuditRequestParam) {
										AuditRequestParam rp = (AuditRequestParam) annot;
										if (rp.print()) {
											try {
												Class<?> rcp = request.getClass();
												Method mPar = rcp.getDeclaredMethod("getParameter",
														new Class<?>[] { String.class });
												renderAccessible(mPar);
												Object values = mPar.invoke(request, new String(rp.name()));

												if (values != null) {
													builder.add(
															new MetaField((rp.key().equals("") ? rp.name() : rp.key()),
																	values,CheckTypeUtil.printType(settyng, values.getClass(), rp)));

												} else {
													Class<?> rca = request.getClass();
													Method mAttr = rca.getDeclaredMethod("getAttribute",
															new Class<?>[] { String.class });
													renderAccessible(mAttr);
													values = mAttr.invoke(request, new String(rp.name()));
													if (values != null) {
														builder.add(new MetaField(
																(rp.key().equals("") ? rp.name() : rp.key()), values,
																CheckTypeUtil.printType(settyng, values.getClass(), rp)));

													}
												}

											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}
										}
									}
								}
							}
						}

						if (request != null) {
							Annotation[][] parameterAnnotations = m.getParameterAnnotations();

							for (Annotation[] annotations : parameterAnnotations) {

								for (Annotation annot : annotations) {
									if (annot instanceof AuditRequestParam) {
										AuditRequestParam rp = (AuditRequestParam) annot;
										if (rp.print()) {

											try {
												Class<?> rcp = request.getClass();
												Method mPar = rcp.getDeclaredMethod("getParameter",
														new Class<?>[] { String.class });
												renderAccessible(mPar);
												Object values = mPar.invoke(request, new String(rp.name()));

												if (values != null) {
													builder.add(
															new MetaField((rp.key().equals("") ? rp.name() : rp.key()),
																	values,  CheckTypeUtil.printType(settyng, values.getClass(), rp)));

												} else {
													Class<?> rca = request.getClass();
													Method mAttr = rca.getDeclaredMethod("getAttribute",
															new Class<?>[] { String.class });
													renderAccessible(mAttr);
													values = mAttr.invoke(request, new String(rp.name()));
													if (values != null) {
														builder.add(new MetaField(
																(rp.key().equals("") ? rp.name() : rp.key()), values,
																CheckTypeUtil.printType(settyng, values.getClass(), rp)));

													}
												}

											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}

										}
									}
								}
							}

						}
					}

					return builder;
				}

			}

		} catch (SecurityException e) {

		}

		return builder;

	}

	public static MetaLine auditAllFieldNotAnnotation(Object object, MetaLine builder, Object request, Line<?> line,LogSettyng settyng)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, ClassNotFoundException,
			InvocationTargetException {

		try {
			if (object != null) {
				Class<?> c = object.getClass();

			   builder.add(new MetaField("ClassName", c.getName(), null));

				for (Field f : c.getDeclaredFields()) {
						if (CheckTypeUtil.isCheckTypeWrite(f.getType())) {
							renderAccessible(f);
							builder.add(new MetaField(f.getName(),f.get(object), null));
						}   

					}

					return builder;

			}

		} catch (SecurityException e) {

		}

		return builder;

	}

	
	public static Object getMetodResault(Object object, String method) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException {

		Class<?> c = object.getClass();

		Method[] m = c.getDeclaredMethods();
		for (Method mt : m) {
			renderAccessible(mt);
			if (mt.getName().equalsIgnoreCase(method)) {
				return mt.invoke(object, new Object[] {});
			}
		}

		return null;

	}

	public static void renderAccessible(Method method) {
		if ((!Modifier.isPublic(method.getModifiers()))
				|| (!Modifier.isPublic(method.getDeclaringClass().getModifiers()))) {
			method.setAccessible(true);
		}
	}

	public static void renderAccessible(Field field) {
		if ((!Modifier.isPublic(field.getModifiers()))
				|| (!Modifier.isPublic(field.getDeclaringClass().getModifiers()))) {
			field.setAccessible(true);
		}
	}

	public static void renderAccessible(Constructor<?> constructor) {
		if ((!Modifier.isPublic(constructor.getModifiers()))
				|| (!Modifier.isPublic(constructor.getDeclaringClass().getModifiers()))) {
			constructor.setAccessible(true);
		}
	}

}
