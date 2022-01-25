<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneCambioDomicilioUrl">
	<portlet:param name="ope" value="dichiarazioneCambioDomicilio" />
</portlet:actionURL>

<portlet:actionURL var="uploadCambioDomicilioUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>
<div class="mainDiv">
	<div class="container_pulsante">
		<a class="custom_pulsante" href="${dichiarazioneCambioDomicilioUrl}"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a class="custom_pulsante" href="${uploadCambioDomicilioUrl}"><spring:message code="link.uploadfile" /></a>
	</div>
</div>