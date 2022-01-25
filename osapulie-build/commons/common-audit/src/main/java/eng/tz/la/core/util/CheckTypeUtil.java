package eng.tz.la.core.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eng.tz.la.annotation.AuditField;
import eng.tz.la.annotation.AuditMethod;
import eng.tz.la.annotation.AuditRequestParam;
import eng.tz.la.model.LogSettyng;

/**
 * @author s.mariniello
 */

public class CheckTypeUtil {

	public static boolean isCheckTypeWrite(Class<?> type) {
		if (type.isPrimitive() || type.equals(String.class) || type.equals(Character.class) || type.equals(Date.class)
				|| type.equals(Timestamp.class) || type.equals(java.sql.Date.class) || type.equals(Boolean.class)
				|| type.equals(Integer.class) || type.equals(Double.class) || type.equals(Short.class)
				|| type.equals(Long.class) || type.equals(BigDecimal.class) || type.equals(BigInteger.class)
				|| type.equals(Float.class) || type.equals(StringBuilder.class) || type.equals(StringBuffer.class)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isCheckTypeToList(Class<?> type) {
		if (type.equals(LinkedList.class) || type.equals(ArrayList.class) || type.equals(List.class)
				|| type.equals(Collection.class) || type.equals(Iterator.class) || type.equals(LinkedHashSet.class)
				|| type.equals(HashSet.class) || type.equals(Set.class)) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean isCheckTypeToMap(Class<?> type) {
		if (type.equals(LinkedHashMap.class) || type.equals(HashMap.class) || type.equals(Hashtable.class)
				|| type.equals(Map.class)) {
			return true;
		} else {
			return false;
		}

	}

	public static Class<?> printType(LogSettyng settyng, Class<?> type, AuditMethod aud) {

		return settyng.isPrintType() ? type : (aud.printType() ? type : null);

	}

	public static Class<?> printType(LogSettyng settyng, Class<?> type, AuditField aud) {

		return settyng.isPrintType() ? type : (aud.printType() ? type : null);

	}

	public static Class<?> printType(LogSettyng settyng, Class<?> type, AuditRequestParam aud) {

		return settyng.isPrintType() ? type : (aud.printType() ? type : null);

	}

}
