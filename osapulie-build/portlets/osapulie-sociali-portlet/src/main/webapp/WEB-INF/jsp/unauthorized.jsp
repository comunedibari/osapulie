<%@ include file="/WEB-INF/jsp/common/common.jsp"%>

<h1>
	<spring:message code="exception.unauthorized.title" />
</h1>

<p>
	<spring:message code="exception.unauthorized.message" />
	<c:if test="${not empty unauthorizedExceptionMessage}">
		<br>
		<c:out value="${unauthorizedExceptionMessage}" escapeXml="false" />
	</c:if>
</p>