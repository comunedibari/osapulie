<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="richiestaCertificatoUrl"></portlet:actionURL>

<portlet:resourceURL var="certificatoReportURL" id="certificatoIscrizioneListeElettoraliReport" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<portlet:resourceURL var="certificatoSaveSignatureURL" id="certificatoIscrizioneListeSaveSignature" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>


<portlet:resourceURL var="reportPdfURL" id="reportPdf" escapeXml="false">
	<portlet:param name="codFisc" value="${componenteFamiglia.codiceFiscale}" />
</portlet:resourceURL>

<c:set var="idForm" value="richiestaCertificatoIscrizioneListeElettorali" />

<c:set var="mostraLink" value="no" />	

<c:if test="${empty componenteFamiglia.dataMorte  }">
	<c:set var="mostraLinkReportPdf" value="no" />
	<c:set var="mostraLink" value="si" />
</c:if>

<%@ include file="../common/formrichiestacertificato.jsp"%>