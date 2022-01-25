package eng.tz.la.core;

import java.lang.reflect.InvocationTargetException;

import eng.tz.la.model.Line;
import eng.tz.la.model.LogSettyng;
import eng.tz.la.model.MetaLine;

/**
 * @author s.mariniello
 */

public class AnnotationFactory {

	private static AnnotationFactory annotationFactory;
	public LogSettyng logSettyng;

	private AnnotationFactory(LogSettyng logSettyng) {
		this.logSettyng = logSettyng;
	}

	public static AnnotationFactory get(LogSettyng logSettyng) {

		if (annotationFactory == null) {
			annotationFactory = new AnnotationFactory(logSettyng);
		} else {
			annotationFactory.logSettyng = logSettyng;
		}

		return annotationFactory;
	}

	public static Line<MetaLine> audit(Object obj, Object request, LogSettyng logSettyng) {
		Line<MetaLine> lineBuilder = auditBuilder(obj, request, logSettyng);
		if (lineBuilder == null) {
			return new Line<MetaLine>();
		}
		return lineBuilder;
	}

	public static Line<MetaLine> audit(Object obj, LogSettyng logSettyng) {
		Line<MetaLine> lineBuilder = auditBuilder(obj, null, logSettyng);
		if (lineBuilder == null) {
			return new Line<MetaLine>();
		}
		return lineBuilder;
	}

	public static Line<MetaLine> auditNotAnnotation(Object obj, LogSettyng logSettyng) {
		Line<MetaLine> lineBuilder = auditBuilderNotAnnotation(obj, null, logSettyng);
		if (lineBuilder == null) {
			return new Line<MetaLine>();
		}
		return lineBuilder;
	}

	private static Line<MetaLine> auditBuilder(Object obj, Object request, LogSettyng logSettyng) {
		try {
			return AnnotationFactory.get(logSettyng).auditBean(obj, request);
		} catch (IllegalArgumentException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		} catch (ClassNotFoundException e) {

		} catch (InvocationTargetException e) {

		}
		return new Line<MetaLine>();
	}

	private static Line<MetaLine> auditBuilderNotAnnotation(Object obj, Object request, LogSettyng logSettyng) {
		try {
			return AnnotationFactory.get(logSettyng).auditBeanNotAnnotation(obj, request);
		} catch (IllegalArgumentException e) {

		} catch (InstantiationException e) {

		} catch (IllegalAccessException e) {

		} catch (ClassNotFoundException e) {

		} catch (InvocationTargetException e) {

		}
		return new Line<MetaLine>();
	}

	public Line<MetaLine> auditBean(Object obj, Object objRequest) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException {
		Line<MetaLine> line = new Line<MetaLine>();
		MetaLine builder = new MetaLine();
		if (obj != null) {
			builder = AnnotationUtil.audit(obj, builder, objRequest, line, this.logSettyng);
			line.setT(builder);
		}
		return line;
	}

	public Line<MetaLine> auditBeanNotAnnotation(Object obj, Object objRequest) throws InstantiationException,
			IllegalAccessException, IllegalArgumentException, ClassNotFoundException, InvocationTargetException {
		Line<MetaLine> line = new Line<MetaLine>();
		MetaLine builder = new MetaLine();
		if (obj != null) {
			builder = AnnotationUtil.auditAllFieldNotAnnotation(obj, builder, objRequest, line, this.logSettyng);
			line.setT(builder);
		}
		return line;
	}

}
