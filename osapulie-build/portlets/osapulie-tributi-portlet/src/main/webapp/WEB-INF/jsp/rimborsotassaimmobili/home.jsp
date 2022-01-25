<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="rimborsoIciUrl">
	<portlet:param name="action" value="getRimborsoTassaImmobiliForm" />
</portlet:actionURL>

<portlet:actionURL var="uploadRimborsoIciUrl">
	<portlet:param name="ope" value="getUploadForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<p>
			<spring:message code="label.home.option1.part1" /><spring:message code="button.dichiarazione" /><spring:message code="label.home.option1.part2" /><br>
			<spring:message code="label.home.option2.part1" /><spring:message code="link.uploadfile" /><spring:message code="label.home.option2.part2" />
		</p>
		<a href="${rimborsoIciUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
		<span class="spacer"></span>
		<a href="${uploadRimborsoIciUrl}" class="custom_pulsante"><spring:message code="link.uploadfile" /></a>
	</div>
</div>