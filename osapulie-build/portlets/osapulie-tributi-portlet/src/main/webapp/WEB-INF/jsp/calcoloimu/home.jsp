<%@ include file="../common/common.jsp"%>

<portlet:actionURL var="calcoloImuUrl">
	<portlet:param name="action" value="getCalcoloImuForm" />
</portlet:actionURL>

<div class="mainDiv">
	<div class="container_pulsante">
		<a href="${calcoloImuUrl}" class="custom_pulsante"><spring:message code="button.dichiarazione" /></a>
	</div>
</div>