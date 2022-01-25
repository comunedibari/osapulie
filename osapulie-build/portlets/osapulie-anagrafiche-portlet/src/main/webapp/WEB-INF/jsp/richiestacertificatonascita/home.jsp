<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoNascitaReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="certificatoSaveSignatureURL" id="certificatoNascitaSaveSignature" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>


<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<%--RESOURCE UTILIZZATA PER LA DEMO DEL 26/01/2015 --%>
<portlet:resourceURL var="reportPdfDEMOURL" id="reportPdfDEMO" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="richiestaCertificatoNascita" />

<c:choose>
	<c:when test="${empty mostraLink}">
		<c:set var="mostraLink" value="si" />
	</c:when>
	<c:otherwise>
		<c:set var="mostraLink" value="${mostraLink}" />
	</c:otherwise>
</c:choose>
<c:set var="mostraLinkReportPdf" value="no" />

<%@ include file="../common/formrichiestacertificato.jsp"%>