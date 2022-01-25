<%@ include file="../common/common.jsp"%>

<div class="mainDiv">
	<div>
		<c:choose>
			<c:when test="${esito == 'ok'}">
				<div class="portlet-msg-success">
					<spring:message code="label.pagamento.ok"/>
				</div>
			</c:when>
			<c:otherwise>
				<div class="portlet-msg-error">
					<spring:message code="label.pagamento.ko"/>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
	<table class="esitoPagamentoTable">
		<c:forEach var="entry" items="${esitoMap}">
			<tr>
				<td>
					<c:out value="${entry.key}"/>:
				</td>
				<td>
					<c:out value="${entry.value}"/>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="container_pulsante">
		<portlet:renderURL var="homeURL">
		</portlet:renderURL>
		<a href="/group/guest/home" class="custom_pulsante">
			<spring:message code="label.homePage"  />
		</a>  
		<%@ include file="../common/valutaservizio.jsp"%>
	</div>
</div>
