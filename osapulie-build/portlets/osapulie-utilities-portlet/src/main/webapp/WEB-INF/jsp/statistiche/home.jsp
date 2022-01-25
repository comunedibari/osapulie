<%@ include file="../common/common.jsp"%>
<div class="mainDiv">
	<fieldset>
		<legend><spring:message code="label.statistiche"/></legend>
		<div class="tableDiv">
			<table>
				<tr class="bold">
					<td><spring:message code="label.oggetto"/></td>
					<td><spring:message code="label.valore"/></td>
				</tr>
				<c:forEach items="${statisticheMap}" var="entry">
					<tr>
						<td><c:out value="${entry.key}"/></td>
						<td><c:out value="${entry.value}"/></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</fieldset>
</div>