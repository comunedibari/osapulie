<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoCosapTosapUrl">
	<portlet:param name="action" value="getRimborsoCosapTosapForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadCosapTosapUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${rimborsoCosapTosapUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadCosapTosapUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>