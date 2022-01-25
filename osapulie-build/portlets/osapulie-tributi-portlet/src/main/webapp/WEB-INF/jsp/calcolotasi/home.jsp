<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="calcoloTasiUrl">
	<portlet:param name="action" value="getCalcoloTasiForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${calcoloTasiUrl}" class="custom_pulsante">
			<spring:message code="button.dichiarazione" />
		</a>
	</div>
</div>