<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoTassaRifiutiUrl">
	<portlet:param name="action" value="getRimborsoTassaRifiutiForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadRimborsoTassaRifiutiUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${rimborsoTassaRifiutiUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadRimborsoTassaRifiutiUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>
