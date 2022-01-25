<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoServiziCimiterialiUrl">
	<portlet:param name="action" value="getRimborsoServiziCimiterialiForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadServiziCimiterialUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>
	
<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${rimborsoServiziCimiterialiUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadServiziCimiterialUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>	