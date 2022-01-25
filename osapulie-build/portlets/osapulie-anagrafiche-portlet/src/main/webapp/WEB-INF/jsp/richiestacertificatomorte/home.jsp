<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoMorteReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="certificatoSaveSignatureURL" id="certificatoMorteSaveSignature" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>


<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="richiestaCertificatoMorte" />

<c:set var="mostraLink" value="no" />	
<c:set var="mostraLinkReportPdf" value="no" />

<c:if test="${! empty componenteFamiglia.dataMorte  }">
	<c:choose>
		<c:when test="${empty dataNascitaCondition}">
			<c:set var="mostraLink" value="si" />
		</c:when>
	</c:choose>	
	<c:set var="mostraLinkReportPdf" value="no" />
</c:if>
<%@ include file="../common/formrichiestacertificato.jsp"%>