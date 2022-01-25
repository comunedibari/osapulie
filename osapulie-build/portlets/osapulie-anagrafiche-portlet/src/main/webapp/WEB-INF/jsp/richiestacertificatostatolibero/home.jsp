<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoStatoLiberoReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>
<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="richiestaCertificatoStatoLibero" />
	
<c:set var="mostraLink" value="no" />	
<c:set var="mostraLinkReportPdf" value="no" />

<c:if test="${empty componenteFamiglia.dataMorte && (fn:containsIgnoreCase(componenteFamiglia.statoCivile,'celibe') || fn:containsIgnoreCase(componenteFamiglia.statoCivile,'nubile'))}">
	<c:set var="mostraLink" value="si" />	
	<c:set var="mostraLinkReportPdf" value="no" />
</c:if>

<%@ include file="../common/formrichiestacertificato.jsp"%>