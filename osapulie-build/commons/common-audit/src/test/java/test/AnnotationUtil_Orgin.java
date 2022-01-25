package test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import eng.tz.la.annotation.AuditClass;
import eng.tz.la.annotation.AuditConfig;
import eng.tz.la.annotation.AuditField;
import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.annotation.AuditRequestParam;
import eng.tz.la.core.IMetaActor;
import eng.tz.la.model.Line;
import eng.tz.la.model.MetaField;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class AnnotationUtil_Orgin {

	public static MetaLine audit(Object object, MetaLine builder, Object request, Line<?> line)
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

								if (f.getType().equals(String.class) || f.getType().equals(Character.class)
										|| f.getType().equals(Date.class) || f.getType().equals(Timestamp.class)
										|| f.getType().equals(Boolean.class) || f.getType().equals(Integer.class)
										|| f.getType().equals(Double.class) || f.getType().equals(Short.class)
										|| f.getType().equals(Long.class) || f.getType().equals(BigDecimal.class)
										|| f.getType().equals(BigInteger.class) || f.getType().equals(Float.class)
										|| f.getType().equals(Blob.class) || f.getType().equals(StringBuilder.class)
										|| f.getType().equals(StringBuffer.class)) {

									renderAccessible(f);
									builder.add(new MetaField(aud.key().equals("") ? f.getName() : aud.key(),
											f.get(object), f.getType()));

								} else if (f.getType().isArray()) {
									renderAccessible(f);
									Object obj = f.get(object);
									if (obj != null) {
										Object[] l = (Object[]) obj;
										for (int i = 0; i < l.length; i++) {
											Object elm = l[i];
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} 
								else if (f.getType().equals(LinkedList.class)) {
									renderAccessible(f);
									Object obj = f.get(object);
									if (obj != null) {
										LinkedList<?> l = (LinkedList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (f.getType().equals(ArrayList.class)) {
									renderAccessible(f);
									Object obj = f.get(object);
									if (obj != null) {
										ArrayList<?> l = (ArrayList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (f.getType().equals(List.class)) {
									renderAccessible(f);
									Object obj = f.get(object);
									if (obj != null) {
										List<?> l = (List<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (f.getType().equals(Collection.class)) {
									renderAccessible(f);
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
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (f.getType().equals(Iterator.class)) {
									renderAccessible(f);
									Object obj = f.get(object);
									if (obj != null) {
										Iterator<?> iterator = (Iterator<?>) obj;
										int i = 0;
										while (iterator.hasNext()) {
											i++;
											Object elm = iterator.next();
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (f.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else {
									renderAccessible(f);
									Object objc = f.get(object);
									if (objc != null)
										builder = AnnotationUtil_Orgin.audit(objc, builder, request, line);

								}

							}
							// ....
						}
					}

					for (Method m : c.getDeclaredMethods()) {
						if (m.isAnnotationPresent(AuditMethod.class)) {
							AuditMethod aud = ((AuditMethod) m.getAnnotation(AuditMethod.class));
							if (aud.print() && aud.type().getName().equals(AuditMethod.class.getName())
									&& aud.method().length <= 0) {
								
								if (m.getReturnType().isArray()) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Object[] l = (Object[]) obj;
										for (int i = 0; i < l.length; i++) {
											Object elm = l[i];
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								}else{
									renderAccessible(m);
								Class<?>[] parameterTypes = m.getParameterTypes();

								builder.add(new MetaField((aud.key().equals("") ? m.getName() : aud.key()),
										m.invoke(object, new Object[] {}), m.getReturnType()));
								}
							} else if (!aud.type().getName().equals(AuditMethod.class.getName())
									&& aud.method().length <= 0) {

								///////////////////////////////////////
								if (m.getReturnType().isArray()) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Object[] l = (Object[]) obj;
										for (int i = 0; i < l.length; i++) {
											Object elm = l[i];
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								}
								else if (aud.type().equals(LinkedList.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										LinkedList<?> l = (LinkedList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (aud.type().equals(ArrayList.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										ArrayList<?> l = (ArrayList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (aud.type().equals(List.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										List<?> l = (List<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (aud.type().equals(Collection.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Collection<?> cl = (Collection<?>) obj;
										Iterator<?> iterator = cl.iterator();
										int i = 0;
										while (iterator.hasNext()) {
											i++;
											Object elm = iterator.next();
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}
											}
										}
									}
								} else if (aud.type().equals(Iterator.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Iterator<?> iterator = (Iterator<?>) obj;
										int i = 0;
										while (iterator.hasNext()) {
											i++;
											Object elm = iterator.next();
											if (elm != null) {
												Class<?> type = elm.getClass();
												if (type.equals(String.class) || type.equals(Character.class)
														|| type.equals(Date.class) || type.equals(Timestamp.class)
														|| type.equals(Boolean.class) || type.equals(Integer.class)
														|| type.equals(Double.class) || type.equals(Short.class)
														|| type.equals(Long.class) || type.equals(BigDecimal.class)
														|| type.equals(BigInteger.class) || type.equals(Float.class)
														|| type.equals(StringBuilder.class)
														|| type.equals(StringBuffer.class)) {
													builder.add(
															new MetaField(aud.key().equals("") ? (m.getName() + "_" + i)
																	: (aud.key() + "_" + i), elm, type));

												} else {

													builder = AnnotationUtil_Orgin.audit(elm, builder, request, line);
												}

											}
										}
									}
								} else {
									renderAccessible(m);
									Object object2 = m.invoke(object, new Object[] {});
									if (object2 != null)
										builder = AnnotationUtil_Orgin.audit(object2, builder, request, line);
								}

								/////////////////////////////////////
							} else if (!aud.type().getName().equals(AuditMethod.class.getName())
									&& aud.method().length > 0) {

								///////////////////////////////////////
								if (m.getReturnType().isArray()) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Object[] l = (Object[]) obj;
										for (int i = 0; i < l.length; i++) {
											Object elm = l[i];
											
											///////
											if(elm!=null){
											Class<?> cl = elm.getClass();
											try {
												for (String metodo : aud.method()) {
													Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
													renderAccessible(mcl);
													Object objVal = mcl.invoke(elm, new Object[] {});
													if (objVal != null) {
														Class<?> type = objVal.getClass();
														if (type.equals(String.class) || type.equals(Character.class)
																|| type.equals(Date.class)
																|| type.equals(Timestamp.class)
																|| type.equals(Boolean.class)
																|| type.equals(Integer.class)
																|| type.equals(Double.class) || type.equals(Short.class)
																|| type.equals(Long.class)
																|| type.equals(BigDecimal.class)
																|| type.equals(BigInteger.class)
																|| type.equals(Float.class)
																|| type.equals(StringBuilder.class)
																|| type.equals(StringBuffer.class)) {

															builder.add(new MetaField(
																	(aud.key().equals("") ? mcl.getName() : aud.key()),
																	objVal, objVal.getClass()));
														} else {

															builder = AnnotationUtil_Orgin.audit(objVal, builder, request,
																	line);
														}

													}
												}
											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}
											}
											///////

										}
									}
								}
								else if (aud.type().equals(LinkedList.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										LinkedList<?> l = (LinkedList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);
											
											///////
											if(elm!=null){
											Class<?> cl = elm.getClass();
											try {
												for (String metodo : aud.method()) {
													Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
													renderAccessible(mcl);
													Object objVal = mcl.invoke(elm, new Object[] {});
													if (objVal != null) {
														Class<?> type = objVal.getClass();
														if (type.equals(String.class) || type.equals(Character.class)
																|| type.equals(Date.class)
																|| type.equals(Timestamp.class)
																|| type.equals(Boolean.class)
																|| type.equals(Integer.class)
																|| type.equals(Double.class) || type.equals(Short.class)
																|| type.equals(Long.class)
																|| type.equals(BigDecimal.class)
																|| type.equals(BigInteger.class)
																|| type.equals(Float.class)
																|| type.equals(StringBuilder.class)
																|| type.equals(StringBuffer.class)) {

															builder.add(new MetaField(
																	(aud.key().equals("") ? mcl.getName() : aud.key()),
																	objVal, objVal.getClass()));
														} else {

															builder = AnnotationUtil_Orgin.audit(objVal, builder, request,
																	line);
														}

													}
												}
											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}
											}
											///////

										}
									}
								} else if (aud.type().equals(ArrayList.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										ArrayList<?> l = (ArrayList<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);

											///////
											if(elm!=null){
											Class<?> cl = elm.getClass();
											try {
												for (String metodo : aud.method()) {
													Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
													renderAccessible(mcl);
													Object objVal = mcl.invoke(elm, new Object[] {});
													if (objVal != null) {
														Class<?> type = objVal.getClass();
														if (type.equals(String.class) || type.equals(Character.class)
																|| type.equals(Date.class)
																|| type.equals(Timestamp.class)
																|| type.equals(Boolean.class)
																|| type.equals(Integer.class)
																|| type.equals(Double.class) || type.equals(Short.class)
																|| type.equals(Long.class)
																|| type.equals(BigDecimal.class)
																|| type.equals(BigInteger.class)
																|| type.equals(Float.class)
																|| type.equals(StringBuilder.class)
																|| type.equals(StringBuffer.class)) {

															builder.add(new MetaField(
																	(aud.key().equals("") ? mcl.getName() : aud.key()),
																	objVal, objVal.getClass()));
														} else {

															builder = AnnotationUtil_Orgin.audit(objVal, builder, request,
																	line);
														}

													}
												}
											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}
											}
											///////

										}
									}
								} else if (aud.type().equals(List.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										List<?> l = (List<?>) obj;
										for (int i = 0; i < l.size(); i++) {
											Object elm = l.get(i);

											///////
											if(elm!=null){
											Class<?> cl = elm.getClass();
											try {
												for (String metodo : aud.method()) {
													Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
													renderAccessible(mcl);
													Object objVal = mcl.invoke(elm, new Object[] {});
													if (objVal != null) {
														Class<?> type = objVal.getClass();
														if (type.equals(String.class) || type.equals(Character.class)
																|| type.equals(Date.class)
																|| type.equals(Timestamp.class)
																|| type.equals(Boolean.class)
																|| type.equals(Integer.class)
																|| type.equals(Double.class) || type.equals(Short.class)
																|| type.equals(Long.class)
																|| type.equals(BigDecimal.class)
																|| type.equals(BigInteger.class)
																|| type.equals(Float.class)
																|| type.equals(StringBuilder.class)
																|| type.equals(StringBuffer.class)) {

															builder.add(new MetaField(
																	(aud.key().equals("") ? mcl.getName() : aud.key()),
																	objVal, objVal.getClass()));
														} else {

															builder = AnnotationUtil_Orgin.audit(objVal, builder, request,
																	line);
														}

													}
												}
											} catch (NoSuchMethodException e) {
											} catch (Exception e) {
											}
											}
											///////

										}
									}
								} else if (aud.type().equals(Collection.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Collection<?> coll = (Collection<?>) obj;
										Iterator<?> iterator = coll.iterator();
										int i = 0;
										while (iterator.hasNext()) {
											i++;
											Object elm = iterator.next();
											if (elm != null) {
												///////

												Class<?> cl = elm.getClass();
												try {
													for (String metodo : aud.method()) {
														Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
														renderAccessible(mcl);
														Object objVal = mcl.invoke(elm, new Object[] {});

														if (objVal != null) {
															Class<?> type = objVal.getClass();
															if (type.equals(String.class)
																	|| type.equals(Character.class)
																	|| type.equals(Date.class)
																	|| type.equals(Timestamp.class)
																	|| type.equals(Boolean.class)
																	|| type.equals(Integer.class)
																	|| type.equals(Double.class)
																	|| type.equals(Short.class)
																	|| type.equals(Long.class)
																	|| type.equals(BigDecimal.class)
																	|| type.equals(BigInteger.class)
																	|| type.equals(Float.class)
																	|| type.equals(StringBuilder.class)
																	|| type.equals(StringBuffer.class)) {

																builder.add(new MetaField(
																		(aud.key().equals("") ? mcl.getName()
																				: aud.key()),
																		objVal, objVal.getClass()));
															} else {

																builder = AnnotationUtil_Orgin.audit(objVal, builder, request,
																		line);
															}

														}
													}
												} catch (NoSuchMethodException e) {
												} catch (Exception e) {
												}
											}

										}
									}
								} else if (aud.type().equals(Iterator.class)) {
									renderAccessible(m);
									Object obj = m.invoke(object, new Object[] {});
									if (obj != null) {
										Iterator<?> iterator = (Iterator<?>) obj;
										int i = 0;
										while (iterator.hasNext()) {
											i++;
											Object elm = iterator.next();
											if (elm != null) {
												///////

												Class<?> cl = elm.getClass();
												try {
													for (String metodo : aud.method()) {
														Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
														renderAccessible(mcl);
														Object objVal = mcl.invoke(elm, new Object[] {});
														if (objVal != null){
															Class<?> type = objVal.getClass();
															if (type.equals(String.class)
																	|| type.equals(Character.class)
																	|| type.equals(Date.class)
																	|| type.equals(Timestamp.class)
																	|| type.equals(Boolean.class)
																	|| type.equals(Integer.class)
																	|| type.equals(Double.class)
																	|| type.equals(Short.class)
																	|| type.equals(Long.class)
																	|| type.equals(BigDecimal.class)
																	|| type.equals(BigInteger.class)
																	|| type.equals(Float.class)
																	|| type.equals(StringBuilder.class)
																	|| type.equals(StringBuffer.class)) {

																builder.add(new MetaField(
																		(aud.key().equals("") ? mcl.getName()
																				: aud.key()),
																		objVal, objVal.getClass()));
															} else {

																builder = AnnotationUtil_Orgin.audit(elm, builder, request,
																		line);
															}
														}
													}
												} catch (NoSuchMethodException e) {
												} catch (Exception e) {
												}
											}

										}
									}
								} else {
									renderAccessible(m);
									Object objCl = m.invoke(object, new Object[] {});
									if (objCl != null) {

										Class<?> cl = objCl.getClass();
										try {
											for (String metodo : aud.method()) {
												Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
												renderAccessible(mcl);
												Object objVal = mcl.invoke(objCl, new Object[] {});
													builder.add(new MetaField(
															(aud.key().equals("") ? mcl.getName() : aud.key()), objVal,
															objVal==null?null:objVal.getClass()));
											}
										} catch (NoSuchMethodException e) {
										} catch (Exception e) {
										}

									}
								}

								/////////////////////////////////////
							} else if (aud.type().getName().equals(AuditMethod.class.getName())
									&& aud.method().length > 0)

							{
								renderAccessible(m);
								Object objCl = m.invoke(object, new Object[] {});
								if (objCl != null) {
										 
									Class<?> cl = objCl.getClass();
									try {
										for (String metodo : aud.method()) {
											Method mcl = cl.getDeclaredMethod(metodo, new Class<?>[] {});
											renderAccessible(mcl);
											Object objVal = mcl.invoke(objCl, new Object[] {});
												builder.add(new MetaField(
														(aud.key().equals("") ? mcl.getName() : aud.key()), objVal,
														objVal==null?null:objVal.getClass()));
										}
									} catch (NoSuchMethodException e) {
									} catch (Exception e) {
									}

								}

							}
							// ....
						}

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
																	values, values.getClass()));

												} else {
													Class<?> rca = request.getClass();
													Method mAttr = rca.getDeclaredMethod("getAttribute",
															new Class<?>[] { String.class });
													renderAccessible(mAttr);
													values = mAttr.invoke(request, new String(rp.name()));
													if (values != null) {
														builder.add(new MetaField(
																(rp.key().equals("") ? rp.name() : rp.key()), values,
																values.getClass()));

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
																	values, values.getClass()));

												} else {
													Class<?> rca = request.getClass();
													Method mAttr = rca.getDeclaredMethod("getAttribute",
															new Class<?>[] { String.class });
													renderAccessible(mAttr);
													values = mAttr.invoke(request, new String(rp.name()));
													if (values != null) {
														builder.add(new MetaField(
																(rp.key().equals("") ? rp.name() : rp.key()), values,
																values.getClass()));

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
