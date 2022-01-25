<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="iscrizioneAlboScrutatoriUrl">
	<portlet:param name="ope" value="iscrizioneAlboScrutatori" />
</portlet:actionURL>

<portlet:actionURL var="uploadIscrizioneUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<p>
			<spring:message code="label.home.option1.part1" /><spring:message code="button.dichiarazione" /><spring:message code="label.home.option1.part2" /><br>
			<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
		</p>
		<a class="custom_pulsante" href="${iscrizioneAlboScrutatoriUrl}"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a class="custom_pulsante" href="${uploadIscrizioneUrl}"><spring:message code="link.uploadfile" /></a>
	</div>
</div>