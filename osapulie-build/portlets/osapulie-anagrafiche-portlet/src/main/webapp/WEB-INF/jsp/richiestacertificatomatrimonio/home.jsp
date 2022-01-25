<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoMatrimonioReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="certificatoSaveSignatureURL" id="certificatoMatrimonioSaveSignature" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>


<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="richiestaCertificatoMatrimonio" />

<c:set var="mostraLink" value="no" />	
<c:set var="mostraLinkReportPdf" value="no" />

<c:if test="${fn:containsIgnoreCase(componenteFamiglia.statoCivile, 'coniugat')}">
	<c:set var="mostraLink" value="si" />
	<c:set var="mostraLinkReportPdf" value="no" />
</c:if>
<%@ include file="../common/formrichiestacertificato.jsp"%>