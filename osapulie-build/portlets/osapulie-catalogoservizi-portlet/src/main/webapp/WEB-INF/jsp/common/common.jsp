<%@ page contentType="text/html" isELIgnored="false" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<%@ taglib uri="http://jakarta.apache.org/taglibs/unstandard-1.0" prefix="un" %>
<%@ page import="javax.portlet.RenderRequest" %>
<%@ page import="javax.portlet.RenderResponse" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletSession" %>
<un:useConstants var="PortletConstants" className="it.osapulie.web.portlet.util.PortletConstants" />
<un:useConstants var="InnerPortletConstants" className="it.osapulie.catalogoservizi.web.utils.PortletConstants" />
<un:useConstants var="AuthenticationChannel" className="it.osapulie.shared.enumeration.AuthenticationChannel" />

<portlet:defineObjects />

<!-- Messaggi di ok e ko -->
<c:if test="${message != null}">
	<div class="portlet-msg-success"><c:out value="${message}"/>.</div>
</c:if>
<c:if test="${formError != null}">
	<div class="portlet-msg-error"><c:out value="${formError}"/>.</div>
</c:if>