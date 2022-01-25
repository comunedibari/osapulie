package eng.tz.la.core.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
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

public class ClassTypeListToMethod {

	public static MetaLine typeList(Object object, MetaLine builder, Object request, Line<?> line, Method m,
			AuditMethod aud,LogSettyng settyng) throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			ClassNotFoundException, InvocationTargetException {
		if(object!=null)
		if (m.getReturnType().equals(LinkedList.class)|| aud.type().equals(LinkedList.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				LinkedList<?> l = (LinkedList<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);

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
		}else if (m.getReturnType().equals(ArrayList.class)|| aud.type().equals(ArrayList.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				ArrayList<?> l = (ArrayList<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);

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
		else if (m.getReturnType().equals(List.class)|| aud.type().equals(List.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				List<?> l = (List<?>) obj;
				for (int i = 0; i < l.size(); i++) {
					Object elm = l.get(i);

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
		} else if (m.getReturnType().equals(Collection.class)|| aud.type().equals(Collection.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Collection<?> l = (Collection<?>) obj;
				Iterator<?> iterator = l.iterator();
				int i = 0;
				while (iterator.hasNext()) {

					Object elm = iterator.next();

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
													settyng.isPrintType()?type:(aud.printType()?type:null)));

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
		} else if (m.getReturnType().equals(Iterator.class)|| aud.type().equals(Iterator.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Iterator<?> iterator = (Iterator<?>) obj;
				int i = 0;
				while (iterator.hasNext()) {

					Object elm = iterator.next();

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
		} else if (m.getReturnType().equals(LinkedHashSet.class)|| aud.type().equals(LinkedHashSet.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				LinkedHashSet<?> l = (LinkedHashSet<?>) obj;
				if (l != null && !l.isEmpty()) {
					Iterator<?> iterator = l.iterator();
					int i = 0;
					while (iterator.hasNext()) {

						Object elm = iterator.next();

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

												builder.add(new MetaField(
														(aud.key().equals("") ? mcl.getName() : aud.key()), objVal,
														CheckTypeUtil.printType(settyng, type, aud)));
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
												aud.key().equals("") ? (m.getName() + "_" + i) : (aud.key() + "_" + i),
												elm, CheckTypeUtil.printType(settyng, type, aud)));

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
		} else if (m.getReturnType().equals(HashSet.class) || aud.type().equals(HashSet.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				HashSet<?> l = (HashSet<?>) obj;
				if (l != null && !l.isEmpty()) {
					Iterator<?> iterator = l.iterator();
					int i = 0;
					while (iterator.hasNext()) {

						Object elm = iterator.next();

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

												builder.add(new MetaField(
														(aud.key().equals("") ? mcl.getName() : aud.key()), objVal,
														CheckTypeUtil.printType(settyng, type, aud)));
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
												aud.key().equals("") ? (m.getName() + "_" + i) : (aud.key() + "_" + i),
												elm, CheckTypeUtil.printType(settyng, type, aud)));

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
		} else if (m.getReturnType().equals(Set.class) || aud.type().equals(Set.class)) {
			AnnotationUtil.renderAccessible(m);
			//Object obj = m.invoke(object, new Object[] {});
			Object obj =null;
			if(m.getParameterTypes().length==0)
			obj = m.invoke(object, new Object[] {});
			if (obj != null) {
				Set<?> l = (Set<?>) obj;
				if (l != null && !l.isEmpty()) {
					Iterator<?> iterator = l.iterator();
					int i = 0;
					while (iterator.hasNext()) {

						Object elm = iterator.next();

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

												builder.add(new MetaField(
														(aud.key().equals("") ? mcl.getName() : aud.key()), objVal,
														CheckTypeUtil.printType(settyng, type, aud)));
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
												aud.key().equals("") ? (m.getName() + "_" + i) : (aud.key() + "_" + i),
												elm, CheckTypeUtil.printType(settyng, type, aud)));

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
		} 

		
		
		return builder;
	}

}
