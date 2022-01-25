<%@ include file="../common/common.jsp"%>
<c:if test="${not empty querstringParametersMap}">
	<div class="mainDiv">
		<fieldset>
			<legend><spring:message code="label.parametri"/></legend>
			<table class="errorsTable">
				<c:forEach items="${querstringParametersMap}" var="mapItem">
					<tr>
						<td class="title"><c:out value="${mapItem.key}"/></td>
						<td><c:out value="${mapItem.value}"/></td>
					</tr>
				</c:forEach>
			</table>
		</fieldset>
	</div>
</c:if>