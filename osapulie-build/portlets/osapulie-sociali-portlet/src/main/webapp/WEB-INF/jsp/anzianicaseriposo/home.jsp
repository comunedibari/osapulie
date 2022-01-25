<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="dichiarazioneCaseRiposoUrl">
	<portlet:param name="action" value="getDichiarazioneCaseRiposoForm" />
</portlet:actionURL>
<portlet:actionURL var="uploadDichiarazioneUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a class="custom_pulsante" href="${dichiarazioneCaseRiposoUrl}"><spring:message code="button.dichiarazione" /></a>
		<c:if test="${visualizzaTastoInviaDocumento == true}">
			<span class="spacer"></span>
			<a href="${uploadDichiarazioneUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
		</c:if>
	</div>
</div>