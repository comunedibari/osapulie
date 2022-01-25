<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="variazioneImuUrl">
	<portlet:param name="action" value="getVariazioneImuForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadvariazioneImuUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${variazioneImuUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadvariazioneImuUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>