<%@ include file="../common/common.jsp"%>
<div class="mainDiv">
	<c:if test="${not empty servizioUrl}">
		<div class="navigazioneServizioDiv">
			<a href="${servizioUrl}<c:if test="${!empty comuneSelezionato}">?${PortletConstants.COMUNE_ISA_PARAMETER_NAME}=${comuneSelezionato.codiceIstat}</c:if>"><spring:message code="label.vaiAlServizio"/>&nbsp;<i class="fa fa-angle-double-right"></i></a>
		</div>
	</c:if>
</div>