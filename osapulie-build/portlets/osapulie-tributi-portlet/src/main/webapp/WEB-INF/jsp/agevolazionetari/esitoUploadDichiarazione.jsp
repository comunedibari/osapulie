<%@ include file="../common/common.jsp"%>

<div class="portlet-msg-success">
	<spring:message code="label.esito"/>
</div>

<div class="container_pulsante">
	<a class="custom_pulsante" href="<portlet:renderURL portletMode="view"/>"><spring:message code="button.home"/></a>
	<%@ include file="../common/valutaservizio.jsp"%>
</div>