<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoResidenzaInConvivenzaReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<%--RESOURCE UTILIZZATA PER LA DEMO DEL 26/01/2015 --%>
<portlet:resourceURL var="reportPdfDEMOURL" id="reportPdfDEMO" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>



<c:set var="idForm" value="richiestaCertificatoResidenzaInConvivenza" />

<c:set var="mostraLink" value="no" />	

<c:if test="${empty componenteFamiglia.dataMorte  }">
	<c:set var="mostraLinkReportPdf" value="no" />
	<c:set var="mostraLink" value="si" />
</c:if>

<%@ include file="../common/formrichiestacertificato.jsp"%>
<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoResidenzaInConvivenzaReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>
