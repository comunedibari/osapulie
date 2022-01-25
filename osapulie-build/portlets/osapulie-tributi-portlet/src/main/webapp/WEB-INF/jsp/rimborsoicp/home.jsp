<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoIcpUrl">
	<portlet:param name="action" value="getRimborsoIcpForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadRimborsoIcpUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${rimborsoIcpUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadRimborsoIcpUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>	