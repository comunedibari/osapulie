<%@ include file="/WEB-INF/jsp/common/common.jsp" %>

<h1><spring:message code="exception.generalError.title"/></h1>

<p>${exception.localizedMessage == null ? exception : exception.localizedMessage }<br/>
<spring:message code="exception.contactAdmin"/></p>

<p>${exception.class}</p>
<c:forEach var="stackTraceElem" items="${v.stackTrace}">
	<c:out value="${stackTraceElem}"/><br/>
</c:forEach>

<p style="text-align:center;"><a href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.home"/></a></p>
