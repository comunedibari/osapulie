<%@ include file="../common/common.jsp"%>
<div class="mainDiv">
	<c:choose>
		<c:when test="${empty risposta.errore && !empty risposta.documento}">
			<div class="container_pulsante">
				<portlet:resourceURL var="downloadDocumentoURL" id="downloadDocumento" escapeXml="false">
					<portlet:param name="idDocumento" value="${risposta.documento[0].id}" />
				</portlet:resourceURL>
				<a href="${downloadDocumentoURL}" class="custom_pulsante evaluationServiceDownloadLink"><spring:message code="button.downloadDocumento" /></a>
				<%@ include file="../common/valutaservizio.jsp"%>
			</div>
		</c:when>
		<c:otherwise>
			<spring:message code="label.documentoNonTrovato" />
		</c:otherwise>
	</c:choose>
</div>