<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="variazioneTasiUrl">
	<portlet:param name="action" value="getVariazioneTasiForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadVariazioneTasiUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${variazioneTasiUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadVariazioneTasiUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>