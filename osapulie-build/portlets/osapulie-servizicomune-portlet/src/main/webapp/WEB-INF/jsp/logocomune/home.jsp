<%@ include file="../common/common.jsp"%>

<div id="logoComuneDiv" class="logoComuneDiv">
	<portlet:resourceURL var="showLogoUrl" id="showLogo">
		<portlet:param name="id" value="${idComuneIsa}" />
	</portlet:resourceURL>
	<c:if test="${!empty nomeComune}">
		<img class="logoComuneImg" src="${showLogoUrl}"
			alt="<spring:message code="label.logo.per" arguments="${nomeComune}" />"
			title="<spring:message code="label.logo.per" arguments="${nomeComune}" />" />
	</c:if>
</div>