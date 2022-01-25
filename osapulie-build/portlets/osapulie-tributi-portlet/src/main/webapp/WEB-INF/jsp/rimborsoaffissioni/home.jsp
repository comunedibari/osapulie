<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoAffissioniUrl">
	<portlet:param name="action" value="getRimborsoAffissioniForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadRimborsoAffissioniUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${rimborsoAffissioniUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadRimborsoAffissioniUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>	