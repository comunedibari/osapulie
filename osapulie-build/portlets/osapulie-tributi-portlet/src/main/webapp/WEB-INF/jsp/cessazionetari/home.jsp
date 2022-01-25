<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="cessazioneTariUrl">
	<portlet:param name="action" value="getCessazioneTariForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadCessazioneTariUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${cessazioneTariUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadCessazioneTariUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>