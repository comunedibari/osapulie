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
<%@ page import="javax.portlet.RenderRequest" %>
<%@ page import="javax.portlet.RenderResponse" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="javax.portlet.PortletSession" %>
<portlet:defineObjects />
<script src="<%=request.getContextPath(  ) %>/javascript/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/jquery-ui.custom.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=request.getContextPath(  ) %>/javascript/jquery.dynatree-1.2.4.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" media="all" href="/osapulie-consultazionepraticheweb-portlet/calendar/calendar-win2k-1.css" title="win2k-1" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath(  ) %>/css/skin/ui.dynatree.css">
<script type="text/javascript" src="<%=request.getContextPath(  ) %>/calendar/calendar.js"></script>
<script type="text/javascript" src="<%=request.getContextPath(  ) %>/calendar/lang/calendar-it.js?c=1"></script>
<script type="text/javascript" src="<%=request.getContextPath(  ) %>/calendar/calendar-setup.js"></script>

<!-- Messaggi di ok e ko -->
<c:if test="${message != null}">
	<div class="portlet-msg-success"><c:out value="${message}"/>.</div>
</c:if>
<c:if test="${portletFormError != null}">
	<div class="portlet-msg-error"><c:out value="${portletFormError}"/>.</div>
</c:if>