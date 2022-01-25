<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="iscrizioneAnagrafeTemporaneaUrl">
	<portlet:param name="action" value="getIscrizioneTemporaneaForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadIscrizioneAnagrafeTemporaneaUrl">
	<portlet:param name="action" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a class="custom_pulsante" href="${iscrizioneAnagrafeTemporaneaUrl}"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a class="custom_pulsante" href="${uploadIscrizioneAnagrafeTemporaneaUrl}"><spring:message code="link.uploadfile" /></a>
	</div>
</div>